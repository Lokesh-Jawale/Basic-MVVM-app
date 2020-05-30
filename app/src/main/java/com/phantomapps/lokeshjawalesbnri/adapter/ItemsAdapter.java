package com.phantomapps.lokeshjawalesbnri.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.phantomapps.lokeshjawalesbnri.R;
import com.phantomapps.lokeshjawalesbnri.model.ApiResponse;

public class ItemsAdapter extends PagedListAdapter<ApiResponse, ItemsAdapter.MyViewHolder> {

    private static final DiffUtil.ItemCallback<ApiResponse> diffCallback =
            new DiffUtil.ItemCallback<ApiResponse>() {
                @Override
                public boolean areItemsTheSame(@NonNull ApiResponse oldItem, @NonNull ApiResponse newItem) {
                    return oldItem.description.equals(newItem.description);
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull ApiResponse oldItem, @NonNull ApiResponse newItem) {
                    return oldItem.equals(newItem);
                }
            };

    public Context context;

    public ItemsAdapter(Context context) {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_data_activity, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TextView nameText = holder.nameText;
        TextView desText = holder.desText;
        TextView openCasesCountText = holder.openCasesCountText;
        TextView licenseNameText = holder.licenseNameText;
        TextView keyText = holder.keyText;
        TextView spdxText = holder.spdxText;
        TextView urlText = holder.urlText;
        TextView nodeIdText = holder.nodeIdText;
        TextView adminText = holder.adminText;
        TextView pushText = holder.pushText;
        TextView pullText = holder.pullText;

        ApiResponse list = getItem(position);

        if (list != null) {
            //Log.d("aDebugTag", " /////////////////||||||||||||||||||////////////// " + list.license.getLicenseName());

            if (list.getName() != null)
                nameText.setText(list.getName());
            if (list.getDescription() != null)
                desText.setText(list.getDescription());
            if (list.getOpen_issues_count() != null)
                openCasesCountText.setText(list.getOpen_issues_count());

            if (list.license != null) {
                if (list.license.getName() != null)
                    licenseNameText.setText(list.license.getName());
                else
                    licenseNameText.setText("-");
                if (list.license.getKey() != null)
                    keyText.setText(list.license.getKey());
                else
                    keyText.setText("-");
                if (list.license.getSpdx_id() != null)
                    spdxText.setText(list.license.getSpdx_id());
                else
                    spdxText.setText("-");
                if (list.license.getUrl() != null)
                    urlText.setText(list.license.getUrl());
                else
                    urlText.setText("-");
                if (list.license.getNode_id() != null)
                    nodeIdText.setText(list.license.getNode_id());
                else
                    nodeIdText.setText("-");
            } else {
                licenseNameText.setText("-");
                keyText.setText("-");
                spdxText.setText("-");
                urlText.setText("-");
                nodeIdText.setText("-");
            }

            if (list.permissions != null) {
                if (list.permissions.getAdmin() != null)
                    adminText.setText(list.permissions.getAdmin());
                else
                    adminText.setText("-");
                if (list.permissions.getPush() != null)
                    pushText.setText(list.permissions.getPush());
                else
                    pullText.setText("-");
                if (list.permissions.getPull() != null)
                    pullText.setText(list.permissions.getPull());
                else
                    pushText.setText("-");
            }else{
                pushText.setText("-");
                pullText.setText("-");
                adminText.setText("-");
            }
        }

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
            this.nameText = (TextView) itemView.findViewById(R.id.name_text);
            this.desText = (TextView) itemView.findViewById(R.id.description_text);
            this.openCasesCountText = (TextView) itemView.findViewById(R.id.open_issues_count_text);
            this.keyText = (TextView) itemView.findViewById(R.id.license_key_text);
            this.licenseNameText = (TextView) itemView.findViewById(R.id.license_name_text);
            this.spdxText = (TextView) itemView.findViewById(R.id.license_spdx_id_text);
            this.urlText = (TextView) itemView.findViewById(R.id.license_url_text);
            this.nodeIdText = (TextView) itemView.findViewById(R.id.license_node_id_text);
            this.adminText = (TextView) itemView.findViewById(R.id.permissions_admin_text);
            this.pushText = (TextView) itemView.findViewById(R.id.permissions_push_text);
            this.pullText = (TextView) itemView.findViewById(R.id.permissions_pull_text);
        }

    }

}
