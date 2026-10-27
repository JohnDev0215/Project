package com.android.car.developeroptions.datausage;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.INetworkPolicyManager;
import android.net.NetworkPolicyManager;
import android.net.NetworkTemplate;
import android.os.ServiceManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.SubscriptionPlan;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.util.RecurrenceRule;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.car.developeroptions.widget.EntityHeaderController;
import com.android.internal.util.CollectionUtils;
import com.android.settingslib.NetworkPolicyEditor;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.net.DataUsageController;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
public class DataUsageSummaryPreferenceController extends BasePreferenceController implements PreferenceControllerMixin, LifecycleObserver, OnStart {
    private static final String KEY = "status_header";
    private static final long PETA = 1000000000000000L;
    private static final float RELATIVE_SIZE_LARGE = 1.5625f;
    private static final float RELATIVE_SIZE_SMALL = 0.64f;
    private static final String TAG = "DataUsageController";
    private final Activity mActivity;
    private CharSequence mCarrierName;
    private long mCycleEnd;
    private long mCycleStart;
    private long mDataBarSize;
    protected final DataUsageInfoController mDataInfoController;
    protected final DataUsageController mDataUsageController;
    private final int mDataUsageTemplate;
    private int mDataplanCount;
    private long mDataplanSize;
    private long mDataplanUse;
    private final NetworkTemplate mDefaultTemplate;
    private final EntityHeaderController mEntityHeaderController;
    private final PreferenceFragmentCompat mFragment;
    private final boolean mHasMobileData;
    private final Lifecycle mLifecycle;
    private Intent mManageSubscriptionIntent;
    protected final NetworkPolicyEditor mPolicyEditor;
    private long mSnapshotTime;
    private int mSubscriptionId;
    private final SubscriptionManager mSubscriptionManager;

    private static boolean saneSize(long j) {
        return j >= 0 && j < PETA;
    }

    public static boolean unlimited(long j) {
        return j == Long.MAX_VALUE;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public DataUsageSummaryPreferenceController(Activity activity, Lifecycle lifecycle, PreferenceFragmentCompat preferenceFragmentCompat, int i) {
        super(activity, KEY);
        this.mActivity = activity;
        this.mEntityHeaderController = EntityHeaderController.newInstance(activity, preferenceFragmentCompat, null);
        this.mLifecycle = lifecycle;
        this.mFragment = preferenceFragmentCompat;
        this.mSubscriptionId = i;
        this.mDefaultTemplate = DataUsageUtils.getDefaultTemplate(activity, i);
        this.mPolicyEditor = new NetworkPolicyEditor((NetworkPolicyManager) activity.getSystemService(NetworkPolicyManager.class));
        this.mHasMobileData = DataUsageUtils.hasMobileData(activity) && this.mSubscriptionId != -1;
        DataUsageController dataUsageController = new DataUsageController(activity);
        this.mDataUsageController = dataUsageController;
        dataUsageController.setSubscriptionId(this.mSubscriptionId);
        this.mDataInfoController = new DataUsageInfoController();
        if (this.mHasMobileData) {
            this.mDataUsageTemplate = R.string.cell_data_template;
        } else if (DataUsageUtils.hasWifiRadio(activity)) {
            this.mDataUsageTemplate = R.string.wifi_data_template;
        } else {
            this.mDataUsageTemplate = R.string.ethernet_data_template;
        }
        this.mSubscriptionManager = (SubscriptionManager) this.mContext.getSystemService("telephony_subscription_service");
    }

    DataUsageSummaryPreferenceController(DataUsageController dataUsageController, DataUsageInfoController dataUsageInfoController, NetworkTemplate networkTemplate, NetworkPolicyEditor networkPolicyEditor, int i, boolean z, SubscriptionManager subscriptionManager, Activity activity, Lifecycle lifecycle, EntityHeaderController entityHeaderController, PreferenceFragmentCompat preferenceFragmentCompat, int i2) {
        super(activity, KEY);
        this.mDataUsageController = dataUsageController;
        this.mDataInfoController = dataUsageInfoController;
        this.mDefaultTemplate = networkTemplate;
        this.mPolicyEditor = networkPolicyEditor;
        this.mDataUsageTemplate = i;
        this.mHasMobileData = z;
        this.mSubscriptionManager = subscriptionManager;
        this.mActivity = activity;
        this.mLifecycle = lifecycle;
        this.mEntityHeaderController = entityHeaderController;
        this.mFragment = preferenceFragmentCompat;
        this.mSubscriptionId = i2;
    }

    @Override // com.android.settingslib.core.lifecycle.events.OnStart
    public void onStart() {
        this.mEntityHeaderController.setRecyclerView(this.mFragment.getListView(), this.mLifecycle);
        this.mEntityHeaderController.styleActionBar(this.mActivity);
    }

    void setPlanValues(int i, long j, long j2) {
        this.mDataplanCount = i;
        this.mDataplanSize = j;
        this.mDataBarSize = j;
        this.mDataplanUse = j2;
    }

    void setCarrierValues(String str, long j, long j2, Intent intent) {
        this.mCarrierName = str;
        this.mSnapshotTime = j;
        this.mCycleEnd = j2;
        this.mManageSubscriptionIntent = intent;
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return (DataUsageUtils.hasSim(this.mActivity) || DataUsageUtils.hasWifiRadio(this.mContext)) ? 0 : 2;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        DataUsageSummaryPreference dataUsageSummaryPreference = (DataUsageSummaryPreference) preference;
        if (DataUsageUtils.hasSim(this.mActivity)) {
            DataUsageController.DataUsageInfo dataUsageInfo = this.mDataUsageController.getDataUsageInfo(this.mDefaultTemplate);
            this.mDataInfoController.updateDataLimit(dataUsageInfo, this.mPolicyEditor.getPolicy(this.mDefaultTemplate));
            dataUsageSummaryPreference.setWifiMode(false, null, false);
            if (this.mSubscriptionManager != null) {
                refreshDataplanInfo(dataUsageInfo);
            }
            if (dataUsageInfo.warningLevel > 0 && dataUsageInfo.limitLevel > 0) {
                dataUsageSummaryPreference.setLimitInfo(TextUtils.expandTemplate(this.mContext.getText(R.string.cell_data_warning_and_limit), DataUsageUtils.formatDataUsage(this.mContext, dataUsageInfo.warningLevel), DataUsageUtils.formatDataUsage(this.mContext, dataUsageInfo.limitLevel)));
            } else if (dataUsageInfo.warningLevel > 0) {
                dataUsageSummaryPreference.setLimitInfo(TextUtils.expandTemplate(this.mContext.getText(R.string.cell_data_warning), DataUsageUtils.formatDataUsage(this.mContext, dataUsageInfo.warningLevel)));
            } else if (dataUsageInfo.limitLevel > 0) {
                dataUsageSummaryPreference.setLimitInfo(TextUtils.expandTemplate(this.mContext.getText(R.string.cell_data_limit), DataUsageUtils.formatDataUsage(this.mContext, dataUsageInfo.limitLevel)));
            } else {
                dataUsageSummaryPreference.setLimitInfo(null);
            }
            dataUsageSummaryPreference.setUsageNumbers(this.mDataplanUse, this.mDataplanSize, this.mHasMobileData);
            if (this.mDataBarSize <= 0) {
                dataUsageSummaryPreference.setChartEnabled(false);
            } else {
                dataUsageSummaryPreference.setChartEnabled(true);
                dataUsageSummaryPreference.setLabels(Formatter.formatFileSize(this.mContext, 0L), DataUsageUtils.formatDataUsage(this.mContext, this.mDataBarSize));
                dataUsageSummaryPreference.setProgress(this.mDataplanUse / this.mDataBarSize);
            }
            dataUsageSummaryPreference.setUsageInfo(this.mCycleEnd, this.mSnapshotTime, this.mCarrierName, this.mDataplanCount, this.mManageSubscriptionIntent);
            return;
        }
        DataUsageController.DataUsageInfo dataUsageInfo2 = this.mDataUsageController.getDataUsageInfo(NetworkTemplate.buildTemplateWifiWildcard());
        dataUsageSummaryPreference.setWifiMode(true, dataUsageInfo2.period, false);
        dataUsageSummaryPreference.setLimitInfo(null);
        dataUsageSummaryPreference.setUsageNumbers(dataUsageInfo2.usageLevel, -1L, true);
        dataUsageSummaryPreference.setChartEnabled(false);
        dataUsageSummaryPreference.setUsageInfo(dataUsageInfo2.cycleEnd, -1L, null, 0, null);
    }

    private void refreshDataplanInfo(DataUsageController.DataUsageInfo dataUsageInfo) {
        ZonedDateTime zonedDateTime;
        this.mCarrierName = null;
        this.mDataplanCount = 0;
        this.mDataplanSize = -1L;
        this.mDataBarSize = this.mDataInfoController.getSummaryLimit(dataUsageInfo);
        this.mDataplanUse = dataUsageInfo.usageLevel;
        this.mCycleStart = dataUsageInfo.cycleStart;
        this.mCycleEnd = dataUsageInfo.cycleEnd;
        this.mSnapshotTime = -1L;
        SubscriptionInfo activeSubscriptionInfo = this.mSubscriptionManager.getActiveSubscriptionInfo(this.mSubscriptionId);
        if (activeSubscriptionInfo == null) {
            activeSubscriptionInfo = this.mSubscriptionManager.getAllSubscriptionInfoList().stream().filter(new Predicate() { // from class: com.android.car.developeroptions.datausage.-$$Lambda$DataUsageSummaryPreferenceController$nR9NYcRzD7FxnJBoa2Th-3MmDA4
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.f$0.lambda$refreshDataplanInfo$0$DataUsageSummaryPreferenceController((SubscriptionInfo) obj);
                }
            }).findFirst().orElse(null);
        }
        if (activeSubscriptionInfo != null && this.mHasMobileData) {
            this.mCarrierName = activeSubscriptionInfo.getCarrierName();
            List<SubscriptionPlan> subscriptionPlans = this.mSubscriptionManager.getSubscriptionPlans(this.mSubscriptionId);
            SubscriptionPlan primaryPlan = getPrimaryPlan(this.mSubscriptionManager, this.mSubscriptionId);
            if (primaryPlan != null) {
                this.mDataplanCount = subscriptionPlans.size();
                long dataLimitBytes = primaryPlan.getDataLimitBytes();
                this.mDataplanSize = dataLimitBytes;
                if (unlimited(dataLimitBytes)) {
                    this.mDataplanSize = -1L;
                }
                this.mDataBarSize = this.mDataplanSize;
                this.mDataplanUse = primaryPlan.getDataUsageBytes();
                RecurrenceRule cycleRule = primaryPlan.getCycleRule();
                if (cycleRule != null && (zonedDateTime = cycleRule.start) != null && cycleRule.end != null) {
                    this.mCycleStart = zonedDateTime.toEpochSecond() * 1000;
                    this.mCycleEnd = cycleRule.end.toEpochSecond() * 1000;
                }
                this.mSnapshotTime = primaryPlan.getDataUsageTime();
            }
        }
        this.mManageSubscriptionIntent = createManageSubscriptionIntent(this.mSubscriptionId);
        Log.i(TAG, "Have " + this.mDataplanCount + " plans, dflt sub-id " + this.mSubscriptionId + ", intent " + this.mManageSubscriptionIntent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$refreshDataplanInfo$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$refreshDataplanInfo$0$DataUsageSummaryPreferenceController(SubscriptionInfo subscriptionInfo) {
        return subscriptionInfo.getSubscriptionId() == this.mSubscriptionId;
    }

    private Intent createManageSubscriptionIntent(int i) {
        String subscriptionPlansOwner;
        try {
            subscriptionPlansOwner = INetworkPolicyManager.Stub.asInterface(ServiceManager.getService("netpolicy")).getSubscriptionPlansOwner(i);
        } catch (Exception e) {
            Log.w("Fail to get subscription plan owner for subId " + i, e);
            subscriptionPlansOwner = "";
        }
        if (TextUtils.isEmpty(subscriptionPlansOwner) || this.mSubscriptionManager.getSubscriptionPlans(i).isEmpty()) {
            return null;
        }
        Intent intent = new Intent("android.telephony.action.MANAGE_SUBSCRIPTION_PLANS");
        intent.setPackage(subscriptionPlansOwner);
        intent.putExtra("android.telephony.extra.SUBSCRIPTION_INDEX", i);
        if (this.mContext.getPackageManager().queryIntentActivities(intent, 65536).isEmpty()) {
            return null;
        }
        return intent;
    }

    public static SubscriptionPlan getPrimaryPlan(SubscriptionManager subscriptionManager, int i) {
        List<SubscriptionPlan> subscriptionPlans = subscriptionManager.getSubscriptionPlans(i);
        if (CollectionUtils.isEmpty(subscriptionPlans)) {
            return null;
        }
        SubscriptionPlan subscriptionPlan = subscriptionPlans.get(0);
        if (subscriptionPlan.getDataLimitBytes() <= 0 || !saneSize(subscriptionPlan.getDataUsageBytes()) || subscriptionPlan.getCycleRule() == null) {
            return null;
        }
        return subscriptionPlan;
    }
}
