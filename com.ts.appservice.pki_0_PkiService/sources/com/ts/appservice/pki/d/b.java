package com.ts.appservice.pki.d;

import android.util.Log;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f26a = true;

    public static void a(String str) {
        Log.d("PKIService", str);
    }

    public static void b(String str, String str2) {
        if (!f26a) {
            Log.d(str, str2);
            return;
        }
        Log.d("PKIService", str + StringUtils.SPACE + str2);
    }

    public static void c(String str, Exception exc) {
        d(str, e(exc));
    }

    public static void d(String str, String str2) {
        if (!f26a) {
            Log.e(str, str2);
            return;
        }
        Log.e("PKIService", str + StringUtils.SPACE + str2);
    }

    public static String e(Exception exc) {
        if (exc == null) {
            return null;
        }
        return Log.getStackTraceString(exc);
    }

    public static void f(String str, String str2) {
        if (!f26a) {
            Log.w(str, str2);
            return;
        }
        Log.w("PKIService", str + StringUtils.SPACE + str2);
    }
}
