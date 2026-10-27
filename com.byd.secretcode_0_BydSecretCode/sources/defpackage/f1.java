package defpackage;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import defpackage.b1;
import java.util.ArrayList;

/* JADX INFO: compiled from: SupportActionModeWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class f1 extends ActionMode {
    public final Context a;
    public final b1 b;

    /* JADX INFO: compiled from: SupportActionModeWrapper.java */
    public static class a implements b1.a {
        public final ActionMode.Callback a;
        public final Context b;
        public final ArrayList<f1> c = new ArrayList<>();
        public final g4<Menu, Menu> d = new g4<>();

        public a(Context context, ActionMode.Callback callback) {
            this.b = context;
            this.a = callback;
        }

        @Override // b1.a
        public boolean a(b1 b1Var, Menu menu) {
            return this.a.onPrepareActionMode(e(b1Var), f(menu));
        }

        @Override // b1.a
        public boolean b(b1 b1Var, MenuItem menuItem) {
            return this.a.onActionItemClicked(e(b1Var), new s1(this.b, (f8) menuItem));
        }

        @Override // b1.a
        public boolean c(b1 b1Var, Menu menu) {
            return this.a.onCreateActionMode(e(b1Var), f(menu));
        }

        @Override // b1.a
        public void d(b1 b1Var) {
            this.a.onDestroyActionMode(e(b1Var));
        }

        public ActionMode e(b1 b1Var) {
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                f1 f1Var = this.c.get(i);
                if (f1Var != null && f1Var.b == b1Var) {
                    return f1Var;
                }
            }
            f1 f1Var2 = new f1(this.b, b1Var);
            this.c.add(f1Var2);
            return f1Var2;
        }

        public final Menu f(Menu menu) {
            Menu menu2 = this.d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            x1 x1Var = new x1(this.b, (e8) menu);
            this.d.put(menu, x1Var);
            return x1Var;
        }
    }

    public f1(Context context, b1 b1Var) {
        this.a = context;
        this.b = b1Var;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.b.c();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.b.d();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new x1(this.a, (e8) this.b.e());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.b.f();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.b.g();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.b.h();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.b.i();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.b.j();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.b.k();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.b.l();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.b.m(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.b.p(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.b.r(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z) {
        this.b.s(z);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i) {
        this.b.n(i);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i) {
        this.b.q(i);
    }
}
