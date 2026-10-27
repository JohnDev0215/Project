package W8;

import V8.InterfaceC0817f;
import V8.InterfaceC0818g;
import q7.InterfaceC2189c;
import q7.InterfaceC2194h;
import r7.EnumC2303a;
import s7.AbstractC2408a;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: W8.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0843c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final InterfaceC2189c[] f9910a = new InterfaceC2189c[0];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final B2.q f9911b = new B2.q("NULL", 3);
    public static final B2.q c = new B2.q("UNINITIALIZED", 3);
    public static final B2.q d = new B2.q("DONE", 3);

    public static final Object a(InterfaceC0818g interfaceC0818g, InterfaceC2189c interfaceC2189c, InterfaceC2786o interfaceC2786o, InterfaceC0817f[] interfaceC0817fArr) {
        s sVar = new s(interfaceC0818g, null, interfaceC2786o, interfaceC0817fArr);
        u uVar = new u(interfaceC2189c, interfaceC2189c.getContext());
        Object objM0 = io.sentry.config.a.m0(uVar, uVar, sVar);
        return objM0 == EnumC2303a.l ? objM0 : m7.q.f19267a;
    }

    public static final Object b(InterfaceC2194h interfaceC2194h, Object obj, Object obj2, InterfaceC2785n interfaceC2785n, InterfaceC2189c interfaceC2189c) {
        Object objInvoke;
        Object objN = X8.a.n(interfaceC2194h, obj2);
        try {
            D d10 = new D(interfaceC2189c, interfaceC2194h);
            if (interfaceC2785n instanceof AbstractC2408a) {
                A7.E.e(2, interfaceC2785n);
                objInvoke = interfaceC2785n.invoke(obj, d10);
            } else {
                objInvoke = h6.e.t(interfaceC2785n, obj, d10);
            }
            X8.a.i(interfaceC2194h, objN);
            if (objInvoke == EnumC2303a.l) {
                A7.m.f(interfaceC2189c, "frame");
            }
            return objInvoke;
        } catch (Throwable th) {
            X8.a.i(interfaceC2194h, objN);
            throw th;
        }
    }
}
