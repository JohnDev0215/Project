package defpackage;

import J.N;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import defpackage.rq0;
import defpackage.tq0;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabUtils;
import org.chromium.components.embedder_support.view.ContentView;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: FullscreenHtmlApiHandler.java */
/* JADX INFO: loaded from: classes.dex */
public final class qq0 implements ApplicationStatus.c, ApplicationStatus.g, View.OnSystemUiVisibilityChangeListener, rq0 {
    public final Activity e;
    public final a f;
    public final aq1<Boolean> g;
    public final zp1<Boolean> h;
    public final boolean i;
    public final b<rq0.a> j = new b<>();
    public WebContents k;
    public ContentView l;
    public Tab m;
    public sq0 n;
    public tq0 o;
    public View.OnLayoutChangeListener p;
    public sq0 q;
    public mq0 r;
    public nq0 s;
    public Tab t;
    public ContentView u;

    /* JADX INFO: compiled from: FullscreenHtmlApiHandler.java */
    public static class a extends Handler {
        public final WeakReference<qq0> a;

        /* JADX INFO: renamed from: qq0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: FullscreenHtmlApiHandler.java */
        public class ViewOnLayoutChangeListenerC0165a implements View.OnLayoutChangeListener {
            public final /* synthetic */ View e;

            public ViewOnLayoutChangeListenerC0165a(View view) {
                this.e = view;
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                a.this.sendEmptyMessageDelayed(2, 20L);
                this.e.removeOnLayoutChangeListener(this);
            }
        }

        public a(qq0 qq0Var) {
            this.a = new WeakReference<>(qq0Var);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            qq0 qq0Var;
            ContentView contentView;
            if (message == null || (qq0Var = this.a.get()) == null || qq0Var.k == null || (contentView = qq0Var.l) == null) {
                return;
            }
            int systemUiVisibility = contentView.getSystemUiVisibility();
            int i = message.what;
            if (i == 1) {
                sq0 sq0Var = qq0Var.n;
                if (!(((systemUiVisibility & 4) != 4) == sq0Var.b && ((systemUiVisibility & 2) != 2) == sq0Var.a)) {
                    systemUiVisibility = qq0Var.j(systemUiVisibility);
                    contentView.setSystemUiVisibility(systemUiVisibility);
                }
                if ((systemUiVisibility & 1024) == 0) {
                    return;
                }
                contentView.addOnLayoutChangeListener(new ViewOnLayoutChangeListenerC0165a(contentView));
                ao3.e(contentView, "FullscreenHtmlApiHandler.FullscreenHandler.handleMessage");
                return;
            }
            if (i == 2 && qq0Var.g()) {
                contentView.setSystemUiVisibility(systemUiVisibility & (-1025));
                Window window = qq0Var.e.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int i2 = attributes.flags;
                if ((67108864 & i2) != 0) {
                    attributes.flags = i2 & (-67108865);
                    window.setAttributes(attributes);
                }
            }
        }
    }

    public qq0(Activity activity, aq1 aq1Var) {
        this.e = activity;
        this.h = aq1Var;
        aq1Var.p(new dm0(7, this));
        this.f = new a(this);
        aq1<Boolean> aq1Var2 = new aq1<>();
        this.g = aq1Var2;
        aq1Var2.r(Boolean.FALSE);
        this.i = false;
    }

    public static void n(Tab tab, tf3 tf3Var) {
        ly2 ly2VarA = ly2.a(tab);
        if (tf3Var == null) {
            ly2VarA.e.remove("EnterFullscreen");
        } else {
            ly2VarA.c(tf3Var, "EnterFullscreen");
        }
    }

    @Override // defpackage.rq0
    public final void a(sq0 sq0Var, Tab tab) {
        int i = 1;
        if (g() && (Objects.equals(this.n, sq0Var) || Objects.equals(this.q, sq0Var))) {
            return;
        }
        tf3 tf3Var = new tf3(this, i, sq0Var, tab);
        if (tab.isUserInteractable()) {
            tf3Var.run();
        } else {
            n(tab, tf3Var);
        }
    }

    @Override // defpackage.rq0
    public final void b(Tab tab) {
        if (tab != this.t) {
            return;
        }
        n(tab, null);
        boolean zG = g();
        i();
        if (!zG) {
            return;
        }
        Iterator<rq0.a> it = this.j.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((rq0.a) aVar.next()).b(tab);
            }
        }
    }

    @Override // defpackage.rq0
    public final void c(rq0.a aVar) {
        this.j.c(aVar);
    }

    @Override // org.chromium.base.ApplicationStatus.c
    public final void d(Activity activity, int i) {
        if (i == 5 && this.i) {
            i();
            return;
        }
        if (i == 6) {
            ApplicationStatus.i(this);
            b<ApplicationStatus.g> bVar = ApplicationStatus.g;
            if (bVar == null) {
                return;
            }
            bVar.c(this);
        }
    }

    @Override // org.chromium.base.ApplicationStatus.g
    public final void e(Activity activity, boolean z) {
        if (this.e != activity) {
            return;
        }
        tq0 tq0Var = this.o;
        if (tq0Var != null) {
            tq0Var.onWindowFocusChanged(z);
        }
        a aVar = this.f;
        aVar.removeMessages(1);
        aVar.removeMessages(2);
        if (this.m != null && g() && z) {
            aVar.sendEmptyMessageDelayed(1, 200L);
        }
    }

    @Override // defpackage.rq0
    public final aq1 f() {
        return this.g;
    }

    @Override // defpackage.rq0
    public final boolean g() {
        return this.g.f.booleanValue();
    }

    @Override // defpackage.rq0
    public final void h(rq0.a aVar) {
        this.j.a(aVar);
    }

    @Override // defpackage.rq0
    public final void i() {
        if (g()) {
            l().a();
            this.g.r(Boolean.FALSE);
            WebContents webContents = this.k;
            if (webContents == null || this.m == null) {
                sq0 sq0Var = this.q;
                if (sq0Var != null) {
                    sq0Var.c = true;
                }
                if (this.h.get().booleanValue()) {
                    py2.d(this.t, true);
                }
            } else {
                ContentView contentView = this.l;
                l().c();
                a aVar = this.f;
                aVar.removeMessages(1);
                aVar.removeMessages(2);
                int systemUiVisibility = contentView.getSystemUiVisibility() & (-5640);
                Activity activity = this.e;
                Window window = activity.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int i = attributes.flags;
                if ((67108864 & i) != 0) {
                    attributes.flags = i & (-67108865);
                    window.setAttributes(attributes);
                }
                contentView.setSystemUiVisibility(systemUiVisibility);
                View.OnLayoutChangeListener onLayoutChangeListener = this.p;
                if (onLayoutChangeListener != null) {
                    contentView.removeOnLayoutChangeListener(onLayoutChangeListener);
                }
                oq0 oq0Var = new oq0(this, contentView);
                this.p = oq0Var;
                contentView.addOnLayoutChangeListener(oq0Var);
                if (!webContents.isDestroyed()) {
                    webContents.l();
                }
                if (!z7.M(activity)) {
                    activity.getWindow().setNavigationBarColor(0);
                }
            }
            this.k = null;
            this.l = null;
            this.m = null;
            this.n = null;
        }
        o(true);
    }

    public final int j(int i) {
        sq0 sq0Var = this.n;
        boolean z = sq0Var != null ? sq0Var.a : false;
        boolean z2 = sq0Var != null ? sq0Var.b : false;
        int i2 = (z2 || z) ? 4096 : 4097;
        if (!z) {
            i2 = i2 | 2 | 512;
        }
        if (!z2) {
            i2 = i2 | 4 | 1024;
        }
        return i2 | i;
    }

    public final void k(sq0 sq0Var, Tab tab) {
        WebContents webContentsC = tab.c();
        if (webContentsC == null) {
            return;
        }
        this.n = sq0Var;
        ContentView contentViewD = tab.d();
        int systemUiVisibility = contentViewD.getSystemUiVisibility();
        int i = systemUiVisibility & 4;
        boolean z = false;
        if (i == 4 || (systemUiVisibility & 2) == 2) {
            sq0 sq0Var2 = this.n;
            boolean z2 = sq0Var2.b;
            boolean z3 = i != 4;
            boolean z4 = (systemUiVisibility & 2) != 2;
            if (z3 == z2 && z4 == sq0Var2.a) {
                z = true;
            }
            if (z) {
                return;
            } else {
                systemUiVisibility = j(systemUiVisibility & (-5640));
            }
        } else if ((systemUiVisibility & 1024) == 1024 || (systemUiVisibility & 512) == 512) {
            systemUiVisibility = j(systemUiVisibility);
        } else {
            Activity activityB = TabUtils.b(tab);
            vj1.h.getClass();
            boolean zO = vj1.o(activityB);
            if (!this.n.b && !zO) {
                Window window = this.e.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.flags |= 67108864;
                window.setAttributes(attributes);
            }
            sq0 sq0Var3 = this.n;
            if (!sq0Var3.a) {
                systemUiVisibility |= 512;
            }
            if (!sq0Var3.b) {
                systemUiVisibility |= 1024;
            }
        }
        View.OnLayoutChangeListener onLayoutChangeListener = this.p;
        if (onLayoutChangeListener != null) {
            contentViewD.removeOnLayoutChangeListener(onLayoutChangeListener);
        }
        pq0 pq0Var = new pq0(this, contentViewD);
        this.p = pq0Var;
        contentViewD.addOnLayoutChangeListener(pq0Var);
        contentViewD.setSystemUiVisibility(systemUiVisibility);
        ao3.e(contentViewD, "FullscreenHtmlApiHandler.enterFullScreen");
        this.k = webContentsC;
        this.l = contentViewD;
        this.m = tab;
        l().b();
    }

    public final tq0 l() {
        if (this.o == null) {
            sk skVar = bq.a;
            if (N.M09VlOh_("AndroidWidgetFullscreenToast")) {
                this.o = new tq0.a(this.e, new t20(3, this));
            } else {
                this.o = new tq0.b(this.e, this.f, new hs(5, this), new yq2(11, this), new p11(6, this));
            }
        }
        return this.o;
    }

    public final void m(ContentView contentView) {
        ContentView contentView2 = this.u;
        if (contentView == contentView2) {
            return;
        }
        if (contentView2 != null) {
            contentView2.removeOnSystemUiVisibilityChangeListener(this);
        }
        this.u = contentView;
        if (contentView != null) {
            contentView.addOnSystemUiVisibilityChangeListener(this);
        }
    }

    public final void o(boolean z) {
        WebContents webContentsC;
        GestureListenerManagerImpl gestureListenerManagerImplE;
        Tab tab = this.t;
        if (tab == null || tab.isHidden() || (webContentsC = this.t.c()) == null || (gestureListenerManagerImplE = GestureListenerManagerImpl.e(webContentsC)) == null) {
            return;
        }
        long j = gestureListenerManagerImplE.m;
        if (j == 0) {
            return;
        }
        N.M6a5zchR(j, gestureListenerManagerImplE, z);
    }

    @Override // android.view.View.OnSystemUiVisibilityChangeListener
    public final void onSystemUiVisibilityChange(int i) {
        if (this.m == null || !g()) {
            return;
        }
        this.f.sendEmptyMessageDelayed(1, 200L);
    }
}
