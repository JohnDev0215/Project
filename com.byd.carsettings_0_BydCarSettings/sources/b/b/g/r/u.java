package b.b.g.r;

import android.content.Context;
import android.provider.Settings;

/* JADX INFO: compiled from: SettingsUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class u {
    public static int a(Context context, String str) {
        return b(context, str, 1);
    }

    public static int b(Context context, String str, int i) {
        return Settings.System.getInt(context.getContentResolver(), str, i);
    }

    public static void c(Context context, String str, int i) {
        Settings.System.putInt(context.getContentResolver(), str, i);
    }
}
