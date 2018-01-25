package com.example.hientt17.example.view.ui.fragment;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hientt17.example.R;
import com.example.hientt17.example.databinding.FragmentProjectListBinding;
import com.example.hientt17.example.services.model.Project;
import com.example.hientt17.example.view.adapter.ProjectAdapter;
import com.example.hientt17.example.view.callback.ProjectClickCallback;
import com.example.hientt17.example.view.callback.SearchCallBack;
import com.example.hientt17.example.view.ui.MainActivity;
import com.example.hientt17.example.viewmodel.RepositoryListViewModel;

/**
 * Created by HienTT17 on 1/25/2018.
 */

public class ProjectListFragment extends LifecycleFragment {

    public static final String TAG = "ProjectListFragment";
    private ProjectAdapter projectAdapter;
    private FragmentProjectListBinding binding;
    private String UserName = "";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false);
        projectAdapter = new ProjectAdapter(projectClickCallback);



        // projectList generate form id project_list
        binding.projectList.setAdapter(projectAdapter);
//        binding.setIsLoading(false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RepositoryListViewModel listViewModel = ViewModelProviders.of(ProjectListFragment.this).get(RepositoryListViewModel.class);

        binding.setSearchCallback(() -> {
            binding.setIsLoading(true);
            UserName = binding.searchRepository.getText().toString();
            listViewModel.getListRepository(UserName);
            observeViewModel(listViewModel);
        });

    }

    // todo VIEW MODEL UPDATE IN VIEW
    private void observeViewModel(RepositoryListViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getRepositoryListObservable().observe(this, (listRepo) -> {
            if (listRepo != null) {
                binding.setIsLoading(false);
                projectAdapter.notifyDataChange(listRepo);

            }else{
                Toast.makeText(getActivity(), "Khong tim thay user nay", Toast.LENGTH_SHORT).show();
                binding.setIsLoading(false);
            }
        });
    }

    private final ProjectClickCallback projectClickCallback = new ProjectClickCallback() {
        @Override
        public void onClick(Project project) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).showList(project , UserName);
            }
        }
    };
}
