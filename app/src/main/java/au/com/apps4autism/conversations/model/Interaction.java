package au.com.apps4autism.conversations.model;

import java.util.ArrayList;

public class Interaction {

    ArrayList<Option> mOptions;

    public Interaction() {
        mOptions = new ArrayList<Option>();
    }

    public void addOption(Option option) {
        mOptions.add(option);
    }

    public ArrayList<Option> getOptions() {
        return mOptions;
    }
}
