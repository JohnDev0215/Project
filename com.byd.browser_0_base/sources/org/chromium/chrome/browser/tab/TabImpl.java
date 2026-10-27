package org.chromium.chrome.browser.tab;

import J.N;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import defpackage.a8;
import defpackage.aj3;
import defpackage.aq1;
import defpackage.bq;
import defpackage.ci3;
import defpackage.dr;
import defpackage.e53;
import defpackage.e91;
import defpackage.h53;
import defpackage.hs2;
import defpackage.i53;
import defpackage.ij;
import defpackage.ij2;
import defpackage.j53;
import defpackage.jk1;
import defpackage.js2;
import defpackage.jz2;
import defpackage.kk1;
import defpackage.kq0;
import defpackage.kq3;
import defpackage.ks2;
import defpackage.l02;
import defpackage.lk3;
import defpackage.ls2;
import defpackage.m33;
import defpackage.mk3;
import defpackage.ms2;
import defpackage.n23;
import defpackage.n3;
import defpackage.od0;
import defpackage.oj2;
import defpackage.on2;
import defpackage.pn1;
import defpackage.pq3;
import defpackage.su1;
import defpackage.t03;
import defpackage.td2;
import defpackage.u03;
import defpackage.ui2;
import defpackage.uv;
import defpackage.xz;
import defpackage.yq2;
import defpackage.z8;
import defpackage.zc0;
import defpackage.zo3;
import defpackage.zp1;
import defpackage.zq2;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.SysUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.metrics.PageLoadMetrics;
import org.chromium.chrome.browser.offlinepages.d;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.state.CriticalPersistedTabData;
import org.chromium.components.embedder_support.view.ContentView;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class TabImpl implements Tab {
    public static final List<String> H;
    public boolean A;
    public boolean C;
    public final aq1<Boolean> D;
    public int E;
    public final boolean F;
    public boolean G;
    public long a;
    public final int b;
    public final boolean c;
    public final ContextThemeWrapper d;
    public WindowAndroid e;
    public jk1 f;
    public WebContents g;
    public ContentView h;
    public View i;
    public final i53 j;
    public TabWebContentsDelegateAndroidImpl l;
    public boolean m;
    public boolean n;
    public final Integer o;
    public Integer p;
    public LoadUrlParams q;
    public boolean r;
    public boolean s;
    public boolean v;
    public boolean w;
    public jz2 x;
    public final a y;
    public boolean z;
    public final b<n23> k = new b<>();
    public boolean t = true;
    public int u = 0;
    public final mk3 B = new mk3();

    public class a implements View.OnAttachStateChangeListener {
        public a() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            TabImpl tabImpl = TabImpl.this;
            tabImpl.A = true;
            tabImpl.Y();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            TabImpl tabImpl = TabImpl.this;
            tabImpl.A = false;
            tabImpl.Y();
        }
    }

    static {
        Object[] objArr = {"baijiahao.baidu.com", "mbd.baidu.com"};
        ArrayList arrayList = new ArrayList(2);
        for (int i = 0; i < 2; i++) {
            Object obj = objArr[i];
            Objects.requireNonNull(obj);
            arrayList.add(obj);
        }
        H = Collections.unmodifiableList(arrayList);
    }

    @SuppressLint({"HandlerLeak"})
    public TabImpl(int i, boolean z, Integer num, ui2 ui2Var) {
        int i2 = 1;
        aq1<Boolean> aq1Var = new aq1<>();
        this.D = aq1Var;
        aq1Var.r(Boolean.FALSE);
        t03 t03VarA = t03.a();
        AtomicInteger atomicInteger = t03VarA.a;
        i = i == -1 ? atomicInteger.getAndIncrement() : i;
        int i3 = (i + 1) - atomicInteger.get();
        if (i3 >= 0) {
            atomicInteger.addAndGet(i3);
            t03VarA.b.m(atomicInteger.get(), "org.chromium.chrome.browser.tab.TabIdManager.NEXT_ID");
        }
        this.b = i;
        this.c = z;
        CriticalPersistedTabData.a aVar = CriticalPersistedTabData.A;
        if (!(ui2Var == null) && bq.v.a()) {
            ij2 ij2Var = new ij2(this, i2, ui2Var);
            if (l02.d(this, CriticalPersistedTabData.class) == null) {
            }
            this.F = true;
        }
        this.d = pn1.a(SysUtils.isLowEndDevice() ? org.chromium.chrome.R$style.Theme_Chromium_WithWindowAnimation_LowEnd : org.chromium.chrome.R$style.Theme_Chromium_WithWindowAnimation, xz.a, false);
        this.o = num;
        this.y = new a();
        this.j = new i53(this);
        new e53(new dr(3, this), this);
        this.E = 0;
    }

    public static boolean S(Tab tab) {
        WindowAndroid windowAndroidV;
        if (tab.c() == null || (windowAndroidV = tab.c().V()) == null) {
            return true;
        }
        return !(xz.a(windowAndroidV.i.get()) instanceof ChromeActivity);
    }

    @CalledByNative
    public static long[] getAllNativePtrs(Tab[] tabArr) {
        if (tabArr == null) {
            return null;
        }
        long[] jArr = new long[tabArr.length];
        for (int i = 0; i < tabArr.length; i++) {
            jArr[i] = ((TabImpl) tabArr[i]).getNativePtr();
        }
        return jArr;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final void A(boolean z) {
        this.D.r(Boolean.valueOf(z));
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final mk3 B() {
        return this.B;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final boolean C(int i) {
        ks2 ks2Var;
        ms2 ms2Var;
        boolean zC = false;
        if (M() == null) {
            Log.e("cr_Tab", "Tab couldn't be loaded because Context was null.");
            return false;
        }
        if (this.q != null) {
            WebContents webContentsE = zo3.a().e(isIncognito(), isHidden());
            if (webContentsE == null) {
                webContentsE = pq3.a(n3.k0(this.e, isIncognito()), isHidden());
            }
            R(webContentsE);
            b(this.q);
            this.q = null;
            return true;
        }
        if (((N.Mudil8Bg("RequestDesktopSiteExceptions") || !N.ManEQDnV("RequestDesktopSiteExceptionsDowngrade")) ? false : SharedPreferencesManager.getInstance().h("Chrome.RequestDesktopSiteExceptionsDowngrade.TabSettingSet").contains(String.valueOf(getId()))) && this.g != null) {
            SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
            boolean zL = this.g.m().l();
            int i2 = zL ? 2 : 1;
            if ((sharedPreferencesManager.contains("Chrome.RequestDesktopSiteExceptionsDowngrade.GlobalSettingEnabled") ? sharedPreferencesManager.readBoolean("Chrome.RequestDesktopSiteExceptionsDowngrade.GlobalSettingEnabled", false) : N.MJSt3Ocq(Profile.b(this.g), 73)) != zL) {
                CriticalPersistedTabData.m(this).r(i2);
            }
            sharedPreferencesManager.j("Chrome.RequestDesktopSiteExceptionsDowngrade.TabSettingSet", String.valueOf(getId()));
        }
        W(i + 100);
        if (isFrozen()) {
            WindowAndroid windowAndroid = this.e;
            aj3<ls2> aj3Var = ls2.j;
            zp1 zp1Var = (zp1) ls2.j.e(windowAndroid.r);
            if (zp1Var != null && (ks2Var = (ks2) zp1Var.get()) != null && ks2.e) {
                ks2.e = false;
                e91 e91Var = ks2Var.c.get();
                hs2 hs2Var = new hs2(this, ks2Var.b.f, new zc0(27, e91Var), new td2(9, e91Var));
                hs2Var.l = ks2Var.a;
                hs2Var.m = new zq2(3);
                hs2Var.n = new yq2(16, this);
                Iterator<ms2.a> it = ks2Var.d.iterator();
                while (true) {
                    b.a aVar = (b.a) it;
                    boolean zHasNext = aVar.hasNext();
                    ms2Var = hs2Var.b;
                    if (!zHasNext) {
                        break;
                    }
                    ms2Var.c.a((ms2.a) aVar.next());
                }
                js2 js2Var = new js2(hs2Var);
                b<PageLoadMetrics.a> bVar = PageLoadMetrics.a;
                Object obj = ThreadUtils.a;
                PageLoadMetrics.a.a(js2Var);
                PageLoadMetrics.b.a(js2Var);
                hs2Var.d = new zc0(28, js2Var);
                if (hs2Var.g == 0) {
                    zC = hs2Var.c.c(hs2Var);
                    ms2Var.getClass();
                    oj2.O("Browser.PaintPreview.TabbedPlayer.HadCapture", zC);
                    hs2Var.g = zC ? 1 : 3;
                }
                if (!zC) {
                    Runnable runnable = hs2Var.d;
                    if (runnable != null) {
                        runnable.run();
                        hs2Var.d = null;
                    }
                    hs2Var.a.t(hs2Var.f);
                }
            }
        }
        try {
            TraceEvent.q("Tab.restoreIfNeeded", null);
            if ((!isFrozen() || CriticalPersistedTabData.m(this).s == null || X()) && f()) {
                WebContents webContents = this.g;
                if (webContents != null) {
                    webContents.m().k();
                }
                this.s = true;
                Iterator<n23> it2 = this.k.iterator();
                while (true) {
                    b.a aVar2 = (b.a) it2;
                    if (!aVar2.hasNext()) {
                        break;
                    }
                    ((n23) aVar2.next()).U0();
                }
            }
            return true;
        } finally {
            TraceEvent.C("Tab.restoreIfNeeded");
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final WindowAndroid D() {
        return this.e;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final void E(WindowAndroid windowAndroid, jz2 jz2Var) {
        boolean z = true;
        if (windowAndroid != null) {
            this.e = windowAndroid;
            WebContents webContents = this.g;
            if (webContents != null) {
                webContents.i0(windowAndroid);
            }
            if (jz2Var != null) {
                this.x = jz2Var;
                TabWebContentsDelegateAndroidImpl tabWebContentsDelegateAndroidImpl = new TabWebContentsDelegateAndroidImpl(this, jz2Var.e(this));
                this.l = tabWebContentsDelegateAndroidImpl;
                WebContents webContents2 = this.g;
                if (webContents2 != null) {
                    N.M6xWklI_(this.a, tabWebContentsDelegateAndroidImpl, new su1(this.x.b(this), this));
                    webContents2.K();
                }
            }
            if (isNativePage()) {
                T(getUrl().j(), true);
            }
        }
        if ((windowAndroid == null || jz2Var == null) && (windowAndroid != null || jz2Var != null)) {
            z = false;
        }
        if (z) {
            Iterator<n23> it = this.k.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    break;
                } else {
                    ((n23) aVar.next()).P0(this, windowAndroid);
                }
            }
        }
        Y();
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final boolean F() {
        int iO0 = n3.o0(this.g);
        return this.n || !(iO0 == 5 || iO0 == 4);
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final void G(boolean z) {
        if (this.m == z) {
            return;
        }
        this.m = z;
        Iterator<n23> it = this.k.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((n23) aVar.next()).D0(this, z);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int H(org.chromium.url.GURL r10) {
        /*
            r9 = this;
            org.chromium.content_public.browser.WebContents r0 = r9.g
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L12
            org.chromium.content_public.browser.NavigationController r3 = r0.m()
            boolean r3 = r3.l()
            if (r3 == 0) goto L12
            r3 = r1
            goto L13
        L12:
            r3 = r2
        L13:
            org.chromium.chrome.browser.tab.state.CriticalPersistedTabData r4 = org.chromium.chrome.browser.tab.state.CriticalPersistedTabData.m(r9)
            int r4 = r4.y
            org.chromium.content_public.browser.WebContents r5 = r9.g
            if (r5 == 0) goto L29
            org.chromium.content_public.browser.NavigationController r6 = r5.m()
            boolean r6 = r6.l()
            if (r6 == 0) goto L29
            r6 = r1
            goto L2a
        L29:
            r6 = r2
        L2a:
            r7 = 3
            r8 = 2
            if (r5 == 0) goto L3c
            if (r4 != r7) goto L3c
            if (r6 == 0) goto L34
            r4 = r8
            goto L35
        L34:
            r4 = r2
        L35:
            org.chromium.chrome.browser.tab.state.CriticalPersistedTabData r5 = org.chromium.chrome.browser.tab.state.CriticalPersistedTabData.m(r9)
            r5.r(r4)
        L3c:
            org.chromium.ui.base.WindowAndroid r5 = r9.e
            boolean r6 = r9.isIncognito()
            org.chromium.chrome.browser.profiles.Profile r5 = defpackage.n3.k0(r5, r6)
            if (r10 != 0) goto L4e
            if (r0 == 0) goto L4e
            org.chromium.url.GURL r10 = r0.s()
        L4e:
            java.lang.String r0 = "Android.RequestDesktopSite.UseDesktopUserAgent"
            java.lang.String r6 = "RequestDesktopSiteExceptions"
            if (r4 == 0) goto L72
            defpackage.oj2.O(r0, r3)
            boolean r0 = J.N.Mudil8Bg(r6)
            if (r0 == 0) goto L71
            if (r10 != 0) goto L60
            goto L71
        L60:
            if (r4 != r7) goto L63
            goto L71
        L63:
            if (r4 != r8) goto L66
            goto L67
        L66:
            r1 = r2
        L67:
            defpackage.fb2.c(r5, r10, r1)
            org.chromium.chrome.browser.tab.state.CriticalPersistedTabData r9 = org.chromium.chrome.browser.tab.state.CriticalPersistedTabData.m(r9)
            r9.r(r2)
        L71:
            return r2
        L72:
            boolean r4 = J.N.Mudil8Bg(r6)
            r6 = 73
            if (r4 == 0) goto L8b
            if (r10 == 0) goto L89
            int r10 = J.N.MFhlM$PH(r5, r6, r10, r10)
            if (r10 != r1) goto L84
            r10 = r1
            goto L85
        L84:
            r10 = r2
        L85:
            if (r10 == 0) goto L89
            r10 = r1
            goto L8f
        L89:
            r10 = r2
            goto L8f
        L8b:
            boolean r10 = J.N.MJSt3Ocq(r5, r6)
        L8f:
            if (r10 != 0) goto Lae
            java.lang.String r4 = "RequestDesktopSiteAdditions"
            boolean r4 = J.N.Mudil8Bg(r4)
            if (r4 == 0) goto Lae
            org.chromium.components.prefs.PrefService r10 = defpackage.oj2.k(r5)
            java.lang.String r4 = "desktop_site.peripheral_setting"
            boolean r10 = r10.a(r4)
            boolean r9 = org.chromium.chrome.browser.tab.TabUtils.isHardwareKeyboardAvailable(r9)
            if (r9 == 0) goto Lad
            if (r10 == 0) goto Lad
            r10 = r1
            goto Lae
        Lad:
            r10 = r2
        Lae:
            if (r10 == r3) goto Lb4
            if (r10 == 0) goto Lb3
            r1 = r8
        Lb3:
            r2 = r1
        Lb4:
            defpackage.oj2.O(r0, r10)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.tab.TabImpl.H(org.chromium.url.GURL):int");
    }

    public final void I(boolean z) {
        if (this.g == null) {
            return;
        }
        this.h.removeOnAttachStateChangeListener(this.y);
        this.h = null;
        Y();
        WebContents webContents = this.g;
        if (webContents.u() != null && (webContents.u() instanceof h53)) {
            h53 h53Var = (h53) webContents.u();
            od0 od0Var = h53Var.a;
            od0Var.f = 0;
            od0Var.e = 0;
            od0Var.i = 0;
            od0Var.g = false;
            od0Var.h = false;
            od0Var.l = -1L;
            if (((ContentView) h53Var.getContainerView()) != null) {
                ((ContentView) h53Var.getContainerView()).removeOnDragListener(od0Var);
            }
            if (h53Var.j != null) {
                od0Var.m = null;
                h53Var.j = null;
            }
        }
        this.g = null;
        this.l = null;
        if (z) {
            N.MYIgyGYO(this.a);
            return;
        }
        N.MoDA8Gdb(this.a);
        webContents.D();
        webContents.i(new ViewAndroidDelegate(null), null, null, new org.chromium.content_public.browser.b());
    }

    public final void J(int i) {
        Iterator<n23> it = this.k.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                this.s = false;
                return;
            }
            ((n23) aVar.next()).J0(i, this);
        }
    }

    public final void K(GURL gurl) {
        a0();
        Iterator<n23> it = this.k.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                this.s = false;
                return;
            }
            ((n23) aVar.next()).a1(this, gurl);
        }
    }

    public final void L(GURL gurl) {
        a0();
        if (this.v) {
            O(true);
        }
        Iterator<n23> it = this.k.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((n23) aVar.next()).g1(this, gurl);
            }
        }
    }

    @Deprecated
    public final ChromeActivity<?> M() {
        WindowAndroid windowAndroid = this.e;
        if (windowAndroid == null) {
            return null;
        }
        Activity activityA = xz.a(windowAndroid.i.get());
        if (activityA instanceof ChromeActivity) {
            return (ChromeActivity) activityA;
        }
        return null;
    }

    public final b.a N() {
        return this.k.d();
    }

    public final void O(boolean z) {
        this.v = !z;
        Iterator<n23> it = this.k.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((n23) aVar.next()).I(this, z);
            }
        }
    }

    public final void P() {
        this.r = false;
        b.a aVarN = N();
        while (aVarN.hasNext()) {
            ((n23) aVarN.next()).I0(this);
        }
        this.s = false;
    }

    public final void Q(boolean z, Runnable runnable) {
        jk1 jk1Var = this.f;
        if (jk1Var != null) {
            if (!jk1Var.isFrozen()) {
                this.f.getView().removeOnAttachStateChangeListener(this.y);
            }
            this.f = null;
        }
        if (runnable != null) {
            runnable.run();
        }
        if (z) {
            U();
        }
        if (jk1Var == null) {
            return;
        }
        jk1Var.destroy();
    }

    public final void R(WebContents webContents) {
        try {
            TraceEvent.q("ChromeTab.initWebContents", null);
            WebContents webContents2 = this.g;
            this.g = webContents;
            ContextThemeWrapper contextThemeWrapper = this.d;
            ContentView contentView = new ContentView(contextThemeWrapper, webContents);
            contentView.setContentDescription(contextThemeWrapper.getResources().getString(org.chromium.chrome.R$string.accessibility_content_view));
            this.h = contentView;
            webContents.i(new h53(this, contentView), contentView, this.e, new org.chromium.content_public.browser.b());
            Q(false, null);
            if (webContents2 != null) {
                webContents2.v(0);
                WebContentsAccessibilityImpl webContentsAccessibilityImplB = kq3.b(webContents2);
                webContentsAccessibilityImplB.G = Boolean.FALSE;
                webContentsAccessibilityImplB.A(-1, 2048);
            }
            this.g.v(this.u);
            N.Mt4iWzCb(this.g, H(null) == 2);
            this.h.addOnAttachStateChangeListener(this.y);
            Y();
            this.l = new TabWebContentsDelegateAndroidImpl(this, this.x.e(this));
            N.MUKSQbrZ(this.a, this.c, S(this), webContents, this.l, new su1(this.x.b(this), this));
            this.g.K();
            n3.D0(this);
            U();
        } finally {
            TraceEvent.C("ChromeTab.initWebContents");
        }
    }

    public final boolean T(String str, boolean z) {
        boolean z2 = false;
        if (S(this)) {
            return false;
        }
        jk1 jk1VarA = this.x.a(str, z ? null : this.f, this);
        if (jk1VarA != null) {
            z2 = true;
            if (this.f != jk1VarA) {
                Q(true, new z8(this, 21, jk1VarA));
            }
            b.a aVarN = N();
            while (aVarN.hasNext()) {
                ((n23) aVarN.next()).r1(this);
            }
            b.a aVarN2 = N();
            while (aVarN2.hasNext()) {
                ((n23) aVarN2.next()).X(this, null, null);
            }
        }
        return z2;
    }

    public final void U() {
        Iterator<n23> it = this.k.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((n23) aVar.next()).u(this);
            }
        }
    }

    public final void V(m33 m33Var) {
        CriticalPersistedTabData.m(this).s(m33Var.a);
        CriticalPersistedTabData.m(this).p(m33Var.d);
        CriticalPersistedTabData criticalPersistedTabDataM = CriticalPersistedTabData.m(this);
        on2 on2Var = m33Var.a;
        criticalPersistedTabDataM.q(new GURL(N.MNZ4eg9q((ByteBuffer) on2Var.b, on2Var.a)));
        CriticalPersistedTabData criticalPersistedTabDataM2 = CriticalPersistedTabData.m(this);
        on2 on2Var2 = m33Var.a;
        String strMZZlQD12 = N.MZZlQD12((ByteBuffer) on2Var2.b, on2Var2.a);
        if (!TextUtils.equals(strMZZlQD12, criticalPersistedTabDataM2.n)) {
            criticalPersistedTabDataM2.n = strMZZlQD12;
            criticalPersistedTabDataM2.l();
        }
        CriticalPersistedTabData criticalPersistedTabDataM3 = CriticalPersistedTabData.m(this);
        Integer num = m33Var.g;
        if ((num != null || criticalPersistedTabDataM3.w != null) && (num == null || !num.equals(criticalPersistedTabDataM3.w))) {
            criticalPersistedTabDataM3.w = num;
            criticalPersistedTabDataM3.l();
        }
        CriticalPersistedTabData criticalPersistedTabDataM4 = CriticalPersistedTabData.m(this);
        int i = m33Var.c;
        if (i == -1) {
            i = this.b;
        }
        criticalPersistedTabDataM4.o(i);
        CriticalPersistedTabData.m(this).r(m33Var.i);
    }

    public final void W(int i) {
        WebContents webContents;
        if (H(null) == 0 || (webContents = this.g) == null) {
            return;
        }
        TabUtils.d(i, this, !webContents.m().l(), false);
    }

    public final boolean X() {
        try {
            TraceEvent.q("Tab.unfreezeContents", null);
            on2 on2Var = CriticalPersistedTabData.m(this).s;
            boolean z = false;
            WebContents webContentsA = (WebContents) N.MXGOiJkn((ByteBuffer) on2Var.b, on2Var.a, isHidden(), false);
            if (webContentsA == null) {
                webContentsA = pq3.a(n3.k0(this.e, isIncognito()), isHidden());
                Iterator<n23> it = this.k.iterator();
                while (true) {
                    b.a aVar = (b.a) it;
                    if (!aVar.hasNext()) {
                        break;
                    }
                    ((n23) aVar.next()).H0();
                }
            } else {
                z = true;
            }
            CompositorViewHolder compositorViewHolder = M().y0.f;
            webContentsA.m0(compositorViewHolder.getWidth(), compositorViewHolder.getHeight());
            CriticalPersistedTabData.m(this).s(null);
            R(webContentsA);
            if (!z) {
                b(new LoadUrlParams(CriticalPersistedTabData.m(this).o.j().isEmpty() ? "chrome-native://newtab/" : CriticalPersistedTabData.m(this).o.j(), 5));
            }
            return z;
        } finally {
            TraceEvent.C("Tab.unfreezeContents");
        }
    }

    public final void Y() {
        boolean z = (this.t || isFrozen() || !this.A || S(this)) ? false : true;
        if (z == this.z) {
            return;
        }
        this.z = z;
        Iterator<n23> it = this.k.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((n23) aVar.next()).S0(this, z);
            }
        }
    }

    public final void Z(int i) {
        if (this.E == i) {
            return;
        }
        this.E = i;
        b.a aVarN = N();
        while (aVarN.hasNext()) {
            ((n23) aVarN.next()).M(i, this);
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final void a() {
        jk1 jk1Var = this.f;
        if (jk1Var != null) {
            jk1Var.a();
            return;
        }
        if (d.e(this)) {
            d.g(this.g, new d.e(this));
        } else {
            if (this.g == null) {
                return;
            }
            W(200);
            this.g.m().a();
        }
    }

    public final void a0() {
        String title;
        if (isFrozen()) {
            return;
        }
        if (isNativePage()) {
            title = this.f.getTitle();
        } else {
            WebContents webContents = this.g;
            title = webContents != null ? webContents.getTitle() : "";
        }
        if (TextUtils.equals(CriticalPersistedTabData.m(this).n, title)) {
            return;
        }
        CriticalPersistedTabData criticalPersistedTabDataM = CriticalPersistedTabData.m(this);
        if (!TextUtils.equals(title, criticalPersistedTabDataM.n)) {
            criticalPersistedTabDataM.n = title;
            criticalPersistedTabDataM.l();
        }
        b.a aVarN = N();
        while (aVarN.hasNext()) {
            ((n23) aVarN.next()).r1(this);
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final int b(LoadUrlParams loadUrlParams) {
        try {
            TraceEvent.q("Tab.loadUrl", null);
            int i = 0;
            if (!this.w) {
                this.w = T(loadUrlParams.a, false);
            }
            if ("chrome://java-crash/".equals(loadUrlParams.a)) {
                throw new uv("Intentional Java Crash");
            }
            if (this.C) {
                throw new uv("Tab.loadUrl called on a destroyed tab");
            }
            if (this.a == 0) {
                throw new uv("Tab.loadUrl called when no native side exists");
            }
            GURL gurlH = oj2.h(loadUrlParams.a);
            if (gurlH.b) {
                loadUrlParams.h = H(gurlH);
            } else {
                loadUrlParams.h = H(null);
            }
            if (this.g != null && gurlH.b) {
                if (gurlH.j().equals("chrome://history/")) {
                    n3.X0("ShowHistory");
                }
                i = 1;
                if (!N.Magi68$J(gurlH)) {
                    loadUrlParams.a = gurlH.j();
                    this.g.m().b(loadUrlParams);
                }
            }
            Iterator<n23> it = this.k.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    return i;
                }
                ((n23) aVar.next()).g0(i, this, loadUrlParams);
            }
        } finally {
            TraceEvent.C("Tab.loadUrl");
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final WebContents c() {
        return this.g;
    }

    @CalledByNative
    public final void clearNativePtr() {
        this.a = 0L;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final ContentView d() {
        return this.h;
    }

    @CalledByNative
    public final void deleteNavigationEntriesFromFrozenState(long j) {
        on2 on2Var;
        on2 on2Var2 = CriticalPersistedTabData.m(this).s;
        if (on2Var2 == null) {
            return;
        }
        ByteBuffer byteBuffer = (ByteBuffer) N.MGuJ$X8n((ByteBuffer) on2Var2.b, on2Var2.a, j);
        if (byteBuffer == null) {
            on2Var = null;
        } else {
            on2 on2Var3 = new on2(byteBuffer);
            on2Var3.a = 2;
            on2Var = on2Var3;
        }
        if (on2Var == null) {
            return;
        }
        CriticalPersistedTabData.m(this).s(on2Var);
        Iterator<n23> it = this.k.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((n23) aVar.next()).K0(this);
            }
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final void destroy() {
        this.C = true;
        a0();
        b<n23> bVar = this.k;
        Iterator<n23> it = bVar.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                break;
            } else {
                ((n23) aVar.next()).F0(this);
            }
        }
        bVar.clear();
        mk3 mk3Var = this.B;
        mk3Var.a();
        HashMap<Class<? extends lk3>, lk3> map = mk3Var.b;
        mk3Var.b = null;
        Iterator<lk3> it2 = map.values().iterator();
        while (it2.hasNext()) {
            it2.next().destroy();
        }
        i53 i53Var = this.j;
        TabImpl tabImpl = i53Var.f;
        tabImpl.i = null;
        tabImpl.U();
        PriorityQueue<j53> priorityQueue = i53Var.e;
        j53 j53VarPeek = priorityQueue.peek();
        if (j53VarPeek != null) {
            j53VarPeek.S();
        }
        priorityQueue.clear();
        ij ijVar = i53Var.h;
        if (ijVar != null) {
            ijVar.destroy();
        }
        i53Var.f = null;
        Q(false, null);
        I(true);
        ArrayList arrayList = u03.a;
        Object obj = ThreadUtils.a;
        u03.a.remove(this);
        long j = this.a;
        if (j != 0) {
            N.M1Fyow7a(j);
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final void e() {
        WebContents webContents = this.g;
        if (webContents != null) {
            webContents.m().e();
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final boolean f() {
        WebContents webContents = this.g;
        return webContents != null && webContents.m().f();
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final boolean g() {
        WebContents webContents = this.g;
        return webContents != null && webContents.m().g();
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final Context getContext() {
        WindowAndroid windowAndroid = this.e;
        ContextThemeWrapper contextThemeWrapper = this.d;
        if (windowAndroid == null) {
            return contextThemeWrapper;
        }
        Context context = windowAndroid.i.get();
        return context == context.getApplicationContext() ? contextThemeWrapper : context;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    @CalledByNative
    public int getId() {
        return this.b;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    @CalledByNative
    public int getLaunchType() {
        return this.o.intValue();
    }

    @CalledByNative
    public final long getNativePtr() {
        return this.a;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    @CalledByNative
    public String getTitle() {
        if (CriticalPersistedTabData.m(this).n == null) {
            a0();
        }
        return CriticalPersistedTabData.m(this).n;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    @CalledByNative
    public GURL getUrl() {
        if (!isInitialized()) {
            return GURL.emptyGURL();
        }
        WebContents webContents = this.g;
        GURL gurlS = webContents != null ? webContents.s() : GURL.emptyGURL();
        if (this.g != null || isNativePage() || !gurlS.j().isEmpty()) {
            CriticalPersistedTabData.m(this).q(gurlS);
        }
        return CriticalPersistedTabData.m(this).o != null ? CriticalPersistedTabData.m(this).o : GURL.emptyGURL();
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final View getView() {
        View view = this.i;
        if (view != null) {
            return view;
        }
        jk1 jk1Var = this.f;
        return (jk1Var == null || jk1Var.isFrozen()) ? this.h : this.f.getView();
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final void h() {
        WebContents webContents = this.g;
        if (webContents != null) {
            webContents.m().h();
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final void hide(int i) {
        Tab tab;
        try {
            TraceEvent.q("Tab.hide", null);
            if (isHidden()) {
                return;
            }
            this.t = true;
            Y();
            WebContents webContents = this.g;
            if (webContents != null) {
                webContents.e();
            }
            ArrayList<WeakReference<Tab>> arrayList = kk1.b.a;
            arrayList.add(new WeakReference<>(this));
            if (arrayList.size() > 3 && (tab = arrayList.remove(0).get()) != null) {
                tab.w();
            }
            Iterator<n23> it = this.k.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    return;
                } else {
                    ((n23) aVar.next()).T0(i, this);
                }
            }
        } finally {
            TraceEvent.C("Tab.hide");
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final boolean i() {
        WebContents webContents = this.g;
        return webContents != null && webContents.m().i();
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    @CalledByNative
    public boolean isCustomTab() {
        ChromeActivity<?> chromeActivityM = M();
        return chromeActivityM != null && chromeActivityM.M2();
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final boolean isDestroyed() {
        return this.C;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final boolean isFrozen() {
        return !isNativePage() && this.g == null;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    @CalledByNative
    public boolean isHidden() {
        return this.t;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    @CalledByNative
    public boolean isIncognito() {
        return this.c;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final boolean isInitialized() {
        return this.a != 0;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    @CalledByNative
    public boolean isNativePage() {
        return this.f != null;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    @CalledByNative
    public boolean isUserInteractable() {
        return this.z;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final int j() {
        return this.E;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final boolean k() {
        return this.r;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final void l(int i, int i2) {
        try {
            TraceEvent.q("Tab.show", null);
            if (!isHidden()) {
                return;
            }
            int i3 = 0;
            this.t = false;
            Y();
            C(i2);
            N.MWv3rdnR(this.a);
            WebContents webContents = this.g;
            if (webContents != null) {
                webContents.Y();
            }
            jk1 jk1Var = this.f;
            if (jk1Var != null && (jk1Var.isFrozen() || (jk1Var.getUrl() != null && ((i == 3 || i == 0) && jk1Var.getUrl().equals("chrome-native://newtab/"))))) {
                T(jk1Var.getUrl(), true);
            }
            kk1 kk1Var = kk1.b;
            while (true) {
                ArrayList<WeakReference<Tab>> arrayList = kk1Var.a;
                if (i3 >= arrayList.size()) {
                    break;
                }
                if (arrayList.get(i3).get() == this) {
                    arrayList.remove(i3);
                }
                i3++;
            }
            u03.a(this);
            float fQ = q();
            b<n23> bVar = this.k;
            if (fQ < 100.0f) {
                float fQ2 = q();
                Iterator<n23> it = bVar.iterator();
                while (true) {
                    b.a aVar = (b.a) it;
                    if (!aVar.hasNext()) {
                        break;
                    } else {
                        ((n23) aVar.next()).l1(this, fQ2);
                    }
                }
            }
            Iterator<n23> it2 = bVar.iterator();
            while (true) {
                b.a aVar2 = (b.a) it2;
                if (!aVar2.hasNext()) {
                    CriticalPersistedTabData.m(this).p(System.currentTimeMillis());
                    return;
                }
                ((n23) aVar2.next()).l0(i, this);
            }
        } finally {
            TraceEvent.C("Tab.show");
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final boolean m() {
        return this.n;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final boolean n() {
        return this.m;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final i53 o() {
        return this.j;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final LoadUrlParams p() {
        return this.q;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final float q() {
        if (this.r) {
            return (int) this.g.Q();
        }
        return 1.0f;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final boolean r() {
        return this.s;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final boolean s() {
        return this.i != null;
    }

    @CalledByNative
    public final void setNativePtr(long j) {
        this.a = j;
    }

    @CalledByNative
    public void swapWebContents(WebContents webContents, boolean z, boolean z2) {
        boolean z3 = (this.h == null || this.g == null) ? false : true;
        Rect rect = z3 ? new Rect(0, 0, this.h.getWidth(), this.h.getHeight()) : new Rect();
        b<n23> bVar = this.k;
        Iterator<n23> it = bVar.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                break;
            } else {
                ((n23) aVar.next()).e1(this);
            }
        }
        if (z3) {
            this.g.e();
        }
        Rect rectA = rect.isEmpty() ? TabUtils.a(xz.a) : null;
        if (rectA != null) {
            rect.set(rectA);
        }
        WebContents webContents2 = this.g;
        if (webContents2 != null) {
            webContents2.E(false);
        }
        I(false);
        Q(false, new ci3(this, webContents, rect, rectA, 2));
        if (z) {
            L(getUrl());
            if (z2) {
                K(getUrl());
            }
        }
        Iterator<n23> it2 = bVar.iterator();
        while (true) {
            b.a aVar2 = (b.a) it2;
            if (!aVar2.hasNext()) {
                return;
            } else {
                ((n23) aVar2.next()).Q(this, z, z2);
            }
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final void t(n23 n23Var) {
        this.k.c(n23Var);
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final jk1 u() {
        return this.f;
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final GURL v() {
        return a8.D(getUrl());
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final void w() {
        jk1 jk1Var = this.f;
        if (jk1Var == null || jk1Var.isFrozen() || this.f.getView().getParent() != null) {
            return;
        }
        jk1 jk1Var2 = this.f;
        kq0 kq0Var = new kq0(jk1Var2);
        jk1Var2.destroy();
        this.f = kq0Var;
        Y();
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final void x(n23 n23Var) {
        this.k.a(n23Var);
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final void y() {
        if (this.r) {
            b.a aVarN = N();
            while (aVarN.hasNext()) {
                ((n23) aVarN.next()).a1(this, getUrl());
            }
        }
        WebContents webContents = this.g;
        if (webContents != null) {
            webContents.stop();
        }
    }

    @Override // org.chromium.chrome.browser.tab.Tab
    public final void z() {
        if (this.g != null) {
            W(300);
            this.g.m().r();
        }
    }
}
