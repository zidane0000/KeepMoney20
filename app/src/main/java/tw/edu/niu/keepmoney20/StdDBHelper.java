package tw.edu.niu.keepmoney20;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bigdata_lab on 2018/4/15.
 */

public class StdDBHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "Class";
        private static final int DATABASE_VERSION = 1;

        public StdDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE money" +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "date text NOT NULL," +
                    "price integer NOT NULL," +
                    "category text NOT NULL)" );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db,
                              int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS money");
            onCreate(db);
        }
}
