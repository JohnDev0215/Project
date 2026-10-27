package com.android.car.settings.units;

import android.car.Car;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.car.settings.R;
import com.android.car.settings.common.CarSettingActivities;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.search.CarBaseSearchIndexProvider;
import com.android.settingslib.search.SearchIndexableRaw;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class UnitsSettingsFragment extends SettingsFragment {
    public static final CarBaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new CarBaseSearchIndexProvider(R.xml.units_fragment, CarSettingActivities.UnitsSettingsActivity.class) { // from class: com.android.car.settings.units.UnitsSettingsFragment.1
        private CarPropertyManager mCarPropertyManager;

        @Override // com.android.car.settings.search.CarBaseSearchIndexProvider, com.android.settingslib.search.Indexable.SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean enabled) {
            return null;
        }

        @Override // com.android.car.settings.search.CarBaseSearchIndexProvider, com.android.settingslib.search.Indexable.SearchIndexProvider
        public List<SearchIndexableRaw> getRawDataToIndex(Context context, boolean enabled) {
            List<SearchIndexableRaw> rawData = new ArrayList<>();
            Car car = Car.createCar(context);
            CarPropertyManager carPropertyManager = (CarPropertyManager) car.getCarManager("property");
            this.mCarPropertyManager = carPropertyManager;
            if (carPropertyManager != null) {
                boolean hasUnits = false;
                if (isValidVehicleProperty(289408516)) {
                    hasUnits = true;
                    rawData.add(createRawDataEntry(context, context.getString(R.string.pk_units_speed), context.getString(R.string.units_speed_title), context.getString(R.string.units_settings)));
                }
                if (isValidVehicleProperty(289408512)) {
                    hasUnits = true;
                    rawData.add(createRawDataEntry(context, context.getString(R.string.pk_units_distance), context.getString(R.string.units_distance_title), context.getString(R.string.units_settings)));
                }
                if (isValidVehicleProperty(289408513)) {
                    hasUnits = true;
                    rawData.add(createRawDataEntry(context, context.getString(R.string.pk_units_fuel_consumption), context.getString(R.string.units_fuel_consumption_title), context.getString(R.string.units_settings)));
                    rawData.add(createRawDataEntry(context, context.getString(R.string.pk_units_volume), context.getString(R.string.units_volume_title), context.getString(R.string.units_settings)));
                }
                if (isValidVehicleProperty(289408515)) {
                    hasUnits = true;
                    rawData.add(createRawDataEntry(context, context.getString(R.string.pk_units_energy_consumption), context.getString(R.string.units_energy_consumption_title), context.getString(R.string.units_settings)));
                }
                if (isValidVehicleProperty(289408270)) {
                    hasUnits = true;
                    rawData.add(createRawDataEntry(context, context.getString(R.string.pk_units_temperature), context.getString(R.string.units_temperature_title), context.getString(R.string.units_settings)));
                }
                if (isValidVehicleProperty(289408514)) {
                    hasUnits = true;
                    rawData.add(createRawDataEntry(context, context.getString(R.string.pk_units_pressure), context.getString(R.string.units_pressure_title), context.getString(R.string.units_settings)));
                }
                if (hasUnits) {
                    rawData.add(createRawDataEntry(context, context.getString(R.string.psk_units), context.getString(R.string.units_settings), context.getString(R.string.units_settings)));
                }
            }
            car.disconnect();
            return rawData;
        }

        private boolean isValidVehicleProperty(int propertyId) {
            return this.mCarPropertyManager.getIntProperty(propertyId, 0) != 0;
        }
    };

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.units_fragment;
    }
}
