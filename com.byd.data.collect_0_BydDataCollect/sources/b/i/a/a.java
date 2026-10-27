package b.i.a;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import b.d.h;
import b.g.k.c0.d;
import b.g.k.c0.e;
import b.g.k.t;
import b.i.a.b;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ExploreByTouchHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends b.g.k.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final Rect f2770d = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final b.a<b.g.k.c0.c> f2771e = new C0050a();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final b.InterfaceC0051b<h<b.g.k.c0.c>, b.g.k.c0.c> f2772f = new b();
    private final AccessibilityManager k;
    private final View l;
    private c m;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Rect f2773g = new Rect();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Rect f2774h = new Rect();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final Rect f2775i = new Rect();
    private final int[] j = new int[2];
    int n = Integer.MIN_VALUE;
    int o = Integer.MIN_VALUE;
    private int p = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: b.i.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    class C0050a implements b.a<b.g.k.c0.c> {
        C0050a() {
        }

        @Override // b.i.a.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(b.g.k.c0.c cVar, Rect rect) {
            cVar.m(rect);
        }
    }

    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    class b implements b.InterfaceC0051b<h<b.g.k.c0.c>, b.g.k.c0.c> {
        b() {
        }

        @Override // b.i.a.b.InterfaceC0051b
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public b.g.k.c0.c b(h<b.g.k.c0.c> hVar, int i2) {
            return hVar.p(i2);
        }

        @Override // b.i.a.b.InterfaceC0051b
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public int a(h<b.g.k.c0.c> hVar) {
            return hVar.o();
        }
    }

    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    private class c extends d {
        c() {
        }

        @Override // b.g.k.c0.d
        public b.g.k.c0.c a(int i2) {
            return b.g.k.c0.c.P(a.this.J(i2));
        }

        @Override // b.g.k.c0.d
        public b.g.k.c0.c c(int i2) {
            int i3 = i2 == 2 ? a.this.n : a.this.o;
            if (i3 == Integer.MIN_VALUE) {
                return null;
            }
            return a(i3);
        }

        @Override // b.g.k.c0.d
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
            t.t0(view, 1);
        }
    }

    private static Rect D(View view, int i2, Rect rect) {
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

    private static int H(int i2) {
        if (i2 == 19) {
            return 33;
        }
        if (i2 != 21) {
            return i2 != 22 ? 130 : 66;
        }
        return 17;
    }

    private boolean I(int i2, Rect rect) {
        b.g.k.c0.c cVar;
        h<b.g.k.c0.c> hVarY = y();
        int i3 = this.o;
        b.g.k.c0.c cVarG = i3 == Integer.MIN_VALUE ? null : hVarY.g(i3);
        if (i2 == 1 || i2 == 2) {
            cVar = (b.g.k.c0.c) b.i.a.b.d(hVarY, f2772f, f2771e, cVarG, i2, t.z(this.l) == 1, false);
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
            cVar = (b.g.k.c0.c) b.i.a.b.c(hVarY, f2772f, f2771e, cVarG, rect2, i2);
        }
        return V(cVar != null ? hVarY.k(hVarY.i(cVar)) : Integer.MIN_VALUE);
    }

    private boolean S(int i2, int i3, Bundle bundle) {
        return i3 != 1 ? i3 != 2 ? i3 != 64 ? i3 != 128 ? L(i2, i3, bundle) : n(i2) : U(i2) : o(i2) : V(i2);
    }

    private boolean T(int i2, Bundle bundle) {
        return t.Z(this.l, i2, bundle);
    }

    private boolean U(int i2) {
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

    private void X(int i2) {
        int i3 = this.p;
        if (i3 == i2) {
            return;
        }
        this.p = i2;
        W(i2, 128);
        W(i3, 256);
    }

    private boolean n(int i2) {
        if (this.n != i2) {
            return false;
        }
        this.n = Integer.MIN_VALUE;
        this.l.invalidate();
        W(i2, 65536);
        return true;
    }

    private boolean p() {
        int i2 = this.o;
        return i2 != Integer.MIN_VALUE && L(i2, 16, null);
    }

    private AccessibilityEvent q(int i2, int i3) {
        return i2 != -1 ? r(i2, i3) : s(i3);
    }

    private AccessibilityEvent r(int i2, int i3) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i3);
        b.g.k.c0.c cVarJ = J(i2);
        accessibilityEventObtain.getText().add(cVarJ.w());
        accessibilityEventObtain.setContentDescription(cVarJ.r());
        accessibilityEventObtain.setScrollable(cVarJ.J());
        accessibilityEventObtain.setPassword(cVarJ.I());
        accessibilityEventObtain.setEnabled(cVarJ.E());
        accessibilityEventObtain.setChecked(cVarJ.C());
        N(i2, accessibilityEventObtain);
        if (accessibilityEventObtain.getText().isEmpty() && accessibilityEventObtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        accessibilityEventObtain.setClassName(cVarJ.p());
        e.c(accessibilityEventObtain, this.l, i2);
        accessibilityEventObtain.setPackageName(this.l.getContext().getPackageName());
        return accessibilityEventObtain;
    }

    private AccessibilityEvent s(int i2) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i2);
        this.l.onInitializeAccessibilityEvent(accessibilityEventObtain);
        return accessibilityEventObtain;
    }

    private b.g.k.c0.c t(int i2) {
        b.g.k.c0.c cVarN = b.g.k.c0.c.N();
        cVarN.g0(true);
        cVarN.i0(true);
        cVarN.b0("android.view.View");
        Rect rect = f2770d;
        cVarN.W(rect);
        cVarN.X(rect);
        cVarN.p0(this.l);
        P(i2, cVarN);
        if (cVarN.w() == null && cVarN.r() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        cVarN.m(this.f2774h);
        if (this.f2774h.equals(rect)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int iK = cVarN.k();
        if ((iK & 64) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((iK & 128) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        cVarN.n0(this.l.getContext().getPackageName());
        cVarN.y0(this.l, i2);
        if (this.n == i2) {
            cVarN.U(true);
            cVarN.a(128);
        } else {
            cVarN.U(false);
            cVarN.a(64);
        }
        boolean z = this.o == i2;
        if (z) {
            cVarN.a(2);
        } else if (cVarN.F()) {
            cVarN.a(1);
        }
        cVarN.j0(z);
        this.l.getLocationOnScreen(this.j);
        cVarN.n(this.f2773g);
        if (this.f2773g.equals(rect)) {
            cVarN.m(this.f2773g);
            if (cVarN.f2694c != -1) {
                b.g.k.c0.c cVarN2 = b.g.k.c0.c.N();
                for (int i3 = cVarN.f2694c; i3 != -1; i3 = cVarN2.f2694c) {
                    cVarN2.q0(this.l, -1);
                    cVarN2.W(f2770d);
                    P(i3, cVarN2);
                    cVarN2.m(this.f2774h);
                    Rect rect2 = this.f2773g;
                    Rect rect3 = this.f2774h;
                    rect2.offset(rect3.left, rect3.top);
                }
                cVarN2.R();
            }
            this.f2773g.offset(this.j[0] - this.l.getScrollX(), this.j[1] - this.l.getScrollY());
        }
        if (this.l.getLocalVisibleRect(this.f2775i)) {
            this.f2775i.offset(this.j[0] - this.l.getScrollX(), this.j[1] - this.l.getScrollY());
            if (this.f2773g.intersect(this.f2775i)) {
                cVarN.X(this.f2773g);
                if (G(this.f2773g)) {
                    cVarN.A0(true);
                }
            }
        }
        return cVarN;
    }

    private b.g.k.c0.c u() {
        b.g.k.c0.c cVarO = b.g.k.c0.c.O(this.l);
        t.X(this.l, cVarO);
        ArrayList arrayList = new ArrayList();
        C(arrayList);
        if (cVarO.o() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            cVarO.d(this.l, ((Integer) arrayList.get(i2)).intValue());
        }
        return cVarO;
    }

    private h<b.g.k.c0.c> y() {
        ArrayList arrayList = new ArrayList();
        C(arrayList);
        h<b.g.k.c0.c> hVar = new h<>();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            hVar.l(arrayList.get(i2).intValue(), t(arrayList.get(i2).intValue()));
        }
        return hVar;
    }

    private void z(int i2, Rect rect) {
        J(i2).m(rect);
    }

    public final int A() {
        return this.o;
    }

    protected abstract int B(float f2, float f3);

    protected abstract void C(List<Integer> list);

    public final void E(int i2) {
        F(i2, 0);
    }

    public final void F(int i2, int i3) {
        ViewParent parent;
        if (i2 == Integer.MIN_VALUE || !this.k.isEnabled() || (parent = this.l.getParent()) == null) {
            return;
        }
        AccessibilityEvent accessibilityEventQ = q(i2, 2048);
        b.g.k.c0.b.b(accessibilityEventQ, i3);
        parent.requestSendAccessibilityEvent(this.l, accessibilityEventQ);
    }

    b.g.k.c0.c J(int i2) {
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

    protected abstract boolean L(int i2, int i3, Bundle bundle);

    protected void M(AccessibilityEvent accessibilityEvent) {
    }

    protected void N(int i2, AccessibilityEvent accessibilityEvent) {
    }

    protected void O(b.g.k.c0.c cVar) {
    }

    protected abstract void P(int i2, b.g.k.c0.c cVar);

    protected void Q(int i2, boolean z) {
    }

    boolean R(int i2, int i3, Bundle bundle) {
        return i2 != -1 ? S(i2, i3, bundle) : T(i3, bundle);
    }

    public final boolean V(int i2) {
        int i3;
        if ((!this.l.isFocused() && !this.l.requestFocus()) || (i3 = this.o) == i2) {
            return false;
        }
        if (i3 != Integer.MIN_VALUE) {
            o(i3);
        }
        if (i2 == Integer.MIN_VALUE) {
            return false;
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
        return parent.requestSendAccessibilityEvent(this.l, q(i2, i3));
    }

    @Override // b.g.k.a
    public d b(View view) {
        if (this.m == null) {
            this.m = new c();
        }
        return this.m;
    }

    @Override // b.g.k.a
    public void f(View view, AccessibilityEvent accessibilityEvent) {
        super.f(view, accessibilityEvent);
        M(accessibilityEvent);
    }

    @Override // b.g.k.a
    public void g(View view, b.g.k.c0.c cVar) {
        super.g(view, cVar);
        O(cVar);
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
}
