package c.a.a.a.m;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

/* JADX INFO: compiled from: MatrixEvaluator.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements TypeEvaluator<Matrix> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float[] f2019a = new float[9];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float[] f2020b = new float[9];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Matrix f2021c = new Matrix();

    public Matrix a(float f, Matrix matrix, Matrix matrix2) {
        matrix.getValues(this.f2019a);
        matrix2.getValues(this.f2020b);
        for (int i = 0; i < 9; i++) {
            float[] fArr = this.f2020b;
            float f2 = fArr[i];
            float[] fArr2 = this.f2019a;
            fArr[i] = fArr2[i] + ((f2 - fArr2[i]) * f);
        }
        this.f2021c.setValues(this.f2020b);
        return this.f2021c;
    }
}
