package org.chromium.content.browser.selection;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.ActionMode;
import android.view.View;
import android.view.WindowManager;
import android.view.textclassifier.SelectionEvent;
import android.view.textclassifier.TextClassificationContext;
import android.view.textclassifier.TextClassificationManager;
import android.widget.Magnifier;
import android.widget.directwriting.IDirectWritingService;
import defpackage.a4;
import defpackage.ab1;
import defpackage.aq1;
import defpackage.ci2;
import defpackage.di2;
import defpackage.fi2;
import defpackage.gc3;
import defpackage.gi2;
import defpackage.j2;
import defpackage.jt3;
import defpackage.kt3;
import defpackage.kx0;
import defpackage.lk3;
import defpackage.my;
import defpackage.np2;
import defpackage.qh1;
import defpackage.v22;
import defpackage.vn0;
import defpackage.wt1;
import defpackage.x41;
import defpackage.za2;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content.browser.RenderWidgetHostViewImpl;
import org.chromium.content.browser.input.ImeAdapterImpl;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;
import vn0.a;

/* JADX INFO: loaded from: classes3.dex */
public class SelectionPopupControllerImpl extends j2 implements kx0, jt3, v22.a, lk3 {
    public static boolean M;
    public int A;
    public boolean B;
    public boolean C;
    public vn0 D;
    public boolean E;
    public ci2 F;
    public np2 G;
    public v22 H;
    public ci2.a I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f90J;
    public ab1 K;
    public final a4 L;
    public Context g;
    public WindowAndroid h;
    public final WebContentsImpl i;
    public ActionMode.Callback2 j;
    public RenderFrameHost k;
    public long l;
    public final b m;
    public final fi2 o;
    public final View p;
    public ActionMode q;
    public int s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean y;
    public String z;
    public final Rect n = new Rect();
    public final aq1<Boolean> r = new aq1<>();
    public final Handler f = new Handler();

    public interface a {
    }

    public class b implements ci2.b {
        public b() {
        }

        public final void a(ci2.a aVar) {
            int i;
            SelectionPopupControllerImpl selectionPopupControllerImpl = SelectionPopupControllerImpl.this;
            if (!selectionPopupControllerImpl.C) {
                selectionPopupControllerImpl.I = null;
                return;
            }
            int i2 = aVar.a;
            if (i2 > 0 || (i = aVar.b) < 0) {
                selectionPopupControllerImpl.I = null;
                selectionPopupControllerImpl.s();
                return;
            }
            selectionPopupControllerImpl.I = aVar;
            if (i2 != 0 || i != 0) {
                N.MjgOFo_o(selectionPopupControllerImpl.i.f, i2, i, true);
                return;
            }
            np2 np2Var = selectionPopupControllerImpl.G;
            if (np2Var != null) {
                np2Var.d(selectionPopupControllerImpl.z, selectionPopupControllerImpl.A, aVar);
            }
            selectionPopupControllerImpl.s();
        }
    }

    public static final class c {
        public static final qh1 a = new qh1(21);
    }

    public SelectionPopupControllerImpl(WebContentsImpl webContentsImpl) {
        this.i = webContentsImpl;
        this.H = null;
        this.g = webContentsImpl.j0();
        this.h = webContentsImpl.V();
        ViewAndroidDelegate viewAndroidDelegateU = webContentsImpl.u();
        if (viewAndroidDelegateU != null) {
            this.p = viewAndroidDelegateU.getContainerView();
            viewAndroidDelegateU.d.a(this);
        }
        this.s = 7;
        this.o = new fi2(this);
        kt3 kt3VarE = kt3.e(webContentsImpl);
        if (kt3VarE != null) {
            kt3VarE.b(this);
        }
        this.l = N.MJHXNa8U(this, webContentsImpl);
        ImeAdapterImpl imeAdapterImplB = ImeAdapterImpl.b(webContentsImpl);
        if (imeAdapterImplB != null) {
            imeAdapterImplB.m.add(this);
        }
        this.m = new b();
        this.z = "";
        this.K = new ab1(new org.chromium.content.browser.selection.a(new gc3(this)));
        Object obj = ThreadUtils.a;
        if (my.a == null) {
            my.a = new my();
        }
        my.a.getClass();
        this.L = new a4();
        if (this.H == null) {
            this.H = (v22) webContentsImpl.n0(v22.class, v22.b.a);
        }
        this.H.e.add(this);
        this.j = j2.e;
    }

    public static SelectionPopupControllerImpl j(WebContents webContents) {
        return (SelectionPopupControllerImpl) ((WebContentsImpl) webContents).n0(SelectionPopupControllerImpl.class, c.a);
    }

    public static String r(int i, String str) {
        if (TextUtils.isEmpty(str) || str.length() < i) {
            return str;
        }
        Log.w("cr_SelectionPopupCtlr", "Truncating oversized query (" + str.length() + ").");
        return str.substring(0, i) + "…";
    }

    @Override // defpackage.jt3
    public final void a(WindowAndroid windowAndroid) {
        v22 v22Var;
        WebContentsImpl webContentsImpl = this.i;
        if (windowAndroid != null) {
            this.h = windowAndroid;
            this.g = webContentsImpl.j0();
            this.K = new ab1(new org.chromium.content.browser.selection.a(new gc3(this)));
            g();
            return;
        }
        this.y = true;
        i();
        h();
        if (webContentsImpl != null && (v22Var = (v22) webContentsImpl.n0(v22.class, v22.b.a)) != null) {
            v22Var.a();
        }
        d();
        this.g = null;
        this.h = null;
    }

    @Override // v22.a
    public final void b() {
        g();
    }

    public final void d() {
        WebContentsImpl webContentsImpl = this.i;
        if (webContentsImpl != null) {
            if (this.j != j2.e) {
                if (!webContentsImpl.isDestroyed()) {
                    N.MDK_KK0z(webContentsImpl.f);
                }
                this.I = null;
                this.C = false;
            }
        }
    }

    @Override // defpackage.jt3
    public final void f(boolean z, boolean z2) {
        v22 v22Var;
        if (z) {
            restoreSelectionPopupsIfNecessary();
            return;
        }
        WebContentsImpl webContentsImpl = this.i;
        ImeAdapterImpl.b(webContentsImpl).t.setEmpty();
        if (this.f90J) {
            this.f90J = false;
            hidePopupsAndPreserveSelection();
            return;
        }
        this.y = true;
        i();
        h();
        if (webContentsImpl != null && (v22Var = (v22) webContentsImpl.n0(v22.class, v22.b.a)) != null) {
            v22Var.a();
        }
        d();
    }

    public final void g() {
        vn0 vn0Var = this.D;
        if (vn0Var != null) {
            ActionMode actionMode = vn0Var.d;
            if (actionMode != null) {
                actionMode.finish();
                vn0Var.d = null;
            }
            this.D = null;
        }
    }

    @CalledByNative
    public final Context getContext() {
        return this.g;
    }

    public final void h() {
        WebContentsImpl webContentsImpl = this.i;
        if (webContentsImpl.b0() != null) {
            RenderWidgetHostViewImpl renderWidgetHostViewImplO0 = webContentsImpl.b0();
            long j = renderWidgetHostViewImplO0.a;
            if (j == 0) {
                return;
            }
            N.MQWja$xA(j, renderWidgetHostViewImplO0);
        }
    }

    @CalledByNative
    public void hidePopupsAndPreserveSelection() {
        this.y = false;
        i();
        if (this.H == null) {
            this.H = (v22) this.i.n0(v22.class, v22.b.a);
        }
        this.H.a();
    }

    public final void i() {
        this.t = false;
        this.f.removeCallbacks(this.o);
        if (m()) {
            this.q.finish();
            this.q = null;
            this.r.r(Boolean.valueOf(m()));
        }
    }

    public final Rect k() {
        WebContentsImpl webContentsImpl = this.i;
        float f = webContentsImpl.l.j;
        Rect rect = this.n;
        Rect rect2 = new Rect((int) (rect.left * f), (int) (rect.top * f), (int) (rect.right * f), (int) (rect.bottom * f));
        rect2.offset(0, (int) webContentsImpl.l.k);
        return rect2;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0010  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l(boolean r3) {
        /*
            r2 = this;
            boolean r0 = r2.m()
            if (r0 == 0) goto L10
            android.view.ActionMode r0 = r2.q
            int r0 = r0.getType()
            r1 = 1
            if (r0 != r1) goto L10
            goto L11
        L10:
            r1 = 0
        L11:
            if (r1 != 0) goto L14
            return
        L14:
            boolean r0 = r2.t
            if (r0 != r3) goto L19
            return
        L19:
            r2.t = r3
            fi2 r0 = r2.o
            if (r3 == 0) goto L23
            r0.run()
            goto L35
        L23:
            android.os.Handler r3 = r2.f
            r3.removeCallbacks(r0)
            boolean r3 = r2.m()
            if (r3 == 0) goto L35
            android.view.ActionMode r2 = r2.q
            r0 = 300(0x12c, double:1.48E-321)
            r2.hide(r0)
        L35:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.selection.SelectionPopupControllerImpl.l(boolean):void");
    }

    public final boolean m() {
        return this.q != null;
    }

    public final boolean n(int i) {
        boolean z = (this.s & i) != 0;
        if (i != 1) {
            return z;
        }
        if (!z) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        return wt1.a(intent, 65536);
    }

    @CalledByNative
    public final void nativeSelectionPopupControllerDestroyed() {
        this.l = 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean o(android.view.ActionMode r10, android.view.MenuItem r11) {
        /*
            Method dump skipped, instruction units count: 388
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.selection.SelectionPopupControllerImpl.o(android.view.ActionMode, android.view.MenuItem):boolean");
    }

    @Override // defpackage.jt3
    public final void onAttachedToWindow() {
        x(true);
    }

    @Override // defpackage.jt3
    public final void onDetachedFromWindow() {
        x(false);
    }

    @CalledByNative
    public void onDragUpdate(int i, float f, float f2) {
        ab1 ab1Var;
        View viewO;
        if ((i != 2 || Build.VERSION.SDK_INT >= 31) && (ab1Var = this.K) != null) {
            za2 za2Var = this.i.l;
            float f3 = za2Var.j;
            float f4 = f * f3;
            float f5 = (f2 * f3) + za2Var.k;
            org.chromium.content.browser.selection.a aVar = (org.chromium.content.browser.selection.a) ab1Var.a;
            SelectionPopupControllerImpl selectionPopupControllerImpl = (SelectionPopupControllerImpl) ((gc3) aVar.b).e;
            View viewO2 = null;
            if (M) {
                WindowAndroid windowAndroid = selectionPopupControllerImpl.h;
                viewO = windowAndroid == null ? null : windowAndroid.o();
            } else {
                viewO = selectionPopupControllerImpl.p;
            }
            if (viewO != null) {
                if (ab1Var.c && f5 != ab1Var.i) {
                    if (ab1Var.b.isRunning()) {
                        ab1Var.b.cancel();
                        ab1Var.a();
                        ab1Var.f = ab1Var.d;
                        ab1Var.g = ab1Var.e;
                    } else {
                        ab1Var.f = ab1Var.h;
                        ab1Var.g = ab1Var.i;
                    }
                    ab1Var.b.start();
                } else if (!ab1Var.b.isRunning()) {
                    SelectionPopupControllerImpl selectionPopupControllerImpl2 = (SelectionPopupControllerImpl) ((gc3) aVar.b).e;
                    if (M) {
                        WindowAndroid windowAndroid2 = selectionPopupControllerImpl2.h;
                        if (windowAndroid2 != null) {
                            viewO2 = windowAndroid2.o();
                        }
                    } else {
                        viewO2 = selectionPopupControllerImpl2.p;
                    }
                    if (viewO2 != null) {
                        if (aVar.a == null) {
                            aVar.a = new Magnifier(viewO2);
                        }
                        aVar.a.show(f4, f5);
                    }
                }
                ab1Var.h = f4;
                ab1Var.i = f5;
                ab1Var.c = true;
            }
        }
    }

    @CalledByNative
    public final void onSelectAroundCaretFailure() {
        ci2 ci2Var = this.F;
        if (ci2Var != null) {
            ci2Var.getClass();
        }
    }

    @CalledByNative
    public final void onSelectAroundCaretSuccess(int i, int i2, int i3, int i4) {
        ci2 ci2Var = this.F;
        if (ci2Var != null) {
            ci2Var.getClass();
        }
    }

    @CalledByNative
    public void onSelectionChanged(String str) {
        if (TextUtils.isEmpty(str) && this.C) {
            np2 np2Var = this.G;
            if (np2Var != null) {
                np2Var.c(this.z, this.A, 107, null);
            }
            this.y = false;
            i();
        }
        this.z = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0087  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onSelectionEvent(int r8, int r9, int r10, int r11, int r12) {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.selection.SelectionPopupControllerImpl.onSelectionEvent(int, int, int, int, int):void");
    }

    @Override // defpackage.jt3
    public final void onWindowFocusChanged(boolean z) {
        if (m()) {
            this.q.onWindowFocusChanged(z);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00d1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void p(android.view.ActionMode r13, android.view.Menu r14) {
        /*
            Method dump skipped, instruction units count: 516
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.content.browser.selection.SelectionPopupControllerImpl.p(android.view.ActionMode, android.view.Menu):void");
    }

    @Override // defpackage.kx0
    public final void q(boolean z, boolean z2) {
        if (!z) {
            g();
        }
        if (z == this.u && z2 == this.v) {
            return;
        }
        this.u = z;
        this.v = z2;
        if (m()) {
            this.q.invalidate();
        }
    }

    @CalledByNative
    public void restoreSelectionPopupsIfNecessary() {
        if (!this.C || m()) {
            return;
        }
        s();
    }

    public final void s() {
        View view;
        if ((this.j != j2.e) && this.C && (view = this.p) != null) {
            if (m()) {
                if (!(m() && this.q.getType() == 1)) {
                    try {
                        this.q.invalidate();
                    } catch (NullPointerException e) {
                        Log.w("cr_SelectionPopupCtlr", "Ignoring NPE from ActionMode.invalidate() as workaround for L", e);
                    }
                    l(false);
                    return;
                }
            }
            this.y = false;
            i();
            ActionMode actionModeStartActionMode = view.startActionMode(this.j, 1);
            if (actionModeStartActionMode != null) {
                x41.b(this.g, actionModeStartActionMode);
            }
            this.q = actionModeStartActionMode;
            this.r.r(Boolean.valueOf(m()));
            this.y = true;
            if (m()) {
                return;
            }
            d();
        }
    }

    @CalledByNative
    public void showSelectionMenu(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, String str, int i6, boolean z3, boolean z4, boolean z5, int i7, RenderFrameHost renderFrameHost) {
        Context context;
        this.n.set(i, i2, i3, i4 + i5);
        this.u = z;
        this.z = str;
        this.A = i6;
        boolean z6 = str.length() != 0;
        this.C = z6;
        this.v = z2;
        this.w = z3;
        this.x = z4;
        this.y = true;
        if (!z6) {
            View view = this.p;
            if (view == null || view.getParent() == null || view.getVisibility() != 0) {
                return;
            }
            g();
            gi2 gi2Var = new gi2(this);
            Context context2 = this.h.i.get();
            if (context2 == null) {
                return;
            }
            this.D = new vn0(context2, view, gi2Var);
            v();
            return;
        }
        this.k = renderFrameHost;
        np2 np2Var = this.G;
        if (np2Var != null && i7 != 7) {
            if (i7 == 9) {
                np2Var.d(this.z, this.A, this.I);
            } else if (i7 != 10) {
                String str2 = this.z;
                int i8 = this.A;
                WindowAndroid windowAndroid = np2Var.a;
                if (windowAndroid != null && (context = windowAndroid.i.get()) != null) {
                    np2Var.b = ((TextClassificationManager) context.getSystemService("textclassification")).createTextClassificationSession(new TextClassificationContext.Builder(context.getPackageName(), z ? "edit-webview" : IDirectWritingService.VALUE_BUNDLE_SERVICE_HOST_SOURCE_WEBVIEW).build());
                    di2 di2Var = new di2();
                    np2Var.c = di2Var;
                    di2Var.c(i8, str2);
                    np2Var.c.e = i8;
                    np2Var.b(SelectionEvent.createSelectionStartedEvent(1, 0));
                }
            } else {
                np2Var.c(this.z, this.A, 201, null);
            }
        }
        if (i7 == 9) {
            s();
            return;
        }
        ci2 ci2Var = this.F;
        if (ci2Var == null) {
            s();
            return;
        }
        SmartSelectionClient smartSelectionClient = (SmartSelectionClient) ci2Var;
        long j = smartSelectionClient.a;
        if (j == 0) {
            smartSelectionClient.onSurroundingTextReceived(z5 ? 1 : 0, "", 0, 0);
        } else {
            N.M2GZ6ZNR(j, smartSelectionClient, 240, z5 ? 1 : 0);
        }
    }

    public final void u(int i, int i2) {
        WebContentsImpl webContentsImpl = this.i;
        if (webContentsImpl.b0() != null) {
            RenderWidgetHostViewImpl renderWidgetHostViewImplO0 = webContentsImpl.b0();
            long j = renderWidgetHostViewImplO0.a;
            if (j == 0) {
                throw new IllegalStateException("Native RenderWidgetHostViewAndroid already destroyed", renderWidgetHostViewImplO0.b);
            }
            N.McU85DFE(j, renderWidgetHostViewImplO0, i, i2);
        }
    }

    public final void v() {
        try {
            vn0 vn0Var = this.D;
            vn0Var.e = k();
            ActionMode actionMode = vn0Var.d;
            if (actionMode != null) {
                actionMode.invalidateContentRect();
            } else if (actionMode == null) {
                ActionMode actionModeStartActionMode = vn0Var.a.startActionMode(vn0Var.new a(), 1);
                if (actionModeStartActionMode != null) {
                    x41.b(vn0Var.c, actionModeStartActionMode);
                    vn0Var.d = actionModeStartActionMode;
                }
            }
        } catch (WindowManager.BadTokenException unused) {
        }
    }

    @Override // ya0.a
    public final void w(int i) {
        if (m()) {
            hidePopupsAndPreserveSelection();
            s();
        }
    }

    public final void x(boolean z) {
        boolean z2 = !z;
        long j = this.l;
        if (j != 0) {
            N.M01adZlM(j, this, z2);
        }
        if (z) {
            restoreSelectionPopupsIfNecessary();
            return;
        }
        this.y = false;
        i();
        if (this.H == null) {
            this.H = (v22) this.i.n0(v22.class, v22.b.a);
        }
        this.H.a();
    }
}
