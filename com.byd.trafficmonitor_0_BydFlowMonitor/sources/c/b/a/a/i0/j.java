package c.b.a.a.i0;

/* JADX INFO: compiled from: OffsetEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public final class j extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f3877a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f3878b;

    public j(f fVar, float f2) {
        this.f3877a = fVar;
        this.f3878b = f2;
    }

    @Override // c.b.a.a.i0.f
    public boolean a() {
        return this.f3877a.a();
    }

    @Override // c.b.a.a.i0.f
    public void b(float f2, float f3, float f4, o oVar) {
        this.f3877a.b(f2, f3 - this.f3878b, f4, oVar);
    }
}
