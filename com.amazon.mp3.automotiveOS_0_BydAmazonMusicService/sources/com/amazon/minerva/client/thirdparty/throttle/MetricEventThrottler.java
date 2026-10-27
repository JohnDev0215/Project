package com.amazon.minerva.client.thirdparty.throttle;

import android.util.Log;
import com.amazon.minerva.client.thirdparty.MinervaServiceAndroidAdapter;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.minerva.client.thirdparty.metric.Timestamp;
import com.amazon.minerva.client.thirdparty.utils.LRUCache;

/* JADX INFO: loaded from: classes3.dex */
public class MetricEventThrottler {
    private static final Integer MAX_CACHE_ENTRIES = 500;
    private static final String TAG = "MetricEventThrottler";
    private static int sDefaultThrottleCreditPerHour;
    private static int sMaxThrottleCredit;
    private static MetricEventThrottler sMetricEventThrottler;
    private MinervaServiceAndroidAdapter mMinervaServiceAndroidAdapter;
    private LRUCache<String, ThrottleProfile> mThrottleCache = new LRUCache<>(MAX_CACHE_ENTRIES.intValue());

    private MetricEventThrottler(MinervaServiceAndroidAdapter minervaServiceAndroidAdapter) {
        this.mMinervaServiceAndroidAdapter = minervaServiceAndroidAdapter;
    }

    public static MetricEventThrottler getInstance(MinervaServiceAndroidAdapter minervaServiceAndroidAdapter) {
        if (sMetricEventThrottler == null) {
            synchronized (MetricEventThrottler.class) {
                if (sMetricEventThrottler == null) {
                    sMetricEventThrottler = new MetricEventThrottler(minervaServiceAndroidAdapter);
                }
            }
        }
        return sMetricEventThrottler;
    }

    public int getMaxCacheEntries() {
        return MAX_CACHE_ENTRIES.intValue();
    }

    public int getDefaultThrottleCredit() {
        int defaultThrottleCreditHour = this.mMinervaServiceAndroidAdapter.getMinervaServiceManager().getConfigurationManager().getMetricsConfigurationHelper().getThrottleConfiguration().getDefaultThrottleCreditHour();
        sDefaultThrottleCreditPerHour = defaultThrottleCreditHour;
        return defaultThrottleCreditHour;
    }

    public int getMaxThrottleCredit() {
        int maxThrottleCredit = this.mMinervaServiceAndroidAdapter.getMinervaServiceManager().getConfigurationManager().getMetricsConfigurationHelper().getThrottleConfiguration().getMaxThrottleCredit();
        sMaxThrottleCredit = maxThrottleCredit;
        return maxThrottleCredit;
    }

    public synchronized boolean shouldThrottleMetricEvent(MetricEvent metricEvent) {
        String metricGroupId = metricEvent.getMetricGroupId();
        ThrottleProfile throttleProfile = this.mThrottleCache.get(metricGroupId);
        if (throttleProfile == null) {
            this.mThrottleCache.put(metricGroupId, new ThrottleProfile(Timestamp.now().getEpochMillis(), getDefaultThrottleCredit() - 1));
        } else {
            long epochMillis = Timestamp.now().getEpochMillis();
            double dCalculateCurrentCredit = calculateCurrentCredit(epochMillis, throttleProfile);
            if (dCalculateCurrentCredit < 1.0d) {
                Log.d(TAG, "Drop metric event due to throttle. MetricGroupId=" + metricEvent.getMetricGroupId() + " SchemaId=" + metricEvent.getSchemaId());
                return true;
            }
            throttleProfile.setCredit(dCalculateCurrentCredit - 1.0d);
            throttleProfile.setTimestamp(epochMillis);
            this.mThrottleCache.put(metricGroupId, throttleProfile);
        }
        return false;
    }

    double calculateCurrentCredit(long j, ThrottleProfile throttleProfile) {
        return Math.min(((((((double) getDefaultThrottleCredit()) * (j - throttleProfile.getTimestamp())) / 1000.0d) / 60.0d) / 60.0d) + throttleProfile.getCredit(), getMaxThrottleCredit());
    }
}
