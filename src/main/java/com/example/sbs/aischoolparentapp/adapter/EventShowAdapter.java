package com.example.sbs.aischoolparentapp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.sbs.aischoolparentapp.Config.ConstValue;
import com.example.sbs.aischoolparentapp.R;
import com.example.sbs.aischoolparentapp.model.EventModel;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by munnu on 09/03/2017.
 */

public class EventShowAdapter extends RecyclerView.Adapter<EventShowAdapter.MyViewHolder> {
    List<EventModel> cardList;
    Context context;

    public EventShowAdapter(Context context, List<EventModel> cardList) {
        this.context = context;
        this.cardList = cardList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        ImageView ivEventImageId;
        TextView tvEventNameId, tvEventStartTimeId, tvLocationId, tvEventEndTimeId;

        public MyViewHolder(View view) {
            super(view);
            card = (CardView) view.findViewById(R.id.CardView);
            tvEventNameId = (TextView) view.findViewById(R.id.tvEventNameId);
            tvEventStartTimeId = (TextView) view.findViewById(R.id.tvEventStartTimeId);
            tvEventEndTimeId = (TextView) view.findViewById(R.id.tvEventEndTimeId);
            tvLocationId = (TextView) view.findViewById(R.id.tvLocationId);

            ivEventImageId = (ImageView) view.findViewById(R.id.ivEventImageId);

        }

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.eventlist, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final EventModel clickCategoryListObject = cardList.get(position);
        holder.tvEventNameId.setText(clickCategoryListObject.getEvent_title());
        holder.tvEventStartTimeId.setText(clickCategoryListObject.getEvent_start());
        holder.tvEventEndTimeId.setText(clickCategoryListObject.getEvent_end());
        holder.tvLocationId.setText(clickCategoryListObject.getLocation());

        Picasso.with(context)
                .load(ConstValue.IMAGE_BASE_URL + clickCategoryListObject.getEvent_image())
                .into(holder.ivEventImageId);
    }


    @Override
    public int getItemCount() {
        return cardList.size();
    }
}


