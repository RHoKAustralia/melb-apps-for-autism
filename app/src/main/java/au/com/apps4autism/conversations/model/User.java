package au.com.apps4autism.conversations.model;

public class User {

    public static final String male = "Male";
    public static final String female = "Female";

    private long mId;
    private String mName;
    private int mAge;
    private String mGender;
    private int mCurrentLevel;
    private int mCurrentProgress;

    public User(long id, String name, int age, String gender, int currentLevel, int currentProgress) {
        mId = id;
        mName = name;
        mAge = age;
        mGender = gender;
        mCurrentLevel = currentLevel;
        mCurrentProgress = currentProgress;
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }
    
    public int getAge() {
        return mAge;
    }
    
    public String getGender() {
        return mGender;
    }

    public int getCurrentLevel() {
        return mCurrentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        mCurrentLevel = currentLevel;
    }

    public int getCurrentProgress() {
        return mCurrentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        mCurrentProgress = currentProgress;
    }
}
