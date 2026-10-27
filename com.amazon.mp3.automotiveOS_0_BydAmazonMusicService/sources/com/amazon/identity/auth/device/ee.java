package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.client.metrics.thirdparty.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.thirdparty.MetricEvent;
import com.amazon.client.metrics.thirdparty.PeriodicMetricReporter;
import com.amazon.client.metrics.thirdparty.PeriodicMetricReporterImpl;
import com.amazon.identity.auth.device.de;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class ee extends ae {
    public static ee c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f302a;
    public Context b;

    /* JADX INFO: compiled from: DCP */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final PeriodicMetricReporter f303a;
        public final MetricEvent b;

        public a(Context context) {
            PeriodicMetricReporterImpl periodicMetricReporterImpl = new PeriodicMetricReporterImpl(AndroidMetricsFactoryImpl.getInstance(context), context.getPackageName(), "MAPAndroidPeriodicMetric");
            this.f303a = periodicMetricReporterImpl;
            periodicMetricReporterImpl.startRecordingPeriodically(5L, TimeUnit.MINUTES);
            this.b = periodicMetricReporterImpl.getMetricEvent();
        }
    }

    public ee(Context context) {
        this.b = context;
        a aVar = new a(context);
        this.f302a = aVar;
        aVar.b.incrementCounter("MAPAPP_DCMThirdPartyPeriodic_SUPPORTED", 1.0d);
        ga.c("ThirdPartyPeriodicMetricsCollector", "Successfully create ThirdPartyPeriodicMetricsCollector");
    }

    @Override // com.amazon.identity.auth.device.ae
    public de a(String str) {
        a aVar;
        MetricEvent metricEvent;
        return (!yd.d(this.b) || (aVar = this.f302a) == null || (metricEvent = aVar.b) == null) ? new de.b() : new ge(metricEvent, str);
    }

    @Override // com.amazon.identity.auth.device.ae
    public void b(String str) {
        a aVar;
        MetricEvent metricEvent;
        if (!yd.d(this.b) || (aVar = this.f302a) == null || (metricEvent = aVar.b) == null) {
            return;
        }
        metricEvent.incrementCounter(str, 1.0d);
    }
}
