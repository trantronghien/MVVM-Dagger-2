package com.example.hientt17.example.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.hientt17.example.R;
import com.example.hientt17.example.databinding.ProjectListItemBinding;
import com.example.hientt17.example.services.model.Project;
import com.example.hientt17.example.view.callback.ProjectClickCallback;

import java.util.List;
import java.util.Objects;

/**
 * Created by HienTT17 on 1/25/2018.
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    List<? extends Project> listRepos;

    @Nullable
    private final ProjectClickCallback projectClickCallback;


    public ProjectAdapter(@Nullable ProjectClickCallback projectClickCallback) {
        this.projectClickCallback = projectClickCallback;
    }

    public void notifyDataChange(List<? extends Project> listRepos) {
        if (listRepos == null) {
            this.listRepos = listRepos;
            notifyItemRangeInserted(0, listRepos.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return ProjectAdapter.this.listRepos == null ? 0 : ProjectAdapter.this.listRepos.size();
                }

                @Override
                public int getNewListSize() {
                    return listRepos == null ? 0 : listRepos.size();
                }

                // check item cũ có bằng item mới không--> list có thay đổi ko
                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return ProjectAdapter.this.listRepos.get(oldItemPosition).id ==
                            listRepos.get(newItemPosition).id;
                }

                // check content is change case item update content
                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Project newReponsitory = listRepos.get(newItemPosition);
                    Project oldReponsitory = listRepos.get(oldItemPosition);
                    return Objects.equals(oldReponsitory.clone_url,
                            newReponsitory.clone_url) &&
                            oldReponsitory.id == newReponsitory.id &&
                            Objects.equals(oldReponsitory.description, newReponsitory.description);
                }
            });
            this.listRepos = listRepos;
            result.dispatchUpdatesTo(this);
        }
    }


    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ProjectListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.project_list_item,
                parent,
                false);
        // set call back click item
        binding.setCallback(projectClickCallback);

        return new ProjectViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        holder.binding.setProject(listRepos.get(position));

        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return listRepos != null ? listRepos.size() : 0;
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder {
        private ProjectListItemBinding binding;

        public ProjectViewHolder(ProjectListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
