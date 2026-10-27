package com.android.inputmethod.latin.utils;

import android.view.View;
import android.widget.TextView;
import com.android.inputmethod.compat.BuildCompatUtils;
import com.android.inputmethod.compat.CursorAnchorInfoCompatWrapper;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class CursorAnchorInfoUtils {
    private CursorAnchorInfoUtils() {
    }

    private static boolean isPositionVisible(final View view, final float positionX, final float positionY) {
        float[] fArr = {positionX, positionY};
        View view2 = view;
        while (view2 != null) {
            if (view2 != view) {
                fArr[0] = fArr[0] - view2.getScrollX();
                fArr[1] = fArr[1] - view2.getScrollY();
            }
            if (fArr[0] < 0.0f || fArr[1] < 0.0f || fArr[0] > view2.getWidth() || fArr[1] > view2.getHeight()) {
                return false;
            }
            if (!view2.getMatrix().isIdentity()) {
                view2.getMatrix().mapPoints(fArr);
            }
            fArr[0] = fArr[0] + view2.getLeft();
            fArr[1] = fArr[1] + view2.getTop();
            Object parent = view2.getParent();
            view2 = parent instanceof View ? (View) parent : null;
        }
        return true;
    }

    @Nullable
    public static CursorAnchorInfoCompatWrapper extractFromTextView(@Nonnull final TextView textView) {
        if (BuildCompatUtils.EFFECTIVE_SDK_INT < 21) {
            return null;
        }
        return CursorAnchorInfoCompatWrapper.wrap(extractFromTextViewInternal(textView));
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x014c  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0151  */
    @javax.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.view.inputmethod.CursorAnchorInfo extractFromTextViewInternal(@javax.annotation.Nonnull final android.widget.TextView r27) {
        /*
            Method dump skipped, instruction units count: 454
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.inputmethod.latin.utils.CursorAnchorInfoUtils.extractFromTextViewInternal(android.widget.TextView):android.view.inputmethod.CursorAnchorInfo");
    }
}
