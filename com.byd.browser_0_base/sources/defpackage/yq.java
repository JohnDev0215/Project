package defpackage;

import J.N;
import defpackage.xq;
import java.util.HashMap;
import java.util.Iterator;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.payments.ui.j;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.components.payments.PaymentApp;
import org.chromium.components.payments.PaymentHandlerHost;
import org.chromium.components.payments.PaymentRequestSpec;
import org.chromium.components.payments.e;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: ChromePaymentRequestService.java */
/* JADX INFO: loaded from: classes2.dex */
public final class yq implements j.c {
    public e a;
    public final RenderFrameHost b;
    public final a c;
    public final WebContents d;
    public final s31 e;
    public final j f;
    public boolean g;
    public boolean h;
    public PaymentRequestSpec i;
    public PaymentHandlerHost j;
    public boolean k;

    /* JADX INFO: compiled from: ChromePaymentRequestService.java */
    public interface a extends e.a {
    }

    public yq(e eVar, xq.a aVar) {
        this.a = eVar;
        this.b = eVar.b;
        this.c = aVar;
        WebContents webContents = eVar.f;
        this.d = webContents;
        s31 s31Var = eVar.g;
        this.e = s31Var;
        this.f = new j(this, eVar, webContents, eVar.m, s31Var, eVar.h);
    }

    public final void a() {
        if (this.h) {
            return;
        }
        this.h = true;
        e eVar = this.a;
        if (eVar != null) {
            eVar.k();
            this.a = null;
        }
        j jVar = this.f;
        jVar.getClass();
        yy1 yy1Var = jVar.l;
        if (yy1Var != null) {
            xy1 xy1Var = yy1Var.a;
            if (xy1Var != null) {
                xy1Var.run();
                yy1Var.a = null;
            }
            jVar.l = null;
        }
        org.chromium.chrome.browser.payments.ui.e eVar2 = jVar.x;
        if (eVar2 != null) {
            eVar2.H = true;
            eVar2.b(false);
            yq yqVar = (yq) jVar.o;
            yqVar.c.getClass();
            ChromeActivity chromeActivityU2 = ChromeActivity.u2(yqVar.d);
            r2 r2Var = chromeActivityU2 == null ? null : chromeActivityU2.K;
            if (r2Var != null) {
                r2Var.e(jVar.x);
            }
            jVar.x = null;
            jVar.u.b = false;
        }
        if (jVar.z != null) {
            Iterator it = jVar.o().iterator();
            while (it.hasNext()) {
                ((PaymentApp) it.next()).g();
            }
            jVar.z = null;
        }
        nj2.a().getClass();
        nj2.a.remove(jVar);
        y13 y13Var = jVar.E;
        if (y13Var != null) {
            y13Var.l(jVar.i);
            jVar.E = null;
        }
        TabModel tabModel = jVar.F;
        if (tabModel != null) {
            tabModel.l(jVar.j);
            jVar.F = null;
        }
        z51 z51Var = jVar.G;
        if (z51Var != null) {
            z51Var.f(jVar);
            jVar.G = null;
        }
        HashMap map = jVar.s;
        Iterator it2 = map.values().iterator();
        while (it2.hasNext()) {
            ((g20) it2.next()).a();
        }
        map.clear();
        PaymentHandlerHost paymentHandlerHost = this.j;
        if (paymentHandlerHost != null) {
            N.MDWZVETg(paymentHandlerHost.a);
            paymentHandlerHost.a = 0L;
            this.j = null;
        }
    }

    public final void b(String str) {
        e eVar = this.a;
        if (eVar != null) {
            eVar.l(1, str);
        }
        a();
    }

    public final boolean c(lf0 lf0Var, lf0 lf0Var2, PaymentApp paymentApp) {
        PaymentRequestSpec paymentRequestSpec;
        e eVar = this.a;
        if (eVar != null && (paymentRequestSpec = this.i) != null) {
            if (!(paymentRequestSpec.a == 0)) {
                if (this.j == null) {
                    this.c.getClass();
                    this.j = new PaymentHandlerHost(this.d, eVar);
                }
                paymentApp.x(this.j);
                zx zxVar = this.f.B;
                this.a.m(paymentApp, new zq(lf0Var, lf0Var2, zxVar != null ? (ra) zxVar.b() : null, paymentApp, this.i.d()));
                return true;
            }
        }
        return false;
    }
}
