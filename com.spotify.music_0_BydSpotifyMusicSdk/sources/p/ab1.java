package p;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import androidx.appcompat.view.menu.ExpandedMenuView;

/* JADX INFO: loaded from: classes.dex */
public final class ab1 implements oj1, AdapterView.OnItemClickListener {
    public Context c;
    public LayoutInflater e;
    public si1 k;
    public ExpandedMenuView l;
    public nj1 m;
    public za1 n;

    public ab1(Context context) {
        this.c = context;
        this.e = LayoutInflater.from(context);
    }

    @Override // p.oj1
    public final void b(si1 si1Var, boolean z) {
        nj1 nj1Var = this.m;
        if (nj1Var != null) {
            nj1Var.b(si1Var, z);
        }
    }

    @Override // p.oj1
    public final void d() {
        za1 za1Var = this.n;
        if (za1Var != null) {
            za1Var.notifyDataSetChanged();
        }
    }

    @Override // p.oj1
    public final boolean f(qg2 qg2Var) {
        if (!qg2Var.hasVisibleItems()) {
            return false;
        }
        ti1 ti1Var = new ti1();
        ti1Var.c = qg2Var;
        Context context = qg2Var.a;
        j6 j6Var = new j6(context);
        g6 g6Var = (g6) j6Var.e;
        ab1 ab1Var = new ab1(g6Var.a);
        ti1Var.k = ab1Var;
        ab1Var.m = ti1Var;
        qg2Var.b(ab1Var, context);
        ab1 ab1Var2 = ti1Var.k;
        if (ab1Var2.n == null) {
            ab1Var2.n = new za1(ab1Var2);
        }
        g6Var.g = ab1Var2.n;
        g6Var.h = ti1Var;
        View view = qg2Var.o;
        if (view != null) {
            g6Var.e = view;
        } else {
            g6Var.c = qg2Var.n;
            ((g6) j6Var.e).d = qg2Var.m;
        }
        g6Var.f = ti1Var;
        k6 k6VarD = j6Var.d();
        ti1Var.e = k6VarD;
        k6VarD.setOnDismissListener(ti1Var);
        WindowManager.LayoutParams attributes = ti1Var.e.getWindow().getAttributes();
        attributes.type = 1003;
        attributes.flags |= 131072;
        ti1Var.e.show();
        nj1 nj1Var = this.m;
        if (nj1Var == null) {
            return true;
        }
        nj1Var.s(qg2Var);
        return true;
    }

    @Override // p.oj1
    public final void g(Context context, si1 si1Var) {
        if (this.c != null) {
            this.c = context;
            if (this.e == null) {
                this.e = LayoutInflater.from(context);
            }
        }
        this.k = si1Var;
        za1 za1Var = this.n;
        if (za1Var != null) {
            za1Var.notifyDataSetChanged();
        }
    }

    @Override // p.oj1
    public final boolean h(xi1 xi1Var) {
        return false;
    }

    @Override // p.oj1
    public final boolean i() {
        return false;
    }

    @Override // p.oj1
    public final void j(nj1 nj1Var) {
        this.m = nj1Var;
    }

    @Override // p.oj1
    public final boolean k(xi1 xi1Var) {
        return false;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.k.q(this.n.getItem(i), this, 0);
    }
}
