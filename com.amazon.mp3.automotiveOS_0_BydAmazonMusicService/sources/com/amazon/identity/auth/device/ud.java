package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.client.metrics.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.MetricEvent;
import com.amazon.client.metrics.MetricsFactory;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class ud implements ce {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MetricsFactory f616a;
    public final MetricEvent b;

    public ud(Context context, String str, String str2) {
        MetricsFactory androidMetricsFactoryImpl = AndroidMetricsFactoryImpl.getInstance(context);
        this.f616a = androidMetricsFactoryImpl;
        MetricEvent metricEventCreateConcurrentMetricEvent = androidMetricsFactoryImpl.createConcurrentMetricEvent(str, str2);
        this.b = metricEventCreateConcurrentMetricEvent;
        metricEventCreateConcurrentMetricEvent.addCounter("MAPAPP_DCMFireOS_SUPPORTED", 1.0d);
        androidMetricsFactoryImpl.record(metricEventCreateConcurrentMetricEvent);
        metricEventCreateConcurrentMetricEvent.clear();
    }

    @Override // com.amazon.identity.auth.device.ce
    public void a(String str, String str2, String... strArr) {
        MetricEvent metricEventCreateMetricEvent = this.f616a.createMetricEvent(str, str2);
        for (String str3 : strArr) {
            metricEventCreateMetricEvent.incrementCounter(str3, 1.0d);
        }
        this.f616a.record(metricEventCreateMetricEvent);
    }

    @Override // com.amazon.identity.auth.device.ce
    public de b(String str) {
        return new vd(this.b, str);
    }

    @Override // com.amazon.identity.auth.device.ce
    public void a(String str, String str2, long j) {
        MetricEvent metricEventCreateMetricEvent = this.f616a.createMetricEvent(str, str2);
        metricEventCreateMetricEvent.addTimer(str2, j);
        this.f616a.record(metricEventCreateMetricEvent);
    }

    @Override // com.amazon.identity.auth.device.ce
    public void a(String str, String... strArr) {
        this.b.addCounter(str, 1.0d);
        if (strArr != null) {
            for (String str2 : strArr) {
                this.b.addCounter(str + ":" + str2, 1.0d);
            }
        }
    }

    @Override // com.amazon.identity.auth.device.ce
    public void a(String str) {
        this.b.incrementCounter(str, 1.0d);
    }

    @Override // com.amazon.identity.auth.device.ce
    public void a(String str, double d) {
        this.b.incrementCounter(str, Math.round(d * 10.0d) / 10.0d);
    }

    @Override // com.amazon.identity.auth.device.ce
    public void a() {
        this.f616a.record(this.b);
        this.b.clear();
    }
}
