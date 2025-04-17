package com.example.nsyri;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nsyri.adapter.PhraseAdapter;
import com.example.nsyri.model.Phrase;

import java.util.ArrayList;
import java.util.List;

public class CategoryDetailActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PhraseAdapter adapter;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);

        // Toolbar'ı ayarla
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        // Intent'ten kategori adını al
        categoryName = getIntent().getStringExtra("CATEGORY_NAME");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(categoryName);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        
        // RecyclerView'ı ayarla
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        // Kategori verilerini yükle
        loadDataForCategory();
    }

    private void loadDataForCategory() {
        List<Phrase> phrases = new ArrayList<>();
        
        // Kategoriye göre verileri yükle
        if (categoryName.equals("Samandağ / Antakya Ağızları")) {
            phrases.add(new Phrase("Nereye gidiyorsun?", "وين رايح؟", "weyn rāyeḥ?", "Samandağ / Antakya Ağızları"));
            phrases.add(new Phrase("Baksana şuna!", "اطلع هاد", "ṭalle' hād!", "Samandağ / Antakya Ağızları"));
            phrases.add(new Phrase("Napıyon orda?", "شو عم تعمل هنيك؟", "şū 'am ta'mel hnīk?", "Samandağ / Antakya Ağızları"));
            phrases.add(new Phrase("Biraz bekle", "نطر شوي", "naṭṭer şway", "Samandağ / Antakya Ağızları"));
            phrases.add(new Phrase("Ben geldim", "اجيت", "ijeet", "Samandağ / Antakya Ağızları"));
            phrases.add(new Phrase("Görüşürüz", "منشوفك", "menşūfak", "Samandağ / Antakya Ağızları"));
            phrases.add(new Phrase("Yavaş ol", "روّق", "rawwa'", "Samandağ / Antakya Ağızları"));
            phrases.add(new Phrase("Ne var ne yok?", "شو في ما في؟", "şū fī mā fī?", "Samandağ / Antakya Ağızları"));
            phrases.add(new Phrase("Bilmiyorum valla", "ما بعرف والله", "mā ba'ref wallāh", "Samandağ / Antakya Ağızları"));
            phrases.add(new Phrase("Hadi canım!", "عن جد؟", "'an jadd?", "Samandağ / Antakya Ağızları"));
        } 
        else if (categoryName.equals("Dini İfadeler")) {
            phrases.add(new Phrase("Allah razı olsun", "الله يرضى عليك", "Allāh yırḍa 'aleyk", "Dini İfadeler"));
            phrases.add(new Phrase("Allah korusun", "الله يحميك", "Allāh yeḥmīk", "Dini İfadeler"));
            phrases.add(new Phrase("Allah kabul etsin", "الله يتقبل", "Allāh yet'abbal", "Dini İfadeler"));
            phrases.add(new Phrase("Dualarım seninle", "دعواتي معك", "da'wātī ma'ak", "Dini İfadeler"));
            phrases.add(new Phrase("Hayırlı olsun", "مبروك", "mabrūk", "Dini İfadeler"));
            phrases.add(new Phrase("Başın sağ olsun", "الله يرحمو", "Allāh yerḥmo", "Dini İfadeler"));
            phrases.add(new Phrase("Allah affetsin", "الله يسامحو", "Allāh yesāmeḥo", "Dini İfadeler"));
            phrases.add(new Phrase("İnşallah", "إن شاء الله", "in şā' Allāh", "Dini İfadeler"));
            phrases.add(new Phrase("Maşallah", "ما شاء الله", "mā şā' Allāh", "Dini İfadeler"));
            phrases.add(new Phrase("Elhamdülillah", "الحمد لله", "il-ḥamd lillāh", "Dini İfadeler"));
        }
        
        // Adapter'ı ayarla
        adapter = new PhraseAdapter(this, phrases);
        recyclerView.setAdapter(adapter);
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