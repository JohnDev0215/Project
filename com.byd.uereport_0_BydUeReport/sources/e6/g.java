package e6;

/* JADX INFO: compiled from: MarkerEdgeTreatment.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f4652a;

    public g(float f8) {
        this.f4652a = f8 - 0.001f;
    }

    @Override // e6.f
    public boolean a() {
        return true;
    }

    @Override // e6.f
    public void b(float f8, float f9, float f10, o oVar) {
        float fSqrt = (float) ((((double) this.f4652a) * Math.sqrt(2.0d)) / 2.0d);
        float fSqrt2 = (float) Math.sqrt(Math.pow(this.f4652a, 2.0d) - Math.pow(fSqrt, 2.0d));
        oVar.n(f9 - fSqrt, ((float) (-((((double) this.f4652a) * Math.sqrt(2.0d)) - ((double) this.f4652a)))) + fSqrt2);
        oVar.m(f9, (float) (-((((double) this.f4652a) * Math.sqrt(2.0d)) - ((double) this.f4652a))));
        oVar.m(f9 + fSqrt, ((float) (-((((double) this.f4652a) * Math.sqrt(2.0d)) - ((double) this.f4652a)))) + fSqrt2);
    }
}
