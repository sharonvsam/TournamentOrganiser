package tournamentRDB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import tournamentModels.Level;
import tournamentModels.Tournament;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;


/**
 * Created by VSharon on 3/22/2018.
 */

public class LevelHelper extends BaseHelper<Level> {

    final String ID_COLUMN = "ID";
    final String NAME_COLUMN = "NAME";
    final String TOURNAMENTID_COLUMN = "TOURNAMENTID";
    final String NOOFGROUPS_COLUMN = "NOOFGROUPS";
    final String PRIORITY_COLUMN = "PRIORITY";

    GroupHelper levelGroups;

    public LevelHelper(SQLiteDatabase database) {
        super(database);
        tableName = "level";
        levelGroups = new GroupHelper(database);
    }

    @Override
    public void VerifyTableSchema(int oldVersion, int newVersion) {

    }

    @Override
    public void VerifyTableSchema() {
        CreateTable();
    }

    @Override
    void CreateTable() {
        final String createTournamentTable = "";
        Log.d(tableName, createTournamentTable);
    }

    @Override
    void UpgradeTable() {

    }

    @Override
    public void Add(Level obj) {
        try {
            ContentValues tournamentContent = new ContentValues();
            tournamentContent.put(ID_COLUMN, obj.ID);
            tournamentContent.put(NAME_COLUMN, obj.Name);
            tournamentContent.put(TOURNAMENTID_COLUMN, obj.TournamentID);
            tournamentContent.put(NOOFGROUPS_COLUMN, obj.NoOfGroups);
            tournamentContent.put(PRIORITY_COLUMN, obj.Priority);

            db.insert(tableName, null, tournamentContent);

        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public Level Retrieve(String id) {
        Level obj = null;
        Cursor reader = null;
        try {
            String[] COLUMNS = new String[]{ID_COLUMN, NAME_COLUMN, TOURNAMENTID_COLUMN, NOOFGROUPS_COLUMN, PRIORITY_COLUMN};
            reader = db.query(tableName, COLUMNS, ID_COLUMN + "=?", new String[]{id}, null, null, null);

            List<String> columnList = Arrays.asList(COLUMNS);
            if (reader.moveToNext()) {
                obj = new Level();
                obj.ID = reader.getString(columnList.indexOf(ID_COLUMN));
                obj.Name = reader.getString(columnList.indexOf(NAME_COLUMN));
                obj.TournamentID = reader.getString(columnList.indexOf(TOURNAMENTID_COLUMN));
                obj.NoOfGroups = reader.getInt(columnList.indexOf(NOOFGROUPS_COLUMN));
                obj.Priority = reader.getInt(columnList.indexOf(PRIORITY_COLUMN));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            reader.close();
            return obj;
        }
    }

    @Override
    public void Update(Level obj) {
        try {
            ContentValues tournamentContent = new ContentValues();
            //tournamentContent.put(ID_COLUMN, obj.ID);
            tournamentContent.put(NAME_COLUMN, obj.Name);
            tournamentContent.put(TOURNAMENTID_COLUMN, obj.TournamentID);
            tournamentContent.put(NOOFGROUPS_COLUMN, obj.NoOfGroups);
            tournamentContent.put(PRIORITY_COLUMN, obj.Priority);
            db.update(tableName, tournamentContent, ID_COLUMN + "=?", new String[]{obj.ID});
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public void Delete(String id) {
        db.delete(tableName, ID_COLUMN + "=?", new String[]{id});
    }
}
