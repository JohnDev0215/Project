package o0;

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
    public static final b0 f6664a = new a().a().a().b().c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i f6665b;

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b0 f6673a;

        public d() {
            this(new b0((b0) null));
        }

        public b0 a() {
            return this.f6673a;
        }

        public void b(g0.b bVar) {
        }

        public void c(g0.b bVar) {
        }

        public d(b0 b0Var) {
            this.f6673a = b0Var;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class g extends f {
        public g(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
        }

        @Override // o0.b0.i
        public b0 a() {
            return b0.p(this.f6674b.consumeDisplayCutout());
        }

        @Override // o0.b0.i
        public o0.c d() {
            return o0.c.a(this.f6674b.getDisplayCutout());
        }

        @Override // o0.b0.i
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof g) {
                return Objects.equals(this.f6674b, ((g) obj).f6674b);
            }
            return false;
        }

        @Override // o0.b0.i
        public int hashCode() {
            return this.f6674b.hashCode();
        }

        public g(b0 b0Var, g gVar) {
            super(b0Var, gVar);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b0 f6680a;

        public i(b0 b0Var) {
            this.f6680a = b0Var;
        }

        public b0 a() {
            return this.f6680a;
        }

        public b0 b() {
            return this.f6680a;
        }

        public b0 c() {
            return this.f6680a;
        }

        public o0.c d() {
            return null;
        }

        public g0.b e() {
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
            return j() == iVar.j() && i() == iVar.i() && n0.c.a(g(), iVar.g()) && n0.c.a(f(), iVar.f()) && n0.c.a(d(), iVar.d());
        }

        public g0.b f() {
            return g0.b.f4981a;
        }

        public g0.b g() {
            return g0.b.f4981a;
        }

        public b0 h(int i8, int i9, int i10, int i11) {
            return b0.f6664a;
        }

        public int hashCode() {
            return n0.c.b(Boolean.valueOf(j()), Boolean.valueOf(i()), g(), f(), d());
        }

        public boolean i() {
            return false;
        }

        public boolean j() {
            return false;
        }
    }

    public b0(WindowInsets windowInsets) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 29) {
            this.f6665b = new h(this, windowInsets);
            return;
        }
        if (i8 >= 28) {
            this.f6665b = new g(this, windowInsets);
            return;
        }
        if (i8 >= 21) {
            this.f6665b = new f(this, windowInsets);
        } else if (i8 >= 20) {
            this.f6665b = new e(this, windowInsets);
        } else {
            this.f6665b = new i(this);
        }
    }

    public static g0.b l(g0.b bVar, int i8, int i9, int i10, int i11) {
        int iMax = Math.max(0, bVar.f4982b - i8);
        int iMax2 = Math.max(0, bVar.f4983c - i9);
        int iMax3 = Math.max(0, bVar.f4984d - i10);
        int iMax4 = Math.max(0, bVar.f4985e - i11);
        return (iMax == i8 && iMax2 == i9 && iMax3 == i10 && iMax4 == i11) ? bVar : g0.b.a(iMax, iMax2, iMax3, iMax4);
    }

    public static b0 p(WindowInsets windowInsets) {
        return new b0((WindowInsets) n0.h.e(windowInsets));
    }

    public b0 a() {
        return this.f6665b.a();
    }

    public b0 b() {
        return this.f6665b.b();
    }

    public b0 c() {
        return this.f6665b.c();
    }

    public g0.b d() {
        return this.f6665b.e();
    }

    public int e() {
        return i().f4985e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b0) {
            return n0.c.a(this.f6665b, ((b0) obj).f6665b);
        }
        return false;
    }

    public int f() {
        return i().f4982b;
    }

    public int g() {
        return i().f4984d;
    }

    public int h() {
        return i().f4983c;
    }

    public int hashCode() {
        i iVar = this.f6665b;
        if (iVar == null) {
            return 0;
        }
        return iVar.hashCode();
    }

    public g0.b i() {
        return this.f6665b.g();
    }

    public boolean j() {
        return !i().equals(g0.b.f4981a);
    }

    public b0 k(int i8, int i9, int i10, int i11) {
        return this.f6665b.h(i8, i9, i10, i11);
    }

    public boolean m() {
        return this.f6665b.i();
    }

    @Deprecated
    public b0 n(int i8, int i9, int i10, int i11) {
        return new a(this).c(g0.b.a(i8, i9, i10, i11)).a();
    }

    public WindowInsets o() {
        i iVar = this.f6665b;
        if (iVar instanceof e) {
            return ((e) iVar).f6674b;
        }
        return null;
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class b extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static Field f6667b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static boolean f6668c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static Constructor<WindowInsets> f6669d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static boolean f6670e = false;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public WindowInsets f6671f;

        public b() {
            this.f6671f = d();
        }

        public static WindowInsets d() {
            if (!f6668c) {
                try {
                    f6667b = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException e8) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e8);
                }
                f6668c = true;
            }
            Field field = f6667b;
            if (field != null) {
                try {
                    WindowInsets windowInsets = (WindowInsets) field.get(null);
                    if (windowInsets != null) {
                        return new WindowInsets(windowInsets);
                    }
                } catch (ReflectiveOperationException e9) {
                    Log.i("WindowInsetsCompat", "Could not get value from WindowInsets.CONSUMED field", e9);
                }
            }
            if (!f6670e) {
                try {
                    f6669d = WindowInsets.class.getConstructor(Rect.class);
                } catch (ReflectiveOperationException e10) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e10);
                }
                f6670e = true;
            }
            Constructor<WindowInsets> constructor = f6669d;
            if (constructor != null) {
                try {
                    return constructor.newInstance(new Rect());
                } catch (ReflectiveOperationException e11) {
                    Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e11);
                }
            }
            return null;
        }

        @Override // o0.b0.d
        public b0 a() {
            return b0.p(this.f6671f);
        }

        @Override // o0.b0.d
        public void c(g0.b bVar) {
            WindowInsets windowInsets = this.f6671f;
            if (windowInsets != null) {
                this.f6671f = windowInsets.replaceSystemWindowInsets(bVar.f4982b, bVar.f4983c, bVar.f4984d, bVar.f4985e);
            }
        }

        public b(b0 b0Var) {
            this.f6671f = b0Var.o();
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class c extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WindowInsets.Builder f6672b;

        public c() {
            this.f6672b = new WindowInsets.Builder();
        }

        @Override // o0.b0.d
        public b0 a() {
            return b0.p(this.f6672b.build());
        }

        @Override // o0.b0.d
        public void b(g0.b bVar) {
            this.f6672b.setStableInsets(bVar.c());
        }

        @Override // o0.b0.d
        public void c(g0.b bVar) {
            this.f6672b.setSystemWindowInsets(bVar.c());
        }

        public c(b0 b0Var) {
            WindowInsets.Builder builder;
            WindowInsets windowInsetsO = b0Var.o();
            if (windowInsetsO != null) {
                builder = new WindowInsets.Builder(windowInsetsO);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.f6672b = builder;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class f extends e {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public g0.b f6676d;

        public f(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
            this.f6676d = null;
        }

        @Override // o0.b0.i
        public b0 b() {
            return b0.p(this.f6674b.consumeStableInsets());
        }

        @Override // o0.b0.i
        public b0 c() {
            return b0.p(this.f6674b.consumeSystemWindowInsets());
        }

        @Override // o0.b0.i
        public final g0.b f() {
            if (this.f6676d == null) {
                this.f6676d = g0.b.a(this.f6674b.getStableInsetLeft(), this.f6674b.getStableInsetTop(), this.f6674b.getStableInsetRight(), this.f6674b.getStableInsetBottom());
            }
            return this.f6676d;
        }

        @Override // o0.b0.i
        public boolean i() {
            return this.f6674b.isConsumed();
        }

        public f(b0 b0Var, f fVar) {
            super(b0Var, fVar);
            this.f6676d = null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class e extends i {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WindowInsets f6674b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public g0.b f6675c;

        public e(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var);
            this.f6675c = null;
            this.f6674b = windowInsets;
        }

        @Override // o0.b0.i
        public final g0.b g() {
            if (this.f6675c == null) {
                this.f6675c = g0.b.a(this.f6674b.getSystemWindowInsetLeft(), this.f6674b.getSystemWindowInsetTop(), this.f6674b.getSystemWindowInsetRight(), this.f6674b.getSystemWindowInsetBottom());
            }
            return this.f6675c;
        }

        @Override // o0.b0.i
        public b0 h(int i8, int i9, int i10, int i11) {
            a aVar = new a(b0.p(this.f6674b));
            aVar.c(b0.l(g(), i8, i9, i10, i11));
            aVar.b(b0.l(f(), i8, i9, i10, i11));
            return aVar.a();
        }

        @Override // o0.b0.i
        public boolean j() {
            return this.f6674b.isRound();
        }

        public e(b0 b0Var, e eVar) {
            this(b0Var, new WindowInsets(eVar.f6674b));
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class h extends g {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public g0.b f6677e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public g0.b f6678f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public g0.b f6679g;

        public h(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
            this.f6677e = null;
            this.f6678f = null;
            this.f6679g = null;
        }

        @Override // o0.b0.i
        public g0.b e() {
            if (this.f6678f == null) {
                this.f6678f = g0.b.b(this.f6674b.getMandatorySystemGestureInsets());
            }
            return this.f6678f;
        }

        @Override // o0.b0.e, o0.b0.i
        public b0 h(int i8, int i9, int i10, int i11) {
            return b0.p(this.f6674b.inset(i8, i9, i10, i11));
        }

        public h(b0 b0Var, h hVar) {
            super(b0Var, hVar);
            this.f6677e = null;
            this.f6678f = null;
            this.f6679g = null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final d f6666a;

        public a() {
            int i8 = Build.VERSION.SDK_INT;
            if (i8 >= 29) {
                this.f6666a = new c();
            } else if (i8 >= 20) {
                this.f6666a = new b();
            } else {
                this.f6666a = new d();
            }
        }

        public b0 a() {
            return this.f6666a.a();
        }

        public a b(g0.b bVar) {
            this.f6666a.b(bVar);
            return this;
        }

        public a c(g0.b bVar) {
            this.f6666a.c(bVar);
            return this;
        }

        public a(b0 b0Var) {
            int i8 = Build.VERSION.SDK_INT;
            if (i8 >= 29) {
                this.f6666a = new c(b0Var);
            } else if (i8 >= 20) {
                this.f6666a = new b(b0Var);
            } else {
                this.f6666a = new d(b0Var);
            }
        }
    }

    public b0(b0 b0Var) {
        if (b0Var != null) {
            i iVar = b0Var.f6665b;
            int i8 = Build.VERSION.SDK_INT;
            if (i8 >= 29 && (iVar instanceof h)) {
                this.f6665b = new h(this, (h) iVar);
                return;
            }
            if (i8 >= 28 && (iVar instanceof g)) {
                this.f6665b = new g(this, (g) iVar);
                return;
            }
            if (i8 >= 21 && (iVar instanceof f)) {
                this.f6665b = new f(this, (f) iVar);
                return;
            } else if (i8 >= 20 && (iVar instanceof e)) {
                this.f6665b = new e(this, (e) iVar);
                return;
            } else {
                this.f6665b = new i(this);
                return;
            }
        }
        this.f6665b = new i(this);
    }
}
