package com.example.trabajosnicaragua.ui.jobs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajosnicaragua.R;

import java.util.ArrayList;
import java.util.List;

import data.DBHelper;
import models.Empleo;
import models.Usuario;

public class JobsViewModel extends ViewModel {

    private final MutableLiveData<List<Empleo>> jobsList;

    public JobsViewModel() {
        jobsList = new MutableLiveData<>();


        // Agregar todos los trabajos
        DBHelper dbHelper = new DBHelper();

        // Inicializa con algunos datos de ejemplo
        List<Empleo> exampleJobs = dbHelper.getAllEmpleos();

        if(exampleJobs != null)
            jobsList.setValue(exampleJobs);

    }

    public LiveData<List<Empleo>> getJobsList() {
        return jobsList;
    }

    public void setJobsList(List<Empleo> jobs) {
        jobsList.setValue(jobs);
    }
}
