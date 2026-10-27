package com.visualon.OSMPSensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes7.dex */
public class DeviceSensorLooper implements SensorEventProvider {
    private static final int[] INPUT_SENSORS = {1, 4};
    private boolean mIsRunning;
    private final ArrayList<SensorEventListener> mRegisteredListeners = new ArrayList<>();
    private SensorEventListener mSensorEventListener;
    private Looper mSensorLooper;
    private SensorManager mSensorManager;

    public DeviceSensorLooper(SensorManager sensorManager) {
        this.mSensorManager = sensorManager;
    }

    @Override // com.visualon.OSMPSensor.SensorEventProvider
    public void start() {
        if (this.mIsRunning) {
            return;
        }
        this.mSensorEventListener = new SensorEventListener() { // from class: com.visualon.OSMPSensor.DeviceSensorLooper.1
            @Override // android.hardware.SensorEventListener
            public void onSensorChanged(SensorEvent sensorEvent) {
                for (SensorEventListener sensorEventListener : DeviceSensorLooper.this.mRegisteredListeners) {
                    synchronized (sensorEventListener) {
                        sensorEventListener.onSensorChanged(sensorEvent);
                    }
                }
            }

            @Override // android.hardware.SensorEventListener
            public void onAccuracyChanged(Sensor sensor, int i) {
                for (SensorEventListener sensorEventListener : DeviceSensorLooper.this.mRegisteredListeners) {
                    synchronized (sensorEventListener) {
                        sensorEventListener.onAccuracyChanged(sensor, i);
                    }
                }
            }
        };
        HandlerThread handlerThread = new HandlerThread("sensor") { // from class: com.visualon.OSMPSensor.DeviceSensorLooper.2
            @Override // android.os.HandlerThread
            protected void onLooperPrepared() {
                Handler handler = new Handler(Looper.myLooper());
                for (int i : DeviceSensorLooper.INPUT_SENSORS) {
                    DeviceSensorLooper.this.mSensorManager.registerListener(DeviceSensorLooper.this.mSensorEventListener, DeviceSensorLooper.this.mSensorManager.getDefaultSensor(i), 0, handler);
                }
            }
        };
        handlerThread.start();
        this.mSensorLooper = handlerThread.getLooper();
        this.mIsRunning = true;
    }

    @Override // com.visualon.OSMPSensor.SensorEventProvider
    public void stop() {
        if (this.mIsRunning) {
            this.mSensorManager.unregisterListener(this.mSensorEventListener);
            this.mSensorEventListener = null;
            this.mSensorLooper.quit();
            this.mSensorLooper = null;
            this.mIsRunning = false;
        }
    }

    @Override // com.visualon.OSMPSensor.SensorEventProvider
    public void registerListener(SensorEventListener sensorEventListener) {
        synchronized (this.mRegisteredListeners) {
            this.mRegisteredListeners.add(sensorEventListener);
        }
    }

    @Override // com.visualon.OSMPSensor.SensorEventProvider
    public void unregisterListener(SensorEventListener sensorEventListener) {
        synchronized (this.mRegisteredListeners) {
            this.mRegisteredListeners.remove(sensorEventListener);
        }
    }
}
