package com.example.nsyri;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView historicalFiguresRecyclerView;
    private RecyclerView galleryRecyclerView;
    private TextView historyContent;
    private TextView geographyContent;
    private List<HistoricalFigure> historicalFigures;
    private List<GalleryItem> galleryItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        try {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }

            // İçerik metinleri
            historyContent = findViewById(R.id.historyContent);
            geographyContent = findViewById(R.id.geographyContent);

            // RecyclerView'lar
            historicalFiguresRecyclerView = findViewById(R.id.historicalFiguresRecyclerView);
            galleryRecyclerView = findViewById(R.id.galleryRecyclerView);

            // İçerikleri yükle
            loadHistoryContent();
            loadGeographyContent();
            setupHistoricalFiguresRecyclerView();
            setupGalleryRecyclerView();
        } catch (Exception e) {
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

    private void loadHistoryContent() {
        // Gerçek uygulamada bu içerik veritabanından veya API'den yüklenecek
        String historyText = "Nusayrilik veya Arap Aleviliği, 9. yüzyılda Irak'ta ortaya çıkmış ve " +
                "günümüzde çoğunlukla Suriye, Türkiye ve Lübnan'da yaşayan bir Şii İslam mezhebidir. " +
                "Kurucu olarak kabul edilen Muhammed bin Nusayr'ın ardından adını almıştır.\n\n" +
                "Nusayriler, İslam'ın temel ilkelerini kabul etmekle birlikte kendilerine has inanç ve " +
                "ritüellere sahiptir. Senkretik yapıları, gizlilik prensipleri ve ezoterik öğretileriyle " +
                "bilinirler. Topluluğun tarihsel süreçte çeşitli siyasi ve dini baskılara maruz kaldığı " +
                "bilinmektedir.\n\n" +
                "Türkiye'de çoğunlukla Hatay, özellikle Samandağ ve çevresinde yaşamaktadırlar.";
        
        if (historyContent != null) {
            historyContent.setText(historyText);
        }
    }

    private void loadGeographyContent() {
        // Gerçek uygulamada bu içerik veritabanından veya API'den yüklenecek
        String geographyText = "Nusayriler bugün ağırlıklı olarak şu bölgelerde yaşamaktadırlar:\n\n" +
                "- Suriye: Lazkiye, Tartus ve Hama vilayetleri\n" +
                "- Türkiye: Hatay (özellikle Samandağ), Adana, Mersin\n" +
                "- Lübnan: Kuzey bölgeler, Trablus çevresi\n\n" +
                "Türkiye'deki en önemli yerleşim yerleri Hatay ilindeki Samandağ ilçesi ve çevresidir. " +
                "Burada geleneksel yaşam biçimlerini ve kültürel kimliklerini korumaya devam etmektedirler.";
        
        if (geographyContent != null) {
            geographyContent.setText(geographyText);
        }
    }

    private void setupHistoricalFiguresRecyclerView() {
        try {
            // Tarihi kişiler verilerini oluştur
            createHistoricalFiguresData();

            // RecyclerView'ı ayarla
            if (historicalFiguresRecyclerView != null) {
                historicalFiguresRecyclerView.setLayoutManager(
                        new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                
                if (historicalFigures != null && !historicalFigures.isEmpty()) {
                    historicalFiguresRecyclerView.setAdapter(new HistoricalFigureAdapter(historicalFigures));
                } else {
                    // Boş bir adaptör kullan
                    historicalFiguresRecyclerView.setAdapter(new EmptyAdapter());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void createHistoricalFiguresData() {
        historicalFigures = new ArrayList<>();
        
        // Tarihi kişiler verilerini oluştur
        historicalFigures.add(new HistoricalFigure(
            1,
            "Muhammed bin Nusayr",
            "9. Yüzyıl",
            "muhammad_bin_nusayr",
            "Nusayriliğin kurucusu olarak kabul edilen Muhammed bin Nusayr, 9. yüzyılda yaşamıştır. " +
            "İmam Ali'nin ilahiliğine inanan ve bu inancı yayan kişi olarak bilinir. " +
            "Nusayriliğin temel inanç sistemini oluşturmuştur."
        ));
        
        historicalFigures.add(new HistoricalFigure(
            2,
            "Hüseyin bin Hamdan el-Hasibi",
            "10. Yüzyıl",
            "husayn_ibn_hamdan",
            "Nusayriliğin önemli şahsiyetlerinden biri olan Hüseyin bin Hamdan el-Hasibi, " +
            "10. yüzyılda yaşamış ve Nusayriliğin yayılmasında büyük rol oynamıştır. " +
            "Nusayriliğin temel metinlerinden olan 'Kitabü'l-Mecmu'yu yazmıştır."
        ));
        
        historicalFigures.add(new HistoricalFigure(
            3,
            "Ebu Said el-Meymun",
            "11. Yüzyıl",
            "abu_said",
            "Nusayriliğin önemli şahsiyetlerinden biri olan Ebu Said el-Meymun, " +
            "11. yüzyılda yaşamış ve Nusayriliğin yayılmasında etkili olmuştur. " +
            "Nusayriliğin inanç sistemini geliştiren ve yayan kişilerden biridir."
        ));
        
        historicalFigures.add(new HistoricalFigure(
            4,
            "Hamza bin Ali",
            "11. Yüzyıl",
            "hamza_bin_ali",
            "Nusayriliğin önemli şahsiyetlerinden biri olan Hamza bin Ali, " +
            "11. yüzyılda yaşamış ve Nusayriliğin yayılmasında etkili olmuştur. " +
            "Nusayriliğin inanç sistemini geliştiren ve yayan kişilerden biridir."
        ));
    }

    private void setupGalleryRecyclerView() {
        try {
            // Galeri verilerini oluştur
            createGalleryData();
            
            // RecyclerView'ı ayarla
            if (galleryRecyclerView != null) {
                galleryRecyclerView.setLayoutManager(
                        new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                
                if (galleryItems != null && !galleryItems.isEmpty()) {
                    galleryRecyclerView.setAdapter(new GalleryAdapter(galleryItems));
                } else {
                    // Boş bir adaptör kullan
                    galleryRecyclerView.setAdapter(new EmptyAdapter());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void createGalleryData() {
        galleryItems = new ArrayList<>();
        
        // Galeri verilerini oluştur
        galleryItems.add(new GalleryItem(
            1,
            "Nusayri Dini Ritüeli",
            "nusayri_ritual",
            "Nusayri topluluğunda gerçekleştirilen dini bir ritüel. Bu ritüeller, " +
            "Nusayriliğin önemli bir parçasıdır ve topluluk içinde nesilden nesile aktarılmaktadır."
        ));
        
        galleryItems.add(new GalleryItem(
            2,
            "Samandağ Bölgesi",
            "samandagi",
            "Türkiye'de Nusayrilerin yoğun olarak yaşadığı Hatay'ın Samandağ ilçesi. " +
            "Bu bölge, Nusayriliğin Türkiye'deki en önemli merkezlerinden biridir."
        ));
        
        galleryItems.add(new GalleryItem(
            3,
            "Antakya Şehri",
            "antakya",
            "Tarihi Antakya şehri. Nusayrilerin tarihsel olarak yaşadığı önemli merkezlerden biri. " +
            "Antakya, farklı inanç ve kültürlerin bir arada yaşadığı bir şehir olarak bilinir."
        ));
        
        galleryItems.add(new GalleryItem(
            4,
            "El Yazmaları",
            "manuscripts",
            "Nusayriliğe ait tarihi el yazması metinler. Bu metinler, Nusayriliğin inanç ve " +
            "ritüellerini anlamak için önemli kaynaklardır."
        ));
    }
    
    // Tarihi Kişi veri modeli
    private static class HistoricalFigure {
        private int id;
        private String name;
        private String period;
        private String imagePath;
        private String description;

        public HistoricalFigure(int id, String name, String period, String imagePath, String description) {
            this.id = id;
            this.name = name;
            this.period = period;
            this.imagePath = imagePath;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPeriod() {
            return period;
        }

        public String getImagePath() {
            return imagePath;
        }

        public String getDescription() {
            return description;
        }
    }
    
    // Galeri veri modeli
    private static class GalleryItem {
        private int id;
        private String title;
        private String imagePath;
        private String description;

        public GalleryItem(int id, String title, String imagePath, String description) {
            this.id = id;
            this.title = title;
            this.imagePath = imagePath;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getImagePath() {
            return imagePath;
        }

        public String getDescription() {
            return description;
        }
    }

    // Tarihi Kişiler için RecyclerView Adaptörü
    private class HistoricalFigureAdapter extends RecyclerView.Adapter<HistoricalFigureAdapter.HistoricalFigureViewHolder> {
        
        private List<HistoricalFigure> figures;
        
        public HistoricalFigureAdapter(List<HistoricalFigure> figures) {
            this.figures = figures;
        }
        
        @NonNull
        @Override
        public HistoricalFigureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(
                    R.layout.item_historical_figure, parent, false);
            return new HistoricalFigureViewHolder(view);
        }
        
        @Override
        public void onBindViewHolder(@NonNull HistoricalFigureViewHolder holder, int position) {
            HistoricalFigure figure = figures.get(position);
            holder.figureName.setText(figure.getName());
            
            // İlk olarak asset'lerden resmi yüklemeyi dene
            try {
                loadImageFromAssets(holder.figurePic, "figures/" + figure.getImagePath() + ".jpg");
            } catch (Exception e) {
                // Resim yüklenemediyse placeholderı kullan
                holder.figurePic.setImageResource(R.drawable.placeholder_person);
            }
            
            // Tarihi kişi detaylarına gitmek için tıklama olayını ayarla
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openFigureDetail(figure);
                }
            });
        }
        
        @Override
        public int getItemCount() {
            return figures.size();
        }
        
        class HistoricalFigureViewHolder extends RecyclerView.ViewHolder {
            ImageView figurePic;
            TextView figureName;
            
            public HistoricalFigureViewHolder(@NonNull View itemView) {
                super(itemView);
                figurePic = itemView.findViewById(R.id.figurePic);
                figureName = itemView.findViewById(R.id.figureName);
            }
        }
    }
    
    // Galeri için RecyclerView Adaptörü
    private class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

        private List<GalleryItem> galleryItems;

        public GalleryAdapter(List<GalleryItem> galleryItems) {
            this.galleryItems = galleryItems;
        }

        @NonNull
        @Override
        public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            try {
                View view = getLayoutInflater().inflate(R.layout.item_gallery_image, parent, false);
                return new GalleryViewHolder(view);
            } catch (Exception e) {
                e.printStackTrace();
                // Hata durumunda boş bir view oluştur
                View emptyView = new View(parent.getContext());
                return new GalleryViewHolder(emptyView);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
            try {
                final GalleryItem item = galleryItems.get(position);
                
                // Başlık ayarla
                if (holder.galleryCaption != null) {
                    holder.galleryCaption.setText(item.getTitle());
                }
                
                // Resim yükle
                if (holder.galleryImage != null && item.getImagePath() != null && !item.getImagePath().isEmpty()) {
                    loadImageFromAssets(holder.galleryImage, item.getImagePath());
                }
                
                // Tıklama işleyicisi ayarla
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Resim detaylarını göster
                        Toast.makeText(HistoryActivity.this, 
                                item.getTitle() + ": " + item.getDescription(),
                                Toast.LENGTH_LONG).show();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return galleryItems != null ? galleryItems.size() : 0;
        }

        class GalleryViewHolder extends RecyclerView.ViewHolder {
            ImageView galleryImage;
            TextView galleryCaption;

            public GalleryViewHolder(@NonNull View itemView) {
                super(itemView);
                try {
                    galleryImage = itemView.findViewById(R.id.galleryImage);
                    galleryCaption = itemView.findViewById(R.id.galleryCaption);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    private void openFigureDetail(HistoricalFigure figure) {
        try {
            Intent intent = new Intent(this, FigureDetailActivity.class);
            intent.putExtra("figure_id", figure.getId());
            intent.putExtra("figure_name", figure.getName());
            intent.putExtra("figure_period", figure.getPeriod());
            intent.putExtra("figure_desc", figure.getDescription());
            intent.putExtra("figure_image", figure.getImagePath());
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Detaylara erişilemiyor", Toast.LENGTH_SHORT).show();
        }
    }
    
    private void loadImageFromAssets(ImageView imageView, String imagePath) {
        try {
            // Assets klasöründen dosyayı yükle
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open(imagePath);
            
            // Bitmap oluştur
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            
            // ImageView'a yerleştir
            imageView.setImageBitmap(bitmap);
            
            // Kaynağı kapat
            inputStream.close();
        } catch (IOException e) {
            // Dosya bulunamadı veya açılamadı
            e.printStackTrace();
            
            // Placeholder resmi kullan
            imageView.setImageResource(R.drawable.placeholder_person);
        }
    }
    
    // Boş RecyclerView adaptörü
    private class EmptyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = new View(parent.getContext());
            return new RecyclerView.ViewHolder(view) {};
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            // Boş bırak
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
} 