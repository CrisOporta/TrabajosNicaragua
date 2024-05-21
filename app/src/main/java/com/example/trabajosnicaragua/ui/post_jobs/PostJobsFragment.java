package com.example.trabajosnicaragua.ui.post_jobs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.trabajosnicaragua.databinding.FragmentPostJobsBinding;
import com.example.trabajosnicaragua.ui.jobs.SharedJobsViewModel;


import data.DBHelper;
import es.dmoral.toasty.Toasty;
import models.Empleo;


public class PostJobsFragment extends Fragment {

    private FragmentPostJobsBinding binding;
    private SharedJobsViewModel sharedJobsViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        PostJobsViewModel postJobsViewModel =
//                new ViewModelProvider(this).get(PostJobsViewModel.class);

        sharedJobsViewModel = new ViewModelProvider(requireActivity()).get(SharedJobsViewModel.class);

        binding = FragmentPostJobsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();




        // Configurar el botón de envío
        binding.buttonSubmitJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitJob();
            }
        });

        return root;
    }

    private void submitJob() {
        String jobTitle = binding.textInputJobTitle.getText().toString();
        String jobDescription = binding.textInputJobDescription.getText().toString();
        String jobLocation = binding.textInputJobLocation.getText().toString();
        String jobSalary = binding.textInputJobSalary.getText().toString();
        String jobRequirements = binding.textInputJobRequirements.getText().toString();

        // Validar que los campos no estén vacíos
        if (jobTitle.isEmpty() || jobDescription.isEmpty() || jobLocation.isEmpty() || jobSalary.isEmpty() || jobRequirements.isEmpty()) {
            Toasty.warning(getContext(), "Por favor, completa todos los campos...", Toast.LENGTH_SHORT, true).show();
            return;
        }

        DBHelper dbHelper = new DBHelper();

        // Retrieve the ViewModel
//        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
//
//        // Now you can use the user_rol and user_id
//        String userRol = sharedViewModel.getUserRol();
        int userId = sharedJobsViewModel.getUserId();
        Empleo nuevoEmpleo = new Empleo(jobTitle, jobDescription, jobLocation, jobSalary, jobRequirements, userId); // Verificado inicialmente como 0

        if (!dbHelper.addEmpleo(nuevoEmpleo)) {
            Toasty.error(getContext(), "Error al publicar empleo...", Toast.LENGTH_SHORT, true).show();

        } else {
            Toasty.success(getContext(), "Trabajo publicado con éxito", Toast.LENGTH_SHORT, true).show();
            sharedJobsViewModel.addJob(nuevoEmpleo);
        }


        // Limpiar los campos después de la publicación
        binding.textInputJobTitle.setText("");
        binding.textInputJobDescription.setText("");
        binding.textInputJobLocation.setText("");
        binding.textInputJobSalary.setText("");
        binding.textInputJobRequirements.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
