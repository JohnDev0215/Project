package com.android.car.settings.units;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.preference.ListPreference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;

/* JADX INFO: loaded from: classes5.dex */
public class UnitsEnergyConsumptionPreferenceController extends UnitsBasePreferenceController {
    private final String mKWh;

    public UnitsEnergyConsumptionPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mKWh = getContext().getString(UnitsMap.KILOWATT_HOUR.getAbbreviationResId());
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<ListPreference> getPreferenceType() {
        return ListPreference.class;
    }

    @Override // com.android.car.settings.units.UnitsBasePreferenceController
    protected int getPropertyId() {
        return 289408515;
    }

    @Override // com.android.car.settings.units.UnitsBasePreferenceController
    protected String generateSummaryFromUnit(Unit unit) {
        return getAbbreviationByUnit(unit);
    }

    @Override // com.android.car.settings.units.UnitsBasePreferenceController
    protected String generateEntryStringFromUnit(Unit unit) {
        int unitNameResId;
        if (unit.getId() == 36) {
            unitNameResId = R.string.units_unit_name_kilowatt_per_hundred_miles;
        } else {
            unitNameResId = R.string.units_unit_name_kilowatt_per_hundred_kilometers;
        }
        return getContext().getString(R.string.units_list_entry, getAbbreviationByUnit(unit), getContext().getString(unitNameResId));
    }

    private String getAbbreviationByUnit(Unit unit) {
        String denominator = getContext().getString(R.string.units_ratio_denominator, 100, getContext().getString(unit.getAbbreviationResId()));
        return getContext().getString(R.string.units_ratio, this.mKWh, denominator);
    }
}
