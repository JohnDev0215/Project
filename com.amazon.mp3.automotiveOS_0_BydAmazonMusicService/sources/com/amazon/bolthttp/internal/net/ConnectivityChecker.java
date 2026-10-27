package com.amazon.bolthttp.internal.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.amazon.bolthttp.BoltConfig;
import com.amazon.bolthttp.ConnectivityTimeoutException;
import com.amazon.bolthttp.internal.Logger;
import com.amazon.bolthttp.internal.util.TimeUtils;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public final class ConnectivityChecker {
    private final ConnectivityManager mConnectivityManager;
    private final Logger mLogger;
    private final Object mLock = new Object();
    private volatile NetworkInfo.DetailedState mCurrentState = null;
    private volatile int mNumFailedChecks = 0;
    private volatile long mNextCheckTimestamp = 0;

    public ConnectivityChecker(@Nonnull BoltConfig boltConfig, @Nonnull Logger logger) {
        this.mConnectivityManager = ((BoltConfig) Preconditions.checkNotNull(boltConfig, "config")).getConnectivityManager();
        this.mLogger = (Logger) Preconditions.checkNotNull(logger, "logger");
    }

    public void waitUntilConnected(@Nonnegative long j) throws ConnectivityTimeoutException, InterruptedException {
        if (this.mConnectivityManager == null) {
            return;
        }
        long timeMillis = TimeUtils.getTimeMillis();
        long jCheckIfRefreshNeeded = 0;
        long timeMillis2 = timeMillis;
        while (timeMillis + j >= timeMillis2) {
            TimeUtils.sleep(jCheckIfRefreshNeeded);
            timeMillis2 = TimeUtils.getTimeMillis();
            jCheckIfRefreshNeeded = checkIfRefreshNeeded(timeMillis2);
            if (this.mCurrentState == NetworkInfo.DetailedState.CONNECTED) {
                return;
            }
        }
        throw new ConnectivityTimeoutException(this.mCurrentState);
    }

    private long checkIfRefreshNeeded(long j) {
        long jMax;
        synchronized (this.mLock) {
            if (this.mNextCheckTimestamp <= j) {
                refreshNetworkState();
                if (this.mCurrentState == NetworkInfo.DetailedState.CONNECTED) {
                    this.mNumFailedChecks = 0;
                } else {
                    this.mNumFailedChecks++;
                }
                this.mNextCheckTimestamp = Math.min((((long) this.mNumFailedChecks) * 100) + 100, 500L) + j;
            }
            jMax = Math.max(this.mNextCheckTimestamp - j, 0L);
        }
        return jMax;
    }

    private void refreshNetworkState() {
        NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        NetworkInfo.DetailedState detailedState = activeNetworkInfo != null ? activeNetworkInfo.getDetailedState() : null;
        if (detailedState != this.mCurrentState) {
            this.mLogger.log(Logger.Type.INFO, "ConnectivityChecker: Network state changed: %s --> %s", this.mCurrentState, detailedState);
        }
        this.mCurrentState = detailedState;
    }
}
