package org.chromium.weblayer_private;

import J.N;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.Settings;
import defpackage.AP;
import defpackage.AbstractBinderC0495aR;
import defpackage.AbstractC1054kS;
import defpackage.AbstractRunnableC0550bQ;
import defpackage.C0551bR;
import defpackage.C1438rQ;
import defpackage.C1768xQ;
import defpackage.EP;
import defpackage.FP;
import defpackage.HP;
import defpackage.InterfaceC0663dR;
import defpackage.PQ;
import defpackage.QQ;
import defpackage.Tw;
import defpackage.VP;
import defpackage.Ws;
import defpackage.YR;
import defpackage.Ys;
import defpackage.ZQ;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class BrowserImpl extends AbstractBinderC0495aR {
    public final Ys a = new Ys();
    public long b;
    public final ProfileImpl c;
    public HP d;
    public C1438rQ e;
    public InterfaceC0663dR f;
    public C1768xQ g;
    public boolean h;
    public final QQ i;
    public boolean j;
    public boolean k;
    public Boolean l;
    public EP m;

    public BrowserImpl(ProfileImpl profileImpl, String str, Bundle bundle, C1438rQ c1438rQ) {
        profileImpl.c();
        this.c = profileImpl;
        byte[] byteArray = null;
        EP ep = new EP(null);
        this.m = ep;
        ep.a = str;
        ep.b = bundle != null ? bundle.getByteArray("SAVED_STATE_SESSION_SERVICE_CRYPTO_KEY") : null;
        EP ep2 = this.m;
        if (bundle != null && (str == null || str.isEmpty())) {
            byteArray = bundle.getByteArray("SAVED_STATE_MINIMAL_PERSISTENCE_STATE_KEY");
        }
        ep2.c = byteArray;
        d(c1438rQ);
        long jMChsAOfs = N.MChsAOfs(profileImpl.b, this);
        this.b = jMChsAOfs;
        this.i = new QQ(this, jMChsAOfs);
    }

    private void createTabForSessionRestore(long j) {
        new TabImpl(this.c, this.e, j);
    }

    private boolean getPasswordEchoEnabled() {
        Context contextV = v();
        if (contextV == null) {
            return false;
        }
        if (this.l == null) {
            this.l = Boolean.valueOf(Settings.System.getInt(contextV.getContentResolver(), "show_password", 1) == 1);
        }
        return this.l.booleanValue();
    }

    private void onActiveTabChanged(TabImpl tabImpl) {
        int i;
        this.d.d(tabImpl);
        if (this.h) {
            return;
        }
        try {
            if (this.f != null) {
                InterfaceC0663dR interfaceC0663dR = this.f;
                if (tabImpl != null) {
                    AbstractC1054kS.a();
                    i = tabImpl.n;
                } else {
                    i = 0;
                }
                ((C0551bR) interfaceC0663dR).c(i);
            }
        } catch (RemoteException e) {
            throw new ZQ(e);
        }
    }

    private void onTabAdded(TabImpl tabImpl) {
        tabImpl.j = this;
        tabImpl.F();
        SelectionPopupControllerImpl.t(tabImpl.c).x = new AP(tabImpl.c);
        try {
            if (this.f != null) {
                ((C0551bR) this.f).d(tabImpl);
            }
        } catch (RemoteException e) {
            throw new ZQ(e);
        }
    }

    private void onTabRemoved(TabImpl tabImpl) {
        if (this.h) {
            return;
        }
        try {
            if (this.f != null) {
                InterfaceC0663dR interfaceC0663dR = this.f;
                Objects.requireNonNull(tabImpl);
                AbstractC1054kS.a();
                ((C0551bR) interfaceC0663dR).f(tabImpl.n);
            }
        } catch (RemoteException e) {
            throw new ZQ(e);
        }
    }

    private void onVisibleSecurityStateOfActiveTabChanged() {
        Iterator it = this.a.iterator();
        while (true) {
            Ws ws = (Ws) it;
            if (!ws.hasNext()) {
                return;
            } else {
                ((PQ) ((FP) ws.next())).a();
            }
        }
    }

    public final void A() {
        Iterator it = w().iterator();
        while (it.hasNext()) {
            ((TabImpl) it.next()).F();
        }
    }

    public final void B() {
        if (w().size() > 0) {
            A();
            this.d.d(u());
        }
    }

    public void c(YR yr) {
        AbstractC1054kS.a();
        TabImpl tabImpl = (TabImpl) yr;
        if (tabImpl.j == this) {
            return;
        }
        N.MJ$RHdW5(this.b, this, tabImpl.a);
    }

    public final void d(C1438rQ c1438rQ) {
        this.e = c1438rQ;
        this.d = new HP(c1438rQ);
        this.g = new C1768xQ((Context) c1438rQ.S().get());
        this.l = null;
    }

    public final void f() {
        C1768xQ c1768xQ = this.g;
        if (c1768xQ != null) {
            c1768xQ.a.unregisterReceiver(c1768xQ);
            this.g = null;
        }
        HP hp = this.d;
        if (hp != null) {
            hp.e.K = null;
            hp.d(null);
            TopControlsContainerView topControlsContainerView = hp.c;
            topControlsContainerView.e(null);
            N.MDsa1RkB(topControlsContainerView.s, topControlsContainerView);
            ContentViewRenderView contentViewRenderView = hp.a;
            VP vp = contentViewRenderView.t;
            if (vp != null) {
                vp.e(false);
                contentViewRenderView.t.d();
                VP vp2 = contentViewRenderView.u;
                if (vp2 != null && vp2 != contentViewRenderView.t) {
                    vp2.e(false);
                    contentViewRenderView.u.d();
                }
            }
            contentViewRenderView.t = null;
            contentViewRenderView.u = null;
            contentViewRenderView.w = null;
            while (!contentViewRenderView.C.isEmpty()) {
                AbstractRunnableC0550bQ abstractRunnableC0550bQ = (AbstractRunnableC0550bQ) contentViewRenderView.C.get(0);
                contentViewRenderView.removeCallbacks(abstractRunnableC0550bQ);
                abstractRunnableC0550bQ.run();
            }
            N.MAIaE0OG(contentViewRenderView.v);
            contentViewRenderView.v = 0L;
            this.d = null;
        }
        C1438rQ c1438rQ = this.e;
        if (c1438rQ != null) {
            c1438rQ.P();
            this.e = null;
        }
        this.a.clear();
    }

    public final void i(TabImpl tabImpl) {
        N.M2kEkJ8E(this.b, this, tabImpl.a);
        TabCallbackProxy tabCallbackProxy = tabImpl.e;
        if (tabCallbackProxy != null) {
            N.Mpl66Ope(tabCallbackProxy.a);
            tabCallbackProxy.a = 0L;
            tabImpl.e = null;
        }
        ErrorPageCallbackProxy errorPageCallbackProxy = tabImpl.g;
        if (errorPageCallbackProxy != null) {
            N.MRiF9eNE(errorPageCallbackProxy.a);
            errorPageCallbackProxy.a = 0L;
            tabImpl.g = null;
        }
        FullscreenCallbackProxy fullscreenCallbackProxy = tabImpl.h;
        if (fullscreenCallbackProxy != null) {
            N.MeleUM$n(fullscreenCallbackProxy.a);
            fullscreenCallbackProxy.a = 0L;
            tabImpl.h = null;
        }
        NewTabCallbackProxy newTabCallbackProxy = tabImpl.l;
        if (newTabCallbackProxy != null) {
            N.MxkBiJzp(newTabCallbackProxy.a);
            newTabCallbackProxy.a = 0L;
            tabImpl.l = null;
        }
        tabImpl.v.c.c = true;
        tabImpl.v = null;
        Tw tw = tabImpl.p;
        tw.d.g(tabImpl.q);
        tabImpl.w();
        tabImpl.r = null;
        tabImpl.f = null;
        tabImpl.c.A(tabImpl.d);
        N.MD18T2Ck(tabImpl.a);
        tabImpl.a = 0L;
    }

    public TabImpl u() {
        return (TabImpl) N.Ms4h$LOA(this.b, this);
    }

    public Context v() {
        C1438rQ c1438rQ = this.e;
        if (c1438rQ == null) {
            return null;
        }
        return (Context) c1438rQ.S().get();
    }

    public List w() {
        AbstractC1054kS.a();
        return Arrays.asList((TabImpl[]) N.MGDfuPeL(this.b, this));
    }

    public HP x() {
        HP hp = this.d;
        if (hp != null) {
            return hp;
        }
        throw new RuntimeException("Currently Tab requires Activity context, so it exists only while BrowserFragment is attached to an Activity");
    }

    public void y() {
        this.j = true;
        N.MJ3WqXoC(this.b, this);
        A();
        Boolean bool = this.l;
        if (bool == null) {
            return;
        }
        boolean zBooleanValue = bool.booleanValue();
        this.l = null;
        if (zBooleanValue != getPasswordEchoEnabled()) {
            N.MCP8kZaA(this.b);
        }
    }

    public boolean z(YR yr) {
        AbstractC1054kS.a();
        TabImpl tabImpl = (TabImpl) yr;
        if (tabImpl != null && tabImpl.j != this) {
            return false;
        }
        N.MBT5EbFP(this.b, this, tabImpl != null ? tabImpl.a : 0L);
        return true;
    }
}
