package G1;

import F1.AbstractC0260t;
import android.R;
import android.os.Build;
import android.view.accessibility.AccessibilityNodeInfo;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final d f2634e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final d f2635f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final d f2636g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final d f2637h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final d f2638i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final d f2639j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final d f2640k;
    public static final d l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final d f2641m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final d f2642n;
    public static final d o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final d f2643p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f2644a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f2645b;
    public final Class c;
    public final n d;

    static {
        new d(1, (String) null);
        new d(2, (String) null);
        new d(4, (String) null);
        new d(8, (String) null);
        f2634e = new d(16, (String) null);
        new d(32, (String) null);
        f2635f = new d(64, (String) null);
        f2636g = new d(128, (String) null);
        new d(256, g.class);
        new d(512, g.class);
        new d(1024, h.class);
        new d(2048, h.class);
        f2637h = new d(4096, (String) null);
        f2638i = new d(8192, (String) null);
        new d(16384, (String) null);
        new d(32768, (String) null);
        new d(65536, (String) null);
        new d(131072, l.class);
        f2639j = new d(262144, (String) null);
        f2640k = new d(524288, (String) null);
        l = new d(1048576, (String) null);
        new d(2097152, m.class);
        int i7 = Build.VERSION.SDK_INT;
        new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN, R.id.accessibilityActionShowOnScreen, null, null, null);
        new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION, R.id.accessibilityActionScrollToPosition, null, null, j.class);
        f2641m = new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP, R.id.accessibilityActionScrollUp, null, null, null);
        f2642n = new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT, R.id.accessibilityActionScrollLeft, null, null, null);
        o = new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN, R.id.accessibilityActionScrollDown, null, null, null);
        f2643p = new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT, R.id.accessibilityActionScrollRight, null, null, null);
        new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP, R.id.accessibilityActionPageUp, null, null, null);
        new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN, R.id.accessibilityActionPageDown, null, null, null);
        new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT, R.id.accessibilityActionPageLeft, null, null, null);
        new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT, R.id.accessibilityActionPageRight, null, null, null);
        new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK, R.id.accessibilityActionContextClick, null, null, null);
        new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS, R.id.accessibilityActionSetProgress, null, null, k.class);
        new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW, R.id.accessibilityActionMoveWindow, null, null, i.class);
        new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP, R.id.accessibilityActionShowTooltip, null, null, null);
        new d(AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP, R.id.accessibilityActionHideTooltip, null, null, null);
        new d(i7 >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PRESS_AND_HOLD : null, R.id.accessibilityActionPressAndHold, null, null, null);
        new d(i7 >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_IME_ENTER : null, R.id.accessibilityActionImeEnter, null, null, null);
        new d(i7 >= 32 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_START : null, R.id.accessibilityActionDragStart, null, null, null);
        new d(i7 >= 32 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_DROP : null, R.id.accessibilityActionDragDrop, null, null, null);
        new d(i7 >= 32 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_DRAG_CANCEL : null, R.id.accessibilityActionDragCancel, null, null, null);
        new d(i7 >= 33 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TEXT_SUGGESTIONS : null, R.id.accessibilityActionShowTextSuggestions, null, null, null);
        new d(i7 >= 34 ? AbstractC0260t.a() : null, R.id.accessibilityActionScrollInDirection, null, null, null);
    }

    public d(int i7, String str) {
        this(null, i7, str, null, null);
    }

    public final int a() {
        return ((AccessibilityNodeInfo.AccessibilityAction) this.f2644a).getId();
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof d)) {
            return false;
        }
        Object obj2 = ((d) obj).f2644a;
        Object obj3 = this.f2644a;
        return obj3 == null ? obj2 == null : obj3.equals(obj2);
    }

    public final int hashCode() {
        Object obj = this.f2644a;
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AccessibilityActionCompat: ");
        String strD = e.d(this.f2645b);
        if (strD.equals("ACTION_UNKNOWN")) {
            Object obj = this.f2644a;
            if (((AccessibilityNodeInfo.AccessibilityAction) obj).getLabel() != null) {
                strD = ((AccessibilityNodeInfo.AccessibilityAction) obj).getLabel().toString();
            }
        }
        sb.append(strD);
        return sb.toString();
    }

    public d(int i7, Class cls) {
        this(null, i7, null, null, cls);
    }

    public d(Object obj, int i7, String str, n nVar, Class cls) {
        this.f2645b = i7;
        this.d = nVar;
        if (obj == null) {
            this.f2644a = new AccessibilityNodeInfo.AccessibilityAction(i7, str);
        } else {
            this.f2644a = obj;
        }
        this.c = cls;
    }
}
