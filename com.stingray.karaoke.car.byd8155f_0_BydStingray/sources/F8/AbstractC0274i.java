package F8;

import H7.InterfaceC0318c;
import io.sentry.C1489n1;

/* JADX INFO: renamed from: F8.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0274i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ H7.w[] f2469a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final L8.p f2470b;

    static {
        A7.B b10 = A7.A.f443a;
        f2469a = new H7.w[]{b10.h(new A7.s(b10.c(AbstractC0274i.class, "descriptors"), "annotationsAttribute", "getAnnotationsAttribute(Lorg/jetbrains/kotlin/types/TypeAttributes;)Lorg/jetbrains/kotlin/types/AnnotationsTypeAttribute;"))};
        C1489n1 c1489n1 = H.f2437m;
        InterfaceC0318c interfaceC0318cB = b10.b(C0273h.class);
        c1489n1.getClass();
        int iQ = c1489n1.q(interfaceC0318cB);
        L8.p pVar = new L8.p();
        pVar.l = iQ;
        f2470b = pVar;
    }

    public static final R7.h a(H h10) {
        R7.h hVar;
        A7.m.f(h10, "<this>");
        H7.w wVar = f2469a[0];
        L8.p pVar = f2470b;
        pVar.getClass();
        A7.m.f(wVar, "property");
        C0273h c0273h = (C0273h) h10.l.get(pVar.l);
        return (c0273h == null || (hVar = c0273h.f2468a) == null) ? R7.g.f7535a : hVar;
    }
}
