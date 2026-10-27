package com.android.wifitrackerlib;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
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
import android.util.Log;
import android.util.Pair;
import androidx.core.util.Preconditions;
import androidx.lifecycle.Lifecycle;
import com.android.wifitrackerlib.BaseWifiTracker;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* JADX INFO: loaded from: classes3.dex */
public class WifiPickerTracker extends BaseWifiTracker {
    private static final String TAG = "WifiPickerTracker";
    private WifiEntry mConnectedWifiEntry;
    private NetworkInfo mCurrentNetworkInfo;
    private final WifiPickerTrackerCallback mListener;
    private final Object mLock;
    private NetworkRequestEntry mNetworkRequestEntry;
    private int mNumSavedNetworks;
    private final Map<String, OsuWifiEntry> mOsuWifiEntryCache;
    private final Map<String, PasspointConfiguration> mPasspointConfigCache;
    private final Map<String, PasspointWifiEntry> mPasspointWifiEntryCache;
    private final Map<String, StandardWifiEntry> mStandardWifiEntryCache;
    private final Map<String, WifiConfiguration> mSuggestedConfigCache;
    private final Map<String, StandardWifiEntry> mSuggestedWifiEntryCache;
    private final Map<String, WifiConfiguration> mWifiConfigCache;
    private final List<WifiEntry> mWifiEntries;

    public interface WifiPickerTrackerCallback extends BaseWifiTracker.BaseWifiTrackerCallback {
        void onNumSavedNetworksChanged();

        void onNumSavedSubscriptionsChanged();

        void onWifiEntriesChanged();
    }

    public WifiPickerTracker(Lifecycle lifecycle, Context context, WifiManager wifiManager, ConnectivityManager connectivityManager, NetworkScoreManager networkScoreManager, Handler mainHandler, Handler workerHandler, Clock clock, long maxScanAgeMillis, long scanIntervalMillis, WifiPickerTrackerCallback listener) {
        super(lifecycle, context, wifiManager, connectivityManager, networkScoreManager, mainHandler, workerHandler, clock, maxScanAgeMillis, scanIntervalMillis, listener, TAG);
        this.mLock = new Object();
        this.mWifiEntries = new ArrayList();
        this.mWifiConfigCache = new HashMap();
        this.mSuggestedConfigCache = new HashMap();
        this.mStandardWifiEntryCache = new HashMap();
        this.mSuggestedWifiEntryCache = new HashMap();
        this.mPasspointConfigCache = new HashMap();
        this.mPasspointWifiEntryCache = new HashMap();
        this.mOsuWifiEntryCache = new HashMap();
        this.mListener = listener;
    }

    public WifiEntry getConnectedWifiEntry() {
        return this.mConnectedWifiEntry;
    }

    public List<WifiEntry> getWifiEntries() {
        ArrayList arrayList;
        synchronized (this.mLock) {
            arrayList = new ArrayList(this.mWifiEntries);
        }
        return arrayList;
    }

    public int getNumSavedNetworks() {
        return this.mNumSavedNetworks;
    }

    public int getNumSavedSubscriptions() {
        return this.mPasspointConfigCache.size();
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleOnStart() {
        updateWifiConfigurations(this.mWifiManager.getPrivilegedConfiguredNetworks());
        updatePasspointConfigurations(this.mWifiManager.getPasspointConfigurations());
        this.mScanResultUpdater.update(this.mWifiManager.getScanResults());
        conditionallyUpdateScanResults(true);
        WifiInfo wifiInfo = this.mWifiManager.getConnectionInfo();
        Network currentNetwork = this.mWifiManager.getCurrentNetwork();
        NetworkInfo networkInfo = this.mConnectivityManager.getNetworkInfo(currentNetwork);
        this.mCurrentNetworkInfo = networkInfo;
        updateConnectionInfo(wifiInfo, networkInfo);
        notifyOnNumSavedNetworksChanged();
        notifyOnNumSavedSubscriptionsChanged();
        updateWifiEntries();
        handleNetworkCapabilitiesChanged(this.mConnectivityManager.getNetworkCapabilities(currentNetwork));
        handleLinkPropertiesChanged(this.mConnectivityManager.getLinkProperties(currentNetwork));
        handleDefaultRouteChanged();
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleWifiStateChangedAction() {
        conditionallyUpdateScanResults(true);
        updateWifiEntries();
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleScanResultsAvailableAction(Intent intent) {
        Preconditions.checkNotNull(intent, "Intent cannot be null!");
        conditionallyUpdateScanResults(intent.getBooleanExtra("resultsUpdated", true));
        updateWifiEntries();
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleConfiguredNetworksChangedAction(Intent intent) {
        Preconditions.checkNotNull(intent, "Intent cannot be null!");
        WifiConfiguration config = (WifiConfiguration) intent.getExtra("wifiConfiguration");
        if (config != null && !config.isPasspoint()) {
            updateWifiConfiguration(config, ((Integer) intent.getExtra("changeReason")).intValue());
        } else {
            updateWifiConfigurations(this.mWifiManager.getPrivilegedConfiguredNetworks());
        }
        updatePasspointConfigurations(this.mWifiManager.getPasspointConfigurations());
        List<ScanResult> scanResults = this.mScanResultUpdater.getScanResults();
        updateStandardWifiEntryScans(scanResults);
        updateNetworkRequestEntryScans(scanResults);
        updatePasspointWifiEntryScans(scanResults);
        updateOsuWifiEntryScans(scanResults);
        notifyOnNumSavedNetworksChanged();
        notifyOnNumSavedSubscriptionsChanged();
        updateWifiEntries();
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleNetworkStateChangedAction(Intent intent) {
        Preconditions.checkNotNull(intent, "Intent cannot be null!");
        WifiInfo wifiInfo = this.mWifiManager.getConnectionInfo();
        NetworkInfo networkInfo = (NetworkInfo) intent.getExtra("networkInfo");
        this.mCurrentNetworkInfo = networkInfo;
        updateConnectionInfo(wifiInfo, networkInfo);
        updateWifiEntries();
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleRssiChangedAction() {
        if (this.mConnectedWifiEntry != null) {
            WifiInfo wifiInfo = this.mWifiManager.getConnectionInfo();
            this.mConnectedWifiEntry.updateConnectionInfo(wifiInfo, this.mCurrentNetworkInfo);
        }
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleLinkPropertiesChanged(LinkProperties linkProperties) {
        WifiEntry wifiEntry = this.mConnectedWifiEntry;
        if (wifiEntry != null && wifiEntry.getConnectedState() == 2) {
            this.mConnectedWifiEntry.updateLinkProperties(linkProperties);
        }
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleNetworkCapabilitiesChanged(NetworkCapabilities capabilities) {
        WifiEntry wifiEntry = this.mConnectedWifiEntry;
        if (wifiEntry != null && wifiEntry.getConnectedState() == 2) {
            this.mConnectedWifiEntry.updateNetworkCapabilities(capabilities);
            this.mConnectedWifiEntry.setIsLowQuality(this.mIsWifiValidated && this.mIsCellDefaultRoute);
        }
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleDefaultRouteChanged() {
        WifiEntry wifiEntry = this.mConnectedWifiEntry;
        if (wifiEntry != null) {
            wifiEntry.setIsDefaultNetwork(this.mIsWifiDefaultRoute);
            this.mConnectedWifiEntry.setIsLowQuality(this.mIsWifiValidated && this.mIsCellDefaultRoute);
        }
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleNetworkScoreCacheUpdated() {
        for (StandardWifiEntry entry : this.mStandardWifiEntryCache.values()) {
            entry.onScoreCacheUpdated();
        }
        for (StandardWifiEntry entry2 : this.mSuggestedWifiEntryCache.values()) {
            entry2.onScoreCacheUpdated();
        }
        for (PasspointWifiEntry entry3 : this.mPasspointWifiEntryCache.values()) {
            entry3.onScoreCacheUpdated();
        }
    }

    private void updateWifiEntries() {
        synchronized (this.mLock) {
            StandardWifiEntry standardWifiEntryOrElse = this.mStandardWifiEntryCache.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$0TUEBOvt53oJDxdg7kKxPrqyWlc
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return WifiPickerTracker.lambda$updateWifiEntries$0((StandardWifiEntry) obj);
                }
            }).findAny().orElse(null);
            this.mConnectedWifiEntry = standardWifiEntryOrElse;
            if (standardWifiEntryOrElse == null) {
                this.mConnectedWifiEntry = this.mSuggestedWifiEntryCache.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$qhgVsdlHowIElQHRJK_fI8WN1nM
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return WifiPickerTracker.lambda$updateWifiEntries$1((StandardWifiEntry) obj);
                    }
                }).findAny().orElse(null);
            }
            if (this.mConnectedWifiEntry == null) {
                this.mConnectedWifiEntry = this.mPasspointWifiEntryCache.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$ij2wZKo1q0VBIHIbqkDbWHwhHcA
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return WifiPickerTracker.lambda$updateWifiEntries$2((PasspointWifiEntry) obj);
                    }
                }).findAny().orElse(null);
            }
            if (this.mConnectedWifiEntry == null && this.mNetworkRequestEntry != null && this.mNetworkRequestEntry.getConnectedState() != 0) {
                this.mConnectedWifiEntry = this.mNetworkRequestEntry;
            }
            this.mWifiEntries.clear();
            for (String key : this.mStandardWifiEntryCache.keySet()) {
                if (this.mConnectedWifiEntry == null || !TextUtils.equals(key, this.mConnectedWifiEntry.getKey())) {
                    StandardWifiEntry entry = this.mStandardWifiEntryCache.get(key);
                    StandardWifiEntry suggestedEntry = this.mSuggestedWifiEntryCache.get(key);
                    if (!entry.isSaved() && suggestedEntry != null && suggestedEntry.isUserShareable()) {
                        if (suggestedEntry.getConnectedState() == 0) {
                            this.mWifiEntries.add(suggestedEntry);
                        }
                    } else if (entry.getConnectedState() == 0) {
                        this.mWifiEntries.add(entry);
                    }
                }
            }
            this.mWifiEntries.addAll((Collection) this.mPasspointWifiEntryCache.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$QROaltglkm0qmJQUXPRJYd0Po2c
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return WifiPickerTracker.lambda$updateWifiEntries$3((PasspointWifiEntry) obj);
                }
            }).collect(Collectors.toList()));
            this.mWifiEntries.addAll((Collection) this.mOsuWifiEntryCache.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$RLU9K1ZzG3pjvkYPNdVuba88eBA
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return WifiPickerTracker.lambda$updateWifiEntries$4((OsuWifiEntry) obj);
                }
            }).collect(Collectors.toList()));
            Collections.sort(this.mWifiEntries);
            if (isVerboseLoggingEnabled()) {
                Log.v(TAG, "Connected WifiEntry: " + this.mConnectedWifiEntry);
                Log.v(TAG, "Updated WifiEntries: " + Arrays.toString(this.mWifiEntries.toArray()));
            }
        }
        notifyOnWifiEntriesChanged();
    }

    static /* synthetic */ boolean lambda$updateWifiEntries$0(StandardWifiEntry entry) {
        int connectedState = entry.getConnectedState();
        return connectedState == 2 || connectedState == 1;
    }

    static /* synthetic */ boolean lambda$updateWifiEntries$1(StandardWifiEntry entry) {
        int connectedState = entry.getConnectedState();
        return connectedState == 2 || connectedState == 1;
    }

    static /* synthetic */ boolean lambda$updateWifiEntries$2(PasspointWifiEntry entry) {
        int connectedState = entry.getConnectedState();
        return connectedState == 2 || connectedState == 1;
    }

    static /* synthetic */ boolean lambda$updateWifiEntries$3(PasspointWifiEntry entry) {
        return entry.getConnectedState() == 0;
    }

    static /* synthetic */ boolean lambda$updateWifiEntries$4(OsuWifiEntry entry) {
        return entry.getConnectedState() == 0 && !entry.isAlreadyProvisioned();
    }

    private void updateStandardWifiEntryScans(List<ScanResult> scanResults) {
        Preconditions.checkNotNull(scanResults, "Scan Result list should not be null!");
        final Map<String, List<ScanResult>> scanResultsByKey = Utils.mapScanResultsToKey(scanResults, true, this.mWifiConfigCache, this.mWifiManager.isWpa3SaeSupported(), this.mWifiManager.isWpa3SuiteBSupported(), this.mWifiManager.isEnhancedOpenSupported());
        this.mStandardWifiEntryCache.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$BCwaIQQiP0fvxsNUSnDq9UqTFVQ
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return WifiPickerTracker.lambda$updateStandardWifiEntryScans$5(scanResultsByKey, (Map.Entry) obj);
            }
        });
        for (Map.Entry<String, List<ScanResult>> e : scanResultsByKey.entrySet()) {
            StandardWifiEntry newEntry = new StandardWifiEntry(this.mContext, this.mMainHandler, e.getKey(), e.getValue(), this.mWifiManager, this.mWifiNetworkScoreCache, false);
            newEntry.updateConfig(this.mWifiConfigCache.get(newEntry.getKey()));
            this.mStandardWifiEntryCache.put(newEntry.getKey(), newEntry);
        }
    }

    static /* synthetic */ boolean lambda$updateStandardWifiEntryScans$5(Map scanResultsByKey, Map.Entry e) {
        String key = (String) e.getKey();
        StandardWifiEntry entry = (StandardWifiEntry) e.getValue();
        entry.updateScanResultInfo((List) scanResultsByKey.remove(key));
        return entry.getLevel() == -1;
    }

    private void updateSuggestedWifiEntryScans(List<ScanResult> scanResults) {
        Map<String, WifiConfiguration> userSharedConfigsByKey;
        Preconditions.checkNotNull(scanResults, "Scan Result list should not be null!");
        Map<String, List<ScanResult>> scanResultsByKey = Utils.mapScanResultsToKey(scanResults, true, this.mWifiConfigCache, this.mWifiManager.isWpa3SaeSupported(), this.mWifiManager.isWpa3SuiteBSupported(), this.mWifiManager.isEnhancedOpenSupported());
        Map<String, WifiConfiguration> userSharedConfigsByKey2 = (Map) this.mWifiManager.getWifiConfigForMatchedNetworkSuggestionsSharedWithUser(scanResults).stream().collect(Collectors.toMap($$Lambda$eRhiL3TPu1j8op3nmit378jGeyk.INSTANCE, Function.identity()));
        final Set<String> seenKeys = new TreeSet<>();
        for (String key : userSharedConfigsByKey2.keySet()) {
            seenKeys.add(key);
            if (this.mSuggestedWifiEntryCache.containsKey(key)) {
                userSharedConfigsByKey = userSharedConfigsByKey2;
            } else {
                userSharedConfigsByKey = userSharedConfigsByKey2;
                this.mSuggestedWifiEntryCache.put(key, new StandardWifiEntry(this.mContext, this.mMainHandler, key, userSharedConfigsByKey2.get(key), this.mWifiManager, this.mWifiNetworkScoreCache, false));
            }
            StandardWifiEntry entry = this.mSuggestedWifiEntryCache.get(key);
            entry.setUserShareable(true);
            entry.updateScanResultInfo(scanResultsByKey.get(key));
            userSharedConfigsByKey2 = userSharedConfigsByKey;
        }
        this.mSuggestedWifiEntryCache.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$b19vs3Vj3eXbrjFN5lP9dtI7hsE
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$updateSuggestedWifiEntryScans$6$WifiPickerTracker(seenKeys, (Map.Entry) obj);
            }
        });
    }

    public /* synthetic */ boolean lambda$updateSuggestedWifiEntryScans$6$WifiPickerTracker(Set seenKeys, Map.Entry entry) {
        StandardWifiEntry wifiEntry = (StandardWifiEntry) entry.getValue();
        String key = (String) entry.getKey();
        if (!seenKeys.contains(key)) {
            wifiEntry.updateConfig(this.mSuggestedConfigCache.get(key));
            wifiEntry.setUserShareable(false);
        }
        return !wifiEntry.isSuggestion();
    }

    private void updatePasspointWifiEntryScans(List<ScanResult> scanResults) {
        List<Pair<WifiConfiguration, Map<Integer, List<ScanResult>>>> matchingWifiConfigs;
        Iterator<Pair<WifiConfiguration, Map<Integer, List<ScanResult>>>> it;
        String key;
        Preconditions.checkNotNull(scanResults, "Scan Result list should not be null!");
        final Set<String> seenKeys = new TreeSet<>();
        List<Pair<WifiConfiguration, Map<Integer, List<ScanResult>>>> matchingWifiConfigs2 = this.mWifiManager.getAllMatchingWifiConfigs(scanResults);
        Iterator<Pair<WifiConfiguration, Map<Integer, List<ScanResult>>>> it2 = matchingWifiConfigs2.iterator();
        while (it2.hasNext()) {
            Pair<WifiConfiguration, Map<Integer, List<ScanResult>>> pair = it2.next();
            WifiConfiguration wifiConfig = (WifiConfiguration) pair.first;
            List<ScanResult> homeScans = (List) ((Map) pair.second).get(0);
            List<ScanResult> roamingScans = (List) ((Map) pair.second).get(1);
            String key2 = PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(wifiConfig.getKey());
            seenKeys.add(key2);
            if (this.mPasspointWifiEntryCache.containsKey(key2)) {
                matchingWifiConfigs = matchingWifiConfigs2;
                it = it2;
                key = key2;
            } else if (wifiConfig.fromWifiNetworkSuggestion) {
                matchingWifiConfigs = matchingWifiConfigs2;
                key = key2;
                this.mPasspointWifiEntryCache.put(key, new PasspointWifiEntry(this.mContext, this.mMainHandler, wifiConfig, this.mWifiManager, this.mWifiNetworkScoreCache, false));
                it = it2;
            } else {
                matchingWifiConfigs = matchingWifiConfigs2;
                key = key2;
                if (this.mPasspointConfigCache.containsKey(key)) {
                    it = it2;
                    this.mPasspointWifiEntryCache.put(key, new PasspointWifiEntry(this.mContext, this.mMainHandler, this.mPasspointConfigCache.get(key), this.mWifiManager, this.mWifiNetworkScoreCache, false));
                } else {
                    matchingWifiConfigs2 = matchingWifiConfigs;
                }
            }
            this.mPasspointWifiEntryCache.get(key).updateScanResultInfo(wifiConfig, homeScans, roamingScans);
            matchingWifiConfigs2 = matchingWifiConfigs;
            it2 = it;
        }
        this.mPasspointWifiEntryCache.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$b5PKKEVNG30K9l5cXQHsuoupJmU
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return WifiPickerTracker.lambda$updatePasspointWifiEntryScans$7(seenKeys, (Map.Entry) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$updatePasspointWifiEntryScans$7(Set seenKeys, Map.Entry entry) {
        return ((PasspointWifiEntry) entry.getValue()).getLevel() == -1 || (!seenKeys.contains(entry.getKey()) && ((PasspointWifiEntry) entry.getValue()).getConnectedState() == 0);
    }

    private void updateOsuWifiEntryScans(List<ScanResult> scanResults) {
        Preconditions.checkNotNull(scanResults, "Scan Result list should not be null!");
        Map<OsuProvider, List<ScanResult>> osuProviderToScans = this.mWifiManager.getMatchingOsuProviders(scanResults);
        final Map<OsuProvider, PasspointConfiguration> osuProviderToPasspointConfig = this.mWifiManager.getMatchingPasspointConfigsForOsuProviders(osuProviderToScans.keySet());
        for (OsuWifiEntry entry : this.mOsuWifiEntryCache.values()) {
            entry.updateScanResultInfo(osuProviderToScans.remove(entry.getOsuProvider()));
        }
        for (OsuProvider provider : osuProviderToScans.keySet()) {
            OsuWifiEntry newEntry = new OsuWifiEntry(this.mContext, this.mMainHandler, provider, this.mWifiManager, this.mWifiNetworkScoreCache, false);
            newEntry.updateScanResultInfo(osuProviderToScans.get(provider));
            this.mOsuWifiEntryCache.put(OsuWifiEntry.osuProviderToOsuWifiEntryKey(provider), newEntry);
        }
        this.mOsuWifiEntryCache.values().forEach(new Consumer() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$v1LdO2tPR3JJiIf1xFFNAIzpUTI
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$updateOsuWifiEntryScans$8$WifiPickerTracker(osuProviderToPasspointConfig, (OsuWifiEntry) obj);
            }
        });
        this.mOsuWifiEntryCache.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$ZvrS1GLiBdiVh6rqhik8skRXPAw
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return WifiPickerTracker.lambda$updateOsuWifiEntryScans$9((Map.Entry) obj);
            }
        });
    }

    public /* synthetic */ void lambda$updateOsuWifiEntryScans$8$WifiPickerTracker(Map osuProviderToPasspointConfig, OsuWifiEntry osuEntry) {
        PasspointConfiguration provisionedConfig = (PasspointConfiguration) osuProviderToPasspointConfig.get(osuEntry.getOsuProvider());
        if (provisionedConfig == null) {
            osuEntry.setAlreadyProvisioned(false);
            return;
        }
        osuEntry.setAlreadyProvisioned(true);
        PasspointWifiEntry provisionedEntry = this.mPasspointWifiEntryCache.get(PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(provisionedConfig.getUniqueId()));
        if (provisionedEntry == null) {
            return;
        }
        provisionedEntry.setOsuWifiEntry(osuEntry);
    }

    static /* synthetic */ boolean lambda$updateOsuWifiEntryScans$9(Map.Entry entry) {
        return ((OsuWifiEntry) entry.getValue()).getLevel() == -1;
    }

    private void updateNetworkRequestEntryScans(List<ScanResult> scanResults) {
        Preconditions.checkNotNull(scanResults, "Scan Result list should not be null!");
        NetworkRequestEntry networkRequestEntry = this.mNetworkRequestEntry;
        if (networkRequestEntry == null) {
            return;
        }
        final String ssid = networkRequestEntry.getSsid();
        final int security = this.mNetworkRequestEntry.getSecurity();
        List<ScanResult> matchedScans = (List) scanResults.stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$pqxDzYwc6dZqh70kHuMaEBXjaV4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return WifiPickerTracker.lambda$updateNetworkRequestEntryScans$10(ssid, security, (ScanResult) obj);
            }
        }).collect(Collectors.toList());
        this.mNetworkRequestEntry.updateScanResultInfo(matchedScans);
    }

    static /* synthetic */ boolean lambda$updateNetworkRequestEntryScans$10(String ssid, int security, ScanResult scan) {
        return TextUtils.equals(scan.SSID, ssid) && Utils.getSecurityTypesFromScanResult(scan).contains(Integer.valueOf(security));
    }

    private void conditionallyUpdateScanResults(boolean lastScanSucceeded) {
        if (this.mWifiManager.getWifiState() == 1) {
            updateStandardWifiEntryScans(Collections.emptyList());
            updateSuggestedWifiEntryScans(Collections.emptyList());
            updatePasspointWifiEntryScans(Collections.emptyList());
            updateOsuWifiEntryScans(Collections.emptyList());
            updateNetworkRequestEntryScans(Collections.emptyList());
            return;
        }
        long scanAgeWindow = this.mMaxScanAgeMillis;
        if (lastScanSucceeded) {
            this.mScanResultUpdater.update(this.mWifiManager.getScanResults());
        } else {
            scanAgeWindow += this.mScanIntervalMillis;
        }
        List<ScanResult> scanResults = this.mScanResultUpdater.getScanResults(scanAgeWindow);
        updateStandardWifiEntryScans(scanResults);
        updateSuggestedWifiEntryScans(scanResults);
        updatePasspointWifiEntryScans(scanResults);
        updateOsuWifiEntryScans(scanResults);
        updateNetworkRequestEntryScans(scanResults);
    }

    private void updateWifiConfiguration(WifiConfiguration config, int changeReason) {
        WifiConfiguration updatedConfig;
        StandardWifiEntry updatedEntry;
        Preconditions.checkNotNull(config, "Config should not be null!");
        if (config.fromWifiNetworkSpecifier) {
            if (changeReason == 1) {
                updateNetworkRequestConfig(null);
                return;
            } else {
                updateNetworkRequestConfig(config);
                return;
            }
        }
        String key = StandardWifiEntry.wifiConfigToStandardWifiEntryKey(config);
        if (config.fromWifiNetworkSuggestion) {
            if (changeReason == 1) {
                this.mSuggestedConfigCache.remove(key);
            } else {
                this.mSuggestedConfigCache.put(key, config);
            }
            updatedConfig = this.mSuggestedConfigCache.get(key);
            updatedEntry = this.mSuggestedWifiEntryCache.get(key);
        } else {
            if (changeReason == 1) {
                this.mWifiConfigCache.remove(key);
            } else {
                this.mWifiConfigCache.put(key, config);
            }
            updatedConfig = this.mWifiConfigCache.get(key);
            updatedEntry = this.mStandardWifiEntryCache.get(key);
            this.mNumSavedNetworks = (int) this.mWifiConfigCache.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$VCBQWp0Yd-_9TYKb9qTFEq0hUb8
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return WifiPickerTracker.lambda$updateWifiConfiguration$11((WifiConfiguration) obj);
                }
            }).count();
        }
        if (updatedEntry != null) {
            updatedEntry.updateConfig(updatedConfig);
        }
    }

    static /* synthetic */ boolean lambda$updateWifiConfiguration$11(WifiConfiguration cachedConfig) {
        return (cachedConfig.isEphemeral() || cachedConfig.isPasspoint()) ? false : true;
    }

    private void updateWifiConfigurations(List<WifiConfiguration> configs) {
        Preconditions.checkNotNull(configs, "Config list should not be null!");
        this.mWifiConfigCache.clear();
        this.mSuggestedConfigCache.clear();
        boolean networkRequestConfigAvailable = false;
        for (WifiConfiguration config : configs) {
            if (config.fromWifiNetworkSuggestion) {
                this.mSuggestedConfigCache.put(StandardWifiEntry.wifiConfigToStandardWifiEntryKey(config), config);
            } else if (config.fromWifiNetworkSpecifier) {
                networkRequestConfigAvailable = true;
                updateNetworkRequestConfig(config);
            } else {
                this.mWifiConfigCache.put(StandardWifiEntry.wifiConfigToStandardWifiEntryKey(config), config);
            }
        }
        if (!networkRequestConfigAvailable) {
            updateNetworkRequestConfig(null);
        }
        this.mNumSavedNetworks = (int) this.mWifiConfigCache.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$Ge-PaFPqD_aenaiEba32ZGYPo9E
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return WifiPickerTracker.lambda$updateWifiConfigurations$12((WifiConfiguration) obj);
            }
        }).count();
        this.mStandardWifiEntryCache.entrySet().forEach(new Consumer() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$nziraTiXnLOckrKYTwmswRtPvWo
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$updateWifiConfigurations$13$WifiPickerTracker((Map.Entry) obj);
            }
        });
        this.mSuggestedWifiEntryCache.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$n1MQJLze79Jma9-HXue3y6-3vJ8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$updateWifiConfigurations$14$WifiPickerTracker((Map.Entry) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$updateWifiConfigurations$12(WifiConfiguration cachedConfig) {
        return (cachedConfig.isEphemeral() || cachedConfig.isPasspoint()) ? false : true;
    }

    public /* synthetic */ void lambda$updateWifiConfigurations$13$WifiPickerTracker(Map.Entry entry) {
        StandardWifiEntry wifiEntry = (StandardWifiEntry) entry.getValue();
        String key = wifiEntry.getKey();
        WifiConfiguration config = this.mWifiConfigCache.get(key);
        if (config != null && config.isPasspoint()) {
            return;
        }
        wifiEntry.updateConfig(config);
    }

    public /* synthetic */ boolean lambda$updateWifiConfigurations$14$WifiPickerTracker(Map.Entry entry) {
        StandardWifiEntry wifiEntry = (StandardWifiEntry) entry.getValue();
        String key = wifiEntry.getKey();
        WifiConfiguration config = this.mSuggestedConfigCache.get(key);
        if (config != null && !config.isPasspoint()) {
            wifiEntry.updateConfig(config);
            return false;
        }
        return true;
    }

    private void updateNetworkRequestConfig(WifiConfiguration config) {
        if (config == null) {
            this.mNetworkRequestEntry = null;
            return;
        }
        String configKey = NetworkRequestEntry.wifiConfigToNetworkRequestEntryKey(config);
        NetworkRequestEntry networkRequestEntry = this.mNetworkRequestEntry;
        if (networkRequestEntry == null || !TextUtils.equals(configKey, networkRequestEntry.getKey())) {
            this.mNetworkRequestEntry = new NetworkRequestEntry(this.mContext, this.mMainHandler, configKey, this.mWifiManager, this.mWifiNetworkScoreCache, false);
        }
        this.mNetworkRequestEntry.updateConfig(config);
    }

    private void updatePasspointConfigurations(List<PasspointConfiguration> configs) {
        Preconditions.checkNotNull(configs, "Config list should not be null!");
        this.mPasspointConfigCache.clear();
        this.mPasspointConfigCache.putAll((Map) configs.stream().collect(Collectors.toMap(new Function() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$NZj8llmsS2xh549r2-eluZN8xzY
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(((PasspointConfiguration) obj).getUniqueId());
            }
        }, Function.identity())));
        this.mPasspointWifiEntryCache.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$pk3mnes5HX5A_t5O7BAlvARDDy4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$updatePasspointConfigurations$16$WifiPickerTracker((Map.Entry) obj);
            }
        });
    }

    public /* synthetic */ boolean lambda$updatePasspointConfigurations$16$WifiPickerTracker(Map.Entry entry) {
        PasspointWifiEntry wifiEntry = (PasspointWifiEntry) entry.getValue();
        String key = wifiEntry.getKey();
        wifiEntry.updatePasspointConfig(this.mPasspointConfigCache.get(key));
        return (wifiEntry.isSubscription() || wifiEntry.isSuggestion()) ? false : true;
    }

    private void updateConnectionInfo(WifiInfo wifiInfo, NetworkInfo networkInfo) {
        for (WifiEntry entry : this.mStandardWifiEntryCache.values()) {
            entry.updateConnectionInfo(wifiInfo, networkInfo);
        }
        for (WifiEntry entry2 : this.mSuggestedWifiEntryCache.values()) {
            entry2.updateConnectionInfo(wifiInfo, networkInfo);
        }
        for (WifiEntry entry3 : this.mPasspointWifiEntryCache.values()) {
            entry3.updateConnectionInfo(wifiInfo, networkInfo);
        }
        for (WifiEntry entry4 : this.mOsuWifiEntryCache.values()) {
            entry4.updateConnectionInfo(wifiInfo, networkInfo);
        }
        NetworkRequestEntry networkRequestEntry = this.mNetworkRequestEntry;
        if (networkRequestEntry != null) {
            networkRequestEntry.updateConnectionInfo(wifiInfo, networkInfo);
        }
        conditionallyCreateConnectedStandardWifiEntry(wifiInfo, networkInfo);
        conditionallyCreateConnectedSuggestedWifiEntry(wifiInfo, networkInfo);
        conditionallyCreateConnectedPasspointWifiEntry(wifiInfo, networkInfo);
    }

    private void conditionallyCreateConnectedStandardWifiEntry(final WifiInfo wifiInfo, final NetworkInfo networkInfo) {
        if (wifiInfo.isPasspointAp() || wifiInfo.isOsuAp()) {
            return;
        }
        final int connectedNetId = wifiInfo.getNetworkId();
        this.mWifiConfigCache.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$DitgtO9_1wALYexVACURnNqwLlI
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$conditionallyCreateConnectedStandardWifiEntry$17$WifiPickerTracker(connectedNetId, (WifiConfiguration) obj);
            }
        }).findAny().ifPresent(new Consumer() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$PEwbf5ZKF_Aj2ns46UMv4q1HGVo
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$conditionallyCreateConnectedStandardWifiEntry$18$WifiPickerTracker(wifiInfo, networkInfo, (WifiConfiguration) obj);
            }
        });
    }

    public /* synthetic */ boolean lambda$conditionallyCreateConnectedStandardWifiEntry$17$WifiPickerTracker(int connectedNetId, WifiConfiguration config) {
        return config.networkId == connectedNetId && !this.mStandardWifiEntryCache.containsKey(StandardWifiEntry.wifiConfigToStandardWifiEntryKey(config));
    }

    public /* synthetic */ void lambda$conditionallyCreateConnectedStandardWifiEntry$18$WifiPickerTracker(WifiInfo wifiInfo, NetworkInfo networkInfo, WifiConfiguration config) {
        StandardWifiEntry connectedEntry = new StandardWifiEntry(this.mContext, this.mMainHandler, StandardWifiEntry.wifiConfigToStandardWifiEntryKey(config), config, this.mWifiManager, this.mWifiNetworkScoreCache, false);
        connectedEntry.updateConnectionInfo(wifiInfo, networkInfo);
        this.mStandardWifiEntryCache.put(connectedEntry.getKey(), connectedEntry);
    }

    private void conditionallyCreateConnectedSuggestedWifiEntry(final WifiInfo wifiInfo, final NetworkInfo networkInfo) {
        if (wifiInfo.isPasspointAp() || wifiInfo.isOsuAp()) {
            return;
        }
        final int connectedNetId = wifiInfo.getNetworkId();
        this.mSuggestedConfigCache.values().stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$tJL8mW_AZGayOGupUDCgP3_UlzQ
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$conditionallyCreateConnectedSuggestedWifiEntry$19$WifiPickerTracker(connectedNetId, (WifiConfiguration) obj);
            }
        }).findAny().ifPresent(new Consumer() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$BFLWShdPM2NcgUJrV0IHcg4MXWc
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$conditionallyCreateConnectedSuggestedWifiEntry$20$WifiPickerTracker(wifiInfo, networkInfo, (WifiConfiguration) obj);
            }
        });
    }

    public /* synthetic */ boolean lambda$conditionallyCreateConnectedSuggestedWifiEntry$19$WifiPickerTracker(int connectedNetId, WifiConfiguration config) {
        return config.networkId == connectedNetId && !this.mSuggestedWifiEntryCache.containsKey(StandardWifiEntry.wifiConfigToStandardWifiEntryKey(config));
    }

    public /* synthetic */ void lambda$conditionallyCreateConnectedSuggestedWifiEntry$20$WifiPickerTracker(WifiInfo wifiInfo, NetworkInfo networkInfo, WifiConfiguration config) {
        StandardWifiEntry connectedEntry = new StandardWifiEntry(this.mContext, this.mMainHandler, StandardWifiEntry.wifiConfigToStandardWifiEntryKey(config), config, this.mWifiManager, this.mWifiNetworkScoreCache, false);
        connectedEntry.updateConnectionInfo(wifiInfo, networkInfo);
        this.mSuggestedWifiEntryCache.put(connectedEntry.getKey(), connectedEntry);
    }

    private void conditionallyCreateConnectedPasspointWifiEntry(final WifiInfo wifiInfo, final NetworkInfo networkInfo) {
        if (!wifiInfo.isPasspointAp()) {
            return;
        }
        final int connectedNetId = wifiInfo.getNetworkId();
        Stream.concat(this.mWifiConfigCache.values().stream(), this.mSuggestedConfigCache.values().stream()).filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$bjLRrQ4OQ8BTAnYZyvpe1EHxYpE
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$conditionallyCreateConnectedPasspointWifiEntry$21$WifiPickerTracker(connectedNetId, (WifiConfiguration) obj);
            }
        }).findAny().ifPresent(new Consumer() { // from class: com.android.wifitrackerlib.-$$Lambda$WifiPickerTracker$g9rZ4NCkczW2OW23M8uCJ7orWlk
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$conditionallyCreateConnectedPasspointWifiEntry$22$WifiPickerTracker(wifiInfo, networkInfo, (WifiConfiguration) obj);
            }
        });
    }

    public /* synthetic */ boolean lambda$conditionallyCreateConnectedPasspointWifiEntry$21$WifiPickerTracker(int connectedNetId, WifiConfiguration wifiConfig) {
        return wifiConfig.isPasspoint() && wifiConfig.networkId == connectedNetId && !this.mPasspointWifiEntryCache.containsKey(PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(wifiConfig.getKey()));
    }

    public /* synthetic */ void lambda$conditionallyCreateConnectedPasspointWifiEntry$22$WifiPickerTracker(WifiInfo wifiInfo, NetworkInfo networkInfo, WifiConfiguration wifiConfig) {
        PasspointWifiEntry connectedEntry;
        PasspointConfiguration passpointConfig = this.mPasspointConfigCache.get(PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(wifiConfig.getKey()));
        if (passpointConfig != null) {
            connectedEntry = new PasspointWifiEntry(this.mContext, this.mMainHandler, passpointConfig, this.mWifiManager, this.mWifiNetworkScoreCache, false);
        } else {
            connectedEntry = new PasspointWifiEntry(this.mContext, this.mMainHandler, wifiConfig, this.mWifiManager, this.mWifiNetworkScoreCache, false);
        }
        connectedEntry.updateConnectionInfo(wifiInfo, networkInfo);
        this.mPasspointWifiEntryCache.put(connectedEntry.getKey(), connectedEntry);
    }

    private void notifyOnWifiEntriesChanged() {
        if (this.mListener != null) {
            Handler handler = this.mMainHandler;
            final WifiPickerTrackerCallback wifiPickerTrackerCallback = this.mListener;
            Objects.requireNonNull(wifiPickerTrackerCallback);
            handler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$5hHTXQ3X9FmFmB6qtFasRAuw8jY
                @Override // java.lang.Runnable
                public final void run() {
                    wifiPickerTrackerCallback.onWifiEntriesChanged();
                }
            });
        }
    }

    private void notifyOnNumSavedNetworksChanged() {
        if (this.mListener != null) {
            Handler handler = this.mMainHandler;
            final WifiPickerTrackerCallback wifiPickerTrackerCallback = this.mListener;
            Objects.requireNonNull(wifiPickerTrackerCallback);
            handler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$fOs_tKYRPCSHYuhDjdNG_-Oy-no
                @Override // java.lang.Runnable
                public final void run() {
                    wifiPickerTrackerCallback.onNumSavedNetworksChanged();
                }
            });
        }
    }

    private void notifyOnNumSavedSubscriptionsChanged() {
        if (this.mListener != null) {
            Handler handler = this.mMainHandler;
            final WifiPickerTrackerCallback wifiPickerTrackerCallback = this.mListener;
            Objects.requireNonNull(wifiPickerTrackerCallback);
            handler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$pqRUdfn3mQVx4DYE6gYgaQJgj0E
                @Override // java.lang.Runnable
                public final void run() {
                    wifiPickerTrackerCallback.onNumSavedSubscriptionsChanged();
                }
            });
        }
    }
}
