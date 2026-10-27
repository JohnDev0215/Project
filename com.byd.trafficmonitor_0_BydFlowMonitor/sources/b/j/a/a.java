package b.j.a;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import b.e.h;
import b.h.m.c0.d;
import b.h.m.c0.e;
import b.h.m.t;
import b.h.m.w;
import b.j.a.b;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ExploreByTouchHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends b.h.m.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Rect f2721d = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b.a<b.h.m.c0.c> f2722e = new C0048a();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final b.InterfaceC0049b<h<b.h.m.c0.c>, b.h.m.c0.c> f2723f = new b();
    public final AccessibilityManager k;
    public final View l;
    public c m;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Rect f2724g = new Rect();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Rect f2725h = new Rect();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Rect f2726i = new Rect();
    public final int[] j = new int[2];
    public int n = Integer.MIN_VALUE;
    public int o = Integer.MIN_VALUE;
    public int p = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: b.j.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    public static class C0048a implements b.a<b.h.m.c0.c> {
        @Override // b.j.a.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(b.h.m.c0.c cVar, Rect rect) {
            cVar.m(rect);
        }
    }

    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    public static class b implements b.InterfaceC0049b<h<b.h.m.c0.c>, b.h.m.c0.c> {
        @Override // b.j.a.b.InterfaceC0049b
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public b.h.m.c0.c b(h<b.h.m.c0.c> hVar, int i2) {
            return hVar.k(i2);
        }

        @Override // b.j.a.b.InterfaceC0049b
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public int a(h<b.h.m.c0.c> hVar) {
            return hVar.j();
        }
    }

    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    public class c extends d {
        public c() {
        }

        @Override // b.h.m.c0.d
        public b.h.m.c0.c a(int i2) {
            return b.h.m.c0.c.Q(a.this.J(i2));
        }

        @Override // b.h.m.c0.d
        public b.h.m.c0.c c(int i2) {
            int i3 = i2 == 2 ? a.this.n : a.this.o;
            if (i3 == Integer.MIN_VALUE) {
                return null;
            }
            return a(i3);
        }

        @Override // b.h.m.c0.d
        public boolean e(int i2, int i3, Bundle bundle) {
            return a.this.R(i2, i3, bundle);
        }
    }

    public a(View view) {
        if (view == null) {
            throw new IllegalArgumentException("View may not be null");
        }
        this.l = view;
        this.k = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        view.setFocusable(true);
        if (t.x(view) == 0) {
            t.u0(view, 1);
        }
    }

    public static Rect D(View view, int i2, Rect rect) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (i2 == 17) {
            rect.set(width, 0, width, height);
        } else if (i2 == 33) {
            rect.set(0, height, width, height);
        } else if (i2 == 66) {
            rect.set(-1, 0, -1, height);
        } else {
            if (i2 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            rect.set(0, -1, width, -1);
        }
        return rect;
    }

    public static int H(int i2) {
        if (i2 == 19) {
            return 33;
        }
        if (i2 != 21) {
            return i2 != 22 ? 130 : 66;
        }
        return 17;
    }

    public final int A() {
        return this.o;
    }

    public abstract int B(float f2, float f3);

    public abstract void C(List<Integer> list);

    public final void E(int i2) {
        F(i2, 0);
    }

    public final void F(int i2, int i3) {
        ViewParent parent;
        if (i2 == Integer.MIN_VALUE || !this.k.isEnabled() || (parent = this.l.getParent()) == null) {
            return;
        }
        AccessibilityEvent accessibilityEventQ = q(i2, 2048);
        b.h.m.c0.b.b(accessibilityEventQ, i3);
        w.h(parent, this.l, accessibilityEventQ);
    }

    public final boolean G(Rect rect) {
        if (rect == null || rect.isEmpty() || this.l.getWindowVisibility() != 0) {
            return false;
        }
        Object parent = this.l.getParent();
        while (parent instanceof View) {
            View view = (View) parent;
            if (view.getAlpha() <= 0.0f || view.getVisibility() != 0) {
                return false;
            }
            parent = view.getParent();
        }
        return parent != null;
    }

    public final boolean I(int i2, Rect rect) {
        b.h.m.c0.c cVar;
        h<b.h.m.c0.c> hVarY = y();
        int i3 = this.o;
        b.h.m.c0.c cVarE = i3 == Integer.MIN_VALUE ? null : hVarY.e(i3);
        if (i2 == 1 || i2 == 2) {
            cVar = (b.h.m.c0.c) b.j.a.b.d(hVarY, f2723f, f2722e, cVarE, i2, t.z(this.l) == 1, false);
        } else {
            if (i2 != 17 && i2 != 33 && i2 != 66 && i2 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            Rect rect2 = new Rect();
            int i4 = this.o;
            if (i4 != Integer.MIN_VALUE) {
                z(i4, rect2);
            } else if (rect != null) {
                rect2.set(rect);
            } else {
                D(this.l, i2, rect2);
            }
            cVar = (b.h.m.c0.c) b.j.a.b.c(hVarY, f2723f, f2722e, cVarE, rect2, i2);
        }
        return V(cVar != null ? hVarY.h(hVarY.g(cVar)) : Integer.MIN_VALUE);
    }

    public b.h.m.c0.c J(int i2) {
        return i2 == -1 ? u() : t(i2);
    }

    public final void K(boolean z, int i2, Rect rect) {
        int i3 = this.o;
        if (i3 != Integer.MIN_VALUE) {
            o(i3);
        }
        if (z) {
            I(i2, rect);
        }
    }

    public abstract boolean L(int i2, int i3, Bundle bundle);

    public void M(AccessibilityEvent accessibilityEvent) {
    }

    public void N(int i2, AccessibilityEvent accessibilityEvent) {
    }

    public void O(b.h.m.c0.c cVar) {
    }

    public abstract void P(int i2, b.h.m.c0.c cVar);

    public void Q(int i2, boolean z) {
    }

    public boolean R(int i2, int i3, Bundle bundle) {
        return i2 != -1 ? S(i2, i3, bundle) : T(i3, bundle);
    }

    public final boolean S(int i2, int i3, Bundle bundle) {
        return i3 != 1 ? i3 != 2 ? i3 != 64 ? i3 != 128 ? L(i2, i3, bundle) : n(i2) : U(i2) : o(i2) : V(i2);
    }

    public final boolean T(int i2, Bundle bundle) {
        return t.Z(this.l, i2, bundle);
    }

    public final boolean U(int i2) {
        int i3;
        if (!this.k.isEnabled() || !this.k.isTouchExplorationEnabled() || (i3 = this.n) == i2) {
            return false;
        }
        if (i3 != Integer.MIN_VALUE) {
            n(i3);
        }
        this.n = i2;
        this.l.invalidate();
        W(i2, 32768);
        return true;
    }

    public final boolean V(int i2) {
        int i3;
        if ((!this.l.isFocused() && !this.l.requestFocus()) || (i3 = this.o) == i2) {
            return false;
        }
        if (i3 != Integer.MIN_VALUE) {
            o(i3);
        }
        this.o = i2;
        Q(i2, true);
        W(i2, 8);
        return true;
    }

    public final boolean W(int i2, int i3) {
        ViewParent parent;
        if (i2 == Integer.MIN_VALUE || !this.k.isEnabled() || (parent = this.l.getParent()) == null) {
            return false;
        }
        return w.h(parent, this.l, q(i2, i3));
    }

    public final void X(int i2) {
        int i3 = this.p;
        if (i3 == i2) {
            return;
        }
        this.p = i2;
        W(i2, 128);
        W(i3, 256);
    }

    @Override // b.h.m.a
    public d b(View view) {
        if (this.m == null) {
            this.m = new c();
        }
        return this.m;
    }

    @Override // b.h.m.a
    public void f(View view, AccessibilityEvent accessibilityEvent) {
        super.f(view, accessibilityEvent);
        M(accessibilityEvent);
    }

    @Override // b.h.m.a
    public void g(View view, b.h.m.c0.c cVar) {
        super.g(view, cVar);
        O(cVar);
    }

    public final boolean n(int i2) {
        if (this.n != i2) {
            return false;
        }
        this.n = Integer.MIN_VALUE;
        this.l.invalidate();
        W(i2, 65536);
        return true;
    }

    public final boolean o(int i2) {
        if (this.o != i2) {
            return false;
        }
        this.o = Integer.MIN_VALUE;
        Q(i2, false);
        W(i2, 8);
        return true;
    }

    public final boolean p() {
        int i2 = this.o;
        return i2 != Integer.MIN_VALUE && L(i2, 16, null);
    }

    public final AccessibilityEvent q(int i2, int i3) {
        return i2 != -1 ? r(i2, i3) : s(i3);
    }

    public final AccessibilityEvent r(int i2, int i3) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i3);
        b.h.m.c0.c cVarJ = J(i2);
        accessibilityEventObtain.getText().add(cVarJ.x());
        accessibilityEventObtain.setContentDescription(cVarJ.r());
        accessibilityEventObtain.setScrollable(cVarJ.K());
        accessibilityEventObtain.setPassword(cVarJ.J());
        accessibilityEventObtain.setEnabled(cVarJ.F());
        accessibilityEventObtain.setChecked(cVarJ.D());
        N(i2, accessibilityEventObtain);
        if (accessibilityEventObtain.getText().isEmpty() && accessibilityEventObtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        accessibilityEventObtain.setClassName(cVarJ.p());
        e.c(accessibilityEventObtain, this.l, i2);
        accessibilityEventObtain.setPackageName(this.l.getContext().getPackageName());
        return accessibilityEventObtain;
    }

    public final AccessibilityEvent s(int i2) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i2);
        this.l.onInitializeAccessibilityEvent(accessibilityEventObtain);
        return accessibilityEventObtain;
    }

    public final b.h.m.c0.c t(int i2) {
        b.h.m.c0.c cVarO = b.h.m.c0.c.O();
        cVarO.h0(true);
        cVarO.j0(true);
        cVarO.c0("android.view.View");
        Rect rect = f2721d;
        cVarO.X(rect);
        cVarO.Y(rect);
        cVarO.s0(this.l);
        P(i2, cVarO);
        if (cVarO.x() == null && cVarO.r() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        cVarO.m(this.f2725h);
        if (this.f2725h.equals(rect)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int iK = cVarO.k();
        if ((iK & 64) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((iK & 128) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        cVarO.q0(this.l.getContext().getPackageName());
        cVarO.B0(this.l, i2);
        if (this.n == i2) {
            cVarO.V(true);
            cVarO.a(128);
        } else {
            cVarO.V(false);
            cVarO.a(64);
        }
        boolean z = this.o == i2;
        if (z) {
            cVarO.a(2);
        } else if (cVarO.G()) {
            cVarO.a(1);
        }
        cVarO.k0(z);
        this.l.getLocationOnScreen(this.j);
        cVarO.n(this.f2724g);
        if (this.f2724g.equals(rect)) {
            cVarO.m(this.f2724g);
            if (cVarO.f2610c != -1) {
                b.h.m.c0.c cVarO2 = b.h.m.c0.c.O();
                for (int i3 = cVarO.f2610c; i3 != -1; i3 = cVarO2.f2610c) {
                    cVarO2.t0(this.l, -1);
                    cVarO2.X(f2721d);
                    P(i3, cVarO2);
                    cVarO2.m(this.f2725h);
                    Rect rect2 = this.f2724g;
                    Rect rect3 = this.f2725h;
                    rect2.offset(rect3.left, rect3.top);
                }
                cVarO2.S();
            }
            this.f2724g.offset(this.j[0] - this.l.getScrollX(), this.j[1] - this.l.getScrollY());
        }
        if (this.l.getLocalVisibleRect(this.f2726i)) {
            this.f2726i.offset(this.j[0] - this.l.getScrollX(), this.j[1] - this.l.getScrollY());
            if (this.f2724g.intersect(this.f2726i)) {
                cVarO.Y(this.f2724g);
                if (G(this.f2724g)) {
                    cVarO.D0(true);
                }
            }
        }
        return cVarO;
    }

    public final b.h.m.c0.c u() {
        b.h.m.c0.c cVarP = b.h.m.c0.c.P(this.l);
        t.X(this.l, cVarP);
        ArrayList arrayList = new ArrayList();
        C(arrayList);
        if (cVarP.o() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            cVarP.d(this.l, ((Integer) arrayList.get(i2)).intValue());
        }
        return cVarP;
    }

    public final boolean v(MotionEvent motionEvent) {
        if (!this.k.isEnabled() || !this.k.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            int iB = B(motionEvent.getX(), motionEvent.getY());
            X(iB);
            return iB != Integer.MIN_VALUE;
        }
        if (action != 10 || this.p == Integer.MIN_VALUE) {
            return false;
        }
        X(Integer.MIN_VALUE);
        return true;
    }

    public final boolean w(KeyEvent keyEvent) {
        int i2 = 0;
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
                    boolean z = false;
                    while (i2 < repeatCount && I(iH, null)) {
                        i2++;
                        z = true;
                    }
                    return z;
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
        return this.n;
    }

    public final h<b.h.m.c0.c> y() {
        ArrayList arrayList = new ArrayList();
        C(arrayList);
        h<b.h.m.c0.c> hVar = new h<>();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            hVar.i(i2, t(i2));
        }
        return hVar;
    }

    public final void z(int i2, Rect rect) {
        J(i2).m(rect);
    }
}
