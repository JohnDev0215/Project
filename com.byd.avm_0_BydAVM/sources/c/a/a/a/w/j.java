package c.a.a.a.w;

/* JADX INFO: compiled from: OffsetEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public final class j extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final f f2077a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f2078b;

    public j(f fVar, float f) {
        this.f2077a = fVar;
        this.f2078b = f;
    }

    @Override // c.a.a.a.w.f
    boolean a() {
        return this.f2077a.a();
    }

    @Override // c.a.a.a.w.f
    public void b(float f, float f2, float f3, o oVar) {
        this.f2077a.b(f, f2 - this.f2078b, f3, oVar);
    }
}
