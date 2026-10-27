package com.android.car.developeroptions.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.UserHandle;
import android.util.Log;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.widget.RestrictedAppPreference;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class LocationServicePreferenceController extends LocationBasePreferenceController implements LifecycleObserver, OnResume, OnPause {
    static final IntentFilter INTENT_FILTER_INJECTED_SETTING_CHANGED = new IntentFilter("android.location.InjectedSettingChanged");
    private PreferenceCategory mCategoryLocationServices;
    private PreferenceCategory mCategoryLocationServicesManaged;
    private final LocationSettings mFragment;
    BroadcastReceiver mInjectedSettingsReceiver;
    private final AppSettingsInjector mInjector;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "location_services";
    }

    public LocationServicePreferenceController(Context context, LocationSettings locationSettings, Lifecycle lifecycle) {
        this(context, locationSettings, lifecycle, new AppSettingsInjector(context));
    }

    LocationServicePreferenceController(Context context, LocationSettings locationSettings, Lifecycle lifecycle, AppSettingsInjector appSettingsInjector) {
        super(context, lifecycle);
        this.mFragment = locationSettings;
        this.mInjector = appSettingsInjector;
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mCategoryLocationServices = (PreferenceCategory) preferenceScreen.findPreference("location_services");
        this.mCategoryLocationServicesManaged = (PreferenceCategory) preferenceScreen.findPreference("location_services_managed_profile");
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        this.mCategoryLocationServices.removeAll();
        this.mCategoryLocationServicesManaged.removeAll();
        boolean z = false;
        boolean z2 = false;
        for (Map.Entry<Integer, List<Preference>> entry : getLocationServices().entrySet()) {
            for (Preference preference2 : entry.getValue()) {
                if (preference2 instanceof RestrictedAppPreference) {
                    ((RestrictedAppPreference) preference2).checkRestrictionAndSetDisabled();
                }
            }
            if (entry.getKey().intValue() == UserHandle.myUserId()) {
                LocationSettings.addPreferencesSorted(entry.getValue(), this.mCategoryLocationServices);
                z = true;
            } else {
                LocationSettings.addPreferencesSorted(entry.getValue(), this.mCategoryLocationServicesManaged);
                z2 = true;
            }
        }
        this.mCategoryLocationServices.setVisible(z);
        this.mCategoryLocationServicesManaged.setVisible(z2);
    }

    @Override // com.android.car.developeroptions.location.LocationEnabler.LocationModeChangeListener
    public void onLocationModeChanged(int i, boolean z) {
        this.mInjector.reloadStatusMessages();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        if (this.mInjectedSettingsReceiver == null) {
            this.mInjectedSettingsReceiver = new BroadcastReceiver() { // from class: com.android.car.developeroptions.location.LocationServicePreferenceController.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (Log.isLoggable("LocationServicePrefCtrl", 3)) {
                        Log.d("LocationServicePrefCtrl", "Received settings change intent: " + intent);
                    }
                    LocationServicePreferenceController.this.mInjector.reloadStatusMessages();
                }
            };
        }
        this.mContext.registerReceiver(this.mInjectedSettingsReceiver, INTENT_FILTER_INJECTED_SETTING_CHANGED);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnPause
    public void onPause() {
        this.mContext.unregisterReceiver(this.mInjectedSettingsReceiver);
    }

    private Map<Integer, List<Preference>> getLocationServices() {
        int managedProfileId = Utils.getManagedProfileId(this.mUserManager, UserHandle.myUserId());
        return this.mInjector.getInjectedSettings(this.mFragment.getPreferenceManager().getContext(), (managedProfileId == -10000 || this.mLocationEnabler.getShareLocationEnforcedAdmin(managedProfileId) == null) ? -2 : UserHandle.myUserId());
    }
}
