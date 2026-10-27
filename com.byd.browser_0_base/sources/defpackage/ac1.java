package defpackage;

/* JADX INFO: compiled from: MarkerEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public final class ac1 extends if0 {
    public final float e;

    public ac1(float f) {
        this.e = f - 0.001f;
    }

    @Override // defpackage.if0
    public final void e(float f, float f2, float f3, xj2 xj2Var) {
        double d = this.e;
        float fSqrt = (float) ((Math.sqrt(2.0d) * d) / 2.0d);
        float fSqrt2 = (float) Math.sqrt(Math.pow(d, 2.0d) - Math.pow(fSqrt, 2.0d));
        xj2Var.e(f2 - fSqrt, ((float) (-((Math.sqrt(2.0d) * d) - d))) + fSqrt2, 270.0f, 0.0f);
        xj2Var.d(f2, (float) (-((Math.sqrt(2.0d) * d) - d)));
        xj2Var.d(f2 + fSqrt, ((float) (-((Math.sqrt(2.0d) * d) - d))) + fSqrt2);
    }
}
