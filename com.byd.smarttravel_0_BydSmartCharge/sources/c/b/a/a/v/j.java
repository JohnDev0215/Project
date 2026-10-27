package c.b.a.a.v;

/* JADX INFO: compiled from: OffsetEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public final class j extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f1861a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f1862b;

    public j(f fVar, float f) {
        this.f1861a = fVar;
        this.f1862b = f;
    }

    @Override // c.b.a.a.v.f
    public boolean a() {
        return this.f1861a.a();
    }

    @Override // c.b.a.a.v.f
    public void b(float f, float f2, float f3, o oVar) {
        this.f1861a.b(f, f2 - this.f1862b, f3, oVar);
    }
}
