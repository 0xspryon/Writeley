package com.springfield.y.writeley.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.springfield.y.writeley.R;

/**
 * Created by ipr0d1g1 on 6/30/18.
 */

public class MonthSelectorAdapter extends RecyclerView.Adapter<MonthSelectorAdapter.MonthVH> {

    private final String[] months;

    public MonthSelectorAdapter(Context context) {
        months = context.getResources().getStringArray(R.array.months_of_year);
    }

    @NonNull
    @Override
    public MonthVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_month, parent, false);

        return new MonthVH(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MonthVH holder, int position) {
        String month = months[position];
        holder.monthTv.setText(month);
    }

    @Override
    public int getItemCount() {
        return months.length;
    }

    public class MonthVH extends RecyclerView.ViewHolder {
        public TextView monthTv;

        public MonthVH(View view) {
            super(view);
            monthTv = view.findViewById(R.id.text_month);
        }
    }
}
