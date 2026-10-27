package org.chromium.weblayer_private;

import J.N;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.os.Build;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.Property;
import android.view.View;
import defpackage.AR;
import defpackage.AbstractC0566bn;
import defpackage.AbstractC0929iE;
import defpackage.AbstractC1054kS;
import defpackage.AbstractC1711wO;
import defpackage.AbstractC1767xP;
import defpackage.ActionModeCallbackC1133lw;
import defpackage.Aw;
import defpackage.BO;
import defpackage.BR;
import defpackage.BinderC0998jS;
import defpackage.C0705eE;
import defpackage.C1713wQ;
import defpackage.C1766xO;
import defpackage.DR;
import defpackage.FQ;
import defpackage.GQ;
import defpackage.GR;
import defpackage.Gx;
import defpackage.HP;
import defpackage.IQ;
import defpackage.InterfaceC0552bS;
import defpackage.InterpolatorC1217nO;
import defpackage.Ix;
import defpackage.Kx;
import defpackage.Lu;
import defpackage.RD;
import defpackage.Rw;
import defpackage.Tw;
import defpackage.XR;
import defpackage.ZR;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.components.autofill.AutofillProvider;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;
import org.chromium.components.find_in_page.FindMatchRectsDetails;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.LocalizationUtils;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;
import org.chromium.weblayer_private.TabImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class TabImpl extends XR {
    public static int w = 1;
    public long a;
    public ProfileImpl b;
    public WebContents c;
    public AbstractC0929iE d;
    public TabCallbackProxy e;
    public NavigationControllerImpl f;
    public ErrorPageCallbackProxy g;
    public FullscreenCallbackProxy h;
    public ViewAndroidDelegate i;
    public BrowserImpl j;
    public AutofillProvider k;
    public NewTabCallbackProxy l;
    public InterfaceC0552bS m;
    public final int n;
    public List o;
    public Tw p;
    public Callback q;
    public DR r;
    public Gx s;
    public Kx t;
    public boolean u;
    public C1713wQ v;

    public TabImpl(ProfileImpl profileImpl, WindowAndroid windowAndroid) {
        int i = w + 1;
        w = i;
        this.n = i;
        x(profileImpl, windowAndroid, N.M0NNPeB3(profileImpl.b, this));
    }

    public static String A(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str;
    }

    public static FindMatchRectsDetails createFindMatchRectsDetails(int i, int i2, RectF rectF) {
        return new FindMatchRectsDetails(i, i2, rectF);
    }

    public static RectF createRectF(float f, float f2, float f3, float f4) {
        return new RectF(f, f2, f3, f4);
    }

    private boolean doBrowserControlsShrinkRendererSize() {
        boolean z;
        HP hpV = v();
        if (hpV != null) {
            if (hpV.h.a()) {
                z = hpV.i;
            } else {
                TopControlsContainerView topControlsContainerView = hpV.c;
                z = (topControlsContainerView.w == null || topControlsContainerView.A == 0) ? false : true;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    private void onFindMatchRectsAvailable(FindMatchRectsDetails findMatchRectsDetails) {
        Kx kx = this.t;
        if (kx == null || !this.u) {
            return;
        }
        kx.b(findMatchRectsDetails.a, findMatchRectsDetails.b, findMatchRectsDetails.c);
    }

    private void onFindResultAvailable(int i, int i2, boolean z) {
        try {
            if (this.r != null) {
                ((BR) this.r).d(i, i2 - 1, z);
            }
            Kx kx = this.t;
            if (kx != null) {
                kx.Q = false;
                if (z) {
                    if (i <= 0) {
                        this.u = false;
                        kx.b(-1, new RectF[0], null);
                    } else {
                        this.u = true;
                        Gx gx = this.s;
                        N.M4m8QCn$(gx.a, gx, kx.H);
                    }
                }
            }
        } catch (RemoteException e) {
            throw new AndroidRuntimeException(e);
        }
    }

    public static void setMatchRectByIndex(FindMatchRectsDetails findMatchRectsDetails, int i, RectF rectF) {
        findMatchRectsDetails.b[i] = rectF;
    }

    private void showContextMenu(ContextMenuParams contextMenuParams) {
        if (WebLayerFactoryImpl.c() < 82) {
            return;
        }
        ((ZR) this.m).v(new BinderC0998jS(contextMenuParams.a), new BinderC0998jS(A(contextMenuParams.b)), new BinderC0998jS(A(contextMenuParams.c)), new BinderC0998jS(A(contextMenuParams.d)), new BinderC0998jS(A(contextMenuParams.e)));
    }

    public void B(AR ar) {
        AbstractC1054kS.a();
        if (ar != null) {
            ErrorPageCallbackProxy errorPageCallbackProxy = this.g;
            if (errorPageCallbackProxy == null) {
                this.g = new ErrorPageCallbackProxy(this.a, ar);
                return;
            } else {
                errorPageCallbackProxy.b = ar;
                return;
            }
        }
        ErrorPageCallbackProxy errorPageCallbackProxy2 = this.g;
        if (errorPageCallbackProxy2 != null) {
            N.MRiF9eNE(errorPageCallbackProxy2.a);
            errorPageCallbackProxy2.a = 0L;
            this.g = null;
        }
    }

    public boolean C(DR dr) {
        HP hpV;
        AbstractC1054kS.a();
        if (dr == null) {
            this.r = null;
            w();
            return true;
        }
        if (this.r != null || (hpV = v()) == null || ((Integer) this.p.c).intValue() == 2) {
            return false;
        }
        setBrowserControlsVisibilityConstraint(2, 1);
        this.r = dr;
        this.s = new Gx(this.c);
        this.t = new Kx(this.j.v(), hpV.d, this.j.e, this.s);
        return true;
    }

    public void D(GR gr) {
        AbstractC1054kS.a();
        if (gr != null) {
            FullscreenCallbackProxy fullscreenCallbackProxy = this.h;
            if (fullscreenCallbackProxy == null) {
                this.h = new FullscreenCallbackProxy(this.a, gr);
                return;
            } else {
                fullscreenCallbackProxy.b = gr;
                return;
            }
        }
        FullscreenCallbackProxy fullscreenCallbackProxy2 = this.h;
        if (fullscreenCallbackProxy2 != null) {
            N.MeleUM$n(fullscreenCallbackProxy2.a);
            fullscreenCallbackProxy2.a = 0L;
            this.h = null;
        }
    }

    public void E(boolean z) {
        NewTabCallbackProxy newTabCallbackProxy;
        AbstractC1054kS.a();
        if (z && this.l == null) {
            this.l = new NewTabCallbackProxy(this);
        } else {
            if (z || (newTabCallbackProxy = this.l) == null) {
                return;
            }
            N.MxkBiJzp(newTabCallbackProxy.a);
            newTabCallbackProxy.a = 0L;
            this.l = null;
        }
    }

    public void F() {
        this.c.q(this.j.e);
        ViewAndroidDelegate viewAndroidDelegate = this.i;
        HP hp = this.j.d;
        viewAndroidDelegate.b(hp == null ? null : hp.b);
        G();
        if (Build.VERSION.SDK_INT >= 26) {
            SelectionPopupControllerImpl selectionPopupControllerImplT = SelectionPopupControllerImpl.t(this.c);
            if (this.j.v() == null) {
                this.k = null;
                selectionPopupControllerImplT.A = null;
            } else {
                Context contextV = this.j.v();
                HP hp2 = this.j.d;
                Aw aw = new Aw(contextV, hp2 != null ? hp2.b : null);
                this.k = aw;
                aw.f(this.c);
                selectionPopupControllerImplT.A = new ActionModeCallbackC1133lw(this.j.v(), this.k);
            }
            N.Myi236il(this.a, this.k);
        }
    }

    public final void G() {
        boolean zY = y();
        boolean z = this.c.n() == 2;
        if (zY) {
            if (z) {
                return;
            }
            this.c.N();
        } else if (z) {
            this.c.w();
        }
    }

    public boolean i() {
        HP hpV = v();
        if (hpV != null && hpV.f.a(1, 5)) {
            return true;
        }
        if (this.c.J()) {
            this.c.t();
            return true;
        }
        SelectionPopupControllerImpl selectionPopupControllerImplT = SelectionPopupControllerImpl.t(this.c);
        if (selectionPopupControllerImplT == null || !selectionPopupControllerImplT.g()) {
            return false;
        }
        selectionPopupControllerImplT.p();
        return true;
    }

    public void setBrowserControlsVisibilityConstraint(int i, int i2) {
        ((Rw) this.o.get(i)).b(Integer.valueOf(i2));
    }

    public void showRepostFormWarningDialog() {
        final HP hpV = v();
        if (hpV == null) {
            this.c.i().d();
            return;
        }
        C1766xO c1766xO = new C1766xO(hpV.f, new Callback(hpV) { // from class: GP
            public final HP a;

            {
                this.a = hpV;
            }

            @Override // org.chromium.base.Callback
            public void a(Object obj) {
                Integer num = (Integer) obj;
                TabImpl tabImpl = this.a.g;
                WebContents webContents = tabImpl == null ? null : tabImpl.c;
                if (webContents == null) {
                    return;
                }
                if (num.intValue() != 1) {
                    webContents.i().d();
                } else {
                    webContents.i().f();
                }
            }
        });
        Resources resources = ((Context) hpV.e.S().get()).getResources();
        BO bo = new BO(AbstractC1711wO.q);
        bo.d(AbstractC1711wO.a, c1766xO);
        bo.c(AbstractC1711wO.c, resources, AbstractC0566bn.B1);
        bo.c(AbstractC1711wO.e, resources, AbstractC0566bn.z1);
        bo.c(AbstractC1711wO.g, resources, AbstractC0566bn.A1);
        bo.c(AbstractC1711wO.j, resources, AbstractC0566bn.t0);
        bo.b(AbstractC1711wO.m, true);
        hpV.f.e(bo.a(), 1, true);
    }

    public void u(String str, boolean z) {
        AbstractC1054kS.a();
        if (this.s == null) {
            return;
        }
        if (str.length() > 0) {
            Gx gx = this.s;
            N.MiKuFRTN(gx.a, gx, str, z, false);
        } else {
            Gx gx2 = this.s;
            N.MWOuMqhA(gx2.a, gx2, true);
        }
    }

    public final HP v() {
        if (this.j.u() == this) {
            return this.j.x();
        }
        return null;
    }

    public final void w() {
        Gx gx = this.s;
        if (gx == null) {
            return;
        }
        N.MWOuMqhA(gx.a, gx, true);
        Kx kx = this.t;
        kx.N = true;
        kx.F = null;
        Animator animator = kx.M;
        if (animator != null && animator.isRunning()) {
            kx.M.cancel();
        }
        Property property = View.TRANSLATION_X;
        float[] fArr = new float[1];
        int i = kx.y;
        if (LocalizationUtils.isLayoutRtl()) {
            i = -i;
        }
        fArr[0] = i;
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(kx, (Property<Kx, Float>) property, fArr);
        kx.M = objectAnimatorOfFloat;
        objectAnimatorOfFloat.setDuration(200L);
        kx.M.setInterpolator(InterpolatorC1217nO.h);
        kx.G.Z(kx.M);
        kx.M.addListener(new Ix(kx));
        this.t = null;
        Gx gx2 = this.s;
        N.MlPioXlo(gx2.a, gx2);
        gx2.a = 0L;
        this.s = null;
        setBrowserControlsVisibilityConstraint(2, 3);
        try {
            if (this.r != null) {
                ((BR) this.r).c();
            }
            this.r = null;
        } catch (RemoteException e) {
            throw new AndroidRuntimeException(e);
        }
    }

    public final void x(ProfileImpl profileImpl, WindowAndroid windowAndroid, long j) {
        this.b = profileImpl;
        this.a = j;
        this.c = (WebContents) N.MV_K4t5p(j, this);
        FQ fq = new FQ(this, null);
        this.i = fq;
        this.c.o("", fq, new IQ(null), windowAndroid, new C0705eE());
        GQ gq = new GQ(this);
        this.d = gq;
        this.c.d(gq);
        this.o = new ArrayList();
        this.p = new Tw(new Rw[0]);
        for (int i = 0; i < 4; i++) {
            Rw rw = new Rw(3);
            this.o.add(rw);
            this.p.c(rw);
        }
        Callback callback = new Callback(this) { // from class: EQ
            public final TabImpl a;

            {
                this.a = this;
            }

            @Override // org.chromium.base.Callback
            public void a(Object obj) {
                TabImpl tabImpl = this.a;
                Objects.requireNonNull(tabImpl);
                int iIntValue = ((Integer) obj).intValue();
                N.M$Jahyw2(tabImpl.a, iIntValue);
                if (iIntValue != 1) {
                    tabImpl.w();
                }
            }
        };
        this.q = callback;
        Tw tw = this.p;
        tw.d.f(callback);
        Object obj = tw.c;
        if (obj != null) {
            tw.b.post(new Lu(tw, obj, callback));
        }
        this.v = new C1713wQ(this);
    }

    public boolean y() {
        return this.j.u() == this && this.j.j;
    }

    public void z(RD rd) {
        GURL gurl;
        String str = rd.a;
        if (str == null || str.isEmpty()) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            gurl = AbstractC1767xP.a;
        } else {
            GURL.a();
            gurl = (GURL) N.Ml2KxI$W(str);
        }
        if (gurl.b) {
            rd.a = gurl.c();
            this.c.i().e(rd);
        }
    }

    public TabImpl(ProfileImpl profileImpl, WindowAndroid windowAndroid, long j) {
        int i = w + 1;
        w = i;
        this.n = i;
        N.MKSJ4vB6(j, this);
        x(profileImpl, windowAndroid, j);
    }
}
