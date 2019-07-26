package com.sjani.databindingexample.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.sjani.databindingexample.Models.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(User user);

    @Query("SELECT * FROM users")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM users WHERE uid = :uid")
    LiveData<User> getUserwithUid(String uid);

    @Query("DELETE FROM users WHERE uid = :uid")
    void deleteUser(String uid);

    @Query("DELETE FROM users")
    void clearTable();

    @Query("SELECT * FROM users")
    List<User> getAllUsersforTest();
}
