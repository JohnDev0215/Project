package android.hardware.bydauto.speed;

import android.hardware.bydauto.BYDAutoEvent;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoSpeedEvent extends BYDAutoEvent {
    public BYDAutoSpeedEvent(int i7, int i8, int i9) {
        super(0, 0, 0);
    }

    @Override // android.hardware.bydauto.BYDAutoEvent
    public byte[] getBufferData() {
        return new byte[0];
    }

    public BYDAutoSpeedEvent(int i7, int i8, int i9, Object obj) {
        super(0, 0, 0);
    }

    public BYDAutoSpeedEvent(int i7, int i8, double d7) {
        super(0, 0, 0);
    }

    public BYDAutoSpeedEvent(int i7, int i8, double d7, Object obj) {
        super(0, 0, 0);
    }

    public BYDAutoSpeedEvent(int i7, int i8, byte[] bArr, Object obj) {
        super(0, 0, 0);
    }
}
