package com.android.car.developeroptions.datausage;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.widget.Switch;
import androidx.preference.Preference;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.SettingsPreferenceFragment;
import com.android.car.developeroptions.applications.AppStateBaseBridge;
import com.android.car.developeroptions.datausage.AppStateDataUsageBridge;
import com.android.car.developeroptions.datausage.DataSaverBackend;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.car.developeroptions.widget.SwitchBar;
import com.android.settingslib.applications.ApplicationsState;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DataSaverSummary extends SettingsPreferenceFragment implements SwitchBar.OnSwitchChangeListener, DataSaverBackend.Listener, AppStateBaseBridge.Callback, ApplicationsState.Callbacks {
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.datausage.DataSaverSummary.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            ArrayList arrayList = new ArrayList();
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.data_saver;
            arrayList.add(searchIndexableResource);
            return arrayList;
        }
    };
    private ApplicationsState mApplicationsState;
    private DataSaverBackend mDataSaverBackend;
    private AppStateDataUsageBridge mDataUsageBridge;
    private ApplicationsState.Session mSession;
    private SwitchBar mSwitchBar;
    private boolean mSwitching;
    private Preference mUnrestrictedAccess;

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_data_saver;
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 348;
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onAllSizesComputed() {
    }

    @Override // com.android.car.developeroptions.datausage.DataSaverBackend.Listener
    public void onBlacklistStatusChanged(int i, boolean z) {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onLauncherInfoChanged() {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onLoadEntriesCompleted() {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onPackageIconChanged() {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onPackageListChanged() {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onPackageSizeChanged(String str) {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    /* JADX INFO: renamed from: onRebuildComplete */
    public void lambda$rebuild$2$ManageApplications$ApplicationsAdapter(ArrayList<ApplicationsState.AppEntry> arrayList) {
    }

    @Override // com.android.settingslib.applications.ApplicationsState.Callbacks
    public void onRunningStateChanged(boolean z) {
    }

    @Override // com.android.car.developeroptions.datausage.DataSaverBackend.Listener
    public void onWhitelistStatusChanged(int i, boolean z) {
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.data_saver);
        this.mUnrestrictedAccess = findPreference("unrestricted_access");
        this.mApplicationsState = ApplicationsState.getInstance((Application) getContext().getApplicationContext());
        DataSaverBackend dataSaverBackend = new DataSaverBackend(getContext());
        this.mDataSaverBackend = dataSaverBackend;
        this.mDataUsageBridge = new AppStateDataUsageBridge(this.mApplicationsState, this, dataSaverBackend);
        this.mSession = this.mApplicationsState.newSession(this, getSettingsLifecycle());
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        SwitchBar switchBar = ((SettingsActivity) getActivity()).getSwitchBar();
        this.mSwitchBar = switchBar;
        switchBar.setSwitchBarText(R.string.data_saver_switch_title, R.string.data_saver_switch_title);
        this.mSwitchBar.show();
        this.mSwitchBar.addOnSwitchChangeListener(this);
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mDataSaverBackend.refreshWhitelist();
        this.mDataSaverBackend.refreshBlacklist();
        this.mDataSaverBackend.addListener(this);
        this.mDataUsageBridge.resume();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mDataSaverBackend.remListener(this);
        this.mDataUsageBridge.pause();
    }

    @Override // com.android.car.developeroptions.widget.SwitchBar.OnSwitchChangeListener
    public void onSwitchChanged(Switch r1, boolean z) {
        synchronized (this) {
            if (this.mSwitching) {
                return;
            }
            this.mSwitching = true;
            this.mDataSaverBackend.setDataSaverEnabled(z);
        }
    }

    @Override // com.android.car.developeroptions.datausage.DataSaverBackend.Listener
    public void onDataSaverChanged(boolean z) {
        synchronized (this) {
            this.mSwitchBar.setChecked(z);
            this.mSwitching = false;
        }
    }

    @Override // com.android.car.developeroptions.applications.AppStateBaseBridge.Callback
    public void onExtraInfoUpdated() {
        Object obj;
        if (isAdded()) {
            ArrayList<ApplicationsState.AppEntry> allApps = this.mSession.getAllApps();
            int size = allApps.size();
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                ApplicationsState.AppEntry appEntry = allApps.get(i2);
                if (ApplicationsState.FILTER_DOWNLOADED_AND_LAUNCHER.filterApp(appEntry) && (obj = appEntry.extraInfo) != null && ((AppStateDataUsageBridge.DataUsageState) obj).isDataSaverWhitelisted) {
                    i++;
                }
            }
            this.mUnrestrictedAccess.setSummary(getResources().getQuantityString(R.plurals.data_saver_unrestricted_summary, i, Integer.valueOf(i)));
        }
    }
}
