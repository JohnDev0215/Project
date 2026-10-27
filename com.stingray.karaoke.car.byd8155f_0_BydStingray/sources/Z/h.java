package z;

import F0.W;
import K.C0356o;
import K.E;
import T.C0754m0;
import T.C0761q;
import e0.C1178r;
import z7.InterfaceC2772a;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1178r f24237a = new C1178r();

    public static void b(h hVar, C0356o c0356o, boolean z6, InterfaceC2772a interfaceC2772a) {
        g0.l lVar = g0.l.f14684a;
        hVar.getClass();
        hVar.f24237a.add(new b0.b(262103052, new E(c0356o, z6, lVar, (InterfaceC2786o) null, interfaceC2772a), true));
    }

    public final void a(C2751b c2751b, C0761q c0761q, int i7) {
        c0761q.S(1320309496);
        int i10 = (i7 & 6) == 0 ? (c0761q.f(c2751b) ? 4 : 2) | i7 : i7;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(this) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            C1178r c1178r = this.f24237a;
            int size = c1178r.size();
            for (int i11 = 0; i11 < size; i11++) {
                ((InterfaceC2786o) c1178r.get(i11)).g(c2751b, c0761q, Integer.valueOf(i10 & 14));
            }
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new W(this, c2751b, i7, 12);
        }
    }
}
