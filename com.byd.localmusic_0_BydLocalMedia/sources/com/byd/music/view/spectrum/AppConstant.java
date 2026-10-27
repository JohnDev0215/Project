package com.byd.music.view.spectrum;

import android.graphics.Color;

/* JADX INFO: loaded from: classes5.dex */
public class AppConstant {
    public static final int ALPHA = 255;
    public static final int BLUE = 255;
    public static final int COLOR = Color.argb(255, 200, 200, 200);
    public static final int GREEN = 255;
    public static final int LAGER_OFFSET;
    public static final int LUMP_COUNT = 256;
    public static final int RED = 255;
    public static boolean imageValue = true;
    public static boolean isFFT = true;

    static {
        LAGER_OFFSET = isFFT ? 3 : 0;
    }
}
