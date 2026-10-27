package c.a.k;

import android.util.Log;

/* JADX INFO: compiled from: UsbUpdateLogger.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f2883a = "UsbUpdateLogger";

    public static String a() {
        return Thread.currentThread().getStackTrace()[4].getMethodName();
    }

    public static void b(String str) {
        Log.e(f2883a, str);
    }

    public static void c(String str) {
        Log.i(f2883a, str);
    }

    public static void d() {
        c(a());
    }
}
