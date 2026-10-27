package android.hardware.bydauto.engine;

import android.hardware.bydauto.BYDAutoEvent;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class BYDAutoEngineEvent extends BYDAutoEvent {
    private static final String TAG = "BYDAutoEngineEvent";

    public BYDAutoEngineEvent(int i7, int i8, int i9) {
        super(0, 0, 0);
        Log.d(TAG, TAG);
    }

    @Override // android.hardware.bydauto.BYDAutoEvent
    public byte[] getBufferData() {
        Log.d(TAG, TAG);
        return new byte[0];
    }

    public BYDAutoEngineEvent(int i7, int i8, int i9, Object obj) {
        super(0, 0, 0);
        Log.d(TAG, TAG);
    }

    public BYDAutoEngineEvent(int i7, int i8, double d7) {
        super(0, 0, 0);
        Log.d(TAG, TAG);
    }

    public BYDAutoEngineEvent(int i7, int i8, double d7, Object obj) {
        super(0, 0, 0);
        Log.d(TAG, TAG);
    }

    public BYDAutoEngineEvent(int i7, int i8, byte[] bArr, Object obj) {
        super(0, 0, 0);
        Log.d(TAG, TAG);
    }
}
