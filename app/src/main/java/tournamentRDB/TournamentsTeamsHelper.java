package tournamentRDB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import tournamentModels.Team;
import java.util.Arrays;
import java.util.List;

/**
 * Created by VSharon on 4/5/2018.
 */

public class TournamentsTeamsHelper {

    String tableName;
    SQLiteDatabase db;
    TeamHelper teamHelper;
    final String TOURNAMENTID_COLUMN = "TOURNAMENTID";
    final String ID_COLUMN = "ID";

    public TournamentsTeamsHelper(SQLiteDatabase database) {
        db = database;
        teamHelper = new TeamHelper(database);
        tableName = "tournamentsteams";
    }

    public void VerifyTableSchema(int oldVersion, int newVersion) {

    }

    public void VerifyTableSchema() {
        CreateTable();
    }

    void CreateTable() {
        try {
            final String createTournamentTable = "CREATE TABLE IF NOT EXISTS " + tableName
                    + "( " + ID_COLUMN + " TEXT NOT NULL"
                    + ", " + TOURNAMENTID_COLUMN + " TEXT NOT NULL"
                    + "PRIMARY KEY (" + ID_COLUMN + "," + TOURNAMENTID_COLUMN + ")";
            Log.d(tableName, createTournamentTable);
            db.execSQL(createTournamentTable);
        } catch (SQLException e) {
            throw e;
        }
    }

    void UpgradeTable() {

    }

    public void Add(String id, Team[] teams) {
        try {

            ContentValues tournamentTeamsContent = new ContentValues();
            for (Team team : teams) {
                /*if (teamHelper.Retrieve(team.ID) == null)
                    teamHelper.Add(team);*/
                if (teamHelper.Retrieve(team.ID) == null) {
                    Log.d(tableName, "Team ID: " + team.ID + "doesnot exist");
                    continue;
                }
                tournamentTeamsContent.clear();
                tournamentTeamsContent.put(TOURNAMENTID_COLUMN, id);
                tournamentTeamsContent.put(ID_COLUMN, team.ID);

                db.insert(tableName, null, tournamentTeamsContent);
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public Team[] Retrieve(String id) {

        Team[] tournamentTeams = new Team[0];
        Cursor reader = null;
        try {
            String[] COLUMNS = new String[]{TOURNAMENTID_COLUMN, ID_COLUMN};
            reader = db.query(tableName, COLUMNS, TOURNAMENTID_COLUMN + "=?", new String[]{id}, null, null, null);

            List<String> columnList = Arrays.asList(COLUMNS);
            int count = reader.getCount();
            tournamentTeams = new Team[count];
            int i = 0;
            while (reader.moveToNext()) {
                Team team = teamHelper.Retrieve(reader.getString(columnList.indexOf(TOURNAMENTID_COLUMN)));
                tournamentTeams[i] = team;
                i++;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            reader.close();
            return tournamentTeams;
        }
    }

    public void Delete(String id) {
        db.delete(tableName, TOURNAMENTID_COLUMN + "=?", new String[]{id});
    }
}
