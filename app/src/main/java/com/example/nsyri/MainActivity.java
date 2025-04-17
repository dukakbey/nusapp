package com.example.nsyri;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import android.widget.TextView;

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
            // Example: Show information about "Gadir-i Hum"
            String eventName = "Gadir-i Hum";
            String eventDate = "18.07.2024";
            
            // Set event name
            if (nextEventName != null) {
                nextEventName.setText(eventName);
            }
            
            // Set event date
            if (nextEventDate != null) {
                nextEventDate.setText(eventDate);
            }
            
            // Calculate and set days remaining
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            Date date = sdf.parse(eventDate);
            
            if (date != null && daysRemaining != null) {
                Calendar eventCal = Calendar.getInstance();
                eventCal.setTime(date);
                
                Calendar currentCal = Calendar.getInstance();
                
                // If the event is already past for this year, calculate for next year
                if (eventCal.before(currentCal)) {
                    eventCal.add(Calendar.YEAR, 1);
                }
                
                long diffInMillis = eventCal.getTimeInMillis() - currentCal.getTimeInMillis();
                int daysLeft = (int) TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
                
                daysRemaining.setText(daysLeft + " gün kaldı");
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