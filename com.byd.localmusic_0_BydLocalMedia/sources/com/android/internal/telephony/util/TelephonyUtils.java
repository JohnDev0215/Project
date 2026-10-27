package com.android.internal.telephony.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.ComponentInfo;
import android.content.pm.ResolveInfo;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.os.SystemProperties;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes4.dex */
public final class TelephonyUtils {
    public static boolean IS_DEBUGGABLE;
    public static boolean IS_USER = "user".equals(Build.TYPE);

    public static String emptyIfNull(String str) {
        return str == null ? "" : str;
    }

    static {
        IS_DEBUGGABLE = SystemProperties.getInt("ro.debuggable", 0) == 1;
    }

    public static boolean checkDumpPermission(Context context, String str, PrintWriter printWriter) {
        if (context.checkCallingOrSelfPermission(Manifest.permission.DUMP) == 0) {
            return true;
        }
        printWriter.println("Permission Denial: can't dump " + str + " from from pid=" + Binder.getCallingPid() + ", uid=" + Binder.getCallingUid() + " due to missing android.permission.DUMP permission");
        return false;
    }

    public static <T> List<T> emptyIfNull(List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }

    public static RuntimeException rethrowAsRuntimeException(RemoteException remoteException) {
        throw new RuntimeException(remoteException);
    }

    public static ComponentInfo getComponentInfo(ResolveInfo resolveInfo) {
        if (resolveInfo.activityInfo != null) {
            return resolveInfo.activityInfo;
        }
        if (resolveInfo.serviceInfo != null) {
            return resolveInfo.serviceInfo;
        }
        if (resolveInfo.providerInfo != null) {
            return resolveInfo.providerInfo;
        }
        throw new IllegalStateException("Missing ComponentInfo!");
    }

    public static void runWithCleanCallingIdentity(Runnable runnable) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            runnable.run();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public static <T> T runWithCleanCallingIdentity(Supplier<T> supplier) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return supplier.get();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public static Bundle filterValues(Bundle bundle) {
        Bundle bundle2 = new Bundle(bundle);
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (!(obj instanceof Integer) && !(obj instanceof Long) && !(obj instanceof Double) && !(obj instanceof String) && !(obj instanceof int[]) && !(obj instanceof long[]) && !(obj instanceof double[]) && !(obj instanceof String[]) && !(obj instanceof PersistableBundle) && obj != null && !(obj instanceof Boolean) && !(obj instanceof boolean[])) {
                if (obj instanceof Bundle) {
                    bundle2.putBundle(str, filterValues((Bundle) obj));
                } else if (!obj.getClass().getName().startsWith("android.")) {
                    bundle2.remove(str);
                }
            }
        }
        return bundle2;
    }

    public static void waitUntilReady(CountDownLatch countDownLatch, long j) {
        try {
            countDownLatch.await(j, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
        }
    }
}
