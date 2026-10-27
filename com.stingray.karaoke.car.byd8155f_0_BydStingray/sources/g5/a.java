package G5;

import A9.k;
import E5.q;
import E5.u;
import E5.x;

/* JADX INFO: loaded from: classes.dex */
public final class a extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q f2680a;

    public a(q qVar) {
        this.f2680a = qVar;
    }

    @Override // E5.q
    public final Object a(u uVar) {
        if (uVar.z() != 9) {
            return this.f2680a.a(uVar);
        }
        throw new k("Unexpected null at " + uVar.k());
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        if (obj != null) {
            this.f2680a.d(xVar, obj);
        } else {
            throw new k("Unexpected null at " + xVar.j());
        }
    }

    public final String toString() {
        return this.f2680a + ".nonNull()";
    }
}
