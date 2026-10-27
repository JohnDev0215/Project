package com.android.wifitrackerlib;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkScoreCache;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.os.Handler;
import android.text.TextUtils;
import androidx.core.util.Preconditions;
import com.android.wifitrackerlib.WifiEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/* JADX INFO: loaded from: classes6.dex */
public class PasspointWifiEntry extends WifiEntry implements WifiEntry.WifiEntryCallback {
    static final String KEY_PREFIX = "PasspointWifiEntry:";
    private final Context mContext;
    private final List<ScanResult> mCurrentHomeScanResults;
    private final List<ScanResult> mCurrentRoamingScanResults;
    private String mFqdn;
    private String mFriendlyName;
    private boolean mIsRoaming;
    private final String mKey;
    private final Object mLock;
    private int mMeteredOverride;
    private OsuWifiEntry mOsuWifiEntry;
    private PasspointConfiguration mPasspointConfig;
    private int mSecurity;
    protected long mSubscriptionExpirationTimeInMillis;
    private WifiConfiguration mWifiConfig;

    PasspointWifiEntry(Context context, Handler callbackHandler, PasspointConfiguration passpointConfig, WifiManager wifiManager, WifiNetworkScoreCache scoreCache, boolean forSavedNetworksPage) throws IllegalArgumentException {
        super(callbackHandler, wifiManager, scoreCache, forSavedNetworksPage);
        this.mLock = new Object();
        this.mCurrentHomeScanResults = new ArrayList();
        this.mCurrentRoamingScanResults = new ArrayList();
        this.mSecurity = 3;
        this.mIsRoaming = false;
        this.mMeteredOverride = 0;
        Preconditions.checkNotNull(passpointConfig, "Cannot construct with null PasspointConfiguration!");
        this.mContext = context;
        this.mPasspointConfig = passpointConfig;
        this.mKey = uniqueIdToPasspointWifiEntryKey(passpointConfig.getUniqueId());
        this.mFqdn = passpointConfig.getHomeSp().getFqdn();
        this.mFriendlyName = passpointConfig.getHomeSp().getFriendlyName();
        this.mSubscriptionExpirationTimeInMillis = passpointConfig.getSubscriptionExpirationTimeMillis();
        this.mMeteredOverride = this.mPasspointConfig.getMeteredOverride();
    }

    PasspointWifiEntry(Context context, Handler callbackHandler, WifiConfiguration wifiConfig, WifiManager wifiManager, WifiNetworkScoreCache scoreCache, boolean forSavedNetworksPage) throws IllegalArgumentException {
        super(callbackHandler, wifiManager, scoreCache, forSavedNetworksPage);
        this.mLock = new Object();
        this.mCurrentHomeScanResults = new ArrayList();
        this.mCurrentRoamingScanResults = new ArrayList();
        this.mSecurity = 3;
        this.mIsRoaming = false;
        this.mMeteredOverride = 0;
        Preconditions.checkNotNull(wifiConfig, "Cannot construct with null PasspointConfiguration!");
        if (!wifiConfig.isPasspoint()) {
            throw new IllegalArgumentException("Given WifiConfiguration is not for Passpoint!");
        }
        this.mContext = context;
        this.mWifiConfig = wifiConfig;
        this.mKey = uniqueIdToPasspointWifiEntryKey(wifiConfig.getKey());
        this.mFqdn = wifiConfig.FQDN;
        this.mFriendlyName = this.mWifiConfig.providerFriendlyName;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String getKey() {
        return this.mKey;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public int getConnectedState() {
        OsuWifiEntry osuWifiEntry;
        if (isExpired() && super.getConnectedState() == 0 && (osuWifiEntry = this.mOsuWifiEntry) != null) {
            return osuWifiEntry.getConnectedState();
        }
        return super.getConnectedState();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String getTitle() {
        return this.mFriendlyName;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String getSummary(boolean concise) {
        String str;
        StringJoiner sj = new StringJoiner(this.mContext.getString(R.string.summary_separator));
        if (isExpired()) {
            OsuWifiEntry osuWifiEntry = this.mOsuWifiEntry;
            if (osuWifiEntry != null) {
                sj.add(osuWifiEntry.getSummary(concise));
            } else {
                sj.add(this.mContext.getString(R.string.wifi_passpoint_expired));
            }
        } else if (getConnectedState() == 0) {
            String disconnectDescription = Utils.getDisconnectedStateDescription(this.mContext, this);
            if (TextUtils.isEmpty(disconnectDescription)) {
                if (concise) {
                    sj.add(this.mContext.getString(R.string.wifi_disconnected));
                } else if (!this.mForSavedNetworksPage) {
                    WifiConfiguration wifiConfiguration = this.mWifiConfig;
                    if (wifiConfiguration != null && wifiConfiguration.fromWifiNetworkSuggestion) {
                        Context context = this.mContext;
                        String carrierName = Utils.getCarrierNameForSubId(context, Utils.getSubIdForConfig(context, this.mWifiConfig));
                        String suggestorLabel = Utils.getAppLabel(this.mContext, this.mWifiConfig.creatorName);
                        if (TextUtils.isEmpty(suggestorLabel)) {
                            suggestorLabel = this.mWifiConfig.creatorName;
                        }
                        Context context2 = this.mContext;
                        int i = R.string.available_via_app;
                        Object[] objArr = new Object[1];
                        if (carrierName != null) {
                            str = carrierName;
                        } else {
                            str = suggestorLabel;
                        }
                        objArr[0] = str;
                        sj.add(context2.getString(i, objArr));
                    } else {
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
        String str;
        if (getConnectedState() == 2) {
            String carrierName = null;
            String suggestionOrSpecifierPackageName = this.mWifiInfo != null ? this.mWifiInfo.getRequestingPackageName() : null;
            if (!TextUtils.isEmpty(suggestionOrSpecifierPackageName)) {
                WifiConfiguration wifiConfiguration = this.mWifiConfig;
                if (wifiConfiguration != null) {
                    Context context = this.mContext;
                    carrierName = Utils.getCarrierNameForSubId(context, Utils.getSubIdForConfig(context, wifiConfiguration));
                }
                String suggestorLabel = Utils.getAppLabel(this.mContext, suggestionOrSpecifierPackageName);
                if (TextUtils.isEmpty(suggestorLabel)) {
                    suggestorLabel = suggestionOrSpecifierPackageName;
                }
                Context context2 = this.mContext;
                int i = R.string.connected_via_app;
                Object[] objArr = new Object[1];
                if (carrierName != null) {
                    str = carrierName;
                } else {
                    str = suggestorLabel;
                }
                objArr[0] = str;
                return context2.getString(i, objArr);
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
        return getConnectedState() == 2 ? Utils.getImsiProtectionDescription(this.mContext, this.mWifiConfig) : "";
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String getSsid() {
        if (this.mWifiInfo != null) {
            return WifiInfo.sanitizeSsid(this.mWifiInfo.getSSID());
        }
        WifiConfiguration wifiConfiguration = this.mWifiConfig;
        if (wifiConfiguration != null) {
            return WifiInfo.sanitizeSsid(wifiConfiguration.SSID);
        }
        return null;
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
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isSuggestion() {
        WifiConfiguration wifiConfiguration = this.mWifiConfig;
        return wifiConfiguration != null && wifiConfiguration.fromWifiNetworkSuggestion;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isSubscription() {
        return this.mPasspointConfig != null;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public WifiConfiguration getWifiConfiguration() {
        return null;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canConnect() {
        if (!isExpired()) {
            return (this.mLevel == -1 || getConnectedState() != 0 || this.mWifiConfig == null) ? false : true;
        }
        OsuWifiEntry osuWifiEntry = this.mOsuWifiEntry;
        return osuWifiEntry != null && osuWifiEntry.canConnect();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public void connect(WifiEntry.ConnectCallback callback) {
        OsuWifiEntry osuWifiEntry;
        if (isExpired() && (osuWifiEntry = this.mOsuWifiEntry) != null) {
            osuWifiEntry.connect(callback);
            return;
        }
        this.mConnectCallback = callback;
        if (this.mWifiConfig == null) {
            new WifiEntry.ConnectActionListener().onFailure(0);
        }
        this.mWifiManager.connect(this.mWifiConfig, new WifiEntry.ConnectActionListener());
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
            this.mCallbackHandler.postDelayed(new Runnable() { // from class: com.android.wifitrackerlib.-$$Lambda$PasspointWifiEntry$gbiwhcAyeJI2OMF-ikzyiEehxqU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$disconnect$0$PasspointWifiEntry(callback);
                }
            }, 10000L);
            this.mWifiManager.disableEphemeralNetwork(this.mWifiConfig.FQDN);
            this.mWifiManager.disconnect();
        }
    }

    public /* synthetic */ void lambda$disconnect$0$PasspointWifiEntry(WifiEntry.DisconnectCallback callback) {
        if (callback != null && this.mCalledDisconnect) {
            callback.onDisconnectResult(1);
        }
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canForget() {
        return (isSuggestion() || this.mPasspointConfig == null) ? false : true;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public void forget(WifiEntry.ForgetCallback callback) {
        if (!canForget()) {
            return;
        }
        this.mForgetCallback = callback;
        this.mWifiManager.removePasspointConfiguration(this.mPasspointConfig.getHomeSp().getFqdn());
        new WifiEntry.ForgetActionListener().onSuccess();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canSignIn() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public void signIn(WifiEntry.SignInCallback callback) {
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canShare() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canEasyConnect() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public int getMeteredChoice() {
        int i = this.mMeteredOverride;
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 2;
        }
        return 0;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canSetMeteredChoice() {
        return (isSuggestion() || this.mPasspointConfig == null) ? false : true;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public void setMeteredChoice(int meteredChoice) {
        if (!canSetMeteredChoice()) {
            return;
        }
        if (meteredChoice == 0) {
            this.mMeteredOverride = 0;
        } else if (meteredChoice == 1) {
            this.mMeteredOverride = 1;
        } else if (meteredChoice == 2) {
            this.mMeteredOverride = 2;
        } else {
            return;
        }
        this.mWifiManager.setPasspointMeteredOverride(this.mPasspointConfig.getHomeSp().getFqdn(), this.mMeteredOverride);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canSetPrivacy() {
        return (isSuggestion() || this.mPasspointConfig == null) ? false : true;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public int getPrivacy() {
        PasspointConfiguration passpointConfiguration = this.mPasspointConfig;
        return (passpointConfiguration == null || passpointConfiguration.isMacRandomizationEnabled()) ? 1 : 0;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public void setPrivacy(int privacy) {
        if (!canSetPrivacy()) {
            return;
        }
        this.mWifiManager.setMacRandomizationSettingPasspointEnabled(this.mPasspointConfig.getHomeSp().getFqdn(), privacy != 0);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isAutoJoinEnabled() {
        PasspointConfiguration passpointConfiguration = this.mPasspointConfig;
        if (passpointConfiguration != null) {
            return passpointConfiguration.isAutojoinEnabled();
        }
        WifiConfiguration wifiConfiguration = this.mWifiConfig;
        if (wifiConfiguration != null) {
            return wifiConfiguration.allowAutojoin;
        }
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canSetAutoJoinEnabled() {
        return (this.mPasspointConfig == null && this.mWifiConfig == null) ? false : true;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public void setAutoJoinEnabled(boolean enabled) {
        if (this.mPasspointConfig != null) {
            this.mWifiManager.allowAutojoinPasspoint(this.mPasspointConfig.getHomeSp().getFqdn(), enabled);
        } else if (this.mWifiConfig != null) {
            this.mWifiManager.allowAutojoin(this.mWifiConfig.networkId, enabled);
        }
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String getSecurityString(boolean concise) {
        return concise ? this.mContext.getString(R.string.wifi_security_short_eap) : this.mContext.getString(R.string.wifi_security_eap);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isExpired() {
        return this.mSubscriptionExpirationTimeInMillis > 0 && System.currentTimeMillis() >= this.mSubscriptionExpirationTimeInMillis;
    }

    void updatePasspointConfig(PasspointConfiguration passpointConfig) {
        this.mPasspointConfig = passpointConfig;
        if (passpointConfig != null) {
            this.mFriendlyName = passpointConfig.getHomeSp().getFriendlyName();
            this.mSubscriptionExpirationTimeInMillis = passpointConfig.getSubscriptionExpirationTimeMillis();
            this.mMeteredOverride = passpointConfig.getMeteredOverride();
        }
        notifyOnUpdated();
    }

    void updateScanResultInfo(WifiConfiguration wifiConfig, List<ScanResult> homeScanResults, List<ScanResult> roamingScanResults) throws IllegalArgumentException {
        this.mIsRoaming = false;
        this.mWifiConfig = wifiConfig;
        synchronized (this.mLock) {
            this.mCurrentHomeScanResults.clear();
            this.mCurrentRoamingScanResults.clear();
            if (homeScanResults != null) {
                this.mCurrentHomeScanResults.addAll(homeScanResults);
            }
            if (roamingScanResults != null) {
                this.mCurrentRoamingScanResults.addAll(roamingScanResults);
            }
        }
        if (this.mWifiConfig != null) {
            this.mSecurity = Utils.getSecurityTypeFromWifiConfiguration(wifiConfig);
            List<ScanResult> currentScanResults = new ArrayList<>();
            if (homeScanResults != null && !homeScanResults.isEmpty()) {
                currentScanResults.addAll(homeScanResults);
            } else if (roamingScanResults != null && !roamingScanResults.isEmpty()) {
                currentScanResults.addAll(roamingScanResults);
                this.mIsRoaming = true;
            }
            ScanResult bestScanResult = Utils.getBestScanResultByLevel(currentScanResults);
            if (bestScanResult != null) {
                this.mWifiConfig.SSID = "\"" + bestScanResult.SSID + "\"";
            }
            if (getConnectedState() == 0) {
                this.mLevel = bestScanResult != null ? this.mWifiManager.calculateSignalLevel(bestScanResult.level) : -1;
                this.mSpeed = Utils.getAverageSpeedFromScanResults(this.mScoreCache, currentScanResults);
            }
        } else {
            this.mLevel = -1;
        }
        notifyOnUpdated();
    }

    void onScoreCacheUpdated() {
        if (this.mWifiInfo != null) {
            this.mSpeed = Utils.getSpeedFromWifiInfo(this.mScoreCache, this.mWifiInfo);
        } else {
            synchronized (this.mLock) {
                if (!this.mCurrentHomeScanResults.isEmpty()) {
                    this.mSpeed = Utils.getAverageSpeedFromScanResults(this.mScoreCache, this.mCurrentHomeScanResults);
                } else {
                    this.mSpeed = Utils.getAverageSpeedFromScanResults(this.mScoreCache, this.mCurrentRoamingScanResults);
                }
            }
        }
        notifyOnUpdated();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    protected boolean connectionInfoMatches(WifiInfo wifiInfo, NetworkInfo networkInfo) {
        if (!wifiInfo.isPasspointAp()) {
            return false;
        }
        return TextUtils.equals(wifiInfo.getPasspointFqdn(), this.mFqdn);
    }

    static String uniqueIdToPasspointWifiEntryKey(String uniqueId) {
        Preconditions.checkNotNull(uniqueId, "Cannot create key with null unique id!");
        return KEY_PREFIX + uniqueId;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    String getScanResultDescription() {
        return "";
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    String getNetworkSelectionDescription() {
        return Utils.getNetworkSelectionDescription(this.mWifiConfig);
    }

    void setOsuWifiEntry(OsuWifiEntry osuWifiEntry) {
        this.mOsuWifiEntry = osuWifiEntry;
        if (osuWifiEntry != null) {
            osuWifiEntry.setListener(this);
        }
    }

    @Override // com.android.wifitrackerlib.WifiEntry.WifiEntryCallback
    public void onUpdated() {
        notifyOnUpdated();
    }
}
