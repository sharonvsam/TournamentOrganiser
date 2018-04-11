package tournamentRDB;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VSharon on 3/20/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tournamentorganiser.db";

    private TournamentHelper tournamentHelper;
    private TeamHelper teamHelper;
    private TournamentsTeamsHelper tournamentsTeamsHelper;
    private LevelHelper levelHelper;
    private GroupHelper groupHelper;
    private PlayerHelper playerHelper;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        tournamentHelper = new TournamentHelper(db);
        teamHelper = new TeamHelper(db);
        tournamentsTeamsHelper = new TournamentsTeamsHelper(db);
        levelHelper = new LevelHelper(db);
        groupHelper = new GroupHelper(db);
        playerHelper = new PlayerHelper(db);

        tournamentHelper.VerifyTableSchema();
        teamHelper.VerifyTableSchema();
        tournamentsTeamsHelper.VerifyTableSchema();
        levelHelper.VerifyTableSchema();
        groupHelper.VerifyTableSchema();
        playerHelper.VerifyTableSchema();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        tournamentHelper = new TournamentHelper(db);
        teamHelper = new TeamHelper(db);
        tournamentsTeamsHelper = new TournamentsTeamsHelper(db);
        levelHelper = new LevelHelper(db);
        groupHelper = new GroupHelper(db);
        playerHelper = new PlayerHelper(db);

        tournamentHelper.VerifyTableSchema(oldVersion, newVersion);
        teamHelper.VerifyTableSchema(oldVersion, newVersion);
        tournamentsTeamsHelper.VerifyTableSchema(oldVersion, newVersion);
        levelHelper.VerifyTableSchema(oldVersion, newVersion);
        groupHelper.VerifyTableSchema(oldVersion, newVersion);
        playerHelper.VerifyTableSchema(oldVersion, newVersion);
    }
}
