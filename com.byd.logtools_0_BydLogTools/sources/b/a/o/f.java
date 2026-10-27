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
    final Context f2181a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final b f2182b;

    /* JADX INFO: compiled from: SupportActionModeWrapper.java */
    public static class a implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ActionMode.Callback f2183a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Context f2184b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final ArrayList<f> f2185c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final b.d.g<Menu, Menu> f2186d = new b.d.g<>();

        public a(Context context, ActionMode.Callback callback) {
            this.f2184b = context;
            this.f2183a = callback;
        }

        private Menu f(Menu menu) {
            Menu menu2 = this.f2186d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            o oVar = new o(this.f2184b, (b.g.e.a.a) menu);
            this.f2186d.put(menu, oVar);
            return oVar;
        }

        @Override // b.a.o.b.a
        public boolean a(b bVar, Menu menu) {
            return this.f2183a.onPrepareActionMode(e(bVar), f(menu));
        }

        @Override // b.a.o.b.a
        public boolean b(b bVar, MenuItem menuItem) {
            return this.f2183a.onActionItemClicked(e(bVar), new j(this.f2184b, (b.g.e.a.b) menuItem));
        }

        @Override // b.a.o.b.a
        public boolean c(b bVar, Menu menu) {
            return this.f2183a.onCreateActionMode(e(bVar), f(menu));
        }

        @Override // b.a.o.b.a
        public void d(b bVar) {
            this.f2183a.onDestroyActionMode(e(bVar));
        }

        public ActionMode e(b bVar) {
            int size = this.f2185c.size();
            for (int i = 0; i < size; i++) {
                f fVar = this.f2185c.get(i);
                if (fVar != null && fVar.f2182b == bVar) {
                    return fVar;
                }
            }
            f fVar2 = new f(this.f2184b, bVar);
            this.f2185c.add(fVar2);
            return fVar2;
        }
    }

    public f(Context context, b bVar) {
        this.f2181a = context;
        this.f2182b = bVar;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.f2182b.c();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.f2182b.d();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new o(this.f2181a, (b.g.e.a.a) this.f2182b.e());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.f2182b.f();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f2182b.g();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.f2182b.h();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.f2182b.i();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.f2182b.j();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f2182b.k();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.f2182b.l();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.f2182b.m(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f2182b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.f2182b.p(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f2182b.r(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z) {
        this.f2182b.s(z);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i) {
        this.f2182b.n(i);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i) {
        this.f2182b.q(i);
    }
}
