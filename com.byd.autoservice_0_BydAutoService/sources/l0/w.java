package l0;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;

/* JADX INFO: compiled from: ViewParentCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class w {
    public static boolean a(ViewParent viewParent, View view, float f3, float f4, boolean z3) {
        if (Build.VERSION.SDK_INT < 21) {
            if (viewParent instanceof n) {
                return ((n) viewParent).onNestedFling(view, f3, f4, z3);
            }
            return false;
        }
        try {
            return viewParent.onNestedFling(view, f3, f4, z3);
        } catch (AbstractMethodError e3) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedFling", e3);
            return false;
        }
    }

    public static boolean b(ViewParent viewParent, View view, float f3, float f4) {
        if (Build.VERSION.SDK_INT < 21) {
            if (viewParent instanceof n) {
                return ((n) viewParent).onNestedPreFling(view, f3, f4);
            }
            return false;
        }
        try {
            return viewParent.onNestedPreFling(view, f3, f4);
        } catch (AbstractMethodError e3) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedPreFling", e3);
            return false;
        }
    }

    public static void c(ViewParent viewParent, View view, int i3, int i4, int[] iArr, int i5) {
        if (viewParent instanceof l) {
            ((l) viewParent).j(view, i3, i4, iArr, i5);
            return;
        }
        if (i5 == 0) {
            if (Build.VERSION.SDK_INT < 21) {
                if (viewParent instanceof n) {
                    ((n) viewParent).onNestedPreScroll(view, i3, i4, iArr);
                    return;
                }
                return;
            }
            try {
                viewParent.onNestedPreScroll(view, i3, i4, iArr);
            } catch (AbstractMethodError e3) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedPreScroll", e3);
            }
        }
    }

    public static void d(ViewParent viewParent, View view, int i3, int i4, int i5, int i6, int i7, int[] iArr) {
        if (viewParent instanceof m) {
            ((m) viewParent).m(view, i3, i4, i5, i6, i7, iArr);
            return;
        }
        iArr[0] = iArr[0] + i5;
        iArr[1] = iArr[1] + i6;
        if (viewParent instanceof l) {
            ((l) viewParent).n(view, i3, i4, i5, i6, i7);
            return;
        }
        if (i7 == 0) {
            if (Build.VERSION.SDK_INT < 21) {
                if (viewParent instanceof n) {
                    ((n) viewParent).onNestedScroll(view, i3, i4, i5, i6);
                    return;
                }
                return;
            }
            try {
                viewParent.onNestedScroll(view, i3, i4, i5, i6);
            } catch (AbstractMethodError e3) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedScroll", e3);
            }
        }
    }

    public static void e(ViewParent viewParent, View view, View view2, int i3, int i4) {
        if (viewParent instanceof l) {
            ((l) viewParent).h(view, view2, i3, i4);
            return;
        }
        if (i4 == 0) {
            if (Build.VERSION.SDK_INT < 21) {
                if (viewParent instanceof n) {
                    ((n) viewParent).onNestedScrollAccepted(view, view2, i3);
                    return;
                }
                return;
            }
            try {
                viewParent.onNestedScrollAccepted(view, view2, i3);
            } catch (AbstractMethodError e3) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedScrollAccepted", e3);
            }
        }
    }

    public static boolean f(ViewParent viewParent, View view, View view2, int i3, int i4) {
        if (viewParent instanceof l) {
            return ((l) viewParent).o(view, view2, i3, i4);
        }
        if (i4 != 0) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 21) {
            if (viewParent instanceof n) {
                return ((n) viewParent).onStartNestedScroll(view, view2, i3);
            }
            return false;
        }
        try {
            return viewParent.onStartNestedScroll(view, view2, i3);
        } catch (AbstractMethodError e3) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onStartNestedScroll", e3);
            return false;
        }
    }

    public static void g(ViewParent viewParent, View view, int i3) {
        if (viewParent instanceof l) {
            ((l) viewParent).i(view, i3);
            return;
        }
        if (i3 == 0) {
            if (Build.VERSION.SDK_INT < 21) {
                if (viewParent instanceof n) {
                    ((n) viewParent).onStopNestedScroll(view);
                    return;
                }
                return;
            }
            try {
                viewParent.onStopNestedScroll(view);
            } catch (AbstractMethodError e3) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onStopNestedScroll", e3);
            }
        }
    }

    @Deprecated
    public static boolean h(ViewParent viewParent, View view, AccessibilityEvent accessibilityEvent) {
        return viewParent.requestSendAccessibilityEvent(view, accessibilityEvent);
    }
}
