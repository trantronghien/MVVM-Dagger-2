package com.example.hientt17.example.view;

import com.example.hientt17.example.model.GitHubRepository;

import java.util.List;

/**
 * Created by HienTT17 on 1/24/2018.
 */

public interface MainDataView {
    void onComplete(List<GitHubRepository> list);
    void onFailed(String errMess);
}
