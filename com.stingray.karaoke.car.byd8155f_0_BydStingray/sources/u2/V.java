package u2;

import B2.C0075o;
import e2.C1215x;
import k2.InterfaceC1606g;
import o2.C2026c;

/* JADX INFO: loaded from: classes.dex */
public final class V implements InterfaceC2491B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC1606g f22747a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C2026c f22748b;
    public final h3.y c;
    public x2.i d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f22749e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f22750f;

    public V(InterfaceC1606g interfaceC1606g) {
        this(interfaceC1606g, new C0075o());
    }

    @Override // u2.InterfaceC2491B
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final W c(e2.F f10) {
        q2.o oVar;
        f10.f13879b.getClass();
        InterfaceC1606g interfaceC1606g = this.f22747a;
        C2026c c2026c = this.f22748b;
        h3.y yVar = this.c;
        yVar.getClass();
        f10.f13879b.getClass();
        C1215x c1215x = f10.f13879b.c;
        if (c1215x == null) {
            oVar = q2.o.f21212a;
        } else {
            synchronized (yVar.f15499m) {
                try {
                    if (!c1215x.equals((C1215x) yVar.f15500n)) {
                        yVar.f15500n = c1215x;
                        yVar.o = h3.y.g(c1215x);
                    }
                    oVar = (q2.f) yVar.o;
                    oVar.getClass();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return new W(f10, interfaceC1606g, c2026c, oVar, this.d, this.f22749e, this.f22750f);
    }

    public V(InterfaceC1606g interfaceC1606g, B2.u uVar) {
        C2026c c2026c = new C2026c(uVar, 5);
        h3.y yVar = new h3.y(19);
        l9.n nVar = new l9.n(15);
        this.f22747a = interfaceC1606g;
        this.f22748b = c2026c;
        this.c = yVar;
        this.d = nVar;
        this.f22749e = 1048576;
    }
}
