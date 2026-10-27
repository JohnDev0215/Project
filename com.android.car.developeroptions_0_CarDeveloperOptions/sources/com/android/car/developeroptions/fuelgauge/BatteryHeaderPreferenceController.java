package com.android.car.developeroptions.fuelgauge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.text.NumberFormat;
import android.os.PowerManager;
import android.text.TextUtils;
import android.widget.TextView;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.car.developeroptions.widget.EntityHeaderController;
import com.android.settingslib.Utils;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.widget.LayoutPreference;

/* JADX INFO: loaded from: classes.dex */
public class BatteryHeaderPreferenceController extends BasePreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnStart {
    static final String KEY_BATTERY_HEADER = "battery_header";
    private Activity mActivity;
    private LayoutPreference mBatteryLayoutPref;
    BatteryMeterView mBatteryMeterView;
    TextView mBatteryPercentText;
    private PreferenceFragmentCompat mHost;
    private Lifecycle mLifecycle;
    private final PowerManager mPowerManager;
    TextView mSummary1;
    TextView mSummary2;

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

    public BatteryHeaderPreferenceController(Context context, String str) {
        super(context, str);
        this.mPowerManager = (PowerManager) context.getSystemService(PowerManager.class);
    }

    public void setActivity(Activity activity) {
        this.mActivity = activity;
    }

    public void setFragment(PreferenceFragmentCompat preferenceFragmentCompat) {
        this.mHost = preferenceFragmentCompat;
    }

    public void setLifecycle(Lifecycle lifecycle) {
        this.mLifecycle = lifecycle;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        LayoutPreference layoutPreference = (LayoutPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mBatteryLayoutPref = layoutPreference;
        this.mBatteryMeterView = (BatteryMeterView) layoutPreference.findViewById(R.id.battery_header_icon);
        this.mBatteryPercentText = (TextView) this.mBatteryLayoutPref.findViewById(R.id.battery_percent);
        this.mSummary1 = (TextView) this.mBatteryLayoutPref.findViewById(R.id.summary1);
        this.mSummary2 = (TextView) this.mBatteryLayoutPref.findViewById(R.id.summary2);
        quickUpdateHeaderPreference();
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        EntityHeaderController entityHeaderControllerNewInstance = EntityHeaderController.newInstance(this.mActivity, this.mHost, this.mBatteryLayoutPref.findViewById(R.id.battery_entity_header));
        entityHeaderControllerNewInstance.setRecyclerView(this.mHost.getListView(), this.mLifecycle);
        entityHeaderControllerNewInstance.styleActionBar(this.mActivity);
    }

    public void updateHeaderPreference(BatteryInfo batteryInfo) {
        this.mBatteryPercentText.setText(formatBatteryPercentageText(batteryInfo.batteryLevel));
        CharSequence charSequence = batteryInfo.remainingLabel;
        if (charSequence == null) {
            this.mSummary1.setText(batteryInfo.statusLabel);
        } else {
            this.mSummary1.setText(charSequence);
        }
        this.mSummary2.setText("");
        this.mBatteryMeterView.setBatteryLevel(batteryInfo.batteryLevel);
        this.mBatteryMeterView.setCharging(!batteryInfo.discharging);
        this.mBatteryMeterView.setPowerSave(this.mPowerManager.isPowerSaveMode());
    }

    public void quickUpdateHeaderPreference() {
        Intent intentRegisterReceiver = this.mContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int batteryLevel = Utils.getBatteryLevel(intentRegisterReceiver);
        boolean z = intentRegisterReceiver.getIntExtra("plugged", -1) == 0;
        this.mBatteryMeterView.setBatteryLevel(batteryLevel);
        this.mBatteryMeterView.setCharging(!z);
        this.mBatteryMeterView.setPowerSave(this.mPowerManager.isPowerSaveMode());
        this.mBatteryPercentText.setText(formatBatteryPercentageText(batteryLevel));
    }

    private CharSequence formatBatteryPercentageText(int i) {
        return TextUtils.expandTemplate(this.mContext.getText(R.string.battery_header_title_alternate), NumberFormat.getIntegerInstance().format(i));
    }
}
