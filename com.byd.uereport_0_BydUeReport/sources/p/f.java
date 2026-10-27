package p;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;
import p.b;
import q.j;
import q.o;

/* JADX INFO: compiled from: SupportActionModeWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends ActionMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f6910a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f6911b;

    /* JADX INFO: compiled from: SupportActionModeWrapper.java */
    public static class a implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ActionMode.Callback f6912a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Context f6913b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ArrayList<f> f6914c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final v.g<Menu, Menu> f6915d = new v.g<>();

        public a(Context context, ActionMode.Callback callback) {
            this.f6913b = context;
            this.f6912a = callback;
        }

        @Override // p.b.a
        public boolean a(b bVar, Menu menu) {
            return this.f6912a.onPrepareActionMode(e(bVar), f(menu));
        }

        @Override // p.b.a
        public void b(b bVar) {
            this.f6912a.onDestroyActionMode(e(bVar));
        }

        @Override // p.b.a
        public boolean c(b bVar, MenuItem menuItem) {
            return this.f6912a.onActionItemClicked(e(bVar), new j(this.f6913b, (i0.b) menuItem));
        }

        @Override // p.b.a
        public boolean d(b bVar, Menu menu) {
            return this.f6912a.onCreateActionMode(e(bVar), f(menu));
        }

        public ActionMode e(b bVar) {
            int size = this.f6914c.size();
            for (int i8 = 0; i8 < size; i8++) {
                f fVar = this.f6914c.get(i8);
                if (fVar != null && fVar.f6911b == bVar) {
                    return fVar;
                }
            }
            f fVar2 = new f(this.f6913b, bVar);
            this.f6914c.add(fVar2);
            return fVar2;
        }

        public final Menu f(Menu menu) {
            Menu menu2 = this.f6915d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            o oVar = new o(this.f6913b, (i0.a) menu);
            this.f6915d.put(menu, oVar);
            return oVar;
        }
    }

    public f(Context context, b bVar) {
        this.f6910a = context;
        this.f6911b = bVar;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.f6911b.c();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.f6911b.d();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new o(this.f6910a, (i0.a) this.f6911b.e());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.f6911b.f();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f6911b.g();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.f6911b.h();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.f6911b.i();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.f6911b.j();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f6911b.k();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.f6911b.l();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.f6911b.m(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f6911b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.f6911b.p(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f6911b.r(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z7) {
        this.f6911b.s(z7);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i8) {
        this.f6911b.n(i8);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i8) {
        this.f6911b.q(i8);
    }
}
