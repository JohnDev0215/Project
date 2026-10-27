package com.android.car.developeroptions.wifi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.utils.AnnotationSpan;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;

/* JADX INFO: loaded from: classes.dex */
public class WifiWakeupPreferenceController extends AbstractPreferenceController implements LifecycleObserver, OnPause, OnResume {
    private final Fragment mFragment;
    private final IntentFilter mLocationFilter;
    LocationManager mLocationManager;
    private final BroadcastReceiver mLocationReceiver;
    SwitchPreference mPreference;
    WifiManager mWifiManager;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "enable_wifi_wakeup";
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return true;
    }

    public WifiWakeupPreferenceController(Context context, DashboardFragment dashboardFragment, Lifecycle lifecycle) {
        super(context);
        this.mLocationReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.wifi.WifiWakeupPreferenceController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                WifiWakeupPreferenceController wifiWakeupPreferenceController = WifiWakeupPreferenceController.this;
                wifiWakeupPreferenceController.updateState(wifiWakeupPreferenceController.mPreference);
            }
        };
        this.mLocationFilter = new IntentFilter("android.location.MODE_CHANGED");
        this.mFragment = dashboardFragment;
        this.mLocationManager = (LocationManager) context.getSystemService("location");
        this.mWifiManager = (WifiManager) context.getSystemService(WifiManager.class);
        lifecycle.addObserver(this);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        SwitchPreference switchPreference = (SwitchPreference) preferenceScreen.findPreference("enable_wifi_wakeup");
        this.mPreference = switchPreference;
        updateState(switchPreference);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!TextUtils.equals(preference.getKey(), "enable_wifi_wakeup") || !(preference instanceof SwitchPreference)) {
            return false;
        }
        if (!this.mLocationManager.isLocationEnabled()) {
            this.mFragment.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        } else if (getWifiWakeupEnabled()) {
            setWifiWakeupEnabled(false);
        } else if (!getWifiScanningEnabled()) {
            showScanningDialog();
        } else {
            setWifiWakeupEnabled(true);
        }
        updateState(this.mPreference);
        return true;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        if (preference instanceof SwitchPreference) {
            ((SwitchPreference) preference).setChecked(getWifiWakeupEnabled() && getWifiScanningEnabled() && this.mLocationManager.isLocationEnabled());
            if (!this.mLocationManager.isLocationEnabled()) {
                preference.setSummary(getNoLocationSummary());
            } else {
                preference.setSummary(R.string.wifi_wakeup_summary);
            }
        }
    }

    CharSequence getNoLocationSummary() {
        return AnnotationSpan.linkify(this.mContext.getText(R.string.wifi_wakeup_summary_no_location), new AnnotationSpan.LinkInfo("link", null));
    }

    public void onActivityResult(int i, int i2) {
        if (i != 600) {
            return;
        }
        if (this.mLocationManager.isLocationEnabled()) {
            setWifiWakeupEnabled(true);
        }
        updateState(this.mPreference);
    }

    private boolean getWifiScanningEnabled() {
        return this.mWifiManager.isScanAlwaysAvailable();
    }

    private void showScanningDialog() {
        WifiScanningRequiredFragment wifiScanningRequiredFragmentNewInstance = WifiScanningRequiredFragment.newInstance();
        wifiScanningRequiredFragmentNewInstance.setTargetFragment(this.mFragment, 600);
        wifiScanningRequiredFragmentNewInstance.show(this.mFragment.getFragmentManager(), "WifiWakeupPrefController");
    }

    private boolean getWifiWakeupEnabled() {
        return this.mWifiManager.isAutoWakeupEnabled();
    }

    private void setWifiWakeupEnabled(boolean z) {
        this.mWifiManager.setAutoWakeupEnabled(z);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        this.mContext.registerReceiver(this.mLocationReceiver, this.mLocationFilter);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnPause
    public void onPause() {
        this.mContext.unregisterReceiver(this.mLocationReceiver);
    }
}
