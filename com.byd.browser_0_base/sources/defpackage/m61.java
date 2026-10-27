package defpackage;

import android.graphics.RectF;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: LayoutTab.java */
/* JADX INFO: loaded from: classes.dex */
public final class m61 extends PropertyModel {
    public static final PropertyModel.n A;
    public static final PropertyModel.n B;
    public static final PropertyModel.n C;
    public static final PropertyModel.n D;
    public static final PropertyModel.o E;
    public static final PropertyModel.n F;
    public static final PropertyModel.o G;
    public static final PropertyModel.o H;
    public static final PropertyModel.r<RectF> I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final PropertyModel.r<RectF> f46J;
    public static final PropertyModel.o K;
    public static final PropertyModel.n L;
    public static final PropertyModel.p M;
    public static final PropertyModel.p N;
    public static final PropertyModel.p O;
    public static final PropertyModel.o P;
    public static final PropertyModel.o Q;
    public static final v62[] R;
    public static float c;
    public static float d;
    public static final PropertyModel.p e;
    public static final PropertyModel.n f;
    public static final PropertyModel.o g;
    public static final PropertyModel.o h;
    public static final PropertyModel.o i;
    public static final PropertyModel.o j;
    public static final PropertyModel.o k;
    public static final PropertyModel.o l;
    public static final PropertyModel.o m;
    public static final PropertyModel.o n;
    public static final PropertyModel.o o;
    public static final PropertyModel.o p;
    public static final PropertyModel.o q;
    public static final PropertyModel.o r;
    public static final PropertyModel.o s;
    public static final PropertyModel.o t;
    public static final PropertyModel.o u;
    public static final PropertyModel.o v;
    public static final PropertyModel.o w;
    public static final PropertyModel.o x;
    public static final PropertyModel.o y;
    public static final PropertyModel.n z;

    static {
        PropertyModel.p pVar = new PropertyModel.p();
        e = pVar;
        PropertyModel.n nVar = new PropertyModel.n();
        f = nVar;
        PropertyModel.o oVar = new PropertyModel.o();
        g = oVar;
        PropertyModel.o oVar2 = new PropertyModel.o();
        h = oVar2;
        PropertyModel.o oVar3 = new PropertyModel.o();
        i = oVar3;
        PropertyModel.o oVar4 = new PropertyModel.o();
        j = oVar4;
        PropertyModel.o oVar5 = new PropertyModel.o();
        k = oVar5;
        PropertyModel.o oVar6 = new PropertyModel.o();
        l = oVar6;
        PropertyModel.o oVar7 = new PropertyModel.o();
        m = oVar7;
        PropertyModel.o oVar8 = new PropertyModel.o();
        n = oVar8;
        PropertyModel.o oVar9 = new PropertyModel.o();
        o = oVar9;
        PropertyModel.o oVar10 = new PropertyModel.o();
        p = oVar10;
        PropertyModel.o oVar11 = new PropertyModel.o();
        q = oVar11;
        PropertyModel.o oVar12 = new PropertyModel.o();
        r = oVar12;
        PropertyModel.o oVar13 = new PropertyModel.o();
        s = oVar13;
        PropertyModel.o oVar14 = new PropertyModel.o();
        t = oVar14;
        PropertyModel.o oVar15 = new PropertyModel.o();
        u = oVar15;
        PropertyModel.o oVar16 = new PropertyModel.o();
        v = oVar16;
        PropertyModel.o oVar17 = new PropertyModel.o();
        w = oVar17;
        PropertyModel.o oVar18 = new PropertyModel.o();
        x = oVar18;
        PropertyModel.o oVar19 = new PropertyModel.o();
        y = oVar19;
        PropertyModel.n nVar2 = new PropertyModel.n();
        z = nVar2;
        PropertyModel.n nVar3 = new PropertyModel.n();
        A = nVar3;
        PropertyModel.n nVar4 = new PropertyModel.n();
        B = nVar4;
        PropertyModel.n nVar5 = new PropertyModel.n();
        C = nVar5;
        PropertyModel.n nVar6 = new PropertyModel.n();
        D = nVar6;
        PropertyModel.o oVar20 = new PropertyModel.o();
        E = oVar20;
        PropertyModel.n nVar7 = new PropertyModel.n();
        F = nVar7;
        PropertyModel.o oVar21 = new PropertyModel.o();
        G = oVar21;
        PropertyModel.o oVar22 = new PropertyModel.o();
        H = oVar22;
        PropertyModel.n nVar8 = new PropertyModel.n();
        PropertyModel.r<RectF> rVar = new PropertyModel.r<>();
        I = rVar;
        PropertyModel.r<RectF> rVar2 = new PropertyModel.r<>();
        f46J = rVar2;
        PropertyModel.o oVar23 = new PropertyModel.o();
        K = oVar23;
        PropertyModel.n nVar9 = new PropertyModel.n();
        PropertyModel.n nVar10 = new PropertyModel.n();
        L = nVar10;
        PropertyModel.p pVar2 = new PropertyModel.p();
        M = pVar2;
        PropertyModel.p pVar3 = new PropertyModel.p();
        N = pVar3;
        PropertyModel.p pVar4 = new PropertyModel.p();
        O = pVar4;
        PropertyModel.o oVar24 = new PropertyModel.o();
        P = oVar24;
        PropertyModel.o oVar25 = new PropertyModel.o();
        Q = oVar25;
        R = new v62[]{pVar, nVar, oVar, oVar2, oVar3, oVar4, oVar5, oVar6, oVar7, oVar8, oVar9, oVar10, oVar11, oVar12, oVar13, oVar14, oVar15, oVar16, oVar17, oVar18, oVar19, nVar2, nVar3, nVar4, nVar5, nVar6, oVar20, nVar7, oVar21, oVar22, nVar8, rVar, rVar2, oVar23, nVar9, nVar10, pVar2, pVar3, pVar4, oVar24, oVar25};
    }

    public m61(int i2, int i3, int i4, boolean z2) {
        super(R);
        n(e, i2);
        l(f, z2);
        p(I, new RectF());
        p(f46J, new RectF());
        n(M, -1);
        n(N, -855310);
        n(O, -1);
        m(P, 1.0f);
        u(i3, i4);
    }

    public final int q() {
        return h(e);
    }

    public final float r() {
        return Math.min(g(u), g(w));
    }

    public final float s() {
        return Math.min(g(t), g(v));
    }

    public final float t() {
        return g(u);
    }

    public final String toString() {
        return Integer.toString(q());
    }

    public final void u(int i2, int i3) {
        m(p, 1.0f);
        m(q, 1.0f);
        m(y, 1.0f);
        m(r, 1.0f);
        m(s, 1.0f);
        m(l, 0.0f);
        m(m, 0.0f);
        m(n, Float.MAX_VALUE);
        m(o, Float.MAX_VALUE);
        m(g, 1.0f);
        l(z, true);
        m(h, 0.0f);
        m(i, 0.0f);
        m(j, 0.0f);
        m(k, 0.0f);
        m(x, 0.0f);
        m(K, 1.0f);
        l(B, true);
        l(C, false);
        l(D, false);
        m(E, 1.0f);
        l(F, false);
        m(G, 0.0f);
        m(H, 1.0f);
        float f2 = i2;
        m(t, d * f2);
        float f3 = i3;
        m(u, d * f3);
        m(v, f2 * d);
        m(w, f3 * d);
        l(L, false);
    }

    public final void v() {
        m(K, 0.0f);
    }
}
