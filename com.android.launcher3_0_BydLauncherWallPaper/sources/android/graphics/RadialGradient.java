package android.graphics;

import android.graphics.ColorSpace;
import android.graphics.Shader;

/* JADX INFO: loaded from: classes.dex */
public class RadialGradient extends Shader {
    private int mCenterColor;
    private final long[] mColorLongs;
    private int[] mColors;
    private int mEdgeColor;
    private float[] mPositions;
    private float mRadius;
    private Shader.TileMode mTileMode;
    private float mX;
    private float mY;

    private static native long nativeCreate(long j, float f, float f2, float f3, long[] jArr, float[] fArr, int i, long j2);

    public RadialGradient(float f, float f2, float f3, int[] iArr, float[] fArr, Shader.TileMode tileMode) {
        this(f, f2, f3, convertColors(iArr), fArr, tileMode, ColorSpace.get(ColorSpace.Named.SRGB));
    }

    public RadialGradient(float f, float f2, float f3, long[] jArr, float[] fArr, Shader.TileMode tileMode) {
        this(f, f2, f3, (long[]) jArr.clone(), fArr, tileMode, detectColorSpace(jArr));
    }

    private RadialGradient(float f, float f2, float f3, long[] jArr, float[] fArr, Shader.TileMode tileMode, ColorSpace colorSpace) {
        super(colorSpace);
        if (f3 <= 0.0f) {
            throw new IllegalArgumentException("radius must be > 0");
        }
        if (fArr != null && jArr.length != fArr.length) {
            throw new IllegalArgumentException("color and position arrays must be of equal length");
        }
        this.mX = f;
        this.mY = f2;
        this.mRadius = f3;
        this.mColorLongs = jArr;
        this.mPositions = fArr != null ? (float[]) fArr.clone() : null;
        this.mTileMode = tileMode;
    }

    public RadialGradient(float f, float f2, float f3, int i, int i2, Shader.TileMode tileMode) {
        this(f, f2, f3, Color.pack(i), Color.pack(i2), tileMode);
    }

    public RadialGradient(float f, float f2, float f3, long j, long j2, Shader.TileMode tileMode) {
        this(f, f2, f3, new long[]{j, j2}, (float[]) null, tileMode);
    }

    @Override // android.graphics.Shader
    long createNativeInstance(long j) {
        return nativeCreate(j, this.mX, this.mY, this.mRadius, this.mColorLongs, this.mPositions, this.mTileMode.nativeInt, colorSpace().getNativeInstance());
    }
}
