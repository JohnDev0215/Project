package b.a.a.h;

import android.content.Context;
import android.util.Log;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static long f260a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f261b = new Object();

    public static boolean a(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static boolean b() {
        if (Math.abs(System.currentTimeMillis() - f260a) < 300) {
            Log.i("Utils", "isQuickClick true");
            return true;
        }
        f260a = System.currentTimeMillis();
        return false;
    }

    public static boolean c(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }
}
