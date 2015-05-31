package au.com.apps4autism.conversations.model;

public class Question {

    String mText;
    boolean mIsCorrect;

    public Question(String question, boolean isCorrect) {
        mText = question;
        mIsCorrect = isCorrect;
    }

    public boolean isCorrect() {
        return mIsCorrect;
    }

    public String getText() {
        return mText;
    }
}
