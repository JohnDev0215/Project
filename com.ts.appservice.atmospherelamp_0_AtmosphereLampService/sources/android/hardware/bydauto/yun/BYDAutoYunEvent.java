package android.hardware.bydauto.yun;

import android.hardware.bydauto.BYDAutoEvent;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoYunEvent extends BYDAutoEvent {
    public BYDAutoYunEvent(int deviceType, int eventType, byte[] value, Object data) {
        super(0, 0, 0);
    }

    @Override // android.hardware.bydauto.BYDAutoEvent, android.hardware.IBYDAutoEvent
    public byte[] getBufferData() {
        return new byte[0];
    }
}
