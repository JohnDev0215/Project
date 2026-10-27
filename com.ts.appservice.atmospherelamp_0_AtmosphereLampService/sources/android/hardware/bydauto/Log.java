package android.hardware.bydauto;

import android.app.Application;
import android.content.Context;
import android.provider.Settings;

/* JADX INFO: loaded from: classes.dex */
public class Log {
    public static final int DEBUG_LOGGER_CLOSE = 0;
    public static final int DEBUG_LOGGER_OPEN = 1;
    public static final String PERSIST_DEBUG_LOGGER = "persist.javalib.log.logger";
    public static final String TAG = "JAVALIB.LOG";
    private static Application mApp;
    private static boolean sFlag;

    static {
        sFlag = false;
        sFlag = checkLogLogger();
    }

    private static Application getApplication() {
        Application application = mApp;
        if (application != null) {
            return application;
        }
        try {
            mApp = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, (Object[]) null);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return mApp;
    }

    private static boolean checkLogLogger() {
        Context context = getApplication();
        return context != null && Settings.System.getInt(context.getContentResolver(), PERSIST_DEBUG_LOGGER, 0) == 1;
    }

    public static void v(String tag, String msg) {
        if (sFlag) {
            android.util.Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (sFlag) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (sFlag) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        android.util.Log.w(tag, msg);
    }

    public static void e(String tag, String msg) {
        android.util.Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable e) {
        android.util.Log.e(tag, msg, e);
    }
}
