package com.android.car.settings.datetime;

import android.car.drivingstate.CarUxRestrictions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.text.format.DateFormat;
import androidx.preference.TwoStatePreference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import java.util.Calendar;

/* JADX INFO: loaded from: classes5.dex */
public class TimeFormatTogglePreferenceController extends PreferenceController<TwoStatePreference> {
    private static final int DEMO_DAY_OF_MONTH = 31;
    private static final int DEMO_HOUR_OF_DAY = 13;
    private static final int DEMO_MINUTE = 0;
    private static final int DEMO_MONTH = 11;
    private static final int DEMO_SECOND = 0;
    public static final String HOURS_12 = "12";
    public static final String HOURS_24 = "24";
    private final BroadcastReceiver mTimeChangeReceiver;
    private final Calendar mTimeFormatDemoDate;

    public TimeFormatTogglePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mTimeFormatDemoDate = Calendar.getInstance();
        this.mTimeChangeReceiver = new BroadcastReceiver() { // from class: com.android.car.settings.datetime.TimeFormatTogglePreferenceController.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                TimeFormatTogglePreferenceController.this.refreshUi();
            }
        };
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<TwoStatePreference> getPreferenceType() {
        return TwoStatePreference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        getContext().registerReceiver(this.mTimeChangeReceiver, new IntentFilter("android.intent.action.TIME_SET"));
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        getContext().unregisterReceiver(this.mTimeChangeReceiver);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(TwoStatePreference preference) {
        Calendar now = Calendar.getInstance();
        this.mTimeFormatDemoDate.setTimeZone(now.getTimeZone());
        this.mTimeFormatDemoDate.set(now.get(1), 11, 31, 13, 0, 0);
        preference.setSummary(DateFormat.getTimeFormat(getContext()).format(this.mTimeFormatDemoDate.getTime()));
        preference.setChecked(is24Hour());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(TwoStatePreference preference, Object newValue) {
        int timeFormatPreference;
        boolean isUse24HourFormatEnabled = ((Boolean) newValue).booleanValue();
        Settings.System.putString(getContext().getContentResolver(), "time_12_24", isUse24HourFormatEnabled ? HOURS_24 : HOURS_12);
        Intent timeChanged = new Intent("android.intent.action.TIME_SET");
        if (isUse24HourFormatEnabled) {
            timeFormatPreference = 1;
        } else {
            timeFormatPreference = 0;
        }
        timeChanged.putExtra("android.intent.extra.TIME_PREF_24_HOUR_FORMAT", timeFormatPreference);
        getContext().sendBroadcast(timeChanged);
        return true;
    }

    private boolean is24Hour() {
        return DateFormat.is24HourFormat(getContext());
    }
}
