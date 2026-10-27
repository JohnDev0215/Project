package com.android.systemui.util.wifi;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkKey;
import android.net.NetworkRequest;
import android.net.NetworkScoreManager;
import android.net.ScoredNetwork;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkScoreCache;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import com.android.systemui.R;
import com.android.systemui.statusbar.policy.NetworkControllerImpl;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WifiStatusTracker {
    public boolean connected;
    public boolean enabled;
    public boolean isCaptivePortal;
    public boolean isDefaultNetwork;
    public int level;
    private final WifiNetworkScoreCache.CacheListener mCacheListener;
    private final Runnable mCallback;
    private final ConnectivityManager mConnectivityManager;
    private final Context mContext;
    private Network mDefaultNetwork;
    private final ConnectivityManager.NetworkCallback mDefaultNetworkCallback;
    private NetworkCapabilities mDefaultNetworkCapabilities;
    private final Handler mHandler;
    private final ConnectivityManager.NetworkCallback mNetworkCallback;
    private final NetworkRequest mNetworkRequest;
    private final NetworkScoreManager mNetworkScoreManager;
    private WifiInfo mWifiInfo;
    private final WifiManager mWifiManager;
    private final WifiNetworkScoreCache mWifiNetworkScoreCache;
    public int rssi;
    public String ssid;
    public int state;
    public String statusLabel;

    public WifiStatusTracker(Context context, WifiManager wifiManager, NetworkScoreManager networkScoreManager, ConnectivityManager connectivityManager, Runnable callback) {
        Handler handler = new Handler(Looper.getMainLooper());
        this.mHandler = handler;
        this.mCacheListener = new WifiNetworkScoreCache.CacheListener(handler) { // from class: com.android.systemui.util.wifi.WifiStatusTracker.1
            public void networkCacheUpdated(List<ScoredNetwork> updatedNetworks) {
                WifiStatusTracker.this.updateStatusLabel();
                WifiStatusTracker.this.mCallback.run();
            }
        };
        this.mNetworkRequest = new NetworkRequest.Builder().clearCapabilities().addCapability(15).addTransportType(1).build();
        this.mNetworkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.android.systemui.util.wifi.WifiStatusTracker.2
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                WifiStatusTracker.this.updateStatusLabel();
                WifiStatusTracker.this.mCallback.run();
            }
        };
        this.mDefaultNetworkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.android.systemui.util.wifi.WifiStatusTracker.3
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onCapabilitiesChanged(Network network, NetworkCapabilities nc) {
                WifiStatusTracker.this.mDefaultNetwork = network;
                WifiStatusTracker.this.mDefaultNetworkCapabilities = nc;
                WifiStatusTracker.this.updateStatusLabel();
                WifiStatusTracker.this.mCallback.run();
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network) {
                WifiStatusTracker.this.mDefaultNetwork = null;
                WifiStatusTracker.this.mDefaultNetworkCapabilities = null;
                WifiStatusTracker.this.updateStatusLabel();
                WifiStatusTracker.this.mCallback.run();
            }
        };
        this.mDefaultNetwork = null;
        this.mDefaultNetworkCapabilities = null;
        this.mContext = context;
        this.mWifiManager = wifiManager;
        this.mWifiNetworkScoreCache = new WifiNetworkScoreCache(context);
        this.mNetworkScoreManager = networkScoreManager;
        this.mConnectivityManager = connectivityManager;
        this.mCallback = callback;
    }

    public void setListening(boolean listening) {
        if (listening) {
            this.mNetworkScoreManager.registerNetworkScoreCache(1, this.mWifiNetworkScoreCache, 1);
            this.mWifiNetworkScoreCache.registerListener(this.mCacheListener);
            this.mConnectivityManager.registerNetworkCallback(this.mNetworkRequest, this.mNetworkCallback, this.mHandler);
            this.mConnectivityManager.registerDefaultNetworkCallback(this.mDefaultNetworkCallback, this.mHandler);
            return;
        }
        this.mNetworkScoreManager.unregisterNetworkScoreCache(1, this.mWifiNetworkScoreCache);
        this.mWifiNetworkScoreCache.unregisterListener();
        this.mConnectivityManager.unregisterNetworkCallback(this.mNetworkCallback);
        this.mConnectivityManager.unregisterNetworkCallback(this.mDefaultNetworkCallback);
    }

    public void fetchInitialState() {
        if (this.mWifiManager == null) {
            return;
        }
        updateWifiState();
        NetworkInfo networkInfo = this.mConnectivityManager.getNetworkInfo(1);
        boolean z = networkInfo != null && networkInfo.isConnected();
        this.connected = z;
        this.mWifiInfo = null;
        this.ssid = null;
        if (z) {
            WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
            this.mWifiInfo = connectionInfo;
            if (connectionInfo != null) {
                if (connectionInfo.isPasspointAp() || this.mWifiInfo.isOsuAp()) {
                    this.ssid = this.mWifiInfo.getPasspointProviderFriendlyName();
                } else {
                    this.ssid = getValidSsid(this.mWifiInfo);
                }
                updateRssi(this.mWifiInfo.getRssi());
                maybeRequestNetworkScore();
            }
        }
        updateStatusLabel();
    }

    public void handleBroadcast(Intent intent) {
        if (this.mWifiManager == null) {
            return;
        }
        String action = intent.getAction();
        if (action.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
            updateWifiState();
            return;
        }
        if (action.equals("android.net.wifi.STATE_CHANGE")) {
            updateWifiState();
            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
            boolean z = networkInfo != null && networkInfo.isConnected();
            this.connected = z;
            this.mWifiInfo = null;
            this.ssid = null;
            if (z) {
                WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
                this.mWifiInfo = connectionInfo;
                if (connectionInfo != null) {
                    if (connectionInfo.isPasspointAp() || this.mWifiInfo.isOsuAp()) {
                        this.ssid = this.mWifiInfo.getPasspointProviderFriendlyName();
                    } else {
                        this.ssid = getValidSsid(this.mWifiInfo);
                    }
                    updateRssi(this.mWifiInfo.getRssi());
                    maybeRequestNetworkScore();
                }
            }
            updateStatusLabel();
            this.mCallback.run();
            return;
        }
        if (action.equals("android.net.wifi.RSSI_CHANGED")) {
            updateRssi(intent.getIntExtra("newRssi", -200));
            updateStatusLabel();
            this.mCallback.run();
        }
    }

    private void updateWifiState() {
        int wifiState = this.mWifiManager.getWifiState();
        this.state = wifiState;
        this.enabled = wifiState == 3;
    }

    private void updateRssi(int newRssi) {
        this.rssi = newRssi;
        this.level = this.mWifiManager.calculateSignalLevel(newRssi);
    }

    private void maybeRequestNetworkScore() {
        NetworkKey networkKeyCreateFromWifiInfo = NetworkKey.createFromWifiInfo(this.mWifiInfo);
        if (this.mWifiNetworkScoreCache.getScoredNetwork(networkKeyCreateFromWifiInfo) == null) {
            this.mNetworkScoreManager.requestScores(new NetworkKey[]{networkKeyCreateFromWifiInfo});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateStatusLabel() {
        NetworkCapabilities networkCapabilities;
        NetworkCapabilities networkCapabilities2;
        this.isDefaultNetwork = false;
        NetworkCapabilities networkCapabilities3 = this.mDefaultNetworkCapabilities;
        if (networkCapabilities3 != null) {
            this.isDefaultNetwork = networkCapabilities3.hasTransport(1);
        }
        if (this.isDefaultNetwork) {
            networkCapabilities = this.mDefaultNetworkCapabilities;
        } else {
            networkCapabilities = this.mConnectivityManager.getNetworkCapabilities(this.mWifiManager.getCurrentNetwork());
        }
        this.isCaptivePortal = false;
        if (networkCapabilities != null) {
            if (networkCapabilities.hasCapability(17)) {
                this.statusLabel = this.mContext.getString(R.string.wifi_status_sign_in_required);
                this.isCaptivePortal = true;
                return;
            }
            if (networkCapabilities.hasCapability(24)) {
                this.statusLabel = this.mContext.getString(R.string.wifi_limited_connection);
                return;
            }
            if (!networkCapabilities.hasCapability(16)) {
                Settings.Global.getString(this.mContext.getContentResolver(), "private_dns_mode");
                if (networkCapabilities.isPrivateDnsBroken()) {
                    this.statusLabel = this.mContext.getString(R.string.private_dns_broken);
                    return;
                } else {
                    this.statusLabel = this.mContext.getString(R.string.wifi_status_no_internet);
                    return;
                }
            }
            if (!this.isDefaultNetwork && (networkCapabilities2 = this.mDefaultNetworkCapabilities) != null && networkCapabilities2.hasTransport(0)) {
                this.statusLabel = this.mContext.getString(R.string.wifi_connected_low_quality);
                return;
            }
        }
        ScoredNetwork scoredNetwork = this.mWifiNetworkScoreCache.getScoredNetwork(NetworkKey.createFromWifiInfo(this.mWifiInfo));
        this.statusLabel = scoredNetwork == null ? null : AccessPoint.getSpeedLabel(this.mContext, scoredNetwork, this.rssi);
    }

    public void refreshLocale() {
        updateStatusLabel();
        this.mCallback.run();
    }

    private String getValidSsid(WifiInfo info) {
        String ssid = info.getSSID();
        if (ssid != null && !NetworkControllerImpl.NONE.equals(ssid)) {
            return ssid;
        }
        List<WifiConfiguration> configuredNetworks = this.mWifiManager.getConfiguredNetworks();
        int size = configuredNetworks.size();
        for (int i = 0; i < size; i++) {
            if (configuredNetworks.get(i).networkId == info.getNetworkId()) {
                return configuredNetworks.get(i).SSID;
            }
        }
        return null;
    }
}
