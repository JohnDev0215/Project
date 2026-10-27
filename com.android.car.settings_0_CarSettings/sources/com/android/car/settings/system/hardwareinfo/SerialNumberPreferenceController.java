package com.android.car.settings.system.hardwareinfo;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.Build;
import androidx.preference.Preference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public class SerialNumberPreferenceController extends PreferenceController<Preference> {
    public SerialNumberPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        preference.setSummary(Build.getSerial());
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        return 4;
    }
}
