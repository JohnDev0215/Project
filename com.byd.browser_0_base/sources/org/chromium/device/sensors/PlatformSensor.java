package org.chromium.device.sensors;

import J.N;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.HandlerThread;
import android.util.Log;
import java.util.HashSet;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class PlatformSensor implements SensorEventListener {
    public static final HashSet h = new HashSet();
    public final Object a = new Object();
    public long b;
    public final Sensor c;
    public final int d;
    public final int e;
    public double f;
    public final PlatformSensorProvider g;

    public PlatformSensor(Sensor sensor, int i, PlatformSensorProvider platformSensorProvider, long j) {
        this.e = i;
        this.g = platformSensorProvider;
        this.c = sensor;
        this.b = j;
        this.d = sensor.getMinDelay();
        h.add(Integer.valueOf(sensor.getType()));
    }

    @CalledByNative
    public static PlatformSensor create(PlatformSensorProvider platformSensorProvider, int i, long j) {
        int i2;
        SensorManager sensorManager = platformSensorProvider.a;
        if (sensorManager == null) {
            return null;
        }
        int i3 = 1;
        if (i != 0) {
            if (i == 9) {
                i3 = 11;
            } else if (i != 11) {
                if (i != 2) {
                    if (i == 3) {
                        i3 = 10;
                    } else if (i == 4) {
                        i3 = 9;
                    } else if (i == 5) {
                        i3 = 4;
                    } else {
                        if (i != 6) {
                            return null;
                        }
                        i3 = 2;
                    }
                }
                i2 = 3;
            } else {
                i3 = 15;
            }
            i2 = 4;
        } else {
            i2 = 1;
            i3 = 5;
        }
        List<Sensor> sensorList = sensorManager.getSensorList(i3);
        if (sensorList.isEmpty()) {
            return null;
        }
        return new PlatformSensor(sensorList.get(0), i2, platformSensorProvider, j);
    }

    public final void a() {
        if (this.f == 0.0d) {
            return;
        }
        this.g.a.unregisterListener(this, this.c);
    }

    public final void b(double d, double d2, double d3, double d4, double d5) {
        N.Mb4JvlL7(this.b, this, d, d2, d3, d4, d5);
    }

    @CalledByNative
    public boolean checkSensorConfiguration(double d) {
        return this.d <= ((int) ((1.0d / d) * 1000000.0d));
    }

    @CalledByNative
    public double getDefaultConfiguration() {
        return 5.0d;
    }

    @CalledByNative
    public double getMaximumSupportedFrequency() {
        int i = this.d;
        return i == 0 ? getDefaultConfiguration() : 1.0d / (((double) i) * 1.0E-6d);
    }

    @CalledByNative
    public int getReportingMode() {
        return this.c.getReportingMode() == 0 ? 1 : 0;
    }

    @Override // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent) {
        synchronized (this.a) {
            long j = this.b;
            if (j == 0) {
                Log.w("cr_PlatformSensor", "Should not get sensor events after PlatformSensorAndroid is destroyed.");
                return;
            }
            float[] fArr = sensorEvent.values;
            if (fArr.length < this.e) {
                if (j != 0) {
                    N.MrHXg7he(j, this);
                }
                stopSensor();
                return;
            }
            double d = sensorEvent.timestamp * 1.0E-9d;
            int length = fArr.length;
            if (length == 1) {
                b(d, fArr[0], 0.0d, 0.0d, 0.0d);
            } else if (length == 2) {
                b(d, fArr[0], fArr[1], 0.0d, 0.0d);
            } else if (length != 3) {
                b(d, fArr[0], fArr[1], fArr[2], fArr[3]);
            } else {
                b(d, fArr[0], fArr[1], fArr[2], 0.0d);
            }
        }
    }

    @CalledByNative
    public void sensorDestroyed() {
        h.remove(Integer.valueOf(this.c.getType()));
        if (!N.MVi$blz$("AsyncSensorCalls")) {
            stopSensor();
        }
        synchronized (this.a) {
            this.b = 0L;
        }
    }

    @CalledByNative
    public boolean startSensor(double d) {
        boolean zRegisterListener;
        if (this.f == d) {
            return true;
        }
        a();
        PlatformSensorProvider platformSensorProvider = this.g;
        platformSensorProvider.a(this);
        try {
            zRegisterListener = platformSensorProvider.a.registerListener(this, this.c, (int) ((1.0d / d) * 1000000.0d), platformSensorProvider.c);
        } catch (RuntimeException e) {
            Log.w("cr_PlatformSensor", "Failed to register sensor listener.", e);
            zRegisterListener = false;
        }
        if (zRegisterListener) {
            this.f = d;
            return zRegisterListener;
        }
        stopSensor();
        return zRegisterListener;
    }

    @CalledByNative
    public void startSensor2(double d) {
        boolean zRegisterListener;
        if (this.f == d) {
            return;
        }
        a();
        this.g.a(this);
        try {
            PlatformSensorProvider platformSensorProvider = this.g;
            zRegisterListener = platformSensorProvider.a.registerListener(this, this.c, (int) ((1.0d / d) * 1000000.0d), platformSensorProvider.c);
        } catch (RuntimeException e) {
            Log.w("cr_PlatformSensor", "Failed to register sensor listener.", e);
            zRegisterListener = false;
        }
        if (zRegisterListener) {
            this.f = d;
            return;
        }
        stopSensor();
        synchronized (this.a) {
            long j = this.b;
            if (j != 0) {
                N.MrHXg7he(j, this);
            }
        }
    }

    @CalledByNative
    public void stopSensor() {
        HandlerThread handlerThread;
        a();
        PlatformSensorProvider platformSensorProvider = this.g;
        synchronized (platformSensorProvider.d) {
            platformSensorProvider.d.remove(this);
            if (platformSensorProvider.d.isEmpty() && (handlerThread = platformSensorProvider.b) != null) {
                handlerThread.quitSafely();
                platformSensorProvider.b = null;
                platformSensorProvider.c = null;
            }
        }
        this.f = 0.0d;
    }

    @Override // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor, int i) {
    }
}
