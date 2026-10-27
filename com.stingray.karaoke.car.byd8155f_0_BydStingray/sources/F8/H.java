package F8;

import io.sentry.C1489n1;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class H extends L8.d {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C1489n1 f2437m = new C1489n1(6);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final H f2438n = new H(n7.u.l);

    public H(List list) {
        this.l = L8.k.l;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            C0273h c0273h = (C0273h) it.next();
            c0273h.getClass();
            int iQ = f2437m.q(A7.A.f443a.b(C0273h.class));
            int iD = this.l.d();
            if (iD != 0) {
                if (iD == 1) {
                    L8.a aVar = this.l;
                    A7.m.d(aVar, "null cannot be cast to non-null type org.jetbrains.kotlin.util.OneElementArrayMap<T of org.jetbrains.kotlin.util.AttributeArrayOwner>");
                    L8.r rVar = (L8.r) aVar;
                    int i7 = rVar.f4094m;
                    if (i7 == iQ) {
                        this.l = new L8.r(iQ, c0273h);
                    } else {
                        L8.c cVar = new L8.c();
                        cVar.l = new Object[20];
                        cVar.f4071m = 0;
                        this.l = cVar;
                        cVar.l(i7, rVar.l);
                    }
                }
                this.l.l(iQ, c0273h);
            } else {
                this.l = new L8.r(iQ, c0273h);
            }
        }
    }
}
