package t8;

import F8.AbstractC0287w;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0671y;
import java.util.List;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: t8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C2449b extends g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final A7.o f22495b;

    /* JADX WARN: Multi-variable type inference failed */
    public C2449b(List list, InterfaceC2782k interfaceC2782k) {
        super(list);
        this.f22495b = (A7.o) interfaceC2782k;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [A7.o, z7.k] */
    @Override // t8.g
    public final AbstractC0287w a(InterfaceC0671y interfaceC0671y) {
        InterfaceC0654g interfaceC0654gN;
        A7.m.f(interfaceC0671y, "module");
        AbstractC0287w abstractC0287w = (AbstractC0287w) this.f22495b.b(interfaceC0671y);
        if (!N7.i.x(abstractC0287w) && (((interfaceC0654gN = abstractC0287w.B0().n()) == null || N7.i.q(interfaceC0654gN) == null) && !N7.i.A(abstractC0287w, N7.n.f4838V.i()) && !N7.i.A(abstractC0287w, N7.n.f4839W.i()) && !N7.i.A(abstractC0287w, N7.n.f4840X.i()))) {
            N7.i.A(abstractC0287w, N7.n.f4841Y.i());
        }
        return abstractC0287w;
    }
}
