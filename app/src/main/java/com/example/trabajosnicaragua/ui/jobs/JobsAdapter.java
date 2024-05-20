package com.example.trabajosnicaragua.ui.jobs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajosnicaragua.R;

import java.util.List;

import models.Empleo;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobViewHolder> {

    private List<Empleo> jobsList;

    public JobsAdapter(List<Empleo> jobsList) {
        this.jobsList = jobsList;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jobs, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Empleo job = jobsList.get(position);
        holder.textViewJobTitle.setText(job.getTitulo());
        holder.textViewJobDescription.setText(job.getDescripcion());
        holder.textViewJobUbication.setText(job.getUbicacion());
        holder.textViewJobSalary.setText(job.getSalario());
        holder.textViewJobRequirements.setText(job.getRequisitos());
        holder.textViewJobCreatedAt.setText(job.getCreatedAt().toString());
        holder.textViewJobUpdatedAt.setText(job.getUpdatedAt().toString());
    }

    @Override
    public int getItemCount() {
        return jobsList.size();
    }

    public void updateJobsList(List<Empleo> jobs) {
        jobsList = jobs;
        notifyDataSetChanged();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {
        TextView textViewJobTitle;
        TextView textViewJobDescription;
        TextView textViewJobUbication;
        TextView textViewJobSalary;
        TextView textViewJobRequirements;
        TextView textViewJobCreatedAt;
        TextView textViewJobUpdatedAt;
        ImageView imageViewJob;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewJobTitle = itemView.findViewById(R.id.text_view_job_title);
            textViewJobDescription = itemView.findViewById(R.id.text_view_job_description);
            textViewJobUbication = itemView.findViewById(R.id.text_view_job_ubication);
            textViewJobSalary = itemView.findViewById(R.id.text_view_job_salary);
            textViewJobRequirements = itemView.findViewById(R.id.text_view_job_requirements);
            textViewJobCreatedAt = itemView.findViewById(R.id.text_view_job_createdat);
            textViewJobUpdatedAt = itemView.findViewById(R.id.text_view_job_updatedat);
            imageViewJob = itemView.findViewById(R.id.image_view_job);
        }
    }
}
