package b.b.p;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import b.b.p.b;
import b.b.p.j.j;
import b.b.p.j.o;
import java.util.ArrayList;

/* JADX INFO: compiled from: SupportActionModeWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends ActionMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f874a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f875b;

    /* JADX INFO: compiled from: SupportActionModeWrapper.java */
    public static class a implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ActionMode.Callback f876a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Context f877b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ArrayList<f> f878c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final b.d.g<Menu, Menu> f879d = new b.d.g<>();

        public a(Context context, ActionMode.Callback callback) {
            this.f877b = context;
            this.f876a = callback;
        }

        @Override // b.b.p.b.a
        public boolean a(b bVar, Menu menu) {
            return this.f876a.onPrepareActionMode(e(bVar), f(menu));
        }

        @Override // b.b.p.b.a
        public boolean b(b bVar, MenuItem menuItem) {
            return this.f876a.onActionItemClicked(e(bVar), new j(this.f877b, (b.f.h.a.b) menuItem));
        }

        @Override // b.b.p.b.a
        public boolean c(b bVar, Menu menu) {
            return this.f876a.onCreateActionMode(e(bVar), f(menu));
        }

        @Override // b.b.p.b.a
        public void d(b bVar) {
            this.f876a.onDestroyActionMode(e(bVar));
        }

        public ActionMode e(b bVar) {
            int size = this.f878c.size();
            for (int i2 = 0; i2 < size; i2++) {
                f fVar = this.f878c.get(i2);
                if (fVar != null && fVar.f875b == bVar) {
                    return fVar;
                }
            }
            f fVar2 = new f(this.f877b, bVar);
            this.f878c.add(fVar2);
            return fVar2;
        }

        public final Menu f(Menu menu) {
            Menu menu2 = this.f879d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            o oVar = new o(this.f877b, (b.f.h.a.a) menu);
            this.f879d.put(menu, oVar);
            return oVar;
        }
    }

    public f(Context context, b bVar) {
        this.f874a = context;
        this.f875b = bVar;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.f875b.c();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.f875b.d();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new o(this.f874a, (b.f.h.a.a) this.f875b.e());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.f875b.f();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f875b.g();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.f875b.h();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.f875b.i();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.f875b.j();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f875b.k();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.f875b.l();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.f875b.m(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f875b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.f875b.p(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f875b.r(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z) {
        this.f875b.s(z);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i2) {
        this.f875b.n(i2);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i2) {
        this.f875b.q(i2);
    }
}
