package com.android.inputmethod.latin.utils;

/* JADX INFO: loaded from: classes2.dex */
public final class DebugLogUtils {
    private static final String TAG = "DebugLogUtils";
    private static final boolean sDBG = false;

    public static void l(final Object... args) {
    }

    public static void r(final Object... args) {
    }

    public static String s(final Object o) {
        return o == null ? "null" : o.toString();
    }

    public static String getStackTrace() {
        return getStackTrace(2147483646);
    }

    public static String getStackTrace(final int limit) {
        StringBuilder sb = new StringBuilder();
        try {
            throw new RuntimeException();
        } catch (RuntimeException e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (int i = 1; i < stackTrace.length && i < limit + 1; i++) {
                sb.append(stackTrace[i].toString() + "\n");
            }
            return sb.toString();
        }
    }

    public static String getStackTrace(final Throwable t) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : t.getStackTrace()) {
            sb.append(stackTraceElement.toString() + "\n");
        }
        return sb.toString();
    }
}
