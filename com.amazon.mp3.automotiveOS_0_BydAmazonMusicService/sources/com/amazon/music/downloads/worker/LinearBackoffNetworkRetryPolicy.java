package com.amazon.music.downloads.worker;

import android.os.SystemClock;

/* JADX INFO: loaded from: classes4.dex */
public final class LinearBackoffNetworkRetryPolicy implements NetworkRetryPolicy {
    private long mFileSizeDownloaded = 0;
    private int mRetryCount = 0;

    @Override // com.amazon.music.downloads.worker.NetworkRetryPolicy
    public boolean sleepBeforeRetry(long j) {
        if (j != this.mFileSizeDownloaded) {
            this.mRetryCount = 0;
        }
        this.mFileSizeDownloaded = j;
        int i = this.mRetryCount + 1;
        this.mRetryCount = i;
        if (i > 2) {
            return false;
        }
        SystemClock.sleep(i * 2000);
        return true;
    }
}
