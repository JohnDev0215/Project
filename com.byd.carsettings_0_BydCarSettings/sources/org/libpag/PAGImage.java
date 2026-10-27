package org.libpag;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;

/* JADX INFO: loaded from: classes3.dex */
public class PAGImage {
    public long nativeContext;

    static {
        org.extra.tools.a.a("pag");
        nativeInit();
    }

    public PAGImage(long j) {
        this.nativeContext = 0L;
        this.nativeContext = j;
    }

    public static PAGImage FromAssets(AssetManager assetManager, String str) {
        long jLoadFromAssets = LoadFromAssets(assetManager, str);
        if (jLoadFromAssets == 0) {
            return null;
        }
        return new PAGImage(jLoadFromAssets);
    }

    public static PAGImage FromBitmap(Bitmap bitmap) {
        if (bitmap != null && bitmap.getConfig() == Bitmap.Config.ARGB_4444) {
            bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, false);
        }
        long jLoadFromBitmap = LoadFromBitmap(bitmap);
        if (jLoadFromBitmap == 0) {
            return null;
        }
        return new PAGImage(jLoadFromBitmap);
    }

    public static PAGImage FromBytes(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        long jLoadFromBytes = LoadFromBytes(bArr, bArr.length);
        if (jLoadFromBytes == 0) {
            return null;
        }
        return new PAGImage(jLoadFromBytes);
    }

    public static PAGImage FromPath(String str) {
        long jLoadFromPath = LoadFromPath(str);
        if (jLoadFromPath == 0) {
            return null;
        }
        return new PAGImage(jLoadFromPath);
    }

    public static PAGImage FromTexture(int i, int i2, int i3, int i4) {
        return FromTexture(i, i2, i3, i4, false);
    }

    private static native long LoadFromAssets(AssetManager assetManager, String str);

    private static native long LoadFromBitmap(Bitmap bitmap);

    private static native long LoadFromBytes(byte[] bArr, int i);

    private static native long LoadFromPath(String str);

    private static native long LoadFromTexture(int i, int i2, int i3, int i4, boolean z);

    private native void nativeFinalize();

    private native void nativeGetMatrix(float[] fArr);

    private static final native void nativeInit();

    private final native void nativeRelease();

    private native void nativeSetMatrix(float f2, float f3, float f4, float f5, float f6, float f7);

    public void finalize() {
        nativeFinalize();
    }

    public native int height();

    public Matrix matrix() {
        float[] fArr = new float[9];
        nativeGetMatrix(fArr);
        Matrix matrix = new Matrix();
        matrix.setValues(fArr);
        return matrix;
    }

    public void release() {
        nativeRelease();
    }

    public native int scaleMode();

    public void setMatrix(Matrix matrix) {
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        nativeSetMatrix(fArr[0], fArr[3], fArr[1], fArr[4], fArr[2], fArr[5]);
    }

    public native void setScaleMode(int i);

    public native int width();

    public static PAGImage FromTexture(int i, int i2, int i3, int i4, boolean z) {
        long jLoadFromTexture = LoadFromTexture(i, i2, i3, i4, z);
        if (jLoadFromTexture == 0) {
            return null;
        }
        return new PAGImage(jLoadFromTexture);
    }
}
