package org.chromium.chrome.browser.toolbar;

import J.N;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.LruCache;
import defpackage.a63;
import defpackage.a8;
import defpackage.bc3;
import defpackage.bk3;
import defpackage.br1;
import defpackage.i83;
import defpackage.jg2;
import defpackage.jk1;
import defpackage.km1;
import defpackage.kp2;
import defpackage.l61;
import defpackage.n3;
import defpackage.oj2;
import defpackage.pb0;
import defpackage.ph3;
import defpackage.qh1;
import defpackage.qp;
import defpackage.rj3;
import defpackage.rr1;
import defpackage.uj3;
import defpackage.yp;
import defpackage.z7;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.omnibox.f;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TrustedCdn;
import org.chromium.components.omnibox.OmniboxUrlEmphasizer;
import org.chromium.components.omnibox.R$string;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class LocationBarModel implements bc3, org.chromium.chrome.browser.omnibox.f {
    public final Context a;
    public final km1 b;
    public final d c;
    public final b d;
    public final a e;
    public final jg2 f;
    public qp g;
    public Profile h;
    public LruCache<c, SpannableStringBuilder> i;
    public boolean j;
    public Tab k;
    public int l;
    public l61 m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public int s;
    public long t;
    public final org.chromium.base.b<f.a> u = new org.chromium.base.b<>();
    public GURL v = GURL.emptyGURL();
    public String w;
    public String x;
    public boolean y;

    public interface a {
    }

    @FunctionalInterface
    public interface b {
    }

    public static class c {
        public final String a;
        public final String b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;
        public final int g;

        public c(String str, String str2, int i, int i2, int i3, int i4, int i5) {
            this.a = str;
            this.b = str2;
            this.c = i;
            this.d = i2;
            this.e = i3;
            this.f = i4;
            this.g = i5;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || c.class != obj.getClass()) {
                return false;
            }
            c cVar = (c) obj;
            return this.c == cVar.c && this.d == cVar.d && this.e == cVar.e && this.f == cVar.f && this.g == cVar.g && this.a.equals(cVar.a) && this.b.equals(cVar.b);
        }

        public final int hashCode() {
            return Objects.hash(this.a, this.b, Integer.valueOf(this.c), Integer.valueOf(this.d), Integer.valueOf(this.e), Integer.valueOf(this.f), Integer.valueOf(this.g));
        }
    }

    @FunctionalInterface
    public interface d {
    }

    public LocationBarModel(Context context, km1 km1Var, qh1 qh1Var, i83 i83Var, f fVar, jg2 jg2Var) {
        this.a = context;
        this.b = km1Var;
        this.c = qh1Var;
        this.d = i83Var;
        this.e = fVar;
        this.l = yp.a(context, false);
        this.f = jg2Var;
    }

    public final void A() {
        boolean z = this.n;
        this.o = (z || this.l == yp.a(this.a, z) || !q() || this.k.isNativePage()) ? false : true;
    }

    @Override // defpackage.bc3, org.chromium.chrome.browser.omnibox.f
    public final int a() {
        if (!e()) {
            return this.l;
        }
        return yp.a(this.a, this.n);
    }

    @Override // defpackage.bc3, org.chromium.chrome.browser.omnibox.f
    public final rj3 b() {
        String strMvJvjGzq;
        String str;
        br1 br1Var;
        TraceEvent traceEventH0 = TraceEvent.H0("LocationBarModel.getUrlBarData", null);
        try {
            if (!q()) {
                rj3 rj3Var = rj3.h;
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return rj3Var;
            }
            GURL gurlI = i();
            boolean z = this.n;
            HashSet<String> hashSet = rj3.f;
            boolean z2 = false;
            if (!((jk1.h(gurlI, z) || bk3.l(gurlI)) ? false : true)) {
                rj3 rj3Var2 = rj3.h;
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return rj3Var2;
            }
            String strTrim = gurlI.j().trim();
            boolean zS = s();
            String strMl$ZWVQn = "";
            if (this.j) {
                strMvJvjGzq = this.w;
            } else {
                long j = this.t;
                strMvJvjGzq = j == 0 ? "" : N.MvJvjGzq(j, this);
            }
            if (this.k.isFrozen()) {
                rj3 rj3VarU = u(strTrim, strMvJvjGzq, strMvJvjGzq, zS);
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return rj3VarU;
            }
            boolean zL = a8.L(strTrim);
            d dVar = this.c;
            if (zL) {
                GURL gurlD = a8.D(new GURL(strTrim));
                ((qh1) dVar).getClass();
                Integer num = pb0.a;
                String strM5yzUycr = N.M5yzUycr(gurlD);
                rj3 rj3VarU2 = u(strM5yzUycr, strM5yzUycr, strM5yzUycr, zS);
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return rj3VarU2;
            }
            if (!zS) {
                if (this.j) {
                    str = this.x;
                } else {
                    long j2 = this.t;
                    if (j2 != 0) {
                        strMl$ZWVQn = N.Ml$ZWVQn(j2, this);
                    }
                    str = strMl$ZWVQn;
                }
                if (str.equals(strMvJvjGzq)) {
                    rj3 rj3VarU3 = u(strTrim, strMvJvjGzq, strMvJvjGzq, false);
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                    }
                    return rj3VarU3;
                }
                rj3 rj3VarU4 = u(strTrim, str, strMvJvjGzq, false);
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return rj3VarU4;
            }
            GURL gurlV = this.k.v();
            ((qh1) dVar).getClass();
            Integer num2 = pb0.a;
            String strP = bk3.p(N.M5yzUycr(gurlV));
            a aVar = this.e;
            Tab tab = this.k;
            ((f) aVar).getClass();
            if (tab != null && tab.isInitialized() && (br1Var = (br1) tab.B().b(br1.class)) != null && br1Var.f) {
                z2 = true;
            }
            if (z2) {
                rj3 rj3VarU5 = u(strTrim, strP, strP, true);
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
                return rj3VarU5;
            }
            rj3 rj3VarU6 = u(strTrim, strP, "", true);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return rj3VarU6;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // defpackage.bc3, org.chromium.chrome.browser.omnibox.f
    public final int c(boolean z) {
        boolean zS = s();
        int iV = v(d(), zS);
        boolean z2 = true;
        boolean z3 = !z;
        if (h()) {
            return R$drawable.omnibox_info;
        }
        if (zS) {
            return R$drawable.ic_offline_pin_24dp;
        }
        if ((iV == 0 || iV == 6) && this.t == 0) {
            return R$drawable.omnibox_info;
        }
        boolean z4 = this.n;
        this.f.getClass();
        boolean z5 = !(z4 ^ true) || this.b.i() || e();
        if (!this.y || (q() && this.k.isCustomTab())) {
            z2 = false;
        }
        return z7.C(iV, z3, z5, z2);
    }

    @Override // defpackage.bc3, org.chromium.chrome.browser.omnibox.f
    public final Tab d() {
        if (q()) {
            return this.k;
        }
        return null;
    }

    @Override // defpackage.bc3, org.chromium.chrome.browser.omnibox.f
    public final boolean e() {
        if (!this.p || this.m == null) {
            return false;
        }
        if (!this.r) {
            if (!this.q) {
                return false;
            }
            int i = this.s;
            if (i != 1 && i != 6 && i != 5) {
                return false;
            }
        }
        return true;
    }

    @Override // defpackage.bc3
    public final Profile f() {
        if (!this.n) {
            return this.h;
        }
        Tab tab = this.k;
        WindowAndroid windowAndroidD = tab != null ? tab.D() : null;
        ((i83) this.d).getClass();
        Profile profileH0 = n3.h0(windowAndroidD);
        return profileH0 != null ? profileH0 : this.h.f(true);
    }

    @Override // defpackage.bc3, org.chromium.chrome.browser.omnibox.f
    public final km1 g() {
        return this.b;
    }

    @CalledByNative
    public final WebContents getActiveWebContents() {
        if (q()) {
            return this.k.c();
        }
        return null;
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final String getTitle() {
        if (!q()) {
            return "";
        }
        String title = d().getTitle();
        return TextUtils.isEmpty(title) ? title : title.trim();
    }

    @Override // defpackage.bc3, org.chromium.chrome.browser.omnibox.f
    public final boolean h() {
        return !e() && q() && a63.a(this.k).b();
    }

    @Override // defpackage.bc3
    public final GURL i() {
        if (e()) {
            return uj3.a;
        }
        if (this.j) {
            return this.v;
        }
        Tab tabD = d();
        return (tabD == null || !tabD.isInitialized()) ? GURL.emptyGURL() : tabD.getUrl();
    }

    @Override // defpackage.bc3, org.chromium.chrome.browser.omnibox.f
    public final boolean isIncognito() {
        return this.n;
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final int j(boolean z, boolean z2) {
        if (this.t == 0) {
            return 0;
        }
        if (e()) {
            return 1;
        }
        return N.MY48gn2Q(this.t, this, z, z2);
    }

    @Override // defpackage.bc3
    public final boolean k() {
        return this.p;
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final void l(f.a aVar) {
        this.u.a(aVar);
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final int m() {
        int iA = rr1.a(a(), this.a, this.n);
        int iR = r();
        boolean z = this.n;
        if (iR == 5) {
            if (z) {
                return R$color.baseline_error_200;
            }
            if (iA == 3) {
                return R$color.default_red;
            }
        }
        return kp2.a();
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    @Deprecated
    public final String n() {
        return i().j().trim();
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final void o(f.a aVar) {
        this.u.c(aVar);
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final int p() {
        int iR = r();
        if (iR != 0) {
            if (iR == 3 || iR == 4) {
                return R$string.accessibility_security_btn_secure;
            }
            if (iR == 5) {
                return R$string.accessibility_security_btn_dangerous;
            }
            if (iR != 6) {
                return 0;
            }
        }
        return R$string.accessibility_security_btn_warn;
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final boolean q() {
        Tab tab = this.k;
        return (tab == null || !tab.isInitialized() || this.k.isDestroyed()) ? false : true;
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final int r() {
        return v(d(), s());
    }

    @Override // org.chromium.chrome.browser.omnibox.f
    public final boolean s() {
        boolean z;
        br1 br1Var;
        if (!q()) {
            return false;
        }
        Tab tab = this.k;
        ((f) this.e).getClass();
        TraceEvent.q("isOfflinePage", null);
        if (tab == null || !tab.isInitialized() || (br1Var = (br1) tab.B().b(br1.class)) == null) {
            z = false;
        } else {
            z = br1Var.e;
            if (Math.random() < 0.01d) {
                boolean zE = org.chromium.chrome.browser.offlinepages.d.e(tab);
                oj2.O("OfflinePages.CachedOfflineStatusValid", zE == z);
                z = zE;
            }
        }
        TraceEvent.C("isOfflinePage");
        return z && !e();
    }

    @Override // defpackage.bc3
    public final boolean t() {
        return e() || this.o;
    }

    public final rj3 u(String str, String str2, String str3, boolean z) {
        qp qpVar;
        SpannableStringBuilder spannableStringBuilder;
        SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(str2);
        if (this.t != 0 && spannableStringBuilder2.length() > 0) {
            Tab tab = this.k;
            if (tab == null || TrustedCdn.d(tab) == null) {
                boolean z2 = this.n;
                int iA = a();
                Context context = this.a;
                int iA2 = rr1.a(iA, context, z2);
                int iC = rr1.c(context, iA2);
                int iB = rr1.b(context, iA2);
                int i = R$color.default_red;
                if (iA2 == 1 || iA2 == 2) {
                    i = R$color.default_red_light;
                } else if (iA2 == 0) {
                    i = R$color.default_red_dark;
                }
                int color = context.getResources().getColor(i);
                int i2 = R$color.default_green;
                if (iA2 == 1 || iA2 == 2) {
                    i2 = R$color.default_green_light;
                } else if (iA2 == 0) {
                    i2 = R$color.default_green_dark;
                }
                int color2 = context.getResources().getColor(i2);
                c cVar = new c(str, str2, v(d(), z), iC, iB, color, color2);
                if (this.j) {
                    qpVar = this.g;
                    spannableStringBuilder = this.i.get(cVar);
                } else {
                    qpVar = new qp(f());
                    spannableStringBuilder = null;
                }
                qp qpVar2 = qpVar;
                try {
                    if (spannableStringBuilder != null) {
                        return rj3.c(str, spannableStringBuilder, str3);
                    }
                    OmniboxUrlEmphasizer.b(spannableStringBuilder2, qpVar2, r(), (t() || this.n) ? false : true, iC, iB, color, color2);
                    if (this.j) {
                        this.i.put(cVar, spannableStringBuilder2);
                    }
                    if (!this.j) {
                        qpVar2.a();
                    }
                } finally {
                    if (!this.j) {
                        qpVar2.a();
                    }
                }
            }
        }
        return rj3.c(str, spannableStringBuilder2, str3);
    }

    public final int v(Tab tab, boolean z) {
        if (tab == null || z || e()) {
            return 0;
        }
        String strD = TrustedCdn.d(tab);
        if (strD == null) {
            return n3.o0(tab.c());
        }
        try {
            return new ph3(strD).i().equals("https") ? 3 : 6;
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public final void w() {
        Iterator<f.a> it = this.u.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((f.a) aVar.next()).I();
            }
        }
    }

    public final void x() {
        if (r() == 5) {
            long j = this.t;
            this.w = j == 0 ? "" : N.MvJvjGzq(j, this);
            long j2 = this.t;
            this.x = j2 != 0 ? N.Ml$ZWVQn(j2, this) : "";
        }
        Iterator<f.a> it = this.u.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((f.a) aVar.next()).f();
            }
        }
    }

    public final void y() {
        Iterator<f.a> it = this.u.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((f.a) aVar.next()).u();
            }
        }
    }

    public final void z() {
        boolean z;
        if (this.j) {
            TraceEvent traceEventH0 = TraceEvent.H0("LocationBarModel.updateVisibleGurl", null);
            try {
                String strMl$ZWVQn = "";
                if (e()) {
                    this.w = "";
                    this.x = "";
                    this.v = uj3.a;
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                    }
                    z = true;
                } else {
                    GURL gurlEmptyGURL = this.t == 0 ? GURL.emptyGURL() : this.b.i() ? d().getUrl() : (GURL) N.MqKPLLn6(this.t, this);
                    if (gurlEmptyGURL.equals(this.v)) {
                        if (traceEventH0 != null) {
                            traceEventH0.close();
                        }
                        z = false;
                    } else {
                        this.v = gurlEmptyGURL;
                        long j = this.t;
                        this.w = j == 0 ? "" : N.MvJvjGzq(j, this);
                        long j2 = this.t;
                        if (j2 != 0) {
                            strMl$ZWVQn = N.Ml$ZWVQn(j2, this);
                        }
                        this.x = strMl$ZWVQn;
                        if (traceEventH0 != null) {
                            traceEventH0.close();
                        }
                        z = true;
                    }
                }
            } catch (Throwable th) {
                if (traceEventH0 != null) {
                    try {
                        traceEventH0.close();
                    } catch (Throwable unused) {
                    }
                }
                throw th;
            }
        } else {
            z = true;
        }
        if (!z) {
            return;
        }
        Iterator<f.a> it = this.u.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((f.a) aVar.next()).D();
            }
        }
    }
}
