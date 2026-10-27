package t0;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.util.ArrayList;
import java.util.List;
import o0.t;
import o0.w;
import p0.d;
import p0.e;
import t0.b;
import ts.car.service.carplay.data.config.CarPlayConfig;
import v.h;

/* JADX INFO: compiled from: ExploreByTouchHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends o0.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Rect f7953d = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b.a<p0.c> f7954e = new C0121a();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final b.InterfaceC0122b<h<p0.c>, p0.c> f7955f = new b();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final AccessibilityManager f7960k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final View f7961l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public c f7962m;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Rect f7956g = new Rect();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Rect f7957h = new Rect();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Rect f7958i = new Rect();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int[] f7959j = new int[2];

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f7963n = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f7964o = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f7965p = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: t0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    public static class C0121a implements b.a<p0.c> {
        @Override // t0.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(p0.c cVar, Rect rect) {
            cVar.m(rect);
        }
    }

    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    public static class b implements b.InterfaceC0122b<h<p0.c>, p0.c> {
        @Override // t0.b.InterfaceC0122b
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public p0.c a(h<p0.c> hVar, int i8) {
            return hVar.m(i8);
        }

        @Override // t0.b.InterfaceC0122b
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public int b(h<p0.c> hVar) {
            return hVar.l();
        }
    }

    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    public class c extends d {
        public c() {
        }

        @Override // p0.d
        public p0.c a(int i8) {
            return p0.c.Q(a.this.J(i8));
        }

        @Override // p0.d
        public p0.c c(int i8) {
            int i9 = i8 == 2 ? a.this.f7963n : a.this.f7964o;
            if (i9 == Integer.MIN_VALUE) {
                return null;
            }
            return a(i9);
        }

        @Override // p0.d
        public boolean e(int i8, int i9, Bundle bundle) {
            return a.this.R(i8, i9, bundle);
        }
    }

    public a(View view) {
        if (view == null) {
            throw new IllegalArgumentException("View may not be null");
        }
        this.f7961l = view;
        this.f7960k = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        view.setFocusable(true);
        if (t.y(view) == 0) {
            t.v0(view, 1);
        }
    }

    public static Rect D(View view, int i8, Rect rect) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (i8 == 17) {
            rect.set(width, 0, width, height);
        } else if (i8 == 33) {
            rect.set(0, height, width, height);
        } else if (i8 == 66) {
            rect.set(-1, 0, -1, height);
        } else {
            if (i8 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            rect.set(0, -1, width, -1);
        }
        return rect;
    }

    public static int H(int i8) {
        if (i8 == 19) {
            return 33;
        }
        if (i8 != 21) {
            return i8 != 22 ? 130 : 66;
        }
        return 17;
    }

    public final int A() {
        return this.f7964o;
    }

    public abstract int B(float f8, float f9);

    public abstract void C(List<Integer> list);

    public final void E(int i8) {
        F(i8, 0);
    }

    public final void F(int i8, int i9) {
        ViewParent parent;
        if (i8 == Integer.MIN_VALUE || !this.f7960k.isEnabled() || (parent = this.f7961l.getParent()) == null) {
            return;
        }
        AccessibilityEvent accessibilityEventQ = q(i8, 2048);
        p0.b.b(accessibilityEventQ, i9);
        w.h(parent, this.f7961l, accessibilityEventQ);
    }

    public final boolean G(Rect rect) {
        if (rect == null || rect.isEmpty() || this.f7961l.getWindowVisibility() != 0) {
            return false;
        }
        Object parent = this.f7961l.getParent();
        while (parent instanceof View) {
            View view = (View) parent;
            if (view.getAlpha() <= 0.0f || view.getVisibility() != 0) {
                return false;
            }
            parent = view.getParent();
        }
        return parent != null;
    }

    public final boolean I(int i8, Rect rect) {
        p0.c cVar;
        h<p0.c> hVarY = y();
        int i9 = this.f7964o;
        p0.c cVarE = i9 == Integer.MIN_VALUE ? null : hVarY.e(i9);
        if (i8 == 1 || i8 == 2) {
            cVar = (p0.c) t0.b.d(hVarY, f7955f, f7954e, cVarE, i8, t.A(this.f7961l) == 1, false);
        } else {
            if (i8 != 17 && i8 != 33 && i8 != 66 && i8 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            Rect rect2 = new Rect();
            int i10 = this.f7964o;
            if (i10 != Integer.MIN_VALUE) {
                z(i10, rect2);
            } else if (rect != null) {
                rect2.set(rect);
            } else {
                D(this.f7961l, i8, rect2);
            }
            cVar = (p0.c) t0.b.c(hVarY, f7955f, f7954e, cVarE, rect2, i8);
        }
        return V(cVar != null ? hVarY.i(hVarY.h(cVar)) : Integer.MIN_VALUE);
    }

    public p0.c J(int i8) {
        return i8 == -1 ? u() : t(i8);
    }

    public final void K(boolean z7, int i8, Rect rect) {
        int i9 = this.f7964o;
        if (i9 != Integer.MIN_VALUE) {
            o(i9);
        }
        if (z7) {
            I(i8, rect);
        }
    }

    public abstract boolean L(int i8, int i9, Bundle bundle);

    public void M(AccessibilityEvent accessibilityEvent) {
    }

    public void N(int i8, AccessibilityEvent accessibilityEvent) {
    }

    public void O(p0.c cVar) {
    }

    public abstract void P(int i8, p0.c cVar);

    public void Q(int i8, boolean z7) {
    }

    public boolean R(int i8, int i9, Bundle bundle) {
        return i8 != -1 ? S(i8, i9, bundle) : T(i9, bundle);
    }

    public final boolean S(int i8, int i9, Bundle bundle) {
        return i9 != 1 ? i9 != 2 ? i9 != 64 ? i9 != 128 ? L(i8, i9, bundle) : n(i8) : U(i8) : o(i8) : V(i8);
    }

    public final boolean T(int i8, Bundle bundle) {
        return t.a0(this.f7961l, i8, bundle);
    }

    public final boolean U(int i8) {
        int i9;
        if (!this.f7960k.isEnabled() || !this.f7960k.isTouchExplorationEnabled() || (i9 = this.f7963n) == i8) {
            return false;
        }
        if (i9 != Integer.MIN_VALUE) {
            n(i9);
        }
        this.f7963n = i8;
        this.f7961l.invalidate();
        W(i8, CarPlayConfig.IapFeature.IAP_FEATURE_ROUTE_GUIDANCE);
        return true;
    }

    public final boolean V(int i8) {
        int i9;
        if ((!this.f7961l.isFocused() && !this.f7961l.requestFocus()) || (i9 = this.f7964o) == i8) {
            return false;
        }
        if (i9 != Integer.MIN_VALUE) {
            o(i9);
        }
        this.f7964o = i8;
        Q(i8, true);
        W(i8, 8);
        return true;
    }

    public final boolean W(int i8, int i9) {
        ViewParent parent;
        if (i8 == Integer.MIN_VALUE || !this.f7960k.isEnabled() || (parent = this.f7961l.getParent()) == null) {
            return false;
        }
        return w.h(parent, this.f7961l, q(i8, i9));
    }

    public final void X(int i8) {
        int i9 = this.f7965p;
        if (i9 == i8) {
            return;
        }
        this.f7965p = i8;
        W(i8, 128);
        W(i9, 256);
    }

    @Override // o0.a
    public d b(View view) {
        if (this.f7962m == null) {
            this.f7962m = new c();
        }
        return this.f7962m;
    }

    @Override // o0.a
    public void f(View view, AccessibilityEvent accessibilityEvent) {
        super.f(view, accessibilityEvent);
        M(accessibilityEvent);
    }

    @Override // o0.a
    public void g(View view, p0.c cVar) {
        super.g(view, cVar);
        O(cVar);
    }

    public final boolean n(int i8) {
        if (this.f7963n != i8) {
            return false;
        }
        this.f7963n = Integer.MIN_VALUE;
        this.f7961l.invalidate();
        W(i8, CarPlayConfig.IapFeature.IAP_FEATURE_EAP_OVER_IAP2);
        return true;
    }

    public final boolean o(int i8) {
        if (this.f7964o != i8) {
            return false;
        }
        this.f7964o = Integer.MIN_VALUE;
        Q(i8, false);
        W(i8, 8);
        return true;
    }

    public final boolean p() {
        int i8 = this.f7964o;
        return i8 != Integer.MIN_VALUE && L(i8, 16, null);
    }

    public final AccessibilityEvent q(int i8, int i9) {
        return i8 != -1 ? r(i8, i9) : s(i9);
    }

    public final AccessibilityEvent r(int i8, int i9) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i9);
        p0.c cVarJ = J(i8);
        accessibilityEventObtain.getText().add(cVarJ.x());
        accessibilityEventObtain.setContentDescription(cVarJ.r());
        accessibilityEventObtain.setScrollable(cVarJ.K());
        accessibilityEventObtain.setPassword(cVarJ.J());
        accessibilityEventObtain.setEnabled(cVarJ.F());
        accessibilityEventObtain.setChecked(cVarJ.D());
        N(i8, accessibilityEventObtain);
        if (accessibilityEventObtain.getText().isEmpty() && accessibilityEventObtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        accessibilityEventObtain.setClassName(cVarJ.p());
        e.c(accessibilityEventObtain, this.f7961l, i8);
        accessibilityEventObtain.setPackageName(this.f7961l.getContext().getPackageName());
        return accessibilityEventObtain;
    }

    public final AccessibilityEvent s(int i8) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i8);
        this.f7961l.onInitializeAccessibilityEvent(accessibilityEventObtain);
        return accessibilityEventObtain;
    }

    public final p0.c t(int i8) {
        p0.c cVarO = p0.c.O();
        cVarO.h0(true);
        cVarO.j0(true);
        cVarO.c0("android.view.View");
        Rect rect = f7953d;
        cVarO.X(rect);
        cVarO.Y(rect);
        cVarO.s0(this.f7961l);
        P(i8, cVarO);
        if (cVarO.x() == null && cVarO.r() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        cVarO.m(this.f7957h);
        if (this.f7957h.equals(rect)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int iK = cVarO.k();
        if ((iK & 64) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((iK & 128) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        cVarO.q0(this.f7961l.getContext().getPackageName());
        cVarO.B0(this.f7961l, i8);
        if (this.f7963n == i8) {
            cVarO.V(true);
            cVarO.a(128);
        } else {
            cVarO.V(false);
            cVarO.a(64);
        }
        boolean z7 = this.f7964o == i8;
        if (z7) {
            cVarO.a(2);
        } else if (cVarO.G()) {
            cVarO.a(1);
        }
        cVarO.k0(z7);
        this.f7961l.getLocationOnScreen(this.f7959j);
        cVarO.n(this.f7956g);
        if (this.f7956g.equals(rect)) {
            cVarO.m(this.f7956g);
            if (cVarO.f6966c != -1) {
                p0.c cVarO2 = p0.c.O();
                for (int i9 = cVarO.f6966c; i9 != -1; i9 = cVarO2.f6966c) {
                    cVarO2.t0(this.f7961l, -1);
                    cVarO2.X(f7953d);
                    P(i9, cVarO2);
                    cVarO2.m(this.f7957h);
                    Rect rect2 = this.f7956g;
                    Rect rect3 = this.f7957h;
                    rect2.offset(rect3.left, rect3.top);
                }
                cVarO2.S();
            }
            this.f7956g.offset(this.f7959j[0] - this.f7961l.getScrollX(), this.f7959j[1] - this.f7961l.getScrollY());
        }
        if (this.f7961l.getLocalVisibleRect(this.f7958i)) {
            this.f7958i.offset(this.f7959j[0] - this.f7961l.getScrollX(), this.f7959j[1] - this.f7961l.getScrollY());
            if (this.f7956g.intersect(this.f7958i)) {
                cVarO.Y(this.f7956g);
                if (G(this.f7956g)) {
                    cVarO.D0(true);
                }
            }
        }
        return cVarO;
    }

    public final p0.c u() {
        p0.c cVarP = p0.c.P(this.f7961l);
        t.Y(this.f7961l, cVarP);
        ArrayList arrayList = new ArrayList();
        C(arrayList);
        if (cVarP.o() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int i8 = 0; i8 < size; i8++) {
            cVarP.d(this.f7961l, ((Integer) arrayList.get(i8)).intValue());
        }
        return cVarP;
    }

    public final boolean v(MotionEvent motionEvent) {
        if (!this.f7960k.isEnabled() || !this.f7960k.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            int iB = B(motionEvent.getX(), motionEvent.getY());
            X(iB);
            return iB != Integer.MIN_VALUE;
        }
        if (action != 10 || this.f7965p == Integer.MIN_VALUE) {
            return false;
        }
        X(Integer.MIN_VALUE);
        return true;
    }

    public final boolean w(KeyEvent keyEvent) {
        int i8 = 0;
        if (keyEvent.getAction() == 1) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 61) {
            if (keyEvent.hasNoModifiers()) {
                return I(2, null);
            }
            if (keyEvent.hasModifiers(1)) {
                return I(1, null);
            }
            return false;
        }
        if (keyCode != 66) {
            switch (keyCode) {
                case 19:
                case 20:
                case 21:
                case 22:
                    if (!keyEvent.hasNoModifiers()) {
                        return false;
                    }
                    int iH = H(keyCode);
                    int repeatCount = keyEvent.getRepeatCount() + 1;
                    boolean z7 = false;
                    while (i8 < repeatCount && I(iH, null)) {
                        i8++;
                        z7 = true;
                    }
                    return z7;
                case 23:
                    break;
                default:
                    return false;
            }
        }
        if (!keyEvent.hasNoModifiers() || keyEvent.getRepeatCount() != 0) {
            return false;
        }
        p();
        return true;
    }

    public final int x() {
        return this.f7963n;
    }

    public final h<p0.c> y() {
        ArrayList arrayList = new ArrayList();
        C(arrayList);
        h<p0.c> hVar = new h<>();
        for (int i8 = 0; i8 < arrayList.size(); i8++) {
            hVar.j(i8, t(i8));
        }
        return hVar;
    }

    public final void z(int i8, Rect rect) {
        J(i8).m(rect);
    }
}
