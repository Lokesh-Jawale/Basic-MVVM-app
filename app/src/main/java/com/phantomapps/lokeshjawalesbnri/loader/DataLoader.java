package com.phantomapps.lokeshjawalesbnri.loader;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.phantomapps.lokeshjawalesbnri.QuerryData;
import com.phantomapps.lokeshjawalesbnri.model.DataEntity;

import java.util.ArrayList;

public class DataLoader extends AsyncTaskLoader<ArrayList<DataEntity>> {

    public DataLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public ArrayList<DataEntity> loadInBackground() {
        ArrayList<DataEntity> dataEntities = new QuerryData().extractDataFromUrl();

        return dataEntities;
    }

}
