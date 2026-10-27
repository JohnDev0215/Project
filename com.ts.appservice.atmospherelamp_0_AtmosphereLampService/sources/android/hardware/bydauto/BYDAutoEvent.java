package android.hardware.bydauto;

import android.hardware.IBYDAutoEvent;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoEvent implements IBYDAutoEvent {
    public BYDAutoEvent(int deviceType, int eventType, int value) {
    }

    public BYDAutoEvent(int deviceType, int eventType, int value, Object data) {
    }

    public BYDAutoEvent(int deviceType, int eventType, float value) {
    }

    public BYDAutoEvent(int deviceType, int eventType, float value, Object data) {
    }

    public BYDAutoEvent(int deviceType, int eventType, byte[] value, Object data) {
    }

    @Override // android.hardware.IBYDAutoEvent
    public int getDeviceType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoEvent
    public int getEventType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoEvent
    public int getValue() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoEvent
    public double getDoubleValue() {
        return 0.0d;
    }

    @Override // android.hardware.IBYDAutoEvent
    public Object getData() {
        return new Object();
    }

    @Override // android.hardware.IBYDAutoEvent
    public void setData(Object data) {
    }

    @Override // android.hardware.IBYDAutoEvent
    public byte[] getBufferData() {
        return new byte[0];
    }
}
