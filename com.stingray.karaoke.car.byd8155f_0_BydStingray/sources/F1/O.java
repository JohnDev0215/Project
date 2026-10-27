package F1;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public abstract class O extends U {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static boolean f2310f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static Method f2311g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static Class f2312h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static Field f2313i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static Field f2314j;
    public final WindowInsets c;
    public y1.b d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public y1.b f2315e;

    public O(V v10, WindowInsets windowInsets) {
        super(v10);
        this.d = null;
        this.c = windowInsets;
    }

    private y1.b n(View view) {
        if (Build.VERSION.SDK_INT >= 30) {
            throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
        }
        if (!f2310f) {
            o();
        }
        Method method = f2311g;
        if (method != null && f2312h != null && f2313i != null) {
            try {
                Object objInvoke = method.invoke(view, null);
                if (objInvoke == null) {
                    h6.n.u("WindowInsetsCompat", "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", new NullPointerException());
                    return null;
                }
                Rect rect = (Rect) f2313i.get(f2314j.get(objInvoke));
                if (rect != null) {
                    return y1.b.a(rect.left, rect.top, rect.right, rect.bottom);
                }
                return null;
            } catch (ReflectiveOperationException e10) {
                h6.n.i("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e10.getMessage(), e10);
            }
        }
        return null;
    }

    @SuppressLint({"PrivateApi"})
    private static void o() {
        try {
            f2311g = View.class.getDeclaredMethod("getViewRootImpl", null);
            Class<?> cls = Class.forName("android.view.View$AttachInfo");
            f2312h = cls;
            f2313i = cls.getDeclaredField("mVisibleInsets");
            f2314j = Class.forName("android.view.ViewRootImpl").getDeclaredField("mAttachInfo");
            f2313i.setAccessible(true);
            f2314j.setAccessible(true);
        } catch (ReflectiveOperationException e10) {
            h6.n.i("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e10.getMessage(), e10);
        }
        f2310f = true;
    }

    @Override // F1.U
    public void d(View view) {
        y1.b bVarN = n(view);
        if (bVarN == null) {
            bVarN = y1.b.f24112e;
        }
        p(bVarN);
    }

    @Override // F1.U
    public final y1.b h() {
        if (this.d == null) {
            WindowInsets windowInsets = this.c;
            this.d = y1.b.a(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
        }
        return this.d;
    }

    @Override // F1.U
    public boolean k() {
        return this.c.isRound();
    }

    @Override // F1.U
    public void l(y1.b[] bVarArr) {
    }

    @Override // F1.U
    public void m(V v10) {
    }

    public void p(y1.b bVar) {
        this.f2315e = bVar;
    }
}
