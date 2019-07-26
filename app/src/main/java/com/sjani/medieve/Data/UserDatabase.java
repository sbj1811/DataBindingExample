package com.sjani.medieve.Data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.sjani.medieve.Models.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class UserDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "users";
    private static final Object LOCK = new Object();
    private static UserDatabase sInstance;

    public static UserDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, UserDatabase.DATABASE_NAME)
                        .build();
            }
        }
        return sInstance;
    }


    public abstract UserDao userDao();
}
