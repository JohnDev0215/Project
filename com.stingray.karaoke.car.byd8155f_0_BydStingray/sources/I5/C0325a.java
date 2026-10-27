package I5;

import D.AbstractC0133o;
import T.C0761q;
import n0.AbstractC1861K;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: I5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0325a implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C0325a f2945m = new C0325a(0);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0325a f2946n = new C0325a(1);
    public static final C0325a o = new C0325a(2);
    public final /* synthetic */ int l;

    public /* synthetic */ C0325a(int i7) {
        this.l = i7;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        switch (this.l) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    AbstractC0133o.a(androidx.compose.foundation.a.b(androidx.compose.foundation.layout.c.c(g0.l.f14684a, 1.0f), l6.d.c(c0761q).f18704g, AbstractC1861K.f19381a), c0761q, 0);
                }
                break;
            case 1:
                C0761q c0761q2 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    w0.c.e(null, c0761q2, 0);
                }
                break;
            default:
                C0761q c0761q3 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q3.x()) {
                    c0761q3.K();
                } else {
                    k6.o.f18284a.d(6, c0761q3);
                }
                break;
        }
        return m7.q.f19267a;
    }
}
