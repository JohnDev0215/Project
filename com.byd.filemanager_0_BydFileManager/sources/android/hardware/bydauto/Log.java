package android.hardware.bydauto;

import android.app.Application;
import android.provider.Settings;

/* JADX INFO: loaded from: classes.dex */
public class Log {
    public static final int DEBUG_LOGGER_CLOSE = 0;
    public static final int DEBUG_LOGGER_OPEN = 1;
    public static final String PERSIST_DEBUG_LOGGER = "persist.javalib.log.logger";
    public static final String TAG = "JAVALIB.LOG";
    private static Application mApp = null;
    private static boolean sFlag = checkLogLogger();

    private static boolean checkLogLogger() {
        Application application = getApplication();
        return application != null && Settings.System.getInt(application.getContentResolver(), PERSIST_DEBUG_LOGGER, 0) == 1;
    }

    public static void d(String str, String str2) {
        if (sFlag) {
            android.util.Log.d(str, str2);
        }
    }

    public static void e(String str, String str2) {
        android.util.Log.e(str, str2);
    }

    private static Application getApplication() {
        Application application = mApp;
        if (application != null) {
            return application;
        }
        try {
            mApp = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return mApp;
    }

    public static void i(String str, String str2) {
        if (sFlag) {
            android.util.Log.i(str, str2);
        }
    }

    public static void v(String str, String str2) {
        if (sFlag) {
            android.util.Log.v(str, str2);
        }
    }

    public static void w(String str, String str2) {
        android.util.Log.w(str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        android.util.Log.e(str, str2, th);
    }
}
