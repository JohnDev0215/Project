package com.android.car.settings.units;

import android.car.CarNotConnectedException;
import android.car.drivingstate.CarUxRestrictions;
import android.car.hardware.CarPropertyValue;
import android.car.hardware.property.CarPropertyManager;
import android.content.Context;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import androidx.preference.ListPreference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.units.CarUnitsManager;

/* JADX INFO: loaded from: classes5.dex */
public abstract class UnitsBasePreferenceController extends PreferenceController<ListPreference> {
    protected final CarPropertyManager.CarPropertyEventCallback mCarPropertyEventCallback;
    private CarUnitsManager mCarUnitsManager;
    private Unit mDefaultUnit;
    private boolean mIsCarUnitsManagerStarted;
    protected final CarUnitsManager.OnCarServiceListener mOnCarServiceListener;
    private Unit[] mSupportedUnits;
    private Unit mUnitBeingUsed;

    protected abstract int getPropertyId();

    public UnitsBasePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mOnCarServiceListener = new CarUnitsManager.OnCarServiceListener() { // from class: com.android.car.settings.units.UnitsBasePreferenceController.1
            @Override // com.android.car.settings.units.CarUnitsManager.OnCarServiceListener
            public void handleServiceConnected(CarPropertyManager carPropertyManager) {
                if (carPropertyManager != null) {
                    try {
                        carPropertyManager.registerCallback(UnitsBasePreferenceController.this.mCarPropertyEventCallback, UnitsBasePreferenceController.this.getPropertyId(), 0.0f);
                    } catch (CarNotConnectedException e) {
                        return;
                    }
                }
                UnitsBasePreferenceController.this.mSupportedUnits = UnitsBasePreferenceController.this.mCarUnitsManager.getUnitsSupportedByProperty(UnitsBasePreferenceController.this.getPropertyId());
                if (UnitsBasePreferenceController.this.mSupportedUnits != null && UnitsBasePreferenceController.this.mSupportedUnits.length > 0) {
                    UnitsBasePreferenceController.this.mDefaultUnit = UnitsBasePreferenceController.this.mSupportedUnits[0];
                    ((ListPreference) UnitsBasePreferenceController.this.getPreference()).setEntries(UnitsBasePreferenceController.this.getEntriesOfSupportedUnits());
                    ((ListPreference) UnitsBasePreferenceController.this.getPreference()).setEntryValues(UnitsBasePreferenceController.this.getIdsOfSupportedUnits());
                    ((ListPreference) UnitsBasePreferenceController.this.getPreference()).setValue(Integer.toString(UnitsBasePreferenceController.this.getUnitUsedByThisProperty().getId()));
                    UnitsBasePreferenceController.this.refreshUi();
                }
                UnitsBasePreferenceController.this.mIsCarUnitsManagerStarted = true;
            }

            @Override // com.android.car.settings.units.CarUnitsManager.OnCarServiceListener
            public void handleServiceDisconnected() {
                UnitsBasePreferenceController.this.mIsCarUnitsManagerStarted = false;
            }
        };
        this.mCarPropertyEventCallback = new CarPropertyManager.CarPropertyEventCallback() { // from class: com.android.car.settings.units.UnitsBasePreferenceController.2
            @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
            public void onChangeEvent(CarPropertyValue value) {
                if (value != null && value.getStatus() == 0) {
                    UnitsBasePreferenceController.this.mUnitBeingUsed = UnitsMap.MAP.get(value.getValue());
                    UnitsBasePreferenceController.this.refreshUi();
                }
            }

            @Override // android.car.hardware.property.CarPropertyManager.CarPropertyEventCallback
            public void onErrorEvent(int propId, int zone) {
            }
        };
        this.mIsCarUnitsManagerStarted = false;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        super.onCreateInternal();
        CarUnitsManager carUnitsManager = new CarUnitsManager(getContext());
        this.mCarUnitsManager = carUnitsManager;
        carUnitsManager.connect();
        this.mCarUnitsManager.registerCarServiceListener(this.mOnCarServiceListener);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onDestroyInternal() {
        super.onDestroyInternal();
        this.mCarUnitsManager.disconnect();
        this.mCarUnitsManager.unregisterCarServiceListener();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(ListPreference preference) {
        Unit unit;
        if (this.mIsCarUnitsManagerStarted && (unit = this.mUnitBeingUsed) != null) {
            preference.setSummary(generateSummaryFromUnit(unit));
            preference.setValue(Integer.toString(this.mUnitBeingUsed.getId()));
        }
    }

    @Override // com.android.car.settings.common.PreferenceController
    public boolean handlePreferenceChanged(ListPreference preference, Object newValue) {
        int unitId = Integer.parseInt((String) newValue);
        this.mCarUnitsManager.setUnitUsedByProperty(getPropertyId(), unitId);
        return true;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        Unit[] unitArr = this.mSupportedUnits;
        return (unitArr == null || unitArr.length <= 0) ? 1 : 0;
    }

    protected String[] getEntriesOfSupportedUnits() {
        String[] names = new String[this.mSupportedUnits.length];
        for (int i = 0; i < names.length; i++) {
            Unit unit = this.mSupportedUnits[i];
            names[i] = generateEntryStringFromUnit(unit);
        }
        return names;
    }

    protected String generateSummaryFromUnit(Unit unit) {
        return getContext().getString(unit.getAbbreviationResId());
    }

    protected String generateEntryStringFromUnit(Unit unit) {
        return BidiFormatter.getInstance().unicodeWrap(getContext().getString(R.string.units_list_entry, getContext().getString(unit.getAbbreviationResId()), getContext().getString(unit.getNameResId())), TextDirectionHeuristics.LOCALE);
    }

    protected String[] getIdsOfSupportedUnits() {
        String[] ids = new String[this.mSupportedUnits.length];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = Integer.toString(this.mSupportedUnits[i].getId());
        }
        return ids;
    }

    protected CarUnitsManager getCarUnitsManager() {
        return this.mCarUnitsManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Unit getUnitUsedByThisProperty() {
        Unit savedUnit = this.mCarUnitsManager.getUnitUsedByProperty(getPropertyId());
        if (savedUnit == null) {
            return this.mDefaultUnit;
        }
        return savedUnit;
    }
}
