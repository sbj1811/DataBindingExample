package com.sjani.databindingexample.MainList;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.facebook.stetho.Stetho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sjani.databindingexample.Models.Event;
import com.sjani.databindingexample.R;
import com.sjani.databindingexample.AddEvent.AddEventActivity;
import com.sjani.databindingexample.EventListViewModel;
import com.sjani.databindingexample.ViewModelFactory;
import com.sjani.databindingexample.Utils.FactoryUtils;
import com.sjani.databindingexample.Utils.ListItemListerner;
import com.sjani.databindingexample.databinding.ActivityMainBinding;

import java.util.List;

/**
 * Main activity with user card and medication event list
 */
public class MainActivity extends AppCompatActivity implements ListItemListerner {

    private static final String TAG = MainActivity.class.getSimpleName();

    TextView userDisease;
    EventListViewModel eventListViewModel;

    /**
     * Creates the View
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView(savedInstanceState);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> showAddEvent());

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build()
        );
    }

    private void setView(Bundle savedInstanceState) {
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        ViewModelFactory factory = FactoryUtils.getFactory(this);
        eventListViewModel = ViewModelProviders.of(this, factory).get(EventListViewModel.class);
      //  if (savedInstanceState==null) {
            eventListViewModel.init();
     //   }
        eventListViewModel.getUsers().observe(this, Users -> {
            if (Users != null && Users.size() != 0) eventListViewModel.setUser(Users.get(0));
            eventListViewModel.getEventsforUser().observe(this, events -> {
                eventListViewModel.loading.set(View.GONE);
                if (events != null && events.size() != 0) {
                    eventListViewModel.showEmpty.set(View.GONE);
                    eventListViewModel.setEventsInAdapter(events);
                }
                activityMainBinding.setViewModel(eventListViewModel);
            });
        });
    }

    private void showAddEvent() {
        Intent intent = new Intent(this, AddEventActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent);
            overridePendingTransition(R.xml.slide_from_right, R.xml.slide_to_left);
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(String name, String dateTime) {
        String message = "You took " + name + " at " + dateTime;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
