package au.com.apps4autism.conversations.view.LevelActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_level, parent, false);
        }

        Level level = getItem(position);

        TextView levelNumberText = ViewHolder.get(convertView, R.id.level_num_text);
        levelNumberText.setText(String.valueOf(level.getDifficulty()));

        TextView levelNametext = ViewHolder.get(convertView, R.id.level_name_text);
        levelNametext.setText(level.getName());

        return convertView;
    }
}
