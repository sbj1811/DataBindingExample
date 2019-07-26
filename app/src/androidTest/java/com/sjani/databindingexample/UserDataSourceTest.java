package com.sjani.databindingexample;

import androidx.room.Room;
import androidx.test.InstrumentationRegistry;


import com.sjani.databindingexample.Data.UserDatabase;
import com.sjani.databindingexample.Models.Medication;
import com.sjani.databindingexample.Models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test User getting stored in database
 */
public class UserDataSourceTest {

    private UserDatabase userDatabase;

    @Before
    public void initDb() throws Exception {
        userDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), UserDatabase.class).build();

    }

    @After
    public void closeDb() throws Exception {
        userDatabase.close();
    }

    @Test
    public void insertAndGetAlert() {
        User user = createUser();
        userDatabase.userDao().save(user);
        User newUser = userDatabase.userDao().getAllUsersforTest().get(0);
        assertEquals(user.getName(), newUser.getName());
        assertEquals(user.getAddress1(), newUser.getAddress1());
        assertEquals(user.getSex(), newUser.getSex());
        assertEquals(user.getDob(), newUser.getDob());
        assertEquals(user.getDisease(), newUser.getDisease());

    }

    private User createUser() {
        User user = new User();
        user.setName("Stephen A. McMillan");
        user.setAddress1("2508 Young Road Murtaugh, ID 83344");
        user.setSex("male");
        user.setDob("4/16/1953");
        user.setUid("51c685a4fb20655902014ac3");
        user.setDisease("asthma");
        user.setAddress2("");
        Medication m1 = new Medication();
        Medication m2 = new Medication();
        m1.setName("proair");
        m1.setMedicationtype("rescue");
        m2.setName("symbicort");
        m2.setMedicationtype("controller");
        List<Medication> medications = new ArrayList<>();
        medications.add(m1);
        medications.add(m2);
        user.setMedications(medications);
        return user;
    }

}
