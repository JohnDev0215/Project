package com.android.car.settings.wifi;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.SoftApConfiguration;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public abstract class WifiTetherBasePreferenceController<V extends Preference> extends PreferenceController<V> {
    public static final String ACTION_RESTART_WIFI_TETHERING = "com.android.car.settings.wifi.ACTION_RESTART_WIFI_TETHERING";
    private CarWifiManager mCarWifiManager;

    protected abstract String getDefaultSummary();

    protected abstract String getSummary();

    public WifiTetherBasePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        this.mCarWifiManager = new CarWifiManager(getContext());
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mCarWifiManager.start();
        getPreference().setPersistent(true);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mCarWifiManager.stop();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onDestroyInternal() {
        this.mCarWifiManager.destroy();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(V preference) {
        String summary = getSummary();
        String defaultSummary = getDefaultSummary();
        if (TextUtils.isEmpty(summary)) {
            preference.setSummary(defaultSummary);
        } else {
            preference.setSummary(summary);
        }
    }

    protected SoftApConfiguration getCarSoftApConfig() {
        return this.mCarWifiManager.getSoftApConfig();
    }

    protected void setCarSoftApConfig(SoftApConfiguration configuration) {
        this.mCarWifiManager.setSoftApConfig(configuration);
        requestWifiTetherRestart();
    }

    protected CarWifiManager getCarWifiManager() {
        return this.mCarWifiManager;
    }

    protected void requestWifiTetherRestart() {
        Intent intent = new Intent(ACTION_RESTART_WIFI_TETHERING);
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
    }
}
