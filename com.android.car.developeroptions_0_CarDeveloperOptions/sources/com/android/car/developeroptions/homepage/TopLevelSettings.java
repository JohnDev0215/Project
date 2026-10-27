package com.android.car.developeroptions.homepage;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.support.SupportPreferenceController;
import com.android.settingslib.core.instrumentation.Instrumentable;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class TopLevelSettings extends DashboardFragment implements PreferenceFragmentCompat.OnPreferenceStartFragmentCallback {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.homepage.TopLevelSettings.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.top_level_settings;
            return Arrays.asList(searchIndexableResource);
        }
    };

    @Override // androidx.preference.PreferenceFragmentCompat
    public Fragment getCallbackFragment() {
        return this;
    }

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return 0;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "TopLevelSettings";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 35;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.top_level_settings;
    }

    public TopLevelSettings() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("need_search_icon_in_action_bar", false);
        setArguments(bundle);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        ((SupportPreferenceController) use(SupportPreferenceController.class)).setActivity(getActivity());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.preference.PreferenceFragmentCompat.OnPreferenceStartFragmentCallback
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat preferenceFragmentCompat, Preference preference) {
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getActivity());
        subSettingLauncher.setDestination(preference.getFragment());
        subSettingLauncher.setArguments(preference.getExtras());
        subSettingLauncher.setSourceMetricsCategory(preferenceFragmentCompat instanceof Instrumentable ? ((Instrumentable) preferenceFragmentCompat).getMetricsCategory() : 0);
        subSettingLauncher.setTitleRes(-1);
        subSettingLauncher.launch();
        return true;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected boolean shouldForceRoundedIcon() {
        return getContext().getResources().getBoolean(R.bool.config_force_rounded_icon_TopLevelSettings);
    }
}
