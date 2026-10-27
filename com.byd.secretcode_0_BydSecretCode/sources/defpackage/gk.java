package defpackage;

/* JADX INFO: compiled from: MarkerEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public final class gk extends fk {
    public final float a;

    public gk(float f) {
        this.a = f - 0.001f;
    }

    @Override // defpackage.fk
    public boolean a() {
        return true;
    }

    @Override // defpackage.fk
    public void b(float f, float f2, float f3, ok okVar) {
        float fSqrt = (float) ((((double) this.a) * Math.sqrt(2.0d)) / 2.0d);
        float fSqrt2 = (float) Math.sqrt(Math.pow(this.a, 2.0d) - Math.pow(fSqrt, 2.0d));
        okVar.n(f2 - fSqrt, ((float) (-((((double) this.a) * Math.sqrt(2.0d)) - ((double) this.a)))) + fSqrt2);
        okVar.m(f2, (float) (-((((double) this.a) * Math.sqrt(2.0d)) - ((double) this.a))));
        okVar.m(f2 + fSqrt, ((float) (-((((double) this.a) * Math.sqrt(2.0d)) - ((double) this.a)))) + fSqrt2);
    }
}
