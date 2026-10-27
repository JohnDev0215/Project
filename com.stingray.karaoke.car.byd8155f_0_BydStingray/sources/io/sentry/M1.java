package io.sentry;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class M1 implements O0, U1 {
    public final /* synthetic */ P1 l;

    public /* synthetic */ M1(P1 p12) {
        this.l = p12;
    }

    @Override // io.sentry.U1
    public void a(S1 s12) {
        P1 p12 = this.l;
        d2 d2Var = p12.f15922q;
        if (d2Var != null) {
            d2Var.h(s12);
        }
        O1 o12 = p12.f15913f;
        c2 c2Var = p12.f15923r;
        if (c2Var.f16485p == null) {
            if (o12.f15908a) {
                p12.v(o12.f15909b, null);
            }
        } else if (!c2Var.o || p12.G()) {
            p12.m();
        }
    }

    @Override // io.sentry.O0
    public void i(N0 n02) {
        P1 p12 = this.l;
        p12.getClass();
        synchronized (n02.f15902m) {
            if (n02.f15893a == p12) {
                n02.a();
            }
        }
    }
}
