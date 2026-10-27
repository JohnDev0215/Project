package p;

import android.graphics.Rect;
import android.view.WindowInsets;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class sw2 extends ww2 {
    public static Field c = null;
    public static boolean d = false;
    public static Constructor e = null;
    public static boolean f = false;
    public WindowInsets a;
    public w41 b;

    public sw2() {
        this.a = e();
    }

    private static WindowInsets e() {
        if (!d) {
            try {
                c = WindowInsets.class.getDeclaredField("CONSUMED");
            } catch (ReflectiveOperationException unused) {
            }
            d = true;
        }
        Field field = c;
        if (field != null) {
            try {
                WindowInsets windowInsets = (WindowInsets) field.get(null);
                if (windowInsets != null) {
                    return new WindowInsets(windowInsets);
                }
            } catch (ReflectiveOperationException unused2) {
            }
        }
        if (!f) {
            try {
                e = WindowInsets.class.getConstructor(Rect.class);
            } catch (ReflectiveOperationException unused3) {
            }
            f = true;
        }
        Constructor constructor = e;
        if (constructor != null) {
            try {
                return (WindowInsets) constructor.newInstance(new Rect());
            } catch (ReflectiveOperationException unused4) {
            }
        }
        return null;
    }

    @Override // p.ww2
    public fx2 b() {
        a();
        fx2 fx2VarG = fx2.g(this.a, null);
        dx2 dx2Var = fx2VarG.a;
        dx2Var.o(null);
        dx2Var.q(this.b);
        return fx2VarG;
    }

    @Override // p.ww2
    public void c(w41 w41Var) {
        this.b = w41Var;
    }

    @Override // p.ww2
    public void d(w41 w41Var) {
        WindowInsets windowInsets = this.a;
        if (windowInsets != null) {
            this.a = windowInsets.replaceSystemWindowInsets(w41Var.a, w41Var.b, w41Var.c, w41Var.d);
        }
    }

    public sw2(fx2 fx2Var) {
        super(fx2Var);
        this.a = fx2Var.f();
    }
}
