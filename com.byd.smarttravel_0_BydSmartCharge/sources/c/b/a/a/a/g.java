package c.b.a.a.a;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

/* JADX INFO: compiled from: MatrixEvaluator.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements TypeEvaluator<Matrix> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float[] f1657a = new float[9];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float[] f1658b = new float[9];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Matrix f1659c = new Matrix();

    public Matrix a(float f, Matrix matrix, Matrix matrix2) {
        matrix.getValues(this.f1657a);
        matrix2.getValues(this.f1658b);
        for (int i = 0; i < 9; i++) {
            float[] fArr = this.f1658b;
            float f2 = fArr[i];
            float[] fArr2 = this.f1657a;
            fArr[i] = fArr2[i] + ((f2 - fArr2[i]) * f);
        }
        this.f1659c.setValues(this.f1658b);
        return this.f1659c;
    }
}
