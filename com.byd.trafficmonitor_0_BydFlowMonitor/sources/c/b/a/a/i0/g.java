package c.b.a.a.i0;

/* JADX INFO: compiled from: MarkerEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public final class g extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f3856a;

    public g(float f2) {
        this.f3856a = f2 - 0.001f;
    }

    @Override // c.b.a.a.i0.f
    public boolean a() {
        return true;
    }

    @Override // c.b.a.a.i0.f
    public void b(float f2, float f3, float f4, o oVar) {
        float fSqrt = (float) ((((double) this.f3856a) * Math.sqrt(2.0d)) / 2.0d);
        float fSqrt2 = (float) Math.sqrt(Math.pow(this.f3856a, 2.0d) - Math.pow(fSqrt, 2.0d));
        oVar.n(f3 - fSqrt, ((float) (-((((double) this.f3856a) * Math.sqrt(2.0d)) - ((double) this.f3856a)))) + fSqrt2);
        oVar.m(f3, (float) (-((((double) this.f3856a) * Math.sqrt(2.0d)) - ((double) this.f3856a))));
        oVar.m(f3 + fSqrt, ((float) (-((((double) this.f3856a) * Math.sqrt(2.0d)) - ((double) this.f3856a)))) + fSqrt2);
    }
}
