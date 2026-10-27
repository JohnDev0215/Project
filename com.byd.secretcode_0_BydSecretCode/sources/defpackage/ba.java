package defpackage;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.WindowInsets;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Objects;

/* JADX INFO: compiled from: WindowInsetsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class ba {
    public static final ba a = new a().a().a().b().c();
    public final i b;

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class d {
        public final ba a;

        public d() {
            this(new ba((ba) null));
        }

        public ba a() {
            return this.a;
        }

        public void b(o7 o7Var) {
        }

        public void c(o7 o7Var) {
        }

        public d(ba baVar) {
            this.a = baVar;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class g extends f {
        public g(ba baVar, WindowInsets windowInsets) {
            super(baVar, windowInsets);
        }

        @Override // ba.i
        public ba a() {
            return ba.p(this.b.consumeDisplayCutout());
        }

        @Override // ba.i
        public c9 d() {
            return c9.a(this.b.getDisplayCutout());
        }

        @Override // ba.i
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof g) {
                return Objects.equals(this.b, ((g) obj).b);
            }
            return false;
        }

        @Override // ba.i
        public int hashCode() {
            return this.b.hashCode();
        }

        public g(ba baVar, g gVar) {
            super(baVar, gVar);
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class i {
        public final ba a;

        public i(ba baVar) {
            this.a = baVar;
        }

        public ba a() {
            return this.a;
        }

        public ba b() {
            return this.a;
        }

        public ba c() {
            return this.a;
        }

        public c9 d() {
            return null;
        }

        public o7 e() {
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
            return j() == iVar.j() && i() == iVar.i() && u8.a(g(), iVar.g()) && u8.a(f(), iVar.f()) && u8.a(d(), iVar.d());
        }

        public o7 f() {
            return o7.a;
        }

        public o7 g() {
            return o7.a;
        }

        public ba h(int i, int i2, int i3, int i4) {
            return ba.a;
        }

        public int hashCode() {
            return u8.b(Boolean.valueOf(j()), Boolean.valueOf(i()), g(), f(), d());
        }

        public boolean i() {
            return false;
        }

        public boolean j() {
            return false;
        }
    }

    public ba(WindowInsets windowInsets) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            this.b = new h(this, windowInsets);
            return;
        }
        if (i2 >= 28) {
            this.b = new g(this, windowInsets);
            return;
        }
        if (i2 >= 21) {
            this.b = new f(this, windowInsets);
        } else if (i2 >= 20) {
            this.b = new e(this, windowInsets);
        } else {
            this.b = new i(this);
        }
    }

    public static o7 l(o7 o7Var, int i2, int i3, int i4, int i5) {
        int iMax = Math.max(0, o7Var.b - i2);
        int iMax2 = Math.max(0, o7Var.c - i3);
        int iMax3 = Math.max(0, o7Var.d - i4);
        int iMax4 = Math.max(0, o7Var.e - i5);
        return (iMax == i2 && iMax2 == i3 && iMax3 == i4 && iMax4 == i5) ? o7Var : o7.a(iMax, iMax2, iMax3, iMax4);
    }

    public static ba p(WindowInsets windowInsets) {
        return new ba((WindowInsets) z8.c(windowInsets));
    }

    public ba a() {
        return this.b.a();
    }

    public ba b() {
        return this.b.b();
    }

    public ba c() {
        return this.b.c();
    }

    public o7 d() {
        return this.b.e();
    }

    public int e() {
        return i().e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ba) {
            return u8.a(this.b, ((ba) obj).b);
        }
        return false;
    }

    public int f() {
        return i().b;
    }

    public int g() {
        return i().d;
    }

    public int h() {
        return i().c;
    }

    public int hashCode() {
        i iVar = this.b;
        if (iVar == null) {
            return 0;
        }
        return iVar.hashCode();
    }

    public o7 i() {
        return this.b.g();
    }

    public boolean j() {
        return !i().equals(o7.a);
    }

    public ba k(int i2, int i3, int i4, int i5) {
        return this.b.h(i2, i3, i4, i5);
    }

    public boolean m() {
        return this.b.i();
    }

    @Deprecated
    public ba n(int i2, int i3, int i4, int i5) {
        return new a(this).c(o7.a(i2, i3, i4, i5)).a();
    }

    public WindowInsets o() {
        i iVar = this.b;
        if (iVar instanceof e) {
            return ((e) iVar).b;
        }
        return null;
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class b extends d {
        public static Field b = null;
        public static boolean c = false;
        public static Constructor<WindowInsets> d = null;
        public static boolean e = false;
        public WindowInsets f;

        public b() {
            this.f = d();
        }

        public static WindowInsets d() {
            if (!c) {
                try {
                    b = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException e2) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e2);
                }
                c = true;
            }
            Field field = b;
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
                    d = WindowInsets.class.getConstructor(Rect.class);
                } catch (ReflectiveOperationException e4) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e4);
                }
                e = true;
            }
            Constructor<WindowInsets> constructor = d;
            if (constructor != null) {
                try {
                    return constructor.newInstance(new Rect());
                } catch (ReflectiveOperationException e5) {
                    Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e5);
                }
            }
            return null;
        }

        @Override // ba.d
        public ba a() {
            return ba.p(this.f);
        }

        @Override // ba.d
        public void c(o7 o7Var) {
            WindowInsets windowInsets = this.f;
            if (windowInsets != null) {
                this.f = windowInsets.replaceSystemWindowInsets(o7Var.b, o7Var.c, o7Var.d, o7Var.e);
            }
        }

        public b(ba baVar) {
            this.f = baVar.o();
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class c extends d {
        public final WindowInsets.Builder b;

        public c() {
            this.b = new WindowInsets.Builder();
        }

        @Override // ba.d
        public ba a() {
            return ba.p(this.b.build());
        }

        @Override // ba.d
        public void b(o7 o7Var) {
            this.b.setStableInsets(o7Var.c());
        }

        @Override // ba.d
        public void c(o7 o7Var) {
            this.b.setSystemWindowInsets(o7Var.c());
        }

        public c(ba baVar) {
            WindowInsets.Builder builder;
            WindowInsets windowInsetsO = baVar.o();
            if (windowInsetsO != null) {
                builder = new WindowInsets.Builder(windowInsetsO);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.b = builder;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class f extends e {
        public o7 d;

        public f(ba baVar, WindowInsets windowInsets) {
            super(baVar, windowInsets);
            this.d = null;
        }

        @Override // ba.i
        public ba b() {
            return ba.p(this.b.consumeStableInsets());
        }

        @Override // ba.i
        public ba c() {
            return ba.p(this.b.consumeSystemWindowInsets());
        }

        @Override // ba.i
        public final o7 f() {
            if (this.d == null) {
                this.d = o7.a(this.b.getStableInsetLeft(), this.b.getStableInsetTop(), this.b.getStableInsetRight(), this.b.getStableInsetBottom());
            }
            return this.d;
        }

        @Override // ba.i
        public boolean i() {
            return this.b.isConsumed();
        }

        public f(ba baVar, f fVar) {
            super(baVar, fVar);
            this.d = null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class e extends i {
        public final WindowInsets b;
        public o7 c;

        public e(ba baVar, WindowInsets windowInsets) {
            super(baVar);
            this.c = null;
            this.b = windowInsets;
        }

        @Override // ba.i
        public final o7 g() {
            if (this.c == null) {
                this.c = o7.a(this.b.getSystemWindowInsetLeft(), this.b.getSystemWindowInsetTop(), this.b.getSystemWindowInsetRight(), this.b.getSystemWindowInsetBottom());
            }
            return this.c;
        }

        @Override // ba.i
        public ba h(int i, int i2, int i3, int i4) {
            a aVar = new a(ba.p(this.b));
            aVar.c(ba.l(g(), i, i2, i3, i4));
            aVar.b(ba.l(f(), i, i2, i3, i4));
            return aVar.a();
        }

        @Override // ba.i
        public boolean j() {
            return this.b.isRound();
        }

        public e(ba baVar, e eVar) {
            this(baVar, new WindowInsets(eVar.b));
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static class h extends g {
        public o7 e;
        public o7 f;
        public o7 g;

        public h(ba baVar, WindowInsets windowInsets) {
            super(baVar, windowInsets);
            this.e = null;
            this.f = null;
            this.g = null;
        }

        @Override // ba.i
        public o7 e() {
            if (this.f == null) {
                this.f = o7.b(this.b.getMandatorySystemGestureInsets());
            }
            return this.f;
        }

        @Override // ba.e, ba.i
        public ba h(int i, int i2, int i3, int i4) {
            return ba.p(this.b.inset(i, i2, i3, i4));
        }

        public h(ba baVar, h hVar) {
            super(baVar, hVar);
            this.e = null;
            this.f = null;
            this.g = null;
        }
    }

    /* JADX INFO: compiled from: WindowInsetsCompat.java */
    public static final class a {
        public final d a;

        public a() {
            int i = Build.VERSION.SDK_INT;
            if (i >= 29) {
                this.a = new c();
            } else if (i >= 20) {
                this.a = new b();
            } else {
                this.a = new d();
            }
        }

        public ba a() {
            return this.a.a();
        }

        public a b(o7 o7Var) {
            this.a.b(o7Var);
            return this;
        }

        public a c(o7 o7Var) {
            this.a.c(o7Var);
            return this;
        }

        public a(ba baVar) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 29) {
                this.a = new c(baVar);
            } else if (i >= 20) {
                this.a = new b(baVar);
            } else {
                this.a = new d(baVar);
            }
        }
    }

    public ba(ba baVar) {
        if (baVar != null) {
            i iVar = baVar.b;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 29 && (iVar instanceof h)) {
                this.b = new h(this, (h) iVar);
                return;
            }
            if (i2 >= 28 && (iVar instanceof g)) {
                this.b = new g(this, (g) iVar);
                return;
            }
            if (i2 >= 21 && (iVar instanceof f)) {
                this.b = new f(this, (f) iVar);
                return;
            } else if (i2 >= 20 && (iVar instanceof e)) {
                this.b = new e(this, (e) iVar);
                return;
            } else {
                this.b = new i(this);
                return;
            }
        }
        this.b = new i(this);
    }
}
