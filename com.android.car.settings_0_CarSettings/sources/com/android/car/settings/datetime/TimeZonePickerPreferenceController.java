package com.android.car.settings.datetime;

import android.car.drivingstate.CarUxRestrictions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.settingslib.datetime.ZoneGetter;
import java.util.Calendar;

/* JADX INFO: loaded from: classes5.dex */
public class TimeZonePickerPreferenceController extends PreferenceController<Preference> {
    private final IntentFilter mIntentFilter;
    private final BroadcastReceiver mTimeChangeReceiver;

    public TimeZonePickerPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mTimeChangeReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.datetime.TimeZonePickerPreferenceController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                TimeZonePickerPreferenceController.this.refreshUi();
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        this.mIntentFilter = intentFilter;
        intentFilter.addAction("android.intent.action.TIME_SET");
        this.mIntentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        getContext().registerReceiver(this.mTimeChangeReceiver, this.mIntentFilter);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        getContext().unregisterReceiver(this.mTimeChangeReceiver);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        Calendar now = Calendar.getInstance();
        preference.setSummary(ZoneGetter.getTimeZoneOffsetAndName(getContext(), now.getTimeZone(), now.getTime()));
        preference.setEnabled(!autoTimezoneIsEnabled());
    }

    private boolean autoTimezoneIsEnabled() {
        return Settings.Global.getInt(getContext().getContentResolver(), "auto_time_zone", 0) > 0;
    }
}
