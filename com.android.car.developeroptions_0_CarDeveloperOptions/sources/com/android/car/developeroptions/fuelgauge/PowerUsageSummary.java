package com.android.car.developeroptions.fuelgauge;

import android.content.Context;
import android.os.Bundle;
import android.provider.SearchIndexableResource;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SettingsActivity;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipLoader;
import com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipPreferenceController;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.car.developeroptions.search.BaseSearchIndexProvider;
import com.android.settingslib.Utils;
import com.android.settingslib.search.Indexable$SearchIndexProvider;
import com.android.settingslib.utils.PowerUtil;
import com.android.settingslib.utils.StringUtil;
import com.android.settingslib.widget.LayoutPreference;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PowerUsageSummary extends PowerUsageBase implements View.OnLongClickListener, BatteryTipPreferenceController.BatteryTipListener {
    static final int BATTERY_INFO_LOADER = 1;
    static final int BATTERY_TIP_LOADER = 2;
    static final int MENU_ADVANCED_BATTERY = 2;
    static final int MENU_STATS_TYPE = 1;
    public static final Indexable$SearchIndexProvider SEARCH_INDEX_DATA_PROVIDER = new BaseSearchIndexProvider() { // from class: com.android.car.developeroptions.fuelgauge.PowerUsageSummary.4
        @Override // com.android.car.developeroptions.search.BaseSearchIndexProvider, com.android.settingslib.search.Indexable$SearchIndexProvider
        public List<SearchIndexableResource> getXmlResourcesToIndex(Context context, boolean z) {
            SearchIndexableResource searchIndexableResource = new SearchIndexableResource(context);
            searchIndexableResource.xmlResId = R.xml.power_usage_summary;
            return Collections.singletonList(searchIndexableResource);
        }
    };
    BatteryHeaderPreferenceController mBatteryHeaderPreferenceController;
    BatteryInfo mBatteryInfo;
    LayoutPreference mBatteryLayoutPref;
    BatteryTipPreferenceController mBatteryTipPreferenceController;
    BatteryUtils mBatteryUtils;
    PowerGaugePreference mLastFullChargePref;
    boolean mNeedUpdateBatteryTip;
    PowerUsageFeatureProvider mPowerFeatureProvider;
    PowerGaugePreference mScreenUsagePref;
    private int mStatsType = 0;
    LoaderManager.LoaderCallbacks<BatteryInfo> mBatteryInfoLoaderCallbacks = new LoaderManager.LoaderCallbacks<BatteryInfo>() { // from class: com.android.car.developeroptions.fuelgauge.PowerUsageSummary.1
        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<BatteryInfo> loader) {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<BatteryInfo> onCreateLoader(int i, Bundle bundle) {
            return new BatteryInfoLoader(PowerUsageSummary.this.getContext(), PowerUsageSummary.this.mStatsHelper);
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<BatteryInfo> loader, BatteryInfo batteryInfo) {
            PowerUsageSummary.this.mBatteryHeaderPreferenceController.updateHeaderPreference(batteryInfo);
            PowerUsageSummary powerUsageSummary = PowerUsageSummary.this;
            powerUsageSummary.mBatteryInfo = batteryInfo;
            powerUsageSummary.updateLastFullChargePreference();
        }
    };
    LoaderManager.LoaderCallbacks<List<BatteryInfo>> mBatteryInfoDebugLoaderCallbacks = new LoaderManager.LoaderCallbacks<List<BatteryInfo>>() { // from class: com.android.car.developeroptions.fuelgauge.PowerUsageSummary.2
        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<List<BatteryInfo>> loader) {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<List<BatteryInfo>> onCreateLoader(int i, Bundle bundle) {
            return new DebugEstimatesLoader(PowerUsageSummary.this.getContext(), PowerUsageSummary.this.mStatsHelper);
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<List<BatteryInfo>> loader, List<BatteryInfo> list) {
            PowerUsageSummary.this.updateViews(list);
        }
    };
    private LoaderManager.LoaderCallbacks<List<BatteryTip>> mBatteryTipsCallbacks = new LoaderManager.LoaderCallbacks<List<BatteryTip>>() { // from class: com.android.car.developeroptions.fuelgauge.PowerUsageSummary.3
        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<List<BatteryTip>> loader) {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<List<BatteryTip>> onCreateLoader(int i, Bundle bundle) {
            return new BatteryTipLoader(PowerUsageSummary.this.getContext(), PowerUsageSummary.this.mStatsHelper);
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<List<BatteryTip>> loader, List<BatteryTip> list) {
            PowerUsageSummary.this.mBatteryTipPreferenceController.updateBatteryTips(list);
        }
    };

    @Override // com.android.car.developeroptions.support.actionbar.HelpResourceProvider
    public int getHelpResource() {
        return R.string.help_url_battery;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "PowerUsageSummary";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1263;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.power_usage_summary;
    }

    protected void updateViews(List<BatteryInfo> list) {
        BatteryMeterView batteryMeterView = (BatteryMeterView) this.mBatteryLayoutPref.findViewById(R.id.battery_header_icon);
        TextView textView = (TextView) this.mBatteryLayoutPref.findViewById(R.id.battery_percent);
        TextView textView2 = (TextView) this.mBatteryLayoutPref.findViewById(R.id.summary1);
        TextView textView3 = (TextView) this.mBatteryLayoutPref.findViewById(R.id.summary2);
        BatteryInfo batteryInfo = list.get(0);
        BatteryInfo batteryInfo2 = list.get(1);
        textView.setText(Utils.formatPercentage(batteryInfo.batteryLevel));
        textView2.setText(this.mPowerFeatureProvider.getOldEstimateDebugString(Formatter.formatShortElapsedTime(getContext(), PowerUtil.convertUsToMs(batteryInfo.remainingTimeUs))));
        textView3.setText(this.mPowerFeatureProvider.getEnhancedEstimateDebugString(Formatter.formatShortElapsedTime(getContext(), PowerUtil.convertUsToMs(batteryInfo2.remainingTimeUs))));
        batteryMeterView.setBatteryLevel(batteryInfo.batteryLevel);
        batteryMeterView.setCharging(!batteryInfo.discharging);
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        SettingsActivity settingsActivity = (SettingsActivity) getActivity();
        BatteryHeaderPreferenceController batteryHeaderPreferenceController = (BatteryHeaderPreferenceController) use(BatteryHeaderPreferenceController.class);
        this.mBatteryHeaderPreferenceController = batteryHeaderPreferenceController;
        batteryHeaderPreferenceController.setActivity(settingsActivity);
        this.mBatteryHeaderPreferenceController.setFragment(this);
        this.mBatteryHeaderPreferenceController.setLifecycle(getSettingsLifecycle());
        BatteryTipPreferenceController batteryTipPreferenceController = (BatteryTipPreferenceController) use(BatteryTipPreferenceController.class);
        this.mBatteryTipPreferenceController = batteryTipPreferenceController;
        batteryTipPreferenceController.setActivity(settingsActivity);
        this.mBatteryTipPreferenceController.setFragment(this);
        this.mBatteryTipPreferenceController.setBatteryTipListener(new BatteryTipPreferenceController.BatteryTipListener() { // from class: com.android.car.developeroptions.fuelgauge.-$$Lambda$slOJHP_G23gXdqF8yz0zLNIEAXo
            @Override // com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipPreferenceController.BatteryTipListener
            public final void onBatteryTipHandled(BatteryTip batteryTip) {
                this.f$0.onBatteryTipHandled(batteryTip);
            }
        });
    }

    @Override // com.android.car.developeroptions.fuelgauge.PowerUsageBase, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setAnimationAllowed(true);
        initFeatureProvider();
        this.mBatteryLayoutPref = (LayoutPreference) findPreference("battery_header");
        this.mScreenUsagePref = (PowerGaugePreference) findPreference("screen_usage");
        this.mLastFullChargePref = (PowerGaugePreference) findPreference("last_full_charge");
        this.mBatteryUtils = BatteryUtils.getInstance(getContext());
        restartBatteryInfoLoader();
        this.mBatteryTipPreferenceController.restoreInstanceState(bundle);
        updateBatteryTipFlag(bundle);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menu.add(0, 2, 0, R.string.advanced_battery_title);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 1) {
            if (this.mStatsType == 0) {
                this.mStatsType = 2;
            } else {
                this.mStatsType = 0;
            }
            refreshUi(0);
            return true;
        }
        if (itemId == 2) {
            SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getContext());
            subSettingLauncher.setDestination(PowerUsageAdvanced.class.getName());
            subSettingLauncher.setSourceMetricsCategory(getMetricsCategory());
            subSettingLauncher.setTitleRes(R.string.advanced_battery_title);
            subSettingLauncher.launch();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // com.android.car.developeroptions.fuelgauge.PowerUsageBase
    protected void refreshUi(int i) {
        if (getContext() == null) {
            return;
        }
        if (this.mNeedUpdateBatteryTip && i != 1) {
            restartBatteryTipLoader();
        } else {
            this.mNeedUpdateBatteryTip = true;
        }
        restartBatteryInfoLoader();
        updateLastFullChargePreference();
        this.mScreenUsagePref.setSubtitle(StringUtil.formatElapsedTime(getContext(), this.mBatteryUtils.calculateScreenUsageTime(this.mStatsHelper), false));
    }

    void restartBatteryTipLoader() {
        getLoaderManager().restartLoader(2, Bundle.EMPTY, this.mBatteryTipsCallbacks);
    }

    void setBatteryLayoutPreference(LayoutPreference layoutPreference) {
        this.mBatteryLayoutPref = layoutPreference;
    }

    void updateLastFullChargePreference() {
        BatteryInfo batteryInfo = this.mBatteryInfo;
        if (batteryInfo != null && batteryInfo.averageTimeToDischarge != -1) {
            this.mLastFullChargePref.setTitle(R.string.battery_full_charge_last);
            this.mLastFullChargePref.setSubtitle(StringUtil.formatElapsedTime(getContext(), this.mBatteryInfo.averageTimeToDischarge, false));
        } else {
            long jCalculateLastFullChargeTime = this.mBatteryUtils.calculateLastFullChargeTime(this.mStatsHelper, System.currentTimeMillis());
            this.mLastFullChargePref.setTitle(R.string.battery_last_full_charge);
            this.mLastFullChargePref.setSubtitle(StringUtil.formatRelativeTime(getContext(), jCalculateLastFullChargeTime, false));
        }
    }

    void showBothEstimates() {
        Context context = getContext();
        if (context == null || !this.mPowerFeatureProvider.isEnhancedBatteryPredictionEnabled(context)) {
            return;
        }
        getLoaderManager().restartLoader(3, Bundle.EMPTY, this.mBatteryInfoDebugLoaderCallbacks);
    }

    void initFeatureProvider() {
        Context context = getContext();
        this.mPowerFeatureProvider = FeatureFactory.getFactory(context).getPowerUsageFeatureProvider(context);
    }

    void restartBatteryInfoLoader() {
        getLoaderManager().restartLoader(1, Bundle.EMPTY, this.mBatteryInfoLoaderCallbacks);
        if (this.mPowerFeatureProvider.isEstimateDebugEnabled()) {
            this.mBatteryLayoutPref.findViewById(R.id.summary1).setOnLongClickListener(this);
        }
    }

    void updateBatteryTipFlag(Bundle bundle) {
        this.mNeedUpdateBatteryTip = bundle == null || this.mBatteryTipPreferenceController.needUpdate();
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        showBothEstimates();
        view.setOnLongClickListener(null);
        return true;
    }

    @Override // com.android.car.developeroptions.fuelgauge.PowerUsageBase
    /* JADX INFO: renamed from: restartBatteryStatsLoader */
    protected void lambda$onCreate$0$PowerUsageBase(int i) {
        super.lambda$onCreate$0$PowerUsageBase(i);
        this.mBatteryHeaderPreferenceController.quickUpdateHeaderPreference();
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mBatteryTipPreferenceController.saveInstanceState(bundle);
    }

    @Override // com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipPreferenceController.BatteryTipListener
    public void onBatteryTipHandled(BatteryTip batteryTip) {
        restartBatteryTipLoader();
    }
}
