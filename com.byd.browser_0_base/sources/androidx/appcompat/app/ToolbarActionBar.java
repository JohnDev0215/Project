package androidx.appcompat.app;

import android.content.Context;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatDelegateImpl;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import defpackage.in3;
import defpackage.om3;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
class ToolbarActionBar extends ActionBar {
    public final ToolbarWidgetWrapper a;
    public final Window.Callback b;
    public final ToolbarMenuCallback c;
    public boolean d;
    public boolean e;
    public boolean f;
    public final ArrayList<ActionBar.OnMenuVisibilityListener> g = new ArrayList<>();
    public final Runnable h = new Runnable() { // from class: androidx.appcompat.app.ToolbarActionBar.1
        @Override // java.lang.Runnable
        public final void run() {
            ToolbarActionBar toolbarActionBar = ToolbarActionBar.this;
            Window.Callback callback = toolbarActionBar.b;
            Menu menuZ = toolbarActionBar.z();
            MenuBuilder menuBuilder = menuZ instanceof MenuBuilder ? (MenuBuilder) menuZ : null;
            if (menuBuilder != null) {
                menuBuilder.y();
            }
            try {
                menuZ.clear();
                if (!callback.onCreatePanelMenu(0, menuZ) || !callback.onPreparePanel(0, null, menuZ)) {
                    menuZ.clear();
                }
            } finally {
                if (menuBuilder != null) {
                    menuBuilder.x();
                }
            }
        }
    };
    public final Toolbar.OnMenuItemClickListener i;

    public final class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        public boolean e;

        public ActionMenuPresenterCallback() {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public final void c(MenuBuilder menuBuilder, boolean z) {
            if (this.e) {
                return;
            }
            this.e = true;
            ToolbarActionBar toolbarActionBar = ToolbarActionBar.this;
            toolbarActionBar.a.h();
            toolbarActionBar.b.onPanelClosed(108, menuBuilder);
            this.e = false;
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public final boolean d(MenuBuilder menuBuilder) {
            ToolbarActionBar.this.b.onMenuOpened(108, menuBuilder);
            return true;
        }
    }

    public final class MenuBuilderCallback implements MenuBuilder.Callback {
        public MenuBuilderCallback() {
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public final boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public final void b(MenuBuilder menuBuilder) {
            ToolbarActionBar toolbarActionBar = ToolbarActionBar.this;
            boolean zA = toolbarActionBar.a.a();
            Window.Callback callback = toolbarActionBar.b;
            if (zA) {
                callback.onPanelClosed(108, menuBuilder);
            } else if (callback.onPreparePanel(0, null, menuBuilder)) {
                callback.onMenuOpened(108, menuBuilder);
            }
        }
    }

    public class ToolbarMenuCallback implements AppCompatDelegateImpl.ActionBarMenuCallback {
        public ToolbarMenuCallback() {
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.ActionBarMenuCallback
        public final void a(int i) {
            if (i == 0) {
                ToolbarActionBar toolbarActionBar = ToolbarActionBar.this;
                if (toolbarActionBar.d) {
                    return;
                }
                toolbarActionBar.a.m = true;
                toolbarActionBar.d = true;
            }
        }

        @Override // androidx.appcompat.app.AppCompatDelegateImpl.ActionBarMenuCallback
        public final View onCreatePanelView(int i) {
            if (i == 0) {
                return new View(ToolbarActionBar.this.a.getContext());
            }
            return null;
        }
    }

    public ToolbarActionBar(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        Toolbar.OnMenuItemClickListener onMenuItemClickListener = new Toolbar.OnMenuItemClickListener() { // from class: androidx.appcompat.app.ToolbarActionBar.2
            @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return ToolbarActionBar.this.b.onMenuItemSelected(0, menuItem);
            }
        };
        this.i = onMenuItemClickListener;
        toolbar.getClass();
        ToolbarWidgetWrapper toolbarWidgetWrapper = new ToolbarWidgetWrapper(toolbar, false);
        this.a = toolbarWidgetWrapper;
        callback.getClass();
        this.b = callback;
        toolbarWidgetWrapper.l = callback;
        toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        toolbarWidgetWrapper.setWindowTitle(charSequence);
        this.c = new ToolbarMenuCallback();
    }

    @Override // androidx.appcompat.app.ActionBar
    public void addOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.g.add(onMenuVisibilityListener);
    }

    @Override // androidx.appcompat.app.ActionBar
    public final boolean g() {
        return this.a.f();
    }

    @Override // androidx.appcompat.app.ActionBar
    public final boolean h() {
        ToolbarWidgetWrapper toolbarWidgetWrapper = this.a;
        if (!toolbarWidgetWrapper.j()) {
            return false;
        }
        toolbarWidgetWrapper.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public final void i(boolean z) {
        if (z == this.f) {
            return;
        }
        this.f = z;
        ArrayList<ActionBar.OnMenuVisibilityListener> arrayList = this.g;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            arrayList.get(i).a();
        }
    }

    @Override // androidx.appcompat.app.ActionBar
    public final int j() {
        return this.a.b;
    }

    @Override // androidx.appcompat.app.ActionBar
    public final int k() {
        return this.a.a.getHeight();
    }

    @Override // androidx.appcompat.app.ActionBar
    public final Context l() {
        return this.a.getContext();
    }

    @Override // androidx.appcompat.app.ActionBar
    public final boolean m() {
        ToolbarWidgetWrapper toolbarWidgetWrapper = this.a;
        Toolbar toolbar = toolbarWidgetWrapper.a;
        Runnable runnable = this.h;
        toolbar.removeCallbacks(runnable);
        Toolbar toolbar2 = toolbarWidgetWrapper.a;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.d.k(toolbar2, runnable);
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public final void o() {
        this.a.a.removeCallbacks(this.h);
    }

    @Override // androidx.appcompat.app.ActionBar
    public final boolean p(int i, KeyEvent keyEvent) {
        Menu menuZ = z();
        if (menuZ == null) {
            return false;
        }
        menuZ.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuZ.performShortcut(i, keyEvent, 0);
    }

    @Override // androidx.appcompat.app.ActionBar
    public final boolean q(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            r();
        }
        return true;
    }

    @Override // androidx.appcompat.app.ActionBar
    public final boolean r() {
        return this.a.g();
    }

    @Override // androidx.appcompat.app.ActionBar
    public void removeOnMenuVisibilityListener(ActionBar.OnMenuVisibilityListener onMenuVisibilityListener) {
        this.g.remove(onMenuVisibilityListener);
    }

    @Override // androidx.appcompat.app.ActionBar
    public final void t(boolean z) {
        int i = z ? 4 : 0;
        ToolbarWidgetWrapper toolbarWidgetWrapper = this.a;
        toolbarWidgetWrapper.k((i & 4) | (toolbarWidgetWrapper.b & (-5)));
    }

    @Override // androidx.appcompat.app.ActionBar
    public final void u(int i) {
        this.a.q(i);
    }

    @Override // androidx.appcompat.app.ActionBar
    public final void w() {
        this.a.setTitle("");
    }

    @Override // androidx.appcompat.app.ActionBar
    public final void x(CharSequence charSequence) {
        this.a.setWindowTitle(charSequence);
    }

    public final Menu z() {
        boolean z = this.e;
        ToolbarWidgetWrapper toolbarWidgetWrapper = this.a;
        if (!z) {
            ActionMenuPresenterCallback actionMenuPresenterCallback = new ActionMenuPresenterCallback();
            MenuBuilderCallback menuBuilderCallback = new MenuBuilderCallback();
            Toolbar toolbar = toolbarWidgetWrapper.a;
            toolbar.R = actionMenuPresenterCallback;
            toolbar.S = menuBuilderCallback;
            ActionMenuView actionMenuView = toolbar.e;
            if (actionMenuView != null) {
                actionMenuView.y = actionMenuPresenterCallback;
                actionMenuView.z = menuBuilderCallback;
            }
            this.e = true;
        }
        return toolbarWidgetWrapper.a.getMenu();
    }

    @Override // androidx.appcompat.app.ActionBar
    public final void n() {
    }

    @Override // androidx.appcompat.app.ActionBar
    public final void s(boolean z) {
    }

    @Override // androidx.appcompat.app.ActionBar
    public final void v(boolean z) {
    }
}
