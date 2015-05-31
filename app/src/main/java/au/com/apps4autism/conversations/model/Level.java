package au.com.apps4autism.conversations.model;

/**
 * Created by tim on 31/05/15.
 */
public class Level {
    private String name;
    private int difficulty;
    private boolean completed;
    private boolean locked;

    public Level(String name, int difficulty, boolean completed, boolean locked) {
        this.name = name;
        this.difficulty = difficulty;
        this.completed = completed;
        this.locked = locked;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
