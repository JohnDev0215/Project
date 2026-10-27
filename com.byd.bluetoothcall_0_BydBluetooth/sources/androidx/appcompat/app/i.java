package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.appcompat.app.a;
import androidx.appcompat.view.menu.g;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.f0;
import androidx.appcompat.widget.p;
import b.g.k.t;
import java.util.ArrayList;

/* JADX INFO: compiled from: ToolbarActionBar.java */
/* JADX INFO: loaded from: classes.dex */
class i extends androidx.appcompat.app.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    p f274a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f275b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Window.Callback f276c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f277d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f278e;
    private ArrayList<a.b> f = new ArrayList<>();
    private final Runnable g = new a();
    private final Toolbar.f h;

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            i.this.x();
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    class b implements Toolbar.f {
        b() {
        }

        @Override // androidx.appcompat.widget.Toolbar.f
        public boolean onMenuItemClick(MenuItem menuItem) {
            return i.this.f276c.onMenuItemSelected(0, menuItem);
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    private final class c implements m.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f281b;

        c() {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
            if (this.f281b) {
                return;
            }
            this.f281b = true;
            i.this.f274a.h();
            Window.Callback callback = i.this.f276c;
            if (callback != null) {
                callback.onPanelClosed(108, gVar);
            }
            this.f281b = false;
        }

        @Override // androidx.appcompat.view.menu.m.a
        public boolean b(androidx.appcompat.view.menu.g gVar) {
            Window.Callback callback = i.this.f276c;
            if (callback == null) {
                return false;
            }
            callback.onMenuOpened(108, gVar);
            return true;
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    private final class d implements g.a {
        d() {
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            return false;
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void b(androidx.appcompat.view.menu.g gVar) {
            i iVar = i.this;
            if (iVar.f276c != null) {
                if (iVar.f274a.c()) {
                    i.this.f276c.onPanelClosed(108, gVar);
                } else if (i.this.f276c.onPreparePanel(0, null, gVar)) {
                    i.this.f276c.onMenuOpened(108, gVar);
                }
            }
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    private class e extends b.a.o.i {
        public e(Window.Callback callback) {
            super(callback);
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public View onCreatePanelView(int i) {
            return i == 0 ? new View(i.this.f274a.q()) : super.onCreatePanelView(i);
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public boolean onPreparePanel(int i, View view, Menu menu) {
            boolean zOnPreparePanel = super.onPreparePanel(i, view, menu);
            if (zOnPreparePanel) {
                i iVar = i.this;
                if (!iVar.f275b) {
                    iVar.f274a.f();
                    i.this.f275b = true;
                }
            }
            return zOnPreparePanel;
        }
    }

    i(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        b bVar = new b();
        this.h = bVar;
        this.f274a = new f0(toolbar, false);
        e eVar = new e(callback);
        this.f276c = eVar;
        this.f274a.setWindowCallback(eVar);
        toolbar.setOnMenuItemClickListener(bVar);
        this.f274a.setWindowTitle(charSequence);
    }

    private Menu v() {
        if (!this.f277d) {
            this.f274a.i(new c(), new d());
            this.f277d = true;
        }
        return this.f274a.l();
    }

    @Override // androidx.appcompat.app.a
    public boolean g() {
        return this.f274a.d();
    }

    @Override // androidx.appcompat.app.a
    public boolean h() {
        if (!this.f274a.u()) {
            return false;
        }
        this.f274a.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.a
    public void i(boolean z) {
        if (z == this.f278e) {
            return;
        }
        this.f278e = z;
        int size = this.f.size();
        for (int i = 0; i < size; i++) {
            this.f.get(i).a(z);
        }
    }

    @Override // androidx.appcompat.app.a
    public int j() {
        return this.f274a.j();
    }

    @Override // androidx.appcompat.app.a
    public Context k() {
        return this.f274a.q();
    }

    @Override // androidx.appcompat.app.a
    public boolean l() {
        this.f274a.o().removeCallbacks(this.g);
        t.c0(this.f274a.o(), this.g);
        return true;
    }

    @Override // androidx.appcompat.app.a
    public void m(Configuration configuration) {
        super.m(configuration);
    }

    @Override // androidx.appcompat.app.a
    void n() {
        this.f274a.o().removeCallbacks(this.g);
    }

    @Override // androidx.appcompat.app.a
    public boolean o(int i, KeyEvent keyEvent) {
        Menu menuV = v();
        if (menuV == null) {
            return false;
        }
        menuV.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuV.performShortcut(i, keyEvent, 0);
    }

    @Override // androidx.appcompat.app.a
    public boolean p(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            q();
        }
        return true;
    }

    @Override // androidx.appcompat.app.a
    public boolean q() {
        return this.f274a.e();
    }

    @Override // androidx.appcompat.app.a
    public void r(boolean z) {
    }

    @Override // androidx.appcompat.app.a
    public void s(boolean z) {
    }

    @Override // androidx.appcompat.app.a
    public void t(CharSequence charSequence) {
        this.f274a.setWindowTitle(charSequence);
    }

    public Window.Callback w() {
        return this.f276c;
    }

    void x() {
        Menu menuV = v();
        androidx.appcompat.view.menu.g gVar = menuV instanceof androidx.appcompat.view.menu.g ? (androidx.appcompat.view.menu.g) menuV : null;
        if (gVar != null) {
            gVar.h0();
        }
        try {
            menuV.clear();
            if (!this.f276c.onCreatePanelMenu(0, menuV) || !this.f276c.onPreparePanel(0, null, menuV)) {
                menuV.clear();
            }
        } finally {
            if (gVar != null) {
                gVar.g0();
            }
        }
    }
}
