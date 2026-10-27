package defpackage;

/* JADX INFO: compiled from: OffsetEdgeTreatment.java */
/* JADX INFO: loaded from: classes.dex */
public final class jk extends fk {
    public final fk a;
    public final float b;

    public jk(fk fkVar, float f) {
        this.a = fkVar;
        this.b = f;
    }

    @Override // defpackage.fk
    public boolean a() {
        return this.a.a();
    }

    @Override // defpackage.fk
    public void b(float f, float f2, float f3, ok okVar) {
        this.a.b(f, f2 - this.b, f3, okVar);
    }
}
