package com.android.internal.os;

import android.bluetooth.hci.BluetoothHciProtoEnums;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.StrictMode;
import android.os.SystemClock;
import android.system.suspend.ISuspendControlService;
import android.system.suspend.WakeLockInfo;
import android.util.Slog;
import com.android.internal.os.KernelWakelockStats;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public class KernelWakelockReader {
    private static final String TAG = "KernelWakelockReader";
    private static int sKernelWakelockUpdateVersion = 0;
    private static final String sSysClassWakeupDir = "/sys/class/wakeup";
    private static final String sWakelockFile = "/proc/wakelocks";
    private static final String sWakeupSourceFile = "/d/wakeup_sources";
    private static final int[] PROC_WAKELOCKS_FORMAT = {BluetoothHciProtoEnums.CMD_READ_LOCAL_AMP_INFO, 8201, 9, 9, 9, 8201};
    private static final int[] WAKEUP_SOURCES_FORMAT = {4105, 8457, 265, 265, 265, 265, 8457};
    private final String[] mProcWakelocksName = new String[3];
    private final long[] mProcWakelocksData = new long[3];
    private ISuspendControlService mSuspendControlService = null;
    private byte[] mKernelWakelockBuffer = new byte[32768];

    public final KernelWakelockStats readKernelWakelockStats(KernelWakelockStats kernelWakelockStats) {
        FileInputStream fileInputStream;
        boolean z;
        int i;
        if (new File(sSysClassWakeupDir).exists()) {
            updateVersion(kernelWakelockStats);
            if (getWakelockStatsFromSystemSuspend(kernelWakelockStats) == null) {
                Slog.w(TAG, "Failed to get wakelock stats from SystemSuspend");
                return null;
            }
            return removeOldStats(kernelWakelockStats);
        }
        int i2 = 0;
        Arrays.fill(this.mKernelWakelockBuffer, (byte) 0);
        long jUptimeMillis = SystemClock.uptimeMillis();
        int iAllowThreadDiskReadsMask = StrictMode.allowThreadDiskReadsMask();
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(sWakelockFile);
                    z = false;
                    i = 0;
                } catch (IOException e) {
                    Slog.wtf(TAG, "failed to read kernel wakelocks", e);
                    return null;
                } finally {
                    StrictMode.setThreadPolicyMask(iAllowThreadDiskReadsMask);
                }
            } catch (FileNotFoundException unused) {
                Slog.wtf(TAG, "neither /proc/wakelocks nor /d/wakeup_sources exists");
                return null;
            }
        } catch (FileNotFoundException unused2) {
            fileInputStream = new FileInputStream(sWakeupSourceFile);
            z = true;
            i = 0;
        }
        while (true) {
            int i3 = fileInputStream.read(this.mKernelWakelockBuffer, i, this.mKernelWakelockBuffer.length - i);
            if (i3 <= 0) {
                break;
            }
            i += i3;
        }
        fileInputStream.close();
        StrictMode.setThreadPolicyMask(iAllowThreadDiskReadsMask);
        long jUptimeMillis2 = SystemClock.uptimeMillis() - jUptimeMillis;
        if (jUptimeMillis2 > 100) {
            Slog.w(TAG, "Reading wakelock stats took " + jUptimeMillis2 + "ms");
        }
        if (i > 0) {
            if (i >= this.mKernelWakelockBuffer.length) {
                Slog.wtf(TAG, "Kernel wake locks exceeded mKernelWakelockBuffer size " + this.mKernelWakelockBuffer.length);
            }
            while (true) {
                if (i2 >= i) {
                    break;
                }
                if (this.mKernelWakelockBuffer[i2] == 0) {
                    i = i2;
                    break;
                }
                i2++;
            }
        }
        updateVersion(kernelWakelockStats);
        if (getWakelockStatsFromSystemSuspend(kernelWakelockStats) == null) {
            Slog.w(TAG, "Failed to get Native wakelock stats from SystemSuspend");
        }
        parseProcWakelocks(this.mKernelWakelockBuffer, i, z, kernelWakelockStats);
        return removeOldStats(kernelWakelockStats);
    }

    private KernelWakelockStats getWakelockStatsFromSystemSuspend(KernelWakelockStats kernelWakelockStats) {
        if (this.mSuspendControlService == null) {
            try {
                this.mSuspendControlService = ISuspendControlService.Stub.asInterface(ServiceManager.getServiceOrThrow("suspend_control"));
            } catch (ServiceManager.ServiceNotFoundException e) {
                Slog.wtf(TAG, "Required service suspend_control not available", e);
                return null;
            }
        }
        try {
            updateWakelockStats(this.mSuspendControlService.getWakeLockStats(), kernelWakelockStats);
            return kernelWakelockStats;
        } catch (RemoteException e2) {
            Slog.wtf(TAG, "Failed to obtain wakelock stats from ISuspendControlService", e2);
            return null;
        }
    }

    public KernelWakelockStats updateWakelockStats(WakeLockInfo[] wakeLockInfoArr, KernelWakelockStats kernelWakelockStats) {
        for (WakeLockInfo wakeLockInfo : wakeLockInfoArr) {
            if (!kernelWakelockStats.containsKey(wakeLockInfo.name)) {
                kernelWakelockStats.put(wakeLockInfo.name, new KernelWakelockStats.Entry((int) wakeLockInfo.activeCount, wakeLockInfo.totalTime * 1000, sKernelWakelockUpdateVersion));
            } else {
                KernelWakelockStats.Entry entry = kernelWakelockStats.get(wakeLockInfo.name);
                entry.mCount = (int) wakeLockInfo.activeCount;
                entry.mTotalTime = wakeLockInfo.totalTime * 1000;
                entry.mVersion = sKernelWakelockUpdateVersion;
            }
        }
        return kernelWakelockStats;
    }

    public KernelWakelockStats parseProcWakelocks(byte[] bArr, int i, boolean z, KernelWakelockStats kernelWakelockStats) {
        int[] iArr;
        long j;
        int i2 = 0;
        while (i2 < i && bArr[i2] != 10 && bArr[i2] != 0) {
            i2++;
        }
        int i3 = i2 + 1;
        synchronized (this) {
            int i4 = i3;
            while (i3 < i) {
                int i5 = i4;
                while (i5 < i) {
                    if (bArr[i5] == 10 || bArr[i5] == 0) {
                        break;
                    }
                    i5++;
                }
                if (i5 > i - 1) {
                    break;
                }
                String[] strArr = this.mProcWakelocksName;
                long[] jArr = this.mProcWakelocksData;
                for (int i6 = i4; i6 < i5; i6++) {
                    if ((bArr[i6] & 128) != 0) {
                        bArr[i6] = 63;
                    }
                }
                if (z) {
                    iArr = WAKEUP_SOURCES_FORMAT;
                } else {
                    iArr = PROC_WAKELOCKS_FORMAT;
                }
                boolean procLine = Process.parseProcLine(bArr, i4, i5, iArr, strArr, jArr, null);
                String strTrim = strArr[0].trim();
                int i7 = (int) jArr[1];
                if (z) {
                    j = jArr[2] * 1000;
                } else {
                    j = (jArr[2] + 500) / 1000;
                }
                if (!procLine || strTrim.length() <= 0) {
                    if (!procLine) {
                        try {
                            Slog.wtf(TAG, "Failed to parse proc line: " + new String(bArr, i4, i5 - i4));
                        } catch (Exception unused) {
                            Slog.wtf(TAG, "Failed to parse proc line!");
                        }
                    }
                } else if (!kernelWakelockStats.containsKey(strTrim)) {
                    kernelWakelockStats.put(strTrim, new KernelWakelockStats.Entry(i7, j, sKernelWakelockUpdateVersion));
                } else {
                    KernelWakelockStats.Entry entry = (KernelWakelockStats.Entry) kernelWakelockStats.get(strTrim);
                    if (entry.mVersion == sKernelWakelockUpdateVersion) {
                        entry.mCount += i7;
                        entry.mTotalTime += j;
                    } else {
                        entry.mCount = i7;
                        entry.mTotalTime = j;
                        entry.mVersion = sKernelWakelockUpdateVersion;
                    }
                }
                i4 = i5 + 1;
                i3 = i5;
            }
        }
        return kernelWakelockStats;
    }

    public KernelWakelockStats updateVersion(KernelWakelockStats kernelWakelockStats) {
        int i = sKernelWakelockUpdateVersion + 1;
        sKernelWakelockUpdateVersion = i;
        kernelWakelockStats.kernelWakelockVersion = i;
        return kernelWakelockStats;
    }

    public KernelWakelockStats removeOldStats(KernelWakelockStats kernelWakelockStats) {
        Iterator<KernelWakelockStats.Entry> it = kernelWakelockStats.values().iterator();
        while (it.hasNext()) {
            if (it.next().mVersion != sKernelWakelockUpdateVersion) {
                it.remove();
            }
        }
        return kernelWakelockStats;
    }
}
