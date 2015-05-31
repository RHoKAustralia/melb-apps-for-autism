package au.com.apps4autism.conversations.view;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import au.com.apps4autism.conversations.R;
import au.com.apps4autism.conversations.model.Conversation;
import au.com.apps4autism.conversations.model.Interaction;
import au.com.apps4autism.conversations.model.Question;
import au.com.apps4autism.conversations.util.database.DatabaseManager;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by tim on 30/05/15.
 */
public class ConversationActivity extends AppCompatActivity {
    @InjectView(R.id.conversation_list)
    ListView mConversationList;
    @InjectView(R.id.question_text)
    TextView mQuestionTextView;
    @InjectView(R.id.play_button)
    FloatingActionButton mPlayButton;
    @InjectView(R.id.conversation_root)
    RelativeLayout mRootLayout;
    @InjectView(R.id.topic_image)
    ImageView mTopicImage;

    Conversation mConversation;
    ArrayList<Interaction> mInteractions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        ButterKnife.inject(this);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DatabaseManager databaseManager = new DatabaseManager(this);
        databaseManager.open();
        mConversation = databaseManager.getConversation("Birthdays", 1);
        databaseManager.close();

        mInteractions = mConversation.getInteractions();

        final String statement = mConversation.getStatement();
        mQuestionTextView.setText(statement);

        final TextToSpeech tts = new TextToSpeech(getApplicationContext(), null);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.speak(statement, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        mRootLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mPlayButton.getLayoutParams();
                layoutParams.setMargins(0,
                        mTopicImage.getMeasuredHeight() - getResources().getDimensionPixelSize(R.dimen.fab_half_size),
                        getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin), 0);
                mPlayButton.setLayoutParams(layoutParams);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        ArrayList<Question> questions = mInteractions.get(0).getQuestions();
        adapter.add(questions.get(0).getText());
        adapter.add(questions.get(1).getText());
        mConversationList.setAdapter(adapter);
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