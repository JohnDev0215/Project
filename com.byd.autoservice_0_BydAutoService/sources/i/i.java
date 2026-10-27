package i;

import android.content.Context;
import android.content.res.Configuration;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.Toolbar;
import i.a;
import java.util.ArrayList;
import l0.t;
import n.g;
import n.m;
import o.f0;
import o.p;

/* JADX INFO: compiled from: ToolbarActionBar.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends i.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public p f3162a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f3163b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Window.Callback f3164c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3165d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3166e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList<a.b> f3167f = new ArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Runnable f3168g = new a();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Toolbar.f f3169h;

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
            return i.this.f3164c.onMenuItemSelected(0, menuItem);
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public final class c implements m.a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f3172b;

        public c() {
        }

        @Override // n.m.a
        public void a(n.g gVar, boolean z3) {
            if (this.f3172b) {
                return;
            }
            this.f3172b = true;
            i.this.f3162a.h();
            Window.Callback callback = i.this.f3164c;
            if (callback != null) {
                callback.onPanelClosed(108, gVar);
            }
            this.f3172b = false;
        }

        @Override // n.m.a
        public boolean b(n.g gVar) {
            Window.Callback callback = i.this.f3164c;
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

        @Override // n.g.a
        public boolean a(n.g gVar, MenuItem menuItem) {
            return false;
        }

        @Override // n.g.a
        public void b(n.g gVar) {
            i iVar = i.this;
            if (iVar.f3164c != null) {
                if (iVar.f3162a.c()) {
                    i.this.f3164c.onPanelClosed(108, gVar);
                } else if (i.this.f3164c.onPreparePanel(0, null, gVar)) {
                    i.this.f3164c.onMenuOpened(108, gVar);
                }
            }
        }
    }

    /* JADX INFO: compiled from: ToolbarActionBar.java */
    public class e extends m.i {
        public e(Window.Callback callback) {
            super(callback);
        }

        @Override // m.i, android.view.Window.Callback
        public View onCreatePanelView(int i3) {
            return i3 == 0 ? new View(i.this.f3162a.q()) : super.onCreatePanelView(i3);
        }

        @Override // m.i, android.view.Window.Callback
        public boolean onPreparePanel(int i3, View view, Menu menu) {
            boolean zOnPreparePanel = super.onPreparePanel(i3, view, menu);
            if (zOnPreparePanel) {
                i iVar = i.this;
                if (!iVar.f3163b) {
                    iVar.f3162a.f();
                    i.this.f3163b = true;
                }
            }
            return zOnPreparePanel;
        }
    }

    public i(Toolbar toolbar, CharSequence charSequence, Window.Callback callback) {
        b bVar = new b();
        this.f3169h = bVar;
        this.f3162a = new f0(toolbar, false);
        e eVar = new e(callback);
        this.f3164c = eVar;
        this.f3162a.setWindowCallback(eVar);
        toolbar.setOnMenuItemClickListener(bVar);
        this.f3162a.setWindowTitle(charSequence);
    }

    @Override // i.a
    public boolean g() {
        return this.f3162a.d();
    }

    @Override // i.a
    public boolean h() {
        if (!this.f3162a.u()) {
            return false;
        }
        this.f3162a.collapseActionView();
        return true;
    }

    @Override // i.a
    public void i(boolean z3) {
        if (z3 == this.f3166e) {
            return;
        }
        this.f3166e = z3;
        int size = this.f3167f.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.f3167f.get(i3).a(z3);
        }
    }

    @Override // i.a
    public int j() {
        return this.f3162a.j();
    }

    @Override // i.a
    public Context k() {
        return this.f3162a.q();
    }

    @Override // i.a
    public boolean l() {
        this.f3162a.o().removeCallbacks(this.f3168g);
        t.b0(this.f3162a.o(), this.f3168g);
        return true;
    }

    @Override // i.a
    public void m(Configuration configuration) {
        super.m(configuration);
    }

    @Override // i.a
    public void n() {
        this.f3162a.o().removeCallbacks(this.f3168g);
    }

    @Override // i.a
    public boolean o(int i3, KeyEvent keyEvent) {
        Menu menuV = v();
        if (menuV == null) {
            return false;
        }
        menuV.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuV.performShortcut(i3, keyEvent, 0);
    }

    @Override // i.a
    public boolean p(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            q();
        }
        return true;
    }

    @Override // i.a
    public boolean q() {
        return this.f3162a.e();
    }

    @Override // i.a
    public void r(boolean z3) {
    }

    @Override // i.a
    public void s(boolean z3) {
    }

    @Override // i.a
    public void t(CharSequence charSequence) {
        this.f3162a.setWindowTitle(charSequence);
    }

    public final Menu v() {
        if (!this.f3165d) {
            this.f3162a.i(new c(), new d());
            this.f3165d = true;
        }
        return this.f3162a.l();
    }

    public Window.Callback w() {
        return this.f3164c;
    }

    public void x() {
        Menu menuV = v();
        n.g gVar = menuV instanceof n.g ? (n.g) menuV : null;
        if (gVar != null) {
            gVar.h0();
        }
        try {
            menuV.clear();
            if (!this.f3164c.onCreatePanelMenu(0, menuV) || !this.f3164c.onPreparePanel(0, null, menuV)) {
                menuV.clear();
            }
        } finally {
            if (gVar != null) {
                gVar.g0();
            }
        }
    }
}
