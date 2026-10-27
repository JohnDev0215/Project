package s1;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;

/* JADX INFO: compiled from: MatrixEvaluator.java */
/* JADX INFO: loaded from: classes.dex */
public class g implements TypeEvaluator<Matrix> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float[] f4911a = new float[9];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float[] f4912b = new float[9];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Matrix f4913c = new Matrix();

    public Matrix a(float f3, Matrix matrix, Matrix matrix2) {
        matrix.getValues(this.f4911a);
        matrix2.getValues(this.f4912b);
        for (int i3 = 0; i3 < 9; i3++) {
            float[] fArr = this.f4912b;
            float f4 = fArr[i3];
            float[] fArr2 = this.f4911a;
            fArr[i3] = fArr2[i3] + ((f4 - fArr2[i3]) * f3);
        }
        this.f4913c.setValues(this.f4912b);
        return this.f4913c;
    }
}
