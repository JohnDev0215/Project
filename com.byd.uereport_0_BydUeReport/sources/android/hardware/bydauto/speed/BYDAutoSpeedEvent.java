package android.hardware.bydauto.speed;

import android.hardware.bydauto.BYDAutoEvent;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoSpeedEvent extends BYDAutoEvent {
    public BYDAutoSpeedEvent(int i8, int i9, int i10) {
        super(0, 0, 0);
    }

    @Override // android.hardware.bydauto.BYDAutoEvent
    public byte[] getBufferData() {
        return new byte[0];
    }

    public BYDAutoSpeedEvent(int i8, int i9, int i10, Object obj) {
        super(0, 0, 0);
    }

    public BYDAutoSpeedEvent(int i8, int i9, double d8) {
        super(0, 0, 0);
    }

    public BYDAutoSpeedEvent(int i8, int i9, double d8, Object obj) {
        super(0, 0, 0);
    }

    public BYDAutoSpeedEvent(int i8, int i9, byte[] bArr, Object obj) {
        super(0, 0, 0);
    }
}
