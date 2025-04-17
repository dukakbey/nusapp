package com.example.nsyri.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Utility class to check if the app is running the latest version.
 */
public class VersionCheckUtility {
    private static final String TAG = "VersionCheckUtility";
    private static final String VERSION_CHECK_URL = "https://example.com/api/version-check";
    
    public interface VersionCheckListener {
        void onVersionCheckCompleted(boolean isLatestVersion, String latestVersion);
        void onVersionCheckFailed(Exception e);
    }
    
    /**
     * Check if the app is running the latest version.
     * 
     * @param context  The context
     * @param listener The listener to notify when the check is complete
     */
    public static void checkVersion(Context context, VersionCheckListener listener) {
        String currentVersion = getCurrentVersion(context);
        if (currentVersion == null) {
            listener.onVersionCheckFailed(new Exception("Failed to get current version"));
            return;
        }
        
        new VersionCheckTask(currentVersion, listener).execute();
    }
    
    /**
     * Get the current version of the app.
     * 
     * @param context The context
     * @return The current version, or null if it could not be determined
     */
    private static String getCurrentVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Failed to get current version", e);
            return null;
        }
    }
    
    /**
     * AsyncTask to check the latest version from the server.
     */
    private static class VersionCheckTask extends AsyncTask<Void, Void, VersionCheckResult> {
        private final String currentVersion;
        private final VersionCheckListener listener;
        
        public VersionCheckTask(String currentVersion, VersionCheckListener listener) {
            this.currentVersion = currentVersion;
            this.listener = listener;
        }
        
        @Override
        protected VersionCheckResult doInBackground(Void... voids) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            
            try {
                URL url = new URL(VERSION_CHECK_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                
                int responseCode = connection.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    throw new IOException("HTTP error code: " + responseCode);
                }
                
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                
                JSONObject json = new JSONObject(response.toString());
                String latestVersion = json.getString("latestVersion");
                
                return new VersionCheckResult(compareVersions(currentVersion, latestVersion) >= 0, latestVersion);
            } catch (IOException | JSONException e) {
                return new VersionCheckResult(e);
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e(TAG, "Error closing reader", e);
                    }
                }
            }
        }
        
        @Override
        protected void onPostExecute(VersionCheckResult result) {
            if (result.exception != null) {
                listener.onVersionCheckFailed(result.exception);
            } else {
                listener.onVersionCheckCompleted(result.isLatestVersion, result.latestVersion);
            }
        }
        
        /**
         * Compare two version strings.
         * 
         * @param version1 The first version
         * @param version2 The second version
         * @return A negative integer, zero, or a positive integer as the first version is less than, equal to, or greater than the second
         */
        private int compareVersions(String version1, String version2) {
            String[] parts1 = version1.split("\\.");
            String[] parts2 = version2.split("\\.");
            
            int length = Math.max(parts1.length, parts2.length);
            
            for (int i = 0; i < length; i++) {
                int part1 = i < parts1.length ? Integer.parseInt(parts1[i]) : 0;
                int part2 = i < parts2.length ? Integer.parseInt(parts2[i]) : 0;
                
                if (part1 < part2) {
                    return -1;
                }
                
                if (part1 > part2) {
                    return 1;
                }
            }
            
            return 0;
        }
    }
    
    /**
     * Result of a version check.
     */
    private static class VersionCheckResult {
        public final boolean isLatestVersion;
        public final String latestVersion;
        public final Exception exception;
        
        public VersionCheckResult(boolean isLatestVersion, String latestVersion) {
            this.isLatestVersion = isLatestVersion;
            this.latestVersion = latestVersion;
            this.exception = null;
        }
        
        public VersionCheckResult(Exception exception) {
            this.isLatestVersion = false;
            this.latestVersion = null;
            this.exception = exception;
        }
    }
} 