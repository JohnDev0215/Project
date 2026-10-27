package com.android.car.developeroptions.applications;

import android.app.usage.UsageStats;
import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.view.View;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.applications.RecentAppStatsMixin;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.notification.EmergencyBroadcastPreferenceController;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AppAndNotificationDashboardFragment extends DashboardFragment implements RecentAppStatsMixin.RecentAppStatsListener {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.applications.AppAndNotificationDashboardFragment.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.app_and_notification;
            return Arrays.asList(searchIndexableResource);
        }
    };
    private AllAppsInfoPreferenceController mAllAppsInfoPreferenceController;
    private View mProgressAnimation;
    private View mProgressHeader;
    private RecentAppStatsMixin mRecentAppStatsMixin;
    private RecentAppsPreferenceController mRecentAppsPreferenceController;

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_apps_and_notifications;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "AppAndNotifDashboard";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 748;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.app_and_notification;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        ((SpecialAppAccessPreferenceController) use(SpecialAppAccessPreferenceController.class)).setSession(getSettingsLifecycle());
        this.mRecentAppStatsMixin = new RecentAppStatsMixin(context, 3);
        getSettingsLifecycle().addObserver(this.mRecentAppStatsMixin);
        this.mRecentAppStatsMixin.addListener(this);
        RecentAppsPreferenceController recentAppsPreferenceController = (RecentAppsPreferenceController) use(RecentAppsPreferenceController.class);
        this.mRecentAppsPreferenceController = recentAppsPreferenceController;
        recentAppsPreferenceController.setFragment(this);
        this.mRecentAppStatsMixin.addListener(this.mRecentAppsPreferenceController);
        AllAppsInfoPreferenceController allAppsInfoPreferenceController = (AllAppsInfoPreferenceController) use(AllAppsInfoPreferenceController.class);
        this.mAllAppsInfoPreferenceController = allAppsInfoPreferenceController;
        this.mRecentAppStatsMixin.addListener(allAppsInfoPreferenceController);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        View pinnedHeaderView = setPinnedHeaderView(R.layout.progress_header);
        this.mProgressHeader = pinnedHeaderView;
        this.mProgressAnimation = pinnedHeaderView.findViewById(R.id.progress_bar_animation);
        setLoadingEnabled(false);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        setLoadingEnabled(true);
    }

    @Override // com.android.car.developeroptions.applications.RecentAppStatsMixin.RecentAppStatsListener
    public void onReloadDataCompleted(List<UsageStats> list) {
        setLoadingEnabled(false);
        if (list.isEmpty()) {
            return;
        }
        Utils.setActionBarShadowAnimation(getActivity(), getSettingsLifecycle(), getListView());
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context);
    }

    private void setLoadingEnabled(boolean z) {
        View view = this.mProgressHeader;
        if (view == null || this.mProgressAnimation == null) {
            return;
        }
        view.setVisibility(z ? 0 : 4);
        this.mProgressAnimation.setVisibility(z ? 0 : 4);
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new EmergencyBroadcastPreferenceController(context, "app_and_notif_cell_broadcast_settings"));
        return arrayList;
    }
}
