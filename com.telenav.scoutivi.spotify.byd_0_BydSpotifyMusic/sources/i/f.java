package i;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import i.b;
import java.util.ArrayList;

/* JADX INFO: compiled from: SupportActionModeWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends ActionMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Context f6775a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final b f6776b;

    /* JADX INFO: compiled from: SupportActionModeWrapper.java */
    public static class a implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ActionMode.Callback f6777a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Context f6778b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final ArrayList<f> f6779c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final n.g<Menu, Menu> f6780d = new n.g<>();

        public a(Context context, ActionMode.Callback callback) {
            this.f6778b = context;
            this.f6777a = callback;
        }

        private Menu f(Menu menu) {
            Menu menu2 = this.f6780d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            j.d dVar = new j.d(this.f6778b, (b0.a) menu);
            this.f6780d.put(menu, dVar);
            return dVar;
        }

        @Override // i.b.a
        public boolean a(b bVar, MenuItem menuItem) {
            return this.f6777a.onActionItemClicked(e(bVar), new j.c(this.f6778b, (b0.b) menuItem));
        }

        @Override // i.b.a
        public boolean b(b bVar, Menu menu) {
            return this.f6777a.onPrepareActionMode(e(bVar), f(menu));
        }

        @Override // i.b.a
        public void c(b bVar) {
            this.f6777a.onDestroyActionMode(e(bVar));
        }

        @Override // i.b.a
        public boolean d(b bVar, Menu menu) {
            return this.f6777a.onCreateActionMode(e(bVar), f(menu));
        }

        public ActionMode e(b bVar) {
            int size = this.f6779c.size();
            for (int i7 = 0; i7 < size; i7++) {
                f fVar = this.f6779c.get(i7);
                if (fVar != null && fVar.f6776b == bVar) {
                    return fVar;
                }
            }
            f fVar2 = new f(this.f6778b, bVar);
            this.f6779c.add(fVar2);
            return fVar2;
        }
    }

    public f(Context context, b bVar) {
        this.f6775a = context;
        this.f6776b = bVar;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.f6776b.c();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.f6776b.d();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new j.d(this.f6775a, (b0.a) this.f6776b.e());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.f6776b.f();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f6776b.g();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.f6776b.h();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.f6776b.i();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.f6776b.j();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f6776b.k();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.f6776b.l();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.f6776b.m(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f6776b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.f6776b.p(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f6776b.r(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z6) {
        this.f6776b.s(z6);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i7) {
        this.f6776b.n(i7);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i7) {
        this.f6776b.q(i7);
    }
}
