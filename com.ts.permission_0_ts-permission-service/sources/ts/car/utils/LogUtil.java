package ts.car.utils;

import android.os.SystemProperties;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class LogUtil {
    private static final String PERSIST_PERMISSION_DEBUGGABLE = "persist.sys.permission.debuggable";
    private static final String TAG = "TSPS_TSPermissionManagerService";
    private static boolean sDebuggable;

    static {
        sDebuggable = false;
        sDebuggable = SystemProperties.getBoolean(PERSIST_PERMISSION_DEBUGGABLE, false);
    }

    public static void info(String msg) {
        if (sDebuggable) {
            Log.i(TAG, msg);
        }
    }
}
