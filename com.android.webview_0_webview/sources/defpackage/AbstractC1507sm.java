package defpackage;

/* JADX INFO: renamed from: sm, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1507sm {
    public abstract void a(Object obj, int i, AbstractC0731el abstractC0731el);

    public abstract Object b(Object obj);

    public final boolean c(Object obj, InterfaceC1123lm interfaceC1123lm) throws C0155Il {
        int iJ = interfaceC1123lm.j();
        int i = iJ >>> 3;
        int i2 = iJ & 7;
        if (i2 == 0) {
            ((C1562tm) obj).b((i << 3) | 0, Long.valueOf(interfaceC1123lm.C()));
            return true;
        }
        if (i2 == 1) {
            ((C1562tm) obj).b((i << 3) | 1, Long.valueOf(interfaceC1123lm.m()));
            return true;
        }
        if (i2 == 2) {
            a(obj, i, interfaceC1123lm.d());
            return true;
        }
        if (i2 != 3) {
            if (i2 == 4) {
                return false;
            }
            if (i2 != 5) {
                throw C0155Il.c();
            }
            ((C1562tm) obj).b((i << 3) | 5, Integer.valueOf(interfaceC1123lm.E()));
            return true;
        }
        Object objD = d();
        int i3 = i << 3;
        int i4 = i3 | 4;
        while (interfaceC1123lm.K() != Integer.MAX_VALUE && c(objD, interfaceC1123lm)) {
        }
        if (i4 != interfaceC1123lm.j()) {
            throw C0155Il.a();
        }
        C1562tm c1562tm = (C1562tm) objD;
        c1562tm.e = false;
        ((C1562tm) obj).b(i3 | 3, c1562tm);
        return true;
    }

    public abstract Object d();

    public abstract void e(Object obj, Object obj2);

    public abstract boolean f(InterfaceC1123lm interfaceC1123lm);
}
