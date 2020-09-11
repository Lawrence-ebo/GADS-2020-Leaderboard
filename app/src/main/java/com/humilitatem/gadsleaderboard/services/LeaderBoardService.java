package com.humilitatem.gadsleaderboard.services;

import androidx.annotation.Nullable;

import com.humilitatem.gadsleaderboard.model.Learner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface LeaderBoardService {
    @GET("api/hours")
    Call<List<Learner>>  getLearningLeaders();

    @GET("api/skilliq")
    Call<List<Learner>> getSkillIQLeaders();

    //GADS Submission Form
    @POST()
    @FormUrlEncoded
    Call<Void> postSubmissionDetails(@Url String altUrl,
                                     @Field("entry.1877115667") @Nullable String firstName,
                                     @Field("entry.2006916086") @Nullable String lastName,
                                     @Field("entry.1824927963") @Nullable String emailAddress,
                                     @Field("entry.284483984") @Nullable String githubLink

    );

    //My test form
// @POST()
//    @FormUrlEncoded
//    Call<Void> postSubmissionDetails(@Url String altUrl,
//                                     @Field("entry.2005620554") @Nullable String firstName,
//                                     @Field("entry.487965518") @Nullable String lastName,
//                                     @Field("entry.1045781291") @Nullable String emailAddress,
//                                     @Field("entry.1065046570") @Nullable String githubLink
//
//    );
}
