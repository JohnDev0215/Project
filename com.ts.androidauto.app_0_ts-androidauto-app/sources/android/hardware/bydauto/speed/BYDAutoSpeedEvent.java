package android.hardware.bydauto.speed;

import android.hardware.bydauto.BYDAutoEvent;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoSpeedEvent extends BYDAutoEvent {
    public BYDAutoSpeedEvent(int deviceType, int eventType, int value) {
        super(0, 0, 0);
    }

    public BYDAutoSpeedEvent(int deviceType, int eventType, int value, Object data) {
        super(0, 0, 0);
    }

    public BYDAutoSpeedEvent(int deviceType, int eventType, double value) {
        super(0, 0, 0);
    }

    public BYDAutoSpeedEvent(int deviceType, int eventType, double value, Object data) {
        super(0, 0, 0);
    }

    public BYDAutoSpeedEvent(int deviceType, int eventType, byte[] value, Object data) {
        super(0, 0, 0);
    }

    @Override // android.hardware.bydauto.BYDAutoEvent, android.hardware.IBYDAutoEvent
    public byte[] getBufferData() {
        return new byte[0];
    }
}
