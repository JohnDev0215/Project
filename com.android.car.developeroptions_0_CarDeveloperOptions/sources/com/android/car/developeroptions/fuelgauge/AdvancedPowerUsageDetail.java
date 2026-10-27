package com.android.car.developeroptions.fuelgauge;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.BatteryStats;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.applications.appinfo.AppButtonsPreferenceController;
import com.android.car.developeroptions.applications.appinfo.ButtonActionDialogFragment;
import com.android.car.developeroptions.core.InstrumentedPreferenceFragment;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipPreferenceController;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip;
import com.android.car.developeroptions.widget.EntityHeaderController;
import com.android.internal.os.BatterySipper;
import com.android.internal.os.BatteryStatsHelper;
import com.android.internal.util.ArrayUtils;
import com.android.settingslib.Utils;
import com.android.settingslib.applications.AppUtils;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.utils.StringUtil;
import com.android.settingslib.widget.LayoutPreference;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AdvancedPowerUsageDetail extends DashboardFragment implements ButtonActionDialogFragment.AppButtonsDialogListener, BatteryTipPreferenceController.BatteryTipListener {
    private AppButtonsPreferenceController mAppButtonsPreferenceController;
    ApplicationsState.AppEntry mAppEntry;
    private BackgroundActivityPreferenceController mBackgroundActivityPreferenceController;
    Preference mBackgroundPreference;
    BatteryUtils mBatteryUtils;
    Preference mForegroundPreference;
    LayoutPreference mHeaderPreference;
    private String mPackageName;
    ApplicationsState mState;

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "AdvancedPowerDetail";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 53;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.power_usage_detail;
    }

    static void startBatteryDetailPage(Activity activity, BatteryUtils batteryUtils, InstrumentedPreferenceFragment instrumentedPreferenceFragment, BatteryStatsHelper batteryStatsHelper, int i, BatteryEntry batteryEntry, String str) {
        long processTimeMs;
        batteryStatsHelper.getStats();
        Bundle bundle = new Bundle();
        BatterySipper batterySipper = batteryEntry.sipper;
        BatteryStats.Uid uid = batterySipper.uidObj;
        boolean z = batterySipper.drainType == BatterySipper.DrainType.APP;
        if (z) {
            processTimeMs = batteryUtils.getProcessTimeMs(1, uid, i);
        } else {
            processTimeMs = batterySipper.usageTimeMs;
        }
        long processTimeMs2 = z ? batteryUtils.getProcessTimeMs(2, uid, i) : 0L;
        if (ArrayUtils.isEmpty(batterySipper.mPackages)) {
            bundle.putString("extra_label", batteryEntry.getLabel());
            bundle.putInt("extra_icon_id", batteryEntry.iconId);
            bundle.putString("extra_package_name", null);
        } else {
            String str2 = batteryEntry.defaultPackageName;
            if (str2 == null) {
                str2 = batterySipper.mPackages[0];
            }
            bundle.putString("extra_package_name", str2);
        }
        bundle.putInt("extra_uid", batterySipper.getUid());
        bundle.putLong("extra_background_time", processTimeMs2);
        bundle.putLong("extra_foreground_time", processTimeMs);
        bundle.putString("extra_power_usage_percent", str);
        bundle.putInt("extra_power_usage_amount", (int) batterySipper.totalPowerMah);
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(activity);
        subSettingLauncher.setDestination(AdvancedPowerUsageDetail.class.getName());
        subSettingLauncher.setTitleRes(R.string.battery_details_title);
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setSourceMetricsCategory(instrumentedPreferenceFragment.getMetricsCategory());
        subSettingLauncher.setUserHandle(new UserHandle(getUserIdToLaunchAdvancePowerUsageDetail(batterySipper)));
        subSettingLauncher.launch();
    }

    private static int getUserIdToLaunchAdvancePowerUsageDetail(BatterySipper batterySipper) {
        if (batterySipper.drainType == BatterySipper.DrainType.USER) {
            return ActivityManager.getCurrentUser();
        }
        return UserHandle.getUserId(batterySipper.getUid());
    }

    public static void startBatteryDetailPage(Activity activity, InstrumentedPreferenceFragment instrumentedPreferenceFragment, BatteryStatsHelper batteryStatsHelper, int i, BatteryEntry batteryEntry, String str) {
        startBatteryDetailPage(activity, BatteryUtils.getInstance(activity), instrumentedPreferenceFragment, batteryStatsHelper, i, batteryEntry, str);
    }

    public static void startBatteryDetailPage(Activity activity, InstrumentedPreferenceFragment instrumentedPreferenceFragment, String str) {
        Bundle bundle = new Bundle(3);
        PackageManager packageManager = activity.getPackageManager();
        bundle.putString("extra_package_name", str);
        bundle.putString("extra_power_usage_percent", Utils.formatPercentage(0));
        try {
            bundle.putInt("extra_uid", packageManager.getPackageUid(str, 0));
        } catch (PackageManager.NameNotFoundException e) {
            Log.w("AdvancedPowerDetail", "Cannot find package: " + str, e);
        }
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(activity);
        subSettingLauncher.setDestination(AdvancedPowerUsageDetail.class.getName());
        subSettingLauncher.setTitleRes(R.string.battery_details_title);
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setSourceMetricsCategory(instrumentedPreferenceFragment.getMetricsCategory());
        subSettingLauncher.launch();
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mState = ApplicationsState.getInstance(getActivity().getApplication());
        this.mBatteryUtils = BatteryUtils.getInstance(getContext());
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPackageName = getArguments().getString("extra_package_name");
        this.mForegroundPreference = findPreference("app_usage_foreground");
        this.mBackgroundPreference = findPreference("app_usage_background");
        this.mHeaderPreference = (LayoutPreference) findPreference("header_view");
        String str = this.mPackageName;
        if (str != null) {
            this.mAppEntry = this.mState.getEntry(str, UserHandle.myUserId());
        }
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        initHeader();
        initPreference();
    }

    void initHeader() {
        View viewFindViewById = this.mHeaderPreference.findViewById(R.id.entity_header);
        FragmentActivity activity = getActivity();
        Bundle arguments = getArguments();
        EntityHeaderController entityHeaderControllerNewInstance = EntityHeaderController.newInstance(activity, this, viewFindViewById);
        entityHeaderControllerNewInstance.setRecyclerView(getListView(), getSettingsLifecycle());
        entityHeaderControllerNewInstance.setButtonActions(0, 0);
        ApplicationsState.AppEntry appEntry = this.mAppEntry;
        if (appEntry == null) {
            entityHeaderControllerNewInstance.setLabel(arguments.getString("extra_label"));
            if (arguments.getInt("extra_icon_id", 0) == 0) {
                entityHeaderControllerNewInstance.setIcon(activity.getPackageManager().getDefaultActivityIcon());
            } else {
                entityHeaderControllerNewInstance.setIcon(activity.getDrawable(arguments.getInt("extra_icon_id")));
            }
        } else {
            this.mState.ensureIcon(appEntry);
            entityHeaderControllerNewInstance.setLabel(this.mAppEntry);
            entityHeaderControllerNewInstance.setIcon(this.mAppEntry);
            AppUtils.isInstant(this.mAppEntry.info);
            entityHeaderControllerNewInstance.setIsInstantApp(AppUtils.isInstant(this.mAppEntry.info));
        }
        entityHeaderControllerNewInstance.done((Activity) activity, true);
    }

    void initPreference() {
        Bundle arguments = getArguments();
        Context context = getContext();
        long j = arguments.getLong("extra_foreground_time");
        long j2 = arguments.getLong("extra_background_time");
        this.mForegroundPreference.setSummary(TextUtils.expandTemplate(getText(R.string.battery_used_for), StringUtil.formatElapsedTime(context, j, false)));
        this.mBackgroundPreference.setSummary(TextUtils.expandTemplate(getText(R.string.battery_active_for), StringUtil.formatElapsedTime(context, j2, false)));
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        Bundle arguments = getArguments();
        int i = arguments.getInt("extra_uid", 0);
        String string = arguments.getString("extra_package_name");
        BackgroundActivityPreferenceController backgroundActivityPreferenceController = new BackgroundActivityPreferenceController(context, this, i, string);
        this.mBackgroundActivityPreferenceController = backgroundActivityPreferenceController;
        arrayList.add(backgroundActivityPreferenceController);
        arrayList.add(new BatteryOptimizationPreferenceController((SettingsActivity) getActivity(), this, string));
        AppButtonsPreferenceController appButtonsPreferenceController = new AppButtonsPreferenceController((SettingsActivity) getActivity(), this, getSettingsLifecycle(), string, this.mState, 0, 1);
        this.mAppButtonsPreferenceController = appButtonsPreferenceController;
        arrayList.add(appButtonsPreferenceController);
        return arrayList;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        AppButtonsPreferenceController appButtonsPreferenceController = this.mAppButtonsPreferenceController;
        if (appButtonsPreferenceController != null) {
            appButtonsPreferenceController.handleActivityResult(i, i2, intent);
        }
    }

    @Override // com.android.car.developeroptions.applications.appinfo.ButtonActionDialogFragment.AppButtonsDialogListener
    public void handleDialogClick(int i) {
        AppButtonsPreferenceController appButtonsPreferenceController = this.mAppButtonsPreferenceController;
        if (appButtonsPreferenceController != null) {
            appButtonsPreferenceController.handleDialogClick(i);
        }
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipPreferenceController.BatteryTipListener
    public void onBatteryTipHandled(BatteryTip batteryTip) {
        BackgroundActivityPreferenceController backgroundActivityPreferenceController = this.mBackgroundActivityPreferenceController;
        backgroundActivityPreferenceController.updateSummary(findPreference(backgroundActivityPreferenceController.getPreferenceKey()));
    }
}
