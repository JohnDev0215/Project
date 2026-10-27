package android.hardware.bydauto.sensor;

import android.hardware.bydauto.BYDAutoEvent;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoSensorEvent extends BYDAutoEvent {
    public BYDAutoSensorEvent(int i7, int i8, byte[] bArr, Object obj) {
        super(0, 0, 0);
    }

    @Override // android.hardware.bydauto.BYDAutoEvent
    public byte[] getBufferData() {
        return new byte[0];
    }
}
