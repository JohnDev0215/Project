package com.amazon.client.metrics.common.internal.android;

import android.content.Context;
import com.amazon.client.metrics.common.Channel;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricEventConverter;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.client.metrics.common.Priority;
import com.amazon.client.metrics.thirdparty.AndroidMetricsFactoryImpl;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidMetricsFactory implements MetricsFactory {
    private static AndroidMetricsFactory sAndroidMetricsFactory;
    private final com.amazon.client.metrics.thirdparty.MetricsFactory mDelegateThirdPartyMetricsFactory;

    private AndroidMetricsFactory(Context context) {
        if (context == null) {
            throw new NullPointerException("Context may not be null");
        }
        this.mDelegateThirdPartyMetricsFactory = AndroidMetricsFactoryImpl.getInstance(context);
    }

    public static synchronized AndroidMetricsFactory getInstance(Context context) {
        if (sAndroidMetricsFactory == null) {
            sAndroidMetricsFactory = new AndroidMetricsFactory(context);
        }
        return sAndroidMetricsFactory;
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createMetricEvent(String str, String str2) {
        return MetricEventConverter.convertMetricEvent_fromThirdPartyToCommon(this.mDelegateThirdPartyMetricsFactory.createMetricEvent(str, str2));
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public void record(MetricEvent metricEvent) {
        record(metricEvent, Priority.NORMAL, Channel.ANONYMOUS);
    }

    public void record(MetricEvent metricEvent, Priority priority, Channel channel) {
        if (Priority.RESERVED_FOR_NON_ANONYMOUS_METRICS.equals(priority)) {
            priority = Priority.NORMAL;
            channel = Channel.NON_ANONYMOUS;
        } else if (Priority.RESERVED_FOR_LOCATION_SERVICE.equals(priority)) {
            priority = Priority.NORMAL;
            channel = Channel.LOCATION;
        }
        this.mDelegateThirdPartyMetricsFactory.record(MetricEventConverter.convertMetricEvent_fromCommonToThirdParty(metricEvent), com.amazon.client.metrics.thirdparty.Priority.valueOf(priority.name()), com.amazon.client.metrics.thirdparty.Channel.valueOf(channel.name()));
    }
}
