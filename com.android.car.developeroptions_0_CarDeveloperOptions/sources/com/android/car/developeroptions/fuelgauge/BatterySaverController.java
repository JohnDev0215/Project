package com.android.car.developeroptions.fuelgauge;

import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.fuelgauge.BatterySaverReceiver;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.Utils;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.fuelgauge.BatterySaverUtils;

/* JADX INFO: loaded from: classes.dex */
public class BatterySaverController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop, BatterySaverReceiver.BatterySaverListener {
    private static final String KEY_BATTERY_SAVER = "battery_saver_summary";
    private Preference mBatterySaverPref;
    private final BatterySaverReceiver mBatteryStateChangeReceiver;
    private final ContentObserver mObserver;
    private final PowerManager mPowerManager;

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 1;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return KEY_BATTERY_SAVER;
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

    @Override // com.android.car.developeroptions.fuelgauge.BatterySaverReceiver.BatterySaverListener
    public void onBatteryChanged(boolean z) {
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public BatterySaverController(Context context) {
        super(context, KEY_BATTERY_SAVER);
        this.mObserver = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.android.car.developeroptions.fuelgauge.BatterySaverController.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                BatterySaverController.this.updateSummary();
            }
        };
        this.mPowerManager = (PowerManager) this.mContext.getSystemService("power");
        BatterySaverReceiver batterySaverReceiver = new BatterySaverReceiver(context);
        this.mBatteryStateChangeReceiver = batterySaverReceiver;
        batterySaverReceiver.setBatterySaverListener(this);
        BatterySaverUtils.revertScheduleToNoneIfNeeded(context);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mBatterySaverPref = preferenceScreen.findPreference(KEY_BATTERY_SAVER);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("low_power_trigger_level"), true, this.mObserver);
        this.mBatteryStateChangeReceiver.setListening(true);
        updateSummary();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mContext.getContentResolver().unregisterContentObserver(this.mObserver);
        this.mBatteryStateChangeReceiver.setListening(false);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        boolean zIsPowerSaveMode = this.mPowerManager.isPowerSaveMode();
        int i = Settings.Global.getInt(contentResolver, "low_power_trigger_level", 0);
        int i2 = Settings.Global.getInt(contentResolver, "automatic_power_save_mode", 0);
        if (zIsPowerSaveMode) {
            return this.mContext.getString(R.string.battery_saver_on_summary);
        }
        if (i2 != 0) {
            return this.mContext.getString(R.string.battery_saver_auto_routine);
        }
        if (i != 0) {
            return this.mContext.getString(R.string.battery_saver_off_scheduled_summary, Utils.formatPercentage(i));
        }
        return this.mContext.getString(R.string.battery_saver_off_summary);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSummary() {
        this.mBatterySaverPref.setSummary(getSummary());
    }

    @Override // com.android.car.developeroptions.fuelgauge.BatterySaverReceiver.BatterySaverListener
    public void onPowerSaveModeChanged() {
        updateSummary();
    }
}
