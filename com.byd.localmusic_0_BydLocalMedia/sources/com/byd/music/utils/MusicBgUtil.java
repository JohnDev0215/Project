package com.byd.music.utils;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.ObservableInt;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.Utils;

/* JADX INFO: loaded from: classes5.dex */
public class MusicBgUtil {
    private static final String TAG = "MusicBgUtil";
    public static int darkBodyColor = -1712392705;
    public static int darkTitleColor = -16777216;
    private static boolean isHighLight = false;
    private static Drawable lastBgDrawable = null;
    public static int lightTitleColor = -1116673;
    public static ObservableInt obDarkTitleColor = new ObservableInt(-16777216);
    public static ObservableInt obDarkBodyColor = new ObservableInt(-1728053248);
    public static ObservableInt obLightTitleColor = new ObservableInt(-1116673);

    public static int setAlphaComponent(int i, int i2) {
        if (i2 < 0 || i2 > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (i & 16777215) | (i2 << 24);
    }

    public static boolean isLightColor(int i) {
        return 1.0d - ((((((double) Color.red(i)) * 0.299d) + (((double) Color.green(i)) * 0.587d)) + (((double) Color.blue(i)) * 0.114d)) / 255.0d) < 0.5d;
    }

    public static void traverseViewGroup(View view, int i) {
        if (view == null) {
            return;
        }
        if (!(view instanceof ViewGroup)) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(i);
                return;
            } else {
                if (view instanceof ImageView) {
                    ((ImageView) view).setImageTintList(ColorStateList.valueOf(i));
                    return;
                }
                return;
            }
        }
        int i2 = 0;
        while (true) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (i2 >= viewGroup.getChildCount()) {
                return;
            }
            traverseViewGroup(viewGroup.getChildAt(i2), i);
            i2++;
        }
    }

    public static boolean isNight() {
        return (Utils.getApp().getResources().getConfiguration().uiMode & 48) == 32;
    }

    public static void destroyMusicBg() {
        lastBgDrawable = null;
    }

    private static byte[] getCompressBgBytes(Drawable drawable, View view) {
        return ImageUtils.bitmap2Bytes(ImageUtils.scale(BlurBitmapUtil.blurBitmap(ImageUtils.bytes2Bitmap(ImageUtils.compressByQuality(ImageUtils.drawable2Bitmap(drawable), 50)), 25.0f), view.getWidth(), view.getHeight()), Bitmap.CompressFormat.JPEG);
    }

    private static Bitmap getBgClipBitmap(Drawable drawable, int i, int i2, int i3, int i4) {
        return ImageUtils.clip(ImageUtils.scale(BlurBitmapUtil.blurBitmap(ImageUtils.bytes2Bitmap(ImageUtils.compressByQuality(ImageUtils.drawable2Bitmap(drawable), 50)), 25.0f), i, i2), 0, 0, i3, i4);
    }
}
