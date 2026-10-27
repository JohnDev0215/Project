package com.android.car.developeroptions.location;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public class LocationSettings extends DashboardFragment {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.location.LocationSettings.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.location_settings;
            return Arrays.asList(searchIndexableResource);
        }
    };

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_location_access;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "LocationSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 63;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.location_settings;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        SwitchBar switchBar = settingsActivity.getSwitchBar();
        switchBar.setSwitchBarText(R.string.location_settings_master_switch_title, R.string.location_settings_master_switch_title);
        new LocationSwitchBarController(settingsActivity, switchBar, getSettingsLifecycle());
        switchBar.show();
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, this, getSettingsLifecycle());
    }

    static void addPreferencesSorted(List<Preference> list, PreferenceGroup preferenceGroup) {
        Collections.sort(list, Comparator.comparing(new Function() { // from class: com.android.car.developeroptions.location.-$$Lambda$LocationSettings$w9rDlqXuVLQF06ez6ZqKxi2zVt8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Preference) obj).getTitle().toString();
            }
        }));
        Iterator<Preference> it = list.iterator();
        while (it.hasNext()) {
            preferenceGroup.addPreference(it.next());
        }
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context, LocationSettings locationSettings, Lifecycle lifecycle) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new AppLocationPermissionPreferenceController(context, lifecycle));
        arrayList.add(new LocationForWorkPreferenceController(context, lifecycle));
        arrayList.add(new RecentLocationAccessPreferenceController(context));
        arrayList.add(new LocationScanningPreferenceController(context));
        arrayList.add(new LocationServicePreferenceController(context, locationSettings, lifecycle));
        arrayList.add(new LocationFooterPreferenceController(context));
        return arrayList;
    }
}
