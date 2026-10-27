package defpackage;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/* JADX INFO: compiled from: ActionMode.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b1 {
    public Object b;
    public boolean c;

    /* JADX INFO: compiled from: ActionMode.java */
    public interface a {
        boolean a(b1 b1Var, Menu menu);

        boolean b(b1 b1Var, MenuItem menuItem);

        boolean c(b1 b1Var, Menu menu);

        void d(b1 b1Var);
    }

    public abstract void c();

    public abstract View d();

    public abstract Menu e();

    public abstract MenuInflater f();

    public abstract CharSequence g();

    public Object h() {
        return this.b;
    }

    public abstract CharSequence i();

    public boolean j() {
        return this.c;
    }

    public abstract void k();

    public abstract boolean l();

    public abstract void m(View view);

    public abstract void n(int i);

    public abstract void o(CharSequence charSequence);

    public void p(Object obj) {
        this.b = obj;
    }

    public abstract void q(int i);

    public abstract void r(CharSequence charSequence);

    public void s(boolean z) {
        this.c = z;
    }
}
