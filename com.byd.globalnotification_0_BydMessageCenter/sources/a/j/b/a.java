package a.j.b;

import a.e.i;
import a.h.k.q;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.material.chip.Chip;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends a.h.k.a {
    public static final Rect n = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    public static final a.j.b.b<a.h.k.x.b> o = new C0021a();
    public static final a.j.b.c<i<a.h.k.x.b>, a.h.k.x.b> p = new b();
    public final AccessibilityManager h;
    public final View i;
    public c j;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f893d = new Rect();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Rect f894e = new Rect();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Rect f895f = new Rect();
    public final int[] g = new int[2];
    public int k = Integer.MIN_VALUE;
    public int l = Integer.MIN_VALUE;
    public int m = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: a.j.b.a$a, reason: collision with other inner class name */
    public static class C0021a implements a.j.b.b<a.h.k.x.b> {
        public void a(Object obj, Rect rect) {
            ((a.h.k.x.b) obj).f856a.getBoundsInParent(rect);
        }
    }

    public static class b implements a.j.b.c<i<a.h.k.x.b>, a.h.k.x.b> {
    }

    public class c extends a.h.k.x.c {
        public c() {
        }

        @Override // a.h.k.x.c
        public a.h.k.x.b a(int i) {
            return new a.h.k.x.b(AccessibilityNodeInfo.obtain(a.this.o(i).f856a));
        }

        @Override // a.h.k.x.c
        public a.h.k.x.b b(int i) {
            int i2 = i == 2 ? a.this.k : a.this.l;
            if (i2 == Integer.MIN_VALUE) {
                return null;
            }
            return new a.h.k.x.b(AccessibilityNodeInfo.obtain(a.this.o(i2).f856a));
        }

        @Override // a.h.k.x.c
        public boolean c(int i, int i2, Bundle bundle) {
            int i3;
            a aVar = a.this;
            if (i == -1) {
                return q.L(aVar.i, i2, bundle);
            }
            boolean z = true;
            if (i2 == 1) {
                return aVar.r(i);
            }
            if (i2 == 2) {
                return aVar.k(i);
            }
            if (i2 != 64) {
                return i2 != 128 ? aVar.p(i, i2, bundle) : aVar.j(i);
            }
            if (aVar.h.isEnabled() && aVar.h.isTouchExplorationEnabled() && (i3 = aVar.k) != i) {
                if (i3 != Integer.MIN_VALUE) {
                    aVar.j(i3);
                }
                aVar.k = i;
                aVar.i.invalidate();
                aVar.s(i, 32768);
            } else {
                z = false;
            }
            return z;
        }
    }

    public a(View view) {
        if (view == null) {
            throw new IllegalArgumentException("View may not be null");
        }
        this.i = view;
        this.h = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        view.setFocusable(true);
        if (q.n(view) == 0) {
            view.setImportantForAccessibility(1);
        }
    }

    @Override // a.h.k.a
    public a.h.k.x.c b(View view) {
        if (this.j == null) {
            this.j = new c();
        }
        return this.j;
    }

    @Override // a.h.k.a
    public void c(View view, AccessibilityEvent accessibilityEvent) {
        this.f807a.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // a.h.k.a
    public void d(View view, a.h.k.x.b bVar) {
        this.f807a.onInitializeAccessibilityNodeInfo(view, bVar.f856a);
        Chip.b bVar2 = (Chip.b) this;
        bVar.f856a.setCheckable(Chip.this.g());
        bVar.f856a.setClickable(Chip.this.isClickable());
        if (Chip.this.g() || Chip.this.isClickable()) {
            bVar.f856a.setClassName(Chip.this.g() ? "android.widget.CompoundButton" : "android.widget.Button");
        } else {
            bVar.f856a.setClassName("android.view.View");
        }
        bVar.f856a.setText(Chip.this.getText());
    }

    public final boolean j(int i) {
        if (this.k != i) {
            return false;
        }
        this.k = Integer.MIN_VALUE;
        this.i.invalidate();
        s(i, 65536);
        return true;
    }

    public final boolean k(int i) {
        if (this.l != i) {
            return false;
        }
        this.l = Integer.MIN_VALUE;
        Chip.b bVar = (Chip.b) this;
        if (i == 1) {
            Chip chip = Chip.this;
            chip.l = false;
            chip.refreshDrawableState();
        }
        s(i, 8);
        return true;
    }

    public final a.h.k.x.b l(int i) {
        AccessibilityNodeInfo accessibilityNodeInfo;
        a.h.k.x.b bVar = new a.h.k.x.b(AccessibilityNodeInfo.obtain());
        bVar.f856a.setEnabled(true);
        bVar.f856a.setFocusable(true);
        bVar.f856a.setClassName("android.view.View");
        bVar.f856a.setBoundsInParent(n);
        bVar.f856a.setBoundsInScreen(n);
        View view = this.i;
        bVar.f857b = -1;
        bVar.f856a.setParent(view);
        q(i, bVar);
        if (bVar.g() == null && bVar.e() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        bVar.f856a.getBoundsInParent(this.f894e);
        if (this.f894e.equals(n)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int actions = bVar.f856a.getActions();
        if ((actions & 64) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        int i2 = 128;
        if ((actions & 128) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        bVar.f856a.setPackageName(this.i.getContext().getPackageName());
        View view2 = this.i;
        bVar.f858c = i;
        bVar.f856a.setSource(view2, i);
        boolean z = false;
        if (this.k == i) {
            bVar.f856a.setAccessibilityFocused(true);
            accessibilityNodeInfo = bVar.f856a;
        } else {
            bVar.f856a.setAccessibilityFocused(false);
            accessibilityNodeInfo = bVar.f856a;
            i2 = 64;
        }
        accessibilityNodeInfo.addAction(i2);
        boolean z2 = this.l == i;
        if (z2) {
            bVar.f856a.addAction(2);
        } else if (bVar.f856a.isFocusable()) {
            bVar.f856a.addAction(1);
        }
        bVar.f856a.setFocused(z2);
        this.i.getLocationOnScreen(this.g);
        bVar.f856a.getBoundsInScreen(this.f893d);
        if (this.f893d.equals(n)) {
            bVar.f856a.getBoundsInParent(this.f893d);
            if (bVar.f857b != -1) {
                a.h.k.x.b bVar2 = new a.h.k.x.b(AccessibilityNodeInfo.obtain());
                for (int i3 = bVar.f857b; i3 != -1; i3 = bVar2.f857b) {
                    View view3 = this.i;
                    bVar2.f857b = -1;
                    bVar2.f856a.setParent(view3, -1);
                    bVar2.f856a.setBoundsInParent(n);
                    q(i3, bVar2);
                    bVar2.f856a.getBoundsInParent(this.f894e);
                    Rect rect = this.f893d;
                    Rect rect2 = this.f894e;
                    rect.offset(rect2.left, rect2.top);
                }
                bVar2.f856a.recycle();
            }
            this.f893d.offset(this.g[0] - this.i.getScrollX(), this.g[1] - this.i.getScrollY());
        }
        if (this.i.getLocalVisibleRect(this.f895f)) {
            this.f895f.offset(this.g[0] - this.i.getScrollX(), this.g[1] - this.i.getScrollY());
            if (this.f893d.intersect(this.f895f)) {
                bVar.f856a.setBoundsInScreen(this.f893d);
                Rect rect3 = this.f893d;
                if (rect3 != null && !rect3.isEmpty() && this.i.getWindowVisibility() == 0) {
                    View view4 = this.i;
                    while (true) {
                        Object parent = view4.getParent();
                        if (parent instanceof View) {
                            view4 = (View) parent;
                            if (view4.getAlpha() <= 0.0f || view4.getVisibility() != 0) {
                                break;
                            }
                        } else if (parent != null) {
                            z = true;
                        }
                    }
                }
                if (z) {
                    bVar.f856a.setVisibleToUser(true);
                }
            }
        }
        return bVar;
    }

    public abstract void m(List<Integer> list);

    /* JADX WARN: Removed duplicated region for block: B:103:0x0193  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x013c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x013f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean n(int r20, android.graphics.Rect r21) {
        /*
            Method dump skipped, instruction units count: 475
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.j.b.a.n(int, android.graphics.Rect):boolean");
    }

    public a.h.k.x.b o(int i) {
        if (i != -1) {
            return l(i);
        }
        a.h.k.x.b bVar = new a.h.k.x.b(AccessibilityNodeInfo.obtain(this.i));
        q.K(this.i, bVar);
        ArrayList arrayList = new ArrayList();
        m(arrayList);
        if (bVar.f856a.getChildCount() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            bVar.f856a.addChild(this.i, ((Integer) arrayList.get(i2)).intValue());
        }
        return bVar;
    }

    public abstract boolean p(int i, int i2, Bundle bundle);

    public abstract void q(int i, a.h.k.x.b bVar);

    public final boolean r(int i) {
        int i2;
        if ((!this.i.isFocused() && !this.i.requestFocus()) || (i2 = this.l) == i) {
            return false;
        }
        if (i2 != Integer.MIN_VALUE) {
            k(i2);
        }
        this.l = i;
        Chip.b bVar = (Chip.b) this;
        if (i == 1) {
            Chip chip = Chip.this;
            chip.l = true;
            chip.refreshDrawableState();
        }
        s(i, 8);
        return true;
    }

    public final boolean s(int i, int i2) {
        ViewParent parent;
        AccessibilityEvent accessibilityEventObtain;
        if (i == Integer.MIN_VALUE || !this.h.isEnabled() || (parent = this.i.getParent()) == null) {
            return false;
        }
        if (i != -1) {
            accessibilityEventObtain = AccessibilityEvent.obtain(i2);
            a.h.k.x.b bVarO = o(i);
            accessibilityEventObtain.getText().add(bVarO.g());
            accessibilityEventObtain.setContentDescription(bVarO.e());
            accessibilityEventObtain.setScrollable(bVarO.f856a.isScrollable());
            accessibilityEventObtain.setPassword(bVarO.f856a.isPassword());
            accessibilityEventObtain.setEnabled(bVarO.f856a.isEnabled());
            accessibilityEventObtain.setChecked(bVarO.f856a.isChecked());
            if (accessibilityEventObtain.getText().isEmpty() && accessibilityEventObtain.getContentDescription() == null) {
                throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
            }
            accessibilityEventObtain.setClassName(bVarO.f856a.getClassName());
            accessibilityEventObtain.setSource(this.i, i);
            accessibilityEventObtain.setPackageName(this.i.getContext().getPackageName());
        } else {
            accessibilityEventObtain = AccessibilityEvent.obtain(i2);
            this.i.onInitializeAccessibilityEvent(accessibilityEventObtain);
        }
        return parent.requestSendAccessibilityEvent(this.i, accessibilityEventObtain);
    }

    public final void t(int i) {
        int i2 = this.m;
        if (i2 == i) {
            return;
        }
        this.m = i;
        s(i, 128);
        s(i2, 256);
    }
}
