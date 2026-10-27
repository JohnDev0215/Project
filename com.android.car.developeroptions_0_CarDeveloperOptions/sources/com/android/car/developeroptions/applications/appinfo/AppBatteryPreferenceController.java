package com.android.car.developeroptions.applications.appinfo;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.UserManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.fuelgauge.AdvancedPowerUsageDetail;
import com.android.car.developeroptions.fuelgauge.BatteryEntry;
import com.android.car.developeroptions.fuelgauge.BatteryStatsHelperLoader;
import com.android.car.developeroptions.fuelgauge.BatteryUtils;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.internal.os.BatterySipper;
import com.android.internal.os.BatteryStatsHelper;
import com.android.settingslib.Utils;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AppBatteryPreferenceController extends BasePreferenceController implements LoaderManager.LoaderCallbacks<BatteryStatsHelper>, LifecycleObserver, OnResume, OnPause {
    private static final String KEY_BATTERY = "battery";
    BatteryStatsHelper mBatteryHelper;
    private String mBatteryPercent;
    BatteryUtils mBatteryUtils;
    private final String mPackageName;
    private final AppInfoDashboardFragment mParent;
    private Preference mPreference;
    BatterySipper mSipper;

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

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<BatteryStatsHelper> loader) {
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public AppBatteryPreferenceController(Context context, AppInfoDashboardFragment appInfoDashboardFragment, String str, Lifecycle lifecycle) {
        super(context, KEY_BATTERY);
        this.mParent = appInfoDashboardFragment;
        this.mBatteryUtils = BatteryUtils.getInstance(this.mContext);
        this.mPackageName = str;
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(R.bool.config_show_app_info_settings_battery) ? 0 : 2;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference preferenceFindPreference = preferenceScreen.findPreference(getPreferenceKey());
        this.mPreference = preferenceFindPreference;
        preferenceFindPreference.setEnabled(false);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!KEY_BATTERY.equals(preference.getKey())) {
            return false;
        }
        if (isBatteryStatsAvailable()) {
            BatteryEntry batteryEntry = new BatteryEntry(this.mContext, null, (UserManager) this.mContext.getSystemService("user"), this.mSipper);
            batteryEntry.defaultPackageName = this.mPackageName;
            AdvancedPowerUsageDetail.startBatteryDetailPage(this.mParent.getActivity(), this.mParent, this.mBatteryHelper, 0, batteryEntry, this.mBatteryPercent);
            return true;
        }
        AdvancedPowerUsageDetail.startBatteryDetailPage(this.mParent.getActivity(), this.mParent, this.mPackageName);
        return true;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnResume
    public void onResume() {
        this.mParent.getLoaderManager().restartLoader(4, Bundle.EMPTY, this);
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnPause
    public void onPause() {
        this.mParent.getLoaderManager().destroyLoader(4);
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public Loader<BatteryStatsHelper> onCreateLoader(int i, Bundle bundle) {
        return new BatteryStatsHelperLoader(this.mContext);
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoadFinished(Loader<BatteryStatsHelper> loader, BatteryStatsHelper batteryStatsHelper) {
        this.mBatteryHelper = batteryStatsHelper;
        PackageInfo packageInfo = this.mParent.getPackageInfo();
        if (packageInfo != null) {
            this.mSipper = findTargetSipper(batteryStatsHelper, packageInfo.applicationInfo.uid);
            if (this.mParent.getActivity() != null) {
                updateBattery();
            }
        }
    }

    void updateBattery() {
        this.mPreference.setEnabled(true);
        if (isBatteryStatsAvailable()) {
            String percentage = Utils.formatPercentage((int) this.mBatteryUtils.calculateBatteryPercent(this.mSipper.totalPowerMah, this.mBatteryHelper.getTotalPower(), this.mBatteryUtils.removeHiddenBatterySippers(new ArrayList(this.mBatteryHelper.getUsageList())), this.mBatteryHelper.getStats().getDischargeAmount(0)));
            this.mBatteryPercent = percentage;
            this.mPreference.setSummary(this.mContext.getString(R.string.battery_summary, percentage));
            return;
        }
        this.mPreference.setSummary(this.mContext.getString(R.string.no_battery_summary));
    }

    boolean isBatteryStatsAvailable() {
        return (this.mBatteryHelper == null || this.mSipper == null) ? false : true;
    }

    BatterySipper findTargetSipper(BatteryStatsHelper batteryStatsHelper, int i) {
        List usageList = batteryStatsHelper.getUsageList();
        int size = usageList.size();
        for (int i2 = 0; i2 < size; i2++) {
            BatterySipper batterySipper = (BatterySipper) usageList.get(i2);
            if (batterySipper.getUid() == i) {
                return batterySipper;
            }
        }
        return null;
    }
}
