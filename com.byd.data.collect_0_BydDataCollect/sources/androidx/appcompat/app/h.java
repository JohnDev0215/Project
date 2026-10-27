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
import androidx.appcompat.widget.g0;
import androidx.appcompat.widget.p;
import b.g.k.t;
import java.util.ArrayList;

/* JADX INFO: compiled from: ToolbarActionBar.java */
/* JADX INFO: loaded from: classes.dex */
class h extends androidx.appcompat.app.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    p f193a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f194b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Window.Callback f195c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f196d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f197e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ArrayList<a.b> f198f = new ArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Runnable f199g = new a();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Toolbar.f f200h;

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            h.this.x();
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    class b implements Toolbar.f {
        b() {
        }

        @Override // androidx.appcompat.widget.Toolbar.f
        public boolean onMenuItemClick(MenuItem menuItem) {
            return h.this.f195c.onMenuItemSelected(0, menuItem);
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    private final class c implements m.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f203b;

        c() {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
            if (this.f203b) {
                return;
            }
            this.f203b = true;
            h.this.f193a.i();
            Window.Callback callback = h.this.f195c;
            if (callback != null) {
                callback.onPanelClosed(108, gVar);
            }
            this.f203b = false;
        }

        @Override // androidx.appcompat.view.menu.m.a
        public boolean b(androidx.appcompat.view.menu.g gVar) {
            Window.Callback callback = h.this.f195c;
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
            h hVar = h.this;
            if (hVar.f195c != null) {
                if (hVar.f193a.d()) {
                    h.this.f195c.onPanelClosed(108, gVar);
                } else if (h.this.f195c.onPreparePanel(0, null, gVar)) {
                    h.this.f195c.onMenuOpened(108, gVar);
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
        public View onCreatePanelView(int i2) {
            return i2 == 0 ? new View(h.this.f193a.a()) : super.onCreatePanelView(i2);
        }

        @Override // b.a.o.i, android.view.Window.Callback
        public boolean onPreparePanel(int i2, View view, Menu menu) {
            boolean zOnPreparePanel = super.onPreparePanel(i2, view, menu);
            if (zOnPreparePanel) {
                h hVar = h.this;
                if (!hVar.f194b) {
                    hVar.f193a.g();
                    h.this.f194b = true;
                }
            }
            return zOnPreparePanel;
        }
    }

    h(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        b bVar = new b();
        this.f200h = bVar;
        this.f193a = new g0(toolbar, false);
        e eVar = new e(callback);
        this.f195c = eVar;
        this.f193a.setWindowCallback(eVar);
        toolbar.setOnMenuItemClickListener(bVar);
        this.f193a.setWindowTitle(charSequence);
    }

    private Menu v() {
        if (!this.f196d) {
            this.f193a.j(new c(), new d());
            this.f196d = true;
        }
        return this.f193a.m();
    }

    @Override // androidx.appcompat.app.a
    public boolean g() {
        return this.f193a.e();
    }

    @Override // androidx.appcompat.app.a
    public boolean h() {
        if (!this.f193a.u()) {
            return false;
        }
        this.f193a.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.a
    public void i(boolean z) {
        if (z == this.f197e) {
            return;
        }
        this.f197e = z;
        int size = this.f198f.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f198f.get(i2).a(z);
        }
    }

    @Override // androidx.appcompat.app.a
    public int j() {
        return this.f193a.k();
    }

    @Override // androidx.appcompat.app.a
    public Context k() {
        return this.f193a.a();
    }

    @Override // androidx.appcompat.app.a
    public boolean l() {
        this.f193a.p().removeCallbacks(this.f199g);
        t.b0(this.f193a.p(), this.f199g);
        return true;
    }

    @Override // androidx.appcompat.app.a
    public void m(Configuration configuration) {
        super.m(configuration);
    }

    @Override // androidx.appcompat.app.a
    void n() {
        this.f193a.p().removeCallbacks(this.f199g);
    }

    @Override // androidx.appcompat.app.a
    public boolean o(int i2, KeyEvent keyEvent) {
        Menu menuV = v();
        if (menuV == null) {
            return false;
        }
        menuV.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuV.performShortcut(i2, keyEvent, 0);
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
        return this.f193a.f();
    }

    @Override // androidx.appcompat.app.a
    public void r(boolean z) {
    }

    @Override // androidx.appcompat.app.a
    public void s(boolean z) {
    }

    @Override // androidx.appcompat.app.a
    public void t(CharSequence charSequence) {
        this.f193a.setWindowTitle(charSequence);
    }

    public Window.Callback w() {
        return this.f195c;
    }

    void x() {
        Menu menuV = v();
        androidx.appcompat.view.menu.g gVar = menuV instanceof androidx.appcompat.view.menu.g ? (androidx.appcompat.view.menu.g) menuV : null;
        if (gVar != null) {
            gVar.h0();
        }
        try {
            menuV.clear();
            if (!this.f195c.onCreatePanelMenu(0, menuV) || !this.f195c.onPreparePanel(0, null, menuV)) {
                menuV.clear();
            }
        } finally {
            if (gVar != null) {
                gVar.g0();
            }
        }
    }
}
