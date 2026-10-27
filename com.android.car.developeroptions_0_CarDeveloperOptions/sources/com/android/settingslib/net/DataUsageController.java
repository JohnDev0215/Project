package com.android.settingslib.net;

import android.R;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.INetworkStatsService;
import android.net.NetworkPolicy;
import android.net.NetworkPolicyManager;
import android.net.NetworkTemplate;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.format.DateUtils;
import android.util.Log;
import android.util.Range;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.ArrayUtils;
import java.time.ZonedDateTime;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class DataUsageController {
    private static final StringBuilder PERIOD_BUILDER;
    private static final Formatter PERIOD_FORMATTER;
    private final Context mContext;
    private NetworkNameProvider mNetworkController;
    private final NetworkStatsManager mNetworkStatsManager;
    private final NetworkPolicyManager mPolicyManager;
    private int mSubscriptionId;

    public static class DataUsageInfo {
        public long cycleEnd;
        public long cycleStart;
        public long limitLevel;
        public String period;
        public long usageLevel;
        public long warningLevel;
    }

    public interface NetworkNameProvider {
        String getMobileDataNetworkName();
    }

    static {
        Log.isLoggable("DataUsageController", 3);
        PERIOD_BUILDER = new StringBuilder(50);
        PERIOD_FORMATTER = new Formatter(PERIOD_BUILDER, Locale.getDefault());
    }

    public DataUsageController(Context context) {
        this.mContext = context;
        ConnectivityManager.from(context);
        INetworkStatsService.Stub.asInterface(ServiceManager.getService("netstats"));
        this.mPolicyManager = NetworkPolicyManager.from(this.mContext);
        this.mNetworkStatsManager = (NetworkStatsManager) context.getSystemService(NetworkStatsManager.class);
        this.mSubscriptionId = -1;
    }

    public void setSubscriptionId(int i) {
        this.mSubscriptionId = i;
    }

    public long getDefaultWarningLevel() {
        return ((long) this.mContext.getResources().getInteger(R.integer.config_minMillisBetweenInputUserActivityEvents)) * 1048576;
    }

    private DataUsageInfo warn(String str) {
        Log.w("DataUsageController", "Failed to get data usage, " + str);
        return null;
    }

    public DataUsageInfo getDataUsageInfo() {
        return getDataUsageInfo(DataUsageUtils.getMobileTemplate(this.mContext, this.mSubscriptionId));
    }

    public DataUsageInfo getWifiDataUsageInfo() {
        return getDataUsageInfo(NetworkTemplate.buildTemplateWifiWildcard());
    }

    public DataUsageInfo getDataUsageInfo(NetworkTemplate networkTemplate) {
        long j;
        NetworkPolicy networkPolicyFindNetworkPolicy = findNetworkPolicy(networkTemplate);
        long jCurrentTimeMillis = System.currentTimeMillis();
        Iterator itCycleIterator = networkPolicyFindNetworkPolicy != null ? networkPolicyFindNetworkPolicy.cycleIterator() : null;
        if (itCycleIterator == null || !itCycleIterator.hasNext()) {
            j = jCurrentTimeMillis - 2419200000L;
        } else {
            Range range = (Range) itCycleIterator.next();
            long epochMilli = ((ZonedDateTime) range.getLower()).toInstant().toEpochMilli();
            jCurrentTimeMillis = ((ZonedDateTime) range.getUpper()).toInstant().toEpochMilli();
            j = epochMilli;
        }
        long usageLevel = getUsageLevel(networkTemplate, j, jCurrentTimeMillis);
        if (usageLevel < 0) {
            return warn("no entry data");
        }
        DataUsageInfo dataUsageInfo = new DataUsageInfo();
        dataUsageInfo.usageLevel = usageLevel;
        dataUsageInfo.period = formatDateRange(j, jCurrentTimeMillis);
        dataUsageInfo.cycleStart = j;
        dataUsageInfo.cycleEnd = jCurrentTimeMillis;
        if (networkPolicyFindNetworkPolicy != null) {
            long j2 = networkPolicyFindNetworkPolicy.limitBytes;
            if (j2 <= 0) {
                j2 = 0;
            }
            dataUsageInfo.limitLevel = j2;
            long j3 = networkPolicyFindNetworkPolicy.warningBytes;
            dataUsageInfo.warningLevel = j3 > 0 ? j3 : 0L;
        } else {
            dataUsageInfo.warningLevel = getDefaultWarningLevel();
        }
        NetworkNameProvider networkNameProvider = this.mNetworkController;
        if (networkNameProvider != null) {
            networkNameProvider.getMobileDataNetworkName();
        }
        return dataUsageInfo;
    }

    public long getHistoricalUsageLevel(NetworkTemplate networkTemplate) {
        return getUsageLevel(networkTemplate, 0L, System.currentTimeMillis());
    }

    private long getUsageLevel(NetworkTemplate networkTemplate, long j, long j2) {
        try {
            NetworkStats.Bucket bucketQuerySummaryForDevice = this.mNetworkStatsManager.querySummaryForDevice(networkTemplate, j, j2);
            if (bucketQuerySummaryForDevice != null) {
                return bucketQuerySummaryForDevice.getRxBytes() + bucketQuerySummaryForDevice.getTxBytes();
            }
            Log.w("DataUsageController", "Failed to get data usage, no entry data");
            return -1L;
        } catch (RemoteException unused) {
            Log.w("DataUsageController", "Failed to get data usage, remote call failed");
            return -1L;
        }
    }

    private NetworkPolicy findNetworkPolicy(NetworkTemplate networkTemplate) {
        NetworkPolicy[] networkPolicies;
        NetworkPolicyManager networkPolicyManager = this.mPolicyManager;
        if (networkPolicyManager == null || networkTemplate == null || (networkPolicies = networkPolicyManager.getNetworkPolicies()) == null) {
            return null;
        }
        for (NetworkPolicy networkPolicy : networkPolicies) {
            if (networkPolicy != null && networkTemplate.equals(networkPolicy.template)) {
                return networkPolicy;
            }
        }
        return null;
    }

    @VisibleForTesting
    public TelephonyManager getTelephonyManager() {
        int defaultDataSubscriptionId = this.mSubscriptionId;
        if (!SubscriptionManager.isValidSubscriptionId(defaultDataSubscriptionId)) {
            defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
        }
        if (!SubscriptionManager.isValidSubscriptionId(defaultDataSubscriptionId)) {
            int[] activeSubscriptionIdList = SubscriptionManager.from(this.mContext).getActiveSubscriptionIdList();
            if (!ArrayUtils.isEmpty(activeSubscriptionIdList)) {
                defaultDataSubscriptionId = activeSubscriptionIdList[0];
            }
        }
        return ((TelephonyManager) this.mContext.getSystemService(TelephonyManager.class)).createForSubscriptionId(defaultDataSubscriptionId);
    }

    private String formatDateRange(long j, long j2) {
        String string;
        synchronized (PERIOD_BUILDER) {
            PERIOD_BUILDER.setLength(0);
            string = DateUtils.formatDateRange(this.mContext, PERIOD_FORMATTER, j, j2, 65552, null).toString();
        }
        return string;
    }
}
