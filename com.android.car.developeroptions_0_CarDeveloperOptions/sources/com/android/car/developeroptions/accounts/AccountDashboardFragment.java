package com.android.car.developeroptions.accounts;

import android.content.Context;
import android.provider.SearchIndexableResource;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.dashboard.DashboardFragment;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.users.AutoSyncDataPreferenceController;
import com.android.car.developeroptions.users.AutoSyncPersonalDataPreferenceController;
import com.android.car.developeroptions.users.AutoSyncWorkDataPreferenceController;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AccountDashboardFragment extends DashboardFragment {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.accounts.AccountDashboardFragment.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.accounts_dashboard_settings;
            return Arrays.asList(searchIndexableResource);
        }
    };

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_user_and_account_dashboard;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "AccountDashboardFrag";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 8;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.accounts_dashboard_settings;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        return buildPreferenceControllers(context, this, getIntent().getStringArrayExtra("authorities"));
    }

    private static List<AbstractPreferenceController> buildPreferenceControllers(Context context, SettingsPreferenceFragment settingsPreferenceFragment, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        AccountPreferenceController accountPreferenceController = new AccountPreferenceController(context, settingsPreferenceFragment, strArr);
        if (settingsPreferenceFragment != null) {
            settingsPreferenceFragment.getSettingsLifecycle().addObserver(accountPreferenceController);
        }
        arrayList.add(accountPreferenceController);
        arrayList.add(new AutoSyncDataPreferenceController(context, settingsPreferenceFragment));
        arrayList.add(new AutoSyncPersonalDataPreferenceController(context, settingsPreferenceFragment));
        arrayList.add(new AutoSyncWorkDataPreferenceController(context, settingsPreferenceFragment));
        return arrayList;
    }
}
