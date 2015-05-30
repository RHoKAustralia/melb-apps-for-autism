package au.com.apps4autism.conversations.model;

public class User {

    public static final String male = "Male";
    public static final String female = "Female";

    private String mName;
    private int mAge;
    private String mGender;
    private int mCurrentLevel;
    private int mCurrentProgress;

    public User(String name, int age, String gender) {
        this(name, age, gender, 1, 0);
    }

    public User(String name, int age, String gender, int currentLevel, int currentProgress) {
        mName = name;
        mAge = age;
        mGender = gender;
        mCurrentLevel = currentLevel;
        mCurrentProgress = currentProgress;
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

    public int getCurrentProgress() {
        return mCurrentProgress;
    }
}
