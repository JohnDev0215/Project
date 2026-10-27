package b.f.m;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.WindowInsets;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Objects;

/* JADX INFO: compiled from: WindowInsetsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a0 f1735a = new a().a().a().b().c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i f1736b;

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final a0 f1744a;

        public d() {
            this(new a0((a0) null));
        }

        public a0 a() {
            return this.f1744a;
        }

        public void b(b.f.g.b bVar) {
        }

        public void c(b.f.g.b bVar) {
        }

        public d(a0 a0Var) {
            this.f1744a = a0Var;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class g extends f {
        public g(a0 a0Var, WindowInsets windowInsets) {
            super(a0Var, windowInsets);
        }

        @Override // b.f.m.a0.i
        public a0 a() {
            return a0.n(this.f1745b.consumeDisplayCutout());
        }

        @Override // b.f.m.a0.i
        public b.f.m.c d() {
            return b.f.m.c.a(this.f1745b.getDisplayCutout());
        }

        @Override // b.f.m.a0.i
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof g) {
                return Objects.equals(this.f1745b, ((g) obj).f1745b);
            }
            return false;
        }

        @Override // b.f.m.a0.i
        public int hashCode() {
            return this.f1745b.hashCode();
        }

        public g(a0 a0Var, g gVar) {
            super(a0Var, gVar);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final a0 f1751a;

        public i(a0 a0Var) {
            this.f1751a = a0Var;
        }

        public a0 a() {
            return this.f1751a;
        }

        public a0 b() {
            return this.f1751a;
        }

        public a0 c() {
            return this.f1751a;
        }

        public b.f.m.c d() {
            return null;
        }

        public b.f.g.b e() {
            return b.f.g.b.f1635a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof i)) {
                return false;
            }
            i iVar = (i) obj;
            return i() == iVar.i() && h() == iVar.h() && b.f.l.c.a(f(), iVar.f()) && b.f.l.c.a(e(), iVar.e()) && b.f.l.c.a(d(), iVar.d());
        }

        public b.f.g.b f() {
            return b.f.g.b.f1635a;
        }

        public a0 g(int i2, int i3, int i4, int i5) {
            return a0.f1735a;
        }

        public boolean h() {
            return false;
        }

        public int hashCode() {
            return b.f.l.c.b(Boolean.valueOf(i()), Boolean.valueOf(h()), f(), e(), d());
        }

        public boolean i() {
            return false;
        }
    }

    public a0(WindowInsets windowInsets) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            this.f1736b = new h(this, windowInsets);
            return;
        }
        if (i2 >= 28) {
            this.f1736b = new g(this, windowInsets);
            return;
        }
        if (i2 >= 21) {
            this.f1736b = new f(this, windowInsets);
        } else if (i2 >= 20) {
            this.f1736b = new e(this, windowInsets);
        } else {
            this.f1736b = new i(this);
        }
    }

    public static b.f.g.b j(b.f.g.b bVar, int i2, int i3, int i4, int i5) {
        int iMax = Math.max(0, bVar.f1636b - i2);
        int iMax2 = Math.max(0, bVar.f1637c - i3);
        int iMax3 = Math.max(0, bVar.f1638d - i4);
        int iMax4 = Math.max(0, bVar.f1639e - i5);
        return (iMax == i2 && iMax2 == i3 && iMax3 == i4 && iMax4 == i5) ? bVar : b.f.g.b.a(iMax, iMax2, iMax3, iMax4);
    }

    public static a0 n(WindowInsets windowInsets) {
        return new a0((WindowInsets) b.f.l.f.b(windowInsets));
    }

    public a0 a() {
        return this.f1736b.a();
    }

    public a0 b() {
        return this.f1736b.b();
    }

    public a0 c() {
        return this.f1736b.c();
    }

    public int d() {
        return h().f1639e;
    }

    public int e() {
        return h().f1636b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof a0) {
            return b.f.l.c.a(this.f1736b, ((a0) obj).f1736b);
        }
        return false;
    }

    public int f() {
        return h().f1638d;
    }

    public int g() {
        return h().f1637c;
    }

    public b.f.g.b h() {
        return this.f1736b.f();
    }

    public int hashCode() {
        i iVar = this.f1736b;
        if (iVar == null) {
            return 0;
        }
        return iVar.hashCode();
    }

    public a0 i(int i2, int i3, int i4, int i5) {
        return this.f1736b.g(i2, i3, i4, i5);
    }

    public boolean k() {
        return this.f1736b.h();
    }

    @Deprecated
    public a0 l(int i2, int i3, int i4, int i5) {
        return new a(this).c(b.f.g.b.a(i2, i3, i4, i5)).a();
    }

    public WindowInsets m() {
        i iVar = this.f1736b;
        if (iVar instanceof e) {
            return ((e) iVar).f1745b;
        }
        return null;
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class b extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static Field f1738b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static boolean f1739c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static Constructor<WindowInsets> f1740d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static boolean f1741e = false;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public WindowInsets f1742f;

        public b() {
            this.f1742f = d();
        }

        public static WindowInsets d() {
            if (!f1739c) {
                try {
                    f1738b = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException e2) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e2);
                }
                f1739c = true;
            }
            Field field = f1738b;
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
            if (!f1741e) {
                try {
                    f1740d = WindowInsets.class.getConstructor(Rect.class);
                } catch (ReflectiveOperationException e4) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e4);
                }
                f1741e = true;
            }
            Constructor<WindowInsets> constructor = f1740d;
            if (constructor != null) {
                try {
                    return constructor.newInstance(new Rect());
                } catch (ReflectiveOperationException e5) {
                    Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e5);
                }
            }
            return null;
        }

        @Override // b.f.m.a0.d
        public a0 a() {
            return a0.n(this.f1742f);
        }

        @Override // b.f.m.a0.d
        public void c(b.f.g.b bVar) {
            WindowInsets windowInsets = this.f1742f;
            if (windowInsets != null) {
                this.f1742f = windowInsets.replaceSystemWindowInsets(bVar.f1636b, bVar.f1637c, bVar.f1638d, bVar.f1639e);
            }
        }

        public b(a0 a0Var) {
            this.f1742f = a0Var.m();
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class c extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WindowInsets.Builder f1743b;

        public c() {
            this.f1743b = new WindowInsets.Builder();
        }

        @Override // b.f.m.a0.d
        public a0 a() {
            return a0.n(this.f1743b.build());
        }

        @Override // b.f.m.a0.d
        public void b(b.f.g.b bVar) {
            this.f1743b.setStableInsets(bVar.b());
        }

        @Override // b.f.m.a0.d
        public void c(b.f.g.b bVar) {
            this.f1743b.setSystemWindowInsets(bVar.b());
        }

        public c(a0 a0Var) {
            WindowInsets.Builder builder;
            WindowInsets windowInsetsM = a0Var.m();
            if (windowInsetsM != null) {
                builder = new WindowInsets.Builder(windowInsetsM);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.f1743b = builder;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class f extends e {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public b.f.g.b f1747d;

        public f(a0 a0Var, WindowInsets windowInsets) {
            super(a0Var, windowInsets);
            this.f1747d = null;
        }

        @Override // b.f.m.a0.i
        public a0 b() {
            return a0.n(this.f1745b.consumeStableInsets());
        }

        @Override // b.f.m.a0.i
        public a0 c() {
            return a0.n(this.f1745b.consumeSystemWindowInsets());
        }

        @Override // b.f.m.a0.i
        public final b.f.g.b e() {
            if (this.f1747d == null) {
                this.f1747d = b.f.g.b.a(this.f1745b.getStableInsetLeft(), this.f1745b.getStableInsetTop(), this.f1745b.getStableInsetRight(), this.f1745b.getStableInsetBottom());
            }
            return this.f1747d;
        }

        @Override // b.f.m.a0.i
        public boolean h() {
            return this.f1745b.isConsumed();
        }

        public f(a0 a0Var, f fVar) {
            super(a0Var, fVar);
            this.f1747d = null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class e extends i {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WindowInsets f1745b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public b.f.g.b f1746c;

        public e(a0 a0Var, WindowInsets windowInsets) {
            super(a0Var);
            this.f1746c = null;
            this.f1745b = windowInsets;
        }

        @Override // b.f.m.a0.i
        public final b.f.g.b f() {
            if (this.f1746c == null) {
                this.f1746c = b.f.g.b.a(this.f1745b.getSystemWindowInsetLeft(), this.f1745b.getSystemWindowInsetTop(), this.f1745b.getSystemWindowInsetRight(), this.f1745b.getSystemWindowInsetBottom());
            }
            return this.f1746c;
        }

        @Override // b.f.m.a0.i
        public a0 g(int i2, int i3, int i4, int i5) {
            a aVar = new a(a0.n(this.f1745b));
            aVar.c(a0.j(f(), i2, i3, i4, i5));
            aVar.b(a0.j(e(), i2, i3, i4, i5));
            return aVar.a();
        }

        @Override // b.f.m.a0.i
        public boolean i() {
            return this.f1745b.isRound();
        }

        public e(a0 a0Var, e eVar) {
            this(a0Var, new WindowInsets(eVar.f1745b));
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class h extends g {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public b.f.g.b f1748e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public b.f.g.b f1749f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public b.f.g.b f1750g;

        public h(a0 a0Var, WindowInsets windowInsets) {
            super(a0Var, windowInsets);
            this.f1748e = null;
            this.f1749f = null;
            this.f1750g = null;
        }

        @Override // b.f.m.a0.e, b.f.m.a0.i
        public a0 g(int i2, int i3, int i4, int i5) {
            return a0.n(this.f1745b.inset(i2, i3, i4, i5));
        }

        public h(a0 a0Var, h hVar) {
            super(a0Var, hVar);
            this.f1748e = null;
            this.f1749f = null;
            this.f1750g = null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final d f1737a;

        public a() {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 29) {
                this.f1737a = new c();
            } else if (i2 >= 20) {
                this.f1737a = new b();
            } else {
                this.f1737a = new d();
            }
        }

        public a0 a() {
            return this.f1737a.a();
        }

        public a b(b.f.g.b bVar) {
            this.f1737a.b(bVar);
            return this;
        }

        public a c(b.f.g.b bVar) {
            this.f1737a.c(bVar);
            return this;
        }

        public a(a0 a0Var) {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 29) {
                this.f1737a = new c(a0Var);
            } else if (i2 >= 20) {
                this.f1737a = new b(a0Var);
            } else {
                this.f1737a = new d(a0Var);
            }
        }
    }

    public a0(a0 a0Var) {
        if (a0Var != null) {
            i iVar = a0Var.f1736b;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 29 && (iVar instanceof h)) {
                this.f1736b = new h(this, (h) iVar);
                return;
            }
            if (i2 >= 28 && (iVar instanceof g)) {
                this.f1736b = new g(this, (g) iVar);
                return;
            }
            if (i2 >= 21 && (iVar instanceof f)) {
                this.f1736b = new f(this, (f) iVar);
                return;
            } else if (i2 >= 20 && (iVar instanceof e)) {
                this.f1736b = new e(this, (e) iVar);
                return;
            } else {
                this.f1736b = new i(this);
                return;
            }
        }
        this.f1736b = new i(this);
    }
}
