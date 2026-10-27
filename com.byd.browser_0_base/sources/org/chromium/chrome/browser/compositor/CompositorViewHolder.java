package org.chromium.chrome.browser.compositor;

import J.N;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import defpackage.C0188h0;
import defpackage.ao3;
import defpackage.aq1;
import defpackage.df0;
import defpackage.di0;
import defpackage.ds2;
import defpackage.e00;
import defpackage.fj;
import defpackage.g61;
import defpackage.h61;
import defpackage.in3;
import defpackage.j61;
import defpackage.je2;
import defpackage.kj;
import defpackage.l23;
import defpackage.mo3;
import defpackage.nh0;
import defpackage.om3;
import defpackage.pi0;
import defpackage.rq0;
import defpackage.rw;
import defpackage.ry;
import defpackage.sw;
import defpackage.t41;
import defpackage.tw;
import defpackage.uh3;
import defpackage.ur3;
import defpackage.uw;
import defpackage.vw;
import defpackage.ww;
import defpackage.x7;
import defpackage.xd3;
import defpackage.y13;
import defpackage.yj1;
import defpackage.zd3;
import defpackage.zp1;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.WeakHashMap;
import org.chromium.base.Callback;
import org.chromium.base.TraceEvent;
import org.chromium.base.b;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.compositor.CompositorView;
import org.chromium.chrome.browser.compositor.a;
import org.chromium.chrome.browser.layouts.EventFilter;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.components.browser_ui.widget.InsetObserverView;
import org.chromium.components.content_capture.OnscreenContentProvider;
import org.chromium.components.embedder_support.view.ContentView;
import org.chromium.components.prefs.PrefService;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: loaded from: classes.dex */
public class CompositorViewHolder extends FrameLayout implements ry, g61, j61, a.InterfaceC0074a, kj.a, InsetObserverView.d, C0188h0.b, l23.a, ViewGroup.OnHierarchyChangeListener {
    public static final yj1 b0 = new yj1("DeferKeepScreenOnDuringGesture", false);
    public static final yj1 c0 = new yj1("DeferNotifyInMotion", false);
    public static final yj1 d0 = new yj1("ResizeOnlyActiveTab", false);
    public boolean A;
    public ww B;
    public Tab C;
    public View D;
    public ContentView E;
    public b F;
    public final Rect G;
    public final Point H;
    public boolean I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f64J;
    public boolean K;
    public boolean L;
    public x7 M;
    public final Callback<Integer> N;
    public boolean O;
    public int P;
    public OnscreenContentProvider Q;
    public final HashSet R;
    public final HashSet S;
    public final HashSet T;
    public MotionEvent U;
    public xd3 V;
    public View W;
    public PrefService a0;
    public androidx.appcompat.widget.a e;
    public final org.chromium.base.b<zd3> f;
    public final aq1<Boolean> g;
    public di0 h;
    public boolean i;
    public boolean j;
    public final org.chromium.chrome.browser.compositor.a k;
    public h61 l;
    public CompositorView m;
    public boolean n;
    public boolean o;
    public int p;
    public final ArrayList<Runnable> q;
    public boolean r;
    public Runnable s;
    public y13 t;
    public fj u;
    public c v;
    public d w;
    public e00 x;
    public InsetObserverView y;
    public zp1<Integer> z;

    public static class DebugOverlay extends View {
        public final ArrayList e;
        public final Paint f;

        public DebugOverlay(Context context) {
            super(context);
            this.e = new ArrayList();
            this.f = new Paint();
        }

        @Override // android.view.View
        public final void onDraw(Canvas canvas) {
            int i = 0;
            while (true) {
                ArrayList arrayList = this.e;
                if (i >= arrayList.size()) {
                    return;
                }
                Paint paint = this.f;
                paint.setColor(((Integer) ((Pair) arrayList.get(i)).second).intValue());
                canvas.drawRect((Rect) ((Pair) arrayList.get(i)).first, paint);
                i++;
            }
        }
    }

    public class a implements di0.a {
        public final RectF a = new RectF();

        public a() {
        }
    }

    public class b extends nh0 {

        public class a implements View.OnLayoutChangeListener {
            public a() {
            }

            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                view.removeOnLayoutChangeListener(this);
                b bVar = b.this;
                MotionEvent motionEvent = CompositorViewHolder.this.U;
                if (motionEvent == null) {
                    return;
                }
                MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
                motionEventObtain.setAction(0);
                CompositorViewHolder.this.dispatchTouchEvent(motionEventObtain);
                for (int i9 = 1; i9 < CompositorViewHolder.this.U.getPointerCount(); i9++) {
                    MotionEvent motionEventObtain2 = MotionEvent.obtain(CompositorViewHolder.this.U);
                    motionEventObtain2.setAction((i9 << 8) | 5);
                    CompositorViewHolder.this.dispatchTouchEvent(motionEventObtain2);
                }
            }
        }

        public b() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void Q(Tab tab, boolean z, boolean z2) {
            CompositorViewHolder.this.D.addOnLayoutChangeListener(new a());
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void e(boolean z) {
            CompositorViewHolder compositorViewHolder = CompositorViewHolder.this;
            compositorViewHolder.L = z;
            compositorViewHolder.A();
            if (z) {
                return;
            }
            compositorViewHolder.z();
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
            if (navigationHandle.c || !navigationHandle.g) {
                return;
            }
            CompositorViewHolder compositorViewHolder = CompositorViewHolder.this;
            compositorViewHolder.D(compositorViewHolder.getWebContents().l0());
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void u(Tab tab) {
            CompositorViewHolder.this.t();
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void v1(int i) {
            CompositorViewHolder.this.D(i);
        }
    }

    public class c extends View {
        public boolean e;
        public final ArrayList f;

        public c(Context context) {
            super(context);
            this.f = new ArrayList();
        }

        @Override // android.view.View
        public final boolean isImportantForAccessibility() {
            CompositorViewHolder compositorViewHolder = CompositorViewHolder.this;
            if (compositorViewHolder.j && !this.e) {
                this.e = true;
                ArrayList arrayList = this.f;
                arrayList.clear();
                compositorViewHolder.l.f0(arrayList);
                int i = arrayList.size() == 0 ? 2 : 0;
                if (getImportantForAccessibility() != i) {
                    setImportantForAccessibility(i);
                    sendAccessibilityEvent(2048);
                }
                this.e = false;
            }
            return super.isImportantForAccessibility();
        }
    }

    public class d extends pi0 {
        public final float q;
        public final ArrayList r;
        public final Rect s;
        public final RectF t;
        public final Rect u;

        public d(View view) {
            super(view);
            this.r = new ArrayList();
            this.s = new Rect(0, 0, 1, 1);
            this.t = new RectF();
            this.u = new Rect();
            this.q = CompositorViewHolder.this.getContext().getResources().getDisplayMetrics().density;
        }

        @Override // defpackage.pi0
        public final int n(float f, float f2) {
            ArrayList arrayList = this.r;
            if (arrayList == null) {
                return Integer.MIN_VALUE;
            }
            for (int i = 0; i < arrayList.size(); i++) {
                mo3 mo3Var = (mo3) arrayList.get(i);
                float f3 = this.q;
                if (mo3Var.c(f / f3, f2 / f3)) {
                    return i;
                }
            }
            return Integer.MIN_VALUE;
        }

        @Override // defpackage.pi0
        public final void o(ArrayList arrayList) {
            CompositorViewHolder compositorViewHolder = CompositorViewHolder.this;
            if (compositorViewHolder.l == null) {
                return;
            }
            ArrayList arrayList2 = this.r;
            arrayList2.clear();
            compositorViewHolder.l.f0(arrayList2);
            for (int i = 0; i < arrayList2.size(); i++) {
                arrayList.add(Integer.valueOf(i));
            }
        }

        @Override // defpackage.pi0
        public final boolean s(int i, int i2, Bundle bundle) {
            if (i2 != 16) {
                return false;
            }
            ((mo3) this.r.get(i)).a(SystemClock.uptimeMillis());
            return true;
        }

        @Override // defpackage.pi0
        public final void t(AccessibilityEvent accessibilityEvent, int i) {
            ArrayList arrayList = this.r;
            if (arrayList == null || arrayList.size() <= i) {
                accessibilityEvent.setContentDescription("");
            } else {
                accessibilityEvent.setContentDescription(((mo3) arrayList.get(i)).d());
                accessibilityEvent.setClassName(CompositorViewHolder.class.getName());
            }
        }

        @Override // defpackage.pi0
        public final void v(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            ArrayList arrayList = this.r;
            if (arrayList == null || arrayList.size() <= i) {
                accessibilityNodeInfoCompat.l(this.s);
                accessibilityNodeInfoCompat.r("");
                return;
            }
            mo3 mo3Var = (mo3) arrayList.get(i);
            RectF rectF = this.t;
            mo3Var.b(rectF);
            Rect rect = this.u;
            rectF.roundOut(rect);
            float f = rect.left;
            float f2 = this.q;
            rect.left = (int) (f * f2);
            rect.top = (int) (rect.top * f2);
            rect.right = (int) (rect.right * f2);
            rect.bottom = (int) (rect.bottom * f2);
            if (rect.width() == 0) {
                rect.right = rect.left + 1;
            }
            if (rect.height() == 0) {
                rect.bottom = rect.top + 1;
            }
            accessibilityNodeInfoCompat.l(rect);
            accessibilityNodeInfoCompat.r(mo3Var.d());
            accessibilityNodeInfoCompat.a(16);
            accessibilityNodeInfoCompat.a(1);
            accessibilityNodeInfoCompat.a(32);
        }
    }

    public interface e {
        void T(h61 h61Var, View view, e00 e00Var);
    }

    public CompositorViewHolder(Context context) {
        super(context);
        this.f = new org.chromium.base.b<>();
        this.g = new aq1<>();
        this.k = new org.chromium.chrome.browser.compositor.a();
        this.n = true;
        this.q = new ArrayList<>();
        this.G = new Rect();
        this.H = new Point();
        this.N = new sw(this, 0);
        this.P = 1;
        this.R = new HashSet();
        this.S = new HashSet();
        this.T = new HashSet();
        s();
    }

    private int getKeyboardBottomInsetForControlsPixels() {
        zp1<Integer> zp1Var;
        if (this.P == 1 || (zp1Var = this.z) == null || zp1Var.get() == null) {
            return 0;
        }
        return this.z.get().intValue();
    }

    private Point getViewportSize() {
        boolean z = this.A;
        Point point = this.H;
        if (z && this.P == 2 && t41.f.f(getContext(), this)) {
            Rect rect = this.G;
            getWindowVisibleDisplayFrame(rect);
            point.set(Math.min(rect.width(), getWidth()), Math.min(rect.height(), getHeight()));
        } else {
            point.set(getWidth(), getHeight());
        }
        return point;
    }

    private void setTab(Tab tab) {
        if (tab != null && !ds2.a.a.g) {
            tab.C(0);
        }
        View view = tab != null ? tab.getView() : null;
        if (this.D == view) {
            return;
        }
        y(false);
        Tab tab2 = this.C;
        if (tab2 != tab) {
            this.O = false;
            if (tab2 != null) {
                tab2.t(this.F);
            }
            if (tab != null) {
                tab.x(this.F);
                CompositorView compositorView = this.m;
                N.MefOJ2yP(compositorView.j, compositorView);
            }
            ContentView contentViewD = tab != null ? tab.d() : null;
            ContentView contentView = this.E;
            if (contentView != null) {
                contentView.removeOnHierarchyChangeListener(this);
                this.E.setDeferKeepScreenOnChanges(false);
            }
            if (contentViewD != null) {
                contentViewD.addOnHierarchyChangeListener(this);
            }
            this.E = contentViewD;
        }
        this.C = tab;
        this.D = view;
        y(this.n);
        Tab tab3 = this.C;
        if (tab3 != null) {
            q(tab3);
        }
        OnscreenContentProvider onscreenContentProvider = this.Q;
        if (onscreenContentProvider == null) {
            this.Q = new OnscreenContentProvider(getContext(), this, getWebContents());
            return;
        }
        WebContents webContents = getWebContents();
        onscreenContentProvider.c = new WeakReference<>(webContents);
        long j = onscreenContentProvider.a;
        if (j != 0) {
            N.Mljy_ZmC(j, webContents);
        }
    }

    public final void A() {
        ContentView contentView;
        boolean z = this.K || this.L;
        this.g.r(Boolean.valueOf(z));
        if (!b0.a() || (contentView = this.E) == null) {
            return;
        }
        contentView.setDeferKeepScreenOnChanges(z);
    }

    public final void B() {
        this.m.setRenderHostNeedsDidSwapBuffersCallback((this.R.isEmpty() && this.S.isEmpty() && this.T.isEmpty()) ? false : true);
    }

    public final void C() {
        CompositorView compositorView;
        if (this.K || this.L) {
            return;
        }
        fj fjVar = this.u;
        boolean z = false;
        if (fjVar != null) {
            int i = fjVar.s;
            if (!((i == fjVar.m || i == fjVar.l) && (fjVar.n - fjVar.u() == fjVar.o || fjVar.n - fjVar.u() == fjVar.n))) {
                return;
            }
            fj fjVar2 = this.u;
            boolean z2 = fjVar2.s > fjVar2.m || fjVar2.n - fjVar2.u() > fjVar2.o;
            if (z2 != this.f64J) {
                this.f64J = z2;
                z = true;
            }
        }
        E(getCurrentTab());
        if (z) {
            WebContents webContents = getWebContents();
            boolean z3 = this.f64J;
            if (webContents == null || (compositorView = this.m) == null) {
                return;
            }
            N.MI$giMjY(compositorView.j, compositorView, webContents, z3);
        }
    }

    public final void D(int i) {
        if (i == 0) {
            i = this.a0.a("virtual_keyboard_resizes_layout_by_default") ? 2 : 1;
        }
        int i2 = this.P;
        if (i2 == i) {
            return;
        }
        this.P = i;
        if (i2 == 1 || i == 1) {
            x();
        }
    }

    public final void E(Tab tab) {
        int iB;
        if (tab == null) {
            return;
        }
        WebContents webContentsC = tab.c();
        ContentView contentViewD = tab.d();
        if (webContentsC == null || contentViewD == null) {
            return;
        }
        Point viewportSize = getViewportSize();
        int i = viewportSize.x;
        int i2 = viewportSize.y;
        int keyboardBottomInsetForControlsPixels = getKeyboardBottomInsetForControlsPixels();
        fj fjVar = this.u;
        int topControlsHeightPixels = keyboardBottomInsetForControlsPixels + (fjVar != null ? fjVar.m + fjVar.o : 0);
        if (this.f64J) {
            topControlsHeightPixels = getTopControlsHeightPixels() + getBottomControlsHeightPixels();
        }
        if (!(contentViewD.getWindowToken() != null)) {
            contentViewD.measure(View.MeasureSpec.makeMeasureSpec(i, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(i2, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
            contentViewD.layout(0, 0, contentViewD.getMeasuredWidth(), contentViewD.getMeasuredHeight());
            webContentsC.m0(contentViewD.getWidth(), contentViewD.getHeight() - topControlsHeightPixels);
            Log.i("CompositorViewHolder", "getWidth: " + contentViewD.getWidth() + ", getHeight: " + contentViewD.getHeight() + ", controlsHeight: " + topControlsHeightPixels);
            f(null);
            return;
        }
        int i3 = this.P;
        boolean z = i3 == 3 || i3 == 1;
        if (this.A || !z) {
            iB = 0;
        } else {
            iB = t41.f.b(getRootView());
            i2 += iB;
        }
        Log.i("CompositorViewHolder", "isAttachedToWindow(view): width: " + i + ", height: " + i2 + ", controlsHeight: " + topControlsHeightPixels);
        webContentsC.m0(i, i2 - topControlsHeightPixels);
        if (this.P == 3) {
            boolean z2 = iB > 0;
            if (z2 || this.O) {
                this.O = z2;
                Rect rect = new Rect();
                getRootView().getWindowVisibleDisplayFrame(rect);
                if (!z2) {
                    CompositorView compositorView = this.m;
                    if (compositorView != null) {
                        N.MGbjFlrB(compositorView.j, compositorView, webContentsC, 0, 0, 0, 0);
                        return;
                    }
                    return;
                }
                int i4 = rect.left;
                int i5 = rect.top;
                CompositorView compositorView2 = this.m;
                if (compositorView2 != null) {
                    N.MGbjFlrB(compositorView2.j, compositorView2, webContentsC, i4, i5, i, iB);
                }
            }
        }
    }

    @Override // org.chromium.components.browser_ui.widget.InsetObserverView.d
    public final void a(int i) {
        if (this.A) {
            o();
        }
    }

    @Override // defpackage.g61
    public final void b(boolean z, boolean z2) {
        if (z == this.n && z2 == this.o) {
            return;
        }
        this.n = z;
        this.o = z2;
        y(z);
    }

    @Override // kj.a
    public final void c(boolean z, int i, int i2, int i3) {
        u();
        if (z) {
            f(null);
        }
        z();
    }

    @Override // defpackage.g61
    public final void d() {
        f(null);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchDragEvent(DragEvent dragEvent) {
        di0 di0Var = this.h;
        dragEvent.getAction();
        a aVar = (a) di0Var.a;
        h61 h61Var = CompositorViewHolder.this.l;
        RectF rectF = aVar.a;
        if (h61Var != null) {
            h61Var.K(rectF);
        }
        di0Var.b(-rectF.top);
        boolean zDispatchDragEvent = super.dispatchDragEvent(dragEvent);
        di0 di0Var2 = this.h;
        int action = dragEvent.getAction();
        di0Var2.getClass();
        if (action == 6 || action == 4 || action == 3) {
            di0Var2.b(0.0f);
        }
        return zDispatchDragEvent;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        d dVar = this.w;
        if (dVar == null || !dVar.m(motionEvent)) {
            return super.dispatchHoverEvent(motionEvent);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 2 || motionEvent.getActionMasked() == 0 || motionEvent.getActionMasked() == 5 || motionEvent.getActionMasked() == 6) {
            this.U = motionEvent;
        }
        if (motionEvent.getActionMasked() == 3 || motionEvent.getActionMasked() == 1) {
            this.U = null;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0 || actionMasked == 5) {
            this.K = true;
        } else if (actionMasked == 3 || actionMasked == 1) {
            this.K = false;
            C();
        }
        yj1 yj1Var = c0;
        if (!yj1Var.a()) {
            A();
        }
        Iterator<zd3> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                break;
            }
            ((zd3) aVar.next()).a(motionEvent);
        }
        boolean zDispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        if (yj1Var.a()) {
            A();
        }
        return zDispatchTouchEvent;
    }

    @Override // defpackage.g61
    public final void e(RectF rectF) {
        float fU;
        h(rectF);
        fj fjVar = this.u;
        if (fjVar != null) {
            rectF.top = fjVar.l + fjVar.q + rectF.top;
            fU = fjVar.u();
        } else {
            fU = 0.0f;
        }
        rectF.bottom -= getBottomControlsHeightPixels() - fU;
    }

    @Override // defpackage.g61
    public final void f(Runnable runnable) {
        if (runnable != null) {
            this.R.add(runnable);
            B();
        }
        CompositorView compositorView = this.m;
        long j = compositorView.j;
        if (j != 0) {
            N.M_Nkznfe(j, compositorView);
        }
    }

    @Override // defpackage.g61
    public final void g(RectF rectF) {
        h(rectF);
        if (this.u != null) {
            rectF.top += r0.l;
        }
        rectF.bottom -= getBottomControlsHeightPixels();
    }

    public View getActiveSurfaceView() {
        return this.m.getActiveSurfaceView();
    }

    @Override // defpackage.g61
    public int getBottomControlsHeightPixels() {
        int keyboardBottomInsetForControlsPixels = getKeyboardBottomInsetForControlsPixels();
        fj fjVar = this.u;
        return keyboardBottomInsetForControlsPixels + (fjVar != null ? fjVar.n : 0);
    }

    @Override // defpackage.g61
    public fj getBrowserControlsManager() {
        return this.u;
    }

    public CompositorView getCompositorView() {
        return this.m;
    }

    public ViewGroup getContentView() {
        Tab currentTab = getCurrentTab();
        if (currentTab != null) {
            return currentTab.d();
        }
        return null;
    }

    public Tab getCurrentTab() {
        y13 y13Var;
        if (this.l == null || (y13Var = this.t) == null) {
            return null;
        }
        Tab tabI = y13Var.i();
        return tabI == null ? this.C : tabI;
    }

    public df0 getDynamicResourceLoader() {
        return this.m.getResourceManager().a();
    }

    @Override // defpackage.g61
    public rq0 getFullscreenManager() {
        return this.u.y;
    }

    @Override // defpackage.g61
    public float getHeightMinusBrowserControls() {
        return getHeight() - (getBottomControlsHeightPixels() + getTopControlsHeightPixels());
    }

    public zp1<Boolean> getInMotionSupplier() {
        return this.g;
    }

    public org.chromium.chrome.browser.compositor.a getInvalidator() {
        return this.k;
    }

    public h61 getLayoutManager() {
        return this.l;
    }

    @Override // defpackage.ry
    public float getOverlayTranslateY() {
        fj fjVar = this.u;
        return fjVar.l + fjVar.q;
    }

    @Override // defpackage.j61
    public ResourceManager getResourceManager() {
        return this.m.getResourceManager();
    }

    @Override // defpackage.g61
    public int getTopControlsHeightPixels() {
        fj fjVar = this.u;
        if (fjVar != null) {
            return fjVar.l;
        }
        return 0;
    }

    public int getVirtualKeyboardModeForTesting() {
        return this.P;
    }

    public WebContents getWebContents() {
        Tab currentTab = getCurrentTab();
        if (currentTab != null) {
            return currentTab.c();
        }
        return null;
    }

    @Override // defpackage.g61
    public final void h(RectF rectF) {
        Point viewportSize = getViewportSize();
        rectF.set(0.0f, 0.0f, viewportSize.x, viewportSize.y);
    }

    public final void i() {
        ArrayList<Runnable> arrayList = this.q;
        if (arrayList.isEmpty()) {
            return;
        }
        TraceEvent.R("CompositorViewHolder.flushInvalidation");
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).run();
        }
        arrayList.clear();
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x000e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void k() {
        /*
            r4 = this;
            android.view.ViewGroup r0 = r4.getContentView()
            if (r0 == 0) goto Le
            java.util.WeakHashMap<android.view.View, in3> r1 = defpackage.om3.a
            boolean r1 = om3.g.b(r0)
            if (r1 != 0) goto Lf
        Le:
            r0 = r4
        Lf:
            r1 = 0
            r2 = r1
        L11:
            if (r0 == 0) goto L28
            int r3 = r0.getSystemUiVisibility()
            r2 = r2 | r3
            android.view.ViewParent r3 = r0.getParent()
            boolean r3 = r3 instanceof android.view.View
            if (r3 != 0) goto L21
            goto L28
        L21:
            android.view.ViewParent r0 = r0.getParent()
            android.view.View r0 = (android.view.View) r0
            goto L11
        L28:
            r0 = r2 & 4
            r3 = 1
            if (r0 != 0) goto L38
            r0 = r2 & 2048(0x800, float:2.87E-42)
            if (r0 != 0) goto L38
            r0 = r2 & 4096(0x1000, float:5.74E-42)
            if (r0 == 0) goto L36
            goto L38
        L36:
            r0 = r1
            goto L39
        L38:
            r0 = r3
        L39:
            r2 = r2 & 1024(0x400, float:1.435E-42)
            if (r2 == 0) goto L3e
            goto L3f
        L3e:
            r3 = r1
        L3f:
            boolean r2 = r4.A
            if (r2 != r0) goto L44
            return
        L44:
            r4.A = r0
            ww r0 = r4.B
            if (r0 != 0) goto L52
            ww r0 = new ww
            r0.<init>(r4, r1)
            r4.B = r0
            goto L5b
        L52:
            android.os.Handler r0 = r4.getHandler()
            ww r1 = r4.B
            r0.removeCallbacks(r1)
        L5b:
            if (r3 == 0) goto L60
            r0 = 500(0x1f4, double:2.47E-321)
            goto L62
        L60:
            r0 = 0
        L62:
            ww r2 = r4.B
            r4.postDelayed(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.compositor.CompositorViewHolder.k():void");
    }

    @Override // kj.a
    public final void l(int i) {
        if (this.C == null) {
            return;
        }
        WebContents webContents = getWebContents();
        if (webContents != null) {
            webContents.N();
        }
        E(getCurrentTab());
        u();
    }

    @Override // kj.a
    public final void n() {
        if (this.C == null) {
            return;
        }
        WebContents webContents = getWebContents();
        if (webContents != null) {
            webContents.N();
        }
        E(getCurrentTab());
        u();
    }

    public final void o() {
        if (this.P == 1) {
            return;
        }
        E(getCurrentTab());
        u();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        this.k.a = this;
        super.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public final void onChildViewAdded(View view, View view2) {
        z();
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public final void onChildViewRemoved(View view, View view2) {
        z();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        i();
        this.k.a = null;
        super.onDetachedFromWindow();
        if (this.w != null) {
            this.v.setAccessibilityDelegate(null);
            this.w = null;
            removeView(this.v);
            this.v = null;
        }
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptHoverEvent(MotionEvent motionEvent) {
        this.h.a(motionEvent, true);
        return super.onInterceptHoverEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        b.a aVar;
        EventFilter eventFilter;
        EventFilter eventFilterX;
        super.onInterceptTouchEvent(motionEvent);
        Iterator<zd3> it = this.f.iterator();
        do {
            aVar = (b.a) it;
            if (!aVar.hasNext()) {
                if (this.l == null) {
                    return false;
                }
                this.h.a(motionEvent, false);
                h61 h61Var = this.l;
                boolean z = this.i;
                if (h61Var.q == null) {
                    return false;
                }
                if (motionEvent.getAction() == 0) {
                    h61Var.g = (int) motionEvent.getX();
                    h61Var.h = (int) motionEvent.getY();
                }
                PointF pointFE = h61Var.E(motionEvent);
                ArrayList arrayList = h61Var.L;
                int size = arrayList.size() - 1;
                while (true) {
                    eventFilter = null;
                    if (size < 0) {
                        eventFilterX = null;
                        break;
                    }
                    if (((je2) arrayList.get(size)).q1() && (eventFilterX = ((je2) arrayList.get(size)).x()) != null) {
                        if (pointFE != null) {
                            float f = pointFE.x;
                            float f2 = pointFE.y;
                            eventFilterX.c = f;
                            eventFilterX.d = f2;
                        }
                        if (eventFilterX.a(motionEvent, z)) {
                            break;
                        }
                    }
                    size--;
                }
                if (eventFilterX == null) {
                    EventFilter eventFilterK = h61Var.q.k();
                    if (eventFilterK != null) {
                        if (pointFE != null) {
                            float f3 = pointFE.x;
                            float f4 = pointFE.y;
                            eventFilterK.c = f3;
                            eventFilterK.d = f4;
                        }
                        if (eventFilterK.a(motionEvent, z)) {
                            eventFilter = eventFilterK;
                        }
                    }
                    eventFilterX = eventFilter;
                }
                h61Var.F = eventFilterX != h61Var.t;
                h61Var.t = eventFilterX;
                if (eventFilterX != null) {
                    h61Var.q.J();
                }
                return h61Var.t != null;
            }
        } while (!((zd3) aVar.next()).b());
        return true;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            u();
        }
        super.onLayout(z, i, i2, i3, i4);
        d dVar = this.w;
        if (dVar != null) {
            dVar.y(dVar.k, 65536);
            this.w.p(-1, 1);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.i = t41.f.f(getContext(), this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final PointerIcon onResolvePointerIcon(MotionEvent motionEvent, int i) {
        ViewGroup contentView = getContentView();
        if (contentView == null) {
            return null;
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (om3.g.b(contentView)) {
            return contentView.onResolvePointerIcon(motionEvent, i);
        }
        return null;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        y13 y13Var;
        super.onSizeChanged(i, i2, i3, i4);
        if (d0.a() || (y13Var = this.t) == null) {
            return;
        }
        for (TabModel tabModel : y13Var.y()) {
            for (int i5 = 0; i5 < tabModel.getCount(); i5++) {
                Tab tabAt = tabModel.getTabAt(i5);
                if (tabAt != null) {
                    E(tabAt);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0031  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean onTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            r0 = 0
            super.onTouchEvent(r5)     // Catch: java.lang.Exception -> L38
            h61 r1 = r4.l     // Catch: java.lang.Exception -> L38
            r2 = 1
            if (r1 == 0) goto L31
            org.chromium.chrome.browser.layouts.EventFilter r3 = r1.t     // Catch: java.lang.Exception -> L38
            if (r3 != 0) goto Lf
        Ld:
            r1 = r0
            goto L2d
        Lf:
            boolean r3 = r1.F     // Catch: java.lang.Exception -> L38
            if (r3 == 0) goto L27
            int r3 = r5.getActionMasked()     // Catch: java.lang.Exception -> L38
            if (r3 == 0) goto L27
            android.view.MotionEvent r3 = android.view.MotionEvent.obtain(r5)     // Catch: java.lang.Exception -> L38
            r3.setAction(r0)     // Catch: java.lang.Exception -> L38
            boolean r3 = r1.Q(r3)     // Catch: java.lang.Exception -> L38
            if (r3 != 0) goto L27
            goto Ld
        L27:
            r1.F = r0     // Catch: java.lang.Exception -> L38
            boolean r1 = r1.Q(r5)     // Catch: java.lang.Exception -> L38
        L2d:
            if (r1 == 0) goto L31
            r1 = r2
            goto L32
        L31:
            r1 = r0
        L32:
            di0 r3 = r4.h     // Catch: java.lang.Exception -> L38
            r3.a(r5, r2)     // Catch: java.lang.Exception -> L38
            return r1
        L38:
            r5 = move-exception
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = r4.getSimpleName()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "onTouchEvent: "
            r1.<init>(r2)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            android.util.Log.e(r4, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.compositor.CompositorViewHolder.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void p(Runnable runnable) {
        View view = this.W;
        if (view != null) {
            view.clearFocus();
        }
        if (hasFocus() ? t41.f.e(this) : false) {
            this.s = runnable;
        } else {
            runnable.run();
        }
    }

    public final void q(Tab tab) {
        WebContents webContentsC = tab.c();
        if (webContentsC != null) {
            int width = this.m.getWidth();
            int height = this.m.getHeight();
            CompositorView compositorView = this.m;
            if (compositorView != null) {
                N.MzYzRqF3(compositorView.j, compositorView, webContentsC, width, height);
            }
            boolean z = this.f64J;
            CompositorView compositorView2 = this.m;
            if (compositorView2 != null) {
                N.MI$giMjY(compositorView2.j, compositorView2, webContentsC, z);
            }
            D(webContentsC.l0());
        } else if (tab.getView() != null) {
            D(0);
        }
        if (tab.getView() != null && tab.getView() == tab.d()) {
            E(tab);
        }
    }

    @Override // l23.a
    public final void r(boolean z, boolean z2) {
        setFocusable(!z);
    }

    public final void s() {
        this.h = new di0(new a());
        this.F = new b();
        addOnLayoutChangeListener(new uw(0, this));
        CompositorView compositorView = new CompositorView(getContext(), this);
        this.m = compositorView;
        addView(compositorView, 0, new FrameLayout.LayoutParams(-1, -2));
        setOnSystemUiVisibilityChangeListener(new vw(0, this));
        k();
        setDefaultFocusHighlightEnabled(false);
    }

    public void setApplicationViewportInsetSupplier(x7 x7Var) {
        this.M = x7Var;
        x7Var.p(this.N);
        x();
    }

    public void setAutofillUiBottomInsetSupplier(zp1<Integer> zp1Var) {
        this.z = zp1Var;
        zp1Var.p(this.N);
        x();
    }

    public void setBrowserControlsManager(fj fjVar) {
        this.u = fjVar;
        fjVar.o(this);
        u();
    }

    public void setCompositorViewForTesting(CompositorView compositorView) {
        this.m = compositorView;
    }

    public void setControlContainer(e00 e00Var) {
        df0 df0VarA = this.m.getResourceManager() != null ? this.m.getResourceManager().a() : null;
        if (df0VarA != null && this.x != null) {
            df0VarA.e(R$id.control_container);
        }
        this.x = e00Var;
        if (df0VarA != null && e00Var != null) {
            df0VarA.d(R$id.control_container, e00Var.getToolbarResourceAdapter());
        }
        this.e = new androidx.appcompat.widget.a(18, this);
    }

    public void setInsetObserverView(InsetObserverView insetObserverView) {
        InsetObserverView insetObserverView2 = this.y;
        if (insetObserverView2 != null) {
            insetObserverView2.f.c(this);
        }
        this.y = insetObserverView;
        if (insetObserverView != null) {
            insetObserverView.f.a(this);
            o();
        }
        x();
    }

    public void setLayoutManager(h61 h61Var) {
        this.l = h61Var;
        u();
    }

    public void setOverlayMode(boolean z) {
        CompositorView compositorView = this.m;
        if (compositorView != null) {
            compositorView.setOverlayVideoMode(z);
        }
    }

    public void setRootView(View view) {
        this.m.setRootView(view);
    }

    public void setTopUiThemeColorProvider(xd3 xd3Var) {
        this.V = xd3Var;
    }

    public void setUrlBar(View view) {
        this.W = view;
    }

    public final void t() {
        y13 y13Var = this.t;
        if (y13Var == null) {
            return;
        }
        setTab(y13Var.i());
    }

    public final void u() {
        h61 h61Var = this.l;
        if (h61Var != null) {
            h61Var.S();
        }
    }

    public final void v() {
        if (this.I) {
            return;
        }
        this.m.setBackgroundColor(-1);
    }

    public final void w() {
        setTab(null);
        x7 x7Var = this.M;
        Callback<Integer> callback = this.N;
        if (x7Var != null && callback != null) {
            x7Var.d(callback);
        }
        zp1<Integer> zp1Var = this.z;
        if (zp1Var != null && callback != null) {
            zp1Var.d(callback);
        }
        CompositorView compositorView = this.m;
        rw rwVar = compositorView.f;
        rwVar.h = null;
        rw.b bVar = rwVar.f;
        rwVar.c(bVar);
        rw.b bVar2 = rwVar.e;
        rwVar.c(bVar2);
        bVar2.b().removeCallback(rwVar);
        bVar.b().removeCallback(rwVar);
        CompositorView.a aVar = compositorView.w;
        if (aVar != null) {
            CompositorView.this.getContext().getApplicationContext().unregisterReceiver(aVar);
        }
        long j = compositorView.j;
        if (j != 0) {
            N.MsBICFNS(j, compositorView);
        }
        compositorView.j = 0L;
        h61 h61Var = this.l;
        if (h61Var != null) {
            h61Var.destroy();
        }
        InsetObserverView insetObserverView = this.y;
        if (insetObserverView != null) {
            insetObserverView.f.c(this);
            this.y = null;
        }
        OnscreenContentProvider onscreenContentProvider = this.Q;
        if (onscreenContentProvider != null) {
            long j2 = onscreenContentProvider.a;
            if (j2 != 0) {
                N.Mcvkj$M_(j2);
                onscreenContentProvider.a = 0L;
            }
        }
        ContentView contentView = this.E;
        if (contentView != null) {
            contentView.removeOnHierarchyChangeListener(this);
        }
    }

    public final void x() {
        x7 x7Var = this.M;
        if (x7Var == null) {
            return;
        }
        int i = this.P;
        ur3 ur3Var = x7Var.j;
        HashSet hashSet = x7Var.i;
        if (i == 1) {
            zp1<Integer> zp1Var = this.z;
            if (zp1Var != null) {
                hashSet.add(zp1Var);
                zp1Var.p(ur3Var);
            }
            InsetObserverView insetObserverView = this.y;
            if (insetObserverView != null) {
                x7 x7Var2 = this.M;
                zp1<Integer> supplierForBottomInset = insetObserverView.getSupplierForBottomInset();
                x7Var2.i.add(supplierForBottomInset);
                supplierForBottomInset.p(x7Var2.j);
                return;
            }
            return;
        }
        zp1<Integer> zp1Var2 = this.z;
        if (zp1Var2 != null) {
            x7Var.h.remove(zp1Var2);
            hashSet.remove(zp1Var2);
            zp1Var2.d(ur3Var);
            x7Var.s();
        }
        InsetObserverView insetObserverView2 = this.y;
        if (insetObserverView2 != null) {
            x7 x7Var3 = this.M;
            zp1<Integer> supplierForBottomInset2 = insetObserverView2.getSupplierForBottomInset();
            x7Var3.h.remove(supplierForBottomInset2);
            x7Var3.i.remove(supplierForBottomInset2);
            supplierForBottomInset2.d(x7Var3.j);
            x7Var3.s();
        }
    }

    public final void y(boolean z) {
        if (this.D == null) {
            return;
        }
        WebContents webContents = getWebContents();
        if (!z) {
            if (this.D.getParent() == this) {
                setFocusable(this.o);
                setFocusableInTouchMode(this.o);
                if (webContents != null && !webContents.isDestroyed()) {
                    getContentView().setVisibility(4);
                }
                removeView(this.D);
                return;
            }
            return;
        }
        if (this.D != getCurrentTab().getView() || this.D.getParent() == this) {
            return;
        }
        uh3.l(this.D);
        if (webContents != null) {
            getContentView().setVisibility(0);
            C();
        }
        addView(this.D, 1);
        setFocusable(false);
        setFocusableInTouchMode(false);
        View view = this.W;
        if (view == null || !view.hasFocus()) {
            this.D.requestFocus();
        }
    }

    @Override // defpackage.C0188h0.b
    public final void y0(boolean z) {
        if (z && this.w == null) {
            c cVar = new c(getContext());
            this.v = cVar;
            addView(cVar);
            d dVar = new d(this.v);
            this.w = dVar;
            om3.k(this.v, dVar);
        }
    }

    public final void z() {
        int i;
        TraceEvent.q("CompositorViewHolder:updateContentViewChildrenDimension", null);
        ViewGroup contentView = getContentView();
        if (contentView != null) {
            float overlayTranslateY = getOverlayTranslateY();
            fj fjVar = this.u;
            float fU = fjVar.n - fjVar.u();
            for (int i2 = 0; i2 < contentView.getChildCount(); i2++) {
                View childAt = contentView.getChildAt(i2);
                if ((childAt.getLayoutParams() instanceof FrameLayout.LayoutParams) && 48 == (((FrameLayout.LayoutParams) childAt.getLayoutParams()).gravity & 112)) {
                    childAt.setTranslationY(overlayTranslateY);
                    TraceEvent.R("FullscreenManager:child.setTranslationY()");
                }
            }
            for (int i3 = 0; i3 < contentView.getChildCount(); i3++) {
                View childAt2 = contentView.getChildAt(i3);
                if (childAt2.getLayoutParams() instanceof FrameLayout.LayoutParams) {
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt2.getLayoutParams();
                    if (layoutParams.height == -1 && (layoutParams.topMargin != (i = (int) overlayTranslateY) || layoutParams.bottomMargin != ((int) fU))) {
                        layoutParams.topMargin = i;
                        layoutParams.bottomMargin = (int) fU;
                        ao3.e(childAt2, "CompositorViewHolder.applyMarginToFullscreenChildViews");
                        TraceEvent.R("FullscreenManager:child.requestLayout()");
                    }
                }
            }
            C();
        }
        TraceEvent.C("CompositorViewHolder:updateContentViewChildrenDimension");
    }

    public CompositorViewHolder(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = new org.chromium.base.b<>();
        this.g = new aq1<>();
        this.k = new org.chromium.chrome.browser.compositor.a();
        this.n = true;
        this.q = new ArrayList<>();
        this.G = new Rect();
        this.H = new Point();
        this.N = new tw(this, 0);
        this.P = 1;
        this.R = new HashSet();
        this.S = new HashSet();
        this.T = new HashSet();
        s();
    }

    @Override // defpackage.g61
    public j61 getLayoutRenderHost() {
        return this;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void clearChildFocus(View view) {
    }

    @Override // org.chromium.components.browser_ui.widget.InsetObserverView.d
    public final void m(Rect rect) {
    }
}
