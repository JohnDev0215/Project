package com.android.car.developeroptions.privacy;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.view.View;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.Utils;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.notification.LockScreenNotificationPreferenceController;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PrivacyDashboardFragment extends DashboardFragment {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.privacy.PrivacyDashboardFragment.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.privacy_dashboard_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    View mProgressAnimation;
    View mProgressHeader;

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_privacy_dashboard;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "PrivacyDashboardFrag";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1587;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.privacy_dashboard_settings;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, getSettingsLifecycle());
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        ((PermissionBarChartPreferenceController) use(PermissionBarChartPreferenceController.class)).setFragment(this);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Utils.setActionBarShadowAnimation(getActivity(), getSettingsLifecycle(), getListView());
        initLoadingBar();
    }

    void initLoadingBar() {
        View pinnedHeaderView = setPinnedHeaderView(R.layout.progress_header);
        this.mProgressHeader = pinnedHeaderView;
        this.mProgressAnimation = pinnedHeaderView.findViewById(R.id.progress_bar_animation);
        setLoadingEnabled(false);
    }

    void setLoadingEnabled(boolean z) {
        View view = this.mProgressHeader;
        if (view == null || this.mProgressAnimation == null) {
            return;
        }
        view.setVisibility(z ? 0 : 4);
        this.mProgressAnimation.setVisibility(z ? 0 : 4);
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Lifecycle lifecycle) {
        ArrayList arrayList = new ArrayList();
        LockScreenNotificationPreferenceController lockScreenNotificationPreferenceController = new LockScreenNotificationPreferenceController(context, "privacy_lock_screen_notifications", "privacy_work_profile_notifications_category", "privacy_lock_screen_work_profile_notifications");
        if (lifecycle != null) {
            lifecycle.addObserver(lockScreenNotificationPreferenceController);
        }
        arrayList.add(lockScreenNotificationPreferenceController);
        return arrayList;
    }
}
