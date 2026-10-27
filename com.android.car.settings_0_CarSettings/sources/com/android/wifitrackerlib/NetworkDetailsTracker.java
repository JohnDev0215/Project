package com.android.wifitrackerlib;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkScoreManager;
import android.net.wifi.WifiManager;
import android.os.Handler;
import androidx.core.util.Preconditions;
import androidx.lifecycle.Lifecycle;
import java.time.Clock;

/* JADX INFO: loaded from: classes6.dex */
public abstract class NetworkDetailsTracker extends BaseWifiTracker {
    protected NetworkInfo mCurrentNetworkInfo;

    public abstract WifiEntry getWifiEntry();

    public static NetworkDetailsTracker createNetworkDetailsTracker(Lifecycle lifecycle, Context context, WifiManager wifiManager, ConnectivityManager connectivityManager, NetworkScoreManager networkScoreManager, Handler mainHandler, Handler workerHandler, Clock clock, long maxScanAgeMillis, long scanIntervalMillis, String key) {
        if (!key.startsWith("StandardWifiEntry:") && !key.startsWith("NetworkRequestEntry:")) {
            if (key.startsWith("PasspointWifiEntry:")) {
                return new PasspointNetworkDetailsTracker(lifecycle, context, wifiManager, connectivityManager, networkScoreManager, mainHandler, workerHandler, clock, maxScanAgeMillis, scanIntervalMillis, key);
            }
            throw new IllegalArgumentException("Key does not contain valid key prefix!");
        }
        return new StandardNetworkDetailsTracker(lifecycle, context, wifiManager, connectivityManager, networkScoreManager, mainHandler, workerHandler, clock, maxScanAgeMillis, scanIntervalMillis, key);
    }

    NetworkDetailsTracker(Lifecycle lifecycle, Context context, WifiManager wifiManager, ConnectivityManager connectivityManager, NetworkScoreManager networkScoreManager, Handler mainHandler, Handler workerHandler, Clock clock, long maxScanAgeMillis, long scanIntervalMillis, String tag) {
        super(lifecycle, context, wifiManager, connectivityManager, networkScoreManager, mainHandler, workerHandler, clock, maxScanAgeMillis, scanIntervalMillis, null, tag);
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleNetworkStateChangedAction(Intent intent) {
        Preconditions.checkNotNull(intent, "Intent cannot be null!");
        this.mCurrentNetworkInfo = (NetworkInfo) intent.getExtra("networkInfo");
        getWifiEntry().updateConnectionInfo(this.mWifiManager.getConnectionInfo(), this.mCurrentNetworkInfo);
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleRssiChangedAction() {
        getWifiEntry().updateConnectionInfo(this.mWifiManager.getConnectionInfo(), this.mCurrentNetworkInfo);
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleLinkPropertiesChanged(LinkProperties linkProperties) {
        WifiEntry chosenEntry = getWifiEntry();
        if (chosenEntry.getConnectedState() == 2) {
            chosenEntry.updateLinkProperties(linkProperties);
        }
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleNetworkCapabilitiesChanged(NetworkCapabilities capabilities) {
        WifiEntry chosenEntry = getWifiEntry();
        if (chosenEntry.getConnectedState() == 2) {
            chosenEntry.updateNetworkCapabilities(capabilities);
            chosenEntry.setIsLowQuality(this.mIsWifiValidated && this.mIsCellDefaultRoute);
        }
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleDefaultRouteChanged() {
        WifiEntry chosenEntry = getWifiEntry();
        if (chosenEntry.getConnectedState() == 2) {
            chosenEntry.setIsDefaultNetwork(this.mIsWifiDefaultRoute);
            chosenEntry.setIsLowQuality(this.mIsWifiValidated && this.mIsCellDefaultRoute);
        }
    }
}
