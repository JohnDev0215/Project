package com.amazon.bolthttp.internal.net;

import android.net.wifi.WifiManager;
import com.amazon.bolthttp.BoltConfig;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public final class WifiLockHolder {
    private final Object mLock = new Object();
    private volatile int mLockCount = 0;
    private final WifiManager.WifiLock mWifiLock;
    private final WifiManager mWifiManager;

    public WifiLockHolder(@Nonnull BoltConfig boltConfig) {
        WifiManager wifiManager = ((BoltConfig) Preconditions.checkNotNull(boltConfig, "config")).getWifiManager();
        this.mWifiManager = wifiManager;
        if (wifiManager != null) {
            WifiManager.WifiLock wifiLockCreateWifiLock = wifiManager.createWifiLock(1, "BoltHttp.WifiLockHolder");
            this.mWifiLock = wifiLockCreateWifiLock;
            wifiLockCreateWifiLock.setReferenceCounted(false);
            return;
        }
        this.mWifiLock = null;
    }

    public void acquireLock() {
        if (this.mWifiLock == null) {
            return;
        }
        synchronized (this.mLock) {
            if (this.mLockCount == 0) {
                this.mWifiLock.acquire();
            }
            this.mLockCount++;
        }
    }

    public void releaseLock() {
        if (this.mWifiLock == null) {
            return;
        }
        synchronized (this.mLock) {
            if (this.mLockCount > 0) {
                this.mLockCount--;
            }
            if (this.mLockCount == 0) {
                this.mWifiLock.release();
            }
        }
    }
}
