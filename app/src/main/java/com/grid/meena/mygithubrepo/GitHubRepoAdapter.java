package com.grid.meena.mygithubrepo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class GitHubRepoAdapter extends RecyclerView.Adapter<GitHubRepoAdapter.GithubRepoViewHolder> {

    private List<GithubRepo> githubRepo;

    public GitHubRepoAdapter(List<GithubRepo> githubRepo) {
        this.githubRepo = githubRepo;
    }

    @NonNull
    @Override
    public GithubRepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.github_user_item, parent, false);
        return new GithubRepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GithubRepoViewHolder holder, int position) {
        holder.bind(githubRepo.get(position));
    }

    @Override
    public int getItemCount() {
        return githubRepo.size();
    }

    void updateGithubRepo(List<GithubRepo> githubRepo) {
        this.githubRepo.addAll(githubRepo);
    }

    static class GithubRepoViewHolder extends RecyclerView.ViewHolder {

        private final TextView userNameTextView;

        public GithubRepoViewHolder(@NonNull View itemView) {
            super(itemView);//?

            userNameTextView = itemView.findViewById(R.id.username_textview);
        }

        void bind(GithubRepo githubRepo) {
            userNameTextView.setText(githubRepo.name);
        }
    }
}