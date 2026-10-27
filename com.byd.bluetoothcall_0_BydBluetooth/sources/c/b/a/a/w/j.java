package c.b.a.a.w;

/* JADX INFO: compiled from: OffsetEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public final class j extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final f f2765a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f2766b;

    public j(f fVar, float f) {
        this.f2765a = fVar;
        this.f2766b = f;
    }

    @Override // c.b.a.a.w.f
    boolean a() {
        return this.f2765a.a();
    }

    @Override // c.b.a.a.w.f
    public void b(float f, float f2, float f3, o oVar) {
        this.f2765a.b(f, f2 - this.f2766b, f3, oVar);
    }
}
