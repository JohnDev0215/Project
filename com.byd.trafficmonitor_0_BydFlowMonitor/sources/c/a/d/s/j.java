package c.a.d.s;

import android.content.Context;

/* JADX INFO: compiled from: PreferencesUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f3689a = "pref_flow";

    public static String a(Context context, String str, String str2) {
        return context.getSharedPreferences(f3689a, 0).getString(str, str2);
    }
}
