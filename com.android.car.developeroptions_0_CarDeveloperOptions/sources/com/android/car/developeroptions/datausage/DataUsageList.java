package com.android.car.developeroptions.datausage;

import android.app.ActivityManager;
import android.app.usage.NetworkStats;
import android.content.Intent;
import android.graphics.Color;
import android.net.NetworkPolicy;
import android.net.NetworkTemplate;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.telephony.SubscriptionInfo;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.SubSettingLauncher;
import com.android.car.developeroptions.datausage.CellDataPreference;
import com.android.car.developeroptions.datausage.CycleAdapter;
import com.android.car.developeroptions.widget.LoadingViewController;
import com.android.settingslib.AppItem;
import com.android.settingslib.net.NetworkCycleChartData;
import com.android.settingslib.net.NetworkCycleChartDataLoader;
import com.android.settingslib.net.NetworkStatsSummaryLoader;
import com.android.settingslib.net.UidDetailProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DataUsageList extends DataUsageBaseFragment {
    private PreferenceGroup mApps;
    private ChartDataUsagePreference mChart;
    private CycleAdapter mCycleAdapter;
    private List<NetworkCycleChartData> mCycleData;
    private Spinner mCycleSpinner;
    private ArrayList<Long> mCycles;
    private View mHeader;
    private LoadingViewController mLoadingViewController;
    int mNetworkType;
    NetworkTemplate mTemplate;
    private UidDetailProvider mUidDetailProvider;
    private Preference mUsageAmount;
    private final CellDataPreference.DataStateListener mDataStateListener = new CellDataPreference.DataStateListener() { // from class: com.android.car.developeroptions.datausage.DataUsageList.1
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            DataUsageList.this.updatePolicy();
        }
    };
    int mSubId = -1;
    private AdapterView.OnItemSelectedListener mCycleListener = new AdapterView.OnItemSelectedListener() { // from class: com.android.car.developeroptions.datausage.DataUsageList.4
        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            DataUsageList.this.mChart.setNetworkCycleData((NetworkCycleChartData) DataUsageList.this.mCycleData.get(i));
            DataUsageList.this.updateDetailData();
        }
    };
    private final LoaderManager.LoaderCallbacks<List<NetworkCycleChartData>> mNetworkCycleDataCallbacks = new LoaderManager.LoaderCallbacks<List<NetworkCycleChartData>>() { // from class: com.android.car.developeroptions.datausage.DataUsageList.5
        /* JADX WARN: Type inference failed for: r0v3, types: [androidx.loader.content.Loader<java.util.List<com.android.settingslib.net.NetworkCycleChartData>>, com.android.settingslib.net.NetworkCycleDataLoader] */
        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<List<NetworkCycleChartData>> onCreateLoader(int i, Bundle bundle) {
            NetworkCycleChartDataLoader.Builder<?> builder = NetworkCycleChartDataLoader.builder(DataUsageList.this.getContext());
            builder.setNetworkTemplate(DataUsageList.this.mTemplate);
            return builder.build();
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<List<NetworkCycleChartData>> loader, List<NetworkCycleChartData> list) {
            DataUsageList.this.mLoadingViewController.showContent(false);
            DataUsageList.this.mCycleData = list;
            DataUsageList.this.updatePolicy();
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<List<NetworkCycleChartData>> loader) {
            DataUsageList.this.mCycleData = null;
        }
    };
    private final LoaderManager.LoaderCallbacks<NetworkStats> mNetworkStatsDetailCallbacks = new LoaderManager.LoaderCallbacks<NetworkStats>() { // from class: com.android.car.developeroptions.datausage.DataUsageList.6
        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<NetworkStats> onCreateLoader(int i, Bundle bundle) {
            NetworkStatsSummaryLoader.Builder builder = new NetworkStatsSummaryLoader.Builder(DataUsageList.this.getContext());
            builder.setStartTime(DataUsageList.this.mChart.getInspectStart());
            builder.setEndTime(DataUsageList.this.mChart.getInspectEnd());
            builder.setNetworkTemplate(DataUsageList.this.mTemplate);
            return builder.build();
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<NetworkStats> loader, NetworkStats networkStats) {
            DataUsageList.this.bindStats(networkStats, DataUsageList.this.services.mPolicyManager.getUidsWithPolicy(1));
            updateEmptyVisible();
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<NetworkStats> loader) {
            DataUsageList.this.bindStats(null, new int[0]);
            updateEmptyVisible();
        }

        private void updateEmptyVisible() {
            if ((DataUsageList.this.mApps.getPreferenceCount() != 0) != (DataUsageList.this.getPreferenceScreen().getPreferenceCount() != 0)) {
                if (DataUsageList.this.mApps.getPreferenceCount() != 0) {
                    DataUsageList.this.getPreferenceScreen().addPreference(DataUsageList.this.mUsageAmount);
                    DataUsageList.this.getPreferenceScreen().addPreference(DataUsageList.this.mApps);
                } else {
                    DataUsageList.this.getPreferenceScreen().removeAll();
                }
            }
        }
    };

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment
    protected String getLogTag() {
        return "DataUsageList";
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 341;
    }

    @Override // com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment
    protected int getPreferenceScreenResId() {
        return R.xml.data_usage_list;
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageBaseFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentActivity activity = getActivity();
        if (!isBandwidthControlEnabled()) {
            Log.w("DataUsageList", "No bandwidth control; leaving");
            activity.finish();
        }
        this.mUidDetailProvider = new UidDetailProvider(activity);
        this.mUsageAmount = findPreference("usage_amount");
        this.mChart = (ChartDataUsagePreference) findPreference("chart_data");
        this.mApps = (PreferenceGroup) findPreference("apps_group");
        processArgument();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        View pinnedHeaderView = setPinnedHeaderView(R.layout.apps_filter_spinner);
        this.mHeader = pinnedHeaderView;
        pinnedHeaderView.findViewById(R.id.filter_settings).setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.datausage.-$$Lambda$DataUsageList$lwnAd_G2w1u7gJFFxsbXuro5p5Y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$0$DataUsageList(view2);
            }
        });
        this.mCycleSpinner = (Spinner) this.mHeader.findViewById(R.id.filter_spinner);
        this.mCycleAdapter = new CycleAdapter(this.mCycleSpinner.getContext(), new CycleAdapter.SpinnerInterface() { // from class: com.android.car.developeroptions.datausage.DataUsageList.2
            @Override // com.android.car.developeroptions.datausage.CycleAdapter.SpinnerInterface
            public void setAdapter(CycleAdapter cycleAdapter) {
                DataUsageList.this.mCycleSpinner.setAdapter((SpinnerAdapter) cycleAdapter);
            }

            @Override // com.android.car.developeroptions.datausage.CycleAdapter.SpinnerInterface
            public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
                DataUsageList.this.mCycleSpinner.setOnItemSelectedListener(onItemSelectedListener);
            }

            @Override // com.android.car.developeroptions.datausage.CycleAdapter.SpinnerInterface
            public Object getSelectedItem() {
                return DataUsageList.this.mCycleSpinner.getSelectedItem();
            }

            @Override // com.android.car.developeroptions.datausage.CycleAdapter.SpinnerInterface
            public void setSelection(int i) {
                DataUsageList.this.mCycleSpinner.setSelection(i);
            }
        }, this.mCycleListener);
        LoadingViewController loadingViewController = new LoadingViewController(getView().findViewById(R.id.loading_container), getListView());
        this.mLoadingViewController = loadingViewController;
        loadingViewController.showLoadingViewDelayed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onViewCreated$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onViewCreated$0$DataUsageList(View view) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("network_template", this.mTemplate);
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getContext());
        subSettingLauncher.setDestination(BillingCycleSettings.class.getName());
        subSettingLauncher.setTitleRes(R.string.billing_cycle);
        subSettingLauncher.setSourceMetricsCategory(getMetricsCategory());
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.launch();
    }

    @Override // com.android.car.developeroptions.datausage.DataUsageBaseFragment, com.android.car.developeroptions.dashboard.DashboardFragment, com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mDataStateListener.setListener(true, this.mSubId, getContext());
        updateBody();
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mDataStateListener.setListener(false, this.mSubId, getContext());
    }

    @Override // com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        this.mUidDetailProvider.clearCache();
        this.mUidDetailProvider = null;
        super.onDestroy();
    }

    void processArgument() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mSubId = arguments.getInt("sub_id", -1);
            this.mTemplate = arguments.getParcelable("network_template");
            this.mNetworkType = arguments.getInt("network_type", 0);
        }
        if (this.mTemplate == null && this.mSubId == -1) {
            Intent intent = getIntent();
            this.mSubId = intent.getIntExtra("android.provider.extra.SUB_ID", -1);
            this.mTemplate = intent.getParcelableExtra("network_template");
        }
    }

    private void updateBody() {
        SubscriptionInfo activeSubscriptionInfo;
        if (isAdded()) {
            FragmentActivity activity = getActivity();
            getLoaderManager().restartLoader(2, buildArgs(this.mTemplate), this.mNetworkCycleDataCallbacks);
            getActivity().invalidateOptionsMenu();
            int color = activity.getColor(R.color.sim_noitification);
            int i = this.mSubId;
            if (i != -1 && (activeSubscriptionInfo = this.services.mSubscriptionManager.getActiveSubscriptionInfo(i)) != null) {
                color = activeSubscriptionInfo.getIconTint();
            }
            this.mChart.setColors(color, Color.argb(127, Color.red(color), Color.green(color), Color.blue(color)));
        }
    }

    private Bundle buildArgs(NetworkTemplate networkTemplate) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("template", networkTemplate);
        bundle.putParcelable("app", null);
        bundle.putInt("fields", 10);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePolicy() {
        NetworkPolicy policy = this.services.mPolicyEditor.getPolicy(this.mTemplate);
        View viewFindViewById = this.mHeader.findViewById(R.id.filter_settings);
        if (isNetworkPolicyModifiable(policy, this.mSubId) && isMobileDataAvailable(this.mSubId)) {
            this.mChart.setNetworkPolicy(policy);
            viewFindViewById.setVisibility(0);
            ((ImageView) viewFindViewById).setColorFilter(android.R.color.white);
        } else {
            this.mChart.setNetworkPolicy(null);
            viewFindViewById.setVisibility(8);
        }
        if (this.mCycleAdapter.updateCycleList(this.mCycleData)) {
            updateDetailData();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDetailData() {
        getLoaderManager().restartLoader(3, null, this.mNetworkStatsDetailCallbacks);
        List<NetworkCycleChartData> list = this.mCycleData;
        this.mUsageAmount.setTitle(getString(R.string.data_used_template, DataUsageUtils.formatDataUsage(getActivity(), (list == null || list.isEmpty()) ? 0L : this.mCycleData.get(this.mCycleSpinner.getSelectedItemPosition()).getTotalUsage())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindStats(NetworkStats networkStats, int[] iArr) {
        int i;
        int i2;
        int i3;
        this.mApps.removeAll();
        if (networkStats == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        int currentUser = ActivityManager.getCurrentUser();
        UserManager userManager = UserManager.get(getContext());
        List<UserHandle> userProfiles = userManager.getUserProfiles();
        SparseArray sparseArray = new SparseArray();
        NetworkStats.Bucket bucket = new NetworkStats.Bucket();
        long jAccumulate = 0;
        while (true) {
            int i4 = 0;
            if (!networkStats.hasNextBucket() || !networkStats.getNextBucket(bucket)) {
                break;
            }
            int uid = bucket.getUid();
            int userId = UserHandle.getUserId(uid);
            int iBuildKeyForUser = -4;
            if (!UserHandle.isApp(uid)) {
                i = uid;
                if (i != -4 && i != -5 && i != 1061) {
                    i = 1000;
                }
                i2 = 2;
            } else if (userProfiles.contains(new UserHandle(userId))) {
                if (userId != currentUser) {
                    i3 = uid;
                    jAccumulate = accumulate(UidDetailProvider.buildKeyForUser(userId), sparseArray, bucket, 0, arrayList, jAccumulate);
                } else {
                    i3 = uid;
                }
                i2 = 2;
                i = i3;
            } else {
                if (userManager.getUserInfo(userId) == null) {
                    i4 = 2;
                } else {
                    iBuildKeyForUser = UidDetailProvider.buildKeyForUser(userId);
                }
                i = iBuildKeyForUser;
                i2 = i4;
            }
            jAccumulate = accumulate(i, sparseArray, bucket, i2, arrayList, jAccumulate);
        }
        networkStats.close();
        for (int i5 : iArr) {
            if (userProfiles.contains(new UserHandle(UserHandle.getUserId(i5)))) {
                AppItem appItem = (AppItem) sparseArray.get(i5);
                if (appItem == null) {
                    appItem = new AppItem(i5);
                    appItem.total = -1L;
                    arrayList.add(appItem);
                    sparseArray.put(appItem.key, appItem);
                }
                appItem.restricted = true;
            }
        }
        Collections.sort(arrayList);
        for (int i6 = 0; i6 < arrayList.size(); i6++) {
            AppDataUsagePreference appDataUsagePreference = new AppDataUsagePreference(getContext(), (AppItem) arrayList.get(i6), jAccumulate != 0 ? (int) ((((AppItem) arrayList.get(i6)).total * 100) / jAccumulate) : 0, this.mUidDetailProvider);
            appDataUsagePreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.car.developeroptions.datausage.DataUsageList.3
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public boolean onPreferenceClick(Preference preference) {
                    DataUsageList.this.startAppDataUsage(((AppDataUsagePreference) preference).getItem());
                    return true;
                }
            });
            this.mApps.addPreference(appDataUsagePreference);
        }
    }

    void startAppDataUsage(AppItem appItem) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("app_item", appItem);
        bundle.putParcelable("network_template", this.mTemplate);
        if (this.mCycles == null) {
            this.mCycles = new ArrayList<>();
            for (NetworkCycleChartData networkCycleChartData : this.mCycleData) {
                if (this.mCycles.isEmpty()) {
                    this.mCycles.add(Long.valueOf(networkCycleChartData.getEndTime()));
                }
                this.mCycles.add(Long.valueOf(networkCycleChartData.getStartTime()));
            }
        }
        bundle.putSerializable("network_cycles", this.mCycles);
        bundle.putLong("selected_cycle", this.mCycleData.get(this.mCycleSpinner.getSelectedItemPosition()).getEndTime());
        SubSettingLauncher subSettingLauncher = new SubSettingLauncher(getContext());
        subSettingLauncher.setDestination(AppDataUsage.class.getName());
        subSettingLauncher.setTitleRes(R.string.data_usage_app_summary_title);
        subSettingLauncher.setArguments(bundle);
        subSettingLauncher.setSourceMetricsCategory(getMetricsCategory());
        subSettingLauncher.launch();
    }

    private static long accumulate(int i, SparseArray<AppItem> sparseArray, NetworkStats.Bucket bucket, int i2, ArrayList<AppItem> arrayList, long j) {
        int uid = bucket.getUid();
        AppItem appItem = sparseArray.get(i);
        if (appItem == null) {
            appItem = new AppItem(i);
            appItem.category = i2;
            arrayList.add(appItem);
            sparseArray.put(appItem.key, appItem);
        }
        appItem.addUid(uid);
        long rxBytes = appItem.total + bucket.getRxBytes() + bucket.getTxBytes();
        appItem.total = rxBytes;
        return Math.max(j, rxBytes);
    }
}
