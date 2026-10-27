package b.g.k;

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
    public static final b0 f2304a = new a().a().a().b().c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final i f2305b;

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b0 f2312a;

        d() {
            this(new b0((b0) null));
        }

        b0 a() {
            return this.f2312a;
        }

        void b(b.g.d.b bVar) {
        }

        void c(b.g.d.b bVar) {
        }

        d(b0 b0Var) {
            this.f2312a = b0Var;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class g extends f {
        g(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
        }

        @Override // b.g.k.b0.i
        b0 a() {
            return b0.p(this.f2313b.consumeDisplayCutout());
        }

        @Override // b.g.k.b0.i
        b.g.k.c d() {
            return b.g.k.c.a(this.f2313b.getDisplayCutout());
        }

        @Override // b.g.k.b0.i
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof g) {
                return Objects.equals(this.f2313b, ((g) obj).f2313b);
            }
            return false;
        }

        @Override // b.g.k.b0.i
        public int hashCode() {
            return this.f2313b.hashCode();
        }

        g(b0 b0Var, g gVar) {
            super(b0Var, gVar);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final b0 f2317a;

        i(b0 b0Var) {
            this.f2317a = b0Var;
        }

        b0 a() {
            return this.f2317a;
        }

        b0 b() {
            return this.f2317a;
        }

        b0 c() {
            return this.f2317a;
        }

        b.g.k.c d() {
            return null;
        }

        b.g.d.b e() {
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
            return j() == iVar.j() && i() == iVar.i() && b.g.j.c.a(g(), iVar.g()) && b.g.j.c.a(f(), iVar.f()) && b.g.j.c.a(d(), iVar.d());
        }

        b.g.d.b f() {
            return b.g.d.b.f2181a;
        }

        b.g.d.b g() {
            return b.g.d.b.f2181a;
        }

        b0 h(int i, int i2, int i3, int i4) {
            return b0.f2304a;
        }

        public int hashCode() {
            return b.g.j.c.b(Boolean.valueOf(j()), Boolean.valueOf(i()), g(), f(), d());
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
            this.f2305b = new h(this, windowInsets);
            return;
        }
        if (i2 >= 28) {
            this.f2305b = new g(this, windowInsets);
            return;
        }
        if (i2 >= 21) {
            this.f2305b = new f(this, windowInsets);
        } else if (i2 >= 20) {
            this.f2305b = new e(this, windowInsets);
        } else {
            this.f2305b = new i(this);
        }
    }

    static b.g.d.b l(b.g.d.b bVar, int i2, int i3, int i4, int i5) {
        int iMax = Math.max(0, bVar.f2182b - i2);
        int iMax2 = Math.max(0, bVar.f2183c - i3);
        int iMax3 = Math.max(0, bVar.f2184d - i4);
        int iMax4 = Math.max(0, bVar.f2185e - i5);
        return (iMax == i2 && iMax2 == i3 && iMax3 == i4 && iMax4 == i5) ? bVar : b.g.d.b.a(iMax, iMax2, iMax3, iMax4);
    }

    public static b0 p(WindowInsets windowInsets) {
        return new b0((WindowInsets) b.g.j.h.c(windowInsets));
    }

    public b0 a() {
        return this.f2305b.a();
    }

    public b0 b() {
        return this.f2305b.b();
    }

    public b0 c() {
        return this.f2305b.c();
    }

    public b.g.d.b d() {
        return this.f2305b.e();
    }

    public int e() {
        return i().f2185e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b0) {
            return b.g.j.c.a(this.f2305b, ((b0) obj).f2305b);
        }
        return false;
    }

    public int f() {
        return i().f2182b;
    }

    public int g() {
        return i().f2184d;
    }

    public int h() {
        return i().f2183c;
    }

    public int hashCode() {
        i iVar = this.f2305b;
        if (iVar == null) {
            return 0;
        }
        return iVar.hashCode();
    }

    public b.g.d.b i() {
        return this.f2305b.g();
    }

    public boolean j() {
        return !i().equals(b.g.d.b.f2181a);
    }

    public b0 k(int i2, int i3, int i4, int i5) {
        return this.f2305b.h(i2, i3, i4, i5);
    }

    public boolean m() {
        return this.f2305b.i();
    }

    @Deprecated
    public b0 n(int i2, int i3, int i4, int i5) {
        return new a(this).c(b.g.d.b.a(i2, i3, i4, i5)).a();
    }

    public WindowInsets o() {
        i iVar = this.f2305b;
        if (iVar instanceof e) {
            return ((e) iVar).f2313b;
        }
        return null;
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class b extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static Field f2307b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static boolean f2308c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static Constructor<WindowInsets> f2309d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private static boolean f2310e = false;
        private WindowInsets f;

        b() {
            this.f = d();
        }

        private static WindowInsets d() {
            if (!f2308c) {
                try {
                    f2307b = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException e2) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e2);
                }
                f2308c = true;
            }
            Field field = f2307b;
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
            if (!f2310e) {
                try {
                    f2309d = WindowInsets.class.getConstructor(Rect.class);
                } catch (ReflectiveOperationException e4) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e4);
                }
                f2310e = true;
            }
            Constructor<WindowInsets> constructor = f2309d;
            if (constructor != null) {
                try {
                    return constructor.newInstance(new Rect());
                } catch (ReflectiveOperationException e5) {
                    Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e5);
                }
            }
            return null;
        }

        @Override // b.g.k.b0.d
        b0 a() {
            return b0.p(this.f);
        }

        @Override // b.g.k.b0.d
        void c(b.g.d.b bVar) {
            WindowInsets windowInsets = this.f;
            if (windowInsets != null) {
                this.f = windowInsets.replaceSystemWindowInsets(bVar.f2182b, bVar.f2183c, bVar.f2184d, bVar.f2185e);
            }
        }

        b(b0 b0Var) {
            this.f = b0Var.o();
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class c extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final WindowInsets.Builder f2311b;

        c() {
            this.f2311b = new WindowInsets.Builder();
        }

        @Override // b.g.k.b0.d
        b0 a() {
            return b0.p(this.f2311b.build());
        }

        @Override // b.g.k.b0.d
        void b(b.g.d.b bVar) {
            this.f2311b.setStableInsets(bVar.c());
        }

        @Override // b.g.k.b0.d
        void c(b.g.d.b bVar) {
            this.f2311b.setSystemWindowInsets(bVar.c());
        }

        c(b0 b0Var) {
            WindowInsets.Builder builder;
            WindowInsets windowInsetsO = b0Var.o();
            if (windowInsetsO != null) {
                builder = new WindowInsets.Builder(windowInsetsO);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.f2311b = builder;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class f extends e {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private b.g.d.b f2315d;

        f(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
            this.f2315d = null;
        }

        @Override // b.g.k.b0.i
        b0 b() {
            return b0.p(this.f2313b.consumeStableInsets());
        }

        @Override // b.g.k.b0.i
        b0 c() {
            return b0.p(this.f2313b.consumeSystemWindowInsets());
        }

        @Override // b.g.k.b0.i
        final b.g.d.b f() {
            if (this.f2315d == null) {
                this.f2315d = b.g.d.b.a(this.f2313b.getStableInsetLeft(), this.f2313b.getStableInsetTop(), this.f2313b.getStableInsetRight(), this.f2313b.getStableInsetBottom());
            }
            return this.f2315d;
        }

        @Override // b.g.k.b0.i
        boolean i() {
            return this.f2313b.isConsumed();
        }

        f(b0 b0Var, f fVar) {
            super(b0Var, fVar);
            this.f2315d = null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class e extends i {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final WindowInsets f2313b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private b.g.d.b f2314c;

        e(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var);
            this.f2314c = null;
            this.f2313b = windowInsets;
        }

        @Override // b.g.k.b0.i
        final b.g.d.b g() {
            if (this.f2314c == null) {
                this.f2314c = b.g.d.b.a(this.f2313b.getSystemWindowInsetLeft(), this.f2313b.getSystemWindowInsetTop(), this.f2313b.getSystemWindowInsetRight(), this.f2313b.getSystemWindowInsetBottom());
            }
            return this.f2314c;
        }

        @Override // b.g.k.b0.i
        b0 h(int i, int i2, int i3, int i4) {
            a aVar = new a(b0.p(this.f2313b));
            aVar.c(b0.l(g(), i, i2, i3, i4));
            aVar.b(b0.l(f(), i, i2, i3, i4));
            return aVar.a();
        }

        @Override // b.g.k.b0.i
        boolean j() {
            return this.f2313b.isRound();
        }

        e(b0 b0Var, e eVar) {
            this(b0Var, new WindowInsets(eVar.f2313b));
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class h extends g {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private b.g.d.b f2316e;
        private b.g.d.b f;
        private b.g.d.b g;

        h(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
            this.f2316e = null;
            this.f = null;
            this.g = null;
        }

        @Override // b.g.k.b0.i
        b.g.d.b e() {
            if (this.f == null) {
                this.f = b.g.d.b.b(this.f2313b.getMandatorySystemGestureInsets());
            }
            return this.f;
        }

        @Override // b.g.k.b0.e, b.g.k.b0.i
        b0 h(int i, int i2, int i3, int i4) {
            return b0.p(this.f2313b.inset(i, i2, i3, i4));
        }

        h(b0 b0Var, h hVar) {
            super(b0Var, hVar);
            this.f2316e = null;
            this.f = null;
            this.g = null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final d f2306a;

        public a() {
            int i = Build.VERSION.SDK_INT;
            if (i >= 29) {
                this.f2306a = new c();
            } else if (i >= 20) {
                this.f2306a = new b();
            } else {
                this.f2306a = new d();
            }
        }

        public b0 a() {
            return this.f2306a.a();
        }

        public a b(b.g.d.b bVar) {
            this.f2306a.b(bVar);
            return this;
        }

        public a c(b.g.d.b bVar) {
            this.f2306a.c(bVar);
            return this;
        }

        public a(b0 b0Var) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 29) {
                this.f2306a = new c(b0Var);
            } else if (i >= 20) {
                this.f2306a = new b(b0Var);
            } else {
                this.f2306a = new d(b0Var);
            }
        }
    }

    public b0(b0 b0Var) {
        if (b0Var != null) {
            i iVar = b0Var.f2305b;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 29 && (iVar instanceof h)) {
                this.f2305b = new h(this, (h) iVar);
                return;
            }
            if (i2 >= 28 && (iVar instanceof g)) {
                this.f2305b = new g(this, (g) iVar);
                return;
            }
            if (i2 >= 21 && (iVar instanceof f)) {
                this.f2305b = new f(this, (f) iVar);
                return;
            } else if (i2 >= 20 && (iVar instanceof e)) {
                this.f2305b = new e(this, (e) iVar);
                return;
            } else {
                this.f2305b = new i(this);
                return;
            }
        }
        this.f2305b = new i(this);
    }
}
