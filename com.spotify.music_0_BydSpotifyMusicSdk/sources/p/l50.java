package p;

import com.google.common.base.Optional;
import com.spotify.automotive.baseui.login.AAMLoginScannableActivity;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes.dex */
public final class l50 implements a7 {
    public final h50 a;
    public final ct1 b;
    public final wy1 c;
    public final wy1 d;

    public l50(h50 h50Var) {
        this.a = h50Var;
        dt1 dt1Var = new dt1(new m0(h50Var.m, 29));
        wy1 wy1Var = h50Var.n;
        ct1 ct1Var = new ct1(new ct1(wy1Var, 0), 4);
        m0 m0Var = new m0(wy1Var, 28);
        p50 p50Var = new p50(new ct1(h50Var.S, 3));
        b51 b51Var = q50.a;
        this.b = new ct1(new jj0(h50Var.l, dt1Var, ct1Var, m0Var, p50Var, b51Var, b51Var), 1);
        this.c = nd2.a(new ed(p02.c, ln1.a, vr2.k, 17));
        wy1 wy1VarA = nd2.a(q30.d);
        this.d = nd2.a(new c21(this.b, zb2.c, this.c, h50Var.X, ci1.l, h50Var.D, wy1VarA, 1));
    }

    @Override // p.a7
    public final void a(Object obj) {
        AAMLoginScannableActivity aAMLoginScannableActivity = (AAMLoginScannableActivity) obj;
        h50 h50Var = this.a;
        aAMLoginScannableActivity.G = h50Var.b();
        aAMLoginScannableActivity.U = new p01((ur2) this.d.get());
        aAMLoginScannableActivity.V = new com.spotify.eventsender.eventsender.b(h50Var.a, dt1.a(m0.d((OkHttpClient) h50Var.m.get())), ct1.c(ct1.a((ut) h50Var.n.get())), m0.c((ut) h50Var.n.get()), Optional.e(ct1.b((f61) h50Var.S.get())), Optional.a(), Optional.a());
    }
}
