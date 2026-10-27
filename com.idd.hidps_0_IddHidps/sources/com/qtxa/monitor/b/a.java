package com.qtxa.monitor.b;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import com.qtxa.report.DefaultDetails;
import com.qtxa.report.JniReportMessage;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f70a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private PhoneStateListener f71b;

    /* JADX INFO: renamed from: com.qtxa.monitor.b.a$a, reason: collision with other inner class name */
    class C0013a extends PhoneStateListener {
        C0013a() {
        }

        @Override // android.telephony.PhoneStateListener
        public void onServiceStateChanged(ServiceState serviceState) {
            super.onServiceStateChanged(serviceState);
            a.this.c(serviceState);
        }
    }

    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static a f73a = new a(null);
    }

    /* synthetic */ a(C0013a c0013a) {
        this();
    }

    public static a b() {
        return b.f73a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(ServiceState serviceState) {
        int radioTechnology = serviceState.getRadioTechnology();
        if (radioTechnology != this.f70a) {
            e(new com.qtxa.monitor.b.b(f(radioTechnology), System.currentTimeMillis() + ""));
            this.f70a = radioTechnology;
        }
    }

    private String f(int i) {
        switch (i) {
            case 0:
                return "Unknown";
            case 1:
                return "GPRS";
            case 2:
                return "EDGE";
            case 3:
                return "UMTS";
            case 4:
                return "CDMA-IS95A";
            case 5:
                return "CDMA-IS95B";
            case 6:
                return "1xRTT";
            case 7:
                return "EvDo-rev.0";
            case 8:
                return "EvDo-rev.A";
            case 9:
                return "HSDPA";
            case 10:
                return "HSUPA";
            case 11:
                return "HSPA";
            case 12:
                return "EvDo-rev.B";
            case 13:
                return "eHRPD";
            case 14:
                return "LTE";
            case 15:
                return "HSPAP";
            case 16:
                return "GSM";
            case 17:
                return "TD-SCDMA";
            case 18:
                return "IWLAN";
            case 19:
                return "LTE_CA";
            default:
                return "Unexpected";
        }
    }

    public void d(Context context) {
        ((TelephonyManager) context.getSystemService("phone")).listen(this.f71b, 1);
    }

    public void e(com.qtxa.monitor.b.b bVar) {
        com.qtxa.monitor.c.a.a("LocalPhoneManager", "notifyPhoneStateChange: " + bVar);
        DefaultDetails defaultDetails = new DefaultDetails();
        defaultDetails.details = bVar.toString();
        JniReportMessage.java_reportMessage(1, 1404, "Event Phone state: " + bVar.f74a, 3, defaultDetails);
    }

    public void g(Context context) {
        ((TelephonyManager) context.getSystemService("phone")).listen(this.f71b, 0);
    }

    private a() {
        this.f70a = 0;
        this.f71b = new C0013a();
    }
}
