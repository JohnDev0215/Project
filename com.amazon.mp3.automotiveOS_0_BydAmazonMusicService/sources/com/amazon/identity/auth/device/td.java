package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.client.metrics.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.MetricEvent;
import com.amazon.client.metrics.PeriodicMetricReporter;
import com.amazon.client.metrics.PeriodicMetricReporterImpl;
import com.amazon.identity.auth.device.de;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class td extends ae {
    public static td b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f586a;

    /* JADX INFO: compiled from: DCP */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final PeriodicMetricReporter f587a;
        public final MetricEvent b;

        public a(Context context) {
            PeriodicMetricReporterImpl periodicMetricReporterImpl = new PeriodicMetricReporterImpl(AndroidMetricsFactoryImpl.getInstance(context), context.getPackageName(), "MAPAndroidPeriodicMetric");
            this.f587a = periodicMetricReporterImpl;
            periodicMetricReporterImpl.startRecordingPeriodically(5L, TimeUnit.MINUTES);
            this.b = periodicMetricReporterImpl.getMetricEvent();
        }
    }

    public td(Context context) {
        a aVar = new a(context);
        this.f586a = aVar;
        aVar.b.incrementCounter("MAPAPP_DCMFireOSPeriodic_SUPPORTED", 1.0d);
        ga.c("FireOSPeriodicMetricsCollector", "Successfully create FireOSPeriodicMetricsCollector.");
    }

    @Override // com.amazon.identity.auth.device.ae
    public de a(String str) {
        a aVar;
        MetricEvent metricEvent;
        return (!yd.d() || (aVar = this.f586a) == null || (metricEvent = aVar.b) == null) ? new de.b() : new vd(metricEvent, str);
    }

    @Override // com.amazon.identity.auth.device.ae
    public void b(String str) {
        a aVar;
        MetricEvent metricEvent;
        if (!yd.d() || (aVar = this.f586a) == null || (metricEvent = aVar.b) == null) {
            return;
        }
        metricEvent.incrementCounter(str, 1.0d);
    }
}
