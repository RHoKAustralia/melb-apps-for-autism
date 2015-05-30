package au.com.apps4autism.conversations.model;

import java.util.Map;
import java.util.HashMap;

public class Themes {

    Map<String,String> mAvailableThemes;

    public Themes() {
        mAvailableThemes = new HashMap<String, String>();
    }

    public void addTheme(String name, String imagePath) {
        mAvailableThemes.put(name,imagePath);
    }

    public Map<String,String> getThemes() {
        return mAvailableThemes;
    }
}
