package b.b.a.h;

import android.content.Context;

/* JADX INFO: compiled from: SharedPrefUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class i {
    public static String a(Context context, String str) {
        return context.getSharedPreferences("Cache_SharedPref", 0).getString(str, "");
    }

    public static void b(Context context, String str, String str2) {
        context.getSharedPreferences("Cache_SharedPref", 0).edit().putString(str, str2).apply();
    }
}
