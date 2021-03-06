package au.com.apps4autism.conversations.view.themes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

import au.com.apps4autism.conversations.R;
import au.com.apps4autism.conversations.model.Theme;
import au.com.apps4autism.conversations.util.ViewHolder;

/**
 * Created by tim on 30/05/15.
 */
public class ThemeAdapter extends ArrayAdapter<Theme> {
    private final LayoutInflater mLayoutInflater;

    public ThemeAdapter(Context context, List<Theme> themes) {
        super(context, 0, themes);

        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.grid_item_theme, parent, false);
        }

        Theme theme = getItem(position);

        ImageView imageView = ViewHolder.get(convertView, R.id.topic_image);

        File file = new File(theme.getImagePath());
        Picasso.with(getContext()).load(file).error(R.drawable.stock_cinema).into(imageView);

        if(theme.isComplete()) {
            imageView.setColorFilter(getContext().getResources().getColor(R.color.theme_complete));
        } else {
            imageView.clearColorFilter();
        }

        return convertView;
    }
}
