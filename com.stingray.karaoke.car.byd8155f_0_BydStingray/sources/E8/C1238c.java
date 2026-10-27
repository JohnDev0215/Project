package e8;

import A7.m;
import F8.A;
import F8.AbstractC0268c;
import F8.AbstractC0287w;
import F8.C0269d;
import F8.H;
import F8.K;
import F8.O;
import F8.P;
import F8.T;
import H8.h;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.Q;
import S0.p;
import a.AbstractC0904a;
import h8.AbstractC1387d;
import java.util.ArrayList;
import java.util.List;
import m7.i;
import n7.AbstractC2000o;
import y8.n;

/* JADX INFO: renamed from: e8.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1238c extends T {
    public static final C1236a c = AbstractC0904a.S(2, false, true, null, 5).b(3);
    public static final C1236a d = AbstractC0904a.S(2, false, true, null, 5).b(2);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final io.sentry.internal.debugmeta.c f14488b = new io.sentry.internal.debugmeta.c(new Y4.e(19));

    @Override // F8.T
    public final O d(AbstractC0287w abstractC0287w) {
        return new P(h(abstractC0287w, new C1236a(2, false, false, null, 62)));
    }

    public final i g(A a10, InterfaceC0652e interfaceC0652e, C1236a c1236a) {
        if (a10.B0().p().isEmpty()) {
            return new i(a10, Boolean.FALSE);
        }
        if (N7.i.x(a10)) {
            O o = (O) a10.o0().get(0);
            int iA = o.a();
            AbstractC0287w abstractC0287wB = o.b();
            m.e(abstractC0287wB, "componentTypeProjection.type");
            return new i(C0269d.r(a10.t0(), a10.B0(), AbstractC1387d.j(new P(iA, h(abstractC0287wB, c1236a))), a10.F0()), Boolean.FALSE);
        }
        if (AbstractC0268c.i(a10)) {
            return new i(H8.i.c(h.f2892y, a10.B0().toString()), Boolean.FALSE);
        }
        n nVarC0 = interfaceC0652e.C0(this);
        m.e(nVarC0, "declaration.getMemberScope(this)");
        H hT0 = a10.t0();
        K kC = interfaceC0652e.C();
        m.e(kC, "declaration.typeConstructor");
        List<Q> listP = interfaceC0652e.C().p();
        m.e(listP, "declaration.typeConstructor.parameters");
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(listP, 10));
        for (Q q4 : listP) {
            m.e(q4, "parameter");
            io.sentry.internal.debugmeta.c cVar = this.f14488b;
            arrayList.add(Y4.e.C(q4, c1236a, cVar, cVar.I(q4, c1236a)));
        }
        return new i(C0269d.t(hT0, kC, arrayList, a10.F0(), nVarC0, new p(interfaceC0652e, this, a10, c1236a)), Boolean.TRUE);
    }

    public final AbstractC0287w h(AbstractC0287w abstractC0287w, C1236a c1236a) {
        InterfaceC0654g interfaceC0654gN = abstractC0287w.B0().n();
        if (interfaceC0654gN instanceof Q) {
            c1236a.getClass();
            return h(this.f14488b.I((Q) interfaceC0654gN, C1236a.a(c1236a, 0, true, null, null, 59)), c1236a);
        }
        if (!(interfaceC0654gN instanceof InterfaceC0652e)) {
            throw new IllegalStateException(("Unexpected declaration kind: " + interfaceC0654gN).toString());
        }
        InterfaceC0654g interfaceC0654gN2 = AbstractC0268c.y(abstractC0287w).B0().n();
        if (interfaceC0654gN2 instanceof InterfaceC0652e) {
            i iVarG = g(AbstractC0268c.k(abstractC0287w), (InterfaceC0652e) interfaceC0654gN, c);
            A a10 = (A) iVarG.l;
            boolean zBooleanValue = ((Boolean) iVarG.f19259m).booleanValue();
            i iVarG2 = g(AbstractC0268c.y(abstractC0287w), (InterfaceC0652e) interfaceC0654gN2, d);
            A a11 = (A) iVarG2.l;
            return (zBooleanValue || ((Boolean) iVarG2.f19259m).booleanValue()) ? new C1240e(a10, a11) : C0269d.j(a10, a11);
        }
        throw new IllegalStateException(("For some reason declaration for upper bound is not a class but \"" + interfaceC0654gN2 + "\" while for lower it's \"" + interfaceC0654gN + '\"').toString());
    }
}
