package com.sjani.databindingexample.AddEvent;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.sjani.databindingexample.Models.Event;
import com.sjani.databindingexample.Models.Medication;
import com.sjani.databindingexample.Models.User;
import com.sjani.databindingexample.R;
import com.sjani.databindingexample.EventListViewModel;
import com.sjani.databindingexample.ViewModelFactory;
import com.sjani.databindingexample.Utils.FactoryUtils;
import com.sjani.databindingexample.Utils.StringUtils;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddEventActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = AddEventActivity.class.getSimpleName();
    @BindView(R.id.btn_date)
    Button btnDatePicker;
    @BindView(R.id.btn_time)
    Button btnTimePicker;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.edit_medication_name)
    EditText medicationNameEV;
    @BindView(R.id.edit_date)
    EditText dateEV;
    @BindView(R.id.edit_time)
    EditText timeEV;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private EventListViewModel eventListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        ButterKnife.bind(this);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEvent();
            }
        });
    }

    /**
     * Save event to event database
     */
    private void saveEvent() {
        ViewModelFactory factory = FactoryUtils.getFactory(this);
        eventListViewModel = ViewModelProviders.of(this, factory).get(EventListViewModel.class);
        eventListViewModel.getUsersrfromDb().observe(this, Users -> {
            String medicationName = medicationNameEV.getText().toString().trim().toLowerCase();
            String datetime = StringUtils.ConvertToISO8601(dateEV.getText().toString().trim(), timeEV.getText().toString().trim());
            boolean matchFound = false;
            Event event = new Event();
            User user = Users.get(0);
            List<Medication> medicationList = user.getMedications();
            // LLooks for medication type and creates an event to be stored in the db
            for (Medication medication : medicationList) {
                if (medicationName.equals(medication.getName())) {
                    String medicationType = medication.getMedicationtype();
                    event.setMedication(medicationName);
                    event.setMedicationtype(medicationType);
                    event.setDatetime(datetime);
                    event.setUid(user.getUid());
                    matchFound = true;
                    break;
                }
            }
            //Store newly created event into the database
            if (matchFound) {
                Observable.fromCallable(() -> {
                    event.setId(eventListViewModel.getnewEventId());
                    eventListViewModel.setEventinDb(event);
                    return false;
                })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();
                Toast.makeText(this, "Event Saved!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Error: Medication name does not match or complete all fields", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            // Launch Date Picker Dialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, year, monthOfYear, dayOfMonth) -> dateEV.setText((monthOfYear + 1) + "-" + dayOfMonth + "-" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (view, hourOfDay, minute) -> timeEV.setText(hourOfDay + ":" + minute), mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}
