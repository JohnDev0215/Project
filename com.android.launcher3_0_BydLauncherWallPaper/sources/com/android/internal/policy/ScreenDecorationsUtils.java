package com.android.internal.policy;

import android.content.res.Resources;
import com.android.internal.R;

/* JADX INFO: loaded from: classes4.dex */
public class ScreenDecorationsUtils {
    public static float getWindowCornerRadius(Resources resources) {
        if (!supportsRoundedCornersOnWindows(resources)) {
            return 0.0f;
        }
        float dimension = resources.getDimension(R.dimen.rounded_corner_radius) - resources.getDimension(R.dimen.rounded_corner_radius_adjustment);
        float dimension2 = resources.getDimension(R.dimen.rounded_corner_radius_top) - resources.getDimension(R.dimen.rounded_corner_radius_top_adjustment);
        if (dimension2 == 0.0f) {
            dimension2 = dimension;
        }
        float dimension3 = resources.getDimension(R.dimen.rounded_corner_radius_bottom) - resources.getDimension(R.dimen.rounded_corner_radius_bottom_adjustment);
        if (dimension3 != 0.0f) {
            dimension = dimension3;
        }
        return Math.min(dimension2, dimension);
    }

    public static boolean supportsRoundedCornersOnWindows(Resources resources) {
        return resources.getBoolean(R.bool.config_supportsRoundedCornersOnWindows);
    }
}
