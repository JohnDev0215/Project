package b.b.l.u;

import android.os.Process;
import android.util.Log;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: RpcLog.java */
/* JADX INFO: loaded from: classes2.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f2071a = "RPC";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f2072b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f2073c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Map<String, Long> f2074d;

    static {
        if (new File("/data/rpclog").exists()) {
            f2073c = true;
        }
        f2074d = new HashMap();
    }

    public static void a(String str) {
        c(16, null, str);
    }

    public static boolean b() {
        return f2073c;
    }

    public static void c(int i, String str, String str2) {
        if (f2072b) {
            i = 65536;
        }
        if (str == null) {
            str = f2071a;
        }
        if (str == null && i == 65536) {
            str = "RPC-ERROR";
        }
        if (str == null || (69905 & i) == 0) {
            return;
        }
        String str3 = "[" + Process.myPid() + "][RPC] " + str2;
        if (i == 1) {
            Log.v(str, str3);
            return;
        }
        if (i == 16) {
            Log.d(str, str3);
            return;
        }
        if (i == 256) {
            Log.i(str, str3);
        } else if (i == 4096) {
            Log.w(str, str3);
        } else {
            if (i != 65536) {
                return;
            }
            Log.e(str, str3);
        }
    }

    public static void d(String str) {
        c(1, null, str);
    }
}
