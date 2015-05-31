package au.com.apps4autism.conversations.model;

public class Theme {

    String mName;
    String mImagePath;
    boolean mIsComplete;

    public Theme(String name, String imagePath, boolean isComplete) {
        mName = name;
        mImagePath = imagePath;
        mIsComplete = isComplete;
    }

    public String getName() {
        return mName;
    }

    public String getImagePath() {
        return mImagePath;
    }

    public boolean isComplete() {
        return mIsComplete;
    }
}
