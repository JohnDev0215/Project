package W5;

import T.C0761q;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class f implements InterfaceC2786o {
    public static final f l = new f();

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
            io.sentry.config.a.H(str, c0761q, iIntValue & 14);
        }
        return m7.q.f19267a;
    }
}
