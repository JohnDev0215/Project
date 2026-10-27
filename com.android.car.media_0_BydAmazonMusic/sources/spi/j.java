package spi;

import android.util.Log;
import java.util.logging.Level;

/* JADX INFO: compiled from: AndroidLogger.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j implements j6 {
    public static final boolean b;
    public final String a;

    static {
        boolean z;
        try {
            Class.forName("android.util.Log");
            z = true;
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        b = z;
    }

    public j(String str) {
        this.a = str;
    }

    @Override // spi.j6
    public final void a(Level level, String str) {
        if (level != Level.OFF) {
            Log.println(c(level), this.a, str);
        }
    }

    @Override // spi.j6
    public final void b(Level level, String str, Throwable th) {
        if (level != Level.OFF) {
            int iC = c(level);
            String str2 = this.a;
            StringBuilder sbN = c.n(str, "\n");
            sbN.append(Log.getStackTraceString(th));
            Log.println(iC, str2, sbN.toString());
        }
    }

    public final int c(Level level) {
        int iIntValue = level.intValue();
        if (iIntValue < 800) {
            return iIntValue < 500 ? 2 : 3;
        }
        if (iIntValue < 900) {
            return 4;
        }
        return iIntValue < 1000 ? 5 : 6;
    }
}
