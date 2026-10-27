package android.hardware.bydauto.special;

import android.hardware.bydauto.BYDAutoEvent;

/* JADX INFO: loaded from: classes2.dex */
public class BYDAutoSpecialEvent extends BYDAutoEvent {
    @Override // android.hardware.bydauto.BYDAutoEvent, android.hardware.IBYDAutoEvent
    public byte[] getBufferData() {
        return new byte[0];
    }

    public BYDAutoSpecialEvent(int i, int i2, byte[] bArr, Object obj) {
        super(0, 0, 0);
    }
}
