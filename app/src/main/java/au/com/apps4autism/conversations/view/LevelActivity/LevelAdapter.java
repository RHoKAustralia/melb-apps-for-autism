package au.com.apps4autism.conversations.view.LevelActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import au.com.apps4autism.conversations.R;
import au.com.apps4autism.conversations.model.Level;
import au.com.apps4autism.conversations.util.ViewHolder;

/**
 * Created by tim on 30/05/15.
 */
public class LevelAdapter extends ArrayAdapter<Level> {
    private final LayoutInflater mLayoutInflater;

    public LevelAdapter(Context context, List<Level> levels) {
        super(context, 0, levels);

        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_level, parent, false);
        }

        Level level = getItem(position);

        TextView levelNumberText = ViewHolder.get(convertView, R.id.level_num_text);
        levelNumberText.setText(String.valueOf(level.getDifficulty()));

        TextView levelNametext = ViewHolder.get(convertView, R.id.level_name_text);
        levelNametext.setText(level.getName());

        ImageView levelStatusImage = ViewHolder.get(convertView, R.id.level_status_image);
        if (level.isLocked()) {
            convertView.setBackgroundResource(R.color.level_locked);

            levelStatusImage.setImageResource(R.drawable.ic_lock_grey600_48dp);
            levelStatusImage.setVisibility(View.VISIBLE);
        } else if (level.isCompleted()) {
            convertView.setBackgroundResource(R.color.theme_complete);
            levelStatusImage.setImageResource(R.drawable.ic_done_grey600_48dp);
            levelStatusImage.setVisibility(View.VISIBLE);
        } else {
            convertView.setBackgroundResource(android.R.color.white);
            ViewHolder.get(convertView, R.id.level_status_image).setVisibility(View.GONE);
        }

        return convertView;
    }
}
