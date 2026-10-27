package com.amazon.mp3.download.generator;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.task.PrimeDeviceAuthorizationTask;
import com.amazon.mp3.util.PrimeContentUtil;

/* JADX INFO: loaded from: classes3.dex */
public class DownloadAvailabilityStateListener implements PrimeContentUtil.ContentAvailabilityStateListener {
    private static long sPrimeAuthDeclineTimestamp;
    private final Context mContext;
    private boolean mShouldWait = true;

    public DownloadAvailabilityStateListener(Context context) {
        this.mContext = context;
    }

    public boolean shouldWait() {
        return this.mShouldWait;
    }

    @Override // com.amazon.mp3.util.PrimeContentUtil.ContentAvailabilityStateListener
    public void resultReceived(final boolean contentAvailable, final ContentUnavailableReason reasonUnavailable) {
        if (contentAvailable) {
            synchronized (this) {
                this.mShouldWait = false;
                notify();
            }
        } else if (reasonUnavailable == ContentUnavailableReason.DEVICE_NOT_PRIME_AUTHORIZED) {
            this.mShouldWait = true;
            new PrimeDeviceAuthorizationTask(new PrimeDeviceAuthorizationTask.IContinuation() { // from class: com.amazon.mp3.download.generator.DownloadAvailabilityStateListener.1
                @Override // com.amazon.mp3.task.PrimeDeviceAuthorizationTask.IContinuation
                public void doWork() {
                    DownloadAvailabilityStateListener.this.mContext.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
                    synchronized (DownloadAvailabilityStateListener.this) {
                        DownloadAvailabilityStateListener.this.mShouldWait = false;
                        DownloadAvailabilityStateListener.this.notify();
                    }
                }

                @Override // com.amazon.mp3.task.PrimeDeviceAuthorizationTask.IContinuation
                public void doNotWork() {
                    synchronized (DownloadAvailabilityStateListener.this) {
                        long unused = DownloadAvailabilityStateListener.sPrimeAuthDeclineTimestamp = SystemClock.elapsedRealtime();
                        DownloadAvailabilityStateListener.this.mShouldWait = false;
                        DownloadAvailabilityStateListener.this.notify();
                    }
                }

                @Override // com.amazon.mp3.task.PrimeDeviceAuthorizationTask.IContinuation
                public void onNetworkError() {
                    synchronized (DownloadAvailabilityStateListener.this) {
                        DownloadAvailabilityStateListener.this.mShouldWait = false;
                        DownloadAvailabilityStateListener.this.notify();
                    }
                }
            }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            synchronized (this) {
                sPrimeAuthDeclineTimestamp = SystemClock.elapsedRealtime();
                this.mShouldWait = false;
                notify();
            }
        }
    }
}
