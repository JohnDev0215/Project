package c.g.k;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.WindowInsets;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Objects;

/* JADX INFO: compiled from: WindowInsetsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b0 f2281a = new a().a().a().b().c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final i f2282b;

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b0 f2288a;

        d() {
            this(new b0((b0) null));
        }

        b0 a() {
            return this.f2288a;
        }

        void b(c.g.d.b bVar) {
        }

        void c(c.g.d.b bVar) {
        }

        d(b0 b0Var) {
            this.f2288a = b0Var;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class g extends f {
        g(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
        }

        @Override // c.g.k.b0.i
        b0 a() {
            return b0.p(this.f2289b.consumeDisplayCutout());
        }

        @Override // c.g.k.b0.i
        c.g.k.c d() {
            return c.g.k.c.a(this.f2289b.getDisplayCutout());
        }

        @Override // c.g.k.b0.i
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof g) {
                return Objects.equals(this.f2289b, ((g) obj).f2289b);
            }
            return false;
        }

        @Override // c.g.k.b0.i
        public int hashCode() {
            return this.f2289b.hashCode();
        }

        g(b0 b0Var, g gVar) {
            super(b0Var, gVar);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final b0 f2292a;

        i(b0 b0Var) {
            this.f2292a = b0Var;
        }

        b0 a() {
            return this.f2292a;
        }

        b0 b() {
            return this.f2292a;
        }

        b0 c() {
            return this.f2292a;
        }

        c.g.k.c d() {
            return null;
        }

        c.g.d.b e() {
            return g();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof i)) {
                return false;
            }
            i iVar = (i) obj;
            return j() == iVar.j() && i() == iVar.i() && c.g.j.c.a(g(), iVar.g()) && c.g.j.c.a(f(), iVar.f()) && c.g.j.c.a(d(), iVar.d());
        }

        c.g.d.b f() {
            return c.g.d.b.f2173a;
        }

        c.g.d.b g() {
            return c.g.d.b.f2173a;
        }

        b0 h(int i, int i2, int i3, int i4) {
            return b0.f2281a;
        }

        public int hashCode() {
            return c.g.j.c.b(Boolean.valueOf(j()), Boolean.valueOf(i()), g(), f(), d());
        }

        boolean i() {
            return false;
        }

        boolean j() {
            return false;
        }
    }

    private b0(WindowInsets windowInsets) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            this.f2282b = new h(this, windowInsets);
            return;
        }
        if (i2 >= 28) {
            this.f2282b = new g(this, windowInsets);
            return;
        }
        if (i2 >= 21) {
            this.f2282b = new f(this, windowInsets);
        } else if (i2 >= 20) {
            this.f2282b = new e(this, windowInsets);
        } else {
            this.f2282b = new i(this);
        }
    }

    static c.g.d.b l(c.g.d.b bVar, int i2, int i3, int i4, int i5) {
        int iMax = Math.max(0, bVar.f2174b - i2);
        int iMax2 = Math.max(0, bVar.f2175c - i3);
        int iMax3 = Math.max(0, bVar.f2176d - i4);
        int iMax4 = Math.max(0, bVar.e - i5);
        return (iMax == i2 && iMax2 == i3 && iMax3 == i4 && iMax4 == i5) ? bVar : c.g.d.b.a(iMax, iMax2, iMax3, iMax4);
    }

    public static b0 p(WindowInsets windowInsets) {
        return new b0((WindowInsets) c.g.j.h.c(windowInsets));
    }

    public b0 a() {
        return this.f2282b.a();
    }

    public b0 b() {
        return this.f2282b.b();
    }

    public b0 c() {
        return this.f2282b.c();
    }

    public c.g.d.b d() {
        return this.f2282b.e();
    }

    public int e() {
        return i().e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b0) {
            return c.g.j.c.a(this.f2282b, ((b0) obj).f2282b);
        }
        return false;
    }

    public int f() {
        return i().f2174b;
    }

    public int g() {
        return i().f2176d;
    }

    public int h() {
        return i().f2175c;
    }

    public int hashCode() {
        i iVar = this.f2282b;
        if (iVar == null) {
            return 0;
        }
        return iVar.hashCode();
    }

    public c.g.d.b i() {
        return this.f2282b.g();
    }

    public boolean j() {
        return !i().equals(c.g.d.b.f2173a);
    }

    public b0 k(int i2, int i3, int i4, int i5) {
        return this.f2282b.h(i2, i3, i4, i5);
    }

    public boolean m() {
        return this.f2282b.i();
    }

    @Deprecated
    public b0 n(int i2, int i3, int i4, int i5) {
        return new a(this).c(c.g.d.b.a(i2, i3, i4, i5)).a();
    }

    public WindowInsets o() {
        i iVar = this.f2282b;
        if (iVar instanceof e) {
            return ((e) iVar).f2289b;
        }
        return null;
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class b extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static Field f2284b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static boolean f2285c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static Constructor<WindowInsets> f2286d = null;
        private static boolean e = false;
        private WindowInsets f;

        b() {
            this.f = d();
        }

        private static WindowInsets d() {
            if (!f2285c) {
                try {
                    f2284b = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException e2) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e2);
                }
                f2285c = true;
            }
            Field field = f2284b;
            if (field != null) {
                try {
                    WindowInsets windowInsets = (WindowInsets) field.get(null);
                    if (windowInsets != null) {
                        return new WindowInsets(windowInsets);
                    }
                } catch (ReflectiveOperationException e3) {
                    Log.i("WindowInsetsCompat", "Could not get value from WindowInsets.CONSUMED field", e3);
                }
            }
            if (!e) {
                try {
                    f2286d = WindowInsets.class.getConstructor(Rect.class);
                } catch (ReflectiveOperationException e4) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e4);
                }
                e = true;
            }
            Constructor<WindowInsets> constructor = f2286d;
            if (constructor != null) {
                try {
                    return constructor.newInstance(new Rect());
                } catch (ReflectiveOperationException e5) {
                    Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e5);
                }
            }
            return null;
        }

        @Override // c.g.k.b0.d
        b0 a() {
            return b0.p(this.f);
        }

        @Override // c.g.k.b0.d
        void c(c.g.d.b bVar) {
            WindowInsets windowInsets = this.f;
            if (windowInsets != null) {
                this.f = windowInsets.replaceSystemWindowInsets(bVar.f2174b, bVar.f2175c, bVar.f2176d, bVar.e);
            }
        }

        b(b0 b0Var) {
            this.f = b0Var.o();
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class c extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final WindowInsets.Builder f2287b;

        c() {
            this.f2287b = new WindowInsets.Builder();
        }

        @Override // c.g.k.b0.d
        b0 a() {
            return b0.p(this.f2287b.build());
        }

        @Override // c.g.k.b0.d
        void b(c.g.d.b bVar) {
            this.f2287b.setStableInsets(bVar.c());
        }

        @Override // c.g.k.b0.d
        void c(c.g.d.b bVar) {
            this.f2287b.setSystemWindowInsets(bVar.c());
        }

        c(b0 b0Var) {
            WindowInsets.Builder builder;
            WindowInsets windowInsetsO = b0Var.o();
            if (windowInsetsO != null) {
                builder = new WindowInsets.Builder(windowInsetsO);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.f2287b = builder;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class f extends e {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private c.g.d.b f2291d;

        f(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
            this.f2291d = null;
        }

        @Override // c.g.k.b0.i
        b0 b() {
            return b0.p(this.f2289b.consumeStableInsets());
        }

        @Override // c.g.k.b0.i
        b0 c() {
            return b0.p(this.f2289b.consumeSystemWindowInsets());
        }

        @Override // c.g.k.b0.i
        final c.g.d.b f() {
            if (this.f2291d == null) {
                this.f2291d = c.g.d.b.a(this.f2289b.getStableInsetLeft(), this.f2289b.getStableInsetTop(), this.f2289b.getStableInsetRight(), this.f2289b.getStableInsetBottom());
            }
            return this.f2291d;
        }

        @Override // c.g.k.b0.i
        boolean i() {
            return this.f2289b.isConsumed();
        }

        f(b0 b0Var, f fVar) {
            super(b0Var, fVar);
            this.f2291d = null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class e extends i {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final WindowInsets f2289b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private c.g.d.b f2290c;

        e(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var);
            this.f2290c = null;
            this.f2289b = windowInsets;
        }

        @Override // c.g.k.b0.i
        final c.g.d.b g() {
            if (this.f2290c == null) {
                this.f2290c = c.g.d.b.a(this.f2289b.getSystemWindowInsetLeft(), this.f2289b.getSystemWindowInsetTop(), this.f2289b.getSystemWindowInsetRight(), this.f2289b.getSystemWindowInsetBottom());
            }
            return this.f2290c;
        }

        @Override // c.g.k.b0.i
        b0 h(int i, int i2, int i3, int i4) {
            a aVar = new a(b0.p(this.f2289b));
            aVar.c(b0.l(g(), i, i2, i3, i4));
            aVar.b(b0.l(f(), i, i2, i3, i4));
            return aVar.a();
        }

        @Override // c.g.k.b0.i
        boolean j() {
            return this.f2289b.isRound();
        }

        e(b0 b0Var, e eVar) {
            this(b0Var, new WindowInsets(eVar.f2289b));
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class h extends g {
        private c.g.d.b e;
        private c.g.d.b f;
        private c.g.d.b g;

        h(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
            this.e = null;
            this.f = null;
            this.g = null;
        }

        @Override // c.g.k.b0.i
        c.g.d.b e() {
            if (this.f == null) {
                this.f = c.g.d.b.b(this.f2289b.getMandatorySystemGestureInsets());
            }
            return this.f;
        }

        @Override // c.g.k.b0.e, c.g.k.b0.i
        b0 h(int i, int i2, int i3, int i4) {
            return b0.p(this.f2289b.inset(i, i2, i3, i4));
        }

        h(b0 b0Var, h hVar) {
            super(b0Var, hVar);
            this.e = null;
            this.f = null;
            this.g = null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final d f2283a;

        public a() {
            int i = Build.VERSION.SDK_INT;
            if (i >= 29) {
                this.f2283a = new c();
            } else if (i >= 20) {
                this.f2283a = new b();
            } else {
                this.f2283a = new d();
            }
        }

        public b0 a() {
            return this.f2283a.a();
        }

        public a b(c.g.d.b bVar) {
            this.f2283a.b(bVar);
            return this;
        }

        public a c(c.g.d.b bVar) {
            this.f2283a.c(bVar);
            return this;
        }

        public a(b0 b0Var) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 29) {
                this.f2283a = new c(b0Var);
            } else if (i >= 20) {
                this.f2283a = new b(b0Var);
            } else {
                this.f2283a = new d(b0Var);
            }
        }
    }

    public b0(b0 b0Var) {
        if (b0Var != null) {
            i iVar = b0Var.f2282b;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 29 && (iVar instanceof h)) {
                this.f2282b = new h(this, (h) iVar);
                return;
            }
            if (i2 >= 28 && (iVar instanceof g)) {
                this.f2282b = new g(this, (g) iVar);
                return;
            }
            if (i2 >= 21 && (iVar instanceof f)) {
                this.f2282b = new f(this, (f) iVar);
                return;
            } else if (i2 >= 20 && (iVar instanceof e)) {
                this.f2282b = new e(this, (e) iVar);
                return;
            } else {
                this.f2282b = new i(this);
                return;
            }
        }
        this.f2282b = new i(this);
    }
}
