package au.com.apps4autism.conversations.view;

import android.content.Intent;
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
import butterknife.ButterKnife;
import butterknife.InjectView;

public class TopicActivity extends AppCompatActivity {
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

        DatabaseManager databaseManager = new DatabaseManager(this);
        databaseManager.open();
        mThemes = databaseManager.getThemes(1);
        databaseManager.close();

        TopicAdapter adapter = new TopicAdapter(this);

        for(int i = 0; i < mThemes.size(); i++) {
            adapter.add(mThemes.get(i).getName());
        }

        mTopicGrid.setAdapter(adapter);
        mTopicGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ImageView imageView = ButterKnife.findById(view, R.id.topic_image);

                Intent intent = new Intent(TopicActivity.this, ConversationActivity.class);
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
