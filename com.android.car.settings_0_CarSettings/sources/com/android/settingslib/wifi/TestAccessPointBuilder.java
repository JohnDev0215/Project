package com.android.settingslib.wifi;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes6.dex */
public class TestAccessPointBuilder {
    private static final int MAX_RSSI = -55;
    private static final int MIN_RSSI = -100;
    Context mContext;
    private ArrayList<ScanResult> mScanResults;
    private ArrayList<TimestampedScoredNetwork> mScoredNetworkCache;
    private WifiConfiguration mWifiConfig;
    private WifiInfo mWifiInfo;
    private String mBssid = null;
    private int mSpeed = 0;
    private int mRssi = Integer.MIN_VALUE;
    private int mNetworkId = -1;
    private String ssid = "TestSsid";
    private NetworkInfo mNetworkInfo = null;
    private String mFqdn = null;
    private String mProviderFriendlyName = null;
    private int mSecurity = 0;

    public TestAccessPointBuilder(Context context) {
        this.mContext = context;
    }

    public AccessPoint build() {
        Bundle bundle = new Bundle();
        WifiConfiguration wifiConfig = null;
        if (this.mNetworkId != -1) {
            wifiConfig = new WifiConfiguration();
            wifiConfig.networkId = this.mNetworkId;
            wifiConfig.BSSID = this.mBssid;
        }
        bundle.putString("key_ssid", this.ssid);
        bundle.putParcelable("key_config", wifiConfig);
        bundle.putParcelable("key_networkinfo", this.mNetworkInfo);
        bundle.putParcelable("key_wifiinfo", this.mWifiInfo);
        String str = this.mFqdn;
        if (str != null) {
            bundle.putString("key_passpoint_unique_id", str);
        }
        String str2 = this.mProviderFriendlyName;
        if (str2 != null) {
            bundle.putString("key_provider_friendly_name", str2);
        }
        ArrayList<ScanResult> arrayList = this.mScanResults;
        if (arrayList != null) {
            bundle.putParcelableArray("key_scanresults", (Parcelable[]) arrayList.toArray(new Parcelable[arrayList.size()]));
        }
        ArrayList<? extends Parcelable> arrayList2 = this.mScoredNetworkCache;
        if (arrayList2 != null) {
            bundle.putParcelableArrayList("key_scorednetworkcache", arrayList2);
        }
        bundle.putInt("key_security", this.mSecurity);
        bundle.putInt("key_speed", this.mSpeed);
        AccessPoint ap = new AccessPoint(this.mContext, bundle);
        ap.setRssi(this.mRssi);
        return ap;
    }

    public TestAccessPointBuilder setActive(boolean active) {
        if (active) {
            this.mNetworkInfo = new NetworkInfo(8, 8, "TestNetwork", "TestNetwork");
        } else {
            this.mNetworkInfo = null;
        }
        return this;
    }

    public TestAccessPointBuilder setLevel(int level) {
        WifiManager wifiManager = (WifiManager) this.mContext.getSystemService(WifiManager.class);
        int maxSignalLevel = wifiManager.getMaxSignalLevel();
        if (level == 0) {
            this.mRssi = -100;
        } else if (level > maxSignalLevel) {
            this.mRssi = MAX_RSSI;
        } else {
            float outputRange = maxSignalLevel;
            this.mRssi = (int) (((level * 45.0f) / outputRange) - 100.0f);
        }
        return this;
    }

    public TestAccessPointBuilder setNetworkInfo(NetworkInfo info) {
        this.mNetworkInfo = info;
        return this;
    }

    public TestAccessPointBuilder setRssi(int rssi) {
        this.mRssi = rssi;
        return this;
    }

    public TestAccessPointBuilder setSpeed(int speed) {
        this.mSpeed = speed;
        return this;
    }

    public TestAccessPointBuilder setReachable(boolean reachable) {
        if (reachable) {
            if (this.mRssi == Integer.MIN_VALUE) {
                this.mRssi = -100;
            }
        } else {
            this.mRssi = Integer.MIN_VALUE;
        }
        return this;
    }

    public TestAccessPointBuilder setSaved(boolean saved) {
        if (saved) {
            this.mNetworkId = 1;
        } else {
            this.mNetworkId = -1;
        }
        return this;
    }

    public TestAccessPointBuilder setSecurity(int security) {
        this.mSecurity = security;
        return this;
    }

    public TestAccessPointBuilder setSsid(String newSsid) {
        this.ssid = newSsid;
        return this;
    }

    public TestAccessPointBuilder setFqdn(String fqdn) {
        this.mFqdn = fqdn;
        return this;
    }

    public TestAccessPointBuilder setProviderFriendlyName(String friendlyName) {
        this.mProviderFriendlyName = friendlyName;
        return this;
    }

    public TestAccessPointBuilder setWifiInfo(WifiInfo info) {
        this.mWifiInfo = info;
        return this;
    }

    public TestAccessPointBuilder setNetworkId(int networkId) {
        this.mNetworkId = networkId;
        return this;
    }

    public TestAccessPointBuilder setBssid(String bssid) {
        this.mBssid = bssid;
        return this;
    }

    public TestAccessPointBuilder setScanResults(ArrayList<ScanResult> scanResults) {
        this.mScanResults = scanResults;
        return this;
    }

    public TestAccessPointBuilder setScoredNetworkCache(ArrayList<TimestampedScoredNetwork> scoredNetworkCache) {
        this.mScoredNetworkCache = scoredNetworkCache;
        return this;
    }
}
