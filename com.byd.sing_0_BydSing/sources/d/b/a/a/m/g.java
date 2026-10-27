package d.b.a.a.m;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

/* JADX INFO: compiled from: MatrixEvaluator.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements TypeEvaluator<Matrix> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float[] f3953a = new float[9];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float[] f3954b = new float[9];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Matrix f3955c = new Matrix();

    public Matrix a(float f, Matrix matrix, Matrix matrix2) {
        matrix.getValues(this.f3953a);
        matrix2.getValues(this.f3954b);
        for (int i = 0; i < 9; i++) {
            float[] fArr = this.f3954b;
            float f2 = fArr[i];
            float[] fArr2 = this.f3953a;
            fArr[i] = fArr2[i] + ((f2 - fArr2[i]) * f);
        }
        this.f3955c.setValues(this.f3954b);
        return this.f3955c;
    }
}
