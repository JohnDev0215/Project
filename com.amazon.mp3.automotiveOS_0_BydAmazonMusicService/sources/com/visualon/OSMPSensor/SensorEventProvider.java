package com.visualon.OSMPSensor;

import android.hardware.SensorEventListener;

/* JADX INFO: loaded from: classes7.dex */
public interface SensorEventProvider {
    void registerListener(SensorEventListener sensorEventListener);

    void start();

    void stop();

    void unregisterListener(SensorEventListener sensorEventListener);
}
