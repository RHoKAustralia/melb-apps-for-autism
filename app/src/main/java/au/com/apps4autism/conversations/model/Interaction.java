package au.com.apps4autism.conversations.model;

import java.util.ArrayList;

public class Interaction {
    private ArrayList<Question> mQuestions;
    private String mAnswer;
    private String mAnswerAudioPath;

    public Interaction(ArrayList<Question> questions, String answer, String audioPath) {
        mQuestions = questions;
        mAnswer = answer;
        mAnswerAudioPath = audioPath;
    }

    public ArrayList<Question> getQuestions() {
        return mQuestions;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public String getAnswerAudioPath() {
        return mAnswerAudioPath;
    }

}
