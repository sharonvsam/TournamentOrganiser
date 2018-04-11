package tournamentRDB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import tournamentModels.Player;
import java.util.Arrays;
import java.util.List;

/**
 * Created by VSharon on 4/10/2018.
 */

public class PlayerHelper extends BaseHelper<Player> {

    final String ID_COLUMN = "ID";
    final String NAME_COLUMN = "NAME";
    final String LOGO_COLUMN = "LOGO";
    final String LOCALITY_COLUMN = "LOCALITY";

    public PlayerHelper(SQLiteDatabase database) {
        super(database);
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
                    + ", " + LOCALITY_COLUMN + " TEXT";
            Log.d(tableName, createTournamentTable);
            db.execSQL(createTournamentTable);
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    void UpgradeTable() {

    }

    @Override
    public void Add(Player obj) {
        try {
            ContentValues tournamentContent = new ContentValues();
            tournamentContent.put(ID_COLUMN, obj.ID);
            tournamentContent.put(NAME_COLUMN, obj.Name);
            tournamentContent.put(LOGO_COLUMN, obj.Logo);
            tournamentContent.put(LOCALITY_COLUMN, obj.Locality);

            db.insert(tableName, null, tournamentContent);

        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public Player Retrieve(String id) {
        Player obj = null;
        Cursor reader = null;
        try {
            String[] COLUMNS = new String[]{ID_COLUMN, NAME_COLUMN, LOGO_COLUMN, LOCALITY_COLUMN};
            reader = db.query(tableName, COLUMNS, ID_COLUMN + "=?", new String[]{id}, null, null, null);

            List<String> columnList = Arrays.asList(COLUMNS);
            if (reader.moveToNext()) {
                obj = new Player();
                obj.ID = reader.getString(columnList.indexOf(ID_COLUMN));
                obj.Name = reader.getString(columnList.indexOf(NAME_COLUMN));
                obj.Logo = reader.getString(columnList.indexOf(LOGO_COLUMN));
                obj.Locality = reader.getString(columnList.indexOf(LOCALITY_COLUMN));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            reader.close();
            return obj;
        }
    }

    @Override
    public void Update(Player obj) {
        try {
            ContentValues tournamentContent = new ContentValues();
            tournamentContent.put(NAME_COLUMN, obj.Name);
            tournamentContent.put(LOGO_COLUMN, obj.Logo);
            tournamentContent.put(LOCALITY_COLUMN, obj.Locality);
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
