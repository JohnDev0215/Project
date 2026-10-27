package o0;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;

/* JADX INFO: compiled from: ViewParentCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class w {
    public static boolean a(ViewParent viewParent, View view, float f8, float f9, boolean z7) {
        if (Build.VERSION.SDK_INT < 21) {
            if (viewParent instanceof n) {
                return ((n) viewParent).onNestedFling(view, f8, f9, z7);
            }
            return false;
        }
        try {
            return viewParent.onNestedFling(view, f8, f9, z7);
        } catch (AbstractMethodError e8) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedFling", e8);
            return false;
        }
    }

    public static boolean b(ViewParent viewParent, View view, float f8, float f9) {
        if (Build.VERSION.SDK_INT < 21) {
            if (viewParent instanceof n) {
                return ((n) viewParent).onNestedPreFling(view, f8, f9);
            }
            return false;
        }
        try {
            return viewParent.onNestedPreFling(view, f8, f9);
        } catch (AbstractMethodError e8) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedPreFling", e8);
            return false;
        }
    }

    public static void c(ViewParent viewParent, View view, int i8, int i9, int[] iArr, int i10) {
        if (viewParent instanceof l) {
            ((l) viewParent).j(view, i8, i9, iArr, i10);
            return;
        }
        if (i10 == 0) {
            if (Build.VERSION.SDK_INT < 21) {
                if (viewParent instanceof n) {
                    ((n) viewParent).onNestedPreScroll(view, i8, i9, iArr);
                    return;
                }
                return;
            }
            try {
                viewParent.onNestedPreScroll(view, i8, i9, iArr);
            } catch (AbstractMethodError e8) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedPreScroll", e8);
            }
        }
    }

    public static void d(ViewParent viewParent, View view, int i8, int i9, int i10, int i11, int i12, int[] iArr) {
        if (viewParent instanceof m) {
            ((m) viewParent).m(view, i8, i9, i10, i11, i12, iArr);
            return;
        }
        iArr[0] = iArr[0] + i10;
        iArr[1] = iArr[1] + i11;
        if (viewParent instanceof l) {
            ((l) viewParent).n(view, i8, i9, i10, i11, i12);
            return;
        }
        if (i12 == 0) {
            if (Build.VERSION.SDK_INT < 21) {
                if (viewParent instanceof n) {
                    ((n) viewParent).onNestedScroll(view, i8, i9, i10, i11);
                    return;
                }
                return;
            }
            try {
                viewParent.onNestedScroll(view, i8, i9, i10, i11);
            } catch (AbstractMethodError e8) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedScroll", e8);
            }
        }
    }

    public static void e(ViewParent viewParent, View view, View view2, int i8, int i9) {
        if (viewParent instanceof l) {
            ((l) viewParent).h(view, view2, i8, i9);
            return;
        }
        if (i9 == 0) {
            if (Build.VERSION.SDK_INT < 21) {
                if (viewParent instanceof n) {
                    ((n) viewParent).onNestedScrollAccepted(view, view2, i8);
                    return;
                }
                return;
            }
            try {
                viewParent.onNestedScrollAccepted(view, view2, i8);
            } catch (AbstractMethodError e8) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onNestedScrollAccepted", e8);
            }
        }
    }

    public static boolean f(ViewParent viewParent, View view, View view2, int i8, int i9) {
        if (viewParent instanceof l) {
            return ((l) viewParent).o(view, view2, i8, i9);
        }
        if (i9 != 0) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 21) {
            if (viewParent instanceof n) {
                return ((n) viewParent).onStartNestedScroll(view, view2, i8);
            }
            return false;
        }
        try {
            return viewParent.onStartNestedScroll(view, view2, i8);
        } catch (AbstractMethodError e8) {
            Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onStartNestedScroll", e8);
            return false;
        }
    }

    public static void g(ViewParent viewParent, View view, int i8) {
        if (viewParent instanceof l) {
            ((l) viewParent).i(view, i8);
            return;
        }
        if (i8 == 0) {
            if (Build.VERSION.SDK_INT < 21) {
                if (viewParent instanceof n) {
                    ((n) viewParent).onStopNestedScroll(view);
                    return;
                }
                return;
            }
            try {
                viewParent.onStopNestedScroll(view);
            } catch (AbstractMethodError e8) {
                Log.e("ViewParentCompat", "ViewParent " + viewParent + " does not implement interface method onStopNestedScroll", e8);
            }
        }
    }

    @Deprecated
    public static boolean h(ViewParent viewParent, View view, AccessibilityEvent accessibilityEvent) {
        return viewParent.requestSendAccessibilityEvent(view, accessibilityEvent);
    }
}
