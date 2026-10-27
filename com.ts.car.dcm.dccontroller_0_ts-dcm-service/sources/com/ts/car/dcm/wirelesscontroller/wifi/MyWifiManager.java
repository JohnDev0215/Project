package com.ts.car.dcm.wirelesscontroller.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.util.Log;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class MyWifiManager {
    private static final int ADDRESS_BYTE = 255;
    private static final int B1 = 8;
    private static final int B2 = 16;
    private static final int B3 = 24;
    public static final int INDEX_0 = 0;
    public static final int INDEX_1 = 1;
    public static final int INDEX_2 = 2;
    public static final int INDEX_3 = 3;
    public static final int KEY_RANDOM_END_AFTER = 13;
    public static final int KEY_RANDOM_END_FRONT = 8;
    public static final int KEY_RANDOM_FIRST_AFTER = 9;
    public static final int KEY_RANDOM_FIRST_FRONT = 0;
    private static final String TAG = MyWifiManager.class.getName();
    private WifiCallback callback;
    private ConnectivityManager mConnectivityManager;
    private Context mContext;
    private WifiConnectionReceiver mWifiConnectionReceiver;
    private WifiManager mWifiManager;

    public MyWifiManager(Context context) {
        this.mContext = context;
    }

    public void init() {
        this.mConnectivityManager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        this.mWifiManager = (WifiManager) this.mContext.getSystemService("wifi");
        if (this.mWifiConnectionReceiver == null) {
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.net.wifi.WIFI_AP_STATE_CHANGED");
            filter.addAction("android.net.wifi.STATE_CHANGE");
            WifiConnectionReceiver wifiConnectionReceiver = new WifiConnectionReceiver();
            this.mWifiConnectionReceiver = wifiConnectionReceiver;
            this.mContext.registerReceiver(wifiConnectionReceiver, filter);
        }
    }

    public void setCallback(WifiCallback callback) {
        this.callback = callback;
    }

    public void destroy() {
        this.mContext.unregisterReceiver(this.mWifiConnectionReceiver);
        this.callback = null;
    }

    private class WifiConnectionReceiver extends BroadcastReceiver {
        private WifiConnectionReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Parcelable parcelableExta;
            String action = intent.getAction();
            if (MyWifiManager.this.callback == null) {
                Log.w(MyWifiManager.TAG, "callback is null");
                return;
            }
            if ("android.net.wifi.WIFI_AP_STATE_CHANGED".equals(action)) {
                int state = intent.getIntExtra("wifi_state", 14);
                MyWifiManager.this.callback.onWifiApStateChanged(state);
                Log.d(MyWifiManager.TAG, "onReceive, action:" + action + ", state:" + state);
                return;
            }
            if ("android.net.wifi.STATE_CHANGE".equals(action) && (parcelableExta = intent.getParcelableExtra("networkInfo")) != null) {
                NetworkInfo networkInfo = (NetworkInfo) parcelableExta;
                String bssid = intent.getStringExtra("bssid");
                NetworkInfo.State state2 = networkInfo.getState();
                Log.d(MyWifiManager.TAG, "onReceive, network state changed receive info:" + bssid + ", event:" + state2);
                int serverAddress = -1;
                DhcpInfo dhcpInfo = MyWifiManager.this.mWifiManager.getDhcpInfo();
                if (dhcpInfo != null) {
                    serverAddress = dhcpInfo.serverAddress;
                }
                WifiDeviceInfo wifiDeviceInfo = new WifiDeviceInfo();
                wifiDeviceInfo.macAddress = bssid;
                wifiDeviceInfo.ipAddress = MyWifiManager.this.intToInetAddress(serverAddress).getHostAddress();
                wifiDeviceInfo.name = networkInfo.getExtraInfo();
                MyWifiManager.this.callback.onNetworkStateChanged(state2, wifiDeviceInfo);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InetAddress intToInetAddress(int hostAddress) {
        byte[] addressBytes = {(byte) (hostAddress & 255), (byte) ((hostAddress >> 8) & 255), (byte) ((hostAddress >> 16) & 255), (byte) ((hostAddress >> 24) & 255)};
        try {
            return InetAddress.getByAddress(addressBytes);
        } catch (UnknownHostException e) {
            throw new AssertionError();
        }
    }

    public boolean isWifiApEnabled() {
        return this.mWifiManager.isWifiApEnabled();
    }

    public WifiDeviceInfo getWifiDevice() {
        WifiInfo wifiInfo = this.mWifiManager.getConnectionInfo();
        if (wifiInfo != null) {
            int serverAddress = -1;
            DhcpInfo dhcpInfo = this.mWifiManager.getDhcpInfo();
            if (dhcpInfo != null) {
                serverAddress = dhcpInfo.serverAddress;
            }
            WifiDeviceInfo wifiDeviceInfo = new WifiDeviceInfo();
            wifiDeviceInfo.macAddress = wifiInfo.getBSSID();
            wifiDeviceInfo.name = wifiInfo.getSSID();
            wifiDeviceInfo.ipAddress = intToInetAddress(serverAddress).getHostAddress();
            return wifiDeviceInfo;
        }
        return null;
    }

    public void setSoftApConfiguration(SoftApConfiguration config) {
        this.mWifiManager.setSoftApConfiguration(config);
    }

    public SoftApConfiguration getSoftApConfiguration() {
        return this.mWifiManager.getSoftApConfiguration();
    }

    public void startTethering(int action, boolean state, final WifiStartTetheringCallback callback) {
        this.mConnectivityManager.startTethering(action, state, new ConnectivityManager.OnStartTetheringCallback() { // from class: com.ts.car.dcm.wirelesscontroller.wifi.MyWifiManager.1
            public void onTetheringStarted() {
                WifiStartTetheringCallback wifiStartTetheringCallback = callback;
                if (wifiStartTetheringCallback != null) {
                    wifiStartTetheringCallback.onTetheringStarted();
                }
            }

            public void onTetheringFailed() {
                WifiStartTetheringCallback wifiStartTetheringCallback = callback;
                if (wifiStartTetheringCallback != null) {
                    wifiStartTetheringCallback.onTetheringFailed();
                }
            }
        });
    }

    public void stopTethering(int action) {
        this.mConnectivityManager.stopTethering(action);
    }

    public void handleStartWifiAp(SoftApConfiguration config, boolean isApSsidRandom) {
        SoftApConfiguration.Builder configBuilder = new SoftApConfiguration.Builder(config);
        configBuilder.setPassphrase("0123456789", 1);
        Log.d(TAG, "isApSsidRandom: " + isApSsidRandom);
        if (isApSsidRandom) {
            String postfix = "";
            String uuid = UUID.randomUUID().toString();
            try {
                byte[] hash = MessageDigest.getInstance("SHA1").digest(uuid.getBytes(Charset.forName("UTF-8")));
                postfix = String.format("-%02x%02x%02x%02x", Byte.valueOf(hash[0]), Byte.valueOf(hash[1]), Byte.valueOf(hash[2]), Byte.valueOf(hash[3]));
            } catch (NoSuchAlgorithmException ex) {
                Log.d(TAG, "WifiConfiguration#getWifiConfiguration remote exception", ex);
            }
            configBuilder.setSsid("Android" + postfix);
            configBuilder.setPassphrase(uuid.substring(0, 8) + uuid.substring(9, 13), 1);
        }
        setSoftApConfiguration(configBuilder.build());
        startTethering(0, false, new WifiStartTetheringCallback() { // from class: com.ts.car.dcm.wirelesscontroller.wifi.MyWifiManager.2
            @Override // com.ts.car.dcm.wirelesscontroller.wifi.WifiStartTetheringCallback
            public void onTetheringStarted() {
                Log.d(MyWifiManager.TAG, "onTetheringStarted");
            }

            @Override // com.ts.car.dcm.wirelesscontroller.wifi.WifiStartTetheringCallback
            public void onTetheringFailed() {
                Log.e(MyWifiManager.TAG, "onTetheringFailed");
            }
        });
    }
}
