package com.sjani.medieve.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.sjani.medieve.Models.Event;

import java.util.List;

@Dao
public interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(Event event);

    @Query("SELECT * FROM events")
    LiveData<List<Event>> getAllEvents();

    @Query("SELECT * FROM events WHERE id = :id")
    LiveData<List<Event>> getEventwithId(String id);

    @Query("DELETE FROM events WHERE id = :id")
    void deleteEvent(String id);

    @Query("DELETE FROM events")
    void clearTable();
}
