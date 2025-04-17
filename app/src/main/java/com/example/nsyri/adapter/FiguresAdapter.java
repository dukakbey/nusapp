package com.example.nsyri.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nsyri.R;
import com.example.nsyri.model.Figure;

import java.util.List;

public class FiguresAdapter extends RecyclerView.Adapter<FiguresAdapter.FigureViewHolder> {

    private List<Figure> figures;
    private Context context;
    private OnFigureClickListener listener;

    public interface OnFigureClickListener {
        void onFigureClick(Figure figure);
    }

    public FiguresAdapter(Context context, List<Figure> figures, OnFigureClickListener listener) {
        this.context = context;
        this.figures = figures;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FigureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.figures_list_item, parent, false);
        return new FigureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FigureViewHolder holder, int position) {
        Figure figure = figures.get(position);
        holder.figureImage.setImageResource(figure.getImageResourceId());
        holder.figureName.setText(figure.getName());
        
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onFigureClick(figure);
            }
        });
    }

    @Override
    public int getItemCount() {
        return figures.size();
    }

    public static class FigureViewHolder extends RecyclerView.ViewHolder {
        ImageView figureImage;
        TextView figureName;

        public FigureViewHolder(@NonNull View itemView) {
            super(itemView);
            figureImage = itemView.findViewById(R.id.figure_image);
            figureName = itemView.findViewById(R.id.figure_name);
        }
    }
} 