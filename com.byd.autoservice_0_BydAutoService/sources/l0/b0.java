package l0;

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
    public static final b0 f3616a = new a().a().a().b().c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i f3617b;

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b0 f3625a;

        public d() {
            this(new b0((b0) null));
        }

        public b0 a() {
            return this.f3625a;
        }

        public void b(d0.b bVar) {
        }

        public void c(d0.b bVar) {
        }

        public d(b0 b0Var) {
            this.f3625a = b0Var;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class g extends f {
        public g(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
        }

        @Override // l0.b0.i
        public b0 a() {
            return b0.p(this.f3626b.consumeDisplayCutout());
        }

        @Override // l0.b0.i
        public l0.c d() {
            return l0.c.a(this.f3626b.getDisplayCutout());
        }

        @Override // l0.b0.i
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof g) {
                return Objects.equals(this.f3626b, ((g) obj).f3626b);
            }
            return false;
        }

        @Override // l0.b0.i
        public int hashCode() {
            return this.f3626b.hashCode();
        }

        public g(b0 b0Var, g gVar) {
            super(b0Var, gVar);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final b0 f3632a;

        public i(b0 b0Var) {
            this.f3632a = b0Var;
        }

        public b0 a() {
            return this.f3632a;
        }

        public b0 b() {
            return this.f3632a;
        }

        public b0 c() {
            return this.f3632a;
        }

        public l0.c d() {
            return null;
        }

        public d0.b e() {
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
            return j() == iVar.j() && i() == iVar.i() && k0.c.a(g(), iVar.g()) && k0.c.a(f(), iVar.f()) && k0.c.a(d(), iVar.d());
        }

        public d0.b f() {
            return d0.b.f2647a;
        }

        public d0.b g() {
            return d0.b.f2647a;
        }

        public b0 h(int i3, int i4, int i5, int i6) {
            return b0.f3616a;
        }

        public int hashCode() {
            return k0.c.b(Boolean.valueOf(j()), Boolean.valueOf(i()), g(), f(), d());
        }

        public boolean i() {
            return false;
        }

        public boolean j() {
            return false;
        }
    }

    public b0(WindowInsets windowInsets) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 29) {
            this.f3617b = new h(this, windowInsets);
            return;
        }
        if (i3 >= 28) {
            this.f3617b = new g(this, windowInsets);
            return;
        }
        if (i3 >= 21) {
            this.f3617b = new f(this, windowInsets);
        } else if (i3 >= 20) {
            this.f3617b = new e(this, windowInsets);
        } else {
            this.f3617b = new i(this);
        }
    }

    public static d0.b l(d0.b bVar, int i3, int i4, int i5, int i6) {
        int iMax = Math.max(0, bVar.f2648b - i3);
        int iMax2 = Math.max(0, bVar.f2649c - i4);
        int iMax3 = Math.max(0, bVar.f2650d - i5);
        int iMax4 = Math.max(0, bVar.f2651e - i6);
        return (iMax == i3 && iMax2 == i4 && iMax3 == i5 && iMax4 == i6) ? bVar : d0.b.a(iMax, iMax2, iMax3, iMax4);
    }

    public static b0 p(WindowInsets windowInsets) {
        return new b0((WindowInsets) k0.h.c(windowInsets));
    }

    public b0 a() {
        return this.f3617b.a();
    }

    public b0 b() {
        return this.f3617b.b();
    }

    public b0 c() {
        return this.f3617b.c();
    }

    public d0.b d() {
        return this.f3617b.e();
    }

    public int e() {
        return i().f2651e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof b0) {
            return k0.c.a(this.f3617b, ((b0) obj).f3617b);
        }
        return false;
    }

    public int f() {
        return i().f2648b;
    }

    public int g() {
        return i().f2650d;
    }

    public int h() {
        return i().f2649c;
    }

    public int hashCode() {
        i iVar = this.f3617b;
        if (iVar == null) {
            return 0;
        }
        return iVar.hashCode();
    }

    public d0.b i() {
        return this.f3617b.g();
    }

    public boolean j() {
        return !i().equals(d0.b.f2647a);
    }

    public b0 k(int i3, int i4, int i5, int i6) {
        return this.f3617b.h(i3, i4, i5, i6);
    }

    public boolean m() {
        return this.f3617b.i();
    }

    @Deprecated
    public b0 n(int i3, int i4, int i5, int i6) {
        return new a(this).c(d0.b.a(i3, i4, i5, i6)).a();
    }

    public WindowInsets o() {
        i iVar = this.f3617b;
        if (iVar instanceof e) {
            return ((e) iVar).f3626b;
        }
        return null;
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class b extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static Field f3619b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static boolean f3620c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static Constructor<WindowInsets> f3621d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static boolean f3622e = false;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public WindowInsets f3623f;

        public b() {
            this.f3623f = d();
        }

        public static WindowInsets d() {
            if (!f3620c) {
                try {
                    f3619b = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException e3) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e3);
                }
                f3620c = true;
            }
            Field field = f3619b;
            if (field != null) {
                try {
                    WindowInsets windowInsets = (WindowInsets) field.get(null);
                    if (windowInsets != null) {
                        return new WindowInsets(windowInsets);
                    }
                } catch (ReflectiveOperationException e4) {
                    Log.i("WindowInsetsCompat", "Could not get value from WindowInsets.CONSUMED field", e4);
                }
            }
            if (!f3622e) {
                try {
                    f3621d = WindowInsets.class.getConstructor(Rect.class);
                } catch (ReflectiveOperationException e5) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e5);
                }
                f3622e = true;
            }
            Constructor<WindowInsets> constructor = f3621d;
            if (constructor != null) {
                try {
                    return constructor.newInstance(new Rect());
                } catch (ReflectiveOperationException e6) {
                    Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e6);
                }
            }
            return null;
        }

        @Override // l0.b0.d
        public b0 a() {
            return b0.p(this.f3623f);
        }

        @Override // l0.b0.d
        public void c(d0.b bVar) {
            WindowInsets windowInsets = this.f3623f;
            if (windowInsets != null) {
                this.f3623f = windowInsets.replaceSystemWindowInsets(bVar.f2648b, bVar.f2649c, bVar.f2650d, bVar.f2651e);
            }
        }

        public b(b0 b0Var) {
            this.f3623f = b0Var.o();
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class c extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WindowInsets.Builder f3624b;

        public c() {
            this.f3624b = new WindowInsets.Builder();
        }

        @Override // l0.b0.d
        public b0 a() {
            return b0.p(this.f3624b.build());
        }

        @Override // l0.b0.d
        public void b(d0.b bVar) {
            this.f3624b.setStableInsets(bVar.c());
        }

        @Override // l0.b0.d
        public void c(d0.b bVar) {
            this.f3624b.setSystemWindowInsets(bVar.c());
        }

        public c(b0 b0Var) {
            WindowInsets.Builder builder;
            WindowInsets windowInsetsO = b0Var.o();
            if (windowInsetsO != null) {
                builder = new WindowInsets.Builder(windowInsetsO);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.f3624b = builder;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class f extends e {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public d0.b f3628d;

        public f(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
            this.f3628d = null;
        }

        @Override // l0.b0.i
        public b0 b() {
            return b0.p(this.f3626b.consumeStableInsets());
        }

        @Override // l0.b0.i
        public b0 c() {
            return b0.p(this.f3626b.consumeSystemWindowInsets());
        }

        @Override // l0.b0.i
        public final d0.b f() {
            if (this.f3628d == null) {
                this.f3628d = d0.b.a(this.f3626b.getStableInsetLeft(), this.f3626b.getStableInsetTop(), this.f3626b.getStableInsetRight(), this.f3626b.getStableInsetBottom());
            }
            return this.f3628d;
        }

        @Override // l0.b0.i
        public boolean i() {
            return this.f3626b.isConsumed();
        }

        public f(b0 b0Var, f fVar) {
            super(b0Var, fVar);
            this.f3628d = null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class e extends i {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WindowInsets f3626b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public d0.b f3627c;

        public e(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var);
            this.f3627c = null;
            this.f3626b = windowInsets;
        }

        @Override // l0.b0.i
        public final d0.b g() {
            if (this.f3627c == null) {
                this.f3627c = d0.b.a(this.f3626b.getSystemWindowInsetLeft(), this.f3626b.getSystemWindowInsetTop(), this.f3626b.getSystemWindowInsetRight(), this.f3626b.getSystemWindowInsetBottom());
            }
            return this.f3627c;
        }

        @Override // l0.b0.i
        public b0 h(int i3, int i4, int i5, int i6) {
            a aVar = new a(b0.p(this.f3626b));
            aVar.c(b0.l(g(), i3, i4, i5, i6));
            aVar.b(b0.l(f(), i3, i4, i5, i6));
            return aVar.a();
        }

        @Override // l0.b0.i
        public boolean j() {
            return this.f3626b.isRound();
        }

        public e(b0 b0Var, e eVar) {
            this(b0Var, new WindowInsets(eVar.f3626b));
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class h extends g {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public d0.b f3629e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public d0.b f3630f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public d0.b f3631g;

        public h(b0 b0Var, WindowInsets windowInsets) {
            super(b0Var, windowInsets);
            this.f3629e = null;
            this.f3630f = null;
            this.f3631g = null;
        }

        @Override // l0.b0.i
        public d0.b e() {
            if (this.f3630f == null) {
                this.f3630f = d0.b.b(this.f3626b.getMandatorySystemGestureInsets());
            }
            return this.f3630f;
        }

        @Override // l0.b0.e, l0.b0.i
        public b0 h(int i3, int i4, int i5, int i6) {
            return b0.p(this.f3626b.inset(i3, i4, i5, i6));
        }

        public h(b0 b0Var, h hVar) {
            super(b0Var, hVar);
            this.f3629e = null;
            this.f3630f = null;
            this.f3631g = null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final d f3618a;

        public a() {
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 29) {
                this.f3618a = new c();
            } else if (i3 >= 20) {
                this.f3618a = new b();
            } else {
                this.f3618a = new d();
            }
        }

        public b0 a() {
            return this.f3618a.a();
        }

        public a b(d0.b bVar) {
            this.f3618a.b(bVar);
            return this;
        }

        public a c(d0.b bVar) {
            this.f3618a.c(bVar);
            return this;
        }

        public a(b0 b0Var) {
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 29) {
                this.f3618a = new c(b0Var);
            } else if (i3 >= 20) {
                this.f3618a = new b(b0Var);
            } else {
                this.f3618a = new d(b0Var);
            }
        }
    }

    public b0(b0 b0Var) {
        if (b0Var != null) {
            i iVar = b0Var.f3617b;
            int i3 = Build.VERSION.SDK_INT;
            if (i3 >= 29 && (iVar instanceof h)) {
                this.f3617b = new h(this, (h) iVar);
                return;
            }
            if (i3 >= 28 && (iVar instanceof g)) {
                this.f3617b = new g(this, (g) iVar);
                return;
            }
            if (i3 >= 21 && (iVar instanceof f)) {
                this.f3617b = new f(this, (f) iVar);
                return;
            } else if (i3 >= 20 && (iVar instanceof e)) {
                this.f3617b = new e(this, (e) iVar);
                return;
            } else {
                this.f3617b = new i(this);
                return;
            }
        }
        this.f3617b = new i(this);
    }
}
