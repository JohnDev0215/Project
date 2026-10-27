package com.android.wifitrackerlib;

import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.text.TextUtils;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
class StandardNetworkDetailsTracker extends NetworkDetailsTracker {
    private final StandardWifiEntry mChosenEntry;
    private NetworkInfo mCurrentNetworkInfo;

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

    private void conditionallyUpdateScanResults(boolean z) {
        if (this.mWifiManager.getWifiState() == 1) {
            this.mChosenEntry.updateScanResultInfo(Collections.emptyList());
            return;
        }
        long j = this.mMaxScanAgeMillis;
        if (z) {
            cacheNewScanResults();
        } else {
            j += this.mScanIntervalMillis;
        }
        this.mChosenEntry.updateScanResultInfo(this.mScanResultUpdater.getScanResults(j));
    }

    private void conditionallyUpdateConfig() {
        this.mChosenEntry.updateConfig((WifiConfiguration) this.mWifiManager.getPrivilegedConfiguredNetworks().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$StandardNetworkDetailsTracker$Zbxm60gLtoXTwkhPsrGCaQl4hMc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.configMatches((WifiConfiguration) obj);
            }
        }).findAny().orElse(null));
    }

    private void cacheNewScanResults() {
        this.mScanResultUpdater.update((List) this.mWifiManager.getScanResults().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$StandardNetworkDetailsTracker$pFn5Xq4o7c3wIEPexiRYP1yHlzY
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$cacheNewScanResults$0$StandardNetworkDetailsTracker((ScanResult) obj);
            }
        }).collect(Collectors.toList()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$cacheNewScanResults$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$cacheNewScanResults$0$StandardNetworkDetailsTracker(ScanResult scanResult) {
        return TextUtils.equals(scanResult.SSID, this.mChosenEntry.getSsid()) && Utils.getSecurityTypesFromScanResult(scanResult).contains(Integer.valueOf(this.mChosenEntry.getSecurity()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean configMatches(WifiConfiguration wifiConfiguration) {
        String strWifiConfigToStandardWifiEntryKey;
        if (wifiConfiguration.isPasspoint()) {
            return false;
        }
        if (wifiConfiguration.fromWifiNetworkSpecifier) {
            strWifiConfigToStandardWifiEntryKey = NetworkRequestEntry.wifiConfigToNetworkRequestEntryKey(wifiConfiguration);
        } else {
            strWifiConfigToStandardWifiEntryKey = StandardWifiEntry.wifiConfigToStandardWifiEntryKey(wifiConfiguration);
        }
        return TextUtils.equals(strWifiConfigToStandardWifiEntryKey, this.mChosenEntry.getKey());
    }
}
