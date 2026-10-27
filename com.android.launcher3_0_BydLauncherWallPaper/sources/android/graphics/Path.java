package android.graphics;

import android.graphics.Region;
import dalvik.annotation.optimization.CriticalNative;
import dalvik.annotation.optimization.FastNative;
import java.util.Objects;
import libcore.util.NativeAllocationRegistry;

/* JADX INFO: loaded from: classes.dex */
public class Path {
    public boolean isSimplePath;
    private Direction mLastDirection;
    public final long mNativePath;
    public Region rects;
    private static final NativeAllocationRegistry sRegistry = NativeAllocationRegistry.createMalloced(Path.class.getClassLoader(), nGetFinalizer());
    static final FillType[] sFillTypeArray = {FillType.WINDING, FillType.EVEN_ODD, FillType.INVERSE_WINDING, FillType.INVERSE_EVEN_ODD};

    public enum Op {
        DIFFERENCE,
        INTERSECT,
        UNION,
        XOR,
        REVERSE_DIFFERENCE
    }

    private static native void nAddArc(long j, float f, float f2, float f3, float f4, float f5, float f6);

    private static native void nAddCircle(long j, float f, float f2, float f3, int i);

    private static native void nAddOval(long j, float f, float f2, float f3, float f4, int i);

    private static native void nAddPath(long j, long j2);

    private static native void nAddPath(long j, long j2, float f, float f2);

    private static native void nAddPath(long j, long j2, long j3);

    private static native void nAddRect(long j, float f, float f2, float f3, float f4, int i);

    private static native void nAddRoundRect(long j, float f, float f2, float f3, float f4, float f5, float f6, int i);

    private static native void nAddRoundRect(long j, float f, float f2, float f3, float f4, float[] fArr, int i);

    private static native float[] nApproximate(long j, float f);

    private static native void nArcTo(long j, float f, float f2, float f3, float f4, float f5, float f6, boolean z);

    private static native void nClose(long j);

    private static native void nComputeBounds(long j, RectF rectF);

    private static native void nCubicTo(long j, float f, float f2, float f3, float f4, float f5, float f6);

    @CriticalNative
    private static native int nGetFillType(long j);

    private static native long nGetFinalizer();

    private static native void nIncReserve(long j, int i);

    private static native long nInit();

    private static native long nInit(long j);

    @CriticalNative
    private static native boolean nIsConvex(long j);

    @CriticalNative
    private static native boolean nIsEmpty(long j);

    @FastNative
    private static native boolean nIsRect(long j, RectF rectF);

    private static native void nLineTo(long j, float f, float f2);

    private static native void nMoveTo(long j, float f, float f2);

    private static native void nOffset(long j, float f, float f2);

    private static native boolean nOp(long j, long j2, int i, long j3);

    private static native void nQuadTo(long j, float f, float f2, float f3, float f4);

    private static native void nRCubicTo(long j, float f, float f2, float f3, float f4, float f5, float f6);

    private static native void nRLineTo(long j, float f, float f2);

    private static native void nRMoveTo(long j, float f, float f2);

    private static native void nRQuadTo(long j, float f, float f2, float f3, float f4);

    @CriticalNative
    private static native void nReset(long j);

    @CriticalNative
    private static native void nRewind(long j);

    private static native void nSet(long j, long j2);

    @CriticalNative
    private static native void nSetFillType(long j, int i);

    private static native void nSetLastPoint(long j, float f, float f2);

    private static native void nTransform(long j, long j2);

    private static native void nTransform(long j, long j2, long j3);

    public Path() {
        this.isSimplePath = true;
        this.mLastDirection = null;
        long jNInit = nInit();
        this.mNativePath = jNInit;
        sRegistry.registerNativeAllocation(this, jNInit);
    }

    public Path(Path path) {
        long j;
        this.isSimplePath = true;
        this.mLastDirection = null;
        if (path != null) {
            j = path.mNativePath;
            this.isSimplePath = path.isSimplePath;
            if (path.rects != null) {
                this.rects = new Region(path.rects);
            }
        } else {
            j = 0;
        }
        long jNInit = nInit(j);
        this.mNativePath = jNInit;
        sRegistry.registerNativeAllocation(this, jNInit);
    }

    public void reset() {
        this.isSimplePath = true;
        this.mLastDirection = null;
        Region region = this.rects;
        if (region != null) {
            region.setEmpty();
        }
        FillType fillType = getFillType();
        nReset(this.mNativePath);
        setFillType(fillType);
    }

    public void rewind() {
        this.isSimplePath = true;
        this.mLastDirection = null;
        Region region = this.rects;
        if (region != null) {
            region.setEmpty();
        }
        nRewind(this.mNativePath);
    }

    public void set(Path path) {
        Region region;
        if (this == path) {
            return;
        }
        this.isSimplePath = path.isSimplePath;
        nSet(this.mNativePath, path.mNativePath);
        if (this.isSimplePath) {
            Region region2 = this.rects;
            if (region2 != null && (region = path.rects) != null) {
                region2.set(region);
                return;
            }
            if (region2 != null && path.rects == null) {
                region2.setEmpty();
            } else if (path.rects != null) {
                this.rects = new Region(path.rects);
            }
        }
    }

    public boolean op(Path path, Op op) {
        return op(this, path, op);
    }

    public boolean op(Path path, Path path2, Op op) {
        if (!nOp(path.mNativePath, path2.mNativePath, op.ordinal(), this.mNativePath)) {
            return false;
        }
        this.isSimplePath = false;
        this.rects = null;
        return true;
    }

    @Deprecated
    public boolean isConvex() {
        return nIsConvex(this.mNativePath);
    }

    public enum FillType {
        WINDING(0),
        EVEN_ODD(1),
        INVERSE_WINDING(2),
        INVERSE_EVEN_ODD(3);

        final int nativeInt;

        FillType(int i) {
            this.nativeInt = i;
        }
    }

    public FillType getFillType() {
        return sFillTypeArray[nGetFillType(this.mNativePath)];
    }

    public void setFillType(FillType fillType) {
        nSetFillType(this.mNativePath, fillType.nativeInt);
    }

    public boolean isInverseFillType() {
        return (nGetFillType(this.mNativePath) & FillType.INVERSE_WINDING.nativeInt) != 0;
    }

    public void toggleInverseFillType() {
        nSetFillType(this.mNativePath, nGetFillType(this.mNativePath) ^ FillType.INVERSE_WINDING.nativeInt);
    }

    public boolean isEmpty() {
        return nIsEmpty(this.mNativePath);
    }

    public boolean isRect(RectF rectF) {
        return nIsRect(this.mNativePath, rectF);
    }

    public void computeBounds(RectF rectF, boolean z) {
        nComputeBounds(this.mNativePath, rectF);
    }

    public void incReserve(int i) {
        nIncReserve(this.mNativePath, i);
    }

    public void moveTo(float f, float f2) {
        nMoveTo(this.mNativePath, f, f2);
    }

    public void rMoveTo(float f, float f2) {
        nRMoveTo(this.mNativePath, f, f2);
    }

    public void lineTo(float f, float f2) {
        this.isSimplePath = false;
        nLineTo(this.mNativePath, f, f2);
    }

    public void rLineTo(float f, float f2) {
        this.isSimplePath = false;
        nRLineTo(this.mNativePath, f, f2);
    }

    public void quadTo(float f, float f2, float f3, float f4) {
        this.isSimplePath = false;
        nQuadTo(this.mNativePath, f, f2, f3, f4);
    }

    public void rQuadTo(float f, float f2, float f3, float f4) {
        this.isSimplePath = false;
        nRQuadTo(this.mNativePath, f, f2, f3, f4);
    }

    public void cubicTo(float f, float f2, float f3, float f4, float f5, float f6) {
        this.isSimplePath = false;
        nCubicTo(this.mNativePath, f, f2, f3, f4, f5, f6);
    }

    public void rCubicTo(float f, float f2, float f3, float f4, float f5, float f6) {
        this.isSimplePath = false;
        nRCubicTo(this.mNativePath, f, f2, f3, f4, f5, f6);
    }

    public void arcTo(RectF rectF, float f, float f2, boolean z) {
        arcTo(rectF.left, rectF.top, rectF.right, rectF.bottom, f, f2, z);
    }

    public void arcTo(RectF rectF, float f, float f2) {
        arcTo(rectF.left, rectF.top, rectF.right, rectF.bottom, f, f2, false);
    }

    public void arcTo(float f, float f2, float f3, float f4, float f5, float f6, boolean z) {
        this.isSimplePath = false;
        nArcTo(this.mNativePath, f, f2, f3, f4, f5, f6, z);
    }

    public void close() {
        this.isSimplePath = false;
        nClose(this.mNativePath);
    }

    public enum Direction {
        CW(0),
        CCW(1);

        final int nativeInt;

        Direction(int i) {
            this.nativeInt = i;
        }
    }

    private void detectSimplePath(float f, float f2, float f3, float f4, Direction direction) {
        if (this.mLastDirection == null) {
            this.mLastDirection = direction;
        }
        if (this.mLastDirection != direction) {
            this.isSimplePath = false;
            return;
        }
        if (this.rects == null) {
            this.rects = new Region();
        }
        this.rects.op((int) f, (int) f2, (int) f3, (int) f4, Region.Op.UNION);
    }

    public void addRect(RectF rectF, Direction direction) {
        addRect(rectF.left, rectF.top, rectF.right, rectF.bottom, direction);
    }

    public void addRect(float f, float f2, float f3, float f4, Direction direction) {
        detectSimplePath(f, f2, f3, f4, direction);
        nAddRect(this.mNativePath, f, f2, f3, f4, direction.nativeInt);
    }

    public void addOval(RectF rectF, Direction direction) {
        addOval(rectF.left, rectF.top, rectF.right, rectF.bottom, direction);
    }

    public void addOval(float f, float f2, float f3, float f4, Direction direction) {
        this.isSimplePath = false;
        nAddOval(this.mNativePath, f, f2, f3, f4, direction.nativeInt);
    }

    public void addCircle(float f, float f2, float f3, Direction direction) {
        this.isSimplePath = false;
        nAddCircle(this.mNativePath, f, f2, f3, direction.nativeInt);
    }

    public void addArc(RectF rectF, float f, float f2) {
        addArc(rectF.left, rectF.top, rectF.right, rectF.bottom, f, f2);
    }

    public void addArc(float f, float f2, float f3, float f4, float f5, float f6) {
        this.isSimplePath = false;
        nAddArc(this.mNativePath, f, f2, f3, f4, f5, f6);
    }

    public void addRoundRect(RectF rectF, float f, float f2, Direction direction) {
        addRoundRect(rectF.left, rectF.top, rectF.right, rectF.bottom, f, f2, direction);
    }

    public void addRoundRect(float f, float f2, float f3, float f4, float f5, float f6, Direction direction) {
        this.isSimplePath = false;
        nAddRoundRect(this.mNativePath, f, f2, f3, f4, f5, f6, direction.nativeInt);
    }

    public void addRoundRect(RectF rectF, float[] fArr, Direction direction) {
        Objects.requireNonNull(rectF, "need rect parameter");
        addRoundRect(rectF.left, rectF.top, rectF.right, rectF.bottom, fArr, direction);
    }

    public void addRoundRect(float f, float f2, float f3, float f4, float[] fArr, Direction direction) {
        if (fArr.length < 8) {
            throw new ArrayIndexOutOfBoundsException("radii[] needs 8 values");
        }
        this.isSimplePath = false;
        nAddRoundRect(this.mNativePath, f, f2, f3, f4, fArr, direction.nativeInt);
    }

    public void addPath(Path path, float f, float f2) {
        this.isSimplePath = false;
        nAddPath(this.mNativePath, path.mNativePath, f, f2);
    }

    public void addPath(Path path) {
        this.isSimplePath = false;
        nAddPath(this.mNativePath, path.mNativePath);
    }

    public void addPath(Path path, Matrix matrix) {
        if (!path.isSimplePath) {
            this.isSimplePath = false;
        }
        nAddPath(this.mNativePath, path.mNativePath, matrix.native_instance);
    }

    public void offset(float f, float f2, Path path) {
        if (path != null) {
            path.set(this);
            this = path;
        }
        this.offset(f, f2);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void offset(float r5, float r6) {
        /*
            r4 = this;
            boolean r0 = r4.isSimplePath
            if (r0 == 0) goto L9
            android.graphics.Region r1 = r4.rects
            if (r1 != 0) goto L9
            return
        L9:
            if (r0 == 0) goto L25
            double r0 = (double) r5
            double r2 = java.lang.Math.rint(r0)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L25
            double r0 = (double) r6
            double r2 = java.lang.Math.rint(r0)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L25
            android.graphics.Region r0 = r4.rects
            int r1 = (int) r5
            int r2 = (int) r6
            r0.translate(r1, r2)
            goto L28
        L25:
            r0 = 0
            r4.isSimplePath = r0
        L28:
            long r0 = r4.mNativePath
            nOffset(r0, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.graphics.Path.offset(float, float):void");
    }

    public void setLastPoint(float f, float f2) {
        this.isSimplePath = false;
        nSetLastPoint(this.mNativePath, f, f2);
    }

    public void transform(Matrix matrix, Path path) {
        long j;
        if (path != null) {
            path.isSimplePath = false;
            j = path.mNativePath;
        } else {
            j = 0;
        }
        nTransform(this.mNativePath, matrix.native_instance, j);
    }

    public void transform(Matrix matrix) {
        this.isSimplePath = false;
        nTransform(this.mNativePath, matrix.native_instance);
    }

    public final long readOnlyNI() {
        return this.mNativePath;
    }

    final long mutateNI() {
        this.isSimplePath = false;
        return this.mNativePath;
    }

    public float[] approximate(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("AcceptableError must be greater than or equal to 0");
        }
        return nApproximate(this.mNativePath, f);
    }
}
