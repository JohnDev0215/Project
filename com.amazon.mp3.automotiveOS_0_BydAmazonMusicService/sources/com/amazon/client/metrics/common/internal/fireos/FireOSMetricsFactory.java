package com.amazon.client.metrics.common.internal.fireos;

import android.content.Context;
import com.amazon.client.metrics.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.common.Channel;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricEventConverter;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.client.metrics.common.Priority;

/* JADX INFO: loaded from: classes2.dex */
public class FireOSMetricsFactory implements MetricsFactory {
    private static FireOSMetricsFactory sFireOSMetricsFactory;
    private final com.amazon.client.metrics.MetricsFactory mDelegateFirstPartyMetricsFactory;

    private FireOSMetricsFactory(Context context) {
        if (context == null) {
            throw new NullPointerException("Context may not be null");
        }
        this.mDelegateFirstPartyMetricsFactory = AndroidMetricsFactoryImpl.getInstance(context);
    }

    public static synchronized FireOSMetricsFactory getInstance(Context context) {
        if (sFireOSMetricsFactory == null) {
            sFireOSMetricsFactory = new FireOSMetricsFactory(context);
        }
        return sFireOSMetricsFactory;
    }

    @Override // com.amazon.client.metrics.common.MetricsFactory
    public MetricEvent createMetricEvent(String str, String str2) {
        return MetricEventConverter.convertMetricEvent_fromFirstPartyToCommon(this.mDelegateFirstPartyMetricsFactory.createMetricEvent(str, str2));
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
        this.mDelegateFirstPartyMetricsFactory.record(MetricEventConverter.convertMetricEvent_fromCommonToFirstParty(metricEvent), com.amazon.client.metrics.Priority.valueOf(priority.name()), com.amazon.client.metrics.Channel.valueOf(channel.name()));
    }
}
