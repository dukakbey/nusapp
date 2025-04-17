package com.example.nsyri.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nsyri.R;
import com.example.nsyri.model.ReligiousDay;

import java.util.List;

public class ReligiousDaysAdapter extends RecyclerView.Adapter<ReligiousDaysAdapter.ViewHolder> {

    private final Context context;
    private final List<ReligiousDay> religiousDaysList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ReligiousDay religiousDay);
    }

    public ReligiousDaysAdapter(Context context, List<ReligiousDay> religiousDaysList, OnItemClickListener listener) {
        this.context = context;
        this.religiousDaysList = religiousDaysList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_religious_day, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReligiousDay religiousDay = religiousDaysList.get(position);
        
        holder.nameTextView.setText(religiousDay.getName());
        holder.dateTextView.setText(religiousDay.getDate());
        holder.daysRemainingTextView.setText(context.getString(R.string.days_remaining_count, religiousDay.getDaysRemaining()));
        holder.descriptionTextView.setText(religiousDay.getDescription());
        
        // Set background color based on whether the day is joyous or not
        if (religiousDay.isJoyous()) {
            holder.background.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        } else {
            holder.background.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        }
        
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(religiousDay);
            }
        });
    }

    @Override
    public int getItemCount() {
        return religiousDaysList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView nameTextView;
        TextView dateTextView;
        TextView daysRemainingTextView;
        TextView descriptionTextView;
        LinearLayout background;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            nameTextView = itemView.findViewById(R.id.tv_religious_day_name);
            dateTextView = itemView.findViewById(R.id.tv_religious_day_date);
            daysRemainingTextView = itemView.findViewById(R.id.tv_days_remaining);
            descriptionTextView = itemView.findViewById(R.id.tv_religious_day_description);
            background = itemView.findViewById(R.id.religious_day_background);
        }
    }
} 