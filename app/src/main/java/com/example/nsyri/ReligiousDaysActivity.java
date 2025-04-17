package com.example.nsyri;

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
                        religiousDay -> Toast.makeText(ReligiousDaysActivity.this, 
                            religiousDay.getName() + " - " + religiousDay.getDate(), 
                            Toast.LENGTH_SHORT).show());
                    
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
            
            // Add sample religious days
            religiousDaysList.add(new ReligiousDay("Gadir-i Hum", "18.07.2024", 
                    "Hz. Muhammed'in (s.a.v.) Hz. Ali'yi (a.s.) kendisinden sonra ümmetin rehberi olarak tayin ettiği gün.", true, calculateDaysRemaining("18.07.2024")));
            
            religiousDaysList.add(new ReligiousDay("Aşure Günü", "16.07.2024", 
                    "Hz. Hüseyin'in (a.s.) Kerbela'da şehit edildiği gün.", false, calculateDaysRemaining("16.07.2024")));
            
            religiousDaysList.add(new ReligiousDay("Miraç Kandili", "15.01.2024", 
                    "Hz. Muhammed'in (s.a.v.) göklere yükseldiği gece.", true, calculateDaysRemaining("15.01.2024")));
            
            religiousDaysList.add(new ReligiousDay("Mevlid Kandili", "26.09.2024", 
                    "Hz. Muhammed'in (s.a.v.) doğum günü.", true, calculateDaysRemaining("26.09.2024")));
            
            religiousDaysList.add(new ReligiousDay("Berat Kandili", "25.02.2024", 
                    "Günahlardan arınma gecesi.", true, calculateDaysRemaining("25.02.2024")));
            
            Log.d(TAG, "Loaded " + religiousDaysList.size() + " religious days");
        } catch (Exception e) {
            Log.e(TAG, "Error loading religious days: " + e.getMessage(), e);
            religiousDaysList = new ArrayList<>(); // ensure not null
        }
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