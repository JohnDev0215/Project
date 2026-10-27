package p;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class fx2 {
    public static final fx2 b;
    public final dx2 a;

    static {
        if (Build.VERSION.SDK_INT >= 30) {
            b = cx2.q;
        } else {
            b = dx2.b;
        }
    }

    public fx2(WindowInsets windowInsets) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 30) {
            this.a = new cx2(this, windowInsets);
            return;
        }
        if (i >= 29) {
            this.a = new bx2(this, windowInsets);
        } else if (i >= 28) {
            this.a = new ax2(this, windowInsets);
        } else {
            this.a = new yw2(this, windowInsets);
        }
    }

    public static w41 e(w41 w41Var, int i, int i2, int i3, int i4) {
        int iMax = Math.max(0, w41Var.a - i);
        int iMax2 = Math.max(0, w41Var.b - i2);
        int iMax3 = Math.max(0, w41Var.c - i3);
        int iMax4 = Math.max(0, w41Var.d - i4);
        return (iMax == i && iMax2 == i2 && iMax3 == i3 && iMax4 == i4) ? w41Var : w41.a(iMax, iMax2, iMax3, iMax4);
    }

    public static fx2 g(WindowInsets windowInsets, View view) {
        windowInsets.getClass();
        fx2 fx2Var = new fx2(windowInsets);
        if (view != null) {
            WeakHashMap weakHashMap = zt2.a;
            if (lt2.b(view)) {
                fx2 fx2VarA = pt2.a(view);
                dx2 dx2Var = fx2Var.a;
                dx2Var.p(fx2VarA);
                dx2Var.d(view.getRootView());
            }
        }
        return fx2Var;
    }

    public final int a() {
        return this.a.j().d;
    }

    public final int b() {
        return this.a.j().a;
    }

    public final int c() {
        return this.a.j().c;
    }

    public final int d() {
        return this.a.j().b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof fx2)) {
            return false;
        }
        return ap1.a(this.a, ((fx2) obj).a);
    }

    public final WindowInsets f() {
        dx2 dx2Var = this.a;
        if (dx2Var instanceof xw2) {
            return ((xw2) dx2Var).c;
        }
        return null;
    }

    public final int hashCode() {
        dx2 dx2Var = this.a;
        if (dx2Var == null) {
            return 0;
        }
        return dx2Var.hashCode();
    }

    public fx2() {
        this.a = new dx2(this);
    }
}
