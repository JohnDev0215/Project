package android.hardware.bydauto;

import android.hardware.IBYDAutoEvent;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoEvent implements IBYDAutoEvent {
    public BYDAutoEvent(int i, int i2, float f) {
    }

    public BYDAutoEvent(int i, int i2, float f, Object obj) {
    }

    public BYDAutoEvent(int i, int i2, int i3) {
    }

    public BYDAutoEvent(int i, int i2, int i3, Object obj) {
    }

    public BYDAutoEvent(int i, int i2, byte[] bArr, Object obj) {
    }

    @Override // android.hardware.IBYDAutoEvent
    public byte[] getBufferData() {
        return new byte[0];
    }

    @Override // android.hardware.IBYDAutoEvent
    public Object getData() {
        return new Object();
    }

    @Override // android.hardware.IBYDAutoEvent
    public int getDeviceType() {
        return 0;
    }

    @Override // android.hardware.IBYDAutoEvent
    public double getDoubleValue() {
        return 0.0d;
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
    public void setData(Object obj) {
    }
}
