package defpackage;

import defpackage.m00;

/* JADX INFO: compiled from: CoroutineContext.kt */
/* JADX INFO: loaded from: classes.dex */
public final class n00 extends z41 implements nr0<m00, m00.b, m00> {
    public final /* synthetic */ ea2<m00> f;
    public final /* synthetic */ boolean g = true;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n00(ea2 ea2Var) {
        super(2);
        this.f = ea2Var;
    }

    /* JADX WARN: Type inference failed for: r3v3, types: [T, m00] */
    @Override // defpackage.nr0
    public final m00 d(m00 m00Var, m00.b bVar) {
        m00 m00Var2 = m00Var;
        m00.b bVar2 = bVar;
        if (!(bVar2 instanceof j00)) {
            return m00Var2.plus(bVar2);
        }
        ea2<m00> ea2Var = this.f;
        if (ea2Var.e.get(bVar2.getKey()) == null) {
            return m00Var2.plus(this.g ? ((j00) bVar2).v0() : (j00) bVar2);
        }
        ea2Var.e = ea2Var.e.minusKey(bVar2.getKey());
        return m00Var2.plus(((j00) bVar2).U0());
    }
}
