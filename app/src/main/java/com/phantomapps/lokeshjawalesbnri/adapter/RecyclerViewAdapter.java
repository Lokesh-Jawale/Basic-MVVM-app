package com.phantomapps.lokeshjawalesbnri.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.phantomapps.lokeshjawalesbnri.MainActivity;
import com.phantomapps.lokeshjawalesbnri.R;
import com.phantomapps.lokeshjawalesbnri.model.DataEntity;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private ArrayList<DataEntity> dlist;
    private Context mContext;

    public RecyclerViewAdapter( Context context, ArrayList<DataEntity> dlist){
        this.mContext = context;
        this.dlist = dlist;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameText;
        TextView desText;
        TextView openCasesCountText;
        TextView keyText;
        TextView licenseNameText;
        TextView spdxText;
        TextView urlText;
        TextView nodeIdText;
        TextView adminText;
        TextView pushText;
        TextView pullText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.nameText = (TextView)itemView.findViewById(R.id.name_text);
            this.desText = (TextView)itemView.findViewById(R.id.description_text);
            this.openCasesCountText = (TextView)itemView.findViewById(R.id.open_issues_count_text);
            this.keyText = (TextView)itemView.findViewById(R.id.license_key_text);
            this.licenseNameText = (TextView)itemView.findViewById(R.id.license_name_text);
            this.spdxText = (TextView)itemView.findViewById(R.id.license_spdx_id_text);
            this.urlText = (TextView)itemView.findViewById(R.id.license_url_text);
            this.nodeIdText = (TextView)itemView.findViewById(R.id.license_node_id_text);
            this.adminText = (TextView)itemView.findViewById(R.id.permissions_admin_text);
            this.pushText = (TextView)itemView.findViewById(R.id.permissions_push_text);
            this.pullText = (TextView)itemView.findViewById(R.id.permissions_pull_text);
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_data_activity, viewGroup , false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        TextView nameText = myViewHolder.nameText;
        TextView desText = myViewHolder.desText;
        TextView openCasesCountText = myViewHolder.openCasesCountText;
        TextView licenseNameText = myViewHolder.licenseNameText;
        TextView keyText = myViewHolder.keyText;
        TextView spdxText = myViewHolder.spdxText;
        TextView urlText = myViewHolder.urlText;
        TextView nodeIdText = myViewHolder.nodeIdText;
        TextView adminText = myViewHolder.adminText;
        TextView pushText = myViewHolder.pushText;
        TextView pullText = myViewHolder.pullText;

        Log.d("aDebugTag","//////   views binded");

        DataEntity list = dlist.get(i);

        if(list.getName() != null)
            nameText.setText(list.getName());
        if(list.getDes() != null)
            desText.setText(list.getDes());
        if(list.getOpen_issues_count() != null)
            openCasesCountText.setText(list.getOpen_issues_count());
        if(list.getLicenseName() != null)
            licenseNameText.setText(list.getLicenseName());
        if(list.getKey() != null)
            keyText.setText(list.getKey());
        if(list.getSpdxId() != null)
            spdxText.setText(list.getSpdxId());
        if(list.getUrl() != null)
            urlText.setText(list.getUrl());
        if(list.getNodeId() != null)
            nodeIdText.setText(list.getNodeId());
        if(list.getAdmin() != null)
            adminText.setText(list.getAdmin());
        if(list.getPush() != null)
            pushText.setText(list.getPush());
        if(list.getPull() != null)
            pullText.setText(list.getPull());

    }

    @Override
    public int getItemCount() {
        return dlist.size();
    }

}
