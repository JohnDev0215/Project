package c.b.a.a.m;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

/* JADX INFO: compiled from: MatrixEvaluator.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements TypeEvaluator<Matrix> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float[] f4018a = new float[9];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float[] f4019b = new float[9];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Matrix f4020c = new Matrix();

    public Matrix a(float f2, Matrix matrix, Matrix matrix2) {
        matrix.getValues(this.f4018a);
        matrix2.getValues(this.f4019b);
        for (int i2 = 0; i2 < 9; i2++) {
            float[] fArr = this.f4019b;
            float f3 = fArr[i2];
            float[] fArr2 = this.f4018a;
            fArr[i2] = fArr2[i2] + ((f3 - fArr2[i2]) * f2);
        }
        this.f4020c.setValues(this.f4019b);
        return this.f4020c;
    }
}
