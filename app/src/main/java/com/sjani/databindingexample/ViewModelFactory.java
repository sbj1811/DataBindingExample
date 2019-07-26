package com.sjani.databindingexample;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.sjani.databindingexample.EventListViewModel;
import com.sjani.databindingexample.Utils.DataRepository;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private DataRepository repository;

    public ViewModelFactory(DataRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new EventListViewModel(repository);
    }
}
