package d1;

import A.C0022k;
import E0.AbstractC0156f;
import E0.F;
import E0.j0;
import E0.k0;
import F0.T;
import F0.q1;
import F1.C0254m;
import F1.InterfaceC0253l;
import S0.p;
import S8.AbstractC0727y;
import T.AbstractC0764s;
import T.C0741g;
import T.InterfaceC0749k;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.Region;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.compose.ui.node.Owner;
import androidx.lifecycle.A;
import androidx.lifecycle.a0;
import com.stingray.drivemodedetector.R;
import h6.q;
import java.util.LinkedHashMap;
import x3.AbstractC2673a;
import y0.t;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class i extends ViewGroup implements InterfaceC0253l, InterfaceC0749k, j0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public InterfaceC2782k f13295A;
    public final int[] B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public int f13296C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public int f13297D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final C0254m f13298E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public boolean f13299F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final F f13300G;
    public final x0.d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final View f13301m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Owner f13302n;
    public InterfaceC2772a o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f13303p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public InterfaceC2772a f13304q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public InterfaceC2772a f13305r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public g0.o f13306s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public InterfaceC2782k f13307t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public a1.b f13308u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public InterfaceC2782k f13309v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public A f13310w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public t3.e f13311x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final h f13312y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final h f13313z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(Context context, AbstractC0764s abstractC0764s, int i7, x0.d dVar, View view, Owner owner) {
        super(context);
        int i10 = 12;
        int i11 = 2;
        int i12 = 1;
        int i13 = 0;
        this.l = dVar;
        this.f13301m = view;
        this.f13302n = owner;
        if (abstractC0764s != null) {
            LinkedHashMap linkedHashMap = q1.f2251a;
            setTag(R.id.androidx_compose_ui_view_composition_context, abstractC0764s);
        }
        setSaveFromParentEnabled(false);
        addView(view);
        this.o = g.f13291p;
        this.f13304q = g.o;
        this.f13305r = g.f13290n;
        g0.l lVar = g0.l.f14684a;
        this.f13306s = lVar;
        this.f13308u = l9.d.g();
        o oVar = (o) this;
        this.f13312y = new h(oVar, i12);
        this.f13313z = new h(oVar, i13);
        this.B = new int[2];
        this.f13296C = Integer.MIN_VALUE;
        this.f13297D = Integer.MIN_VALUE;
        this.f13298E = new C0254m(0);
        F f10 = new F(3, 0, false);
        f10.f1488u = oVar;
        g0.o oVarA = L0.k.a(androidx.compose.ui.input.nestedscroll.a.a(lVar, j.f13314a, dVar), true, C1057a.f13277p);
        t tVar = new t();
        tVar.f24098a = new C1059c(oVar, i12);
        N7.g gVar = new N7.g();
        N7.g gVar2 = tVar.f24099b;
        if (gVar2 != null) {
            gVar2.f4793m = null;
        }
        tVar.f24099b = gVar;
        gVar.f4793m = tVar;
        setOnRequestDisallowInterceptTouchEvent$ui_release(gVar);
        g0.o oVarD = androidx.compose.ui.layout.a.d(androidx.compose.ui.draw.a.a(oVarA.k(tVar), new C0022k(oVar, f10, oVar, i10)), new C1058b(oVar, f10, i11));
        f10.y0(this.f13306s.k(oVarD));
        this.f13307t = new C0741g(f10, 6, oVarD);
        f10.v0(this.f13308u);
        this.f13309v = new p(f10, i10);
        f10.f1475N = new C1058b(oVar, f10, i13);
        f10.f1476O = new C1059c(oVar, i13);
        f10.x0(new C1060d(oVar, f10));
        this.f13300G = f10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final k0 getSnapshotObserver() {
        if (isAttachedToWindow()) {
            return this.f13302n.getSnapshotObserver();
        }
        l9.d.h0("Expected AndroidViewHolder to be attached when observing reads.");
        throw null;
    }

    public static final int k(o oVar, int i7, int i10, int i11) {
        return (i11 >= 0 || i7 == i10) ? View.MeasureSpec.makeMeasureSpec(AbstractC2673a.v(i11, i7, i10), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) : (i11 != -2 || i10 == Integer.MAX_VALUE) ? (i11 != -1 || i10 == Integer.MAX_VALUE) ? View.MeasureSpec.makeMeasureSpec(0, 0) : View.MeasureSpec.makeMeasureSpec(i10, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED) : View.MeasureSpec.makeMeasureSpec(i10, Integer.MIN_VALUE);
    }

    @Override // E0.j0
    public final boolean A() {
        return isAttachedToWindow();
    }

    @Override // F1.InterfaceC0252k
    public final void a(View view, View view2, int i7, int i10) {
        C0254m c0254m = this.f13298E;
        if (i10 == 1) {
            c0254m.c = i7;
        } else {
            c0254m.f2343b = i7;
        }
    }

    @Override // F1.InterfaceC0252k
    public final void b(View view, int i7) {
        C0254m c0254m = this.f13298E;
        if (i7 == 1) {
            c0254m.c = 0;
        } else {
            c0254m.f2343b = 0;
        }
    }

    @Override // F1.InterfaceC0252k
    public final void c(View view, int i7, int i10, int[] iArr, int i11) {
        if (this.f13301m.isNestedScrollingEnabled()) {
            float f10 = i7;
            float f11 = -1;
            long jA = h6.e.a(f10 * f11, i10 * f11);
            int i12 = i11 == 0 ? 1 : 2;
            x0.g gVar = this.l.f23678a;
            x0.g gVar2 = null;
            if (gVar != null && gVar.f14695x) {
                gVar2 = (x0.g) AbstractC0156f.k(gVar);
            }
            long jN = gVar2 != null ? gVar2.n(jA, i12) : 0L;
            iArr[0] = T.n(m0.c.d(jN));
            iArr[1] = T.n(m0.c.e(jN));
        }
    }

    @Override // T.InterfaceC0749k
    public final void d() {
        View view = this.f13301m;
        if (view.getParent() != this) {
            addView(view);
        } else {
            this.f13304q.a();
        }
    }

    @Override // T.InterfaceC0749k
    public final void e() {
        this.f13305r.a();
    }

    @Override // T.InterfaceC0749k
    public final void f() {
        this.f13304q.a();
        removeAllViewsInLayout();
    }

    @Override // F1.InterfaceC0253l
    public final void g(View view, int i7, int i10, int i11, int i12, int i13, int[] iArr) {
        if (this.f13301m.isNestedScrollingEnabled()) {
            float f10 = i7;
            float f11 = -1;
            long jA = h6.e.a(f10 * f11, i10 * f11);
            long jA2 = h6.e.a(i11 * f11, i12 * f11);
            int i14 = i13 == 0 ? 1 : 2;
            x0.g gVar = this.l.f23678a;
            x0.g gVar2 = null;
            if (gVar != null && gVar.f14695x) {
                gVar2 = (x0.g) AbstractC0156f.k(gVar);
            }
            x0.g gVar3 = gVar2;
            long jN = gVar3 != null ? gVar3.N(i14, jA, jA2) : 0L;
            iArr[0] = T.n(m0.c.d(jN));
            iArr[1] = T.n(m0.c.e(jN));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean gatherTransparentRegion(Region region) {
        if (region == null) {
            return true;
        }
        int[] iArr = this.B;
        getLocationInWindow(iArr);
        int i7 = iArr[0];
        region.op(i7, iArr[1], getWidth() + i7, getHeight() + iArr[1], Region.Op.DIFFERENCE);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return getClass().getName();
    }

    public final a1.b getDensity() {
        return this.f13308u;
    }

    public final View getInteropView() {
        return this.f13301m;
    }

    public final F getLayoutNode() {
        return this.f13300G;
    }

    @Override // android.view.View
    public ViewGroup.LayoutParams getLayoutParams() {
        ViewGroup.LayoutParams layoutParams = this.f13301m.getLayoutParams();
        return layoutParams == null ? new ViewGroup.LayoutParams(-1, -1) : layoutParams;
    }

    public final A getLifecycleOwner() {
        return this.f13310w;
    }

    public final g0.o getModifier() {
        return this.f13306s;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        C0254m c0254m = this.f13298E;
        return c0254m.c | c0254m.f2343b;
    }

    public final InterfaceC2782k getOnDensityChanged$ui_release() {
        return this.f13309v;
    }

    public final InterfaceC2782k getOnModifierChanged$ui_release() {
        return this.f13307t;
    }

    public final InterfaceC2782k getOnRequestDisallowInterceptTouchEvent$ui_release() {
        return this.f13295A;
    }

    public final InterfaceC2772a getRelease() {
        return this.f13305r;
    }

    public final InterfaceC2772a getReset() {
        return this.f13304q;
    }

    public final t3.e getSavedStateRegistryOwner() {
        return this.f13311x;
    }

    public final InterfaceC2772a getUpdate() {
        return this.o;
    }

    public final View getView() {
        return this.f13301m;
    }

    @Override // F1.InterfaceC0252k
    public final void h(View view, int i7, int i10, int i11, int i12, int i13) {
        if (this.f13301m.isNestedScrollingEnabled()) {
            float f10 = i7;
            float f11 = -1;
            long jA = h6.e.a(f10 * f11, i10 * f11);
            long jA2 = h6.e.a(i11 * f11, i12 * f11);
            int i14 = i13 == 0 ? 1 : 2;
            x0.g gVar = this.l.f23678a;
            x0.g gVar2 = null;
            if (gVar != null && gVar.f14695x) {
                gVar2 = (x0.g) AbstractC0156f.k(gVar);
            }
            x0.g gVar3 = gVar2;
            if (gVar3 != null) {
                gVar3.N(i14, jA, jA2);
            }
        }
    }

    @Override // F1.InterfaceC0252k
    public final boolean i(View view, View view2, int i7, int i10) {
        return ((i7 & 2) == 0 && (i7 & 1) == 0) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ViewParent invalidateChildInParent(int[] iArr, Rect rect) {
        super.invalidateChildInParent(iArr, rect);
        if (!this.f13299F) {
            this.f13300G.O();
            return null;
        }
        this.f13301m.postOnAnimation(new A2.l(this.f13313z, 10));
        return null;
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return this.f13301m.isNestedScrollingEnabled();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f13312y.a();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onDescendantInvalidated(View view, View view2) {
        super.onDescendantInvalidated(view, view2);
        if (!this.f13299F) {
            this.f13300G.O();
        } else {
            this.f13301m.postOnAnimation(new A2.l(this.f13313z, 10));
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getSnapshotObserver().f1682a.c(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        this.f13301m.layout(0, 0, i11 - i7, i12 - i10);
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i10) {
        View view = this.f13301m;
        if (view.getParent() != this) {
            setMeasuredDimension(View.MeasureSpec.getSize(i7), View.MeasureSpec.getSize(i10));
            return;
        }
        if (view.getVisibility() == 8) {
            setMeasuredDimension(0, 0);
            return;
        }
        view.measure(i7, i10);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
        this.f13296C = i7;
        this.f13297D = i10;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f10, float f11, boolean z6) {
        if (!this.f13301m.isNestedScrollingEnabled()) {
            return false;
        }
        AbstractC0727y.w(this.l.c(), null, null, new C1061e(z6, this, P8.n.i(f10 * (-1.0f), f11 * (-1.0f)), null), 3);
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f10, float f11) {
        if (!this.f13301m.isNestedScrollingEnabled()) {
            return false;
        }
        AbstractC0727y.w(this.l.c(), null, null, new C1062f(this, P8.n.i(f10 * (-1.0f), f11 * (-1.0f)), null), 3);
        return false;
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i7) {
        super.onWindowVisibilityChanged(i7);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z6) {
        InterfaceC2782k interfaceC2782k = this.f13295A;
        if (interfaceC2782k != null) {
            interfaceC2782k.b(Boolean.valueOf(z6));
        }
        super.requestDisallowInterceptTouchEvent(z6);
    }

    public final void setDensity(a1.b bVar) {
        if (bVar != this.f13308u) {
            this.f13308u = bVar;
            InterfaceC2782k interfaceC2782k = this.f13309v;
            if (interfaceC2782k != null) {
                interfaceC2782k.b(bVar);
            }
        }
    }

    public final void setLifecycleOwner(A a10) {
        if (a10 != this.f13310w) {
            this.f13310w = a10;
            a0.j(this, a10);
        }
    }

    public final void setModifier(g0.o oVar) {
        if (oVar != this.f13306s) {
            this.f13306s = oVar;
            InterfaceC2782k interfaceC2782k = this.f13307t;
            if (interfaceC2782k != null) {
                interfaceC2782k.b(oVar);
            }
        }
    }

    public final void setOnDensityChanged$ui_release(InterfaceC2782k interfaceC2782k) {
        this.f13309v = interfaceC2782k;
    }

    public final void setOnModifierChanged$ui_release(InterfaceC2782k interfaceC2782k) {
        this.f13307t = interfaceC2782k;
    }

    public final void setOnRequestDisallowInterceptTouchEvent$ui_release(InterfaceC2782k interfaceC2782k) {
        this.f13295A = interfaceC2782k;
    }

    public final void setRelease(InterfaceC2772a interfaceC2772a) {
        this.f13305r = interfaceC2772a;
    }

    public final void setReset(InterfaceC2772a interfaceC2772a) {
        this.f13304q = interfaceC2772a;
    }

    public final void setSavedStateRegistryOwner(t3.e eVar) {
        if (eVar != this.f13311x) {
            this.f13311x = eVar;
            q.z(this, eVar);
        }
    }

    public final void setUpdate(InterfaceC2772a interfaceC2772a) {
        this.o = interfaceC2772a;
        this.f13303p = true;
        this.f13312y.a();
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return true;
    }
}
