package com.byd.systemsettings.wifi2.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.byd.systemsettings.wifi2.model.CipherType;
import com.byd.systemsettings.wifi2.model.WifiScanResult;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import m4.d;
import p2.g;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class WifiManagerPie {
    public static final String NONE_BSSID = "02:00:00:00:00:00";
    public static final String NONE_SSID = "<unknown ssid>";
    private static final long TIMEOUT_CONNECT = 25000;
    public static final int WIFI_NOT_FOUND = -99;
    public static final int WPA2_PSK = 4;
    public ConnectivityManager cm;
    public WifiConfiguration curConfig;
    public ConnectExecuteStateCallback mCallback;
    private int mNetId;
    public WifiManager.WifiLock mWifiLock;
    public WifiManager mWifiManager;
    public String TAG = "WifiManagerPie";
    private volatile Timer connectTimer = null;
    public int preNetId = -1;
    public StringBuffer sb = new StringBuffer();

    /* JADX INFO: renamed from: com.byd.systemsettings.wifi2.impl.WifiManagerPie$3, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        public static final /* synthetic */ int[] $SwitchMap$com$byd$systemsettings$wifi2$model$CipherType;

        static {
            int[] iArr = new int[CipherType.values().length];
            $SwitchMap$com$byd$systemsettings$wifi2$model$CipherType = iArr;
            try {
                iArr[CipherType.UNKNOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$CipherType[CipherType.OPEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$CipherType[CipherType.WEP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$CipherType[CipherType.WPA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$CipherType[CipherType.WPA2.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$CipherType[CipherType.WPA3_PERSONAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$CipherType[CipherType.WPA3.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$CipherType[CipherType.SAE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$wifi2$model$CipherType[CipherType.EAP.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    private void doAutoConnect(WifiConfiguration wifiConfiguration) {
        this.curConfig = wifiConfiguration;
        if (wifiConfiguration == null) {
            return;
        }
        this.mNetId = this.mWifiManager.addNetwork(wifiConfiguration);
        try {
            this.cm.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback());
        } catch (RuntimeException e8) {
            a.b(this.TAG, "doAutoConnect", "e: " + e8.toString());
        }
        a.a(this.TAG, "doAutoConnect", "config: " + getText(wifiConfiguration) + ", netid: " + this.mNetId + ", preNetId: " + this.preNetId);
        boolean zEnableNetwork = this.mWifiManager.enableNetwork(this.mNetId, true);
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("enable: ");
        sb.append(zEnableNetwork);
        a.a(str, "doAutoConnect", sb.toString());
        this.preNetId = this.mNetId;
    }

    private void doConnect(WifiConfiguration wifiConfiguration) {
        this.curConfig = wifiConfiguration;
        if (wifiConfiguration == null) {
            return;
        }
        ConnectExecuteStateCallback connectExecuteStateCallback = this.mCallback;
        if (connectExecuteStateCallback != null) {
            connectExecuteStateCallback.onConnectStart();
        }
        this.mNetId = this.mWifiManager.addNetwork(wifiConfiguration);
        try {
            this.cm.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback());
        } catch (RuntimeException e8) {
            a.b(this.TAG, "doConnect", "e: " + e8.toString());
        }
        a.a(this.TAG, "doConnect", "config: " + getText(wifiConfiguration) + ", netid: " + this.mNetId + ", preNetId: " + this.preNetId);
        int i8 = this.preNetId;
        if (i8 < 0) {
            enableNewNetwork(this.mNetId);
        } else {
            this.mWifiManager.disableNetwork(i8);
            g.a().c(new Runnable() { // from class: com.byd.systemsettings.wifi2.impl.WifiManagerPie.1
                @Override // java.lang.Runnable
                public void run() {
                    WifiManagerPie wifiManagerPie = WifiManagerPie.this;
                    wifiManagerPie.enableNewNetwork(wifiManagerPie.mNetId);
                }
            }, 100L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableNewNetwork(int i8) {
        boolean zEnableNetwork = this.mWifiManager.enableNetwork(i8, true);
        a.a(this.TAG, "doConnect_enableNewNetwork", "enable: " + zEnableNetwork);
        this.preNetId = i8;
        removeConnectTimer();
        this.connectTimer = new Timer();
        this.connectTimer.schedule(new TimerTask() { // from class: com.byd.systemsettings.wifi2.impl.WifiManagerPie.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                WifiManagerPie.this.onConnectTimeout();
            }
        }, TIMEOUT_CONNECT);
    }

    private CipherType getCipherTypeFromScanResult(String str) {
        List<ScanResult> scanResult = readScanResult();
        if (scanResult == null || scanResult.isEmpty()) {
            return null;
        }
        for (ScanResult scanResult2 : scanResult) {
            if (scanResult2.SSID.equals(str)) {
                a.a(this.TAG, "--getCipherTypeFromScanResult--ScanResult: " + d.f(scanResult2.SSID));
                return CipherType.get(scanResult2.capabilities);
            }
        }
        return null;
    }

    private WifiConfiguration getNewWifiConfig(String str, String str2, String str3, CipherType cipherType) {
        if (str == null || cipherType == null) {
            return null;
        }
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.allowedAuthAlgorithms.clear();
        wifiConfiguration.allowedGroupCiphers.clear();
        wifiConfiguration.allowedKeyManagement.clear();
        wifiConfiguration.allowedPairwiseCiphers.clear();
        wifiConfiguration.allowedProtocols.clear();
        wifiConfiguration.SSID = "\"" + str + "\"";
        if (str2 != null) {
            wifiConfiguration.BSSID = str2;
        }
        a.a(this.TAG, "getNewWifiConfig", "config: " + getText(wifiConfiguration), ", type: " + cipherType);
        setWifiConfigSecurity(wifiConfiguration, str3, cipherType);
        return wifiConfiguration;
    }

    private String getText(WifiConfiguration wifiConfiguration) {
        return d.j(wifiConfiguration);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConnectTimeout() {
        a.a(this.TAG, "--onConnectTimeout--");
        ConnectExecuteStateCallback connectExecuteStateCallback = this.mCallback;
        if (connectExecuteStateCallback != null) {
            connectExecuteStateCallback.onConnectTimeout();
        }
    }

    private WifiConfiguration setExistWifiConfigSecurity(WifiConfiguration wifiConfiguration, String str, CipherType cipherType) {
        if (wifiConfiguration == null || cipherType == null) {
            return null;
        }
        a.a(this.TAG, "setExistWifiConfigSecurity", "config: " + getText(wifiConfiguration) + ", type: " + cipherType);
        int i8 = AnonymousClass3.$SwitchMap$com$byd$systemsettings$wifi2$model$CipherType[cipherType.ordinal()];
        if (i8 == 1 || i8 == 2) {
            wifiConfiguration.allowedKeyManagement.set(0);
        } else if (i8 == 3) {
            wifiConfiguration.wepKeys[0] = "\"" + str + "\"";
        } else if (i8 == 4 || i8 == 5 || i8 == 7 || i8 == 9) {
            wifiConfiguration.preSharedKey = "\"" + str + "\"";
        }
        return wifiConfiguration;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private WifiConfiguration setWifiConfigSecurity(WifiConfiguration wifiConfiguration, String str, CipherType cipherType) {
        if (wifiConfiguration == null || cipherType == null) {
            return null;
        }
        a.a(this.TAG, "setWifiConfigSecurity", "config: " + getText(wifiConfiguration) + ", type: " + cipherType);
        switch (AnonymousClass3.$SwitchMap$com$byd$systemsettings$wifi2$model$CipherType[cipherType.ordinal()]) {
            case 1:
            case 2:
                wifiConfiguration.allowedKeyManagement.set(0);
                return wifiConfiguration;
            case 3:
                wifiConfiguration.hiddenSSID = true;
                wifiConfiguration.wepKeys[0] = "\"" + str + "\"";
                wifiConfiguration.allowedAuthAlgorithms.set(1);
                wifiConfiguration.allowedGroupCiphers.set(3);
                wifiConfiguration.allowedGroupCiphers.set(2);
                wifiConfiguration.allowedGroupCiphers.set(0);
                wifiConfiguration.allowedGroupCiphers.set(1);
                wifiConfiguration.allowedKeyManagement.set(0);
                wifiConfiguration.wepTxKeyIndex = 0;
                return wifiConfiguration;
            case 4:
                wifiConfiguration.preSharedKey = "\"" + str + "\"";
                wifiConfiguration.hiddenSSID = true;
                wifiConfiguration.allowedAuthAlgorithms.set(0);
                wifiConfiguration.allowedGroupCiphers.set(2);
                wifiConfiguration.allowedKeyManagement.set(1);
                wifiConfiguration.allowedPairwiseCiphers.set(1);
                wifiConfiguration.allowedGroupCiphers.set(3);
                wifiConfiguration.allowedPairwiseCiphers.set(2);
                wifiConfiguration.status = 2;
                return wifiConfiguration;
            case 5:
            case 6:
                wifiConfiguration.preSharedKey = "\"" + str + "\"";
                wifiConfiguration.hiddenSSID = true;
                wifiConfiguration.allowedAuthAlgorithms.set(0);
                wifiConfiguration.allowedGroupCiphers.set(2);
                wifiConfiguration.allowedKeyManagement.set(4);
                wifiConfiguration.allowedPairwiseCiphers.set(1);
                wifiConfiguration.allowedGroupCiphers.set(3);
                wifiConfiguration.allowedPairwiseCiphers.set(2);
                wifiConfiguration.status = 2;
                return wifiConfiguration;
            case 7:
                wifiConfiguration.preSharedKey = "\"" + str + "\"";
                wifiConfiguration.hiddenSSID = true;
                wifiConfiguration.allowedAuthAlgorithms.set(3);
                wifiConfiguration.allowedGroupCiphers.set(2);
                wifiConfiguration.allowedKeyManagement.set(4);
                wifiConfiguration.allowedPairwiseCiphers.set(1);
                wifiConfiguration.allowedGroupCiphers.set(3);
                wifiConfiguration.allowedPairwiseCiphers.set(2);
                wifiConfiguration.status = 2;
                return wifiConfiguration;
            case 8:
                wifiConfiguration.preSharedKey = "\"" + str + "\"";
                wifiConfiguration.hiddenSSID = true;
                wifiConfiguration.allowedAuthAlgorithms.set(3);
                wifiConfiguration.allowedAuthAlgorithms.set(0);
                wifiConfiguration.allowedGroupCiphers.set(2);
                wifiConfiguration.allowedKeyManagement.set(1);
                wifiConfiguration.allowedPairwiseCiphers.set(1);
                wifiConfiguration.allowedGroupCiphers.set(3);
                wifiConfiguration.allowedPairwiseCiphers.set(2);
                wifiConfiguration.status = 2;
                return wifiConfiguration;
            case 9:
                return wifiConfiguration;
            default:
                a.e(this.TAG, "setWifiConfigSecurity", "default--This config is not set", "config: " + getText(wifiConfiguration) + ", type: " + cipherType);
                return wifiConfiguration;
        }
    }

    public void acquireWifiLock() {
        this.mWifiLock.acquire();
    }

    public void autoConnect(WifiConfiguration wifiConfiguration) {
        doAutoConnect(wifiConfiguration);
    }

    public void cancelConnect() {
        disconnectWifi(this.mNetId);
    }

    public void connect(WifiScanResult wifiScanResult) {
        if (wifiScanResult == null) {
            return;
        }
        WifiConfiguration wifiConfiguration = getWifiConfiguration(wifiScanResult.getName(), wifiScanResult.getMac());
        a.a(this.TAG, ", connect, config: " + getText(wifiConfiguration));
        if (wifiConfiguration == null) {
            forgetNetwork(wifiScanResult.getResult().SSID);
        } else {
            doConnect(wifiConfiguration);
        }
    }

    public boolean connectWithoutScanResult(String str, String str2, CipherType cipherType) {
        a.a(this.TAG, "connectWithoutScanResult", ", ssid: " + d.f(str) + ", type: " + cipherType);
        if (cipherType != null && !TextUtils.isEmpty(str)) {
            WifiConfiguration newWifiConfig = getNewWifiConfig(str, null, str2, cipherType);
            a.a(this.TAG, "connectWithoutScanResult", "--2--config: " + getText(newWifiConfig));
            if (newWifiConfig != null) {
                doConnect(newWifiConfig);
                return true;
            }
        }
        return false;
    }

    public void createWifiLock() {
        this.mWifiLock = this.mWifiManager.createWifiLock("Test");
    }

    public void disconnect() {
        WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
        if (connectionInfo == null) {
            return;
        }
        disconnectWifi(connectionInfo.getNetworkId());
    }

    public void disconnectWifi(int i8) {
        a.a(this.TAG, "disconnectWifi", "netId: " + i8);
        this.mWifiManager.disableNetwork(i8);
        this.mWifiManager.disconnect();
    }

    @SuppressLint({"MissingPermission"})
    public void forgetAllNetwork() {
        Iterator<WifiConfiguration> it = this.mWifiManager.getConfiguredNetworks().iterator();
        while (it.hasNext()) {
            this.mWifiManager.forget(it.next().networkId, null);
        }
    }

    public void forgetNetwork(int i8) {
        a.a(this.TAG, "forgetNetwork", "networkId = " + i8);
        this.mWifiManager.forget(i8, null);
    }

    public WifiInfo getConnectedWifiInfo() {
        WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
        if (connectionInfo == null || connectionInfo.getSupplicantState() != SupplicantState.COMPLETED || "<unknown ssid>".equals(connectionInfo.getSSID())) {
            return null;
        }
        return connectionInfo;
    }

    public WifiInfo getConnectingWifiInfo() {
        WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
        if ("<unknown ssid>".equals(connectionInfo.getSSID()) || NONE_BSSID.equals(connectionInfo.getBSSID()) || TextUtils.isEmpty(connectionInfo.getSSID())) {
            return null;
        }
        return connectionInfo;
    }

    public int getRssiLevel(int i8) {
        return WifiManager.calculateSignalLevel(i8, 5);
    }

    public CipherType getWifiCipherType(WifiConfiguration wifiConfiguration) {
        BitSet bitSet;
        if (wifiConfiguration == null || (bitSet = wifiConfiguration.allowedKeyManagement) == null) {
            return CipherType.UNKNOW;
        }
        if (bitSet.cardinality() > 1) {
            return CipherType.UNKNOW;
        }
        int authType = wifiConfiguration.getAuthType();
        a.a(this.TAG, "---getWifiCipherType---" + d.f(wifiConfiguration.SSID) + ", authType: " + authType);
        return authType != 0 ? authType != 1 ? authType != 2 ? authType != 4 ? authType != 8 ? CipherType.UNKNOW : CipherType.SAE : CipherType.WPA2 : CipherType.EAP : CipherType.WPA : CipherType.OPEN;
    }

    @SuppressLint({"MissingPermission"})
    public WifiConfiguration getWifiConfiguration(String str) {
        for (WifiConfiguration wifiConfiguration : this.mWifiManager.getConfiguredNetworks()) {
            if (!wifiConfiguration.SSID.equals(str)) {
                if (wifiConfiguration.SSID.equals("\"" + str + "\"")) {
                }
            }
            a.a(this.TAG, "getWifiConfiguration", ", Configuration is found. config: " + getText(wifiConfiguration));
            return wifiConfiguration;
        }
        return null;
    }

    @SuppressLint({"MissingPermission"})
    public List<WifiConfiguration> getWifiConfigurationSavedList() {
        return this.mWifiManager.getConfiguredNetworks();
    }

    @SuppressLint({"NewApi"})
    public void init(Context context) {
        if (context == null) {
            return;
        }
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
        this.cm = (ConnectivityManager) context.getSystemService("connectivity");
        int wifiState = this.mWifiManager.getWifiState();
        boolean zIsWpa3SaeSupported = this.mWifiManager.isWpa3SaeSupported();
        a.a(this.TAG, "--init--state: " + wifiState + ", wpa3sae: " + zIsWpa3SaeSupported);
    }

    public boolean isWifiEnabled() {
        return this.mWifiManager.isWifiEnabled();
    }

    @SuppressLint({"MissingPermission"})
    public boolean isWifiSaved(WifiScanResult wifiScanResult) {
        if (wifiScanResult == null) {
            return false;
        }
        a.a(this.TAG, "isWifiSaved", ", scanResult: " + d.f(wifiScanResult.getName()));
        WifiConfiguration wifiConfiguration = getWifiConfiguration(wifiScanResult.getName(), wifiScanResult.getMac());
        CipherType wifiCipherType = getWifiCipherType(wifiConfiguration);
        a.a(this.TAG, "isWifiSaved", ", type: " + wifiCipherType + ", wc: " + getText(wifiConfiguration));
        return (wifiCipherType == null || wifiCipherType == CipherType.OPEN || wifiCipherType == CipherType.UNKNOW || wifiConfiguration == null) ? false : true;
    }

    public void onRelease() {
        removeConnectTimer();
    }

    public void onUserConnectCompleted() {
        a.a(this.TAG, "--onUserConnectCompleted--");
        removeConnectTimer();
        ConnectExecuteStateCallback connectExecuteStateCallback = this.mCallback;
        if (connectExecuteStateCallback != null) {
            connectExecuteStateCallback.onConnectCompleted();
        }
    }

    @SuppressLint({"MissingPermission"})
    public List<ScanResult> readScanResult() {
        return this.mWifiManager.getScanResults();
    }

    public void releaseWifiLock() {
        if (this.mWifiLock.isHeld()) {
            this.mWifiLock.acquire();
        }
    }

    public void removeConnectTimer() {
        if (this.connectTimer != null) {
            this.connectTimer.cancel();
            this.connectTimer = null;
        }
    }

    public void saveNetwork(String str, String str2, CipherType cipherType) {
        a.a(this.TAG, "saveNetwork", "ssid: " + d.f(str) + ", bssid: " + d.d(str2) + ", type: " + cipherType);
        if (cipherType == null || cipherType == CipherType.UNKNOW) {
            return;
        }
        WifiConfiguration wifiConfiguration = getWifiConfiguration(str, str2);
        if (wifiConfiguration == null) {
            a.b(this.TAG, "saveNetwork", "save failed. ssid: " + d.f(str) + ", config is null.");
            return;
        }
        m4.a.e(wifiConfiguration, "lastConnected", Long.valueOf(System.currentTimeMillis()));
        a.a(this.TAG, "saveNetwork", "config: " + getText(wifiConfiguration));
        this.mWifiManager.save(wifiConfiguration, null);
    }

    public void setConnectExecuteStateCallback(ConnectExecuteStateCallback connectExecuteStateCallback) {
        this.mCallback = connectExecuteStateCallback;
    }

    public void setEnabled(boolean z7) {
        this.mWifiManager.setWifiEnabled(z7);
    }

    public void startScan() {
        if (!this.mWifiManager.isWifiEnabled()) {
            this.mWifiManager.setWifiEnabled(true);
        }
        boolean zStartScan = this.mWifiManager.startScan();
        a.c(this.TAG, "---startScan----result: " + zStartScan);
    }

    public void stopScan() {
    }

    @SuppressLint({"MissingPermission"})
    public void forgetNetwork(String str) {
        a.a(this.TAG, "forgetNetwork", "ssid: " + d.f(str));
        WifiConfiguration wifiConfiguration = getWifiConfiguration(str);
        if (wifiConfiguration != null) {
            this.mWifiManager.forget(wifiConfiguration.networkId, null);
        }
    }

    public void connect(WifiScanResult wifiScanResult, String str) {
        if (wifiScanResult == null) {
            return;
        }
        WifiConfiguration newWifiConfig = getNewWifiConfig(wifiScanResult.getName(), wifiScanResult.getMac(), str, wifiScanResult.getCipherType());
        a.a(this.TAG, ", connect, config: " + getText(newWifiConfig));
        if (newWifiConfig == null) {
            forgetNetwork(wifiScanResult.getResult().SSID);
        } else {
            doConnect(newWifiConfig);
        }
    }

    @SuppressLint({"MissingPermission"})
    public WifiConfiguration getWifiConfiguration(String str, String str2) {
        for (WifiConfiguration wifiConfiguration : this.mWifiManager.getConfiguredNetworks()) {
            if (!wifiConfiguration.SSID.equals(str)) {
                if (!wifiConfiguration.SSID.equals("\"" + str + "\"")) {
                    continue;
                }
            }
            a.a(this.TAG, "getWifiConfiguration", "The same ssid configuration is found. bssid: " + d.d(str2) + ", config: " + getText(wifiConfiguration));
            if (str2 == null) {
                return wifiConfiguration;
            }
            String str3 = wifiConfiguration.BSSID;
            if (str3 == null) {
                wifiConfiguration.BSSID = str2;
                return wifiConfiguration;
            }
            if (str2.equals(str3)) {
                return wifiConfiguration;
            }
        }
        return null;
    }

    public void connect(WifiConfiguration wifiConfiguration) {
        doConnect(wifiConfiguration);
    }
}
