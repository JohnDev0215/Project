package org.libpag;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLContext;
import android.os.Build;
import android.view.Surface;

/* JADX INFO: loaded from: classes3.dex */
public class PAGSurface {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Surface f4038a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f4039b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4040c = 0;
    public long nativeSurface;

    static {
        org.extra.tools.a.a("pag");
        nativeInit();
    }

    private PAGSurface(long j) {
        this.nativeSurface = 0L;
        this.nativeSurface = j;
    }

    public static PAGSurface FromSurface(Surface surface) {
        return FromSurface(surface, EGL14.EGL_NO_CONTEXT);
    }

    public static PAGSurface FromSurfaceTexture(SurfaceTexture surfaceTexture) {
        return FromSurfaceTexture(surfaceTexture, EGL14.EGL_NO_CONTEXT);
    }

    public static PAGSurface FromTexture(int i, int i2, int i3) {
        return FromTexture(i, i2, i3, false);
    }

    public static PAGSurface FromTextureForAsyncThread(int i, int i2, int i3) {
        return FromTextureForAsyncThread(i, i2, i3, false);
    }

    public static PAGSurface MakeOffscreen(int i, int i2) {
        long jSetupOffscreen = SetupOffscreen(i, i2);
        if (jSetupOffscreen == 0) {
            return null;
        }
        return new PAGSurface(jSetupOffscreen);
    }

    private static native long SetupFromSurfaceWithGLContext(Surface surface, long j);

    public static native long SetupFromTexture(int i, int i2, int i3, boolean z, boolean z2);

    private static native long SetupOffscreen(int i, int i2);

    private native void nativeFinalize();

    private static native void nativeInit();

    private native void nativeRelease();

    public native boolean clearAll();

    public native boolean copyPixelsTo(Bitmap bitmap);

    public void finalize() {
        nativeFinalize();
    }

    public native void freeCache();

    public native int height();

    public native Bitmap makeSnapshot();

    public void release() {
        Surface surface;
        if (this.f4039b && (surface = this.f4038a) != null) {
            surface.release();
        }
        nativeRelease();
    }

    public native void updateSize();

    public native int width();

    public static PAGSurface FromSurface(Surface surface, EGLContext eGLContext) {
        if (surface == null) {
            return null;
        }
        long jSetupFromSurfaceWithGLContext = SetupFromSurfaceWithGLContext(surface, (eGLContext == null || eGLContext == EGL14.EGL_NO_CONTEXT) ? 0L : Build.VERSION.SDK_INT >= 21 ? eGLContext.getNativeHandle() : eGLContext.getHandle());
        if (jSetupFromSurfaceWithGLContext == 0) {
            return null;
        }
        PAGSurface pAGSurface = new PAGSurface(jSetupFromSurfaceWithGLContext);
        pAGSurface.f4038a = surface;
        return pAGSurface;
    }

    public static PAGSurface FromSurfaceTexture(SurfaceTexture surfaceTexture, EGLContext eGLContext) {
        if (surfaceTexture == null) {
            return null;
        }
        PAGSurface pAGSurfaceFromSurface = FromSurface(new Surface(surfaceTexture), eGLContext);
        if (pAGSurfaceFromSurface != null) {
            pAGSurfaceFromSurface.f4039b = true;
        }
        return pAGSurfaceFromSurface;
    }

    public static PAGSurface FromTexture(int i, int i2, int i3, boolean z) {
        long jSetupFromTexture = SetupFromTexture(i, i2, i3, z, false);
        if (jSetupFromTexture == 0) {
            return null;
        }
        PAGSurface pAGSurface = new PAGSurface(jSetupFromTexture);
        pAGSurface.f4040c = i;
        return pAGSurface;
    }

    public static PAGSurface FromTextureForAsyncThread(int i, int i2, int i3, boolean z) {
        long jSetupFromTexture = SetupFromTexture(i, i2, i3, z, true);
        if (jSetupFromTexture == 0) {
            return null;
        }
        PAGSurface pAGSurface = new PAGSurface(jSetupFromTexture);
        pAGSurface.f4040c = i;
        return pAGSurface;
    }
}
