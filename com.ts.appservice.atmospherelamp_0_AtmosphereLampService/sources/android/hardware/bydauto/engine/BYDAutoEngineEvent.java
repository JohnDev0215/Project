package android.hardware.bydauto.engine;

import android.hardware.bydauto.BYDAutoEvent;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoEngineEvent extends BYDAutoEvent {
    private static final String TAG = "BYDAutoEngineEvent";

    public BYDAutoEngineEvent(int deviceType, int eventType, int value) {
        super(0, 0, 0);
        Log.d(TAG, TAG);
    }

    public BYDAutoEngineEvent(int deviceType, int eventType, int value, Object data) {
        super(0, 0, 0);
        Log.d(TAG, TAG);
    }

    public BYDAutoEngineEvent(int deviceType, int eventType, double value) {
        super(0, 0, 0);
        Log.d(TAG, TAG);
    }

    public BYDAutoEngineEvent(int deviceType, int eventType, double value, Object data) {
        super(0, 0, 0);
        Log.d(TAG, TAG);
    }

    public BYDAutoEngineEvent(int deviceType, int eventType, byte[] value, Object data) {
        super(0, 0, 0);
        Log.d(TAG, TAG);
    }

    @Override // android.hardware.bydauto.BYDAutoEvent, android.hardware.IBYDAutoEvent
    public byte[] getBufferData() {
        Log.d(TAG, TAG);
        return new byte[0];
    }
}
