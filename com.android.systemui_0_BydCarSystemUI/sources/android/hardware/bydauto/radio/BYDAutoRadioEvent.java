package android.hardware.bydauto.radio;

import android.hardware.bydauto.BYDAutoEvent;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoRadioEvent extends BYDAutoEvent {
    @Override // android.hardware.bydauto.BYDAutoEvent, android.hardware.IBYDAutoEvent
    public byte[] getBufferData() {
        return new byte[2];
    }

    public BYDAutoRadioEvent(int i, int i2, byte[] bArr, Object obj) {
        super(0, 0, 0);
    }
}
