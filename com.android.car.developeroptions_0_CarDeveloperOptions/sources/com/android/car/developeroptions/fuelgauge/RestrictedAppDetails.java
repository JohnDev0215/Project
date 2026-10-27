package com.android.car.developeroptions.fuelgauge;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.IconDrawableFactory;
import android.util.Log;
import android.util.SparseLongArray;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.core.InstrumentedPreferenceFragment;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.fuelgauge.batterytip.AppInfo;
import com.android.car.developeroptions.fuelgauge.batterytip.BatteryDatabaseManager;
import com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipDialogFragment;
import com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipPreferenceController;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.RestrictAppTip;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.UnrestrictAppTip;
import com.android.car.developeroptions.widget.AppCheckBoxPreference;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.utils.StringUtil;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RestrictedAppDetails extends DashboardFragment implements BatteryTipPreferenceController.BatteryTipListener {
    static final String EXTRA_APP_INFO_LIST = "app_info_list";
    List<AppInfo> mAppInfos;
    BatteryDatabaseManager mBatteryDatabaseManager;
    BatteryUtils mBatteryUtils;
    IconDrawableFactory mIconDrawableFactory;
    PackageManager mPackageManager;
    PreferenceGroup mRestrictedAppListGroup;

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return null;
    }

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_uri_restricted_apps;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "RestrictedAppDetails";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1285;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.restricted_apps_detail;
    }

    public static void startRestrictedAppDetails(InstrumentedPreferenceFragment instrumentedPreferenceFragment, List<AppInfo> list) {
        Bundle bundle = new Bundle();
        bundle.putParcelableList(EXTRA_APP_INFO_LIST, list);
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(instrumentedPreferenceFragment.getContext());
        subSettingLauncher.setDestination(RestrictedAppDetails.class.getName());
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setTitleRes(R.string.restricted_app_title);
        subSettingLauncher.setSourceMetricsCategory(instrumentedPreferenceFragment.getMetricsCategory());
        subSettingLauncher.launch();
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context context = getContext();
        this.mRestrictedAppListGroup = (PreferenceGroup) findPreference("restrict_app_list");
        this.mAppInfos = getArguments().getParcelableArrayList(EXTRA_APP_INFO_LIST);
        this.mPackageManager = context.getPackageManager();
        this.mIconDrawableFactory = IconDrawableFactory.newInstance(context);
        this.mBatteryUtils = BatteryUtils.getInstance(context);
        this.mBatteryDatabaseManager = BatteryDatabaseManager.getInstance(context);
        refreshUi();
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        return super.onPreferenceTreeClick(preference);
    }

    void refreshUi() {
        this.mRestrictedAppListGroup.removeAll();
        Context prefContext = getPrefContext();
        SparseLongArray sparseLongArrayQueryActionTime = this.mBatteryDatabaseManager.queryActionTime(0);
        long jCurrentTimeMillis = System.currentTimeMillis();
        int size = this.mAppInfos.size();
        for (int i = 0; i < size; i++) {
            AppCheckBoxPreference appCheckBoxPreference = new AppCheckBoxPreference(prefContext);
            final AppInfo appInfo = this.mAppInfos.get(i);
            try {
                ApplicationInfo applicationInfoAsUser = this.mPackageManager.getApplicationInfoAsUser(appInfo.packageName, 0, UserHandle.getUserId(appInfo.uid));
                appCheckBoxPreference.setChecked(this.mBatteryUtils.isForceAppStandbyEnabled(appInfo.uid, appInfo.packageName));
                appCheckBoxPreference.setTitle(this.mPackageManager.getApplicationLabel(applicationInfoAsUser));
                appCheckBoxPreference.setIcon(Utils.getBadgedIcon(this.mIconDrawableFactory, this.mPackageManager, appInfo.packageName, UserHandle.getUserId(appInfo.uid)));
                appCheckBoxPreference.setKey(getKeyFromAppInfo(appInfo));
                appCheckBoxPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.android.car.developeroptions.fuelgauge.-$$Lambda$RestrictedAppDetails$zTlogNa-fz9ihG6sYNkAUyAcAmI
                    @Override // androidx.preference.Preference.OnPreferenceChangeListener
                    public final boolean onPreferenceChange(Preference preference, Object obj) {
                        return this.f$0.lambda$refreshUi$0$RestrictedAppDetails(appInfo, preference, obj);
                    }
                });
                if (sparseLongArrayQueryActionTime.get(appInfo.uid, -1L) != -1) {
                    appCheckBoxPreference.setSummary(getString(R.string.restricted_app_time_summary, StringUtil.formatRelativeTime(prefContext, jCurrentTimeMillis - r12, false)));
                }
                appCheckBoxPreference.getSummaryOn();
                this.mRestrictedAppListGroup.addPreference(appCheckBoxPreference);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("RestrictedAppDetails", "Can't find package: " + appInfo.packageName);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$refreshUi$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$refreshUi$0$RestrictedAppDetails(AppInfo appInfo, Preference preference, Object obj) {
        BatteryTipDialogFragment batteryTipDialogFragmentCreateDialogFragment = createDialogFragment(appInfo, ((Boolean) obj).booleanValue());
        batteryTipDialogFragmentCreateDialogFragment.setTargetFragment(this, 0);
        batteryTipDialogFragmentCreateDialogFragment.show(getFragmentManager(), "RestrictedAppDetails");
        return false;
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipPreferenceController.BatteryTipListener
    public void onBatteryTipHandled(BatteryTip batteryTip) {
        AppInfo unrestrictAppInfo;
        boolean z = batteryTip instanceof RestrictAppTip;
        if (z) {
            unrestrictAppInfo = ((RestrictAppTip) batteryTip).getRestrictAppList().get(0);
        } else {
            unrestrictAppInfo = ((UnrestrictAppTip) batteryTip).getUnrestrictAppInfo();
        }
        CheckBoxPreference checkBoxPreference = (CheckBoxPreference) this.mRestrictedAppListGroup.findPreference(getKeyFromAppInfo(unrestrictAppInfo));
        if (checkBoxPreference != null) {
            checkBoxPreference.setChecked(z);
        }
    }

    BatteryTipDialogFragment createDialogFragment(AppInfo appInfo, boolean z) {
        BatteryTip unrestrictAppTip;
        if (z) {
            unrestrictAppTip = new RestrictAppTip(0, appInfo);
        } else {
            unrestrictAppTip = new UnrestrictAppTip(0, appInfo);
        }
        return BatteryTipDialogFragment.newInstance(unrestrictAppTip, getMetricsCategory());
    }

    String getKeyFromAppInfo(AppInfo appInfo) {
        return appInfo.uid + "," + appInfo.packageName;
    }
}
