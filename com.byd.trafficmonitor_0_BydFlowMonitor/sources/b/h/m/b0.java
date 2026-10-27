package b.h.m;

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
    public static final b0 f2587a = new a().a().a().b().c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i f2588b;

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b0 f2596a;

        public d() {
            this(new b0((b0) null));
        }

        public b0 a() {
            return this.f2596a;
        }

        public void b(b.h.f.b bVar) {
        }

        public void c(b.h.f.b bVar) {
        }

        public d(b0 b0Var) {
            this.f2596a = b0Var;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class g extends f {
        public g(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
        }

        @Override // b.h.m.b0.i
        public b0 a() {
            return b0.p(this.f2597b.consumeDisplayCutout());
        }

        @Override // b.h.m.b0.i
        public b.h.m.c d() {
            return b.h.m.c.a(this.f2597b.getDisplayCutout());
        }

        @Override // b.h.m.b0.i
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof g) {
                return Objects.equals(this.f2597b, ((g) obj).f2597b);
            }
            return false;
        }

        @Override // b.h.m.b0.i
        public int hashCode() {
            return this.f2597b.hashCode();
        }

        public g(b0 b0Var, g gVar) {
            super(b0Var, gVar);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b0 f2603a;

        public i(b0 b0Var) {
            this.f2603a = b0Var;
        }

        public b0 a() {
            return this.f2603a;
        }

        public b0 b() {
            return this.f2603a;
        }

        public b0 c() {
            return this.f2603a;
        }

        public b.h.m.c d() {
            return null;
        }

        public b.h.f.b e() {
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
            return j() == iVar.j() && i() == iVar.i() && b.h.l.b.a(g(), iVar.g()) && b.h.l.b.a(f(), iVar.f()) && b.h.l.b.a(d(), iVar.d());
        }

        public b.h.f.b f() {
            return b.h.f.b.f2431a;
        }

        public b.h.f.b g() {
            return b.h.f.b.f2431a;
        }

        public b0 h(int i2, int i3, int i4, int i5) {
            return b0.f2587a;
        }

        public int hashCode() {
            return b.h.l.b.b(Boolean.valueOf(j()), Boolean.valueOf(i()), g(), f(), d());
        }

        public boolean i() {
            return false;
        }

        public boolean j() {
            return false;
        }
    }

    public b0(WindowInsets windowInsets) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            this.f2588b = new h(this, windowInsets);
            return;
        }
        if (i2 >= 28) {
            this.f2588b = new g(this, windowInsets);
            return;
        }
        if (i2 >= 21) {
            this.f2588b = new f(this, windowInsets);
        } else if (i2 >= 20) {
            this.f2588b = new e(this, windowInsets);
        } else {
            this.f2588b = new i(this);
        }
    }

    public static b.h.f.b l(b.h.f.b bVar, int i2, int i3, int i4, int i5) {
        int iMax = Math.max(0, bVar.f2432b - i2);
        int iMax2 = Math.max(0, bVar.f2433c - i3);
        int iMax3 = Math.max(0, bVar.f2434d - i4);
        int iMax4 = Math.max(0, bVar.f2435e - i5);
        return (iMax == i2 && iMax2 == i3 && iMax3 == i4 && iMax4 == i5) ? bVar : b.h.f.b.a(iMax, iMax2, iMax3, iMax4);
    }

    public static b0 p(WindowInsets windowInsets) {
        return new b0((WindowInsets) b.h.l.g.c(windowInsets));
    }

    public b0 a() {
        return this.f2588b.a();
    }

    public b0 b() {
        return this.f2588b.b();
    }

    public b0 c() {
        return this.f2588b.c();
    }

    public b.h.f.b d() {
        return this.f2588b.e();
    }

    public int e() {
        return i().f2435e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b0) {
            return b.h.l.b.a(this.f2588b, ((b0) obj).f2588b);
        }
        return false;
    }

    public int f() {
        return i().f2432b;
    }

    public int g() {
        return i().f2434d;
    }

    public int h() {
        return i().f2433c;
    }

    public int hashCode() {
        i iVar = this.f2588b;
        if (iVar == null) {
            return 0;
        }
        return iVar.hashCode();
    }

    public b.h.f.b i() {
        return this.f2588b.g();
    }

    public boolean j() {
        return !i().equals(b.h.f.b.f2431a);
    }

    public b0 k(int i2, int i3, int i4, int i5) {
        return this.f2588b.h(i2, i3, i4, i5);
    }

    public boolean m() {
        return this.f2588b.i();
    }

    @Deprecated
    public b0 n(int i2, int i3, int i4, int i5) {
        return new a(this).c(b.h.f.b.a(i2, i3, i4, i5)).a();
    }

    public WindowInsets o() {
        i iVar = this.f2588b;
        if (iVar instanceof e) {
            return ((e) iVar).f2597b;
        }
        return null;
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class b extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static Field f2590b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static boolean f2591c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static Constructor<WindowInsets> f2592d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static boolean f2593e = false;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public WindowInsets f2594f;

        public b() {
            this.f2594f = d();
        }

        public static WindowInsets d() {
            if (!f2591c) {
                try {
                    f2590b = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException e2) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e2);
                }
                f2591c = true;
            }
            Field field = f2590b;
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
            if (!f2593e) {
                try {
                    f2592d = WindowInsets.class.getConstructor(Rect.class);
                } catch (ReflectiveOperationException e4) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e4);
                }
                f2593e = true;
            }
            Constructor<WindowInsets> constructor = f2592d;
            if (constructor != null) {
                try {
                    return constructor.newInstance(new Rect());
                } catch (ReflectiveOperationException e5) {
                    Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e5);
                }
            }
            return null;
        }

        @Override // b.h.m.b0.d
        public b0 a() {
            return b0.p(this.f2594f);
        }

        @Override // b.h.m.b0.d
        public void c(b.h.f.b bVar) {
            WindowInsets windowInsets = this.f2594f;
            if (windowInsets != null) {
                this.f2594f = windowInsets.replaceSystemWindowInsets(bVar.f2432b, bVar.f2433c, bVar.f2434d, bVar.f2435e);
            }
        }

        public b(b0 b0Var) {
            this.f2594f = b0Var.o();
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class c extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WindowInsets.Builder f2595b;

        public c() {
            this.f2595b = new WindowInsets.Builder();
        }

        @Override // b.h.m.b0.d
        public b0 a() {
            return b0.p(this.f2595b.build());
        }

        @Override // b.h.m.b0.d
        public void b(b.h.f.b bVar) {
            this.f2595b.setStableInsets(bVar.c());
        }

        @Override // b.h.m.b0.d
        public void c(b.h.f.b bVar) {
            this.f2595b.setSystemWindowInsets(bVar.c());
        }

        public c(b0 b0Var) {
            WindowInsets.Builder builder;
            WindowInsets windowInsetsO = b0Var.o();
            if (windowInsetsO != null) {
                builder = new WindowInsets.Builder(windowInsetsO);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.f2595b = builder;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class f extends e {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public b.h.f.b f2599d;

        public f(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
            this.f2599d = null;
        }

        @Override // b.h.m.b0.i
        public b0 b() {
            return b0.p(this.f2597b.consumeStableInsets());
        }

        @Override // b.h.m.b0.i
        public b0 c() {
            return b0.p(this.f2597b.consumeSystemWindowInsets());
        }

        @Override // b.h.m.b0.i
        public final b.h.f.b f() {
            if (this.f2599d == null) {
                this.f2599d = b.h.f.b.a(this.f2597b.getStableInsetLeft(), this.f2597b.getStableInsetTop(), this.f2597b.getStableInsetRight(), this.f2597b.getStableInsetBottom());
            }
            return this.f2599d;
        }

        @Override // b.h.m.b0.i
        public boolean i() {
            return this.f2597b.isConsumed();
        }

        public f(b0 b0Var, f fVar) {
            super(b0Var, fVar);
            this.f2599d = null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class e extends i {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WindowInsets f2597b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public b.h.f.b f2598c;

        public e(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var);
            this.f2598c = null;
            this.f2597b = windowInsets;
        }

        @Override // b.h.m.b0.i
        public final b.h.f.b g() {
            if (this.f2598c == null) {
                this.f2598c = b.h.f.b.a(this.f2597b.getSystemWindowInsetLeft(), this.f2597b.getSystemWindowInsetTop(), this.f2597b.getSystemWindowInsetRight(), this.f2597b.getSystemWindowInsetBottom());
            }
            return this.f2598c;
        }

        @Override // b.h.m.b0.i
        public b0 h(int i2, int i3, int i4, int i5) {
            a aVar = new a(b0.p(this.f2597b));
            aVar.c(b0.l(g(), i2, i3, i4, i5));
            aVar.b(b0.l(f(), i2, i3, i4, i5));
            return aVar.a();
        }

        @Override // b.h.m.b0.i
        public boolean j() {
            return this.f2597b.isRound();
        }

        public e(b0 b0Var, e eVar) {
            this(b0Var, new WindowInsets(eVar.f2597b));
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class h extends g {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public b.h.f.b f2600e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public b.h.f.b f2601f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public b.h.f.b f2602g;

        public h(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
            this.f2600e = null;
            this.f2601f = null;
            this.f2602g = null;
        }

        @Override // b.h.m.b0.i
        public b.h.f.b e() {
            if (this.f2601f == null) {
                this.f2601f = b.h.f.b.b(this.f2597b.getMandatorySystemGestureInsets());
            }
            return this.f2601f;
        }

        @Override // b.h.m.b0.e, b.h.m.b0.i
        public b0 h(int i2, int i3, int i4, int i5) {
            return b0.p(this.f2597b.inset(i2, i3, i4, i5));
        }

        public h(b0 b0Var, h hVar) {
            super(b0Var, hVar);
            this.f2600e = null;
            this.f2601f = null;
            this.f2602g = null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final d f2589a;

        public a() {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 29) {
                this.f2589a = new c();
            } else if (i2 >= 20) {
                this.f2589a = new b();
            } else {
                this.f2589a = new d();
            }
        }

        public b0 a() {
            return this.f2589a.a();
        }

        public a b(b.h.f.b bVar) {
            this.f2589a.b(bVar);
            return this;
        }

        public a c(b.h.f.b bVar) {
            this.f2589a.c(bVar);
            return this;
        }

        public a(b0 b0Var) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 29) {
                this.f2589a = new c(b0Var);
            } else if (i2 >= 20) {
                this.f2589a = new b(b0Var);
            } else {
                this.f2589a = new d(b0Var);
            }
        }
    }

    public b0(b0 b0Var) {
        if (b0Var != null) {
            i iVar = b0Var.f2588b;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 29 && (iVar instanceof h)) {
                this.f2588b = new h(this, (h) iVar);
                return;
            }
            if (i2 >= 28 && (iVar instanceof g)) {
                this.f2588b = new g(this, (g) iVar);
                return;
            }
            if (i2 >= 21 && (iVar instanceof f)) {
                this.f2588b = new f(this, (f) iVar);
                return;
            } else if (i2 >= 20 && (iVar instanceof e)) {
                this.f2588b = new e(this, (e) iVar);
                return;
            } else {
                this.f2588b = new i(this);
                return;
            }
        }
        this.f2588b = new i(this);
    }
}
