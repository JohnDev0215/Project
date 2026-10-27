package e6;

/* JADX INFO: compiled from: OffsetEdgeTreatment.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final f f4701a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f4702b;

    public j(f fVar, float f8) {
        this.f4701a = fVar;
        this.f4702b = f8;
    }

    @Override // e6.f
    public boolean a() {
        return this.f4701a.a();
    }

    @Override // e6.f
    public void b(float f8, float f9, float f10, o oVar) {
        this.f4701a.b(f8, f9 - this.f4702b, f10, oVar);
    }
}
