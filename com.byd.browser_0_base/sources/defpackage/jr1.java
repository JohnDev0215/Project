package defpackage;

/* JADX INFO: compiled from: OffsetEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public final class jr1 extends if0 {
    public final if0 e;
    public final float f;

    public jr1(ac1 ac1Var, float f) {
        this.e = ac1Var;
        this.f = f;
    }

    @Override // defpackage.if0
    public final boolean b() {
        return this.e.b();
    }

    @Override // defpackage.if0
    public final void e(float f, float f2, float f3, xj2 xj2Var) {
        this.e.e(f, f2 - this.f, f3, xj2Var);
    }
}
