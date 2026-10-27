package android.hardware.bydauto.speed;

import android.hardware.bydauto.BYDAutoEvent;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoSpeedEvent extends BYDAutoEvent {
    public BYDAutoSpeedEvent(int i2, int i3, int i4) {
        super(0, 0, 0);
    }

    @Override // android.hardware.bydauto.BYDAutoEvent
    public byte[] getBufferData() {
        return new byte[0];
    }

    public BYDAutoSpeedEvent(int i2, int i3, int i4, Object obj) {
        super(0, 0, 0);
    }

    public BYDAutoSpeedEvent(int i2, int i3, double d2) {
        super(0, 0, 0);
    }

    public BYDAutoSpeedEvent(int i2, int i3, double d2, Object obj) {
        super(0, 0, 0);
    }

    public BYDAutoSpeedEvent(int i2, int i3, byte[] bArr, Object obj) {
        super(0, 0, 0);
    }
}
