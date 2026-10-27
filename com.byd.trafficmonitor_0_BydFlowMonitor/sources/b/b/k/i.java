package b.b.k;

import android.content.Context;
import android.content.res.Configuration;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.Toolbar;
import b.b.k.a;
import b.b.p.j.g;
import b.b.p.j.m;
import b.b.q.f0;
import b.b.q.p;
import b.h.m.t;
import java.util.ArrayList;

/* JADX INFO: compiled from: ToolbarActionBar.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends b.b.k.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public p f1453a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1454b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Window.Callback f1455c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1456d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1457e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList<a.b> f1458f = new ArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Runnable f1459g = new a();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Toolbar.f f1460h;

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
            return i.this.f1455c.onMenuItemSelected(0, menuItem);
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public final class c implements m.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1463b;

        public c() {
        }

        @Override // b.b.p.j.m.a
        public void a(b.b.p.j.g gVar, boolean z) {
            if (this.f1463b) {
                return;
            }
            this.f1463b = true;
            i.this.f1453a.h();
            Window.Callback callback = i.this.f1455c;
            if (callback != null) {
                callback.onPanelClosed(108, gVar);
            }
            this.f1463b = false;
        }

        @Override // b.b.p.j.m.a
        public boolean b(b.b.p.j.g gVar) {
            Window.Callback callback = i.this.f1455c;
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

        @Override // b.b.p.j.g.a
        public boolean a(b.b.p.j.g gVar, MenuItem menuItem) {
            return false;
        }

        @Override // b.b.p.j.g.a
        public void b(b.b.p.j.g gVar) {
            i iVar = i.this;
            if (iVar.f1455c != null) {
                if (iVar.f1453a.c()) {
                    i.this.f1455c.onPanelClosed(108, gVar);
                } else if (i.this.f1455c.onPreparePanel(0, null, gVar)) {
                    i.this.f1455c.onMenuOpened(108, gVar);
                }
            }
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public class e extends b.b.p.i {
        public e(Window.Callback callback) {
            super(callback);
        }

        @Override // b.b.p.i, android.view.Window.Callback
        public View onCreatePanelView(int i2) {
            return i2 == 0 ? new View(i.this.f1453a.q()) : super.onCreatePanelView(i2);
        }

        @Override // b.b.p.i, android.view.Window.Callback
        public boolean onPreparePanel(int i2, View view, Menu menu) {
            boolean zOnPreparePanel = super.onPreparePanel(i2, view, menu);
            if (zOnPreparePanel) {
                i iVar = i.this;
                if (!iVar.f1454b) {
                    iVar.f1453a.f();
                    i.this.f1454b = true;
                }
            }
            return zOnPreparePanel;
        }
    }

    public i(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        b bVar = new b();
        this.f1460h = bVar;
        this.f1453a = new f0(toolbar, false);
        e eVar = new e(callback);
        this.f1455c = eVar;
        this.f1453a.setWindowCallback(eVar);
        toolbar.setOnMenuItemClickListener(bVar);
        this.f1453a.setWindowTitle(charSequence);
    }

    @Override // b.b.k.a
    public boolean g() {
        return this.f1453a.d();
    }

    @Override // b.b.k.a
    public boolean h() {
        if (!this.f1453a.u()) {
            return false;
        }
        this.f1453a.collapseActionView();
        return true;
    }

    @Override // b.b.k.a
    public void i(boolean z) {
        if (z == this.f1457e) {
            return;
        }
        this.f1457e = z;
        int size = this.f1458f.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f1458f.get(i2).a(z);
        }
    }

    @Override // b.b.k.a
    public int j() {
        return this.f1453a.j();
    }

    @Override // b.b.k.a
    public Context k() {
        return this.f1453a.q();
    }

    @Override // b.b.k.a
    public boolean l() {
        this.f1453a.o().removeCallbacks(this.f1459g);
        t.c0(this.f1453a.o(), this.f1459g);
        return true;
    }

    @Override // b.b.k.a
    public void m(Configuration configuration) {
        super.m(configuration);
    }

    @Override // b.b.k.a
    public void n() {
        this.f1453a.o().removeCallbacks(this.f1459g);
    }

    @Override // b.b.k.a
    public boolean o(int i2, KeyEvent keyEvent) {
        Menu menuV = v();
        if (menuV == null) {
            return false;
        }
        menuV.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuV.performShortcut(i2, keyEvent, 0);
    }

    @Override // b.b.k.a
    public boolean p(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            q();
        }
        return true;
    }

    @Override // b.b.k.a
    public boolean q() {
        return this.f1453a.e();
    }

    @Override // b.b.k.a
    public void r(boolean z) {
    }

    @Override // b.b.k.a
    public void s(boolean z) {
    }

    @Override // b.b.k.a
    public void t(CharSequence charSequence) {
        this.f1453a.setWindowTitle(charSequence);
    }

    public final Menu v() {
        if (!this.f1456d) {
            this.f1453a.i(new c(), new d());
            this.f1456d = true;
        }
        return this.f1453a.l();
    }

    public Window.Callback w() {
        return this.f1455c;
    }

    public void x() {
        Menu menuV = v();
        b.b.p.j.g gVar = menuV instanceof b.b.p.j.g ? (b.b.p.j.g) menuV : null;
        if (gVar != null) {
            gVar.h0();
        }
        try {
            menuV.clear();
            if (!this.f1455c.onCreatePanelMenu(0, menuV) || !this.f1455c.onPreparePanel(0, null, menuV)) {
                menuV.clear();
            }
        } finally {
            if (gVar != null) {
                gVar.g0();
            }
        }
    }
}
