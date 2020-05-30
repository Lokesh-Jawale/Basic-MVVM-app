package com.phantomapps.lokeshjawalesbnri.datasource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.phantomapps.lokeshjawalesbnri.RetrofitClient;
import com.phantomapps.lokeshjawalesbnri.model.ApiResponse;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, ApiResponse> {

    public static final int PAGE = 1;
    public static final int PAGE_SIZE = 10;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, ApiResponse> callback) {

        RetrofitClient.getInstance()
                .getApi()
                .getRepos(PAGE, PAGE_SIZE)
                .enqueue(new Callback<List<ApiResponse>>() {
                    @Override
                    public void onResponse(Call<List<ApiResponse>> call, Response<List<ApiResponse>> response) {

                        if(response.body() != null){
                            callback.onResult(response.body(), null, PAGE + 1);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<ApiResponse>> call, Throwable t) {

                    }
                });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ApiResponse> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getRepos(params.key, PAGE_SIZE)
                .enqueue(new Callback<List<ApiResponse>>() {
                    @Override
                    public void onResponse(Call<List<ApiResponse>> call, Response<List<ApiResponse>> response) {
                        if(response.body() != null){
                            Integer key = (params.key > 1) ? params.key - 1: null;

                            callback.onResult(response.body(), key);

                        }
                    }

                    @Override
                    public void onFailure(Call<List<ApiResponse>> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ApiResponse> callback) {

        RetrofitClient.getInstance()
                .getApi()
                .getRepos(params.key, PAGE_SIZE)
                .enqueue(new Callback<List<ApiResponse>>() {
                    @Override
                    public void onResponse(Call<List<ApiResponse>> call, Response<List<ApiResponse>> response) {
                        if(response.body() != null){
                            Integer key = params.key + 1;
                            try {
                                callback.onResult(response.body(), key);
                            }catch (Exception e){
                                e.getMessage();
                            }finally {
                                Log.d("aDebugTag","///// |||||||||||?/////////////" +params.key);
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<List<ApiResponse>> call, Throwable t) {

                    }
                });
    }

}
