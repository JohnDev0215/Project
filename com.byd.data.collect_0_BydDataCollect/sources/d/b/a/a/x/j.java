package d.b.a.a.x;

/* JADX INFO: compiled from: OffsetEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public final class j extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final f f4772a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f4773b;

    public j(f fVar, float f2) {
        this.f4772a = fVar;
        this.f4773b = f2;
    }

    @Override // d.b.a.a.x.f
    boolean b() {
        return this.f4772a.b();
    }

    @Override // d.b.a.a.x.f
    public void c(float f2, float f3, float f4, o oVar) {
        this.f4772a.c(f2, f3 - this.f4773b, f4, oVar);
    }
}
