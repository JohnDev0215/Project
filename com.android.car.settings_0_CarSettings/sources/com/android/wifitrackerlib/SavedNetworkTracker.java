package com.android.wifitrackerlib;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkScoreManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import androidx.core.util.Preconditions;
import androidx.lifecycle.Lifecycle;
import com.android.wifitrackerlib.BaseWifiTracker;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes6.dex */
public class SavedNetworkTracker extends BaseWifiTracker {
    private static final String TAG = "SavedNetworkTracker";
    private final SavedNetworkTrackerCallback mListener;
    private final Object mLock;
    private final Map<String, PasspointWifiEntry> mPasspointWifiEntryCache;
    private final List<WifiEntry> mSavedWifiEntries;
    private final Map<String, StandardWifiEntry> mStandardWifiEntryCache;
    private final List<WifiEntry> mSubscriptionWifiEntries;

    public interface SavedNetworkTrackerCallback extends BaseWifiTracker.BaseWifiTrackerCallback {
        void onSavedWifiEntriesChanged();

        void onSubscriptionWifiEntriesChanged();
    }

    public SavedNetworkTracker(Lifecycle lifecycle, Context context, WifiManager wifiManager, ConnectivityManager connectivityManager, NetworkScoreManager networkScoreManager, Handler mainHandler, Handler workerHandler, Clock clock, long maxScanAgeMillis, long scanIntervalMillis, SavedNetworkTrackerCallback listener) {
        super(lifecycle, context, wifiManager, connectivityManager, networkScoreManager, mainHandler, workerHandler, clock, maxScanAgeMillis, scanIntervalMillis, listener, TAG);
        this.mLock = new Object();
        this.mSavedWifiEntries = new ArrayList();
        this.mSubscriptionWifiEntries = new ArrayList();
        this.mStandardWifiEntryCache = new HashMap();
        this.mPasspointWifiEntryCache = new HashMap();
        this.mListener = listener;
    }

    public List<WifiEntry> getSavedWifiEntries() {
        ArrayList arrayList;
        synchronized (this.mLock) {
            arrayList = new ArrayList(this.mSavedWifiEntries);
        }
        return arrayList;
    }

    public List<WifiEntry> getSubscriptionWifiEntries() {
        ArrayList arrayList;
        synchronized (this.mLock) {
            arrayList = new ArrayList(this.mSubscriptionWifiEntries);
        }
        return arrayList;
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleOnStart() {
        updateStandardWifiEntryConfigs(this.mWifiManager.getConfiguredNetworks());
        updatePasspointWifiEntryConfigs(this.mWifiManager.getPasspointConfigurations());
        conditionallyUpdateScanResults(true);
        updateSavedWifiEntries();
        updateSubscriptionWifiEntries();
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleWifiStateChangedAction() {
        conditionallyUpdateScanResults(true);
        updateSavedWifiEntries();
        updateSubscriptionWifiEntries();
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleScanResultsAvailableAction(Intent intent) {
        Preconditions.checkNotNull(intent, "Intent cannot be null!");
        conditionallyUpdateScanResults(intent.getBooleanExtra("resultsUpdated", true));
        updateSavedWifiEntries();
        updateSubscriptionWifiEntries();
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleConfiguredNetworksChangedAction(Intent intent) {
        Preconditions.checkNotNull(intent, "Intent cannot be null!");
        WifiConfiguration config = (WifiConfiguration) intent.getExtra("wifiConfiguration");
        if (config != null && !config.isPasspoint()) {
            updateStandardWifiEntryConfig(config, ((Integer) intent.getExtra("changeReason")).intValue());
        } else {
            updateStandardWifiEntryConfigs(this.mWifiManager.getConfiguredNetworks());
        }
        updatePasspointWifiEntryConfigs(this.mWifiManager.getPasspointConfigurations());
        updateSavedWifiEntries();
        updateSubscriptionWifiEntries();
    }

    @Override // com.android.wifitrackerlib.BaseWifiTracker
    protected void handleNetworkScoreCacheUpdated() {
        for (StandardWifiEntry entry : this.mStandardWifiEntryCache.values()) {
            entry.onScoreCacheUpdated();
        }
        for (PasspointWifiEntry entry2 : this.mPasspointWifiEntryCache.values()) {
            entry2.onScoreCacheUpdated();
        }
    }

    private void updateSavedWifiEntries() {
        synchronized (this.mLock) {
            this.mSavedWifiEntries.clear();
            this.mSavedWifiEntries.addAll(this.mStandardWifiEntryCache.values());
            Collections.sort(this.mSavedWifiEntries);
            if (isVerboseLoggingEnabled()) {
                Log.v(TAG, "Updated SavedWifiEntries: " + Arrays.toString(this.mSavedWifiEntries.toArray()));
            }
        }
        notifyOnSavedWifiEntriesChanged();
    }

    private void updateSubscriptionWifiEntries() {
        synchronized (this.mLock) {
            this.mSubscriptionWifiEntries.clear();
            this.mSubscriptionWifiEntries.addAll(this.mPasspointWifiEntryCache.values());
            Collections.sort(this.mSubscriptionWifiEntries);
            if (isVerboseLoggingEnabled()) {
                Log.v(TAG, "Updated SubscriptionWifiEntries: " + Arrays.toString(this.mSubscriptionWifiEntries.toArray()));
            }
        }
        notifyOnSubscriptionWifiEntriesChanged();
    }

    private void updateStandardWifiEntryScans(List<ScanResult> scanResults) {
        Preconditions.checkNotNull(scanResults, "Scan Result list should not be null!");
        final Map<String, List<ScanResult>> scanResultsByKey = Utils.mapScanResultsToKey(scanResults, false, null, this.mWifiManager.isWpa3SaeSupported(), this.mWifiManager.isWpa3SuiteBSupported(), this.mWifiManager.isEnhancedOpenSupported());
        this.mStandardWifiEntryCache.entrySet().forEach(new Consumer() { // from class: com.android.wifitrackerlib.-$$Lambda$SavedNetworkTracker$RngE0pMCth0-JbStnHNgSxFSuio
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SavedNetworkTracker.lambda$updateStandardWifiEntryScans$0(scanResultsByKey, (Map.Entry) obj);
            }
        });
    }

    static /* synthetic */ void lambda$updateStandardWifiEntryScans$0(Map scanResultsByKey, Map.Entry entry) {
        String key = (String) entry.getKey();
        StandardWifiEntry wifiEntry = (StandardWifiEntry) entry.getValue();
        wifiEntry.updateScanResultInfo((List) scanResultsByKey.get(key));
    }

    private void updatePasspointWifiEntryScans(List<ScanResult> scanResults) {
        Preconditions.checkNotNull(scanResults, "Scan Result list should not be null!");
        Set<String> seenKeys = new TreeSet<>();
        List<Pair<WifiConfiguration, Map<Integer, List<ScanResult>>>> matchingWifiConfigs = this.mWifiManager.getAllMatchingWifiConfigs(scanResults);
        for (Pair<WifiConfiguration, Map<Integer, List<ScanResult>>> pair : matchingWifiConfigs) {
            WifiConfiguration wifiConfig = (WifiConfiguration) pair.first;
            String key = PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(wifiConfig.getKey());
            seenKeys.add(key);
            if (this.mPasspointWifiEntryCache.containsKey(key)) {
                this.mPasspointWifiEntryCache.get(key).updateScanResultInfo(wifiConfig, (List) ((Map) pair.second).get(0), (List) ((Map) pair.second).get(1));
            }
        }
        for (PasspointWifiEntry entry : this.mPasspointWifiEntryCache.values()) {
            if (!seenKeys.contains(entry.getKey())) {
                entry.updateScanResultInfo(null, null, null);
            }
        }
    }

    private void conditionallyUpdateScanResults(boolean lastScanSucceeded) {
        if (this.mWifiManager.getWifiState() == 1) {
            updateStandardWifiEntryScans(Collections.emptyList());
            updatePasspointWifiEntryScans(Collections.emptyList());
            return;
        }
        long scanAgeWindow = this.mMaxScanAgeMillis;
        if (lastScanSucceeded) {
            this.mScanResultUpdater.update(this.mWifiManager.getScanResults());
        } else {
            scanAgeWindow += this.mScanIntervalMillis;
        }
        updateStandardWifiEntryScans(this.mScanResultUpdater.getScanResults(scanAgeWindow));
        updatePasspointWifiEntryScans(this.mScanResultUpdater.getScanResults(scanAgeWindow));
    }

    private void updateStandardWifiEntryConfig(WifiConfiguration config, int changeReason) {
        Preconditions.checkNotNull(config, "Config should not be null!");
        String key = StandardWifiEntry.wifiConfigToStandardWifiEntryKey(config);
        StandardWifiEntry entry = this.mStandardWifiEntryCache.get(key);
        if (entry != null) {
            if (changeReason == 1) {
                entry.updateConfig(null);
                this.mStandardWifiEntryCache.remove(key);
                return;
            } else {
                entry.updateConfig(config);
                return;
            }
        }
        if (changeReason != 1) {
            this.mStandardWifiEntryCache.put(key, new StandardWifiEntry(this.mContext, this.mMainHandler, key, config, this.mWifiManager, this.mWifiNetworkScoreCache, true));
        }
    }

    private void updateStandardWifiEntryConfigs(List<WifiConfiguration> configs) {
        Preconditions.checkNotNull(configs, "Config list should not be null!");
        final Map<String, WifiConfiguration> wifiConfigsByKey = (Map) configs.stream().collect(Collectors.toMap($$Lambda$eRhiL3TPu1j8op3nmit378jGeyk.INSTANCE, Function.identity()));
        this.mStandardWifiEntryCache.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$SavedNetworkTracker$Uwm7U8CRSvjmODKGQBR_weIFtK8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return SavedNetworkTracker.lambda$updateStandardWifiEntryConfigs$1(wifiConfigsByKey, (Map.Entry) obj);
            }
        });
        for (String key : wifiConfigsByKey.keySet()) {
            this.mStandardWifiEntryCache.put(key, new StandardWifiEntry(this.mContext, this.mMainHandler, key, wifiConfigsByKey.get(key), this.mWifiManager, this.mWifiNetworkScoreCache, true));
        }
    }

    static /* synthetic */ boolean lambda$updateStandardWifiEntryConfigs$1(Map wifiConfigsByKey, Map.Entry entry) {
        StandardWifiEntry wifiEntry = (StandardWifiEntry) entry.getValue();
        String key = wifiEntry.getKey();
        wifiEntry.updateConfig((WifiConfiguration) wifiConfigsByKey.remove(key));
        return !wifiEntry.isSaved();
    }

    private void updatePasspointWifiEntryConfigs(List<PasspointConfiguration> configs) {
        Preconditions.checkNotNull(configs, "Config list should not be null!");
        final Map<String, PasspointConfiguration> passpointConfigsByKey = (Map) configs.stream().collect(Collectors.toMap(new Function() { // from class: com.android.wifitrackerlib.-$$Lambda$SavedNetworkTracker$GiPU-7UrK85F3w9N7PMlA7M9niw
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PasspointWifiEntry.uniqueIdToPasspointWifiEntryKey(((PasspointConfiguration) obj).getUniqueId());
            }
        }, Function.identity()));
        this.mPasspointWifiEntryCache.entrySet().removeIf(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$SavedNetworkTracker$eyseTp76DXJATqUf1N-bJ_EBfOw
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return SavedNetworkTracker.lambda$updatePasspointWifiEntryConfigs$3(passpointConfigsByKey, (Map.Entry) obj);
            }
        });
        for (String key : passpointConfigsByKey.keySet()) {
            this.mPasspointWifiEntryCache.put(key, new PasspointWifiEntry(this.mContext, this.mMainHandler, passpointConfigsByKey.get(key), this.mWifiManager, this.mWifiNetworkScoreCache, true));
        }
    }

    static /* synthetic */ boolean lambda$updatePasspointWifiEntryConfigs$3(Map passpointConfigsByKey, Map.Entry entry) {
        PasspointWifiEntry wifiEntry = (PasspointWifiEntry) entry.getValue();
        String key = wifiEntry.getKey();
        PasspointConfiguration cachedConfig = (PasspointConfiguration) passpointConfigsByKey.remove(key);
        if (cachedConfig != null) {
            wifiEntry.updatePasspointConfig(cachedConfig);
            return false;
        }
        return true;
    }

    private void notifyOnSavedWifiEntriesChanged() {
        if (this.mListener != null) {
            Handler handler = this.mMainHandler;
            final SavedNetworkTrackerCallback savedNetworkTrackerCallback = this.mListener;
            Objects.requireNonNull(savedNetworkTrackerCallback);
            handler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$h1BuAemuREs5Akn8nay-wLEicng
                @Override // java.lang.Runnable
                public final void run() {
                    savedNetworkTrackerCallback.onSavedWifiEntriesChanged();
                }
            });
        }
    }

    private void notifyOnSubscriptionWifiEntriesChanged() {
        if (this.mListener != null) {
            Handler handler = this.mMainHandler;
            final SavedNetworkTrackerCallback savedNetworkTrackerCallback = this.mListener;
            Objects.requireNonNull(savedNetworkTrackerCallback);
            handler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$bWK0YQGHCIgfdyydB3bBJO0pQoM
                @Override // java.lang.Runnable
                public final void run() {
                    savedNetworkTrackerCallback.onSubscriptionWifiEntriesChanged();
                }
            });
        }
    }
}
