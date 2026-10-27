package d.b.a.a.m;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

/* JADX INFO: compiled from: MatrixEvaluator.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements TypeEvaluator<Matrix> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float[] f4684a = new float[9];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float[] f4685b = new float[9];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Matrix f4686c = new Matrix();

    public Matrix a(float f2, Matrix matrix, Matrix matrix2) {
        matrix.getValues(this.f4684a);
        matrix2.getValues(this.f4685b);
        for (int i2 = 0; i2 < 9; i2++) {
            float[] fArr = this.f4685b;
            float f3 = fArr[i2];
            float[] fArr2 = this.f4684a;
            fArr[i2] = fArr2[i2] + ((f3 - fArr2[i2]) * f2);
        }
        this.f4686c.setValues(this.f4685b);
        return this.f4686c;
    }
}
