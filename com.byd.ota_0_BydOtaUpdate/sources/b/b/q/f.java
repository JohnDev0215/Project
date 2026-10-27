package b.b.q;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import b.b.q.v;

/* JADX INFO: compiled from: AppCompatDrawableManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final PorterDuff.Mode f1015a = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static f f1016b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public v f1017c;

    /* JADX INFO: compiled from: AppCompatDrawableManager.java */
    public class a implements v.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int[] f1018a = {b.b.e.S, b.b.e.Q, b.b.e.f731a};

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int[] f1019b = {b.b.e.o, b.b.e.B, b.b.e.t, b.b.e.p, b.b.e.q, b.b.e.s, b.b.e.r};

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int[] f1020c = {b.b.e.P, b.b.e.R, b.b.e.k, b.b.e.I, b.b.e.J, b.b.e.L, b.b.e.N, b.b.e.K, b.b.e.M, b.b.e.O};

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int[] f1021d = {b.b.e.w, b.b.e.f739i, b.b.e.v};

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final int[] f1022e = {b.b.e.H, b.b.e.T};

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final int[] f1023f = {b.b.e.f733c, b.b.e.f737g, b.b.e.f734d, b.b.e.f738h};

        @Override // b.b.q.v.e
        public Drawable a(v vVar, Context context, int i2) {
            if (i2 == b.b.e.j) {
                return new LayerDrawable(new Drawable[]{vVar.j(context, b.b.e.f739i), vVar.j(context, b.b.e.k)});
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0046  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0061 A[RETURN] */
        @Override // b.b.q.v.e
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean b(android.content.Context r7, int r8, android.graphics.drawable.Drawable r9) {
            /*
                r6 = this;
                android.graphics.PorterDuff$Mode r0 = b.b.q.f.a()
                int[] r1 = r6.f1018a
                boolean r1 = r6.f(r1, r8)
                r2 = 16842801(0x1010031, float:2.3693695E-38)
                r3 = -1
                r4 = 0
                r5 = 1
                if (r1 == 0) goto L17
                int r2 = b.b.a.x
            L14:
                r8 = r3
            L15:
                r1 = r5
                goto L44
            L17:
                int[] r1 = r6.f1020c
                boolean r1 = r6.f(r1, r8)
                if (r1 == 0) goto L22
                int r2 = b.b.a.v
                goto L14
            L22:
                int[] r1 = r6.f1021d
                boolean r1 = r6.f(r1, r8)
                if (r1 == 0) goto L2d
                android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
                goto L14
            L2d:
                int r1 = b.b.e.u
                if (r8 != r1) goto L3c
                r2 = 16842800(0x1010030, float:2.3693693E-38)
                r8 = 1109603123(0x42233333, float:40.8)
                int r8 = java.lang.Math.round(r8)
                goto L15
            L3c:
                int r1 = b.b.e.l
                if (r8 != r1) goto L41
                goto L14
            L41:
                r8 = r3
                r1 = r4
                r2 = r1
            L44:
                if (r1 == 0) goto L61
                boolean r1 = b.b.q.q.a(r9)
                if (r1 == 0) goto L50
                android.graphics.drawable.Drawable r9 = r9.mutate()
            L50:
                int r7 = b.b.q.z.c(r7, r2)
                android.graphics.PorterDuffColorFilter r7 = b.b.q.f.e(r7, r0)
                r9.setColorFilter(r7)
                if (r8 == r3) goto L60
                r9.setAlpha(r8)
            L60:
                return r5
            L61:
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: b.b.q.f.a.b(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
        }

        @Override // b.b.q.v.e
        public ColorStateList c(Context context, int i2) {
            if (i2 == b.b.e.m) {
                return b.b.l.a.a.c(context, b.b.c.f718e);
            }
            if (i2 == b.b.e.G) {
                return b.b.l.a.a.c(context, b.b.c.f721h);
            }
            if (i2 == b.b.e.F) {
                return k(context);
            }
            if (i2 == b.b.e.f736f) {
                return j(context);
            }
            if (i2 == b.b.e.f732b) {
                return g(context);
            }
            if (i2 == b.b.e.f735e) {
                return i(context);
            }
            if (i2 == b.b.e.D || i2 == b.b.e.E) {
                return b.b.l.a.a.c(context, b.b.c.f720g);
            }
            if (f(this.f1019b, i2)) {
                return z.e(context, b.b.a.x);
            }
            if (f(this.f1022e, i2)) {
                return b.b.l.a.a.c(context, b.b.c.f717d);
            }
            if (f(this.f1023f, i2)) {
                return b.b.l.a.a.c(context, b.b.c.f716c);
            }
            if (i2 == b.b.e.A) {
                return b.b.l.a.a.c(context, b.b.c.f719f);
            }
            return null;
        }

        @Override // b.b.q.v.e
        public boolean d(Context context, int i2, Drawable drawable) {
            if (i2 == b.b.e.C) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                Drawable drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(R.id.background);
                int i3 = b.b.a.x;
                l(drawableFindDrawableByLayerId, z.c(context, i3), f.f1015a);
                l(layerDrawable.findDrawableByLayerId(R.id.secondaryProgress), z.c(context, i3), f.f1015a);
                l(layerDrawable.findDrawableByLayerId(R.id.progress), z.c(context, b.b.a.v), f.f1015a);
                return true;
            }
            if (i2 != b.b.e.y && i2 != b.b.e.x && i2 != b.b.e.z) {
                return false;
            }
            LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
            l(layerDrawable2.findDrawableByLayerId(R.id.background), z.b(context, b.b.a.x), f.f1015a);
            Drawable drawableFindDrawableByLayerId2 = layerDrawable2.findDrawableByLayerId(R.id.secondaryProgress);
            int i4 = b.b.a.v;
            l(drawableFindDrawableByLayerId2, z.c(context, i4), f.f1015a);
            l(layerDrawable2.findDrawableByLayerId(R.id.progress), z.c(context, i4), f.f1015a);
            return true;
        }

        @Override // b.b.q.v.e
        public PorterDuff.Mode e(int i2) {
            if (i2 == b.b.e.F) {
                return PorterDuff.Mode.MULTIPLY;
            }
            return null;
        }

        public final boolean f(int[] iArr, int i2) {
            for (int i3 : iArr) {
                if (i3 == i2) {
                    return true;
                }
            }
            return false;
        }

        public final ColorStateList g(Context context) {
            return h(context, 0);
        }

        public final ColorStateList h(Context context, int i2) {
            int iC = z.c(context, b.b.a.w);
            return new ColorStateList(new int[][]{z.f1127b, z.f1130e, z.f1128c, z.f1134i}, new int[]{z.b(context, b.b.a.u), b.f.g.a.b(iC, i2), b.f.g.a.b(iC, i2), i2});
        }

        public final ColorStateList i(Context context) {
            return h(context, z.c(context, b.b.a.t));
        }

        public final ColorStateList j(Context context) {
            return h(context, z.c(context, b.b.a.u));
        }

        public final ColorStateList k(Context context) {
            int[][] iArr = new int[3][];
            int[] iArr2 = new int[3];
            int i2 = b.b.a.y;
            ColorStateList colorStateListE = z.e(context, i2);
            if (colorStateListE == null || !colorStateListE.isStateful()) {
                iArr[0] = z.f1127b;
                iArr2[0] = z.b(context, i2);
                iArr[1] = z.f1131f;
                iArr2[1] = z.c(context, b.b.a.v);
                iArr[2] = z.f1134i;
                iArr2[2] = z.c(context, i2);
            } else {
                iArr[0] = z.f1127b;
                iArr2[0] = colorStateListE.getColorForState(iArr[0], 0);
                iArr[1] = z.f1131f;
                iArr2[1] = z.c(context, b.b.a.v);
                iArr[2] = z.f1134i;
                iArr2[2] = colorStateListE.getDefaultColor();
            }
            return new ColorStateList(iArr, iArr2);
        }

        public final void l(Drawable drawable, int i2, PorterDuff.Mode mode) {
            if (q.a(drawable)) {
                drawable = drawable.mutate();
            }
            if (mode == null) {
                mode = f.f1015a;
            }
            drawable.setColorFilter(f.e(i2, mode));
        }
    }

    public static synchronized f b() {
        if (f1016b == null) {
            h();
        }
        return f1016b;
    }

    public static synchronized PorterDuffColorFilter e(int i2, PorterDuff.Mode mode) {
        return v.l(i2, mode);
    }

    public static synchronized void h() {
        if (f1016b == null) {
            f fVar = new f();
            f1016b = fVar;
            fVar.f1017c = v.h();
            f1016b.f1017c.u(new a());
        }
    }

    public static void i(Drawable drawable, c0 c0Var, int[] iArr) {
        v.w(drawable, c0Var, iArr);
    }

    public synchronized Drawable c(Context context, int i2) {
        return this.f1017c.j(context, i2);
    }

    public synchronized Drawable d(Context context, int i2, boolean z) {
        return this.f1017c.k(context, i2, z);
    }

    public synchronized ColorStateList f(Context context, int i2) {
        return this.f1017c.m(context, i2);
    }

    public synchronized void g(Context context) {
        this.f1017c.s(context);
    }
}
