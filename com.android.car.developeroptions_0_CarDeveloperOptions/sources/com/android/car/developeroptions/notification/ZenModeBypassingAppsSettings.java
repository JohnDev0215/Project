package com.android.car.developeroptions.notification;

import android.app.Application;
import android.content.Context;
import android.provider.SearchIndexableResource;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ZenModeBypassingAppsSettings extends ZenModeSettingsBase {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.notification.ZenModeBypassingAppsSettings.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.zen_mode_bypassing_apps;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };

    @Override // com.android.car.developeroptions.notification.ZenModeSettingsBase, com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "ZenBypassingApps";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1588;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.zen_mode_bypassing_apps;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        FragmentActivity activity = getActivity();
        return buildPreferenceControllers(context, activity != null ? activity.getApplication() : null, this);
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Application application, Fragment fragment) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ZenModeAllBypassingAppsPreferenceController(context, application, fragment));
        return arrayList;
    }
}
