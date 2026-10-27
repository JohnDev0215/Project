package p;

/* JADX INFO: loaded from: classes.dex */
public final class h1 extends zu {
    @Override // p.zu
    public final void A0(i1 i1Var, i1 i1Var2) {
        i1Var.b = i1Var2;
    }

    @Override // p.zu
    public final void B0(i1 i1Var, Thread thread) {
        i1Var.a = thread;
    }

    @Override // p.zu
    public final boolean m(j1 j1Var, e1 e1Var, e1 e1Var2) {
        synchronized (j1Var) {
            try {
                if (j1Var.e != e1Var) {
                    return false;
                }
                j1Var.e = e1Var2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // p.zu
    public final boolean n(j1 j1Var, Object obj, Object obj2) {
        synchronized (j1Var) {
            try {
                if (j1Var.c != obj) {
                    return false;
                }
                j1Var.c = obj2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // p.zu
    public final boolean o(j1 j1Var, i1 i1Var, i1 i1Var2) {
        synchronized (j1Var) {
            try {
                if (j1Var.k != i1Var) {
                    return false;
                }
                j1Var.k = i1Var2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
