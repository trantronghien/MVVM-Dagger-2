package com.example.hientt17.example.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by HienTT17 on 1/24/2018.
 * https://api.github.com/users/trantronghien/repos?page=3
 */

public interface ApiService {
    @GET("/users/{user}/repos")
    Call<List<GitHubRepository>> getGitRepository(@Path("user") String user,@Query("page") int page);
}
