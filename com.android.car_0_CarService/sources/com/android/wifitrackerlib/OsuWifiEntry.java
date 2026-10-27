package com.android.wifitrackerlib;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkScoreCache;
import android.net.wifi.hotspot2.OsuProvider;
import android.net.wifi.hotspot2.PasspointConfiguration;
import android.net.wifi.hotspot2.ProvisioningCallback;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Pair;
import androidx.core.util.Preconditions;
import com.android.wifitrackerlib.WifiEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
class OsuWifiEntry extends WifiEntry {
    static final String KEY_PREFIX = "OsuWifiEntry:";
    private final Context mContext;
    private final List<ScanResult> mCurrentScanResults;
    private boolean mIsAlreadyProvisioned;
    private final String mKey;
    private final Object mLock;
    private OsuProvider mOsuProvider;
    private String mOsuStatusString;
    private String mSsid;

    OsuWifiEntry(Context context, Handler callbackHandler, OsuProvider osuProvider, WifiManager wifiManager, WifiNetworkScoreCache scoreCache, boolean forSavedNetworksPage) throws IllegalArgumentException {
        super(callbackHandler, wifiManager, scoreCache, forSavedNetworksPage);
        this.mLock = new Object();
        this.mCurrentScanResults = new ArrayList();
        this.mIsAlreadyProvisioned = false;
        Preconditions.checkNotNull(osuProvider, "Cannot construct with null osuProvider!");
        this.mContext = context;
        this.mOsuProvider = osuProvider;
        this.mKey = osuProviderToOsuWifiEntryKey(osuProvider);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String getKey() {
        return this.mKey;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String getTitle() {
        String friendlyName = this.mOsuProvider.getFriendlyName();
        if (!TextUtils.isEmpty(friendlyName)) {
            return friendlyName;
        }
        if (!TextUtils.isEmpty(this.mSsid)) {
            return this.mSsid;
        }
        Uri serverUri = this.mOsuProvider.getServerUri();
        if (serverUri != null) {
            return serverUri.toString();
        }
        return "";
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String getSummary(boolean concise) {
        String str = this.mOsuStatusString;
        if (str != null) {
            return str;
        }
        if (isAlreadyProvisioned()) {
            return concise ? this.mContext.getString(R.string.wifi_passpoint_expired) : this.mContext.getString(R.string.tap_to_renew_subscription_and_connect);
        }
        return this.mContext.getString(R.string.tap_to_sign_up);
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String getSsid() {
        return this.mSsid;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public int getSecurity() {
        return 0;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String getMacAddress() {
        return null;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isMetered() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isSaved() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isSuggestion() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isSubscription() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public WifiConfiguration getWifiConfiguration() {
        return null;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canConnect() {
        return this.mLevel != -1 && getConnectedState() == 0;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public void connect(WifiEntry.ConnectCallback callback) {
        this.mConnectCallback = callback;
        this.mWifiManager.startSubscriptionProvisioning(this.mOsuProvider, this.mContext.getMainExecutor(), new OsuWifiEntryProvisioningCallback());
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canDisconnect() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public void disconnect(WifiEntry.DisconnectCallback callback) {
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canForget() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public void forget(WifiEntry.ForgetCallback callback) {
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
        return 0;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canSetMeteredChoice() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public void setMeteredChoice(int meteredChoice) {
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public int getPrivacy() {
        return 2;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canSetPrivacy() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public void setPrivacy(int privacy) {
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isAutoJoinEnabled() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean canSetAutoJoinEnabled() {
        return false;
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public void setAutoJoinEnabled(boolean enabled) {
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public String getSecurityString(boolean concise) {
        return "";
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    public boolean isExpired() {
        return false;
    }

    void updateScanResultInfo(List<ScanResult> scanResults) throws IllegalArgumentException {
        if (scanResults == null) {
            scanResults = new ArrayList();
        }
        synchronized (this.mLock) {
            this.mCurrentScanResults.clear();
            this.mCurrentScanResults.addAll(scanResults);
        }
        ScanResult bestScanResult = Utils.getBestScanResultByLevel(scanResults);
        if (bestScanResult != null) {
            this.mSsid = bestScanResult.SSID;
            if (getConnectedState() == 0) {
                this.mLevel = this.mWifiManager.calculateSignalLevel(bestScanResult.level);
            }
        } else {
            this.mLevel = -1;
        }
        notifyOnUpdated();
    }

    static String osuProviderToOsuWifiEntryKey(OsuProvider osuProvider) {
        Preconditions.checkNotNull(osuProvider, "Cannot create key with null OsuProvider!");
        return KEY_PREFIX + osuProvider.getFriendlyName() + "," + osuProvider.getServerUri().toString();
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    protected boolean connectionInfoMatches(WifiInfo wifiInfo, NetworkInfo networkInfo) {
        return wifiInfo.isOsuAp() && TextUtils.equals(wifiInfo.getPasspointProviderFriendlyName(), this.mOsuProvider.getFriendlyName());
    }

    @Override // com.android.wifitrackerlib.WifiEntry
    String getScanResultDescription() {
        return "";
    }

    OsuProvider getOsuProvider() {
        return this.mOsuProvider;
    }

    boolean isAlreadyProvisioned() {
        return this.mIsAlreadyProvisioned;
    }

    void setAlreadyProvisioned(boolean isAlreadyProvisioned) {
        this.mIsAlreadyProvisioned = isAlreadyProvisioned;
    }

    class OsuWifiEntryProvisioningCallback extends ProvisioningCallback {
        OsuWifiEntryProvisioningCallback() {
        }

        public void onProvisioningFailure(int status) {
            if (TextUtils.equals(OsuWifiEntry.this.mOsuStatusString, OsuWifiEntry.this.mContext.getString(R.string.osu_completing_sign_up))) {
                OsuWifiEntry osuWifiEntry = OsuWifiEntry.this;
                osuWifiEntry.mOsuStatusString = osuWifiEntry.mContext.getString(R.string.osu_sign_up_failed);
            } else {
                OsuWifiEntry osuWifiEntry2 = OsuWifiEntry.this;
                osuWifiEntry2.mOsuStatusString = osuWifiEntry2.mContext.getString(R.string.osu_connect_failed);
            }
            if (OsuWifiEntry.this.mConnectCallback != null) {
                OsuWifiEntry.this.mConnectCallback.onConnectResult(2);
            }
            OsuWifiEntry.this.notifyOnUpdated();
        }

        public void onProvisioningStatus(int status) {
            String newStatusString = null;
            switch (status) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    newStatusString = String.format(OsuWifiEntry.this.mContext.getString(R.string.osu_opening_provider), OsuWifiEntry.this.getTitle());
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                    newStatusString = OsuWifiEntry.this.mContext.getString(R.string.osu_completing_sign_up);
                    break;
            }
            boolean updated = true ^ TextUtils.equals(OsuWifiEntry.this.mOsuStatusString, newStatusString);
            OsuWifiEntry.this.mOsuStatusString = newStatusString;
            if (updated) {
                OsuWifiEntry.this.notifyOnUpdated();
            }
        }

        public void onProvisioningComplete() {
            ScanResult bestScan;
            OsuWifiEntry osuWifiEntry = OsuWifiEntry.this;
            osuWifiEntry.mOsuStatusString = osuWifiEntry.mContext.getString(R.string.osu_sign_up_complete);
            OsuWifiEntry.this.notifyOnUpdated();
            PasspointConfiguration passpointConfig = (PasspointConfiguration) OsuWifiEntry.this.mWifiManager.getMatchingPasspointConfigsForOsuProviders(Collections.singleton(OsuWifiEntry.this.mOsuProvider)).get(OsuWifiEntry.this.mOsuProvider);
            if (passpointConfig == null) {
                if (OsuWifiEntry.this.mConnectCallback != null) {
                    OsuWifiEntry.this.mConnectCallback.onConnectResult(2);
                    return;
                }
                return;
            }
            String uniqueId = passpointConfig.getUniqueId();
            Iterator it = OsuWifiEntry.this.mWifiManager.getAllMatchingWifiConfigs(OsuWifiEntry.this.mWifiManager.getScanResults()).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Pair<WifiConfiguration, Map<Integer, List<ScanResult>>> pairing = (Pair) it.next();
                WifiConfiguration config = (WifiConfiguration) pairing.first;
                if (TextUtils.equals(config.getKey(), uniqueId)) {
                    List<ScanResult> homeScans = (List) ((Map) pairing.second).get(0);
                    List<ScanResult> roamingScans = (List) ((Map) pairing.second).get(1);
                    if (homeScans != null && !homeScans.isEmpty()) {
                        bestScan = Utils.getBestScanResultByLevel(homeScans);
                    } else if (roamingScans != null && !roamingScans.isEmpty()) {
                        bestScan = Utils.getBestScanResultByLevel(roamingScans);
                    }
                    config.SSID = "\"" + bestScan.SSID + "\"";
                    OsuWifiEntry.this.mWifiManager.connect(config, null);
                    return;
                }
            }
            if (OsuWifiEntry.this.mConnectCallback != null) {
                OsuWifiEntry.this.mConnectCallback.onConnectResult(2);
            }
        }
    }
}
