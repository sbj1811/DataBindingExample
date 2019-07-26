package com.sjani.medieve.Utils;

import androidx.lifecycle.LiveData;

import com.sjani.medieve.Data.EventDao;
import com.sjani.medieve.Data.UserDao;
import com.sjani.medieve.Models.Event;
import com.sjani.medieve.Models.User;
import com.sjani.medieve.Models.UserEvents;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Reposityr to get data from RestAPIs and local SQLIte database
 */
public class DataRepository {
    private static final String TAG = DataRepository.class.getSimpleName();
    private static long eventDbSize = 0;
    private static boolean gotUsers;
    int userDbSize = 0;
    private EventDao eventDao;
    private UserDao userDao;

    public DataRepository(EventDao eventDao, UserDao userDao) {
        this.eventDao = eventDao;
        this.userDao = userDao;
    }

    /**
     * Gets Users and Event list from RestAPI and stores into the database
     */
    public void getEventsfromApi() {
        ApiConnection.getApi().getEvents().enqueue(new Callback<UserEvents>() {
            @Override
            public void onResponse(Call<UserEvents> call, Response<UserEvents> response) {
                UserEvents userEvents = response.body();
                Observable.fromCallable(() -> {
                    if (userEvents != null) {
                        List<Event> eventList = userEvents.getEvents();
                        eventDao.clearTable();
                        User user = response.body().getUser();
                        userDao.save(user);
                        gotUsers = true;
                        for (Event event : eventList) {
                            setEvent(event);
                        }
                    }
                    return false;
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();
            }

            @Override
            public void onFailure(Call<UserEvents> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    /**
     * Gets List of Users from local SQLite database
     *
     * @return LiveData of List of users
     */
    public LiveData<List<User>> getUserData() {
        if (!gotUsers) {
            getEventsfromApi();
        }
        return userDao.getAllUsers();
    }

    public LiveData<List<User>> getUsersFromDb() {
        return userDao.getAllUsers();
    }

    /**
     * Gets List of Events from local SQLite database
     *
     * @return LiveData of List of events
     */
    public LiveData<List<Event>> getEvents() {
        return eventDao.getAllEvents();
    }

    public void setEvent(Event event) {
        eventDbSize++;
        eventDao.save(event);
    }

    public long getEventDbSize() {
        return eventDbSize;
    }

}
