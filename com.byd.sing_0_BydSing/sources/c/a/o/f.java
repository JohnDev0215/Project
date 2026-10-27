package c.a.o;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.view.menu.o;
import c.a.o.b;
import java.util.ArrayList;

/* JADX INFO: compiled from: SupportActionModeWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends ActionMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Context f2038a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final b f2039b;

    /* JADX INFO: compiled from: SupportActionModeWrapper.java */
    public static class a implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ActionMode.Callback f2040a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Context f2041b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final ArrayList<f> f2042c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.d.g<Menu, Menu> f2043d = new c.d.g<>();

        public a(Context context, ActionMode.Callback callback) {
            this.f2041b = context;
            this.f2040a = callback;
        }

        private Menu f(Menu menu) {
            Menu menu2 = this.f2043d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            o oVar = new o(this.f2041b, (c.g.e.a.a) menu);
            this.f2043d.put(menu, oVar);
            return oVar;
        }

        @Override // c.a.o.b.a
        public boolean a(b bVar, Menu menu) {
            return this.f2040a.onPrepareActionMode(e(bVar), f(menu));
        }

        @Override // c.a.o.b.a
        public void b(b bVar) {
            this.f2040a.onDestroyActionMode(e(bVar));
        }

        @Override // c.a.o.b.a
        public boolean c(b bVar, MenuItem menuItem) {
            return this.f2040a.onActionItemClicked(e(bVar), new j(this.f2041b, (c.g.e.a.b) menuItem));
        }

        @Override // c.a.o.b.a
        public boolean d(b bVar, Menu menu) {
            return this.f2040a.onCreateActionMode(e(bVar), f(menu));
        }

        public ActionMode e(b bVar) {
            int size = this.f2042c.size();
            for (int i = 0; i < size; i++) {
                f fVar = this.f2042c.get(i);
                if (fVar != null && fVar.f2039b == bVar) {
                    return fVar;
                }
            }
            f fVar2 = new f(this.f2041b, bVar);
            this.f2042c.add(fVar2);
            return fVar2;
        }
    }

    public f(Context context, b bVar) {
        this.f2038a = context;
        this.f2039b = bVar;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.f2039b.c();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.f2039b.d();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new o(this.f2038a, (c.g.e.a.a) this.f2039b.e());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.f2039b.f();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f2039b.g();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.f2039b.h();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.f2039b.i();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.f2039b.j();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f2039b.k();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.f2039b.l();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.f2039b.m(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f2039b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.f2039b.p(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f2039b.r(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z) {
        this.f2039b.s(z);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i) {
        this.f2039b.n(i);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i) {
        this.f2039b.q(i);
    }
}
