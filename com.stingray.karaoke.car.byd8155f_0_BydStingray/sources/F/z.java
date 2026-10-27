package F;

import A.EnumC0017h0;
import E.E;
import E.F;
import S8.AbstractC0727y;
import T.C0753m;
import T.C0761q;
import q7.C2195i;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public abstract class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final o f1988a;

    static {
        E e10 = new E(1);
        n7.u uVar = n7.u.l;
        EnumC0017h0 enumC0017h0 = EnumC0017h0.l;
        AbstractC0727y.a(C2195i.l);
        f1988a = new o(null, 0, false, 0.0f, e10, false, 0, n.f1914p, uVar, 0, 0, 0, enumC0017h0, 0, 0);
    }

    public static final y a(int i7, int i10, C0761q c0761q, int i11) {
        if ((i11 & 1) != 0) {
            i7 = 0;
        }
        if ((i11 & 2) != 0) {
            i10 = 0;
        }
        Object[] objArr = new Object[0];
        io.sentry.internal.debugmeta.c cVar = y.f1972t;
        boolean zD = c0761q.d(i7) | c0761q.d(i10);
        Object objG = c0761q.G();
        if (zD || objG == C0753m.f8270a) {
            objG = new F(i7, i10, 1);
            c0761q.a0(objG);
        }
        return (y) w0.c.Z(objArr, cVar, (InterfaceC2772a) objG, c0761q, 0, 4);
    }
}
