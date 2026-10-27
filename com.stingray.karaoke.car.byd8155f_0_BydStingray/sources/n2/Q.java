package n2;

import android.util.Pair;
import java.io.IOException;
import u2.C2492C;
import u2.C2516t;
import u2.C2521y;
import u2.InterfaceC2497H;

/* JADX INFO: loaded from: classes.dex */
public final class Q implements InterfaceC2497H, q2.l {
    public final T l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Y4.m f19579m;

    public Q(Y4.m mVar, T t10) {
        this.f19579m = mVar;
        this.l = t10;
    }

    @Override // u2.InterfaceC2497H
    public final void B(int i7, C2492C c2492c, C2516t c2516t, C2521y c2521y) {
        Pair pairD = d(i7, c2492c);
        if (pairD != null) {
            ((h2.x) this.f19579m.f10765j).c(new N(this, pairD, c2516t, c2521y, 1));
        }
    }

    @Override // q2.l
    public final void C(int i7, C2492C c2492c) {
        Pair pairD = d(i7, c2492c);
        if (pairD != null) {
            ((h2.x) this.f19579m.f10765j).c(new O(this, pairD, 2));
        }
    }

    @Override // u2.InterfaceC2497H
    public final void P(int i7, C2492C c2492c, final C2516t c2516t, final C2521y c2521y, final IOException iOException, final boolean z6) {
        final Pair pairD = d(i7, c2492c);
        if (pairD != null) {
            ((h2.x) this.f19579m.f10765j).c(new Runnable() { // from class: n2.P
                @Override // java.lang.Runnable
                public final void run() {
                    o2.e eVar = (o2.e) this.l.f19579m.f10764i;
                    Pair pair = pairD;
                    eVar.P(((Integer) pair.first).intValue(), (C2492C) pair.second, c2516t, c2521y, iOException, z6);
                }
            });
        }
    }

    @Override // q2.l
    public final void a(int i7, C2492C c2492c, int i10) {
        Pair pairD = d(i7, c2492c);
        if (pairD != null) {
            ((h2.x) this.f19579m.f10765j).c(new h.k(this, pairD, i10, 4));
        }
    }

    @Override // q2.l
    public final void b(int i7, C2492C c2492c) {
        Pair pairD = d(i7, c2492c);
        if (pairD != null) {
            ((h2.x) this.f19579m.f10765j).c(new O(this, pairD, 0));
        }
    }

    @Override // q2.l
    public final void c(int i7, C2492C c2492c, Exception exc) {
        Pair pairD = d(i7, c2492c);
        if (pairD != null) {
            ((h2.x) this.f19579m.f10765j).c(new T1.k(this, pairD, exc, 16));
        }
    }

    public final Pair d(int i7, C2492C c2492c) {
        C2492C c2492cA;
        T t10 = this.l;
        C2492C c2492c2 = null;
        if (c2492c != null) {
            int i10 = 0;
            while (true) {
                if (i10 >= t10.c.size()) {
                    c2492cA = null;
                    break;
                }
                if (((C2492C) t10.c.get(i10)).d == c2492c.d) {
                    Object obj = t10.f19583b;
                    int i11 = Z.f19611n;
                    c2492cA = c2492c.a(Pair.create(obj, c2492c.f22663a));
                    break;
                }
                i10++;
            }
            if (c2492cA == null) {
                return null;
            }
            c2492c2 = c2492cA;
        }
        return Pair.create(Integer.valueOf(i7 + t10.d), c2492c2);
    }

    @Override // q2.l
    public final void n(int i7, C2492C c2492c) {
        Pair pairD = d(i7, c2492c);
        if (pairD != null) {
            ((h2.x) this.f19579m.f10765j).c(new O(this, pairD, 1));
        }
    }

    @Override // u2.InterfaceC2497H
    public final void s(int i7, C2492C c2492c, C2521y c2521y) {
        Pair pairD = d(i7, c2492c);
        if (pairD != null) {
            ((h2.x) this.f19579m.f10765j).c(new T1.k(this, pairD, c2521y, 15));
        }
    }

    @Override // u2.InterfaceC2497H
    public final void u(int i7, C2492C c2492c, C2516t c2516t, C2521y c2521y) {
        Pair pairD = d(i7, c2492c);
        if (pairD != null) {
            ((h2.x) this.f19579m.f10765j).c(new N(this, pairD, c2516t, c2521y, 0));
        }
    }

    @Override // u2.InterfaceC2497H
    public final void v(int i7, C2492C c2492c, C2516t c2516t, C2521y c2521y) {
        Pair pairD = d(i7, c2492c);
        if (pairD != null) {
            ((h2.x) this.f19579m.f10765j).c(new N(this, pairD, c2516t, c2521y, 2));
        }
    }
}
