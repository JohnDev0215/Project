package L1;

import B2.O;
import F0.F;
import F1.C0243b;
import F1.H;
import Y4.e;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.material.chip.Chip;
import io.sentry.hints.i;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends C0243b {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final Rect f3967n = new Rect(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    public static final e o = new e(4);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final i f3968p = new i(4);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final AccessibilityManager f3972h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Chip f3973i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public F f3974j;
    public final Rect d = new Rect();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Rect f3969e = new Rect();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Rect f3970f = new Rect();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int[] f3971g = new int[2];

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f3975k = Integer.MIN_VALUE;
    public int l = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3976m = Integer.MIN_VALUE;

    public a(Chip chip) {
        if (chip == null) {
            throw new IllegalArgumentException("View may not be null");
        }
        this.f3973i = chip;
        this.f3972h = (AccessibilityManager) chip.getContext().getSystemService("accessibility");
        chip.setFocusable(true);
        int[] iArr = H.f2303a;
        if (chip.getImportantForAccessibility() == 0) {
            chip.setImportantForAccessibility(1);
        }
    }

    private void s(int i7) {
        int i10 = this.f3976m;
        if (i10 == i7) {
            return;
        }
        this.f3976m = i7;
        r(i7, 128);
        r(i10, 256);
    }

    @Override // F1.C0243b
    public final O b(View view) {
        if (this.f3974j == null) {
            this.f3974j = new F(this, 1);
        }
        return this.f3974j;
    }

    @Override // F1.C0243b
    public final void d(View view, G1.e eVar) {
        View.AccessibilityDelegate accessibilityDelegate = this.f2324a;
        AccessibilityNodeInfo accessibilityNodeInfo = eVar.f2646a;
        accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
        Chip chip = ((R4.c) this).f7428q;
        accessibilityNodeInfo.setCheckable(chip.e());
        accessibilityNodeInfo.setClickable(chip.isClickable());
        if (chip.e() || chip.isClickable()) {
            eVar.h(chip.e() ? "android.widget.CompoundButton" : "android.widget.Button");
        } else {
            eVar.h("android.view.View");
        }
        eVar.k(chip.getText());
    }

    public final boolean j(int i7) {
        if (this.l != i7) {
            return false;
        }
        this.l = Integer.MIN_VALUE;
        R4.c cVar = (R4.c) this;
        if (i7 == 1) {
            Chip chip = cVar.f7428q;
            chip.f12832x = false;
            chip.refreshDrawableState();
        }
        r(i7, 8);
        return true;
    }

    public final G1.e k(int i7) {
        AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain();
        G1.e eVar = new G1.e(accessibilityNodeInfoObtain);
        accessibilityNodeInfoObtain.setEnabled(true);
        accessibilityNodeInfoObtain.setFocusable(true);
        eVar.h("android.view.View");
        Rect rect = f3967n;
        accessibilityNodeInfoObtain.setBoundsInParent(rect);
        accessibilityNodeInfoObtain.setBoundsInScreen(rect);
        eVar.f2647b = -1;
        Chip chip = this.f3973i;
        accessibilityNodeInfoObtain.setParent(chip);
        p(i7, eVar);
        if (eVar.g() == null && accessibilityNodeInfoObtain.getContentDescription() == null) {
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        }
        Rect rect2 = this.f3969e;
        eVar.f(rect2);
        if (rect2.equals(rect)) {
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        }
        int actions = accessibilityNodeInfoObtain.getActions();
        if ((actions & 64) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        if ((actions & 128) != 0) {
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        }
        accessibilityNodeInfoObtain.setPackageName(chip.getContext().getPackageName());
        eVar.c = i7;
        accessibilityNodeInfoObtain.setSource(chip, i7);
        if (this.f3975k == i7) {
            accessibilityNodeInfoObtain.setAccessibilityFocused(true);
            eVar.a(128);
        } else {
            accessibilityNodeInfoObtain.setAccessibilityFocused(false);
            eVar.a(64);
        }
        boolean z6 = this.l == i7;
        if (z6) {
            eVar.a(2);
        } else if (accessibilityNodeInfoObtain.isFocusable()) {
            eVar.a(1);
        }
        accessibilityNodeInfoObtain.setFocused(z6);
        int[] iArr = this.f3971g;
        chip.getLocationOnScreen(iArr);
        Rect rect3 = this.d;
        accessibilityNodeInfoObtain.getBoundsInScreen(rect3);
        if (rect3.equals(rect)) {
            eVar.f(rect3);
            if (eVar.f2647b != -1) {
                G1.e eVar2 = new G1.e(AccessibilityNodeInfo.obtain());
                for (int i10 = eVar.f2647b; i10 != -1; i10 = eVar2.f2647b) {
                    eVar2.f2647b = -1;
                    AccessibilityNodeInfo accessibilityNodeInfo = eVar2.f2646a;
                    accessibilityNodeInfo.setParent(chip, -1);
                    accessibilityNodeInfo.setBoundsInParent(rect);
                    p(i10, eVar2);
                    eVar2.f(rect2);
                    rect3.offset(rect2.left, rect2.top);
                }
            }
            rect3.offset(iArr[0] - chip.getScrollX(), iArr[1] - chip.getScrollY());
        }
        Rect rect4 = this.f3970f;
        if (chip.getLocalVisibleRect(rect4)) {
            rect4.offset(iArr[0] - chip.getScrollX(), iArr[1] - chip.getScrollY());
            if (rect3.intersect(rect4)) {
                AccessibilityNodeInfo accessibilityNodeInfo2 = eVar.f2646a;
                accessibilityNodeInfo2.setBoundsInScreen(rect3);
                if (!rect3.isEmpty() && chip.getWindowVisibility() == 0) {
                    Object parent = chip.getParent();
                    while (true) {
                        if (parent instanceof View) {
                            View view = (View) parent;
                            if (view.getAlpha() <= 0.0f || view.getVisibility() != 0) {
                                break;
                            }
                            parent = view.getParent();
                        } else if (parent != null) {
                            accessibilityNodeInfo2.setVisibleToUser(true);
                        }
                    }
                }
            }
        }
        return eVar;
    }

    public final boolean l(MotionEvent motionEvent) {
        AccessibilityManager accessibilityManager = this.f3972h;
        if (!accessibilityManager.isEnabled() || !accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7 && action != 9) {
            if (action != 10 || this.f3976m == Integer.MIN_VALUE) {
                return false;
            }
            s(Integer.MIN_VALUE);
            return true;
        }
        float x8 = motionEvent.getX();
        float y10 = motionEvent.getY();
        Rect rect = Chip.f12817F;
        Chip chip = ((R4.c) this).f7428q;
        int i7 = (chip.d() && chip.getCloseIconTouchBounds().contains(x8, y10)) ? 1 : 0;
        s(i7);
        return i7 != Integer.MIN_VALUE;
    }

    public abstract void m(ArrayList arrayList);

    /* JADX WARN: Removed duplicated region for block: B:40:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean n(int r20, android.graphics.Rect r21) {
        /*
            Method dump skipped, instruction units count: 499
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: L1.a.n(int, android.graphics.Rect):boolean");
    }

    public final G1.e o(int i7) {
        if (i7 != -1) {
            return k(i7);
        }
        Chip chip = this.f3973i;
        AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain(chip);
        G1.e eVar = new G1.e(accessibilityNodeInfoObtain);
        int[] iArr = H.f2303a;
        chip.onInitializeAccessibilityNodeInfo(accessibilityNodeInfoObtain);
        ArrayList arrayList = new ArrayList();
        m(arrayList);
        if (accessibilityNodeInfoObtain.getChildCount() > 0 && arrayList.size() > 0) {
            throw new RuntimeException("Views cannot have both real and virtual children");
        }
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            eVar.f2646a.addChild(chip, ((Integer) arrayList.get(i10)).intValue());
        }
        return eVar;
    }

    public abstract void p(int i7, G1.e eVar);

    public final boolean q(int i7) {
        int i10;
        Chip chip = this.f3973i;
        if ((!chip.isFocused() && !chip.requestFocus()) || (i10 = this.l) == i7) {
            return false;
        }
        if (i10 != Integer.MIN_VALUE) {
            j(i10);
        }
        if (i7 == Integer.MIN_VALUE) {
            return false;
        }
        this.l = i7;
        R4.c cVar = (R4.c) this;
        if (i7 == 1) {
            Chip chip2 = cVar.f7428q;
            chip2.f12832x = true;
            chip2.refreshDrawableState();
        }
        r(i7, 8);
        return true;
    }

    public final void r(int i7, int i10) {
        View view;
        ViewParent parent;
        AccessibilityEvent accessibilityEventObtain;
        if (i7 == Integer.MIN_VALUE || !this.f3972h.isEnabled() || (parent = (view = this.f3973i).getParent()) == null) {
            return;
        }
        if (i7 != -1) {
            accessibilityEventObtain = AccessibilityEvent.obtain(i10);
            G1.e eVarO = o(i7);
            accessibilityEventObtain.getText().add(eVarO.g());
            AccessibilityNodeInfo accessibilityNodeInfo = eVarO.f2646a;
            accessibilityEventObtain.setContentDescription(accessibilityNodeInfo.getContentDescription());
            accessibilityEventObtain.setScrollable(accessibilityNodeInfo.isScrollable());
            accessibilityEventObtain.setPassword(accessibilityNodeInfo.isPassword());
            accessibilityEventObtain.setEnabled(accessibilityNodeInfo.isEnabled());
            accessibilityEventObtain.setChecked(accessibilityNodeInfo.isChecked());
            if (accessibilityEventObtain.getText().isEmpty() && accessibilityEventObtain.getContentDescription() == null) {
                throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
            }
            accessibilityEventObtain.setClassName(accessibilityNodeInfo.getClassName());
            accessibilityEventObtain.setSource(view, i7);
            accessibilityEventObtain.setPackageName(view.getContext().getPackageName());
        } else {
            accessibilityEventObtain = AccessibilityEvent.obtain(i10);
            view.onInitializeAccessibilityEvent(accessibilityEventObtain);
        }
        parent.requestSendAccessibilityEvent(view, accessibilityEventObtain);
    }
}
