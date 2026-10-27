package com.amazon.device.ads;

import com.amazon.device.ads.Metrics;
import java.util.Vector;

/* JADX INFO: loaded from: classes2.dex */
class MetricsCollector {
    private static final String LOGTAG = "MetricsCollector";
    private final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    protected Vector<MetricHit> metricHits = new Vector<>(60);

    public void incrementMetric(Metrics.MetricType metricType) {
        this.logger.d("METRIC Increment " + metricType.toString());
        this.metricHits.add(new MetricHitIncrement(metricType, 1));
    }

    public void startMetricInMillisecondsFromNanoseconds(Metrics.MetricType metricType, long j) {
        this.logger.d("METRIC Start " + metricType.toString());
        this.metricHits.add(new MetricHitStartTime(metricType, NumberUtils.convertToMillisecondsFromNanoseconds(j)));
    }

    public void startMetric(Metrics.MetricType metricType) {
        startMetricInMillisecondsFromNanoseconds(metricType, System.nanoTime());
    }

    public void stopMetricInMillisecondsFromNanoseconds(Metrics.MetricType metricType, long j) {
        this.logger.d("METRIC Stop " + metricType.toString());
        this.metricHits.add(new MetricHitStopTime(metricType, NumberUtils.convertToMillisecondsFromNanoseconds(j)));
    }

    public void stopMetric(Metrics.MetricType metricType) {
        stopMetricInMillisecondsFromNanoseconds(metricType, System.nanoTime());
    }

    static class MetricHit {
        public final Metrics.MetricType metric;

        public MetricHit(Metrics.MetricType metricType) {
            this.metric = metricType;
        }
    }

    static class MetricHitStartTime extends MetricHit {
        public final long startTime;

        public MetricHitStartTime(Metrics.MetricType metricType, long j) {
            super(metricType);
            this.startTime = j;
        }
    }

    static class MetricHitStopTime extends MetricHit {
        public final long stopTime;

        public MetricHitStopTime(Metrics.MetricType metricType, long j) {
            super(metricType);
            this.stopTime = j;
        }
    }

    static class MetricHitIncrement extends MetricHit {
        public final int increment;

        public MetricHitIncrement(Metrics.MetricType metricType, int i) {
            super(metricType);
            this.increment = i;
        }
    }
}
