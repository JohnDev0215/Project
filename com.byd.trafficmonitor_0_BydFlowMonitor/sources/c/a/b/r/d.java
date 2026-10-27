package c.a.b.r;

import android.os.Process;
import android.util.Log;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: RpcLog.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f3534a = "RPC";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f3535b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f3536c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Map<String, Long> f3537d;

    static {
        if (new File("/data/rpclog").exists()) {
            f3536c = true;
        }
        f3537d = new HashMap();
    }

    public static boolean a() {
        return f3536c;
    }

    public static void b(int i2, String str, String str2) {
        if (f3535b) {
            i2 = 65536;
        }
        if (str == null) {
            str = f3534a;
        }
        if (str == null && i2 == 65536) {
            str = "RPC-ERROR";
        }
        if (str == null || (69905 & i2) == 0) {
            return;
        }
        String str3 = "[" + Process.myPid() + "][RPC] " + str2;
        if (i2 == 1) {
            Log.v(str, str3);
            return;
        }
        if (i2 == 16) {
            Log.d(str, str3);
            return;
        }
        if (i2 == 256) {
            Log.i(str, str3);
        } else if (i2 == 4096) {
            Log.w(str, str3);
        } else {
            if (i2 != 65536) {
                return;
            }
            Log.e(str, str3);
        }
    }

    public static void c(String str) {
        b(1, null, str);
    }
}
