package com.android.car.developeroptions.fuelgauge.batterytip.tips;

import android.content.Context;
import android.content.res.Resources;
import android.icu.text.ListFormatter;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.fuelgauge.batterytip.AppInfo;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RestrictAppTip extends BatteryTip {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.android.car.developeroptions.fuelgauge.batterytip.tips.RestrictAppTip.1
        @Override // android.os.Parcelable.Creator
        public BatteryTip createFromParcel(Parcel parcel) {
            return new RestrictAppTip(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public BatteryTip[] newArray(int i) {
            return new RestrictAppTip[i];
        }
    };
    private List<AppInfo> mRestrictAppList;

    public RestrictAppTip(int i, List<AppInfo> list) {
        super(1, i, i == 0);
        this.mRestrictAppList = list;
        this.mNeedUpdate = false;
    }

    public RestrictAppTip(int i, AppInfo appInfo) {
        super(1, i, i == 0);
        ArrayList arrayList = new ArrayList();
        this.mRestrictAppList = arrayList;
        arrayList.add(appInfo);
        this.mNeedUpdate = false;
    }

    RestrictAppTip(Parcel parcel) {
        super(parcel);
        this.mRestrictAppList = parcel.createTypedArrayList(AppInfo.CREATOR);
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip
    public CharSequence getTitle(Context context) {
        int size = this.mRestrictAppList.size();
        String applicationLabel = size > 0 ? Utils.getApplicationLabel(context, this.mRestrictAppList.get(0).packageName) : "";
        Resources resources = context.getResources();
        return this.mState == 1 ? resources.getQuantityString(R.plurals.battery_tip_restrict_handled_title, size, applicationLabel, Integer.valueOf(size)) : resources.getQuantityString(R.plurals.battery_tip_restrict_title, size, Integer.valueOf(size));
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip
    public CharSequence getSummary(Context context) {
        int size = this.mRestrictAppList.size();
        return context.getResources().getQuantityString(this.mState == 1 ? R.plurals.battery_tip_restrict_handled_summary : R.plurals.battery_tip_restrict_summary, size, size > 0 ? Utils.getApplicationLabel(context, this.mRestrictAppList.get(0).packageName) : "", Integer.valueOf(size));
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip
    public int getIconId() {
        return this.mState == 1 ? R.drawable.ic_perm_device_information_green_24dp : R.drawable.ic_battery_alert_24dp;
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip
    public void updateState(BatteryTip batteryTip) {
        int i = batteryTip.mState;
        if (i == 0) {
            this.mState = 0;
            this.mRestrictAppList = ((RestrictAppTip) batteryTip).mRestrictAppList;
            this.mShowDialog = true;
        } else if (this.mState == 0 && i == 2) {
            this.mState = 1;
            this.mShowDialog = false;
        } else {
            this.mState = batteryTip.getState();
            this.mShowDialog = batteryTip.shouldShowDialog();
            this.mRestrictAppList = ((RestrictAppTip) batteryTip).mRestrictAppList;
        }
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip
    public void sanityCheck(Context context) {
        super.sanityCheck(context);
        this.mRestrictAppList.removeIf(AppLabelPredicate.getInstance(context));
        if (this.mRestrictAppList.isEmpty()) {
            this.mState = 2;
        }
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip
    public void log(Context context, MetricsFeatureProvider metricsFeatureProvider) {
        metricsFeatureProvider.action(context, 1347, this.mState);
        if (this.mState == 0) {
            int size = this.mRestrictAppList.size();
            for (int i = 0; i < size; i++) {
                AppInfo appInfo = this.mRestrictAppList.get(i);
                Iterator<Integer> it = appInfo.anomalyTypes.iterator();
                while (it.hasNext()) {
                    metricsFeatureProvider.action(0, 1353, 0, appInfo.packageName, it.next().intValue());
                }
            }
        }
    }

    public List<AppInfo> getRestrictAppList() {
        return this.mRestrictAppList;
    }

    public CharSequence getRestrictAppsString(Context context) {
        ArrayList arrayList = new ArrayList();
        int size = this.mRestrictAppList.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(Utils.getApplicationLabel(context, this.mRestrictAppList.get(i).packageName));
        }
        return ListFormatter.getInstance().format(arrayList);
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(" {");
        int size = this.mRestrictAppList.size();
        for (int i = 0; i < size; i++) {
            sb.append(" " + this.mRestrictAppList.get(i).toString() + " ");
        }
        sb.append('}');
        return sb.toString();
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.mRestrictAppList);
    }
}
