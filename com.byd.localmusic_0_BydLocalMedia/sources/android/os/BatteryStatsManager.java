package android.os;

import android.annotation.SystemApi;
import android.os.connectivity.CellularBatteryStats;
import android.os.connectivity.WifiBatteryStats;
import com.android.internal.app.IBatteryStats;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class BatteryStatsManager {
    public static final int NUM_WIFI_STATES = 8;
    public static final int NUM_WIFI_SUPPL_STATES = 13;
    public static final int WIFI_STATE_OFF = 0;
    public static final int WIFI_STATE_OFF_SCANNING = 1;
    public static final int WIFI_STATE_ON_CONNECTED_P2P = 5;
    public static final int WIFI_STATE_ON_CONNECTED_STA = 4;
    public static final int WIFI_STATE_ON_CONNECTED_STA_P2P = 6;
    public static final int WIFI_STATE_ON_DISCONNECTED = 3;
    public static final int WIFI_STATE_ON_NO_NETWORKS = 2;
    public static final int WIFI_STATE_SOFT_AP = 7;
    public static final int WIFI_SUPPL_STATE_ASSOCIATED = 7;
    public static final int WIFI_SUPPL_STATE_ASSOCIATING = 6;
    public static final int WIFI_SUPPL_STATE_AUTHENTICATING = 5;
    public static final int WIFI_SUPPL_STATE_COMPLETED = 10;
    public static final int WIFI_SUPPL_STATE_DISCONNECTED = 1;
    public static final int WIFI_SUPPL_STATE_DORMANT = 11;
    public static final int WIFI_SUPPL_STATE_FOUR_WAY_HANDSHAKE = 8;
    public static final int WIFI_SUPPL_STATE_GROUP_HANDSHAKE = 9;
    public static final int WIFI_SUPPL_STATE_INACTIVE = 3;
    public static final int WIFI_SUPPL_STATE_INTERFACE_DISABLED = 2;
    public static final int WIFI_SUPPL_STATE_INVALID = 0;
    public static final int WIFI_SUPPL_STATE_SCANNING = 4;
    public static final int WIFI_SUPPL_STATE_UNINITIALIZED = 12;
    private final IBatteryStats mBatteryStats;

    @Retention(RetentionPolicy.SOURCE)
    public @interface WifiState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface WifiSupplState {
    }

    public BatteryStatsManager(IBatteryStats iBatteryStats) {
        this.mBatteryStats = iBatteryStats;
    }

    public void reportWifiRssiChanged(int i) {
        try {
            this.mBatteryStats.noteWifiRssiChanged(i);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public void reportWifiOn() {
        try {
            this.mBatteryStats.noteWifiOn();
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public void reportWifiOff() {
        try {
            this.mBatteryStats.noteWifiOff();
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public void reportWifiState(int i, String str) {
        try {
            this.mBatteryStats.noteWifiState(i, str);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public void reportWifiScanStartedFromSource(WorkSource workSource) {
        try {
            this.mBatteryStats.noteWifiScanStartedFromSource(workSource);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public void reportWifiScanStoppedFromSource(WorkSource workSource) {
        try {
            this.mBatteryStats.noteWifiScanStoppedFromSource(workSource);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public void reportWifiBatchedScanStartedFromSource(WorkSource workSource, int i) {
        try {
            this.mBatteryStats.noteWifiBatchedScanStartedFromSource(workSource, i);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public void reportWifiBatchedScanStoppedFromSource(WorkSource workSource) {
        try {
            this.mBatteryStats.noteWifiBatchedScanStoppedFromSource(workSource);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public CellularBatteryStats getCellularBatteryStats() {
        try {
            return this.mBatteryStats.getCellularBatteryStats();
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            return null;
        }
    }

    public WifiBatteryStats getWifiBatteryStats() {
        try {
            return this.mBatteryStats.getWifiBatteryStats();
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            return null;
        }
    }

    public void reportFullWifiLockAcquiredFromSource(WorkSource workSource) {
        try {
            this.mBatteryStats.noteFullWifiLockAcquiredFromSource(workSource);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public void reportFullWifiLockReleasedFromSource(WorkSource workSource) {
        try {
            this.mBatteryStats.noteFullWifiLockReleasedFromSource(workSource);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public void reportWifiSupplicantStateChanged(int i, boolean z) {
        try {
            this.mBatteryStats.noteWifiSupplicantStateChanged(i, z);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public void reportWifiMulticastEnabled(WorkSource workSource) {
        try {
            this.mBatteryStats.noteWifiMulticastEnabled(workSource.getAttributionUid());
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public void reportWifiMulticastDisabled(WorkSource workSource) {
        try {
            this.mBatteryStats.noteWifiMulticastDisabled(workSource.getAttributionUid());
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }
}
