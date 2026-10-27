package m;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;
import m.b;
import n.j;
import n.o;

/* JADX INFO: compiled from: SupportActionModeWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends ActionMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f3744a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f3745b;

    /* JADX INFO: compiled from: SupportActionModeWrapper.java */
    public static class a implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ActionMode.Callback f3746a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Context f3747b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ArrayList<f> f3748c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final s.g<Menu, Menu> f3749d = new s.g<>();

        public a(Context context, ActionMode.Callback callback) {
            this.f3747b = context;
            this.f3746a = callback;
        }

        @Override // m.b.a
        public boolean a(b bVar, Menu menu) {
            return this.f3746a.onPrepareActionMode(e(bVar), f(menu));
        }

        @Override // m.b.a
        public boolean b(b bVar, MenuItem menuItem) {
            return this.f3746a.onActionItemClicked(e(bVar), new j(this.f3747b, (f0.b) menuItem));
        }

        @Override // m.b.a
        public boolean c(b bVar, Menu menu) {
            return this.f3746a.onCreateActionMode(e(bVar), f(menu));
        }

        @Override // m.b.a
        public void d(b bVar) {
            this.f3746a.onDestroyActionMode(e(bVar));
        }

        public ActionMode e(b bVar) {
            int size = this.f3748c.size();
            for (int i3 = 0; i3 < size; i3++) {
                f fVar = this.f3748c.get(i3);
                if (fVar != null && fVar.f3745b == bVar) {
                    return fVar;
                }
            }
            f fVar2 = new f(this.f3747b, bVar);
            this.f3748c.add(fVar2);
            return fVar2;
        }

        public final Menu f(Menu menu) {
            Menu menu2 = this.f3749d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            o oVar = new o(this.f3747b, (f0.a) menu);
            this.f3749d.put(menu, oVar);
            return oVar;
        }
    }

    public f(Context context, b bVar) {
        this.f3744a = context;
        this.f3745b = bVar;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.f3745b.c();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.f3745b.d();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new o(this.f3744a, (f0.a) this.f3745b.e());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.f3745b.f();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f3745b.g();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.f3745b.h();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.f3745b.i();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.f3745b.j();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f3745b.k();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.f3745b.l();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.f3745b.m(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f3745b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.f3745b.p(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f3745b.r(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z3) {
        this.f3745b.s(z3);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i3) {
        this.f3745b.n(i3);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i3) {
        this.f3745b.q(i3);
    }
}
