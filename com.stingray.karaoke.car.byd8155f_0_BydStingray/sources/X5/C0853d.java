package X5;

import T.C0761q;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: X5.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0853d implements InterfaceC2786o {
    public static final C0853d l = new C0853d();

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        String str = (String) obj;
        C0761q c0761q = (C0761q) obj2;
        int iIntValue = ((Number) obj3).intValue();
        A7.m.f(str, "id");
        if ((iIntValue & 6) == 0) {
            iIntValue |= c0761q.f(str) ? 4 : 2;
        }
        if ((iIntValue & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            io.sentry.config.a.l(str, c0761q, iIntValue & 14);
        }
        return m7.q.f19267a;
    }
}
