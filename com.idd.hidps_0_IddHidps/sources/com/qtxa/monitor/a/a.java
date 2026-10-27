package com.qtxa.monitor.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.qtxa.report.DefaultDetails;
import com.qtxa.report.JniReportMessage;

/* JADX INFO: loaded from: classes.dex */
public class a {

    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static a f67a = new a();
    }

    public static a a() {
        return b.f67a;
    }

    public void b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("save_version", 0);
        String string = sharedPreferences.getString("pre_version", "-1");
        String str = Build.DISPLAY;
        if (string.equals(str)) {
            return;
        }
        com.qtxa.monitor.c.a.a("LocalOtaManager", "system version changed  preversion: " + string + " currentVersion: " + str);
        long jCurrentTimeMillis = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("OTA SUCCESS ");
        sb.append(str);
        com.qtxa.monitor.a.b bVar = new com.qtxa.monitor.a.b(string, str, jCurrentTimeMillis, sb.toString(), "OTA SUCCESS " + str);
        sharedPreferences.edit().putString("pre_version", str).apply();
        c(bVar);
    }

    public void c(com.qtxa.monitor.a.b bVar) {
        com.qtxa.monitor.c.a.a("LocalOtaManager", "notifyOtaStateChange: " + bVar);
        DefaultDetails defaultDetails = new DefaultDetails();
        defaultDetails.details = bVar.toString();
        JniReportMessage.java_reportMessage(1, 1308, "Event OTA state: " + bVar.d, 3, defaultDetails);
    }

    private a() {
    }
}
