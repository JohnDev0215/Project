package com.android.car.developeroptions.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import androidx.core.text.BidiFormatter;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;

/* JADX INFO: loaded from: classes.dex */
public class WifiInfoPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnResume, OnPause {
    private final IntentFilter mFilter;
    private final BroadcastReceiver mReceiver;
    private Preference mWifiIpAddressPref;
    private Preference mWifiMacAddressPref;
    private final WifiManager mWifiManager;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return null;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public WifiInfoPreferenceController(Context context, Lifecycle lifecycle, WifiManager wifiManager) {
        super(context);
        this.mReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.wifi.WifiInfoPreferenceController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                if (action.equals("android.net.wifi.LINK_CONFIGURATION_CHANGED") || action.equals("android.net.wifi.STATE_CHANGE")) {
                    WifiInfoPreferenceController.this.updateWifiInfo();
                }
            }
        };
        this.mWifiManager = wifiManager;
        IntentFilter intentFilter = new IntentFilter();
        this.mFilter = intentFilter;
        intentFilter.addAction("android.net.wifi.LINK_CONFIGURATION_CHANGED");
        this.mFilter.addAction("android.net.wifi.STATE_CHANGE");
        lifecycle.addObserver(this);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference preferenceFindPreference = preferenceScreen.findPreference("mac_address");
        this.mWifiMacAddressPref = preferenceFindPreference;
        preferenceFindPreference.setSelectable(false);
        Preference preferenceFindPreference2 = preferenceScreen.findPreference("current_ip_address");
        this.mWifiIpAddressPref = preferenceFindPreference2;
        preferenceFindPreference2.setSelectable(false);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        this.mContext.registerReceiver(this.mReceiver, this.mFilter);
        updateWifiInfo();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnPause
    public void onPause() {
        this.mContext.unregisterReceiver(this.mReceiver);
    }

    public void updateWifiInfo() {
        String strUnicodeWrap;
        if (this.mWifiMacAddressPref != null) {
            android.net.wifi.WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
            boolean zIsConnectedMacRandomizationSupported = this.mWifiManager.isConnectedMacRandomizationSupported();
            String macAddress = connectionInfo == null ? null : connectionInfo.getMacAddress();
            if (zIsConnectedMacRandomizationSupported && "02:00:00:00:00:00".equals(macAddress)) {
                this.mWifiMacAddressPref.setSummary(R.string.wifi_status_mac_randomized);
            } else if (TextUtils.isEmpty(macAddress) || "02:00:00:00:00:00".equals(macAddress)) {
                this.mWifiMacAddressPref.setSummary(R.string.status_unavailable);
            } else {
                this.mWifiMacAddressPref.setSummary(macAddress);
            }
        }
        if (this.mWifiIpAddressPref != null) {
            String wifiIpAddresses = Utils.getWifiIpAddresses(this.mContext);
            Preference preference = this.mWifiIpAddressPref;
            if (wifiIpAddresses == null) {
                strUnicodeWrap = this.mContext.getString(R.string.status_unavailable);
            } else {
                strUnicodeWrap = BidiFormatter.getInstance().unicodeWrap(wifiIpAddresses);
            }
            preference.setSummary(strUnicodeWrap);
        }
    }
}
