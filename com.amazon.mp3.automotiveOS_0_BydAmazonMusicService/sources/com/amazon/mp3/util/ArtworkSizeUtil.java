package com.amazon.mp3.util;

import android.app.Activity;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes4.dex */
public final class ArtworkSizeUtil {
    private static int sFullWidthArtworkSize;
    private static int sGridArtworkSize;

    public static synchronized int getGridArtworkSize(Activity activity) {
        if (sGridArtworkSize == 0) {
            calculateArtworkSize(activity);
        }
        return sGridArtworkSize;
    }

    public static synchronized int getFullWidthArtworkSize(Activity activity) {
        if (sFullWidthArtworkSize == 0) {
            calculateArtworkSize(activity);
        }
        return sFullWidthArtworkSize;
    }

    public static DisplayMetrics getDisplayMetrics(Activity activity) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics;
    }

    private static void calculateArtworkSize(Activity activity) {
        Resources resources = activity.getResources();
        DisplayMetrics displayMetrics = getDisplayMetrics(activity);
        int integer = resources.getInteger(R.integer.album_tab_num_columns_portrait);
        int dimensionPixelSize = ((integer - 1) * resources.getDimensionPixelSize(R.dimen.album_tab_inside_padding)) + (resources.getDimensionPixelSize(R.dimen.album_tab_outside_padding) * 2);
        int iMin = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
        sFullWidthArtworkSize = iMin;
        sGridArtworkSize = (iMin - dimensionPixelSize) / integer;
    }
}
