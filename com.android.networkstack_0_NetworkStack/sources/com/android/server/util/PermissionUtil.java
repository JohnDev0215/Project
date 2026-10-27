package com.android.server.util;

import android.os.Binder;
import android.os.Process;
import android.os.UserHandle;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class PermissionUtil {
    private static final AtomicInteger sSystemPid = new AtomicInteger(-1);

    public static void enforceNetworkStackCallingPermission() {
        int callingUid = Binder.getCallingUid();
        if (callingUid == 1000) {
            checkConsistentSystemPid();
        } else {
            if (callingUid == Process.myUid() || UserHandle.getAppId(callingUid) == 1002) {
                return;
            }
            throw new SecurityException("Invalid caller: " + callingUid);
        }
    }

    private static void checkConsistentSystemPid() {
        int callingPid = Binder.getCallingPid();
        if (sSystemPid.compareAndSet(-1, callingPid) || sSystemPid.get() == callingPid) {
            return;
        }
        throw new SecurityException("Invalid PID for the system server, expected " + sSystemPid.get() + " but was called from " + callingPid);
    }

    public static void checkDumpPermission() {
        int callingUid = Binder.getCallingUid();
        if (callingUid == Process.myUid() || callingUid == 1000 || callingUid == 0 || callingUid == 2000) {
            return;
        }
        throw new SecurityException("No dump permissions for caller: " + callingUid);
    }
}
