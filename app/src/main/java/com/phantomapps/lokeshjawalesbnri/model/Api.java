package com.phantomapps.lokeshjawalesbnri.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("repos")
    Call<List<ApiResponse>> getRepos(
            @Query("page") int page,
            @Query("per_page") int size
    );

//    @GET("repos")
//    Call<ApiResponse> getRepos(
//            @Query("page") int page,
//            @Query("per_page") int size
//    );

}
