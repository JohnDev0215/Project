package com.android.car.developeroptions.datausage;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class UnrestrictedDataAccess extends DashboardFragment {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.datausage.UnrestrictedDataAccess.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.unrestricted_data_access_settings;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    private ApplicationsState.AppFilter mFilter;
    private boolean mShowSystem;

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_unrestricted_data_access;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "UnrestrictedDataAccess";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 349;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.unrestricted_data_access_settings;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mShowSystem = bundle != null && bundle.getBoolean("show_system");
        ((UnrestrictedDataAccessPreferenceController) use(UnrestrictedDataAccessPreferenceController.class)).setParentFragment(this);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.add(0, 43, 0, this.mShowSystem ? R.string.menu_hide_system : R.string.menu_show_system);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 43) {
            boolean z = !this.mShowSystem;
            this.mShowSystem = z;
            menuItem.setTitle(z ? R.string.menu_hide_system : R.string.menu_show_system);
            this.mFilter = this.mShowSystem ? ApplicationsState.FILTER_ALL_ENABLED : ApplicationsState.FILTER_DOWNLOADED_AND_LAUNCHER;
            ((UnrestrictedDataAccessPreferenceController) use(UnrestrictedDataAccessPreferenceController.class)).setFilter(this.mFilter);
            ((UnrestrictedDataAccessPreferenceController) use(UnrestrictedDataAccessPreferenceController.class)).rebuild();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("show_system", this.mShowSystem);
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mFilter = this.mShowSystem ? ApplicationsState.FILTER_ALL_ENABLED : ApplicationsState.FILTER_DOWNLOADED_AND_LAUNCHER;
        ((UnrestrictedDataAccessPreferenceController) use(UnrestrictedDataAccessPreferenceController.class)).setSession(getSettingsLifecycle());
        ((UnrestrictedDataAccessPreferenceController) use(UnrestrictedDataAccessPreferenceController.class)).setFilter(this.mFilter);
    }
}
