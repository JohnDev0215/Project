package android.hardware.bydauto.special;

import android.hardware.bydauto.BYDAutoEvent;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoSpecialEvent extends BYDAutoEvent {
    public BYDAutoSpecialEvent(int i3, int i4, byte[] bArr, Object obj) {
        super(0, 0, 0);
    }

    @Override // android.hardware.bydauto.BYDAutoEvent
    public byte[] getBufferData() {
        return new byte[0];
    }
}
