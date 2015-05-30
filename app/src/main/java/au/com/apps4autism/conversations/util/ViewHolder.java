package au.com.apps4autism.conversations.util;

import android.util.SparseArray;
import android.view.View;

/***
 * Generic view holder to hold whatever we want inside the tag of the view
 * fetchable via ID
 */
public class ViewHolder {
    // generic return type to reduce the casting noise in client code
    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = getHolderArray(view);
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }

    private static SparseArray<View> getHolderArray(View view) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray();
            view.setTag(viewHolder);
        }
        return viewHolder;
    }
}