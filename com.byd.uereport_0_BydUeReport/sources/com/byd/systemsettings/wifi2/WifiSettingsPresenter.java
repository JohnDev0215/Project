package com.byd.systemsettings.wifi2;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.text.TextUtils;
import com.byd.bt.model.ConnectState;
import com.byd.bt.model.EnableState;
import com.byd.systemsettings.ui.dialog.ServiceDialogHolder;
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
import m4.d;
import x3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class WifiSettingsPresenter extends f<WifiSettingsPager> {
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
    private WifiRssiChangedCallback rssiChangedCallback;
    private WifiScanResultCallback scanResultCallback;
    private int handlerWhat = 0;
    private volatile boolean focusing = false;
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
        public void onConnectFailed(int i8) {
            WeakReference<WifiSettingsPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null) {
                return;
            }
            a.a(WifiSettingsPresenter.TAG, "---onConnectFailed---errCode = " + i8);
            this.ref.get().sendMessage(273, Integer.valueOf(i8));
        }

        @Override // com.byd.systemsettings.wifi2.callback.IWifiConnectStateCallback
        public void onConnectStateChanged(ConnectState connectState, NetworkInfo networkInfo) {
            WeakReference<WifiSettingsPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null) {
                return;
            }
            if (this.ref.get().isAutoConnecting() && connectState != ConnectState.CONNECTED) {
                a.a(WifiSettingsPresenter.TAG, "onConnectStateChanged", "doing auto connect. state: " + connectState + ", info: " + networkInfo);
                if (this.ref.get().getV() != null && !((WifiSettingsPager) this.ref.get().getV()).isReceiveAutoConnectResult()) {
                    return;
                }
            }
            this.ref.get().sendMessage(272, new WifiConnectParams(connectState, networkInfo));
        }

        @Override // com.byd.systemsettings.wifi2.callback.IWifiConnectStateCallback
        public void onSupplicantStateChanged(SupplicantState supplicantState, int i8) {
            WeakReference<WifiSettingsPresenter> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null) {
                return;
            }
            if (!this.ref.get().isAutoConnecting() || supplicantState == SupplicantState.COMPLETED) {
                this.ref.get().sendMessage(275, new SupplicantParams(supplicantState, i8));
                return;
            }
            a.a(WifiSettingsPresenter.TAG, "onSupplicantStateChanged", "doing auto connect. state: " + supplicantState);
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
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null || !this.ref.get().focusing) {
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
            if (weakReference == null || weakReference.get() == null || this.ref.get().getV() == null || !this.ref.get().focusing) {
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
    public void sendMessage(int i8, Object obj) {
        a.a(TAG, "----sendMessage---handlerWhat: " + this.handlerWhat + ", arg1: " + i8);
        if (getV() != null) {
            getV().sendMessage(this.handlerWhat, i8, obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMessageDelay(int i8, long j8, Object obj) {
        a.a(TAG, "----sendMessageDelay---handlerWhat: " + this.handlerWhat + ", arg1: " + i8);
        if (getV() != null) {
            getV().sendMessageDelay(this.handlerWhat, i8, j8, obj);
        }
    }

    public void addNetwork(String str, String str2, CipherType cipherType) {
        a.a(TAG, "--addNetwork-ssid: " + d.f(str) + ", type = " + cipherType);
        this.bwm.connectWithoutScanResult(str, str2, cipherType);
        getV().startAddNetConnectTimer();
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
        a.a(TAG, "---connect--1--result: " + d.f(wifiScanResult.getName()) + ", cap: " + wifiScanResult.getCapabilities());
        this.bwm.connect(wifiScanResult, str);
        getV().startConnectTimer();
    }

    public void connectByConfig(WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration != null) {
            a.a(TAG, ", ----connectByConfig----" + d.f(wifiConfiguration.SSID));
        }
        this.bwm.connect(wifiConfiguration);
        getV().startConnectTimer();
    }

    public void connectOpenType(WifiScanResult wifiScanResult) {
        if (wifiScanResult == null) {
            return;
        }
        a.a(TAG, "---connectOpenType--0--result: " + d.f(wifiScanResult.getName()) + ", cap: " + wifiScanResult.getCapabilities());
        this.bwm.connectOpenType(wifiScanResult);
        getV().startConnectTimer();
    }

    public void disconnect() {
        a.a(TAG, ", ----disconnect----");
        this.bwm.disconnect();
    }

    public void enabled(boolean z7) {
        a.a(TAG, ", enable = " + z7);
        this.bwm.setEnabled(z7);
    }

    public void forgetNetwork(int i8) {
        a.a(TAG, "---forgetNetwork---networkId = " + i8);
        this.bwm.forgetNetwork(i8);
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

    public int getRssiLevel(int i8) {
        return this.bwm.getRssiLevel(i8);
    }

    public WifiConfiguration getSavedConfig(String str) {
        return this.bwm.getSavedConfig(str);
    }

    public CipherType getWifiCipherType(WifiConfiguration wifiConfiguration) {
        return this.bwm.getWifiCipherType(wifiConfiguration);
    }

    public void init(Context context, int i8) {
        a.c(TAG, "---init---");
        this.handlerWhat = i8;
        this.bwm.init(context.getApplicationContext());
        if (this.scanResultCallback == null) {
            this.scanResultCallback = new WifiScanResultCallback(this);
        }
        if (this.rssiChangedCallback == null) {
            this.rssiChangedCallback = new WifiRssiChangedCallback(this);
        }
        BydWifiManager bydWifiManager = this.bwm;
        Class<?> cls = CLASS;
        bydWifiManager.registerWifiStateCallback(cls.getName(), new WifiStateChangedCallback(this));
        this.bwm.registerWifiScanResultCallback(cls.getName(), this.scanResultCallback);
        this.bwm.registerWifiConnectStateCallback(cls.getName(), new WifiConnectStateCallback(this));
        this.bwm.registerWifiRssiChangedCallback(cls.getName(), this.rssiChangedCallback);
    }

    public boolean isAutoConnecting() {
        return this.bwm.isAutoConnecting();
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

    public boolean isWifiStateConnecting() {
        return this.bwm.isWifiStateConnecting();
    }

    public void onUserConnectCompleted() {
        this.bwm.onUserConnectCompleted();
    }

    public void onWindowFocusChanged(boolean z7) {
        this.focusing = z7;
        a.a(TAG, "---onWindowFocusChanged---hasFocus: " + z7);
        if (!z7) {
            BydWifiManager bydWifiManager = this.bwm;
            Class<?> cls = CLASS;
            bydWifiManager.unregisterWifiScanResultCallback(cls.getName());
            this.bwm.unregisterWifiRssiChangedCallback(cls.getName());
            this.bwm.stopAutoScan();
            return;
        }
        if (this.bwm.isAutoScanning()) {
            return;
        }
        if (this.scanResultCallback == null) {
            this.scanResultCallback = new WifiScanResultCallback(this);
        }
        if (this.rssiChangedCallback == null) {
            this.rssiChangedCallback = new WifiRssiChangedCallback(this);
        }
        BydWifiManager bydWifiManager2 = this.bwm;
        Class<?> cls2 = CLASS;
        bydWifiManager2.registerWifiScanResultCallback(cls2.getName(), this.scanResultCallback);
        this.bwm.registerWifiRssiChangedCallback(cls2.getName(), this.rssiChangedCallback);
        if (ServiceDialogHolder.isJustRestoreCompleted() && getV().isDoingConnect()) {
            return;
        }
        this.bwm.startAutoScan();
    }

    public void saveNetwork(String str, String str2, CipherType cipherType) {
        a.a(TAG, ", ssid = " + d.f(str));
        this.bwm.saveNetwork(str, str2, cipherType);
    }

    public void startScan() {
        a.a(TAG, "---startScan---");
        this.bwm.startScan();
    }

    @Override // z3.f
    public void unbindV() {
        super.unbindV();
        a.c(TAG, "---unbindV---bwm: " + this.bwm);
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
        this.scanResultCallback = null;
        this.rssiChangedCallback = null;
    }

    public void forgetNetwork(String str) {
        a.a(TAG, "---forgetNetwork---ssid = " + d.f(str));
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.bwm.forgetNetwork(str);
    }
}
