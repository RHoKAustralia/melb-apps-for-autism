package au.com.apps4autism.conversations.model;

import java.util.LinkedList;

public class Conversation {

    private String mStatement;
    private String mStatementAudioPath;
    private LinkedList<Interaction> mInteractions;

    public Conversation(String statement, String statementAudioPath) {
        mStatement = statement;
        mStatementAudioPath = statementAudioPath;
        mInteractions = new LinkedList<Interaction>();
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

    public Interaction getInteraction(Interaction interaction) {
        return mInteractions.pollFirst(); // Returns null if no element is found
    }

}
