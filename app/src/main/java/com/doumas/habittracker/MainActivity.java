package com.doumas.habittracker;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import data.HabitsContract;
import data.HabitsDBHelper;


public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Insert records for testing */
        HabitsDBHelper mDbHelper = new HabitsDBHelper(this);

        mDbHelper.insertRecord(
                "Read a book",
                "2017-06-29",
                90,
                HabitsContract.HabitsEntry.DURATION_MINUTE,
                "Continuing the exercise regime",
                HabitsContract.HabitsEntry.BADGE_GOOD);

        mDbHelper.insertRecord(
                "Practised guitar",
                "2017-06-29",
                30,
                HabitsContract.HabitsEntry.DURATION_MINUTE,
                "If only I could practise more",
                HabitsContract.HabitsEntry.BADGE_BELOW_AVERAGE);


        /** Read the table records and write to log for test */
        Cursor readCursor = mDbHelper.queryAllRecords();

        try {
            while (readCursor.moveToNext()) {
                Log.i(LOG_TAG,
                        "Habit >> " + readCursor.getInt(0) + " / "
                                + readCursor.getString(1) + " / "
                                + readCursor.getString(2) + " / "
                                + readCursor.getInt(3) + " " + readCursor.getString(4) + " / "
                                + readCursor.getString(5) + " / "
                                + readCursor.getInt(6));
            }
        } finally {
            readCursor.close();
        }
    }
}