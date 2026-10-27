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
import android.net.wifi.hotspot2.OsuProvider;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Pair;
import androidx.core.util.Preconditions;
import androidx.lifecycle.Lifecycle;
import java.time.Clock;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes3.dex */
class PasspointNetworkDetailsTracker extends NetworkDetailsTracker {
    private static final String TAG = "PasspointNetworkDetailsTracker";
    private final PasspointWifiEntry mChosenEntry;
    private NetworkInfo mCurrentNetworkInfo;
    private WifiConfiguration mCurrentWifiConfig;
    private OsuWifiEntry mOsuWifiEntry;

    PasspointNetworkDetailsTracker(Lifecycle lifecycle, Context context, WifiManager wifiManager, ConnectivityManager connectivityManager, NetworkScoreManager networkScoreManager, Handler mainHandler, Handler workerHandler, Clock clock, long maxScanAgeMillis, long scanIntervalMillis, final String key) {
        super(lifecycle, context, wifiManager, connectivityManager, networkScoreManager, mainHandler, workerHandler, clock, maxScanAgeMillis, scanIntervalMillis, TAG);
        Optional<PasspointConfiguration> optionalPasspointConfig = this.mWifiManager.getPasspointConfigurations().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$PasspointNetworkDetailsTracker$f6N1P-zciB6IOqUxbZjrv70YfKo
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TextUtils.equals(key, PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(((PasspointConfiguration) obj).getUniqueId()));
            }
        }).findAny();
        if (optionalPasspointConfig.isPresent()) {
            this.mChosenEntry = new PasspointWifiEntry(this.mContext, this.mMainHandler, optionalPasspointConfig.get(), this.mWifiManager, this.mWifiNetworkScoreCache, false);
            return;
        }
        Optional<WifiConfiguration> optionalWifiConfig = this.mWifiManager.getPrivilegedConfiguredNetworks().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$PasspointNetworkDetailsTracker$f3K9fctj5iLndELi8z-SmqMZdkI
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return PasspointNetworkDetailsTracker.lambda$new$1(key, (WifiConfiguration) obj);
            }
        }).findAny();
        if (optionalWifiConfig.isPresent()) {
            this.mChosenEntry = new PasspointWifiEntry(this.mContext, this.mMainHandler, optionalWifiConfig.get(), this.mWifiManager, this.mWifiNetworkScoreCache, false);
            return;
        }
        throw new IllegalArgumentException("Cannot find config for given PasspointWifiEntry key!");
    }

    static /* synthetic */ boolean lambda$new$1(String key, WifiConfiguration wifiConfig) {
        return wifiConfig.isPasspoint() && TextUtils.equals(key, PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(wifiConfig.getKey()));
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
        conditionallyUpdateConfig();
    }

    private void updatePasspointWifiEntryScans(List<ScanResult> scanResults) {
        Preconditions.checkNotNull(scanResults, "Scan Result list should not be null!");
        List<Pair<WifiConfiguration, Map<Integer, List<ScanResult>>>> matchingWifiConfigs = this.mWifiManager.getAllMatchingWifiConfigs(scanResults);
        for (Pair<WifiConfiguration, Map<Integer, List<ScanResult>>> pair : matchingWifiConfigs) {
            WifiConfiguration wifiConfig = (WifiConfiguration) pair.first;
            String key = PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(wifiConfig.getKey());
            if (TextUtils.equals(key, this.mChosenEntry.getKey())) {
                this.mCurrentWifiConfig = wifiConfig;
                this.mChosenEntry.updateScanResultInfo(wifiConfig, (List) ((Map) pair.second).get(0), (List) ((Map) pair.second).get(1));
                return;
            }
        }
        this.mChosenEntry.updateScanResultInfo(this.mCurrentWifiConfig, null, null);
    }

    private void updateOsuWifiEntryScans(List<ScanResult> scanResults) {
        Preconditions.checkNotNull(scanResults, "Scan Result list should not be null!");
        Map<OsuProvider, List<ScanResult>> osuProviderToScans = this.mWifiManager.getMatchingOsuProviders(scanResults);
        Map<OsuProvider, PasspointConfiguration> osuProviderToPasspointConfig = this.mWifiManager.getMatchingPasspointConfigsForOsuProviders(osuProviderToScans.keySet());
        OsuWifiEntry osuWifiEntry = this.mOsuWifiEntry;
        if (osuWifiEntry != null) {
            osuWifiEntry.updateScanResultInfo(osuProviderToScans.get(osuWifiEntry.getOsuProvider()));
        } else {
            for (OsuProvider provider : osuProviderToScans.keySet()) {
                PasspointConfiguration provisionedConfig = osuProviderToPasspointConfig.get(provider);
                if (provisionedConfig != null && TextUtils.equals(this.mChosenEntry.getKey(), PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(provisionedConfig.getUniqueId()))) {
                    OsuWifiEntry osuWifiEntry2 = new OsuWifiEntry(this.mContext, this.mMainHandler, provider, this.mWifiManager, this.mWifiNetworkScoreCache, false);
                    this.mOsuWifiEntry = osuWifiEntry2;
                    osuWifiEntry2.updateScanResultInfo(osuProviderToScans.get(provider));
                    this.mOsuWifiEntry.setAlreadyProvisioned(true);
                    this.mChosenEntry.setOsuWifiEntry(this.mOsuWifiEntry);
                    return;
                }
            }
        }
        OsuWifiEntry osuWifiEntry3 = this.mOsuWifiEntry;
        if (osuWifiEntry3 != null && osuWifiEntry3.getLevel() == -1) {
            this.mChosenEntry.setOsuWifiEntry(null);
            this.mOsuWifiEntry = null;
        }
    }

    private void conditionallyUpdateScanResults(boolean lastScanSucceeded) {
        if (this.mWifiManager.getWifiState() == 1) {
            this.mChosenEntry.updateScanResultInfo(this.mCurrentWifiConfig, null, null);
            return;
        }
        long scanAgeWindow = this.mMaxScanAgeMillis;
        if (lastScanSucceeded) {
            cacheNewScanResults();
        } else {
            scanAgeWindow += this.mScanIntervalMillis;
        }
        List<ScanResult> currentScans = this.mScanResultUpdater.getScanResults(scanAgeWindow);
        updatePasspointWifiEntryScans(currentScans);
        updateOsuWifiEntryScans(currentScans);
    }

    private void conditionallyUpdateConfig() {
        this.mWifiManager.getPasspointConfigurations().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$PasspointNetworkDetailsTracker$0W7xlLKDDotyH98hU3W2TjOfq8c
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$conditionallyUpdateConfig$2$PasspointNetworkDetailsTracker((PasspointConfiguration) obj);
            }
        }).findAny().ifPresent(new Consumer() { // from class: com.android.wifitrackerlib.-$$Lambda$PasspointNetworkDetailsTracker$GW4OvaOI_VBi99-1zq1zNrs27hE
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$conditionallyUpdateConfig$3$PasspointNetworkDetailsTracker((PasspointConfiguration) obj);
            }
        });
    }

    public /* synthetic */ boolean lambda$conditionallyUpdateConfig$2$PasspointNetworkDetailsTracker(PasspointConfiguration config) {
        return TextUtils.equals(PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(config.getUniqueId()), this.mChosenEntry.getKey());
    }

    public /* synthetic */ void lambda$conditionallyUpdateConfig$3$PasspointNetworkDetailsTracker(PasspointConfiguration config) {
        this.mChosenEntry.updatePasspointConfig(config);
    }

    private void cacheNewScanResults() {
        this.mScanResultUpdater.update(this.mWifiManager.getScanResults());
    }
}
