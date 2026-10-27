package b.b.a.h;

import android.util.Log;

/* JADX INFO: compiled from: CarSetLogUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f522a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f523b = "CarSetLogUtils";

    public static void a(Object obj, String str) {
        if (f522a) {
            Log.d(f523b, obj.getClass().getSimpleName() + "_content : " + str);
        }
    }

    public static void b(Object obj, String str) {
        if (f522a) {
            Log.d(f523b, obj.getClass().getSimpleName() + "_content : " + str);
        }
    }
}
