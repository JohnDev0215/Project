package l;

import android.content.Context;
import android.content.res.Configuration;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import l.a;
import o0.t;
import q.g;
import q.m;
import r.f0;
import r.p;

/* JADX INFO: compiled from: ToolbarActionBar.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends l.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public p f5956a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f5957b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Window.Callback f5958c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f5959d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f5960e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList<a.b> f5961f = new ArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Runnable f5962g = new a();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Toolbar.f f5963h;

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
            return i.this.f5958c.onMenuItemSelected(0, menuItem);
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public final class c implements m.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f5966d;

        public c() {
        }

        @Override // q.m.a
        public void a(q.g gVar, boolean z7) {
            if (this.f5966d) {
                return;
            }
            this.f5966d = true;
            i.this.f5956a.h();
            Window.Callback callback = i.this.f5958c;
            if (callback != null) {
                callback.onPanelClosed(108, gVar);
            }
            this.f5966d = false;
        }

        @Override // q.m.a
        public boolean b(q.g gVar) {
            Window.Callback callback = i.this.f5958c;
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

        @Override // q.g.a
        public boolean a(q.g gVar, MenuItem menuItem) {
            return false;
        }

        @Override // q.g.a
        public void b(q.g gVar) {
            i iVar = i.this;
            if (iVar.f5958c != null) {
                if (iVar.f5956a.b()) {
                    i.this.f5958c.onPanelClosed(108, gVar);
                } else if (i.this.f5958c.onPreparePanel(0, null, gVar)) {
                    i.this.f5958c.onMenuOpened(108, gVar);
                }
            }
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public class e extends p.i {
        public e(Window.Callback callback) {
            super(callback);
        }

        @Override // p.i, android.view.Window.Callback
        public View onCreatePanelView(int i8) {
            return i8 == 0 ? new View(i.this.f5956a.n()) : super.onCreatePanelView(i8);
        }

        @Override // p.i, android.view.Window.Callback
        public boolean onPreparePanel(int i8, View view, Menu menu) {
            boolean zOnPreparePanel = super.onPreparePanel(i8, view, menu);
            if (zOnPreparePanel) {
                i iVar = i.this;
                if (!iVar.f5957b) {
                    iVar.f5956a.c();
                    i.this.f5957b = true;
                }
            }
            return zOnPreparePanel;
        }
    }

    public i(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        b bVar = new b();
        this.f5963h = bVar;
        this.f5956a = new f0(toolbar, false);
        e eVar = new e(callback);
        this.f5958c = eVar;
        this.f5956a.setWindowCallback(eVar);
        toolbar.setOnMenuItemClickListener(bVar);
        this.f5956a.setWindowTitle(charSequence);
    }

    @Override // l.a
    public boolean g() {
        return this.f5956a.e();
    }

    @Override // l.a
    public boolean h() {
        if (!this.f5956a.o()) {
            return false;
        }
        this.f5956a.collapseActionView();
        return true;
    }

    @Override // l.a
    public void i(boolean z7) {
        if (z7 == this.f5960e) {
            return;
        }
        this.f5960e = z7;
        int size = this.f5961f.size();
        for (int i8 = 0; i8 < size; i8++) {
            this.f5961f.get(i8).a(z7);
        }
    }

    @Override // l.a
    public int j() {
        return this.f5956a.q();
    }

    @Override // l.a
    public Context k() {
        return this.f5956a.n();
    }

    @Override // l.a
    public boolean l() {
        this.f5956a.l().removeCallbacks(this.f5962g);
        t.d0(this.f5956a.l(), this.f5962g);
        return true;
    }

    @Override // l.a
    public void m(Configuration configuration) {
        super.m(configuration);
    }

    @Override // l.a
    public void n() {
        this.f5956a.l().removeCallbacks(this.f5962g);
    }

    @Override // l.a
    public boolean o(int i8, KeyEvent keyEvent) {
        Menu menuV = v();
        if (menuV == null) {
            return false;
        }
        menuV.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuV.performShortcut(i8, keyEvent, 0);
    }

    @Override // l.a
    public boolean p(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            q();
        }
        return true;
    }

    @Override // l.a
    public boolean q() {
        return this.f5956a.f();
    }

    @Override // l.a
    public void r(boolean z7) {
    }

    @Override // l.a
    public void s(boolean z7) {
    }

    @Override // l.a
    public void t(CharSequence charSequence) {
        this.f5956a.setWindowTitle(charSequence);
    }

    public final Menu v() {
        if (!this.f5959d) {
            this.f5956a.i(new c(), new d());
            this.f5959d = true;
        }
        return this.f5956a.r();
    }

    public Window.Callback w() {
        return this.f5958c;
    }

    public void x() {
        Menu menuV = v();
        q.g gVar = menuV instanceof q.g ? (q.g) menuV : null;
        if (gVar != null) {
            gVar.h0();
        }
        try {
            menuV.clear();
            if (!this.f5958c.onCreatePanelMenu(0, menuV) || !this.f5958c.onPreparePanel(0, null, menuV)) {
                menuV.clear();
            }
        } finally {
            if (gVar != null) {
                gVar.g0();
            }
        }
    }
}
