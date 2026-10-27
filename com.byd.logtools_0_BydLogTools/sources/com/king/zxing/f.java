package com.king.zxing;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.preference.PreferenceManager;
import com.king.zxing.camera.FrontLightMode;

/* JADX INFO: compiled from: AmbientLightManager.java */
/* JADX INFO: loaded from: classes.dex */
final class f implements SensorEventListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float f4593a = 45.0f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float f4594b = 100.0f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f4595c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private com.king.zxing.camera.d f4596d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Sensor f4597e;

    f(Context context) {
        this.f4595c = context;
    }

    public void a(float f2) {
        this.f4594b = f2;
    }

    public void b(float f2) {
        this.f4593a = f2;
    }

    void c(com.king.zxing.camera.d dVar) {
        this.f4596d = dVar;
        if (FrontLightMode.readPref(PreferenceManager.getDefaultSharedPreferences(this.f4595c)) == FrontLightMode.AUTO) {
            SensorManager sensorManager = (SensorManager) this.f4595c.getSystemService("sensor");
            Sensor defaultSensor = sensorManager.getDefaultSensor(5);
            this.f4597e = defaultSensor;
            if (defaultSensor != null) {
                sensorManager.registerListener(this, defaultSensor, 3);
            }
        }
    }

    void d() {
        if (this.f4597e != null) {
            ((SensorManager) this.f4595c.getSystemService("sensor")).unregisterListener(this);
            this.f4596d = null;
            this.f4597e = null;
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        float f2 = sensorEvent.values[0];
        com.king.zxing.camera.d dVar = this.f4596d;
        if (dVar != null) {
            if (f2 <= this.f4593a) {
                dVar.k(true, f2);
            } else if (f2 >= this.f4594b) {
                dVar.k(false, f2);
            }
        }
    }
}
