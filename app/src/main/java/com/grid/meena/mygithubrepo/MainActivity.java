package com.grid.meena.mygithubrepo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    public static final String GITHUB_FRAGMENT = "GITHUB_FRAGMENT";

    private static final String TAG = "Knowing the Flow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "In MainActivity");

        attachGithubFragment();

    }

    private void attachGithubFragment() {
        GithubFragment githubFragment = new GithubFragment();

        Log.i("Knowing the flow", "attachGithubFragment() MainActivity");

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_layout, githubFragment, GITHUB_FRAGMENT)
                .commit();
    }
}
