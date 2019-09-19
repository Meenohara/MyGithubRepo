package com.grid.meena.mygithubrepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubService {


    @GET("users/meenohara/repos")
    Call<List<GithubRepo>> getMyRepos();
}
