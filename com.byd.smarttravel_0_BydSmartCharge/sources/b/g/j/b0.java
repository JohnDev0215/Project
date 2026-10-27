package b.g.j;

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
    public static final b0 f1097a = new a().a().a().b().c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i f1098b;

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b0 f1104a;

        public d() {
            this(new b0((b0) null));
        }

        public b0 a() {
            return this.f1104a;
        }

        public void b(b.g.c.b bVar) {
        }

        public void c(b.g.c.b bVar) {
        }

        public d(b0 b0Var) {
            this.f1104a = b0Var;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class g extends f {
        public g(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
        }

        @Override // b.g.j.b0.i
        public b0 a() {
            return b0.p(this.f1105b.consumeDisplayCutout());
        }

        @Override // b.g.j.b0.i
        public b.g.j.c d() {
            return b.g.j.c.a(this.f1105b.getDisplayCutout());
        }

        @Override // b.g.j.b0.i
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof g) {
                return Objects.equals(this.f1105b, ((g) obj).f1105b);
            }
            return false;
        }

        @Override // b.g.j.b0.i
        public int hashCode() {
            return this.f1105b.hashCode();
        }

        public g(b0 b0Var, g gVar) {
            super(b0Var, gVar);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b0 f1108a;

        public i(b0 b0Var) {
            this.f1108a = b0Var;
        }

        public b0 a() {
            return this.f1108a;
        }

        public b0 b() {
            return this.f1108a;
        }

        public b0 c() {
            return this.f1108a;
        }

        public b.g.j.c d() {
            return null;
        }

        public b.g.c.b e() {
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
            return j() == iVar.j() && i() == iVar.i() && b.g.i.c.a(g(), iVar.g()) && b.g.i.c.a(f(), iVar.f()) && b.g.i.c.a(d(), iVar.d());
        }

        public b.g.c.b f() {
            return b.g.c.b.f978a;
        }

        public b.g.c.b g() {
            return b.g.c.b.f978a;
        }

        public b0 h(int i, int i2, int i3, int i4) {
            return b0.f1097a;
        }

        public int hashCode() {
            return b.g.i.c.b(Boolean.valueOf(j()), Boolean.valueOf(i()), g(), f(), d());
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
            this.f1098b = new h(this, windowInsets);
            return;
        }
        if (i2 >= 28) {
            this.f1098b = new g(this, windowInsets);
            return;
        }
        if (i2 >= 21) {
            this.f1098b = new f(this, windowInsets);
        } else if (i2 >= 20) {
            this.f1098b = new e(this, windowInsets);
        } else {
            this.f1098b = new i(this);
        }
    }

    public static b.g.c.b l(b.g.c.b bVar, int i2, int i3, int i4, int i5) {
        int iMax = Math.max(0, bVar.f979b - i2);
        int iMax2 = Math.max(0, bVar.f980c - i3);
        int iMax3 = Math.max(0, bVar.f981d - i4);
        int iMax4 = Math.max(0, bVar.e - i5);
        return (iMax == i2 && iMax2 == i3 && iMax3 == i4 && iMax4 == i5) ? bVar : b.g.c.b.a(iMax, iMax2, iMax3, iMax4);
    }

    public static b0 p(WindowInsets windowInsets) {
        return new b0((WindowInsets) b.g.i.h.c(windowInsets));
    }

    public b0 a() {
        return this.f1098b.a();
    }

    public b0 b() {
        return this.f1098b.b();
    }

    public b0 c() {
        return this.f1098b.c();
    }

    public b.g.c.b d() {
        return this.f1098b.e();
    }

    public int e() {
        return i().e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b0) {
            return b.g.i.c.a(this.f1098b, ((b0) obj).f1098b);
        }
        return false;
    }

    public int f() {
        return i().f979b;
    }

    public int g() {
        return i().f981d;
    }

    public int h() {
        return i().f980c;
    }

    public int hashCode() {
        i iVar = this.f1098b;
        if (iVar == null) {
            return 0;
        }
        return iVar.hashCode();
    }

    public b.g.c.b i() {
        return this.f1098b.g();
    }

    public boolean j() {
        return !i().equals(b.g.c.b.f978a);
    }

    public b0 k(int i2, int i3, int i4, int i5) {
        return this.f1098b.h(i2, i3, i4, i5);
    }

    public boolean m() {
        return this.f1098b.i();
    }

    @Deprecated
    public b0 n(int i2, int i3, int i4, int i5) {
        return new a(this).c(b.g.c.b.a(i2, i3, i4, i5)).a();
    }

    public WindowInsets o() {
        i iVar = this.f1098b;
        if (iVar instanceof e) {
            return ((e) iVar).f1105b;
        }
        return null;
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class b extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static Field f1100b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static boolean f1101c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static Constructor<WindowInsets> f1102d = null;
        public static boolean e = false;
        public WindowInsets f;

        public b() {
            this.f = d();
        }

        public static WindowInsets d() {
            if (!f1101c) {
                try {
                    f1100b = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException e2) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e2);
                }
                f1101c = true;
            }
            Field field = f1100b;
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
                    f1102d = WindowInsets.class.getConstructor(Rect.class);
                } catch (ReflectiveOperationException e4) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e4);
                }
                e = true;
            }
            Constructor<WindowInsets> constructor = f1102d;
            if (constructor != null) {
                try {
                    return constructor.newInstance(new Rect());
                } catch (ReflectiveOperationException e5) {
                    Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e5);
                }
            }
            return null;
        }

        @Override // b.g.j.b0.d
        public b0 a() {
            return b0.p(this.f);
        }

        @Override // b.g.j.b0.d
        public void c(b.g.c.b bVar) {
            WindowInsets windowInsets = this.f;
            if (windowInsets != null) {
                this.f = windowInsets.replaceSystemWindowInsets(bVar.f979b, bVar.f980c, bVar.f981d, bVar.e);
            }
        }

        public b(b0 b0Var) {
            this.f = b0Var.o();
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class c extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WindowInsets.Builder f1103b;

        public c() {
            this.f1103b = new WindowInsets.Builder();
        }

        @Override // b.g.j.b0.d
        public b0 a() {
            return b0.p(this.f1103b.build());
        }

        @Override // b.g.j.b0.d
        public void b(b.g.c.b bVar) {
            this.f1103b.setStableInsets(bVar.c());
        }

        @Override // b.g.j.b0.d
        public void c(b.g.c.b bVar) {
            this.f1103b.setSystemWindowInsets(bVar.c());
        }

        public c(b0 b0Var) {
            WindowInsets.Builder builder;
            WindowInsets windowInsetsO = b0Var.o();
            if (windowInsetsO != null) {
                builder = new WindowInsets.Builder(windowInsetsO);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.f1103b = builder;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class f extends e {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public b.g.c.b f1107d;

        public f(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
            this.f1107d = null;
        }

        @Override // b.g.j.b0.i
        public b0 b() {
            return b0.p(this.f1105b.consumeStableInsets());
        }

        @Override // b.g.j.b0.i
        public b0 c() {
            return b0.p(this.f1105b.consumeSystemWindowInsets());
        }

        @Override // b.g.j.b0.i
        public final b.g.c.b f() {
            if (this.f1107d == null) {
                this.f1107d = b.g.c.b.a(this.f1105b.getStableInsetLeft(), this.f1105b.getStableInsetTop(), this.f1105b.getStableInsetRight(), this.f1105b.getStableInsetBottom());
            }
            return this.f1107d;
        }

        @Override // b.g.j.b0.i
        public boolean i() {
            return this.f1105b.isConsumed();
        }

        public f(b0 b0Var, f fVar) {
            super(b0Var, fVar);
            this.f1107d = null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class e extends i {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WindowInsets f1105b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public b.g.c.b f1106c;

        public e(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var);
            this.f1106c = null;
            this.f1105b = windowInsets;
        }

        @Override // b.g.j.b0.i
        public final b.g.c.b g() {
            if (this.f1106c == null) {
                this.f1106c = b.g.c.b.a(this.f1105b.getSystemWindowInsetLeft(), this.f1105b.getSystemWindowInsetTop(), this.f1105b.getSystemWindowInsetRight(), this.f1105b.getSystemWindowInsetBottom());
            }
            return this.f1106c;
        }

        @Override // b.g.j.b0.i
        public b0 h(int i, int i2, int i3, int i4) {
            a aVar = new a(b0.p(this.f1105b));
            aVar.c(b0.l(g(), i, i2, i3, i4));
            aVar.b(b0.l(f(), i, i2, i3, i4));
            return aVar.a();
        }

        @Override // b.g.j.b0.i
        public boolean j() {
            return this.f1105b.isRound();
        }

        public e(b0 b0Var, e eVar) {
            this(b0Var, new WindowInsets(eVar.f1105b));
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class h extends g {
        public b.g.c.b e;
        public b.g.c.b f;
        public b.g.c.b g;

        public h(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
            this.e = null;
            this.f = null;
            this.g = null;
        }

        @Override // b.g.j.b0.i
        public b.g.c.b e() {
            if (this.f == null) {
                this.f = b.g.c.b.b(this.f1105b.getMandatorySystemGestureInsets());
            }
            return this.f;
        }

        @Override // b.g.j.b0.e, b.g.j.b0.i
        public b0 h(int i, int i2, int i3, int i4) {
            return b0.p(this.f1105b.inset(i, i2, i3, i4));
        }

        public h(b0 b0Var, h hVar) {
            super(b0Var, hVar);
            this.e = null;
            this.f = null;
            this.g = null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final d f1099a;

        public a() {
            int i = Build.VERSION.SDK_INT;
            if (i >= 29) {
                this.f1099a = new c();
            } else if (i >= 20) {
                this.f1099a = new b();
            } else {
                this.f1099a = new d();
            }
        }

        public b0 a() {
            return this.f1099a.a();
        }

        public a b(b.g.c.b bVar) {
            this.f1099a.b(bVar);
            return this;
        }

        public a c(b.g.c.b bVar) {
            this.f1099a.c(bVar);
            return this;
        }

        public a(b0 b0Var) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 29) {
                this.f1099a = new c(b0Var);
            } else if (i >= 20) {
                this.f1099a = new b(b0Var);
            } else {
                this.f1099a = new d(b0Var);
            }
        }
    }

    public b0(b0 b0Var) {
        if (b0Var != null) {
            i iVar = b0Var.f1098b;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 29 && (iVar instanceof h)) {
                this.f1098b = new h(this, (h) iVar);
                return;
            }
            if (i2 >= 28 && (iVar instanceof g)) {
                this.f1098b = new g(this, (g) iVar);
                return;
            }
            if (i2 >= 21 && (iVar instanceof f)) {
                this.f1098b = new f(this, (f) iVar);
                return;
            } else if (i2 >= 20 && (iVar instanceof e)) {
                this.f1098b = new e(this, (e) iVar);
                return;
            } else {
                this.f1098b = new i(this);
                return;
            }
        }
        this.f1098b = new i(this);
    }
}
