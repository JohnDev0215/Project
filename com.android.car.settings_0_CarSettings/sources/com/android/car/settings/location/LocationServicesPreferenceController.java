package com.android.car.settings.location;

import android.car.drivingstate.CarUxRestrictions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.settingslib.location.SettingsInjector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class LocationServicesPreferenceController extends PreferenceController<PreferenceGroup> {
    private final BroadcastReceiver mReceiver;
    private SettingsInjector mSettingsInjector;
    private static final Logger LOG = new Logger((Class<?>) LocationServicesPreferenceController.class);
    private static final IntentFilter INTENT_FILTER_INJECTED_SETTING_CHANGED = new IntentFilter("android.location.InjectedSettingChanged");

    public LocationServicesPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.location.LocationServicesPreferenceController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                LocationServicesPreferenceController.LOG.i("Received injected settings change intent: " + intent);
                LocationServicesPreferenceController.this.mSettingsInjector.reloadStatusMessages();
            }
        };
        this.mSettingsInjector = new CarLocationSettingsInjector(context);
    }

    void setSettingsInjector(SettingsInjector injector) {
        this.mSettingsInjector = injector;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<PreferenceGroup> getPreferenceType() {
        return PreferenceGroup.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        getContext().registerReceiver(this.mReceiver, INTENT_FILTER_INJECTED_SETTING_CHANGED);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        getContext().unregisterReceiver(this.mReceiver);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(PreferenceGroup preferenceGroup) {
        getPreference().removeAll();
        List<Preference> injectedSettings = getSortedInjectedPreferences(-2);
        for (Preference preference : injectedSettings) {
            getPreference().addPreference(preference);
        }
        preferenceGroup.setVisible(preferenceGroup.getPreferenceCount() > 0);
    }

    private List<Preference> getSortedInjectedPreferences(int profileId) {
        List<Preference> sortedInjections = new ArrayList<>();
        Map<Integer, List<Preference>> injections = this.mSettingsInjector.getInjectedSettings(getContext(), profileId);
        for (Map.Entry<Integer, List<Preference>> entry : injections.entrySet()) {
            sortedInjections.addAll(entry.getValue());
        }
        Collections.sort(sortedInjections);
        return sortedInjections;
    }
}
