package com.amazon.avod.media.framework.platform.power;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.PowerManager;
import com.amazon.avod.media.framework.platform.Handlers;
import com.amazon.avod.media.framework.platform.power.PowerManagementLockFactory;
import com.amazon.avod.util.DLog;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidLockFactory implements PowerManagementLockFactory {
    static final PowerManagementLockFactory.PowerManagementLock NOOP_POWER_LOCK = new PowerManagementLockFactory.PowerManagementLock() { // from class: com.amazon.avod.media.framework.platform.power.AndroidLockFactory.1
        @Override // com.amazon.avod.media.framework.platform.power.PowerManagementLockFactory.PowerManagementLock
        public void acquire() {
        }

        @Override // com.amazon.avod.media.framework.platform.power.PowerManagementLockFactory.PowerManagementLock
        public void release() {
        }
    };
    private final PowerManager mPowerManager;
    private final Handler mUIHandler = Handlers.getUIHandler();
    private final WifiManager mWifiManager;

    public AndroidLockFactory(Context context) {
        this.mPowerManager = (PowerManager) context.getSystemService("power");
        this.mWifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
    }

    @Override // com.amazon.avod.media.framework.platform.power.PowerManagementLockFactory
    public PowerManagementLockFactory.PowerManagementLock newWifiLock(String str) {
        if (this.mWifiManager == null) {
            DLog.errorf("Couldn't get reference to WifiManager - returning no-op lock");
            return NOOP_POWER_LOCK;
        }
        String str2 = str + "_HighPerfMode";
        return new AndroidWifiLockAdapter(this.mWifiManager.createWifiLock(3, str2), str2);
    }

    private class AndroidWifiLockAdapter implements PowerManagementLockFactory.PowerManagementLock {
        private final String mName;
        private final WifiManager.WifiLock mWifiLock;

        public AndroidWifiLockAdapter(WifiManager.WifiLock wifiLock, String str) {
            this.mWifiLock = wifiLock;
            this.mName = str;
        }

        @Override // com.amazon.avod.media.framework.platform.power.PowerManagementLockFactory.PowerManagementLock
        public void acquire() {
            AndroidLockFactory.this.mUIHandler.post(new Runnable() { // from class: com.amazon.avod.media.framework.platform.power.AndroidLockFactory.AndroidWifiLockAdapter.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (AndroidWifiLockAdapter.this.mWifiLock) {
                        if (!AndroidWifiLockAdapter.this.mWifiLock.isHeld()) {
                            DLog.logf("Acquiring wifi lock %s (%s)", AndroidWifiLockAdapter.this.mName, AndroidWifiLockAdapter.this.mWifiLock);
                            AndroidWifiLockAdapter.this.mWifiLock.acquire();
                        }
                    }
                }
            });
        }

        @Override // com.amazon.avod.media.framework.platform.power.PowerManagementLockFactory.PowerManagementLock
        public void release() {
            AndroidLockFactory.this.mUIHandler.post(new Runnable() { // from class: com.amazon.avod.media.framework.platform.power.AndroidLockFactory.AndroidWifiLockAdapter.2
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (AndroidWifiLockAdapter.this.mWifiLock) {
                        if (AndroidWifiLockAdapter.this.mWifiLock.isHeld()) {
                            DLog.logf("Releasing wifi lock %s (%s)", AndroidWifiLockAdapter.this.mName, AndroidWifiLockAdapter.this.mWifiLock);
                            AndroidWifiLockAdapter.this.mWifiLock.release();
                        }
                    }
                }
            });
        }
    }
}
