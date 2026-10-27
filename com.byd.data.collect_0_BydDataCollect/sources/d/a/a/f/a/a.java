package d.a.a.f.a;

import android.content.Context;
import android.provider.Settings;

/* JADX INFO: compiled from: ConfigManager.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile a f4629a;

    private a() {
    }

    public static a b() {
        if (f4629a == null) {
            synchronized (a.class) {
                if (f4629a == null) {
                    f4629a = new a();
                }
            }
        }
        return f4629a;
    }

    private int c(Context context, String str, int i2) {
        return Settings.System.getInt(context.getContentResolver(), str, i2);
    }

    public boolean a(Context context) {
        int iC = c(context, "bydux_catch_crash_enable", 1);
        d.a.a.f.b.b.a.j("value=" + iC);
        return iC == 1;
    }

    public boolean d(Context context) {
        int iC = c(context, "bydux_loge_enable", 1);
        d.a.a.f.b.b.a.j("value=" + iC);
        return iC == 1;
    }

    public boolean e(Context context) {
        int iC = c(context, "bydux_logv_enable", 1);
        d.a.a.f.b.b.a.j("value=" + iC);
        return iC == 1;
    }
}
