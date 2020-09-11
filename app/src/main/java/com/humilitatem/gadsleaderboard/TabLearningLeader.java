package com.humilitatem.gadsleaderboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.humilitatem.gadsleaderboard.model.Learner;
import com.humilitatem.gadsleaderboard.services.LeaderBoardService;
import com.humilitatem.gadsleaderboard.services.ServiceBuilder;
import com.humilitatem.gadsleaderboard.ui.adapters.LeadersRecyclerAdapter;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabLearningLeader extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final Context context = getContext();
        View rootView = inflater.inflate(R.layout.tab_learning_leader, container, false);

        ProgressBar progressBar = rootView.findViewById(R.id.progressBar);

        final RecyclerView recyclerView = rootView.findViewById(R.id.learningRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        LeaderBoardService leaderBoardService = ServiceBuilder.buildService(LeaderBoardService.class);
        Call<List<Learner>> request = leaderBoardService.getLearningLeaders();

        request.enqueue(new Callback<List<Learner>>() {
            @Override
            public void onResponse(Call<List<Learner>> request, Response<List<Learner>> response) {
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    recyclerView.setAdapter(new LeadersRecyclerAdapter(response.body(), getContext()));
                } else if (response.code() == 401) {
                    Toast.makeText(context, "Your session has expired", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Failed to retrieve leaders", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Learner>> request, Throwable t) {
                progressBar.setVisibility(View.GONE);
                if (t instanceof IOException) {
                    Toast.makeText(context, "A connection error occured", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Failed to retrieve leaders", Toast.LENGTH_LONG).show();
                }
            }
        });
        return rootView;
    }
}