package com.amazon.mp3.det;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.util.Log;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class PendingCrashLogs extends CustomerLogUploader {
    private static final String TAG = "PendingCrashLogs";

    @VisibleForTesting
    boolean debug = false;
    private Boolean featureEnabled;

    @SuppressLint({"ApplySharedPref"})
    public void scheduleLogsUpload(@NonNull final Context context, @NonNull final String crashHashCode) {
        if (isFeatureEnabled()) {
            getPendingCrashLogsPrefs(context).edit().putLong(crashHashCode, System.currentTimeMillis()).commit();
            Log.debug(TAG, "The log upload is scheduled: %s", crashHashCode);
        }
    }

    public void uploadPendingCrashLogsIfRequired(@NonNull final Context context) {
        if (isFeatureEnabled()) {
            SharedPreferences pendingCrashLogsPrefs = getPendingCrashLogsPrefs(context);
            SharedPreferences submittedCrashLogsPrefs = getSubmittedCrashLogsPrefs(context);
            Map<String, ?> all = pendingCrashLogsPrefs.getAll();
            SharedPreferences.Editor editorEdit = pendingCrashLogsPrefs.edit();
            for (Map.Entry<String, ?> entry : all.entrySet()) {
                if (!submittedCrashLogsPrefs.contains(entry.getKey())) {
                    upload(context);
                    recordLogIsSubmitted(submittedCrashLogsPrefs, entry);
                }
                editorEdit.remove(entry.getKey());
            }
            editorEdit.apply();
        }
    }

    @VisibleForTesting
    boolean isFeatureEnabled() {
        if (this.featureEnabled == null) {
            if (this.debug) {
                this.featureEnabled = Boolean.TRUE;
            } else {
                this.featureEnabled = Boolean.valueOf(AmazonApplication.getCapabilities().shouldUploadLogAfterCrash());
            }
            String str = TAG;
            Object[] objArr = new Object[1];
            objArr[0] = this.featureEnabled.booleanValue() ? "enabled" : "disabled";
            Log.debug(str, "The feature is %s", objArr);
        }
        return this.featureEnabled.booleanValue();
    }

    @VisibleForTesting
    SharedPreferences getPendingCrashLogsPrefs(@NonNull final Context context) {
        return context.getSharedPreferences("com.amazon.mp3_PendingCrashLogs", 0);
    }

    public SharedPreferences getSubmittedCrashLogsPrefs(@NonNull final Context context) {
        return context.getSharedPreferences("com.amazon.mp3_SubmittedCrashLogs", 0);
    }

    @VisibleForTesting
    void recordLogIsSubmitted(@NonNull final SharedPreferences submittedCrashLogsSharedPreferences, @NonNull final Map.Entry<String, ?> entry) {
        long jCurrentTimeMillis;
        Object value = entry.getValue();
        if (value instanceof Long) {
            jCurrentTimeMillis = ((Long) value).longValue();
        } else {
            jCurrentTimeMillis = System.currentTimeMillis();
        }
        submittedCrashLogsSharedPreferences.edit().putLong(entry.getKey(), jCurrentTimeMillis).apply();
        Log.debug(TAG, "Log is submitted: %s", entry.getKey());
    }

    public void uploadLogAfterNativeCrash(@NonNull final Context context) {
        if (canUpload(context)) {
            upload(context);
        }
    }

    @Override // com.amazon.mp3.det.CustomerLogUploader
    @VisibleForTesting
    boolean canUpload(@NonNull final Context context) {
        return super.canUpload(context) && isFeatureEnabled();
    }
}
