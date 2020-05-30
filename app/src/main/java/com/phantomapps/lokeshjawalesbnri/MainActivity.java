package com.phantomapps.lokeshjawalesbnri;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.phantomapps.lokeshjawalesbnri.adapter.ItemsAdapter;
import com.phantomapps.lokeshjawalesbnri.model.ApiResponse;
import com.phantomapps.lokeshjawalesbnri.model.DataViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView emptyText;
    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_activity);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        emptyText = (TextView) findViewById(R.id.emptyText);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        ConnectivityManager connMgr = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {

            DataViewModel dataViewModel = ViewModelProviders.of(this).get(DataViewModel.class);

            ItemsAdapter adapter = new ItemsAdapter(MainActivity.this);

            dataViewModel.pagedListLiveData.observe(this, new Observer<PagedList<ApiResponse>>() {

                @Override
                public void onChanged(PagedList<ApiResponse> apiResponses) {
                    adapter.submitList(apiResponses);
                }

            });

            recyclerView.setAdapter(adapter);
//            LoaderManager loaderManager = getSupportLoaderManager();
//            loaderManager.initLoader(1, null, this).forceLoad();

        } else {
            emptyText.setText("NO Internet Connection");
            progressBar.setVisibility(View.GONE);
        }
        progressBar.setVisibility(View.GONE);
    }

}
