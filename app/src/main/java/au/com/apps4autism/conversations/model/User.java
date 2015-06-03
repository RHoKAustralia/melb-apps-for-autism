package au.com.apps4autism.conversations.model;

public class User {

    public static final String male = "Male";
    public static final String female = "Female";

    private long mId;
    private String mName;
    private int mAge;
    private String mGender;

    public User(long id, String name, int age, String gender) {
        mId = id;
        mName = name;
        mAge = age;
        mGender = gender;
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
}
