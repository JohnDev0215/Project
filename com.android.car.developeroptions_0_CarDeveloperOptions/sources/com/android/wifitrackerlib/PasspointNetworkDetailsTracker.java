package com.android.wifitrackerlib;

import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.hotspot2.OsuProvider;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.text.TextUtils;
import android.util.Pair;
import androidx.core.util.Preconditions;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
class PasspointNetworkDetailsTracker extends NetworkDetailsTracker {
    private final PasspointWifiEntry mChosenEntry;
    private NetworkInfo mCurrentNetworkInfo;
    private WifiConfiguration mCurrentWifiConfig;
    private OsuWifiEntry mOsuWifiEntry;

    @Override // com.android.wifitrackerlib.NetworkDetailsTracker
    public WifiEntry getWifiEntry() {
        return this.mChosenEntry;
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleOnStart() {
        conditionallyUpdateScanResults(true);
        conditionallyUpdateConfig();
        WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
        Network currentNetwork = this.mWifiManager.getCurrentNetwork();
        NetworkInfo networkInfo = this.mConnectivityManager.getNetworkInfo(currentNetwork);
        this.mCurrentNetworkInfo = networkInfo;
        this.mChosenEntry.updateConnectionInfo(connectionInfo, networkInfo);
        handleNetworkCapabilitiesChanged(this.mConnectivityManager.getNetworkCapabilities(currentNetwork));
        handleLinkPropertiesChanged(this.mConnectivityManager.getLinkProperties(currentNetwork));
        this.mChosenEntry.setIsDefaultNetwork(this.mIsWifiDefaultRoute);
        this.mChosenEntry.setIsLowQuality(this.mIsWifiValidated && this.mIsCellDefaultRoute);
    }

    private void updatePasspointWifiEntryScans(List<ScanResult> list) {
        Preconditions.checkNotNull(list, "Scan Result list should not be null!");
        for (Pair pair : this.mWifiManager.getAllMatchingWifiConfigs(list)) {
            WifiConfiguration wifiConfiguration = (WifiConfiguration) pair.first;
            if (TextUtils.equals(PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(wifiConfiguration.getKey()), this.mChosenEntry.getKey())) {
                this.mCurrentWifiConfig = wifiConfiguration;
                this.mChosenEntry.updateScanResultInfo(wifiConfiguration, (List) ((Map) pair.second).get(0), (List) ((Map) pair.second).get(1));
                return;
            }
        }
        this.mChosenEntry.updateScanResultInfo(this.mCurrentWifiConfig, null, null);
    }

    private void updateOsuWifiEntryScans(List<ScanResult> list) {
        Preconditions.checkNotNull(list, "Scan Result list should not be null!");
        Map matchingOsuProviders = this.mWifiManager.getMatchingOsuProviders(list);
        Map matchingPasspointConfigsForOsuProviders = this.mWifiManager.getMatchingPasspointConfigsForOsuProviders(matchingOsuProviders.keySet());
        OsuWifiEntry osuWifiEntry = this.mOsuWifiEntry;
        if (osuWifiEntry != null) {
            osuWifiEntry.updateScanResultInfo((List) matchingOsuProviders.get(osuWifiEntry.getOsuProvider()));
        } else {
            for (OsuProvider osuProvider : matchingOsuProviders.keySet()) {
                PasspointConfiguration passpointConfiguration = (PasspointConfiguration) matchingPasspointConfigsForOsuProviders.get(osuProvider);
                if (passpointConfiguration != null && TextUtils.equals(this.mChosenEntry.getKey(), PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(passpointConfiguration.getUniqueId()))) {
                    OsuWifiEntry osuWifiEntry2 = new OsuWifiEntry(this.mContext, this.mMainHandler, osuProvider, this.mWifiManager, this.mWifiNetworkScoreCache, false);
                    this.mOsuWifiEntry = osuWifiEntry2;
                    osuWifiEntry2.updateScanResultInfo((List) matchingOsuProviders.get(osuProvider));
                    this.mOsuWifiEntry.setAlreadyProvisioned(true);
                    this.mChosenEntry.setOsuWifiEntry(this.mOsuWifiEntry);
                    return;
                }
            }
        }
        OsuWifiEntry osuWifiEntry3 = this.mOsuWifiEntry;
        if (osuWifiEntry3 == null || osuWifiEntry3.getLevel() != -1) {
            return;
        }
        this.mChosenEntry.setOsuWifiEntry(null);
        this.mOsuWifiEntry = null;
    }

    private void conditionallyUpdateScanResults(boolean z) {
        if (this.mWifiManager.getWifiState() == 1) {
            this.mChosenEntry.updateScanResultInfo(this.mCurrentWifiConfig, null, null);
            return;
        }
        long j = this.mMaxScanAgeMillis;
        if (z) {
            cacheNewScanResults();
        } else {
            j += this.mScanIntervalMillis;
        }
        List<ScanResult> scanResults = this.mScanResultUpdater.getScanResults(j);
        updatePasspointWifiEntryScans(scanResults);
        updateOsuWifiEntryScans(scanResults);
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

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$conditionallyUpdateConfig$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$conditionallyUpdateConfig$2$PasspointNetworkDetailsTracker(PasspointConfiguration passpointConfiguration) {
        return TextUtils.equals(PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(passpointConfiguration.getUniqueId()), this.mChosenEntry.getKey());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$conditionallyUpdateConfig$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$conditionallyUpdateConfig$3$PasspointNetworkDetailsTracker(PasspointConfiguration passpointConfiguration) {
        this.mChosenEntry.updatePasspointConfig(passpointConfiguration);
    }

    private void cacheNewScanResults() {
        this.mScanResultUpdater.update(this.mWifiManager.getScanResults());
    }
}
