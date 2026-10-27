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
    public static final b0 f1714a = new a().a().a().b().c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final i f1715b;

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b0 f1721a;

        d() {
            this(new b0((b0) null));
        }

        b0 a() {
            return this.f1721a;
        }

        void b(b.g.d.b bVar) {
        }

        void c(b.g.d.b bVar) {
        }

        d(b0 b0Var) {
            this.f1721a = b0Var;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class g extends f {
        g(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
        }

        @Override // b.g.k.b0.i
        b0 a() {
            return b0.p(this.f1722b.consumeDisplayCutout());
        }

        @Override // b.g.k.b0.i
        b.g.k.c d() {
            return b.g.k.c.a(this.f1722b.getDisplayCutout());
        }

        @Override // b.g.k.b0.i
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof g) {
                return Objects.equals(this.f1722b, ((g) obj).f1722b);
            }
            return false;
        }

        @Override // b.g.k.b0.i
        public int hashCode() {
            return this.f1722b.hashCode();
        }

        g(b0 b0Var, g gVar) {
            super(b0Var, gVar);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final b0 f1725a;

        i(b0 b0Var) {
            this.f1725a = b0Var;
        }

        b0 a() {
            return this.f1725a;
        }

        b0 b() {
            return this.f1725a;
        }

        b0 c() {
            return this.f1725a;
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
            return b.g.d.b.f1606a;
        }

        b.g.d.b g() {
            return b.g.d.b.f1606a;
        }

        b0 h(int i, int i2, int i3, int i4) {
            return b0.f1714a;
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
            this.f1715b = new h(this, windowInsets);
            return;
        }
        if (i2 >= 28) {
            this.f1715b = new g(this, windowInsets);
            return;
        }
        if (i2 >= 21) {
            this.f1715b = new f(this, windowInsets);
        } else if (i2 >= 20) {
            this.f1715b = new e(this, windowInsets);
        } else {
            this.f1715b = new i(this);
        }
    }

    static b.g.d.b l(b.g.d.b bVar, int i2, int i3, int i4, int i5) {
        int iMax = Math.max(0, bVar.f1607b - i2);
        int iMax2 = Math.max(0, bVar.f1608c - i3);
        int iMax3 = Math.max(0, bVar.f1609d - i4);
        int iMax4 = Math.max(0, bVar.e - i5);
        return (iMax == i2 && iMax2 == i3 && iMax3 == i4 && iMax4 == i5) ? bVar : b.g.d.b.a(iMax, iMax2, iMax3, iMax4);
    }

    public static b0 p(WindowInsets windowInsets) {
        return new b0((WindowInsets) b.g.j.h.c(windowInsets));
    }

    public b0 a() {
        return this.f1715b.a();
    }

    public b0 b() {
        return this.f1715b.b();
    }

    public b0 c() {
        return this.f1715b.c();
    }

    public b.g.d.b d() {
        return this.f1715b.e();
    }

    public int e() {
        return i().e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b0) {
            return b.g.j.c.a(this.f1715b, ((b0) obj).f1715b);
        }
        return false;
    }

    public int f() {
        return i().f1607b;
    }

    public int g() {
        return i().f1609d;
    }

    public int h() {
        return i().f1608c;
    }

    public int hashCode() {
        i iVar = this.f1715b;
        if (iVar == null) {
            return 0;
        }
        return iVar.hashCode();
    }

    public b.g.d.b i() {
        return this.f1715b.g();
    }

    public boolean j() {
        return !i().equals(b.g.d.b.f1606a);
    }

    public b0 k(int i2, int i3, int i4, int i5) {
        return this.f1715b.h(i2, i3, i4, i5);
    }

    public boolean m() {
        return this.f1715b.i();
    }

    @Deprecated
    public b0 n(int i2, int i3, int i4, int i5) {
        return new a(this).c(b.g.d.b.a(i2, i3, i4, i5)).a();
    }

    public WindowInsets o() {
        i iVar = this.f1715b;
        if (iVar instanceof e) {
            return ((e) iVar).f1722b;
        }
        return null;
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class b extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static Field f1717b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static boolean f1718c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static Constructor<WindowInsets> f1719d = null;
        private static boolean e = false;
        private WindowInsets f;

        b() {
            this.f = d();
        }

        private static WindowInsets d() {
            if (!f1718c) {
                try {
                    f1717b = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException e2) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e2);
                }
                f1718c = true;
            }
            Field field = f1717b;
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
                    f1719d = WindowInsets.class.getConstructor(Rect.class);
                } catch (ReflectiveOperationException e4) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e4);
                }
                e = true;
            }
            Constructor<WindowInsets> constructor = f1719d;
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
                this.f = windowInsets.replaceSystemWindowInsets(bVar.f1607b, bVar.f1608c, bVar.f1609d, bVar.e);
            }
        }

        b(b0 b0Var) {
            this.f = b0Var.o();
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class c extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final WindowInsets.Builder f1720b;

        c() {
            this.f1720b = new WindowInsets.Builder();
        }

        @Override // b.g.k.b0.d
        b0 a() {
            return b0.p(this.f1720b.build());
        }

        @Override // b.g.k.b0.d
        void b(b.g.d.b bVar) {
            this.f1720b.setStableInsets(bVar.c());
        }

        @Override // b.g.k.b0.d
        void c(b.g.d.b bVar) {
            this.f1720b.setSystemWindowInsets(bVar.c());
        }

        c(b0 b0Var) {
            WindowInsets.Builder builder;
            WindowInsets windowInsetsO = b0Var.o();
            if (windowInsetsO != null) {
                builder = new WindowInsets.Builder(windowInsetsO);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.f1720b = builder;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class f extends e {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private b.g.d.b f1724d;

        f(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
            this.f1724d = null;
        }

        @Override // b.g.k.b0.i
        b0 b() {
            return b0.p(this.f1722b.consumeStableInsets());
        }

        @Override // b.g.k.b0.i
        b0 c() {
            return b0.p(this.f1722b.consumeSystemWindowInsets());
        }

        @Override // b.g.k.b0.i
        final b.g.d.b f() {
            if (this.f1724d == null) {
                this.f1724d = b.g.d.b.a(this.f1722b.getStableInsetLeft(), this.f1722b.getStableInsetTop(), this.f1722b.getStableInsetRight(), this.f1722b.getStableInsetBottom());
            }
            return this.f1724d;
        }

        @Override // b.g.k.b0.i
        boolean i() {
            return this.f1722b.isConsumed();
        }

        f(b0 b0Var, f fVar) {
            super(b0Var, fVar);
            this.f1724d = null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class e extends i {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final WindowInsets f1722b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private b.g.d.b f1723c;

        e(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var);
            this.f1723c = null;
            this.f1722b = windowInsets;
        }

        @Override // b.g.k.b0.i
        final b.g.d.b g() {
            if (this.f1723c == null) {
                this.f1723c = b.g.d.b.a(this.f1722b.getSystemWindowInsetLeft(), this.f1722b.getSystemWindowInsetTop(), this.f1722b.getSystemWindowInsetRight(), this.f1722b.getSystemWindowInsetBottom());
            }
            return this.f1723c;
        }

        @Override // b.g.k.b0.i
        b0 h(int i, int i2, int i3, int i4) {
            a aVar = new a(b0.p(this.f1722b));
            aVar.c(b0.l(g(), i, i2, i3, i4));
            aVar.b(b0.l(f(), i, i2, i3, i4));
            return aVar.a();
        }

        @Override // b.g.k.b0.i
        boolean j() {
            return this.f1722b.isRound();
        }

        e(b0 b0Var, e eVar) {
            this(b0Var, new WindowInsets(eVar.f1722b));
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class h extends g {
        private b.g.d.b e;
        private b.g.d.b f;
        private b.g.d.b g;

        h(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
            this.e = null;
            this.f = null;
            this.g = null;
        }

        @Override // b.g.k.b0.i
        b.g.d.b e() {
            if (this.f == null) {
                this.f = b.g.d.b.b(this.f1722b.getMandatorySystemGestureInsets());
            }
            return this.f;
        }

        @Override // b.g.k.b0.e, b.g.k.b0.i
        b0 h(int i, int i2, int i3, int i4) {
            return b0.p(this.f1722b.inset(i, i2, i3, i4));
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
        private final d f1716a;

        public a() {
            int i = Build.VERSION.SDK_INT;
            if (i >= 29) {
                this.f1716a = new c();
            } else if (i >= 20) {
                this.f1716a = new b();
            } else {
                this.f1716a = new d();
            }
        }

        public b0 a() {
            return this.f1716a.a();
        }

        public a b(b.g.d.b bVar) {
            this.f1716a.b(bVar);
            return this;
        }

        public a c(b.g.d.b bVar) {
            this.f1716a.c(bVar);
            return this;
        }

        public a(b0 b0Var) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 29) {
                this.f1716a = new c(b0Var);
            } else if (i >= 20) {
                this.f1716a = new b(b0Var);
            } else {
                this.f1716a = new d(b0Var);
            }
        }
    }

    public b0(b0 b0Var) {
        if (b0Var != null) {
            i iVar = b0Var.f1715b;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 29 && (iVar instanceof h)) {
                this.f1715b = new h(this, (h) iVar);
                return;
            }
            if (i2 >= 28 && (iVar instanceof g)) {
                this.f1715b = new g(this, (g) iVar);
                return;
            }
            if (i2 >= 21 && (iVar instanceof f)) {
                this.f1715b = new f(this, (f) iVar);
                return;
            } else if (i2 >= 20 && (iVar instanceof e)) {
                this.f1715b = new e(this, (e) iVar);
                return;
            } else {
                this.f1715b = new i(this);
                return;
            }
        }
        this.f1715b = new i(this);
    }
}
