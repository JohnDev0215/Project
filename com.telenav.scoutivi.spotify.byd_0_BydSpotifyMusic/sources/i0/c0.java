package i0;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/* JADX INFO: compiled from: WindowInsetsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class c0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final c0 f6843b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final k f6844a;

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class d extends c {
        d() {
        }

        d(c0 c0Var) {
            super(c0Var);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final c0 f6853a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        z.b[] f6854b;

        e() {
            this(new c0((c0) null));
        }

        protected final void a() {
            z.b[] bVarArr = this.f6854b;
            if (bVarArr != null) {
                z.b bVar = bVarArr[l.a(1)];
                z.b bVar2 = this.f6854b[l.a(2)];
                if (bVar != null && bVar2 != null) {
                    f(z.b.a(bVar, bVar2));
                } else if (bVar != null) {
                    f(bVar);
                } else if (bVar2 != null) {
                    f(bVar2);
                }
                z.b bVar3 = this.f6854b[l.a(16)];
                if (bVar3 != null) {
                    e(bVar3);
                }
                z.b bVar4 = this.f6854b[l.a(32)];
                if (bVar4 != null) {
                    c(bVar4);
                }
                z.b bVar5 = this.f6854b[l.a(64)];
                if (bVar5 != null) {
                    g(bVar5);
                }
            }
        }

        c0 b() {
            throw null;
        }

        void c(z.b bVar) {
        }

        void d(z.b bVar) {
            throw null;
        }

        void e(z.b bVar) {
        }

        void f(z.b bVar) {
            throw null;
        }

        void g(z.b bVar) {
        }

        e(c0 c0Var) {
            this.f6853a = c0Var;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class h extends g {
        h(c0 c0Var, WindowInsets windowInsets) {
            super(c0Var, windowInsets);
        }

        @Override // i0.c0.k
        c0 a() {
            return c0.s(this.f6861c.consumeDisplayCutout());
        }

        @Override // i0.c0.f, i0.c0.k
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof h)) {
                return false;
            }
            h hVar = (h) obj;
            return Objects.equals(this.f6861c, hVar.f6861c) && Objects.equals(this.f6865g, hVar.f6865g);
        }

        @Override // i0.c0.k
        i0.d f() {
            return i0.d.a(this.f6861c.getDisplayCutout());
        }

        @Override // i0.c0.k
        public int hashCode() {
            return this.f6861c.hashCode();
        }

        h(c0 c0Var, h hVar) {
            super(c0Var, hVar);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class j extends i {

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        static final c0 f6870r = c0.s(WindowInsets.CONSUMED);

        j(c0 c0Var, WindowInsets windowInsets) {
            super(c0Var, windowInsets);
        }

        @Override // i0.c0.f, i0.c0.k
        final void d(View view) {
        }

        j(c0 c0Var, j jVar) {
            super(c0Var, jVar);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class k {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final c0 f6871b = new a().a().a().b().c();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c0 f6872a;

        k(c0 c0Var) {
            this.f6872a = c0Var;
        }

        c0 a() {
            return this.f6872a;
        }

        c0 b() {
            return this.f6872a;
        }

        c0 c() {
            return this.f6872a;
        }

        void d(View view) {
        }

        void e(c0 c0Var) {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof k)) {
                return false;
            }
            k kVar = (k) obj;
            return l() == kVar.l() && k() == kVar.k() && h0.c.a(i(), kVar.i()) && h0.c.a(h(), kVar.h()) && h0.c.a(f(), kVar.f());
        }

        i0.d f() {
            return null;
        }

        z.b g() {
            return i();
        }

        z.b h() {
            return z.b.f10021e;
        }

        public int hashCode() {
            return h0.c.b(Boolean.valueOf(l()), Boolean.valueOf(k()), i(), h(), f());
        }

        z.b i() {
            return z.b.f10021e;
        }

        c0 j(int i7, int i8, int i9, int i10) {
            return f6871b;
        }

        boolean k() {
            return false;
        }

        boolean l() {
            return false;
        }

        public void m(z.b[] bVarArr) {
        }

        void n(z.b bVar) {
        }

        void o(c0 c0Var) {
        }

        public void p(z.b bVar) {
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static final class l {
        static int a(int i7) {
            if (i7 == 1) {
                return 0;
            }
            if (i7 == 2) {
                return 1;
            }
            if (i7 == 4) {
                return 2;
            }
            if (i7 == 8) {
                return 3;
            }
            if (i7 == 16) {
                return 4;
            }
            if (i7 == 32) {
                return 5;
            }
            if (i7 == 64) {
                return 6;
            }
            if (i7 == 128) {
                return 7;
            }
            if (i7 == 256) {
                return 8;
            }
            throw new IllegalArgumentException("type needs to be >= FIRST and <= LAST, type=" + i7);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 30) {
            f6843b = j.f6870r;
        } else {
            f6843b = k.f6871b;
        }
    }

    private c0(WindowInsets windowInsets) {
        int i7 = Build.VERSION.SDK_INT;
        if (i7 >= 30) {
            this.f6844a = new j(this, windowInsets);
        } else if (i7 >= 29) {
            this.f6844a = new i(this, windowInsets);
        } else {
            this.f6844a = new h(this, windowInsets);
        }
    }

    static z.b k(z.b bVar, int i7, int i8, int i9, int i10) {
        int iMax = Math.max(0, bVar.f10022a - i7);
        int iMax2 = Math.max(0, bVar.f10023b - i8);
        int iMax3 = Math.max(0, bVar.f10024c - i9);
        int iMax4 = Math.max(0, bVar.f10025d - i10);
        return (iMax == i7 && iMax2 == i8 && iMax3 == i9 && iMax4 == i10) ? bVar : z.b.b(iMax, iMax2, iMax3, iMax4);
    }

    public static c0 s(WindowInsets windowInsets) {
        return t(windowInsets, null);
    }

    public static c0 t(WindowInsets windowInsets, View view) {
        c0 c0Var = new c0((WindowInsets) h0.h.f(windowInsets));
        if (view != null && view.isAttachedToWindow()) {
            c0Var.p(u.F(view));
            c0Var.d(view.getRootView());
        }
        return c0Var;
    }

    @Deprecated
    public c0 a() {
        return this.f6844a.a();
    }

    @Deprecated
    public c0 b() {
        return this.f6844a.b();
    }

    @Deprecated
    public c0 c() {
        return this.f6844a.c();
    }

    void d(View view) {
        this.f6844a.d(view);
    }

    @Deprecated
    public z.b e() {
        return this.f6844a.g();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c0) {
            return h0.c.a(this.f6844a, ((c0) obj).f6844a);
        }
        return false;
    }

    @Deprecated
    public int f() {
        return this.f6844a.i().f10025d;
    }

    @Deprecated
    public int g() {
        return this.f6844a.i().f10022a;
    }

    @Deprecated
    public int h() {
        return this.f6844a.i().f10024c;
    }

    public int hashCode() {
        k kVar = this.f6844a;
        if (kVar == null) {
            return 0;
        }
        return kVar.hashCode();
    }

    @Deprecated
    public int i() {
        return this.f6844a.i().f10023b;
    }

    public c0 j(int i7, int i8, int i9, int i10) {
        return this.f6844a.j(i7, i8, i9, i10);
    }

    public boolean l() {
        return this.f6844a.k();
    }

    @Deprecated
    public c0 m(int i7, int i8, int i9, int i10) {
        return new a(this).c(z.b.b(i7, i8, i9, i10)).a();
    }

    void n(z.b[] bVarArr) {
        this.f6844a.m(bVarArr);
    }

    void o(z.b bVar) {
        this.f6844a.n(bVar);
    }

    void p(c0 c0Var) {
        this.f6844a.o(c0Var);
    }

    void q(z.b bVar) {
        this.f6844a.p(bVar);
    }

    public WindowInsets r() {
        k kVar = this.f6844a;
        if (kVar instanceof f) {
            return ((f) kVar).f6861c;
        }
        return null;
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class b extends e {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private static Field f6846e = null;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private static boolean f6847f = false;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private static Constructor<WindowInsets> f6848g = null;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private static boolean f6849h = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private WindowInsets f6850c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private z.b f6851d;

        b() {
            this.f6850c = h();
        }

        private static WindowInsets h() {
            if (!f6847f) {
                try {
                    f6846e = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException e7) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e7);
                }
                f6847f = true;
            }
            Field field = f6846e;
            if (field != null) {
                try {
                    WindowInsets windowInsets = (WindowInsets) field.get(null);
                    if (windowInsets != null) {
                        return new WindowInsets(windowInsets);
                    }
                } catch (ReflectiveOperationException e8) {
                    Log.i("WindowInsetsCompat", "Could not get value from WindowInsets.CONSUMED field", e8);
                }
            }
            if (!f6849h) {
                try {
                    f6848g = WindowInsets.class.getConstructor(Rect.class);
                } catch (ReflectiveOperationException e9) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e9);
                }
                f6849h = true;
            }
            Constructor<WindowInsets> constructor = f6848g;
            if (constructor != null) {
                try {
                    return constructor.newInstance(new Rect());
                } catch (ReflectiveOperationException e10) {
                    Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e10);
                }
            }
            return null;
        }

        @Override // i0.c0.e
        c0 b() {
            a();
            c0 c0VarS = c0.s(this.f6850c);
            c0VarS.n(this.f6854b);
            c0VarS.q(this.f6851d);
            return c0VarS;
        }

        @Override // i0.c0.e
        void d(z.b bVar) {
            this.f6851d = bVar;
        }

        @Override // i0.c0.e
        void f(z.b bVar) {
            WindowInsets windowInsets = this.f6850c;
            if (windowInsets != null) {
                this.f6850c = windowInsets.replaceSystemWindowInsets(bVar.f10022a, bVar.f10023b, bVar.f10024c, bVar.f10025d);
            }
        }

        b(c0 c0Var) {
            this.f6850c = c0Var.r();
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class c extends e {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final WindowInsets.Builder f6852c;

        c() {
            this.f6852c = new WindowInsets.Builder();
        }

        @Override // i0.c0.e
        c0 b() {
            a();
            c0 c0VarS = c0.s(this.f6852c.build());
            c0VarS.n(this.f6854b);
            return c0VarS;
        }

        @Override // i0.c0.e
        void c(z.b bVar) {
            this.f6852c.setMandatorySystemGestureInsets(bVar.e());
        }

        @Override // i0.c0.e
        void d(z.b bVar) {
            this.f6852c.setStableInsets(bVar.e());
        }

        @Override // i0.c0.e
        void e(z.b bVar) {
            this.f6852c.setSystemGestureInsets(bVar.e());
        }

        @Override // i0.c0.e
        void f(z.b bVar) {
            this.f6852c.setSystemWindowInsets(bVar.e());
        }

        @Override // i0.c0.e
        void g(z.b bVar) {
            this.f6852c.setTappableElementInsets(bVar.e());
        }

        c(c0 c0Var) {
            WindowInsets.Builder builder;
            WindowInsets windowInsetsR = c0Var.r();
            if (windowInsetsR != null) {
                builder = new WindowInsets.Builder(windowInsetsR);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.f6852c = builder;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class g extends f {

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        private z.b f6866n;

        g(c0 c0Var, WindowInsets windowInsets) {
            super(c0Var, windowInsets);
            this.f6866n = null;
        }

        @Override // i0.c0.k
        c0 b() {
            return c0.s(this.f6861c.consumeStableInsets());
        }

        @Override // i0.c0.k
        c0 c() {
            return c0.s(this.f6861c.consumeSystemWindowInsets());
        }

        @Override // i0.c0.k
        final z.b h() {
            if (this.f6866n == null) {
                this.f6866n = z.b.b(this.f6861c.getStableInsetLeft(), this.f6861c.getStableInsetTop(), this.f6861c.getStableInsetRight(), this.f6861c.getStableInsetBottom());
            }
            return this.f6866n;
        }

        @Override // i0.c0.k
        boolean k() {
            return this.f6861c.isConsumed();
        }

        @Override // i0.c0.k
        public void p(z.b bVar) {
            this.f6866n = bVar;
        }

        g(c0 c0Var, g gVar) {
            super(c0Var, gVar);
            this.f6866n = null;
            this.f6866n = gVar.f6866n;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class f extends k {

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private static boolean f6855h = false;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private static Method f6856i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private static Class<?> f6857j;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        private static Class<?> f6858k;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        private static Field f6859l;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        private static Field f6860m;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final WindowInsets f6861c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private z.b[] f6862d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private z.b f6863e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private c0 f6864f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        z.b f6865g;

        f(c0 c0Var, WindowInsets windowInsets) {
            super(c0Var);
            this.f6863e = null;
            this.f6861c = windowInsets;
        }

        private z.b q(View view) {
            if (Build.VERSION.SDK_INT >= 30) {
                throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
            }
            if (!f6855h) {
                r();
            }
            Method method = f6856i;
            if (method != null && f6858k != null && f6859l != null) {
                try {
                    Object objInvoke = method.invoke(view, new Object[0]);
                    if (objInvoke == null) {
                        Log.w("WindowInsetsCompat", "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", new NullPointerException());
                        return null;
                    }
                    Rect rect = (Rect) f6859l.get(f6860m.get(objInvoke));
                    if (rect != null) {
                        return z.b.c(rect);
                    }
                    return null;
                } catch (ReflectiveOperationException e7) {
                    Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e7.getMessage(), e7);
                }
            }
            return null;
        }

        @SuppressLint({"PrivateApi"})
        private static void r() {
            try {
                f6856i = View.class.getDeclaredMethod("getViewRootImpl", new Class[0]);
                f6857j = Class.forName("android.view.ViewRootImpl");
                Class<?> cls = Class.forName("android.view.View$AttachInfo");
                f6858k = cls;
                f6859l = cls.getDeclaredField("mVisibleInsets");
                f6860m = f6857j.getDeclaredField("mAttachInfo");
                f6859l.setAccessible(true);
                f6860m.setAccessible(true);
            } catch (ReflectiveOperationException e7) {
                Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e7.getMessage(), e7);
            }
            f6855h = true;
        }

        @Override // i0.c0.k
        void d(View view) {
            z.b bVarQ = q(view);
            if (bVarQ == null) {
                bVarQ = z.b.f10021e;
            }
            n(bVarQ);
        }

        @Override // i0.c0.k
        void e(c0 c0Var) {
            c0Var.p(this.f6864f);
            c0Var.o(this.f6865g);
        }

        @Override // i0.c0.k
        public boolean equals(Object obj) {
            if (super.equals(obj)) {
                return Objects.equals(this.f6865g, ((f) obj).f6865g);
            }
            return false;
        }

        @Override // i0.c0.k
        final z.b i() {
            if (this.f6863e == null) {
                this.f6863e = z.b.b(this.f6861c.getSystemWindowInsetLeft(), this.f6861c.getSystemWindowInsetTop(), this.f6861c.getSystemWindowInsetRight(), this.f6861c.getSystemWindowInsetBottom());
            }
            return this.f6863e;
        }

        @Override // i0.c0.k
        c0 j(int i7, int i8, int i9, int i10) {
            a aVar = new a(c0.s(this.f6861c));
            aVar.c(c0.k(i(), i7, i8, i9, i10));
            aVar.b(c0.k(h(), i7, i8, i9, i10));
            return aVar.a();
        }

        @Override // i0.c0.k
        boolean l() {
            return this.f6861c.isRound();
        }

        @Override // i0.c0.k
        public void m(z.b[] bVarArr) {
            this.f6862d = bVarArr;
        }

        @Override // i0.c0.k
        void n(z.b bVar) {
            this.f6865g = bVar;
        }

        @Override // i0.c0.k
        void o(c0 c0Var) {
            this.f6864f = c0Var;
        }

        f(c0 c0Var, f fVar) {
            this(c0Var, new WindowInsets(fVar.f6861c));
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class i extends h {

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        private z.b f6867o;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        private z.b f6868p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        private z.b f6869q;

        i(c0 c0Var, WindowInsets windowInsets) {
            super(c0Var, windowInsets);
            this.f6867o = null;
            this.f6868p = null;
            this.f6869q = null;
        }

        @Override // i0.c0.k
        z.b g() {
            if (this.f6868p == null) {
                this.f6868p = z.b.d(this.f6861c.getMandatorySystemGestureInsets());
            }
            return this.f6868p;
        }

        @Override // i0.c0.f, i0.c0.k
        c0 j(int i7, int i8, int i9, int i10) {
            return c0.s(this.f6861c.inset(i7, i8, i9, i10));
        }

        @Override // i0.c0.g, i0.c0.k
        public void p(z.b bVar) {
        }

        i(c0 c0Var, i iVar) {
            super(c0Var, iVar);
            this.f6867o = null;
            this.f6868p = null;
            this.f6869q = null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final e f6845a;

        public a() {
            int i7 = Build.VERSION.SDK_INT;
            if (i7 >= 30) {
                this.f6845a = new d();
            } else if (i7 >= 29) {
                this.f6845a = new c();
            } else {
                this.f6845a = new b();
            }
        }

        public c0 a() {
            return this.f6845a.b();
        }

        @Deprecated
        public a b(z.b bVar) {
            this.f6845a.d(bVar);
            return this;
        }

        @Deprecated
        public a c(z.b bVar) {
            this.f6845a.f(bVar);
            return this;
        }

        public a(c0 c0Var) {
            int i7 = Build.VERSION.SDK_INT;
            if (i7 >= 30) {
                this.f6845a = new d(c0Var);
            } else if (i7 >= 29) {
                this.f6845a = new c(c0Var);
            } else {
                this.f6845a = new b(c0Var);
            }
        }
    }

    public c0(c0 c0Var) {
        if (c0Var != null) {
            k kVar = c0Var.f6844a;
            int i7 = Build.VERSION.SDK_INT;
            if (i7 >= 30 && (kVar instanceof j)) {
                this.f6844a = new j(this, (j) kVar);
            } else if (i7 >= 29 && (kVar instanceof i)) {
                this.f6844a = new i(this, (i) kVar);
            } else if (kVar instanceof h) {
                this.f6844a = new h(this, (h) kVar);
            } else if (kVar instanceof g) {
                this.f6844a = new g(this, (g) kVar);
            } else if (kVar instanceof f) {
                this.f6844a = new f(this, (f) kVar);
            } else {
                this.f6844a = new k(this);
            }
            kVar.e(this);
            return;
        }
        this.f6844a = new k(this);
    }
}
