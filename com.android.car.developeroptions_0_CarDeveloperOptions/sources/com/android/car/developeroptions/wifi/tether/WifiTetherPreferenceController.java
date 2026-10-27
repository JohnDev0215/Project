package com.android.car.developeroptions.wifi.tether;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.WifiClient;
import android.net.wifi.WifiManager;
import android.text.BidiFormatter;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.wifi.tether.WifiTetherSoftApManager;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WifiTetherPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnStart, OnStop {
    private final ConnectivityManager mConnectivityManager;
    Preference mPreference;
    private int mSoftApState;
    private final WifiManager mWifiManager;
    private final String[] mWifiRegexs;
    WifiTetherSoftApManager mWifiTetherSoftApManager;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "wifi_tether";
    }

    public WifiTetherPreferenceController(Context context, Lifecycle lifecycle) {
        this(context, lifecycle, true);
    }

    WifiTetherPreferenceController(Context context, Lifecycle lifecycle, boolean z) {
        super(context);
        this.mConnectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
        this.mWifiRegexs = this.mConnectivityManager.getTetherableWifiRegexs();
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
        if (z) {
            initWifiTetherSoftApManager();
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        String[] strArr = this.mWifiRegexs;
        return (strArr == null || strArr.length == 0 || Utils.isMonkeyRunning()) ? false : true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference preferenceFindPreference = preferenceScreen.findPreference("wifi_tether");
        this.mPreference = preferenceFindPreference;
        if (preferenceFindPreference == null) {
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        WifiTetherSoftApManager wifiTetherSoftApManager;
        if (this.mPreference == null || (wifiTetherSoftApManager = this.mWifiTetherSoftApManager) == null) {
            return;
        }
        wifiTetherSoftApManager.registerSoftApCallback();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        WifiTetherSoftApManager wifiTetherSoftApManager;
        if (this.mPreference == null || (wifiTetherSoftApManager = this.mWifiTetherSoftApManager) == null) {
            return;
        }
        wifiTetherSoftApManager.unRegisterSoftApCallback();
    }

    void initWifiTetherSoftApManager() {
        this.mWifiTetherSoftApManager = new WifiTetherSoftApManager(this.mWifiManager, new WifiTetherSoftApManager.WifiTetherSoftApCallback() { // from class: com.android.car.developeroptions.wifi.tether.WifiTetherPreferenceController.1
            @Override // com.android.car.developeroptions.wifi.tether.WifiTetherSoftApManager.WifiTetherSoftApCallback
            public void onStateChanged(int i, int i2) {
                WifiTetherPreferenceController.this.mSoftApState = i;
                WifiTetherPreferenceController.this.handleWifiApStateChanged(i, i2);
            }

            @Override // com.android.car.developeroptions.wifi.tether.WifiTetherSoftApManager.WifiTetherSoftApCallback
            public void onConnectedClientsChanged(List<WifiClient> list) {
                WifiTetherPreferenceController wifiTetherPreferenceController = WifiTetherPreferenceController.this;
                if (wifiTetherPreferenceController.mPreference == null || wifiTetherPreferenceController.mSoftApState != 13) {
                    return;
                }
                int size = list.size();
                WifiTetherPreferenceController wifiTetherPreferenceController2 = WifiTetherPreferenceController.this;
                wifiTetherPreferenceController2.mPreference.setSummary(((AbstractPreferenceController) wifiTetherPreferenceController2).mContext.getResources().getQuantityString(R.plurals.wifi_tether_connected_summary, size, Integer.valueOf(size)));
            }
        });
    }

    void handleWifiApStateChanged(int i, int i2) {
        switch (i) {
            case 10:
                this.mPreference.setSummary(R.string.wifi_tether_stopping);
                break;
            case 11:
                this.mPreference.setSummary(R.string.wifi_hotspot_off_subtext);
                break;
            case 12:
                this.mPreference.setSummary(R.string.wifi_tether_starting);
                break;
            case 13:
                updateConfigSummary(this.mWifiManager.getSoftApConfiguration());
                break;
            default:
                if (i2 == 1) {
                    this.mPreference.setSummary(R.string.wifi_sap_no_channel_error);
                } else {
                    this.mPreference.setSummary(R.string.wifi_error);
                }
                break;
        }
    }

    private void updateConfigSummary(SoftApConfiguration softApConfiguration) {
        if (softApConfiguration == null) {
            Log.e("WifiTetherPreferenceController", "Ap config null unexpectedly");
        } else {
            this.mPreference.setSummary(this.mContext.getString(R.string.wifi_tether_enabled_subtext, BidiFormatter.getInstance().unicodeWrap(softApConfiguration.getSsid())));
        }
    }
}
