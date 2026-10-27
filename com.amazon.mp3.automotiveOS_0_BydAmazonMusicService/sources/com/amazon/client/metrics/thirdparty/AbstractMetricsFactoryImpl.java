package com.amazon.client.metrics.thirdparty;

import android.os.RemoteException;
import android.util.Log;
import com.amazon.client.metrics.thirdparty.clickstream.internal.ClickStreamData;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractMetricsFactoryImpl extends BaseMetricsFactoryImpl {
    protected static MetricsFactory sMetricsFactory;
    protected static IMetricsService sMetricsService;

    protected abstract IMetricsService getService();

    @Override // com.amazon.client.metrics.thirdparty.MetricsFactory
    public void record(MetricEvent metricEvent, Priority priority, Channel channel) {
        List<DataPoint> asDataPoints;
        if (metricEvent == null) {
            Log.d("AbstractMetricsFactoryImpl", "record : Null metric event");
            return;
        }
        if (Priority.RESERVED_FOR_LOCATION_SERVICE.equals(priority) || Priority.RESERVED_FOR_NON_ANONYMOUS_METRICS.equals(priority)) {
            throw new IllegalArgumentException("record: Using Deprecated Priority. This will not work as intended. Consider using Channel instead");
        }
        if (metricEvent instanceof ConcurrentMetricEvent) {
            asDataPoints = ((ConcurrentMetricEvent) metricEvent).getAsDataPointsAndClear();
        } else {
            asDataPoints = metricEvent.getAsDataPoints();
            metricEvent.clear();
        }
        if (asDataPoints == null || asDataPoints.isEmpty()) {
            Log.d("AbstractMetricsFactoryImpl", "record : No valid data points in metrics event");
            return;
        }
        if (Channel.NON_ANONYMOUS.equals(channel)) {
            metricEvent.setAnonymous(false);
            String nonAnonymousCustomerId = metricEvent.getNonAnonymousCustomerId();
            if (nonAnonymousCustomerId != null) {
                asDataPoints.add(new DataPoint(ClickStreamData.NON_ANONYMOUS_CUSTOMER_ID.getName(), nonAnonymousCustomerId, 1, DataPointType.DV));
            }
            String nonAnonymousSessionId = metricEvent.getNonAnonymousSessionId();
            if (nonAnonymousSessionId != null) {
                asDataPoints.add(new DataPoint(ClickStreamData.NON_ANONYMOUS_SESSION_ID.getName(), nonAnonymousSessionId, 1, DataPointType.DV));
            }
        } else {
            metricEvent.setNonAnonymousCustomerId(null);
            metricEvent.setNonAnonymousSessionId(null);
            metricEvent.setAnonymous(true);
        }
        asDataPoints.add(new DataPoint(ClickStreamData.ANONYMOUS.getName(), String.valueOf(metricEvent.getAnonymous()), 1, DataPointType.DV));
        try {
            getService().record(priority.ordinal(), channel.ordinal(), metricEvent.getProgram(), metricEvent.getSource(), System.currentTimeMillis(), DataPointEnvelope.convertToEnvelopes(asDataPoints));
        } catch (RemoteException e) {
            Log.e("AbstractMetricsFactoryImpl", "record : RemoteException caught while trying to record metric: ", e);
        } catch (SecurityException e2) {
            Log.e("AbstractMetricsFactoryImpl", "record : SecurityException caught while trying to record metric: ", e2);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.BaseMetricsFactoryImpl
    public void record(MetricEvent metricEvent, Priority priority) {
        if (Priority.RESERVED_FOR_LOCATION_SERVICE.equals(priority)) {
            record(metricEvent, Priority.NORMAL, Channel.LOCATION);
        } else if (Priority.RESERVED_FOR_NON_ANONYMOUS_METRICS.equals(priority)) {
            record(metricEvent, Priority.NORMAL, Channel.NON_ANONYMOUS);
        } else {
            record(metricEvent, priority, Channel.ANONYMOUS);
        }
    }
}
