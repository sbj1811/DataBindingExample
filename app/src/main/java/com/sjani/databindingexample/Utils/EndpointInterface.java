package com.sjani.databindingexample.Utils;

import com.sjani.databindingexample.Models.UserEvents;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndpointInterface {
    String API_ENDPOINT = "https://s3-us-west-2.amazonaws.com";

    @GET("/ph-svc-mobile-interview-jyzi2gyja/propeller_mobile_assessment_data.json")
    Call<UserEvents> getEvents();
}
