package com.visualon.OSMPSensor;

/* JADX INFO: loaded from: classes7.dex */
public class SystemClock implements Clock {
    @Override // com.visualon.OSMPSensor.Clock
    public long nanoTime() {
        return System.nanoTime();
    }
}
