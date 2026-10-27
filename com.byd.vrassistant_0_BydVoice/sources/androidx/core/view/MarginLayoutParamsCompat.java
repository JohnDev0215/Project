package androidx.core.view;

import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public final class MarginLayoutParamsCompat {
    public static int getMarginStart(ViewGroup.MarginLayoutParams lp) {
        return lp.getMarginStart();
    }

    public static int getMarginEnd(ViewGroup.MarginLayoutParams lp) {
        return lp.getMarginEnd();
    }

    public static void setMarginStart(ViewGroup.MarginLayoutParams lp, int marginStart) {
        lp.setMarginStart(marginStart);
    }

    public static void setMarginEnd(ViewGroup.MarginLayoutParams lp, int marginEnd) {
        lp.setMarginEnd(marginEnd);
    }

    public static boolean isMarginRelative(ViewGroup.MarginLayoutParams lp) {
        return lp.isMarginRelative();
    }

    public static int getLayoutDirection(ViewGroup.MarginLayoutParams lp) {
        int layoutDirection = lp.getLayoutDirection();
        if (layoutDirection == 0 || layoutDirection == 1) {
            return layoutDirection;
        }
        return 0;
    }

    public static void setLayoutDirection(ViewGroup.MarginLayoutParams lp, int layoutDirection) {
        lp.setLayoutDirection(layoutDirection);
    }

    public static void resolveLayoutDirection(ViewGroup.MarginLayoutParams lp, int layoutDirection) {
        lp.resolveLayoutDirection(layoutDirection);
    }

    private MarginLayoutParamsCompat() {
    }
}
