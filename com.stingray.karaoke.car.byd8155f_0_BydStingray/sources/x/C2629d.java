package x;

import T.C0735d;
import T.C0738e0;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import x3.AbstractC2673a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: x.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2629d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final D0 f23491a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f23492b;
    public final C2648n c;
    public final C0738e0 d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0738e0 f23493e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final T f23494f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C2640i0 f23495g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final AbstractC2652s f23496h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final AbstractC2652s f23497i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final AbstractC2652s f23498j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final AbstractC2652s f23499k;

    public C2629d(Object obj, D0 d02, Object obj2) {
        this.f23491a = d02;
        this.f23492b = obj2;
        C2648n c2648n = new C2648n(d02, obj, null, 60);
        this.c = c2648n;
        Boolean bool = Boolean.FALSE;
        T.S s6 = T.S.f8224q;
        this.d = C0735d.O(bool, s6);
        this.f23493e = C0735d.O(obj, s6);
        this.f23494f = new T();
        this.f23495g = new C2640i0(obj2, 3);
        AbstractC2652s abstractC2652s = c2648n.f23570n;
        boolean z6 = abstractC2652s instanceof C2649o;
        AbstractC2652s abstractC2652s2 = z6 ? AbstractC2631e.f23505e : abstractC2652s instanceof C2650p ? AbstractC2631e.f23506f : abstractC2652s instanceof C2651q ? AbstractC2631e.f23507g : AbstractC2631e.f23508h;
        this.f23496h = abstractC2652s2;
        AbstractC2652s abstractC2652s3 = z6 ? AbstractC2631e.f23503a : abstractC2652s instanceof C2650p ? AbstractC2631e.f23504b : abstractC2652s instanceof C2651q ? AbstractC2631e.c : AbstractC2631e.d;
        this.f23497i = abstractC2652s3;
        this.f23498j = abstractC2652s2;
        this.f23499k = abstractC2652s3;
    }

    public static final Object a(C2629d c2629d, Object obj) {
        AbstractC2652s abstractC2652s = c2629d.f23496h;
        AbstractC2652s abstractC2652s2 = c2629d.f23498j;
        boolean zA = A7.m.a(abstractC2652s2, abstractC2652s);
        AbstractC2652s abstractC2652s3 = c2629d.f23499k;
        if (zA && A7.m.a(abstractC2652s3, c2629d.f23497i)) {
            return obj;
        }
        D0 d02 = c2629d.f23491a;
        AbstractC2652s abstractC2652s4 = (AbstractC2652s) d02.f23394a.b(obj);
        int iB = abstractC2652s4.b();
        boolean z6 = false;
        for (int i7 = 0; i7 < iB; i7++) {
            if (abstractC2652s4.a(i7) < abstractC2652s2.a(i7) || abstractC2652s4.a(i7) > abstractC2652s3.a(i7)) {
                abstractC2652s4.e(AbstractC2673a.u(abstractC2652s4.a(i7), abstractC2652s2.a(i7), abstractC2652s3.a(i7)), i7);
                z6 = true;
            }
        }
        return z6 ? d02.f23395b.b(abstractC2652s4) : obj;
    }

    public static final void b(C2629d c2629d) {
        C2648n c2648n = c2629d.c;
        c2648n.f23570n.d();
        c2648n.o = Long.MIN_VALUE;
        c2629d.d.setValue(Boolean.FALSE);
    }

    public static Object c(C2629d c2629d, Object obj, InterfaceC2647m interfaceC2647m, InterfaceC2782k interfaceC2782k, InterfaceC2189c interfaceC2189c, int i7) {
        if ((i7 & 2) != 0) {
            interfaceC2647m = c2629d.f23495g;
        }
        InterfaceC2647m interfaceC2647m2 = interfaceC2647m;
        Object objB = c2629d.f23491a.f23395b.b(c2629d.c.f23570n);
        if ((i7 & 8) != 0) {
            interfaceC2782k = null;
        }
        Object objD = c2629d.d();
        D0 d02 = c2629d.f23491a;
        return T.a(c2629d.f23494f, new C2623a(c2629d, objB, new p0(interfaceC2647m2, d02, objD, obj, (AbstractC2652s) d02.f23394a.b(objB)), c2629d.c.o, interfaceC2782k, null), interfaceC2189c);
    }

    public final Object d() {
        return this.c.f23569m.getValue();
    }

    public final Object e(Object obj, InterfaceC2189c interfaceC2189c) {
        Object objA = T.a(this.f23494f, new C2625b(this, obj, null), interfaceC2189c);
        return objA == EnumC2303a.l ? objA : m7.q.f19267a;
    }

    public final Object f(s7.i iVar) {
        Object objA = T.a(this.f23494f, new C2627c(this, null), iVar);
        return objA == EnumC2303a.l ? objA : m7.q.f19267a;
    }

    public /* synthetic */ C2629d(Object obj, D0 d02, Object obj2, int i7) {
        this(obj, d02, (i7 & 4) != 0 ? null : obj2);
    }
}
