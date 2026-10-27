package com.byd.systemsettings.wifi2;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.text.TextUtils;
import b.b.m.c.a;
import b.b.m.e.g;
import b.b.m.g.f;
import com.byd.bt.model.ConnectState;
import com.byd.bt.model.EnableState;
import com.byd.systemsettings.wifi2.BydWifiManager;
import com.byd.systemsettings.wifi2.callback.IEnableStateCallback;
import com.byd.systemsettings.wifi2.callback.IWifiConnectStateCallback;
import com.byd.systemsettings.wifi2.callback.IWifiRssiChangedCallback;
import com.byd.systemsettings.wifi2.callback.IWifiScanResultCallback;
import com.byd.systemsettings.wifi2.model.CipherType;
import com.byd.systemsettings.wifi2.model.SupplicantParams;
import com.byd.systemsettings.wifi2.model.WifiConnectParams;
import com.byd.systemsettings.wifi2.model.WifiScanResult;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WifiSettingsPresenter extends g<WifiSettingsPager> {
    public static final int ADD_NET_CIPHER_TYPE_ERR = 279;
    public static final int ADD_NET_TIME_OUT_MSG = 278;
    public static final int ARG1_WIFI_CONNECT_CHANGED = 272;
    public static final int ARG1_WIFI_CONNECT_FAILED = 273;
    public static final int ARG1_WIFI_ENABLE_CHANGED = 274;
    public static final int ARG1_WIFI_RSSI_CHANGED = 277;
    public static final int ARG1_WIFI_SCAN_RESULT = 257;
    public static final int ARG1_WIFI_SUPPLICANT_STATE_CHANGED = 275;
    private static final Class<?> CLASS = WifiSettingsPresenter.class;
    private static final String TAG = "WifiSettingsPresenter";
    private int handlerWhat = 0;
    private final BydWifiManager bwm = BydWifiManager.Singleton.get();

    public class WifiConnectStateCallback implements IWifiConnectStateCallback {
        private final WeakReference<WifiSettingsPresenter> ref;

        public WifiConnectStateCallback(WifiSettingsPresenter wifiSettingsPresenter) {
            this.ref = new WeakReference<>(wifiSettingsPresenter);
        }

        @Override // com.byd.systemsettings.wifi2.callback.IWifiConnectStateCallback
        public void onCipherTypeErr() {
            WeakReference<WifiSettingsPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null) {
                return;
            }
            a.a(WifiSettingsPresenter.TAG, "---onCipherTypeErr---");
            this.ref.get().sendMessageDelay(279, 1000L, 0);
        }

        @Override // com.byd.systemsettings.wifi2.callback.IWifiConnectStateCallback
        public void onConnectFailed(int i) {
            WeakReference<WifiSettingsPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null) {
                return;
            }
            a.a(WifiSettingsPresenter.TAG, "---onConnectFailed---errCode = " + i);
            this.ref.get().sendMessage(273, Integer.valueOf(i));
        }

        @Override // com.byd.systemsettings.wifi2.callback.IWifiConnectStateCallback
        public void onConnectStateChanged(ConnectState connectState, NetworkInfo networkInfo) {
            WeakReference<WifiSettingsPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null) {
                return;
            }
            a.a(WifiSettingsPresenter.TAG, "---onConnectStateChanged---state: " + connectState + ", info: " + networkInfo);
            this.ref.get().sendMessage(272, new WifiConnectParams(connectState, networkInfo));
        }

        @Override // com.byd.systemsettings.wifi2.callback.IWifiConnectStateCallback
        public void onSupplicantStateChanged(SupplicantState supplicantState, int i) {
            WeakReference<WifiSettingsPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null) {
                return;
            }
            this.ref.get().sendMessage(275, new SupplicantParams(supplicantState, i));
        }
    }

    public class WifiRssiChangedCallback implements IWifiRssiChangedCallback {
        private final WeakReference<WifiSettingsPresenter> ref;

        public WifiRssiChangedCallback(WifiSettingsPresenter wifiSettingsPresenter) {
            this.ref = new WeakReference<>(wifiSettingsPresenter);
        }

        @Override // com.byd.systemsettings.wifi2.callback.IWifiRssiChangedCallback
        public void onRssiChanged() {
            a.a(WifiSettingsPresenter.TAG, "---onRssiChanged---");
            WeakReference<WifiSettingsPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null) {
                return;
            }
            this.ref.get().sendMessage(277, null);
        }
    }

    public class WifiScanResultCallback implements IWifiScanResultCallback {
        private final WeakReference<WifiSettingsPresenter> ref;

        public WifiScanResultCallback(WifiSettingsPresenter wifiSettingsPresenter) {
            this.ref = new WeakReference<>(wifiSettingsPresenter);
        }

        @Override // com.byd.systemsettings.wifi2.callback.IWifiScanResultCallback
        public void onScanFinished() {
            a.a(WifiSettingsPresenter.TAG, "---onScanFinished---");
            WeakReference<WifiSettingsPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null) {
                return;
            }
            ((WifiSettingsPager) this.ref.get().getV()).onScanFinished();
        }

        @Override // com.byd.systemsettings.wifi2.callback.IWifiScanResultCallback
        public void onScanResult(List<ScanResult> list) {
            WeakReference<WifiSettingsPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null) {
                return;
            }
            if (list != null) {
                a.a(WifiSettingsPresenter.TAG, "--onScanResult--scanResultList.size: " + list.size());
            }
            this.ref.get().sendMessage(257, list);
        }

        @Override // com.byd.systemsettings.wifi2.callback.IWifiScanResultCallback
        public void onScanTimeout() {
            a.a(WifiSettingsPresenter.TAG, "---onScanTimeout---");
            WeakReference<WifiSettingsPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null) {
                return;
            }
            ((WifiSettingsPager) this.ref.get().getV()).onScanTimeout();
        }
    }

    public class WifiStateChangedCallback implements IEnableStateCallback {
        private final WeakReference<WifiSettingsPresenter> ref;

        public WifiStateChangedCallback(WifiSettingsPresenter wifiSettingsPresenter) {
            this.ref = new WeakReference<>(wifiSettingsPresenter);
        }

        @Override // com.byd.systemsettings.wifi2.callback.IEnableStateCallback
        public void onEnableStateChanged(EnableState enableState) {
            WeakReference<WifiSettingsPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null) {
                return;
            }
            a.a(WifiSettingsPresenter.TAG, "---onEnableStateChanged---state = " + enableState);
            this.ref.get().sendMessage(274, enableState);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMessage(int i, Object obj) {
        a.a(TAG, "----sendMessage---handlerWhat: " + this.handlerWhat + ", arg1: " + i);
        if (getV() != null) {
            getV().sendMessage(this.handlerWhat, i, obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMessageDelay(int i, long j, Object obj) {
        a.a(TAG, "----sendMessageDelay---handlerWhat: " + this.handlerWhat + ", arg1: " + i);
        if (getV() != null) {
            getV().sendMessageDelay(this.handlerWhat, i, j, obj);
        }
    }

    public void addNetwork(String str, String str2, CipherType cipherType) {
        a.a(TAG, "--addNetwork-ssid: " + f.g(str) + ", type = " + cipherType);
        this.bwm.connectWithoutScanResult(str, str2, cipherType);
    }

    public void autoScan() {
        a.a(TAG, "---autoScan---");
        this.bwm.autoScan();
    }

    public void cancelConnect() {
        this.bwm.cancelConnect();
    }

    public void connect(WifiScanResult wifiScanResult, String str) {
        if (wifiScanResult == null) {
            return;
        }
        a.a(TAG, "---connect--1--result: " + f.g(wifiScanResult.getName()) + ", cap: " + wifiScanResult.getCapabilities());
        this.bwm.connect(wifiScanResult, str);
        getV().startConnectTimer();
    }

    public void connectByConfig(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration != null) {
            a.a(TAG, ", ----connectByConfig----" + f.g(wifiConfiguration.SSID));
        }
        this.bwm.connect(wifiConfiguration);
        getV().startConnectTimer();
    }

    public void connectOpenType(WifiScanResult wifiScanResult) {
        if (wifiScanResult == null) {
            return;
        }
        a.a(TAG, "---connectOpenType--0--result: " + f.g(wifiScanResult.getName()) + ", cap: " + wifiScanResult.getCapabilities());
        this.bwm.connectOpenType(wifiScanResult);
        getV().startConnectTimer();
    }

    public void disconnect() {
        a.a(TAG, ", ----disconnect----");
        this.bwm.disconnect();
    }

    public void enabled(boolean z) {
        a.a(TAG, ", enable = " + z);
        this.bwm.setEnabled(z);
    }

    public void forgetNetwork(int i) {
        a.a(TAG, "---forgetNetwork---networkId = " + i);
        this.bwm.forgetNetwork(i);
    }

    public String getConnectedName() {
        return this.bwm.getConnectedName();
    }

    public WifiInfo getConnectedWifiInfo() {
        return this.bwm.getConnectedWifiInfo();
    }

    public WifiInfo getConnectingWifiInfo() {
        return this.bwm.getConnectingWifiInfo();
    }

    public int getRssiLevel(int i) {
        return this.bwm.getRssiLevel(i);
    }

    public WifiConfiguration getSavedConfig(String str) {
        return this.bwm.getSavedConfig(str);
    }

    public CipherType getWifiCipherType(WifiConfiguration wifiConfiguration) {
        return this.bwm.getWifiCipherType(wifiConfiguration);
    }

    public void init(Context context, int i) {
        a.d(TAG, "---init---");
        this.handlerWhat = i;
        this.bwm.init(context.getApplicationContext());
        BydWifiManager bydWifiManager = this.bwm;
        Class<?> cls = CLASS;
        bydWifiManager.registerWifiStateCallback(cls.getName(), new WifiStateChangedCallback(this));
        this.bwm.registerWifiScanResultCallback(cls.getName(), new WifiScanResultCallback(this));
        this.bwm.registerWifiConnectStateCallback(cls.getName(), new WifiConnectStateCallback(this));
        this.bwm.registerWifiRssiChangedCallback(cls.getName(), new WifiRssiChangedCallback(this));
    }

    public boolean isConnected() {
        return this.bwm.isConnected();
    }

    public boolean isWifiEnabled() {
        return this.bwm.isWifiEnabled();
    }

    public boolean isWifiSaved(WifiScanResult wifiScanResult) {
        return this.bwm.isWifiSaved(wifiScanResult);
    }

    public void saveNetwork(String str, CipherType cipherType) {
        a.a(TAG, ", ssid = " + f.g(str));
        this.bwm.saveNetwork(str, cipherType);
    }

    public void startScan() {
        a.a(TAG, "---startScan---");
        this.bwm.startScan();
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        a.d(TAG, "---unbindV---bwm: " + this.bwm);
        BydWifiManager bydWifiManager = this.bwm;
        if (bydWifiManager != null) {
            Class<?> cls = CLASS;
            bydWifiManager.unregisterWifiStateCallback(cls.getName());
            this.bwm.unregisterWifiScanResultCallback(cls.getName());
            this.bwm.unregisterWifiConnectStateCallback(cls.getName());
            this.bwm.unregisterWifiRssiChangedCallback(cls.getName());
            this.bwm.stopScan();
            this.bwm.stopAutoScan();
        }
    }

    public void forgetNetwork(String str) {
        a.a(TAG, "---forgetNetwork---ssid = " + f.g(str));
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.bwm.forgetNetwork(str);
    }
}
