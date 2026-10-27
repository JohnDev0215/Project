package defpackage;

import android.graphics.Rect;
import defpackage.z92;
import org.chromium.chrome.browser.keyboard_accessory.a;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewAndroidDelegate;

/* JADX INFO: compiled from: WebContentsViewRectProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class sq3 extends z92 {
    public final WebContents g;
    public zp1<fj> h;
    public zp1<a> i;
    public zp1<Integer> j;
    public final so k = new so(8, this);
    public final wb l = new wb(6, this);
    public final c3 m = new c3(7, this);

    public sq3(WebContents webContents, zp1<fj> zp1Var, zp1<a> zp1Var2) {
        this.g = webContents;
        f(zp1Var2);
        e(zp1Var);
    }

    public static Object c(zp1 zp1Var) {
        if (zp1Var != null) {
            return zp1Var.get();
        }
        return null;
    }

    public final void d(zp1<Integer> zp1Var) {
        zp1<Integer> zp1Var2 = this.j;
        wb wbVar = this.l;
        if (zp1Var2 != null) {
            zp1Var2.d(wbVar);
        }
        this.j = zp1Var;
        if (zp1Var != null) {
            zp1Var.p(wbVar);
        }
        g((Integer) c(this.j), (fj) c(this.h));
    }

    public final void e(zp1<fj> zp1Var) {
        zp1<fj> zp1Var2 = this.h;
        c3 c3Var = this.m;
        if (zp1Var2 != null) {
            zp1Var2.d(c3Var);
        }
        this.h = zp1Var;
        if (zp1Var != null) {
            zp1Var.p(c3Var);
        }
        g((Integer) c(this.j), (fj) c(this.h));
    }

    public final void f(zp1<a> zp1Var) {
        zp1<a> zp1Var2 = this.i;
        so soVar = this.k;
        if (zp1Var2 != null) {
            d(null);
            this.i.d(soVar);
        }
        this.i = zp1Var;
        if (zp1Var != null) {
            zp1Var.p(soVar);
            d(this.i.f() ? this.i.get().t0() : null);
        }
    }

    public final void g(Integer num, fj fjVar) {
        ViewAndroidDelegate viewAndroidDelegateU = this.g.u();
        if (viewAndroidDelegateU == null || viewAndroidDelegateU.getContainerView() == null) {
            return;
        }
        Rect rect = new Rect();
        viewAndroidDelegateU.getContainerView().getGlobalVisibleRect(rect);
        if (fjVar != null) {
            rect.top += fjVar.l;
            rect.bottom -= fjVar.u();
        }
        if (num != null) {
            rect.bottom -= num.intValue();
        }
        Rect rect2 = this.e;
        if (rect2.equals(rect)) {
            return;
        }
        rect2.set(rect);
        z92.a aVar = this.f;
        if (aVar != null) {
            ((p4) aVar).g();
        }
    }
}
