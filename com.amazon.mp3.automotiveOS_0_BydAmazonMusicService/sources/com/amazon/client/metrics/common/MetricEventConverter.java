package com.amazon.client.metrics.common;

import com.amazon.client.metrics.common.internal.android.AndroidMetricEvent;
import com.amazon.client.metrics.common.internal.android.AndroidNullMetricEvent;
import com.amazon.client.metrics.common.internal.fireos.FireOSMetricEvent;
import com.amazon.client.metrics.common.internal.fireos.FireOSNullMetricEvent;

/* JADX INFO: loaded from: classes2.dex */
public class MetricEventConverter {
    public static MetricEvent convertMetricEvent_fromFirstPartyToCommon(com.amazon.client.metrics.MetricEvent metricEvent) {
        if (metricEvent == null) {
            return null;
        }
        return new FireOSMetricEvent(metricEvent);
    }

    public static com.amazon.client.metrics.NullMetricEvent convertNullMetricEvent_fromCommonToFirstParty(NullMetricEvent nullMetricEvent) {
        if (nullMetricEvent == null) {
            return null;
        }
        MetricEvent delegateMetricEvent = nullMetricEvent.getDelegateMetricEvent();
        if (delegateMetricEvent instanceof FireOSNullMetricEvent) {
            return ((FireOSNullMetricEvent) delegateMetricEvent).getDelegateMetricEvent();
        }
        return null;
    }

    public static com.amazon.client.metrics.MetricEvent convertMetricEvent_fromCommonToFirstParty(MetricEvent metricEvent) {
        if (metricEvent == null) {
            return null;
        }
        if (metricEvent instanceof FireOSMetricEvent) {
            return ((FireOSMetricEvent) metricEvent).getDelegateMetricEvent();
        }
        if (metricEvent instanceof NullMetricEvent) {
            return convertNullMetricEvent_fromCommonToFirstParty((NullMetricEvent) metricEvent);
        }
        return null;
    }

    public static MetricEvent convertMetricEvent_fromThirdPartyToCommon(com.amazon.client.metrics.thirdparty.MetricEvent metricEvent) {
        if (metricEvent == null) {
            return null;
        }
        return new AndroidMetricEvent(metricEvent);
    }

    public static com.amazon.client.metrics.thirdparty.NullMetricEvent convertNullMetricEvent_fromCommonToThirdParty(NullMetricEvent nullMetricEvent) {
        if (nullMetricEvent == null) {
            return null;
        }
        MetricEvent delegateMetricEvent = nullMetricEvent.getDelegateMetricEvent();
        if (delegateMetricEvent instanceof AndroidNullMetricEvent) {
            return ((AndroidNullMetricEvent) delegateMetricEvent).getDelegateMetricEvent();
        }
        return null;
    }

    public static com.amazon.client.metrics.thirdparty.MetricEvent convertMetricEvent_fromCommonToThirdParty(MetricEvent metricEvent) {
        if (metricEvent == null) {
            return null;
        }
        if (metricEvent instanceof AndroidMetricEvent) {
            return ((AndroidMetricEvent) metricEvent).getDelegateMetricEvent();
        }
        if (metricEvent instanceof NullMetricEvent) {
            return convertNullMetricEvent_fromCommonToThirdParty((NullMetricEvent) metricEvent);
        }
        return null;
    }
}
