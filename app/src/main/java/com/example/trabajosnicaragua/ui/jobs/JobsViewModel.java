package com.example.trabajosnicaragua.ui.jobs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.trabajosnicaragua.R;

import java.util.ArrayList;
import java.util.List;

public class JobsViewModel extends ViewModel {

    private final MutableLiveData<List<Job>> jobsList;

    public JobsViewModel() {
        jobsList = new MutableLiveData<>();
        // Inicializa con algunos datos de ejemplo
        List<Job> exampleJobs = new ArrayList<>();
        exampleJobs.add(new Job("Programador Backend", "Se solicita un programador Backend con conocimientos en .NET", R.drawable.dev_job_image));
        exampleJobs.add(new Job("Profesor Historia", "Se solicita profesor de Historia de Nicaragua", R.drawable.teacher_job_image));
        exampleJobs.add(new Job("Contador", "Necesitamos contador con 3 años de experiencia", R.drawable.accountant_job_image));
        jobsList.setValue(exampleJobs);
    }

    public LiveData<List<Job>> getJobsList() {
        return jobsList;
    }

    public void setJobsList(List<Job> jobs) {
        jobsList.setValue(jobs);
    }
}
