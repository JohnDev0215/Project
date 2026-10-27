package androidx.compose.foundation.layout;

import D.L;
import a1.k;
import g0.o;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {
    public static L a(float f10, int i7) {
        if ((i7 & 1) != 0) {
            f10 = 0;
        }
        float f11 = 0;
        return new L(f10, f11, f10, f11);
    }

    public static final L b(float f10, float f11, float f12, float f13) {
        return new L(f10, f11, f12, f13);
    }

    public static L c(float f10, float f11, float f12, float f13, int i7) {
        if ((i7 & 1) != 0) {
            f10 = 0;
        }
        if ((i7 & 2) != 0) {
            f11 = 0;
        }
        if ((i7 & 4) != 0) {
            f12 = 0;
        }
        if ((i7 & 8) != 0) {
            f13 = 0;
        }
        return new L(f10, f11, f12, f13);
    }

    public static o d(o oVar, float f10) {
        return oVar.k(new AspectRatioElement(f10, false));
    }

    public static final float e(L l, k kVar) {
        return kVar == k.l ? l.c(kVar) : l.b(kVar);
    }

    public static final float f(L l, k kVar) {
        return kVar == k.l ? l.b(kVar) : l.c(kVar);
    }

    public static final o g(o oVar, int i7) {
        return oVar.k(new IntrinsicHeightElement(i7));
    }

    public static final o h(o oVar, float f10, float f11) {
        return oVar.k(new OffsetElement(f10, f11));
    }

    public static o i(o oVar, float f10, float f11, int i7) {
        if ((i7 & 1) != 0) {
            f10 = 0;
        }
        if ((i7 & 2) != 0) {
            f11 = 0;
        }
        return h(oVar, f10, f11);
    }

    public static final o j(o oVar, L l) {
        return oVar.k(new PaddingValuesElement(l));
    }

    public static final o k(o oVar, float f10) {
        return oVar.k(new PaddingElement(f10, f10, f10, f10));
    }

    public static final o l(o oVar, float f10, float f11) {
        return oVar.k(new PaddingElement(f10, f11, f10, f11));
    }

    public static o m(o oVar, float f10, float f11, int i7) {
        if ((i7 & 1) != 0) {
            f10 = 0;
        }
        if ((i7 & 2) != 0) {
            f11 = 0;
        }
        return l(oVar, f10, f11);
    }

    public static final o n(o oVar, float f10, float f11, float f12, float f13) {
        return oVar.k(new PaddingElement(f10, f11, f12, f13));
    }

    public static o o(o oVar, float f10, float f11, float f12, float f13, int i7) {
        if ((i7 & 1) != 0) {
            f10 = 0;
        }
        if ((i7 & 2) != 0) {
            f11 = 0;
        }
        if ((i7 & 4) != 0) {
            f12 = 0;
        }
        if ((i7 & 8) != 0) {
            f13 = 0;
        }
        return n(oVar, f10, f11, f12, f13);
    }

    public static final o p(o oVar) {
        return oVar.k(new IntrinsicWidthElement());
    }
}
