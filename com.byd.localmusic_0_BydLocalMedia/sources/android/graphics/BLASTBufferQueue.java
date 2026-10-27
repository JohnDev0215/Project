package android.graphics;

import android.view.Surface;
import android.view.SurfaceControl;

/* JADX INFO: loaded from: classes.dex */
public final class BLASTBufferQueue {
    private long mNativeObject;

    private static native long nativeCreate(long j, long j2, long j3, boolean z);

    private static native void nativeDestroy(long j);

    private static native Surface nativeGetSurface(long j);

    private static native void nativeSetNextTransaction(long j, long j2);

    private static native void nativeUpdate(long j, long j2, long j3, long j4);

    public BLASTBufferQueue(SurfaceControl surfaceControl, int i, int i2, boolean z) {
        this.mNativeObject = nativeCreate(surfaceControl.mNativeObject, i, i2, z);
    }

    public void destroy() {
        nativeDestroy(this.mNativeObject);
    }

    public Surface getSurface() {
        return nativeGetSurface(this.mNativeObject);
    }

    public void setNextTransaction(SurfaceControl.Transaction transaction) {
        nativeSetNextTransaction(this.mNativeObject, transaction.mNativeObject);
    }

    public void update(SurfaceControl surfaceControl, int i, int i2) {
        nativeUpdate(this.mNativeObject, surfaceControl.mNativeObject, i, i2);
    }

    protected void finalize() throws Throwable {
        try {
            if (this.mNativeObject != 0) {
                nativeDestroy(this.mNativeObject);
            }
        } finally {
            super.finalize();
        }
    }
}
