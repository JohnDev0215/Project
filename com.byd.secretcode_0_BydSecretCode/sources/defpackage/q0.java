package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.Toolbar;
import defpackage.i0;
import defpackage.p1;
import defpackage.v1;
import java.util.ArrayList;

/* JADX INFO: compiled from: ToolbarActionBar.java */
/* JADX INFO: loaded from: classes.dex */
public class q0 extends i0 {
    public r2 a;
    public boolean b;
    public Window.Callback c;
    public boolean d;
    public boolean e;
    public ArrayList<i0.b> f = new ArrayList<>();
    public final Runnable g = new a();
    public final Toolbar.f h;

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            q0.this.x();
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public class b implements Toolbar.f {
        public b() {
        }

        @Override // androidx.appcompat.widget.Toolbar.f
        public boolean onMenuItemClick(MenuItem menuItem) {
            return q0.this.c.onMenuItemSelected(0, menuItem);
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public final class c implements v1.a {
        public boolean b;

        public c() {
        }

        @Override // v1.a
        public void a(p1 p1Var, boolean z) {
            if (this.b) {
                return;
            }
            this.b = true;
            q0.this.a.h();
            Window.Callback callback = q0.this.c;
            if (callback != null) {
                callback.onPanelClosed(108, p1Var);
            }
            this.b = false;
        }

        @Override // v1.a
        public boolean b(p1 p1Var) {
            Window.Callback callback = q0.this.c;
            if (callback == null) {
                return false;
            }
            callback.onMenuOpened(108, p1Var);
            return true;
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public final class d implements p1.a {
        public d() {
        }

        @Override // p1.a
        public boolean a(p1 p1Var, MenuItem menuItem) {
            return false;
        }

        @Override // p1.a
        public void b(p1 p1Var) {
            q0 q0Var = q0.this;
            if (q0Var.c != null) {
                if (q0Var.a.c()) {
                    q0.this.c.onPanelClosed(108, p1Var);
                } else if (q0.this.c.onPreparePanel(0, null, p1Var)) {
                    q0.this.c.onMenuOpened(108, p1Var);
                }
            }
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public class e extends i1 {
        public e(Window.Callback callback) {
            super(callback);
        }

        @Override // defpackage.i1, android.view.Window.Callback
        public View onCreatePanelView(int i) {
            return i == 0 ? new View(q0.this.a.q()) : super.onCreatePanelView(i);
        }

        @Override // defpackage.i1, android.view.Window.Callback
        public boolean onPreparePanel(int i, View view, Menu menu) {
            boolean zOnPreparePanel = super.onPreparePanel(i, view, menu);
            if (zOnPreparePanel) {
                q0 q0Var = q0.this;
                if (!q0Var.b) {
                    q0Var.a.f();
                    q0.this.b = true;
                }
            }
            return zOnPreparePanel;
        }
    }

    public q0(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        b bVar = new b();
        this.h = bVar;
        this.a = new h3(toolbar, false);
        e eVar = new e(callback);
        this.c = eVar;
        this.a.setWindowCallback(eVar);
        toolbar.setOnMenuItemClickListener(bVar);
        this.a.setWindowTitle(charSequence);
    }

    @Override // defpackage.i0
    public boolean g() {
        return this.a.d();
    }

    @Override // defpackage.i0
    public boolean h() {
        if (!this.a.u()) {
            return false;
        }
        this.a.collapseActionView();
        return true;
    }

    @Override // defpackage.i0
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

    @Override // defpackage.i0
    public int j() {
        return this.a.j();
    }

    @Override // defpackage.i0
    public Context k() {
        return this.a.q();
    }

    @Override // defpackage.i0
    public boolean l() {
        this.a.o().removeCallbacks(this.g);
        t9.b0(this.a.o(), this.g);
        return true;
    }

    @Override // defpackage.i0
    public void m(Configuration configuration) {
        super.m(configuration);
    }

    @Override // defpackage.i0
    public void n() {
        this.a.o().removeCallbacks(this.g);
    }

    @Override // defpackage.i0
    public boolean o(int i, KeyEvent keyEvent) {
        Menu menuV = v();
        if (menuV == null) {
            return false;
        }
        menuV.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuV.performShortcut(i, keyEvent, 0);
    }

    @Override // defpackage.i0
    public boolean p(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            q();
        }
        return true;
    }

    @Override // defpackage.i0
    public boolean q() {
        return this.a.e();
    }

    @Override // defpackage.i0
    public void r(boolean z) {
    }

    @Override // defpackage.i0
    public void s(boolean z) {
    }

    @Override // defpackage.i0
    public void t(CharSequence charSequence) {
        this.a.setWindowTitle(charSequence);
    }

    public final Menu v() {
        if (!this.d) {
            this.a.i(new c(), new d());
            this.d = true;
        }
        return this.a.l();
    }

    public Window.Callback w() {
        return this.c;
    }

    public void x() {
        Menu menuV = v();
        p1 p1Var = menuV instanceof p1 ? (p1) menuV : null;
        if (p1Var != null) {
            p1Var.h0();
        }
        try {
            menuV.clear();
            if (!this.c.onCreatePanelMenu(0, menuV) || !this.c.onPreparePanel(0, null, menuV)) {
                menuV.clear();
            }
        } finally {
            if (p1Var != null) {
                p1Var.g0();
            }
        }
    }
}
