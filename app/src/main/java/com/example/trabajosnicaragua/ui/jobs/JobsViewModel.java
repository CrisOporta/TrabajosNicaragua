package com.example.trabajosnicaragua.ui.jobs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class JobsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public JobsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Los trabajos disponibles aparecerán aquí");
    }

    public LiveData<String> getText() {
        return mText;
    }
}