package com.byd.systemsettings.wifi2.service;

import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import com.byd.bt.model.ConnectState;
import com.byd.bt.model.EnableState;
import com.byd.systemsettings.wifi2.cache.WifiCache;
import com.byd.systemsettings.wifi2.callback.IEnableStateCallback;
import com.byd.systemsettings.wifi2.callback.IWifiConnectStateCallback;
import com.byd.systemsettings.wifi2.callback.IWifiRssiChangedCallback;
import com.byd.systemsettings.wifi2.callback.IWifiScanCallback;
import com.byd.systemsettings.wifi2.impl.BydWifiAutoConnection;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class WifiActionDispatcher {
    private static final long SEC_30 = 30000;
    private static final String TAG = "WifiActionDispatcher";
    private static volatile WifiActionDispatcher instance;
    private volatile boolean threadStoped = true;
    private volatile boolean doingDispatch = false;
    private final LinkedBlockingQueue<Intent> msgQueue = new LinkedBlockingQueue<>();

    /* JADX INFO: renamed from: com.byd.systemsettings.wifi2.service.WifiActionDispatcher$2, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$android$net$NetworkInfo$State;

        static {
            int[] iArr = new int[NetworkInfo.State.values().length];
            $SwitchMap$android$net$NetworkInfo$State = iArr;
            try {
                iArr[NetworkInfo.State.CONNECTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$State[NetworkInfo.State.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$State[NetworkInfo.State.DISCONNECTING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$net$NetworkInfo$State[NetworkInfo.State.DISCONNECTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private WifiActionDispatcher() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doActionDispatch(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        String action = intent.getAction();
        a.a(TAG, "onHandleIntent_wifi", "action: " + action);
        action.hashCode();
        switch (action) {
            case "android.net.wifi.WIFI_STATE_CHANGED":
                onWifiStateChanged(intent);
                break;
            case "android.net.wifi.RSSI_CHANGED":
                onRssiChanged(intent);
                break;
            case "android.net.wifi.STATE_CHANGE":
                onWifiConnectStateChanged(intent);
                break;
            case "android.net.wifi.supplicant.STATE_CHANGE":
                onSupplicantStateChanged(intent);
                break;
            case "android.net.wifi.SCAN_RESULTS":
                onReceiveWifiScanResult(intent);
                break;
        }
    }

    public static WifiActionDispatcher getInstance() {
        if (instance == null) {
            synchronized (WifiActionDispatcher.class) {
                instance = new WifiActionDispatcher();
            }
        }
        return instance;
    }

    private void onReceiveWifiScanResult(Intent intent) {
        IWifiScanCallback iWifiScanCallback = (IWifiScanCallback) WifiCache.get(WifiCache.WIFI_DISCOVER);
        if (iWifiScanCallback == null) {
            return;
        }
        a.a(TAG, "-----onReceiveWifiScanResult----");
        iWifiScanCallback.onWifiDiscovered();
    }

    private void onRssiChanged(Intent intent) {
        IWifiRssiChangedCallback iWifiRssiChangedCallback = (IWifiRssiChangedCallback) WifiCache.get(WifiCache.RSSI_CHANGED);
        if (iWifiRssiChangedCallback == null) {
            a.a(TAG, "onRssiChanged", "cb == null return");
        } else {
            a.a(TAG, "onRssiChanged", ", connected wifi rsii changed.");
            iWifiRssiChangedCallback.onRssiChanged();
        }
    }

    private void onSupplicantStateChanged(Intent intent) {
        IWifiConnectStateCallback iWifiConnectStateCallback = (IWifiConnectStateCallback) WifiCache.get(WifiCache.WIFI_CONNECT_STATE);
        if (iWifiConnectStateCallback == null) {
            a.a(TAG, "onSupplicantStateChanged_dispatch", "cb == null return");
            return;
        }
        SupplicantState supplicantState = (SupplicantState) intent.getParcelableExtra("newState");
        int intExtra = intent.getIntExtra("supplicantErrorReason", 0);
        a.a(TAG, "onSupplicantStateChanged_dispatch", ", state: " + supplicantState + ",supplicantErrorReason:" + intExtra);
        iWifiConnectStateCallback.onSupplicantStateChanged(supplicantState, intExtra);
    }

    private void onWifiConnectFailed(Intent intent) {
        IWifiConnectStateCallback iWifiConnectStateCallback = (IWifiConnectStateCallback) WifiCache.get(WifiCache.WIFI_CONNECT_STATE);
        if (iWifiConnectStateCallback == null) {
            return;
        }
        int intExtra = intent.getIntExtra("supplicantError", -9999);
        a.a(TAG, "onWifiConnectFailed", ", errCode = " + intExtra);
        if (intExtra == -9999) {
            return;
        }
        iWifiConnectStateCallback.onConnectFailed(intExtra);
    }

    private void onWifiConnectStateChanged(Intent intent) {
        IWifiConnectStateCallback iWifiConnectStateCallback = (IWifiConnectStateCallback) WifiCache.get(WifiCache.WIFI_CONNECT_STATE);
        if (iWifiConnectStateCallback == null) {
            return;
        }
        NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
        a.a(TAG, "onWifiConnectStateChanged_dispatch", ", netInfo = " + networkInfo);
        if (networkInfo == null) {
            return;
        }
        int i8 = AnonymousClass2.$SwitchMap$android$net$NetworkInfo$State[networkInfo.getState().ordinal()];
        if (i8 == 1) {
            iWifiConnectStateCallback.onConnectStateChanged(ConnectState.CONNECTING, networkInfo);
            return;
        }
        if (i8 == 2) {
            iWifiConnectStateCallback.onConnectStateChanged(ConnectState.CONNECTED, networkInfo);
        } else if (i8 == 3) {
            iWifiConnectStateCallback.onConnectStateChanged(ConnectState.DISCONNECTING, networkInfo);
        } else {
            if (i8 != 4) {
                return;
            }
            iWifiConnectStateCallback.onConnectStateChanged(ConnectState.DISCONNECTED, networkInfo);
        }
    }

    private void onWifiStateChanged(Intent intent) {
        IEnableStateCallback iEnableStateCallback = (IEnableStateCallback) WifiCache.get(WifiCache.WIFI_STATE);
        if (iEnableStateCallback == null) {
            return;
        }
        int intExtra = intent.getIntExtra(WifiCache.WIFI_STATE, -1);
        a.a(TAG, "onWifiStateChanged", ", state = " + intExtra);
        if (intExtra == 0) {
            iEnableStateCallback.onEnableStateChanged(EnableState.DISABLING);
            return;
        }
        if (intExtra == 1) {
            iEnableStateCallback.onEnableStateChanged(EnableState.DISABLE);
        } else if (intExtra == 2) {
            iEnableStateCallback.onEnableStateChanged(EnableState.ENABLING);
        } else {
            if (intExtra != 3) {
                return;
            }
            iEnableStateCallback.onEnableStateChanged(EnableState.ENABLE);
        }
    }

    private boolean stillListeningScanResult(String str) {
        if (WifiCache.isUIListening()) {
            return true;
        }
        if ("android.net.wifi.RSSI_CHANGED".equals(str)) {
            return false;
        }
        if ("android.net.wifi.SCAN_RESULTS".equals(str)) {
            return BydWifiAutoConnection.Singleton.get().isAutoConnectEnable();
        }
        return true;
    }

    public void dispatchWifiAction(Intent intent) {
        String action = intent.getAction();
        a.e(TAG, "dispatchWifiAction", "action: " + action);
        if (!stillListeningScanResult(action)) {
            a.e(TAG, "dispatchWifiAction", "action: " + action + ", uiListening: " + WifiCache.isUIListening());
            return;
        }
        this.msgQueue.offer(intent);
        if (this.doingDispatch) {
            a.a(TAG, "dispatchWifiAction", "Doing dispatch, action is offered.action: " + action);
            return;
        }
        this.doingDispatch = true;
        Thread thread = new Thread(new Runnable() { // from class: com.byd.systemsettings.wifi2.service.WifiActionDispatcher.1
            @Override // java.lang.Runnable
            public void run() {
                a.e(WifiActionDispatcher.TAG, "dispatchWifiAction", "Thread START.");
                WifiActionDispatcher.this.threadStoped = false;
                while (!WifiActionDispatcher.this.threadStoped && WifiActionDispatcher.this.doingDispatch) {
                    Intent intent2 = null;
                    try {
                        intent2 = (Intent) WifiActionDispatcher.this.msgQueue.poll(WifiActionDispatcher.SEC_30, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e8) {
                        a.a(WifiActionDispatcher.TAG, "dispatchWifiAction", "msg poll exception. e: " + e8.toString());
                    }
                    a.a(WifiActionDispatcher.TAG, "dispatchWifiAction", "do dispatch, in: " + intent2);
                    if (intent2 == null) {
                        break;
                    } else {
                        WifiActionDispatcher.this.doActionDispatch(intent2);
                    }
                }
                a.e(WifiActionDispatcher.TAG, "dispatchWifiAction", "Thread END.");
                WifiActionDispatcher.this.doingDispatch = false;
                WifiActionDispatcher.this.threadStoped = true;
            }
        });
        thread.setName(TAG);
        thread.start();
    }
}
