package p;

import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class g8 implements kp1, nj1 {
    public final /* synthetic */ s8 c;

    public /* synthetic */ g8(s8 s8Var) {
        this.c = s8Var;
    }

    @Override // p.nj1
    public final void b(si1 si1Var, boolean z) {
        this.c.p(si1Var);
    }

    @Override // p.kp1
    public final fx2 i(View view, fx2 fx2Var) {
        int iD = fx2Var.d();
        int iH = this.c.H(fx2Var, null);
        if (iD != iH) {
            int iB = fx2Var.b();
            int iC = fx2Var.c();
            int iA = fx2Var.a();
            hk0 hk0Var = new hk0(fx2Var);
            ((ww2) hk0Var.e).d(w41.a(iB, iH, iC, iA));
            fx2Var = ((ww2) hk0Var.e).b();
        }
        WeakHashMap weakHashMap = zt2.a;
        WindowInsets windowInsetsF = fx2Var.f();
        if (windowInsetsF == null) {
            return fx2Var;
        }
        WindowInsets windowInsetsB = mt2.b(view, windowInsetsF);
        return !windowInsetsB.equals(windowInsetsF) ? fx2.g(windowInsetsB, view) : fx2Var;
    }

    @Override // p.nj1
    public final boolean s(si1 si1Var) {
        Window.Callback callback = this.c.t.getCallback();
        if (callback == null) {
            return true;
        }
        callback.onMenuOpened(108, si1Var);
        return true;
    }
}
