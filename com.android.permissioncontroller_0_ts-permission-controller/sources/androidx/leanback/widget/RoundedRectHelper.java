package androidx.leanback.widget;

import android.os.Build;
import android.view.View;
import androidx.leanback.R;

/* JADX INFO: loaded from: classes3.dex */
final class RoundedRectHelper {
    static boolean supportsRoundedCorner() {
        return Build.VERSION.SDK_INT >= 21;
    }

    static void setClipToRoundedOutline(View view, boolean clip, int radius) {
        if (Build.VERSION.SDK_INT >= 21) {
            RoundedRectHelperApi21.setClipToRoundedOutline(view, clip, radius);
        }
    }

    static void setClipToRoundedOutline(View view, boolean clip) {
        if (Build.VERSION.SDK_INT >= 21) {
            int radius = view.getResources().getDimensionPixelSize(R.dimen.lb_rounded_rect_corner_radius);
            RoundedRectHelperApi21.setClipToRoundedOutline(view, clip, radius);
        }
    }

    private RoundedRectHelper() {
    }
}
