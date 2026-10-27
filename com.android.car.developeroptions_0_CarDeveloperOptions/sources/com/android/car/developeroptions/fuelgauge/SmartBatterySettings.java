package com.android.car.developeroptions.fuelgauge;

import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.core.InstrumentedPreferenceFragment;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SmartBatterySettings extends DashboardFragment {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.fuelgauge.SmartBatterySettings.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.smart_battery_detail;
            return Arrays.asList(searchIndexableResource);
        }
    };

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_uri_smart_battery_settings;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "SmartBatterySettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1281;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.smart_battery_detail;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, (SettingsActivity) getActivity(), this);
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context, SettingsActivity settingsActivity, InstrumentedPreferenceFragment instrumentedPreferenceFragment) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SmartBatteryPreferenceController(context));
        if (settingsActivity != null && instrumentedPreferenceFragment != null) {
            arrayList.add(new RestrictAppPreferenceController(instrumentedPreferenceFragment));
        } else {
            arrayList.add(new RestrictAppPreferenceController(context));
        }
        return arrayList;
    }
}
