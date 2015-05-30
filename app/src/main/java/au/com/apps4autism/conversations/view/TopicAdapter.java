package au.com.apps4autism.conversations.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import au.com.apps4autism.conversations.R;
import au.com.apps4autism.conversations.util.ViewHolder;

/**
 * Created by tim on 30/05/15.
 */
public class TopicAdapter extends ArrayAdapter<String> {
    private final LayoutInflater mLayoutInflater;

    public TopicAdapter(Context context) {
        super(context, 0);

        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.grid_item_topic, parent, false);
        }

        TextView topicText = ViewHolder.get(convertView, R.id.topic_text);
        topicText.setText(getItem(position));

        return convertView;
    }
}
