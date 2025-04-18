package com.example.nsyri;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.nsyri.util.DrawableHelper;

public class FigureDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figure_detail);

        // Toolbar'ı ayarla
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Intent'ten verileri al
        String name = getIntent().getStringExtra(ReligiousDaysActivity.EXTRA_RELIGIOUS_DAY_NAME);
        String date = getIntent().getStringExtra(ReligiousDaysActivity.EXTRA_RELIGIOUS_DAY_DATE);
        String description = getIntent().getStringExtra(ReligiousDaysActivity.EXTRA_RELIGIOUS_DAY_DESC);
        boolean isJoyous = true; // Varsayılan değer
        
        if (name != null && name.contains("Aşure")) {
            isJoyous = false; // Aşure günü için matem
        }

        // Başlığı ayarla
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(name);
        }

        // UI elemanlarını bul
        ImageView imageView = findViewById(R.id.iv_figure_image);
        TextView titleTextView = findViewById(R.id.tv_figure_name);
        TextView dateTextView = findViewById(R.id.tv_figure_date);
        TextView descriptionTextView = findViewById(R.id.tv_figure_description);

        // UI elemanlarını doldur
        // Programatik olarak oluşturulan drawable ile resmi ayarla
        imageView.setImageDrawable(DrawableHelper.createReligiousDayDrawable(name != null ? name : "", isJoyous));
        titleTextView.setText(name);
        dateTextView.setText(date);
        
        // Detaylı açıklama oluştur
        String detailedDescription = description;
        
        if (name != null) {
            if (name.contains("Gadir-i Hum")) {
                detailedDescription += "\n\nGadir-i Hum Bayramı, Hicrî takvimin Zilhicce ayının 18. gününde kutlanır. Bu gün, Hz. Muhammed'in (s.a.v.) Veda Haccı dönüşünde, Gadir-i Hum denilen mevkide Hz. Ali'yi (a.s.) kendisinden sonra ümmetin lideri olarak tayin ettiği gündür.\n\n" +
                        "Bu olay, İslam tarihinde çok önemli bir dönüm noktasıdır ve özellikle Şii İslam'da büyük öneme sahiptir. " +
                        "Hz. Muhammed (s.a.v.), binlerce hacının önünde Hz. Ali'nin (a.s.) elini kaldırarak şu meşhur sözü söylemiştir: 'Ben kimin mevlası isem, Ali de onun mevlasıdır.'";
            } else if (name.contains("Aşure")) {
                detailedDescription += "\n\nAşure Günü, Hicrî takvimin Muharrem ayının 10. gününde anılır. Bu gün, Hz. Muhammed'in torunu Hz. Hüseyin'in (a.s.) ve ailesinin Kerbela'da şehit edildiği gündür.\n\n" +
                        "Bu acı olay, İslam tarihinde derin iz bırakmış ve her yıl büyük bir hüzünle anılmaktadır. " +
                        "Kerbela olayı, 680 yılında gerçekleşmiş ve İslam tarihinin en trajik olaylarından biri olarak kabul edilir. " +
                        "Bu günde genellikle matem tutulur ve Hz. Hüseyin'in anısına çeşitli anma etkinlikleri düzenlenir.";
            } else if (name.contains("Miraç")) {
                detailedDescription += "\n\nMiraç Kandili, Hz. Muhammed'in (s.a.v.) bir gece içinde Mescid-i Haram'dan Mescid-i Aksa'ya ve oradan da göklere yükseldiği, Allah'ın huzuruna çıktığı gecedir.\n\n" +
                        "Bu gece, İslam inancında çok önemli bir yere sahiptir. Hicrî takvimin Recep ayının 27. gecesinde kutlanır. " +
                        "Bu gece, namazın farz kılındığı ve Hz. Muhammed'e (s.a.v.) İslam'ın temel ilkelerinin bildirildiği gecedir.";
            } else if (name.contains("Mevlid")) {
                detailedDescription += "\n\nMevlid Kandili, Hz. Muhammed'in (s.a.v.) doğum günüdür ve Hicrî takvimin Rebiülevvel ayının 12. gecesinde kutlanır.\n\n" +
                        "Bu gece, İslam dünyasında büyük bir sevinçle karşılanır ve çeşitli etkinliklerle kutlanır. " +
                        "Mevlid kelimesi, Arapça'da 'doğum zamanı' anlamına gelir. " +
                        "Bu gecede, Hz. Muhammed'in (s.a.v.) hayatı, öğretileri ve erdemleri anılır.";
            } else if (name.contains("Berat")) {
                detailedDescription += "\n\nBerat Kandili, Hicrî takvimin Şaban ayının 15. gecesinde kutlanır. Bu gece, günahlardan arınma, af ve mağfiret gecesi olarak kabul edilir.\n\n" +
                        "Berat kelimesi, Arapça'da 'beraat etmek' yani aklanmak, temize çıkmak anlamına gelir. " +
                        "İnanışa göre, bu gecede Allah, kullarının günahlarını affeder ve onlara rahmetiyle muamele eder. " +
                        "Bu gece, dua etmek, ibadet etmek ve Allah'tan af dilemek için özel bir fırsattır.";
            }
        }
        
        descriptionTextView.setText(detailedDescription);
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