package p2;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

/* JADX INFO: compiled from: MatrixEvaluator.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements TypeEvaluator<Matrix> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float[] f8830a = new float[9];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float[] f8831b = new float[9];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Matrix f8832c = new Matrix();

    public Matrix a(float f7, Matrix matrix, Matrix matrix2) {
        matrix.getValues(this.f8830a);
        matrix2.getValues(this.f8831b);
        for (int i7 = 0; i7 < 9; i7++) {
            float[] fArr = this.f8831b;
            float f8 = fArr[i7];
            float[] fArr2 = this.f8830a;
            fArr[i7] = fArr2[i7] + ((f8 - fArr2[i7]) * f7);
        }
        this.f8832c.setValues(this.f8831b);
        return this.f8832c;
    }
}
