package w;

import T.C0753m;
import T.C0761q;
import T.Q0;
import n0.C1891t;
import o0.AbstractC2007c;
import q0.C2162a;
import x.AbstractC2631e;
import x.AbstractC2637h;
import x.C0;
import x.C2640i0;
import x.D0;
import x.E0;
import x.InterfaceC2647m;

/* JADX INFO: loaded from: classes.dex */
public abstract class L {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2640i0 f23077a = AbstractC2631e.p(0.0f, 7, null);

    public static final Q0 a(long j3, C0 c02, String str, C0761q c0761q, int i7, int i10) {
        InterfaceC2647m interfaceC2647m = (i10 & 2) != 0 ? f23077a : c02;
        String str2 = (i10 & 4) != 0 ? "ColorAnimation" : str;
        boolean zF = c0761q.f(C1891t.f(j3));
        Object objG = c0761q.G();
        if (zF || objG == C0753m.f8270a) {
            AbstractC2007c abstractC2007cF = C1891t.f(j3);
            C2584o c2584o = C2584o.f23121p;
            C2162a c2162a = new C2162a(abstractC2007cF, 6);
            D0 d02 = E0.f23396a;
            D0 d03 = new D0(c2584o, c2162a);
            c0761q.a0(d03);
            objG = d03;
        }
        return AbstractC2637h.c(new C1891t(j3), (D0) objG, interfaceC2647m, null, str2, null, c0761q, ((i7 << 3) & 896) | ((i7 << 6) & 57344), 8);
    }
}
