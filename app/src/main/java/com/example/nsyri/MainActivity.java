package com.example.nsyri;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import android.widget.TextView;

import com.example.nsyri.util.IconProvider;
// import com.example.nsyri.util.VersionCheckUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    
    private CardView historyCard;
    private CardView religiousDaysCard;
    private CardView translationCard;
    private TextView nextEventTitle;
    private TextView nextEventName;
    private TextView nextEventDate;
    private TextView daysRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_main);
            
            // Initialize UI components
            initializeViews();
            
            // Set up card icons
            setupCardIcons();
            
            // Set up card click listeners
            setupClickListeners();
            
            // Update next event information
            updateNextEventInfo();
            
            // Temporarily disable version check to avoid crashes
            // checkAppVersion();
            
        } catch (Exception e) {
            Log.e(TAG, "Error in onCreate: " + e.getMessage(), e);
            Toast.makeText(this, "Uygulama başlatılırken bir hata oluştu", Toast.LENGTH_LONG).show();
        }
    }
    
    private void initializeViews() {
        try {
            historyCard = findViewById(R.id.cardHistory);
            religiousDaysCard = findViewById(R.id.cardReligiousDays);
            translationCard = findViewById(R.id.cardTranslation);
            nextEventTitle = findViewById(R.id.nextEventTitle);
            nextEventName = findViewById(R.id.nextEventName);
            nextEventDate = findViewById(R.id.nextEventDate);
            daysRemaining = findViewById(R.id.daysRemaining);
        } catch (Exception e) {
            Log.e(TAG, "Error initializing views: " + e.getMessage(), e);
        }
    }
    
    private void setupCardIcons() {
        try {
            // Tarih ve Kültür ikonu
            ImageView historyIcon = historyCard.findViewById(R.id.cardIcon);
            if (historyIcon != null) {
                historyIcon.setImageDrawable(IconProvider.createMenuIcon(this, IconProvider.MenuCategory.HISTORY));
            }
            
            // Dini Günler ikonu
            ImageView religiousDaysIcon = religiousDaysCard.findViewById(R.id.cardIcon);
            if (religiousDaysIcon != null) {
                religiousDaysIcon.setImageDrawable(IconProvider.createMenuIcon(this, IconProvider.MenuCategory.RELIGIOUS_DAYS));
            }
            
            // Çeviri ve Konuşma Rehberi ikonu
            ImageView translationIcon = translationCard.findViewById(R.id.cardIcon);
            if (translationIcon != null) {
                translationIcon.setImageDrawable(IconProvider.createMenuIcon(this, IconProvider.MenuCategory.TRANSLATION));
            }
        } catch (Exception e) {
            Log.e(TAG, "Error setting up card icons: " + e.getMessage(), e);
        }
    }
    
    private void setupClickListeners() {
        try {
            if (historyCard != null) {
                historyCard.setOnClickListener(view -> {
                    try {
                        startActivity(new Intent(MainActivity.this, HistoryActivity.class));
                    } catch (Exception e) {
                        Log.e(TAG, "Error starting HistoryActivity: " + e.getMessage());
                        Toast.makeText(MainActivity.this, "Tarih sayfası açılamadı", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            
            if (religiousDaysCard != null) {
                religiousDaysCard.setOnClickListener(view -> {
                    try {
                        startActivity(new Intent(MainActivity.this, ReligiousDaysActivity.class));
                    } catch (Exception e) {
                        Log.e(TAG, "Error starting ReligiousDaysActivity: " + e.getMessage());
                        Toast.makeText(MainActivity.this, "Dini günler sayfası açılamadı", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            
            if (translationCard != null) {
                translationCard.setOnClickListener(view -> {
                    try {
                        startActivity(new Intent(MainActivity.this, TranslationActivity.class));
                    } catch (Exception e) {
                        Log.e(TAG, "Error starting TranslationActivity: " + e.getMessage());
                        Toast.makeText(MainActivity.this, "Çeviri sayfası açılamadı", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (Exception e) {
            Log.e(TAG, "Error setting up click listeners: " + e.getMessage(), e);
        }
    }
    
    private void updateNextEventInfo() {
        try {
            // 2025 yılı için dini günler
            String[][] religiousDays = {
                {"Gadir-i Hum Bayramı", "14.06.2025"},
                {"Aşure Günü", "14.07.2025"},
                {"Miraç Kandili", "25.02.2025"},
                {"Mevlid Kandili", "04.10.2025"},
                {"Berat Kandili", "27.03.2025"}
            };
            
            String nextEventName = "";
            String nextEventDate = "";
            int minDaysRemaining = Integer.MAX_VALUE;
            
            // Şu anki tarihi al
            Calendar currentCal = Calendar.getInstance();
            Date currentDate = currentCal.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            
            // En yakın dini günü bul
            for (String[] eventInfo : religiousDays) {
                try {
                    String name = eventInfo[0];
                    String dateStr = eventInfo[1];
                    
                    Date eventDate = sdf.parse(dateStr);
                    if (eventDate == null) continue;
                    
                    Calendar eventCal = Calendar.getInstance();
                    eventCal.setTime(eventDate);
                    
                    // Eğer tarih geçmişse, gelecek yıla ayarla
                    if (eventCal.before(currentCal)) {
                        eventCal.add(Calendar.YEAR, 1);
                    }
                    
                    // Kalan gün sayısını hesapla
                    long diffInMillis = eventCal.getTimeInMillis() - currentCal.getTimeInMillis();
                    int daysLeft = (int) TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
                    
                    // Eğer daha yakınsa, bu etkinliği sakla
                    if (daysLeft < minDaysRemaining) {
                        minDaysRemaining = daysLeft;
                        nextEventName = name;
                        nextEventDate = dateStr;
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Error processing event: " + eventInfo[0], e);
                }
            }
            
            // Bilgileri UI'a ayarla
            if (this.nextEventName != null) {
                this.nextEventName.setText(nextEventName);
            }
            
            if (this.nextEventDate != null) {
                this.nextEventDate.setText(nextEventDate);
            }
            
            if (daysRemaining != null) {
                daysRemaining.setText(minDaysRemaining + " gün kaldı");
                // Rengi ayarla
                if (minDaysRemaining <= 7) {
                    // Yakın zaman için kırmızı
                    daysRemaining.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
                } else {
                    // Normal zaman için yeşil
                    daysRemaining.setTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error updating event info: " + e.getMessage(), e);
        }
    }
    
    /*
    private void checkAppVersion() {
        try {
            VersionCheckUtility.checkVersion(this, new VersionCheckUtility.VersionCheckListener() {
                @Override
                public void onVersionCheckCompleted(boolean isLatestVersion, String latestVersion) {
                    if (!isLatestVersion) {
                        runOnUiThread(() -> showUpdateDialog(latestVersion));
                    }
                }

                @Override
                public void onVersionCheckFailed(Exception e) {
                    // Silent fail - don't bother user if version check fails
                    Log.e(TAG, "Version check failed: " + e.getMessage(), e);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Error checking app version: " + e.getMessage(), e);
        }
    }
    
    private void showUpdateDialog(String latestVersion) {
        try {
            new AlertDialog.Builder(this)
                .setTitle(R.string.update_available)
                .setMessage(getString(R.string.update_message, latestVersion))
                .setPositiveButton(R.string.update, (dialog, which) -> {
                    // Open Google Play or download page
                    Toast.makeText(MainActivity.this, R.string.redirecting_to_update, Toast.LENGTH_SHORT).show();
                    // In a real app, redirect to Play Store or download page
                })
                .setNegativeButton(R.string.later, null)
                .show();
        } catch (Exception e) {
            Log.e(TAG, "Error showing update dialog: " + e.getMessage(), e);
        }
    }
    */
} 