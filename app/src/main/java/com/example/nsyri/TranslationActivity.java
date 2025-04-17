package com.example.nsyri;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class TranslationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try {
            // Test mesajı göster
            Toast.makeText(this, "Çeviri ve Konuşma Rehberi açılıyor...", Toast.LENGTH_SHORT).show();
            
            // Layout'u ayarla
            setContentView(R.layout.activity_translation);

            // Toolbar'ı ayarla
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(R.string.translation_title);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            
            // Test mesajı göster
            Toast.makeText(this, "Aktivite başarıyla yüklendi", Toast.LENGTH_SHORT).show();
        } 
        catch (Exception e) {
            // Hata mesajı göster
            Toast.makeText(this, "Hata: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
} 