package au.com.apps4autism.conversations.model;

import java.io.Serializable;

public class Theme implements Serializable {

    String mName;
    String mImagePath;

    public Theme(String name, String imagePath) {
        mName = name;
        mImagePath = imagePath;
    }

    public String getName() {
        return mName;
    }

    public String getImagePath() {
        return mName;
    }
}
