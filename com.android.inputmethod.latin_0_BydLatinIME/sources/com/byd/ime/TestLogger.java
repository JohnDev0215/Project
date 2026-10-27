package com.byd.ime;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TestLogger.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004H\u0007J\u001c\u0010\t\u001a\u00020\n2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004H\u0007J\u0012\u0010\f\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004H\u0007J\b\u0010\r\u001a\u00020\nH\u0007J\b\u0010\u000e\u001a\u00020\nH\u0007J\u0012\u0010\u000e\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004H\u0007J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u00048BX\u0083\u0004¢\u0006\f\u0012\u0004\b\u0006\u0010\u0002\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/byd/ime/TestLogger;", "", "()V", "TAG", "", "methodNameForLogger", "getMethodNameForLogger$annotations", "getMethodNameForLogger", "()Ljava/lang/String;", "logE", "", "info", "logI", "logMethod", "logStackTrace", "setRTL", "context", "Landroid/content/Context;", "LatinIME_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class TestLogger {
    public static final TestLogger INSTANCE = new TestLogger();
    private static String TAG = "TestLogger";

    @JvmStatic
    private static /* synthetic */ void getMethodNameForLogger$annotations() {
    }

    private TestLogger() {
    }

    @JvmStatic
    public static final void logE(String info) {
        Log.e(TAG, info);
    }

    @JvmStatic
    public static final void logE(String TAG2, String info) {
        Log.e(TAG2, info);
    }

    @JvmStatic
    public static final void logI(String info) {
        Log.i(TAG, info);
    }

    @JvmStatic
    public static final void logStackTrace() {
        TAG = "LoggerTrace";
        logI(Log.getStackTraceString(new Throwable()));
        TAG = "TestLogger";
    }

    @JvmStatic
    public static final void logStackTrace(String info) {
        logI(info);
        TAG = "LoggerTrace";
        logI(Log.getStackTraceString(new Throwable()));
        TAG = "TestLogger";
    }

    @JvmStatic
    public static final void logMethod() {
        logI(getMethodNameForLogger());
    }

    private static final String getMethodNameForLogger() {
        String methodName = Thread.currentThread().getStackTrace()[4].getMethodName();
        Intrinsics.checkNotNullExpressionValue(methodName, "ste[4].methodName");
        return methodName;
    }

    @JvmStatic
    public static final void setRTL(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Locale locale = new Locale("fa_IR");
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics, "context.resources.displayMetrics");
        context.getResources().updateConfiguration(configuration, displayMetrics);
    }
}
