package m0;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import i0.u;
import j0.d;
import j0.e;
import java.util.ArrayList;
import java.util.List;
import m0.b;
import n.h;

/* JADX INFO: compiled from: ExploreByTouchHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends i0.a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private static final Rect f8025n = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private static final b.a<j0.c> f8026o = new C0120a();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private static final b.InterfaceC0121b<h<j0.c>, j0.c> f8027p = new b();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final AccessibilityManager f8032h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final View f8033i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private c f8034j;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Rect f8028d = new Rect();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Rect f8029e = new Rect();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Rect f8030f = new Rect();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final int[] f8031g = new int[2];

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    int f8035k = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    int f8036l = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f8037m = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: m0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    class C0120a implements b.a<j0.c> {
        C0120a() {
        }

        @Override // m0.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void a(j0.c cVar, Rect rect) {
            cVar.i(rect);
        }
    }

    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    class b implements b.InterfaceC0121b<h<j0.c>, j0.c> {
        b() {
        }

        @Override // m0.b.InterfaceC0121b
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public j0.c b(h<j0.c> hVar, int i7) {
            return hVar.l(i7);
        }

        @Override // m0.b.InterfaceC0121b
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public int a(h<j0.c> hVar) {
            return hVar.k();
        }
    }

    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    private class c extends d {
        c() {
        }

        @Override // j0.d
        public j0.c b(int i7) {
            return j0.c.G(a.this.H(i7));
        }

        @Override // j0.d
        public j0.c d(int i7) {
            int i8 = i7 == 2 ? a.this.f8035k : a.this.f8036l;
            if (i8 == Integer.MIN_VALUE) {
                return null;
            }
            return b(i8);
        }

        @Override // j0.d
        public boolean f(int i7, int i8, Bundle bundle) {
            return a.this.P(i7, i8, bundle);
        }
    }

    public a(View view) {
        if (view == null) {
            throw new IllegalArgumentException("View may not be null");
        }
        this.f8033i = view;
        this.f8032h = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        view.setFocusable(true);
        if (u.w(view) == 0) {
            u.t0(view, 1);
        }
    }

    private static Rect D(View view, int i7, Rect rect) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (i7 == 17) {
            rect.set(width, 0, width, height);
        } else if (i7 == 33) {
            rect.set(0, height, width, height);
        } else if (i7 == 66) {
            rect.set(-1, 0, -1, height);
        } else {
            if (i7 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            rect.set(0, -1, width, -1);
        }
        return rect;
    }

    private boolean E(Rect rect) {
        if (rect == null || rect.isEmpty() || this.f8033i.getWindowVisibility() != 0) {
            return false;
        }
        Object parent = this.f8033i.getParent();
        while (parent instanceof View) {
            View view = (View) parent;
            if (view.getAlpha() <= 0.0f || view.getVisibility() != 0) {
                return false;
            }
            parent = view.getParent();
        }
        return parent != null;
    }

    private static int F(int i7) {
        if (i7 == 19) {
            return 33;
        }
        if (i7 != 21) {
            return i7 != 22 ? 130 : 66;
        }
        return 17;
    }

    private boolean G(int i7, Rect rect) {
        j0.c cVar;
        h<j0.c> hVarY = y();
        int i8 = this.f8036l;
        j0.c cVarE = i8 == Integer.MIN_VALUE ? null : hVarY.e(i8);
        if (i7 == 1 || i7 == 2) {
            cVar = (j0.c) m0.b.d(hVarY, f8027p, f8026o, cVarE, i7, u.y(this.f8033i) == 1, false);
        } else {
            if (i7 != 17 && i7 != 33 && i7 != 66 && i7 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            Rect rect2 = new Rect();
            int i9 = this.f8036l;
            if (i9 != Integer.MIN_VALUE) {
                z(i9, rect2);
            } else if (rect != null) {
                rect2.set(rect);
            } else {
                D(this.f8033i, i7, rect2);
            }
            cVar = (j0.c) m0.b.c(hVarY, f8027p, f8026o, cVarE, rect2, i7);
        }
        return T(cVar != null ? hVarY.h(hVarY.g(cVar)) : Integer.MIN_VALUE);
    }

    private boolean Q(int i7, int i8, Bundle bundle) {
        return i8 != 1 ? i8 != 2 ? i8 != 64 ? i8 != 128 ? J(i7, i8, bundle) : n(i7) : S(i7) : o(i7) : T(i7);
    }

    private boolean R(int i7, Bundle bundle) {
        return u.Z(this.f8033i, i7, bundle);
    }

    private boolean S(int i7) {
        int i8;
        if (!this.f8032h.isEnabled() || !this.f8032h.isTouchExplorationEnabled() || (i8 = this.f8035k) == i7) {
            return false;
        }
        if (i8 != Integer.MIN_VALUE) {
            n(i8);
        }
        this.f8035k = i7;
        this.f8033i.invalidate();
        U(i7, 32768);
        return true;
    }

    private void V(int i7) {
        int i8 = this.f8037m;
        if (i8 == i7) {
            return;
        }
        this.f8037m = i7;
        U(i7, 128);
        U(i8, 256);
    }

    private boolean n(int i7) {
        if (this.f8035k != i7) {
            return false;
        }
        this.f8035k = Integer.MIN_VALUE;
        this.f8033i.invalidate();
        U(i7, 65536);
        return true;
    }

    private boolean p() {
        int i7 = this.f8036l;
        return i7 != Integer.MIN_VALUE && J(i7, 16, null);
    }

    private AccessibilityEvent q(int i7, int i8) {
        return i7 != -1 ? r(i7, i8) : s(i8);
    }

    private AccessibilityEvent r(int i7, int i8) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i8);
        j0.c cVarH = H(i7);
        accessibilityEventObtain.getText().add(cVarH.q());
        accessibilityEventObtain.setContentDescription(cVarH.n());
        accessibilityEventObtain.setScrollable(cVarH.B());
        accessibilityEventObtain.setPassword(cVarH.A());
        accessibilityEventObtain.setEnabled(cVarH.w());
        accessibilityEventObtain.setChecked(cVarH.u());
        L(i7, accessibilityEventObtain);
        if (accessibilityEventObtain.getText().isEmpty() && accessibilityEventObtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        accessibilityEventObtain.setClassName(cVarH.l());
        e.c(accessibilityEventObtain, this.f8033i, i7);
        accessibilityEventObtain.setPackageName(this.f8033i.getContext().getPackageName());
        return accessibilityEventObtain;
    }

    private AccessibilityEvent s(int i7) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i7);
        this.f8033i.onInitializeAccessibilityEvent(accessibilityEventObtain);
        return accessibilityEventObtain;
    }

    private j0.c t(int i7) {
        j0.c cVarE = j0.c.E();
        cVarE.U(true);
        cVarE.W(true);
        cVarE.P("android.view.View");
        Rect rect = f8025n;
        cVarE.L(rect);
        cVarE.M(rect);
        cVarE.d0(this.f8033i);
        N(i7, cVarE);
        if (cVarE.q() == null && cVarE.n() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        cVarE.i(this.f8029e);
        if (this.f8029e.equals(rect)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int iH = cVarE.h();
        if ((iH & 64) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((iH & 128) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        cVarE.b0(this.f8033i.getContext().getPackageName());
        cVarE.j0(this.f8033i, i7);
        if (this.f8035k == i7) {
            cVarE.K(true);
            cVarE.a(128);
        } else {
            cVarE.K(false);
            cVarE.a(64);
        }
        boolean z6 = this.f8036l == i7;
        if (z6) {
            cVarE.a(2);
        } else if (cVarE.x()) {
            cVarE.a(1);
        }
        cVarE.X(z6);
        this.f8033i.getLocationOnScreen(this.f8031g);
        cVarE.j(this.f8028d);
        if (this.f8028d.equals(rect)) {
            cVarE.i(this.f8028d);
            if (cVarE.f7111b != -1) {
                j0.c cVarE2 = j0.c.E();
                for (int i8 = cVarE.f7111b; i8 != -1; i8 = cVarE2.f7111b) {
                    cVarE2.e0(this.f8033i, -1);
                    cVarE2.L(f8025n);
                    N(i8, cVarE2);
                    cVarE2.i(this.f8029e);
                    Rect rect2 = this.f8028d;
                    Rect rect3 = this.f8029e;
                    rect2.offset(rect3.left, rect3.top);
                }
                cVarE2.I();
            }
            this.f8028d.offset(this.f8031g[0] - this.f8033i.getScrollX(), this.f8031g[1] - this.f8033i.getScrollY());
        }
        if (this.f8033i.getLocalVisibleRect(this.f8030f)) {
            this.f8030f.offset(this.f8031g[0] - this.f8033i.getScrollX(), this.f8031g[1] - this.f8033i.getScrollY());
            if (this.f8028d.intersect(this.f8030f)) {
                cVarE.M(this.f8028d);
                if (E(this.f8028d)) {
                    cVarE.n0(true);
                }
            }
        }
        return cVarE;
    }

    private j0.c u() {
        j0.c cVarF = j0.c.F(this.f8033i);
        u.X(this.f8033i, cVarF);
        ArrayList arrayList = new ArrayList();
        C(arrayList);
        if (cVarF.k() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            cVarF.c(this.f8033i, ((Integer) arrayList.get(i7)).intValue());
        }
        return cVarF;
    }

    private h<j0.c> y() {
        ArrayList arrayList = new ArrayList();
        C(arrayList);
        h<j0.c> hVar = new h<>();
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            hVar.i(arrayList.get(i7).intValue(), t(arrayList.get(i7).intValue()));
        }
        return hVar;
    }

    private void z(int i7, Rect rect) {
        H(i7).i(rect);
    }

    public final int A() {
        return this.f8036l;
    }

    protected abstract int B(float f7, float f8);

    protected abstract void C(List<Integer> list);

    j0.c H(int i7) {
        return i7 == -1 ? u() : t(i7);
    }

    public final void I(boolean z6, int i7, Rect rect) {
        int i8 = this.f8036l;
        if (i8 != Integer.MIN_VALUE) {
            o(i8);
        }
        if (z6) {
            G(i7, rect);
        }
    }

    protected abstract boolean J(int i7, int i8, Bundle bundle);

    protected void K(AccessibilityEvent accessibilityEvent) {
    }

    protected void L(int i7, AccessibilityEvent accessibilityEvent) {
    }

    protected void M(j0.c cVar) {
    }

    protected abstract void N(int i7, j0.c cVar);

    protected void O(int i7, boolean z6) {
    }

    boolean P(int i7, int i8, Bundle bundle) {
        return i7 != -1 ? Q(i7, i8, bundle) : R(i8, bundle);
    }

    public final boolean T(int i7) {
        int i8;
        if ((!this.f8033i.isFocused() && !this.f8033i.requestFocus()) || (i8 = this.f8036l) == i7) {
            return false;
        }
        if (i8 != Integer.MIN_VALUE) {
            o(i8);
        }
        if (i7 == Integer.MIN_VALUE) {
            return false;
        }
        this.f8036l = i7;
        O(i7, true);
        U(i7, 8);
        return true;
    }

    public final boolean U(int i7, int i8) {
        ViewParent parent;
        if (i7 == Integer.MIN_VALUE || !this.f8032h.isEnabled() || (parent = this.f8033i.getParent()) == null) {
            return false;
        }
        return parent.requestSendAccessibilityEvent(this.f8033i, q(i7, i8));
    }

    @Override // i0.a
    public d b(View view) {
        if (this.f8034j == null) {
            this.f8034j = new c();
        }
        return this.f8034j;
    }

    @Override // i0.a
    public void f(View view, AccessibilityEvent accessibilityEvent) {
        super.f(view, accessibilityEvent);
        K(accessibilityEvent);
    }

    @Override // i0.a
    public void g(View view, j0.c cVar) {
        super.g(view, cVar);
        M(cVar);
    }

    public final boolean o(int i7) {
        if (this.f8036l != i7) {
            return false;
        }
        this.f8036l = Integer.MIN_VALUE;
        O(i7, false);
        U(i7, 8);
        return true;
    }

    public final boolean v(MotionEvent motionEvent) {
        if (!this.f8032h.isEnabled() || !this.f8032h.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            int iB = B(motionEvent.getX(), motionEvent.getY());
            V(iB);
            return iB != Integer.MIN_VALUE;
        }
        if (action != 10 || this.f8037m == Integer.MIN_VALUE) {
            return false;
        }
        V(Integer.MIN_VALUE);
        return true;
    }

    public final boolean w(KeyEvent keyEvent) {
        int i7 = 0;
        if (keyEvent.getAction() == 1) {
            return false;
        }
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 61) {
            if (keyEvent.hasNoModifiers()) {
                return G(2, null);
            }
            if (keyEvent.hasModifiers(1)) {
                return G(1, null);
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
                    int iF = F(keyCode);
                    int repeatCount = keyEvent.getRepeatCount() + 1;
                    boolean z6 = false;
                    while (i7 < repeatCount && G(iF, null)) {
                        i7++;
                        z6 = true;
                    }
                    return z6;
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
        return this.f8035k;
    }
}
