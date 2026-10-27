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
    final Context f1471a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final b f1472b;

    /* JADX INFO: compiled from: SupportActionModeWrapper.java */
    public static class a implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ActionMode.Callback f1473a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Context f1474b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final ArrayList<f> f1475c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final b.d.g<Menu, Menu> f1476d = new b.d.g<>();

        public a(Context context, ActionMode.Callback callback) {
            this.f1474b = context;
            this.f1473a = callback;
        }

        private Menu f(Menu menu) {
            Menu menu2 = this.f1476d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            o oVar = new o(this.f1474b, (b.g.e.a.a) menu);
            this.f1476d.put(menu, oVar);
            return oVar;
        }

        @Override // b.a.o.b.a
        public boolean a(b bVar, Menu menu) {
            return this.f1473a.onPrepareActionMode(e(bVar), f(menu));
        }

        @Override // b.a.o.b.a
        public boolean b(b bVar, MenuItem menuItem) {
            return this.f1473a.onActionItemClicked(e(bVar), new j(this.f1474b, (b.g.e.a.b) menuItem));
        }

        @Override // b.a.o.b.a
        public boolean c(b bVar, Menu menu) {
            return this.f1473a.onCreateActionMode(e(bVar), f(menu));
        }

        @Override // b.a.o.b.a
        public void d(b bVar) {
            this.f1473a.onDestroyActionMode(e(bVar));
        }

        public ActionMode e(b bVar) {
            int size = this.f1475c.size();
            for (int i = 0; i < size; i++) {
                f fVar = this.f1475c.get(i);
                if (fVar != null && fVar.f1472b == bVar) {
                    return fVar;
                }
            }
            f fVar2 = new f(this.f1474b, bVar);
            this.f1475c.add(fVar2);
            return fVar2;
        }
    }

    public f(Context context, b bVar) {
        this.f1471a = context;
        this.f1472b = bVar;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.f1472b.c();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.f1472b.d();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new o(this.f1471a, (b.g.e.a.a) this.f1472b.e());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.f1472b.f();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f1472b.g();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.f1472b.h();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.f1472b.i();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.f1472b.j();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f1472b.k();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.f1472b.l();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.f1472b.m(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f1472b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.f1472b.p(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f1472b.r(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z) {
        this.f1472b.s(z);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i) {
        this.f1472b.n(i);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i) {
        this.f1472b.q(i);
    }
}
