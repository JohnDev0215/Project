package com.android.car.developeroptions;

import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.display.BrightnessLevelPreferenceController;
import com.android.car.developeroptions.display.CameraGesturePreferenceController;
import com.android.car.developeroptions.display.LiftToWakePreferenceController;
import com.android.car.developeroptions.display.NightDisplayPreferenceController;
import com.android.car.developeroptions.display.NightModePreferenceController;
import com.android.car.developeroptions.display.ScreenSaverPreferenceController;
import com.android.car.developeroptions.display.ShowOperatorNamePreferenceController;
import com.android.car.developeroptions.display.TapToWakePreferenceController;
import com.android.car.developeroptions.display.ThemePreferenceController;
import com.android.car.developeroptions.display.TimeoutPreferenceController;
import com.android.car.developeroptions.display.VrDisplayPreferenceController;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DisplaySettings extends DashboardFragment {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.DisplaySettings.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.display_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_uri_display;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "DisplaySettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 46;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.display_settings;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, getSettingsLifecycle());
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context, Lifecycle lifecycle) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new CameraGesturePreferenceController(context));
        arrayList.add(new LiftToWakePreferenceController(context));
        arrayList.add(new NightDisplayPreferenceController(context));
        arrayList.add(new NightModePreferenceController(context));
        arrayList.add(new ScreenSaverPreferenceController(context));
        arrayList.add(new TapToWakePreferenceController(context));
        arrayList.add(new TimeoutPreferenceController(context, "screen_timeout"));
        arrayList.add(new VrDisplayPreferenceController(context));
        arrayList.add(new ShowOperatorNamePreferenceController(context));
        arrayList.add(new ThemePreferenceController(context));
        arrayList.add(new BrightnessLevelPreferenceController(context, lifecycle));
        return arrayList;
    }
}
