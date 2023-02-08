package com.example.poetryapp.Api;

import com.example.poetryapp.Response.DeleteResponse;
import com.example.poetryapp.Response.GetPoetryResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("getpoetry.php")
    Call<GetPoetryResponse> getpoetry();

    @FormUrlEncoded
    @POST("deletepoetry.php")
    Call<DeleteResponse> deletepoetry(@Field("pid")String Poetry_ID);

    @FormUrlEncoded
    @POST("addpoetry.php")
    Call<DeleteResponse> addpoetry (@Field("poetry_data")String poetry_data ,@Field("poet_name")String poet_name);

    @FormUrlEncoded
    @POST("updatepoetry.php")
    Call<DeleteResponse> updatepoetry(@Field("pd") String poetryData,@Field("id") String pid);





}
