package p;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class v12 {
    public p12 a;
    public ArrayList b;
    public long c;
    public long d;
    public long e;
    public long f;

    public static void b(n22 n22Var) {
        RecyclerView recyclerView;
        int i = n22Var.j;
        if (n22Var.h() || (i & 4) != 0 || (recyclerView = n22Var.r) == null) {
            return;
        }
        recyclerView.F(n22Var);
    }

    public abstract boolean a(n22 n22Var, n22 n22Var2, u12 u12Var, u12 u12Var2);

    public final void c(n22 n22Var) {
        p12 p12Var = this.a;
        if (p12Var != null) {
            boolean z = true;
            n22Var.p(true);
            if (n22Var.h != null && n22Var.i == null) {
                n22Var.h = null;
            }
            n22Var.i = null;
            if ((n22Var.j & 16) != 0) {
                return;
            }
            RecyclerView recyclerView = p12Var.a;
            recyclerView.c0();
            is isVar = recyclerView.m;
            p12 p12Var2 = isVar.a;
            RecyclerView recyclerView2 = p12Var2.a;
            View view = n22Var.a;
            int iIndexOfChild = recyclerView2.indexOfChild(view);
            if (iIndexOfChild == -1) {
                isVar.k(view);
            } else {
                hs hsVar = isVar.b;
                if (hsVar.d(iIndexOfChild)) {
                    hsVar.f(iIndexOfChild);
                    isVar.k(view);
                    p12Var2.h(iIndexOfChild);
                } else {
                    z = false;
                }
            }
            if (z) {
                n22 n22VarI = RecyclerView.I(view);
                f22 f22Var = recyclerView.e;
                f22Var.j(n22VarI);
                f22Var.g(n22VarI);
            }
            recyclerView.d0(!z);
            if (z || !n22Var.l()) {
                return;
            }
            recyclerView.removeDetachedView(view, false);
        }
    }

    public abstract void d(n22 n22Var);

    public abstract void e();

    public abstract boolean f();
}
