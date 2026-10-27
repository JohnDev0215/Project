package com.android.car.settings.datausage;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import android.net.INetworkPolicyManager;
import android.net.NetworkTemplate;
import android.os.ServiceManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.SubscriptionPlan;
import android.telephony.TelephonyManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.text.style.AbsoluteSizeSpan;
import android.util.RecurrenceRule;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.car.settings.network.NetworkUtils;
import com.android.settingslib.net.DataUsageController;
import com.android.settingslib.utils.StringUtil;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: classes5.dex */
public class DataUsageSummaryPreferenceController extends PreferenceController<DataUsageSummaryPreference> {
    private static final int MAX_PROGRESS_BAR_VALUE = 1000;
    private CharSequence mCarrierName;
    private long mCycleEnd;
    private final DataUsageController mDataUsageController;
    private int mDataplanCount;
    private long mDataplanSize;
    private long mDataplanTrackingThreshold;
    private long mDataplanUse;
    private final NetworkTemplate mDefaultTemplate;
    private Intent mManageSubscriptionIntent;
    private long mSnapshotTime;
    private final SubscriptionManager mSubscriptionManager;
    private final TelephonyManager mTelephonyManager;
    private static final Logger LOG = new Logger((Class<?>) DataUsageSummaryPreferenceController.class);
    private static final long MILLIS_IN_A_DAY = TimeUnit.DAYS.toMillis(1);
    private static final long MILLIS_IN_AN_HOUR = TimeUnit.HOURS.toMillis(1);
    private static final long MILLIS_IN_A_MINUTE = TimeUnit.MINUTES.toMillis(1);
    private static final long MILLIS_IN_A_SECOND = TimeUnit.SECONDS.toMillis(1);
    static final long WARNING_AGE = TimeUnit.HOURS.toMillis(6);

    public DataUsageSummaryPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mDataplanSize = -1L;
        this.mSubscriptionManager = (SubscriptionManager) context.getSystemService(SubscriptionManager.class);
        this.mTelephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        this.mDataUsageController = new DataUsageController(context);
        int defaultSubId = DataUsageUtils.getDefaultSubscriptionId(this.mSubscriptionManager);
        this.mDefaultTemplate = DataUsageUtils.getMobileNetworkTemplate(this.mTelephonyManager, defaultSubId);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<DataUsageSummaryPreference> getPreferenceType() {
        return DataUsageSummaryPreference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        return !NetworkUtils.hasSim(this.mTelephonyManager) ? 1 : 0;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        getPreference().setMin(0);
        getPreference().setMax(1000);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.android.car.settings.common.PreferenceController
    public void updateState(DataUsageSummaryPreference preference) {
        DataUsageController.DataUsageInfo info = this.mDataUsageController.getDataUsageInfo(this.mDefaultTemplate);
        if (this.mSubscriptionManager != null) {
            refreshDataplanInfo(info);
        }
        preference.setTitle(getUsageText());
        preference.setManageSubscriptionIntent(this.mManageSubscriptionIntent);
        preference.setDataLimitText(getLimitText(info));
        preference.setRemainingBillingCycleText(getRemainingBillingCycleTimeText());
        preference.setCarrierInfoText(getCarrierInfoText());
        long updateAgeMillis = calculateTruncatedUpdateAge(this.mSnapshotTime);
        if (updateAgeMillis <= WARNING_AGE) {
            preference.setCarrierInfoTextStyle(R.style.DataUsageSummaryCarrierInfoTextAppearance);
        } else {
            preference.setCarrierInfoTextStyle(R.style.DataUsageSummaryCarrierInfoWarningTextAppearance);
        }
        preference.setMinLabel(DataUsageUtils.bytesToIecUnits(getContext(), 0L));
        preference.setMaxLabel(DataUsageUtils.bytesToIecUnits(getContext(), this.mDataplanTrackingThreshold));
        preference.setProgress(scaleUsage(this.mDataplanUse, this.mDataplanTrackingThreshold));
    }

    private CharSequence getUsageText() {
        Formatter.BytesResult usedResult = Formatter.formatBytes(getContext().getResources(), this.mDataplanUse, 10);
        SpannableString usageNumberText = new SpannableString(usedResult.value);
        int textSize = getContext().getResources().getDimensionPixelSize(R.dimen.usage_number_text_size);
        usageNumberText.setSpan(new AbsoluteSizeSpan(textSize), 0, usageNumberText.length(), 33);
        CharSequence template = getContext().getText(R.string.data_used_formatted);
        CharSequence usageText = TextUtils.expandTemplate(template, usageNumberText, usedResult.units);
        return usageText;
    }

    private CharSequence getLimitText(DataUsageController.DataUsageInfo info) {
        if (info.warningLevel > 0 && info.limitLevel > 0) {
            return TextUtils.expandTemplate(getContext().getText(R.string.cell_data_warning_and_limit), DataUsageUtils.bytesToIecUnits(getContext(), info.warningLevel), DataUsageUtils.bytesToIecUnits(getContext(), info.limitLevel));
        }
        if (info.warningLevel > 0) {
            return TextUtils.expandTemplate(getContext().getText(R.string.cell_data_warning), DataUsageUtils.bytesToIecUnits(getContext(), info.warningLevel));
        }
        if (info.limitLevel > 0) {
            return TextUtils.expandTemplate(getContext().getText(R.string.cell_data_limit), DataUsageUtils.bytesToIecUnits(getContext(), info.limitLevel));
        }
        return null;
    }

    private CharSequence getRemainingBillingCycleTimeText() {
        long millisLeft = this.mCycleEnd - System.currentTimeMillis();
        if (millisLeft <= 0) {
            return getContext().getString(R.string.billing_cycle_none_left);
        }
        int daysLeft = (int) (millisLeft / MILLIS_IN_A_DAY);
        if (daysLeft < 1) {
            return getContext().getString(R.string.billing_cycle_less_than_one_day_left);
        }
        return getContext().getResources().getQuantityString(R.plurals.billing_cycle_days_left, daysLeft, Integer.valueOf(daysLeft));
    }

    private CharSequence getCarrierInfoText() {
        int textResourceId;
        if (this.mDataplanCount <= 0) {
            return null;
        }
        long j = this.mSnapshotTime;
        if (j >= 0) {
            long updateAgeMillis = calculateTruncatedUpdateAge(j);
            CharSequence updateTime = null;
            if (updateAgeMillis == 0) {
                if (this.mCarrierName != null) {
                    textResourceId = R.string.carrier_and_update_now_text;
                } else {
                    textResourceId = R.string.no_carrier_update_now_text;
                }
            } else {
                if (this.mCarrierName != null) {
                    textResourceId = R.string.carrier_and_update_text;
                } else {
                    textResourceId = R.string.no_carrier_update_text;
                }
                updateTime = StringUtil.formatElapsedTime(getContext(), updateAgeMillis, false);
            }
            return TextUtils.expandTemplate(getContext().getText(textResourceId), this.mCarrierName, updateTime);
        }
        return null;
    }

    private void refreshDataplanInfo(DataUsageController.DataUsageInfo info) {
        this.mCarrierName = null;
        this.mDataplanCount = 0;
        this.mSnapshotTime = -1L;
        this.mDataplanSize = -1L;
        this.mDataplanTrackingThreshold = getSummaryLimit(info);
        this.mDataplanUse = info.usageLevel;
        this.mCycleEnd = info.cycleEnd;
        int defaultSubId = SubscriptionManager.getDefaultSubscriptionId();
        SubscriptionInfo subInfo = this.mSubscriptionManager.getDefaultDataSubscriptionInfo();
        if (subInfo != null) {
            this.mCarrierName = subInfo.getCarrierName();
            List<SubscriptionPlan> plans = this.mSubscriptionManager.getSubscriptionPlans(defaultSubId);
            SubscriptionPlan primaryPlan = DataUsageUtils.getPrimaryPlan(this.mSubscriptionManager, defaultSubId);
            if (primaryPlan != null) {
                this.mDataplanCount = plans.size();
                long dataLimitBytes = primaryPlan.getDataLimitBytes();
                this.mDataplanSize = dataLimitBytes;
                if (dataLimitBytes == LongCompanionObject.MAX_VALUE) {
                    this.mDataplanSize = -1L;
                }
                this.mDataplanTrackingThreshold = this.mDataplanSize;
                this.mDataplanUse = primaryPlan.getDataUsageBytes();
                RecurrenceRule rule = primaryPlan.getCycleRule();
                if (rule != null && rule.start != null && rule.end != null) {
                    this.mCycleEnd = rule.end.toEpochSecond() * MILLIS_IN_A_SECOND;
                }
                this.mSnapshotTime = primaryPlan.getDataUsageTime();
            }
        }
        this.mManageSubscriptionIntent = createManageSubscriptionIntent(defaultSubId);
    }

    private Intent createManageSubscriptionIntent(int subId) {
        INetworkPolicyManager networkPolicyManager = INetworkPolicyManager.Stub.asInterface(ServiceManager.getService("netpolicy"));
        String owner = "";
        try {
            owner = networkPolicyManager.getSubscriptionPlansOwner(subId);
        } catch (Exception ex) {
            LOG.w("Fail to get subscription plan owner for subId " + subId, ex);
        }
        if (TextUtils.isEmpty(owner)) {
            return null;
        }
        List<SubscriptionPlan> plans = this.mSubscriptionManager.getSubscriptionPlans(subId);
        if (plans.isEmpty()) {
            return null;
        }
        Intent intent = new Intent("android.telephony.action.MANAGE_SUBSCRIPTION_PLANS");
        intent.setPackage(owner);
        intent.putExtra("android.telephony.extra.SUBSCRIPTION_INDEX", subId);
        if (getContext().getPackageManager().queryIntentActivities(intent, 65536).isEmpty()) {
            return null;
        }
        return intent;
    }

    private int scaleUsage(long usage, long maxUsage) {
        if (maxUsage == 0) {
            return 0;
        }
        return (int) ((usage / maxUsage) * 1000.0f);
    }

    private static long getSummaryLimit(DataUsageController.DataUsageInfo info) {
        long limit = info.limitLevel;
        if (limit <= 0) {
            limit = info.warningLevel;
        }
        if (info.usageLevel > limit) {
            return info.usageLevel;
        }
        return limit;
    }

    private long calculateTruncatedUpdateAge(long snapshotTime) {
        long updateAgeMillis = System.currentTimeMillis() - snapshotTime;
        long j = MILLIS_IN_A_DAY;
        if (updateAgeMillis >= j) {
            return (updateAgeMillis / j) * j;
        }
        long j2 = MILLIS_IN_AN_HOUR;
        if (updateAgeMillis >= j2) {
            return (updateAgeMillis / j2) * j2;
        }
        long j3 = MILLIS_IN_A_MINUTE;
        if (updateAgeMillis >= j3) {
            return (updateAgeMillis / j3) * j3;
        }
        return 0L;
    }
}
