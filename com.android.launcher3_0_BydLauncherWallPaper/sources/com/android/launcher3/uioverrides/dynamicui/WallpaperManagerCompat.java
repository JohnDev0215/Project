package com.android.launcher3.uioverrides.dynamicui;

import android.content.Context;
import com.android.launcher3.Utilities;

/* JADX INFO: loaded from: classes4.dex */
public abstract class WallpaperManagerCompat {
    private static WallpaperManagerCompat sInstance;
    private static final Object sInstanceLock = new Object();

    public interface OnColorsChangedListenerCompat {
        void onColorsChanged(WallpaperColorsCompat colors, int which);
    }

    public abstract void addOnColorsChangedListener(OnColorsChangedListenerCompat listener);

    public abstract WallpaperColorsCompat getWallpaperColors(int which);

    public static WallpaperManagerCompat getInstance(Context context) {
        WallpaperManagerCompat wallpaperManagerCompat;
        synchronized (sInstanceLock) {
            if (sInstance == null) {
                Context applicationContext = context.getApplicationContext();
                if (Utilities.ATLEAST_OREO_MR1) {
                    try {
                        sInstance = new WallpaperManagerCompatVOMR1(applicationContext);
                    } catch (Throwable unused) {
                    }
                }
                if (sInstance == null) {
                    sInstance = new WallpaperManagerCompatVL(applicationContext);
                }
            }
            wallpaperManagerCompat = sInstance;
        }
        return wallpaperManagerCompat;
    }
}
