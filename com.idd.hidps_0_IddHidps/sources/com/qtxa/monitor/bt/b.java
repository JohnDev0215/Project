package com.qtxa.monitor.bt;

import com.qtxa.report.DefaultDetails;
import com.qtxa.report.JniReportMessage;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: com.qtxa.monitor.bt.b$b, reason: collision with other inner class name */
    private static class C0014b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static b f80a = new b();
    }

    public static b b() {
        return C0014b.f80a;
    }

    public void a(com.qtxa.monitor.bt.a aVar) {
        com.qtxa.monitor.c.a.a("LocalBtManager", "NotifyBtStateChange: " + aVar);
        DefaultDetails defaultDetails = new DefaultDetails();
        defaultDetails.details = aVar.toString();
        JniReportMessage.java_reportMessage(1, 1403, "Event BT state: " + aVar.e, 3, defaultDetails);
    }

    private b() {
    }
}
