package com.sjani.databindingexample;


import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sjani.databindingexample.Models.Event;
import com.sjani.databindingexample.Models.User;
import com.sjani.databindingexample.MainList.ListAdapter;
import com.sjani.databindingexample.Utils.DataRepository;
import com.sjani.databindingexample.Utils.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventListViewModel extends ViewModel {
    private static final String TAG = EventListViewModel.class.getSimpleName();

    private DataRepository repository;
    private ListAdapter adapter;
    private User newUser;
    private List<Event> eventList;
    public ObservableInt loading;
    public ObservableInt showEmpty;

    public void init(){
        adapter = new ListAdapter(R.layout.event_list_item,this);
        newUser = new User();
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);
    }

    public ListAdapter getAdapter() {
        return adapter;
    }

    public void setEventsInAdapter(List<Event> events){
        List<Event> reversedList = new ArrayList<>();
        for (int i = events.size() - 1; i >= 0; i--) {
            reversedList.add(events.get(i));
        }
        Collections.sort(reversedList, (o1, o2) -> {
            String dateTime1 = o1.getDatetime();
            String dateTime2 = o2.getDatetime();
            return dateTime2.compareTo(dateTime1);
        });
        eventList = reversedList;
        this.adapter.swapResults(reversedList);
        this.adapter.notifyDataSetChanged();
    }

    public void setUser(User user) {
        this.newUser.setName(user.getName());
        this.newUser.setAddress1(String.format("%s %s", user.getAddress1(), user.getAddress2()));
        this.newUser.setDob(user.getDob());
        this.newUser.setSex(user.getSex().substring(0, 1).toUpperCase() + user.getSex().substring(1));
        this.newUser.setDisease(user.getDisease().substring(0, 1).toUpperCase() + user.getDisease().substring(1));
    }

    public Event getEventAt(Integer index){
        Event event = new Event();
        event.setMedication(eventList.get(index).getMedication());
        event.setMedicationtype(eventList.get(index).getMedicationtype());
        event.setId(eventList.get(index).getId());
        String dateTime = eventList.get(index).getDatetime();
        if (dateTime.equals("")) {
            dateTime = "2015-01-01T11:32:00.000Z";
        }
        event.setUid(newUser.getUid());
        event.setDatetime(StringUtils.formatDateTime(dateTime));
        return event;
    }

    public User getUser(){
        return newUser;
    }


    public EventListViewModel(DataRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<User>> getUsers() {
        return repository.getUserData();
    }

    public LiveData<List<User>> getUsersrfromDb() {
        return repository.getUsersFromDb();
    }

    public LiveData<List<Event>> getEventsforUser() {
        return repository.getEvents();
    }

    public void setEventinDb(Event event) {
        repository.setEvent(event);
    }

    public long getnewEventId() {
        return repository.getEventDbSize() + 1;
    }
}
