package defpackage;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import defpackage.ho0;
import defpackage.om3;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ExploreByTouchHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class pi0 extends C0214v {
    public static final Rect n = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    public static final a o = new a();
    public static final b p = new b();
    public final AccessibilityManager h;
    public final View i;
    public c j;
    public final Rect d = new Rect();
    public final Rect e = new Rect();
    public final Rect f = new Rect();
    public final int[] g = new int[2];
    public int k = Integer.MIN_VALUE;
    public int l = Integer.MIN_VALUE;
    public int m = Integer.MIN_VALUE;

    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    public class a implements ho0.a<AccessibilityNodeInfoCompat> {
    }

    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    public class b {
    }

    /* JADX INFO: compiled from: ExploreByTouchHelper.java */
    public class c extends C0180d0 {
        public c() {
        }

        @Override // defpackage.C0180d0
        public final AccessibilityNodeInfoCompat g(int i) {
            return new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain(pi0.this.r(i).a));
        }

        @Override // defpackage.C0180d0
        public final AccessibilityNodeInfoCompat i(int i) {
            pi0 pi0Var = pi0.this;
            int i2 = i == 2 ? pi0Var.k : pi0Var.l;
            if (i2 == Integer.MIN_VALUE) {
                return null;
            }
            return g(i2);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x002c  */
        @Override // defpackage.C0180d0
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean j(int r7, int r8, android.os.Bundle r9) {
            /*
                r6 = this;
                pi0 r6 = defpackage.pi0.this
                android.view.View r0 = r6.i
                r1 = -1
                if (r7 == r1) goto L64
                r1 = 1
                if (r8 == r1) goto L5f
                r2 = 2
                if (r8 == r2) goto L5a
                r2 = 64
                r3 = 65536(0x10000, float:9.1835E-41)
                r4 = 0
                r5 = -2147483648(0xffffffff80000000, float:-0.0)
                if (r8 == r2) goto L2f
                r2 = 128(0x80, float:1.8E-43)
                if (r8 == r2) goto L1f
                boolean r6 = r6.s(r7, r8, r9)
                goto L6a
            L1f:
                int r8 = r6.k
                if (r8 != r7) goto L2c
                r6.k = r5
                r0.invalidate()
                r6.y(r7, r3)
                goto L2d
            L2c:
                r1 = r4
            L2d:
                r6 = r1
                goto L6a
            L2f:
                android.view.accessibility.AccessibilityManager r8 = r6.h
                boolean r9 = r8.isEnabled()
                if (r9 == 0) goto L2c
                boolean r8 = r8.isTouchExplorationEnabled()
                if (r8 != 0) goto L3e
                goto L2c
            L3e:
                int r8 = r6.k
                if (r8 == r7) goto L2c
                if (r8 == r5) goto L4e
                r6.k = r5
                android.view.View r9 = r6.i
                r9.invalidate()
                r6.y(r8, r3)
            L4e:
                r6.k = r7
                r0.invalidate()
                r8 = 32768(0x8000, float:4.5918E-41)
                r6.y(r7, r8)
                goto L2d
            L5a:
                boolean r6 = r6.j(r7)
                goto L6a
            L5f:
                boolean r6 = r6.x(r7)
                goto L6a
            L64:
                java.util.WeakHashMap<android.view.View, in3> r6 = defpackage.om3.a
                boolean r6 = om3.d.i(r0, r8, r9)
            L6a:
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: pi0.c.j(int, int, android.os.Bundle):boolean");
        }
    }

    public pi0(View view) {
        if (view == null) {
            throw new IllegalArgumentException("View may not be null");
        }
        this.i = view;
        this.h = (AccessibilityManager) view.getContext().getSystemService("accessibility");
        view.setFocusable(true);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        if (om3.d.b(view) == 0) {
            om3.d.p(view, 1);
        }
    }

    @Override // defpackage.C0214v
    public final C0180d0 b(View view) {
        if (this.j == null) {
            this.j = new c();
        }
        return this.j;
    }

    @Override // defpackage.C0214v
    public final void c(View view, AccessibilityEvent accessibilityEvent) {
        super.c(view, accessibilityEvent);
    }

    @Override // defpackage.C0214v
    public final void d(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        this.a.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.a);
        u(accessibilityNodeInfoCompat);
    }

    public final boolean j(int i) {
        if (this.l != i) {
            return false;
        }
        this.l = Integer.MIN_VALUE;
        w(i, false);
        y(i, 8);
        return true;
    }

    public final AccessibilityEvent k(int i, int i2) {
        View view = this.i;
        if (i == -1) {
            AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i2);
            view.onInitializeAccessibilityEvent(accessibilityEventObtain);
            return accessibilityEventObtain;
        }
        AccessibilityEvent accessibilityEventObtain2 = AccessibilityEvent.obtain(i2);
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompatR = r(i);
        accessibilityEventObtain2.getText().add(accessibilityNodeInfoCompatR.j());
        accessibilityEventObtain2.setContentDescription(accessibilityNodeInfoCompatR.g());
        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompatR.a;
        accessibilityEventObtain2.setScrollable(accessibilityNodeInfo.isScrollable());
        accessibilityEventObtain2.setPassword(accessibilityNodeInfo.isPassword());
        accessibilityEventObtain2.setEnabled(accessibilityNodeInfo.isEnabled());
        accessibilityEventObtain2.setChecked(accessibilityNodeInfo.isChecked());
        t(accessibilityEventObtain2, i);
        if (accessibilityEventObtain2.getText().isEmpty() && accessibilityEventObtain2.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        }
        accessibilityEventObtain2.setClassName(accessibilityNodeInfo.getClassName());
        accessibilityEventObtain2.setSource(view, i);
        accessibilityEventObtain2.setPackageName(view.getContext().getPackageName());
        return accessibilityEventObtain2;
    }

    public final AccessibilityNodeInfoCompat l(int i) {
        AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain();
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(accessibilityNodeInfoObtain);
        accessibilityNodeInfoObtain.setEnabled(true);
        accessibilityNodeInfoObtain.setFocusable(true);
        accessibilityNodeInfoCompat.n("android.view.View");
        Rect rect = n;
        accessibilityNodeInfoCompat.l(rect);
        accessibilityNodeInfoCompat.m(rect);
        accessibilityNodeInfoCompat.b = -1;
        View view = this.i;
        accessibilityNodeInfoObtain.setParent(view);
        v(i, accessibilityNodeInfoCompat);
        if (accessibilityNodeInfoCompat.j() == null && accessibilityNodeInfoCompat.g() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        Rect rect2 = this.e;
        accessibilityNodeInfoObtain.getBoundsInParent(rect2);
        Rect rect3 = this.d;
        accessibilityNodeInfoCompat.f(rect3);
        if (rect2.equals(rect) && rect3.equals(rect)) {
            throw new RuntimeException("Callbacks must set parent bounds or screen bounds in populateNodeForVirtualViewId()");
        }
        int actions = accessibilityNodeInfoObtain.getActions();
        if ((actions & 64) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((actions & 128) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        accessibilityNodeInfoObtain.setPackageName(view.getContext().getPackageName());
        accessibilityNodeInfoCompat.c = i;
        accessibilityNodeInfoObtain.setSource(view, i);
        boolean z = false;
        if (this.k == i) {
            accessibilityNodeInfoObtain.setAccessibilityFocused(true);
            accessibilityNodeInfoCompat.a(128);
        } else {
            accessibilityNodeInfoObtain.setAccessibilityFocused(false);
            accessibilityNodeInfoCompat.a(64);
        }
        boolean z2 = this.l == i;
        if (z2) {
            accessibilityNodeInfoCompat.a(2);
        } else if (accessibilityNodeInfoObtain.isFocusable()) {
            accessibilityNodeInfoCompat.a(1);
        }
        accessibilityNodeInfoObtain.setFocused(z2);
        int[] iArr = this.g;
        view.getLocationOnScreen(iArr);
        if (rect3.equals(rect)) {
            accessibilityNodeInfoCompat.l(rect2);
            Rect rect4 = new Rect();
            rect4.set(rect2);
            if (accessibilityNodeInfoCompat.b != -1) {
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2 = new AccessibilityNodeInfoCompat(AccessibilityNodeInfo.obtain());
                Rect rect5 = new Rect();
                for (int i2 = accessibilityNodeInfoCompat.b; i2 != -1; i2 = accessibilityNodeInfoCompat2.b) {
                    accessibilityNodeInfoCompat2.b = -1;
                    AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat2.a;
                    accessibilityNodeInfo.setParent(view, -1);
                    accessibilityNodeInfoCompat2.l(rect);
                    v(i2, accessibilityNodeInfoCompat2);
                    accessibilityNodeInfo.getBoundsInParent(rect5);
                    rect4.offset(rect5.left, rect5.top);
                }
            }
            view.getLocationOnScreen(iArr);
            rect4.offset(iArr[0] - view.getScrollX(), iArr[1] - view.getScrollY());
            accessibilityNodeInfoCompat.m(rect4);
            accessibilityNodeInfoCompat.f(rect3);
        }
        Rect rect6 = this.f;
        if (view.getLocalVisibleRect(rect6)) {
            rect6.offset(iArr[0] - view.getScrollX(), iArr[1] - view.getScrollY());
            if (rect3.intersect(rect6)) {
                accessibilityNodeInfoCompat.m(rect3);
                if (!rect3.isEmpty() && view.getWindowVisibility() == 0) {
                    Object parent = view.getParent();
                    while (true) {
                        if (parent instanceof View) {
                            View view2 = (View) parent;
                            if (view2.getAlpha() <= 0.0f || view2.getVisibility() != 0) {
                                break;
                            }
                            parent = view2.getParent();
                        } else if (parent != null) {
                            z = true;
                        }
                    }
                }
                if (z) {
                    accessibilityNodeInfoCompat.a.setVisibleToUser(true);
                }
            }
        }
        return accessibilityNodeInfoCompat;
    }

    public final boolean m(MotionEvent motionEvent) {
        int i;
        AccessibilityManager accessibilityManager = this.h;
        if (!accessibilityManager.isEnabled() || !accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            int iN = n(motionEvent.getX(), motionEvent.getY());
            int i2 = this.m;
            if (i2 != iN) {
                this.m = iN;
                y(iN, 128);
                y(i2, 256);
            }
            return iN != Integer.MIN_VALUE;
        }
        if (action != 10 || (i = this.m) == Integer.MIN_VALUE) {
            return false;
        }
        if (i != Integer.MIN_VALUE) {
            this.m = Integer.MIN_VALUE;
            y(Integer.MIN_VALUE, 128);
            y(i, 256);
        }
        return true;
    }

    public abstract int n(float f, float f2);

    public abstract void o(ArrayList arrayList);

    public final void p(int i, int i2) {
        View view;
        ViewParent parent;
        if (i == Integer.MIN_VALUE || !this.h.isEnabled() || (parent = (view = this.i).getParent()) == null) {
            return;
        }
        AccessibilityEvent accessibilityEventK = k(i, 2048);
        accessibilityEventK.setContentChangeTypes(i2);
        parent.requestSendAccessibilityEvent(view, accessibilityEventK);
    }

    /* JADX WARN: Removed duplicated region for block: B:119:0x0150 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x014b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean q(int r19, android.graphics.Rect r20) {
        /*
            Method dump skipped, instruction units count: 483
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pi0.q(int, android.graphics.Rect):boolean");
    }

    public final AccessibilityNodeInfoCompat r(int i) {
        if (i != -1) {
            return l(i);
        }
        View view = this.i;
        AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain(view);
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(accessibilityNodeInfoObtain);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        view.onInitializeAccessibilityNodeInfo(accessibilityNodeInfoObtain);
        ArrayList arrayList = new ArrayList();
        o(arrayList);
        if (accessibilityNodeInfoObtain.getChildCount() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            accessibilityNodeInfoCompat.a.addChild(view, ((Integer) arrayList.get(i2)).intValue());
        }
        return accessibilityNodeInfoCompat;
    }

    public abstract boolean s(int i, int i2, Bundle bundle);

    public abstract void v(int i, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat);

    public final boolean x(int i) {
        int i2;
        View view = this.i;
        if ((!view.isFocused() && !view.requestFocus()) || (i2 = this.l) == i) {
            return false;
        }
        if (i2 != Integer.MIN_VALUE) {
            j(i2);
        }
        if (i == Integer.MIN_VALUE) {
            return false;
        }
        this.l = i;
        w(i, true);
        y(i, 8);
        return true;
    }

    public final void y(int i, int i2) {
        View view;
        ViewParent parent;
        if (i == Integer.MIN_VALUE || !this.h.isEnabled() || (parent = (view = this.i).getParent()) == null) {
            return;
        }
        parent.requestSendAccessibilityEvent(view, k(i, i2));
    }

    public void u(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }

    public void t(AccessibilityEvent accessibilityEvent, int i) {
    }

    public void w(int i, boolean z) {
    }
}
