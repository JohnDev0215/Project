package E;

import A.EnumC0017h0;
import S8.AbstractC0727y;
import T.C0753m;
import T.C0761q;
import q7.C2195i;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public abstract class G {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f1324a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final t f1325b = new t(null, 0, false, 0.0f, new E(0), 0.0f, false, AbstractC0727y.a(C2195i.l), l9.d.g(), io.sentry.config.a.f(0, 0, 15), n7.u.l, 0, 0, 0, false, EnumC0017h0.l, 0, 0);

    public static final D a(int i7, int i10, C0761q c0761q, int i11, int i12) {
        if ((i12 & 1) != 0) {
            i7 = 0;
        }
        if ((i12 & 2) != 0) {
            i10 = 0;
        }
        Object[] objArr = new Object[0];
        io.sentry.internal.debugmeta.c cVar = D.f1301x;
        boolean z6 = ((((i11 & 14) ^ 6) > 4 && c0761q.d(i7)) || (i11 & 6) == 4) | ((((i11 & 112) ^ 48) > 32 && c0761q.d(i10)) || (i11 & 48) == 32);
        Object objG = c0761q.G();
        if (z6 || objG == C0753m.f8270a) {
            objG = new F(i7, i10, 0);
            c0761q.a0(objG);
        }
        return (D) w0.c.Z(objArr, cVar, (InterfaceC2772a) objG, c0761q, 0, 4);
    }
}
