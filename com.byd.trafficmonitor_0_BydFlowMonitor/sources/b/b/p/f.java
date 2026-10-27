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
    public final Context f1545a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f1546b;

    /* JADX INFO: compiled from: SupportActionModeWrapper.java */
    public static class a implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ActionMode.Callback f1547a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Context f1548b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ArrayList<f> f1549c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final b.e.g<Menu, Menu> f1550d = new b.e.g<>();

        public a(Context context, ActionMode.Callback callback) {
            this.f1548b = context;
            this.f1547a = callback;
        }

        @Override // b.b.p.b.a
        public boolean a(b bVar, Menu menu) {
            return this.f1547a.onPrepareActionMode(e(bVar), f(menu));
        }

        @Override // b.b.p.b.a
        public boolean b(b bVar, MenuItem menuItem) {
            return this.f1547a.onActionItemClicked(e(bVar), new j(this.f1548b, (b.h.g.a.b) menuItem));
        }

        @Override // b.b.p.b.a
        public boolean c(b bVar, Menu menu) {
            return this.f1547a.onCreateActionMode(e(bVar), f(menu));
        }

        @Override // b.b.p.b.a
        public void d(b bVar) {
            this.f1547a.onDestroyActionMode(e(bVar));
        }

        public ActionMode e(b bVar) {
            int size = this.f1549c.size();
            for (int i2 = 0; i2 < size; i2++) {
                f fVar = this.f1549c.get(i2);
                if (fVar != null && fVar.f1546b == bVar) {
                    return fVar;
                }
            }
            f fVar2 = new f(this.f1548b, bVar);
            this.f1549c.add(fVar2);
            return fVar2;
        }

        public final Menu f(Menu menu) {
            Menu menu2 = this.f1550d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            o oVar = new o(this.f1548b, (b.h.g.a.a) menu);
            this.f1550d.put(menu, oVar);
            return oVar;
        }
    }

    public f(Context context, b bVar) {
        this.f1545a = context;
        this.f1546b = bVar;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.f1546b.c();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.f1546b.d();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new o(this.f1545a, (b.h.g.a.a) this.f1546b.e());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.f1546b.f();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f1546b.g();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.f1546b.h();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.f1546b.i();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.f1546b.j();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f1546b.k();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.f1546b.l();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.f1546b.m(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f1546b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.f1546b.p(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f1546b.r(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z) {
        this.f1546b.s(z);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i2) {
        this.f1546b.n(i2);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i2) {
        this.f1546b.q(i2);
    }
}
