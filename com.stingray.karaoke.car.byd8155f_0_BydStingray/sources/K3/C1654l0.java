package k3;

import android.os.Looper;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Objects;

/* JADX INFO: renamed from: k3.l0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1654l0 implements InterfaceC1656m0, H0 {
    public final /* synthetic */ e2.S l;

    public /* synthetic */ C1654l0(e2.S s6) {
        this.l = s6;
    }

    @Override // k3.H0
    public void c(InterfaceC1677x0 interfaceC1677x0, int i7) {
        interfaceC1677x0.k(i7, this.l);
    }

    @Override // k3.InterfaceC1656m0
    public void e(C1628b0 c1628b0) {
        boolean z6;
        if (c1628b0.v()) {
            e2.S s6 = c1628b0.f17920u;
            int i7 = AbstractC1343A.f15195a;
            e2.S s10 = this.l;
            if (Objects.equals(s6, s10)) {
                return;
            }
            c1628b0.f17920u = s10;
            e2.S s11 = c1628b0.f17921v;
            e2.S sX0 = C1628b0.X0(c1628b0.f17919t, s10);
            c1628b0.f17921v = sX0;
            if (sX0.equals(s11)) {
                z6 = false;
            } else {
                d5.d0 d0Var = c1628b0.f17917r;
                d5.d0 d0VarR1 = C1628b0.r1(c1628b0.f17916q, c1628b0.f17915p, c1628b0.f17918s, c1628b0.f17921v);
                c1628b0.f17917r = d0VarR1;
                z6 = !d0VarR1.equals(d0Var);
                c1628b0.f17910i.f(13, new C1678y(c1628b0, 19));
            }
            if (z6) {
                C1672v c1672v = c1628b0.f17904a;
                c1672v.getClass();
                AbstractC1346c.j(Looper.myLooper() == c1672v.f18137e.getLooper());
                InterfaceC1668t interfaceC1668t = c1672v.d;
                interfaceC1668t.getClass();
                interfaceC1668t.n();
            }
        }
    }
}
