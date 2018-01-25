package com.example.hientt17.example.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.hientt17.example.services.api.ProjectRepository;
import com.example.hientt17.example.services.model.Project;

/**
 * Created by HienTT17 on 1/25/2018.
 */

public class RepositoryDetailViewModel extends AndroidViewModel{

    private LiveData<Project> projectObservable;

    public ObservableField<Project> project = new ObservableField<>();

    public RepositoryDetailViewModel(@Nullable Application application){
        super(application);
    }

    public void getDetailRepository(String User ,String projectID){
        projectObservable = ProjectRepository.getInstance().getProjectDetails(User , projectID);
    }
    public LiveData<Project> getObservableProject() {
        return projectObservable;
    }

    public void setProject(Project project) {
        this.project.set(project);
    }


    /**
     * A creator is used to inject the project ID into the ViewModel
     */
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application application;

        public Factory(@NonNull Application application) {
            this.application = application;
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new RepositoryDetailViewModel(application);
        }
    }

}
