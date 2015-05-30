package au.com.apps4autism.conversations.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.util.Log;

import au.com.apps4autism.conversations.R;
import au.com.apps4autism.conversations.util.database.DatabaseManager;
import au.com.apps4autism.conversations.model.User;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class LevelActivity extends AppCompatActivity {
    @InjectView(R.id.level_list)
    ListView mLevelList;
    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        ButterKnife.inject(this);

        DatabaseManager databaseManager = new DatabaseManager(this);
        databaseManager.open();
        mUser = databaseManager.getUser("Sam");
        databaseManager.close();

        ArrayAdapter levelAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        levelAdapter.add("Level One");
        levelAdapter.add("Level Two");
        mLevelList.setAdapter(levelAdapter);
        mLevelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(LevelActivity.this, TopicActivity.class);
                startActivity(intent);
            }
        });

    }

}
