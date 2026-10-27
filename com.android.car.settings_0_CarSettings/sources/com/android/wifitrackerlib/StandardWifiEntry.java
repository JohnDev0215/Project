package com.android.wifitrackerlib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkScoreManager;
import android.net.NetworkScorerAppData;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkScoreCache;
import android.os.Handler;
import android.os.SystemClock;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import androidx.core.util.Preconditions;
import com.android.wifitrackerlib.WifiEntry;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes6.dex */
public class StandardWifiEntry extends WifiEntry {
    private static final int EAP_UNKNOWN = 2;
    private static final int EAP_WPA = 0;
    private static final int EAP_WPA2_WPA3 = 1;
    static final String KEY_PREFIX = "StandardWifiEntry:";
    private static final int PSK_UNKNOWN = 3;
    private static final int PSK_WPA = 0;
    private static final int PSK_WPA2 = 1;
    private static final int PSK_WPA_WPA2 = 2;
    private final Context mContext;
    private final List<ScanResult> mCurrentScanResults;
    private int mEapType;
    private boolean mIsUserShareable;
    private final String mKey;
    private final Object mLock;
    private int mPskType;
    private String mRecommendationServiceLabel;
    private final int mSecurity;
    private boolean mShouldAutoOpenCaptivePortal;
    private final String mSsid;
    private WifiConfiguration mWifiConfig;

    @Retention(RetentionPolicy.SOURCE)
    public @interface EapType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PskType {
    }

    StandardWifiEntry(Context context, Handler callbackHandler, String key, List<ScanResult> scanResults, WifiManager wifiManager, WifiNetworkScoreCache scoreCache, boolean forSavedNetworksPage) throws IllegalArgumentException {
        this(context, callbackHandler, key, wifiManager, scoreCache, forSavedNetworksPage);
        Preconditions.checkNotNull(scanResults, "Cannot construct with null ScanResult list!");
        if (scanResults.isEmpty()) {
            throw new IllegalArgumentException("Cannot construct with empty ScanResult list!");
        }
        updateScanResultInfo(scanResults);
        updateRecommendationServiceLabel();
    }

    StandardWifiEntry(Context context, Handler callbackHandler, String key, WifiConfiguration config, WifiManager wifiManager, WifiNetworkScoreCache scoreCache, boolean forSavedNetworksPage) throws IllegalArgumentException {
        this(context, callbackHandler, key, wifiManager, scoreCache, forSavedNetworksPage);
        Preconditions.checkNotNull(config, "Cannot construct with null config!");
        Preconditions.checkNotNull(config.SSID, "Supplied config must have an SSID!");
        this.mWifiConfig = config;
        updateRecommendationServiceLabel();
    }

    StandardWifiEntry(Context context, Handler callbackHandler, String key, WifiManager wifiManager, WifiNetworkScoreCache scoreCache, boolean forSavedNetworksPage) {
        super(callbackHandler, wifiManager, scoreCache, forSavedNetworksPage);
        this.mLock = new Object();
        this.mCurrentScanResults = new ArrayList();
        this.mEapType = 2;
        this.mPskType = 3;
        this.mIsUserShareable = false;
        this.mShouldAutoOpenCaptivePortal = false;
        this.mContext = context;
        this.mKey = key;
        try {
            int prefixDelimiter = key.indexOf(":");
            int securityDelimiter = key.lastIndexOf(",");
            this.mSsid = key.substring(prefixDelimiter + 1, securityDelimiter);
            this.mSecurity = Integer.valueOf(key.substring(securityDelimiter + 1)).intValue();
            updateRecommendationServiceLabel();
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Malformed key: " + key);
        }
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String getKey() {
        return this.mKey;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String getTitle() {
        return this.mSsid;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String getSummary(boolean concise) {
        StringJoiner sj = new StringJoiner(this.mContext.getString(R.string.summary_separator));
        if (!concise && this.mForSavedNetworksPage && isSaved()) {
            CharSequence appLabel = Utils.getAppLabel(this.mContext, this.mWifiConfig.creatorName);
            if (!TextUtils.isEmpty(appLabel)) {
                sj.add(this.mContext.getString(R.string.saved_network, appLabel));
            }
        }
        if (getConnectedState() == 0) {
            String disconnectDescription = Utils.getDisconnectedStateDescription(this.mContext, this);
            if (TextUtils.isEmpty(disconnectDescription)) {
                if (concise) {
                    sj.add(this.mContext.getString(R.string.wifi_disconnected));
                } else if (!this.mForSavedNetworksPage) {
                    if (isSuggestion()) {
                        Context context = this.mContext;
                        String carrierName = Utils.getCarrierNameForSubId(context, Utils.getSubIdForConfig(context, this.mWifiConfig));
                        String suggestorName = Utils.getAppLabel(this.mContext, this.mWifiConfig.creatorName);
                        if (TextUtils.isEmpty(suggestorName)) {
                            suggestorName = this.mWifiConfig.creatorName;
                        }
                        Context context2 = this.mContext;
                        int i = R.string.available_via_app;
                        Object[] objArr = new Object[1];
                        objArr[0] = carrierName != null ? carrierName : suggestorName;
                        sj.add(context2.getString(i, objArr));
                    } else if (isSaved()) {
                        sj.add(this.mContext.getString(R.string.wifi_remembered));
                    }
                }
            } else {
                sj.add(disconnectDescription);
            }
        } else {
            String connectDescription = getConnectStateDescription();
            if (!TextUtils.isEmpty(connectDescription)) {
                sj.add(connectDescription);
            }
        }
        String speedDescription = Utils.getSpeedDescription(this.mContext, this);
        if (!TextUtils.isEmpty(speedDescription)) {
            sj.add(speedDescription);
        }
        String autoConnectDescription = Utils.getAutoConnectDescription(this.mContext, this);
        if (!TextUtils.isEmpty(autoConnectDescription)) {
            sj.add(autoConnectDescription);
        }
        String meteredDescription = Utils.getMeteredDescription(this.mContext, this);
        if (!TextUtils.isEmpty(meteredDescription)) {
            sj.add(meteredDescription);
        }
        if (!concise) {
            String verboseLoggingDescription = Utils.getVerboseLoggingDescription(this);
            if (!TextUtils.isEmpty(verboseLoggingDescription)) {
                sj.add(verboseLoggingDescription);
            }
        }
        return sj.toString();
    }

    private String getConnectStateDescription() {
        if (getConnectedState() == 2) {
            String carrierName = null;
            String suggestionOrSpecifierPackageName = this.mWifiInfo != null ? this.mWifiInfo.getRequestingPackageName() : null;
            if (!TextUtils.isEmpty(suggestionOrSpecifierPackageName)) {
                WifiConfiguration wifiConfiguration = this.mWifiConfig;
                if (wifiConfiguration != null) {
                    Context context = this.mContext;
                    carrierName = Utils.getCarrierNameForSubId(context, Utils.getSubIdForConfig(context, wifiConfiguration));
                }
                String suggestorName = Utils.getAppLabel(this.mContext, suggestionOrSpecifierPackageName);
                if (TextUtils.isEmpty(suggestorName)) {
                    suggestorName = suggestionOrSpecifierPackageName;
                }
                Context context2 = this.mContext;
                int i = R.string.connected_via_app;
                Object[] objArr = new Object[1];
                objArr[0] = carrierName != null ? carrierName : suggestorName;
                return context2.getString(i, objArr);
            }
            if (!isSaved() && !isSuggestion()) {
                return !TextUtils.isEmpty(this.mRecommendationServiceLabel) ? String.format(this.mContext.getString(R.string.connected_via_network_scorer), this.mRecommendationServiceLabel) : this.mContext.getString(R.string.connected_via_network_scorer_default);
            }
            if (this.mIsLowQuality) {
                return this.mContext.getString(R.string.wifi_connected_low_quality);
            }
            String networkCapabilitiesinformation = Utils.getCurrentNetworkCapabilitiesInformation(this.mContext, this.mNetworkCapabilities);
            if (!TextUtils.isEmpty(networkCapabilitiesinformation)) {
                return networkCapabilitiesinformation;
            }
        }
        return Utils.getNetworkDetailedState(this.mContext, this.mNetworkInfo);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public CharSequence getSecondSummary() {
        return getConnectedState() == 2 ? Utils.getImsiProtectionDescription(this.mContext, getWifiConfiguration()) : "";
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String getSsid() {
        return this.mSsid;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public int getSecurity() {
        return this.mSecurity;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String getMacAddress() {
        if (this.mWifiInfo != null) {
            String wifiInfoMac = this.mWifiInfo.getMacAddress();
            if (!TextUtils.isEmpty(wifiInfoMac) && !TextUtils.equals(wifiInfoMac, "02:00:00:00:00:00")) {
                return wifiInfoMac;
            }
        }
        if (this.mWifiConfig == null || getPrivacy() != 1) {
            String[] factoryMacs = this.mWifiManager.getFactoryMacAddresses();
            if (factoryMacs.length > 0) {
                return factoryMacs[0];
            }
            return null;
        }
        return this.mWifiConfig.getRandomizedMacAddress().toString();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isMetered() {
        if (getMeteredChoice() == 1) {
            return true;
        }
        WifiConfiguration wifiConfiguration = this.mWifiConfig;
        return wifiConfiguration != null && wifiConfiguration.meteredHint;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isSaved() {
        WifiConfiguration wifiConfiguration = this.mWifiConfig;
        return (wifiConfiguration == null || wifiConfiguration.isEphemeral()) ? false : true;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isSuggestion() {
        WifiConfiguration wifiConfiguration = this.mWifiConfig;
        return wifiConfiguration != null && wifiConfiguration.fromWifiNetworkSuggestion;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isSubscription() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public WifiConfiguration getWifiConfiguration() {
        if (!isSaved()) {
            return null;
        }
        return this.mWifiConfig;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public WifiEntry.ConnectedInfo getConnectedInfo() {
        return this.mConnectedInfo;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canConnect() {
        WifiConfiguration wifiConfiguration;
        if (this.mLevel == -1 || getConnectedState() != 0) {
            return false;
        }
        if (getSecurity() != 3 || (wifiConfiguration = this.mWifiConfig) == null || wifiConfiguration.enterpriseConfig == null || !this.mWifiConfig.enterpriseConfig.isAuthenticationSimBased()) {
            return true;
        }
        List<SubscriptionInfo> activeSubscriptionInfos = ((SubscriptionManager) this.mContext.getSystemService("telephony_subscription_service")).getActiveSubscriptionInfoList();
        if (activeSubscriptionInfos == null || activeSubscriptionInfos.size() == 0) {
            return false;
        }
        if (this.mWifiConfig.carrierId == -1) {
            return true;
        }
        for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfos) {
            if (subscriptionInfo.getCarrierId() == this.mWifiConfig.carrierId) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public void connect(final WifiEntry.ConnectCallback callback) {
        this.mConnectCallback = callback;
        this.mShouldAutoOpenCaptivePortal = true;
        if (isSaved() || isSuggestion()) {
            this.mWifiManager.connect(this.mWifiConfig.networkId, new WifiEntry.ConnectActionListener());
            return;
        }
        int i = this.mSecurity;
        if (i == 0 || i == 4) {
            WifiConfiguration connectConfig = new WifiConfiguration();
            connectConfig.SSID = "\"" + this.mSsid + "\"";
            if (this.mSecurity == 4) {
                connectConfig.allowedKeyManagement.set(9);
                connectConfig.requirePmf = true;
            } else {
                connectConfig.allowedKeyManagement.set(0);
            }
            this.mWifiManager.connect(connectConfig, new WifiEntry.ConnectActionListener());
            return;
        }
        if (callback != null) {
            this.mCallbackHandler.post(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$StandardWifiEntry$RuC7yREceMbI61_zVW5EXFu-JHA
                @Override // java.lang.Runnable
                public final void run() {
                    callback.onConnectResult(1);
                }
            });
        }
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canDisconnect() {
        return getConnectedState() == 2;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public void disconnect(final WifiEntry.DisconnectCallback callback) {
        if (canDisconnect()) {
            this.mCalledDisconnect = true;
            this.mDisconnectCallback = callback;
            this.mCallbackHandler.postDelayed(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$StandardWifiEntry$UZGGZY6miJgeSSJxai0hhutNNIQ
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$disconnect$1$StandardWifiEntry(callback);
                }
            }, 10000L);
            this.mWifiManager.disableEphemeralNetwork(this.mWifiConfig.SSID);
            this.mWifiManager.disconnect();
        }
    }

    public /* synthetic */ void lambda$disconnect$1$StandardWifiEntry(WifiEntry.DisconnectCallback callback) {
        if (callback != null && this.mCalledDisconnect) {
            callback.onDisconnectResult(1);
        }
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canForget() {
        return getWifiConfiguration() != null;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public void forget(WifiEntry.ForgetCallback callback) {
        if (canForget()) {
            this.mForgetCallback = callback;
            this.mWifiManager.forget(this.mWifiConfig.networkId, new WifiEntry.ForgetActionListener());
        }
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canSignIn() {
        return this.mNetworkCapabilities != null && this.mNetworkCapabilities.hasCapability(17);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public void signIn(WifiEntry.SignInCallback callback) {
        if (canSignIn()) {
            ((ConnectivityManager) this.mContext.getSystemService("connectivity")).startCaptivePortalApp(this.mWifiManager.getCurrentNetwork());
        }
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canShare() {
        if (getWifiConfiguration() == null) {
            return false;
        }
        int i = this.mSecurity;
        return i == 0 || i == 1 || i == 2 || i == 4 || i == 5;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canEasyConnect() {
        if (getWifiConfiguration() == null || !this.mWifiManager.isEasyConnectSupported()) {
            return false;
        }
        int i = this.mSecurity;
        return i == 2 || i == 5;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public int getMeteredChoice() {
        if (getWifiConfiguration() != null) {
            int meteredOverride = getWifiConfiguration().meteredOverride;
            if (meteredOverride == 1) {
                return 1;
            }
            if (meteredOverride == 2) {
                return 2;
            }
            return 0;
        }
        return 0;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canSetMeteredChoice() {
        return getWifiConfiguration() != null;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public void setMeteredChoice(int meteredChoice) {
        if (!canSetMeteredChoice()) {
            return;
        }
        if (meteredChoice == 0) {
            this.mWifiConfig.meteredOverride = 0;
        } else if (meteredChoice == 1) {
            this.mWifiConfig.meteredOverride = 1;
        } else if (meteredChoice == 2) {
            this.mWifiConfig.meteredOverride = 2;
        }
        this.mWifiManager.save(this.mWifiConfig, null);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canSetPrivacy() {
        return isSaved();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public int getPrivacy() {
        WifiConfiguration wifiConfiguration = this.mWifiConfig;
        if (wifiConfiguration != null && wifiConfiguration.macRandomizationSetting == 0) {
            return 0;
        }
        return 1;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public void setPrivacy(int privacy) {
        if (!canSetPrivacy()) {
            return;
        }
        this.mWifiConfig.macRandomizationSetting = privacy != 1 ? 0 : 1;
        this.mWifiManager.save(this.mWifiConfig, null);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isAutoJoinEnabled() {
        WifiConfiguration wifiConfiguration = this.mWifiConfig;
        if (wifiConfiguration == null) {
            return false;
        }
        return wifiConfiguration.allowAutojoin;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canSetAutoJoinEnabled() {
        return isSaved() || isSuggestion();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public void setAutoJoinEnabled(boolean enabled) {
        if (!canSetAutoJoinEnabled()) {
            return;
        }
        this.mWifiManager.allowAutojoin(this.mWifiConfig.networkId, enabled);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String getSecurityString(boolean concise) {
        switch (this.mSecurity) {
            case 1:
                return this.mContext.getString(R.string.wifi_security_wep);
            case 2:
                int i = this.mPskType;
                if (i == 0) {
                    return concise ? this.mContext.getString(R.string.wifi_security_short_wpa) : this.mContext.getString(R.string.wifi_security_wpa);
                }
                if (i == 1) {
                    if (concise) {
                        return this.mContext.getString(R.string.wifi_security_short_wpa2_wpa3);
                    }
                    return this.mContext.getString(R.string.wifi_security_wpa2_wpa3);
                }
                if (concise) {
                    return this.mContext.getString(R.string.wifi_security_short_wpa_wpa2_wpa3);
                }
                return this.mContext.getString(R.string.wifi_security_wpa_wpa2_wpa3);
            case 3:
                int i2 = this.mEapType;
                if (i2 == 0) {
                    return concise ? this.mContext.getString(R.string.wifi_security_short_eap_wpa) : this.mContext.getString(R.string.wifi_security_eap_wpa);
                }
                if (i2 != 1) {
                    return concise ? this.mContext.getString(R.string.wifi_security_short_eap) : this.mContext.getString(R.string.wifi_security_eap);
                }
                if (concise) {
                    return this.mContext.getString(R.string.wifi_security_short_eap_wpa2_wpa3);
                }
                return this.mContext.getString(R.string.wifi_security_eap_wpa2_wpa3);
            case 4:
                return concise ? this.mContext.getString(R.string.wifi_security_short_owe) : this.mContext.getString(R.string.wifi_security_owe);
            case 5:
                return concise ? this.mContext.getString(R.string.wifi_security_short_sae) : this.mContext.getString(R.string.wifi_security_sae);
            case 6:
                return concise ? this.mContext.getString(R.string.wifi_security_short_eap_suiteb) : this.mContext.getString(R.string.wifi_security_eap_suiteb);
            default:
                return concise ? "" : this.mContext.getString(R.string.wifi_security_none);
        }
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isExpired() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean shouldEditBeforeConnect() {
        WifiConfiguration wifiConfig = getWifiConfiguration();
        if (wifiConfig == null) {
            return false;
        }
        WifiConfiguration.NetworkSelectionStatus networkSelectionStatus = wifiConfig.getNetworkSelectionStatus();
        if (networkSelectionStatus.getNetworkSelectionStatus() == 0 || (networkSelectionStatus.getDisableReasonCounter(2) <= 0 && networkSelectionStatus.getDisableReasonCounter(8) <= 0 && networkSelectionStatus.getDisableReasonCounter(5) <= 0)) {
            return false;
        }
        return true;
    }

    void updateScanResultInfo(List<ScanResult> scanResults) throws IllegalArgumentException {
        int iCalculateSignalLevel;
        if (scanResults == null) {
            scanResults = new ArrayList();
        }
        for (ScanResult result : scanResults) {
            if (!TextUtils.equals(result.SSID, this.mSsid)) {
                throw new IllegalArgumentException("Attempted to update with wrong SSID! Expected: " + this.mSsid + ", Actual: " + result.SSID + ", ScanResult: " + result);
            }
        }
        synchronized (this.mLock) {
            this.mCurrentScanResults.clear();
            this.mCurrentScanResults.addAll(scanResults);
        }
        ScanResult bestScanResult = Utils.getBestScanResultByLevel(scanResults);
        if (bestScanResult != null) {
            updateEapType(bestScanResult);
            updatePskType(bestScanResult);
        }
        if (getConnectedState() == 0) {
            if (bestScanResult != null) {
                iCalculateSignalLevel = this.mWifiManager.calculateSignalLevel(bestScanResult.level);
            } else {
                iCalculateSignalLevel = -1;
            }
            this.mLevel = iCalculateSignalLevel;
            synchronized (this.mLock) {
                this.mSpeed = Utils.getAverageSpeedFromScanResults(this.mScoreCache, this.mCurrentScanResults);
            }
        }
        notifyOnUpdated();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    void updateNetworkCapabilities(NetworkCapabilities capabilities) {
        super.updateNetworkCapabilities(capabilities);
        if (canSignIn() && this.mShouldAutoOpenCaptivePortal) {
            this.mShouldAutoOpenCaptivePortal = false;
            signIn(null);
        }
    }

    void onScoreCacheUpdated() {
        if (this.mWifiInfo != null) {
            this.mSpeed = Utils.getSpeedFromWifiInfo(this.mScoreCache, this.mWifiInfo);
        } else {
            synchronized (this.mLock) {
                this.mSpeed = Utils.getAverageSpeedFromScanResults(this.mScoreCache, this.mCurrentScanResults);
            }
        }
        notifyOnUpdated();
    }

    private void updateEapType(ScanResult result) {
        if (result.capabilities.contains("RSN-EAP")) {
            this.mEapType = 1;
        } else if (result.capabilities.contains("WPA-EAP")) {
            this.mEapType = 0;
        } else {
            this.mEapType = 2;
        }
    }

    private void updatePskType(ScanResult result) {
        if (this.mSecurity != 2) {
            this.mPskType = 3;
            return;
        }
        boolean wpa = result.capabilities.contains("WPA-PSK");
        boolean wpa2 = result.capabilities.contains("RSN-PSK");
        if (wpa2 && wpa) {
            this.mPskType = 2;
            return;
        }
        if (wpa2) {
            this.mPskType = 1;
        } else if (wpa) {
            this.mPskType = 0;
        } else {
            this.mPskType = 3;
        }
    }

    void updateConfig(WifiConfiguration wifiConfig) throws IllegalArgumentException {
        if (wifiConfig != null) {
            if (!TextUtils.equals(this.mSsid, WifiInfo.sanitizeSsid(wifiConfig.SSID))) {
                throw new IllegalArgumentException("Attempted to update with wrong SSID! Expected: " + this.mSsid + ", Actual: " + WifiInfo.sanitizeSsid(wifiConfig.SSID) + ", Config: " + wifiConfig);
            }
            if (this.mSecurity != Utils.getSecurityTypeFromWifiConfiguration(wifiConfig)) {
                throw new IllegalArgumentException("Attempted to update with wrong security! Expected: " + this.mSecurity + ", Actual: " + Utils.getSecurityTypeFromWifiConfiguration(wifiConfig) + ", Config: " + wifiConfig);
            }
        }
        this.mWifiConfig = wifiConfig;
        notifyOnUpdated();
    }

    void setUserShareable(boolean isUserShareable) {
        this.mIsUserShareable = isUserShareable;
    }

    boolean isUserShareable() {
        return this.mIsUserShareable;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    protected boolean connectionInfoMatches(WifiInfo wifiInfo, NetworkInfo networkInfo) {
        WifiConfiguration wifiConfiguration;
        return (wifiInfo.isPasspointAp() || wifiInfo.isOsuAp() || (wifiConfiguration = this.mWifiConfig) == null || wifiConfiguration.networkId != wifiInfo.getNetworkId()) ? false : true;
    }

    private void updateRecommendationServiceLabel() {
        NetworkScorerAppData scorer = ((NetworkScoreManager) this.mContext.getSystemService("network_score")).getActiveScorer();
        if (scorer != null) {
            this.mRecommendationServiceLabel = scorer.getRecommendationServiceLabel();
        }
    }

    static String ssidAndSecurityToStandardWifiEntryKey(String ssid, int security) {
        return KEY_PREFIX + ssid + "," + security;
    }

    static String wifiConfigToStandardWifiEntryKey(WifiConfiguration config) {
        Preconditions.checkNotNull(config, "Cannot create key with null config!");
        Preconditions.checkNotNull(config.SSID, "Cannot create key with null SSID in config!");
        return KEY_PREFIX + WifiInfo.sanitizeSsid(config.SSID) + "," + Utils.getSecurityTypeFromWifiConfiguration(config);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    String getScanResultDescription() {
        synchronized (this.mLock) {
            if (this.mCurrentScanResults.size() == 0) {
                return "";
            }
            return "[" + getScanResultDescription(2400, 2500) + ";" + getScanResultDescription(4900, 5900) + ";" + getScanResultDescription(WifiEntry.MIN_FREQ_6GHZ, WifiEntry.MAX_FREQ_6GHZ) + "]";
        }
    }

    private String getScanResultDescription(final int minFrequency, final int maxFrequency) {
        List<ScanResult> scanResults;
        synchronized (this.mLock) {
            scanResults = (List) this.mCurrentScanResults.stream().filter(new Predicate() { // from class: com.android.wifitrackerlib.-$$Lambda$StandardWifiEntry$lKgEQcmtM1x3SpHuutK3I2-nfI0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return StandardWifiEntry.lambda$getScanResultDescription$2(minFrequency, maxFrequency, (ScanResult) obj);
                }
            }).sorted(Comparator.comparingInt(new ToIntFunction() { // from class: com.android.wifitrackerlib.-$$Lambda$StandardWifiEntry$Lr4BrIBW8EpwljEjYsXvjw-UzPU
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return StandardWifiEntry.lambda$getScanResultDescription$3((ScanResult) obj);
                }
            })).collect(Collectors.toList());
        }
        int scanResultCount = scanResults.size();
        if (scanResultCount == 0) {
            return "";
        }
        final StringBuilder description = new StringBuilder();
        description.append("(");
        description.append(scanResultCount);
        description.append(")");
        if (scanResultCount > 4) {
            int maxLavel = scanResults.stream().mapToInt(new ToIntFunction() { // from class: com.android.wifitrackerlib.-$$Lambda$StandardWifiEntry$ulMGK6KYyQVXHFy8lpHK9UIg2Q4
                @Override // java.util.function.ToIntFunction
                public final int applyAsInt(Object obj) {
                    return ((ScanResult) obj).level;
                }
            }).max().getAsInt();
            description.append("max=");
            description.append(maxLavel);
            description.append(",");
        }
        final long nowMs = SystemClock.elapsedRealtime();
        scanResults.forEach(new Consumer() { // from class: com.android.wifitrackerlib.-$$Lambda$StandardWifiEntry$HDaxgAFxNOzpZGjcKD6Vxnrfnp4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$getScanResultDescription$5$StandardWifiEntry(description, nowMs, (ScanResult) obj);
            }
        });
        return description.toString();
    }

    static /* synthetic */ boolean lambda$getScanResultDescription$2(int minFrequency, int maxFrequency, ScanResult scanResult) {
        return scanResult.frequency >= minFrequency && scanResult.frequency <= maxFrequency;
    }

    static /* synthetic */ int lambda$getScanResultDescription$3(ScanResult scanResult) {
        return scanResult.level * (-1);
    }

    public /* synthetic */ void lambda$getScanResultDescription$5$StandardWifiEntry(StringBuilder description, long nowMs, ScanResult scanResult) {
        description.append(getScanResultDescription(scanResult, nowMs));
    }

    private String getScanResultDescription(ScanResult scanResult, long nowMs) {
        StringBuilder description = new StringBuilder();
        description.append(" \n{");
        description.append(scanResult.BSSID);
        if (this.mWifiInfo != null && scanResult.BSSID.equals(this.mWifiInfo.getBSSID())) {
            description.append("*");
        }
        description.append("=");
        description.append(scanResult.frequency);
        description.append(",");
        description.append(scanResult.level);
        int ageSeconds = ((int) (nowMs - (scanResult.timestamp / 1000))) / 1000;
        description.append(",");
        description.append(ageSeconds);
        description.append("s");
        description.append("}");
        return description.toString();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    String getNetworkSelectionDescription() {
        return Utils.getNetworkSelectionDescription(getWifiConfiguration());
    }
}
