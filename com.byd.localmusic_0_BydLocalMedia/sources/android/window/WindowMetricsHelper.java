package android.window;

import android.graphics.Insets;
import android.graphics.Rect;
import android.view.DisplayCutout;
import android.view.ViewRootImpl;
import android.view.WindowInsets;
import android.view.WindowMetrics;

/* JADX INFO: loaded from: classes3.dex */
public final class WindowMetricsHelper {
    private WindowMetricsHelper() {
    }

    public static Rect getBoundsExcludingNavigationBarAndCutout(WindowMetrics windowMetrics) {
        Insets insetsMax;
        WindowInsets windowInsets = windowMetrics.getWindowInsets();
        if (ViewRootImpl.sNewInsetsMode == 2) {
            insetsMax = windowInsets.getInsetsIgnoringVisibility(WindowInsets.Type.navigationBars() | WindowInsets.Type.displayCutout());
        } else {
            Insets stableInsets = windowInsets.getStableInsets();
            Insets insetsOf = Insets.of(stableInsets.left, 0, stableInsets.right, stableInsets.bottom);
            DisplayCutout displayCutout = windowInsets.getDisplayCutout();
            insetsMax = displayCutout != null ? Insets.max(insetsOf, Insets.of(displayCutout.getSafeInsets())) : insetsOf;
        }
        Rect rect = new Rect(windowMetrics.getBounds());
        rect.inset(insetsMax);
        return rect;
    }
}
