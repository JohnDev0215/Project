package com.amazon.alexa.comms.mediaInsights.service.deviceDetails;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkDetailsCollector {

    @NonNull
    private Context appContext;

    @NonNull
    private String networkDetailsSharedPrefFileName;

    public enum NetworkDetailKeys {
        DOWN_LINK_SPEED,
        UP_LINK_SPEED,
        WIFI_LINK_SPEED,
        WIFI_LINK_SPEED_UNITS,
        WIFI_SIGNAL_STRENGTH,
        WIFI_FREQUENCY,
        WIFI_FREQUENCY_UNITS
    }

    public NetworkDetailsCollector(@NonNull Context context, @NonNull String str) {
        if (context == null) {
            throw new IllegalArgumentException("appContext is null");
        }
        if (str == null) {
            throw new IllegalArgumentException("networkDetailsSharedPrefFileName is null");
        }
        this.appContext = context;
        this.networkDetailsSharedPrefFileName = str;
    }

    public void collect() {
        HashMap map = new HashMap();
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.appContext.getApplicationContext().getSystemService("connectivity");
            WifiManager wifiManager = (WifiManager) this.appContext.getApplicationContext().getSystemService("wifi");
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                map.putAll(captureNetworkCapabilityDetails(connectivityManager));
                map.putAll(captureWifiDetails(wifiManager));
            }
            saveNetworkDetails(map);
        } catch (Throwable th) {
            Log.d("MediaInsightsPublisher", "Failed to capture network details", th);
        }
    }

    private Map<String, String> captureNetworkCapabilityDetails(ConnectivityManager connectivityManager) {
        Network network;
        HashMap map = new HashMap();
        if (connectivityManager == null) {
            Log.d("MediaInsightsPublisher", "Connectivity Manager is null or wifi is not enabled");
            return map;
        }
        try {
            Network[] allNetworks = connectivityManager.getAllNetworks();
            int length = allNetworks.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    network = null;
                    break;
                }
                network = allNetworks[i];
                if (connectivityManager.getNetworkInfo(network).isConnected()) {
                    break;
                }
                i++;
            }
            NetworkCapabilities networkCapabilities = network != null ? connectivityManager.getNetworkCapabilities(network) : null;
            if (networkCapabilities == null) {
                return map;
            }
            int linkDownstreamBandwidthKbps = networkCapabilities.getLinkDownstreamBandwidthKbps();
            int linkUpstreamBandwidthKbps = networkCapabilities.getLinkUpstreamBandwidthKbps();
            map.put(NetworkDetailKeys.DOWN_LINK_SPEED.toString(), String.valueOf(linkDownstreamBandwidthKbps));
            map.put(NetworkDetailKeys.UP_LINK_SPEED.toString(), String.valueOf(linkUpstreamBandwidthKbps));
        } catch (Throwable th) {
            Log.d("MediaInsightsPublisher", "Could not capture network details", th);
        }
        return map;
    }

    private Map<String, String> captureWifiDetails(WifiManager wifiManager) {
        HashMap map = new HashMap();
        if (wifiManager == null || !wifiManager.isWifiEnabled()) {
            Log.d("MediaInsightsPublisher", "Wifi Manager is null or wifi is not enabled");
            return map;
        }
        try {
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo == null) {
                return map;
            }
            int linkSpeed = connectionInfo.getLinkSpeed();
            int frequency = connectionInfo.getFrequency();
            int iCalculateSignalLevel = WifiManager.calculateSignalLevel(connectionInfo.getRssi(), 10);
            map.put(NetworkDetailKeys.WIFI_LINK_SPEED.toString(), String.valueOf(linkSpeed));
            map.put(NetworkDetailKeys.WIFI_LINK_SPEED_UNITS.toString(), "Mbps");
            map.put(NetworkDetailKeys.WIFI_FREQUENCY.toString(), String.valueOf(frequency));
            map.put(NetworkDetailKeys.WIFI_FREQUENCY_UNITS.toString(), "MHz");
            map.put(NetworkDetailKeys.WIFI_SIGNAL_STRENGTH.toString(), String.valueOf(iCalculateSignalLevel));
        } catch (Throwable th) {
            Log.d("MediaInsightsPublisher", "Could not capture wifi details", th);
        }
        return map;
    }

    private void saveNetworkDetails(Map<String, String> map) {
        SharedPreferences.Editor editorEdit = this.appContext.getSharedPreferences(this.networkDetailsSharedPrefFileName, 0).edit();
        for (NetworkDetailKeys networkDetailKeys : (NetworkDetailKeys[]) NetworkDetailKeys.class.getEnumConstants()) {
            String string = networkDetailKeys.toString();
            editorEdit.putString(string, map.get(string));
        }
        editorEdit.apply();
    }
}
