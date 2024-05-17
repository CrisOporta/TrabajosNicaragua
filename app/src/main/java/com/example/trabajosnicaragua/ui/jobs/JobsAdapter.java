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

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobViewHolder> {

    private List<Job> jobsList;

    public JobsAdapter(List<Job> jobsList) {
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
        Job job = jobsList.get(position);
        holder.textViewJobTitle.setText(job.getTitle());
        holder.textViewJobDescription.setText(job.getDescription());
        holder.imageViewJob.setImageResource(job.getImageResId()); // Establecer la imagen local
    }

    @Override
    public int getItemCount() {
        return jobsList.size();
    }

    public void updateJobsList(List<Job> jobs) {
        jobsList = jobs;
        notifyDataSetChanged();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {
        TextView textViewJobTitle;
        TextView textViewJobDescription;
        ImageView imageViewJob;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewJobTitle = itemView.findViewById(R.id.text_view_job_title);
            textViewJobDescription = itemView.findViewById(R.id.text_view_job_description);
            imageViewJob = itemView.findViewById(R.id.image_view_job);
        }
    }
}
