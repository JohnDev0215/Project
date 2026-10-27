package com.byd.engineermode.utils;

import android.util.Log;

/* JADX INFO: compiled from: LogUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f2516a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f2517b = false;

    public static void a(String str, String str2) {
        if (e()) {
            if (d()) {
                str = "OTA_UPGRADE" + str;
            }
            Log.d(str, str2);
        }
    }

    public static void b(String str, String str2) {
        if (e()) {
            if (d()) {
                str = "OTA_UPGRADE" + str;
            }
            Log.e(str, str2);
        }
    }

    public static void c(String str, String str2) {
        if (e()) {
            if (d()) {
                str = "OTA_UPGRADE" + str;
            }
            Log.i(str, str2);
        }
    }

    public static boolean d() {
        return f2517b;
    }

    public static boolean e() {
        return f2516a;
    }
}
