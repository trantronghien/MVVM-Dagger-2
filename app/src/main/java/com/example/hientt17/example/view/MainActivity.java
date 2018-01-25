package com.example.hientt17.example.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.hientt17.example.R;
import com.example.hientt17.example.adapter.GitRepositoryAdapter;
import com.example.hientt17.example.databinding.ActivityMainBinding;
import com.example.hientt17.example.model.GitHubRepository;
import com.example.hientt17.example.viewmodel.GitReponsitoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainDataView {

    private GitReponsitoryViewModel modelControl;
    private RecyclerView recyclerView;
    private GitRepositoryAdapter adapter;
    private List<GitHubRepository> listRep;
    FrameLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modelControl = new GitReponsitoryViewModel(this);
        modelControl.getUserRepon("trantronghien", 2);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        GitHubRepository model = new GitHubRepository();
        binding.setModelGithub(model);

        listRep = new ArrayList<>();
        adapter = new GitRepositoryAdapter(listRep);

        recyclerView = findViewById(R.id.listUser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        loading = findViewById(R.id.loading);

    }

    @Override
    public void onComplete(List<GitHubRepository> list) {
        this.listRep = list;
        adapter.notifyDataChange(listRep);
        loading.setVisibility(View.GONE);
    }

    @Override
    public void onFailed(String errMess) {
        Toast.makeText(this, errMess, Toast.LENGTH_SHORT).show();
        loading.setVisibility(View.GONE);
    }
}
