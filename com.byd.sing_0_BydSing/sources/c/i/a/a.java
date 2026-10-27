package c.i.a;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import c.d.h;
import c.g.k.c0.d;
import c.g.k.c0.e;
import c.g.k.t;
import c.g.k.w;
import c.i.a.b;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ExploreByTouchHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends c.g.k.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final Rect f2359d = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    private static final b.a<c.g.k.c0.c> e = new C0070a();
    private static final b.InterfaceC0071b<h<c.g.k.c0.c>, c.g.k.c0.c> f = new b();
    private final AccessibilityManager k;
    private final View l;
    private c m;
    private final Rect g = new Rect();
    private final Rect h = new Rect();
    private final Rect i = new Rect();
    private final int[] j = new int[2];
    int n = Integer.MIN_VALUE;
    int o = Integer.MIN_VALUE;
    private int p = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: c.i.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    static class C0070a implements b.a<c.g.k.c0.c> {
        C0070a() {
        }

        @Override // c.i.a.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(c.g.k.c0.c cVar, Rect rect) {
            cVar.m(rect);
        }
    }

    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    static class b implements b.InterfaceC0071b<h<c.g.k.c0.c>, c.g.k.c0.c> {
        b() {
        }

        @Override // c.i.a.b.InterfaceC0071b
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public c.g.k.c0.c a(h<c.g.k.c0.c> hVar, int i) {
            return hVar.l(i);
        }

        @Override // c.i.a.b.InterfaceC0071b
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public int b(h<c.g.k.c0.c> hVar) {
            return hVar.k();
        }
    }

    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    private class c extends d {
        c() {
        }

        @Override // c.g.k.c0.d
        public c.g.k.c0.c a(int i) {
            return c.g.k.c0.c.P(a.this.J(i));
        }

        @Override // c.g.k.c0.d
        public c.g.k.c0.c c(int i) {
            int i2 = i == 2 ? a.this.n : a.this.o;
            if (i2 == Integer.MIN_VALUE) {
                return null;
            }
            return a(i2);
        }

        @Override // c.g.k.c0.d
        public boolean e(int i, int i2, Bundle bundle) {
            return a.this.R(i, i2, bundle);
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
            t.t0(view, 1);
        }
    }

    private static Rect D(View view, int i, Rect rect) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (i == 17) {
            rect.set(width, 0, width, height);
        } else if (i == 33) {
            rect.set(0, height, width, height);
        } else if (i == 66) {
            rect.set(-1, 0, -1, height);
        } else {
            if (i != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            rect.set(0, -1, width, -1);
        }
        return rect;
    }

    private boolean G(Rect rect) {
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

    private static int H(int i) {
        if (i == 19) {
            return 33;
        }
        if (i != 21) {
            return i != 22 ? 130 : 66;
        }
        return 17;
    }

    private boolean I(int i, Rect rect) {
        c.g.k.c0.c cVar;
        h<c.g.k.c0.c> hVarY = y();
        int i2 = this.o;
        c.g.k.c0.c cVarE = i2 == Integer.MIN_VALUE ? null : hVarY.e(i2);
        if (i == 1 || i == 2) {
            cVar = (c.g.k.c0.c) c.i.a.b.d(hVarY, f, e, cVarE, i, t.z(this.l) == 1, false);
        } else {
            if (i != 17 && i != 33 && i != 66 && i != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            Rect rect2 = new Rect();
            int i3 = this.o;
            if (i3 != Integer.MIN_VALUE) {
                z(i3, rect2);
            } else if (rect != null) {
                rect2.set(rect);
            } else {
                D(this.l, i, rect2);
            }
            cVar = (c.g.k.c0.c) c.i.a.b.c(hVarY, f, e, cVarE, rect2, i);
        }
        return V(cVar != null ? hVarY.h(hVarY.g(cVar)) : Integer.MIN_VALUE);
    }

    private boolean S(int i, int i2, Bundle bundle) {
        return i2 != 1 ? i2 != 2 ? i2 != 64 ? i2 != 128 ? L(i, i2, bundle) : n(i) : U(i) : o(i) : V(i);
    }

    private boolean T(int i, Bundle bundle) {
        return t.Z(this.l, i, bundle);
    }

    private boolean U(int i) {
        int i2;
        if (!this.k.isEnabled() || !this.k.isTouchExplorationEnabled() || (i2 = this.n) == i) {
            return false;
        }
        if (i2 != Integer.MIN_VALUE) {
            n(i2);
        }
        this.n = i;
        this.l.invalidate();
        W(i, 32768);
        return true;
    }

    private void X(int i) {
        int i2 = this.p;
        if (i2 == i) {
            return;
        }
        this.p = i;
        W(i, 128);
        W(i2, 256);
    }

    private boolean n(int i) {
        if (this.n != i) {
            return false;
        }
        this.n = Integer.MIN_VALUE;
        this.l.invalidate();
        W(i, 65536);
        return true;
    }

    private boolean p() {
        int i = this.o;
        return i != Integer.MIN_VALUE && L(i, 16, null);
    }

    private AccessibilityEvent q(int i, int i2) {
        return i != -1 ? r(i, i2) : s(i2);
    }

    private AccessibilityEvent r(int i, int i2) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i2);
        c.g.k.c0.c cVarJ = J(i);
        accessibilityEventObtain.getText().add(cVarJ.w());
        accessibilityEventObtain.setContentDescription(cVarJ.r());
        accessibilityEventObtain.setScrollable(cVarJ.J());
        accessibilityEventObtain.setPassword(cVarJ.I());
        accessibilityEventObtain.setEnabled(cVarJ.E());
        accessibilityEventObtain.setChecked(cVarJ.C());
        N(i, accessibilityEventObtain);
        if (accessibilityEventObtain.getText().isEmpty() && accessibilityEventObtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        accessibilityEventObtain.setClassName(cVarJ.p());
        e.c(accessibilityEventObtain, this.l, i);
        accessibilityEventObtain.setPackageName(this.l.getContext().getPackageName());
        return accessibilityEventObtain;
    }

    private AccessibilityEvent s(int i) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i);
        this.l.onInitializeAccessibilityEvent(accessibilityEventObtain);
        return accessibilityEventObtain;
    }

    private c.g.k.c0.c t(int i) {
        c.g.k.c0.c cVarN = c.g.k.c0.c.N();
        cVarN.g0(true);
        cVarN.i0(true);
        cVarN.b0("android.view.View");
        Rect rect = f2359d;
        cVarN.W(rect);
        cVarN.X(rect);
        cVarN.q0(this.l);
        P(i, cVarN);
        if (cVarN.w() == null && cVarN.r() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        cVarN.m(this.h);
        if (this.h.equals(rect)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int iK = cVarN.k();
        if ((iK & 64) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((iK & 128) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        cVarN.o0(this.l.getContext().getPackageName());
        cVarN.z0(this.l, i);
        if (this.n == i) {
            cVarN.U(true);
            cVarN.a(128);
        } else {
            cVarN.U(false);
            cVarN.a(64);
        }
        boolean z = this.o == i;
        if (z) {
            cVarN.a(2);
        } else if (cVarN.F()) {
            cVarN.a(1);
        }
        cVarN.j0(z);
        this.l.getLocationOnScreen(this.j);
        cVarN.n(this.g);
        if (this.g.equals(rect)) {
            cVarN.m(this.g);
            if (cVarN.f2299c != -1) {
                c.g.k.c0.c cVarN2 = c.g.k.c0.c.N();
                for (int i2 = cVarN.f2299c; i2 != -1; i2 = cVarN2.f2299c) {
                    cVarN2.r0(this.l, -1);
                    cVarN2.W(f2359d);
                    P(i2, cVarN2);
                    cVarN2.m(this.h);
                    Rect rect2 = this.g;
                    Rect rect3 = this.h;
                    rect2.offset(rect3.left, rect3.top);
                }
                cVarN2.R();
            }
            this.g.offset(this.j[0] - this.l.getScrollX(), this.j[1] - this.l.getScrollY());
        }
        if (this.l.getLocalVisibleRect(this.i)) {
            this.i.offset(this.j[0] - this.l.getScrollX(), this.j[1] - this.l.getScrollY());
            if (this.g.intersect(this.i)) {
                cVarN.X(this.g);
                if (G(this.g)) {
                    cVarN.B0(true);
                }
            }
        }
        return cVarN;
    }

    private c.g.k.c0.c u() {
        c.g.k.c0.c cVarO = c.g.k.c0.c.O(this.l);
        t.X(this.l, cVarO);
        ArrayList arrayList = new ArrayList();
        C(arrayList);
        if (cVarO.o() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            cVarO.d(this.l, ((Integer) arrayList.get(i)).intValue());
        }
        return cVarO;
    }

    private h<c.g.k.c0.c> y() {
        ArrayList arrayList = new ArrayList();
        C(arrayList);
        h<c.g.k.c0.c> hVar = new h<>();
        for (int i = 0; i < arrayList.size(); i++) {
            hVar.i(i, t(i));
        }
        return hVar;
    }

    private void z(int i, Rect rect) {
        J(i).m(rect);
    }

    public final int A() {
        return this.o;
    }

    protected abstract int B(float f2, float f3);

    protected abstract void C(List<Integer> list);

    public final void E(int i) {
        F(i, 0);
    }

    public final void F(int i, int i2) {
        ViewParent parent;
        if (i == Integer.MIN_VALUE || !this.k.isEnabled() || (parent = this.l.getParent()) == null) {
            return;
        }
        AccessibilityEvent accessibilityEventQ = q(i, 2048);
        c.g.k.c0.b.b(accessibilityEventQ, i2);
        w.h(parent, this.l, accessibilityEventQ);
    }

    c.g.k.c0.c J(int i) {
        return i == -1 ? u() : t(i);
    }

    public final void K(boolean z, int i, Rect rect) {
        int i2 = this.o;
        if (i2 != Integer.MIN_VALUE) {
            o(i2);
        }
        if (z) {
            I(i, rect);
        }
    }

    protected abstract boolean L(int i, int i2, Bundle bundle);

    protected void M(AccessibilityEvent accessibilityEvent) {
    }

    protected void N(int i, AccessibilityEvent accessibilityEvent) {
    }

    protected void O(c.g.k.c0.c cVar) {
    }

    protected abstract void P(int i, c.g.k.c0.c cVar);

    protected void Q(int i, boolean z) {
    }

    boolean R(int i, int i2, Bundle bundle) {
        return i != -1 ? S(i, i2, bundle) : T(i2, bundle);
    }

    public final boolean V(int i) {
        int i2;
        if ((!this.l.isFocused() && !this.l.requestFocus()) || (i2 = this.o) == i) {
            return false;
        }
        if (i2 != Integer.MIN_VALUE) {
            o(i2);
        }
        this.o = i;
        Q(i, true);
        W(i, 8);
        return true;
    }

    public final boolean W(int i, int i2) {
        ViewParent parent;
        if (i == Integer.MIN_VALUE || !this.k.isEnabled() || (parent = this.l.getParent()) == null) {
            return false;
        }
        return w.h(parent, this.l, q(i, i2));
    }

    @Override // c.g.k.a
    public d b(View view) {
        if (this.m == null) {
            this.m = new c();
        }
        return this.m;
    }

    @Override // c.g.k.a
    public void f(View view, AccessibilityEvent accessibilityEvent) {
        super.f(view, accessibilityEvent);
        M(accessibilityEvent);
    }

    @Override // c.g.k.a
    public void g(View view, c.g.k.c0.c cVar) {
        super.g(view, cVar);
        O(cVar);
    }

    public final boolean o(int i) {
        if (this.o != i) {
            return false;
        }
        this.o = Integer.MIN_VALUE;
        Q(i, false);
        W(i, 8);
        return true;
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
        int i = 0;
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
                    while (i < repeatCount && I(iH, null)) {
                        i++;
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
}
