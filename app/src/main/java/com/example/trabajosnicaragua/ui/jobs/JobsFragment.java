package com.example.trabajosnicaragua.ui.jobs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajosnicaragua.databinding.FragmentJobsBinding;

import java.util.ArrayList;

public class JobsFragment extends Fragment {

    private FragmentJobsBinding binding;
    private JobsAdapter jobsAdapter;
    private SharedJobsViewModel sharedJobsViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sharedJobsViewModel = new ViewModelProvider(requireActivity()).get(SharedJobsViewModel.class);

        binding = FragmentJobsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Inicializar el RecyclerView
        RecyclerView recyclerView = binding.recyclerViewJobs;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Configurar el adaptador
        jobsAdapter = new JobsAdapter(new ArrayList<>());
        recyclerView.setAdapter(jobsAdapter);

        // Añadir DividerItemDecoration
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext()));

        // Observar los cambios en la lista de trabajos
        sharedJobsViewModel.getJobsList().observe(getViewLifecycleOwner(), jobs -> {
            jobsAdapter.updateJobsList(jobs);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
