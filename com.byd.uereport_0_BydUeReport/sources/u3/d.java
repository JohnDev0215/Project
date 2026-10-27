package u3;

import android.os.Process;
import android.util.Log;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: RpcLog.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f8169a = "RPC";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f8170b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f8171c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static Map<String, Long> f8172d;

    static {
        if (new File("/data/rpclog").exists()) {
            f8171c = true;
        }
        f8172d = new HashMap();
    }

    public static boolean a() {
        return f8171c;
    }

    public static void b(int i8, String str, String str2) {
        if (f8170b) {
            i8 = 65536;
        }
        if (str == null) {
            str = f8169a;
        }
        if (str == null && i8 == 65536) {
            str = "RPC-ERROR";
        }
        if (str == null || (69905 & i8) == 0) {
            return;
        }
        String str3 = "[" + Process.myPid() + "][RPC] " + str2;
        if (i8 == 1) {
            Log.v(str, str3);
            return;
        }
        if (i8 == 16) {
            Log.d(str, str3);
            return;
        }
        if (i8 == 256) {
            Log.i(str, str3);
        } else if (i8 == 4096) {
            Log.w(str, str3);
        } else {
            if (i8 != 65536) {
                return;
            }
            Log.e(str, str3);
        }
    }

    public static void c(String str) {
        b(1, null, str);
    }
}
