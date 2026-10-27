package android.hardware.bydauto.speed;

import android.hardware.bydauto.BYDAutoEvent;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoSpeedEvent extends BYDAutoEvent {
    public BYDAutoSpeedEvent(int i3, int i4, int i5) {
        super(0, 0, 0);
    }

    @Override // android.hardware.bydauto.BYDAutoEvent
    public byte[] getBufferData() {
        return new byte[0];
    }

    public BYDAutoSpeedEvent(int i3, int i4, int i5, Object obj) {
        super(0, 0, 0);
    }

    public BYDAutoSpeedEvent(int i3, int i4, double d3) {
        super(0, 0, 0);
    }

    public BYDAutoSpeedEvent(int i3, int i4, double d3, Object obj) {
        super(0, 0, 0);
    }

    public BYDAutoSpeedEvent(int i3, int i4, byte[] bArr, Object obj) {
        super(0, 0, 0);
    }
}
