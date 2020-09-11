package com.humilitatem.gadsleaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.humilitatem.gadsleaderboard.services.LeaderBoardService;
import com.humilitatem.gadsleaderboard.services.ServiceBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionForm extends AppCompatActivity {

    EditText firstName, lastName, email, githubLink;
    Button submitToGoggleForm;
    ImageView backButton, cancel;
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mGitHubLink;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission_form);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.email);
        githubLink = findViewById(R.id.githubLink);
        submitToGoggleForm = findViewById(R.id.submitToGoggleForm);
        backButton = findViewById(R.id.back);
        mProgressBar = findViewById(R.id.submitProgressBar);

        backButton.setOnClickListener(view -> {
            finish();
        });

        submitToGoggleForm.setOnClickListener(view -> sureDialog());
    }

    private void sureDialog() {
        final Dialog dialog = new Dialog(SubmissionForm.this);
        mFirstName = firstName.getText().toString().trim();
        mLastName = lastName.getText().toString().trim();
        mEmail = email.getText().toString().trim();
        mGitHubLink = githubLink.getText().toString().trim();

        dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
        dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_check);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_are_you_sure);
        dialog.show();

        Button yes = dialog.findViewById(R.id.yesButton);
        yes.setOnClickListener(view -> {
            if (mFirstName.length() > 0 && mLastName.length() > 0
                    && mEmail.length() > 0 && mGitHubLink.length() > 0) {
            postData();
            } else {
                Toast.makeText(getApplicationContext(), "Required Fields Missing!", Toast.LENGTH_SHORT).show();
            }
            dialog.dismiss();
        });

        cancel = dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(view -> dialog.dismiss());
    }
    private void failureDialog() {
        Dialog dialog = new Dialog(SubmissionForm.this);

        dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
        dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_check);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_failure);
        dialog.show();
    }

    private void successDialog() {
        Dialog dialog = new Dialog(SubmissionForm.this);
        dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
        dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_check);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_success);
        dialog.show();
    }

    private void postData() {
        mProgressBar.setVisibility(View.VISIBLE);
        LeaderBoardService leaderBoardService = ServiceBuilder.buildService(LeaderBoardService.class);
        Call<Void> request = leaderBoardService.postSubmissionDetails(
                "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse",
//                My test google form
//                "https://docs.google.com/forms/u/0/d/e/1FAIpQLSeoMBpHPYrxzAhhMwWnkbwmzitT1HLYI5hRLcZnBMTaECPFlg/formResponse",
                mFirstName, mLastName, mEmail, mGitHubLink);

        request.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> request, Response<Void> response) {
                Log.d("ResponseCode", response.toString());
                Log.d("RequestCode", request.toString());
                mProgressBar.setVisibility(View.GONE);
                successDialog();
            }

            @Override
            public void onFailure(Call<Void> request, Throwable t) {
                Log.d("ResponseCode", request.toString());
                mProgressBar.setVisibility(View.GONE);
                failureDialog();
            }
        });
    }
}