package au.com.apps4autism.conversations.view.conversation;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import au.com.apps4autism.conversations.R;
import au.com.apps4autism.conversations.util.ViewHolder;

/**
 * Created by tim on 30/05/15.
 */
public class ConversationAdapter extends ArrayAdapter<String> {
    private final LayoutInflater mLayoutInflater;

    public ConversationAdapter(Context context) {
        super(context, 0);

        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_chat, parent, false);
        }

        String message = getItem(position);

        TextView questionText = ViewHolder.get(convertView, R.id.question_text);
        questionText.setText(message);

        CardView card = ViewHolder.get(convertView, R.id.chat_card);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)card.getLayoutParams();
        if(position % 2 == 1) {
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        } else {
            params.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        }
        card.setLayoutParams(params);

        return convertView;
    }
}
