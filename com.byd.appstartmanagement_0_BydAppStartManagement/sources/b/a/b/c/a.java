package b.a.b.c;

import android.util.Log;

/* JADX INFO: compiled from: CarSetLogUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f178a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f179b = "CarSetLogUtils";

    public static void a(Object obj, String str) {
        if (f178a) {
            Log.d(f179b, obj.getClass().getSimpleName() + "_content : " + str);
        }
    }
}
