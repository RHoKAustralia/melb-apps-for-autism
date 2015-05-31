package au.com.apps4autism.conversations.view.topics;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import au.com.apps4autism.conversations.R;
import au.com.apps4autism.conversations.model.Theme;
import au.com.apps4autism.conversations.util.database.DatabaseManager;
import au.com.apps4autism.conversations.view.conversation.ConversationActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class TopicActivity extends AppCompatActivity {
    public static final String LEVEL_NUM_KEY = "levelNum";

    @InjectView(R.id.topic_grid)
    GridView mTopicGrid;
    ArrayList<Theme> mThemes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);
        ButterKnife.inject(this);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final int levelNum = getIntent().getExtras().getInt(LEVEL_NUM_KEY, 1);

        DatabaseManager databaseManager = new DatabaseManager(this);
        try {
            databaseManager.open();
            mThemes = databaseManager.getThemes(0, levelNum);
            databaseManager.close();
        } catch (SQLException e) {
            mThemes = databaseManager.getThemes(0, levelNum);
        }

        TopicAdapter adapter = new TopicAdapter(this, mThemes);

        mTopicGrid.setAdapter(adapter);
        mTopicGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                ImageView imageView = ButterKnife.findById(view, R.id.topic_image);

                Intent intent = new Intent(TopicActivity.this, ConversationActivity.class);
                intent.putExtra(ConversationActivity.LEVEL_NUM_KEY, levelNum);
                intent.putExtra(ConversationActivity.THEME_KEY, mThemes.get(position));
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(TopicActivity.this, imageView, "topic_image");
                startActivity(intent, options.toBundle());
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
