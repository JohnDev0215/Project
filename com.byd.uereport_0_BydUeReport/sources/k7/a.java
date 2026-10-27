package k7;

import android.util.Log;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import j7.f;
import java.util.logging.Level;

/* JADX INFO: compiled from: AndroidLogger.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f5927a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f5928b;

    static {
        boolean z7;
        try {
            Class.forName("android.util.Log");
            z7 = true;
        } catch (ClassNotFoundException unused) {
            z7 = false;
        }
        f5927a = z7;
    }

    public a(String str) {
        this.f5928b = str;
    }

    public static boolean c() {
        return f5927a;
    }

    @Override // j7.f
    public void a(Level level, String str) {
        if (level != Level.OFF) {
            Log.println(d(level), this.f5928b, str);
        }
    }

    @Override // j7.f
    public void b(Level level, String str, Throwable th) {
        if (level != Level.OFF) {
            Log.println(d(level), this.f5928b, str + BydAdbHelper.COMMAND_LINE_END + Log.getStackTraceString(th));
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
