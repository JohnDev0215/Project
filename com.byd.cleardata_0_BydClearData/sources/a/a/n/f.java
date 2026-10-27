package a.a.n;

import a.a.n.b;
import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.view.menu.o;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class f extends ActionMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Context f72a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final b f73b;

    public static class a implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ActionMode.Callback f74a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Context f75b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final ArrayList<f> f76c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final a.d.g<Menu, Menu> f77d = new a.d.g<>();

        public a(Context context, ActionMode.Callback callback) {
            this.f75b = context;
            this.f74a = callback;
        }

        private Menu f(Menu menu) {
            Menu menu2 = this.f77d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            o oVar = new o(this.f75b, (a.g.e.a.a) menu);
            this.f77d.put(menu, oVar);
            return oVar;
        }

        @Override // a.a.n.b.a
        public boolean a(b bVar, Menu menu) {
            return this.f74a.onPrepareActionMode(e(bVar), f(menu));
        }

        @Override // a.a.n.b.a
        public boolean b(b bVar, MenuItem menuItem) {
            return this.f74a.onActionItemClicked(e(bVar), new j(this.f75b, (a.g.e.a.b) menuItem));
        }

        @Override // a.a.n.b.a
        public boolean c(b bVar, Menu menu) {
            return this.f74a.onCreateActionMode(e(bVar), f(menu));
        }

        @Override // a.a.n.b.a
        public void d(b bVar) {
            this.f74a.onDestroyActionMode(e(bVar));
        }

        public ActionMode e(b bVar) {
            int size = this.f76c.size();
            for (int i = 0; i < size; i++) {
                f fVar = this.f76c.get(i);
                if (fVar != null && fVar.f73b == bVar) {
                    return fVar;
                }
            }
            f fVar2 = new f(this.f75b, bVar);
            this.f76c.add(fVar2);
            return fVar2;
        }
    }

    public f(Context context, b bVar) {
        this.f72a = context;
        this.f73b = bVar;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.f73b.c();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.f73b.d();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new o(this.f72a, (a.g.e.a.a) this.f73b.e());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.f73b.f();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f73b.g();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.f73b.h();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.f73b.i();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.f73b.j();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f73b.k();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.f73b.l();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.f73b.m(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i) {
        this.f73b.n(i);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f73b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.f73b.p(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i) {
        this.f73b.q(i);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f73b.r(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z) {
        this.f73b.s(z);
    }
}
