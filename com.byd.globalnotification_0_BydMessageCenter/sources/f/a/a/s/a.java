package f.a.a.s;

import android.util.Log;
import f.a.a.g;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes.dex */
public class a implements g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final boolean f2597b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2598a;

    static {
        boolean z;
        try {
            Class.forName("android.util.Log");
            z = true;
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        f2597b = z;
    }

    public a(String str) {
        this.f2598a = str;
    }

    @Override // f.a.a.g
    public void a(Level level, String str) {
        if (level != Level.OFF) {
            Log.println(c(level), this.f2598a, str);
        }
    }

    @Override // f.a.a.g
    public void b(Level level, String str, Throwable th) {
        if (level != Level.OFF) {
            Log.println(c(level), this.f2598a, str + "\n" + Log.getStackTraceString(th));
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
