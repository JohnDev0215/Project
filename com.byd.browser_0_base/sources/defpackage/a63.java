package defpackage;

import J.N;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import java.io.File;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.paint_preview.services.PaintPreviewTabService;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.paintpreview.player.PlayerCompositorDelegateImpl;
import org.chromium.components.paintpreview.player.b;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.EventForwarder;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: TabbedPaintPreview.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a63 implements lk3 {
    public Tab e;
    public org.chromium.components.paintpreview.player.b i;
    public vj j;
    public Runnable k;
    public Callback<Boolean> l;
    public boolean m;
    public boolean n;
    public int o = -1;
    public final b g = new b();
    public final PaintPreviewTabService h = (PaintPreviewTabService) N.M$ZOxizP();
    public final a f = new a();

    /* JADX INFO: compiled from: TabbedPaintPreview.java */
    public class b implements j53 {
        public b() {
        }

        @Override // defpackage.j53
        public final void S() {
            a63 a63Var = a63.this;
            vj vjVar = a63Var.j;
            if (vjVar != null) {
                vjVar.u(a63Var.o);
                a63Var.o = -1;
            }
            a63Var.e(false);
        }

        @Override // defpackage.j53
        public final View getView() {
            org.chromium.components.paintpreview.player.b bVar = a63.this.i;
            if (bVar == null) {
                return null;
            }
            return bVar.e;
        }

        @Override // defpackage.j53
        public final int q0() {
            return 2;
        }

        @Override // defpackage.j53
        public final void r() {
            a63 a63Var = a63.this;
            vj vjVar = a63Var.j;
            if (vjVar != null && a63Var.o == -1) {
                a63Var.o = vjVar.v();
            }
            a63Var.e(true);
        }
    }

    public a63(Tab tab) {
        this.e = tab;
    }

    public static a63 a(Tab tab) {
        if (tab.B().b(a63.class) == null) {
            tab.B().d(a63.class, new a63(tab));
        }
        return (a63) tab.B().b(a63.class);
    }

    public final boolean b() {
        Tab tab = this.e;
        if (tab == null) {
            return false;
        }
        return tab.o().b(this.g);
    }

    public final boolean c(b.InterfaceC0137b interfaceC0137b) {
        boolean zMYa8QxsY;
        if (this.m) {
            return true;
        }
        TraceEvent.q("TabbedPaintPreview.maybeShow", null);
        if (!PaintPreviewTabService.c(this.e)) {
            TraceEvent.C("TabbedPaintPreview.maybeShow");
            return false;
        }
        int id = this.e.getId();
        PaintPreviewTabService paintPreviewTabService = this.h;
        long j = paintPreviewTabService.c;
        if (j == 0) {
            zMYa8QxsY = false;
        } else {
            if (j == 0 ? false : N.MWP3QaBv(j)) {
                zMYa8QxsY = N.MYa8QxsY(paintPreviewTabService.c, id);
            } else {
                long j2 = paintPreviewTabService.c;
                String strMPozT7P0 = j2 == 0 ? "" : N.MPozT7P0(j2);
                ku2 ku2VarT = ku2.t();
                try {
                    zMYa8QxsY = new File(strMPozT7P0, id + ".zip").exists();
                    ku2VarT.close();
                } catch (Throwable th) {
                    try {
                        ku2VarT.close();
                    } catch (Throwable unused) {
                    }
                    throw th;
                }
            }
        }
        if (!zMYa8QxsY) {
            TraceEvent.C("TabbedPaintPreview.maybeShow");
            return false;
        }
        this.e.x(this.f);
        N.MJ3oAy5s();
        this.i = new org.chromium.components.paintpreview.player.b(this.e.getUrl(), this.e.getContext(), this.h, String.valueOf(this.e.getId()), interfaceC0137b, yp.b(this.e.getContext(), false));
        TraceEvent.q("TabbedPaintPreview.maybeShow addTabViewProvider", null);
        this.e.o().a(this.g);
        TraceEvent.C("TabbedPaintPreview.maybeShow addTabViewProvider");
        this.m = true;
        TraceEvent.C("TabbedPaintPreview.maybeShow");
        return true;
    }

    public final void d(boolean z) {
        Point point;
        N.M9gwtxem();
        if (this.e == null || this.i == null || this.n) {
            return;
        }
        TraceEvent.q("TabbedPaintPreview.remove", null);
        this.n = true;
        w12 w12Var = this.i.d;
        if (w12Var != null) {
            w12Var.b();
        }
        this.e.t(this.f);
        org.chromium.components.paintpreview.player.b bVar = this.i;
        w12 w12Var2 = bVar.d;
        if (w12Var2 == null) {
            point = null;
        } else {
            Rect rectA = w12Var2.a.i.a();
            Point point2 = new Point(rectA.left, rectA.top);
            long j = bVar.b.b;
            if (j == 0) {
                point = new Point();
            } else {
                int[] iArrMMWNsnld = N.MMWNsnld(j);
                point = new Point(iArrMMWNsnld[0], iArrMMWNsnld[1]);
            }
            point.offset(point2.x, point2.y);
        }
        w12 w12Var3 = this.i.d;
        float fB = w12Var3 == null ? 0.0f : w12Var3.a.i.b();
        org.chromium.components.paintpreview.player.b bVar2 = this.i;
        WebContentsAccessibilityImpl webContentsAccessibilityImpl = bVar2.k;
        boolean z2 = webContentsAccessibilityImpl != null;
        if (webContentsAccessibilityImpl != null) {
            bVar2.d.d.setWebContentsAccessibility(null);
            bVar2.k.destroy();
            bVar2.k = null;
        }
        PlayerCompositorDelegateImpl playerCompositorDelegateImpl = bVar2.b;
        if (playerCompositorDelegateImpl != null) {
            long j2 = playerCompositorDelegateImpl.b;
            if (j2 != 0) {
                N.MIRVkfIx(j2);
                playerCompositorDelegateImpl.b = 0L;
            }
            bVar2.b = null;
        }
        w12 w12Var4 = bVar2.d;
        if (w12Var4 != null) {
            w12Var4.a();
            bVar2.d = null;
        }
        WebContents webContentsC = this.e.c();
        if (webContentsC != null && fB != 0.0f && point != null) {
            EventForwarder eventForwarderH = webContentsC.H();
            float f = fB / ((WebContentsImpl) webContentsC).l.g;
            long jUptimeMillis = SystemClock.uptimeMillis();
            eventForwarderH.c(12, jUptimeMillis, 0.0f);
            eventForwarderH.c(13, jUptimeMillis, f);
            eventForwarderH.c(14, jUptimeMillis, 0.0f);
            new Handler().postDelayed(new l91(eventForwarderH, 23, point), 10L);
        }
        this.g.getView().animate().alpha(0.0f).setDuration(z ? 500L : 0L).setListener(new b63(this, z2));
        e(false);
        Runnable runnable = this.k;
        if (runnable != null) {
            runnable.run();
        }
        TraceEvent.C("TabbedPaintPreview.remove");
    }

    @Override // defpackage.lk3
    public final void destroy() {
        this.e.t(this.f);
        this.e = null;
    }

    public final void e(boolean z) {
        Callback<Boolean> callback = this.l;
        if (callback == null) {
            return;
        }
        callback.onResult(Boolean.valueOf(z));
    }

    /* JADX INFO: compiled from: TabbedPaintPreview.java */
    public class a extends nh0 {
        public a() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void T0(int i, Tab tab) {
            a63 a63Var = a63.this;
            vj vjVar = a63Var.j;
            if (vjVar != null) {
                vjVar.u(a63Var.o);
                a63Var.o = -1;
            }
            a63Var.e(false);
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void l0(int i, Tab tab) {
            a63 a63Var = a63.this;
            if (a63Var.b()) {
                vj vjVar = a63Var.j;
                if (vjVar != null && a63Var.o == -1) {
                    a63Var.o = vjVar.v();
                }
                a63Var.e(true);
            }
        }

        @Override // defpackage.n23
        public final void P0(Tab tab, WindowAndroid windowAndroid) {
        }
    }
}
