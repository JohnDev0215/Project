package defpackage;

import android.animation.TypeEvaluator;

/* JADX INFO: compiled from: FloatArrayEvaluator.java */
/* JADX INFO: loaded from: classes.dex */
public final class qn0 implements TypeEvaluator<float[]> {
    public final float[] a;

    public qn0(float[] fArr) {
        this.a = fArr;
    }

    @Override // android.animation.TypeEvaluator
    public final float[] evaluate(float f, float[] fArr, float[] fArr2) {
        float[] fArr3 = fArr;
        float[] fArr4 = fArr2;
        float[] fArr5 = this.a;
        if (fArr5 == null) {
            fArr5 = new float[fArr3.length];
        }
        for (int i = 0; i < fArr5.length; i++) {
            float f2 = fArr3[i];
            fArr5[i] = C0173a.f(fArr4[i], f2, f, f2);
        }
        return fArr5;
    }
}
