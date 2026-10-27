package c.b.a.a.v;

/* JADX INFO: compiled from: MarkerEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public final class g extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f1850a;

    public g(float f) {
        this.f1850a = f - 0.001f;
    }

    @Override // c.b.a.a.v.f
    public boolean a() {
        return true;
    }

    @Override // c.b.a.a.v.f
    public void b(float f, float f2, float f3, o oVar) {
        float fSqrt = (float) ((((double) this.f1850a) * Math.sqrt(2.0d)) / 2.0d);
        float fSqrt2 = (float) Math.sqrt(Math.pow(this.f1850a, 2.0d) - Math.pow(fSqrt, 2.0d));
        oVar.n(f2 - fSqrt, ((float) (-((((double) this.f1850a) * Math.sqrt(2.0d)) - ((double) this.f1850a)))) + fSqrt2);
        oVar.m(f2, (float) (-((((double) this.f1850a) * Math.sqrt(2.0d)) - ((double) this.f1850a))));
        oVar.m(f2 + fSqrt, ((float) (-((((double) this.f1850a) * Math.sqrt(2.0d)) - ((double) this.f1850a)))) + fSqrt2);
    }
}
