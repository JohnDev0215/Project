package h8;

import W7.AbstractC0839c;
import i8.C1417b;
import i8.EnumC1416a;
import j8.C;
import o8.C2057b;
import o8.C2058c;
import o8.C2061f;
import p8.C2138m;
import w8.C2608c;

/* JADX INFO: renamed from: h8.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1391h implements D8.l {
    public final C2608c l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C2608c f15611m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final V7.c f15612n;

    public C1391h(V7.c cVar, C c, n8.g gVar, int i7) {
        A7.m.f(cVar, "kotlinClass");
        A7.m.f(c, "packageProto");
        A7.m.f(gVar, "nameResolver");
        A7.k.p(i7, "abiStability");
        C2608c c2608cB = C2608c.b(AbstractC0839c.a(cVar.f9548a));
        C1417b c1417b = cVar.f9549b;
        C2608c c2608cD = null;
        String str = ((EnumC1416a) c1417b.c) == EnumC1416a.MULTIFILE_CLASS_PART ? (String) c1417b.f15788h : null;
        if (str != null && str.length() > 0) {
            c2608cD = C2608c.d(str);
        }
        this.l = c2608cB;
        this.f15611m = c2608cD;
        this.f15612n = cVar;
        C2138m c2138m = m8.k.f19336m;
        A7.m.e(c2138m, "packageModuleName");
        Integer num = (Integer) AbstractC1386c.h(c, c2138m);
        if (num != null) {
            gVar.n(num.intValue());
        }
    }

    public final C2057b a() {
        C2058c c2058c;
        C2608c c2608c = this.l;
        String str = c2608c.f23346a;
        int iLastIndexOf = str.lastIndexOf("/");
        if (iLastIndexOf == -1) {
            c2058c = C2058c.c;
            if (c2058c == null) {
                C2608c.a(7);
                throw null;
            }
        } else {
            c2058c = new C2058c(str.substring(0, iLastIndexOf).replace('/', '.'));
        }
        String strE = c2608c.e();
        A7.m.e(strE, "className.internalName");
        return new C2057b(c2058c, C2061f.e(Q8.h.a1('/', strE, strE)));
    }

    public final String toString() {
        return C1391h.class.getSimpleName() + ": " + this.l;
    }
}
