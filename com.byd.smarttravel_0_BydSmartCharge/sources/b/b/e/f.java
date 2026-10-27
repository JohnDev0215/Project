package b.b.e;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import b.b.e.b;
import b.b.e.j.j;
import b.b.e.j.o;
import java.util.ArrayList;

/* JADX INFO: compiled from: SupportActionModeWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends ActionMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f653a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b f654b;

    /* JADX INFO: compiled from: SupportActionModeWrapper.java */
    public static class a implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ActionMode.Callback f655a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Context f656b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ArrayList<f> f657c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final b.e.g<Menu, Menu> f658d = new b.e.g<>();

        public a(Context context, ActionMode.Callback callback) {
            this.f656b = context;
            this.f655a = callback;
        }

        @Override // b.b.e.b.a
        public boolean a(b bVar, Menu menu) {
            return this.f655a.onPrepareActionMode(e(bVar), f(menu));
        }

        @Override // b.b.e.b.a
        public boolean b(b bVar, MenuItem menuItem) {
            return this.f655a.onActionItemClicked(e(bVar), new j(this.f656b, (b.g.d.a.b) menuItem));
        }

        @Override // b.b.e.b.a
        public boolean c(b bVar, Menu menu) {
            return this.f655a.onCreateActionMode(e(bVar), f(menu));
        }

        @Override // b.b.e.b.a
        public void d(b bVar) {
            this.f655a.onDestroyActionMode(e(bVar));
        }

        public ActionMode e(b bVar) {
            int size = this.f657c.size();
            for (int i = 0; i < size; i++) {
                f fVar = this.f657c.get(i);
                if (fVar != null && fVar.f654b == bVar) {
                    return fVar;
                }
            }
            f fVar2 = new f(this.f656b, bVar);
            this.f657c.add(fVar2);
            return fVar2;
        }

        public final Menu f(Menu menu) {
            Menu menu2 = this.f658d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            o oVar = new o(this.f656b, (b.g.d.a.a) menu);
            this.f658d.put(menu, oVar);
            return oVar;
        }
    }

    public f(Context context, b bVar) {
        this.f653a = context;
        this.f654b = bVar;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.f654b.c();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.f654b.d();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new o(this.f653a, (b.g.d.a.a) this.f654b.e());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.f654b.f();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f654b.g();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.f654b.h();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.f654b.i();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.f654b.j();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f654b.k();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.f654b.l();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.f654b.m(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f654b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.f654b.p(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f654b.r(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z) {
        this.f654b.s(z);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i) {
        this.f654b.n(i);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i) {
        this.f654b.q(i);
    }
}
