package com.example.nsyri;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nsyri.adapter.FigureAdapter;
import com.example.nsyri.model.Figure;

import java.util.ArrayList;
import java.util.List;

public class HistoricalFiguresActivity extends AppCompatActivity implements FigureAdapter.OnFigureClickListener {

    private RecyclerView recyclerView;
    private FigureAdapter adapter;
    private List<Figure> figures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical_figures);

        // Toolbar'ı ayarla
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.historical_figures);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.figures_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Veri listesini oluştur
        figures = generateFiguresList();

        // Adapter'ı oluştur ve RecyclerView'a bağla
        adapter = new FigureAdapter(this, figures, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    // Tarihi kişilerin listesini oluştur
    private List<Figure> generateFiguresList() {
        List<Figure> figuresList = new ArrayList<>();
        
        // Figure modeli artık (String name, int imageResourceId, String biography) formatını kullanıyor
        figuresList.add(new Figure("İbn Nusayr", 
                R.drawable.ibn_nusayr, 
                "İbn Nusayr, Nusayrilik inancının kurucusu olarak kabul edilir. 9. yüzyılda yaşamış ve Nusayri topluluğunun temellerini atmıştır."));
        
        figuresList.add(new Figure("Hüseyin bin Hamdan el-Hasîbî", 
                R.drawable.el_hasibi, 
                "Hüseyin bin Hamdan el-Hasîbî, 10. yüzyılda yaşamış ve Nusayriliğin en önemli din adamlarından biridir. İnancın yayılmasında büyük rol oynamıştır."));
        
        figuresList.add(new Figure("Ebû Sa'îd Meymûn et-Taberânî", 
                R.drawable.et_taberani, 
                "Ebû Sa'îd Meymûn et-Taberânî, 11. yüzyılda yaşamış ve Nusayriliğin önemli din adamlarından biridir. Birçok dini eserin yazarıdır."));
        
        // Daha fazla tarihî kişi eklenebilir
        
        return figuresList;
    }

    @Override
    public void onFigureClick(Figure figure) {
        // Figür detay sayfasına yönlendirme
        Intent intent = new Intent(this, FigureDetailActivity.class);
        intent.putExtra("FIGURE_NAME", figure.getName());
        intent.putExtra("FIGURE_IMAGE", figure.getImageResourceId());
        intent.putExtra("FIGURE_BIO", figure.getBiography());
        startActivity(intent);
    }
} 