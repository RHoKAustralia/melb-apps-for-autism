package au.com.apps4autism.conversations.view.themes;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import au.com.apps4autism.conversations.R;
import au.com.apps4autism.conversations.model.Level;
import au.com.apps4autism.conversations.model.Theme;
import au.com.apps4autism.conversations.util.database.DatabaseManager;
import au.com.apps4autism.conversations.view.conversation.ConversationActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class ThemeActivity extends AppCompatActivity {
    public static final String LEVEL_NUM_KEY = "levelNum";

    @InjectView(R.id.topic_grid)
    GridView mTopicGrid;
    private ThemeAdapter mAdapter;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            mAdapter.getItem(requestCode).setComplete(true);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);
        ButterKnife.inject(this);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final int levelNum = getIntent().getExtras().getInt(LEVEL_NUM_KEY, 1);

        DatabaseManager databaseManager = new DatabaseManager(this);
        ArrayList<Theme> themes;
        try {
            databaseManager.open();
            themes = databaseManager.getThemes(0, levelNum);
            databaseManager.close();
        } catch (SQLException e) {
            themes = databaseManager.getThemes(0, levelNum);
        }

        Level level = databaseManager.getLevels().get(levelNum - 1);
        getSupportActionBar().setTitle(level.getName());

        mAdapter = new ThemeAdapter(this, themes);

        mTopicGrid.setAdapter(mAdapter);
        mTopicGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(ThemeActivity.this, ConversationActivity.class);
                intent.putExtra(ConversationActivity.LEVEL_NUM_KEY, levelNum);
                intent.putExtra(ConversationActivity.THEME_KEY, mAdapter.getItem(position));
                startActivityForResult(intent, position);
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
