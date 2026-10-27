package org.libpag;

import android.graphics.SurfaceTexture;
import android.view.Surface;

/* JADX INFO: loaded from: classes3.dex */
public class VideoSurface implements SurfaceTexture.OnFrameAvailableListener {
    public long nativeContext = 0;

    static {
        org.extra.tools.a.a("pag");
        nativeInit();
    }

    private VideoSurface(int i, int i2) {
        nativeSetup(i, i2);
    }

    public static VideoSurface a(int i, int i2) {
        VideoSurface videoSurface = new VideoSurface(i, i2);
        if (videoSurface.nativeContext == 0) {
            return null;
        }
        return videoSurface;
    }

    private native void nativeFinalize();

    private static native void nativeInit();

    private native void nativeRelease();

    private native void nativeSetup(int i, int i2);

    private native void notifyFrameAvailable();

    public void finalize() {
        nativeFinalize();
    }

    public native Surface getInputSurface();

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        notifyFrameAvailable();
    }

    public void a() {
        nativeRelease();
    }
}
