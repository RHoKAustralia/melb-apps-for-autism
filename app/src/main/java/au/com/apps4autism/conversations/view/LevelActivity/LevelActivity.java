package au.com.apps4autism.conversations.view.LevelActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import au.com.apps4autism.conversations.R;
import au.com.apps4autism.conversations.model.User;
import au.com.apps4autism.conversations.util.database.DatabaseManager;
import au.com.apps4autism.conversations.view.themes.ThemeActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class LevelActivity extends AppCompatActivity {
    @InjectView(R.id.level_list)
    ListView mLevelList;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        ButterKnife.inject(this);

        DatabaseManager databaseManager = new DatabaseManager(this);
        try {
            databaseManager.open();
            mUser = databaseManager.getUser("Sam");
            databaseManager.close();
        } catch (Exception e) {
            mUser = databaseManager.getUser("Sam");
        }

        LevelAdapter levelAdapter = new LevelAdapter(this, databaseManager.getLevels());
        mLevelList.setAdapter(levelAdapter);

        mLevelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(LevelActivity.this, ThemeActivity.class);
                intent.putExtra(ThemeActivity.LEVEL_NUM_KEY, i + 1);
                startActivity(intent);
            }
        });

    }

}
