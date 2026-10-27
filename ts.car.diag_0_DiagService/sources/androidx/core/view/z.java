package androidx.core.view;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.WindowInsets;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Objects;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class z {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static final z f582b = new a().a().a().b().c();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final i f583a;

    /* JADX INFO: compiled from: caradapter-bug */
    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final z f588a;

        d() {
            this(new z((z) null));
        }

        @NonNull
        z a() {
            return this.f588a;
        }

        void b(@NonNull androidx.core.graphics.b bVar) {
        }

        void c(@NonNull androidx.core.graphics.b bVar) {
        }

        d(@NonNull z zVar) {
            this.f588a = zVar;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(28)
    private static class g extends f {
        g(@NonNull z zVar, @NonNull WindowInsets windowInsets) {
            super(zVar, windowInsets);
        }

        @Override // androidx.core.view.z.i
        @NonNull
        z a() {
            return z.m(this.f589b.consumeDisplayCutout());
        }

        @Override // androidx.core.view.z.i
        @Nullable
        androidx.core.view.c d() {
            return androidx.core.view.c.a(this.f589b.getDisplayCutout());
        }

        @Override // androidx.core.view.z.i
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof g) {
                return Objects.equals(this.f589b, ((g) obj).f589b);
            }
            return false;
        }

        @Override // androidx.core.view.z.i
        public int hashCode() {
            return this.f589b.hashCode();
        }

        g(@NonNull z zVar, @NonNull g gVar) {
            super(zVar, gVar);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(29)
    private static class h extends g {
        h(@NonNull z zVar, @NonNull WindowInsets windowInsets) {
            super(zVar, windowInsets);
        }

        @Override // androidx.core.view.z.e, androidx.core.view.z.i
        @NonNull
        z g(int i, int i2, int i3, int i4) {
            return z.m(this.f589b.inset(i, i2, i3, i4));
        }

        h(@NonNull z zVar, @NonNull h hVar) {
            super(zVar, hVar);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private static class i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final z f591a;

        i(@NonNull z zVar) {
            this.f591a = zVar;
        }

        @NonNull
        z a() {
            return this.f591a;
        }

        @NonNull
        z b() {
            return this.f591a;
        }

        @NonNull
        z c() {
            return this.f591a;
        }

        @Nullable
        androidx.core.view.c d() {
            return null;
        }

        @NonNull
        androidx.core.graphics.b e() {
            return androidx.core.graphics.b.e;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof i)) {
                return false;
            }
            i iVar = (i) obj;
            return i() == iVar.i() && h() == iVar.h() && androidx.core.d.a.a(f(), iVar.f()) && androidx.core.d.a.a(e(), iVar.e()) && androidx.core.d.a.a(d(), iVar.d());
        }

        @NonNull
        androidx.core.graphics.b f() {
            return androidx.core.graphics.b.e;
        }

        @NonNull
        z g(int i, int i2, int i3, int i4) {
            return z.f582b;
        }

        boolean h() {
            return false;
        }

        public int hashCode() {
            return androidx.core.d.a.b(Boolean.valueOf(i()), Boolean.valueOf(h()), f(), e(), d());
        }

        boolean i() {
            return false;
        }
    }

    @RequiresApi(20)
    private z(@NonNull WindowInsets windowInsets) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 29) {
            this.f583a = new h(this, windowInsets);
            return;
        }
        if (i2 >= 28) {
            this.f583a = new g(this, windowInsets);
            return;
        }
        if (i2 >= 21) {
            this.f583a = new f(this, windowInsets);
        } else if (i2 >= 20) {
            this.f583a = new e(this, windowInsets);
        } else {
            this.f583a = new i(this);
        }
    }

    static androidx.core.graphics.b j(androidx.core.graphics.b bVar, int i2, int i3, int i4, int i5) {
        int iMax = Math.max(0, bVar.f480a - i2);
        int iMax2 = Math.max(0, bVar.f481b - i3);
        int iMax3 = Math.max(0, bVar.f482c - i4);
        int iMax4 = Math.max(0, bVar.d - i5);
        return (iMax == i2 && iMax2 == i3 && iMax3 == i4 && iMax4 == i5) ? bVar : androidx.core.graphics.b.a(iMax, iMax2, iMax3, iMax4);
    }

    @NonNull
    @RequiresApi(20)
    public static z m(@NonNull WindowInsets windowInsets) {
        androidx.core.d.e.b(windowInsets);
        return new z(windowInsets);
    }

    @NonNull
    public z a() {
        return this.f583a.a();
    }

    @NonNull
    public z b() {
        return this.f583a.b();
    }

    @NonNull
    public z c() {
        return this.f583a.c();
    }

    public int d() {
        return h().d;
    }

    public int e() {
        return h().f480a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof z) {
            return androidx.core.d.a.a(this.f583a, ((z) obj).f583a);
        }
        return false;
    }

    public int f() {
        return h().f482c;
    }

    public int g() {
        return h().f481b;
    }

    @NonNull
    public androidx.core.graphics.b h() {
        return this.f583a.f();
    }

    public int hashCode() {
        i iVar = this.f583a;
        if (iVar == null) {
            return 0;
        }
        return iVar.hashCode();
    }

    @NonNull
    public z i(@IntRange(from = 0) int i2, @IntRange(from = 0) int i3, @IntRange(from = 0) int i4, @IntRange(from = 0) int i5) {
        return this.f583a.g(i2, i3, i4, i5);
    }

    @NonNull
    @Deprecated
    public z k(int i2, int i3, int i4, int i5) {
        a aVar = new a(this);
        aVar.c(androidx.core.graphics.b.a(i2, i3, i4, i5));
        return aVar.a();
    }

    @Nullable
    @RequiresApi(20)
    public WindowInsets l() {
        i iVar = this.f583a;
        if (iVar instanceof e) {
            return ((e) iVar).f589b;
        }
        return null;
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(api = 20)
    private static class b extends d {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static Field f585c = null;
        private static boolean d = false;
        private static Constructor<WindowInsets> e = null;
        private static boolean f = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private WindowInsets f586b;

        b() {
            this.f586b = d();
        }

        @Nullable
        private static WindowInsets d() {
            if (!d) {
                try {
                    f585c = WindowInsets.class.getDeclaredField("CONSUMED");
                } catch (ReflectiveOperationException e2) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e2);
                }
                d = true;
            }
            Field field = f585c;
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
            if (!f) {
                try {
                    e = WindowInsets.class.getConstructor(Rect.class);
                } catch (ReflectiveOperationException e4) {
                    Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e4);
                }
                f = true;
            }
            Constructor<WindowInsets> constructor = e;
            if (constructor != null) {
                try {
                    return constructor.newInstance(new Rect());
                } catch (ReflectiveOperationException e5) {
                    Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e5);
                }
            }
            return null;
        }

        @Override // androidx.core.view.z.d
        @NonNull
        z a() {
            return z.m(this.f586b);
        }

        @Override // androidx.core.view.z.d
        void c(@NonNull androidx.core.graphics.b bVar) {
            WindowInsets windowInsets = this.f586b;
            if (windowInsets != null) {
                this.f586b = windowInsets.replaceSystemWindowInsets(bVar.f480a, bVar.f481b, bVar.f482c, bVar.d);
            }
        }

        b(@NonNull z zVar) {
            this.f586b = zVar.l();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(api = 29)
    private static class c extends d {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final WindowInsets.Builder f587b;

        c() {
            this.f587b = new WindowInsets.Builder();
        }

        @Override // androidx.core.view.z.d
        @NonNull
        z a() {
            return z.m(this.f587b.build());
        }

        @Override // androidx.core.view.z.d
        void b(@NonNull androidx.core.graphics.b bVar) {
            this.f587b.setStableInsets(bVar.b());
        }

        @Override // androidx.core.view.z.d
        void c(@NonNull androidx.core.graphics.b bVar) {
            this.f587b.setSystemWindowInsets(bVar.b());
        }

        c(@NonNull z zVar) {
            WindowInsets.Builder builder;
            WindowInsets windowInsetsL = zVar.l();
            if (windowInsetsL != null) {
                builder = new WindowInsets.Builder(windowInsetsL);
            } else {
                builder = new WindowInsets.Builder();
            }
            this.f587b = builder;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(21)
    private static class f extends e {
        private androidx.core.graphics.b d;

        f(@NonNull z zVar, @NonNull WindowInsets windowInsets) {
            super(zVar, windowInsets);
            this.d = null;
        }

        @Override // androidx.core.view.z.i
        @NonNull
        z b() {
            return z.m(this.f589b.consumeStableInsets());
        }

        @Override // androidx.core.view.z.i
        @NonNull
        z c() {
            return z.m(this.f589b.consumeSystemWindowInsets());
        }

        @Override // androidx.core.view.z.i
        @NonNull
        final androidx.core.graphics.b e() {
            if (this.d == null) {
                this.d = androidx.core.graphics.b.a(this.f589b.getStableInsetLeft(), this.f589b.getStableInsetTop(), this.f589b.getStableInsetRight(), this.f589b.getStableInsetBottom());
            }
            return this.d;
        }

        @Override // androidx.core.view.z.i
        boolean h() {
            return this.f589b.isConsumed();
        }

        f(@NonNull z zVar, @NonNull f fVar) {
            super(zVar, fVar);
            this.d = null;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @RequiresApi(20)
    private static class e extends i {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @NonNull
        final WindowInsets f589b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private androidx.core.graphics.b f590c;

        e(@NonNull z zVar, @NonNull WindowInsets windowInsets) {
            super(zVar);
            this.f590c = null;
            this.f589b = windowInsets;
        }

        @Override // androidx.core.view.z.i
        @NonNull
        final androidx.core.graphics.b f() {
            if (this.f590c == null) {
                this.f590c = androidx.core.graphics.b.a(this.f589b.getSystemWindowInsetLeft(), this.f589b.getSystemWindowInsetTop(), this.f589b.getSystemWindowInsetRight(), this.f589b.getSystemWindowInsetBottom());
            }
            return this.f590c;
        }

        @Override // androidx.core.view.z.i
        @NonNull
        z g(int i, int i2, int i3, int i4) {
            a aVar = new a(z.m(this.f589b));
            aVar.c(z.j(f(), i, i2, i3, i4));
            aVar.b(z.j(e(), i, i2, i3, i4));
            return aVar.a();
        }

        @Override // androidx.core.view.z.i
        boolean i() {
            return this.f589b.isRound();
        }

        e(@NonNull z zVar, @NonNull e eVar) {
            this(zVar, new WindowInsets(eVar.f589b));
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final d f584a;

        public a() {
            int i = Build.VERSION.SDK_INT;
            if (i >= 29) {
                this.f584a = new c();
            } else if (i >= 20) {
                this.f584a = new b();
            } else {
                this.f584a = new d();
            }
        }

        @NonNull
        public z a() {
            return this.f584a.a();
        }

        @NonNull
        public a b(@NonNull androidx.core.graphics.b bVar) {
            this.f584a.b(bVar);
            return this;
        }

        @NonNull
        public a c(@NonNull androidx.core.graphics.b bVar) {
            this.f584a.c(bVar);
            return this;
        }

        public a(@NonNull z zVar) {
            int i = Build.VERSION.SDK_INT;
            if (i >= 29) {
                this.f584a = new c(zVar);
            } else if (i >= 20) {
                this.f584a = new b(zVar);
            } else {
                this.f584a = new d(zVar);
            }
        }
    }

    public z(@Nullable z zVar) {
        int i2 = Build.VERSION.SDK_INT;
        if (zVar != null) {
            i iVar = zVar.f583a;
            if (i2 >= 29 && (iVar instanceof h)) {
                this.f583a = new h(this, (h) iVar);
                return;
            }
            if (i2 >= 28 && (iVar instanceof g)) {
                this.f583a = new g(this, (g) iVar);
                return;
            }
            if (i2 >= 21 && (iVar instanceof f)) {
                this.f583a = new f(this, (f) iVar);
                return;
            } else if (i2 >= 20 && (iVar instanceof e)) {
                this.f583a = new e(this, (e) iVar);
                return;
            } else {
                this.f583a = new i(this);
                return;
            }
        }
        this.f583a = new i(this);
    }
}
