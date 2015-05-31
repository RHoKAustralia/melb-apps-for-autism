package au.com.apps4autism.conversations.view.topics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import au.com.apps4autism.conversations.R;
import au.com.apps4autism.conversations.model.Theme;
import au.com.apps4autism.conversations.util.ViewHolder;

/**
 * Created by tim on 30/05/15.
 */
public class TopicAdapter extends ArrayAdapter<Theme> {
    private final LayoutInflater mLayoutInflater;

    public TopicAdapter(Context context, List<Theme> themes) {
        super(context, 0, themes);

        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.grid_item_topic, parent, false);
        }

        Theme theme = getItem(position);

        TextView topicText = ViewHolder.get(convertView, R.id.topic_text);
        topicText.setText(theme.getName());
        Picasso.with(getContext()).load(theme.getImagePath()).error(R.drawable.stock_cinema);

        return convertView;
    }
}
