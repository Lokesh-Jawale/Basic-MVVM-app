package com.phantomapps.lokeshjawalesbnri;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phantomapps.lokeshjawalesbnri.adapter.RecyclerViewAdapter;
import com.phantomapps.lokeshjawalesbnri.loader.DataLoader;
import com.phantomapps.lokeshjawalesbnri.model.DataEntity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<ArrayList<DataEntity>> {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private TextView emptyText;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_activity);

        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        emptyText = (TextView)findViewById(R.id.emptyText);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);

        ConnectivityManager connMgr = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.initLoader(1, null, this).forceLoad();
        }else{
            emptyText.setText("NO Internet Connection");
            progressBar.setVisibility(View.GONE);
        }

    }

    private void initiateRecyclerView(ArrayList<DataEntity> finalList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new RecyclerViewAdapter(this, finalList));
        progressBar.setVisibility(View.GONE);
    }

    @NonNull
    @Override
    public Loader<ArrayList<DataEntity>> onCreateLoader(int id, @Nullable Bundle args) {
        return new DataLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<DataEntity>> loader, ArrayList<DataEntity> data) {
        initiateRecyclerView(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<DataEntity>> loader) {
        loader.reset();
    }

}
