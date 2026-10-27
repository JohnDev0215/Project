package b.b.a;

import android.content.Context;
import android.content.res.Configuration;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.Toolbar;
import b.b.a.a;
import b.b.e.j.g;
import b.b.e.j.m;
import b.b.f.f0;
import b.b.f.p;
import b.g.j.t;
import java.util.ArrayList;

/* JADX INFO: compiled from: ToolbarActionBar.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends b.b.a.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public p f592a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f593b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Window.Callback f594c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f595d;
    public boolean e;
    public ArrayList<a.b> f = new ArrayList<>();
    public final Runnable g = new a();
    public final Toolbar.f h;

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            i.this.x();
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public class b implements Toolbar.f {
        public b() {
        }

        @Override // androidx.appcompat.widget.Toolbar.f
        public boolean onMenuItemClick(MenuItem menuItem) {
            return i.this.f594c.onMenuItemSelected(0, menuItem);
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public final class c implements m.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f598b;

        public c() {
        }

        @Override // b.b.e.j.m.a
        public void a(b.b.e.j.g gVar, boolean z) {
            if (this.f598b) {
                return;
            }
            this.f598b = true;
            i.this.f592a.h();
            Window.Callback callback = i.this.f594c;
            if (callback != null) {
                callback.onPanelClosed(108, gVar);
            }
            this.f598b = false;
        }

        @Override // b.b.e.j.m.a
        public boolean b(b.b.e.j.g gVar) {
            Window.Callback callback = i.this.f594c;
            if (callback == null) {
                return false;
            }
            callback.onMenuOpened(108, gVar);
            return true;
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public final class d implements g.a {
        public d() {
        }

        @Override // b.b.e.j.g.a
        public boolean a(b.b.e.j.g gVar, MenuItem menuItem) {
            return false;
        }

        @Override // b.b.e.j.g.a
        public void b(b.b.e.j.g gVar) {
            i iVar = i.this;
            if (iVar.f594c != null) {
                if (iVar.f592a.c()) {
                    i.this.f594c.onPanelClosed(108, gVar);
                } else if (i.this.f594c.onPreparePanel(0, null, gVar)) {
                    i.this.f594c.onMenuOpened(108, gVar);
                }
            }
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public class e extends b.b.e.i {
        public e(Window.Callback callback) {
            super(callback);
        }

        @Override // b.b.e.i, android.view.Window.Callback
        public View onCreatePanelView(int i) {
            return i == 0 ? new View(i.this.f592a.q()) : super.onCreatePanelView(i);
        }

        @Override // b.b.e.i, android.view.Window.Callback
        public boolean onPreparePanel(int i, View view, Menu menu) {
            boolean zOnPreparePanel = super.onPreparePanel(i, view, menu);
            if (zOnPreparePanel) {
                i iVar = i.this;
                if (!iVar.f593b) {
                    iVar.f592a.f();
                    i.this.f593b = true;
                }
            }
            return zOnPreparePanel;
        }
    }

    public i(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        b bVar = new b();
        this.h = bVar;
        this.f592a = new f0(toolbar, false);
        e eVar = new e(callback);
        this.f594c = eVar;
        this.f592a.setWindowCallback(eVar);
        toolbar.setOnMenuItemClickListener(bVar);
        this.f592a.setWindowTitle(charSequence);
    }

    @Override // b.b.a.a
    public boolean g() {
        return this.f592a.d();
    }

    @Override // b.b.a.a
    public boolean h() {
        if (!this.f592a.u()) {
            return false;
        }
        this.f592a.collapseActionView();
        return true;
    }

    @Override // b.b.a.a
    public void i(boolean z) {
        if (z == this.e) {
            return;
        }
        this.e = z;
        int size = this.f.size();
        for (int i = 0; i < size; i++) {
            this.f.get(i).a(z);
        }
    }

    @Override // b.b.a.a
    public int j() {
        return this.f592a.j();
    }

    @Override // b.b.a.a
    public Context k() {
        return this.f592a.q();
    }

    @Override // b.b.a.a
    public boolean l() {
        this.f592a.o().removeCallbacks(this.g);
        t.b0(this.f592a.o(), this.g);
        return true;
    }

    @Override // b.b.a.a
    public void m(Configuration configuration) {
        super.m(configuration);
    }

    @Override // b.b.a.a
    public void n() {
        this.f592a.o().removeCallbacks(this.g);
    }

    @Override // b.b.a.a
    public boolean o(int i, KeyEvent keyEvent) {
        Menu menuV = v();
        if (menuV == null) {
            return false;
        }
        menuV.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuV.performShortcut(i, keyEvent, 0);
    }

    @Override // b.b.a.a
    public boolean p(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            q();
        }
        return true;
    }

    @Override // b.b.a.a
    public boolean q() {
        return this.f592a.e();
    }

    @Override // b.b.a.a
    public void r(boolean z) {
    }

    @Override // b.b.a.a
    public void s(boolean z) {
    }

    @Override // b.b.a.a
    public void t(CharSequence charSequence) {
        this.f592a.setWindowTitle(charSequence);
    }

    public final Menu v() {
        if (!this.f595d) {
            this.f592a.i(new c(), new d());
            this.f595d = true;
        }
        return this.f592a.l();
    }

    public Window.Callback w() {
        return this.f594c;
    }

    public void x() {
        Menu menuV = v();
        b.b.e.j.g gVar = menuV instanceof b.b.e.j.g ? (b.b.e.j.g) menuV : null;
        if (gVar != null) {
            gVar.h0();
        }
        try {
            menuV.clear();
            if (!this.f594c.onCreatePanelMenu(0, menuV) || !this.f594c.onPreparePanel(0, null, menuV)) {
                menuV.clear();
            }
        } finally {
            if (gVar != null) {
                gVar.g0();
            }
        }
    }
}
