package Q;

import A.C0044v0;
import T.C0735d;
import T.C0752l0;
import T.C0761q;
import T.C0773y;
import n0.C1891t;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class B extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f5489m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0543z f5490n;
    public final /* synthetic */ boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f5491p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ b0.b f5492q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public B(InterfaceC2785n interfaceC2785n, C0543z c0543z, boolean z6, InterfaceC2785n interfaceC2785n2, b0.b bVar) {
        super(2);
        this.f5489m = interfaceC2785n;
        this.f5490n = c0543z;
        this.o = z6;
        this.f5491p = interfaceC2785n2;
        this.f5492q = bVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.Q(1264683960);
            boolean z6 = this.o;
            C0543z c0543z = this.f5490n;
            InterfaceC2785n interfaceC2785n = this.f5489m;
            if (interfaceC2785n != null) {
                C0735d.a(AbstractC0524p.f6016a.a(new C1891t(z6 ? c0543z.f6074b : c0543z.f6075e)), b0.g.b(2035552199, new A(interfaceC2785n, 0), c0761q), c0761q, 56);
            }
            c0761q.p(false);
            C0773y c0773y = AbstractC0524p.f6016a;
            C0752l0 c0752l0A = c0773y.a(new C1891t(z6 ? c0543z.f6073a : c0543z.d));
            b0.b bVar = this.f5492q;
            InterfaceC2785n interfaceC2785n2 = this.f5491p;
            C0735d.a(c0752l0A, b0.g.b(-1728894036, new C0044v0(interfaceC2785n, interfaceC2785n2, bVar, 3), c0761q), c0761q, 56);
            if (interfaceC2785n2 != null) {
                C0735d.a(c0773y.a(new C1891t(z6 ? c0543z.c : c0543z.f6076f)), b0.g.b(580312062, new A(interfaceC2785n2, 1), c0761q), c0761q, 56);
            }
        }
        return m7.q.f19267a;
    }
}
