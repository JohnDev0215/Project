package e0;

import T.C0735d;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: e0.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1179s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f13731a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f13732b = new Object();

    public static final void a(int i7, int i10) {
        if (i7 < 0 || i7 >= i10) {
            throw new IndexOutOfBoundsException("index (" + i7 + ") is out of bound of [0, " + i10 + ')');
        }
    }

    public static final int b(int[] iArr, int i7) {
        int length = iArr.length - 1;
        int i10 = 0;
        while (i10 <= length) {
            int i11 = (i10 + length) >>> 1;
            int i12 = iArr[i11];
            if (i7 > i12) {
                i10 = i11 + 1;
            } else {
                if (i7 >= i12) {
                    return i11;
                }
                length = i11 - 1;
            }
        }
        return -(i10 + 1);
    }

    public static AbstractC1167g c() {
        return (AbstractC1167g) AbstractC1174n.f13720a.p();
    }

    public static AbstractC1167g d(AbstractC1167g abstractC1167g) {
        if (abstractC1167g instanceof C1159F) {
            C1159F c1159f = (C1159F) abstractC1167g;
            if (c1159f.f13685t == C0735d.C()) {
                c1159f.f13683r = null;
                return abstractC1167g;
            }
        }
        if (abstractC1167g instanceof C1160G) {
            C1160G c1160g = (C1160G) abstractC1167g;
            if (c1160g.f13689h == C0735d.C()) {
                c1160g.f13688g = null;
                return abstractC1167g;
            }
        }
        AbstractC1167g abstractC1167gH = AbstractC1174n.h(abstractC1167g, null, false);
        abstractC1167gH.j();
        return abstractC1167gH;
    }

    public static Object e(InterfaceC2772a interfaceC2772a, InterfaceC2782k interfaceC2782k) {
        AbstractC1167g c1159f;
        if (interfaceC2782k == null) {
            return interfaceC2772a.a();
        }
        AbstractC1167g abstractC1167g = (AbstractC1167g) AbstractC1174n.f13720a.p();
        if (abstractC1167g instanceof C1159F) {
            C1159F c1159f2 = (C1159F) abstractC1167g;
            if (c1159f2.f13685t == C0735d.C()) {
                InterfaceC2782k interfaceC2782k2 = c1159f2.f13683r;
                InterfaceC2782k interfaceC2782k3 = c1159f2.f13684s;
                try {
                    ((C1159F) abstractC1167g).f13683r = AbstractC1174n.l(interfaceC2782k, interfaceC2782k2, true);
                    ((C1159F) abstractC1167g).f13684s = AbstractC1174n.b(null, interfaceC2782k3);
                    return interfaceC2772a.a();
                } finally {
                    c1159f2.f13683r = interfaceC2782k2;
                    c1159f2.f13684s = interfaceC2782k3;
                }
            }
        }
        if (abstractC1167g == null || (abstractC1167g instanceof C1163c)) {
            c1159f = new C1159F(abstractC1167g instanceof C1163c ? (C1163c) abstractC1167g : null, interfaceC2782k, null, true, false);
        } else {
            if (interfaceC2782k == null) {
                return interfaceC2772a.a();
            }
            c1159f = abstractC1167g.t(interfaceC2782k);
        }
        try {
            AbstractC1167g abstractC1167gJ = c1159f.j();
            try {
                return interfaceC2772a.a();
            } finally {
                AbstractC1167g.p(abstractC1167gJ);
            }
        } finally {
            c1159f.c();
        }
    }

    public static void f(AbstractC1167g abstractC1167g, AbstractC1167g abstractC1167g2, InterfaceC2782k interfaceC2782k) {
        if (abstractC1167g != abstractC1167g2) {
            abstractC1167g2.getClass();
            AbstractC1167g.p(abstractC1167g);
            abstractC1167g2.c();
        } else if (abstractC1167g instanceof C1159F) {
            ((C1159F) abstractC1167g).f13683r = interfaceC2782k;
        } else if (abstractC1167g instanceof C1160G) {
            ((C1160G) abstractC1167g).f13688g = interfaceC2782k;
        } else {
            throw new IllegalStateException(("Non-transparent snapshot was reused: " + abstractC1167g).toString());
        }
    }

    public static final void g() {
        throw new UnsupportedOperationException();
    }
}
