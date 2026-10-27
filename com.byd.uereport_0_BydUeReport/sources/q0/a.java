package q0;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.animation.Interpolator;

/* JADX INFO: compiled from: PathInterpolatorApi14.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements Interpolator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float[] f7237a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float[] f7238b;

    public a(Path path) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        int i8 = ((int) (length / 0.002f)) + 1;
        this.f7237a = new float[i8];
        this.f7238b = new float[i8];
        float[] fArr = new float[2];
        for (int i9 = 0; i9 < i8; i9++) {
            pathMeasure.getPosTan((i9 * length) / (i8 - 1), fArr, null);
            this.f7237a[i9] = fArr[0];
            this.f7238b[i9] = fArr[1];
        }
    }

    public static Path a(float f8, float f9, float f10, float f11) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(f8, f9, f10, f11, 1.0f, 1.0f);
        return path;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f8) {
        if (f8 <= 0.0f) {
            return 0.0f;
        }
        if (f8 >= 1.0f) {
            return 1.0f;
        }
        int i8 = 0;
        int length = this.f7237a.length - 1;
        while (length - i8 > 1) {
            int i9 = (i8 + length) / 2;
            if (f8 < this.f7237a[i9]) {
                length = i9;
            } else {
                i8 = i9;
            }
        }
        float[] fArr = this.f7237a;
        float f9 = fArr[length] - fArr[i8];
        if (f9 == 0.0f) {
            return this.f7238b[i8];
        }
        float f10 = (f8 - fArr[i8]) / f9;
        float[] fArr2 = this.f7238b;
        float f11 = fArr2[i8];
        return f11 + (f10 * (fArr2[length] - f11));
    }

    public a(float f8, float f9, float f10, float f11) {
        this(a(f8, f9, f10, f11));
    }
}
