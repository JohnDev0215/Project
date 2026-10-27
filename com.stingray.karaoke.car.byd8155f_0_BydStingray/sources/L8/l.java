package L8;

import F8.A;
import F8.AbstractC0287w;
import F8.C0269d;
import F8.F;
import F8.H;
import F8.Y;
import Q7.AbstractC0669w;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0671y;
import T7.Q;
import b8.C1011f;
import h8.AbstractC1387d;
import java.util.List;
import n7.AbstractC1998m;
import v8.AbstractC2564d;

/* JADX INFO: loaded from: classes.dex */
public final class l implements e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final l f4086b = new l(0);
    public static final l c = new l(1);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4087a;

    public /* synthetic */ l(int i7) {
        this.f4087a = i7;
    }

    @Override // L8.e
    public final String a() {
        switch (this.f4087a) {
            case 0:
                return "second parameter must be of type KProperty<*> or its supertype";
            default:
                return "should not have varargs or parameters with default values";
        }
    }

    @Override // L8.e
    public final String b(C1011f c1011f) {
        switch (this.f4087a) {
        }
        return C7.a.Q(this, c1011f);
    }

    @Override // L8.e
    public final boolean c(C1011f c1011f) {
        A aQ;
        switch (this.f4087a) {
            case 0:
                Q q4 = (Q) c1011f.D0().get(1);
                io.sentry.hints.i iVar = N7.m.d;
                A7.m.e(q4, "secondParameter");
                InterfaceC0671y interfaceC0671yJ = AbstractC2564d.j(q4);
                iVar.getClass();
                InterfaceC0652e interfaceC0652eD = AbstractC0669w.d(interfaceC0671yJ, N7.n.f4833Q);
                if (interfaceC0652eD == null) {
                    aQ = null;
                } else {
                    H.f2437m.getClass();
                    H h10 = H.f2438n;
                    List listP = interfaceC0652eD.C().p();
                    A7.m.e(listP, "kPropertyClass.typeConstructor.parameters");
                    Object objR0 = AbstractC1998m.r0(listP);
                    A7.m.e(objR0, "kPropertyClass.typeConstructor.parameters.single()");
                    aQ = C0269d.q(h10, interfaceC0652eD, AbstractC1387d.j(new F((Q7.Q) objR0)));
                }
                if (aQ == null) {
                    return false;
                }
                AbstractC0287w abstractC0287wB = q4.b();
                A7.m.e(abstractC0287wB, "secondParameter.type");
                return G8.d.f2722a.b(aQ, Y.g(abstractC0287wB, false));
            default:
                List<Q> listD0 = c1011f.D0();
                A7.m.e(listD0, "functionDescriptor.valueParameters");
                if (listD0.isEmpty()) {
                    return true;
                }
                for (Q q10 : listD0) {
                    A7.m.e(q10, "it");
                    if (AbstractC2564d.a(q10) || q10.f8826v != null) {
                        return false;
                    }
                }
                return true;
        }
    }
}
