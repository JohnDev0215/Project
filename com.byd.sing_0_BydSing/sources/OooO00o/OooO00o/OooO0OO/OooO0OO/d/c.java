package OooO00o.OooO00o.OooO0Oo.OooO0OO.d;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class c {
    public static String a(Context context, String str) {
        return context.getApplicationContext().getSharedPreferences("cache", 0).getString(str, null);
    }

    public static void b(Context context, String str, int i) {
        c(context, str, String.valueOf(i));
    }

    public static void c(Context context, String str, String str2) {
        context.getApplicationContext().getSharedPreferences("cache", 0).edit().putString(str, str2).apply();
    }
}
