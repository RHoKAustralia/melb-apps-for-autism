package au.com.apps4autism.conversations.view.conversation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Iterator;

import au.com.apps4autism.conversations.R;
import au.com.apps4autism.conversations.model.Conversation;
import au.com.apps4autism.conversations.model.Interaction;
import au.com.apps4autism.conversations.model.Question;
import au.com.apps4autism.conversations.model.Theme;
import au.com.apps4autism.conversations.util.database.DatabaseManager;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by tim on 30/05/15.
 */
public class ConversationActivity extends AppCompatActivity {
    public static final String LEVEL_NUM_KEY = "levelNum";
    public static final String THEME_KEY = "theme";

    @InjectView(R.id.conversation_list)
    ListView mConversationList;
    @InjectView(R.id.options_list)
    ListView mOptionsList;
    @InjectView(R.id.play_button)
    FloatingActionButton mPlayButton;
    @InjectView(R.id.conversation_root)
    RelativeLayout mRootLayout;
    //@InjectView(R.id.topic_image)
    //ImageView mTopicImage;

    private Conversation mConversation;
    private Iterator<Interaction> mInteractions;
    private ConversationAdapter mConversationAdapter;
    private OptionsAdapter mOptionsAdapter;
    private Interaction mCurrentInteraction;
    private String ttsContent;
    private TextToSpeech mTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        ButterKnife.inject(this);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int levelNum = getIntent().getExtras().getInt(LEVEL_NUM_KEY);
        Theme theme = (Theme)getIntent().getExtras().getSerializable(THEME_KEY);

        getSupportActionBar().setTitle(theme.getName());

        DatabaseManager databaseManager = new DatabaseManager(this);
        try {
            databaseManager.open();
            mConversation = databaseManager.getConversation(theme.getName(), levelNum);
            databaseManager.close();
        } catch (Exception e) {
            mConversation = databaseManager.getConversation(theme.getName(), levelNum);
        }

        mInteractions = mConversation.getInteractions().iterator();

        final String statement = mConversation.getStatement();
        ttsContent = statement;

        mTts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                mTts.speak(ttsContent, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTts.speak(ttsContent, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        mRootLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mPlayButton.getLayoutParams();
                layoutParams.setMargins(0,
                        (int)mOptionsList.getY() - getResources().getDimensionPixelSize(R.dimen.fab_half_size),
                        getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin), 0);
                mPlayButton.setLayoutParams(layoutParams);
            }
        });

        mConversationAdapter = new ConversationAdapter(this);
        mConversationAdapter.add(statement);
        mConversationList.setAdapter(mConversationAdapter);

        mCurrentInteraction = mInteractions.next();

        mOptionsAdapter = new OptionsAdapter(this);
        mOptionsAdapter.addAll(mCurrentInteraction.getQuestions());
        mOptionsList.setAdapter(mOptionsAdapter);
        mOptionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Question question = mOptionsAdapter.getItem(position);
                if(question.isCorrect()) {
                    mConversationAdapter.add(question.getText());
                    mConversationAdapter.add(mCurrentInteraction.getAnswer());
                    ttsContent = mCurrentInteraction.getAnswer();
                    mTts.speak(ttsContent, TextToSpeech.QUEUE_FLUSH, null);

                    mConversationList.post(new Runnable() {
                        @Override
                        public void run() {
                            // Select the last row so it will scroll into view...
                            mConversationList.setSelection(mConversationAdapter.getCount() - 1);
                        }
                    });

                    if(mInteractions.hasNext()) {
                        mCurrentInteraction = mInteractions.next();
                        mOptionsAdapter.clear();
                        mOptionsAdapter.addAll(mCurrentInteraction.getQuestions());
                    } else {
                        mOptionsAdapter.clear();
                        mPlayButton.setVisibility(View.GONE);
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(new Runnable() {
                             @Override
                             public void run() {
                                 setResult(RESULT_OK);
                                 finish();
                             }
                         }, 500);
                        Toast.makeText(ConversationActivity.this, "You win!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(ConversationActivity.this, "Try again", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}