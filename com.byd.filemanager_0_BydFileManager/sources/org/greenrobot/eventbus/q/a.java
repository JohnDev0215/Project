package org.greenrobot.eventbus.q;

import android.util.Log;
import java.util.logging.Level;
import org.greenrobot.eventbus.f;

/* JADX INFO: compiled from: AndroidLogger.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final boolean f4727a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f4728b;

    static {
        boolean z;
        try {
            Class.forName("android.util.Log");
            z = true;
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        f4727a = z;
    }

    public a(String str) {
        this.f4728b = str;
    }

    public static boolean c() {
        return f4727a;
    }

    private int d(Level level) {
        int iIntValue = level.intValue();
        if (iIntValue < 800) {
            return iIntValue < 500 ? 2 : 3;
        }
        if (iIntValue < 900) {
            return 4;
        }
        return iIntValue < 1000 ? 5 : 6;
    }

    @Override // org.greenrobot.eventbus.f
    public void a(Level level, String str) {
        if (level != Level.OFF) {
            Log.println(d(level), this.f4728b, str);
        }
    }

    @Override // org.greenrobot.eventbus.f
    public void b(Level level, String str, Throwable th) {
        if (level != Level.OFF) {
            Log.println(d(level), this.f4728b, str + "\n" + Log.getStackTraceString(th));
        }
    }
}
