package a.g.k;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.WindowInsets;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b0 f378a = new a().a().a().b().c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final i f379b;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final d f380a;

        public a() {
            int i = Build.VERSION.SDK_INT;
            this.f380a = i >= 29 ? new c() : i >= 20 ? new b() : new d();
        }

        public a(b0 b0Var) {
            int i = Build.VERSION.SDK_INT;
            this.f380a = i >= 29 ? new c(b0Var) : i >= 20 ? new b(b0Var) : new d(b0Var);
        }

        public b0 a() {
            return this.f380a.a();
        }

        public a b(a.g.d.b bVar) {
            this.f380a.b(bVar);
            return this;
        }

        public a c(a.g.d.b bVar) {
            this.f380a.c(bVar);
            return this;
        }
    }

    private static class b extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static Field f381b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static boolean f382c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private static Constructor<WindowInsets> f383d = null;
        private static boolean e = false;
        private WindowInsets f;

        b() {
            this.f = d();
        }

        b(b0 b0Var) {
            this.f = b0Var.n();
        }

        private static WindowInsets d() {
            if (!f382c) {
                try {
                    f381b = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException e2) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e2);
                }
                f382c = true;
            }
            Field field = f381b;
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
                    f383d = WindowInsets.class.getConstructor(Rect.class);
                } catch (ReflectiveOperationException e4) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e4);
                }
                e = true;
            }
            Constructor<WindowInsets> constructor = f383d;
            if (constructor != null) {
                try {
                    return constructor.newInstance(new Rect());
                } catch (ReflectiveOperationException e5) {
                    Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e5);
                }
            }
            return null;
        }

        @Override // a.g.k.b0.d
        b0 a() {
            return b0.o(this.f);
        }

        @Override // a.g.k.b0.d
        void c(a.g.d.b bVar) {
            WindowInsets windowInsets = this.f;
            if (windowInsets != null) {
                this.f = windowInsets.replaceSystemWindowInsets(bVar.f272b, bVar.f273c, bVar.f274d, bVar.e);
            }
        }
    }

    private static class c extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final WindowInsets.Builder f384b;

        c() {
            this.f384b = new WindowInsets.Builder();
        }

        c(b0 b0Var) {
            WindowInsets windowInsetsN = b0Var.n();
            this.f384b = windowInsetsN != null ? new WindowInsets.Builder(windowInsetsN) : new WindowInsets.Builder();
        }

        @Override // a.g.k.b0.d
        b0 a() {
            return b0.o(this.f384b.build());
        }

        @Override // a.g.k.b0.d
        void b(a.g.d.b bVar) {
            this.f384b.setStableInsets(bVar.c());
        }

        @Override // a.g.k.b0.d
        void c(a.g.d.b bVar) {
            this.f384b.setSystemWindowInsets(bVar.c());
        }
    }

    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final b0 f385a;

        d() {
            this(new b0((b0) null));
        }

        d(b0 b0Var) {
            this.f385a = b0Var;
        }

        b0 a() {
            return this.f385a;
        }

        void b(a.g.d.b bVar) {
        }

        void c(a.g.d.b bVar) {
        }
    }

    private static class e extends i {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final WindowInsets f386b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private a.g.d.b f387c;

        e(b0 b0Var, e eVar) {
            this(b0Var, new WindowInsets(eVar.f386b));
        }

        e(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var);
            this.f387c = null;
            this.f386b = windowInsets;
        }

        @Override // a.g.k.b0.i
        final a.g.d.b g() {
            if (this.f387c == null) {
                this.f387c = a.g.d.b.a(this.f386b.getSystemWindowInsetLeft(), this.f386b.getSystemWindowInsetTop(), this.f386b.getSystemWindowInsetRight(), this.f386b.getSystemWindowInsetBottom());
            }
            return this.f387c;
        }

        @Override // a.g.k.b0.i
        b0 h(int i, int i2, int i3, int i4) {
            a aVar = new a(b0.o(this.f386b));
            aVar.c(b0.k(g(), i, i2, i3, i4));
            aVar.b(b0.k(f(), i, i2, i3, i4));
            return aVar.a();
        }

        @Override // a.g.k.b0.i
        boolean j() {
            return this.f386b.isRound();
        }
    }

    private static class f extends e {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private a.g.d.b f388d;

        f(b0 b0Var, f fVar) {
            super(b0Var, fVar);
            this.f388d = null;
        }

        f(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
            this.f388d = null;
        }

        @Override // a.g.k.b0.i
        b0 b() {
            return b0.o(this.f386b.consumeStableInsets());
        }

        @Override // a.g.k.b0.i
        b0 c() {
            return b0.o(this.f386b.consumeSystemWindowInsets());
        }

        @Override // a.g.k.b0.i
        final a.g.d.b f() {
            if (this.f388d == null) {
                this.f388d = a.g.d.b.a(this.f386b.getStableInsetLeft(), this.f386b.getStableInsetTop(), this.f386b.getStableInsetRight(), this.f386b.getStableInsetBottom());
            }
            return this.f388d;
        }

        @Override // a.g.k.b0.i
        boolean i() {
            return this.f386b.isConsumed();
        }
    }

    private static class g extends f {
        g(b0 b0Var, g gVar) {
            super(b0Var, gVar);
        }

        g(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
        }

        @Override // a.g.k.b0.i
        b0 a() {
            return b0.o(this.f386b.consumeDisplayCutout());
        }

        @Override // a.g.k.b0.i
        a.g.k.c d() {
            return a.g.k.c.a(this.f386b.getDisplayCutout());
        }

        @Override // a.g.k.b0.i
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof g) {
                return Objects.equals(this.f386b, ((g) obj).f386b);
            }
            return false;
        }

        @Override // a.g.k.b0.i
        public int hashCode() {
            return this.f386b.hashCode();
        }
    }

    private static class h extends g {
        private a.g.d.b e;
        private a.g.d.b f;
        private a.g.d.b g;

        h(b0 b0Var, h hVar) {
            super(b0Var, hVar);
            this.e = null;
            this.f = null;
            this.g = null;
        }

        h(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
            this.e = null;
            this.f = null;
            this.g = null;
        }

        @Override // a.g.k.b0.i
        a.g.d.b e() {
            if (this.f == null) {
                this.f = a.g.d.b.b(this.f386b.getMandatorySystemGestureInsets());
            }
            return this.f;
        }

        @Override // a.g.k.b0.e, a.g.k.b0.i
        b0 h(int i, int i2, int i3, int i4) {
            return b0.o(this.f386b.inset(i, i2, i3, i4));
        }
    }

    private static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final b0 f389a;

        i(b0 b0Var) {
            this.f389a = b0Var;
        }

        b0 a() {
            return this.f389a;
        }

        b0 b() {
            return this.f389a;
        }

        b0 c() {
            return this.f389a;
        }

        a.g.k.c d() {
            return null;
        }

        a.g.d.b e() {
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
            return j() == iVar.j() && i() == iVar.i() && a.g.j.c.a(g(), iVar.g()) && a.g.j.c.a(f(), iVar.f()) && a.g.j.c.a(d(), iVar.d());
        }

        a.g.d.b f() {
            return a.g.d.b.f271a;
        }

        a.g.d.b g() {
            return a.g.d.b.f271a;
        }

        b0 h(int i, int i2, int i3, int i4) {
            return b0.f378a;
        }

        public int hashCode() {
            return a.g.j.c.b(Boolean.valueOf(j()), Boolean.valueOf(i()), g(), f(), d());
        }

        boolean i() {
            return false;
        }

        boolean j() {
            return false;
        }
    }

    public b0(b0 b0Var) {
        i iVar;
        i eVar;
        if (b0Var != null) {
            i iVar2 = b0Var.f379b;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 29 && (iVar2 instanceof h)) {
                eVar = new h(this, (h) iVar2);
            } else if (i2 >= 28 && (iVar2 instanceof g)) {
                eVar = new g(this, (g) iVar2);
            } else if (i2 >= 21 && (iVar2 instanceof f)) {
                eVar = new f(this, (f) iVar2);
            } else if (i2 < 20 || !(iVar2 instanceof e)) {
                iVar = new i(this);
            } else {
                eVar = new e(this, (e) iVar2);
            }
            this.f379b = eVar;
            return;
        }
        iVar = new i(this);
        this.f379b = iVar;
    }

    private b0(WindowInsets windowInsets) {
        i eVar;
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            eVar = new h(this, windowInsets);
        } else if (i2 >= 28) {
            eVar = new g(this, windowInsets);
        } else if (i2 >= 21) {
            eVar = new f(this, windowInsets);
        } else {
            if (i2 < 20) {
                this.f379b = new i(this);
                return;
            }
            eVar = new e(this, windowInsets);
        }
        this.f379b = eVar;
    }

    static a.g.d.b k(a.g.d.b bVar, int i2, int i3, int i4, int i5) {
        int iMax = Math.max(0, bVar.f272b - i2);
        int iMax2 = Math.max(0, bVar.f273c - i3);
        int iMax3 = Math.max(0, bVar.f274d - i4);
        int iMax4 = Math.max(0, bVar.e - i5);
        return (iMax == i2 && iMax2 == i3 && iMax3 == i4 && iMax4 == i5) ? bVar : a.g.d.b.a(iMax, iMax2, iMax3, iMax4);
    }

    public static b0 o(WindowInsets windowInsets) {
        return new b0((WindowInsets) a.g.j.h.c(windowInsets));
    }

    public b0 a() {
        return this.f379b.a();
    }

    public b0 b() {
        return this.f379b.b();
    }

    public b0 c() {
        return this.f379b.c();
    }

    public a.g.d.b d() {
        return this.f379b.e();
    }

    public int e() {
        return i().e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b0) {
            return a.g.j.c.a(this.f379b, ((b0) obj).f379b);
        }
        return false;
    }

    public int f() {
        return i().f272b;
    }

    public int g() {
        return i().f274d;
    }

    public int h() {
        return i().f273c;
    }

    public int hashCode() {
        i iVar = this.f379b;
        if (iVar == null) {
            return 0;
        }
        return iVar.hashCode();
    }

    public a.g.d.b i() {
        return this.f379b.g();
    }

    public b0 j(int i2, int i3, int i4, int i5) {
        return this.f379b.h(i2, i3, i4, i5);
    }

    public boolean l() {
        return this.f379b.i();
    }

    @Deprecated
    public b0 m(int i2, int i3, int i4, int i5) {
        return new a(this).c(a.g.d.b.a(i2, i3, i4, i5)).a();
    }

    public WindowInsets n() {
        i iVar = this.f379b;
        if (iVar instanceof e) {
            return ((e) iVar).f386b;
        }
        return null;
    }
}
