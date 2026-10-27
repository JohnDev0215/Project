package com.byd.systemsettings.wifi2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import androidx.annotation.RequiresApi;
import b.b.m.c.a;
import b.b.m.g.f;
import com.byd.systemsettings.wifi2.model.CipherType;
import com.byd.systemsettings.wifi2.model.WifiScanResult;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WifiManagerPie {
    public static final String NONE_BSSID = "02:00:00:00:00:00";
    public static final String NONE_SSID = "<unknown ssid>";
    public static final int WIFI_NOT_FOUND = -99;
    public static final int WPA2_PSK = 4;
    public ConnectivityManager cm;
    public WifiConfiguration curConfig;
    private int mNetId;
    public WifiManager.WifiLock mWifiLock;
    public WifiManager mWifiManager;
    public String TAG = "WifiManagerPie";
    public int preNetId = -1;
    public StringBuffer sb = new StringBuffer();

    /* JADX INFO: renamed from: com.byd.systemsettings.wifi2.WifiManagerPie$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
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

    private void doConnect(WifiConfiguration wifiConfiguration) {
        this.curConfig = wifiConfiguration;
        if (wifiConfiguration == null) {
            return;
        }
        this.mNetId = this.mWifiManager.addNetwork(wifiConfiguration);
        a.a(this.TAG, "doConnect", ", ssid: " + f.g(wifiConfiguration.SSID) + ", netid: " + this.mNetId + ", preNetId: " + this.preNetId);
        int i = this.preNetId;
        if (i >= 0 && this.mNetId != i) {
            this.mWifiManager.disableNetwork(i);
        }
        this.cm.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback());
        boolean zEnableNetwork = this.mWifiManager.enableNetwork(this.mNetId, true);
        a.a(this.TAG, "doConnect", ", enable = " + zEnableNetwork);
    }

    private CipherType getCipherTypeFromScanResult(String str) {
        List<ScanResult> scanResult = readScanResult();
        if (scanResult == null || scanResult.isEmpty()) {
            return null;
        }
        for (ScanResult scanResult2 : scanResult) {
            if (scanResult2.SSID.equals(str)) {
                a.a(this.TAG, "--getCipherTypeFromScanResult--ScanResult: " + f.g(scanResult2.SSID));
                return CipherType.get(scanResult2.capabilities);
            }
        }
        return null;
    }

    private WifiConfiguration getNewWifiConfig(String str, String str2, CipherType cipherType) {
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
        a.a(this.TAG, "getNewWifiConfig", "---getNewWifiConfig---ssid: " + f.g(str) + ", type: " + cipherType);
        setWifiConfigSecurity(wifiConfiguration, str2, cipherType);
        return wifiConfiguration;
    }

    private String getText(WifiConfiguration wifiConfiguration) {
        return wifiConfiguration == null ? " is null" : f.g(wifiConfiguration.SSID);
    }

    private WifiConfiguration setExistWifiConfigSecurity(WifiConfiguration wifiConfiguration, String str, CipherType cipherType) {
        if (wifiConfiguration == null || cipherType == null) {
            return null;
        }
        a.a(this.TAG, "---setExistWifiConfigSecurity---ssid: " + wifiConfiguration.SSID + ", type: " + cipherType);
        int i = AnonymousClass1.$SwitchMap$com$byd$systemsettings$wifi2$model$CipherType[cipherType.ordinal()];
        if (i == 1 || i == 2) {
            wifiConfiguration.allowedKeyManagement.set(0);
        } else if (i == 3) {
            wifiConfiguration.wepKeys[0] = "\"" + str + "\"";
        } else if (i == 4 || i == 5 || i == 7 || i == 9) {
            wifiConfiguration.preSharedKey = "\"" + str + "\"";
        }
        return wifiConfiguration;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private WifiConfiguration setWifiConfigSecurity(WifiConfiguration wifiConfiguration, String str, CipherType cipherType) {
        if (wifiConfiguration == null || cipherType == null) {
            return null;
        }
        a.a(this.TAG, "---setWifiConfigSecurity---ssid: " + f.g(wifiConfiguration.SSID) + ", type: " + cipherType);
        switch (AnonymousClass1.$SwitchMap$com$byd$systemsettings$wifi2$model$CipherType[cipherType.ordinal()]) {
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
            default:
                return wifiConfiguration;
        }
    }

    public void acquireWifiLock() {
        this.mWifiLock.acquire();
    }

    public void cancelConnect() {
        disconnectWifi(this.mNetId);
    }

    public void connect(WifiScanResult wifiScanResult) {
        if (wifiScanResult == null) {
            return;
        }
        WifiConfiguration wifiConfiguration = getWifiConfiguration(wifiScanResult.getName());
        a.a(this.TAG, ", connect, config: " + getText(wifiConfiguration));
        if (wifiConfiguration == null) {
            forgetNetwork(wifiScanResult.getResult().SSID);
        } else {
            doConnect(wifiConfiguration);
        }
    }

    public boolean connectWithoutScanResult(String str, String str2, CipherType cipherType) {
        a.a(this.TAG, "connectWithoutScanResult", ", ssid: " + f.g(str) + ", type: " + cipherType);
        if (cipherType != null && !TextUtils.isEmpty(str)) {
            WifiConfiguration wifiConfiguration = getWifiConfiguration(str);
            a.a(this.TAG, "connectWithoutScanResult", "--0--config: " + getText(wifiConfiguration));
            CipherType wifiCipherType = wifiConfiguration != null ? getWifiCipherType(wifiConfiguration) : getCipherTypeFromScanResult(str);
            a.a(this.TAG, "connectWithoutScanResult", "--1--cipherType: " + wifiCipherType);
            if (wifiCipherType != null) {
                CipherType cipherType2 = CipherType.WPA;
                if (cipherType.equals(cipherType2)) {
                    if (!cipherType2.equals(wifiCipherType) && !CipherType.WPA2.equals(wifiCipherType)) {
                        return false;
                    }
                } else if (wifiCipherType != cipherType) {
                    return false;
                }
            }
            if (wifiConfiguration == null) {
                wifiConfiguration = getNewWifiConfig(str, str2, cipherType);
            }
            a.a(this.TAG, "connectWithoutScanResult", "--2--config: " + getText(wifiConfiguration));
            if (wifiConfiguration != null) {
                doConnect(wifiConfiguration);
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

    public void disconnectWifi(int i) {
        this.mWifiManager.disableNetwork(i);
        this.mWifiManager.disconnect();
    }

    @SuppressLint({"MissingPermission"})
    public void forgetAllNetwork() {
        Iterator<WifiConfiguration> it = this.mWifiManager.getConfiguredNetworks().iterator();
        while (it.hasNext()) {
            this.mWifiManager.forget(it.next().networkId, null);
        }
    }

    public void forgetNetwork(int i) {
        a.a(this.TAG, "forgetNetwork", "networkId = " + i);
        this.mWifiManager.forget(i, null);
    }

    public WifiInfo getConnectedWifiInfo() {
        WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
        if (connectionInfo == null || connectionInfo.getSupplicantState() != SupplicantState.COMPLETED) {
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

    public int getRssiLevel(int i) {
        return WifiManager.calculateSignalLevel(i, 5);
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
        a.a(this.TAG, "---getWifiCipherType---" + f.g(wifiConfiguration.SSID) + ", authType: " + authType);
        return authType != 0 ? authType != 1 ? authType != 2 ? authType != 4 ? authType != 8 ? CipherType.UNKNOW : CipherType.SAE : CipherType.WPA2 : CipherType.EAP : CipherType.WPA : CipherType.OPEN;
    }

    @SuppressLint({"MissingPermission"})
    public WifiConfiguration getWifiConfiguration(String str) {
        for (WifiConfiguration wifiConfiguration : this.mWifiManager.getConfiguredNetworks()) {
            if (!wifiConfiguration.SSID.equals(str)) {
                if (wifiConfiguration.SSID.equals("\"" + str + "\"")) {
                }
            }
            a.a(this.TAG, "getWifiConfiguration", ", Configuration is found. ssid: " + f.g(str));
            return wifiConfiguration;
        }
        return null;
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
        a.a(this.TAG, "isWifiSaved", ", scanResult: " + f.g(wifiScanResult.getName()));
        WifiConfiguration wifiConfiguration = getWifiConfiguration(wifiScanResult.getName());
        CipherType wifiCipherType = getWifiCipherType(wifiConfiguration);
        a.a(this.TAG, "isWifiSaved", ", type: " + wifiCipherType + ", wc: " + getText(wifiConfiguration));
        return (wifiCipherType == null || wifiCipherType == CipherType.OPEN || wifiCipherType == CipherType.UNKNOW || wifiConfiguration == null) ? false : true;
    }

    public void onRelease() {
    }

    @RequiresApi(api = 23)
    @SuppressLint({"MissingPermission"})
    public List<ScanResult> readScanResult() {
        return this.mWifiManager.getScanResults();
    }

    public void releaseWifiLock() {
        if (this.mWifiLock.isHeld()) {
            this.mWifiLock.acquire();
        }
    }

    public void saveNetwork(String str, CipherType cipherType) {
        a.a(this.TAG, "---saveNetwork---ssid = " + f.g(str) + ", type: " + cipherType);
        if (cipherType == null || cipherType == CipherType.OPEN || cipherType == CipherType.UNKNOW) {
            return;
        }
        WifiConfiguration wifiConfiguration = getWifiConfiguration(str);
        if (wifiConfiguration == null) {
            a.c(this.TAG, "---saveNetwork---ssid = " + f.g(str) + ", wc: " + wifiConfiguration);
            return;
        }
        a.a(this.TAG, "---saveNetwork---ssid = " + f.g(str) + ", wc.ssid: " + f.g(wifiConfiguration.SSID));
        this.mWifiManager.save(wifiConfiguration, null);
    }

    public void setEnabled(boolean z) {
        this.mWifiManager.setWifiEnabled(z);
    }

    public void startScan() {
        if (!this.mWifiManager.isWifiEnabled()) {
            this.mWifiManager.setWifiEnabled(true);
        }
        boolean zStartScan = this.mWifiManager.startScan();
        a.d(this.TAG, "---startScan----result: " + zStartScan);
    }

    public void stopScan() {
    }

    @SuppressLint({"MissingPermission"})
    public void forgetNetwork(String str) {
        a.a(this.TAG, "forgetNetwork", "ssid: " + f.g(str));
        WifiConfiguration wifiConfiguration = getWifiConfiguration(str);
        if (wifiConfiguration != null) {
            this.mWifiManager.forget(wifiConfiguration.networkId, null);
        }
    }

    public void connect(WifiScanResult wifiScanResult, String str) {
        if (wifiScanResult == null) {
            return;
        }
        WifiConfiguration newWifiConfig = getNewWifiConfig(wifiScanResult.getName(), str, wifiScanResult.getCipherType());
        a.a(this.TAG, ", connect, config: " + getText(newWifiConfig));
        if (newWifiConfig == null) {
            forgetNetwork(wifiScanResult.getResult().SSID);
        } else {
            doConnect(newWifiConfig);
        }
    }

    public void connect(WifiConfiguration wifiConfiguration) {
        doConnect(wifiConfiguration);
    }
}
