package com.android.car.developeroptions.fuelgauge;

import android.content.Context;
import android.content.IntentFilter;
import android.text.BidiFormatter;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.fuelgauge.BatteryBroadcastReceiver;
import com.android.car.developeroptions.fuelgauge.BatteryInfo;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;

/* JADX INFO: loaded from: classes.dex */
public class TopLevelBatteryPreferenceController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private final BatteryBroadcastReceiver mBatteryBroadcastReceiver;
    private BatteryInfo mBatteryInfo;
    private Preference mPreference;

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

    public TopLevelBatteryPreferenceController(Context context, String str) {
        super(context, str);
        BatteryBroadcastReceiver batteryBroadcastReceiver = new BatteryBroadcastReceiver(this.mContext);
        this.mBatteryBroadcastReceiver = batteryBroadcastReceiver;
        batteryBroadcastReceiver.setBatteryChangedListener(new BatteryBroadcastReceiver.OnBatteryChangedListener() { // from class: com.android.car.developeroptions.fuelgauge.-$$Lambda$TopLevelBatteryPreferenceController$Z2ICEnNb5vxCFt-Xck8rsLZoLhw
            @Override // com.android.car.developeroptions.fuelgauge.BatteryBroadcastReceiver.OnBatteryChangedListener
            public final void onBatteryChanged(int i) {
                this.f$0.lambda$new$1$TopLevelBatteryPreferenceController(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$1$TopLevelBatteryPreferenceController(int i) {
        BatteryInfo.getBatteryInfo(this.mContext, new BatteryInfo.Callback() { // from class: com.android.car.developeroptions.fuelgauge.-$$Lambda$TopLevelBatteryPreferenceController$ulr0jksYOYbm8oYPjhJKqLHBOwQ
            @Override // com.android.car.developeroptions.fuelgauge.BatteryInfo.Callback
            public final void onBatteryInfoLoaded(BatteryInfo batteryInfo) {
                this.f$0.lambda$new$0$TopLevelBatteryPreferenceController(batteryInfo);
            }
        }, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$0$TopLevelBatteryPreferenceController(BatteryInfo batteryInfo) {
        this.mBatteryInfo = batteryInfo;
        updateState(this.mPreference);
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(R.bool.config_show_top_level_battery) ? 1 : 3;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mBatteryBroadcastReceiver.register();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStop
    public void onStop() {
        this.mBatteryBroadcastReceiver.unRegister();
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        return getDashboardLabel(this.mContext, this.mBatteryInfo);
    }

    static CharSequence getDashboardLabel(Context context, BatteryInfo batteryInfo) {
        CharSequence charSequence;
        if (batteryInfo == null || context == null) {
            return null;
        }
        BidiFormatter bidiFormatter = BidiFormatter.getInstance();
        if (!batteryInfo.discharging && (charSequence = batteryInfo.chargeLabel) != null) {
            return charSequence;
        }
        if (batteryInfo.remainingLabel == null) {
            return batteryInfo.batteryPercentString;
        }
        return context.getString(R.string.power_remaining_settings_home_page, bidiFormatter.unicodeWrap(batteryInfo.batteryPercentString), bidiFormatter.unicodeWrap(batteryInfo.remainingLabel));
    }
}
