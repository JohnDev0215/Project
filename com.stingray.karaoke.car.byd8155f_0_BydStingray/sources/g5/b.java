package G5;

import E5.q;
import E5.u;
import E5.x;

/* JADX INFO: loaded from: classes.dex */
public final class b extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q f2681a;

    public b(q qVar) {
        this.f2681a = qVar;
    }

    @Override // E5.q
    public final Object a(u uVar) {
        if (uVar.z() != 9) {
            return this.f2681a.a(uVar);
        }
        uVar.v();
        return null;
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        if (obj == null) {
            xVar.m();
        } else {
            this.f2681a.d(xVar, obj);
        }
    }

    public final String toString() {
        return this.f2681a + ".nullSafe()";
    }
}
