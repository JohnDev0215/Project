package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.appcompat.widget.v;

/* JADX INFO: compiled from: AppCompatDrawableManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final PorterDuff.Mode f951a = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static f f952b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private v f953c;

    /* JADX INFO: compiled from: AppCompatDrawableManager.java */
    class a implements v.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int[] f954a = {c.a.e.S, c.a.e.Q, c.a.e.f1978a};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int[] f955b = {c.a.e.o, c.a.e.B, c.a.e.t, c.a.e.p, c.a.e.q, c.a.e.s, c.a.e.r};

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int[] f956c = {c.a.e.P, c.a.e.R, c.a.e.k, c.a.e.I, c.a.e.J, c.a.e.L, c.a.e.N, c.a.e.K, c.a.e.M, c.a.e.O};

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final int[] f957d = {c.a.e.w, c.a.e.i, c.a.e.v};
        private final int[] e = {c.a.e.H, c.a.e.T};
        private final int[] f = {c.a.e.f1980c, c.a.e.g, c.a.e.f1981d, c.a.e.h};

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
            int iC = z.c(context, c.a.a.w);
            return new ColorStateList(new int[][]{z.f1031b, z.e, z.f1032c, z.i}, new int[]{z.b(context, c.a.a.u), c.g.d.a.b(iC, i), c.g.d.a.b(iC, i), i});
        }

        private ColorStateList i(Context context) {
            return h(context, z.c(context, c.a.a.t));
        }

        private ColorStateList j(Context context) {
            return h(context, z.c(context, c.a.a.u));
        }

        private ColorStateList k(Context context) {
            int[][] iArr = new int[3][];
            int[] iArr2 = new int[3];
            int i = c.a.a.z;
            ColorStateList colorStateListE = z.e(context, i);
            if (colorStateListE == null || !colorStateListE.isStateful()) {
                iArr[0] = z.f1031b;
                iArr2[0] = z.b(context, i);
                iArr[1] = z.f;
                iArr2[1] = z.c(context, c.a.a.v);
                iArr[2] = z.i;
                iArr2[2] = z.c(context, i);
            } else {
                iArr[0] = z.f1031b;
                iArr2[0] = colorStateListE.getColorForState(iArr[0], 0);
                iArr[1] = z.f;
                iArr2[1] = z.c(context, c.a.a.v);
                iArr[2] = z.i;
                iArr2[2] = colorStateListE.getDefaultColor();
            }
            return new ColorStateList(iArr, iArr2);
        }

        private void l(Drawable drawable, int i, PorterDuff.Mode mode) {
            if (q.a(drawable)) {
                drawable = drawable.mutate();
            }
            if (mode == null) {
                mode = f.f951a;
            }
            drawable.setColorFilter(f.e(i, mode));
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0046  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0061 A[RETURN] */
        @Override // androidx.appcompat.widget.v.e
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean a(android.content.Context r7, int r8, android.graphics.drawable.Drawable r9) {
            /*
                r6 = this;
                android.graphics.PorterDuff$Mode r0 = androidx.appcompat.widget.f.a()
                int[] r1 = r6.f954a
                boolean r1 = r6.f(r1, r8)
                r2 = 16842801(0x1010031, float:2.3693695E-38)
                r3 = -1
                r4 = 0
                r5 = 1
                if (r1 == 0) goto L17
                int r2 = c.a.a.x
            L14:
                r8 = r3
            L15:
                r1 = r5
                goto L44
            L17:
                int[] r1 = r6.f956c
                boolean r1 = r6.f(r1, r8)
                if (r1 == 0) goto L22
                int r2 = c.a.a.v
                goto L14
            L22:
                int[] r1 = r6.f957d
                boolean r1 = r6.f(r1, r8)
                if (r1 == 0) goto L2d
                android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
                goto L14
            L2d:
                int r1 = c.a.e.u
                if (r8 != r1) goto L3c
                r2 = 16842800(0x1010030, float:2.3693693E-38)
                r8 = 1109603123(0x42233333, float:40.8)
                int r8 = java.lang.Math.round(r8)
                goto L15
            L3c:
                int r1 = c.a.e.l
                if (r8 != r1) goto L41
                goto L14
            L41:
                r8 = r3
                r1 = r4
                r2 = r1
            L44:
                if (r1 == 0) goto L61
                boolean r1 = androidx.appcompat.widget.q.a(r9)
                if (r1 == 0) goto L50
                android.graphics.drawable.Drawable r9 = r9.mutate()
            L50:
                int r7 = androidx.appcompat.widget.z.c(r7, r2)
                android.graphics.PorterDuffColorFilter r7 = androidx.appcompat.widget.f.e(r7, r0)
                r9.setColorFilter(r7)
                if (r8 == r3) goto L60
                r9.setAlpha(r8)
            L60:
                return r5
            L61:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.f.a.a(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
        }

        @Override // androidx.appcompat.widget.v.e
        public PorterDuff.Mode b(int i) {
            if (i == c.a.e.F) {
                return PorterDuff.Mode.MULTIPLY;
            }
            return null;
        }

        @Override // androidx.appcompat.widget.v.e
        public Drawable c(v vVar, Context context, int i) {
            if (i == c.a.e.j) {
                return new LayerDrawable(new Drawable[]{vVar.j(context, c.a.e.i), vVar.j(context, c.a.e.k)});
            }
            return null;
        }

        @Override // androidx.appcompat.widget.v.e
        public ColorStateList d(Context context, int i) {
            if (i == c.a.e.m) {
                return c.a.k.a.a.c(context, c.a.c.e);
            }
            if (i == c.a.e.G) {
                return c.a.k.a.a.c(context, c.a.c.h);
            }
            if (i == c.a.e.F) {
                return k(context);
            }
            if (i == c.a.e.f) {
                return j(context);
            }
            if (i == c.a.e.f1979b) {
                return g(context);
            }
            if (i == c.a.e.e) {
                return i(context);
            }
            if (i == c.a.e.D || i == c.a.e.E) {
                return c.a.k.a.a.c(context, c.a.c.g);
            }
            if (f(this.f955b, i)) {
                return z.e(context, c.a.a.x);
            }
            if (f(this.e, i)) {
                return c.a.k.a.a.c(context, c.a.c.f1973d);
            }
            if (f(this.f, i)) {
                return c.a.k.a.a.c(context, c.a.c.f1972c);
            }
            if (i == c.a.e.A) {
                return c.a.k.a.a.c(context, c.a.c.f);
            }
            return null;
        }

        @Override // androidx.appcompat.widget.v.e
        public boolean e(Context context, int i, Drawable drawable) {
            if (i == c.a.e.C) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                Drawable drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(R.id.background);
                int i2 = c.a.a.x;
                l(drawableFindDrawableByLayerId, z.c(context, i2), f.f951a);
                l(layerDrawable.findDrawableByLayerId(R.id.secondaryProgress), z.c(context, i2), f.f951a);
                l(layerDrawable.findDrawableByLayerId(R.id.progress), z.c(context, c.a.a.v), f.f951a);
                return true;
            }
            if (i != c.a.e.y && i != c.a.e.x && i != c.a.e.z) {
                return false;
            }
            LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
            l(layerDrawable2.findDrawableByLayerId(R.id.background), z.b(context, c.a.a.x), f.f951a);
            Drawable drawableFindDrawableByLayerId2 = layerDrawable2.findDrawableByLayerId(R.id.secondaryProgress);
            int i3 = c.a.a.v;
            l(drawableFindDrawableByLayerId2, z.c(context, i3), f.f951a);
            l(layerDrawable2.findDrawableByLayerId(R.id.progress), z.c(context, i3), f.f951a);
            return true;
        }
    }

    public static synchronized f b() {
        if (f952b == null) {
            h();
        }
        return f952b;
    }

    public static synchronized PorterDuffColorFilter e(int i, PorterDuff.Mode mode) {
        return v.l(i, mode);
    }

    public static synchronized void h() {
        if (f952b == null) {
            f fVar = new f();
            f952b = fVar;
            fVar.f953c = v.h();
            f952b.f953c.u(new a());
        }
    }

    static void i(Drawable drawable, c0 c0Var, int[] iArr) {
        v.w(drawable, c0Var, iArr);
    }

    public synchronized Drawable c(Context context, int i) {
        return this.f953c.j(context, i);
    }

    synchronized Drawable d(Context context, int i, boolean z) {
        return this.f953c.k(context, i, z);
    }

    synchronized ColorStateList f(Context context, int i) {
        return this.f953c.m(context, i);
    }

    public synchronized void g(Context context) {
        this.f953c.s(context);
    }
}
