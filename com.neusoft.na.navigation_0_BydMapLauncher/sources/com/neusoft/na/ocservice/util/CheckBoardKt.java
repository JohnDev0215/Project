package com.neusoft.na.ocservice.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CheckBoard.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0006\u0010\u0006\u001a\u00020\u0005\u001a\u0006\u0010\u0007\u001a\u00020\u0005\u001a\u0006\u0010\b\u001a\u00020\u0005\u001a\u0006\u0010\t\u001a\u00020\u0005\u001a\u0006\u0010\n\u001a\u00020\u0005\u001a\u0006\u0010\u000b\u001a\u00020\u0005\u001a\u0006\u0010\f\u001a\u00020\u0005\u001a\u000e\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f\u001a\u000e\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f\u001a\u0006\u0010\u0011\u001a\u00020\u0005\u001a\u0006\u0010\u0012\u001a\u00020\u0005\u001a\u0006\u0010\u0013\u001a\u00020\u0005\u001a\u0006\u0010\u0014\u001a\u00020\u0005\u001a\u0006\u0010\u0015\u001a\u00020\u0005\u001a\u0006\u0010\u0016\u001a\u00020\u0005\u001a\u0006\u0010\u0017\u001a\u00020\u0005\u001a\u0006\u0010\u0018\u001a\u00020\u0005\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0019"}, d2 = {"LOW_MEMORY_DEVICE_LIMIT", "", "getLOW_MEMORY_DEVICE_LIMIT", "()J", "isByd", "", "isByd8155f", "isBydSeries", "isC3", "isC4Pro", "isGreatwallA01", "isGreatwallB01", "isGreatwallV61", "isLowEndDevice", "context", "Landroid/content/Context;", "isLowMemoryDevice", "isProtonMC2", "isProtonX11Mobile", "isSeres", "isSmart", "isStandard", "isWeyforproductv3", "isZeekr", "isZeekrBx1e", "ocservice_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
public final class CheckBoardKt {
    private static final long LOW_MEMORY_DEVICE_LIMIT = 1073741824;

    public static final boolean isByd() {
        return false;
    }

    public static final boolean isByd8155f() {
        return true;
    }

    public static final boolean isGreatwallA01() {
        return false;
    }

    public static final boolean isGreatwallB01() {
        return false;
    }

    public static final boolean isGreatwallV61() {
        return false;
    }

    public static final boolean isProtonMC2() {
        return false;
    }

    public static final boolean isProtonX11Mobile() {
        return false;
    }

    public static final boolean isSeres() {
        return false;
    }

    public static final boolean isSmart() {
        return false;
    }

    public static final boolean isStandard() {
        return false;
    }

    public static final boolean isWeyforproductv3() {
        return false;
    }

    public static final boolean isZeekrBx1e() {
        return false;
    }

    public static final long getLOW_MEMORY_DEVICE_LIMIT() {
        return LOW_MEMORY_DEVICE_LIMIT;
    }

    public static final boolean isC4Pro() {
        return Intrinsics.areEqual(Build.BOARD, "c4pro");
    }

    public static final boolean isC3() {
        return Intrinsics.areEqual(Build.BOARD, "C3ALFUS");
    }

    public static final boolean isBydSeries() {
        return isByd() || isByd8155f();
    }

    public static final boolean isZeekr() {
        return isZeekrBx1e();
    }

    public static final boolean isLowMemoryDevice(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) systemService).getMemoryInfo(memoryInfo);
        return memoryInfo.totalMem < LOW_MEMORY_DEVICE_LIMIT;
    }

    public static final boolean isLowEndDevice(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return isLowMemoryDevice(context);
    }
}
