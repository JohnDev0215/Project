package com.android.launcher3.graphics;

import android.content.Context;
import android.graphics.Color;
import androidx.core.graphics.ColorUtils;
import com.android.launcher3.R;
import com.android.launcher3.icons.GraphicsUtils;
import com.android.launcher3.util.Themes;

/* JADX INFO: loaded from: classes4.dex */
public class IconPalette {
    private static final boolean DEBUG = false;
    private static final float MIN_PRELOAD_COLOR_LIGHTNESS = 0.6f;
    private static final float MIN_PRELOAD_COLOR_SATURATION = 0.2f;
    private static final String TAG = "IconPalette";

    public static int getPreloadProgressColor(Context context, int dominantColor) {
        float[] fArr = new float[3];
        Color.colorToHSV(dominantColor, fArr);
        if (fArr[1] < 0.2f) {
            return Themes.getColorAccent(context);
        }
        fArr[2] = Math.max(0.6f, fArr[2]);
        return Color.HSVToColor(fArr);
    }

    public static int resolveContrastColor(Context context, int color, int background) {
        return ensureTextContrast(resolveColor(context, color), background);
    }

    private static int resolveColor(Context context, int color) {
        return color == 0 ? context.getColor(R.color.notification_icon_default_color) : color;
    }

    private static String contrastChange(int colorOld, int colorNew, int bg) {
        return String.format("from %.2f:1 to %.2f:1", Double.valueOf(ColorUtils.calculateContrast(colorOld, bg)), Double.valueOf(ColorUtils.calculateContrast(colorNew, bg)));
    }

    private static int ensureTextContrast(int color, int bg) {
        return findContrastColor(color, bg, 4.5d);
    }

    private static int findContrastColor(int fg, int bg, double minRatio) {
        if (ColorUtils.calculateContrast(fg, bg) >= minRatio) {
            return fg;
        }
        double[] dArr = new double[3];
        ColorUtils.colorToLAB(bg, dArr);
        double d = dArr[0];
        ColorUtils.colorToLAB(fg, dArr);
        double d2 = dArr[0];
        boolean z = d < 50.0d;
        double d3 = z ? d2 : 0.0d;
        if (z) {
            d2 = 100.0d;
        }
        double d4 = dArr[1];
        double d5 = dArr[2];
        for (int i = 0; i < 15 && d2 - d3 > 1.0E-5d; i++) {
            double d6 = (d3 + d2) / 2.0d;
            if (ColorUtils.calculateContrast(ColorUtils.LABToColor(d6, d4, d5), bg) <= minRatio ? !z : z) {
                d2 = d6;
            } else {
                d3 = d6;
            }
        }
        return ColorUtils.LABToColor(d3, d4, d5);
    }

    public static int getMutedColor(int color, float whiteScrimAlpha) {
        return ColorUtils.compositeColors(GraphicsUtils.setColorAlphaBound(-1, (int) (whiteScrimAlpha * 255.0f)), color);
    }
}
