package c.a.a.a.w;

/* JADX INFO: compiled from: OffsetEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public final class j extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final f f2379a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f2380b;

    public j(f fVar, float f) {
        this.f2379a = fVar;
        this.f2380b = f;
    }

    @Override // c.a.a.a.w.f
    boolean a() {
        return this.f2379a.a();
    }

    @Override // c.a.a.a.w.f
    public void b(float f, float f2, float f3, o oVar) {
        this.f2379a.b(f, f2 - this.f2380b, f3, oVar);
    }
}
