package d.b.a.a.w;

/* JADX INFO: compiled from: MarkerEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public final class g extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float f4830a;

    public g(float f2) {
        this.f4830a = f2 - 0.001f;
    }

    @Override // d.b.a.a.w.f
    boolean a() {
        return true;
    }

    @Override // d.b.a.a.w.f
    public void b(float f2, float f3, float f4, o oVar) {
        float fSqrt = (float) ((((double) this.f4830a) * Math.sqrt(2.0d)) / 2.0d);
        float fSqrt2 = (float) Math.sqrt(Math.pow(this.f4830a, 2.0d) - Math.pow(fSqrt, 2.0d));
        oVar.n(f3 - fSqrt, ((float) (-((((double) this.f4830a) * Math.sqrt(2.0d)) - ((double) this.f4830a)))) + fSqrt2);
        oVar.m(f3, (float) (-((((double) this.f4830a) * Math.sqrt(2.0d)) - ((double) this.f4830a))));
        oVar.m(f3 + fSqrt, ((float) (-((((double) this.f4830a) * Math.sqrt(2.0d)) - ((double) this.f4830a)))) + fSqrt2);
    }
}
