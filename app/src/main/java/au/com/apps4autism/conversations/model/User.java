package au.com.apps4autism.conversations.model;

public class User {

    public static final String male = "Male";
    public static final String female = "Female";

    private String mName;
    private int mAge;
    private String mGender;

    public User(String name, int age, String gender) {
        mName = name;
        mAge = age;
        mGender = gender;
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
