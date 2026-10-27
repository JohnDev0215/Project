package com.android.internal.util;

import android.os.Debug;
import android.os.StrictMode;

/* JADX INFO: loaded from: classes4.dex */
public final class MemInfoReader {
    final long[] mInfos = new long[16];

    public void readMemInfo() {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            Debug.getMemInfo(this.mInfos);
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }

    public long getTotalSize() {
        return this.mInfos[0] * 1024;
    }

    public long getFreeSize() {
        return this.mInfos[1] * 1024;
    }

    public long getCachedSize() {
        return getCachedSizeKb() * 1024;
    }

    public long getKernelUsedSize() {
        return getKernelUsedSizeKb() * 1024;
    }

    public long getTotalSizeKb() {
        return this.mInfos[0];
    }

    public long getFreeSizeKb() {
        return this.mInfos[1];
    }

    public long getCachedSizeKb() {
        long[] jArr = this.mInfos;
        long j = jArr[15];
        if (j == 0) {
            j = jArr[6];
        }
        long[] jArr2 = this.mInfos;
        return ((jArr2[2] + j) + jArr2[3]) - jArr2[11];
    }

    public long getKernelUsedSizeKb() {
        long[] jArr = this.mInfos;
        long j = jArr[4] + jArr[7] + jArr[12] + jArr[13];
        return !Debug.isVmapStack() ? j + this.mInfos[14] : j;
    }

    public long getSwapTotalSizeKb() {
        return this.mInfos[8];
    }

    public long getSwapFreeSizeKb() {
        return this.mInfos[9];
    }

    public long getZramTotalSizeKb() {
        return this.mInfos[10];
    }

    public long[] getRawInfo() {
        return this.mInfos;
    }
}
