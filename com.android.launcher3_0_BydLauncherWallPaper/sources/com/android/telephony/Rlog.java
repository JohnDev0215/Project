package com.android.telephony;

import android.security.keystore.KeyProperties;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.android.internal.telephony.util.TelephonyUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes4.dex */
public final class Rlog {
    private static final boolean USER_BUILD = TelephonyUtils.IS_USER;

    private Rlog() {
    }

    private static int log(int i, String str, String str2) {
        return Log.logToRadioBuffer(i, str, str2);
    }

    public static int v(String str, String str2) {
        return log(2, str, str2);
    }

    public static int v(String str, String str2, Throwable th) {
        return log(2, str, str2 + '\n' + Log.getStackTraceString(th));
    }

    public static int d(String str, String str2) {
        return log(3, str, str2);
    }

    public static int d(String str, String str2, Throwable th) {
        return log(3, str, str2 + '\n' + Log.getStackTraceString(th));
    }

    public static int i(String str, String str2) {
        return log(4, str, str2);
    }

    public static int i(String str, String str2, Throwable th) {
        return log(4, str, str2 + '\n' + Log.getStackTraceString(th));
    }

    public static int w(String str, String str2) {
        return log(5, str, str2);
    }

    public static int w(String str, String str2, Throwable th) {
        return log(5, str, str2 + '\n' + Log.getStackTraceString(th));
    }

    public static int w(String str, Throwable th) {
        return log(5, str, Log.getStackTraceString(th));
    }

    public static int e(String str, String str2) {
        return log(6, str, str2);
    }

    public static int e(String str, String str2, Throwable th) {
        return log(6, str, str2 + '\n' + Log.getStackTraceString(th));
    }

    public static int println(int i, String str, String str2) {
        return log(i, str, str2);
    }

    public static boolean isLoggable(String str, int i) {
        return Log.isLoggable(str, i);
    }

    public static String pii(String str, Object obj) {
        String strValueOf = String.valueOf(obj);
        return (obj == null || TextUtils.isEmpty(strValueOf) || isLoggable(str, 2)) ? strValueOf : "[" + secureHash(strValueOf.getBytes()) + "]";
    }

    public static String pii(boolean z, Object obj) {
        String strValueOf = String.valueOf(obj);
        return (obj == null || TextUtils.isEmpty(strValueOf) || z) ? strValueOf : "[" + secureHash(strValueOf.getBytes()) + "]";
    }

    private static String secureHash(byte[] bArr) {
        if (USER_BUILD) {
            return "****";
        }
        try {
            return Base64.encodeToString(MessageDigest.getInstance(KeyProperties.DIGEST_SHA1).digest(bArr), 11);
        } catch (NoSuchAlgorithmException unused) {
            return "####";
        }
    }
}
