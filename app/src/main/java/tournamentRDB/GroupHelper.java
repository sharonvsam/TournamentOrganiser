package tournamentRDB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import tournamentModels.Group;

/**
 * Created by VSharon on 4/10/2018.
 */

public class GroupHelper extends BaseHelper<Group> {

    final String ID_COLUMN = "ID";
    final String NAME_COLUMN = "NAME";
    final String LOGO_COLUMN = "LOGO";
    final String LOCALITY_COLUMN = "LOCALITY";
    final String NOOFTEAMS_COLUMN = "NOOFPLAYERS";

    public GroupHelper(SQLiteDatabase database) {
        super(database);
        tableName = "groups";
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
        try {
            final String createTournamentTable = "CREATE TABLE IF NOT EXISTS " + tableName
                    + "( " + ID_COLUMN + " TEXT PRIMARY KEY"
                    + ", " + NAME_COLUMN + " TEXT"
                    + ", " + LOGO_COLUMN + " TEXT"
                    + ", " + LOCALITY_COLUMN + " TEXT"
                    + ", " + NOOFTEAMS_COLUMN + " INT";

            db.execSQL(createTournamentTable);
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    void UpgradeTable() {

    }

    @Override
    public void Add(Group obj) {
        try {
            ContentValues tournamentContent = new ContentValues();
            tournamentContent.put(ID_COLUMN, obj.ID);
            tournamentContent.put(NAME_COLUMN, obj.Name);

            db.insert(tableName, null, tournamentContent);

        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public Group Retrieve(String id) {
        Group obj = null;
        Cursor reader = null;
        try {
            String[] COLUMNS = new String[]{ID_COLUMN, NAME_COLUMN, LOGO_COLUMN, LOCALITY_COLUMN, NOOFTEAMS_COLUMN};
            reader = db.query(tableName, COLUMNS, ID_COLUMN + "=?", new String[]{id}, null, null, null);

            List<String> columnList = Arrays.asList(COLUMNS);
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            if (reader.moveToNext()) {
                obj = new Group();
                obj.ID = reader.getString(columnList.indexOf(ID_COLUMN));
                obj.Name = reader.getString(columnList.indexOf(NAME_COLUMN));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            reader.close();
            return obj;
        }
    }

    @Override
    public void Update(Group obj) {
        try {
            ContentValues tournamentContent = new ContentValues();
            //tournamentContent.put(ID_COLUMN, obj.ID);
            tournamentContent.put(NAME_COLUMN, obj.Name);
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
