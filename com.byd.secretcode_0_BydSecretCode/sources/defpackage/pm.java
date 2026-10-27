package defpackage;

import android.util.Log;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.util.logging.Level;

/* JADX INFO: compiled from: AndroidLogger.java */
/* JADX INFO: loaded from: classes.dex */
public class pm implements em {
    public static final boolean a;
    public final String b;

    static {
        boolean z;
        try {
            Class.forName("android.util.Log");
            z = true;
        } catch (ClassNotFoundException unused) {
            z = false;
        }
        a = z;
    }

    public pm(String str) {
        this.b = str;
    }

    public static boolean c() {
        return a;
    }

    @Override // defpackage.em
    public void a(Level level, String str) {
        if (level != Level.OFF) {
            Log.println(d(level), this.b, str);
        }
    }

    @Override // defpackage.em
    public void b(Level level, String str, Throwable th) {
        if (level != Level.OFF) {
            Log.println(d(level), this.b, str + BydAdbHelper.COMMAND_LINE_END + Log.getStackTraceString(th));
        }
    }

    public final int d(Level level) {
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
