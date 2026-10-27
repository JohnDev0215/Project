package com.android.settingslib.wifi;

import android.app.AppGlobals;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkKey;
import android.net.NetworkScoreManager;
import android.net.NetworkScorerAppData;
import android.net.ScoredNetwork;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkScoreCache;
import android.net.wifi.hotspot2.OsuProvider;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.net.wifi.hotspot2.ProvisioningCallback;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import com.android.car.developeroptions.intelligence.LogProto$SettingsLog;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.CollectionUtils;
import com.android.settingslib.R$array;
import com.android.settingslib.R$string;
import com.android.settingslib.utils.ThreadUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class AccessPoint implements Comparable<AccessPoint> {
    private String bssid;
    AccessPointListener mAccessPointListener;
    private WifiConfiguration mConfig;
    private WifiManager.ActionListener mConnectListener;
    private final Context mContext;
    private int mEapType;
    private final ArraySet<ScanResult> mExtraScanResults;
    private String mFqdn;
    private WifiInfo mInfo;
    private boolean mIsOweTransitionMode;
    private boolean mIsPskSaeTransitionMode;
    private boolean mIsScoredNetworkMetered;
    private String mKey;
    private final Object mLock;
    private NetworkInfo mNetworkInfo;
    private String mOsuFailure;
    private OsuProvider mOsuProvider;
    private boolean mOsuProvisioningComplete;
    private String mOsuStatus;
    private int mPasspointConfigurationVersion;
    private String mPasspointUniqueId;
    private String mProviderFriendlyName;
    private int mRssi;
    private final ArraySet<ScanResult> mScanResults;
    private final Map<String, TimestampedScoredNetwork> mScoredNetworkCache;
    private int mSpeed;
    private long mSubscriptionExpirationTimeInMillis;
    private Object mTag;
    private WifiManager mWifiManager;
    private int networkId;
    private int pskType;
    private int security;
    private String ssid;

    public interface AccessPointListener {
        void onAccessPointChanged(AccessPoint accessPoint);

        void onLevelChanged(AccessPoint accessPoint);
    }

    private static int roundToClosestSpeedEnum(int i) {
        if (i < 5) {
            return 0;
        }
        if (i < 7) {
            return 5;
        }
        if (i < 15) {
            return 10;
        }
        return i < 25 ? 20 : 30;
    }

    public static String securityToString(int i, int i2) {
        return i == 1 ? "WEP" : i == 2 ? i2 == 1 ? "WPA" : i2 == 2 ? "WPA2" : i2 == 3 ? "WPA_WPA2" : "PSK" : i == 3 ? "EAP" : i == 5 ? "SAE" : i == 6 ? "SUITE_B" : i == 4 ? "OWE" : "NONE";
    }

    static {
        new AtomicInteger(0);
    }

    public AccessPoint(Context context, Bundle bundle) {
        this.mLock = new Object();
        this.mScanResults = new ArraySet<>();
        this.mExtraScanResults = new ArraySet<>();
        this.mScoredNetworkCache = new HashMap();
        this.networkId = -1;
        this.pskType = 0;
        this.mEapType = 0;
        this.mRssi = Integer.MIN_VALUE;
        this.mSpeed = 0;
        this.mIsScoredNetworkMetered = false;
        this.mPasspointConfigurationVersion = 0;
        this.mOsuProvisioningComplete = false;
        this.mIsPskSaeTransitionMode = false;
        this.mIsOweTransitionMode = false;
        this.mContext = context;
        if (bundle.containsKey("key_config")) {
            this.mConfig = (WifiConfiguration) bundle.getParcelable("key_config");
        }
        WifiConfiguration wifiConfiguration = this.mConfig;
        if (wifiConfiguration != null) {
            loadConfig(wifiConfiguration);
        }
        if (bundle.containsKey("key_ssid")) {
            this.ssid = bundle.getString("key_ssid");
        }
        if (bundle.containsKey("key_security")) {
            this.security = bundle.getInt("key_security");
        }
        if (bundle.containsKey("key_speed")) {
            this.mSpeed = bundle.getInt("key_speed");
        }
        if (bundle.containsKey("key_psktype")) {
            this.pskType = bundle.getInt("key_psktype");
        }
        if (bundle.containsKey("eap_psktype")) {
            this.mEapType = bundle.getInt("eap_psktype");
        }
        this.mInfo = (WifiInfo) bundle.getParcelable("key_wifiinfo");
        if (bundle.containsKey("key_networkinfo")) {
            this.mNetworkInfo = (NetworkInfo) bundle.getParcelable("key_networkinfo");
        }
        if (bundle.containsKey("key_scanresults")) {
            Parcelable[] parcelableArray = bundle.getParcelableArray("key_scanresults");
            this.mScanResults.clear();
            for (Parcelable parcelable : parcelableArray) {
                this.mScanResults.add((ScanResult) parcelable);
            }
        }
        if (bundle.containsKey("key_scorednetworkcache")) {
            for (TimestampedScoredNetwork timestampedScoredNetwork : bundle.getParcelableArrayList("key_scorednetworkcache")) {
                this.mScoredNetworkCache.put(timestampedScoredNetwork.getScore().networkKey.wifiKey.bssid, timestampedScoredNetwork);
            }
        }
        if (bundle.containsKey("key_passpoint_unique_id")) {
            this.mPasspointUniqueId = bundle.getString("key_passpoint_unique_id");
        }
        if (bundle.containsKey("key_fqdn")) {
            this.mFqdn = bundle.getString("key_fqdn");
        }
        if (bundle.containsKey("key_provider_friendly_name")) {
            this.mProviderFriendlyName = bundle.getString("key_provider_friendly_name");
        }
        if (bundle.containsKey("key_subscription_expiration_time_in_millis")) {
            this.mSubscriptionExpirationTimeInMillis = bundle.getLong("key_subscription_expiration_time_in_millis");
        }
        if (bundle.containsKey("key_passpoint_configuration_version")) {
            this.mPasspointConfigurationVersion = bundle.getInt("key_passpoint_configuration_version");
        }
        if (bundle.containsKey("key_is_psk_sae_transition_mode")) {
            this.mIsPskSaeTransitionMode = bundle.getBoolean("key_is_psk_sae_transition_mode");
        }
        if (bundle.containsKey("key_is_owe_transition_mode")) {
            this.mIsOweTransitionMode = bundle.getBoolean("key_is_owe_transition_mode");
        }
        update(this.mConfig, this.mInfo, this.mNetworkInfo);
        updateKey();
        updateBestRssiInfo();
    }

    public AccessPoint(Context context, WifiConfiguration wifiConfiguration) {
        this.mLock = new Object();
        this.mScanResults = new ArraySet<>();
        this.mExtraScanResults = new ArraySet<>();
        this.mScoredNetworkCache = new HashMap();
        this.networkId = -1;
        this.pskType = 0;
        this.mEapType = 0;
        this.mRssi = Integer.MIN_VALUE;
        this.mSpeed = 0;
        this.mIsScoredNetworkMetered = false;
        this.mPasspointConfigurationVersion = 0;
        this.mOsuProvisioningComplete = false;
        this.mIsPskSaeTransitionMode = false;
        this.mIsOweTransitionMode = false;
        this.mContext = context;
        loadConfig(wifiConfiguration);
        updateKey();
    }

    public AccessPoint(Context context, PasspointConfiguration passpointConfiguration) {
        this.mLock = new Object();
        this.mScanResults = new ArraySet<>();
        this.mExtraScanResults = new ArraySet<>();
        this.mScoredNetworkCache = new HashMap();
        this.networkId = -1;
        this.pskType = 0;
        this.mEapType = 0;
        this.mRssi = Integer.MIN_VALUE;
        this.mSpeed = 0;
        this.mIsScoredNetworkMetered = false;
        this.mPasspointConfigurationVersion = 0;
        this.mOsuProvisioningComplete = false;
        this.mIsPskSaeTransitionMode = false;
        this.mIsOweTransitionMode = false;
        this.mContext = context;
        this.mPasspointUniqueId = passpointConfiguration.getUniqueId();
        this.mFqdn = passpointConfiguration.getHomeSp().getFqdn();
        this.mProviderFriendlyName = passpointConfiguration.getHomeSp().getFriendlyName();
        this.mSubscriptionExpirationTimeInMillis = passpointConfiguration.getSubscriptionExpirationTimeMillis();
        if (passpointConfiguration.isOsuProvisioned()) {
            this.mPasspointConfigurationVersion = 2;
        } else {
            this.mPasspointConfigurationVersion = 1;
        }
        updateKey();
    }

    public AccessPoint(Context context, WifiConfiguration wifiConfiguration, Collection<ScanResult> collection, Collection<ScanResult> collection2) {
        this.mLock = new Object();
        this.mScanResults = new ArraySet<>();
        this.mExtraScanResults = new ArraySet<>();
        this.mScoredNetworkCache = new HashMap();
        this.networkId = -1;
        this.pskType = 0;
        this.mEapType = 0;
        this.mRssi = Integer.MIN_VALUE;
        this.mSpeed = 0;
        this.mIsScoredNetworkMetered = false;
        this.mPasspointConfigurationVersion = 0;
        this.mOsuProvisioningComplete = false;
        this.mIsPskSaeTransitionMode = false;
        this.mIsOweTransitionMode = false;
        this.mContext = context;
        this.networkId = wifiConfiguration.networkId;
        this.mConfig = wifiConfiguration;
        this.mPasspointUniqueId = wifiConfiguration.getKey();
        this.mFqdn = wifiConfiguration.FQDN;
        setScanResultsPasspoint(collection, collection2);
        updateKey();
    }

    public AccessPoint(Context context, OsuProvider osuProvider, Collection<ScanResult> collection) {
        this.mLock = new Object();
        this.mScanResults = new ArraySet<>();
        this.mExtraScanResults = new ArraySet<>();
        this.mScoredNetworkCache = new HashMap();
        this.networkId = -1;
        this.pskType = 0;
        this.mEapType = 0;
        this.mRssi = Integer.MIN_VALUE;
        this.mSpeed = 0;
        this.mIsScoredNetworkMetered = false;
        this.mPasspointConfigurationVersion = 0;
        this.mOsuProvisioningComplete = false;
        this.mIsPskSaeTransitionMode = false;
        this.mIsOweTransitionMode = false;
        this.mContext = context;
        this.mOsuProvider = osuProvider;
        setScanResults(collection);
        updateKey();
    }

    AccessPoint(Context context, Collection<ScanResult> collection) {
        this.mLock = new Object();
        this.mScanResults = new ArraySet<>();
        this.mExtraScanResults = new ArraySet<>();
        this.mScoredNetworkCache = new HashMap();
        this.networkId = -1;
        this.pskType = 0;
        this.mEapType = 0;
        this.mRssi = Integer.MIN_VALUE;
        this.mSpeed = 0;
        this.mIsScoredNetworkMetered = false;
        this.mPasspointConfigurationVersion = 0;
        this.mOsuProvisioningComplete = false;
        this.mIsPskSaeTransitionMode = false;
        this.mIsOweTransitionMode = false;
        this.mContext = context;
        setScanResults(collection);
        updateKey();
    }

    @VisibleForTesting
    void loadConfig(WifiConfiguration wifiConfiguration) {
        String str = wifiConfiguration.SSID;
        this.ssid = str == null ? "" : removeDoubleQuotes(str);
        this.bssid = wifiConfiguration.BSSID;
        this.security = getSecurity(wifiConfiguration);
        this.networkId = wifiConfiguration.networkId;
        this.mConfig = wifiConfiguration;
    }

    private void updateKey() {
        if (isPasspoint()) {
            this.mKey = getKey(this.mConfig);
            return;
        }
        if (isPasspointConfig()) {
            this.mKey = getKey(this.mPasspointUniqueId);
        } else if (isOsuProvider()) {
            this.mKey = getKey(this.mOsuProvider);
        } else {
            this.mKey = getKey(getSsidStr(), getBssid(), getSecurity());
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(AccessPoint accessPoint) {
        if (isActive() && !accessPoint.isActive()) {
            return -1;
        }
        if (!isActive() && accessPoint.isActive()) {
            return 1;
        }
        if (isReachable() && !accessPoint.isReachable()) {
            return -1;
        }
        if (!isReachable() && accessPoint.isReachable()) {
            return 1;
        }
        if (isSaved() && !accessPoint.isSaved()) {
            return -1;
        }
        if (!isSaved() && accessPoint.isSaved()) {
            return 1;
        }
        if (getSpeed() != accessPoint.getSpeed()) {
            return accessPoint.getSpeed() - getSpeed();
        }
        WifiManager wifiManager = getWifiManager();
        int iCalculateSignalLevel = wifiManager.calculateSignalLevel(accessPoint.mRssi) - wifiManager.calculateSignalLevel(this.mRssi);
        if (iCalculateSignalLevel != 0) {
            return iCalculateSignalLevel;
        }
        int iCompareToIgnoreCase = getTitle().compareToIgnoreCase(accessPoint.getTitle());
        return iCompareToIgnoreCase != 0 ? iCompareToIgnoreCase : getSsidStr().compareTo(accessPoint.getSsidStr());
    }

    public boolean equals(Object obj) {
        return (obj instanceof AccessPoint) && compareTo((AccessPoint) obj) == 0;
    }

    public int hashCode() {
        WifiInfo wifiInfo = this.mInfo;
        return (wifiInfo != null ? 0 + (wifiInfo.hashCode() * 13) : 0) + (this.mRssi * 19) + (this.networkId * 23) + (this.ssid.hashCode() * 29);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AccessPoint(");
        sb.append(this.ssid);
        if (this.bssid != null) {
            sb.append(":");
            sb.append(this.bssid);
        }
        if (isSaved()) {
            sb.append(',');
            sb.append("saved");
        }
        if (isActive()) {
            sb.append(',');
            sb.append("active");
        }
        if (isEphemeral()) {
            sb.append(',');
            sb.append("ephemeral");
        }
        if (isConnectable()) {
            sb.append(',');
            sb.append("connectable");
        }
        int i = this.security;
        if (i != 0 && i != 4) {
            sb.append(',');
            sb.append(securityToString(this.security, this.pskType));
        }
        sb.append(",level=");
        sb.append(getLevel());
        if (this.mSpeed != 0) {
            sb.append(",speed=");
            sb.append(this.mSpeed);
        }
        sb.append(",metered=");
        sb.append(isMetered());
        if (isVerboseLoggingEnabled()) {
            sb.append(",rssi=");
            sb.append(this.mRssi);
            synchronized (this.mLock) {
                sb.append(",scan cache size=");
                sb.append(this.mScanResults.size() + this.mExtraScanResults.size());
            }
        }
        sb.append(')');
        return sb.toString();
    }

    boolean update(WifiNetworkScoreCache wifiNetworkScoreCache, boolean z, long j) {
        return updateMetered(wifiNetworkScoreCache) || (z ? updateScores(wifiNetworkScoreCache, j) : false);
    }

    private boolean updateScores(WifiNetworkScoreCache wifiNetworkScoreCache, long j) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        synchronized (this.mLock) {
            for (ScanResult scanResult : this.mScanResults) {
                ScoredNetwork scoredNetwork = wifiNetworkScoreCache.getScoredNetwork(scanResult);
                if (scoredNetwork != null) {
                    TimestampedScoredNetwork timestampedScoredNetwork = this.mScoredNetworkCache.get(scanResult.BSSID);
                    if (timestampedScoredNetwork == null) {
                        this.mScoredNetworkCache.put(scanResult.BSSID, new TimestampedScoredNetwork(scoredNetwork, jElapsedRealtime));
                    } else {
                        timestampedScoredNetwork.update(scoredNetwork, jElapsedRealtime);
                    }
                }
            }
        }
        final long j2 = jElapsedRealtime - j;
        final Iterator<TimestampedScoredNetwork> it = this.mScoredNetworkCache.values().iterator();
        it.forEachRemaining(new Consumer() { // from class: com.android.settingslib.wifi.-$$Lambda$AccessPoint$OIXfUc7y1PqI_zmQ3STe_086YzY
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                AccessPoint.lambda$updateScores$0(j2, it, (TimestampedScoredNetwork) obj);
            }
        });
        return updateSpeed();
    }

    static /* synthetic */ void lambda$updateScores$0(long j, Iterator it, TimestampedScoredNetwork timestampedScoredNetwork) {
        if (timestampedScoredNetwork.getUpdatedTimestampMillis() < j) {
            it.remove();
        }
    }

    private boolean updateSpeed() {
        int i = this.mSpeed;
        int iGenerateAverageSpeedForSsid = generateAverageSpeedForSsid();
        this.mSpeed = iGenerateAverageSpeedForSsid;
        boolean z = i != iGenerateAverageSpeedForSsid;
        if (isVerboseLoggingEnabled() && z) {
            Log.i("SettingsLib.AccessPoint", String.format("%s: Set speed to %d", this.ssid, Integer.valueOf(this.mSpeed)));
        }
        return z;
    }

    private int generateAverageSpeedForSsid() {
        if (this.mScoredNetworkCache.isEmpty()) {
            return 0;
        }
        if (Log.isLoggable("SettingsLib.AccessPoint", 3)) {
            Log.d("SettingsLib.AccessPoint", String.format("Generating fallbackspeed for %s using cache: %s", getSsidStr(), this.mScoredNetworkCache));
        }
        Iterator<TimestampedScoredNetwork> it = this.mScoredNetworkCache.values().iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            int iCalculateBadge = it.next().getScore().calculateBadge(this.mRssi);
            if (iCalculateBadge != 0) {
                i++;
                i2 += iCalculateBadge;
            }
        }
        int i3 = i == 0 ? 0 : i2 / i;
        if (isVerboseLoggingEnabled()) {
            Log.i("SettingsLib.AccessPoint", String.format("%s generated fallback speed is: %d", getSsidStr(), Integer.valueOf(i3)));
        }
        return roundToClosestSpeedEnum(i3);
    }

    private boolean updateMetered(WifiNetworkScoreCache wifiNetworkScoreCache) {
        WifiInfo wifiInfo;
        boolean z = this.mIsScoredNetworkMetered;
        this.mIsScoredNetworkMetered = false;
        if (isActive() && (wifiInfo = this.mInfo) != null) {
            ScoredNetwork scoredNetwork = wifiNetworkScoreCache.getScoredNetwork(NetworkKey.createFromWifiInfo(wifiInfo));
            if (scoredNetwork != null) {
                this.mIsScoredNetworkMetered = scoredNetwork.meteredHint | this.mIsScoredNetworkMetered;
            }
        } else {
            synchronized (this.mLock) {
                Iterator<ScanResult> it = this.mScanResults.iterator();
                while (it.hasNext()) {
                    ScoredNetwork scoredNetwork2 = wifiNetworkScoreCache.getScoredNetwork(it.next());
                    if (scoredNetwork2 != null) {
                        this.mIsScoredNetworkMetered = scoredNetwork2.meteredHint | this.mIsScoredNetworkMetered;
                    }
                }
            }
        }
        return z != this.mIsScoredNetworkMetered;
    }

    public static String getKey(Context context, ScanResult scanResult) {
        return getKey(scanResult.SSID, scanResult.BSSID, getSecurity(context, scanResult));
    }

    public static String getKey(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.isPasspoint()) {
            return getKey(wifiConfiguration.getKey());
        }
        return getKey(removeDoubleQuotes(wifiConfiguration.SSID), wifiConfiguration.BSSID, getSecurity(wifiConfiguration));
    }

    public static String getKey(String str) {
        return "PASSPOINT:" + str;
    }

    public static String getKey(OsuProvider osuProvider) {
        return "OSU:" + osuProvider.getFriendlyName() + ',' + osuProvider.getServerUri();
    }

    private static String getKey(String str, String str2, int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("AP:");
        if (TextUtils.isEmpty(str)) {
            sb.append(str2);
        } else {
            sb.append(str);
        }
        sb.append(',');
        sb.append(i);
        return sb.toString();
    }

    public String getKey() {
        return this.mKey;
    }

    public boolean matches(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.isPasspoint()) {
            return isPasspoint() && wifiConfiguration.getKey().equals(this.mConfig.getKey());
        }
        if (!this.ssid.equals(removeDoubleQuotes(wifiConfiguration.SSID))) {
            return false;
        }
        WifiConfiguration wifiConfiguration2 = this.mConfig;
        if (wifiConfiguration2 != null && wifiConfiguration2.shared != wifiConfiguration.shared) {
            return false;
        }
        int security = getSecurity(wifiConfiguration);
        if (this.mIsPskSaeTransitionMode && ((security == 5 && getWifiManager().isWpa3SaeSupported()) || security == 2)) {
            return true;
        }
        return (this.mIsOweTransitionMode && ((security == 4 && getWifiManager().isEnhancedOpenSupported()) || security == 0)) || this.security == getSecurity(wifiConfiguration);
    }

    private boolean matches(WifiConfiguration wifiConfiguration, WifiInfo wifiInfo) {
        if (wifiConfiguration == null || wifiInfo == null) {
            return false;
        }
        if (wifiConfiguration.isPasspoint() || isSameSsidOrBssid(wifiInfo)) {
            return matches(wifiConfiguration);
        }
        return false;
    }

    @VisibleForTesting
    boolean matches(ScanResult scanResult) {
        if (scanResult == null) {
            return false;
        }
        if (isPasspoint() || isOsuProvider()) {
            throw new IllegalStateException("Should not matches a Passpoint by ScanResult");
        }
        if (!isSameSsidOrBssid(scanResult)) {
            return false;
        }
        if (this.mIsPskSaeTransitionMode) {
            if ((scanResult.capabilities.contains("SAE") && getWifiManager().isWpa3SaeSupported()) || scanResult.capabilities.contains("PSK")) {
                return true;
            }
        } else {
            int i = this.security;
            if ((i == 5 || i == 2) && isPskSaeTransitionMode(scanResult)) {
                return true;
            }
        }
        if (this.mIsOweTransitionMode) {
            int security = getSecurity(this.mContext, scanResult);
            if ((security == 4 && getWifiManager().isEnhancedOpenSupported()) || security == 0) {
                return true;
            }
        } else {
            int i2 = this.security;
            if ((i2 == 4 || i2 == 0) && isOweTransitionMode(scanResult)) {
                return true;
            }
        }
        return this.security == getSecurity(this.mContext, scanResult);
    }

    public WifiConfiguration getConfig() {
        return this.mConfig;
    }

    public String getPasspointFqdn() {
        return this.mFqdn;
    }

    public WifiInfo getInfo() {
        return this.mInfo;
    }

    public int getLevel() {
        return getWifiManager().calculateSignalLevel(this.mRssi);
    }

    public Set<ScanResult> getScanResults() {
        ArraySet arraySet = new ArraySet();
        synchronized (this.mLock) {
            arraySet.addAll((Collection) this.mScanResults);
            arraySet.addAll((Collection) this.mExtraScanResults);
        }
        return arraySet;
    }

    public Map<String, TimestampedScoredNetwork> getScoredNetworkCache() {
        return this.mScoredNetworkCache;
    }

    private void updateBestRssiInfo() {
        int i;
        int i2;
        if (isActive()) {
            return;
        }
        ScanResult scanResult = null;
        synchronized (this.mLock) {
            i = Integer.MIN_VALUE;
            for (ScanResult scanResult2 : this.mScanResults) {
                if (scanResult2.level > i) {
                    i = scanResult2.level;
                    scanResult = scanResult2;
                }
            }
        }
        if (i != Integer.MIN_VALUE && (i2 = this.mRssi) != Integer.MIN_VALUE) {
            this.mRssi = (i2 + i) / 2;
        } else {
            this.mRssi = i;
        }
        if (scanResult != null) {
            this.ssid = scanResult.SSID;
            this.bssid = scanResult.BSSID;
            int security = getSecurity(this.mContext, scanResult);
            this.security = security;
            if (security == 2 || security == 5) {
                this.pskType = getPskType(scanResult);
            }
            if (this.security == 3) {
                this.mEapType = getEapType(scanResult);
            }
            this.mIsPskSaeTransitionMode = isPskSaeTransitionMode(scanResult);
            this.mIsOweTransitionMode = isOweTransitionMode(scanResult);
        }
        if (isPasspoint()) {
            this.mConfig.SSID = convertToQuotedString(this.ssid);
        }
    }

    public boolean isMetered() {
        return this.mIsScoredNetworkMetered || WifiConfiguration.isMetered(this.mConfig, this.mInfo);
    }

    public NetworkInfo getNetworkInfo() {
        return this.mNetworkInfo;
    }

    public int getSecurity() {
        return this.security;
    }

    public String getSecurityString(boolean z) {
        Context context = this.mContext;
        if (isPasspoint() || isPasspointConfig()) {
            if (z) {
                return context.getString(R$string.wifi_security_short_eap);
            }
            return context.getString(R$string.wifi_security_eap);
        }
        if (this.mIsPskSaeTransitionMode) {
            if (z) {
                return context.getString(R$string.wifi_security_short_psk_sae);
            }
            return context.getString(R$string.wifi_security_psk_sae);
        }
        if (this.mIsOweTransitionMode) {
            if (z) {
                return context.getString(R$string.wifi_security_short_none_owe);
            }
            return context.getString(R$string.wifi_security_none_owe);
        }
        switch (this.security) {
            case 1:
                if (z) {
                    return context.getString(R$string.wifi_security_short_wep);
                }
                return context.getString(R$string.wifi_security_wep);
            case 2:
                int i = this.pskType;
                if (i == 1) {
                    if (z) {
                        return context.getString(R$string.wifi_security_short_wpa);
                    }
                    return context.getString(R$string.wifi_security_wpa);
                }
                if (i == 2) {
                    if (z) {
                        return context.getString(R$string.wifi_security_short_wpa2);
                    }
                    return context.getString(R$string.wifi_security_wpa2);
                }
                if (i != 3) {
                    if (z) {
                        return context.getString(R$string.wifi_security_short_psk_generic);
                    }
                    return context.getString(R$string.wifi_security_psk_generic);
                }
                if (z) {
                    return context.getString(R$string.wifi_security_short_wpa_wpa2);
                }
                return context.getString(R$string.wifi_security_wpa_wpa2);
            case 3:
                int i2 = this.mEapType;
                if (i2 == 1) {
                    if (z) {
                        return context.getString(R$string.wifi_security_short_eap_wpa);
                    }
                    return context.getString(R$string.wifi_security_eap_wpa);
                }
                if (i2 != 2) {
                    if (z) {
                        return context.getString(R$string.wifi_security_short_eap);
                    }
                    return context.getString(R$string.wifi_security_eap);
                }
                if (z) {
                    return context.getString(R$string.wifi_security_short_eap_wpa2_wpa3);
                }
                return context.getString(R$string.wifi_security_eap_wpa2_wpa3);
            case 4:
                if (z) {
                    return context.getString(R$string.wifi_security_short_owe);
                }
                return context.getString(R$string.wifi_security_owe);
            case 5:
                if (z) {
                    return context.getString(R$string.wifi_security_short_sae);
                }
                return context.getString(R$string.wifi_security_sae);
            case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                if (z) {
                    return context.getString(R$string.wifi_security_short_eap_suiteb);
                }
                return context.getString(R$string.wifi_security_eap_suiteb);
            default:
                return z ? "" : context.getString(R$string.wifi_security_none);
        }
    }

    public String getSsidStr() {
        return this.ssid;
    }

    public String getBssid() {
        return this.bssid;
    }

    public CharSequence getSsid() {
        return this.ssid;
    }

    @Deprecated
    public String getConfigName() {
        WifiConfiguration wifiConfiguration = this.mConfig;
        if (wifiConfiguration != null && wifiConfiguration.isPasspoint()) {
            return this.mConfig.providerFriendlyName;
        }
        if (this.mPasspointUniqueId != null) {
            return this.mProviderFriendlyName;
        }
        return this.ssid;
    }

    public NetworkInfo.DetailedState getDetailedState() {
        NetworkInfo networkInfo = this.mNetworkInfo;
        if (networkInfo != null) {
            return networkInfo.getDetailedState();
        }
        Log.w("SettingsLib.AccessPoint", "NetworkInfo is null, cannot return detailed state");
        return null;
    }

    public String getSavedNetworkSummary() {
        WifiConfiguration wifiConfiguration = this.mConfig;
        if (wifiConfiguration != null) {
            PackageManager packageManager = this.mContext.getPackageManager();
            String nameForUid = packageManager.getNameForUid(1000);
            int userId = UserHandle.getUserId(wifiConfiguration.creatorUid);
            ApplicationInfo applicationInfo = null;
            String str = wifiConfiguration.creatorName;
            if (str != null && str.equals(nameForUid)) {
                applicationInfo = this.mContext.getApplicationInfo();
            } else {
                try {
                    applicationInfo = AppGlobals.getPackageManager().getApplicationInfo(wifiConfiguration.creatorName, 0, userId);
                } catch (RemoteException unused) {
                }
            }
            if (applicationInfo != null && !applicationInfo.packageName.equals(this.mContext.getString(R$string.settings_package)) && !applicationInfo.packageName.equals(this.mContext.getString(R$string.certinstaller_package))) {
                return this.mContext.getString(R$string.saved_network, applicationInfo.loadLabel(packageManager));
            }
        }
        return (isPasspointConfigurationR1() && isExpired()) ? this.mContext.getString(R$string.wifi_passpoint_expired) : "";
    }

    public String getTitle() {
        if (isPasspoint()) {
            return this.mConfig.providerFriendlyName;
        }
        if (isPasspointConfig()) {
            return this.mProviderFriendlyName;
        }
        if (isOsuProvider()) {
            return this.mOsuProvider.getFriendlyName();
        }
        return getSsidStr();
    }

    public String getSettingsSummary() {
        return getSettingsSummary(false);
    }

    public String getSettingsSummary(boolean z) {
        int i;
        if (isPasspointConfigurationR1() && isExpired()) {
            return this.mContext.getString(R$string.wifi_passpoint_expired);
        }
        StringBuilder sb = new StringBuilder();
        if (isOsuProvider()) {
            if (this.mOsuProvisioningComplete) {
                sb.append(this.mContext.getString(R$string.osu_sign_up_complete));
            } else {
                String str = this.mOsuFailure;
                if (str != null) {
                    sb.append(str);
                } else {
                    String str2 = this.mOsuStatus;
                    if (str2 != null) {
                        sb.append(str2);
                    } else {
                        sb.append(this.mContext.getString(R$string.tap_to_sign_up));
                    }
                }
            }
        } else if (isActive()) {
            Context context = this.mContext;
            NetworkInfo.DetailedState detailedState = getDetailedState();
            WifiInfo wifiInfo = this.mInfo;
            boolean z2 = wifiInfo != null && wifiInfo.isEphemeral();
            WifiInfo wifiInfo2 = this.mInfo;
            sb.append(getSummary(context, null, detailedState, z2, wifiInfo2 != null ? wifiInfo2.getRequestingPackageName() : null));
        } else {
            WifiConfiguration wifiConfiguration = this.mConfig;
            if (wifiConfiguration != null && wifiConfiguration.hasNoInternetAccess()) {
                if (this.mConfig.getNetworkSelectionStatus().getNetworkSelectionStatus() == 2) {
                    i = R$string.wifi_no_internet_no_reconnect;
                } else {
                    i = R$string.wifi_no_internet;
                }
                sb.append(this.mContext.getString(i));
            } else {
                WifiConfiguration wifiConfiguration2 = this.mConfig;
                if (wifiConfiguration2 != null && wifiConfiguration2.getNetworkSelectionStatus().getNetworkSelectionStatus() != 0) {
                    int networkSelectionDisableReason = this.mConfig.getNetworkSelectionStatus().getNetworkSelectionDisableReason();
                    if (networkSelectionDisableReason == 1) {
                        sb.append(this.mContext.getString(R$string.wifi_disabled_generic));
                    } else if (networkSelectionDisableReason == 2) {
                        sb.append(this.mContext.getString(R$string.wifi_disabled_password_failure));
                    } else if (networkSelectionDisableReason == 3) {
                        sb.append(this.mContext.getString(R$string.wifi_disabled_network_failure));
                    } else if (networkSelectionDisableReason == 8) {
                        sb.append(this.mContext.getString(R$string.wifi_check_password_try_again));
                    }
                } else if (!isReachable()) {
                    sb.append(this.mContext.getString(R$string.wifi_not_in_range));
                } else {
                    WifiConfiguration wifiConfiguration3 = this.mConfig;
                    if (wifiConfiguration3 != null) {
                        if (wifiConfiguration3.getRecentFailureReason() == 17) {
                            sb.append(this.mContext.getString(R$string.wifi_ap_unable_to_handle_new_sta));
                        } else if (z) {
                            sb.append(this.mContext.getString(R$string.wifi_disconnected));
                        } else {
                            sb.append(this.mContext.getString(R$string.wifi_remembered));
                        }
                    }
                }
            }
        }
        if (isVerboseLoggingEnabled()) {
            sb.append(WifiUtils.buildLoggingSummary(this, this.mConfig));
        }
        WifiConfiguration wifiConfiguration4 = this.mConfig;
        if (wifiConfiguration4 != null && (WifiUtils.isMeteredOverridden(wifiConfiguration4) || this.mConfig.meteredHint)) {
            return this.mContext.getResources().getString(R$string.preference_summary_default_combination, WifiUtils.getMeteredLabel(this.mContext, this.mConfig), sb.toString());
        }
        if (getSpeedLabel() != null && sb.length() != 0) {
            return this.mContext.getResources().getString(R$string.preference_summary_default_combination, getSpeedLabel(), sb.toString());
        }
        if (getSpeedLabel() != null) {
            return getSpeedLabel();
        }
        return sb.toString();
    }

    public boolean isActive() {
        NetworkInfo networkInfo = this.mNetworkInfo;
        return (networkInfo == null || (this.networkId == -1 && networkInfo.getState() == NetworkInfo.State.DISCONNECTED)) ? false : true;
    }

    public boolean isConnectable() {
        return getLevel() != -1 && getDetailedState() == null;
    }

    public boolean isEphemeral() {
        NetworkInfo networkInfo;
        WifiInfo wifiInfo = this.mInfo;
        return (wifiInfo == null || !wifiInfo.isEphemeral() || (networkInfo = this.mNetworkInfo) == null || networkInfo.getState() == NetworkInfo.State.DISCONNECTED) ? false : true;
    }

    public boolean isPasspoint() {
        WifiConfiguration wifiConfiguration = this.mConfig;
        return wifiConfiguration != null && wifiConfiguration.isPasspoint();
    }

    public boolean isPasspointConfig() {
        return this.mPasspointUniqueId != null && this.mConfig == null;
    }

    public boolean isOsuProvider() {
        return this.mOsuProvider != null;
    }

    public boolean isExpired() {
        return this.mSubscriptionExpirationTimeInMillis > 0 && System.currentTimeMillis() >= this.mSubscriptionExpirationTimeInMillis;
    }

    public boolean isPasspointConfigurationR1() {
        return this.mPasspointConfigurationVersion == 1;
    }

    public void startOsuProvisioning(WifiManager.ActionListener actionListener) {
        this.mConnectListener = actionListener;
        getWifiManager().startSubscriptionProvisioning(this.mOsuProvider, this.mContext.getMainExecutor(), new AccessPointProvisioningCallback());
    }

    private boolean isInfoForThisAccessPoint(WifiConfiguration wifiConfiguration, WifiInfo wifiInfo) {
        if (wifiInfo.isOsuAp() || this.mOsuStatus != null) {
            return wifiInfo.isOsuAp() && this.mOsuStatus != null;
        }
        if (wifiInfo.isPasspointAp() || isPasspoint()) {
            return wifiInfo.isPasspointAp() && isPasspoint() && TextUtils.equals(wifiInfo.getPasspointFqdn(), this.mConfig.FQDN) && TextUtils.equals(wifiInfo.getPasspointProviderFriendlyName(), this.mConfig.providerFriendlyName);
        }
        int i = this.networkId;
        if (i != -1) {
            return i == wifiInfo.getNetworkId();
        }
        if (wifiConfiguration != null) {
            return matches(wifiConfiguration, wifiInfo);
        }
        return TextUtils.equals(removeDoubleQuotes(wifiInfo.getSSID()), this.ssid);
    }

    public boolean isSaved() {
        return this.mConfig != null;
    }

    public Object getTag() {
        return this.mTag;
    }

    public void setTag(Object obj) {
        this.mTag = obj;
    }

    public void generateOpenNetworkConfig() {
        if (!isOpenNetwork()) {
            throw new IllegalStateException();
        }
        if (this.mConfig != null) {
            return;
        }
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        this.mConfig = wifiConfiguration;
        wifiConfiguration.SSID = convertToQuotedString(this.ssid);
        if (this.security == 0) {
            this.mConfig.allowedKeyManagement.set(0);
        } else {
            this.mConfig.allowedKeyManagement.set(9);
            this.mConfig.requirePmf = true;
        }
    }

    public void saveWifiState(Bundle bundle) {
        if (this.ssid != null) {
            bundle.putString("key_ssid", getSsidStr());
        }
        bundle.putInt("key_security", this.security);
        bundle.putInt("key_speed", this.mSpeed);
        bundle.putInt("key_psktype", this.pskType);
        bundle.putInt("eap_psktype", this.mEapType);
        WifiConfiguration wifiConfiguration = this.mConfig;
        if (wifiConfiguration != null) {
            bundle.putParcelable("key_config", wifiConfiguration);
        }
        bundle.putParcelable("key_wifiinfo", this.mInfo);
        synchronized (this.mLock) {
            bundle.putParcelableArray("key_scanresults", (Parcelable[]) this.mScanResults.toArray(new Parcelable[this.mScanResults.size() + this.mExtraScanResults.size()]));
        }
        bundle.putParcelableArrayList("key_scorednetworkcache", new ArrayList<>(this.mScoredNetworkCache.values()));
        NetworkInfo networkInfo = this.mNetworkInfo;
        if (networkInfo != null) {
            bundle.putParcelable("key_networkinfo", networkInfo);
        }
        String str = this.mPasspointUniqueId;
        if (str != null) {
            bundle.putString("key_passpoint_unique_id", str);
        }
        String str2 = this.mFqdn;
        if (str2 != null) {
            bundle.putString("key_fqdn", str2);
        }
        String str3 = this.mProviderFriendlyName;
        if (str3 != null) {
            bundle.putString("key_provider_friendly_name", str3);
        }
        bundle.putLong("key_subscription_expiration_time_in_millis", this.mSubscriptionExpirationTimeInMillis);
        bundle.putInt("key_passpoint_configuration_version", this.mPasspointConfigurationVersion);
        bundle.putBoolean("key_is_psk_sae_transition_mode", this.mIsPskSaeTransitionMode);
        bundle.putBoolean("key_is_owe_transition_mode", this.mIsOweTransitionMode);
    }

    public void setListener(AccessPointListener accessPointListener) {
        this.mAccessPointListener = accessPointListener;
    }

    void setScanResults(Collection<ScanResult> collection) {
        if (CollectionUtils.isEmpty(collection)) {
            Log.d("SettingsLib.AccessPoint", "Cannot set scan results to empty list");
            return;
        }
        if (this.mKey != null && !isPasspoint() && !isOsuProvider()) {
            for (ScanResult scanResult : collection) {
                if (!matches(scanResult)) {
                    Log.d("SettingsLib.AccessPoint", String.format("ScanResult %s\nkey of %s did not match current AP key %s", scanResult, getKey(this.mContext, scanResult), this.mKey));
                    return;
                }
            }
        }
        int level = getLevel();
        synchronized (this.mLock) {
            this.mScanResults.clear();
            this.mScanResults.addAll(collection);
        }
        updateBestRssiInfo();
        int level2 = getLevel();
        if (level2 > 0 && level2 != level) {
            updateSpeed();
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.settingslib.wifi.-$$Lambda$AccessPoint$MkkIS1nUbezHicDMmYnviyiBJyo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setScanResults$1$AccessPoint();
                }
            });
        }
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.settingslib.wifi.-$$Lambda$AccessPoint$0Yq14aFJZLjPMzFGAvglLaxsblI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setScanResults$2$AccessPoint();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$setScanResults$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$setScanResults$1$AccessPoint() {
        AccessPointListener accessPointListener = this.mAccessPointListener;
        if (accessPointListener != null) {
            accessPointListener.onLevelChanged(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$setScanResults$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$setScanResults$2$AccessPoint() {
        AccessPointListener accessPointListener = this.mAccessPointListener;
        if (accessPointListener != null) {
            accessPointListener.onAccessPointChanged(this);
        }
    }

    void setScanResultsPasspoint(Collection<ScanResult> collection, Collection<ScanResult> collection2) {
        synchronized (this.mLock) {
            this.mExtraScanResults.clear();
            if (!CollectionUtils.isEmpty(collection)) {
                if (!CollectionUtils.isEmpty(collection2)) {
                    this.mExtraScanResults.addAll(collection2);
                }
                setScanResults(collection);
            } else if (!CollectionUtils.isEmpty(collection2)) {
                setScanResults(collection2);
            }
        }
    }

    public boolean update(WifiConfiguration wifiConfiguration, WifiInfo wifiInfo, NetworkInfo networkInfo) {
        int level = getLevel();
        if (wifiInfo != null && isInfoForThisAccessPoint(wifiConfiguration, wifiInfo)) {
            z = this.mInfo == null;
            if (!isPasspoint() && this.mConfig != wifiConfiguration) {
                update(wifiConfiguration);
            }
            if (this.mRssi != wifiInfo.getRssi() && wifiInfo.getRssi() != -127) {
                this.mRssi = wifiInfo.getRssi();
            } else {
                NetworkInfo networkInfo2 = this.mNetworkInfo;
                if (networkInfo2 != null && networkInfo != null && networkInfo2.getDetailedState() != networkInfo.getDetailedState()) {
                }
                this.mInfo = wifiInfo;
                this.mNetworkInfo = networkInfo;
            }
            z = true;
            this.mInfo = wifiInfo;
            this.mNetworkInfo = networkInfo;
        } else if (this.mInfo != null) {
            this.mInfo = null;
            this.mNetworkInfo = null;
            z = true;
        }
        if (z && this.mAccessPointListener != null) {
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.settingslib.wifi.-$$Lambda$AccessPoint$S7H59e_8IxpVPy0V68Oc2-zX-rg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$update$3$AccessPoint();
                }
            });
            if (level != getLevel()) {
                ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.settingslib.wifi.-$$Lambda$AccessPoint$QW-1Uw0oxoaKqUtEtPO0oPvH5ng
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$update$4$AccessPoint();
                    }
                });
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$update$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$update$3$AccessPoint() {
        AccessPointListener accessPointListener = this.mAccessPointListener;
        if (accessPointListener != null) {
            accessPointListener.onAccessPointChanged(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$update$4, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$update$4$AccessPoint() {
        AccessPointListener accessPointListener = this.mAccessPointListener;
        if (accessPointListener != null) {
            accessPointListener.onLevelChanged(this);
        }
    }

    void update(WifiConfiguration wifiConfiguration) {
        this.mConfig = wifiConfiguration;
        if (wifiConfiguration != null && !isPasspoint()) {
            this.ssid = removeDoubleQuotes(this.mConfig.SSID);
        }
        this.networkId = wifiConfiguration != null ? wifiConfiguration.networkId : -1;
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.settingslib.wifi.-$$Lambda$AccessPoint$QyP0aXhFuWtm7lmBu1IY3qbfmBA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$update$5$AccessPoint();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$update$5, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$update$5$AccessPoint() {
        AccessPointListener accessPointListener = this.mAccessPointListener;
        if (accessPointListener != null) {
            accessPointListener.onAccessPointChanged(this);
        }
    }

    @VisibleForTesting
    void setRssi(int i) {
        this.mRssi = i;
    }

    int getSpeed() {
        return this.mSpeed;
    }

    String getSpeedLabel() {
        return getSpeedLabel(this.mSpeed);
    }

    String getSpeedLabel(int i) {
        return getSpeedLabel(this.mContext, i);
    }

    private static String getSpeedLabel(Context context, int i) {
        if (i == 5) {
            return context.getString(R$string.speed_label_slow);
        }
        if (i == 10) {
            return context.getString(R$string.speed_label_okay);
        }
        if (i == 20) {
            return context.getString(R$string.speed_label_fast);
        }
        if (i != 30) {
            return null;
        }
        return context.getString(R$string.speed_label_very_fast);
    }

    public static String getSpeedLabel(Context context, ScoredNetwork scoredNetwork, int i) {
        return getSpeedLabel(context, roundToClosestSpeedEnum(scoredNetwork.calculateBadge(i)));
    }

    public boolean isReachable() {
        return this.mRssi != Integer.MIN_VALUE;
    }

    private static CharSequence getAppLabel(String str, PackageManager packageManager) {
        try {
            ApplicationInfo applicationInfoAsUser = packageManager.getApplicationInfoAsUser(str, 0, UserHandle.getUserId(-2));
            return applicationInfoAsUser != null ? applicationInfoAsUser.loadLabel(packageManager) : "";
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("SettingsLib.AccessPoint", "Failed to get app info", e);
            return "";
        }
    }

    public static String getSummary(Context context, String str, NetworkInfo.DetailedState detailedState, boolean z, String str2) {
        NetworkCapabilities networkCapabilities;
        if (detailedState == NetworkInfo.DetailedState.CONNECTED) {
            if (z && !TextUtils.isEmpty(str2)) {
                return context.getString(R$string.connected_via_app, getAppLabel(str2, context.getPackageManager()));
            }
            if (z) {
                NetworkScorerAppData activeScorer = ((NetworkScoreManager) context.getSystemService(NetworkScoreManager.class)).getActiveScorer();
                return (activeScorer == null || activeScorer.getRecommendationServiceLabel() == null) ? context.getString(R$string.connected_via_network_scorer_default) : String.format(context.getString(R$string.connected_via_network_scorer), activeScorer.getRecommendationServiceLabel());
            }
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (detailedState == NetworkInfo.DetailedState.CONNECTED && (networkCapabilities = connectivityManager.getNetworkCapabilities(((WifiManager) context.getSystemService(WifiManager.class)).getCurrentNetwork())) != null) {
            if (networkCapabilities.hasCapability(17)) {
                return context.getString(context.getResources().getIdentifier("network_available_sign_in", "string", "android"));
            }
            if (networkCapabilities.hasCapability(24)) {
                return context.getString(R$string.wifi_limited_connection);
            }
            if (!networkCapabilities.hasCapability(16)) {
                Settings.Global.getString(context.getContentResolver(), "private_dns_mode");
                if (networkCapabilities.isPrivateDnsBroken()) {
                    return context.getString(R$string.private_dns_broken);
                }
                return context.getString(R$string.wifi_connected_no_internet);
            }
        }
        if (detailedState == null) {
            Log.w("SettingsLib.AccessPoint", "state is null, returning empty summary");
            return "";
        }
        String[] stringArray = context.getResources().getStringArray(str == null ? R$array.wifi_status : R$array.wifi_status_with_ssid);
        int iOrdinal = detailedState.ordinal();
        return (iOrdinal >= stringArray.length || stringArray[iOrdinal].length() == 0) ? "" : String.format(stringArray[iOrdinal], str);
    }

    public static String convertToQuotedString(String str) {
        return "\"" + str + "\"";
    }

    private static int getPskType(ScanResult scanResult) {
        boolean zContains = scanResult.capabilities.contains("WPA-PSK");
        boolean zContains2 = scanResult.capabilities.contains("RSN-PSK");
        boolean zContains3 = scanResult.capabilities.contains("RSN-SAE");
        if (zContains2 && zContains) {
            return 3;
        }
        if (zContains2) {
            return 2;
        }
        if (zContains) {
            return 1;
        }
        if (zContains3) {
            return 0;
        }
        Log.w("SettingsLib.AccessPoint", "Received abnormal flag string: " + scanResult.capabilities);
        return 0;
    }

    private static int getEapType(ScanResult scanResult) {
        if (scanResult.capabilities.contains("RSN-EAP")) {
            return 2;
        }
        return scanResult.capabilities.contains("WPA-EAP") ? 1 : 0;
    }

    private static int getSecurity(Context context, ScanResult scanResult) {
        boolean zContains = scanResult.capabilities.contains("WEP");
        boolean zContains2 = scanResult.capabilities.contains("SAE");
        boolean zContains3 = scanResult.capabilities.contains("PSK");
        boolean zContains4 = scanResult.capabilities.contains("EAP_SUITE_B_192");
        boolean zContains5 = scanResult.capabilities.contains("EAP");
        boolean zContains6 = scanResult.capabilities.contains("OWE");
        boolean zContains7 = scanResult.capabilities.contains("OWE_TRANSITION");
        if (zContains2 && zContains3) {
            return ((WifiManager) context.getSystemService("wifi")).isWpa3SaeSupported() ? 5 : 2;
        }
        if (zContains7) {
            return ((WifiManager) context.getSystemService("wifi")).isEnhancedOpenSupported() ? 4 : 0;
        }
        if (zContains) {
            return 1;
        }
        if (zContains2) {
            return 5;
        }
        if (zContains3) {
            return 2;
        }
        if (zContains4) {
            return 6;
        }
        if (zContains5) {
            return 3;
        }
        return zContains6 ? 4 : 0;
    }

    static int getSecurity(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.allowedKeyManagement.get(8)) {
            return 5;
        }
        if (wifiConfiguration.allowedKeyManagement.get(1)) {
            return 2;
        }
        if (wifiConfiguration.allowedKeyManagement.get(10)) {
            return 6;
        }
        if (wifiConfiguration.allowedKeyManagement.get(2) || wifiConfiguration.allowedKeyManagement.get(3)) {
            return 3;
        }
        if (wifiConfiguration.allowedKeyManagement.get(9)) {
            return 4;
        }
        int i = wifiConfiguration.wepTxKeyIndex;
        if (i >= 0) {
            String[] strArr = wifiConfiguration.wepKeys;
            if (i < strArr.length && strArr[i] != null) {
                return 1;
            }
        }
        return 0;
    }

    static String removeDoubleQuotes(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int length = str.length();
        if (length <= 1 || str.charAt(0) != '\"') {
            return str;
        }
        int i = length - 1;
        return str.charAt(i) == '\"' ? str.substring(1, i) : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WifiManager getWifiManager() {
        if (this.mWifiManager == null) {
            this.mWifiManager = (WifiManager) this.mContext.getSystemService("wifi");
        }
        return this.mWifiManager;
    }

    public boolean isOpenNetwork() {
        int i = this.security;
        return i == 0 || i == 4;
    }

    private static boolean isVerboseLoggingEnabled() {
        return WifiTracker.sVerboseLogging || Log.isLoggable("SettingsLib.AccessPoint", 2);
    }

    @VisibleForTesting
    class AccessPointProvisioningCallback extends ProvisioningCallback {
        AccessPointProvisioningCallback() {
        }

        public void onProvisioningFailure(int i) {
            if (TextUtils.equals(AccessPoint.this.mOsuStatus, AccessPoint.this.mContext.getString(R$string.osu_completing_sign_up))) {
                AccessPoint accessPoint = AccessPoint.this;
                accessPoint.mOsuFailure = accessPoint.mContext.getString(R$string.osu_sign_up_failed);
            } else {
                AccessPoint accessPoint2 = AccessPoint.this;
                accessPoint2.mOsuFailure = accessPoint2.mContext.getString(R$string.osu_connect_failed);
            }
            AccessPoint.this.mOsuStatus = null;
            AccessPoint.this.mOsuProvisioningComplete = false;
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.settingslib.wifi.-$$Lambda$AccessPoint$AccessPointProvisioningCallback$74qKnAJvzvRGvsJDwRIri14jOnQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onProvisioningFailure$0$AccessPoint$AccessPointProvisioningCallback();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onProvisioningFailure$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onProvisioningFailure$0$AccessPoint$AccessPointProvisioningCallback() {
            AccessPoint accessPoint = AccessPoint.this;
            AccessPointListener accessPointListener = accessPoint.mAccessPointListener;
            if (accessPointListener != null) {
                accessPointListener.onAccessPointChanged(accessPoint);
            }
        }

        public void onProvisioningStatus(int i) {
            String string;
            switch (i) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                case 7:
                    string = String.format(AccessPoint.this.mContext.getString(R$string.osu_opening_provider), AccessPoint.this.mOsuProvider.getFriendlyName());
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                    string = AccessPoint.this.mContext.getString(R$string.osu_completing_sign_up);
                    break;
                default:
                    string = null;
                    break;
            }
            boolean zEquals = true ^ TextUtils.equals(AccessPoint.this.mOsuStatus, string);
            AccessPoint.this.mOsuStatus = string;
            AccessPoint.this.mOsuFailure = null;
            AccessPoint.this.mOsuProvisioningComplete = false;
            if (zEquals) {
                ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.settingslib.wifi.-$$Lambda$AccessPoint$AccessPointProvisioningCallback$ko59tOsAuz6AC9y5Nq-UikXZo9s
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onProvisioningStatus$1$AccessPoint$AccessPointProvisioningCallback();
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onProvisioningStatus$1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onProvisioningStatus$1$AccessPoint$AccessPointProvisioningCallback() {
            AccessPoint accessPoint = AccessPoint.this;
            AccessPointListener accessPointListener = accessPoint.mAccessPointListener;
            if (accessPointListener != null) {
                accessPointListener.onAccessPointChanged(accessPoint);
            }
        }

        public void onProvisioningComplete() {
            AccessPoint.this.mOsuProvisioningComplete = true;
            AccessPoint.this.mOsuFailure = null;
            AccessPoint.this.mOsuStatus = null;
            ThreadUtils.postOnMainThread(new Runnable() { // from class: com.android.settingslib.wifi.-$$Lambda$AccessPoint$AccessPointProvisioningCallback$8NkGPNV0jfGEnIZHmtcNMYE5Q7Q
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onProvisioningComplete$2$AccessPoint$AccessPointProvisioningCallback();
                }
            });
            WifiManager wifiManager = AccessPoint.this.getWifiManager();
            PasspointConfiguration passpointConfiguration = (PasspointConfiguration) wifiManager.getMatchingPasspointConfigsForOsuProviders(Collections.singleton(AccessPoint.this.mOsuProvider)).get(AccessPoint.this.mOsuProvider);
            if (passpointConfiguration == null) {
                Log.e("SettingsLib.AccessPoint", "Missing PasspointConfiguration for newly provisioned network!");
                if (AccessPoint.this.mConnectListener != null) {
                    AccessPoint.this.mConnectListener.onFailure(0);
                    return;
                }
                return;
            }
            String uniqueId = passpointConfiguration.getUniqueId();
            for (Pair pair : wifiManager.getAllMatchingWifiConfigs(wifiManager.getScanResults())) {
                WifiConfiguration wifiConfiguration = (WifiConfiguration) pair.first;
                if (TextUtils.equals(wifiConfiguration.getKey(), uniqueId)) {
                    wifiManager.connect(new AccessPoint(AccessPoint.this.mContext, wifiConfiguration, (List) ((Map) pair.second).get(0), (List) ((Map) pair.second).get(1)).getConfig(), AccessPoint.this.mConnectListener);
                    return;
                }
            }
            if (AccessPoint.this.mConnectListener != null) {
                AccessPoint.this.mConnectListener.onFailure(0);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onProvisioningComplete$2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onProvisioningComplete$2$AccessPoint$AccessPointProvisioningCallback() {
            AccessPoint accessPoint = AccessPoint.this;
            AccessPointListener accessPointListener = accessPoint.mAccessPointListener;
            if (accessPointListener != null) {
                accessPointListener.onAccessPointChanged(accessPoint);
            }
        }
    }

    private static boolean isPskSaeTransitionMode(ScanResult scanResult) {
        return scanResult.capabilities.contains("PSK") && scanResult.capabilities.contains("SAE");
    }

    private static boolean isOweTransitionMode(ScanResult scanResult) {
        return scanResult.capabilities.contains("OWE_TRANSITION");
    }

    private boolean isSameSsidOrBssid(ScanResult scanResult) {
        if (scanResult == null) {
            return false;
        }
        if (TextUtils.equals(this.ssid, scanResult.SSID)) {
            return true;
        }
        String str = scanResult.BSSID;
        return str != null && TextUtils.equals(this.bssid, str);
    }

    private boolean isSameSsidOrBssid(WifiInfo wifiInfo) {
        if (wifiInfo == null) {
            return false;
        }
        if (TextUtils.equals(this.ssid, removeDoubleQuotes(wifiInfo.getSSID()))) {
            return true;
        }
        return wifiInfo.getBSSID() != null && TextUtils.equals(this.bssid, wifiInfo.getBSSID());
    }
}
