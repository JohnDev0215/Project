package com.amazon.identity.auth.device;

import android.text.TextUtils;
import com.amazon.client.metrics.MetricEvent;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class vd extends de {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MetricEvent f640a;
    public String b;
    public long c = -1;
    public long d = -1;
    public boolean e = false;

    public vd(MetricEvent metricEvent, String str) {
        this.f640a = metricEvent;
        this.b = str;
    }

    @Override // com.amazon.identity.auth.device.de
    public void a() {
        this.e = true;
    }

    @Override // com.amazon.identity.auth.device.de
    public void b() {
        this.c = System.nanoTime();
    }

    @Override // com.amazon.identity.auth.device.de
    public double c() {
        if (TextUtils.isEmpty(this.b)) {
            ga.a("FireOSPlatformDCPMetricsTimer");
            return -1.0d;
        }
        if (this.e) {
            ga.a("FireOSPlatformDCPMetricsTimer");
            return -1.0d;
        }
        long j = this.c;
        if (j < 0) {
            ga.a("FireOSPlatformDCPMetricsTimer");
            return -1.0d;
        }
        long j2 = this.d;
        long jNanoTime = j2 > 0 ? j2 - j : System.nanoTime() - this.c;
        this.c = -1L;
        this.d = -1L;
        double d = jNanoTime / 1000000.0d;
        this.f640a.addTimer(this.b, d);
        return d;
    }

    @Override // com.amazon.identity.auth.device.de
    public double d() {
        double dC = c();
        this.e = true;
        return dC;
    }

    @Override // com.amazon.identity.auth.device.de
    public void e() {
        this.d = System.nanoTime();
    }

    @Override // com.amazon.identity.auth.device.de
    public void a(String str) {
        this.b = str;
    }

    @Override // com.amazon.identity.auth.device.de
    public void a(long j) {
        if (TextUtils.isEmpty(this.b)) {
            ga.a("FireOSPlatformDCPMetricsTimer");
        } else {
            if (this.e) {
                return;
            }
            this.f640a.addTimer(this.b, j);
        }
    }
}
