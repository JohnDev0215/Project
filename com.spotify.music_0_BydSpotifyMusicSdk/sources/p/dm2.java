package p;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class dm2 implements oj1 {
    public si1 c;
    public xi1 e;
    public final /* synthetic */ Toolbar k;

    public dm2(Toolbar toolbar) {
        this.k = toolbar;
    }

    @Override // p.oj1
    public final void b(si1 si1Var, boolean z) {
    }

    @Override // p.oj1
    public final void d() {
        if (this.e != null) {
            si1 si1Var = this.c;
            if (si1Var != null) {
                int size = si1Var.f.size();
                for (int i = 0; i < size; i++) {
                    if (this.c.getItem(i) == this.e) {
                        return;
                    }
                }
            }
            k(this.e);
        }
    }

    @Override // p.oj1
    public final boolean f(qg2 qg2Var) {
        return false;
    }

    @Override // p.oj1
    public final void g(Context context, si1 si1Var) {
        xi1 xi1Var;
        si1 si1Var2 = this.c;
        if (si1Var2 != null && (xi1Var = this.e) != null) {
            si1Var2.d(xi1Var);
        }
        this.c = si1Var;
    }

    @Override // p.oj1
    public final boolean h(xi1 xi1Var) {
        Toolbar toolbar = this.k;
        toolbar.c();
        ViewParent parent = toolbar.f11p.getParent();
        if (parent != toolbar) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(toolbar.f11p);
            }
            toolbar.addView(toolbar.f11p);
        }
        View actionView = xi1Var.getActionView();
        toolbar.q = actionView;
        this.e = xi1Var;
        ViewParent parent2 = actionView.getParent();
        if (parent2 != toolbar) {
            if (parent2 instanceof ViewGroup) {
                ((ViewGroup) parent2).removeView(toolbar.q);
            }
            em2 em2VarH = Toolbar.h();
            em2VarH.a = (toolbar.v & 112) | 8388611;
            em2VarH.b = 2;
            toolbar.q.setLayoutParams(em2VarH);
            toolbar.addView(toolbar.q);
        }
        for (int childCount = toolbar.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = toolbar.getChildAt(childCount);
            if (((em2) childAt.getLayoutParams()).b != 2 && childAt != toolbar.c) {
                toolbar.removeViewAt(childCount);
                toolbar.M.add(childAt);
            }
        }
        toolbar.requestLayout();
        xi1Var.C = true;
        xi1Var.n.p(false);
        KeyEvent.Callback callback = toolbar.q;
        if (callback instanceof hu) {
            ((hu) callback).a();
        }
        toolbar.u();
        return true;
    }

    @Override // p.oj1
    public final boolean i() {
        return false;
    }

    @Override // p.oj1
    public final boolean k(xi1 xi1Var) {
        Toolbar toolbar = this.k;
        KeyEvent.Callback callback = toolbar.q;
        if (callback instanceof hu) {
            ((hu) callback).e();
        }
        toolbar.removeView(toolbar.q);
        toolbar.removeView(toolbar.f11p);
        toolbar.q = null;
        ArrayList arrayList = toolbar.M;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            toolbar.addView((View) arrayList.get(size));
        }
        arrayList.clear();
        this.e = null;
        toolbar.requestLayout();
        xi1Var.C = false;
        xi1Var.n.p(false);
        toolbar.u();
        return true;
    }
}
