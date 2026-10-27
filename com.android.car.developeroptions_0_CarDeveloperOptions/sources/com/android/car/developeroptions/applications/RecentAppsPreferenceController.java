package com.android.car.developeroptions.applications;

import android.app.Application;
import android.app.usage.UsageStats;
import android.content.Context;
import android.content.IntentFilter;
import android.icu.text.RelativeDateTimeFormatter;
import android.os.UserHandle;
import android.util.IconDrawableFactory;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.applications.RecentAppStatsMixin;
import com.android.car.developeroptions.applications.appinfo.AppInfoDashboardFragment;
import com.android.car.developeroptions.applications.manageapplications.ManageApplications;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.utils.StringUtil;
import com.android.settingslib.widget.AppEntitiesHeaderController;
import com.android.settingslib.widget.AppEntityInfo;
import com.android.settingslib.widget.LayoutPreference;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RecentAppsPreferenceController extends BasePreferenceController implements RecentAppStatsMixin.RecentAppStatsListener {
    static final String KEY_DIVIDER = "recent_apps_divider";
    AppEntitiesHeaderController mAppEntitiesController;
    private final ApplicationsState mApplicationsState;
    Preference mDivider;
    private Fragment mHost;
    private final IconDrawableFactory mIconDrawableFactory;
    private List<UsageStats> mRecentApps;
    LayoutPreference mRecentAppsPreference;
    private final int mUserId;

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

    public RecentAppsPreferenceController(Context context, String str) {
        super(context, str);
        this.mApplicationsState = ApplicationsState.getInstance((Application) this.mContext.getApplicationContext());
        this.mUserId = UserHandle.myUserId();
        this.mIconDrawableFactory = IconDrawableFactory.newInstance(this.mContext);
    }

    public void setFragment(Fragment fragment) {
        this.mHost = fragment;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mDivider = preferenceScreen.findPreference(KEY_DIVIDER);
        LayoutPreference layoutPreference = (LayoutPreference) preferenceScreen.findPreference(getPreferenceKey());
        this.mRecentAppsPreference = layoutPreference;
        AppEntitiesHeaderController appEntitiesHeaderControllerNewInstance = AppEntitiesHeaderController.newInstance(this.mContext, layoutPreference.findViewById(R.id.app_entities_header));
        appEntitiesHeaderControllerNewInstance.setHeaderTitleRes(R.string.recent_app_category_title);
        appEntitiesHeaderControllerNewInstance.setHeaderDetailsClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.applications.-$$Lambda$RecentAppsPreferenceController$-kGoAnAQGCk1hdSbWv_HykFvonQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$displayPreference$0$RecentAppsPreferenceController(view);
            }
        });
        this.mAppEntitiesController = appEntitiesHeaderControllerNewInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$displayPreference$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$displayPreference$0$RecentAppsPreferenceController(View view) {
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(this.mContext);
        subSettingLauncher.setDestination(ManageApplications.class.getName());
        subSettingLauncher.setArguments(null);
        subSettingLauncher.setTitleRes(R.string.application_info_label);
        subSettingLauncher.setSourceMetricsCategory(748);
        subSettingLauncher.launch();
    }

    @Override // com.android.car.developeroptions.applications.RecentAppStatsMixin.RecentAppStatsListener
    public void onReloadDataCompleted(List<UsageStats> list) {
        this.mRecentApps = list;
        refreshUi();
        Context context = this.mContext;
        new InstalledAppCounter(context, -1, context.getPackageManager()) { // from class: com.android.car.developeroptions.applications.RecentAppsPreferenceController.1
            @Override // com.android.car.developeroptions.applications.AppCounter
            protected void onCountComplete(int i) {
                RecentAppsPreferenceController recentAppsPreferenceController = RecentAppsPreferenceController.this;
                recentAppsPreferenceController.mAppEntitiesController.setHeaderDetails(((AbstractPreferenceController) recentAppsPreferenceController).mContext.getString(R.string.see_all_apps_title, Integer.valueOf(i)));
                RecentAppsPreferenceController.this.mAppEntitiesController.apply();
            }
        }.execute(new Void[0]);
    }

    private void refreshUi() {
        if (!this.mRecentApps.isEmpty()) {
            displayRecentApps();
            this.mRecentAppsPreference.setVisible(true);
            this.mDivider.setVisible(true);
        } else {
            this.mDivider.setVisible(false);
            this.mRecentAppsPreference.setVisible(false);
        }
    }

    private void displayRecentApps() {
        Iterator<UsageStats> it = this.mRecentApps.iterator();
        int i = 0;
        while (it.hasNext()) {
            AppEntityInfo appEntityInfoCreateAppEntity = createAppEntity(it.next());
            if (appEntityInfoCreateAppEntity != null) {
                this.mAppEntitiesController.setAppEntity(i, appEntityInfoCreateAppEntity);
                i++;
            }
            if (i == 3) {
                return;
            }
        }
    }

    private AppEntityInfo createAppEntity(UsageStats usageStats) {
        final String packageName = usageStats.getPackageName();
        final ApplicationsState.AppEntry entry = this.mApplicationsState.getEntry(packageName, this.mUserId);
        if (entry == null) {
            return null;
        }
        AppEntityInfo.Builder builder = new AppEntityInfo.Builder();
        builder.setIcon(this.mIconDrawableFactory.getBadgedIcon(entry.info));
        builder.setTitle(entry.label);
        builder.setSummary(StringUtil.formatRelativeTime(this.mContext, System.currentTimeMillis() - usageStats.getLastTimeUsed(), false, RelativeDateTimeFormatter.Style.SHORT));
        builder.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.applications.-$$Lambda$RecentAppsPreferenceController$6XtW1wpp9Y9CGHLqtwhG9gzNxtg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$createAppEntity$1$RecentAppsPreferenceController(packageName, entry, view);
            }
        });
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$createAppEntity$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$createAppEntity$1$RecentAppsPreferenceController(String str, ApplicationsState.AppEntry appEntry, View view) {
        AppInfoBase.startAppInfoFragment(AppInfoDashboardFragment.class, R.string.application_info_label, str, appEntry.info.uid, this.mHost, 1001, 748);
    }
}
