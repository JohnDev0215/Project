package org.chromium.device.sensors;

import J.N;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Handler;
import android.os.HandlerThread;
import defpackage.Ts;
import java.util.List;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class PlatformSensor implements SensorEventListener {
    public long a;
    public final Sensor b;
    public final int c;
    public final int d;
    public double e;
    public final PlatformSensorProvider f;

    public PlatformSensor(Sensor sensor, int i, PlatformSensorProvider platformSensorProvider) {
        this.d = i;
        this.f = platformSensorProvider;
        this.b = sensor;
        this.c = sensor.getMinDelay();
    }

    public static PlatformSensor a(int i, int i2, PlatformSensorProvider platformSensorProvider) {
        List<Sensor> sensorList = platformSensorProvider.a.getSensorList(i);
        if (sensorList.isEmpty()) {
            return null;
        }
        return new PlatformSensor(sensorList.get(0), i2, platformSensorProvider);
    }

    public void b(double d, double d2, double d3, double d4, double d5) {
        N.Mb4JvlL7(this.a, this, d, d2, d3, d4, d5);
    }

    public boolean checkSensorConfiguration(double d) {
        return this.c <= ((int) ((1.0d / d) * 1000000.0d));
    }

    public double getDefaultConfiguration() {
        return 5.0d;
    }

    public double getMaximumSupportedFrequency() {
        int i = this.c;
        return i == 0 ? getDefaultConfiguration() : 1.0d / (((double) i) * 1.0E-6d);
    }

    public int getReportingMode() {
        return this.b.getReportingMode() == 0 ? 1 : 0;
    }

    public void initPlatformSensorAndroid(long j) {
        this.a = j;
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        long j = this.a;
        if (j == 0) {
            Ts.f("PlatformSensor", "Should not get sensor events after PlatformSensorAndroid is destroyed.", new Object[0]);
            return;
        }
        float[] fArr = sensorEvent.values;
        if (fArr.length < this.d) {
            N.MrHXg7he(j, this);
            stopSensor();
            return;
        }
        double d = sensorEvent.timestamp * 1.0E-9d;
        int length = fArr.length;
        if (length == 1) {
            b(d, fArr[0], 0.0d, 0.0d, 0.0d);
            return;
        }
        if (length == 2) {
            b(d, fArr[0], fArr[1], 0.0d, 0.0d);
        } else if (length != 3) {
            b(d, fArr[0], fArr[1], fArr[2], fArr[3]);
        } else {
            b(d, fArr[0], fArr[1], fArr[2], 0.0d);
        }
    }

    public void sensorDestroyed() {
        stopSensor();
        this.a = 0L;
    }

    public boolean startSensor(double d) {
        double d2 = this.e;
        if (d2 == d) {
            return true;
        }
        if (d2 != 0.0d) {
            this.f.a.unregisterListener(this, this.b);
        }
        PlatformSensorProvider platformSensorProvider = this.f;
        synchronized (platformSensorProvider.d) {
            if (platformSensorProvider.d.isEmpty() && platformSensorProvider.b == null) {
                HandlerThread handlerThread = new HandlerThread("SensorsHandlerThread");
                platformSensorProvider.b = handlerThread;
                handlerThread.start();
                platformSensorProvider.c = new Handler(platformSensorProvider.b.getLooper());
            }
            platformSensorProvider.d.add(this);
        }
        boolean zRegisterListener = false;
        try {
            zRegisterListener = this.f.a.registerListener(this, this.b, (int) ((1.0d / d) * 1000000.0d), this.f.c);
        } catch (RuntimeException e) {
            Ts.f("PlatformSensor", "Failed to register sensor listener.", e);
        }
        if (zRegisterListener) {
            this.e = d;
            return zRegisterListener;
        }
        stopSensor();
        return zRegisterListener;
    }

    public void stopSensor() {
        HandlerThread handlerThread;
        if (this.e != 0.0d) {
            this.f.a.unregisterListener(this, this.b);
        }
        PlatformSensorProvider platformSensorProvider = this.f;
        synchronized (platformSensorProvider.d) {
            platformSensorProvider.d.remove(this);
            if (platformSensorProvider.d.isEmpty() && (handlerThread = platformSensorProvider.b) != null) {
                handlerThread.quitSafely();
                platformSensorProvider.b = null;
                platformSensorProvider.c = null;
            }
        }
        this.e = 0.0d;
    }
}
