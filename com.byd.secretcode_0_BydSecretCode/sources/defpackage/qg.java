package defpackage;

import android.content.Context;
import android.preference.PreferenceManager;

/* JADX INFO: compiled from: SpUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class qg {
    public static Context a;

    public static void a(Context context, boolean z) {
        b(context, "SAVE_WIFI_ADB_SWITCH_KEY", z);
    }

    public static void b(Context context, String str, boolean z) {
        if (a == null) {
            a = context.getApplicationContext();
        }
        PreferenceManager.getDefaultSharedPreferences(a).edit().putBoolean(str, z).apply();
    }
}
