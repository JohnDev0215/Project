package com.blankj.utilcode.util;

import android.app.backup.FullBackup;
import android.graphics.Color;
import android.media.AudioSystem;
import androidx.core.content.ContextCompat;

/* JADX INFO: loaded from: classes4.dex */
public final class ColorUtils {
    public static int setAlphaComponent(int i, float f) {
        return (i & 16777215) | (((int) ((f * 255.0f) + 0.5f)) << 24);
    }

    public static int setAlphaComponent(int i, int i2) {
        return (i & 16777215) | (i2 << 24);
    }

    public static int setBlueComponent(int i, float f) {
        return (i & (-256)) | ((int) ((f * 255.0f) + 0.5f));
    }

    public static int setBlueComponent(int i, int i2) {
        return (i & (-256)) | i2;
    }

    public static int setGreenComponent(int i, float f) {
        return (i & Color.MAGENTA) | (((int) ((f * 255.0f) + 0.5f)) << 8);
    }

    public static int setGreenComponent(int i, int i2) {
        return (i & Color.MAGENTA) | (i2 << 8);
    }

    public static int setRedComponent(int i, float f) {
        return (i & Color.CYAN) | (((int) ((f * 255.0f) + 0.5f)) << 16);
    }

    public static int setRedComponent(int i, int i2) {
        return (i & Color.CYAN) | (i2 << 16);
    }

    private ColorUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static int getColor(int i) {
        return ContextCompat.getColor(Utils.getApp(), i);
    }

    public static int string2Int(String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'colorString' of type String (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
        }
        return Color.parseColor(str);
    }

    public static String int2RgbString(int i) {
        String hexString = Integer.toHexString(i & 16777215);
        while (hexString.length() < 6) {
            hexString = AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS + hexString;
        }
        return "#" + hexString;
    }

    public static String int2ArgbString(int i) {
        String hexString = Integer.toHexString(i);
        while (hexString.length() < 6) {
            hexString = AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS + hexString;
        }
        while (hexString.length() < 8) {
            hexString = FullBackup.FILES_TREE_TOKEN + hexString;
        }
        return "#" + hexString;
    }

    public static int getRandomColor() {
        return getRandomColor(true);
    }

    public static int getRandomColor(boolean z) {
        return (z ? ((int) (Math.random() * 256.0d)) << 24 : -16777216) | ((int) (Math.random() * 1.6777216E7d));
    }
}
