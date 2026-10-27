package c.a.a.a.w;

/* JADX INFO: compiled from: MarkerEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public final class g extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float f2066a;

    public g(float f) {
        this.f2066a = f - 0.001f;
    }

    @Override // c.a.a.a.w.f
    boolean a() {
        return true;
    }

    @Override // c.a.a.a.w.f
    public void b(float f, float f2, float f3, o oVar) {
        float fSqrt = (float) ((((double) this.f2066a) * Math.sqrt(2.0d)) / 2.0d);
        float fSqrt2 = (float) Math.sqrt(Math.pow(this.f2066a, 2.0d) - Math.pow(fSqrt, 2.0d));
        oVar.n(f2 - fSqrt, ((float) (-((((double) this.f2066a) * Math.sqrt(2.0d)) - ((double) this.f2066a)))) + fSqrt2);
        oVar.m(f2, (float) (-((((double) this.f2066a) * Math.sqrt(2.0d)) - ((double) this.f2066a))));
        oVar.m(f2 + fSqrt, ((float) (-((((double) this.f2066a) * Math.sqrt(2.0d)) - ((double) this.f2066a)))) + fSqrt2);
    }
}
