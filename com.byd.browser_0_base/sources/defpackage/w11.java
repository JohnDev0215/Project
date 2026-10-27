package defpackage;

import J.N;
import defpackage.aa2;
import defpackage.hj0;
import java.util.ArrayList;
import java.util.Arrays;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.components.navigation_interception.InterceptNavigationDelegate;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;
import org.chromium.url.Origin;

/* JADX INFO: compiled from: InterceptNavigationDelegateImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class w11 extends InterceptNavigationDelegate {
    public static final ArrayList f = new ArrayList(Arrays.asList("mdoc", "mdl-openid4vp", "mdoc-openid4vp"));
    public final s11 a;
    public WebContents b;
    public hj0 c;
    public boolean d;
    public boolean e;

    /* JADX INFO: compiled from: InterceptNavigationDelegateImpl.java */
    public class a implements Runnable {
        public final /* synthetic */ LoadUrlParams e;

        public a(LoadUrlParams loadUrlParams) {
            this.e = loadUrlParams;
        }

        @Override // java.lang.Runnable
        public final void run() {
            TabImpl tabImpl = ((u11) w11.this.a).a;
            if (tabImpl.C || tabImpl.m) {
                return;
            }
            tabImpl.b(this.e);
        }
    }

    public w11(u11 u11Var) {
        this.a = u11Var;
        a(u11Var.a.g);
    }

    public final void a(WebContents webContents) {
        if (this.b == webContents) {
            return;
        }
        this.b = webContents;
        if (webContents == null) {
            return;
        }
        if (this.c == null) {
            TabImpl tabImpl = ((u11) this.a).a;
            this.c = tabImpl.x.d(tabImpl);
        }
        N.Mjjyc5BV(this, this.b);
    }

    public final void b(GURL gurl, ij0 ij0Var) {
        if (N.MRiRQ_Ey(N.MWCIEpVs(5)) && ij0Var.p) {
            return;
        }
        LoadUrlParams loadUrlParams = new LoadUrlParams(gurl.j(), 0);
        if (!ij0Var.c.k()) {
            loadUrlParams.e = new on2(ij0Var.c.j(), 0);
        }
        loadUrlParams.n = ij0Var.r;
        loadUrlParams.b = (Origin) N.MWkeKQbk();
        PostTask.c(rh3.a, new a(loadUrlParams));
        this.e = true;
    }

    public final int c() {
        s11 s11Var = this.a;
        if (((u11) s11Var).a.g == null) {
            return -1;
        }
        return ((u11) s11Var).a.g.m().j();
    }

    public final boolean d() {
        u11 u11Var = (u11) this.a;
        WebContents webContents = u11Var.a.g;
        boolean z = false;
        if (webContents == null) {
            return false;
        }
        if (webContents.g().k()) {
            return true;
        }
        if (!(u11Var.a().c != null)) {
            return false;
        }
        aa2.c cVar = u11Var.a().c;
        if (cVar != null && cVar.a) {
            z = true;
        }
        return !z;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(org.chromium.url.GURL r6) {
        /*
            r5 = this;
            hj0 r0 = r5.c
            r0.getClass()
            java.lang.String r0 = r6.j()
            java.lang.String r1 = "wtai://wp/mc;"
            boolean r0 = r0.startsWith(r1)
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L14
            goto L2f
        L14:
            java.lang.String r0 = r6.j()     // Catch: java.lang.Exception -> L31
            android.content.Intent r0 = android.content.Intent.parseUri(r0, r2)     // Catch: java.lang.Exception -> L31
            java.lang.String r3 = r0.getPackage()
            if (r3 == 0) goto L23
            goto L2f
        L23:
            java.util.List r0 = defpackage.hj0.n(r0)
            if (r0 == 0) goto L39
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L39
        L2f:
            r0 = r2
            goto L3a
        L31:
            r0 = move-exception
            java.lang.String r3 = "UrlHandler"
            java.lang.String r4 = "Bad URI %s"
            defpackage.fa1.l(r3, r4, r6, r0)
        L39:
            r0 = r1
        L3a:
            if (r0 == 0) goto L3f
            int r0 = org.chromium.components.external_intents.R$string.blocked_navigation_warning
            goto L41
        L3f:
            int r0 = org.chromium.components.external_intents.R$string.unreachable_navigation_warning
        L41:
            s11 r5 = r5.a
            u11 r5 = (defpackage.u11) r5
            org.chromium.chrome.browser.tab.TabImpl r5 = r5.a
            org.chromium.content_public.browser.WebContents r5 = r5.g
            android.content.Context r3 = defpackage.xz.a
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r6 = r6.j()
            r2[r1] = r6
            java.lang.String r6 = r3.getString(r0, r2)
            r5.B(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.w11.e(org.chromium.url.GURL):void");
    }

    public final void f(GURL gurl, boolean z) {
        int i;
        u11 u11Var = (u11) this.a;
        if (u11Var.a.g == null) {
            return;
        }
        boolean z2 = z && d();
        boolean z3 = u11Var.a.getLaunchType() == 1;
        oj2.U(bk3.d(gurl) ? z3 ? 3 : 0 : bk3.e(gurl) ? z3 ? 5 : 2 : z3 ? 4 : 1, 6, "Android.Intent.MainFrameIntentLaunch");
        if (z2) {
            PostTask.c(rh3.a, new v11(this));
            return;
        }
        if ((u11Var.a().c != null) && c() > (i = u11Var.a().d)) {
            this.d = true;
            u11Var.a.g.m().w(i);
        }
    }

    public final hj0.d g(aa2 aa2Var, GURL gurl, int i, boolean z, boolean z2, boolean z3, GURL gurl2, boolean z4, boolean z5, Callback callback, boolean z6, boolean z7) {
        u11 u11Var = (u11) this.a;
        WebContents webContents = u11Var.a.g;
        boolean zN = webContents == null ? true : webContents.m().n();
        ChromeActivity<?> chromeActivityM = u11Var.a.M();
        aa2Var.a(i, z, z2, chromeActivityM == null ? -1L : chromeActivityM.R, c(), zN, z3);
        hj0.d dVarU = this.c.u(new ij0(gurl, u11Var.a.isIncognito(), gurl2, i, z, true, aa2Var, d(), !(u11Var.a.g.r() == 2), false, z4, z2, callback, z3, zN, z6, z7));
        String strConcat = "Android.TabNavigationInterceptResult.For".concat(z5 ? "ExternalProtocol" : "InternalProtocol");
        int i2 = dVarU.a;
        int i3 = 4;
        oj2.U(i2, 4, strConcat);
        if (i2 == 3) {
            i3 = 0;
        } else if (bk3.e(gurl)) {
            i3 = 2;
        } else if (bk3.d(gurl)) {
            i3 = 3;
        } else if (!f.contains(gurl.i())) {
            i3 = "openid4vp".equals(gurl.i()) ? 5 : 1;
        }
        oj2.U(i3, 6, "Android.TabNavigationIntercept.Scheme");
        return dVarU;
    }

    @Override // org.chromium.components.navigation_interception.InterceptNavigationDelegate
    public final GURL handleSubframeExternalProtocol(GURL gurl, int i, boolean z, Origin origin) {
        hj0.d dVarG = g(new aa2(), gurl, i, false, z, true, GURL.emptyGURL(), false, true, new se3(16, this), false, false);
        int i2 = dVarG.a;
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    return GURL.emptyGURL();
                }
                e(gurl);
                return null;
            }
            if (N.MRiRQ_Ey(N.MWCIEpVs(1))) {
                return dVarG.c;
            }
        }
        return null;
    }

    @Override // org.chromium.components.navigation_interception.InterceptNavigationDelegate
    public final void onResourceRequestWithGesture() {
        u11 u11Var = (u11) this.a;
        aa2 aa2VarA = u11Var.a();
        ChromeActivity<?> chromeActivityM = u11Var.a.M();
        aa2VarA.a(0, false, true, chromeActivityM == null ? -1L : chromeActivityM.R, c(), false, true);
    }

    @Override // org.chromium.components.navigation_interception.InterceptNavigationDelegate
    public final boolean shouldIgnoreNavigation(NavigationHandle navigationHandle, GURL gurl, boolean z, boolean z2) {
        aa2.c cVar;
        if (!navigationHandle.a) {
            throw new uv();
        }
        s11 s11Var = this.a;
        s11Var.getClass();
        aa2 aa2VarA = ((u11) s11Var).a();
        if (z && N.MRiRQ_Ey(N.MWCIEpVs(3)) && (cVar = aa2VarA.c) != null) {
            cVar.f.d = false;
        }
        hj0.d dVarG = g(aa2VarA, gurl, navigationHandle.d, navigationHandle.o, navigationHandle.n, navigationHandle.b, navigationHandle.f, navigationHandle.a, navigationHandle.p, new aq3(5, this), z, z2);
        int i = dVarG.a;
        if (i == 0) {
            f(gurl, true);
            return true;
        }
        if (i == 1) {
            b(dVarG.c, dVarG.d);
            return true;
        }
        if (i == 2) {
            return true;
        }
        if (!navigationHandle.p) {
            return false;
        }
        e(gurl);
        return true;
    }
}
