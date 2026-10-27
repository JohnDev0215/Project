package org.chromium.base.memory;

import android.app.ActivityManager;
import android.os.Debug;
import android.os.Process;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes.dex */
public class MemoryInfoBridge {
    @CalledByNative
    public static Debug.MemoryInfo getActivityManagerMemoryInfoForSelf() {
        try {
            Debug.MemoryInfo[] processMemoryInfo = ((ActivityManager) xz.a.getSystemService("activity")).getProcessMemoryInfo(new int[]{Process.myPid()});
            if (processMemoryInfo == null) {
                return null;
            }
            return processMemoryInfo[0];
        } catch (SecurityException unused) {
            return null;
        }
    }
}
