package B8;

import Q7.AbstractC0669w;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0671y;
import T.C0746i0;
import a.AbstractC0904a;
import h8.AbstractC1387d;
import j8.Q;
import l8.InterfaceC1788f;
import o8.C2057b;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class z extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f821m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ D f822n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ z(D d, int i7) {
        super(1);
        this.f821m = i7;
        this.f822n = d;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f821m) {
            case 0:
                int iIntValue = ((Number) obj).intValue();
                k kVar = (k) this.f822n.f747m;
                C2057b c2057bD = AbstractC0904a.D((InterfaceC1788f) kVar.f780b, iIntValue);
                boolean z6 = c2057bD.c;
                i iVar = (i) kVar.f779a;
                return z6 ? iVar.b(c2057bD) : AbstractC0669w.e(iVar.f763b, c2057bD);
            case 1:
                int iIntValue2 = ((Number) obj).intValue();
                k kVar2 = (k) this.f822n.f747m;
                C2057b c2057bD2 = AbstractC0904a.D((InterfaceC1788f) kVar2.f780b, iIntValue2);
                if (c2057bD2.c) {
                    return null;
                }
                InterfaceC0671y interfaceC0671y = ((i) kVar2.f779a).f763b;
                A7.m.f(interfaceC0671y, "<this>");
                InterfaceC0654g interfaceC0654gE = AbstractC0669w.e(interfaceC0671y, c2057bD2);
                if (interfaceC0654gE instanceof D8.v) {
                    return (D8.v) interfaceC0654gE;
                }
                return null;
            default:
                Q q4 = (Q) obj;
                A7.m.f(q4, "it");
                return AbstractC1387d.m(q4, (C0746i0) ((k) this.f822n.f747m).d);
        }
    }
}
