package n2;

/* JADX INFO: compiled from: OffsetEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public final class j extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f4148a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f4149b;

    public j(f fVar, float f3) {
        this.f4148a = fVar;
        this.f4149b = f3;
    }

    @Override // n2.f
    public boolean a() {
        return this.f4148a.a();
    }

    @Override // n2.f
    public void b(float f3, float f4, float f5, o oVar) {
        this.f4148a.b(f3, f4 - this.f4149b, f5, oVar);
    }
}
