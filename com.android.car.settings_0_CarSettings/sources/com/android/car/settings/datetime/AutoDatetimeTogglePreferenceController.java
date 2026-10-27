package com.android.car.settings.datetime;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import androidx.preference.TwoStatePreference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public class AutoDatetimeTogglePreferenceController extends PreferenceController<TwoStatePreference> {
    public AutoDatetimeTogglePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<TwoStatePreference> getPreferenceType() {
        return TwoStatePreference.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(TwoStatePreference preference) {
        preference.setChecked(isEnabled());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(TwoStatePreference twoStatePreference, Object obj) {
        Settings.Global.putInt(getContext().getContentResolver(), "auto_time", ((Boolean) obj).booleanValue() ? 1 : 0);
        getContext().sendBroadcast(new Intent("android.intent.action.TIME_SET"));
        return true;
    }

    private boolean isEnabled() {
        return Settings.Global.getInt(getContext().getContentResolver(), "auto_time", 0) > 0;
    }
}
