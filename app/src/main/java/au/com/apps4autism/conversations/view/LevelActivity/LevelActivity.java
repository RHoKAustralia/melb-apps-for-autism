package au.com.apps4autism.conversations.view.LevelActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import au.com.apps4autism.conversations.R;
import au.com.apps4autism.conversations.model.Level;
import au.com.apps4autism.conversations.util.database.DatabaseManager;
import au.com.apps4autism.conversations.view.themes.ThemeActivity;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class LevelActivity extends AppCompatActivity {
    @InjectView(R.id.level_list)
    ListView mLevelList;
    private LevelAdapter mLevelAdapter;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            mLevelAdapter.getItem(requestCode).setCompleted(true);
            // if(mLevelAdapter.getCount() >= requestCode) { // TODO enable once more levels are added & handle max level
            if(requestCode == 0) {
                mLevelAdapter.getItem(requestCode + 1).setLocked(false);
            }
            mLevelAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        ButterKnife.inject(this);

        DatabaseManager databaseManager = new DatabaseManager(this);
        mLevelAdapter = new LevelAdapter(this, databaseManager.getLevels());
        mLevelList.setAdapter(mLevelAdapter);

        mLevelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Level level = mLevelAdapter.getItem(position);

                if(level.isLocked()) {
                    Toast.makeText(LevelActivity.this, "Must complete more levels to unlock", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(LevelActivity.this, ThemeActivity.class);
                    intent.putExtra(ThemeActivity.LEVEL_NUM_KEY, position + 1);
                    startActivityForResult(intent, position);
                }
            }
        });

    }

}
