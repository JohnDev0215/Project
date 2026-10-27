package Q;

import T.C0761q;
import n0.AbstractC1861K;
import n0.InterfaceC1866P;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public abstract class T {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final T.R0 f5665a = new T.R0(C0518m.f5990v);

    public static final InterfaceC1866P a(int i7, C0761q c0761q) {
        S s6 = (S) c0761q.k(f5665a);
        switch (AbstractC2641j.c(i7)) {
            case 0:
                return s6.f5661e;
            case 1:
                return b(s6.f5661e);
            case 2:
                return s6.f5659a;
            case 3:
                return b(s6.f5659a);
            case 4:
                return J.e.f3041a;
            case 5:
                return s6.d;
            case 6:
                J.d dVar = s6.d;
                float f10 = (float) 0.0d;
                return J.d.a(dVar, new J.b(f10), null, new J.b(f10), 6);
            case 7:
                return b(s6.d);
            case 8:
                return s6.c;
            case 9:
                return AbstractC1861K.f19381a;
            case 10:
                return s6.f5660b;
            default:
                throw new A9.k();
        }
    }

    public static final J.d b(J.d dVar) {
        float f10 = (float) 0.0d;
        return J.d.a(dVar, null, new J.b(f10), new J.b(f10), 3);
    }
}
