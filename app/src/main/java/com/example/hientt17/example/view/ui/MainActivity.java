package com.example.hientt17.example.view.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.hientt17.example.R;
import com.example.hientt17.example.services.model.Project;
import com.example.hientt17.example.view.ui.fragment.DetailProjectFragment;
import com.example.hientt17.example.view.ui.fragment.ProjectListFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            ProjectListFragment fragment = new ProjectListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, ProjectListFragment.TAG).commit();
        }

    }


    public void showList(Project project, String userName) {
        userName = userName.isEmpty() ? project.owner.login : userName;
        DetailProjectFragment detailfagment = DetailProjectFragment.forProject(project.name, userName);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("project")
                .replace(R.id.fragment_container,
                        detailfagment, null).commit();
    }
}
