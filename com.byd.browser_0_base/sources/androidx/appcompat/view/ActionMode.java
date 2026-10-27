package androidx.appcompat.view;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.menu.MenuBuilder;

/* JADX INFO: loaded from: classes.dex */
public abstract class ActionMode {
    public Object e;
    public boolean f;

    public interface Callback {
        void a(ActionMode actionMode);

        boolean b(ActionMode actionMode, MenuBuilder menuBuilder);

        boolean c(ActionMode actionMode, MenuItem menuItem);

        boolean d(ActionMode actionMode, MenuBuilder menuBuilder);
    }

    public abstract void c();

    public abstract View d();

    public abstract MenuBuilder e();

    public abstract MenuInflater f();

    public abstract CharSequence g();

    public abstract CharSequence h();

    public abstract void i();

    public boolean j() {
        return false;
    }

    public abstract void k(View view);

    public abstract void l(int i);

    public abstract void m(CharSequence charSequence);

    public abstract void n(int i);

    public abstract void o(CharSequence charSequence);

    public void p(boolean z) {
        this.f = z;
    }
}
