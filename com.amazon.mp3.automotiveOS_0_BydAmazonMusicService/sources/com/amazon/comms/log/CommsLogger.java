package com.amazon.comms.log;

import android.os.Build;
import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class CommsLogger {
    private static boolean userBuild = false;
    private static boolean userDebugBuild = false;
    private final String tag;

    static {
        String str = Build.TYPE;
        if (str != null) {
            userBuild = str.equals("user");
            userDebugBuild = str.equals("userdebug");
        }
    }

    private CommsLogger(String str) {
        this.tag = str;
    }

    public static CommsLogger getLogger(String str, Object obj) {
        return new CommsLogger(generateTag(str, obj));
    }

    public static boolean isUserDebugBuild() {
        return userDebugBuild;
    }

    public void d(String str) {
        if (isLoggable(LogLevel.Debug)) {
            Log.d(this.tag, str);
        }
    }

    public void w(String str) {
        if (isLoggable(LogLevel.Warning)) {
            Log.w(this.tag, str);
        }
    }

    public void e(String str) {
        if (isLoggable(LogLevel.Error)) {
            Log.e(this.tag, str);
        }
    }

    public void e(String str, Throwable th) {
        if (isLoggable(LogLevel.Error)) {
            Log.e(this.tag, str, th);
        }
    }

    public boolean isLoggable(LogLevel logLevel) {
        if (isUserDebugBuild()) {
            return true;
        }
        return Log.isLoggable(this.tag, logLevel.value);
    }

    public String sensitive(String str) {
        return isLoggable(LogLevel.Debug) ? str : "*****";
    }

    static String generateTag(String str, Object obj) {
        String simpleName;
        if (str == null) {
            throw new IllegalArgumentException("prefix must be non-null.");
        }
        if (obj == null) {
            throw new IllegalArgumentException("tagObj must be non-null.");
        }
        if (obj instanceof CharSequence) {
            simpleName = obj.toString();
            if (simpleName.equals("")) {
                throw new IllegalArgumentException("tagObj cannot be an empty string.");
            }
        } else {
            Class<?> cls = obj instanceof Class ? (Class) obj : obj.getClass();
            if (cls.isAnonymousClass() && cls.getEnclosingClass() != null) {
                simpleName = cls.getEnclosingClass().getSimpleName();
            } else {
                simpleName = cls.getSimpleName();
            }
        }
        String str2 = str + simpleName;
        return str2.length() > 23 ? str2.substring(0, 23) : str2;
    }
}
