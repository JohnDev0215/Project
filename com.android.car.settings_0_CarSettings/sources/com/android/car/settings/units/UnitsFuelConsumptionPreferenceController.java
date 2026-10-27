package com.android.car.settings.units;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import androidx.preference.ListPreference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;

/* JADX INFO: loaded from: classes5.dex */
public class UnitsFuelConsumptionPreferenceController extends UnitsBasePreferenceController {
    public UnitsFuelConsumptionPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<ListPreference> getPreferenceType() {
        return ListPreference.class;
    }

    @Override // com.android.car.settings.units.UnitsBasePreferenceController
    protected int getPropertyId() {
        return 289408513;
    }

    @Override // com.android.car.settings.units.UnitsBasePreferenceController
    protected String generateSummaryFromUnit(Unit unit) {
        return getAbbreviationByVolumeUnit(unit);
    }

    @Override // com.android.car.settings.units.UnitsBasePreferenceController
    protected String generateEntryStringFromUnit(Unit unit) {
        return getContext().getString(R.string.units_list_entry, getAbbreviationByVolumeUnit(unit), getPronouncedNameByVolumeUnit(unit));
    }

    private String getAbbreviationByVolumeUnit(Unit unit) {
        int id = unit.getId();
        if (id == 66) {
            String abbreviation = getContext().getString(R.string.units_unit_abbreviation_miles_per_gallon_us);
            return abbreviation;
        }
        if (id == 67) {
            String abbreviation2 = getContext().getString(R.string.units_unit_abbreviation_miles_per_gallon_uk);
            return abbreviation2;
        }
        if (getCarUnitsManager().isDistanceOverVolume()) {
            String abbreviation3 = getContext().getString(R.string.units_unit_abbreviation_kilometers_per_liter);
            return abbreviation3;
        }
        String abbreviation4 = getContext().getString(R.string.units_unit_abbreviation_liters_per_hundred_kilometers);
        return abbreviation4;
    }

    private String getPronouncedNameByVolumeUnit(Unit unit) {
        int id = unit.getId();
        if (id == 66) {
            String pronounced = getContext().getString(R.string.units_unit_name_miles_per_gallon_us);
            return pronounced;
        }
        if (id == 67) {
            String pronounced2 = getContext().getString(R.string.units_unit_name_miles_per_gallon_uk);
            return pronounced2;
        }
        if (getCarUnitsManager().isDistanceOverVolume()) {
            String pronounced3 = getContext().getString(R.string.units_unit_name_kilometers_per_liter);
            return pronounced3;
        }
        String pronounced4 = getContext().getString(R.string.units_unit_name_liter_per_hundred_kilometers);
        return pronounced4;
    }
}
