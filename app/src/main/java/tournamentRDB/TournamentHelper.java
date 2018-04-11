package tournamentRDB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import tournamentModels.Tournament;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

/**
 * Created by VSharon on 3/20/2018.
 */

public class TournamentHelper extends BaseHelper<Tournament> {

    final String ID_COLUMN = "ID";
    final String NAME_COLUMN = "NAME";
    final String LOGO_COLUMN = "LOGO";
    final String LOCALITY_COLUMN = "LOCALITY";
    final String NOOFTEAMS_COLUMN = "NOOFTEAMS";
    final String STARTDATE_COLUMN = "STARTDATE";
    final String ENDDATE_COLUMN = "ENDDATE";

    TournamentsTeamsHelper tournamentteams;

    public TournamentHelper(SQLiteDatabase database) {
        super(database);
        tableName = "tournaments";
        tournamentteams = new TournamentsTeamsHelper(database);
    }

    @Override
    public void VerifyTableSchema(int oldVersion,int newVersion) {

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
                    + ", " + NOOFTEAMS_COLUMN + " INT"
                    + ", " + STARTDATE_COLUMN + " DATE"
                    + ", " + ENDDATE_COLUMN + " DATE )";

            db.execSQL(createTournamentTable);
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    void UpgradeTable() {

    }

    @Override
    public void Add(Tournament obj) {
        try {
            ContentValues tournamentContent = new ContentValues();
            tournamentContent.put(ID_COLUMN, obj.ID);
            tournamentContent.put(NAME_COLUMN, obj.Name);
            tournamentContent.put(LOGO_COLUMN, obj.Logo);
            tournamentContent.put(LOCALITY_COLUMN, obj.Locality);
            tournamentContent.put(NOOFTEAMS_COLUMN, obj.NoOfTeams);
            tournamentContent.put(STARTDATE_COLUMN, obj.StartDate.toString());
            tournamentContent.put(ENDDATE_COLUMN, obj.EndDate.toString());

            db.insert(tableName, null, tournamentContent);

            tournamentteams.Add(obj.ID, obj.Teams);
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public Tournament Retrieve(String id) {
        Tournament obj = null;
        Cursor reader = null;
        try {
            String[] COLUMNS = new String[]{ID_COLUMN, NAME_COLUMN, LOGO_COLUMN, LOCALITY_COLUMN, NOOFTEAMS_COLUMN, STARTDATE_COLUMN, ENDDATE_COLUMN};
            reader = db.query(tableName, COLUMNS, ID_COLUMN + "=?", new String[]{id}, null, null, null);

            List<String> columnList = Arrays.asList(COLUMNS);
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            if (reader.moveToNext()) {
                obj = new Tournament();
                obj.ID = reader.getString(columnList.indexOf(ID_COLUMN));
                obj.Name = reader.getString(columnList.indexOf(NAME_COLUMN));
                obj.Logo = reader.getString(columnList.indexOf(LOGO_COLUMN));
                obj.Locality = reader.getString(columnList.indexOf(LOCALITY_COLUMN));
                obj.NoOfTeams = reader.getInt(columnList.indexOf(NOOFTEAMS_COLUMN));
                obj.StartDate = df.parse(reader.getString(columnList.indexOf(STARTDATE_COLUMN)));
                obj.EndDate = df.parse(reader.getString(columnList.indexOf(ENDDATE_COLUMN)));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            reader.close();
            return obj;
        }
    }

    @Override
    public void Update(Tournament obj) {
        try {
            ContentValues tournamentContent = new ContentValues();
            //tournamentContent.put(ID_COLUMN, obj.ID);
            tournamentContent.put(NAME_COLUMN, obj.Name);
            tournamentContent.put(LOGO_COLUMN, obj.Logo);
            tournamentContent.put(LOCALITY_COLUMN, obj.Locality);
            tournamentContent.put(NOOFTEAMS_COLUMN, obj.NoOfTeams);
            tournamentContent.put(STARTDATE_COLUMN, obj.StartDate.toString());
            tournamentContent.put(ENDDATE_COLUMN, obj.EndDate.toString());
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
