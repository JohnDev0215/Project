package b.a.o;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.view.menu.o;
import b.a.o.b;
import java.util.ArrayList;

/* JADX INFO: compiled from: SupportActionModeWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends ActionMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Context f2050a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final b f2051b;

    /* JADX INFO: compiled from: SupportActionModeWrapper.java */
    public static class a implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ActionMode.Callback f2052a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Context f2053b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final ArrayList<f> f2054c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final b.d.g<Menu, Menu> f2055d = new b.d.g<>();

        public a(Context context, ActionMode.Callback callback) {
            this.f2053b = context;
            this.f2052a = callback;
        }

        private Menu f(Menu menu) {
            Menu menu2 = this.f2055d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            o oVar = new o(this.f2053b, (b.g.e.a.a) menu);
            this.f2055d.put(menu, oVar);
            return oVar;
        }

        @Override // b.a.o.b.a
        public boolean a(b bVar, Menu menu) {
            return this.f2052a.onPrepareActionMode(e(bVar), f(menu));
        }

        @Override // b.a.o.b.a
        public boolean b(b bVar, MenuItem menuItem) {
            return this.f2052a.onActionItemClicked(e(bVar), new j(this.f2053b, (b.g.e.a.b) menuItem));
        }

        @Override // b.a.o.b.a
        public boolean c(b bVar, Menu menu) {
            return this.f2052a.onCreateActionMode(e(bVar), f(menu));
        }

        @Override // b.a.o.b.a
        public void d(b bVar) {
            this.f2052a.onDestroyActionMode(e(bVar));
        }

        public ActionMode e(b bVar) {
            int size = this.f2054c.size();
            for (int i = 0; i < size; i++) {
                f fVar = this.f2054c.get(i);
                if (fVar != null && fVar.f2051b == bVar) {
                    return fVar;
                }
            }
            f fVar2 = new f(this.f2053b, bVar);
            this.f2054c.add(fVar2);
            return fVar2;
        }
    }

    public f(Context context, b bVar) {
        this.f2050a = context;
        this.f2051b = bVar;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.f2051b.c();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.f2051b.d();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new o(this.f2050a, (b.g.e.a.a) this.f2051b.e());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.f2051b.f();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f2051b.g();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.f2051b.h();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.f2051b.i();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.f2051b.j();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f2051b.k();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.f2051b.l();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.f2051b.m(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f2051b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.f2051b.p(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f2051b.r(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z) {
        this.f2051b.s(z);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i) {
        this.f2051b.n(i);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i) {
        this.f2051b.q(i);
    }
}
