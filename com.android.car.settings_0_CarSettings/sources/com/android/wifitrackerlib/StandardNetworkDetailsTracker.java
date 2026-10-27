package com.android.wifitrackerlib;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkScoreManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.text.TextUtils;
import androidx.core.util.Preconditions;
import androidx.lifecycle.Lifecycle;
import java.time.Clock;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes6.dex */
class StandardNetworkDetailsTracker extends NetworkDetailsTracker {
    private static final String TAG = "StandardNetworkDetailsTracker";
    private final StandardWifiEntry mChosenEntry;
    private NetworkInfo mCurrentNetworkInfo;
    private final boolean mIsNetworkRequest;

    StandardNetworkDetailsTracker(Lifecycle lifecycle, Context context, WifiManager wifiManager, ConnectivityManager connectivityManager, NetworkScoreManager networkScoreManager, Handler mainHandler, Handler workerHandler, Clock clock, long maxScanAgeMillis, long scanIntervalMillis, String key) {
        super(lifecycle, context, wifiManager, connectivityManager, networkScoreManager, mainHandler, workerHandler, clock, maxScanAgeMillis, scanIntervalMillis, TAG);
        if (key.startsWith("NetworkRequestEntry:")) {
            this.mIsNetworkRequest = true;
            this.mChosenEntry = new NetworkRequestEntry(this.mContext, this.mMainHandler, key, this.mWifiManager, this.mWifiNetworkScoreCache, false);
        } else {
            this.mIsNetworkRequest = false;
            this.mChosenEntry = new StandardWifiEntry(this.mContext, this.mMainHandler, key, this.mWifiManager, this.mWifiNetworkScoreCache, false);
        }
    }

    @Override // com.android.wifitrackerlib.NetworkDetailsTracker
    public WifiEntry getWifiEntry() {
        return this.mChosenEntry;
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleOnStart() {
        conditionallyUpdateScanResults(true);
        conditionallyUpdateConfig();
        WifiInfo wifiInfo = this.mWifiManager.getConnectionInfo();
        Network currentNetwork = this.mWifiManager.getCurrentNetwork();
        NetworkInfo networkInfo = this.mConnectivityManager.getNetworkInfo(currentNetwork);
        this.mCurrentNetworkInfo = networkInfo;
        this.mChosenEntry.updateConnectionInfo(wifiInfo, networkInfo);
        handleNetworkCapabilitiesChanged(this.mConnectivityManager.getNetworkCapabilities(currentNetwork));
        handleLinkPropertiesChanged(this.mConnectivityManager.getLinkProperties(currentNetwork));
        this.mChosenEntry.setIsDefaultNetwork(this.mIsWifiDefaultRoute);
        this.mChosenEntry.setIsLowQuality(this.mIsWifiValidated && this.mIsCellDefaultRoute);
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleWifiStateChangedAction() {
        conditionallyUpdateScanResults(true);
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleScanResultsAvailableAction(Intent intent) {
        Preconditions.checkNotNull(intent, "Intent cannot be null!");
        conditionallyUpdateScanResults(intent.getBooleanExtra("resultsUpdated", true));
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleConfiguredNetworksChangedAction(Intent intent) {
        Preconditions.checkNotNull(intent, "Intent cannot be null!");
        WifiConfiguration updatedConfig = (WifiConfiguration) intent.getExtra("wifiConfiguration");
        if (updatedConfig != null && configMatches(updatedConfig)) {
            int changeReason = intent.getIntExtra("changeReason", -1);
            if (changeReason == 0 || changeReason == 2) {
                this.mChosenEntry.updateConfig(updatedConfig);
                return;
            } else {
                if (changeReason == 1) {
                    this.mChosenEntry.updateConfig(null);
                    return;
                }
                return;
            }
        }
        conditionallyUpdateConfig();
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleNetworkScoreCacheUpdated() {
        this.mChosenEntry.onScoreCacheUpdated();
    }

    private void conditionallyUpdateScanResults(boolean lastScanSucceeded) {
        if (this.mWifiManager.getWifiState() == 1) {
            this.mChosenEntry.updateScanResultInfo(Collections.emptyList());
            return;
        }
        long scanAgeWindow = this.mMaxScanAgeMillis;
        if (lastScanSucceeded) {
            cacheNewScanResults();
        } else {
            scanAgeWindow += this.mScanIntervalMillis;
        }
        this.mChosenEntry.updateScanResultInfo(this.mScanResultUpdater.getScanResults(scanAgeWindow));
    }

    private void conditionallyUpdateConfig() {
        WifiConfiguration updatedConfig = (WifiConfiguration) this.mWifiManager.getPrivilegedConfiguredNetworks().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$StandardNetworkDetailsTracker$Zbxm60gLtoXTwkhPsrGCaQl4hMc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.configMatches((WifiConfiguration) obj);
            }
        }).findAny().orElse(null);
        this.mChosenEntry.updateConfig(updatedConfig);
    }

    private void cacheNewScanResults() {
        this.mScanResultUpdater.update((List) this.mWifiManager.getScanResults().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$StandardNetworkDetailsTracker$pFn5Xq4o7c3wIEPexiRYP1yHlzY
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$cacheNewScanResults$0$StandardNetworkDetailsTracker((ScanResult) obj);
            }
        }).collect(Collectors.toList()));
    }

    public /* synthetic */ boolean lambda$cacheNewScanResults$0$StandardNetworkDetailsTracker(ScanResult scan) {
        return TextUtils.equals(scan.SSID, this.mChosenEntry.getSsid()) && Utils.getSecurityTypesFromScanResult(scan).contains(Integer.valueOf(this.mChosenEntry.getSecurity()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean configMatches(WifiConfiguration config) {
        String configKey;
        if (config.isPasspoint()) {
            return false;
        }
        if (config.fromWifiNetworkSpecifier) {
            configKey = NetworkRequestEntry.wifiConfigToNetworkRequestEntryKey(config);
        } else {
            configKey = StandardWifiEntry.wifiConfigToStandardWifiEntryKey(config);
        }
        return TextUtils.equals(configKey, this.mChosenEntry.getKey());
    }
}
