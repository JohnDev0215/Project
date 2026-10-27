package com.android.launcher3.uioverrides.dynamicui;

import android.app.WallpaperColors;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import com.android.launcher3.uioverrides.dynamicui.WallpaperManagerCompat;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes4.dex */
public class WallpaperManagerCompatVOMR1 extends WallpaperManagerCompat {
    private static final String TAG = "WMCompatVOMR1";
    private Method mWCColorHintsMethod;
    private final WallpaperManager mWm;

    WallpaperManagerCompatVOMR1(Context context) throws Throwable {
        this.mWm = (WallpaperManager) context.getSystemService(WallpaperManager.class);
        try {
            this.mWCColorHintsMethod = WallpaperColors.class.getDeclaredMethod("getColorHints", new Class[0]);
        } catch (Exception e) {
            Log.e(TAG, "getColorHints not available", e);
        }
    }

    @Override // com.android.launcher3.uioverrides.dynamicui.WallpaperManagerCompat
    public WallpaperColorsCompat getWallpaperColors(int which) {
        return convertColorsObject(this.mWm.getWallpaperColors(which));
    }

    @Override // com.android.launcher3.uioverrides.dynamicui.WallpaperManagerCompat
    public void addOnColorsChangedListener(final WallpaperManagerCompat.OnColorsChangedListenerCompat listener) {
        this.mWm.addOnColorsChangedListener(new WallpaperManager.OnColorsChangedListener() { // from class: com.android.launcher3.uioverrides.dynamicui.WallpaperManagerCompatVOMR1.1
            @Override // android.app.WallpaperManager.OnColorsChangedListener
            public void onColorsChanged(WallpaperColors colors, int which) {
                listener.onColorsChanged(WallpaperManagerCompatVOMR1.this.convertColorsObject(colors), which);
            }
        }, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WallpaperColorsCompat convertColorsObject(WallpaperColors colors) {
        if (colors == null) {
            return null;
        }
        Color primaryColor = colors.getPrimaryColor();
        Color secondaryColor = colors.getSecondaryColor();
        Color tertiaryColor = colors.getTertiaryColor();
        int iIntValue = 0;
        int argb = primaryColor != null ? primaryColor.toArgb() : 0;
        int argb2 = secondaryColor != null ? secondaryColor.toArgb() : 0;
        int argb3 = tertiaryColor != null ? tertiaryColor.toArgb() : 0;
        try {
            Method method = this.mWCColorHintsMethod;
            if (method != null) {
                iIntValue = ((Integer) method.invoke(colors, new Object[0])).intValue();
            }
        } catch (Exception e) {
            Log.e(TAG, "error calling color hints", e);
        }
        return new WallpaperColorsCompat(argb, argb2, argb3, iIntValue);
    }
}
