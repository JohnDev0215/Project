package com.amazon.minerva.client.thirdparty.api.impl;

import android.content.Context;
import android.util.Log;
import com.amazon.minerva.client.thirdparty.MinervaServiceAndroidAdapter;
import com.amazon.minerva.client.thirdparty.api.AggregatedMetricEvent;
import com.amazon.minerva.client.thirdparty.api.MetricEvent;
import com.amazon.minerva.client.thirdparty.api.callback.MetricRecordCallback;
import com.amazon.minerva.client.thirdparty.api.callback.MetricRecordStatus;
import com.amazon.minerva.client.thirdparty.api.callback.NoOpMetricRecordCallback;
import com.amazon.minerva.client.thirdparty.configuration.ThrottleConfiguration;
import com.amazon.minerva.client.thirdparty.configuration.ValidationConfiguration;
import com.amazon.minerva.client.thirdparty.kpi.KPIMetric;
import com.amazon.minerva.client.thirdparty.metric.DataPointEnvelope;
import com.amazon.minerva.client.thirdparty.metric.Timestamp;
import com.amazon.minerva.client.thirdparty.metric.TypedValue;
import com.amazon.minerva.client.thirdparty.throttle.MetricEventThrottler;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class AmazonMinervaAndroidClient {
    private static final String TAG = "AmazonMinervaAndroidClient";
    private static final NoOpMetricRecordCallback noOpCallback = new NoOpMetricRecordCallback();
    private final Context context;
    private final String deviceType;
    private MetricEventThrottler mMetricEventThrottler;
    private final MinervaServiceAndroidAdapter mMinervaServiceAndroidAdapter;
    private ThrottleConfiguration mThrottleConfiguration;
    private ValidationConfiguration mValidationConfiguration;
    private final String region;

    AmazonMinervaAndroidClient(Context context, MinervaServiceAndroidAdapter minervaServiceAndroidAdapter, String str, String str2) {
        this.context = context;
        this.mMinervaServiceAndroidAdapter = minervaServiceAndroidAdapter;
        this.region = str;
        this.deviceType = str2;
        this.mThrottleConfiguration = minervaServiceAndroidAdapter.getMinervaServiceManager().getConfigurationManager().getMetricsConfigurationHelper().getThrottleConfiguration();
        this.mValidationConfiguration = minervaServiceAndroidAdapter.getMinervaServiceManager().getConfigurationManager().getMetricsConfigurationHelper().getValidationConfiguration();
        this.mMetricEventThrottler = MetricEventThrottler.getInstance(minervaServiceAndroidAdapter);
    }

    public void record(MetricEvent metricEvent) {
        recordInternal(metricEvent, noOpCallback);
    }

    private void recordInternal(MetricEvent metricEvent, MetricRecordCallback metricRecordCallback) {
        if (metricEvent instanceof AggregatedMetricEvent) {
            ((AggregatedMetricEvent) metricEvent).convertAggregatedToKeyValuePairs();
        }
        if (!isMetricEventValid(metricEvent)) {
            metricRecordCallback.onError(MetricRecordStatus.INVALID_METRIC_EVENT, metricEvent);
            return;
        }
        try {
            if ((this.mThrottleConfiguration.getThrottleSwitch() == 1) && this.mMetricEventThrottler.shouldThrottleMetricEvent(metricEvent)) {
                reportKPI(KPIMetric.THROTTLE.getMetricName(), metricEvent.getMetricGroupId());
                Log.w(TAG, String.format("metricEvent was throttled. metricGroupId: %s, schemaId: %s", metricEvent.getMetricGroupId(), metricEvent.getSchemaId()));
                metricRecordCallback.onError(MetricRecordStatus.THROTTLED, metricEvent);
            } else {
                metricEvent.setClientTimestamp(Timestamp.now());
                this.mMinervaServiceAndroidAdapter.record(this.region, metricEvent.getMetricGroupId(), metricEvent.getSchemaId(), metricEvent.getMetricEventId().toString(), metricEvent.getClientTimestamp().getEpochMillis(), (int) TimeUnit.MILLISECONDS.toMinutes(metricEvent.getClientTimestamp().getTimeZoneOffset()), DataPointEnvelope.convertToEnvelopes(metricEvent.getKeyValuePairsAsDataPoints()));
                metricEvent.clear();
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception happens during metric event record: " + e);
        }
    }

    public void shutdown() {
        Log.i(TAG, "Shutting down the Minerva Client and write metrics from memory to disk.");
        this.mMinervaServiceAndroidAdapter.getPeriodicKPIReporter().shutdown();
        this.mMinervaServiceAndroidAdapter.shutdown();
    }

    private boolean isMetricEventValid(MetricEvent metricEvent) {
        try {
            int maxKeyValuePairCount = this.mValidationConfiguration.getMaxKeyValuePairCount();
            int maxKeySizeBytes = this.mValidationConfiguration.getMaxKeySizeBytes();
            int maxValueSizeBytes = this.mValidationConfiguration.getMaxValueSizeBytes();
            int maxMetricEventSizeBytes = this.mValidationConfiguration.getMaxMetricEventSizeBytes();
            if (metricEvent.getNumInvalidKeyValuePairs() > 0) {
                Log.e(TAG, String.format("Metric Event contains %d invalid key value pairs. ", Integer.valueOf(metricEvent.getNumInvalidKeyValuePairs()), metricEvent.getMetricGroupId(), metricEvent.getSchemaId()));
                reportKPI(KPIMetric.KEY_VALUE_PAIR_FAILED_VALIDATION.getMetricName(), metricEvent.getMetricGroupId());
                reportKPI(KPIMetric.API_VALIDATION.getMetricName(), metricEvent.getMetricGroupId());
                return false;
            }
            if (metricEvent.getKeyValuePairCount() == 0) {
                Log.e(TAG, String.format("Metric Event does not contain any key value pairs.", "metricGroup: %s, schemaId %s", metricEvent.getMetricGroupId(), metricEvent.getSchemaId()));
                reportKPI(KPIMetric.METRIC_EVENT_HAS_NO_KEY_VALUE_PAIR.getMetricName(), metricEvent.getMetricGroupId());
                reportKPI(KPIMetric.API_VALIDATION.getMetricName(), metricEvent.getMetricGroupId());
                return false;
            }
            if (metricEvent.getKeyValuePairCount() > maxKeyValuePairCount) {
                Log.e(TAG, String.format("Metric Event contains %d custom key value pairs, exceeded the limit %d. metricGroup: %s, schemaId %s", Integer.valueOf(metricEvent.getKeyValuePairCount()), Integer.valueOf(maxKeyValuePairCount), metricEvent.getMetricGroupId(), metricEvent.getSchemaId()));
                reportKPI(KPIMetric.KEY_VALUE_PAIR_COUNT_EXCEEDED_MAX.getMetricName(), metricEvent.getMetricGroupId());
                reportKPI(KPIMetric.API_VALIDATION.getMetricName(), metricEvent.getMetricGroupId());
                return false;
            }
            int length = 0;
            for (Map.Entry<String, TypedValue<?>> entry : metricEvent.getKeyValuePairs().entrySet()) {
                String key = entry.getKey();
                if (!key.startsWith(Attributes.PREDEFINED_ATTRIBUTE_PREFIX)) {
                    if (key.length() > maxKeySizeBytes) {
                        Log.e(TAG, String.format("Metric Event has key '%s' exceeding the maximum size %d. metricGroup: %s, schemaId %s", key, Integer.valueOf(maxKeySizeBytes), metricEvent.getMetricGroupId(), metricEvent.getSchemaId()));
                        reportKPI(KPIMetric.KEY_SIZE_EXCEEDED_MAX.getMetricName(), metricEvent.getMetricGroupId());
                        reportKPI(KPIMetric.API_VALIDATION.getMetricName(), metricEvent.getMetricGroupId());
                        return false;
                    }
                    TypedValue<?> value = entry.getValue();
                    if (value.getSizeInBytes() > maxValueSizeBytes) {
                        Log.e(TAG, String.format("Metric Event has key '%s', its value size is %d, exceeded the limit %d. metricGroup: %s, schemaId %s", key, Integer.valueOf(value.getSizeInBytes()), Integer.valueOf(maxValueSizeBytes), metricEvent.getMetricGroupId(), metricEvent.getSchemaId()));
                        reportKPI(KPIMetric.VALUE_SIZE_EXCEEDED_MAX.getMetricName(), metricEvent.getMetricGroupId());
                        reportKPI(KPIMetric.API_VALIDATION.getMetricName(), metricEvent.getMetricGroupId());
                        return false;
                    }
                    length += key.length() + value.getSizeInBytes();
                }
            }
            if (length <= maxMetricEventSizeBytes) {
                return true;
            }
            Log.e(TAG, String.format("Metric Event size is %d, exceeded the limit %d. metricGroup: %s, schemaId %s", Integer.valueOf(length), Integer.valueOf(maxMetricEventSizeBytes), metricEvent.getMetricGroupId(), metricEvent.getSchemaId()));
            reportKPI(KPIMetric.METRIC_EVENT_SIZE_EXCEEDED_MAX.getMetricName(), metricEvent.getMetricGroupId());
            reportKPI(KPIMetric.API_VALIDATION.getMetricName(), metricEvent.getMetricGroupId());
            return false;
        } catch (Exception e) {
            Log.w(TAG, "validation for metric event meet exception: " + e + "Drop the metric event (can't record)");
            return false;
        }
    }

    private void reportKPI(String str, String str2) {
        this.mMinervaServiceAndroidAdapter.getPeriodicKPIReporter().report(str, str2, 1L);
    }
}
