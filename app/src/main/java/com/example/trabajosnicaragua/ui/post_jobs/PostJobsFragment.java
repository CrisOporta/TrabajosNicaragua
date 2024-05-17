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
import com.google.android.material.textfield.TextInputEditText;

public class PostJobsFragment extends Fragment {

    private FragmentPostJobsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PostJobsViewModel postJobsViewModel =
                new ViewModelProvider(this).get(PostJobsViewModel.class);

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
            Toast.makeText(getContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Aquí puedes manejar la lógica de enviar los datos a tu base de datos o backend
        // Por ejemplo:
        // saveJobToDatabase(jobTitle, jobDescription, jobLocation, jobSalary, jobRequirements);

        Toast.makeText(getContext(), "Trabajo publicado con éxito", Toast.LENGTH_SHORT).show();

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