package p;

import android.content.Context;
import android.os.Build;
import android.view.MenuItem;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class mj1 extends ib1 implements wi1 {
    public static final Method K;
    public wi1 J;

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                K = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
        }
    }

    @Override // p.wi1
    public final void n(si1 si1Var, MenuItem menuItem) {
        wi1 wi1Var = this.J;
        if (wi1Var != null) {
            wi1Var.n(si1Var, menuItem);
        }
    }

    @Override // p.wi1
    public final void o(si1 si1Var, xi1 xi1Var) {
        wi1 wi1Var = this.J;
        if (wi1Var != null) {
            wi1Var.o(si1Var, xi1Var);
        }
    }

    @Override // p.ib1
    public final ib0 q(Context context, boolean z) {
        lj1 lj1Var = new lj1(context, z);
        lj1Var.setHoverListener(this);
        return lj1Var;
    }
}
