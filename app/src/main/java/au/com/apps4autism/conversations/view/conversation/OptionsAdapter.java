package au.com.apps4autism.conversations.view.conversation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import au.com.apps4autism.conversations.model.Question;

/**
 * Created by tim on 30/05/15.
 */
public class OptionsAdapter extends ArrayAdapter<Question> {
    private final LayoutInflater mLayoutInflater;

    public OptionsAdapter(Context context) {
        super(context, 0);

        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = mLayoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        ((TextView)convertView).setText(getItem(position).getText());

        return convertView;
    }
}
