package com.amazon.mp3.util.health;

import android.content.SharedPreferences;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes4.dex */
public class AppHealthUtil implements AppHealthEventMonitor {
    private static final String TAG = "AppHealthUtil";
    private final SharedPreferences mSharedPreferences;

    public AppHealthUtil(final SharedPreferences sharedPreferences) {
        this.mSharedPreferences = sharedPreferences;
    }

    @Override // com.amazon.mp3.util.health.AppHealthEventMonitor
    public void appOpened(final String currentVersion) {
        Log.debug(TAG, "App has opened, logging app health metrics.");
        checkAppVersion(currentVersion);
        incrementAppOpenedNoIncidentCount();
    }

    @Override // com.amazon.mp3.util.health.AppHealthEventMonitor
    public void appCrash() {
        if (AmazonApplication.BETA) {
            return;
        }
        resetAppOpenedCount();
    }

    @Override // com.amazon.mp3.util.health.AppHealthEventMonitor
    public void customerSupportContacted() {
        if (AmazonApplication.BETA) {
            return;
        }
        resetAppOpenedCount();
    }

    public void resetAppOpenedNoIncidentCount() {
        resetAppOpenedCount();
    }

    public int getAppOpenedNoIncidentCount() {
        return this.mSharedPreferences.getInt("com.amazon.mp3_key_app_opened_no_incident_count", 0);
    }

    private void checkAppVersion(final String currentVersion) {
        String string = this.mSharedPreferences.getString("com.amazon.mp3_key_last_known_major_minor_version", "0");
        try {
            if (Double.valueOf(currentVersion).doubleValue() > Double.valueOf(string).doubleValue()) {
                Log.debug(TAG, "new major/minor version detected: " + currentVersion + ". Updating version");
                updateVersion(currentVersion);
                if (AmazonApplication.BETA) {
                    return;
                }
                resetAppOpenedCount();
            }
        } catch (NumberFormatException unused) {
            Log.error(TAG, "Invalid version numbers, cannot be converted to double. lastKnownVersion: " + string + " currentVersion: " + currentVersion);
        }
    }

    private void resetAppOpenedCount() {
        this.mSharedPreferences.edit().putInt("com.amazon.mp3_key_app_opened_no_incident_count", 0).apply();
    }

    private void incrementAppOpenedNoIncidentCount() {
        Log.debug(TAG, "Incrementing app opened without incident count");
        this.mSharedPreferences.edit().putInt("com.amazon.mp3_key_app_opened_no_incident_count", this.mSharedPreferences.getInt("com.amazon.mp3_key_app_opened_no_incident_count", 0) + 1).apply();
    }

    private void updateVersion(final String version) {
        SharedPreferences.Editor editorEdit = this.mSharedPreferences.edit();
        editorEdit.putString("com.amazon.mp3_key_last_known_major_minor_version", version);
        editorEdit.apply();
    }
}
