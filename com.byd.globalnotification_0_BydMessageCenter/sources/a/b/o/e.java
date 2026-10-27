package a.b.o;

import a.b.o.a;
import a.b.o.i.j;
import a.b.o.i.o;
import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class e extends ActionMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f101a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a.b.o.a f102b;

    public static class a implements a.InterfaceC0001a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ActionMode.Callback f103a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Context f104b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ArrayList<e> f105c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final a.e.h<Menu, Menu> f106d = new a.e.h<>();

        public a(Context context, ActionMode.Callback callback) {
            this.f104b = context;
            this.f103a = callback;
        }

        @Override // a.b.o.a.InterfaceC0001a
        public boolean a(a.b.o.a aVar, Menu menu) {
            return this.f103a.onPrepareActionMode(e(aVar), f(menu));
        }

        @Override // a.b.o.a.InterfaceC0001a
        public void b(a.b.o.a aVar) {
            this.f103a.onDestroyActionMode(e(aVar));
        }

        @Override // a.b.o.a.InterfaceC0001a
        public boolean c(a.b.o.a aVar, MenuItem menuItem) {
            return this.f103a.onActionItemClicked(e(aVar), new j(this.f104b, (a.h.f.a.b) menuItem));
        }

        @Override // a.b.o.a.InterfaceC0001a
        public boolean d(a.b.o.a aVar, Menu menu) {
            return this.f103a.onCreateActionMode(e(aVar), f(menu));
        }

        public ActionMode e(a.b.o.a aVar) {
            int size = this.f105c.size();
            for (int i = 0; i < size; i++) {
                e eVar = this.f105c.get(i);
                if (eVar != null && eVar.f102b == aVar) {
                    return eVar;
                }
            }
            e eVar2 = new e(this.f104b, aVar);
            this.f105c.add(eVar2);
            return eVar2;
        }

        public final Menu f(Menu menu) {
            Menu orDefault = this.f106d.getOrDefault(menu, null);
            if (orDefault != null) {
                return orDefault;
            }
            o oVar = new o(this.f104b, (a.h.f.a.a) menu);
            this.f106d.put(menu, oVar);
            return oVar;
        }
    }

    public e(Context context, a.b.o.a aVar) {
        this.f101a = context;
        this.f102b = aVar;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.f102b.c();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.f102b.d();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new o(this.f101a, (a.h.f.a.a) this.f102b.e());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.f102b.f();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f102b.g();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.f102b.f90a;
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.f102b.h();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.f102b.f91b;
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f102b.i();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.f102b.j();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.f102b.k(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i) {
        this.f102b.l(i);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f102b.m(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.f102b.f90a = obj;
    }

    @Override // android.view.ActionMode
    public void setTitle(int i) {
        this.f102b.n(i);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f102b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z) {
        this.f102b.p(z);
    }
}
