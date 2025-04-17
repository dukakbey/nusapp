package com.example.nsyri;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FigureDetailActivity extends AppCompatActivity {

    private ImageView figureImageView;
    private TextView figureNameTextView;
    private TextView figureBioTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figure_detail);

        // UI bileşenlerini başlat
        figureImageView = findViewById(R.id.detail_figure_image);
        figureNameTextView = findViewById(R.id.detail_figure_name);
        figureBioTextView = findViewById(R.id.detail_figure_bio);

        // Toolbar'ı ayarla
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.figure_details);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Intent'ten verileri al
        if (getIntent() != null) {
            String name = getIntent().getStringExtra("FIGURE_NAME");
            int imageResourceId = getIntent().getIntExtra("FIGURE_IMAGE", 0);
            String biography = getIntent().getStringExtra("FIGURE_BIO");

            // Verileri UI bileşenlerine yerleştir
            if (name != null) {
                figureNameTextView.setText(name);
            }
            
            if (imageResourceId != 0) {
                figureImageView.setImageResource(imageResourceId);
            }
            
            if (biography != null) {
                figureBioTextView.setText(biography);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
} 