package defpackage;

import defpackage.qh0;

/* JADX INFO: compiled from: String16.java */
/* JADX INFO: loaded from: classes3.dex */
public final class mu2 extends hv2 {
    public static final t50 c = new t50(16, 0);
    public short[] b;

    public mu2() {
        super(16);
    }

    @Override // defpackage.hv2
    public final void a(qh0 qh0Var) {
        qh0 qh0VarT = qh0Var.t(c);
        short[] sArr = this.b;
        if (sArr == null) {
            qh0VarT.q(8, n3.F0(0));
            return;
        }
        qh0 qh0VarS = qh0VarT.s(2, sArr.length, 8, -1);
        qh0.a aVar = qh0VarS.b;
        aVar.b.position(qh0VarS.a + 8);
        aVar.b.asShortBuffer().put(sArr);
    }
}
