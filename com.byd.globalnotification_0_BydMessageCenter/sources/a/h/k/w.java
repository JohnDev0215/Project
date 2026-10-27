package a.h.k;

import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.View;
import android.view.WindowInsets;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class w {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final w f843b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f844a;

    public static class a extends c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final WindowInsets.Builder f845b;

        public a() {
            this.f845b = new WindowInsets.Builder();
        }

        public a(w wVar) {
            WindowInsets windowInsetsF = wVar.f();
            this.f845b = windowInsetsF != null ? new WindowInsets.Builder(windowInsetsF) : new WindowInsets.Builder();
        }

        @Override // a.h.k.w.c
        public w a() {
            w wVarG = w.g(this.f845b.build());
            wVarG.f844a.l(null);
            return wVarG;
        }
    }

    public static class b extends a {
        public b() {
        }

        public b(w wVar) {
            super(wVar);
        }
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final w f846a = new w((w) null);

        public abstract w a();
    }

    public static class d extends i {
        public static boolean g = false;
        public static Method h;
        public static Class<?> i;
        public static Class<?> j;
        public static Field k;
        public static Field l;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final WindowInsets f847c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public a.h.e.b f848d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public w f849e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public a.h.e.b f850f;

        public d(w wVar, WindowInsets windowInsets) {
            super(wVar);
            this.f848d = null;
            this.f847c = windowInsets;
        }

        @Override // a.h.k.w.i
        public void d(View view) {
            if (Build.VERSION.SDK_INT >= 30) {
                throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
            }
            if (!g) {
                try {
                    h = View.class.getDeclaredMethod("getViewRootImpl", new Class[0]);
                    i = Class.forName("android.view.ViewRootImpl");
                    Class<?> cls = Class.forName("android.view.View$AttachInfo");
                    j = cls;
                    k = cls.getDeclaredField("mVisibleInsets");
                    l = i.getDeclaredField("mAttachInfo");
                    k.setAccessible(true);
                    l.setAccessible(true);
                } catch (ReflectiveOperationException e2) {
                    StringBuilder sbD = b.a.a.a.a.d("Failed to get visible insets. (Reflection error). ");
                    sbD.append(e2.getMessage());
                    Log.e("WindowInsetsCompat", sbD.toString(), e2);
                }
                g = true;
            }
            Method method = h;
            a.h.e.b bVarA = null;
            if (method != null && j != null && k != null) {
                try {
                    Object objInvoke = method.invoke(view, new Object[0]);
                    if (objInvoke == null) {
                        Log.w("WindowInsetsCompat", "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", new NullPointerException());
                    } else {
                        Rect rect = (Rect) k.get(l.get(objInvoke));
                        if (rect != null) {
                            bVarA = a.h.e.b.a(rect.left, rect.top, rect.right, rect.bottom);
                        }
                    }
                } catch (ReflectiveOperationException e3) {
                    StringBuilder sbD2 = b.a.a.a.a.d("Failed to get visible insets. (Reflection error). ");
                    sbD2.append(e3.getMessage());
                    Log.e("WindowInsetsCompat", sbD2.toString(), e3);
                }
            }
            if (bVarA == null) {
                bVarA = a.h.e.b.f719e;
            }
            this.f850f = bVarA;
        }

        @Override // a.h.k.w.i
        public boolean equals(Object obj) {
            if (super.equals(obj)) {
                return Objects.equals(this.f850f, ((d) obj).f850f);
            }
            return false;
        }

        @Override // a.h.k.w.i
        public final a.h.e.b h() {
            if (this.f848d == null) {
                this.f848d = a.h.e.b.a(this.f847c.getSystemWindowInsetLeft(), this.f847c.getSystemWindowInsetTop(), this.f847c.getSystemWindowInsetRight(), this.f847c.getSystemWindowInsetBottom());
            }
            return this.f848d;
        }

        @Override // a.h.k.w.i
        public w i(int i2, int i3, int i4, int i5) {
            w wVarG = w.g(this.f847c);
            a bVar = Build.VERSION.SDK_INT >= 30 ? new b(wVarG) : new a(wVarG);
            bVar.f845b.setSystemWindowInsets(w.e(h(), i2, i3, i4, i5).b());
            bVar.f845b.setStableInsets(w.e(g(), i2, i3, i4, i5).b());
            return bVar.a();
        }

        @Override // a.h.k.w.i
        public boolean k() {
            return this.f847c.isRound();
        }

        @Override // a.h.k.w.i
        public void l(a.h.e.b[] bVarArr) {
        }

        @Override // a.h.k.w.i
        public void m(w wVar) {
            this.f849e = wVar;
        }
    }

    public static class e extends d {
        public a.h.e.b m;

        public e(w wVar, WindowInsets windowInsets) {
            super(wVar, windowInsets);
            this.m = null;
        }

        @Override // a.h.k.w.i
        public w b() {
            return w.g(this.f847c.consumeStableInsets());
        }

        @Override // a.h.k.w.i
        public w c() {
            return w.g(this.f847c.consumeSystemWindowInsets());
        }

        @Override // a.h.k.w.i
        public final a.h.e.b g() {
            if (this.m == null) {
                this.m = a.h.e.b.a(this.f847c.getStableInsetLeft(), this.f847c.getStableInsetTop(), this.f847c.getStableInsetRight(), this.f847c.getStableInsetBottom());
            }
            return this.m;
        }

        @Override // a.h.k.w.i
        public boolean j() {
            return this.f847c.isConsumed();
        }
    }

    public static class f extends e {
        public f(w wVar, WindowInsets windowInsets) {
            super(wVar, windowInsets);
        }

        @Override // a.h.k.w.i
        public w a() {
            return w.g(this.f847c.consumeDisplayCutout());
        }

        @Override // a.h.k.w.i
        public a.h.k.c e() {
            DisplayCutout displayCutout = this.f847c.getDisplayCutout();
            if (displayCutout == null) {
                return null;
            }
            return new a.h.k.c(displayCutout);
        }

        @Override // a.h.k.w.d, a.h.k.w.i
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof f)) {
                return false;
            }
            f fVar = (f) obj;
            return Objects.equals(this.f847c, fVar.f847c) && Objects.equals(this.f850f, fVar.f850f);
        }

        @Override // a.h.k.w.i
        public int hashCode() {
            return this.f847c.hashCode();
        }
    }

    public static class g extends f {
        public a.h.e.b n;

        public g(w wVar, WindowInsets windowInsets) {
            super(wVar, windowInsets);
            this.n = null;
        }

        @Override // a.h.k.w.i
        public a.h.e.b f() {
            if (this.n == null) {
                Insets mandatorySystemGestureInsets = this.f847c.getMandatorySystemGestureInsets();
                this.n = a.h.e.b.a(mandatorySystemGestureInsets.left, mandatorySystemGestureInsets.top, mandatorySystemGestureInsets.right, mandatorySystemGestureInsets.bottom);
            }
            return this.n;
        }

        @Override // a.h.k.w.d, a.h.k.w.i
        public w i(int i, int i2, int i3, int i4) {
            return w.g(this.f847c.inset(i, i2, i3, i4));
        }
    }

    public static class h extends g {
        public static final w o = w.g(WindowInsets.CONSUMED);

        public h(w wVar, WindowInsets windowInsets) {
            super(wVar, windowInsets);
        }

        @Override // a.h.k.w.d, a.h.k.w.i
        public final void d(View view) {
        }
    }

    public static class i {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final w f851b;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final w f852a;

        static {
            f851b = (Build.VERSION.SDK_INT >= 30 ? new b() : new a()).a().f844a.a().f844a.b().f844a.c();
        }

        public i(w wVar) {
            this.f852a = wVar;
        }

        public w a() {
            return this.f852a;
        }

        public w b() {
            return this.f852a;
        }

        public w c() {
            return this.f852a;
        }

        public void d(View view) {
        }

        public a.h.k.c e() {
            return null;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof i)) {
                return false;
            }
            i iVar = (i) obj;
            return k() == iVar.k() && j() == iVar.j() && Objects.equals(h(), iVar.h()) && Objects.equals(g(), iVar.g()) && Objects.equals(e(), iVar.e());
        }

        public a.h.e.b f() {
            return h();
        }

        public a.h.e.b g() {
            return a.h.e.b.f719e;
        }

        public a.h.e.b h() {
            return a.h.e.b.f719e;
        }

        public int hashCode() {
            return Objects.hash(Boolean.valueOf(k()), Boolean.valueOf(j()), h(), g(), e());
        }

        public w i(int i, int i2, int i3, int i4) {
            return f851b;
        }

        public boolean j() {
            return false;
        }

        public boolean k() {
            return false;
        }

        public void l(a.h.e.b[] bVarArr) {
        }

        public void m(w wVar) {
        }
    }

    static {
        f843b = Build.VERSION.SDK_INT >= 30 ? h.o : i.f851b;
    }

    public w(w wVar) {
        this.f844a = new i(this);
    }

    public w(WindowInsets windowInsets) {
        this.f844a = Build.VERSION.SDK_INT >= 30 ? new h(this, windowInsets) : new g(this, windowInsets);
    }

    public static a.h.e.b e(a.h.e.b bVar, int i2, int i3, int i4, int i5) {
        int iMax = Math.max(0, bVar.f720a - i2);
        int iMax2 = Math.max(0, bVar.f721b - i3);
        int iMax3 = Math.max(0, bVar.f722c - i4);
        int iMax4 = Math.max(0, bVar.f723d - i5);
        return (iMax == i2 && iMax2 == i3 && iMax3 == i4 && iMax4 == i5) ? bVar : a.h.e.b.a(iMax, iMax2, iMax3, iMax4);
    }

    public static w g(WindowInsets windowInsets) {
        return h(windowInsets, null);
    }

    public static w h(WindowInsets windowInsets, View view) {
        if (windowInsets == null) {
            throw null;
        }
        w wVar = new w(windowInsets);
        if (view != null && view.isAttachedToWindow()) {
            wVar.f844a.m(q.u(view));
            wVar.f844a.d(view.getRootView());
        }
        return wVar;
    }

    @Deprecated
    public int a() {
        return this.f844a.h().f723d;
    }

    @Deprecated
    public int b() {
        return this.f844a.h().f720a;
    }

    @Deprecated
    public int c() {
        return this.f844a.h().f722c;
    }

    @Deprecated
    public int d() {
        return this.f844a.h().f721b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof w) {
            return Objects.equals(this.f844a, ((w) obj).f844a);
        }
        return false;
    }

    public WindowInsets f() {
        i iVar = this.f844a;
        if (iVar instanceof d) {
            return ((d) iVar).f847c;
        }
        return null;
    }

    public int hashCode() {
        i iVar = this.f844a;
        if (iVar == null) {
            return 0;
        }
        return iVar.hashCode();
    }
}
