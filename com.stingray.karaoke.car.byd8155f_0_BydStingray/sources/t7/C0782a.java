package T7;

import F8.C0269d;
import F8.Y;
import java.util.List;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: T7.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0782a implements InterfaceC2772a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ AbstractC0783b f8829m;

    public /* synthetic */ C0782a(AbstractC0783b abstractC0783b, int i7) {
        this.l = i7;
        this.f8829m = abstractC0783b;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        AbstractC0783b abstractC0783b = this.f8829m;
        switch (this.l) {
            case 0:
                y8.n nVarW0 = abstractC0783b.w0();
                N7.g gVar = new N7.g(this, 3);
                H8.f fVar = Y.f2457a;
                if (H8.i.f(abstractC0783b)) {
                    return H8.i.c(H8.h.f2889v, abstractC0783b.toString());
                }
                F8.K kC = abstractC0783b.C();
                if (kC == null) {
                    Y.a(12);
                    throw null;
                }
                if (nVarW0 == null) {
                    Y.a(13);
                    throw null;
                }
                List listD = Y.d(kC.p());
                F8.H.f2437m.getClass();
                return C0269d.t(F8.H.f2438n, kC, listD, false, nVarW0, gVar);
            case 1:
                return new y8.i(abstractC0783b.w0());
            default:
                return new u(abstractC0783b);
        }
    }
}
