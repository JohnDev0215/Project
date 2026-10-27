package org.chromium.content.browser;

import J.N;
import android.view.ViewGroup;
import defpackage.AbstractC1024jz;
import defpackage.Az;
import defpackage.C0590cA;
import defpackage.C1905zz;
import defpackage.HD;
import defpackage.ID;
import defpackage.InterfaceC0594cE;
import defpackage.InterfaceC1074kt;
import defpackage.JD;
import defpackage.Ws;
import defpackage.Xs;
import defpackage.Xz;
import defpackage.Ys;
import defpackage.Yz;
import java.util.Objects;
import org.chromium.base.TraceEvent;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewAndroidDelegate;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class GestureListenerManagerImpl extends Xz implements HD, Yz, InterfaceC1074kt {
    public final WebContentsImpl s;
    public final Ys t;
    public final Xs u;
    public ViewAndroidDelegate v;
    public InterfaceC0594cE w;
    public long x;
    public boolean y;
    public boolean z;

    public GestureListenerManagerImpl(WebContents webContents) {
        this.s = (WebContentsImpl) webContents;
        Ys ys = new Ys();
        this.t = ys;
        this.u = ys.h();
        this.v = this.s.D();
        C0590cA c0590cAP = C0590cA.P(this.s);
        c0590cAP.s.f(this);
        boolean z = c0590cAP.v;
        this.x = N.MefCIE9S(this, this.s);
    }

    public static GestureListenerManagerImpl O(WebContents webContents) {
        return (GestureListenerManagerImpl) ((WebContentsImpl) webContents).S(GestureListenerManagerImpl.class, AbstractC1024jz.a);
    }

    private boolean filterTapOrPressEvent(int i, int i2, int i3) {
        return i == 5 && this.v.getContainerView().performLongClick();
    }

    private void onEventAck(int i, boolean z) {
        if (i == 16) {
            ((Ws) this.u).b();
            while (this.u.hasNext()) {
                ((JD) this.u.next()).j();
            }
            return;
        }
        if (i == 17) {
            ((Ws) this.u).b();
            while (this.u.hasNext()) {
                ((JD) this.u.next()).d();
            }
            return;
        }
        if (i == 21) {
            SelectionPopupControllerImpl selectionPopupControllerImplT = SelectionPopupControllerImpl.t(this.s);
            if (selectionPopupControllerImplT != null) {
                selectionPopupControllerImplT.r();
            }
            ((Ws) this.u).b();
            while (this.u.hasNext()) {
                Objects.requireNonNull((ID) ((JD) this.u.next()));
            }
            return;
        }
        if (i == 23) {
            if (z) {
                this.v.getContainerView().performHapticFeedback(0);
                ((Ws) this.u).b();
                while (this.u.hasNext()) {
                    Objects.requireNonNull((ID) ((JD) this.u.next()));
                }
                return;
            }
            return;
        }
        switch (i) {
            case 11:
                P(true);
                ((Ws) this.u).b();
                while (this.u.hasNext()) {
                    ((JD) this.u.next()).b(S(), R());
                }
                break;
            case 12:
                Q();
                break;
            case 13:
                if (z) {
                    SelectionPopupControllerImpl selectionPopupControllerImplT2 = SelectionPopupControllerImpl.t(this.s);
                    if (selectionPopupControllerImplT2 != null) {
                        selectionPopupControllerImplT2.r();
                    }
                    ((Ws) this.u).b();
                    while (this.u.hasNext()) {
                        ((JD) this.u.next()).a();
                    }
                    break;
                }
                break;
            case 14:
                if (!z) {
                    Q();
                } else {
                    this.z = true;
                    ((Ws) this.u).b();
                    while (this.u.hasNext()) {
                        ((JD) this.u.next()).c(S(), R());
                    }
                }
                break;
        }
    }

    private void onFlingEnd() {
        this.z = false;
        ((Ws) this.u).b();
        while (this.u.hasNext()) {
            ((JD) this.u.next()).f(S(), R());
        }
    }

    private void onNativeDestroyed() {
        ((Ws) this.u).b();
        while (this.u.hasNext()) {
            ((JD) this.u.next()).i();
        }
        this.t.clear();
        this.x = 0L;
    }

    private void resetPopupsAndInput(boolean z) {
        ImeAdapterImpl imeAdapterImplP;
        C1905zz.c(this.s);
        if (isScrollInProgress()) {
            boolean z2 = this.y;
            P(false);
            if (z2) {
                Q();
            }
            if (this.z) {
                onFlingEnd();
                this.z = false;
            }
        }
        if (!z || (imeAdapterImplP = ImeAdapterImpl.P(this.s)) == null) {
            return;
        }
        imeAdapterImplP.X();
    }

    private void updateOnTouchDown() {
        ((Ws) this.u).b();
        while (this.u.hasNext()) {
            Objects.requireNonNull((ID) ((JD) this.u.next()));
        }
    }

    private void updateScrollInfo(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, boolean z) {
        String str;
        TraceEvent.x("GestureListenerManagerImpl:updateScrollInfo", null);
        Az az = this.s.y;
        float f11 = az.j;
        ViewGroup containerView = this.v.getContainerView();
        float f12 = f11 * f3;
        float fMax = Math.max(f6, containerView.getWidth() / f12);
        float fMax2 = Math.max(f7, containerView.getHeight() / f12);
        boolean z2 = true;
        boolean z3 = (f4 == az.h && f5 == az.i) ? false : true;
        if (!(f3 != az.g) && f == az.a && f2 == az.b) {
            z2 = false;
        }
        if (z2) {
            InterfaceC0594cE interfaceC0594cE = this.w;
            float f13 = az.g;
            float f14 = az.j;
            str = "GestureListenerManagerImpl:updateScrollInfo";
            interfaceC0594cE.onScrollChanged((int) (f * f13 * f14), (int) (f13 * f2 * f14), (int) az.c(), (int) az.d());
        } else {
            str = "GestureListenerManagerImpl:updateScrollInfo";
        }
        az.a = f;
        az.b = f2;
        az.g = f3;
        az.h = f4;
        az.i = f5;
        az.k = f10;
        az.c = fMax;
        az.d = fMax2;
        az.e = f8;
        az.f = f9;
        if (z2 || z) {
            int iS = S();
            int iR = R();
            ((Ws) this.u).b();
            while (this.u.hasNext()) {
                ((JD) this.u.next()).g(iS, iR);
            }
        }
        if (z3) {
            ((Ws) this.u).b();
            while (this.u.hasNext()) {
                ((JD) this.u.next()).h(f4, f5);
            }
        }
        TraceEvent.A(str);
    }

    public final void P(boolean z) {
        this.y = z;
        SelectionPopupControllerImpl.t(this.s).A(isScrollInProgress());
    }

    public void Q() {
        P(false);
        ((Ws) this.u).b();
        while (this.u.hasNext()) {
            ((JD) this.u.next()).e(S(), R());
        }
    }

    public final int R() {
        return this.s.y.b();
    }

    public final int S() {
        return (int) Math.floor(this.s.y.d());
    }

    @Override // defpackage.HD
    public boolean isScrollInProgress() {
        return this.y;
    }

    @Override // defpackage.Xz, defpackage.Yz
    public void onWindowFocusChanged(boolean z) {
        if (!z) {
            long j = this.x;
            if (j != 0) {
                N.MMR0DKoy(j, this);
            }
        }
        ((Ws) this.u).b();
        while (this.u.hasNext()) {
            ((JD) this.u.next()).onWindowFocusChanged(z);
        }
    }
}
