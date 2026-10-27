package c.a.b.d;

import android.util.Log;

/* JADX INFO: compiled from: LogUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static long f2340a;

    public static void a(String str, String str2) {
        Log.d("otaUpdate-" + str, str2);
    }

    public static void b(String str, String str2) {
        Log.e("otaUpdate-" + str, str2);
    }

    public static void c(String str, String str2) {
        Log.w("otaUpdate-" + str, str2);
    }

    public static boolean d() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (Math.abs(jCurrentTimeMillis - f2340a) < 500) {
            return true;
        }
        f2340a = jCurrentTimeMillis;
        return false;
    }

    public static void e(String str, String str2) {
        Log.w("otaUpdate-" + str, str2);
    }
}
