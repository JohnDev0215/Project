package defpackage;

import android.content.res.Resources;
import android.os.SystemClock;
import defpackage.ms2;
import java.util.HashMap;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.paintpreview.player.b;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.ui.widget.Toast;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: StartupPaintPreview.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hs2 implements b.InterfaceC0137b {
    public final Tab a;
    public final ms2 b = new ms2();
    public final a63 c;
    public Runnable d;
    public gs2 e;
    public final a f;
    public int g;
    public boolean h;
    public boolean i;
    public int j;
    public boolean k;
    public long l;
    public nw2<Boolean> m;
    public nw2<Boolean> n;

    /* JADX INFO: compiled from: StartupPaintPreview.java */
    public class a extends nh0 {
        public a() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void C0(TabImpl tabImpl, NavigationHandle navigationHandle) {
            hs2 hs2Var = hs2.this;
            if (hs2Var.i) {
                hs2Var.k(5);
            }
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void F0(Tab tab) {
            hs2.this.k(6);
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void T0(int i, Tab tab) {
            hs2 hs2Var = hs2.this;
            hs2Var.j();
            hs2Var.k(7);
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void U0() {
            hs2.this.i = true;
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void a1(Tab tab, GURL gurl) {
            hs2 hs2Var = hs2.this;
            nw2<Boolean> nw2Var = hs2Var.n;
            if (nw2Var == null || !nw2Var.get().booleanValue()) {
                return;
            }
            hs2Var.k(8);
        }
    }

    public hs2(Tab tab, vj vjVar, zc0 zc0Var, td2 td2Var) {
        this.a = tab;
        a63 a63VarA = a63.a(tab);
        this.c = a63VarA;
        a63VarA.j = vjVar;
        a63VarA.l = td2Var;
        a63VarA.k = zc0Var;
        a aVar = new a();
        this.f = aVar;
        this.g = 0;
        tab.x(aVar);
    }

    @Override // org.chromium.components.paintpreview.player.b.InterfaceC0137b
    public final boolean a() {
        return qo.f().c();
    }

    @Override // org.chromium.components.paintpreview.player.b.InterfaceC0137b
    public final void b() {
        if (a()) {
            k(9);
        }
    }

    @Override // org.chromium.components.paintpreview.player.b.InterfaceC0137b
    public final void c() {
        Tab tab = this.a;
        if (tab == null || tab.D() == null || this.k || xp2.a(tab.D()) == null) {
            return;
        }
        if (this.e == null) {
            this.e = new gs2(this);
        }
        Resources resources = tab.getContext().getResources();
        tp2 tp2VarA = tp2.a(resources.getString(R$string.paint_preview_startup_upgrade_snackbar_message), this.e, 1, 36);
        tp2VarA.d = resources.getString(R$string.paint_preview_startup_upgrade_snackbar_action);
        tp2VarA.e = null;
        tp2VarA.j = 8000;
        xp2.a(tab.D()).c(tp2VarA);
        this.k = true;
        this.j++;
    }

    @Override // org.chromium.components.paintpreview.player.b.InterfaceC0137b
    public final void d() {
        k(0);
    }

    @Override // org.chromium.components.paintpreview.player.b.InterfaceC0137b
    public final void e() {
        if (this.g != 1) {
            return;
        }
        long j = this.l;
        nw2<Boolean> nw2Var = this.m;
        ms2 ms2Var = this.b;
        ms2Var.b = true;
        org.chromium.base.b<ms2.a> bVar = ms2Var.c;
        if (nw2Var != null && nw2Var.get().booleanValue()) {
            long jElapsedRealtime = SystemClock.elapsedRealtime() - j;
            oj2.X(jElapsedRealtime, "Browser.PaintPreview.TabbedPlayer.TimeToFirstBitmap");
            Iterator<ms2.a> it = bVar.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    return;
                } else {
                    ((ms2.a) aVar.next()).b(jElapsedRealtime);
                }
            }
        } else {
            if (nw2Var == null) {
                return;
            }
            Iterator<ms2.a> it2 = bVar.iterator();
            while (true) {
                b.a aVar2 = (b.a) it2;
                if (!aVar2.hasNext()) {
                    return;
                } else {
                    ((ms2.a) aVar2.next()).a();
                }
            }
        }
    }

    @Override // org.chromium.components.paintpreview.player.b.InterfaceC0137b
    public final void f(int i) {
        if (this.g != 1) {
            return;
        }
        this.b.getClass();
        oj2.U(i, 16, "Browser.PaintPreview.TabbedPlayer.CompositorFailureReason");
        k(2);
    }

    @Override // org.chromium.components.paintpreview.player.b.InterfaceC0137b
    public final void g(GURL gurl) {
        Tab tab = this.a;
        if (tab == null || !gurl.b || gurl.k()) {
            return;
        }
        tab.b(new LoadUrlParams(gurl.j(), 0));
        k(4);
    }

    @Override // org.chromium.components.paintpreview.player.b.InterfaceC0137b
    public final void i() {
        if (this.h) {
            k(3);
            return;
        }
        ms2 ms2Var = this.b;
        ms2Var.getClass();
        ms2Var.a = System.currentTimeMillis();
    }

    public final void j() {
        Tab tab;
        wp2 wp2VarA;
        if (this.e == null || (tab = this.a) == null || tab.D() == null || (wp2VarA = xp2.a(tab.D())) == null) {
            return;
        }
        wp2VarA.a(this.e);
    }

    public final void k(int i) {
        Runnable runnable = this.d;
        if (runnable != null) {
            runnable.run();
        }
        this.d = null;
        Tab tab = this.a;
        tab.t(this.f);
        int i2 = this.g;
        this.g = 2;
        if (i2 != 1) {
            return;
        }
        this.c.d(i == 3 || i == 1 || i == 0);
        if (i == 3 && !tab.isHidden()) {
            Toast.c(tab.getContext(), R$string.paint_preview_startup_auto_upgrade_toast, 0).e();
        }
        j();
        int i3 = this.j;
        ms2 ms2Var = this.b;
        ms2Var.getClass();
        if (i == 1) {
            n3.X0("PaintPreview.TabbedPlayer.Actionbar.Action");
        }
        n3.X0("PaintPreview.TabbedPlayer.Removed");
        oj2.R(i3, "Browser.PaintPreview.TabbedPlayer.SnackbarCount");
        oj2.U(i, 10, "Browser.PaintPreview.TabbedPlayer.ExitCause");
        if (ms2Var.a != 0) {
            HashMap map = ms2.d;
            if (map.containsKey(Integer.valueOf(i))) {
                oj2.X(System.currentTimeMillis() - ms2Var.a, (String) map.get(Integer.valueOf(i)));
            }
        }
    }

    @Override // org.chromium.components.paintpreview.player.b.InterfaceC0137b
    public final void h() {
    }
}
