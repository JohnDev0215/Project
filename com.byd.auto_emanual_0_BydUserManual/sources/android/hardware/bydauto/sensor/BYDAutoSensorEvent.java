package android.hardware.bydauto.sensor;

import android.hardware.bydauto.BYDAutoEvent;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoSensorEvent extends BYDAutoEvent {
    @Override // android.hardware.bydauto.BYDAutoEvent, android.hardware.IBYDAutoEvent
    public byte[] getBufferData() {
        return new byte[0];
    }

    public BYDAutoSensorEvent(int i, int i2, byte[] bArr, Object obj) {
        super(0, 0, 0);
    }
}
