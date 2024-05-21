package com.example.trabajosnicaragua.ui.jobs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import data.DBHelper;
import models.Empleo;

public class SharedJobsViewModel extends ViewModel {
    private final MutableLiveData<List<Empleo>> jobsList;
    private int userId;
    public SharedJobsViewModel() {
        jobsList = new MutableLiveData<>();

        DBHelper dbHelper = new DBHelper();
        List<Empleo> exampleJobs = dbHelper.getAllEmpleos();
        if (exampleJobs != null)
            jobsList.setValue(exampleJobs);
    }
    public int getUserId() {
        return userId;
    }

    public LiveData<List<Empleo>> getJobsList() {
        return jobsList;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void addJob(Empleo job) {
        List<Empleo> currentJobs = jobsList.getValue();
        if (currentJobs != null) {
            currentJobs.add(0, job);
            jobsList.setValue(currentJobs);
        }
    }
}
