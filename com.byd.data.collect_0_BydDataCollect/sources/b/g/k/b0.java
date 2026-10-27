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
    public static final b0 f2671a = new a().a().a().b().c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final i f2672b;

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b0 f2680a;

        d() {
            this(new b0((b0) null));
        }

        b0 a() {
            return this.f2680a;
        }

        void b(b.g.d.b bVar) {
        }

        void c(b.g.d.b bVar) {
        }

        d(b0 b0Var) {
            this.f2680a = b0Var;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class g extends f {
        g(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
        }

        @Override // b.g.k.b0.i
        b0 a() {
            return b0.q(this.f2681b.consumeDisplayCutout());
        }

        @Override // b.g.k.b0.i
        b.g.k.c d() {
            return b.g.k.c.a(this.f2681b.getDisplayCutout());
        }

        @Override // b.g.k.b0.i
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof g) {
                return Objects.equals(this.f2681b, ((g) obj).f2681b);
            }
            return false;
        }

        @Override // b.g.k.b0.i
        public int hashCode() {
            return this.f2681b.hashCode();
        }

        g(b0 b0Var, g gVar) {
            super(b0Var, gVar);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final b0 f2687a;

        i(b0 b0Var) {
            this.f2687a = b0Var;
        }

        b0 a() {
            return this.f2687a;
        }

        b0 b() {
            return this.f2687a;
        }

        b0 c() {
            return this.f2687a;
        }

        b.g.k.c d() {
            return null;
        }

        b.g.d.b e() {
            return h();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof i)) {
                return false;
            }
            i iVar = (i) obj;
            return k() == iVar.k() && j() == iVar.j() && b.g.j.c.a(h(), iVar.h()) && b.g.j.c.a(f(), iVar.f()) && b.g.j.c.a(d(), iVar.d());
        }

        b.g.d.b f() {
            return b.g.d.b.f2529a;
        }

        b.g.d.b g() {
            return h();
        }

        b.g.d.b h() {
            return b.g.d.b.f2529a;
        }

        public int hashCode() {
            return b.g.j.c.b(Boolean.valueOf(k()), Boolean.valueOf(j()), h(), f(), d());
        }

        b0 i(int i2, int i3, int i4, int i5) {
            return b0.f2671a;
        }

        boolean j() {
            return false;
        }

        boolean k() {
            return false;
        }
    }

    private b0(WindowInsets windowInsets) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            this.f2672b = new h(this, windowInsets);
            return;
        }
        if (i2 >= 28) {
            this.f2672b = new g(this, windowInsets);
            return;
        }
        if (i2 >= 21) {
            this.f2672b = new f(this, windowInsets);
        } else if (i2 >= 20) {
            this.f2672b = new e(this, windowInsets);
        } else {
            this.f2672b = new i(this);
        }
    }

    static b.g.d.b m(b.g.d.b bVar, int i2, int i3, int i4, int i5) {
        int iMax = Math.max(0, bVar.f2530b - i2);
        int iMax2 = Math.max(0, bVar.f2531c - i3);
        int iMax3 = Math.max(0, bVar.f2532d - i4);
        int iMax4 = Math.max(0, bVar.f2533e - i5);
        return (iMax == i2 && iMax2 == i3 && iMax3 == i4 && iMax4 == i5) ? bVar : b.g.d.b.a(iMax, iMax2, iMax3, iMax4);
    }

    public static b0 q(WindowInsets windowInsets) {
        return new b0((WindowInsets) b.g.j.h.c(windowInsets));
    }

    public b0 a() {
        return this.f2672b.a();
    }

    public b0 b() {
        return this.f2672b.b();
    }

    public b0 c() {
        return this.f2672b.c();
    }

    public b.g.d.b d() {
        return this.f2672b.e();
    }

    public b.g.d.b e() {
        return this.f2672b.g();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b0) {
            return b.g.j.c.a(this.f2672b, ((b0) obj).f2672b);
        }
        return false;
    }

    public int f() {
        return j().f2533e;
    }

    public int g() {
        return j().f2530b;
    }

    public int h() {
        return j().f2532d;
    }

    public int hashCode() {
        i iVar = this.f2672b;
        if (iVar == null) {
            return 0;
        }
        return iVar.hashCode();
    }

    public int i() {
        return j().f2531c;
    }

    public b.g.d.b j() {
        return this.f2672b.h();
    }

    public boolean k() {
        return !j().equals(b.g.d.b.f2529a);
    }

    public b0 l(int i2, int i3, int i4, int i5) {
        return this.f2672b.i(i2, i3, i4, i5);
    }

    public boolean n() {
        return this.f2672b.j();
    }

    @Deprecated
    public b0 o(int i2, int i3, int i4, int i5) {
        return new a(this).c(b.g.d.b.a(i2, i3, i4, i5)).a();
    }

    public WindowInsets p() {
        i iVar = this.f2672b;
        if (iVar instanceof e) {
            return ((e) iVar).f2681b;
        }
        return null;
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class b extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static Field f2674b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static boolean f2675c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static Constructor<WindowInsets> f2676d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private static boolean f2677e = false;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private WindowInsets f2678f;

        b() {
            this.f2678f = d();
        }

        private static WindowInsets d() {
            if (!f2675c) {
                try {
                    f2674b = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException e2) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e2);
                }
                f2675c = true;
            }
            Field field = f2674b;
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
            if (!f2677e) {
                try {
                    f2676d = WindowInsets.class.getConstructor(Rect.class);
                } catch (ReflectiveOperationException e4) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e4);
                }
                f2677e = true;
            }
            Constructor<WindowInsets> constructor = f2676d;
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
            return b0.q(this.f2678f);
        }

        @Override // b.g.k.b0.d
        void c(b.g.d.b bVar) {
            WindowInsets windowInsets = this.f2678f;
            if (windowInsets != null) {
                this.f2678f = windowInsets.replaceSystemWindowInsets(bVar.f2530b, bVar.f2531c, bVar.f2532d, bVar.f2533e);
            }
        }

        b(b0 b0Var) {
            this.f2678f = b0Var.p();
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class c extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final WindowInsets.Builder f2679b;

        c() {
            this.f2679b = new WindowInsets.Builder();
        }

        @Override // b.g.k.b0.d
        b0 a() {
            return b0.q(this.f2679b.build());
        }

        @Override // b.g.k.b0.d
        void b(b.g.d.b bVar) {
            this.f2679b.setStableInsets(bVar.c());
        }

        @Override // b.g.k.b0.d
        void c(b.g.d.b bVar) {
            this.f2679b.setSystemWindowInsets(bVar.c());
        }

        c(b0 b0Var) {
            WindowInsets.Builder builder;
            WindowInsets windowInsetsP = b0Var.p();
            if (windowInsetsP != null) {
                builder = new WindowInsets.Builder(windowInsetsP);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.f2679b = builder;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class f extends e {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private b.g.d.b f2683d;

        f(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
            this.f2683d = null;
        }

        @Override // b.g.k.b0.i
        b0 b() {
            return b0.q(this.f2681b.consumeStableInsets());
        }

        @Override // b.g.k.b0.i
        b0 c() {
            return b0.q(this.f2681b.consumeSystemWindowInsets());
        }

        @Override // b.g.k.b0.i
        final b.g.d.b f() {
            if (this.f2683d == null) {
                this.f2683d = b.g.d.b.a(this.f2681b.getStableInsetLeft(), this.f2681b.getStableInsetTop(), this.f2681b.getStableInsetRight(), this.f2681b.getStableInsetBottom());
            }
            return this.f2683d;
        }

        @Override // b.g.k.b0.i
        boolean j() {
            return this.f2681b.isConsumed();
        }

        f(b0 b0Var, f fVar) {
            super(b0Var, fVar);
            this.f2683d = null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class e extends i {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final WindowInsets f2681b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private b.g.d.b f2682c;

        e(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var);
            this.f2682c = null;
            this.f2681b = windowInsets;
        }

        @Override // b.g.k.b0.i
        final b.g.d.b h() {
            if (this.f2682c == null) {
                this.f2682c = b.g.d.b.a(this.f2681b.getSystemWindowInsetLeft(), this.f2681b.getSystemWindowInsetTop(), this.f2681b.getSystemWindowInsetRight(), this.f2681b.getSystemWindowInsetBottom());
            }
            return this.f2682c;
        }

        @Override // b.g.k.b0.i
        b0 i(int i2, int i3, int i4, int i5) {
            a aVar = new a(b0.q(this.f2681b));
            aVar.c(b0.m(h(), i2, i3, i4, i5));
            aVar.b(b0.m(f(), i2, i3, i4, i5));
            return aVar.a();
        }

        @Override // b.g.k.b0.i
        boolean k() {
            return this.f2681b.isRound();
        }

        e(b0 b0Var, e eVar) {
            this(b0Var, new WindowInsets(eVar.f2681b));
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    private static class h extends g {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private b.g.d.b f2684e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private b.g.d.b f2685f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private b.g.d.b f2686g;

        h(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
            this.f2684e = null;
            this.f2685f = null;
            this.f2686g = null;
        }

        @Override // b.g.k.b0.i
        b.g.d.b e() {
            if (this.f2685f == null) {
                this.f2685f = b.g.d.b.b(this.f2681b.getMandatorySystemGestureInsets());
            }
            return this.f2685f;
        }

        @Override // b.g.k.b0.i
        b.g.d.b g() {
            if (this.f2684e == null) {
                this.f2684e = b.g.d.b.b(this.f2681b.getSystemGestureInsets());
            }
            return this.f2684e;
        }

        @Override // b.g.k.b0.e, b.g.k.b0.i
        b0 i(int i2, int i3, int i4, int i5) {
            return b0.q(this.f2681b.inset(i2, i3, i4, i5));
        }

        h(b0 b0Var, h hVar) {
            super(b0Var, hVar);
            this.f2684e = null;
            this.f2685f = null;
            this.f2686g = null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final d f2673a;

        public a() {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 29) {
                this.f2673a = new c();
            } else if (i2 >= 20) {
                this.f2673a = new b();
            } else {
                this.f2673a = new d();
            }
        }

        public b0 a() {
            return this.f2673a.a();
        }

        public a b(b.g.d.b bVar) {
            this.f2673a.b(bVar);
            return this;
        }

        public a c(b.g.d.b bVar) {
            this.f2673a.c(bVar);
            return this;
        }

        public a(b0 b0Var) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 29) {
                this.f2673a = new c(b0Var);
            } else if (i2 >= 20) {
                this.f2673a = new b(b0Var);
            } else {
                this.f2673a = new d(b0Var);
            }
        }
    }

    public b0(b0 b0Var) {
        if (b0Var != null) {
            i iVar = b0Var.f2672b;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 29 && (iVar instanceof h)) {
                this.f2672b = new h(this, (h) iVar);
                return;
            }
            if (i2 >= 28 && (iVar instanceof g)) {
                this.f2672b = new g(this, (g) iVar);
                return;
            }
            if (i2 >= 21 && (iVar instanceof f)) {
                this.f2672b = new f(this, (f) iVar);
                return;
            } else if (i2 >= 20 && (iVar instanceof e)) {
                this.f2672b = new e(this, (e) iVar);
                return;
            } else {
                this.f2672b = new i(this);
                return;
            }
        }
        this.f2672b = new i(this);
    }
}
