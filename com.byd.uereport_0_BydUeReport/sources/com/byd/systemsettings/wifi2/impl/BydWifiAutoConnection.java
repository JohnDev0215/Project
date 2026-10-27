package com.byd.systemsettings.wifi2.impl;

import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.os.SystemClock;
import android.os.SystemProperties;
import com.byd.bt.model.ConnectState;
import com.byd.bt.model.EnableState;
import com.byd.mvp.model.BydData;
import com.byd.systemsettings.wifi2.BydWifiManager;
import com.byd.systemsettings.wifi2.callback.IEnableStateCallback;
import com.byd.systemsettings.wifi2.callback.IWifiConnectStateCallback;
import com.byd.systemsettings.wifi2.callback.IWifiScanResultCallback;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import m4.b;
import m4.d;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class BydWifiAutoConnection implements IEnableStateCallback, IWifiScanResultCallback, IWifiConnectStateCallback {
    private static final Class<?> CLASS = BydWifiAutoConnection.class;
    public static final int DISABLE_AUTO_CONNECT = 0;
    public static final int ENABLE_AUTO_CONNECT = 1;
    public static final String KEY_AUTO_CONNECT = "byd.wifi.autoConnect";
    private static final String TAG = "BydWifiAutoConnection";
    private static final long TIMEOUT_AUTO_CONNECT = 15000;
    private static volatile BydWifiConfiguration bwcConnecting = null;
    private static volatile BydWifiConfiguration bwcConnectingJust = null;
    private static volatile boolean isStrategyEnd = true;
    private volatile boolean autoConnectEnable;
    private volatile boolean autoConnecting;
    private BydWifiManager bwm;
    private ConnectExecuteStateCallback customCallbcak;
    private volatile AutoConnectState mState;
    private volatile SupplicantState preConnectState;
    private List<ScanResult> scanResultList;

    /* JADX INFO: renamed from: com.byd.systemsettings.wifi2.impl.BydWifiAutoConnection$3, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        public static final /* synthetic */ int[] $SwitchMap$android$net$wifi$SupplicantState;

        static {
            int[] iArr = new int[SupplicantState.values().length];
            $SwitchMap$android$net$wifi$SupplicantState = iArr;
            try {
                iArr[SupplicantState.SCANNING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$net$wifi$SupplicantState[SupplicantState.AUTHENTICATING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$net$wifi$SupplicantState[SupplicantState.ASSOCIATING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$net$wifi$SupplicantState[SupplicantState.ASSOCIATED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$net$wifi$SupplicantState[SupplicantState.FOUR_WAY_HANDSHAKE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$android$net$wifi$SupplicantState[SupplicantState.GROUP_HANDSHAKE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public enum AutoConnectState {
        IDLE,
        CONNECTING,
        DISCONNECTED,
        CONNECTED
    }

    public class BydWifiConfiguration extends BydData implements Comparable<BydWifiConfiguration> {
        private String bssid;
        public WifiConfiguration config;
        private int networkId;
        private int rssi;
        private String ssid;

        public BydWifiConfiguration(WifiConfiguration wifiConfiguration, ScanResult scanResult) {
            this.config = wifiConfiguration;
            this.ssid = scanResult.SSID;
            String str = scanResult.BSSID;
            this.bssid = str;
            this.rssi = scanResult.level;
            if (wifiConfiguration != null) {
                this.networkId = wifiConfiguration.networkId;
                wifiConfiguration.BSSID = str;
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            BydWifiConfiguration bydWifiConfiguration = (BydWifiConfiguration) obj;
            return Objects.equals(this.ssid, bydWifiConfiguration.ssid) && Objects.equals(this.bssid, bydWifiConfiguration.bssid);
        }

        public int hashCode() {
            return Objects.hash(this.ssid, this.bssid);
        }

        public String toString() {
            return "BydWifiConfiguration{ssid='" + d.f(this.ssid) + "', bssid='" + d.d(this.bssid) + "', rssi=" + this.rssi + '}';
        }

        @Override // java.lang.Comparable
        public int compareTo(BydWifiConfiguration bydWifiConfiguration) {
            int i8 = this.rssi;
            int i9 = bydWifiConfiguration.rssi;
            if (i8 > i9) {
                return -1;
            }
            return i8 < i9 ? 1 : 0;
        }
    }

    public static class Singleton {
        private static final BydWifiAutoConnection instance = new BydWifiAutoConnection();

        public static BydWifiAutoConnection get() {
            return instance;
        }
    }

    private void doAutoConnect(List<BydWifiConfiguration> list, int i8) {
        int size = list.size();
        a.a(TAG, "doAutoConnectionStrategy_doAutoConnect", "Strategy executing", "foundSize: " + size, "index: " + i8);
        if (i8 < 0 || i8 >= size) {
            return;
        }
        BydWifiConfiguration bydWifiConfiguration = list.get(i8);
        a.a(TAG, "doAutoConnectionStrategy_doAutoConnect", "Strategy executing", "result: " + bydWifiConfiguration);
        if (this.bwm != null) {
            bwcConnecting = bydWifiConfiguration;
            if (this.autoConnectEnable) {
                this.bwm.autoConnect(bydWifiConfiguration.config);
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            while (this.mState == AutoConnectState.CONNECTING) {
                long jLongValue = new BigDecimal(System.currentTimeMillis()).subtract(new BigDecimal(jCurrentTimeMillis)).longValue();
                a.a(TAG, "doAutoConnectionStrategy_doAutoConnect", "Strategy executing", "delta: " + jLongValue);
                if (!this.autoConnectEnable || jLongValue >= TIMEOUT_AUTO_CONNECT) {
                    break;
                } else {
                    SystemClock.sleep(1000L);
                }
            }
            a.a(TAG, "doAutoConnectionStrategy_doAutoConnect", "Strategy executing", "AutoConnectState: " + this.mState);
            if (this.autoConnectEnable && this.mState == AutoConnectState.DISCONNECTED) {
                doAutoConnect(list, i8 + 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doAutoConnectionStrategy(List<BydWifiConfiguration> list) {
        isStrategyEnd = false;
        a.a(TAG, "doAutoConnectionStrategy", "Strategy START.");
        Collections.sort(list);
        this.autoConnecting = true;
        this.mState = AutoConnectState.CONNECTING;
        doAutoConnect(list, 0);
        isStrategyEnd = true;
        this.autoConnecting = false;
        a.a(TAG, "doAutoConnectionStrategy", "Strategy END.");
        this.mState = AutoConnectState.IDLE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doOnUserConnectCompleted() {
        a.a(TAG, "doOnUserConnectCompleted");
        setAutoConnectEnable(true);
        BydWifiManager bydWifiManager = this.bwm;
        if (bydWifiManager != null) {
            bydWifiManager.startAutoScan();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doOnUserConnectStart() {
        a.a(TAG, "doOnUserConnectStart");
        setAutoConnectEnable(false);
        BydWifiManager bydWifiManager = this.bwm;
        if (bydWifiManager != null) {
            bydWifiManager.stopAutoScan();
        }
    }

    private WifiConfiguration findWifiConfiguration(ScanResult scanResult, List<WifiConfiguration> list) {
        for (WifiConfiguration wifiConfiguration : list) {
            if (getSSID(scanResult.SSID).equals(getSSID(wifiConfiguration.SSID))) {
                a.a(TAG, "findWifiConfiguration", "SSID: " + d.f(scanResult.SSID), "sr.BSSID: " + d.d(scanResult.BSSID));
                if (!scanResult.BSSID.equals(wifiConfiguration.BSSID)) {
                    a.c(TAG, "findWifiConfiguration, but the BSSID is different.", "SSID: " + d.f(scanResult.SSID), "sr.BSSID: " + d.d(scanResult.BSSID), "wc.BSSID: " + d.d(wifiConfiguration.BSSID));
                }
                return wifiConfiguration;
            }
        }
        return null;
    }

    private String getSSID(String str) {
        return str == null ? "" : (str.startsWith("\"") || str.endsWith("\"")) ? str.substring(1, str.length() - 1) : str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<BydWifiConfiguration> getSavedConfigList(List<ScanResult> list) {
        if (list != null && !list.isEmpty()) {
            BydWifiManager bydWifiManager = this.bwm;
            List<WifiConfiguration> wifiConfigurationSavedList = bydWifiManager != null ? bydWifiManager.getWifiConfigurationSavedList() : null;
            if (wifiConfigurationSavedList != null && !wifiConfigurationSavedList.isEmpty()) {
                HashMap map = new HashMap();
                for (ScanResult scanResult : list) {
                    WifiConfiguration wifiConfigurationFindWifiConfiguration = findWifiConfiguration(scanResult, wifiConfigurationSavedList);
                    if (wifiConfigurationFindWifiConfiguration != null && map.get(wifiConfigurationFindWifiConfiguration.SSID) == null) {
                        map.put(wifiConfigurationFindWifiConfiguration.SSID, new BydWifiConfiguration(wifiConfigurationFindWifiConfiguration, scanResult));
                    }
                }
                ArrayList arrayList = new ArrayList();
                Iterator it = map.values().iterator();
                while (it.hasNext()) {
                    arrayList.add((BydWifiConfiguration) it.next());
                }
                return arrayList;
            }
        }
        return null;
    }

    private String getWifiInfoText(WifiInfo wifiInfo) {
        if (wifiInfo == null) {
            return "null";
        }
        return d.f(wifiInfo.getSSID()) + ", mac: " + d.d(wifiInfo.getBSSID());
    }

    private void onSupplicantDisconnected(SupplicantState supplicantState, int i8) {
        if (supplicantState != SupplicantState.DISCONNECTED) {
            return;
        }
        a.a(TAG, "onSupplicantDisconnected", "state: " + supplicantState, "errCode: " + i8, "preConnectState: " + this.preConnectState);
        int i9 = AnonymousClass3.$SwitchMap$android$net$wifi$SupplicantState[this.preConnectState.ordinal()];
        if ((i9 == 4 || i9 == 5 || i9 == 6) && i8 == 2 && this.bwm != null && bwcConnectingJust != null) {
            a.e(TAG, "onSupplicantDisconnected", "do forgetNetwork, ssid: " + bwcConnectingJust.ssid, "bssid: " + bwcConnectingJust.bssid);
            this.bwm.forgetNetwork(bwcConnectingJust.networkId);
        }
    }

    public void autoConnectCheck() {
        a.c(TAG, "autoConnectCheck");
        if (!isStrategyEnd) {
            a.e(TAG, "autoConnectCheck", " Strategy executing.");
        } else {
            a.a(TAG, "autoConnectCheck", "Checking");
            b.a(new Runnable() { // from class: com.byd.systemsettings.wifi2.impl.BydWifiAutoConnection.2
                @Override // java.lang.Runnable
                public void run() {
                    if (BydWifiAutoConnection.this.autoConnectEnable) {
                        BydWifiAutoConnection bydWifiAutoConnection = BydWifiAutoConnection.this;
                        List savedConfigList = bydWifiAutoConnection.getSavedConfigList(bydWifiAutoConnection.scanResultList);
                        a.a(BydWifiAutoConnection.TAG, "autoConnectCheck", "savedScanResultList: " + savedConfigList);
                        if (savedConfigList != null && !savedConfigList.isEmpty()) {
                            BydWifiAutoConnection.this.doAutoConnectionStrategy(savedConfigList);
                        }
                        a.a(BydWifiAutoConnection.TAG, "autoConnectCheck", "Check END.");
                    }
                }
            });
        }
    }

    public ConnectExecuteStateCallback getCustomConnectStateCallback() {
        return this.customCallbcak;
    }

    public void init() {
        a.a(TAG, "init");
        BydWifiManager bydWifiManager = BydWifiManager.Singleton.get();
        this.bwm = bydWifiManager;
        Class<?> cls = CLASS;
        bydWifiManager.registerWifiStateCallback(cls.getName(), new IEnableStateCallback() { // from class: t4.a
            @Override // com.byd.systemsettings.wifi2.callback.IEnableStateCallback
            public final void onEnableStateChanged(EnableState enableState) {
                this.f8086a.onEnableStateChanged(enableState);
            }
        });
        this.bwm.registerWifiScanResultCallback(cls.getName(), this);
        this.bwm.registerWifiConnectStateCallback(cls.getName(), this);
        this.bwm.setConnectExecuteStateCallback(this.customCallbcak);
        setFWAutoConnectDisable();
        this.autoConnectEnable = true;
    }

    public boolean isAutoConnectEnable() {
        return this.autoConnectEnable;
    }

    public boolean isAutoConnecting() {
        return this.autoConnecting;
    }

    @Override // com.byd.systemsettings.wifi2.callback.IWifiScanResultCallback
    public boolean isBackground() {
        return true;
    }

    @Override // com.byd.systemsettings.wifi2.callback.IWifiConnectStateCallback
    public void onConnectStateChanged(ConnectState connectState, NetworkInfo networkInfo) {
        if (this.autoConnecting) {
            a.a(TAG, "onConnectStateChanged", "state: " + connectState, "autoConnecting: " + this.autoConnecting);
            if (connectState == ConnectState.CONNECTED) {
                this.mState = AutoConnectState.CONNECTED;
            } else if (connectState == ConnectState.DISCONNECTED) {
                this.mState = AutoConnectState.DISCONNECTED;
            }
        }
    }

    @Override // com.byd.systemsettings.wifi2.callback.IEnableStateCallback
    public void onEnableStateChanged(EnableState enableState) {
        if (enableState != EnableState.ENABLE) {
            return;
        }
        a.a(TAG, "onEnableStateChanged", "state: " + enableState);
    }

    @Override // com.byd.systemsettings.wifi2.callback.IWifiScanResultCallback
    public void onScanResult(List<ScanResult> list) {
        WifiInfo connectedWifiInfo;
        if (this.autoConnectEnable) {
            BydWifiManager bydWifiManager = this.bwm;
            if (bydWifiManager == null || (connectedWifiInfo = bydWifiManager.getConnectedWifiInfo()) == null) {
                this.scanResultList.clear();
                this.scanResultList.addAll(list);
                autoConnectCheck();
                return;
            }
        } else {
            connectedWifiInfo = null;
        }
        a.e(TAG, "onScanResult", "autoConnectEnable: " + this.autoConnectEnable, "connectedInfo: " + getWifiInfoText(connectedWifiInfo));
    }

    @Override // com.byd.systemsettings.wifi2.callback.IWifiConnectStateCallback
    public void onSupplicantStateChanged(SupplicantState supplicantState, int i8) {
        if (SupplicantState.isValidState(supplicantState)) {
            a.a(TAG, "onSupplicantStateChanged", "state: " + supplicantState, "autoConnecting: " + this.autoConnecting);
            if (this.autoConnecting) {
                if (supplicantState == SupplicantState.DISCONNECTED) {
                    bwcConnectingJust = bwcConnecting;
                }
                onSupplicantDisconnected(supplicantState, i8);
                if (supplicantState == SupplicantState.DISCONNECTED) {
                    this.mState = AutoConnectState.DISCONNECTED;
                } else if (supplicantState == SupplicantState.COMPLETED) {
                    this.mState = AutoConnectState.CONNECTED;
                }
                this.preConnectState = supplicantState;
            }
        }
    }

    public void release() {
        a.a(TAG, "release");
        BydWifiManager bydWifiManager = this.bwm;
        if (bydWifiManager != null) {
            Class<?> cls = CLASS;
            bydWifiManager.unregisterWifiStateCallback(cls.getName());
            this.bwm.unregisterWifiScanResultCallback(cls.getName());
            this.bwm.unregisterWifiConnectStateCallback(cls.getName());
            this.bwm = null;
        }
    }

    public void resetEnableState() {
        this.autoConnectEnable = true;
    }

    public void setAutoConnectEnable(boolean z7) {
        a.a(TAG, "setAutoConnectEnable", "enable: " + z7);
        this.autoConnectEnable = z7;
    }

    public void setFWAutoConnectDisable() {
        a.a(TAG, "setFWAutoConnectDisable");
        SystemProperties.set(KEY_AUTO_CONNECT, "0");
    }

    private BydWifiAutoConnection() {
        this.autoConnectEnable = true;
        this.autoConnecting = false;
        this.mState = AutoConnectState.IDLE;
        this.scanResultList = new ArrayList();
        this.customCallbcak = new ConnectExecuteStateCallback() { // from class: com.byd.systemsettings.wifi2.impl.BydWifiAutoConnection.1
            @Override // com.byd.systemsettings.wifi2.impl.ConnectExecuteStateCallback
            public void onConnectCompleted() {
                a.a(BydWifiAutoConnection.TAG, "ConnectExecuteStateCallback_onConnectCompleted");
                BydWifiAutoConnection.this.doOnUserConnectCompleted();
            }

            @Override // com.byd.systemsettings.wifi2.impl.ConnectExecuteStateCallback
            public void onConnectStart() {
                a.a(BydWifiAutoConnection.TAG, "ConnectExecuteStateCallback_onConnectStart");
                BydWifiAutoConnection.this.doOnUserConnectStart();
            }

            @Override // com.byd.systemsettings.wifi2.impl.ConnectExecuteStateCallback
            public void onConnectTimeout() {
                a.a(BydWifiAutoConnection.TAG, "ConnectExecuteStateCallback_onConnectTimeout");
                BydWifiAutoConnection.this.doOnUserConnectCompleted();
            }
        };
    }
}
