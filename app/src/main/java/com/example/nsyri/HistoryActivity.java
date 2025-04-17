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
        
        // Gerçek uygulamada bu veriler veritabanından veya API'den gelecek
        historicalFigures.add(new HistoricalFigure(
            1,
            "Muhammed bin Nusayr",
            "9. Yüzyıl",
            "figures/ibn_nusayr.png",
            "Muhammed bin Nusayr, 9. yüzyılda yaşamış ve Nusayriliğin kurucusu olarak kabul edilen şahsiyettir. " +
            "Aslen Basra'lı olduğu ve Irak bölgesinde yaşadığı bilinmektedir. " +
            "Kendisini 10. İmam Ali el-Hadi ve 11. İmam Hasan el-Askeri'nin öğrencisi ve kapısı (Bab) olarak " +
            "tanıtmış ve onların adına konuştuğunu iddia etmiştir.\n\n" +
            "Bazı kaynaklara göre, Muhammed bin Nusayr kendisinin 'Bab' (kapı) olduğunu, İmamın bilgisinin " +
            "kendisi aracılığıyla yayıldığını ileri sürmüştür. İmam Hasan el-Askeri'nin vefatından sonra, " +
            "kendisini 12. İmam Muhammed Mehdi'nin özel temsilcisi ilan etmiştir.\n\n" +
            "Nusayriler onu, mezheplerinin kurucusu olarak kabul ederler. Onun öğretileri, " +
            "Ali bin Ebu Talib'in ilahiliğine inanç, ruhların tenasühü (reenkarnasyon), gizli ve zahiri " +
            "bilgi gibi kavramları içerir. Kendisinden sonra öğretilerini Hüseyin bin Hamdan el-Hasîbî " +
            "devam ettirmiştir."
        ));
        
        historicalFigures.add(new HistoricalFigure(
            2,
            "Hüseyin bin Hamdan el-Hasîbî",
            "10. Yüzyıl",
            "figures/el_hasibi.png",
            "Hüseyin bin Hamdan el-Hasîbî, 10. yüzyılda yaşamış ve Muhammed bin Nusayr'dan sonra " +
            "Nusayriliğin geliştirip yayılmasında en önemli rolü oynayan kişidir. Aslen Irak'lı olduğu " +
            "ve Bağdat'ta yaşadığı bilinmektedir.\n\n" +
            "El-Hasîbî, Nusayriliğin temel inanç sistemini ve ritüellerini sistematize etmiş, " +
            "çeşitli eserler yazmıştır. En önemli kitabı olan 'Kitabü'l-Hidaye el-Kübra' (Büyük Hidayet Kitabı), " +
            "Nusayrilerin temel kaynaklarından biridir.\n\n" +
            "Abbasi halifesi Nasır'ın hükümdarlığı döneminde (1180-1225) Suriye'ye, özellikle Halep'e " +
            "yerleşen el-Hasîbî, burada kendi öğretisini yaymaya başlamış ve böylece Nusayrilik " +
            "Suriye'de yayılma imkanı bulmuştur. El-Hasîbî'nin Halep'te 358/969 yılında vefat ettiği " +
            "rivayet edilmektedir."
        ));
        
        historicalFigures.add(new HistoricalFigure(
            3,
            "Ebu Said el-Meymun",
            "11. Yüzyıl",
            "figures/abu_said.png",
            "Ebu Said el-Meymun, 11. yüzyılda yaşamış ve Nusayriliğin yayılmasında önemli rol oynamış " +
            "olan bir din adamıdır. El-Hasîbî'nin öğrencilerinden olan Ebu Said, özellikle " +
            "Nusayriliğin Lazkiye ve çevresinde yayılmasına katkıda bulunmuştur.\n\n" +
            "Kaynaklar, onun özellikle gizli öğretilerin aktarımında ve ritüellerin korunmasında " +
            "büyük rol oynadığını belirtmektedir. Mezhebin temel metinlerinden bazılarının " +
            "yorumlanması ve geliştirilmesine katkı sağlamıştır.\n\n" +
            "Ebu Said'in öğretileri, daha sonraki Nusayri şeyhlerinin oluşturduğu literatüre temel teşkil etmiştir. " +
            "Kendisinden sonra, mezhebin öğretileri yetiştirdiği talebeler aracılığıyla devam ettirilmiştir."
        ));
        
        historicalFigures.add(new HistoricalFigure(
            4,
            "Hamza bin Ali",
            "11. Yüzyıl",
            "figures/hamza_bin_ali.png",
            "Hamza bin Ali, 11. yüzyılda yaşamış ve Nusayrilik ile ilişkili olduğu düşünülen önemli " +
            "bir din adamıdır. Bazı kaynaklar, onun Dürziliğin kurucu figürlerinden biri olduğunu " +
            "belirtse de, Nusayri gelenekleri içinde de önemli bir yere sahiptir.\n\n" +
            "Hamza bin Ali'nin öğretileri, Ali bin Ebu Talib'in kutsallığı, evrenin döngüsel doğası " +
            "ve gizli bilgilerin korunması gibi konular üzerine yoğunlaşmıştır. Bu öğretiler, " +
            "hem Nusayrilik hem de diğer batıni mezhepler tarafından benimsenmiştir.\n\n" +
            "Tarihsel kayıtlarda, onun talebeleri aracılığıyla öğretilerinin Suriye ve Lübnan bölgesinde " +
            "yayıldığı belirtilmektedir. Özellikle kozmoloji ve teoloji alanındaki görüşleri, " +
            "Nusayri düşünce dünyasını etkilemiştir."
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
        
        // Gerçek uygulamada bu veriler veritabanından veya API'den gelecek
        galleryItems.add(new GalleryItem(
            1,
            "Samandağ Bölgesi",
            "gallery/samandagi.jpg",
            "Türkiye'de Nusayrilerin en yoğun yaşadığı bölge olan Hatay Samandağ'dan bir görüntü."
        ));
        
        galleryItems.add(new GalleryItem(
            2,
            "Antakya Şehri",
            "gallery/antakya.jpg",
            "Nusayrilerin tarihsel olarak yaşadığı Antakya şehrinden tarihi bir görüntü."
        ));
        
        galleryItems.add(new GalleryItem(
            3,
            "Dini Ritüel",
            "gallery/ritual.jpg",
            "Nusayri dini ritüelini temsil eden bir görüntü."
        ));
        
        galleryItems.add(new GalleryItem(
            4,
            "El Yazmaları",
            "gallery/manuscripts.jpg",
            "Nusayri topluluğuna ait tarihi el yazması metinlerden bir örnek."
        ));
        
        galleryItems.add(new GalleryItem(
            5,
            "Geleneksel Kıyafetler",
            "gallery/traditional_dress.jpg",
            "Nusayrilerin geleneksel kıyafetlerinden örnekler."
        ));
        
        galleryItems.add(new GalleryItem(
            6,
            "Tarihi Cami",
            "gallery/historical_mosque.jpg",
            "Nusayri topluluğu için önemli olan tarihi bir cami."
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
            try {
                View view = getLayoutInflater().inflate(R.layout.item_historical_figure, parent, false);
                return new HistoricalFigureViewHolder(view);
            } catch (Exception e) {
                e.printStackTrace();
                // Hata durumunda boş bir view oluştur
                View emptyView = new View(parent.getContext());
                return new HistoricalFigureViewHolder(emptyView);
            }
        }

        @Override
        public void onBindViewHolder(@NonNull HistoricalFigureViewHolder holder, int position) {
            try {
                final HistoricalFigure figure = figures.get(position);
                
                // İsim ayarla
                if (holder.figureName != null) {
                    holder.figureName.setText(figure.getName());
                }
                
                // Resim yükle
                if (holder.figurePic != null && figure.getImagePath() != null && !figure.getImagePath().isEmpty()) {
                    loadImageFromAssets(holder.figurePic, figure.getImagePath());
                }
                
                // Tıklama işleyicisi ayarla
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openFigureDetail(figure);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return figures != null ? figures.size() : 0;
        }

        class HistoricalFigureViewHolder extends RecyclerView.ViewHolder {
            ImageView figurePic;
            TextView figureName;

            public HistoricalFigureViewHolder(@NonNull View itemView) {
                super(itemView);
                try {
                    figurePic = itemView.findViewById(R.id.figurePic);
                    figureName = itemView.findViewById(R.id.figureName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
            AssetManager assetManager = getAssets();
            InputStream is = assetManager.open(imagePath);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            imageView.setImageBitmap(bitmap);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Hata durumunda varsayılan resim göster
            imageView.setImageResource(android.R.drawable.ic_menu_report_image);
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