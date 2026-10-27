package p;

/* JADX INFO: loaded from: classes.dex */
public final class qa0 extends t61 {
    public final /* synthetic */ int m;
    public final Object n;

    public qa0(int i, Object obj) {
        this.m = i;
        this.n = obj;
    }

    @Override // p.ry0
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        lq2 lq2Var = lq2.a;
        switch (this.m) {
            case 0:
                l((Throwable) obj);
                break;
            case 1:
                l((Throwable) obj);
                break;
            default:
                l((Throwable) obj);
                break;
        }
        return lq2Var;
    }

    @Override // p.t61
    public final void l(Throwable th) {
        int i = this.m;
        Object obj = this.n;
        switch (i) {
            case 0:
                ((oa0) obj).dispose();
                break;
            case 1:
                ((ry0) obj).invoke(th);
                break;
            default:
                Object objI = k().I();
                if (!(objI instanceof hv)) {
                    ((zp) obj).k(y61.a(objI));
                } else {
                    ((zp) obj).k(kotlin.a.b(((hv) objI).a));
                }
                break;
        }
    }
}
