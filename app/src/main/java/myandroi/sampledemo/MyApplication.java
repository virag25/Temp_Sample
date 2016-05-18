package myandroi.sampledemo;

import android.app.Application;
import android.content.Context;

import activerecordbase.ActiveRecordBase;
import activerecordbase.ActiveRecordException;
import activerecordbase.Database;
import activerecordbase.DatabaseBuilder;
import common.Const;
import common.Utils;

/**
 * Created by Virag kuvadia on 18-05-2016.
 */
public class MyApplication extends Application {
    private static MyApplication _intance = null;
    private static ActiveRecordBase mDatabase;


    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseBuilder builder = new DatabaseBuilder(Const.DATABASE_NAME);
        Database.setBuilder(builder);
        try {
            mDatabase = ActiveRecordBase.open(_intance.getApplicationContext(),
                    Const.DATABASE_NAME, Const.DATABASE_VERSION);
        } catch (ActiveRecordException e) {
            e.printStackTrace();
        } catch (Exception e) {
            Utils.LogException(e);
        }

    }


    public MyApplication() {
        _intance = this;
    }

    public static ActiveRecordBase Connection() {
        return mDatabase;
    }

    public static Context getContext() {
        return _intance;
    }

}
