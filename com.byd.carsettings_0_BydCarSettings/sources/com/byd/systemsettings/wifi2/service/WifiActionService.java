package com.byd.systemsettings.wifi2.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.os.Parcel;
import android.os.UserHandle;
import b.b.a.f.h;
import b.b.m.c.a;
import com.byd.bt.model.ConnectState;
import com.byd.bt.model.EnableState;
import com.byd.mvp.service.BydIntentService;
import com.byd.systemsettings.wifi2.cache.WifiCache;
import com.byd.systemsettings.wifi2.callback.IEnableStateCallback;
import com.byd.systemsettings.wifi2.callback.IWifiConnectStateCallback;
import com.byd.systemsettings.wifi2.callback.IWifiRssiChangedCallback;
import com.byd.systemsettings.wifi2.callback.IWifiScanCallback;

/* JADX INFO: loaded from: classes.dex */
public class WifiActionService extends BydIntentService {
    private static final String TAG = "WifiActionService";

    /* JADX INFO: renamed from: com.byd.systemsettings.wifi2.service.WifiActionService$2, reason: invalid class name */
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

    public WifiActionService() {
        super(TAG);
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
            a.a(TAG, "onSupplicantStateChanged", "cb == null return");
            return;
        }
        SupplicantState supplicantState = (SupplicantState) intent.getParcelableExtra("newState");
        int intExtra = intent.getIntExtra("supplicantErrorReason", 0);
        a.a(TAG, "onSupplicantStateChanged", ", state: " + supplicantState + ",supplicantErrorReason:" + intExtra);
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
        a.a(TAG, "onWifiConnectStateChanged", ", netInfo = " + networkInfo);
        if (networkInfo == null) {
            return;
        }
        int i = AnonymousClass2.$SwitchMap$android$net$NetworkInfo$State[networkInfo.getState().ordinal()];
        if (i == 1) {
            iWifiConnectStateCallback.onConnectStateChanged(ConnectState.CONNECTING, networkInfo);
            return;
        }
        if (i == 2) {
            iWifiConnectStateCallback.onConnectStateChanged(ConnectState.CONNECTED, networkInfo);
        } else if (i == 3) {
            iWifiConnectStateCallback.onConnectStateChanged(ConnectState.DISCONNECTING, networkInfo);
        } else {
            if (i != 4) {
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

    public static void startWifiAction(final Context context, final Intent intent) {
        String action = intent.getAction();
        a.g(TAG, "---startWifiAction--action: " + action);
        if (stillListeningScanResult(action)) {
            h.a().j(new Runnable() { // from class: com.byd.systemsettings.wifi2.service.WifiActionService.1
                @Override // java.lang.Runnable
                public void run() {
                    Parcel parcelObtain = Parcel.obtain();
                    intent.setComponent(new ComponentName(context, (Class<?>) WifiActionService.class));
                    b.b.m.g.a.c(context, "startServiceAsUser", new Class[]{Intent.class, UserHandle.class}, new Object[]{intent, UserHandle.readFromParcel(parcelObtain)});
                    parcelObtain.recycle();
                    a.g(WifiActionService.TAG, "---startWifiAction--start service completed. threadName: " + Thread.currentThread().getName() + ", action: " + intent.getAction());
                }
            });
            return;
        }
        a.g(TAG, "---startWifiAction--action: " + action + ", isListeningScanResult: " + WifiCache.isListeningScanResult());
    }

    private static boolean stillListeningScanResult(String str) {
        if (WifiCache.isListeningScanResult()) {
            return true;
        }
        return ("android.net.wifi.SCAN_RESULTS".equals(str) || "android.net.wifi.RSSI_CHANGED".equals(str)) ? false : true;
    }

    @Override // com.byd.mvp.service.BydIntentService
    public void onHandleIntent(Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        String action = intent.getAction();
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
}
