package p;

/* JADX INFO: loaded from: classes.dex */
public final class a03 extends vz2 {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object k;

    public /* synthetic */ a03(int i, Object obj) {
        this.e = i;
        this.k = obj;
    }

    @Override // p.vz2
    public final void a() {
        int i = this.e;
        Object obj = this.k;
        switch (i) {
            case 0:
                g03 g03Var = (g03) obj;
                if (g03Var.n != null) {
                    g03Var.b.e("Unbind from service.", new Object[0]);
                    g03Var.a.unbindService(g03Var.m);
                    g03Var.g = false;
                    g03Var.n = null;
                    g03Var.m = null;
                }
                g03Var.d();
                break;
            default:
                zf1 zf1Var = (zf1) obj;
                g03 g03Var2 = (g03) zf1Var.b;
                g03Var2.b.e("unlinkToDeath", new Object[0]);
                g03Var2.n.asBinder().unlinkToDeath(g03Var2.k, 0);
                g03 g03Var3 = (g03) zf1Var.b;
                g03Var3.n = null;
                g03Var3.g = false;
                break;
        }
    }
}
