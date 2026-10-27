package c.b.a.a.w;

/* JADX INFO: compiled from: MarkerEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public final class g extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float f2187a;

    public g(float f) {
        this.f2187a = f - 0.001f;
    }

    @Override // c.b.a.a.w.f
    boolean a() {
        return true;
    }

    @Override // c.b.a.a.w.f
    public void b(float f, float f2, float f3, o oVar) {
        float fSqrt = (float) ((((double) this.f2187a) * Math.sqrt(2.0d)) / 2.0d);
        float fSqrt2 = (float) Math.sqrt(Math.pow(this.f2187a, 2.0d) - Math.pow(fSqrt, 2.0d));
        oVar.n(f2 - fSqrt, ((float) (-((((double) this.f2187a) * Math.sqrt(2.0d)) - ((double) this.f2187a)))) + fSqrt2);
        oVar.m(f2, (float) (-((((double) this.f2187a) * Math.sqrt(2.0d)) - ((double) this.f2187a))));
        oVar.m(f2 + fSqrt, ((float) (-((((double) this.f2187a) * Math.sqrt(2.0d)) - ((double) this.f2187a)))) + fSqrt2);
    }
}
