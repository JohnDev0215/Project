package j5;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

/* JADX INFO: compiled from: MatrixEvaluator.java */
/* JADX INFO: loaded from: classes2.dex */
public class g implements TypeEvaluator<Matrix> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float[] f5588a = new float[9];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float[] f5589b = new float[9];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Matrix f5590c = new Matrix();

    public Matrix a(float f8, Matrix matrix, Matrix matrix2) {
        matrix.getValues(this.f5588a);
        matrix2.getValues(this.f5589b);
        for (int i8 = 0; i8 < 9; i8++) {
            float[] fArr = this.f5589b;
            float f9 = fArr[i8];
            float[] fArr2 = this.f5588a;
            fArr[i8] = fArr2[i8] + ((f9 - fArr2[i8]) * f8);
        }
        this.f5590c.setValues(this.f5589b);
        return this.f5590c;
    }
}
