package defpackage;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

/* JADX INFO: compiled from: TransitionUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class cf3 implements TypeEvaluator<Matrix> {
    public final float[] a = new float[9];
    public final float[] b = new float[9];
    public final Matrix c = new Matrix();

    @Override // android.animation.TypeEvaluator
    public final Matrix evaluate(float f, Matrix matrix, Matrix matrix2) {
        float[] fArr = this.a;
        matrix.getValues(fArr);
        float[] fArr2 = this.b;
        matrix2.getValues(fArr2);
        for (int i = 0; i < 9; i++) {
            float f2 = fArr2[i];
            float f3 = fArr[i];
            fArr2[i] = C0173a.f(f2, f3, f, f3);
        }
        Matrix matrix3 = this.c;
        matrix3.setValues(fArr2);
        return matrix3;
    }
}
