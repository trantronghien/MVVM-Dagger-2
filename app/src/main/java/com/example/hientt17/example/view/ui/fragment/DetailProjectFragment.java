package com.example.hientt17.example.view.ui.fragment;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hientt17.example.R;
import com.example.hientt17.example.databinding.FragmentProjectDetailBinding;
import com.example.hientt17.example.services.model.Project;
import com.example.hientt17.example.viewmodel.RepositoryDetailViewModel;

/**
 * Created by HienTT17 on 1/25/2018.
 */

public class DetailProjectFragment extends LifecycleFragment {

    private static final String KEY_PROJECT_ID = "project_id";
    private static final String KEY_PROJECT_USER = "project_user";
    private FragmentProjectDetailBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_project_detail , container , false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String projectID = getArguments().getString(KEY_PROJECT_ID);
        String projectUser = getArguments().getString(KEY_PROJECT_USER);
        RepositoryDetailViewModel.Factory factory = new RepositoryDetailViewModel.Factory(getActivity().getApplication());

        RepositoryDetailViewModel detailViewModel = ViewModelProviders.of(this , factory).get(RepositoryDetailViewModel.class);
        detailViewModel.getDetailRepository(projectID ,projectUser );
        binding.setDetailViewModel(detailViewModel);
        binding.setIsLoading(true);

        observeViewModel(detailViewModel);
    }

    private void observeViewModel(final RepositoryDetailViewModel viewModel) {
        // Observe project data
        viewModel.getObservableProject().observe(this, new Observer<Project>() {
            @Override
            public void onChanged(@Nullable Project project) {
                if (project != null) {
                    binding.setIsLoading(false);
                    viewModel.setProject(project);
                }else {
                    Toast.makeText(getActivity(), "khong tim thay thong tin project nay", Toast.LENGTH_SHORT).show();
                    binding.setIsLoading(true);
                }
            }
        });
    }

    /** Creates project fragment for specific project ID */
    public static DetailProjectFragment forProject(String projectID,String UserName) {
        DetailProjectFragment fragment = new DetailProjectFragment();
        Bundle args = new Bundle();
        args.putString(KEY_PROJECT_ID, projectID);
        args.putString(KEY_PROJECT_USER , UserName);
        fragment.setArguments(args);
        return fragment;
    }
}
