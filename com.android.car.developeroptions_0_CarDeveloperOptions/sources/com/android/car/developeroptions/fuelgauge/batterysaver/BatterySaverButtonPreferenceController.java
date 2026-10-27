package com.android.car.developeroptions.fuelgauge.batterysaver;

import android.content.Context;
import android.content.IntentFilter;
import android.os.PowerManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.core.TogglePreferenceController;
import com.android.car.developeroptions.fuelgauge.BatterySaverReceiver;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.car.developeroptions.widget.TwoStateButtonPreference;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.fuelgauge.BatterySaverUtils;

/* JADX INFO: loaded from: classes.dex */
public class BatterySaverButtonPreferenceController extends TogglePreferenceController implements LifecycleObserver, OnStart, OnStop, BatterySaverReceiver.BatterySaverListener {
    private final BatterySaverReceiver mBatterySaverReceiver;
    private final PowerManager mPowerManager;
    private TwoStateButtonPreference mPreference;

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 0;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public boolean isSliceable() {
        return true;
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public BatterySaverButtonPreferenceController(Context context, String str) {
        super(context, str);
        this.mPowerManager = (PowerManager) context.getSystemService("power");
        BatterySaverReceiver batterySaverReceiver = new BatterySaverReceiver(context);
        this.mBatterySaverReceiver = batterySaverReceiver;
        batterySaverReceiver.setBatterySaverListener(this);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mBatterySaverReceiver.setListening(true);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mBatterySaverReceiver.setListening(false);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (TwoStateButtonPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean isChecked() {
        return this.mPowerManager.isPowerSaveMode();
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController
    public boolean setChecked(boolean z) {
        return BatterySaverUtils.setPowerSaveMode(this.mContext, z, false);
    }

    @Override // com.android.car.developeroptions.core.TogglePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        super.updateState(preference);
        TwoStateButtonPreference twoStateButtonPreference = this.mPreference;
        if (twoStateButtonPreference != null) {
            twoStateButtonPreference.setChecked(isChecked());
        }
    }

    @Override // com.android.car.developeroptions.fuelgauge.BatterySaverReceiver.BatterySaverListener
    public void onPowerSaveModeChanged() {
        boolean zIsChecked = isChecked();
        TwoStateButtonPreference twoStateButtonPreference = this.mPreference;
        if (twoStateButtonPreference == null || twoStateButtonPreference.isChecked() == zIsChecked) {
            return;
        }
        this.mPreference.setChecked(zIsChecked);
    }

    @Override // com.android.car.developeroptions.fuelgauge.BatterySaverReceiver.BatterySaverListener
    public void onBatteryChanged(boolean z) {
        TwoStateButtonPreference twoStateButtonPreference = this.mPreference;
        if (twoStateButtonPreference != null) {
            twoStateButtonPreference.setButtonEnabled(!z);
        }
    }
}
