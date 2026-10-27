package androidx.appcompat.c;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.RestrictTo;
import androidx.appcompat.c.b;
import androidx.appcompat.view.menu.n;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class f extends ActionMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Context f170a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final b f171b;

    /* JADX INFO: compiled from: caradapter-bug */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static class a implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ActionMode.Callback f172a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Context f173b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final ArrayList<f> f174c = new ArrayList<>();
        final b.a.g<Menu, Menu> d = new b.a.g<>();

        public a(Context context, ActionMode.Callback callback) {
            this.f173b = context;
            this.f172a = callback;
        }

        private Menu f(Menu menu) {
            Menu menu2 = this.d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            n nVar = new n(this.f173b, (androidx.core.b.a.a) menu);
            this.d.put(menu, nVar);
            return nVar;
        }

        @Override // androidx.appcompat.c.b.a
        public boolean a(b bVar, Menu menu) {
            return this.f172a.onPrepareActionMode(e(bVar), f(menu));
        }

        @Override // androidx.appcompat.c.b.a
        public boolean b(b bVar, MenuItem menuItem) {
            return this.f172a.onActionItemClicked(e(bVar), new androidx.appcompat.view.menu.i(this.f173b, (androidx.core.b.a.b) menuItem));
        }

        @Override // androidx.appcompat.c.b.a
        public boolean c(b bVar, Menu menu) {
            return this.f172a.onCreateActionMode(e(bVar), f(menu));
        }

        @Override // androidx.appcompat.c.b.a
        public void d(b bVar) {
            this.f172a.onDestroyActionMode(e(bVar));
        }

        public ActionMode e(b bVar) {
            int size = this.f174c.size();
            for (int i = 0; i < size; i++) {
                f fVar = this.f174c.get(i);
                if (fVar != null && fVar.f171b == bVar) {
                    return fVar;
                }
            }
            f fVar2 = new f(this.f173b, bVar);
            this.f174c.add(fVar2);
            return fVar2;
        }
    }

    public f(Context context, b bVar) {
        this.f170a = context;
        this.f171b = bVar;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.f171b.c();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.f171b.d();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new n(this.f170a, (androidx.core.b.a.a) this.f171b.e());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.f171b.f();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f171b.g();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.f171b.h();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.f171b.i();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.f171b.j();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f171b.k();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.f171b.l();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.f171b.m(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f171b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.f171b.p(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f171b.r(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z) {
        this.f171b.s(z);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i) {
        this.f171b.n(i);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i) {
        this.f171b.q(i);
    }
}
