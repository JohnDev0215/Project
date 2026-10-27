package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkScoreManager;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.android.systemui.R;
import com.android.systemui.statusbar.datas.Icons;
import com.android.systemui.statusbar.policy.NetworkController;
import com.android.systemui.statusbar.policy.SignalController;
import com.android.systemui.util.StringUtil;
import com.android.systemui.util.wifi.WifiStatusTracker;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class WifiSignalController extends SignalController<WifiState, SignalController.IconGroup> {
    private final WifiStatusTracker mWifiTracker;

    public WifiSignalController(Context context, CallbackHandler callbackHandler, NetworkControllerImpl networkController, WifiManager wifiManager) {
        super("WifiSignalController", context, 1, callbackHandler, networkController);
        WifiStatusTracker wifiStatusTracker = new WifiStatusTracker(this.mContext, wifiManager, (NetworkScoreManager) context.getSystemService(NetworkScoreManager.class), (ConnectivityManager) context.getSystemService(ConnectivityManager.class), new Runnable() { // from class: com.android.systemui.statusbar.policy.-$$Lambda$WifiSignalController$Z4-p5zzA9Fv3s3CeygZbi0Jxwk0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.handleStatusUpdated();
            }
        });
        this.mWifiTracker = wifiStatusTracker;
        wifiStatusTracker.setListening(true);
        if (wifiManager != null) {
            wifiManager.registerTrafficStateCallback(context.getMainExecutor(), new WifiTrafficStateCallback());
        }
        WifiState wifiState = (WifiState) this.mCurrentState;
        WifiState wifiState2 = (WifiState) this.mLastState;
        SignalController.IconGroup iconGroup = new SignalController.IconGroup("Wi-Fi Icons", Icons.WIFI_SIGNAL_STRENGTH, R.mipmap.icon_status_bar_wifi_0, R.mipmap.icon_status_bar_wifi_0);
        wifiState2.iconGroup = iconGroup;
        wifiState.iconGroup = iconGroup;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.systemui.statusbar.policy.SignalController
    public WifiState cleanState() {
        return new WifiState();
    }

    @Override // com.android.systemui.statusbar.policy.SignalController
    public void notifyListeners(NetworkController.SignalCallback callback) {
        boolean z = ((WifiState) this.mCurrentState).enabled && ((WifiState) this.mCurrentState).connected;
        String str = z ? ((WifiState) this.mCurrentState).ssid : null;
        if (z) {
            String str2 = ((WifiState) this.mCurrentState).ssid;
        }
        callback.setWifiIndicators(this.mCurrentState, new NetworkController.IconState(z, getCurrentIconId()), str, ((WifiState) this.mCurrentState).isTransient, ((WifiState) this.mCurrentState).statusLabel);
    }

    public void fetchInitialState() {
        this.mWifiTracker.fetchInitialState();
        ((WifiState) this.mCurrentState).enabled = this.mWifiTracker.enabled;
        ((WifiState) this.mCurrentState).isDefault = this.mWifiTracker.isDefaultNetwork;
        ((WifiState) this.mCurrentState).connected = this.mWifiTracker.connected;
        ((WifiState) this.mCurrentState).ssid = this.mWifiTracker.ssid;
        ((WifiState) this.mCurrentState).rssi = this.mWifiTracker.rssi;
        ((WifiState) this.mCurrentState).level = this.mWifiTracker.level;
        ((WifiState) this.mCurrentState).statusLabel = this.mWifiTracker.statusLabel;
        notifyListenersIfNecessary();
    }

    public void handleBroadcast(Intent intent) {
        this.mWifiTracker.handleBroadcast(intent);
        ((WifiState) this.mCurrentState).enabled = this.mWifiTracker.enabled;
        ((WifiState) this.mCurrentState).isDefault = this.mWifiTracker.isDefaultNetwork;
        ((WifiState) this.mCurrentState).connected = this.mWifiTracker.connected;
        ((WifiState) this.mCurrentState).ssid = this.mWifiTracker.ssid;
        ((WifiState) this.mCurrentState).rssi = this.mWifiTracker.rssi;
        ((WifiState) this.mCurrentState).level = this.mWifiTracker.level;
        ((WifiState) this.mCurrentState).statusLabel = this.mWifiTracker.statusLabel;
        notifyListenersIfNecessary();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleStatusUpdated() {
        ((WifiState) this.mCurrentState).statusLabel = this.mWifiTracker.statusLabel;
        ((WifiState) this.mCurrentState).isDefault = this.mWifiTracker.isDefaultNetwork;
        notifyListenersIfNecessary();
    }

    void setActivity(int wifiActivity) {
        ((WifiState) this.mCurrentState).activityIn = wifiActivity == 3 || wifiActivity == 1;
        ((WifiState) this.mCurrentState).activityOut = wifiActivity == 3 || wifiActivity == 2;
        notifyListenersIfNecessary();
    }

    private class WifiTrafficStateCallback implements WifiManager.TrafficStateCallback {
        private WifiTrafficStateCallback() {
        }

        public void onStateChanged(int state) {
            WifiSignalController.this.setActivity(state);
        }
    }

    static class WifiState extends SignalController.State {
        boolean isDefault;
        boolean isTransient;
        String ssid;
        String statusLabel;

        WifiState() {
        }

        @Override // com.android.systemui.statusbar.policy.SignalController.State
        public void copyFrom(SignalController.State s) {
            super.copyFrom(s);
            WifiState wifiState = (WifiState) s;
            this.ssid = wifiState.ssid;
            this.isTransient = wifiState.isTransient;
            this.isDefault = wifiState.isDefault;
            this.statusLabel = wifiState.statusLabel;
        }

        @Override // com.android.systemui.statusbar.policy.SignalController.State
        protected void toString(StringBuilder builder) {
            super.toString(builder);
            builder.append(",ssid=").append(StringUtil.desensitizeString(this.ssid, 0)).append(",isTransient=").append(this.isTransient).append(",isDefault=").append(this.isDefault).append(",statusLabel=").append(this.statusLabel);
        }

        @Override // com.android.systemui.statusbar.policy.SignalController.State
        public boolean equals(Object o) {
            if (!super.equals(o)) {
                return false;
            }
            WifiState wifiState = (WifiState) o;
            return Objects.equals(wifiState.ssid, this.ssid) && wifiState.isTransient == this.isTransient && wifiState.isDefault == this.isDefault && TextUtils.equals(wifiState.statusLabel, this.statusLabel);
        }
    }
}
