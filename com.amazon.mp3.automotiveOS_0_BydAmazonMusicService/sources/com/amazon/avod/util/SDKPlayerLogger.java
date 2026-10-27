package com.amazon.avod.util;

/* JADX INFO: loaded from: classes2.dex */
public final class SDKPlayerLogger {
    private static String concatTemplate(String str) {
        return String.format("%s.%s", "SDK.Player", str);
    }

    public static void log(String str) {
        DLog.logf(concatTemplate(str));
    }

    public static void log(String str, Object obj) {
        DLog.logf(concatTemplate(str), obj);
    }

    public static void log(String str, Object obj, Object obj2) {
        DLog.logf(concatTemplate(str), obj, obj2);
    }

    public static void warn(String str, Object... objArr) {
        DLog.warnf(concatTemplate(str), objArr);
    }
}
