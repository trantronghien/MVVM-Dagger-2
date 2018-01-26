package com.example.hientt17.example.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.hientt17.example.services.api.ProjectRepository;
import com.example.hientt17.example.services.model.Project;

import java.util.List;

/**
 * Created by HienTT17 on 1/25/2018.
 */

public class RepositoryListViewModel extends AndroidViewModel{

    private LiveData<List<Project>> repositoryList;

    public RepositoryListViewModel(@NonNull Application application) {
        super(application);
    }

    public void getListRepository(String User){
        User = !User.isEmpty() ? User : "TranTrongHien";
        repositoryList = ProjectRepository.getInstance().getProjectList(User);
    }

    public LiveData<List<Project>> getRepositoryListObservable(){
        return repositoryList;
    }

}
