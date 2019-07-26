package com.sjani.medieve.Utils;

import android.content.Context;

import com.sjani.medieve.Data.EventDatabase;
import com.sjani.medieve.Data.UserDatabase;
import com.sjani.medieve.ViewModelFactory;

/**
 * Creates ViewModel Factory
 */
public class FactoryUtils {
    /**
     * Set up Database, Repository and ViewModel Factory
     *
     * @param context Calling component handle
     * @return ViewModel Factory
     */
    public static ViewModelFactory getFactory(Context context) {
        EventDatabase eventDatabase = EventDatabase.getInstance(context.getApplicationContext());
        UserDatabase userDatabase = UserDatabase.getInstance(context.getApplicationContext());
        return new ViewModelFactory(new DataRepository(eventDatabase.eventDao(), userDatabase.userDao()));
    }
}
