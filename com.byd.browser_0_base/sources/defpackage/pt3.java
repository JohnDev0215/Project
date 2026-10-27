package defpackage;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import defpackage.om3;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: WindowInsetsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class pt3 {
    public static final pt3 b;
    public final i a;

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class b extends a {
        public b() {
        }

        @Override // pt3.c
        public void c(int i, n01 n01Var) {
            this.c.setInsets(k.a(i), n01Var.e());
        }

        public b(pt3 pt3Var) {
            super(pt3Var);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class c {
        public final pt3 a;
        public n01[] b;

        public c() {
            this(new pt3());
        }

        public final void a() {
            n01[] n01VarArr = this.b;
            if (n01VarArr != null) {
                n01 n01VarA = n01VarArr[j.a(1)];
                n01 n01VarA2 = this.b[j.a(2)];
                pt3 pt3Var = this.a;
                if (n01VarA2 == null) {
                    n01VarA2 = pt3Var.a(2);
                }
                if (n01VarA == null) {
                    n01VarA = pt3Var.a(1);
                }
                g(n01.a(n01VarA, n01VarA2));
                n01 n01Var = this.b[j.a(16)];
                if (n01Var != null) {
                    f(n01Var);
                }
                n01 n01Var2 = this.b[j.a(32)];
                if (n01Var2 != null) {
                    d(n01Var2);
                }
                n01 n01Var3 = this.b[j.a(64)];
                if (n01Var3 != null) {
                    h(n01Var3);
                }
            }
        }

        public pt3 b() {
            throw null;
        }

        public void c(int i, n01 n01Var) {
            if (this.b == null) {
                this.b = new n01[9];
            }
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if ((i & i2) != 0) {
                    this.b[j.a(i2)] = n01Var;
                }
            }
        }

        public void d(n01 n01Var) {
            throw null;
        }

        public void e(n01 n01Var) {
            throw null;
        }

        public void f(n01 n01Var) {
            throw null;
        }

        public void g(n01 n01Var) {
            throw null;
        }

        public void h(n01 n01Var) {
            throw null;
        }

        public c(pt3 pt3Var) {
            this.a = pt3Var;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class d extends i {
        public static boolean h = false;
        public static Method i;
        public static Class<?> j;
        public static Field k;
        public static Field l;
        public final WindowInsets c;
        public n01[] d;
        public n01 e;
        public pt3 f;
        public n01 g;

        public d(pt3 pt3Var, WindowInsets windowInsets) {
            super(pt3Var);
            this.e = null;
            this.c = windowInsets;
        }

        @SuppressLint({"WrongConstant"})
        private n01 q(int i2, boolean z) {
            n01 n01VarA = n01.e;
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if ((i2 & i3) != 0) {
                    n01VarA = n01.a(n01VarA, r(i3, z));
                }
            }
            return n01VarA;
        }

        private n01 s() {
            pt3 pt3Var = this.f;
            return pt3Var != null ? pt3Var.a.h() : n01.e;
        }

        private n01 t(View view) {
            if (Build.VERSION.SDK_INT >= 30) {
                throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
            }
            if (!h) {
                u();
            }
            Method method = i;
            if (method != null && j != null && k != null) {
                try {
                    Object objInvoke = method.invoke(view, new Object[0]);
                    if (objInvoke == null) {
                        Log.w("WindowInsetsCompat", "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", new NullPointerException());
                        return null;
                    }
                    Rect rect = (Rect) k.get(l.get(objInvoke));
                    if (rect != null) {
                        return n01.c(rect);
                    }
                    return null;
                } catch (ReflectiveOperationException e) {
                    Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e.getMessage(), e);
                }
            }
            return null;
        }

        @SuppressLint({"PrivateApi"})
        private static void u() {
            try {
                i = View.class.getDeclaredMethod("getViewRootImpl", new Class[0]);
                Class<?> cls = Class.forName("android.view.View$AttachInfo");
                j = cls;
                k = cls.getDeclaredField("mVisibleInsets");
                l = Class.forName("android.view.ViewRootImpl").getDeclaredField("mAttachInfo");
                k.setAccessible(true);
                l.setAccessible(true);
            } catch (ReflectiveOperationException e) {
                Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e.getMessage(), e);
            }
            h = true;
        }

        @Override // pt3.i
        public void d(View view) {
            n01 n01VarT = t(view);
            if (n01VarT == null) {
                n01VarT = n01.e;
            }
            v(n01VarT);
        }

        @Override // pt3.i
        public boolean equals(Object obj) {
            if (super.equals(obj)) {
                return Objects.equals(this.g, ((d) obj).g);
            }
            return false;
        }

        @Override // pt3.i
        public n01 f(int i2) {
            return q(i2, false);
        }

        @Override // pt3.i
        public final n01 j() {
            if (this.e == null) {
                WindowInsets windowInsets = this.c;
                this.e = n01.b(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
            }
            return this.e;
        }

        @Override // pt3.i
        public pt3 l(int i2, int i3, int i4, int i5) {
            pt3 pt3VarI = pt3.i(null, this.c);
            c bVar = Build.VERSION.SDK_INT >= 30 ? new b(pt3VarI) : new a(pt3VarI);
            bVar.g(pt3.f(j(), i2, i3, i4, i5));
            bVar.e(pt3.f(h(), i2, i3, i4, i5));
            return bVar.b();
        }

        @Override // pt3.i
        public boolean n() {
            return this.c.isRound();
        }

        @Override // pt3.i
        public void o(n01[] n01VarArr) {
            this.d = n01VarArr;
        }

        @Override // pt3.i
        public void p(pt3 pt3Var) {
            this.f = pt3Var;
        }

        public n01 r(int i2, boolean z) {
            n01 n01VarH;
            int i3;
            if (i2 == 1) {
                return z ? n01.b(0, Math.max(s().b, j().b), 0, 0) : n01.b(0, j().b, 0, 0);
            }
            if (i2 == 2) {
                if (z) {
                    n01 n01VarS = s();
                    n01 n01VarH2 = h();
                    return n01.b(Math.max(n01VarS.a, n01VarH2.a), 0, Math.max(n01VarS.c, n01VarH2.c), Math.max(n01VarS.d, n01VarH2.d));
                }
                n01 n01VarJ = j();
                pt3 pt3Var = this.f;
                n01VarH = pt3Var != null ? pt3Var.a.h() : null;
                int iMin = n01VarJ.d;
                if (n01VarH != null) {
                    iMin = Math.min(iMin, n01VarH.d);
                }
                return n01.b(n01VarJ.a, 0, n01VarJ.c, iMin);
            }
            n01 n01Var = n01.e;
            if (i2 == 8) {
                n01[] n01VarArr = this.d;
                n01VarH = n01VarArr != null ? n01VarArr[j.a(8)] : null;
                if (n01VarH != null) {
                    return n01VarH;
                }
                n01 n01VarJ2 = j();
                n01 n01VarS2 = s();
                int i4 = n01VarJ2.d;
                if (i4 > n01VarS2.d) {
                    return n01.b(0, 0, 0, i4);
                }
                n01 n01Var2 = this.g;
                return (n01Var2 == null || n01Var2.equals(n01Var) || (i3 = this.g.d) <= n01VarS2.d) ? n01Var : n01.b(0, 0, 0, i3);
            }
            if (i2 == 16) {
                return i();
            }
            if (i2 == 32) {
                return g();
            }
            if (i2 == 64) {
                return k();
            }
            if (i2 != 128) {
                return n01Var;
            }
            pt3 pt3Var2 = this.f;
            ab0 ab0VarE = pt3Var2 != null ? pt3Var2.a.e() : e();
            if (ab0VarE == null) {
                return n01Var;
            }
            DisplayCutout displayCutout = ab0VarE.a;
            return n01.b(displayCutout.getSafeInsetLeft(), displayCutout.getSafeInsetTop(), displayCutout.getSafeInsetRight(), displayCutout.getSafeInsetBottom());
        }

        public void v(n01 n01Var) {
            this.g = n01Var;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class e extends d {
        public n01 m;

        public e(pt3 pt3Var, WindowInsets windowInsets) {
            super(pt3Var, windowInsets);
            this.m = null;
        }

        @Override // pt3.i
        public pt3 b() {
            return pt3.i(null, this.c.consumeStableInsets());
        }

        @Override // pt3.i
        public pt3 c() {
            return pt3.i(null, this.c.consumeSystemWindowInsets());
        }

        @Override // pt3.i
        public final n01 h() {
            if (this.m == null) {
                WindowInsets windowInsets = this.c;
                this.m = n01.b(windowInsets.getStableInsetLeft(), windowInsets.getStableInsetTop(), windowInsets.getStableInsetRight(), windowInsets.getStableInsetBottom());
            }
            return this.m;
        }

        @Override // pt3.i
        public boolean m() {
            return this.c.isConsumed();
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class f extends e {
        public f(pt3 pt3Var, WindowInsets windowInsets) {
            super(pt3Var, windowInsets);
        }

        @Override // pt3.i
        public pt3 a() {
            return pt3.i(null, this.c.consumeDisplayCutout());
        }

        @Override // pt3.i
        public ab0 e() {
            DisplayCutout displayCutout = this.c.getDisplayCutout();
            if (displayCutout == null) {
                return null;
            }
            return new ab0(displayCutout);
        }

        @Override // pt3.d, pt3.i
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof f)) {
                return false;
            }
            f fVar = (f) obj;
            return Objects.equals(this.c, fVar.c) && Objects.equals(this.g, fVar.g);
        }

        @Override // pt3.i
        public int hashCode() {
            return this.c.hashCode();
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class g extends f {
        public n01 n;
        public n01 o;
        public n01 p;

        public g(pt3 pt3Var, WindowInsets windowInsets) {
            super(pt3Var, windowInsets);
            this.n = null;
            this.o = null;
            this.p = null;
        }

        @Override // pt3.i
        public n01 g() {
            if (this.o == null) {
                this.o = n01.d(this.c.getMandatorySystemGestureInsets());
            }
            return this.o;
        }

        @Override // pt3.i
        public n01 i() {
            if (this.n == null) {
                this.n = n01.d(this.c.getSystemGestureInsets());
            }
            return this.n;
        }

        @Override // pt3.i
        public n01 k() {
            if (this.p == null) {
                this.p = n01.d(this.c.getTappableElementInsets());
            }
            return this.p;
        }

        @Override // pt3.d, pt3.i
        public pt3 l(int i, int i2, int i3, int i4) {
            return pt3.i(null, this.c.inset(i, i2, i3, i4));
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static final class j {
        public static int a(int i) {
            if (i == 1) {
                return 0;
            }
            if (i == 2) {
                return 1;
            }
            if (i == 4) {
                return 2;
            }
            if (i == 8) {
                return 3;
            }
            if (i == 16) {
                return 4;
            }
            if (i == 32) {
                return 5;
            }
            if (i == 64) {
                return 6;
            }
            if (i == 128) {
                return 7;
            }
            if (i == 256) {
                return 8;
            }
            throw new IllegalArgumentException(gq2.e("type needs to be >= FIRST and <= LAST, type=", i));
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static final class k {
        public static int a(int i) {
            int iStatusBars;
            int i2 = 0;
            for (int i3 = 1; i3 <= 256; i3 <<= 1) {
                if ((i & i3) != 0) {
                    if (i3 == 1) {
                        iStatusBars = WindowInsets.Type.statusBars();
                    } else if (i3 == 2) {
                        iStatusBars = WindowInsets.Type.navigationBars();
                    } else if (i3 == 4) {
                        iStatusBars = WindowInsets.Type.captionBar();
                    } else if (i3 == 8) {
                        iStatusBars = WindowInsets.Type.ime();
                    } else if (i3 == 16) {
                        iStatusBars = WindowInsets.Type.systemGestures();
                    } else if (i3 == 32) {
                        iStatusBars = WindowInsets.Type.mandatorySystemGestures();
                    } else if (i3 == 64) {
                        iStatusBars = WindowInsets.Type.tappableElement();
                    } else if (i3 == 128) {
                        iStatusBars = WindowInsets.Type.displayCutout();
                    }
                    i2 |= iStatusBars;
                }
            }
            return i2;
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 30) {
            b = h.q;
        } else {
            b = i.b;
        }
    }

    public pt3(WindowInsets windowInsets) {
        if (Build.VERSION.SDK_INT >= 30) {
            this.a = new h(this, windowInsets);
        } else {
            this.a = new g(this, windowInsets);
        }
    }

    public static n01 f(n01 n01Var, int i2, int i3, int i4, int i5) {
        int iMax = Math.max(0, n01Var.a - i2);
        int iMax2 = Math.max(0, n01Var.b - i3);
        int iMax3 = Math.max(0, n01Var.c - i4);
        int iMax4 = Math.max(0, n01Var.d - i5);
        return (iMax == i2 && iMax2 == i3 && iMax3 == i4 && iMax4 == i5) ? n01Var : n01.b(iMax, iMax2, iMax3, iMax4);
    }

    public static pt3 i(View view, WindowInsets windowInsets) {
        windowInsets.getClass();
        pt3 pt3Var = new pt3(windowInsets);
        if (view != null) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if (om3.g.b(view)) {
                pt3 pt3VarA = om3.j.a(view);
                i iVar = pt3Var.a;
                iVar.p(pt3VarA);
                iVar.d(view.getRootView());
            }
        }
        return pt3Var;
    }

    public final n01 a(int i2) {
        return this.a.f(i2);
    }

    @Deprecated
    public final int b() {
        return this.a.j().d;
    }

    @Deprecated
    public final int c() {
        return this.a.j().a;
    }

    @Deprecated
    public final int d() {
        return this.a.j().c;
    }

    @Deprecated
    public final int e() {
        return this.a.j().b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof pt3)) {
            return false;
        }
        return Objects.equals(this.a, ((pt3) obj).a);
    }

    @Deprecated
    public final pt3 g(int i2, int i3, int i4, int i5) {
        c bVar = Build.VERSION.SDK_INT >= 30 ? new b(this) : new a(this);
        bVar.g(n01.b(i2, i3, i4, i5));
        return bVar.b();
    }

    public final WindowInsets h() {
        i iVar = this.a;
        if (iVar instanceof d) {
            return ((d) iVar).c;
        }
        return null;
    }

    public final int hashCode() {
        i iVar = this.a;
        if (iVar == null) {
            return 0;
        }
        return iVar.hashCode();
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class a extends c {
        public final WindowInsets.Builder c;

        public a() {
            this.c = new WindowInsets.Builder();
        }

        @Override // pt3.c
        public pt3 b() {
            a();
            pt3 pt3VarI = pt3.i(null, this.c.build());
            pt3VarI.a.o(this.b);
            return pt3VarI;
        }

        @Override // pt3.c
        public void d(n01 n01Var) {
            this.c.setMandatorySystemGestureInsets(n01Var.e());
        }

        @Override // pt3.c
        public void e(n01 n01Var) {
            this.c.setStableInsets(n01Var.e());
        }

        @Override // pt3.c
        public void f(n01 n01Var) {
            this.c.setSystemGestureInsets(n01Var.e());
        }

        @Override // pt3.c
        public void g(n01 n01Var) {
            this.c.setSystemWindowInsets(n01Var.e());
        }

        @Override // pt3.c
        public void h(n01 n01Var) {
            this.c.setTappableElementInsets(n01Var.e());
        }

        public a(pt3 pt3Var) {
            WindowInsets.Builder builder;
            super(pt3Var);
            WindowInsets windowInsetsH = pt3Var.h();
            if (windowInsetsH != null) {
                builder = new WindowInsets.Builder(windowInsetsH);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.c = builder;
        }
    }

    public pt3() {
        this.a = new i(this);
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class h extends g {
        public static final pt3 q = pt3.i(null, WindowInsets.CONSUMED);

        public h(pt3 pt3Var, WindowInsets windowInsets) {
            super(pt3Var, windowInsets);
        }

        @Override // pt3.d, pt3.i
        public n01 f(int i) {
            return n01.d(this.c.getInsets(k.a(i)));
        }

        @Override // pt3.d, pt3.i
        public final void d(View view) {
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class i {
        public static final pt3 b;
        public final pt3 a;

        static {
            b = (Build.VERSION.SDK_INT >= 30 ? new b() : new a()).b().a.a().a.b().a.c();
        }

        public i(pt3 pt3Var) {
            this.a = pt3Var;
        }

        public pt3 a() {
            return this.a;
        }

        public pt3 b() {
            return this.a;
        }

        public pt3 c() {
            return this.a;
        }

        public ab0 e() {
            return null;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof i)) {
                return false;
            }
            i iVar = (i) obj;
            return n() == iVar.n() && m() == iVar.m() && Objects.equals(j(), iVar.j()) && Objects.equals(h(), iVar.h()) && Objects.equals(e(), iVar.e());
        }

        public n01 f(int i) {
            return n01.e;
        }

        public n01 g() {
            return j();
        }

        public n01 h() {
            return n01.e;
        }

        public int hashCode() {
            return Objects.hash(Boolean.valueOf(n()), Boolean.valueOf(m()), j(), h(), e());
        }

        public n01 i() {
            return j();
        }

        public n01 j() {
            return n01.e;
        }

        public n01 k() {
            return j();
        }

        public pt3 l(int i, int i2, int i3, int i4) {
            return b;
        }

        public boolean m() {
            return false;
        }

        public boolean n() {
            return false;
        }

        public void d(View view) {
        }

        public void o(n01[] n01VarArr) {
        }

        public void p(pt3 pt3Var) {
        }
    }
}
