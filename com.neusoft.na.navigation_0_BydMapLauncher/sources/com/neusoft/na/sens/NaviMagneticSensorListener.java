package com.neusoft.na.sens;

import android.car.Car;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import com.neusoft.na.sens.jni.OneCoreSenJni;

/* JADX INFO: loaded from: classes2.dex */
public class NaviMagneticSensorListener implements SensorEventListener {
    private SensorManager m_SensorManager;
    private Context mContext = null;
    float[] values = new float[3];

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public void registerFilter(Context context, int i) {
        this.mContext = context;
        SensorManager sensorManager = (SensorManager) context.getSystemService(Car.SENSOR_SERVICE);
        this.m_SensorManager = sensorManager;
        Sensor defaultSensor = sensorManager.getDefaultSensor(2);
        if (defaultSensor == null) {
            Log.d("NA_SENS", "sensor matching of requested type ( 2 ) not exists or have not permissions.");
        } else {
            if (this.m_SensorManager.registerListener(this, defaultSensor, i)) {
                return;
            }
            Log.d("NA_SENS", "the sensor is not supported: " + defaultSensor.getType());
        }
    }

    public void unregisterFilter() {
        this.m_SensorManager.unregisterListener(this);
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        this.values[0] = sensorEvent.values[0];
        this.values[1] = sensorEvent.values[1];
        this.values[2] = sensorEvent.values[2];
        OneCoreSenJni.getInstance().notifyChangeMagnetic(this.values, 0L);
    }

    public float[] getNaviMagneticSensor() {
        return this.values;
    }
}
