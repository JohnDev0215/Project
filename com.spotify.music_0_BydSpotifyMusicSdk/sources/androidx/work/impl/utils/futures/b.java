package androidx.work.impl.utils.futures;

import p.d1;
import p.g1;
import p.j1;
import p.lb1;

/* JADX INFO: loaded from: classes.dex */
public final class b extends j1 {
    public final boolean j(Object obj) {
        if (obj == null) {
            obj = j1.o;
        }
        if (!j1.n.n(this, null, obj)) {
            return false;
        }
        j1.c(this);
        return true;
    }

    public final boolean k(Throwable th) {
        th.getClass();
        if (!j1.n.n(this, null, new a(th))) {
            return false;
        }
        j1.c(this);
        return true;
    }

    public final boolean l(lb1 lb1Var) {
        a aVar;
        lb1Var.getClass();
        Object obj = this.c;
        if (obj == null) {
            if (lb1Var.isDone()) {
                if (!j1.n.n(this, null, j1.f(lb1Var))) {
                    return false;
                }
                j1.c(this);
            } else {
                g1 g1Var = new g1(this, lb1Var);
                if (j1.n.n(this, null, g1Var)) {
                    try {
                        lb1Var.a(g1Var, DirectExecutor.c);
                    } catch (Throwable th) {
                        try {
                            aVar = new a(th);
                        } catch (Throwable unused) {
                            aVar = a.b;
                        }
                        j1.n.n(this, g1Var, aVar);
                    }
                } else {
                    obj = this.c;
                }
            }
            return true;
        }
        if (!(obj instanceof d1)) {
            return false;
        }
        lb1Var.cancel(((d1) obj).a);
        return false;
    }
}
