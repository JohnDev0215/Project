package k3;

import h3.C1350a;
import io.sentry.C1458d0;

/* JADX INFO: renamed from: k3.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC1630c implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ I0 f17926m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1679y0 f17927n;

    public /* synthetic */ RunnableC1630c(I0 i02, C1679y0 c1679y0, int i7) {
        this.l = i7;
        this.f17926m = i02;
        this.f17927n = c1679y0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                I0 i02 = this.f17926m;
                if (!i02.h()) {
                    if (i02.B) {
                        C1679y0 c1679y0 = this.f17927n;
                        if (!I0.i(c1679y0)) {
                            if (i02.g(c1679y0)) {
                                i02.B = false;
                            }
                        }
                    }
                    i02.f17798e.getClass();
                    break;
                }
                break;
            case 1:
                this.f17926m.f17800g.t1(this.f17927n, Integer.MIN_VALUE, 12, m1.v1(new X0(5)));
                break;
            case 2:
                this.f17926m.f17800g.t1(this.f17927n, Integer.MIN_VALUE, 11, m1.v1(new X0(1)));
                break;
            case 3:
                this.f17926m.f17800g.t1(this.f17927n, Integer.MIN_VALUE, 3, m1.v1(new X0(12)));
                break;
            case 4:
                this.f17926m.f17800g.t1(this.f17927n, Integer.MIN_VALUE, 1, m1.v1(new C1350a(28)));
                break;
            case 5:
                m1 m1Var = this.f17926m.f17800g;
                C1679y0 c1679y02 = this.f17927n;
                m1Var.getClass();
                m1Var.t1(c1679y02, Integer.MIN_VALUE, 1, m1.v1(new C1458d0(m1Var, 20, c1679y02)));
                break;
            case 6:
                m1 m1Var2 = this.f17926m.f17800g;
                C1679y0 c1679y03 = this.f17927n;
                m1Var2.getClass();
                m1Var2.t1(c1679y03, Integer.MIN_VALUE, 1, m1.v1(new C1458d0(m1Var2, 20, c1679y03)));
                break;
            case 7:
                this.f17926m.f17800g.t1(this.f17927n, Integer.MIN_VALUE, 1, m1.v1(new C1350a(28)));
                break;
            case 8:
                this.f17926m.f17800g.t1(this.f17927n, Integer.MIN_VALUE, 9, m1.v1(new X0(6)));
                break;
            default:
                this.f17926m.f17800g.t1(this.f17927n, Integer.MIN_VALUE, 7, m1.v1(new X0(2)));
                break;
        }
    }
}
