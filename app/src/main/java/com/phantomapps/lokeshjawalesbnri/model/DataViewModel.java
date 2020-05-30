package com.phantomapps.lokeshjawalesbnri.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.phantomapps.lokeshjawalesbnri.datasource.ItemDataSource;
import com.phantomapps.lokeshjawalesbnri.datasource.ItemDataSourceFactory;

public class DataViewModel extends ViewModel {

    public LiveData<PagedList<ApiResponse>> pagedListLiveData;
    public LiveData<PageKeyedDataSource<Integer, ApiResponse>> liveData;

    public DataViewModel(){
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        liveData = itemDataSourceFactory.getLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(ItemDataSource.PAGE)
                .build();

        pagedListLiveData = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();

    }

}
