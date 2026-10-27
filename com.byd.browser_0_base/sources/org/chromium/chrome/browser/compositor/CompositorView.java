package org.chromium.chrome.browser.compositor;

import J.N;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.SystemClock;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import defpackage.ac2;
import defpackage.cc3;
import defpackage.ew;
import defpackage.fj;
import defpackage.g3;
import defpackage.g61;
import defpackage.h61;
import defpackage.j61;
import defpackage.je2;
import defpackage.kw;
import defpackage.n3;
import defpackage.nw;
import defpackage.oj2;
import defpackage.ow;
import defpackage.pw;
import defpackage.r11;
import defpackage.rw;
import defpackage.vj1;
import defpackage.xz;
import defpackage.y51;
import defpackage.yp;
import defpackage.ys2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: loaded from: classes.dex */
public class CompositorView extends FrameLayout implements ow, WindowAndroid.e {
    public final Rect e;
    public rw f;
    public boolean g;
    public boolean h;
    public int i;
    public long j;
    public final j61 k;
    public int l;
    public ResourceManager m;
    public WindowAndroid n;
    public TabContentManager o;
    public View p;
    public boolean q;
    public Runnable r;
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean v;
    public a w;

    public class a extends BroadcastReceiver {
        public boolean a;

        public a() {
            xz.g(CompositorView.this.getContext().getApplicationContext(), this, new IntentFilter("android.intent.action.SCREEN_OFF"));
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                CompositorView compositorView = CompositorView.this;
                if (compositorView.f == null || compositorView.j == 0) {
                    return;
                }
                this.a = true;
            }
        }
    }

    public CompositorView(Context context, j61 j61Var) {
        super(context);
        this.e = new Rect();
        this.l = -1;
        this.k = j61Var;
        d();
    }

    public static void b(CompositorView compositorView) {
        compositorView.getClass();
        rw rwVar = new rw(compositorView, compositorView);
        compositorView.f = rwVar;
        rwVar.f(compositorView.getSurfacePixelFormat());
        N.M_Nkznfe(compositorView.j, compositorView);
        rw rwVar2 = compositorView.f;
        int visibility = compositorView.getVisibility();
        rwVar2.e.a.setVisibility(visibility);
        rwVar2.f.a.setVisibility(visibility);
    }

    private int getSurfacePixelFormat() {
        if (this.g || this.h) {
            return -3;
        }
        return (this.s && (this.t ^ true)) ? -3 : -1;
    }

    private void setWindowAndroid(WindowAndroid windowAndroid) {
        this.n.x.c(this);
        this.n = windowAndroid;
        windowAndroid.x.a(this);
        a(windowAndroid.w);
        int windowVisibility = getWindowVisibility();
        WindowAndroid windowAndroid2 = this.n;
        if (windowAndroid2 == null) {
            return;
        }
        if (windowVisibility == 8) {
            long j = windowAndroid2.g;
            if (j != 0) {
                N.MrnNdVRa(j, windowAndroid2, false);
            }
        } else if (windowVisibility == 0) {
            long j2 = windowAndroid2.g;
            if (j2 != 0) {
                N.MrnNdVRa(j2, windowAndroid2, true);
            }
        }
        r11 r11VarA = r11.a();
        r11VarA.c = null;
        r11VarA.d = null;
        r11VarA.b = null;
    }

    @Override // org.chromium.ui.base.WindowAndroid.e
    public final void a(boolean z) {
        if (!this.s || this.t == z) {
            return;
        }
        this.t = z;
        if (z) {
            N.Mlw_qgLA(this.j, this);
        }
        this.f.f(getSurfacePixelFormat());
    }

    public final void c(boolean z, g3 g3Var, TabContentManager tabContentManager) {
        this.n = g3Var;
        g3Var.x.a(this);
        a(g3Var.w);
        this.o = tabContentManager;
        this.j = N.M1onz6N$(this, z, g3Var, tabContentManager);
        this.h = z;
        this.f.f(getSurfacePixelFormat());
        setVisibility(0);
        this.m = (ResourceManager) N.MKvVEaSX(this.j, this);
        N.M_Nkznfe(this.j, this);
    }

    public final void d() {
        ThreadUtils.g();
        this.f = new rw(this, this);
        this.w = new a();
        setBackgroundColor(yp.b(getContext(), false));
        super.setVisibility(0);
        this.f.f(-1);
    }

    @CalledByNative
    public final void didSwapBuffers(boolean z) {
        int i = this.i;
        if (i > 1) {
            this.i = i - 1;
            long j = this.j;
            if (j != 0) {
                N.M_Nkznfe(j, this);
            }
        } else if (i == 1) {
            this.i = 0;
            N.MVesqb5U(this.j, this);
            rw rwVar = this.f;
            rw.b bVar = rwVar.g;
            if (bVar != null) {
                rw.b bVar2 = rwVar.e;
                if (bVar == bVar2) {
                    bVar2 = rwVar.f;
                }
                if (rwVar.h != bVar2) {
                    rwVar.b(bVar2);
                }
            }
        }
        if (z) {
            e();
        }
        this.v = true;
        CompositorViewHolder compositorViewHolder = (CompositorViewHolder) this.k;
        HashSet hashSet = compositorViewHolder.T;
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
        hashSet.clear();
        compositorViewHolder.B();
        j();
    }

    @CalledByNative
    public final void didSwapFrame(int i) {
        androidx.appcompat.widget.a aVar;
        CompositorViewHolder compositorViewHolder = (CompositorViewHolder) this.k;
        compositorViewHolder.getClass();
        TraceEvent.R("didSwapFrame");
        if (compositorViewHolder.I && (aVar = compositorViewHolder.e) != null) {
            compositorViewHolder.post(aVar);
        }
        compositorViewHolder.I = true;
        compositorViewHolder.p = i;
        if (!compositorViewHolder.r || i == 0) {
            compositorViewHolder.i();
        }
        compositorViewHolder.r = !compositorViewHolder.r;
        HashSet hashSet = compositorViewHolder.T;
        HashSet hashSet2 = compositorViewHolder.S;
        hashSet.addAll(hashSet2);
        hashSet2.clear();
        compositorViewHolder.B();
    }

    public final void e() {
        Runnable runnable = this.r;
        this.r = null;
        if (runnable != null) {
            runnable.run();
        }
        j();
    }

    public final void f(Surface surface, int i, int i2, int i3) {
        CompositorView compositorView;
        long j = this.j;
        if (j == 0) {
            return;
        }
        N.MH1eqy7s(j, this, i, i2, i3, !this.t, surface);
        CompositorViewHolder compositorViewHolder = (CompositorViewHolder) this.k;
        ViewGroup contentView = compositorViewHolder.getContentView();
        WebContents webContents = compositorViewHolder.getWebContents();
        if (contentView == null || webContents == null || (compositorView = compositorViewHolder.m) == null) {
            return;
        }
        N.MzYzRqF3(compositorView.j, compositorView, webContents, i2, i3);
    }

    public final void g() {
        if (this.j == 0) {
            return;
        }
        a aVar = this.w;
        if (aVar != null) {
            aVar.a = false;
        }
        this.i = 2;
        this.v = false;
        j();
        N.MGPC4Ktv(this.j, this);
        CompositorViewHolder compositorViewHolder = (CompositorViewHolder) this.k;
        compositorViewHolder.p = 0;
        compositorViewHolder.i();
    }

    public View getActiveSurfaceView() {
        rw.b bVar = this.f.g;
        if (bVar == null) {
            return null;
        }
        return bVar.a;
    }

    public ResourceManager getResourceManager() {
        return this.m;
    }

    public final void h(boolean z) {
        long j = this.j;
        if (j == 0) {
            return;
        }
        if (z) {
            N.Mszb0mNw(j, this);
        }
        a aVar = this.w;
        if (aVar == null || !aVar.a) {
            return;
        }
        aVar.a = false;
        CompositorView compositorView = CompositorView.this;
        rw rwVar = compositorView.f;
        if (rwVar != null) {
            rwVar.h = null;
            rw.b bVar = rwVar.f;
            rwVar.c(bVar);
            rw.b bVar2 = rwVar.e;
            rwVar.c(bVar2);
            bVar2.b().removeCallback(rwVar);
            bVar.b().removeCallback(rwVar);
            b(compositorView);
        }
    }

    public final void i(Runnable runnable) {
        e();
        this.r = runnable;
        if (this.v) {
            e();
        }
        j();
        long j = this.j;
        if (j != 0) {
            N.M_Nkznfe(j, this);
        }
    }

    public final void j() {
        long j = this.j;
        if (j == 0) {
            return;
        }
        N.MsdzyWED(j, this.u || this.i > 0 || this.r != null);
    }

    @CalledByNative
    public final void notifyWillUseSurfaceControl() {
        this.s = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10, types: [java.lang.Object, org.chromium.chrome.browser.layouts.scene_layer.SceneLayer] */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r6v12, types: [ke2] */
    @CalledByNative
    public final void onCompositorLayout() {
        boolean zIsEmpty;
        String str;
        CompositorViewHolder compositorViewHolder = (CompositorViewHolder) this.k;
        compositorViewHolder.getClass();
        TraceEvent.q("CompositorViewHolder:layout", null);
        h61 h61Var = compositorViewHolder.l;
        if (h61Var != null) {
            TraceEvent.q("LayoutDriver:onUpdate", null);
            long jUptimeMillis = SystemClock.uptimeMillis();
            boolean z = h61Var.x;
            int i = 0;
            nw.a aVar = h61Var.f40J;
            if (z) {
                h61Var.x = false;
                ew ewVar = h61Var.G;
                ewVar.getClass();
                long jCurrentTimeMillis = System.currentTimeMillis();
                long j = jCurrentTimeMillis - ewVar.e;
                ewVar.e = jCurrentTimeMillis;
                ewVar.d = false;
                ArrayList<kw> arrayList = ewVar.a;
                if (arrayList.isEmpty()) {
                    zIsEmpty = true;
                } else {
                    ArrayList<kw> arrayList2 = ewVar.c;
                    arrayList2.addAll(arrayList);
                    while (i < arrayList2.size()) {
                        kw kwVar = arrayList2.get(i);
                        int i2 = i;
                        long j2 = kwVar.j + j;
                        kwVar.j = j2;
                        float f = kwVar.o;
                        float f2 = kw.r;
                        long j3 = j;
                        long jMin = Math.min((long) (j2 - (f * f2)), (long) (kwVar.n * f2));
                        if (jMin >= 0) {
                            kwVar.k = 1.0f;
                            long j4 = (long) (kwVar.n * kw.r);
                            if (j4 > 0) {
                                kwVar.k = kwVar.i.getInterpolation(jMin / j4);
                            }
                            ArrayList<kw.a> arrayList3 = kwVar.h;
                            arrayList3.addAll(kwVar.g);
                            for (int i3 = 0; i3 < arrayList3.size(); i3++) {
                                arrayList3.get(i3).a(kwVar);
                            }
                            arrayList3.clear();
                            if (jMin == ((long) (kwVar.n * kw.r))) {
                                kwVar.q = true;
                                kwVar.end();
                            }
                        }
                        if (kwVar.p == 3) {
                            arrayList.remove(kwVar);
                        }
                        i = i2 + 1;
                        j = j3;
                    }
                    arrayList2.clear();
                    ewVar.b.run();
                    zIsEmpty = arrayList.isEmpty();
                }
                y51 y51Var = h61Var.q;
                if (y51Var != null) {
                    boolean zD = y51Var.D();
                    if (y51Var == ((h61) y51Var.m).q) {
                        y51Var.L();
                    }
                    if (zD && zIsEmpty) {
                        int i4 = y51Var.p;
                        if (i4 == 2) {
                            y51Var.f();
                        } else if (i4 == 0) {
                            y51Var.g();
                        }
                    }
                }
                int i5 = 0;
                while (true) {
                    ArrayList arrayList4 = h61Var.L;
                    if (i5 >= arrayList4.size()) {
                        break;
                    }
                    ((je2) arrayList4.get(i5)).G(jUptimeMillis);
                    i5++;
                }
                aVar.r(Long.valueOf(jUptimeMillis));
            } else {
                aVar.r(Long.valueOf(jUptimeMillis));
            }
            TraceEvent.C("LayoutDriver:onUpdate");
            CompositorView compositorView = compositorViewHolder.m;
            h61 h61Var2 = compositorViewHolder.l;
            compositorView.getClass();
            TraceEvent.q("CompositorView:finalizeLayers", null);
            if (h61Var2.q == null || compositorView.j == 0) {
                TraceEvent.C("CompositorView:finalizeLayers");
            } else {
                if (!compositorView.q) {
                    ResourceManager resourceManager = compositorView.m;
                    Context context = compositorView.getContext();
                    int[] iArr = ys2.a;
                    int[] iArr2 = DeviceFormFactor.a(context) ? ys2.a : ys2.c;
                    int[] iArrCopyOf = DeviceFormFactor.a(compositorView.getContext()) ? ys2.b : ys2.c;
                    if (cc3.b()) {
                        iArrCopyOf = Arrays.copyOf(iArrCopyOf, iArrCopyOf.length + 1);
                        iArrCopyOf[iArrCopyOf.length - 1] = ys2.d;
                    }
                    resourceManager.b(0, iArr2, iArrCopyOf);
                    ResourceManager resourceManager2 = compositorView.m;
                    int[] iArr3 = ac2.d;
                    resourceManager2.b(3, iArr3, cc3.b() ? ac2.e : iArr3);
                    compositorView.q = true;
                }
                N.Mjz8vYEz(compositorView.j, compositorView);
                TabContentManager tabContentManager = compositorView.o;
                ResourceManager resourceManager3 = compositorView.m;
                g61 g61Var = h61Var2.f;
                fj browserControlsManager = g61Var != null ? g61Var.getBrowserControlsManager() : null;
                ArrayList arrayList5 = h61Var2.L;
                if (browserControlsManager != null) {
                    for (int i6 = 0; i6 < arrayList5.size(); i6++) {
                        ((je2) arrayList5.get(i6)).N();
                    }
                    if (h61Var2.q.i()) {
                        h61Var2.w = browserControlsManager.c(h61Var2.w);
                    } else {
                        browserControlsManager.q(h61Var2.w);
                    }
                }
                RectF rectF = h61Var2.B;
                h61Var2.K(rectF);
                RectF rectF2 = h61Var2.C;
                g61Var.h(rectF2);
                y51 y51Var2 = h61Var2.q;
                y51Var2.M(rectF, tabContentManager, resourceManager3, browserControlsManager);
                int i7 = 0;
                ?? N = y51Var2.n();
                while (i7 < arrayList5.size()) {
                    if (((je2) arrayList5.get(i7)).q1()) {
                        ?? U = ((je2) arrayList5.get(i7)).U(rectF2, resourceManager3);
                        U.e(N);
                        N = U;
                    }
                    i7++;
                    N = N;
                }
                N.MPdbXv3F(compositorView.j, compositorView, N);
                long j5 = n3.y;
                if (j5 > 0 && n3.A) {
                    if (j5 > 0) {
                        long jUptimeMillis2 = SystemClock.uptimeMillis() - n3.y;
                        int i8 = n3.z;
                        if (i8 == 0) {
                            str = "Tabs.SwitchFromCloseLatency";
                        } else if (i8 == 1) {
                            str = "Tabs.SwitchFromExitLatency";
                        } else if (i8 == 2) {
                            str = "Tabs.SwitchFromNewLatency";
                        } else if (i8 == 3) {
                            str = "Tabs.SwitchFromUserLatency";
                        }
                        oj2.b0(jUptimeMillis2, str.concat("_Actual"));
                    }
                    n3.y = 0L;
                    n3.A = false;
                }
                N.MPzbdzfI(compositorView.j, compositorView);
                TraceEvent.C("CompositorView:finalizeLayers");
            }
        }
        HashSet hashSet = compositorViewHolder.S;
        HashSet hashSet2 = compositorViewHolder.R;
        hashSet.addAll(hashSet2);
        hashSet2.clear();
        compositorViewHolder.B();
        TraceEvent.C("CompositorViewHolder:layout");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.l = -1;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        View view = this.p;
        if (view != null) {
            Rect rect = this.e;
            view.getWindowVisibleDisplayFrame(rect);
            int i3 = rect.top;
            boolean z = i3 != this.l;
            this.l = i3;
            WindowAndroid windowAndroid = this.n;
            Activity activity = windowAndroid != null ? (Activity) windowAndroid.k().get() : null;
            vj1.h.getClass();
            boolean zO = vj1.o(activity);
            if (!z && !zO && getMeasuredWidth() == View.MeasureSpec.getSize(i) && getMeasuredHeight() > View.MeasureSpec.getSize(i2)) {
                i2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
            }
        }
        super.onMeasure(i, i2);
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        WindowAndroid windowAndroid = this.n;
        if (windowAndroid == null) {
            return;
        }
        if (i == 8) {
            long j = windowAndroid.g;
            if (j != 0) {
                N.MrnNdVRa(j, windowAndroid, false);
            }
        } else if (i == 0) {
            long j2 = windowAndroid.g;
            if (j2 != 0) {
                N.MrnNdVRa(j2, windowAndroid, true);
            }
        }
        r11 r11VarA = r11.a();
        r11VarA.c = null;
        r11VarA.d = null;
        r11VarA.b = null;
    }

    @CalledByNative
    public final void recreateSurface() {
        rw rwVar = this.f;
        if (rwVar.g == null) {
            return;
        }
        rwVar.j.post(new pw(rwVar));
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        rw rwVar = this.f;
        rwVar.e.a.setBackgroundDrawable(drawable);
        rwVar.f.a.setBackgroundDrawable(drawable);
    }

    public void setOverlayVideoMode(boolean z) {
        N.M$Spxfoj(this.j, this, z);
        this.g = z;
        this.f.f(getSurfacePixelFormat());
    }

    public void setRenderHostNeedsDidSwapBuffersCallback(boolean z) {
        if (this.u == z) {
            return;
        }
        this.u = z;
        j();
    }

    public void setRootView(View view) {
        if (this.f == null) {
            Object obj = ThreadUtils.a;
            d();
        }
        this.p = view;
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        rw rwVar = this.f;
        rwVar.e.a.setVisibility(i);
        rwVar.f.a.setVisibility(i);
        if (i == 4) {
            e();
        }
    }

    @Override // android.view.View
    public void setWillNotDraw(boolean z) {
        rw rwVar = this.f;
        rwVar.e.a.setWillNotDraw(z);
        rwVar.f.a.setWillNotDraw(z);
    }
}
