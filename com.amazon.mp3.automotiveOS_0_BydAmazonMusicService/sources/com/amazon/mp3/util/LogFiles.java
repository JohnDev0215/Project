package com.amazon.mp3.util;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.det.DetUploader;
import com.amazon.mp3.det.report.CustomerServiceReport;
import com.amazon.music.logger.AmazonMusicMarketLoggerConfig;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes4.dex */
public class LogFiles {
    private static final String TAG = "LogFiles";

    public static AmazonMusicMarketLoggerConfig createAppLogConfig(Context context) {
        final String absolutePath = getAppLogsFile(context).getAbsolutePath();
        final String absolutePath2 = getAppLogsSwapFile(context).getAbsolutePath();
        return new AmazonMusicMarketLoggerConfig() { // from class: com.amazon.mp3.util.LogFiles.1
            @Override // com.amazon.music.logger.AmazonMusicMarketLoggerConfig
            public String getPersistentFilePath() {
                return absolutePath;
            }

            @Override // com.amazon.music.logger.AmazonMusicMarketLoggerConfig
            public String getSwapFilePath() {
                return absolutePath2;
            }
        };
    }

    public static void uploadDeviceLogsIfNeeded(final Context context, final SharedPreferences prefs) {
        int integer = AmazonApplication.getConfiguration(context).getInteger("device_log_upload_interval_days", -1);
        if (integer < 0) {
            Log.debug(TAG, "No logs will be uploaded as intervalInDays is negative. Feature is off.");
            return;
        }
        Date date = new Date(System.currentTimeMillis());
        long j = prefs.getLong("last_device_log_upload_date_key", 0L);
        if (j == 0) {
            Log.debug(TAG, "Saving first device logs upload date: " + date);
            prefs.edit().putLong("last_device_log_upload_date_key", date.getTime()).apply();
            return;
        }
        long days = TimeUnit.MILLISECONDS.toDays(date.getTime() - new Date(j).getTime());
        if (days >= integer) {
            if (ConnectivityUtil.isWifiConnected(context)) {
                DetUploader.submitReport(context, new CustomerServiceReport(context));
                prefs.edit().putLong("last_device_log_upload_date_key", date.getTime()).apply();
                Log.debug(TAG, "Uploaded logs and updated current date: " + date);
                return;
            }
            Log.debug(TAG, "Skipping to upload logs as we're not connected to WiFi.");
            return;
        }
        Log.debug(TAG, "No need to upload logs as only " + days + " have passed.");
    }

    public static void logDebugInfo(Context context) {
        ConnectivityUtil.logConnectivityDebugInfo();
        AccountCredentialUtil.get(context).logDebugInfo();
        AccountDetailUtil.get(context).logDebugInfo();
        StorageInfo.logStorageDebugInfo(context);
    }

    public static ArrayList<File> createLogs(Context context, boolean includeLogcat) {
        File fileCreateLogcatFile;
        ArrayList<File> arrayList = new ArrayList<>(3);
        File appLogsSwapFile = getAppLogsSwapFile(context);
        if (appLogsSwapFile.exists()) {
            arrayList.add(appLogsSwapFile);
        }
        File appLogsFile = getAppLogsFile(context);
        if (appLogsFile.exists()) {
            arrayList.add(appLogsFile);
        }
        if (includeLogcat && (fileCreateLogcatFile = createLogcatFile(context)) != null) {
            arrayList.add(fileCreateLogcatFile);
        }
        return arrayList;
    }

    public static File getLogcatFile(Context context) {
        return new File(getLogDirectory(context), "logcat.txt");
    }

    private static File getAppLogsFile(Context context) {
        return new File(getLogDirectory(context), "log.txt");
    }

    private static File getAppLogsSwapFile(Context context) {
        return new File(getLogDirectory(context), "log.1.txt");
    }

    private static String getLogDirectory(Context context) {
        File file = new File(context.getFilesDir().getAbsolutePath(), "logs");
        if (!file.mkdirs() && !file.isDirectory()) {
            Log.error(TAG, "Failed to create log directory");
        }
        return file.getAbsolutePath();
    }

    private static File createLogcatFile(Context context) {
        try {
            File logcatFile = getLogcatFile(context);
            logcatFile.delete();
            Runtime.getRuntime().exec(new String[]{"/system/bin/logcat", "-d", "-f", logcatFile.getAbsolutePath(), "-v", "threadtime"});
            return logcatFile;
        } catch (IOException unused) {
            Log.warning(TAG, "Logcat command execution failed");
            return null;
        }
    }
}
