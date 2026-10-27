package android.hardware.bydauto.speed;

import android.hardware.bydauto.BYDAutoEvent;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoSpeedEvent extends BYDAutoEvent {
    public BYDAutoSpeedEvent(int i7, int i10, int i11) {
        super(0, 0, 0);
    }

    @Override // android.hardware.bydauto.BYDAutoEvent
    public byte[] getBufferData() {
        return new byte[0];
    }

    public BYDAutoSpeedEvent(int i7, int i10, int i11, Object obj) {
        super(0, 0, 0);
    }

    public BYDAutoSpeedEvent(int i7, int i10, double d) {
        super(0, 0, 0);
    }

    public BYDAutoSpeedEvent(int i7, int i10, double d, Object obj) {
        super(0, 0, 0);
    }

    public BYDAutoSpeedEvent(int i7, int i10, byte[] bArr, Object obj) {
        super(0, 0, 0);
    }
}
