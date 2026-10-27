package org.chromium.ui.gl;

import android.graphics.SurfaceTexture;
import android.util.Log;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes4.dex */
@JNINamespace("gl")
class SurfaceTexturePlatformWrapper {
    private static final String TAG = "SurfaceTexturePlatformWrapper";

    SurfaceTexturePlatformWrapper() {
    }

    private static SurfaceTexture create(int i) {
        return new SurfaceTexture(i);
    }

    private static void destroy(SurfaceTexture surfaceTexture) {
        surfaceTexture.setOnFrameAvailableListener(null);
        surfaceTexture.release();
    }

    private static void setFrameAvailableCallback(SurfaceTexture surfaceTexture, long j) {
        surfaceTexture.setOnFrameAvailableListener(new SurfaceTextureListener(j));
    }

    private static void updateTexImage(SurfaceTexture surfaceTexture) {
        try {
            surfaceTexture.updateTexImage();
        } catch (RuntimeException e) {
            Log.e(TAG, "Error calling updateTexImage", e);
        }
    }

    private static void getTransformMatrix(SurfaceTexture surfaceTexture, float[] fArr) {
        surfaceTexture.getTransformMatrix(fArr);
    }

    private static void attachToGLContext(SurfaceTexture surfaceTexture, int i) {
        surfaceTexture.attachToGLContext(i);
    }

    private static void detachFromGLContext(SurfaceTexture surfaceTexture) {
        surfaceTexture.detachFromGLContext();
    }

    private static void release(SurfaceTexture surfaceTexture) {
        surfaceTexture.release();
    }
}
