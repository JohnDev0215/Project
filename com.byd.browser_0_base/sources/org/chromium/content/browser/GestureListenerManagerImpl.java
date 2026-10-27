package org.chromium.content.browser;

import J.N;
import android.graphics.Point;
import android.view.View;
import defpackage.jt3;
import defpackage.kt3;
import defpackage.lk3;
import defpackage.ms0;
import defpackage.os0;
import defpackage.v22;
import defpackage.wd0;
import defpackage.za2;
import java.util.Collections;
import java.util.HashMap;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.browser.a;
import org.chromium.ui.base.ViewAndroidDelegate;

/* JADX INFO: loaded from: classes3.dex */
public class GestureListenerManagerImpl implements ms0, jt3, lk3, ViewAndroidDelegate.b {
    public final WebContentsImpl e;
    public final b<os0> f;
    public final b.a g;
    public final HashMap<os0, Integer> h;
    public SelectionPopupControllerImpl i;
    public final ViewAndroidDelegate j;
    public a.InterfaceC0153a k;
    public final Point l = new Point();
    public long m;
    public boolean n;
    public boolean o;

    public static final class a {
        public static final wd0 a = new wd0(24);
    }

    public GestureListenerManagerImpl(WebContentsImpl webContentsImpl) {
        this.e = webContentsImpl;
        b<os0> bVar = new b<>();
        this.f = bVar;
        this.g = bVar.d();
        this.h = new HashMap<>();
        ViewAndroidDelegate viewAndroidDelegateU = webContentsImpl.u();
        this.j = viewAndroidDelegateU;
        viewAndroidDelegateU.e.a(this);
        kt3.e(webContentsImpl).b(this);
        this.m = N.MefCIE9S(this, webContentsImpl);
    }

    public static GestureListenerManagerImpl e(WebContents webContents) {
        return (GestureListenerManagerImpl) ((WebContentsImpl) webContents).n0(GestureListenerManagerImpl.class, a.a);
    }

    @Override // org.chromium.ui.base.ViewAndroidDelegate.b
    public final void b(boolean z, float f) {
        b.a aVar = this.g;
        aVar.c();
        while (aVar.hasNext()) {
            ((os0) aVar.next()).g(z);
        }
    }

    public final void d(os0 os0Var, int i) {
        boolean zA = this.f.a(os0Var);
        if (this.m == 0 || !zA) {
            return;
        }
        this.h.put(os0Var, Integer.valueOf(i));
        l();
    }

    @CalledByNative
    public final void didOverscroll(float f, float f2) {
        b.a aVar = this.g;
        aVar.c();
        while (aVar.hasNext()) {
            ((os0) aVar.next()).getClass();
        }
    }

    @CalledByNative
    public final boolean filterTapOrPressEvent(int i, int i2, int i3) {
        return i == 5 && this.j.getContainerView().performLongClick();
    }

    public final void g(float f, float f2, float f3) {
        TraceEvent.q("GestureListenerManagerImpl:onRootScrollOffsetChanged", null);
        WebContentsImpl webContentsImpl = this.e;
        za2 za2Var = webContentsImpl.l;
        this.k.onScrollChanged((int) za2Var.a(f2), (int) za2Var.a(f3), (int) za2Var.a(za2Var.a), (int) za2Var.a(za2Var.b));
        za2 za2Var2 = webContentsImpl.l;
        za2Var2.g = f;
        za2Var2.a = f2;
        za2Var2.b = f3;
        j(n(), m());
        TraceEvent.C("GestureListenerManagerImpl:onRootScrollOffsetChanged");
    }

    public final void h(os0 os0Var) {
        boolean zC = this.f.c(os0Var);
        if (this.m == 0 || !zC) {
            return;
        }
        this.h.remove(os0Var);
        l();
    }

    public final void i(boolean z) {
        this.n = z;
        if (this.i == null) {
            this.i = SelectionPopupControllerImpl.j(this.e);
        }
        this.i.l(isScrollInProgress());
    }

    @Override // defpackage.ms0
    @CalledByNative
    public boolean isScrollInProgress() {
        return this.n;
    }

    public final void j(int i, int i2) {
        b.a aVar = this.g;
        aVar.c();
        while (aVar.hasNext()) {
            ((os0) aVar.next()).d(i, i2);
        }
    }

    public final void k() {
        i(false);
        b.a aVar = this.g;
        aVar.c();
        while (aVar.hasNext()) {
            ((os0) aVar.next()).c(n(), m());
        }
    }

    public final void l() {
        HashMap<os0, Integer> map = this.h;
        N.MZSUpd4B(this.m, map.isEmpty() ? 0 : ((Integer) Collections.max(map.values())).intValue());
    }

    public final int m() {
        za2 za2Var = this.e.l;
        return (int) Math.ceil(za2Var.a(za2Var.f));
    }

    public final int n() {
        return this.e.l.b();
    }

    @CalledByNative
    public void onEventAck(int i, boolean z, float f, float f2) {
        Point point;
        b.a aVar = this.g;
        if (i == 16) {
            aVar.c();
            while (aVar.hasNext()) {
                ((os0) aVar.next()).getClass();
            }
            return;
        }
        if (i == 17) {
            aVar.c();
            while (aVar.hasNext()) {
                ((os0) aVar.next()).getClass();
            }
            return;
        }
        WebContentsImpl webContentsImpl = this.e;
        if (i == 21) {
            if (this.i == null) {
                boolean z2 = SelectionPopupControllerImpl.M;
                this.i = (SelectionPopupControllerImpl) webContentsImpl.n0(SelectionPopupControllerImpl.class, null);
            }
            SelectionPopupControllerImpl selectionPopupControllerImpl = this.i;
            if (selectionPopupControllerImpl != null) {
                selectionPopupControllerImpl.g();
            }
            aVar.c();
            while (aVar.hasNext()) {
                ((os0) aVar.next()).getClass();
            }
            return;
        }
        if (i == 24) {
            if (z) {
                this.j.getContainerView().performHapticFeedback(0);
                aVar.c();
                while (aVar.hasNext()) {
                    ((os0) aVar.next()).getClass();
                }
                return;
            }
            return;
        }
        switch (i) {
            case 12:
                k();
                break;
            case 13:
                if (z) {
                    if (this.i == null) {
                        boolean z3 = SelectionPopupControllerImpl.M;
                        this.i = (SelectionPopupControllerImpl) webContentsImpl.n0(SelectionPopupControllerImpl.class, null);
                    }
                    SelectionPopupControllerImpl selectionPopupControllerImpl2 = this.i;
                    if (selectionPopupControllerImpl2 != null) {
                        selectionPopupControllerImpl2.g();
                    }
                    aVar.c();
                    while (aVar.hasNext()) {
                        if (f < 0.0f || f2 < 0.0f) {
                            point = null;
                        } else {
                            point = this.l;
                            point.set((int) f, (int) f2);
                        }
                        ((os0) aVar.next()).f(point);
                    }
                    break;
                }
                break;
            case 14:
                k();
                break;
        }
    }

    @CalledByNative
    public void onFlingEnd() {
        this.o = false;
        b.a aVar = this.g;
        aVar.c();
        while (aVar.hasNext()) {
            ((os0) aVar.next()).a(n(), m());
        }
    }

    @CalledByNative
    public void onFlingStart(boolean z) {
        this.o = true;
        b.a aVar = this.g;
        aVar.c();
        while (aVar.hasNext()) {
            ((os0) aVar.next()).b(n(), m());
        }
    }

    @CalledByNative
    public final void onNativeDestroyed() {
        b.a aVar = this.g;
        aVar.c();
        while (aVar.hasNext()) {
            ((os0) aVar.next()).getClass();
        }
        this.f.clear();
        this.h.clear();
        this.j.e.c(this);
        this.m = 0L;
    }

    @CalledByNative
    public final void onRootScrollOffsetChanged(float f, float f2) {
        g(this.e.l.g, f, f2);
    }

    @CalledByNative
    public void onScrollBegin(boolean z) {
        i(true);
        b.a aVar = this.g;
        aVar.c();
        while (aVar.hasNext()) {
            ((os0) aVar.next()).e(n(), m(), z);
        }
    }

    @Override // defpackage.jt3
    public final void onWindowFocusChanged(boolean z) {
        if (!z) {
            long j = this.m;
            if (j != 0) {
                N.MMR0DKoy(j, this);
            }
        }
        b.a aVar = this.g;
        aVar.c();
        while (aVar.hasNext()) {
            ((os0) aVar.next()).getClass();
        }
    }

    @CalledByNative
    public void resetPopupsAndInput(boolean z) {
        ImeAdapterImpl imeAdapterImplB;
        WebContentsImpl webContentsImpl = this.e;
        if (webContentsImpl != null) {
            boolean z2 = SelectionPopupControllerImpl.M;
            SelectionPopupControllerImpl selectionPopupControllerImpl = (SelectionPopupControllerImpl) webContentsImpl.n0(SelectionPopupControllerImpl.class, null);
            if (selectionPopupControllerImpl != null) {
                selectionPopupControllerImpl.y = true;
                selectionPopupControllerImpl.i();
            }
            v22 v22Var = (v22) webContentsImpl.n0(v22.class, v22.b.a);
            if (v22Var != null) {
                v22Var.a();
            }
        }
        if (isScrollInProgress()) {
            boolean z3 = this.n;
            i(false);
            if (z3) {
                k();
            }
            if (this.o) {
                onFlingEnd();
                this.o = false;
            }
        }
        if (!z || (imeAdapterImplB = ImeAdapterImpl.b(webContentsImpl)) == null) {
            return;
        }
        imeAdapterImplB.j();
    }

    @CalledByNative
    public final void updateOnTouchDown() {
        b.a aVar = this.g;
        aVar.c();
        while (aVar.hasNext()) {
            ((os0) aVar.next()).getClass();
        }
    }

    @CalledByNative
    public final void updateScrollInfo(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, boolean z) {
        TraceEvent.q("GestureListenerManagerImpl:updateScrollInfo", null);
        za2 za2Var = this.e.l;
        float f11 = za2Var.j;
        View containerView = this.j.getContainerView();
        float f12 = f11 * f3;
        float fMax = Math.max(f6, containerView.getWidth() / f12);
        float fMax2 = Math.max(f7, containerView.getHeight() / f12);
        boolean z2 = (f4 == za2Var.h && f5 == za2Var.i) ? false : true;
        boolean z3 = (!((f3 > za2Var.g ? 1 : (f3 == za2Var.g ? 0 : -1)) != 0) && f == za2Var.a && f2 == za2Var.b) ? false : true;
        if (z3) {
            g(f3, f, f2);
        }
        za2Var.h = f4;
        za2Var.i = f5;
        za2Var.k = f10;
        za2Var.c = fMax;
        za2Var.d = fMax2;
        za2Var.e = f8;
        za2Var.f = f9;
        if (!z3 && z) {
            j(n(), m());
        }
        if (z2) {
            b.a aVar = this.g;
            aVar.c();
            while (aVar.hasNext()) {
                ((os0) aVar.next()).getClass();
            }
        }
        TraceEvent.C("GestureListenerManagerImpl:updateScrollInfo");
    }
}
