package androidx.appcompat.view;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemWrapperICS;
import androidx.appcompat.view.menu.MenuWrapperICS;
import defpackage.pw2;
import defpackage.wm2;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class SupportActionModeWrapper extends android.view.ActionMode {
    public final Context a;
    public final ActionMode b;

    public static class CallbackWrapper implements ActionMode.Callback {
        public final ActionMode.Callback a;
        public final Context b;
        public final ArrayList<SupportActionModeWrapper> c = new ArrayList<>();
        public final wm2<Menu, Menu> d = new wm2<>();

        public CallbackWrapper(Context context, ActionMode.Callback callback) {
            this.b = context;
            this.a = callback;
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public final void a(ActionMode actionMode) {
            this.a.onDestroyActionMode(e(actionMode));
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public final boolean b(ActionMode actionMode, MenuBuilder menuBuilder) {
            SupportActionModeWrapper supportActionModeWrapperE = e(actionMode);
            wm2<Menu, Menu> wm2Var = this.d;
            Menu menuWrapperICS = wm2Var.get(menuBuilder);
            if (menuWrapperICS == null) {
                menuWrapperICS = new MenuWrapperICS(this.b, menuBuilder);
                wm2Var.put(menuBuilder, menuWrapperICS);
            }
            return this.a.onCreateActionMode(supportActionModeWrapperE, menuWrapperICS);
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public final boolean c(ActionMode actionMode, MenuItem menuItem) {
            return this.a.onActionItemClicked(e(actionMode), new MenuItemWrapperICS(this.b, (pw2) menuItem));
        }

        @Override // androidx.appcompat.view.ActionMode.Callback
        public final boolean d(ActionMode actionMode, MenuBuilder menuBuilder) {
            SupportActionModeWrapper supportActionModeWrapperE = e(actionMode);
            wm2<Menu, Menu> wm2Var = this.d;
            Menu menuWrapperICS = wm2Var.get(menuBuilder);
            if (menuWrapperICS == null) {
                menuWrapperICS = new MenuWrapperICS(this.b, menuBuilder);
                wm2Var.put(menuBuilder, menuWrapperICS);
            }
            return this.a.onPrepareActionMode(supportActionModeWrapperE, menuWrapperICS);
        }

        public final SupportActionModeWrapper e(ActionMode actionMode) {
            ArrayList<SupportActionModeWrapper> arrayList = this.c;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                SupportActionModeWrapper supportActionModeWrapper = arrayList.get(i);
                if (supportActionModeWrapper != null && supportActionModeWrapper.b == actionMode) {
                    return supportActionModeWrapper;
                }
            }
            SupportActionModeWrapper supportActionModeWrapper2 = new SupportActionModeWrapper(this.b, actionMode);
            arrayList.add(supportActionModeWrapper2);
            return supportActionModeWrapper2;
        }
    }

    public SupportActionModeWrapper(Context context, ActionMode actionMode) {
        this.a = context;
        this.b = actionMode;
    }

    @Override // android.view.ActionMode
    public final void finish() {
        this.b.c();
    }

    @Override // android.view.ActionMode
    public final View getCustomView() {
        return this.b.d();
    }

    @Override // android.view.ActionMode
    public final Menu getMenu() {
        return new MenuWrapperICS(this.a, this.b.e());
    }

    @Override // android.view.ActionMode
    public final MenuInflater getMenuInflater() {
        return this.b.f();
    }

    @Override // android.view.ActionMode
    public final CharSequence getSubtitle() {
        return this.b.g();
    }

    @Override // android.view.ActionMode
    public final Object getTag() {
        return this.b.e;
    }

    @Override // android.view.ActionMode
    public final CharSequence getTitle() {
        return this.b.h();
    }

    @Override // android.view.ActionMode
    public final boolean getTitleOptionalHint() {
        return this.b.f;
    }

    @Override // android.view.ActionMode
    public final void invalidate() {
        this.b.i();
    }

    @Override // android.view.ActionMode
    public final boolean isTitleOptional() {
        return this.b.j();
    }

    @Override // android.view.ActionMode
    public final void setCustomView(View view) {
        this.b.k(view);
    }

    @Override // android.view.ActionMode
    public final void setSubtitle(CharSequence charSequence) {
        this.b.m(charSequence);
    }

    @Override // android.view.ActionMode
    public final void setTag(Object obj) {
        this.b.e = obj;
    }

    @Override // android.view.ActionMode
    public final void setTitle(CharSequence charSequence) {
        this.b.o(charSequence);
    }

    @Override // android.view.ActionMode
    public final void setTitleOptionalHint(boolean z) {
        this.b.p(z);
    }

    @Override // android.view.ActionMode
    public final void setSubtitle(int i) {
        this.b.l(i);
    }

    @Override // android.view.ActionMode
    public final void setTitle(int i) {
        this.b.n(i);
    }
}
