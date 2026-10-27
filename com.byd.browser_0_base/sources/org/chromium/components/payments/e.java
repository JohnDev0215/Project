package org.chromium.components.payments;

import J.N;
import android.content.Context;
import android.text.TextUtils;
import defpackage.a8;
import defpackage.dh2;
import defpackage.hh2;
import defpackage.i22;
import defpackage.kh2;
import defpackage.ky1;
import defpackage.kz1;
import defpackage.lz1;
import defpackage.mk;
import defpackage.my1;
import defpackage.mz1;
import defpackage.n3;
import defpackage.nj2;
import defpackage.nw2;
import defpackage.ny1;
import defpackage.nz1;
import defpackage.oj2;
import defpackage.oy1;
import defpackage.pz1;
import defpackage.s31;
import defpackage.se1;
import defpackage.ty1;
import defpackage.ut1;
import defpackage.uy1;
import defpackage.vz1;
import defpackage.wz1;
import defpackage.xq;
import defpackage.yq;
import defpackage.zt1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.payments.ui.j;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.payments.PaymentApp;
import org.chromium.components.payments.g;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.Origin;

/* JADX INFO: compiled from: PaymentRequestService.java */
/* JADX INFO: loaded from: classes3.dex */
public final class e implements my1, oy1, PaymentRequestUpdateEventListener, PaymentApp.a, PaymentApp.b, g.a, mk {
    public static e M;
    public pz1 A;
    public yq B;
    public g C;
    public hh2 D;
    public kh2 E;
    public HashMap<String, lz1> F;
    public boolean G;
    public boolean H;
    public boolean I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f88J;
    public boolean K;
    public PaymentApp L;
    public final Runnable a;
    public final RenderFrameHost b;
    public final a c;
    public final nw2<PaymentAppServiceBridge> e;
    public WebContents f;
    public s31 g;
    public String h;
    public String i;
    public Origin j;
    public String k;
    public byte[][] l;
    public boolean m;
    public nz1 n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public int s;
    public PaymentRequestSpec t;
    public boolean v;
    public boolean w;
    public boolean x;
    public String y;
    public final ArrayList d = new ArrayList();
    public int z = 0;
    public boolean u = false;

    /* JADX INFO: compiled from: PaymentRequestService.java */
    public interface a {
        static Context a(RenderFrameHost renderFrameHost) {
            WebContents webContentsE0 = n3.e0(renderFrameHost);
            WindowAndroid windowAndroidV = webContentsE0 == null ? null : webContentsE0.V();
            if (windowAndroidV == null) {
                return null;
            }
            return windowAndroidV.i.get();
        }
    }

    public e(RenderFrameHost renderFrameHost, pz1 pz1Var, i22 i22Var, yq.a aVar, se1 se1Var) {
        this.b = renderFrameHost;
        this.A = pz1Var;
        this.a = i22Var;
        this.c = aVar;
        this.e = se1Var;
    }

    @Override // org.chromium.components.payments.PaymentRequestUpdateEventListener
    public final boolean a(ky1 ky1Var) {
        PaymentApp paymentApp;
        if (ky1Var == null || (paymentApp = this.L) == null || paymentApp.u() || !this.o || this.A == null) {
            return false;
        }
        r(ky1Var);
        return true;
    }

    @Override // defpackage.my1
    public final void b(boolean z) {
        this.H = z || this.I;
        if (this.f88J) {
            v();
        }
    }

    @Override // defpackage.my1
    public final void c(String str, int i) {
        if (TextUtils.isEmpty(this.y)) {
            this.y = str;
            this.z = i;
        }
    }

    @Override // org.chromium.components.payments.PaymentRequestUpdateEventListener
    public final boolean changePaymentMethodFromInvokedApp(String str, String str2) {
        PaymentApp paymentApp;
        pz1 pz1Var;
        if (TextUtils.isEmpty(str) || str2 == null || (paymentApp = this.L) == null || paymentApp.u() || (pz1Var = this.A) == null) {
            return false;
        }
        pz1Var.G0(str, str2);
        return true;
    }

    @Override // org.chromium.components.payments.PaymentRequestUpdateEventListener
    public final boolean changeShippingOptionFromInvokedApp(String str) {
        PaymentApp paymentApp;
        boolean z;
        if (TextUtils.isEmpty(str) || (paymentApp = this.L) == null || paymentApp.u() || !this.o || this.t.e() == null || this.A == null) {
            return false;
        }
        Iterator<wz1> it = this.t.e().iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            if (str.equals(it.next().b)) {
                z = true;
                break;
            }
        }
        if (!z) {
            return false;
        }
        this.A.W(str);
        return true;
    }

    @Override // defpackage.my1
    public final void d() {
        s31 s31Var = this.g;
        N.MsfXXC9d(s31Var.a, s31Var);
    }

    @Override // defpackage.my1
    public final void e(ny1 ny1Var) {
        mz1 mz1VarS;
        if (this.B == null) {
            return;
        }
        this.v = true;
        boolean z = this.G | this.I;
        this.G = z;
        WebContents webContentsE0 = n3.e0(((xq.a) this.c).a);
        this.G = z & (webContentsE0 != null && oj2.k(Profile.b(webContentsE0)).a("payments.can_make_payment_enabled"));
        yq yqVar = this.B;
        ArrayList arrayList = this.d;
        j jVar = yqVar.f;
        Collections.sort(arrayList, jVar.e);
        jVar.z = new dh2(4, (arrayList.isEmpty() || !((PaymentApp) arrayList.get(0)).f()) ? -1 : 0, new ArrayList(arrayList));
        jVar.y();
        nj2.a().getClass();
        nj2.a.add(jVar);
        arrayList.clear();
        s31 s31Var = this.g;
        int size = this.B.f.o().size();
        ArrayList arrayListO = this.B.f.o();
        N.MPFG5SwC(s31Var.a, s31Var, 1, size, !arrayListO.isEmpty() && ((PaymentApp) arrayListO.get(0)).a);
        if (this.w && (mz1VarS = s()) != null) {
            u(mz1VarS.a, mz1VarS.c, mz1VarS.b);
            return;
        }
        if (this.f88J) {
            v();
        }
        if (this.K) {
            w();
        }
    }

    @Override // defpackage.my1
    public final void g() {
        this.I = true;
    }

    @Override // defpackage.my1
    public final void i(PaymentApp paymentApp) {
        yq yqVar = this.B;
        if (yqVar == null) {
            return;
        }
        boolean z = yqVar.f.C;
        paymentApp.getClass();
        this.G |= paymentApp.q();
        if (paymentApp.j().contains("https://google.com/pay") || paymentApp.j().contains("https://android.com/pay")) {
            s31 s31Var = this.g;
            N.MH8h6Eyr(s31Var.a, s31Var, 1);
        } else {
            s31 s31Var2 = this.g;
            N.MH8h6Eyr(s31Var2.a, s31Var2, 4);
        }
        this.d.add(paymentApp);
    }

    public final void j(String str) {
        s31 s31Var = this.g;
        if (s31Var != null) {
            s31Var.a(2);
        }
        l(5, str);
    }

    public final void k() {
        if (this.u) {
            return;
        }
        this.u = true;
        M = null;
        hh2 hh2Var = this.D;
        if (hh2Var != null) {
            hh2Var.a();
            this.D = null;
        }
        kh2 kh2Var = this.E;
        if (kh2Var != null) {
            kh2Var.a();
            this.E = null;
        }
        yq yqVar = this.B;
        if (yqVar != null) {
            yqVar.a();
            this.B = null;
        }
        pz1 pz1Var = this.A;
        if (pz1Var != null) {
            pz1Var.close();
            this.A = null;
        }
        this.a.run();
        s31 s31Var = this.g;
        if (s31Var != null) {
            long j = s31Var.a;
            if (j != 0) {
                N.MK$_cVJA(j, s31Var);
                s31Var.a = 0L;
            }
        }
        PaymentRequestSpec paymentRequestSpec = this.t;
        if (paymentRequestSpec != null) {
            long j2 = paymentRequestSpec.a;
            if (j2 != 0) {
                N.MiX2Cegu(j2);
                paymentRequestSpec.a = 0L;
            }
        }
        uy1.a().getClass();
        Object obj = ThreadUtils.a;
        uy1.e = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l(int r3, java.lang.String r4) {
        /*
            r2 = this;
            pz1 r0 = r2.A
            if (r0 == 0) goto L2e
            java.lang.String r0 = "SecurePaymentConfirmationBrowser"
            boolean r0 = defpackage.a8.N(r0)
            if (r0 == 0) goto L21
            org.chromium.components.payments.PaymentRequestSpec r0 = r2.t
            if (r0 == 0) goto L21
            long r0 = r0.a
            boolean r0 = J.N.MdW7ZAFa(r0)
            if (r0 == 0) goto L21
            int r0 = r2.z
            r1 = 1
            if (r0 == r1) goto L21
            r0 = 7
            if (r3 == r0) goto L21
            goto L22
        L21:
            r1 = 0
        L22:
            pz1 r0 = r2.A
            if (r1 == 0) goto L27
            r3 = 6
        L27:
            if (r1 == 0) goto L2b
            java.lang.String r4 = "The operation either timed out or was not allowed. See: https://www.w3.org/TR/webauthn-2/#sctn-privacy-considerations-client."
        L2b:
            r0.onError(r3, r4)
        L2e:
            r2.k()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.payments.e.l(int, java.lang.String):void");
    }

    public final void m(PaymentApp paymentApp, g gVar) {
        int i = 1;
        if (paymentApp.k() == 1) {
            uy1 uy1VarA = uy1.a();
            ut1 ut1Var = new ut1();
            uy1VarA.getClass();
            Object obj = ThreadUtils.a;
            uy1VarA.b = this;
            uy1VarA.d = ut1Var;
            uy1VarA.c = zt1.c(64, ((org.chromium.components.payments.a) paymentApp).o);
        }
        this.C = gVar;
        s31 s31Var = this.g;
        N.MpLIjj0f(s31Var.a, s31Var, 3);
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        for (String str : paymentApp.j()) {
            if (this.t.a().containsKey(str)) {
                map.put(str, (lz1) this.t.a().get(str));
            }
            if (this.t.b().containsKey(str)) {
                map2.put(str, (ty1) this.t.b().get(str));
            }
        }
        int i2 = 0;
        nz1 nz1Var = new nz1(0);
        nz1Var.e = this.o && paymentApp.p();
        nz1Var.b = this.p && paymentApp.n();
        nz1Var.d = this.q && paymentApp.o();
        nz1Var.c = this.r && paymentApp.m();
        if (this.o && paymentApp.p()) {
            i2 = this.s;
        }
        nz1Var.f = i2;
        List<wz1> listE = paymentApp.p() ? this.t.e() : Collections.unmodifiableList(new ArrayList());
        String str2 = this.t.c().h;
        String str3 = this.k;
        String str4 = this.h;
        String str5 = this.i;
        byte[][] bArr = this.l;
        Map<String, lz1> mapUnmodifiableMap = Collections.unmodifiableMap(map);
        kz1 kz1Var = this.t.c().b;
        kz1[] kz1VarArr = this.t.c().c;
        paymentApp.r(str2, str3, str4, str5, bArr, mapUnmodifiableMap, kz1Var, Collections.unmodifiableList(kz1VarArr != null ? Arrays.asList(kz1VarArr) : new ArrayList()), Collections.unmodifiableMap(map2), nz1Var, listE, this);
        this.L = paymentApp;
        s31 s31Var2 = this.g;
        N.MWAyIl4$(s31Var2.a, s31Var2);
        Iterator<String> it = paymentApp.j().iterator();
        while (true) {
            if (!it.hasNext()) {
                i = 4;
                break;
            }
            String next = it.next();
            if (next.equals("https://android.com/pay") || next.equals("https://google.com/pay")) {
                break;
            }
            if (!next.equals("https://play.google.com/billing")) {
                if (next.equals("secure-payment-confirmation")) {
                    i = 3;
                    break;
                }
            } else {
                i = 2;
                break;
            }
        }
        s31 s31Var3 = this.g;
        N.M9Jdo06k(s31Var3.a, s31Var3, i);
    }

    public final void n(boolean z) {
        pz1 pz1Var = this.A;
        if (pz1Var != null) {
            pz1Var.g(z);
        }
        if (z) {
            this.g.a(1);
            k();
        }
    }

    public final void o(String str) {
        this.L = null;
        uy1.a().getClass();
        Object obj = ThreadUtils.a;
        uy1.e = null;
        yq yqVar = this.B;
        if (yqVar == null) {
            return;
        }
        if (yqVar.k) {
            this.g.a(0);
            l(1, str);
            return;
        }
        org.chromium.chrome.browser.payments.ui.e eVar = yqVar.f.x;
        if (eVar != null) {
            eVar.G = false;
            eVar.a(false);
            j.d dVar = eVar.i;
            dVar.b = true;
            dVar.a();
            eVar.m();
        }
    }

    public final void p(String str, String str2, PayerData payerData) {
        yq yqVar;
        org.chromium.chrome.browser.payments.ui.e eVar;
        if (this.C == null || (yqVar = this.B) == null) {
            return;
        }
        if (yqVar.k && (eVar = yqVar.f.x) != null) {
            eVar.G = true;
            eVar.a(true);
            j.d dVar = eVar.i;
            dVar.b = true;
            dVar.a();
        }
        s31 s31Var = this.g;
        N.MsVk_59O(s31Var.a, s31Var);
        this.C.a(str, str2, payerData, this);
    }

    public final void q(vz1 vz1Var) {
        this.B.getClass();
        if (vz1Var.b.equals("secure-payment-confirmation")) {
            vz1Var = this.L.w(vz1Var);
        }
        pz1 pz1Var = this.A;
        if (pz1Var != null) {
            pz1Var.m0(vz1Var);
        }
        this.C = null;
    }

    public final void r(ky1 ky1Var) {
        if (this.A != null) {
            if (a8.N("WebPaymentsRedactShippingAddress")) {
                ky1Var.i = "";
                ky1Var.k = "";
                ky1Var.j = "";
                ky1Var.c = new String[0];
            }
            this.A.e(ky1Var);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0362  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x03f7  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x03fe  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0102  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final defpackage.mz1 s() {
        /*
            Method dump skipped, instruction units count: 1042
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.payments.e.s():mz1");
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01d8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String t() {
        /*
            Method dump skipped, instruction units count: 583
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.payments.e.t():java.lang.String");
    }

    public final void u(int i, int i2, String str) {
        s31 s31Var = this.g;
        if (!s31Var.b) {
            s31Var.b = true;
            N.MPhEgSJd(s31Var.a, s31Var, i);
        }
        l(i2, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void v() {
        /*
            r5 = this;
            pz1 r0 = r5.A
            if (r0 != 0) goto L5
            return
        L5:
            r0 = 0
            r5.f88J = r0
            boolean r1 = r5.H
            r2 = 1
            if (r1 == 0) goto L30
            org.chromium.components.payments.e$a r1 = r5.c
            xq$a r1 = (xq.a) r1
            org.chromium.content_public.browser.RenderFrameHost r1 = r1.a
            org.chromium.content_public.browser.WebContents r1 = defpackage.n3.e0(r1)
            if (r1 == 0) goto L2b
            org.chromium.chrome.browser.profiles.Profile r1 = org.chromium.chrome.browser.profiles.Profile.b(r1)
            org.chromium.components.prefs.PrefService r1 = defpackage.oj2.k(r1)
            java.lang.String r3 = "payments.can_make_payment_enabled"
            boolean r1 = r1.a(r3)
            if (r1 == 0) goto L2b
            r1 = r2
            goto L2c
        L2b:
            r1 = r0
        L2c:
            if (r1 == 0) goto L30
            r1 = r2
            goto L31
        L30:
            r1 = r0
        L31:
            pz1 r3 = r5.A
            r4 = r1 ^ 1
            r3.V(r4)
            s31 r3 = r5.g
            if (r1 != 0) goto L40
            boolean r5 = r5.m
            if (r5 == 0) goto L41
        L40:
            r0 = r2
        L41:
            long r1 = r3.a
            J.N.MzcQanKX(r1, r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.payments.e.v():void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void w() {
        int i;
        if (this.A == null) {
            return;
        }
        boolean z = this.G;
        this.K = false;
        if (N.MNXjZ6_e(this.f, this.h, this.i, this.F)) {
            i = !z ? 1 : 0;
        } else {
            i = (this.f.isDestroyed() || !N.MSVZEfSr(this.f.g())) != false ? 2 : z ? 3 : 4;
        }
        this.A.i(i);
        s31 s31Var = this.g;
        N.Ma1hMajT(s31Var.a, s31Var, z || this.m);
    }

    @Override // defpackage.my1
    public final oy1 f() {
        return this;
    }

    @Override // defpackage.my1
    public final mk h() {
        return this;
    }
}
