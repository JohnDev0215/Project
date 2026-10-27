package io.sentry.android.core;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import io.sentry.C1457d;
import io.sentry.C1523x;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public final class TempSensorBreadcrumbsIntegration implements io.sentry.Z, Closeable, SensorEventListener {
    public final Context l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public io.sentry.E f16113m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public SentryAndroidOptions f16114n;
    public SensorManager o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f16115p = false;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Object f16116q = new Object();

    public TempSensorBreadcrumbsIntegration(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.l = applicationContext != null ? applicationContext : context;
    }

    public final void b(F1 f12) {
        try {
            SensorManager sensorManager = (SensorManager) this.l.getSystemService("sensor");
            this.o = sensorManager;
            if (sensorManager != null) {
                Sensor defaultSensor = sensorManager.getDefaultSensor(13);
                if (defaultSensor != null) {
                    this.o.registerListener(this, defaultSensor, 3);
                    f12.getLogger().k(EnumC1495p1.DEBUG, "TempSensorBreadcrumbsIntegration installed.", new Object[0]);
                    n7.z.c("TempSensorBreadcrumbs");
                } else {
                    f12.getLogger().k(EnumC1495p1.INFO, "TYPE_AMBIENT_TEMPERATURE is not available.", new Object[0]);
                }
            } else {
                f12.getLogger().k(EnumC1495p1.INFO, "SENSOR_SERVICE is not available.", new Object[0]);
            }
        } catch (Throwable th) {
            f12.getLogger().p(EnumC1495p1.ERROR, th, "Failed to init. the SENSOR_SERVICE.", new Object[0]);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        synchronized (this.f16116q) {
            this.f16115p = true;
        }
        SensorManager sensorManager = this.o;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
            this.o = null;
            SentryAndroidOptions sentryAndroidOptions = this.f16114n;
            if (sentryAndroidOptions != null) {
                sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "TempSensorBreadcrumbsIntegration removed.", new Object[0]);
            }
        }
    }

    @Override // io.sentry.Z
    public final void k(F1 f12) {
        this.f16113m = io.sentry.E.f15834a;
        SentryAndroidOptions sentryAndroidOptions = f12 instanceof SentryAndroidOptions ? (SentryAndroidOptions) f12 : null;
        h6.e.q(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16114n = sentryAndroidOptions;
        sentryAndroidOptions.getLogger().k(EnumC1495p1.DEBUG, "enableSystemEventsBreadcrumbs enabled: %s", Boolean.valueOf(this.f16114n.isEnableSystemEventBreadcrumbs()));
        if (this.f16114n.isEnableSystemEventBreadcrumbs()) {
            try {
                f12.getExecutorService().submit(new r(this, 4, f12));
            } catch (Throwable th) {
                f12.getLogger().s(EnumC1495p1.DEBUG, "Failed to start TempSensorBreadcrumbsIntegration on executor thread.", th);
            }
        }
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i7) {
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent) {
        float[] fArr = sensorEvent.values;
        if (fArr == null || fArr.length == 0 || fArr[0] == 0.0f || this.f16113m == null) {
            return;
        }
        C1457d c1457d = new C1457d();
        c1457d.o = "system";
        c1457d.f16515q = "device.event";
        c1457d.c("TYPE_AMBIENT_TEMPERATURE", "action");
        c1457d.c(Integer.valueOf(sensorEvent.accuracy), "accuracy");
        c1457d.c(Long.valueOf(sensorEvent.timestamp), "timestamp");
        c1457d.f16517s = EnumC1495p1.INFO;
        c1457d.c(Float.valueOf(sensorEvent.values[0]), "degree");
        C1523x c1523x = new C1523x();
        c1523x.c(sensorEvent, "android:sensorEvent");
        this.f16113m.k(c1457d, c1523x);
    }
}
