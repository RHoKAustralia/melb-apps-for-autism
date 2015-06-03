package au.com.apps4autism.conversations.model;

public class Option {

    private String mQuestion;
    private String mAnswer;
    private String mAnswerAudioPath;
    private boolean mIsCorrect;

    public Option(String question, String answer, String answerAudioPath, boolean isCorrect) {
        mQuestion = question;
        mAnswer = answer;
        mAnswerAudioPath = answerAudioPath;
        mIsCorrect = isCorrect;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public String getmAnswerAudioPath() {
        return mAnswerAudioPath;
    }

    public boolean isCorrect() {
        return mIsCorrect;
    }
}
