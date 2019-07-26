package com.sjani.databindingexample.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.sjani.databindingexample.Models.Event;

@Database(entities = {Event.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class EventDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "events";
    private static final Object LOCK = new Object();
    private static EventDatabase sInstance;

    public static EventDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(), EventDatabase.class, EventDatabase.DATABASE_NAME)
                        .build();
            }
        }
        return sInstance;
    }


    public abstract EventDao eventDao();
}
