package com.android.car.settings.datausage;

import android.content.Context;
import android.net.NetworkPolicy;
import android.net.NetworkPolicyManager;
import android.net.NetworkTemplate;
import android.os.Bundle;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Pair;
import androidx.loader.app.LoaderManager;
import com.android.car.settings.R;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.SettingsFragment;
import com.android.car.settings.datausage.AppsNetworkStatsManager;
import com.android.settingslib.NetworkPolicyEditor;
import java.time.ZonedDateTime;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public class AppDataUsageFragment extends SettingsFragment {
    private static final String ARG_NETWORK_SUB_ID = "network_sub_id";
    private static final Logger LOG = new Logger((Class<?>) AppDataUsageFragment.class);
    private static final int SUB_ID_NULL = Integer.MIN_VALUE;
    private AppsNetworkStatsManager mAppsNetworkStatsManager;
    private Bundle mBundle;
    private NetworkTemplate mNetworkTemplate;
    private NetworkPolicyEditor mPolicyEditor;

    public static AppDataUsageFragment newInstance(int subId) {
        AppDataUsageFragment fragment = new AppDataUsageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_NETWORK_SUB_ID, subId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment
    protected int getPreferenceScreenResId() {
        return R.xml.app_data_usage_fragment;
    }

    @Override // com.android.car.settings.common.SettingsFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        int subId = getArguments() != null ? getArguments().getInt(ARG_NETWORK_SUB_ID, Integer.MIN_VALUE) : Integer.MIN_VALUE;
        if (subId == Integer.MIN_VALUE) {
            LOG.d("Cannot get the subscription id from arguments. Switching to default subscription Id: " + subId);
            SubscriptionManager subscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
            subId = DataUsageUtils.getDefaultSubscriptionId(subscriptionManager);
        }
        this.mNetworkTemplate = DataUsageUtils.getMobileNetworkTemplate(telephonyManager, subId);
        this.mPolicyEditor = new NetworkPolicyEditor(NetworkPolicyManager.from(context));
        AppsNetworkStatsManager appsNetworkStatsManager = new AppsNetworkStatsManager(getContext());
        this.mAppsNetworkStatsManager = appsNetworkStatsManager;
        appsNetworkStatsManager.registerListener((AppsNetworkStatsManager.Callback) use(AppDataUsagePreferenceController.class, R.string.pk_app_data_usage_detail));
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mBundle = getBundleForNetworkStats();
        LoaderManager loaderManager = LoaderManager.getInstance(this);
        this.mAppsNetworkStatsManager.startLoading(loaderManager, this.mBundle);
    }

    private Bundle getBundleForNetworkStats() {
        long historyStart = System.currentTimeMillis();
        long historyEnd = 1 + historyStart;
        long start = 0;
        long end = 0;
        boolean hasCycles = false;
        NetworkPolicy policy = this.mPolicyEditor.getPolicy(this.mNetworkTemplate);
        if (policy != null) {
            Iterator<Pair<ZonedDateTime, ZonedDateTime>> it = NetworkPolicyManager.cycleIterator(policy);
            while (it.hasNext()) {
                Pair<ZonedDateTime, ZonedDateTime> cycle = it.next();
                start = ((ZonedDateTime) cycle.first).toInstant().toEpochMilli();
                end = ((ZonedDateTime) cycle.second).toInstant().toEpochMilli();
                hasCycles = true;
            }
        }
        if (!hasCycles) {
            long cycleEnd = historyEnd;
            while (cycleEnd > historyStart) {
                long cycleStart = cycleEnd - 2419200000L;
                start = cycleStart;
                end = cycleEnd;
                cycleEnd = cycleStart;
            }
        }
        return SummaryForAllUidLoader.buildArgs(this.mNetworkTemplate, start, end);
    }

    Bundle getBundle() {
        return this.mBundle;
    }
}
