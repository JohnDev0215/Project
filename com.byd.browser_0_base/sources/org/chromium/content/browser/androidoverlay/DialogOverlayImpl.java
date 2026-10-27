package org.chromium.content.browser.androidoverlay;

import J.N;
import android.R;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import defpackage.es3;
import defpackage.ki1;
import defpackage.ri3;
import defpackage.vt0;
import defpackage.w4;
import defpackage.x4;
import defpackage.xz;
import defpackage.z4;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.content.browser.androidoverlay.AndroidOverlayProviderImpl;
import org.chromium.content.browser.androidoverlay.a;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.gfx.mojom.Rect;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes3.dex */
public class DialogOverlayImpl implements w4, a.b, ViewTreeObserver.OnPreDrawListener {
    public x4 e;
    public final Runnable f;
    public a g;
    public long h;
    public int i;
    public boolean j;
    public Rect k;
    public ViewTreeObserver l;
    public final z4 m;
    public final boolean n;
    public final es3 o = new es3(29, this);
    public WebContentsImpl p;

    public DialogOverlayImpl(x4 x4Var, z4 z4Var, AndroidOverlayProviderImpl.a aVar) {
        Object obj = ThreadUtils.a;
        this.e = x4Var;
        this.f = aVar;
        Rect rect = z4Var.c;
        Rect rect2 = new Rect();
        rect2.b = rect.b;
        rect2.c = rect.c;
        rect2.d = rect.d;
        rect2.e = rect.e;
        this.k = rect2;
        this.m = z4Var;
        this.n = false;
        ri3 ri3Var = z4Var.b;
        long jMqPi0d6D = N.MqPi0d6D(this, ri3Var.b, ri3Var.c, z4Var.e);
        this.h = jMqPi0d6D;
        if (jMqPi0d6D == 0) {
            C();
            t();
        } else {
            N.MAd6qeVr(jMqPi0d6D, this, z4Var.c);
            N.MQAm7B7f(this.h, this);
        }
    }

    @CalledByNative
    public static void receiveCompositorOffset(Rect rect, int i, int i2) {
        rect.b += i;
        rect.c += i2;
    }

    public final void C() {
        x4 x4Var = this.e;
        if (x4Var == null) {
            return;
        }
        this.e = null;
        if (this.i == 0) {
            x4Var.E();
        } else {
            N.MFq0hOYg(((vt0) ((x4.b) x4Var).u0().f).f0().s0());
        }
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Object obj = ThreadUtils.a;
        if (this.j) {
            return;
        }
        this.j = true;
        a aVar = this.g;
        if (aVar != null) {
            aVar.b();
            aVar.d.token = null;
            aVar.a = null;
            t();
        }
        this.f.run();
    }

    @Override // defpackage.w4
    public final void f(Rect rect) {
        Object obj = ThreadUtils.a;
        Rect rect2 = new Rect();
        rect2.b = rect.b;
        rect2.c = rect.c;
        rect2.d = rect.d;
        rect2.e = rect.e;
        this.k = rect2;
        if (this.g == null) {
            return;
        }
        N.MAd6qeVr(this.h, this, rect);
        a aVar = this.g;
        if (aVar.b == null || aVar.d.token == null || !aVar.a(rect)) {
            return;
        }
        aVar.b.getWindow().setAttributes(aVar.d);
    }

    @CalledByNative
    public final void observeContainerView(View view) {
        ViewTreeObserver viewTreeObserver = this.l;
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            this.l.removeOnPreDrawListener(this);
        }
        this.l = null;
        if (view != null) {
            ViewTreeObserver viewTreeObserver2 = view.getViewTreeObserver();
            this.l = viewTreeObserver2;
            viewTreeObserver2.addOnPreDrawListener(this);
        }
    }

    @CalledByNative
    public void onDismissed() {
        Object obj = ThreadUtils.a;
        C();
        a aVar = this.g;
        if (aVar != null) {
            aVar.c(null);
        }
        t();
    }

    @CalledByNative
    public final void onPowerEfficientState(boolean z) {
        x4 x4Var;
        Object obj = ThreadUtils.a;
        if (this.g == null || (x4Var = this.e) == null) {
            return;
        }
        x4Var.onPowerEfficientState(z);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        f(this.k);
        return true;
    }

    @CalledByNative
    public final void onWebContents(WebContentsImpl webContentsImpl) {
        this.p = webContentsImpl;
        if (webContentsImpl.q == null) {
            webContentsImpl.q = new b<>();
        }
        webContentsImpl.q.a(this.o);
    }

    @CalledByNative
    public void onWindowAndroid(WindowAndroid windowAndroid) {
        Object obj = ThreadUtils.a;
        if (this.g != null) {
            this.g.c(windowAndroid != null ? windowAndroid.q() : null);
            return;
        }
        if (windowAndroid == null) {
            return;
        }
        Context context = windowAndroid.i.get();
        if (xz.a(context) == null) {
            return;
        }
        a aVar = new a();
        this.g = aVar;
        aVar.a = this;
        aVar.e = this.n;
        Dialog dialog = new Dialog(context, R.style.Theme.NoDisplay);
        aVar.b = dialog;
        dialog.requestWindowFeature(1);
        aVar.b.setCancelable(false);
        z4 z4Var = this.m;
        boolean z = z4Var.d;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = 51;
        layoutParams.type = aVar.e ? 1000 : 1001;
        layoutParams.flags = 568;
        if (z) {
            layoutParams.flags = 8760;
        }
        try {
            layoutParams.getClass().getField("privateFlags").set(layoutParams, Integer.valueOf(((Integer) layoutParams.getClass().getField("privateFlags").get(layoutParams)).intValue() | 64));
        } catch (ExceptionInInitializerError | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NullPointerException | SecurityException unused) {
        }
        aVar.d = layoutParams;
        aVar.a(z4Var.c);
        this.g.c(windowAndroid.q());
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
        Object obj = ThreadUtils.a;
        close();
    }

    public final void t() {
        Object obj = ThreadUtils.a;
        int i = this.i;
        if (i != 0) {
            N.M1e4GdYZ(i);
            this.i = 0;
        }
        long j = this.h;
        if (j != 0) {
            N.MJj9v_ba(j, this);
            this.h = 0L;
        }
        this.g = null;
        x4 x4Var = this.e;
        if (x4Var != null) {
            x4Var.close();
        }
        this.e = null;
        WebContentsImpl webContentsImpl = this.p;
        if (webContentsImpl != null) {
            webContentsImpl.q.c(this.o);
            this.p = null;
        }
    }
}
