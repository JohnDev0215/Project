package b.g.k.d0;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.animation.Interpolator;

/* JADX INFO: compiled from: PathInterpolatorApi14.java */
/* JADX INFO: loaded from: classes.dex */
class a implements Interpolator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float[] f2337a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float[] f2338b;

    a(Path path) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        int i = ((int) (length / 0.002f)) + 1;
        this.f2337a = new float[i];
        this.f2338b = new float[i];
        float[] fArr = new float[2];
        for (int i2 = 0; i2 < i; i2++) {
            pathMeasure.getPosTan((i2 * length) / (i - 1), fArr, null);
            this.f2337a[i2] = fArr[0];
            this.f2338b[i2] = fArr[1];
        }
    }

    private static Path a(float f, float f2, float f3, float f4) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(f, f2, f3, f4, 1.0f, 1.0f);
        return path;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        if (f <= 0.0f) {
            return 0.0f;
        }
        if (f >= 1.0f) {
            return 1.0f;
        }
        int i = 0;
        int length = this.f2337a.length - 1;
        while (length - i > 1) {
            int i2 = (i + length) / 2;
            if (f < this.f2337a[i2]) {
                length = i2;
            } else {
                i = i2;
            }
        }
        float[] fArr = this.f2337a;
        float f2 = fArr[length] - fArr[i];
        if (f2 == 0.0f) {
            return this.f2338b[i];
        }
        float f3 = (f - fArr[i]) / f2;
        float[] fArr2 = this.f2338b;
        float f4 = fArr2[i];
        return f4 + (f3 * (fArr2[length] - f4));
    }

    a(float f, float f2, float f3, float f4) {
        this(a(f, f2, f3, f4));
    }
}
