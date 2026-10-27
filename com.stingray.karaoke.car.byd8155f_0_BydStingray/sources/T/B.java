package T;

import F0.ChoreographerFrameCallbackC0206h0;
import S8.AbstractC0727y;
import S8.C0712i;
import android.view.Choreographer;
import q7.InterfaceC2189c;
import q7.InterfaceC2192f;
import q7.InterfaceC2193g;
import q7.InterfaceC2194h;
import r7.EnumC2303a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class B implements T {
    public static final B l = new B();

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Choreographer f8156m;

    static {
        Z8.e eVar = S8.E.f8084a;
        f8156m = (Choreographer) AbstractC0727y.A(X8.m.f10573a.f8932q, new A(2, null));
    }

    @Override // q7.InterfaceC2194h
    public final InterfaceC2192f F(InterfaceC2193g interfaceC2193g) {
        return h6.q.r(this, interfaceC2193g);
    }

    @Override // q7.InterfaceC2194h
    public final Object j(Object obj, InterfaceC2785n interfaceC2785n) {
        return interfaceC2785n.invoke(obj, this);
    }

    @Override // q7.InterfaceC2194h
    public final InterfaceC2194h k(InterfaceC2193g interfaceC2193g) {
        return h6.q.w(this, interfaceC2193g);
    }

    @Override // T.T
    public final Object o(InterfaceC2782k interfaceC2782k, InterfaceC2189c interfaceC2189c) {
        C0712i c0712i = new C0712i(1, h6.e.k(interfaceC2189c));
        c0712i.s();
        ChoreographerFrameCallbackC0206h0 choreographerFrameCallbackC0206h0 = new ChoreographerFrameCallbackC0206h0(c0712i, interfaceC2782k);
        f8156m.postFrameCallback(choreographerFrameCallbackC0206h0);
        c0712i.u(new S0.p(choreographerFrameCallbackC0206h0, 1));
        Object objQ = c0712i.q();
        EnumC2303a enumC2303a = EnumC2303a.l;
        return objQ;
    }

    @Override // q7.InterfaceC2194h
    public final InterfaceC2194h v(InterfaceC2194h interfaceC2194h) {
        return h6.q.x(this, interfaceC2194h);
    }
}
