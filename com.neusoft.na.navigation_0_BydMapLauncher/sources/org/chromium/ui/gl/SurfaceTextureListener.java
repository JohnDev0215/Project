package org.chromium.ui.gl;

import android.graphics.SurfaceTexture;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes4.dex */
@JNINamespace("gl")
class SurfaceTextureListener implements SurfaceTexture.OnFrameAvailableListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final long mNativeSurfaceTextureListener;

    private native void nativeDestroy(long j);

    private native void nativeFrameAvailable(long j);

    SurfaceTextureListener(long j) {
        this.mNativeSurfaceTextureListener = j;
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        nativeFrameAvailable(this.mNativeSurfaceTextureListener);
    }

    protected void finalize() throws Throwable {
        try {
            nativeDestroy(this.mNativeSurfaceTextureListener);
        } finally {
            super.finalize();
        }
    }
}
