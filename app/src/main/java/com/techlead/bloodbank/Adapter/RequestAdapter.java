package com.techlead.bloodbank.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.techlead.bloodbank.DataModel.RequestData;
import com.techlead.bloodbank.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {
    private Context context;
    private List<RequestData> requestDataList;

    public RequestAdapter(Context context, List<RequestData> requestDataList) {
        this.context = context;
        this.requestDataList = requestDataList;
    }

    @NonNull
    @NotNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.request_items,parent,false);

        return new RequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RequestAdapter.RequestViewHolder holder, int position) {
        //holder.imageView.setImageResource(requestDataList.get(position).getImageUrl());
        Glide.with(context).load(requestDataList.get(position).getImageUrl()).into(holder.imageView);
        holder.callButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // for later
            }
        });
        holder.shareButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // for later also
            }
        });

    }

    @Override
    public int getItemCount() {
        return requestDataList.size();
    }

    public class RequestViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,callButtonView,shareButtonView;
        TextView messageTextView;
        public RequestViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.message);
            imageView = itemView.findViewById(R.id.image);
            callButtonView = itemView.findViewById(R.id.call_button);
            shareButtonView = itemView.findViewById(R.id.share_button);
        }
    }
}
