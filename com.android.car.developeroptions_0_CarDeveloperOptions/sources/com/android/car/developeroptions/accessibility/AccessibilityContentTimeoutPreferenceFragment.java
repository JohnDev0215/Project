package com.android.car.developeroptions.accessibility;

import android.content.Context;
import android.provider.SearchIndexableResource;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.accessibility.AccessibilityTimeoutController;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class AccessibilityContentTimeoutPreferenceFragment extends DashboardFragment implements AccessibilityTimeoutController.OnChangeListener {
    private static final List<AbstractPreferenceController> sControllers = new ArrayList();
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.accessibility.AccessibilityContentTimeoutPreferenceFragment.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.accessibility_content_timeout_settings;
            return Arrays.asList(searchIndexableResource);
        }
    };

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "AccessibilityContentTimeoutPreferenceFragment";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 2;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.accessibility_content_timeout_settings;
    }

    @Override // com.android.car.developeroptions.accessibility.AccessibilityTimeoutController.OnChangeListener
    public void onCheckedChanged(Preference preference) {
        Iterator<AbstractPreferenceController> it = sControllers.iterator();
        while (it.hasNext()) {
            it.next().updateState(preference);
        }
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Iterator<AbstractPreferenceController> it = buildPreferenceControllers(getPrefContext(), getSettingsLifecycle()).iterator();
        while (it.hasNext()) {
            ((AccessibilityTimeoutController) it.next()).setOnChangeListener(this);
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        Iterator<AbstractPreferenceController> it = buildPreferenceControllers(getPrefContext(), getSettingsLifecycle()).iterator();
        while (it.hasNext()) {
            ((AccessibilityTimeoutController) it.next()).setOnChangeListener(null);
        }
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, getSettingsLifecycle());
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Lifecycle lifecycle) {
        if (sControllers.size() == 0) {
            for (String str : context.getResources().getStringArray(R.array.accessibility_timeout_content_selector_keys)) {
                sControllers.add(new AccessibilityTimeoutController(context, lifecycle, str, "AccessibilityContentTimeoutPreferenceFragment"));
            }
        }
        return sControllers;
    }
}
