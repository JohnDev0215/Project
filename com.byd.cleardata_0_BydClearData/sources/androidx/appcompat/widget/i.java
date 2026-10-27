package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.appcompat.widget.l0;

/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final PorterDuff.Mode f950a = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static i f951b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private l0 f952c;

    class a implements l0.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int[] f953a = {a.a.e.S, a.a.e.Q, a.a.e.f13a};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int[] f954b = {a.a.e.o, a.a.e.B, a.a.e.t, a.a.e.p, a.a.e.q, a.a.e.s, a.a.e.r};

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int[] f955c = {a.a.e.P, a.a.e.R, a.a.e.k, a.a.e.I, a.a.e.J, a.a.e.L, a.a.e.N, a.a.e.K, a.a.e.M, a.a.e.O};

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int[] f956d = {a.a.e.w, a.a.e.i, a.a.e.v};
        private final int[] e = {a.a.e.H, a.a.e.T};
        private final int[] f = {a.a.e.f15c, a.a.e.g, a.a.e.f16d, a.a.e.h};

        a() {
        }

        private boolean f(int[] iArr, int i) {
            for (int i2 : iArr) {
                if (i2 == i) {
                    return true;
                }
            }
            return false;
        }

        private ColorStateList g(Context context) {
            return h(context, 0);
        }

        private ColorStateList h(Context context, int i) {
            int iC = q0.c(context, a.a.a.v);
            return new ColorStateList(new int[][]{q0.f1010b, q0.e, q0.f1011c, q0.i}, new int[]{q0.b(context, a.a.a.t), a.g.d.a.b(iC, i), a.g.d.a.b(iC, i), i});
        }

        private ColorStateList i(Context context) {
            return h(context, q0.c(context, a.a.a.s));
        }

        private ColorStateList j(Context context) {
            return h(context, q0.c(context, a.a.a.t));
        }

        private ColorStateList k(Context context) {
            int[][] iArr = new int[3][];
            int[] iArr2 = new int[3];
            int i = a.a.a.x;
            ColorStateList colorStateListE = q0.e(context, i);
            if (colorStateListE == null || !colorStateListE.isStateful()) {
                iArr[0] = q0.f1010b;
                iArr2[0] = q0.b(context, i);
                iArr[1] = q0.f;
                iArr2[1] = q0.c(context, a.a.a.u);
                iArr[2] = q0.i;
                iArr2[2] = q0.c(context, i);
            } else {
                iArr[0] = q0.f1010b;
                iArr2[0] = colorStateListE.getColorForState(iArr[0], 0);
                iArr[1] = q0.f;
                iArr2[1] = q0.c(context, a.a.a.u);
                iArr[2] = q0.i;
                iArr2[2] = colorStateListE.getDefaultColor();
            }
            return new ColorStateList(iArr, iArr2);
        }

        private void l(Drawable drawable, int i, PorterDuff.Mode mode) {
            if (d0.a(drawable)) {
                drawable = drawable.mutate();
            }
            if (mode == null) {
                mode = i.f950a;
            }
            drawable.setColorFilter(i.e(i, mode));
        }

        @Override // androidx.appcompat.widget.l0.e
        public Drawable a(l0 l0Var, Context context, int i) {
            if (i == a.a.e.j) {
                return new LayerDrawable(new Drawable[]{l0Var.j(context, a.a.e.i), l0Var.j(context, a.a.e.k)});
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0046  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0061 A[RETURN] */
        @Override // androidx.appcompat.widget.l0.e
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean b(android.content.Context r7, int r8, android.graphics.drawable.Drawable r9) {
            /*
                r6 = this;
                android.graphics.PorterDuff$Mode r0 = androidx.appcompat.widget.i.a()
                int[] r1 = r6.f953a
                boolean r1 = r6.f(r1, r8)
                r2 = 16842801(0x1010031, float:2.3693695E-38)
                r3 = -1
                r4 = 0
                r5 = 1
                if (r1 == 0) goto L17
                int r2 = a.a.a.w
            L14:
                r8 = r3
            L15:
                r1 = r5
                goto L44
            L17:
                int[] r1 = r6.f955c
                boolean r1 = r6.f(r1, r8)
                if (r1 == 0) goto L22
                int r2 = a.a.a.u
                goto L14
            L22:
                int[] r1 = r6.f956d
                boolean r1 = r6.f(r1, r8)
                if (r1 == 0) goto L2d
                android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
                goto L14
            L2d:
                int r1 = a.a.e.u
                if (r8 != r1) goto L3c
                r2 = 16842800(0x1010030, float:2.3693693E-38)
                r8 = 1109603123(0x42233333, float:40.8)
                int r8 = java.lang.Math.round(r8)
                goto L15
            L3c:
                int r1 = a.a.e.l
                if (r8 != r1) goto L41
                goto L14
            L41:
                r8 = r3
                r1 = r4
                r2 = r1
            L44:
                if (r1 == 0) goto L61
                boolean r1 = androidx.appcompat.widget.d0.a(r9)
                if (r1 == 0) goto L50
                android.graphics.drawable.Drawable r9 = r9.mutate()
            L50:
                int r7 = androidx.appcompat.widget.q0.c(r7, r2)
                android.graphics.PorterDuffColorFilter r7 = androidx.appcompat.widget.i.e(r7, r0)
                r9.setColorFilter(r7)
                if (r8 == r3) goto L60
                r9.setAlpha(r8)
            L60:
                return r5
            L61:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.i.a.b(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
        }

        @Override // androidx.appcompat.widget.l0.e
        public ColorStateList c(Context context, int i) {
            if (i == a.a.e.m) {
                return a.a.k.a.a.c(context, a.a.c.e);
            }
            if (i == a.a.e.G) {
                return a.a.k.a.a.c(context, a.a.c.h);
            }
            if (i == a.a.e.F) {
                return k(context);
            }
            if (i == a.a.e.f) {
                return j(context);
            }
            if (i == a.a.e.f14b) {
                return g(context);
            }
            if (i == a.a.e.e) {
                return i(context);
            }
            if (i == a.a.e.D || i == a.a.e.E) {
                return a.a.k.a.a.c(context, a.a.c.g);
            }
            if (f(this.f954b, i)) {
                return q0.e(context, a.a.a.w);
            }
            if (f(this.e, i)) {
                return a.a.k.a.a.c(context, a.a.c.f8d);
            }
            if (f(this.f, i)) {
                return a.a.k.a.a.c(context, a.a.c.f7c);
            }
            if (i == a.a.e.A) {
                return a.a.k.a.a.c(context, a.a.c.f);
            }
            return null;
        }

        @Override // androidx.appcompat.widget.l0.e
        public boolean d(Context context, int i, Drawable drawable) {
            Drawable drawableFindDrawableByLayerId;
            int iC;
            if (i == a.a.e.C) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                Drawable drawableFindDrawableByLayerId2 = layerDrawable.findDrawableByLayerId(R.id.background);
                int i2 = a.a.a.w;
                l(drawableFindDrawableByLayerId2, q0.c(context, i2), i.f950a);
                l(layerDrawable.findDrawableByLayerId(R.id.secondaryProgress), q0.c(context, i2), i.f950a);
                drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(R.id.progress);
                iC = q0.c(context, a.a.a.u);
            } else {
                if (i != a.a.e.y && i != a.a.e.x && i != a.a.e.z) {
                    return false;
                }
                LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
                l(layerDrawable2.findDrawableByLayerId(R.id.background), q0.b(context, a.a.a.w), i.f950a);
                Drawable drawableFindDrawableByLayerId3 = layerDrawable2.findDrawableByLayerId(R.id.secondaryProgress);
                int i3 = a.a.a.u;
                l(drawableFindDrawableByLayerId3, q0.c(context, i3), i.f950a);
                drawableFindDrawableByLayerId = layerDrawable2.findDrawableByLayerId(R.id.progress);
                iC = q0.c(context, i3);
            }
            l(drawableFindDrawableByLayerId, iC, i.f950a);
            return true;
        }

        @Override // androidx.appcompat.widget.l0.e
        public PorterDuff.Mode e(int i) {
            if (i == a.a.e.F) {
                return PorterDuff.Mode.MULTIPLY;
            }
            return null;
        }
    }

    public static synchronized i b() {
        if (f951b == null) {
            h();
        }
        return f951b;
    }

    public static synchronized PorterDuffColorFilter e(int i, PorterDuff.Mode mode) {
        return l0.l(i, mode);
    }

    public static synchronized void h() {
        if (f951b == null) {
            i iVar = new i();
            f951b = iVar;
            iVar.f952c = l0.h();
            f951b.f952c.u(new a());
        }
    }

    static void i(Drawable drawable, t0 t0Var, int[] iArr) {
        l0.w(drawable, t0Var, iArr);
    }

    public synchronized Drawable c(Context context, int i) {
        return this.f952c.j(context, i);
    }

    synchronized Drawable d(Context context, int i, boolean z) {
        return this.f952c.k(context, i, z);
    }

    synchronized ColorStateList f(Context context, int i) {
        return this.f952c.m(context, i);
    }

    public synchronized void g(Context context) {
        this.f952c.s(context);
    }
}
