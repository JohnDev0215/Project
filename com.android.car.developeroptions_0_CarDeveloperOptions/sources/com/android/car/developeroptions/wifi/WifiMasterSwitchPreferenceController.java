package com.android.car.developeroptions.wifi;

import android.content.Context;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.widget.MasterSwitchController;
import com.android.car.developeroptions.widget.MasterSwitchPreference;
import com.android.car.developeroptions.widget.SummaryUpdater;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

/* JADX INFO: loaded from: classes.dex */
public class WifiMasterSwitchPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin, SummaryUpdater.OnSummaryChangeListener, LifecycleObserver, OnResume, OnPause, OnStart, OnStop {
    private final MetricsFeatureProvider mMetricsFeatureProvider;
    private final WifiSummaryUpdater mSummaryHelper;
    private WifiEnabler mWifiEnabler;
    private MasterSwitchPreference mWifiPreference;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "toggle_wifi";
    }

    public WifiMasterSwitchPreferenceController(Context context, MetricsFeatureProvider metricsFeatureProvider) {
        super(context);
        this.mMetricsFeatureProvider = metricsFeatureProvider;
        this.mSummaryHelper = new WifiSummaryUpdater(this.mContext, this);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mWifiPreference = (MasterSwitchPreference) preferenceScreen.findPreference("toggle_wifi");
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mContext.getResources().getBoolean(R.bool.config_show_wifi_settings);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        this.mSummaryHelper.register(true);
        WifiEnabler wifiEnabler = this.mWifiEnabler;
        if (wifiEnabler != null) {
            wifiEnabler.resume(this.mContext);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnPause
    public void onPause() {
        WifiEnabler wifiEnabler = this.mWifiEnabler;
        if (wifiEnabler != null) {
            wifiEnabler.pause();
        }
        this.mSummaryHelper.register(false);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mWifiEnabler = new WifiEnabler(this.mContext, new MasterSwitchController(this.mWifiPreference), this.mMetricsFeatureProvider);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        WifiEnabler wifiEnabler = this.mWifiEnabler;
        if (wifiEnabler != null) {
            wifiEnabler.teardownSwitchController();
        }
    }

    @Override // com.android.car.developeroptions.widget.SummaryUpdater.OnSummaryChangeListener
    public void onSummaryChanged(String str) {
        MasterSwitchPreference masterSwitchPreference = this.mWifiPreference;
        if (masterSwitchPreference != null) {
            masterSwitchPreference.setSummary(str);
        }
    }
}
