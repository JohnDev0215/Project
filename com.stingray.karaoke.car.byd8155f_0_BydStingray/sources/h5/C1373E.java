package h5;

/* JADX INFO: renamed from: h5.E, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1373E extends i {
    public final boolean n(y yVar) {
        C1377c c1377c;
        yVar.getClass();
        Object obj = this.l;
        if (obj == null) {
            if (yVar.isDone()) {
                if (!p.f15557q.F(this, null, p.h(yVar))) {
                    return false;
                }
                p.e(this, false);
            } else {
                RunnableC1380f runnableC1380f = new RunnableC1380f(this, yVar);
                if (p.f15557q.F(this, null, runnableC1380f)) {
                    try {
                        yVar.b(runnableC1380f, r.l);
                    } catch (Throwable th) {
                        try {
                            c1377c = new C1377c(th);
                        } catch (Error | Exception unused) {
                            c1377c = C1377c.f15542b;
                        }
                        p.f15557q.F(this, runnableC1380f, c1377c);
                    }
                } else {
                    obj = this.l;
                }
            }
            return true;
        }
        if (!(obj instanceof C1375a)) {
            return false;
        }
        yVar.cancel(((C1375a) obj).f15540a);
        return false;
    }
}
