package com.qtxa.monitor.wifi;

import com.qtxa.report.DefaultDetails;
import com.qtxa.report.JniReportMessage;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: com.qtxa.monitor.wifi.b$b, reason: collision with other inner class name */
    private static class C0016b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final b f93a = new b();
    }

    public static b c() {
        return C0016b.f93a;
    }

    public void a(com.qtxa.monitor.wifi.a aVar) {
        com.qtxa.monitor.c.a.a("LocalWifiManager", "NotifyApStateChange: " + aVar);
        DefaultDetails defaultDetails = new DefaultDetails();
        defaultDetails.details = aVar.toString();
        JniReportMessage.java_reportMessage(1, 1405, "Event AP " + aVar.e, 3, defaultDetails);
    }

    public void b(c cVar) {
        com.qtxa.monitor.c.a.a("LocalWifiManager", "NotifyWifiStateChange: " + cVar);
        DefaultDetails defaultDetails = new DefaultDetails();
        defaultDetails.details = cVar.toString();
        JniReportMessage.java_reportMessage(1, 1401, "Event wifi " + cVar.c, 3, defaultDetails);
    }

    private b() {
    }
}
