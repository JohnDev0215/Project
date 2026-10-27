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
    final Context f1551a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final b f1552b;

    /* JADX INFO: compiled from: SupportActionModeWrapper.java */
    public static class a implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ActionMode.Callback f1553a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Context f1554b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final ArrayList<f> f1555c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final b.d.g<Menu, Menu> f1556d = new b.d.g<>();

        public a(Context context, ActionMode.Callback callback) {
            this.f1554b = context;
            this.f1553a = callback;
        }

        private Menu f(Menu menu) {
            Menu menu2 = this.f1556d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            o oVar = new o(this.f1554b, (b.g.e.a.a) menu);
            this.f1556d.put(menu, oVar);
            return oVar;
        }

        @Override // b.a.o.b.a
        public boolean a(b bVar, Menu menu) {
            return this.f1553a.onPrepareActionMode(e(bVar), f(menu));
        }

        @Override // b.a.o.b.a
        public boolean b(b bVar, MenuItem menuItem) {
            return this.f1553a.onActionItemClicked(e(bVar), new j(this.f1554b, (b.g.e.a.b) menuItem));
        }

        @Override // b.a.o.b.a
        public boolean c(b bVar, Menu menu) {
            return this.f1553a.onCreateActionMode(e(bVar), f(menu));
        }

        @Override // b.a.o.b.a
        public void d(b bVar) {
            this.f1553a.onDestroyActionMode(e(bVar));
        }

        public ActionMode e(b bVar) {
            int size = this.f1555c.size();
            for (int i = 0; i < size; i++) {
                f fVar = this.f1555c.get(i);
                if (fVar != null && fVar.f1552b == bVar) {
                    return fVar;
                }
            }
            f fVar2 = new f(this.f1554b, bVar);
            this.f1555c.add(fVar2);
            return fVar2;
        }
    }

    public f(Context context, b bVar) {
        this.f1551a = context;
        this.f1552b = bVar;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.f1552b.c();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.f1552b.d();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new o(this.f1551a, (b.g.e.a.a) this.f1552b.e());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.f1552b.f();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f1552b.g();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.f1552b.h();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.f1552b.i();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.f1552b.j();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f1552b.k();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.f1552b.l();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.f1552b.m(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f1552b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.f1552b.p(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f1552b.r(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z) {
        this.f1552b.s(z);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i) {
        this.f1552b.n(i);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i) {
        this.f1552b.q(i);
    }
}
