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
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import b.b.m.c.a;
import b.b.m.g.f;
import com.byd.bt.model.ConnectState;
import com.byd.bt.model.EnableState;
import com.byd.systemsettings.wifi2.cache.WifiCache;
import com.byd.systemsettings.wifi2.callback.IEnableStateCallback;
import com.byd.systemsettings.wifi2.callback.IWifiConnectStateCallback;
import com.byd.systemsettings.wifi2.callback.IWifiRssiChangedCallback;
import com.byd.systemsettings.wifi2.callback.IWifiScanResultCallback;
import com.byd.systemsettings.wifi2.common.WifiAP;
import com.byd.systemsettings.wifi2.model.CipherType;
import com.byd.systemsettings.wifi2.model.WifiScanResult;
import com.byd.systemsettings.wifi2.receive.WifiActionReceiver;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class BydWifiManager {
    private static final long DELAY_8000 = 8000;
    private static final int INTERNET_SETTINGS_REQUEST = 33;
    public static final String NONE = "<unknown ssid>";
    private static final String TAG = "BydWifiManager";
    private static final long TIMEOUT_SCAN = 10000;
    private static final int VERSION_Q = 29;
    private static final int WHAT_ON_SCAN_RESULT = 32;
    public static final int WIFI_NOT_FOUND = -99;
    private ConcurrentHashMap<String, IWifiConnectStateCallback> connectCallbackMap;
    private WifiConfiguration curConfig;
    public boolean doConnect;
    private volatile boolean isInited;
    private volatile boolean isScannerRegistered;
    private Context mContext;
    private ReadHandler mHandler;
    private WifiManagerPie mWifiManager;
    private WifiActionReceiver mWifiReceiver;
    private final int preNetId;
    private ConcurrentHashMap<String, IEnableStateCallback> stateCallbackMap;

    public static class ReadHandler extends Handler {
        private final WeakReference<BydWifiManager> ref;

        public ReadHandler(BydWifiManager bydWifiManager) {
            this.ref = new WeakReference<>(bydWifiManager);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            WeakReference<BydWifiManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null || !this.ref.get().isWifiEnabled()) {
            }
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
        public void onConnectFailed(int i) {
            WeakReference<BydWifiManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().onConnectFailed(i);
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
        public void onSupplicantStateChanged(SupplicantState supplicantState, int i) {
            WeakReference<BydWifiManager> weakReference = this.ref;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.ref.get().onSupplicantStateChanged(supplicantState, i);
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

    private void doScan() {
    }

    private void onScanTimeout() {
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
        } catch (RuntimeException e2) {
            a.a(TAG, "e = " + e2.toString());
        }
    }

    public void addNetwork(WifiConfiguration wifiConfiguration) {
    }

    public void autoScan() {
    }

    public void cancelConnect() {
        this.mWifiManager.cancelConnect();
    }

    public void connect(WifiScanResult wifiScanResult, String str) {
        if (wifiScanResult == null) {
            return;
        }
        a.a(TAG, "--connect--0--result = " + f.g(wifiScanResult.getName()));
        this.mWifiManager.connect(wifiScanResult, str);
        this.doConnect = true;
    }

    public void connectOpenType(WifiScanResult wifiScanResult) {
        if (wifiScanResult == null) {
            return;
        }
        a.a(TAG, "--connectOpenType--0--result = " + f.g(wifiScanResult.getName()));
        this.mWifiManager.connect(wifiScanResult, "");
        this.doConnect = true;
    }

    public void connectWithoutScanResult(String str, String str2, CipherType cipherType) {
        this.doConnect = true;
        boolean zConnectWithoutScanResult = this.mWifiManager.connectWithoutScanResult(str, str2, cipherType);
        a.a(TAG, "--connectWithoutScanResult--ssid: " + f.g(str) + ", type: " + cipherType + ", result: " + zConnectWithoutScanResult);
        if (zConnectWithoutScanResult) {
            return;
        }
        onCipherTypeErr();
    }

    public void creatWifiLock() {
        this.mWifiManager.createWifiLock();
    }

    public void createWifiLock() {
    }

    public void disconnect() {
        this.mWifiManager.disconnect();
    }

    public void disconnectWifi(int i) {
    }

    public void forgetAllNetwork() {
        this.mWifiManager.forgetAllNetwork();
    }

    public void forgetNetwork(int i) {
        this.mWifiManager.forgetNetwork(i);
    }

    public String getConnectedName() {
        WifiInfo connectedWifiInfo = this.mWifiManager.getConnectedWifiInfo();
        if (connectedWifiInfo == null) {
            return "";
        }
        String ssid = connectedWifiInfo.getSSID();
        a.a(TAG, "getConnectedName ssid:" + f.g(ssid));
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

    public int getRssiLevel(int i) {
        return this.mWifiManager.getRssiLevel(i);
    }

    public WifiConfiguration getSavedConfig(String str) {
        return this.mWifiManager.getWifiConfiguration(str);
    }

    public CipherType getWifiCipherType(WifiConfiguration wifiConfiguration) {
        return this.mWifiManager.getWifiCipherType(wifiConfiguration);
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
        if (this.connectCallbackMap == null) {
            this.connectCallbackMap = new ConcurrentHashMap<>();
        }
        if (Build.VERSION.SDK_INT >= 29) {
            this.mWifiManager = new WifiManagerQ();
        } else {
            this.mWifiManager = new WifiManagerPie();
        }
        this.mWifiManager.init(context);
        WifiCache.put(WifiCache.WIFI_STATE, new WifiStateCallback(this));
        WifiCache.put(WifiCache.WIFI_CONNECT_STATE, new WifiConnectCallback(this));
        registerReceiver();
        this.isInited = true;
        this.doConnect = false;
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

    public void onConnectFailed(int i) {
        a.a(TAG, "onConnectFailed", ", errCode = " + i);
        ConcurrentHashMap<String, IWifiConnectStateCallback> concurrentHashMap = this.connectCallbackMap;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        Iterator<IWifiConnectStateCallback> it = this.connectCallbackMap.values().iterator();
        while (it.hasNext()) {
            it.next().onConnectFailed(i);
        }
    }

    public void onConnectStateChanged(ConnectState connectState, NetworkInfo networkInfo) {
        a.a(TAG, "onConnectStateChanged", ", state = " + connectState + ", info = " + networkInfo);
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
    }

    public void onScanFinished() {
    }

    public void onSupplicantStateChanged(SupplicantState supplicantState, int i) {
        a.a(TAG, "onSupplicantStateChanged", ", state = " + supplicantState + ",errorReason:" + i);
        ConcurrentHashMap<String, IWifiConnectStateCallback> concurrentHashMap = this.connectCallbackMap;
        if (concurrentHashMap == null || concurrentHashMap.isEmpty()) {
            return;
        }
        Iterator<IWifiConnectStateCallback> it = this.connectCallbackMap.values().iterator();
        while (it.hasNext()) {
            it.next().onSupplicantStateChanged(supplicantState, i);
        }
    }

    @RequiresApi(api = 23)
    @SuppressLint({"MissingPermission"})
    public void onWifiDiscovered() {
        if (isWifiEnabled()) {
            List<ScanResult> scanResult = this.mWifiManager.readScanResult();
            String[] strArr = new String[1];
            StringBuilder sb = new StringBuilder();
            sb.append(", ---onWifiDiscovered--- ");
            sb.append(scanResult == null ? "scanResultList is null." : "scanResultList.size:" + scanResult.size());
            strArr[0] = sb.toString();
            a.d(TAG, strArr);
            ArrayList arrayList = new ArrayList();
            for (ScanResult scanResult2 : scanResult) {
                if (!TextUtils.isEmpty(scanResult2.SSID) && !"<unknown ssid>".equals(scanResult2.SSID)) {
                    arrayList.add(scanResult2);
                }
            }
        }
    }

    public void onWifiNotFound(String str) {
    }

    public String parseIp(int i) {
        return f.m(i);
    }

    public void registerScanReceiver() {
        a.a(TAG, "---registerReceiver---isScannerRegistered: " + this.isScannerRegistered);
        if (this.isScannerRegistered) {
            return;
        }
        this.isScannerRegistered = true;
    }

    public void registerWifiConnectStateCallback(String str, IWifiConnectStateCallback iWifiConnectStateCallback) {
        ConcurrentHashMap<String, IWifiConnectStateCallback> concurrentHashMap = this.connectCallbackMap;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.put(str, iWifiConnectStateCallback);
    }

    public void registerWifiRssiChangedCallback(String str, IWifiRssiChangedCallback iWifiRssiChangedCallback) {
    }

    public void registerWifiScanResultCallback(String str, IWifiScanResultCallback iWifiScanResultCallback) {
        a.a(TAG, ", registerWifiScanResultCallback---k: " + str + ", callback: " + iWifiScanResultCallback);
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
        WifiCache.clear();
        this.isInited = false;
        ConcurrentHashMap<String, IEnableStateCallback> concurrentHashMap = this.stateCallbackMap;
        if (concurrentHashMap != null) {
            concurrentHashMap.clear();
        }
        ConcurrentHashMap<String, IWifiConnectStateCallback> concurrentHashMap2 = this.connectCallbackMap;
        if (concurrentHashMap2 != null) {
            concurrentHashMap2.clear();
        }
        this.mContext = null;
        return true;
    }

    public void removeWifi(int i) {
        disconnectWifi(i);
    }

    public void saveNetwork(String str, CipherType cipherType) {
        this.mWifiManager.saveNetwork(str, cipherType);
    }

    public void setEnabled(boolean z) {
        this.mWifiManager.setEnabled(z);
    }

    public void startScan() {
    }

    public void stopAutoScan() {
    }

    public void stopScan() {
    }

    public void unregisterScanReceiver() {
    }

    public void unregisterWifiConnectStateCallback(String str) {
        ConcurrentHashMap<String, IWifiConnectStateCallback> concurrentHashMap = this.connectCallbackMap;
        if (concurrentHashMap == null) {
            return;
        }
        concurrentHashMap.remove(str);
    }

    public void unregisterWifiRssiChangedCallback(String str) {
    }

    public void unregisterWifiScanResultCallback(String str) {
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
        this.isInited = false;
    }

    public void forgetNetwork(String str) {
        this.mWifiManager.forgetNetwork(str);
    }

    public void connect(WifiConfiguration wifiConfiguration) {
        a.a(TAG, "--connect---config: " + wifiConfiguration);
        this.mWifiManager.connect(wifiConfiguration);
    }
}
