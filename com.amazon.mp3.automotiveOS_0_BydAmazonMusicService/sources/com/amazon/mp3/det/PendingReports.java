package com.amazon.mp3.det;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.det.report.DetReport;
import com.amazon.mp3.det.report.PendingDetReport;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.FileUtil;
import com.amazon.mp3.util.Log;
import java.io.File;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes3.dex */
public class PendingReports {
    private static final String TAG = "PendingReports";

    public static void submitPendingReports(Context context) {
        if (ConnectivityUtil.hasAnyInternetConnection()) {
            for (String str : context.getSharedPreferences("com.amazon.mp3_PendingReports", 0).getStringSet("pending_reports", new LinkedHashSet())) {
                Log.debug(TAG, "uploading pending report: " + str);
                DetUploader.submitReport(context, new PendingDetReport(str, getSavedReportFile(context, str)));
            }
        }
    }

    static void deleteSavedReport(Context context, DetReport report) {
        FileUtil.deleteFile(getSavedReportFile(context, report.getReportTag()));
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.amazon.mp3_PendingReports", 0);
        LinkedHashSet linkedHashSet = new LinkedHashSet(sharedPreferences.getStringSet("pending_reports", new LinkedHashSet()));
        linkedHashSet.remove(report.getReportTag());
        sharedPreferences.edit().putStringSet("pending_reports", linkedHashSet).apply();
    }

    static void saveReport(Context context, String body, DetReport report) {
        FileUtil.saveToFile(body, getSavedReportFile(context, report.getReportTag()));
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.amazon.mp3_PendingReports", 0);
        LinkedHashSet linkedHashSet = new LinkedHashSet(sharedPreferences.getStringSet("pending_reports", new LinkedHashSet()));
        linkedHashSet.add(report.getReportTag());
        sharedPreferences.edit().putStringSet("pending_reports", linkedHashSet).apply();
    }

    private static File getSavedReportFile(Context context, String reportTag) {
        return new File(context.getFilesDir().getAbsolutePath() + File.separatorChar + reportTag);
    }
}
