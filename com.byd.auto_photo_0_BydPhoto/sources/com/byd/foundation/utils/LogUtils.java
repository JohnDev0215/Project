package com.byd.foundation.utils;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class LogUtils {
    private static boolean DEBUG = true;
    private static boolean ERROR = true;
    private static boolean INFO = true;
    private static final String TAG = "LogUtils-";
    private static boolean VERBOSE = true;
    private static boolean WARN = true;

    public static void d(String str, String str2) {
        if (DEBUG) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String str3 = stackTraceElement.getFileName() + "(" + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName();
            Log.d(TAG + str, "[Thread:" + Thread.currentThread().getName() + "][fileInfo:" + str3 + "] " + str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (DEBUG) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String str3 = stackTraceElement.getFileName() + "(" + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName();
            Log.d(TAG + str, "[Thread:" + Thread.currentThread().getName() + "][fileInfo:" + str3 + "] " + str2, th);
        }
    }

    public static void e(String str, String str2) {
        if (ERROR) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String str3 = stackTraceElement.getFileName() + "(" + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName();
            Log.e(TAG + str, "[Thread:" + Thread.currentThread().getName() + "][fileInfo:" + str3 + "] " + str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (ERROR) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String str3 = stackTraceElement.getFileName() + "(" + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName();
            Log.e(TAG + str, "[Thread:" + Thread.currentThread().getName() + "][fileInfo:" + str3 + "] " + str2, th);
        }
    }

    public static void i(String str, String str2) {
        if (INFO) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String str3 = stackTraceElement.getFileName() + "(" + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName();
            Log.i(TAG + str, "[Thread:" + Thread.currentThread().getName() + "][fileInfo:" + str3 + "] " + str2);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (INFO) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String str3 = stackTraceElement.getFileName() + "(" + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName();
            Log.i(TAG + str, "[Thread:" + Thread.currentThread().getName() + "][fileInfo:" + str3 + "] " + str2, th);
        }
    }

    public static void printStackTrace() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement stackTraceElement : stackTrace) {
            sb.append(stackTraceElement.getClassName());
            sb.append(".");
            sb.append(stackTraceElement.getMethodName());
            sb.append("(");
            sb.append(stackTraceElement.getFileName());
            sb.append(":");
            sb.append(stackTraceElement.getLineNumber());
            sb.append(")");
            sb.append("\n");
        }
        w("StackTrace", "printStackTrace" + ((Object) sb));
    }

    public static void setDebugEnable(boolean z) {
        DEBUG = z;
        VERBOSE = z;
        WARN = z;
        INFO = z;
        ERROR = z;
    }

    public static void setDebugEnable(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        DEBUG = z;
        VERBOSE = z2;
        WARN = z3;
        INFO = z4;
        ERROR = z5;
    }

    public static void v(String str, String str2) {
        if (VERBOSE) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String str3 = stackTraceElement.getFileName() + "(" + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName();
            Log.v(TAG + str, "[Thread:" + Thread.currentThread().getName() + "][fileInfo:" + str3 + "] " + str2);
        }
    }

    public static void v(String str, String str2, Throwable th) {
        if (VERBOSE) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String str3 = stackTraceElement.getFileName() + "(" + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName();
            Log.v(TAG + str, "[Thread:" + Thread.currentThread().getName() + "][fileInfo:" + str3 + "] " + str2, th);
        }
    }

    public static void w(String str, String str2) {
        if (WARN) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String str3 = stackTraceElement.getFileName() + "(" + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName();
            Log.w(TAG + str, "[Thread:" + Thread.currentThread().getName() + "][fileInfo:" + str3 + "] " + str2);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (WARN) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String str3 = stackTraceElement.getFileName() + "(" + stackTraceElement.getLineNumber() + ") " + stackTraceElement.getMethodName();
            Log.w(TAG + str, "[Thread:" + Thread.currentThread().getName() + "][fileInfo:" + str3 + "] " + str2, th);
        }
    }
}
