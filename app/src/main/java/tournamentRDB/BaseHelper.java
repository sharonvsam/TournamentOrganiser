package tournamentRDB;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by VSharon on 3/20/2018.
 */

public abstract class BaseHelper<T> implements IEntity<T> {
    String tableName;
    SQLiteDatabase db;

    public BaseHelper(SQLiteDatabase database) {
        db = database;
    }


    public abstract void VerifyTableSchema(int oldVersion,int newVersion);

    public abstract void VerifyTableSchema();

    abstract void CreateTable();

    abstract void UpgradeTable();

}
