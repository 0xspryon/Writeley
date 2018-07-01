package com.springfield.y.writeley.model.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.springfield.y.writeley.model.Note;

/**
 * Created by ipr0d1g1 on 7/1/18.
 */

@Database(entities = {Note.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class WriteleyDatabase extends RoomDatabase {
    private static final String LOG_TAG = WriteleyDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "writeley_db";
    private static WriteleyDatabase mInstance;

    public static WriteleyDatabase getInstance(Context context) {
        if (mInstance == null) {
            synchronized (LOCK) {
                mInstance = Room.databaseBuilder(context.getApplicationContext(),
                        WriteleyDatabase.class, DATABASE_NAME)

                        // TODO: remember to remove the following instruction
                        .allowMainThreadQueries()
                        .build();
            }
        }

        return mInstance;
    }

    public abstract NoteDAO noteDAO();

}
