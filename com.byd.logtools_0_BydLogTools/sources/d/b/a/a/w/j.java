package d.b.a.a.w;

/* JADX INFO: compiled from: OffsetEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public final class j extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final f f4849a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f4850b;

    public j(f fVar, float f2) {
        this.f4849a = fVar;
        this.f4850b = f2;
    }

    @Override // d.b.a.a.w.f
    boolean a() {
        return this.f4849a.a();
    }

    @Override // d.b.a.a.w.f
    public void b(float f2, float f3, float f4, o oVar) {
        this.f4849a.b(f2, f3 - this.f4850b, f4, oVar);
    }
}
