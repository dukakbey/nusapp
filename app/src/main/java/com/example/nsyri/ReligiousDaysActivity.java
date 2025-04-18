package com.example.nsyri;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nsyri.adapter.ReligiousDaysAdapter;
import com.example.nsyri.model.ReligiousDay;
import com.example.nsyri.util.DrawableHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Activity for displaying religious days.
 */
public class ReligiousDaysActivity extends AppCompatActivity {

    private static final String TAG = "ReligiousDaysActivity";
    public static final String EXTRA_RELIGIOUS_DAY_NAME = "religiousDayName";
    public static final String EXTRA_RELIGIOUS_DAY_DATE = "religiousDayDate";
    public static final String EXTRA_RELIGIOUS_DAY_DESC = "religiousDayDesc";
    public static final String EXTRA_RELIGIOUS_DAY_IMAGE = "religiousDayImage";
    
    private RecyclerView recyclerView;
    private List<ReligiousDay> religiousDaysList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try {
            setContentView(R.layout.activity_religious_days);

            // Set up toolbar
            Toolbar toolbar = findViewById(R.id.toolbar);
            if (toolbar != null) {
                setSupportActionBar(toolbar);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(R.string.religious_days_title);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setDisplayShowHomeEnabled(true);
                }
            } else {
                Log.w(TAG, "Toolbar is null");
            }
            
            // Initialize RecyclerView
            recyclerView = findViewById(R.id.religiousDaysRecyclerView);
            if (recyclerView != null) {
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                
                // Load religious days
                loadReligiousDays();
                
                // Set up adapter
                if (religiousDaysList != null && !religiousDaysList.isEmpty()) {
                    ReligiousDaysAdapter adapter = new ReligiousDaysAdapter(this, religiousDaysList, 
                        religiousDay -> {
                            // Detay sayfasına gitme işlemi
                            Intent intent = new Intent(ReligiousDaysActivity.this, FigureDetailActivity.class);
                            intent.putExtra(EXTRA_RELIGIOUS_DAY_NAME, religiousDay.getName());
                            intent.putExtra(EXTRA_RELIGIOUS_DAY_DATE, religiousDay.getDate());
                            intent.putExtra(EXTRA_RELIGIOUS_DAY_DESC, religiousDay.getDescription());
                            intent.putExtra(EXTRA_RELIGIOUS_DAY_IMAGE, religiousDay.getImageResourceId());
                            startActivity(intent);
                        });
                    
                    recyclerView.setAdapter(adapter);
                } else {
                    Log.w(TAG, "religiousDaysList is null or empty");
                    Toast.makeText(this, "Dini günler listesi yüklenemedi", Toast.LENGTH_SHORT).show();
                }
            } else {
                Log.e(TAG, "religiousDaysRecyclerView not found in layout");
                Toast.makeText(this, "Arayüz yüklenemedi", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate: " + e.getMessage(), e);
            Toast.makeText(this, "Dini günler sayfası yüklenirken bir hata oluştu", Toast.LENGTH_LONG).show();
        }
    }

    private void loadReligiousDays() {
        try {
            religiousDaysList = new ArrayList<>();
            
            // 2025 yılı için dini günler tarihleri
            
            // Gadir-i Hum Bayramı: 14-15 Haziran 2025
            religiousDaysList.add(new ReligiousDay("Gadir-i Hum Bayramı", "14-15.06.2025", 
                    "Hz. Muhammed'in (s.a.v.) Veda Haccı dönüşünde, Gadir-i Hum denilen mevkide Hz. Ali'yi (a.s.) kendisinden sonra ümmetin rehberi olarak tayin ettiği gündür. 'Ben kimin mevlası isem, Ali de onun mevlasıdır' hadisi burada söylenmiştir.", 
                    true, calculateDaysRemaining("14.06.2025"), R.drawable.ic_launcher_background));
            
            // Aşure Günü: yaklaşık 14 Temmuz 2025
            religiousDaysList.add(new ReligiousDay("Aşure Günü", "14.07.2025", 
                    "Hz. Hüseyin'in (a.s.) Kerbela'da şehit edildiği gündür. Nusayriler ve tüm Aleviler için matem günüdür. Bu günde özel aşure yemeği yapılır ve dua merasimleri düzenlenir.", 
                    false, calculateDaysRemaining("14.07.2025"), R.drawable.ic_launcher_background));
            
            // Miraç Kandili: 25 Şubat 2025
            religiousDaysList.add(new ReligiousDay("Miraç Kandili", "25.02.2025", 
                    "Hz. Muhammed'in (s.a.v.) bir gece içinde Mescid-i Haram'dan Mescid-i Aksa'ya ve oradan da göklere yükseldiği gecedir. Nusayriler, bu gecede Hz. Muhammed'in Hz. Ali'nin gerçek mahiyetini gördüğüne inanırlar.", 
                    true, calculateDaysRemaining("25.02.2025"), R.drawable.ic_launcher_background));
            
            // Mevlid Kandili: 4 Ekim 2025
            religiousDaysList.add(new ReligiousDay("Mevlid Kandili", "04.10.2025", 
                    "Hz. Muhammed'in (s.a.v.) doğum günüdür. Bu gece, Nusayriler arasında özel dualarla ve toplu merasimlerle kutlanır. Ayrıca bu gün özel yemekler hazırlanarak fakirlere dağıtılır.", 
                    true, calculateDaysRemaining("04.10.2025"), R.drawable.ic_launcher_background));
            
            // Berat Kandili: 27 Mart 2025
            religiousDaysList.add(new ReligiousDay("Berat Kandili", "27.03.2025", 
                    "Günahlardan arınma, af ve mağfiret gecesidir. Bu gecede yapılan duaların kabul olunacağına inanılır. Nusayri topluluklarında kandil simidi yapılır ve Allah'ın rahmetinin bolca ihsan edildiği bu gecede tövbe edilir, dua edilir.", 
                    true, calculateDaysRemaining("27.03.2025"), R.drawable.ic_launcher_background));
            
            // Hz. Ali'nin Doğum Günü: 21 Mart 2025
            religiousDaysList.add(new ReligiousDay("Hz. Ali'nin Doğum Günü", "21.03.2025", 
                    "Hz. Ali'nin (a.s.) doğum günü olarak kutlanır. Nusayriler için çok özel bir gündür ve törenlerle anılır. Bu gün aynı zamanda Nevruz ile çakışması sebebiyle tabiatın yeniden doğuşunu da sembolize eder.", 
                    true, calculateDaysRemaining("21.03.2025"), R.drawable.ic_launcher_background));
            
            // Hıdırellez: 6 Mayıs 2025
            religiousDaysList.add(new ReligiousDay("Hıdırellez", "06.05.2025", 
                    "Hz. Hızır ve Hz. İlyas'ın buluşma günü olarak kutlanır. Doğanın canlanması, bereket ve refahın simgesidir. Bu tarihte piknikler yapılır, dilek dilenir ve Nusayriler arasında önemli bir kutlama günüdür.", 
                    true, calculateDaysRemaining("06.05.2025"), R.drawable.ic_launcher_background));
            
            Log.d(TAG, "Loaded " + religiousDaysList.size() + " religious days for 2025");
        } catch (Exception e) {
            Log.e(TAG, "Error loading religious days: " + e.getMessage(), e);
            religiousDaysList = new ArrayList<>(); // ensure not null
        }
    }
    
    // Helper method to get date for current year
    private Calendar getDateForCurrentYear(int day, int month) {
        Calendar cal = Calendar.getInstance();
        int currentYear = cal.get(Calendar.YEAR);
        
        cal.set(Calendar.YEAR, currentYear);
        cal.set(Calendar.MONTH, month - 1); // Calendar months are 0-based
        cal.set(Calendar.DAY_OF_MONTH, day);
        
        // If the date has already passed this year, use next year's date
        Calendar today = Calendar.getInstance();
        if (cal.before(today)) {
            cal.set(Calendar.YEAR, currentYear + 1);
        }
        
        return cal;
    }

    private int calculateDaysRemaining(String dateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            Date eventDate = sdf.parse(dateString);
            Date currentDate = new Date();
            
            if (eventDate == null) {
                Log.w(TAG, "Could not parse date: " + dateString);
                return 0;
            }
            
            Calendar eventCal = Calendar.getInstance();
            eventCal.setTime(eventDate);
            
            Calendar currentCal = Calendar.getInstance();
            currentCal.setTime(currentDate);
            
            // If the event is already past for this year, calculate for next year
            if (eventCal.before(currentCal)) {
                eventCal.add(Calendar.YEAR, 1);
            }
            
            long diffInMillis = eventCal.getTimeInMillis() - currentCal.getTimeInMillis();
            return (int) TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            Log.e(TAG, "Error calculating days remaining: " + e.getMessage(), e);
            return 0;
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