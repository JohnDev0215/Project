package androidx.leanback.graphics;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.SparseArray;

/* JADX INFO: loaded from: classes3.dex */
public final class ColorFilterCache {
    private static final SparseArray<ColorFilterCache> sColorToFiltersMap = new SparseArray<>();
    private final PorterDuffColorFilter[] mFilters = new PorterDuffColorFilter[256];

    public static ColorFilterCache getColorFilterCache(int color) {
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        int color2 = Color.rgb(r, g, b);
        ColorFilterCache filters = sColorToFiltersMap.get(color2);
        if (filters == null) {
            ColorFilterCache filters2 = new ColorFilterCache(r, g, b);
            sColorToFiltersMap.put(color2, filters2);
            return filters2;
        }
        return filters;
    }

    private ColorFilterCache(int r, int g, int b) {
        for (int i = 0; i <= 255; i++) {
            int color = Color.argb(i, r, g, b);
            this.mFilters[i] = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        }
    }

    public ColorFilter getFilterForLevel(float level) {
        if (level >= 0.0f && level <= 1.0d) {
            int filterIndex = (int) (255.0f * level);
            return this.mFilters[filterIndex];
        }
        return null;
    }
}
