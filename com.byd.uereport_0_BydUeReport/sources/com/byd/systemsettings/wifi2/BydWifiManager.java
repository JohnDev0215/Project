package com.byd.systemsettings.wifi2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.byd.bt.model.ConnectState;
import com.byd.bt.model.EnableState;
import com.byd.systemsettings.wifi2.cache.WifiCache;
import com.byd.systemsettings.wifi2.callback.IEnableStateCallback;
import com.byd.systemsettings.wifi2.callback.IWifiConnectStateCallback;
import com.byd.systemsettings.wifi2.callback.IWifiRssiChangedCallback;
import com.byd.systemsettings.wifi2.callback.IWifiScanCallback;
import com.byd.systemsettings.wifi2.callback.IWifiScanResultCallback;
import com.byd.systemsettings.wifi2.common.WifiAP;
import com.byd.systemsettings.wifi2.impl.BydWifiAutoConnection;
import com.byd.systemsettings.wifi2.impl.ConnectExecuteStateCallback;
import com.byd.systemsettings.wifi2.impl.WifiManagerPie;
import com.byd.systemsettings.wifi2.impl.WifiManagerQ;
import com.byd.systemsettings.wifi2.model.CipherType;
import com.byd.systemsettings.wifi2.model.WifiScanResult;
import com.byd.systemsettings.wifi2.receive.WifiActionReceiver;
import com.byd.systemsettings.wifi2.receive.WifiScanReceiver;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import m4.d;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class BydWifiManager {
    private static final long DELAY_8000 = 8000;
    private static final int INTERNET_SETTINGS_REQUEST = 33;
    public static final String NONE = "<unknown ssid>";
    private static final String TAG = "BydWifiManager";
    private static final long TIMEOUT_SCAN = 10000;
    private static final int VERSION_Q = 29;
    private static final int WHAT_ON_SCAN_RESULT = 32;
    public static final int WIFI_NOT_FOUND = -99;
    private volatile boolean autoScanAllowed;
    private volatile boolean autoScanning;
    private ConcurrentHashMap<String, IWifiConnectStateCallback> connectCallbackMap;
    private WifiConfiguration curConfig;
    public boolean doConnect;
    private volatile boolean isInited;
    private volatile boolean isScannerRegistered;
    private volatile ConnectState mConnectState;
    private Context mContext;
    private ReadHandler mHandler;
    private WifiManagerPie mWifiManager;
    private WifiActionReceiver mWifiReceiver;
    private WifiScanReceiver mWifiScanReceiver;
    private final int preNetId;
    private volatile long preScanTime;
    private ConcurrentHashMap<String, IWifiRssiChangedCallback> rssiCallbackMap;
    private ConcurrentHashMap<String, IWifiScanResultCallback> scanCallbackMap;
    private volatile Timer scanTimer;
    private volatile boolean scanning;
    private ConcurrentHashMap<String, IEnableStateCallback> stateCallbackMap;

    public static class ReadHandler extends Handler {
        private final WeakReference<BydWifiManager> ref;

        public ReadHandler(BydWifiManager bydWifiManager) {
            this.ref = new WeakReference<>(bydWifiManager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            WeakReference<BydWifiManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || !this.ref.get().isWifiEnabled() || message.what != 32) {
                return;
            }
            this.ref.get().doScan();
        }
    }

    public static class Singleton {
        private static final BydWifiManager instance = new BydWifiManager();

        public static BydWifiManager get() {
            return instance;
        }
    }

    public class WifiConnectCallback implements IWifiConnectStateCallback {
        private final WeakReference<BydWifiManager> ref;

        public WifiConnectCallback(BydWifiManager bydWifiManager) {
            this.ref = new WeakReference<>(bydWifiManager);
        }

        @Override // com.byd.systemsettings.wifi2.callback.IWifiConnectStateCallback
        public void onConnectFailed(int i8) {
            WeakReference<BydWifiManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().onConnectFailed(i8);
        }

        @Override // com.byd.systemsettings.wifi2.callback.IWifiConnectStateCallback
        public void onConnectStateChanged(ConnectState connectState, NetworkInfo networkInfo) {
            WeakReference<BydWifiManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().onConnectStateChanged(connectState, networkInfo);
        }

        @Override // com.byd.systemsettings.wifi2.callback.IWifiConnectStateCallback
        public void onSupplicantStateChanged(SupplicantState supplicantState, int i8) {
            WeakReference<BydWifiManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().onSupplicantStateChanged(supplicantState, i8);
        }
    }

    public class WifiRssiChangedCallback implements IWifiRssiChangedCallback {
        private final WeakReference<BydWifiManager> ref;

        public WifiRssiChangedCallback(BydWifiManager bydWifiManager) {
            this.ref = new WeakReference<>(bydWifiManager);
        }

        @Override // com.byd.systemsettings.wifi2.callback.IWifiRssiChangedCallback
        public void onRssiChanged() {
            a.a(BydWifiManager.TAG, "---onRssiChanged---");
            WeakReference<BydWifiManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().onRssiChanged();
        }
    }

    public class WifiScanResultCallback implements IWifiScanCallback {
        private final WeakReference<BydWifiManager> ref;

        public WifiScanResultCallback(BydWifiManager bydWifiManager) {
            this.ref = new WeakReference<>(bydWifiManager);
        }

        @Override // com.byd.systemsettings.wifi2.callback.IWifiScanCallback
        public void onWifiDiscovered() {
            a.a(BydWifiManager.TAG, "---WifiScanResultCallback---onWifiDiscovered-----ref: " + this.ref);
            WeakReference<BydWifiManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().onWifiDiscovered();
        }
    }

    public class WifiStateCallback implements IEnableStateCallback {
        private final WeakReference<BydWifiManager> ref;

        public WifiStateCallback(BydWifiManager bydWifiManager) {
            this.ref = new WeakReference<>(bydWifiManager);
        }

        @Override // com.byd.systemsettings.wifi2.callback.IEnableStateCallback
        public void onEnableStateChanged(EnableState enableState) {
            WeakReference<BydWifiManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().onEnableStateCchanged(enableState);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doScan() {
        a.a(TAG, "---doScan-----");
        this.mWifiManager.startScan();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onScanTimeout() {
        this.scanning = false;
        a.a(TAG, ", ---onScanTimeout---");
        ConcurrentHashMap<String, IWifiScanResultCallback> concurrentHashMap = this.scanCallbackMap;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        Iterator<IWifiScanResultCallback> it = this.scanCallbackMap.values().iterator();
        while (it.hasNext()) {
            it.next().onScanTimeout();
        }
    }

    private void registerReceiver() {
        if (this.mContext == null) {
            return;
        }
        a.a(TAG, "---registerReceiver---");
        this.mWifiReceiver = new WifiActionReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction(WifiAP.WIFI_AP_CONNECT_STATE_CHANGED_ACTION);
        intentFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE");
        this.mContext.registerReceiver(this.mWifiReceiver, intentFilter);
    }

    private void unregisterReceiver() {
        if (this.mContext == null || this.mWifiReceiver == null) {
            return;
        }
        a.a(TAG, "---unregisterReceiver---");
        try {
            this.mContext.unregisterReceiver(this.mWifiReceiver);
        } catch (RuntimeException e8) {
            a.a(TAG, "e = " + e8.toString());
        }
    }

    public void addNetwork(WifiConfiguration wifiConfiguration) {
    }

    public void autoConnect(WifiConfiguration wifiConfiguration) {
        a.a(TAG, "autoConnect", "config: " + d.j(wifiConfiguration));
        this.mWifiManager.autoConnect(wifiConfiguration);
    }

    public void autoScan() {
        a.a(TAG, ", autoScan, check and auto scan. autoScanAllowed: " + this.autoScanAllowed);
        if (this.autoScanAllowed) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            BigDecimal bigDecimalSubtract = new BigDecimal(jCurrentTimeMillis).subtract(new BigDecimal(this.preScanTime));
            if (isWifiEnabled()) {
                if (!this.mHandler.hasMessages(32) || bigDecimalSubtract.longValue() >= DELAY_8000) {
                    a.a(TAG, ", autoScan, delay to do auto scan. ");
                    this.preScanTime = jCurrentTimeMillis;
                    this.autoScanning = true;
                    this.scanning = false;
                    ReadHandler readHandler = this.mHandler;
                    if (readHandler != null) {
                        readHandler.sendEmptyMessageDelayed(32, DELAY_8000);
                    }
                }
            }
        }
    }

    public void cancelConnect() {
        this.mWifiManager.cancelConnect();
    }

    public void connect(WifiScanResult wifiScanResult, String str) {
        if (wifiScanResult == null) {
            return;
        }
        a.a(TAG, "--connect--0--result = " + d.f(wifiScanResult.getName()));
        this.mWifiManager.connect(wifiScanResult, str);
        this.doConnect = true;
    }

    public void connectOpenType(WifiScanResult wifiScanResult) {
        if (wifiScanResult == null) {
            return;
        }
        a.a(TAG, "--connectOpenType--0--result = " + d.f(wifiScanResult.getName()));
        this.mWifiManager.connect(wifiScanResult, "");
        this.doConnect = true;
    }

    public void connectWithoutScanResult(String str, String str2, CipherType cipherType) {
        this.doConnect = true;
        boolean zConnectWithoutScanResult = this.mWifiManager.connectWithoutScanResult(str, str2, cipherType);
        a.a(TAG, "--connectWithoutScanResult--ssid: " + d.f(str) + ", type: " + cipherType + ", result: " + zConnectWithoutScanResult);
        if (zConnectWithoutScanResult) {
            return;
        }
        onConnectFailed(0);
    }

    public void creatWifiLock() {
        this.mWifiManager.createWifiLock();
    }

    public void createWifiLock() {
    }

    public void disconnect() {
        this.mWifiManager.disconnect();
    }

    public void disconnectWifi(int i8) {
    }

    public void forgetAllNetwork() {
        this.mWifiManager.forgetAllNetwork();
    }

    public void forgetNetwork(int i8) {
        this.mWifiManager.forgetNetwork(i8);
    }

    public String getConnectedName() {
        WifiInfo connectedWifiInfo = this.mWifiManager.getConnectedWifiInfo();
        if (connectedWifiInfo == null) {
            return "";
        }
        String ssid = connectedWifiInfo.getSSID();
        a.a(TAG, "getConnectedName ssid:" + d.f(ssid));
        return ssid.substring(1, ssid.length() - 1);
    }

    public WifiInfo getConnectedWifiInfo() {
        return this.mWifiManager.getConnectedWifiInfo();
    }

    public WifiInfo getConnectingWifiInfo() {
        return this.mWifiManager.getConnectingWifiInfo();
    }

    public String getCurWifiIP() {
        WifiInfo connectedWifiInfo = this.mWifiManager.getConnectedWifiInfo();
        return connectedWifiInfo == null ? "" : parseIp(connectedWifiInfo.getIpAddress());
    }

    public int getRssiLevel(int i8) {
        return this.mWifiManager.getRssiLevel(i8);
    }

    public WifiConfiguration getSavedConfig(String str) {
        return this.mWifiManager.getWifiConfiguration(str);
    }

    public CipherType getWifiCipherType(WifiConfiguration wifiConfiguration) {
        return this.mWifiManager.getWifiCipherType(wifiConfiguration);
    }

    public List<WifiConfiguration> getWifiConfigurationSavedList() {
        return this.mWifiManager.getWifiConfigurationSavedList();
    }

    public void init(Context context) {
        if (context == null || this.isInited) {
            return;
        }
        a.a(TAG, "---init---");
        this.mContext = context.getApplicationContext();
        if (this.mHandler == null) {
            this.mHandler = new ReadHandler(this);
        }
        if (this.stateCallbackMap == null) {
            this.stateCallbackMap = new ConcurrentHashMap<>();
        }
        if (this.scanCallbackMap == null) {
            this.scanCallbackMap = new ConcurrentHashMap<>();
        }
        if (this.connectCallbackMap == null) {
            this.connectCallbackMap = new ConcurrentHashMap<>();
        }
        if (this.rssiCallbackMap == null) {
            this.rssiCallbackMap = new ConcurrentHashMap<>();
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.mWifiManager = new WifiManagerQ();
        } else {
            this.mWifiManager = new WifiManagerPie();
        }
        this.mWifiManager.init(context);
        WifiCache.put(WifiCache.WIFI_STATE, new WifiStateCallback(this));
        WifiCache.put(WifiCache.WIFI_DISCOVER, new WifiScanResultCallback(this));
        WifiCache.put(WifiCache.WIFI_CONNECT_STATE, new WifiConnectCallback(this));
        WifiCache.put(WifiCache.RSSI_CHANGED, new WifiRssiChangedCallback(this));
        registerReceiver();
        BydWifiAutoConnection.Singleton.get().init();
        this.isInited = true;
        this.doConnect = false;
    }

    public boolean isAutoConnecting() {
        return BydWifiAutoConnection.Singleton.get().isAutoConnecting();
    }

    public boolean isAutoScanning() {
        return this.autoScanning;
    }

    public boolean isConnected() {
        return this.mWifiManager.getConnectedWifiInfo() != null;
    }

    public boolean isWifiEnabled() {
        return this.mWifiManager.isWifiEnabled();
    }

    public boolean isWifiSaved(WifiScanResult wifiScanResult) {
        return this.mWifiManager.isWifiSaved(wifiScanResult);
    }

    public boolean isWifiStateConnecting() {
        return this.mConnectState == ConnectState.CONNECTING;
    }

    public void onCipherTypeErr() {
        a.a(TAG, "---onCipherTypeErr---");
        ConcurrentHashMap<String, IWifiConnectStateCallback> concurrentHashMap = this.connectCallbackMap;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        Iterator<IWifiConnectStateCallback> it = this.connectCallbackMap.values().iterator();
        while (it.hasNext()) {
            it.next().onCipherTypeErr();
        }
    }

    public void onConnectFailed(int i8) {
        a.a(TAG, "onConnectFailed", ", errCode = " + i8);
        ConcurrentHashMap<String, IWifiConnectStateCallback> concurrentHashMap = this.connectCallbackMap;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        Iterator<IWifiConnectStateCallback> it = this.connectCallbackMap.values().iterator();
        while (it.hasNext()) {
            it.next().onConnectFailed(i8);
        }
    }

    public void onConnectStateChanged(ConnectState connectState, NetworkInfo networkInfo) {
        this.mConnectState = connectState;
        a.a(TAG, "onConnectStateChanged_manager", ", state = " + connectState + ", info = " + networkInfo);
        ConcurrentHashMap<String, IWifiConnectStateCallback> concurrentHashMap = this.connectCallbackMap;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        Iterator<IWifiConnectStateCallback> it = this.connectCallbackMap.values().iterator();
        while (it.hasNext()) {
            it.next().onConnectStateChanged(connectState, networkInfo);
        }
    }

    public void onEnableStateCchanged(EnableState enableState) {
        a.a(TAG, "state = " + enableState);
        ConcurrentHashMap<String, IEnableStateCallback> concurrentHashMap = this.stateCallbackMap;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        Iterator<IEnableStateCallback> it = this.stateCallbackMap.values().iterator();
        while (it.hasNext()) {
            it.next().onEnableStateChanged(enableState);
        }
    }

    public void onRssiChanged() {
        a.a(TAG, "---onRssiChanged---");
        ConcurrentHashMap<String, IWifiRssiChangedCallback> concurrentHashMap = this.rssiCallbackMap;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        Iterator<IWifiRssiChangedCallback> it = this.rssiCallbackMap.values().iterator();
        while (it.hasNext()) {
            it.next().onRssiChanged();
        }
    }

    public void onScanFinished() {
        a.a(TAG, "---onScanFinished---");
        ConcurrentHashMap<String, IWifiScanResultCallback> concurrentHashMap = this.scanCallbackMap;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        Iterator<IWifiScanResultCallback> it = this.scanCallbackMap.values().iterator();
        while (it.hasNext()) {
            it.next().onScanFinished();
        }
    }

    public void onSupplicantStateChanged(SupplicantState supplicantState, int i8) {
        a.a(TAG, "onSupplicantStateChanged_manager", ", state = " + supplicantState + ",errorReason:" + i8);
        ConcurrentHashMap<String, IWifiConnectStateCallback> concurrentHashMap = this.connectCallbackMap;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        Iterator<IWifiConnectStateCallback> it = this.connectCallbackMap.values().iterator();
        while (it.hasNext()) {
            it.next().onSupplicantStateChanged(supplicantState, i8);
        }
    }

    public void onUserConnectCompleted() {
        a.a(TAG, ", ---onUserConnectCompleted---");
        BydWifiAutoConnection.Singleton.get().resetEnableState();
        WifiManagerPie wifiManagerPie = this.mWifiManager;
        if (wifiManagerPie != null) {
            wifiManagerPie.onUserConnectCompleted();
        }
    }

    @SuppressLint({"MissingPermission"})
    public void onWifiDiscovered() {
        if (isWifiEnabled()) {
            if (this.scanTimer != null) {
                this.scanTimer.cancel();
            }
            List<ScanResult> scanResult = this.mWifiManager.readScanResult();
            String[] strArr = new String[1];
            StringBuilder sb = new StringBuilder();
            sb.append(", ---onWifiDiscovered--- ");
            sb.append(scanResult == null ? "scanResultList is null." : "scanResultList.size:" + scanResult.size());
            strArr[0] = sb.toString();
            a.c(TAG, strArr);
            ArrayList arrayList = new ArrayList();
            for (ScanResult scanResult2 : scanResult) {
                if (!TextUtils.isEmpty(scanResult2.SSID) && !"<unknown ssid>".equals(scanResult2.SSID)) {
                    arrayList.add(scanResult2);
                }
            }
            Iterator<IWifiScanResultCallback> it = this.scanCallbackMap.values().iterator();
            while (it.hasNext()) {
                it.next().onScanResult(arrayList);
            }
            onScanFinished();
            a.a(TAG, ", ---onWifiDiscovered---autoScanning: " + this.autoScanning);
            this.scanning = false;
        }
    }

    public void onWifiNotFound(String str) {
    }

    public String parseIp(int i8) {
        return d.n(i8);
    }

    public void registerScanReceiver() {
        a.a(TAG, "---registerReceiver---isScannerRegistered: " + this.isScannerRegistered);
        if (this.isScannerRegistered) {
            return;
        }
        this.isScannerRegistered = true;
        this.mWifiScanReceiver = new WifiScanReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        intentFilter.addAction("android.net.wifi.RSSI_CHANGED");
        this.mContext.registerReceiver(this.mWifiScanReceiver, intentFilter);
    }

    public void registerWifiConnectStateCallback(String str, IWifiConnectStateCallback iWifiConnectStateCallback) {
        ConcurrentHashMap<String, IWifiConnectStateCallback> concurrentHashMap = this.connectCallbackMap;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.put(str, iWifiConnectStateCallback);
    }

    public void registerWifiRssiChangedCallback(String str, IWifiRssiChangedCallback iWifiRssiChangedCallback) {
        ConcurrentHashMap<String, IWifiRssiChangedCallback> concurrentHashMap = this.rssiCallbackMap;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.put(str, iWifiRssiChangedCallback);
    }

    public void registerWifiScanResultCallback(String str, IWifiScanResultCallback iWifiScanResultCallback) {
        a.a(TAG, ", registerWifiScanResultCallback---k: " + str + ", callback: " + iWifiScanResultCallback);
        if (this.scanCallbackMap == null) {
            return;
        }
        registerScanReceiver();
        this.scanCallbackMap.put(str, iWifiScanResultCallback);
    }

    public void registerWifiStateCallback(String str, IEnableStateCallback iEnableStateCallback) {
        ConcurrentHashMap<String, IEnableStateCallback> concurrentHashMap = this.stateCallbackMap;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.put(str, iEnableStateCallback);
    }

    public boolean release() {
        a.a(TAG, "---release---");
        WifiManagerPie wifiManagerPie = this.mWifiManager;
        if (wifiManagerPie != null) {
            wifiManagerPie.onRelease();
        }
        ReadHandler readHandler = this.mHandler;
        if (readHandler != null) {
            readHandler.removeCallbacksAndMessages(null);
        }
        this.mHandler = null;
        unregisterReceiver();
        unregisterScanReceiver();
        WifiCache.clear();
        this.isInited = false;
        ConcurrentHashMap<String, IEnableStateCallback> concurrentHashMap = this.stateCallbackMap;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
        ConcurrentHashMap<String, IWifiScanResultCallback> concurrentHashMap2 = this.scanCallbackMap;
        if (concurrentHashMap2 != null) {
            concurrentHashMap2.clear();
        }
        ConcurrentHashMap<String, IWifiConnectStateCallback> concurrentHashMap3 = this.connectCallbackMap;
        if (concurrentHashMap3 != null) {
            concurrentHashMap3.clear();
        }
        ConcurrentHashMap<String, IWifiRssiChangedCallback> concurrentHashMap4 = this.rssiCallbackMap;
        if (concurrentHashMap4 != null) {
            concurrentHashMap4.clear();
        }
        this.mContext = null;
        BydWifiAutoConnection.Singleton.get().release();
        return true;
    }

    public void removeWifi(int i8) {
        disconnectWifi(i8);
    }

    public void saveNetwork(String str, String str2, CipherType cipherType) {
        this.mWifiManager.saveNetwork(str, str2, cipherType);
    }

    public void setConnectExecuteStateCallback(ConnectExecuteStateCallback connectExecuteStateCallback) {
        this.mWifiManager.setConnectExecuteStateCallback(connectExecuteStateCallback);
    }

    public void setEnabled(boolean z7) {
        this.mWifiManager.setEnabled(z7);
    }

    public void startAutoScan() {
        a.a(TAG, ", ---startAutoScan---");
        this.autoScanAllowed = true;
        autoScan();
    }

    public void startScan() {
        a.a(TAG, ", startScan, scanning = " + this.scanning);
        if (this.scanning) {
            return;
        }
        this.scanning = true;
        BydWifiAutoConnection.Singleton.get().resetEnableState();
        doScan();
        a.a(TAG, "---startScan---do scan timeout detect.");
        this.scanTimer = new Timer();
        this.scanTimer.schedule(new TimerTask() { // from class: com.byd.systemsettings.wifi2.BydWifiManager.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                BydWifiManager.this.onScanTimeout();
            }
        }, TIMEOUT_SCAN);
    }

    public void stopAutoScan() {
        a.a(TAG, ", ---stopScan---autoScanning: " + this.autoScanning);
        this.autoScanAllowed = false;
        this.autoScanning = false;
        ReadHandler readHandler = this.mHandler;
        if (readHandler != null) {
            readHandler.removeCallbacksAndMessages(null);
        }
    }

    public void stopScan() {
        a.a(TAG, ", ---stopScan---scanning: " + this.scanning);
        this.scanning = false;
        if (this.scanTimer != null) {
            this.scanTimer.cancel();
        }
    }

    public void unregisterScanReceiver() {
        if (this.mContext == null || this.mWifiScanReceiver == null) {
            return;
        }
        BydWifiAutoConnection.Singleton.get().resetEnableState();
        if (this.isScannerRegistered) {
            this.isScannerRegistered = false;
            a.a(TAG, "---unregisterScanReceiver---");
            try {
                this.mContext.unregisterReceiver(this.mWifiScanReceiver);
            } catch (RuntimeException e8) {
                a.b(TAG, ", unregisterScanReceiver--e = " + e8.toString());
            }
        }
    }

    public void unregisterWifiConnectStateCallback(String str) {
        ConcurrentHashMap<String, IWifiConnectStateCallback> concurrentHashMap = this.connectCallbackMap;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.remove(str);
    }

    public void unregisterWifiRssiChangedCallback(String str) {
        ConcurrentHashMap<String, IWifiRssiChangedCallback> concurrentHashMap = this.rssiCallbackMap;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.remove(str);
    }

    public void unregisterWifiScanResultCallback(String str) {
        ConcurrentHashMap<String, IWifiScanResultCallback> concurrentHashMap = this.scanCallbackMap;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.remove(str);
        if (this.scanCallbackMap.size() <= 0) {
            unregisterScanReceiver();
        }
    }

    public void unregisterWifiStateCallback(String str) {
        ConcurrentHashMap<String, IEnableStateCallback> concurrentHashMap = this.stateCallbackMap;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.remove(str);
    }

    private BydWifiManager() {
        this.preNetId = -1;
        this.isScannerRegistered = false;
        this.scanning = false;
        this.autoScanning = false;
        this.autoScanAllowed = true;
        this.mConnectState = null;
        this.isInited = false;
        this.preScanTime = 0L;
        this.scanTimer = null;
    }

    public void forgetNetwork(String str) {
        this.mWifiManager.forgetNetwork(str);
    }

    public void connect(WifiConfiguration wifiConfiguration) {
        a.a(TAG, "--connect---config: " + d.j(wifiConfiguration));
        this.mWifiManager.connect(wifiConfiguration);
    }
}
