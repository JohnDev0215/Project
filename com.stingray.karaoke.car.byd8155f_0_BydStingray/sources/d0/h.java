package d0;

import A.C0022k;
import C0.C0114v;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import java.util.LinkedHashMap;
import java.util.Map;
import m7.q;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class h implements InterfaceC1054c {
    public static final io.sentry.internal.debugmeta.c d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f13268a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f13269b = new LinkedHashMap();
    public k c;

    static {
        C1055d c1055d = C1055d.f13260n;
        C1056e c1056e = C1056e.f13262n;
        io.sentry.internal.debugmeta.c cVar = o.f13275a;
        d = new io.sentry.internal.debugmeta.c(c1055d, 15, c1056e);
    }

    public h(Map map) {
        this.f13268a = map;
    }

    @Override // d0.InterfaceC1054c
    public final void d(Object obj) {
        f fVar = (f) this.f13269b.get(obj);
        if (fVar != null) {
            fVar.f13265b = false;
        } else {
            this.f13268a.remove(obj);
        }
    }

    @Override // d0.InterfaceC1054c
    public final void e(Object obj, b0.b bVar, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-1198538093);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(obj) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(bVar) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(this) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.T(obj);
            Object objG = c0761q.G();
            Object obj2 = C0753m.f8270a;
            if (objG == obj2) {
                k kVar = this.c;
                if (!(kVar != null ? kVar.b(obj) : true)) {
                    throw new IllegalArgumentException(("Type of the key " + obj + " is not supported. On Android you can only use types which can be stored inside the Bundle.").toString());
                }
                objG = new f(this, obj);
                c0761q.a0(objG);
            }
            f fVar = (f) objG;
            C0735d.a(m.f13274a.a(fVar.c), bVar, c0761q, (i10 & 112) | 8);
            q qVar = q.f19267a;
            boolean zH = c0761q.h(this) | c0761q.h(obj) | c0761q.h(fVar);
            Object objG2 = c0761q.G();
            if (zH || objG2 == obj2) {
                objG2 = new C0022k(this, obj, fVar, 11);
                c0761q.a0(objG2);
            }
            C0735d.d(qVar, (InterfaceC2782k) objG2, c0761q);
            if (c0761q.f8324x && c0761q.f8294F.f8395i == c0761q.f8325y) {
                c0761q.f8325y = -1;
                c0761q.f8324x = false;
            }
            c0761q.p(false);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0114v(this, obj, bVar, i7, 8);
        }
    }
}
