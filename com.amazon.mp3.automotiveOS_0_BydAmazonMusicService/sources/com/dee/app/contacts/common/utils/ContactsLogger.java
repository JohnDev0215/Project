package com.dee.app.contacts.common.utils;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import com.dee.app.contacts.interfaces.IContactsLogger;
import com.dee.app.contacts.interfaces.models.data.enums.LogLevel;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes5.dex */
@SuppressLint({"LogTagMismatch"})
public class ContactsLogger implements IContactsLogger {
    private static boolean userBuild = false;
    private static boolean userDebugBuild = false;
    public String tag;

    public ContactsLogger() {
        this.tag = "TAG";
    }

    private ContactsLogger(String str) {
        this.tag = str;
    }

    public static ContactsLogger getLogger(String str, Object obj) {
        return new ContactsLogger(generateTag(str, obj));
    }

    public static boolean isUserDebugBuild() {
        return userDebugBuild;
    }

    public void d(String str) {
        if (isLoggable(LogLevel.Debug)) {
            Log.d(this.tag, str);
        }
    }

    public void i(String str) {
        if (isLoggable(LogLevel.Info)) {
            Log.i(this.tag, str);
        }
    }

    public void i(String str, Throwable th) {
        if (isLoggable(LogLevel.Info)) {
            Log.i(this.tag, str, th);
        }
    }

    public void e(@Nonnull String str, Object... objArr) {
        if (isLoggable(LogLevel.Error)) {
            Log.e(this.tag, String.format(str, objArr));
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
        return Log.isLoggable(this.tag, logLevel.getValue());
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

    static {
        String str = Build.TYPE;
        if (str != null) {
            userBuild = str.equals("user");
            userDebugBuild = str.equals("userdebug");
        }
    }
}
