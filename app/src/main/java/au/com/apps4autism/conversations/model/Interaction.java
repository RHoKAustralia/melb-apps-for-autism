package au.com.apps4autism.conversations.model;

import java.util.Map;

public class Interaction {

    private Map<String,Boolean> mQuestions;
    private String mAnswer;
    private String mAnswerAudioPath;

    public Interaction(Map<String,Boolean> questions, String answer, String audioPath) {
        mQuestions = questions;
        mAnswer = answer;
        mAnswerAudioPath = audioPath;
    }

    public Map<String,Boolean> getQuestions() {
        return mQuestions;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public String getAnswerAudioPath() {
        return mAnswerAudioPath;
    }

}
