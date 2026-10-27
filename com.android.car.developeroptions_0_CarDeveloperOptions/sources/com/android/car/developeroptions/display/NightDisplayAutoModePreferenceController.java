package com.android.car.developeroptions.display;

import android.content.Context;
import android.content.IntentFilter;
import android.hardware.display.ColorDisplayManager;
import androidx.preference.DropDownPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;

/* JADX INFO: loaded from: classes.dex */
public class NightDisplayAutoModePreferenceController extends BasePreferenceController implements Preference.OnPreferenceChangeListener {
    private ColorDisplayManager mColorDisplayManager;
    private DropDownPreference mPreference;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public NightDisplayAutoModePreferenceController(Context context, String str) {
        super(context, str);
        this.mColorDisplayManager = (ColorDisplayManager) context.getSystemService(ColorDisplayManager.class);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return ColorDisplayManager.isNightDisplayAvailable(this.mContext) ? 0 : 3;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        DropDownPreference dropDownPreference = (DropDownPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference = dropDownPreference;
        dropDownPreference.setEntries(new CharSequence[]{this.mContext.getString(R.string.night_display_auto_mode_never), this.mContext.getString(R.string.night_display_auto_mode_custom), this.mContext.getString(R.string.night_display_auto_mode_twilight)});
        this.mPreference.setEntryValues(new CharSequence[]{String.valueOf(0), String.valueOf(1), String.valueOf(2)});
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public final void updateState(Preference preference) {
        this.mPreference.setValue(String.valueOf(this.mColorDisplayManager.getNightDisplayAutoMode()));
    }

    @Override // androidx.preference.Preference.OnPreferenceChangeListener
    public final boolean onPreferenceChange(Preference preference, Object obj) {
        return this.mColorDisplayManager.setNightDisplayAutoMode(Integer.parseInt((String) obj));
    }
}
