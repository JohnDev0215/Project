package c.a.a.a.m;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

/* JADX INFO: compiled from: MatrixEvaluator.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements TypeEvaluator<Matrix> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float[] f2315a = new float[9];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float[] f2316b = new float[9];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Matrix f2317c = new Matrix();

    public Matrix a(float f, Matrix matrix, Matrix matrix2) {
        matrix.getValues(this.f2315a);
        matrix2.getValues(this.f2316b);
        for (int i = 0; i < 9; i++) {
            float[] fArr = this.f2316b;
            float f2 = fArr[i];
            float[] fArr2 = this.f2315a;
            fArr[i] = fArr2[i] + ((f2 - fArr2[i]) * f);
        }
        this.f2317c.setValues(this.f2316b);
        return this.f2317c;
    }
}
