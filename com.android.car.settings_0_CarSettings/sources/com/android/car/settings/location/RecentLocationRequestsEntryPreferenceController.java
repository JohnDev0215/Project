package com.android.car.settings.location;

import android.car.drivingstate.CarUxRestrictions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public class RecentLocationRequestsEntryPreferenceController extends PreferenceController<Preference> {
    private static final IntentFilter INTENT_FILTER_LOCATION_MODE_CHANGED = new IntentFilter("android.location.MODE_CHANGED");
    private final LocationManager mLocationManager;
    private final BroadcastReceiver mReceiver;

    public RecentLocationRequestsEntryPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.location.RecentLocationRequestsEntryPreferenceController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                RecentLocationRequestsEntryPreferenceController.this.refreshUi();
            }
        };
        this.mLocationManager = (LocationManager) getContext().getSystemService("location");
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        preference.setEnabled(this.mLocationManager.isLocationEnabled());
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        getContext().registerReceiver(this.mReceiver, INTENT_FILTER_LOCATION_MODE_CHANGED);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        getContext().unregisterReceiver(this.mReceiver);
    }
}
