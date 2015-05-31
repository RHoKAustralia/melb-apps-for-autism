package au.com.apps4autism.conversations.model;

import java.util.ArrayList;

public class Conversation {

    private String mStatement;
    private String mStatementAudioPath;
    private ArrayList<Interaction> mInteractions;

    public Conversation(String statement, String statementAudioPath) {
        mStatement = statement;
        mStatementAudioPath = statementAudioPath;
        mInteractions = new ArrayList<Interaction>();
    }

    public String getStatement() {
        return mStatement;
    }

    public String getStatementAudioPath() {
        return mStatementAudioPath;
    }

    public void addInteraction(Interaction interaction) {
        mInteractions.add(interaction);
    }

    public ArrayList<Interaction> getInteractions() {
        return mInteractions;
    }

}
