package d.b.a.a.m;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

/* JADX INFO: compiled from: MatrixEvaluator.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements TypeEvaluator<Matrix> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float[] f4770a = new float[9];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float[] f4771b = new float[9];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Matrix f4772c = new Matrix();

    public Matrix a(float f2, Matrix matrix, Matrix matrix2) {
        matrix.getValues(this.f4770a);
        matrix2.getValues(this.f4771b);
        for (int i = 0; i < 9; i++) {
            float[] fArr = this.f4771b;
            float f3 = fArr[i];
            float[] fArr2 = this.f4770a;
            fArr[i] = fArr2[i] + ((f3 - fArr2[i]) * f2);
        }
        this.f4772c.setValues(this.f4771b);
        return this.f4772c;
    }
}
