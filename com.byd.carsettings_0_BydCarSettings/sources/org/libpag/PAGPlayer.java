package org.libpag;

import android.graphics.Matrix;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes3.dex */
public class PAGPlayer {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private PAGSurface f4037a = null;
    private long nativeContext = 0;

    static {
        org.extra.tools.a.a("ffavc");
        org.extra.tools.a.a("pag");
        nativeInit();
    }

    public PAGPlayer() {
        nativeSetup();
        org.extra.tools.f.b();
    }

    private native void nativeFinalize();

    private native void nativeGetMatrix(float[] fArr);

    private static final native void nativeInit();

    private final native void nativeRelease();

    private native void nativeSetMatrix(float f2, float f3, float f4, float f5, float f6, float f7);

    private native void nativeSetSurface(long j);

    private final native void nativeSetup();

    public native boolean cacheEnabled();

    public native float cacheScale();

    public native long currentFrame();

    public native long duration();

    public void finalize() {
        nativeFinalize();
    }

    public boolean flush() {
        return flushAndFenceSync(null);
    }

    public native boolean flushAndFenceSync(long[] jArr);

    public native RectF getBounds(PAGLayer pAGLayer);

    public native PAGComposition getComposition();

    public native PAGLayer[] getLayersUnderPoint(float f2, float f3);

    public native double getProgress();

    public PAGSurface getSurface() {
        return this.f4037a;
    }

    public native boolean hitTestPoint(PAGLayer pAGLayer, float f2, float f3, boolean z);

    public Matrix matrix() {
        float[] fArr = new float[9];
        nativeGetMatrix(fArr);
        Matrix matrix = new Matrix();
        matrix.setValues(fArr);
        return matrix;
    }

    public native float maxFrameRate();

    public native void prepare();

    public void release() {
        nativeRelease();
    }

    public native int scaleMode();

    public native void setCacheEnabled(boolean z);

    public native void setCacheScale(float f2);

    public native void setComposition(PAGComposition pAGComposition);

    public void setMatrix(Matrix matrix) {
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        nativeSetMatrix(fArr[0], fArr[3], fArr[1], fArr[4], fArr[2], fArr[5]);
    }

    public native void setMaxFrameRate(float f2);

    public native void setProgress(double d2);

    public native void setScaleMode(int i);

    public void setSurface(PAGSurface pAGSurface) {
        this.f4037a = pAGSurface;
        if (pAGSurface == null) {
            nativeSetSurface(0L);
        } else {
            nativeSetSurface(pAGSurface.nativeSurface);
        }
    }

    public native void setVideoEnabled(boolean z);

    public native boolean videoEnabled();

    public native boolean waitSync(long j);
}
