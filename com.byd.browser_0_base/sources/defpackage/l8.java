package defpackage;

/* JADX INFO: compiled from: ArrayMap.java */
/* JADX INFO: loaded from: classes3.dex */
public final class l8 extends xb1<Object, Object> {
    public final /* synthetic */ n8 d;

    public l8(n8 n8Var) {
        this.d = n8Var;
    }

    @Override // defpackage.xb1
    public final Object a(int i, int i2) {
        return this.d.f[(i << 1) + i2];
    }

    @Override // defpackage.xb1
    public final void b(int i) {
        this.d.h(i);
    }
}
