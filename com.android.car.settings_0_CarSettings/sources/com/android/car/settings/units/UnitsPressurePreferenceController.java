package com.android.car.settings.units;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.preference.ListPreference;
import com.android.car.settings.common.FragmentController;

/* JADX INFO: loaded from: classes5.dex */
public class UnitsPressurePreferenceController extends UnitsBasePreferenceController {
    public UnitsPressurePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<ListPreference> getPreferenceType() {
        return ListPreference.class;
    }

    @Override // com.android.car.settings.units.UnitsBasePreferenceController
    protected int getPropertyId() {
        return 289408514;
    }
}
