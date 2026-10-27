package com.android.car.developeroptions.fuelgauge.batterytip.tips;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.car.developeroptions.R;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;

/* JADX INFO: loaded from: classes.dex */
public class EarlyWarningTip extends BatteryTip {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.android.car.developeroptions.fuelgauge.batterytip.tips.EarlyWarningTip.1
        @Override // android.os.Parcelable.Creator
        public BatteryTip createFromParcel(Parcel parcel) {
            return new EarlyWarningTip(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public BatteryTip[] newArray(int i) {
            return new EarlyWarningTip[i];
        }
    };
    private boolean mPowerSaveModeOn;

    public EarlyWarningTip(int i, boolean z) {
        super(3, i, false);
        this.mPowerSaveModeOn = z;
    }

    public EarlyWarningTip(Parcel parcel) {
        super(parcel);
        this.mPowerSaveModeOn = parcel.readBoolean();
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip
    public CharSequence getTitle(Context context) {
        return context.getString(this.mState == 1 ? R.string.battery_tip_early_heads_up_done_title : R.string.battery_tip_early_heads_up_title);
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip
    public CharSequence getSummary(Context context) {
        return context.getString(this.mState == 1 ? R.string.battery_tip_early_heads_up_done_summary : R.string.battery_tip_early_heads_up_summary);
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip
    public int getIconId() {
        return this.mState == 1 ? R.drawable.ic_battery_status_maybe_24dp : R.drawable.ic_battery_status_bad_24dp;
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip
    public int getIconTintColorId() {
        return this.mState == 1 ? R.color.battery_maybe_color_light : R.color.battery_bad_color_light;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0019  */
    @Override // com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void updateState(com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip r3) {
        /*
            r2 = this;
            com.android.car.developeroptions.fuelgauge.batterytip.tips.EarlyWarningTip r3 = (com.android.car.developeroptions.fuelgauge.batterytip.tips.EarlyWarningTip) r3
            int r0 = r3.mState
            if (r0 != 0) goto La
            r0 = 0
            r2.mState = r0
            goto L1f
        La:
            int r1 = r2.mState
            if (r1 != 0) goto L19
            r1 = 2
            if (r0 != r1) goto L19
            boolean r0 = r3.mPowerSaveModeOn
            if (r0 == 0) goto L16
            r1 = 1
        L16:
            r2.mState = r1
            goto L1f
        L19:
            int r0 = r3.getState()
            r2.mState = r0
        L1f:
            boolean r3 = r3.mPowerSaveModeOn
            r2.mPowerSaveModeOn = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.fuelgauge.batterytip.tips.EarlyWarningTip.updateState(com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip):void");
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip
    public void log(Context context, MetricsFeatureProvider metricsFeatureProvider) {
        metricsFeatureProvider.action(context, 1351, this.mState);
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeBoolean(this.mPowerSaveModeOn);
    }
}
