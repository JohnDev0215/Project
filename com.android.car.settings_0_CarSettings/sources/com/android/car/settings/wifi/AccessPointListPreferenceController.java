package com.android.car.settings.wifi;

import android.car.drivingstate.CarUxRestrictions;
import android.car.drivingstate.CarUxRestrictionsManager;
import android.content.Context;
import android.net.wifi.WifiManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.R;
import com.android.car.settings.common.CarUxRestrictionsHelper;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.wifi.ButtonPasswordEditTextPreference;
import com.android.car.settings.wifi.details.WifiDetailsFragment;
import com.android.settingslib.wifi.AccessPoint;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class AccessPointListPreferenceController extends WifiBasePreferenceController<PreferenceGroup> implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener, CarUxRestrictionsManager.OnUxRestrictionsChangedListener {
    private static final Logger LOG = new Logger((Class<?>) AccessPointListPreferenceController.class);
    private List<AccessPoint> mAccessPoints;
    private final WifiManager.ActionListener mConnectionListener;

    public AccessPointListPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mConnectionListener = new WifiManager.ActionListener() { // from class: com.android.car.settings.wifi.AccessPointListPreferenceController.1
            public void onSuccess() {
                AccessPointListPreferenceController.LOG.d("connected to network");
            }

            public void onFailure(int reason) {
                AccessPointListPreferenceController.LOG.d("Failed to connect to network. Failure code: " + reason);
            }
        };
        this.mAccessPoints = new ArrayList();
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preferenceGroup) {
        List<AccessPoint> allAccessPoints;
        if (getCarWifiManager() == null) {
            return;
        }
        if (CarUxRestrictionsHelper.isNoSetup(getUxRestrictions())) {
            allAccessPoints = getCarWifiManager().getSavedAccessPoints();
        } else {
            allAccessPoints = getCarWifiManager().getAllAccessPoints();
        }
        this.mAccessPoints = allAccessPoints;
        LOG.d("showing accessPoints: " + this.mAccessPoints.size());
        preferenceGroup.setVisible(this.mAccessPoints.isEmpty() ^ true);
        preferenceGroup.removeAll();
        for (AccessPoint accessPoint : this.mAccessPoints) {
            preferenceGroup.addPreference(createAccessPointPreference(accessPoint));
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onApplyUxRestrictions(CarUxRestrictions uxRestrictions) {
    }

    @Override // com.android.car.settings.wifi.WifiBasePreferenceController, com.android.car.settings.wifi.CarWifiManager.Listener
    public void onAccessPointsChanged() {
        refreshUi();
    }

    @Override // com.android.car.settings.wifi.CarWifiManager.Listener
    public void onWifiStateChanged(int state) {
        if (state == 3) {
            refreshUi();
        }
    }

    @Override // androidx.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        AccessPoint accessPoint = ((AccessPointPreference) preference).getAccessPoint();
        if (WifiUtil.isOpenNetwork(accessPoint.getSecurity()) && !accessPoint.isSaved() && !accessPoint.isActive()) {
            getCarWifiManager().connectToPublicWifi(accessPoint, this.mConnectionListener);
            return true;
        }
        if (accessPoint.isActive()) {
            getFragmentController().launchFragment(WifiDetailsFragment.getInstance(accessPoint));
            return true;
        }
        if (accessPoint.isSaved() && !WifiUtil.isAccessPointDisabledByWrongPassword(accessPoint)) {
            getCarWifiManager().connectToSavedWifi(accessPoint, this.mConnectionListener);
            return true;
        }
        return true;
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        AccessPoint accessPoint = ((AccessPointPreference) preference).getAccessPoint();
        WifiUtil.connectToAccessPoint(getContext(), accessPoint.getSsid().toString(), accessPoint.getSecurity(), newValue.toString(), false, this.mConnectionListener);
        return true;
    }

    private AccessPointPreference createAccessPointPreference(final AccessPoint accessPoint) {
        LOG.d("Adding preference for " + WifiUtil.getKey(accessPoint));
        AccessPointPreference accessPointPreference = new AccessPointPreference(getContext(), accessPoint);
        accessPointPreference.setKey(accessPoint.getKey());
        accessPointPreference.setTitle(accessPoint.getConfigName());
        accessPointPreference.setDialogTitle(accessPoint.getConfigName());
        accessPointPreference.setSummary(accessPoint.getSummary());
        accessPointPreference.setOnPreferenceClickListener(this);
        accessPointPreference.setOnPreferenceChangeListener(this);
        accessPointPreference.showButton(false);
        accessPointPreference.setShowChevron(false);
        if (accessPoint.isSaved()) {
            accessPointPreference.setWidgetLayoutResource(R.layout.delete_preference_widget);
            accessPointPreference.setOnButtonClickListener(new ButtonPasswordEditTextPreference.OnButtonClickListener() { // from class: com.android.car.settings.wifi.-$$Lambda$AccessPointListPreferenceController$aljwG32Ungtc4j0Tmix95NMEB3s
                @Override // com.android.car.settings.wifi.ButtonPasswordEditTextPreference.OnButtonClickListener
                public final void onButtonClick(ButtonPasswordEditTextPreference buttonPasswordEditTextPreference) {
                    this.f$0.lambda$createAccessPointPreference$0$AccessPointListPreferenceController(accessPoint, buttonPasswordEditTextPreference);
                }
            });
            accessPointPreference.showButton(true);
        }
        return accessPointPreference;
    }

    public /* synthetic */ void lambda$createAccessPointPreference$0$AccessPointListPreferenceController(AccessPoint accessPoint, ButtonPasswordEditTextPreference preference) {
        WifiUtil.forget(getContext(), accessPoint);
    }
}
