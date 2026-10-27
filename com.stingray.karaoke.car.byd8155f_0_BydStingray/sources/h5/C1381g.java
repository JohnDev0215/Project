package h5;

/* JADX INFO: renamed from: h5.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1381g extends l9.d {
    @Override // l9.d
    public final boolean E(p pVar, C1378d c1378d, C1378d c1378d2) {
        synchronized (pVar) {
            try {
                if (pVar.f15559m != c1378d) {
                    return false;
                }
                pVar.f15559m = c1378d2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // l9.d
    public final boolean F(p pVar, Object obj, Object obj2) {
        synchronized (pVar) {
            try {
                if (pVar.l != obj) {
                    return false;
                }
                pVar.l = obj2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // l9.d
    public final boolean G(p pVar, o oVar, o oVar2) {
        synchronized (pVar) {
            try {
                if (pVar.f15560n != oVar) {
                    return false;
                }
                pVar.f15560n = oVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // l9.d
    public final C1378d S(p pVar) {
        C1378d c1378d;
        C1378d c1378d2 = C1378d.d;
        synchronized (pVar) {
            c1378d = pVar.f15559m;
            if (c1378d != c1378d2) {
                pVar.f15559m = c1378d2;
            }
        }
        return c1378d;
    }

    @Override // l9.d
    public final o T(p pVar) {
        o oVar;
        o oVar2 = o.c;
        synchronized (pVar) {
            oVar = pVar.f15560n;
            if (oVar != oVar2) {
                pVar.f15560n = oVar2;
            }
        }
        return oVar;
    }

    @Override // l9.d
    public final void Y(o oVar, o oVar2) {
        oVar.f15555b = oVar2;
    }

    @Override // l9.d
    public final void Z(o oVar, Thread thread) {
        oVar.f15554a = thread;
    }
}
