package com.android.phaseonechallengetwo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DealAdapter extends RecyclerView.Adapter<DealAdapter.DealAdapterViewHolder>
{
    private List<Deal> dealList = new ArrayList<>();
    private Context mContext;

    @NonNull
    @Override
    public DealAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(R.layout.adapter,parent,shouldAttachToParentImmediately);
        DealAdapterViewHolder dealAdapterViewHolder = new DealAdapterViewHolder(view);
        return dealAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DealAdapterViewHolder holder, int position)
    {
        Deal deal = dealList.get(position);
        holder.area.setText(deal.getArea());
        holder.destination.setText(deal.getDestination());
        holder.amount.setText(String.valueOf(deal.getAmount()));
    }

    @Override
    public int getItemCount() {
        return dealList.size();
    }


    public void setDealList(List<Deal> dealListList)
    {
        this.dealList = dealListList;
        notifyDataSetChanged();
    }

    class DealAdapterViewHolder extends RecyclerView.ViewHolder
    {
        TextView amount;
        TextView destination;
        TextView area;

        public DealAdapterViewHolder(View itemView)
        {
            super(itemView);
            amount = (TextView) itemView.findViewById(R.id.textView_amount);
            destination = (TextView) itemView.findViewById(R.id.textView_destination);
            area = (TextView)itemView.findViewById(R.id.textView_area);
        }
    }
}
