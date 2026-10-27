package d.b.a.a.w;

/* JADX INFO: compiled from: OffsetEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public final class j extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final f f4011a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f4012b;

    public j(f fVar, float f) {
        this.f4011a = fVar;
        this.f4012b = f;
    }

    @Override // d.b.a.a.w.f
    boolean a() {
        return this.f4011a.a();
    }

    @Override // d.b.a.a.w.f
    public void b(float f, float f2, float f3, o oVar) {
        this.f4011a.b(f, f2 - this.f4012b, f3, oVar);
    }
}
