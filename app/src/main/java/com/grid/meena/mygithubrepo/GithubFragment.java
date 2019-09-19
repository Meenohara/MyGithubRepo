package com.grid.meena.mygithubrepo;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class GithubFragment extends Fragment {

    // private static final String TAG = "GithubFragment";
    private static final String TAG = "Knowing the Flow";

    private RecyclerView githubRepoRecyclerView;
    private GitHubRepoAdapter adapter;

    public GithubFragment() {
        // Required empty public constructor

        Log.i(TAG, "GithubFragment Constructor");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "GithubFragment OnCreateView");
        return inflater.inflate(R.layout.fragment_github, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "GithubFragment onViewCreated");
        // setup RecyclerView
        //this must be in presenter?
        githubRepoRecyclerView = view.findViewById(R.id.github_users_recyclerview);
        githubRepoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GitHubRepoAdapter(new ArrayList<GithubRepo>());
        githubRepoRecyclerView.setAdapter(adapter);

        GithubService service = GithubInjection.provideGithubService();

        getRepos(service);
    }

    private static final String NO_INTERNET_MESSAGE = "No internet connection.";
    private static final String REMOTE_SERVER_FAILED_MESSAGE = "Application server could not respond.";
    private static final String UNEXPECTED_ERROR_OCCURRED = "Something went wrong.";

    // 1. Implement MVP
    // 2. Move this to github repository and dependency inject in Model.
    private void getRepos(GithubService service) {
        service.getMyRepos().enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {
                adapter.updateGithubRepo(response.body());
                adapter.notifyDataSetChanged(); // tell the adapter that something has changedx
            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable throwable) {
                // error handling
                // https://github.com/chetdeva/FlickrIt/blob/master/app/src/main/java/com/chetdeva/flickrit/network/ApiClient.kt

               // https://stackoverflow.com/questions/12359175/java-throwable-to-exception
                Log.i(TAG, "onFailure: " + throwable.getMessage());
                Exception ex = new Exception(throwable);//to extract exception out of the throwable
                Log.i(TAG, "onFailure: " + throwable.getCause());
                String errorMessage = resolveException(ex.getCause());//calling the exception specifically
                                //not using Throwable because it is the Super Class so will not
                                //catch the specific exceptions
                //when this was not done it was going to the last else statement
                Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

   String resolveException(Throwable exception) {//Changing this also to Exception gave an error so has been
                                                //retained as Throwable
        if (exception instanceof UnknownHostException) {
            return NO_INTERNET_MESSAGE;
        } else if (exception instanceof SocketTimeoutException) {
            return REMOTE_SERVER_FAILED_MESSAGE;
        } else if (exception instanceof ConnectException) {
            return NO_INTERNET_MESSAGE;
        } else {
            return UNEXPECTED_ERROR_OCCURRED;
        }
    }
}
