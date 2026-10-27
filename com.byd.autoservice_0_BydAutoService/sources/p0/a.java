package p0;

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
import l0.t;
import l0.w;
import m0.d;
import m0.e;
import p0.b;
import s.h;

/* JADX INFO: compiled from: ExploreByTouchHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends l0.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Rect f4469d = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b.a<m0.c> f4470e = new C0047a();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final b.InterfaceC0048b<h<m0.c>, m0.c> f4471f = new b();

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final AccessibilityManager f4476k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final View f4477l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public c f4478m;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Rect f4472g = new Rect();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Rect f4473h = new Rect();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Rect f4474i = new Rect();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int[] f4475j = new int[2];

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f4479n = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f4480o = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f4481p = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: p0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    public static class C0047a implements b.a<m0.c> {
        @Override // p0.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(m0.c cVar, Rect rect) {
            cVar.m(rect);
        }
    }

    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    public static class b implements b.InterfaceC0048b<h<m0.c>, m0.c> {
        @Override // p0.b.InterfaceC0048b
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public m0.c b(h<m0.c> hVar, int i3) {
            return hVar.m(i3);
        }

        @Override // p0.b.InterfaceC0048b
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public int a(h<m0.c> hVar) {
            return hVar.l();
        }
    }

    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    public class c extends d {
        public c() {
        }

        @Override // m0.d
        public m0.c a(int i3) {
            return m0.c.P(a.this.J(i3));
        }

        @Override // m0.d
        public m0.c c(int i3) {
            int i4 = i3 == 2 ? a.this.f4479n : a.this.f4480o;
            if (i4 == Integer.MIN_VALUE) {
                return null;
            }
            return a(i4);
        }

        @Override // m0.d
        public boolean e(int i3, int i4, Bundle bundle) {
            return a.this.R(i3, i4, bundle);
        }
    }

    public a(View view) {
        if (view == null) {
            throw new IllegalArgumentException("View may not be null");
        }
        this.f4477l = view;
        this.f4476k = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        view.setFocusable(true);
        if (t.x(view) == 0) {
            t.t0(view, 1);
        }
    }

    public static Rect D(View view, int i3, Rect rect) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (i3 == 17) {
            rect.set(width, 0, width, height);
        } else if (i3 == 33) {
            rect.set(0, height, width, height);
        } else if (i3 == 66) {
            rect.set(-1, 0, -1, height);
        } else {
            if (i3 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            rect.set(0, -1, width, -1);
        }
        return rect;
    }

    public static int H(int i3) {
        if (i3 == 19) {
            return 33;
        }
        if (i3 != 21) {
            return i3 != 22 ? 130 : 66;
        }
        return 17;
    }

    public final int A() {
        return this.f4480o;
    }

    public abstract int B(float f3, float f4);

    public abstract void C(List<Integer> list);

    public final void E(int i3) {
        F(i3, 0);
    }

    public final void F(int i3, int i4) {
        ViewParent parent;
        if (i3 == Integer.MIN_VALUE || !this.f4476k.isEnabled() || (parent = this.f4477l.getParent()) == null) {
            return;
        }
        AccessibilityEvent accessibilityEventQ = q(i3, 2048);
        m0.b.b(accessibilityEventQ, i4);
        w.h(parent, this.f4477l, accessibilityEventQ);
    }

    public final boolean G(Rect rect) {
        if (rect == null || rect.isEmpty() || this.f4477l.getWindowVisibility() != 0) {
            return false;
        }
        Object parent = this.f4477l.getParent();
        while (parent instanceof View) {
            View view = (View) parent;
            if (view.getAlpha() <= 0.0f || view.getVisibility() != 0) {
                return false;
            }
            parent = view.getParent();
        }
        return parent != null;
    }

    public final boolean I(int i3, Rect rect) {
        m0.c cVar;
        h<m0.c> hVarY = y();
        int i4 = this.f4480o;
        m0.c cVarE = i4 == Integer.MIN_VALUE ? null : hVarY.e(i4);
        if (i3 == 1 || i3 == 2) {
            cVar = (m0.c) p0.b.d(hVarY, f4471f, f4470e, cVarE, i3, t.z(this.f4477l) == 1, false);
        } else {
            if (i3 != 17 && i3 != 33 && i3 != 66 && i3 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            Rect rect2 = new Rect();
            int i5 = this.f4480o;
            if (i5 != Integer.MIN_VALUE) {
                z(i5, rect2);
            } else if (rect != null) {
                rect2.set(rect);
            } else {
                D(this.f4477l, i3, rect2);
            }
            cVar = (m0.c) p0.b.c(hVarY, f4471f, f4470e, cVarE, rect2, i3);
        }
        return V(cVar != null ? hVarY.i(hVarY.h(cVar)) : Integer.MIN_VALUE);
    }

    public m0.c J(int i3) {
        return i3 == -1 ? u() : t(i3);
    }

    public final void K(boolean z3, int i3, Rect rect) {
        int i4 = this.f4480o;
        if (i4 != Integer.MIN_VALUE) {
            o(i4);
        }
        if (z3) {
            I(i3, rect);
        }
    }

    public abstract boolean L(int i3, int i4, Bundle bundle);

    public void M(AccessibilityEvent accessibilityEvent) {
    }

    public void N(int i3, AccessibilityEvent accessibilityEvent) {
    }

    public void O(m0.c cVar) {
    }

    public abstract void P(int i3, m0.c cVar);

    public void Q(int i3, boolean z3) {
    }

    public boolean R(int i3, int i4, Bundle bundle) {
        return i3 != -1 ? S(i3, i4, bundle) : T(i4, bundle);
    }

    public final boolean S(int i3, int i4, Bundle bundle) {
        return i4 != 1 ? i4 != 2 ? i4 != 64 ? i4 != 128 ? L(i3, i4, bundle) : n(i3) : U(i3) : o(i3) : V(i3);
    }

    public final boolean T(int i3, Bundle bundle) {
        return t.Z(this.f4477l, i3, bundle);
    }

    public final boolean U(int i3) {
        int i4;
        if (!this.f4476k.isEnabled() || !this.f4476k.isTouchExplorationEnabled() || (i4 = this.f4479n) == i3) {
            return false;
        }
        if (i4 != Integer.MIN_VALUE) {
            n(i4);
        }
        this.f4479n = i3;
        this.f4477l.invalidate();
        W(i3, 32768);
        return true;
    }

    public final boolean V(int i3) {
        int i4;
        if ((!this.f4477l.isFocused() && !this.f4477l.requestFocus()) || (i4 = this.f4480o) == i3) {
            return false;
        }
        if (i4 != Integer.MIN_VALUE) {
            o(i4);
        }
        this.f4480o = i3;
        Q(i3, true);
        W(i3, 8);
        return true;
    }

    public final boolean W(int i3, int i4) {
        ViewParent parent;
        if (i3 == Integer.MIN_VALUE || !this.f4476k.isEnabled() || (parent = this.f4477l.getParent()) == null) {
            return false;
        }
        return w.h(parent, this.f4477l, q(i3, i4));
    }

    public final void X(int i3) {
        int i4 = this.f4481p;
        if (i4 == i3) {
            return;
        }
        this.f4481p = i3;
        W(i3, 128);
        W(i4, 256);
    }

    @Override // l0.a
    public d b(View view) {
        if (this.f4478m == null) {
            this.f4478m = new c();
        }
        return this.f4478m;
    }

    @Override // l0.a
    public void f(View view, AccessibilityEvent accessibilityEvent) {
        super.f(view, accessibilityEvent);
        M(accessibilityEvent);
    }

    @Override // l0.a
    public void g(View view, m0.c cVar) {
        super.g(view, cVar);
        O(cVar);
    }

    public final boolean n(int i3) {
        if (this.f4479n != i3) {
            return false;
        }
        this.f4479n = Integer.MIN_VALUE;
        this.f4477l.invalidate();
        W(i3, 65536);
        return true;
    }

    public final boolean o(int i3) {
        if (this.f4480o != i3) {
            return false;
        }
        this.f4480o = Integer.MIN_VALUE;
        Q(i3, false);
        W(i3, 8);
        return true;
    }

    public final boolean p() {
        int i3 = this.f4480o;
        return i3 != Integer.MIN_VALUE && L(i3, 16, null);
    }

    public final AccessibilityEvent q(int i3, int i4) {
        return i3 != -1 ? r(i3, i4) : s(i4);
    }

    public final AccessibilityEvent r(int i3, int i4) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i4);
        m0.c cVarJ = J(i3);
        accessibilityEventObtain.getText().add(cVarJ.w());
        accessibilityEventObtain.setContentDescription(cVarJ.r());
        accessibilityEventObtain.setScrollable(cVarJ.J());
        accessibilityEventObtain.setPassword(cVarJ.I());
        accessibilityEventObtain.setEnabled(cVarJ.E());
        accessibilityEventObtain.setChecked(cVarJ.C());
        N(i3, accessibilityEventObtain);
        if (accessibilityEventObtain.getText().isEmpty() && accessibilityEventObtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        accessibilityEventObtain.setClassName(cVarJ.p());
        e.c(accessibilityEventObtain, this.f4477l, i3);
        accessibilityEventObtain.setPackageName(this.f4477l.getContext().getPackageName());
        return accessibilityEventObtain;
    }

    public final AccessibilityEvent s(int i3) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i3);
        this.f4477l.onInitializeAccessibilityEvent(accessibilityEventObtain);
        return accessibilityEventObtain;
    }

    public final m0.c t(int i3) {
        m0.c cVarN = m0.c.N();
        cVarN.g0(true);
        cVarN.i0(true);
        cVarN.b0("android.view.View");
        Rect rect = f4469d;
        cVarN.W(rect);
        cVarN.X(rect);
        cVarN.q0(this.f4477l);
        P(i3, cVarN);
        if (cVarN.w() == null && cVarN.r() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        cVarN.m(this.f4473h);
        if (this.f4473h.equals(rect)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int iK = cVarN.k();
        if ((iK & 64) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((iK & 128) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        cVarN.o0(this.f4477l.getContext().getPackageName());
        cVarN.z0(this.f4477l, i3);
        if (this.f4479n == i3) {
            cVarN.U(true);
            cVarN.a(128);
        } else {
            cVarN.U(false);
            cVarN.a(64);
        }
        boolean z3 = this.f4480o == i3;
        if (z3) {
            cVarN.a(2);
        } else if (cVarN.F()) {
            cVarN.a(1);
        }
        cVarN.j0(z3);
        this.f4477l.getLocationOnScreen(this.f4475j);
        cVarN.n(this.f4472g);
        if (this.f4472g.equals(rect)) {
            cVarN.m(this.f4472g);
            if (cVarN.f3800c != -1) {
                m0.c cVarN2 = m0.c.N();
                for (int i4 = cVarN.f3800c; i4 != -1; i4 = cVarN2.f3800c) {
                    cVarN2.r0(this.f4477l, -1);
                    cVarN2.W(f4469d);
                    P(i4, cVarN2);
                    cVarN2.m(this.f4473h);
                    Rect rect2 = this.f4472g;
                    Rect rect3 = this.f4473h;
                    rect2.offset(rect3.left, rect3.top);
                }
                cVarN2.R();
            }
            this.f4472g.offset(this.f4475j[0] - this.f4477l.getScrollX(), this.f4475j[1] - this.f4477l.getScrollY());
        }
        if (this.f4477l.getLocalVisibleRect(this.f4474i)) {
            this.f4474i.offset(this.f4475j[0] - this.f4477l.getScrollX(), this.f4475j[1] - this.f4477l.getScrollY());
            if (this.f4472g.intersect(this.f4474i)) {
                cVarN.X(this.f4472g);
                if (G(this.f4472g)) {
                    cVarN.B0(true);
                }
            }
        }
        return cVarN;
    }

    public final m0.c u() {
        m0.c cVarO = m0.c.O(this.f4477l);
        t.X(this.f4477l, cVarO);
        ArrayList arrayList = new ArrayList();
        C(arrayList);
        if (cVarO.o() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            cVarO.d(this.f4477l, ((Integer) arrayList.get(i3)).intValue());
        }
        return cVarO;
    }

    public final boolean v(MotionEvent motionEvent) {
        if (!this.f4476k.isEnabled() || !this.f4476k.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            int iB = B(motionEvent.getX(), motionEvent.getY());
            X(iB);
            return iB != Integer.MIN_VALUE;
        }
        if (action != 10 || this.f4481p == Integer.MIN_VALUE) {
            return false;
        }
        X(Integer.MIN_VALUE);
        return true;
    }

    public final boolean w(KeyEvent keyEvent) {
        int i3 = 0;
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
                    boolean z3 = false;
                    while (i3 < repeatCount && I(iH, null)) {
                        i3++;
                        z3 = true;
                    }
                    return z3;
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
        return this.f4479n;
    }

    public final h<m0.c> y() {
        ArrayList arrayList = new ArrayList();
        C(arrayList);
        h<m0.c> hVar = new h<>();
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            hVar.j(i3, t(i3));
        }
        return hVar;
    }

    public final void z(int i3, Rect rect) {
        J(i3).m(rect);
    }
}
