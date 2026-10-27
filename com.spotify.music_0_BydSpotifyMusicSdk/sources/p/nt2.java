package p;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class nt2 implements View.OnApplyWindowInsetsListener {
    public fx2 a = null;
    public final /* synthetic */ View b;
    public final /* synthetic */ kp1 c;

    public nt2(View view, kp1 kp1Var) {
        this.b = view;
        this.c = kp1Var;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        fx2 fx2VarG = fx2.g(windowInsets, view);
        int i = Build.VERSION.SDK_INT;
        kp1 kp1Var = this.c;
        if (i < 30) {
            ot2.a(windowInsets, this.b);
            if (fx2VarG.equals(this.a)) {
                return kp1Var.i(view, fx2VarG).f();
            }
        }
        this.a = fx2VarG;
        fx2 fx2VarI = kp1Var.i(view, fx2VarG);
        if (i >= 30) {
            return fx2VarI.f();
        }
        WeakHashMap weakHashMap = zt2.a;
        mt2.c(view);
        return fx2VarI.f();
    }
}
