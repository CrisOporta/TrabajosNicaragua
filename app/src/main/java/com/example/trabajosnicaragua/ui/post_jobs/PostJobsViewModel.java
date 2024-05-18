package com.example.trabajosnicaragua.ui.post_jobs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PostJobsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PostJobsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Aquí aparecerá el formulario para agregar trabajos");
    }

    public LiveData<String> getText() {
        return mText;
    }
}