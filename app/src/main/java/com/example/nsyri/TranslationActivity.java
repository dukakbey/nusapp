package com.example.nsyri;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.card.MaterialCardView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class TranslationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try {
            // Layout'u ayarla
            setContentView(R.layout.activity_translation);

            // Toolbar'ı ayarla
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle(R.string.translation_title);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            
            // Kategori kartlarını ayarla
            initCategoryCards();
        } 
        catch (Exception e) {
            // Hata mesajı göster
            Toast.makeText(this, "Hata: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    
    private void initCategoryCards() {
        // Dini İfadeler
        MaterialCardView cardReligiousExpressions = findViewById(R.id.cardReligiousExpressions);
        setupCategoryCard(cardReligiousExpressions, getString(R.string.category_religious));
        
        // Temel Kelimeler
        MaterialCardView cardBasicWords = findViewById(R.id.cardBasicWords);
        setupCategoryCard(cardBasicWords, getString(R.string.category_basic));
        
        // Aile ve İnsanlar
        MaterialCardView cardFamily = findViewById(R.id.cardFamily);
        setupCategoryCard(cardFamily, getString(R.string.category_family));
        
        // Samandağ/Antakya Ağızları
        MaterialCardView cardDialect = findViewById(R.id.cardDialect);
        setupCategoryCard(cardDialect, getString(R.string.category_dialect));
        
        // Sık Duyulan Söz Kalıpları
        MaterialCardView cardPhrases = findViewById(R.id.cardPhrases);
        setupCategoryCard(cardPhrases, getString(R.string.category_phrases));
        
        // Ev ve Aile Ortamı
        MaterialCardView cardHomeFamily = findViewById(R.id.cardHomeFamily);
        setupCategoryCard(cardHomeFamily, getString(R.string.category_home_family));
        
        // Duygular ve Tepkiler
        MaterialCardView cardEmotions = findViewById(R.id.cardEmotions);
        setupCategoryCard(cardEmotions, getString(R.string.category_emotions));
        
        // Zaman ve Günlük Hayat
        MaterialCardView cardTimeDaily = findViewById(R.id.cardTimeDaily);
        setupCategoryCard(cardTimeDaily, getString(R.string.category_time_daily));
    }
    
    private void setupCategoryCard(MaterialCardView cardView, String categoryName) {
        if (cardView == null) return;
        
        // Tıklama olayını ekle
        cardView.setOnClickListener(view -> {
            // Toast mesajı göster
            Toast.makeText(this, categoryName + " kategorisi seçildi", Toast.LENGTH_SHORT).show();
            
            // CategoryDetailActivity'ye yönlendir
            Intent intent = new Intent(TranslationActivity.this, CategoryDetailActivity.class);
            intent.putExtra("CATEGORY_NAME", categoryName);
            startActivity(intent);
        });

        // Ripple effect için dokunma geri bildirimini etkinleştir
        cardView.setClickable(true);
        cardView.setFocusable(true);
    }
    
    // Dialog gösteren eski metot - kullanılmıyor artık
    private void showCategoryDialog(String title, String content) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(content)
                .setPositiveButton(R.string.dialog_close, (dialog, which) -> dialog.dismiss())
                .show();
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