package com.example.nsyri.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nsyri.R;
import com.example.nsyri.model.Phrase;

import java.util.List;

public class PhraseAdapter extends RecyclerView.Adapter<PhraseAdapter.PhraseViewHolder> {

    private Context context;
    private List<Phrase> phrases;

    public PhraseAdapter(Context context, List<Phrase> phrases) {
        this.context = context;
        this.phrases = phrases;
    }

    @NonNull
    @Override
    public PhraseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_phrase, parent, false);
        return new PhraseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhraseViewHolder holder, int position) {
        Phrase phrase = phrases.get(position);
        holder.tvTurkish.setText(phrase.getTurkishText());
        holder.tvArabic.setText(phrase.getNusayriText());
        holder.tvPronunciation.setText(phrase.getPronunciation());
    }

    @Override
    public int getItemCount() {
        return phrases.size();
    }

    static class PhraseViewHolder extends RecyclerView.ViewHolder {
        TextView tvTurkish, tvArabic, tvPronunciation;

        public PhraseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTurkish = itemView.findViewById(R.id.tvTurkish);
            tvArabic = itemView.findViewById(R.id.tvArabic);
            tvPronunciation = itemView.findViewById(R.id.tvPronunciation);
        }
    }
} 