package com.sjani.medieve.MainList;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.facebook.stetho.Stetho;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sjani.medieve.R;
import com.sjani.medieve.AddEvent.AddEventActivity;
import com.sjani.medieve.EventListViewModel;
import com.sjani.medieve.ViewModelFactory;
import com.sjani.medieve.Utils.FactoryUtils;
import com.sjani.medieve.Utils.ListItemListerner;
import com.sjani.medieve.databinding.ActivityMainBinding;

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
        if (savedInstanceState==null) {
            eventListViewModel.init();
        }
        eventListViewModel.getUsers().observe(this, Users -> {
            if (Users != null && Users.size() != 0) eventListViewModel.setUser(Users.get(0));
        });
        eventListViewModel.getEventsforUser().observe(this, Events -> {
            if (Events != null && Events.size() != 0) eventListViewModel.setEventsInAdapter(Events);
        });
        activityMainBinding.setViewModel(eventListViewModel);
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
