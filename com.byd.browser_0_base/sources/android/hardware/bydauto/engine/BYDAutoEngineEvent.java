package android.hardware.bydauto.engine;

import android.hardware.bydauto.BYDAutoEvent;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoEngineEvent extends BYDAutoEvent {
    private static final String TAG = "BYDAutoEngineEvent";

    public BYDAutoEngineEvent(int i, int i2, int i3) {
        super(0, 0, 0);
    }

    @Override // android.hardware.bydauto.BYDAutoEvent, android.hardware.IBYDAutoEvent
    public byte[] getBufferData() {
        return new byte[0];
    }

    public BYDAutoEngineEvent(int i, int i2, int i3, Object obj) {
        super(0, 0, 0);
    }

    public BYDAutoEngineEvent(int i, int i2, double d) {
        super(0, 0, 0);
    }

    public BYDAutoEngineEvent(int i, int i2, double d, Object obj) {
        super(0, 0, 0);
    }

    public BYDAutoEngineEvent(int i, int i2, byte[] bArr, Object obj) {
        super(0, 0, 0);
    }
}
