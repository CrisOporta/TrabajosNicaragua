package com.example.trabajosnicaragua.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Aquí aparecerá todo lo relacionado al perfil");
    }

    public LiveData<String> getText() {
        return mText;
    }
}