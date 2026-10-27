package B8;

import B5.K;
import F0.C0229t0;
import F0.R0;
import K.C0331b0;
import Q7.InterfaceC0657j;
import j8.G;
import java.util.List;
import l0.C1717h;
import l0.C1723n;
import n7.AbstractC1998m;
import r.C2257p;
import t.C2422f;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class p extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f794m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f795n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f796p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(s sVar, boolean z6, G g7) {
        super(0);
        this.o = sVar;
        this.f795n = z6;
        this.f796p = g7;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() {
        List listZ0;
        R0 r02;
        switch (this.f794m) {
            case 0:
                s sVar = (s) this.o;
                K kA = sVar.a((InterfaceC0657j) sVar.f805a.c);
                if (kA != null) {
                    k kVar = sVar.f805a;
                    boolean z6 = this.f795n;
                    G g7 = (G) this.f796p;
                    listZ0 = z6 ? AbstractC1998m.z0(((i) kVar.f779a).f764e.t(kA, g7)) : AbstractC1998m.z0(((i) kVar.f779a).f764e.n(kA, g7));
                } else {
                    listZ0 = null;
                }
                return listZ0 == null ? n7.u.l : listZ0;
            case 1:
                if (this.f795n) {
                    C2257p c2257p = (C2257p) this.o;
                    c2257p.getClass();
                    String str = (String) this.f796p;
                    A7.m.f(str, "key");
                    ((C2422f) c2257p.d).d(str);
                }
                return m7.q.f19267a;
            default:
                C0331b0 c0331b0 = (C0331b0) this.o;
                if (!c0331b0.b()) {
                    ((C1723n) this.f796p).a(C1717h.f18335q);
                } else if (!this.f795n && (r02 = c0331b0.c) != null) {
                    ((C0229t0) r02).b();
                }
                return Boolean.TRUE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(C0331b0 c0331b0, C1723n c1723n, boolean z6) {
        super(0);
        this.o = c0331b0;
        this.f796p = c1723n;
        this.f795n = z6;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(boolean z6, C2257p c2257p, String str) {
        super(0);
        this.f795n = z6;
        this.o = c2257p;
        this.f796p = str;
    }
}
