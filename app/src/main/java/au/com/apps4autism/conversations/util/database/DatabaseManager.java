package au.com.apps4autism.conversations.util.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.content.Context;
import android.content.ContentValues;
import android.os.Environment;

import java.util.ArrayList;

import au.com.apps4autism.conversations.model.User;
import au.com.apps4autism.conversations.model.Theme;
import au.com.apps4autism.conversations.model.Conversation;
import au.com.apps4autism.conversations.model.Interaction;
import au.com.apps4autism.conversations.model.Question;

public class DatabaseManager {

    private SQLiteDatabase database;
    private DatabaseHelper databaseHelper;

    static final String storageDirectory = Environment.getExternalStorageDirectory().getPath() + "/ChitChat/";
    static final String themeDirectory = storageDirectory + "/images/themes/";
    static final String audioDirectory = storageDirectory + "/audio/";

    static final String DB_NAME = "appdata.db";
    static final String DB_PATH = storageDirectory + "/db/";
    static final int DB_VERSION = 1;

    static final String TABLE_USER = "user";
    static final String TABLE_THEME = "theme";
    static final String TABLE_STATEMENT = "statement";
    static final String TABLE_INTERACTION = "interaction";
    static final String TABLE_USERPROGRESS = "userprogress";

    static final String COLUMN_ID = "_id";
    static final String COLUMN_USER_ID = "user_id";
    static final String COLUMN_THEME_ID = "theme_id";
    static final String COLUMN_STATEMENT_ID = "statement_id";
    static final String COLUMN_NAME = "name";
    static final String COLUMN_AGE = "age";
    static final String COLUMN_GENDER = "gender";
    static final String COLUMN_CURRENT_LEVEL = "current_level";
    static final String COLUMN_CURRENT_PROGRESS = "current_progress";
    static final String COLUMN_THEME = "theme";
    static final String COLUMN_LEVEL = "level";
    static final String COLUMN_STATEMENT = "text";
    static final String COLUMN_QUESTION = "question_text";
    static final String COLUMN_ANSWER = "answer_text";
    static final String COLUMN_IS_CORRECT = "is_correct";
    static final String COLUMN_IS_COMPLETE = "is_correct";
    static final String COLUMN_IMAGE_ASSET = "image_asset";
    static final String COLUMN_AUDIO_ASSET = "audio_asset";

    // Table columns
    private String[] TABLE_USER_COLUMNS =
            {COLUMN_ID,
            COLUMN_NAME,
            COLUMN_AGE,
            COLUMN_GENDER,
            COLUMN_CURRENT_LEVEL,
            COLUMN_CURRENT_PROGRESS};

    private String[] TABLE_THEME_COLUMNS =
            {COLUMN_ID,
            COLUMN_THEME,
            COLUMN_IMAGE_ASSET,
            };

    private String[] TABLE_STATEMENT_COLUMNS =
            {COLUMN_ID,
            COLUMN_THEME_ID,
            COLUMN_LEVEL,
            COLUMN_STATEMENT,
            COLUMN_AUDIO_ASSET};

    private String[] TABLE_INTERACTION_COLUMNS =
            {COLUMN_ID,
            COLUMN_STATEMENT_ID,
            COLUMN_QUESTION,
            COLUMN_ANSWER,
            COLUMN_IS_CORRECT,
            COLUMN_AUDIO_ASSET};

    private String[] TABLE_USERPROGRESS_COLUMNS =
            {COLUMN_ID,
            COLUMN_USER_ID,
            COLUMN_THEME_ID,
            COLUMN_LEVEL,
            COLUMN_IS_COMPLETE,};

    // Create table statements
    static final String CREATE_TABLE_USER = "create table "
            + TABLE_USER + " ("
            + COLUMN_ID + " integer primary key autoincrement not null, "
            + COLUMN_NAME  + " text, "
            + COLUMN_AGE + " integer, "
            + COLUMN_GENDER + " text, "
            + COLUMN_CURRENT_LEVEL + " integer, "
            + COLUMN_CURRENT_PROGRESS + " integer);";

    static final String CREATE_TABLE_THEME = "create table "
            + TABLE_THEME + " ("
            + COLUMN_ID + " integer primary key autoincrement not null, "
            + COLUMN_THEME + " text, "
            + COLUMN_IMAGE_ASSET + " text);";

    static final String CREATE_TABLE_STATEMENT = "create table "
            + TABLE_STATEMENT + " ("
            + COLUMN_ID + " integer primary key autoincrement not null, "
            + COLUMN_THEME_ID  + " integer, "
            + COLUMN_LEVEL + " integer, "
            + COLUMN_STATEMENT + " text, "
            + COLUMN_AUDIO_ASSET + " text);";

    static final String CREATE_TABLE_INTERACTION = "create table "
            + TABLE_INTERACTION + " ("
            + COLUMN_ID + " integer primary key autoincrement not null, "
            + COLUMN_STATEMENT_ID + " integer, "
            + COLUMN_QUESTION + " text, "
            + COLUMN_ANSWER + " text, "
            + COLUMN_IS_CORRECT + " boolean, "
            + COLUMN_AUDIO_ASSET + " text);";

    static final String CREATE_TABLE_USERPROGRESS = "create table "
            + TABLE_USERPROGRESS + " ("
            + COLUMN_ID + " integer primary key autoincrement not null, "
            + COLUMN_USER_ID + " integer, "
            + COLUMN_THEME_ID + " integer, "
            + COLUMN_LEVEL + " integer, "
            + COLUMN_IS_COMPLETE + " boolean);";
    
    public DatabaseManager(Context context) {
        databaseHelper = DatabaseHelper.getInstance(context);
}

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
    }

    public User createUser(String name, int age, String gender) {
        ContentValues Values = new ContentValues();
        Values.put(COLUMN_NAME, name);
        Values.put(COLUMN_AGE, age);
        Values.put(COLUMN_GENDER, gender);
        Values.put(COLUMN_CURRENT_LEVEL, 1);
        Values.put(COLUMN_CURRENT_PROGRESS, 0);
        long uID = database.insert(TABLE_USER, null, Values);
        User user = new User(uID, name, age, gender, 1, 0);
        return user;
    }

    public User getUser(String userName) {
        // Example SQL

        /*String name;
        int age;
        String gender;
        String whereClause = COLUMN_NAME + " =?";
        String[] whereArgs = new String[]{userName};

        Cursor cursor = database.query(TABLE_USER, TABLE_USER_COLUMNS, whereClause, whereArgs, null, null, null);
        cursor.moveToFirst();

        name = cursor.getString(1);
        age = cursor.getInt(2);
        gender = cursor.getString(3);

        cursor.close();*/

        User user = new User(0, "Sam", 15, User.male, 1, 0);

        return user;
    }

    public ArrayList<Theme> getThemes(long id, int level) {

        ArrayList<Theme> themes = new ArrayList<Theme>();

        // SQL to go here

        // Prepare example theme object

        themes.add(new Theme("Birthdays", themeDirectory + "birthday.png", false));
        themes.add(new Theme("Movies", themeDirectory + "movie.png", false));

        return themes;
    }

    public Conversation getConversation(String theme, int level) {

        // SQL to go here

        // Prepare example conversation object
        String statementAudioPath = audioDirectory + "birthday_statement.wav";
        Conversation conversation = new Conversation("It's my birthday", statementAudioPath);

        ArrayList<Question> questions = new ArrayList<Question>();
        questions.add(new Question("How old are you?", true));
        questions.add(new Question("What year were you born?", false));
        String answer = "I am 15 years old";
        String answerAudioPath = audioDirectory + "birthday.wav";

        Interaction interaction = new Interaction(questions,answer,answerAudioPath);
        conversation.addInteraction(interaction);

        return conversation;
    }
}
