package com.qtxa.qtxadefend;

import android.content.Context;
import android.os.Process;
import android.util.Log;
import com.qtxa.report.AppSecurityDetails;
import com.qtxa.report.CrashDetails;
import com.qtxa.report.DefaultDetails;
import com.qtxa.report.JniReportMessage;
import com.qtxa.report.RootCheckDetails;

/* JADX INFO: loaded from: classes.dex */
public final class w {
    public static void a(String str) {
        if (b.f110a) {
            Log.e("SecurityService", str);
        }
    }

    public static void b(String str) {
        if (b.f111b) {
            Log.e("SecurityService", "" + Process.myTid() + " : " + str);
        }
    }

    public static void c(Context context) {
    }

    public static void d(String str) {
        if (b.f110a) {
            Log.e("SecurityService", "" + Process.myTid() + " : " + str);
        }
    }

    public static void e(String str, String str2) {
        if (b.f110a) {
            Log.e("SecurityService_" + str, "" + Process.myTid() + " : " + str2);
        }
    }

    public static void f(String str) {
        if (b.f110a) {
            Log.e("SecurityService", str);
        }
    }

    public static void g() {
    }

    public static void h(int i, int i2, String str, String str2, String str3) {
        if (b.f110a) {
            Log.e("SecurityService", "mode(" + (i2 == 1 ? "block" : "pass") + ")" + str);
        }
        AppSecurityDetails appSecurityDetails = new AppSecurityDetails();
        appSecurityDetails.pkgname = str2;
        appSecurityDetails.appname = str3;
        appSecurityDetails.det = str;
        JniReportMessage.java_reportMessage(1, i, str, i2, appSecurityDetails);
    }

    public static void i(int i, int i2, String str, String str2) {
        if (b.f110a) {
            Log.e("SecurityService_upload", "日志上报：" + str2);
        }
        DefaultDetails defaultDetails = new DefaultDetails();
        defaultDetails.details = str2;
        JniReportMessage.java_reportMessage(1, i, str, i2, defaultDetails);
    }

    public static void j(int i, int i2, String str, String str2) {
        if (b.f110a) {
            Log.e("SecurityService_upload", str2);
        }
        RootCheckDetails rootCheckDetails = new RootCheckDetails();
        rootCheckDetails.det = str2;
        JniReportMessage.java_reportMessage(1, i, str, i2, rootCheckDetails);
    }

    public static void k(int i, int i2, String str, CrashDetails crashDetails) {
        if (b.f110a) {
            Log.e("SecurityService_upload", str + crashDetails.toString());
        }
        JniReportMessage.java_reportMessage(1, i, str, i2, crashDetails);
    }
}
