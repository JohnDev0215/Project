package p;

import android.annotation.SuppressLint;
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
public abstract class xw2 extends dx2 {
    public static boolean h = false;
    public static Method i;
    public static Class j;
    public static Field k;
    public static Field l;
    public final WindowInsets c;
    public w41[] d;
    public w41 e;
    public fx2 f;
    public w41 g;

    public xw2(fx2 fx2Var, WindowInsets windowInsets) {
        super(fx2Var);
        this.e = null;
        this.c = windowInsets;
    }

    @SuppressLint({"WrongConstant"})
    private w41 r(int i2, boolean z) {
        w41 w41VarA = w41.e;
        for (int i3 = 1; i3 <= 256; i3 <<= 1) {
            if ((i2 & i3) != 0) {
                w41 w41VarS = s(i3, z);
                w41VarA = w41.a(Math.max(w41VarA.a, w41VarS.a), Math.max(w41VarA.b, w41VarS.b), Math.max(w41VarA.c, w41VarS.c), Math.max(w41VarA.d, w41VarS.d));
            }
        }
        return w41VarA;
    }

    private w41 t() {
        fx2 fx2Var = this.f;
        return fx2Var != null ? fx2Var.a.h() : w41.e;
    }

    private w41 u(View view) {
        if (Build.VERSION.SDK_INT >= 30) {
            throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
        }
        if (!h) {
            v();
        }
        Method method = i;
        if (method != null && j != null && k != null) {
            try {
                Object objInvoke = method.invoke(view, new Object[0]);
                if (objInvoke == null) {
                    return null;
                }
                Rect rect = (Rect) k.get(l.get(objInvoke));
                if (rect != null) {
                    return w41.a(rect.left, rect.top, rect.right, rect.bottom);
                }
                return null;
            } catch (ReflectiveOperationException e) {
                Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e.getMessage(), e);
            }
        }
        return null;
    }

    @SuppressLint({"PrivateApi"})
    private static void v() {
        try {
            i = View.class.getDeclaredMethod("getViewRootImpl", new Class[0]);
            Class<?> cls = Class.forName("android.view.View$AttachInfo");
            j = cls;
            k = cls.getDeclaredField("mVisibleInsets");
            l = Class.forName("android.view.ViewRootImpl").getDeclaredField("mAttachInfo");
            k.setAccessible(true);
            l.setAccessible(true);
        } catch (ReflectiveOperationException e) {
            Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e.getMessage(), e);
        }
        h = true;
    }

    @Override // p.dx2
    public void d(View view) {
        w41 w41VarU = u(view);
        if (w41VarU == null) {
            w41VarU = w41.e;
        }
        w(w41VarU);
    }

    @Override // p.dx2
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return Objects.equals(this.g, ((xw2) obj).g);
        }
        return false;
    }

    @Override // p.dx2
    public w41 f(int i2) {
        return r(i2, false);
    }

    @Override // p.dx2
    public final w41 j() {
        if (this.e == null) {
            WindowInsets windowInsets = this.c;
            this.e = w41.a(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
        }
        return this.e;
    }

    @Override // p.dx2
    public fx2 l(int i2, int i3, int i4, int i5) {
        hk0 hk0Var = new hk0(fx2.g(this.c, null));
        ((ww2) hk0Var.e).d(fx2.e(j(), i2, i3, i4, i5));
        ((ww2) hk0Var.e).c(fx2.e(h(), i2, i3, i4, i5));
        return ((ww2) hk0Var.e).b();
    }

    @Override // p.dx2
    public boolean n() {
        return this.c.isRound();
    }

    @Override // p.dx2
    public void o(w41[] w41VarArr) {
        this.d = w41VarArr;
    }

    @Override // p.dx2
    public void p(fx2 fx2Var) {
        this.f = fx2Var;
    }

    public w41 s(int i2, boolean z) {
        w41 w41VarH;
        int i3;
        if (i2 == 1) {
            return z ? w41.a(0, Math.max(t().b, j().b), 0, 0) : w41.a(0, j().b, 0, 0);
        }
        if (i2 == 2) {
            if (z) {
                w41 w41VarT = t();
                w41 w41VarH2 = h();
                return w41.a(Math.max(w41VarT.a, w41VarH2.a), 0, Math.max(w41VarT.c, w41VarH2.c), Math.max(w41VarT.d, w41VarH2.d));
            }
            w41 w41VarJ = j();
            fx2 fx2Var = this.f;
            w41VarH = fx2Var != null ? fx2Var.a.h() : null;
            int iMin = w41VarJ.d;
            if (w41VarH != null) {
                iMin = Math.min(iMin, w41VarH.d);
            }
            return w41.a(w41VarJ.a, 0, w41VarJ.c, iMin);
        }
        w41 w41Var = w41.e;
        if (i2 == 8) {
            w41[] w41VarArr = this.d;
            w41VarH = w41VarArr != null ? w41VarArr[3] : null;
            if (w41VarH != null) {
                return w41VarH;
            }
            w41 w41VarJ2 = j();
            w41 w41VarT2 = t();
            int i4 = w41VarJ2.d;
            if (i4 > w41VarT2.d) {
                return w41.a(0, 0, 0, i4);
            }
            w41 w41Var2 = this.g;
            return (w41Var2 == null || w41Var2.equals(w41Var) || (i3 = this.g.d) <= w41VarT2.d) ? w41Var : w41.a(0, 0, 0, i3);
        }
        if (i2 == 16) {
            return i();
        }
        if (i2 == 32) {
            return g();
        }
        if (i2 == 64) {
            return k();
        }
        if (i2 != 128) {
            return w41Var;
        }
        fx2 fx2Var2 = this.f;
        ka0 ka0VarE = fx2Var2 != null ? fx2Var2.a.e() : e();
        if (ka0VarE == null) {
            return w41Var;
        }
        int i5 = Build.VERSION.SDK_INT;
        DisplayCutout displayCutout = ka0VarE.a;
        return w41.a(i5 >= 28 ? ja0.d(displayCutout) : 0, i5 >= 28 ? ja0.f(displayCutout) : 0, i5 >= 28 ? ja0.e(displayCutout) : 0, i5 >= 28 ? ja0.c(displayCutout) : 0);
    }

    public void w(w41 w41Var) {
        this.g = w41Var;
    }
}
