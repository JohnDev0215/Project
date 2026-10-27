package com.engine.tbox.core.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class LogUtils {
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    private static final int MAX_ENABLED_LOG_LEVEL = 2;
    private static final String TBOX_LOG = "tbox_log_level";
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    public static String TAG = "TBox";
    private static volatile int selectLevel = 3;

    public static void init(Context context, Handler handler) {
        final ContentResolver contentResolver = context.getContentResolver();
        selectLevel = Settings.Secure.getInt(contentResolver, TBOX_LOG, 3);
        Log.i(TAG, "init the log level:" + selectLevel);
        contentResolver.registerContentObserver(Settings.Secure.getUriFor(TBOX_LOG), true, new ContentObserver(handler) { // from class: com.engine.tbox.core.utils.LogUtils.1
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                int unused = LogUtils.selectLevel = Settings.Secure.getInt(contentResolver, LogUtils.TBOX_LOG, 3);
                Log.i(LogUtils.TAG, "update the log level:" + LogUtils.selectLevel);
            }
        });
    }

    public static boolean isLoggable(String tag, int level) {
        if (selectLevel <= level) {
            return true;
        }
        return false;
    }

    public static int v(String tag, String format, Object... args) {
        if (isLoggable(tag, 2)) {
            try {
                return Log.d(tag, String.format(format, args));
            } catch (Exception exp) {
                exp.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    public static int v(String tag, Throwable tr, String format, Object... args) {
        if (isLoggable(tag, 2)) {
            try {
                return Log.v(tag, String.format(format, args), tr);
            } catch (Exception exp) {
                exp.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    public static int d(String tag, String format, Object... args) {
        if (isLoggable(tag, 3)) {
            try {
                return Log.d(tag, String.format(format, args));
            } catch (Exception exp) {
                exp.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    public static int d(String tag, Throwable tr, String format, Object... args) {
        if (isLoggable(tag, 3)) {
            try {
                return Log.d(tag, String.format(format, args), tr);
            } catch (Exception exp) {
                exp.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    public static int i(String tag, String format, Object... args) {
        if (isLoggable(tag, 4)) {
            try {
                return Log.i(tag, String.format(format, args));
            } catch (Exception exp) {
                exp.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    public static int i(String tag, Throwable tr, String format, Object... args) {
        if (isLoggable(tag, 4)) {
            try {
                return Log.i(tag, String.format(format, args), tr);
            } catch (Exception exp) {
                exp.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    public static int w(String tag, String format, Object... args) {
        if (isLoggable(tag, 5)) {
            try {
                return Log.w(tag, String.format(format, args));
            } catch (Exception exp) {
                exp.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    public static int w(String tag, Throwable tr, String format, Object... args) {
        if (isLoggable(tag, 5)) {
            try {
                return Log.w(tag, String.format(format, args), tr);
            } catch (Exception exp) {
                exp.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    public static int e(String tag, String format, Object... args) {
        if (isLoggable(tag, 6)) {
            try {
                return Log.e(tag, String.format(format, args));
            } catch (Exception exp) {
                exp.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    public static int e(String tag, Throwable tr, String format, Object... args) {
        if (isLoggable(tag, 6)) {
            try {
                return Log.e(tag, String.format(format, args), tr);
            } catch (Exception exp) {
                exp.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    public static int wtf(String tag, String format, Object... args) {
        try {
            return Log.wtf(tag, String.format(format, args), new Error());
        } catch (Exception exp) {
            exp.printStackTrace();
            return 0;
        }
    }

    public static int wtf(String tag, Throwable tr, String format, Object... args) {
        try {
            return Log.wtf(tag, String.format(format, args), tr);
        } catch (Exception exp) {
            exp.printStackTrace();
            return 0;
        }
    }
}
