package n2;

/* JADX INFO: compiled from: MarkerEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public final class g extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f4098a;

    public g(float f3) {
        this.f4098a = f3 - 0.001f;
    }

    @Override // n2.f
    public boolean a() {
        return true;
    }

    @Override // n2.f
    public void b(float f3, float f4, float f5, o oVar) {
        float fSqrt = (float) ((((double) this.f4098a) * Math.sqrt(2.0d)) / 2.0d);
        float fSqrt2 = (float) Math.sqrt(Math.pow(this.f4098a, 2.0d) - Math.pow(fSqrt, 2.0d));
        oVar.n(f4 - fSqrt, ((float) (-((((double) this.f4098a) * Math.sqrt(2.0d)) - ((double) this.f4098a)))) + fSqrt2);
        oVar.m(f4, (float) (-((((double) this.f4098a) * Math.sqrt(2.0d)) - ((double) this.f4098a))));
        oVar.m(f4 + fSqrt, ((float) (-((((double) this.f4098a) * Math.sqrt(2.0d)) - ((double) this.f4098a)))) + fSqrt2);
    }
}
