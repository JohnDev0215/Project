package com.android.car.developeroptions.fuelgauge;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PowerUsageAdvanced extends PowerUsageBase {
    static final int MENU_TOGGLE_APPS = 2;
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.fuelgauge.PowerUsageAdvanced.1
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.power_usage_advanced;
            return Arrays.asList(searchIndexableResource);
        }
    };
    private BatteryAppListPreferenceController mBatteryAppListPreferenceController;
    BatteryHistoryPreference mHistPref;
    private PowerUsageFeatureProvider mPowerUsageFeatureProvider;
    boolean mShowAllApps = false;

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "AdvancedBatteryUsage";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 51;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.power_usage_advanced;
    }

    @Override // com.android.car.developeroptions.fuelgauge.PowerUsageBase, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context context = getContext();
        this.mHistPref = (BatteryHistoryPreference) findPreference("battery_graph");
        this.mPowerUsageFeatureProvider = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context);
        BatteryUtils.getInstance(context);
        updateHistPrefSummary(context);
        restoreSavedInstance(bundle);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (getActivity().isChangingConfigurations()) {
            BatteryEntry.clearUidCache();
        }
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.add(0, 2, 0, this.mShowAllApps ? R.string.hide_extra_apps : R.string.show_all_apps);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 2) {
            boolean z = !this.mShowAllApps;
            this.mShowAllApps = z;
            menuItem.setTitle(z ? R.string.hide_extra_apps : R.string.show_all_apps);
            this.mMetricsFeatureProvider.action(getContext(), 852, this.mShowAllApps);
            lambda$onCreate$0$PowerUsageBase(0);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    void restoreSavedInstance(Bundle bundle) {
        if (bundle != null) {
            this.mShowAllApps = bundle.getBoolean("show_all_apps", false);
        }
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("show_all_apps", this.mShowAllApps);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        ArrayList arrayList = new ArrayList();
        BatteryAppListPreferenceController batteryAppListPreferenceController = new BatteryAppListPreferenceController(context, "app_list", getSettingsLifecycle(), (SettingsActivity) getActivity(), this);
        this.mBatteryAppListPreferenceController = batteryAppListPreferenceController;
        arrayList.add(batteryAppListPreferenceController);
        return arrayList;
    }

    @Override // com.android.car.developeroptions.fuelgauge.PowerUsageBase
    protected void refreshUi(int i) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        updatePreference(this.mHistPref);
        updateHistPrefSummary(context);
        this.mBatteryAppListPreferenceController.refreshAppListGroup(this.mStatsHelper, this.mShowAllApps);
    }

    private void updateHistPrefSummary(Context context) {
        boolean z = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("plugged", -1) != 0;
        if (this.mPowerUsageFeatureProvider.isEnhancedBatteryPredictionEnabled(context) && !z) {
            this.mHistPref.setBottomSummary(this.mPowerUsageFeatureProvider.getAdvancedUsageScreenInfoString());
        } else {
            this.mHistPref.hideBottomSummary();
        }
    }
}
