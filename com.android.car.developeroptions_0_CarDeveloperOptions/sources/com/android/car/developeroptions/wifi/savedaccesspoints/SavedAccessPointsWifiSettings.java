package com.android.car.developeroptions.wifi.savedaccesspoints;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.FeatureFlagUtils;
import android.util.Log;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.development.featureflags.FeatureFlagPersistent;
import com.android.car.developeroptions.wifi.WifiDialog;
import com.android.car.developeroptions.wifi.details.WifiNetworkDetailsFragment;
import com.android.settingslib.wifi.AccessPoint;
import com.android.settingslib.wifi.AccessPointPreference;

/* JADX INFO: loaded from: classes.dex */
public class SavedAccessPointsWifiSettings extends DashboardFragment implements WifiDialog.WifiDialogListener, DialogInterface.OnCancelListener {
    private Bundle mAccessPointSavedState;
    private AccessPoint mSelectedAccessPoint;
    private WifiManager mWifiManager;

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public int getDialogMetricsCategory(int i) {
        return i != 1 ? 0 : 602;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "SavedAccessPoints";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 106;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.wifi_display_saved_access_points;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mWifiManager = (WifiManager) getContext().getApplicationContext().getSystemService("wifi");
        ((SavedAccessPointsPreferenceController) use(SavedAccessPointsPreferenceController.class)).setHost(this);
        ((SubscribedAccessPointsPreferenceController) use(SubscribedAccessPointsPreferenceController.class)).setHost(this);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null || !bundle.containsKey("wifi_ap_state")) {
            return;
        }
        this.mAccessPointSavedState = bundle.getBundle("wifi_ap_state");
    }

    public void showWifiDialog(AccessPointPreference accessPointPreference) {
        removeDialog(1);
        if (accessPointPreference != null) {
            this.mSelectedAccessPoint = accessPointPreference.getAccessPoint();
        } else {
            this.mSelectedAccessPoint = null;
            this.mAccessPointSavedState = null;
        }
        if (usingDetailsFragment(getContext())) {
            if (this.mSelectedAccessPoint == null) {
                this.mSelectedAccessPoint = new AccessPoint(getActivity(), this.mAccessPointSavedState);
            }
            Bundle bundle = new Bundle();
            this.mSelectedAccessPoint.saveWifiState(bundle);
            SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getContext());
            subSettingLauncher.setTitleText(this.mSelectedAccessPoint.getTitle());
            subSettingLauncher.setDestination(WifiNetworkDetailsFragment.class.getName());
            subSettingLauncher.setArguments(bundle);
            subSettingLauncher.setSourceMetricsCategory(getMetricsCategory());
            subSettingLauncher.launch();
            return;
        }
        showDialog(1);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.DialogCreatable
    public Dialog onCreateDialog(int i) {
        if (i == 1) {
            if (this.mSelectedAccessPoint == null) {
                this.mSelectedAccessPoint = new AccessPoint(getActivity(), this.mAccessPointSavedState);
                this.mAccessPointSavedState = null;
            }
            WifiDialog wifiDialogCreateModal = WifiDialog.createModal(getActivity(), this, this.mSelectedAccessPoint, 0);
            wifiDialogCreateModal.setOnCancelListener(this);
            return wifiDialogCreateModal;
        }
        return super.onCreateDialog(i);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mSelectedAccessPoint != null) {
            Bundle bundle2 = new Bundle();
            this.mAccessPointSavedState = bundle2;
            this.mSelectedAccessPoint.saveWifiState(bundle2);
            bundle.putBundle("wifi_ap_state", this.mAccessPointSavedState);
        }
    }

    @Override // com.android.car.developeroptions.wifi.WifiDialog.WifiDialogListener
    public void onForget(WifiDialog wifiDialog) {
        AccessPoint accessPoint = this.mSelectedAccessPoint;
        if (accessPoint != null) {
            if (accessPoint.isPasspointConfig()) {
                try {
                    this.mWifiManager.removePasspointConfiguration(this.mSelectedAccessPoint.getPasspointFqdn());
                } catch (RuntimeException unused) {
                    Log.e("SavedAccessPoints", "Failed to remove Passpoint configuration for " + this.mSelectedAccessPoint.getConfigName());
                }
                if (isSubscriptionsFeatureEnabled()) {
                    ((SubscribedAccessPointsPreferenceController) use(SubscribedAccessPointsPreferenceController.class)).postRefreshSubscribedAccessPoints();
                } else {
                    ((SavedAccessPointsPreferenceController) use(SavedAccessPointsPreferenceController.class)).postRefreshSavedAccessPoints();
                }
            } else {
                this.mWifiManager.forget(this.mSelectedAccessPoint.getConfig().networkId, (WifiManager.ActionListener) use(SavedAccessPointsPreferenceController.class));
            }
            this.mSelectedAccessPoint = null;
        }
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        this.mSelectedAccessPoint = null;
    }

    public static boolean usingDetailsFragment(Context context) {
        return FeatureFlagUtils.isEnabled(context, "settings_wifi_details_saved_screen");
    }

    boolean isSubscriptionsFeatureEnabled() {
        return FeatureFlagUtils.isEnabled(getContext(), "settings_mobile_network_v2") && FeatureFlagPersistent.isEnabled(getContext(), "settings_network_and_internet_v2");
    }
}
