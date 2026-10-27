package R;

import C0.V;
import T.C0761q;
import x.AbstractC2614C;
import x.AbstractC2631e;
import x.C0;
import x.s0;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: R.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0673a extends A7.o implements InterfaceC2786o {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0673a f7285n = new C0673a(3, 0);
    public static final C0673a o = new C0673a(3, 1);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7286m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0673a(int i7, int i10) {
        super(i7);
        this.f7286m = i10;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        switch (this.f7286m) {
            case 0:
                C0.M m10 = (C0.M) obj;
                long j3 = ((a1.a) obj3).f11027a;
                int iO = m10.O(AbstractC0675c.f7288a);
                int i7 = iO * 2;
                V vB = ((C0.J) obj2).b(io.sentry.config.a.g0(i7, 0, j3));
                return m10.m0(vB.l - i7, vB.f871m, n7.v.l, new Q.H(iO, 1, vB));
            default:
                s0 s0Var = (s0) obj;
                C0761q c0761q = (C0761q) obj2;
                ((Number) obj3).intValue();
                c0761q.Q(-1154662212);
                A a10 = A.l;
                A a11 = A.f7227m;
                Object objQ = s0Var.b(a10, a11) ? AbstractC2631e.q(67, 0, AbstractC2614C.c, 2) : (s0Var.b(a11, a10) || s0Var.b(A.f7228n, a11)) ? new C0(83, 67, AbstractC2614C.c) : AbstractC2631e.p(0.0f, 7, null);
                c0761q.p(false);
                return objQ;
        }
    }
}
