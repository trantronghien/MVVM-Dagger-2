package com.example.hientt17.example.viewmodel;

import com.example.hientt17.example.model.ApiService;
import com.example.hientt17.example.model.GitHubRepository;
import com.example.hientt17.example.network.ApiServiceModule;
import com.example.hientt17.example.view.MainDataView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by HienTT17 on 1/24/2018.
 */

public class GitReponsitoryViewModel {
    MainDataView view;

    public GitReponsitoryViewModel(MainDataView view) {
        this.view = view;
    }

    public ApiService getApiService() {
        return new ApiServiceModule().createClient().create(ApiService.class);
    }

    public void getUserRepon(String UserName, int page) {
        this.getApiService().getGitRepository(UserName, page).enqueue(new Callback<List<GitHubRepository>>() {
            @Override
            public void onResponse(Call<List<GitHubRepository>> call, Response<List<GitHubRepository>> response) {
                List<GitHubRepository> gitHubRepositories = response.body();
                if (view != null && response.isSuccessful()) {
                    if (gitHubRepositories.isEmpty() || gitHubRepositories == null){
                        view.onFailed("No data");
                    }else{
                        view.onComplete(gitHubRepositories);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<GitHubRepository>> call, Throwable t) {
                if (view != null) {
                    view.onFailed(t.getMessage());
                }
            }
        });
    }
}
