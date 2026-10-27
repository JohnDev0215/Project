package com.android.car.developeroptions.wifi.tether;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.SoftApConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.util.Log;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.dashboard.RestrictedDashboardFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.car.developeroptions.widget.SwitchBarController;
import com.android.car.developeroptions.wifi.tether.WifiTetherBasePreferenceController;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WifiTetherSettings extends RestrictedDashboardFragment implements WifiTetherBasePreferenceController.OnTetherConfigUpdateListener {
    static final String KEY_WIFI_TETHER_AUTO_OFF = "wifi_tether_auto_turn_off";
    static final String KEY_WIFI_TETHER_NETWORK_AP_BAND = "wifi_tether_network_ap_band";
    static final String KEY_WIFI_TETHER_NETWORK_NAME = "wifi_tether_network_name";
    static final String KEY_WIFI_TETHER_NETWORK_PASSWORD = "wifi_tether_network_password";
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER;
    private static final IntentFilter TETHER_STATE_CHANGE_FILTER;
    private WifiTetherApBandPreferenceController mApBandPreferenceController;
    private WifiTetherPasswordPreferenceController mPasswordPreferenceController;
    private boolean mRestartWifiApAfterConfigChange;
    private WifiTetherSSIDPreferenceController mSSIDPreferenceController;
    private WifiTetherSecurityPreferenceController mSecurityPreferenceController;
    private WifiTetherSwitchBarController mSwitchBarController;
    TetherChangeReceiver mTetherChangeReceiver;
    private boolean mUnavailable;
    private WifiManager mWifiManager;

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "WifiTetherSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1014;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.wifi_tether_settings;
    }

    static {
        IntentFilter intentFilter = new IntentFilter("android.net.conn.TETHER_STATE_CHANGED");
        TETHER_STATE_CHANGE_FILTER = intentFilter;
        intentFilter.addAction("android.net.wifi.WIFI_AP_STATE_CHANGED");
        SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.wifi.tether.WifiTetherSettings.1
            @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
            public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
                SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
                searchIndexableResource.xmlResId = R.xml.wifi_tether_settings;
                return Arrays.asList(searchIndexableResource);
            }
        };
    }

    public WifiTetherSettings() {
        super("no_config_tethering");
    }

    @Override // com.android.car.developeroptions.dashboard.RestrictedDashboardFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setIfOnlyAvailableForAdmins(true);
        if (isUiRestricted()) {
            this.mUnavailable = true;
        }
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mWifiManager = (WifiManager) context.getSystemService("wifi");
        this.mTetherChangeReceiver = new TetherChangeReceiver();
        this.mSSIDPreferenceController = (WifiTetherSSIDPreferenceController) use(WifiTetherSSIDPreferenceController.class);
        this.mSecurityPreferenceController = (WifiTetherSecurityPreferenceController) use(WifiTetherSecurityPreferenceController.class);
        this.mPasswordPreferenceController = (WifiTetherPasswordPreferenceController) use(WifiTetherPasswordPreferenceController.class);
        this.mApBandPreferenceController = (WifiTetherApBandPreferenceController) use(WifiTetherApBandPreferenceController.class);
    }

    @Override // com.android.car.developeroptions.dashboard.RestrictedDashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.mUnavailable) {
            return;
        }
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        SwitchBar switchBar = settingsActivity.getSwitchBar();
        this.mSwitchBarController = new WifiTetherSwitchBarController(settingsActivity, new SwitchBarController(switchBar));
        getSettingsLifecycle().addObserver(this.mSwitchBarController);
        switchBar.show();
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (this.mUnavailable) {
            if (!isUiRestrictedByOnlyAdmin()) {
                getEmptyTextView().setText(R.string.tethering_settings_not_available);
            }
            getPreferenceScreen().removeAll();
        } else {
            Context context = getContext();
            if (context != null) {
                context.registerReceiver(this.mTetherChangeReceiver, TETHER_STATE_CHANGE_FILTER);
            }
        }
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStop() {
        Context context;
        super.onStop();
        if (this.mUnavailable || (context = getContext()) == null) {
            return;
        }
        context.unregisterReceiver(this.mTetherChangeReceiver);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, new WifiTetherBasePreferenceController.OnTetherConfigUpdateListener() { // from class: com.android.car.developeroptions.wifi.tether.-$$Lambda$9rxayhDei6avM6PDE3-jYTczQBg
            @Override // com.android.car.developeroptions.wifi.tether.WifiTetherBasePreferenceController.OnTetherConfigUpdateListener
            public final void onTetherConfigUpdated() {
                this.f$0.onTetherConfigUpdated();
            }
        });
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context, WifiTetherBasePreferenceController.OnTetherConfigUpdateListener onTetherConfigUpdateListener) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new WifiTetherSSIDPreferenceController(context, onTetherConfigUpdateListener));
        arrayList.add(new WifiTetherSecurityPreferenceController(context, onTetherConfigUpdateListener));
        arrayList.add(new WifiTetherPasswordPreferenceController(context, onTetherConfigUpdateListener));
        arrayList.add(new WifiTetherApBandPreferenceController(context, onTetherConfigUpdateListener));
        arrayList.add(new WifiTetherAutoOffPreferenceController(context, KEY_WIFI_TETHER_AUTO_OFF));
        return arrayList;
    }

    @Override // com.android.car.developeroptions.wifi.tether.WifiTetherBasePreferenceController.OnTetherConfigUpdateListener
    public void onTetherConfigUpdated() {
        SoftApConfiguration softApConfigurationBuildNewConfig = buildNewConfig();
        this.mPasswordPreferenceController.updateVisibility(softApConfigurationBuildNewConfig.getSecurityType());
        if (this.mWifiManager.getWifiApState() == 13) {
            Log.d("TetheringSettings", "Wifi AP config changed while enabled, stop and restart");
            this.mRestartWifiApAfterConfigChange = true;
            this.mSwitchBarController.stopTether();
        }
        this.mWifiManager.setSoftApConfiguration(softApConfigurationBuildNewConfig);
    }

    private SoftApConfiguration buildNewConfig() {
        int securityType = this.mSecurityPreferenceController.getSecurityType();
        return new SoftApConfiguration.Builder().setSsid(this.mSSIDPreferenceController.getSSID()).setPassphrase(this.mPasswordPreferenceController.getPasswordValidated(securityType), securityType).setBand(this.mApBandPreferenceController.getBand()).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startTether() {
        this.mRestartWifiApAfterConfigChange = false;
        this.mSwitchBarController.startTether();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDisplayWithNewConfig() {
        ((WifiTetherSSIDPreferenceController) use(WifiTetherSSIDPreferenceController.class)).updateDisplay();
        ((WifiTetherSecurityPreferenceController) use(WifiTetherSecurityPreferenceController.class)).updateDisplay();
        ((WifiTetherPasswordPreferenceController) use(WifiTetherPasswordPreferenceController.class)).updateDisplay();
        ((WifiTetherApBandPreferenceController) use(WifiTetherApBandPreferenceController.class)).updateDisplay();
    }

    class TetherChangeReceiver extends BroadcastReceiver {
        TetherChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("WifiTetherSettings", "updating display config due to receiving broadcast action " + action);
            WifiTetherSettings.this.updateDisplayWithNewConfig();
            if (action.equals("android.net.conn.TETHER_STATE_CHANGED")) {
                if (WifiTetherSettings.this.mWifiManager.getWifiApState() == 11 && WifiTetherSettings.this.mRestartWifiApAfterConfigChange) {
                    WifiTetherSettings.this.startTether();
                    return;
                }
                return;
            }
            if (action.equals("android.net.wifi.WIFI_AP_STATE_CHANGED") && intent.getIntExtra("wifi_state", 0) == 11 && WifiTetherSettings.this.mRestartWifiApAfterConfigChange) {
                WifiTetherSettings.this.startTether();
            }
        }
    }
}
