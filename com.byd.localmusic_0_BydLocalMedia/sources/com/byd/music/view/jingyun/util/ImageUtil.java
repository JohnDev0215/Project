package com.byd.music.view.jingyun.util;

import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.Rect;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.palette.graphics.Palette;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public class ImageUtil {
    public static Palette.Swatch getColor(Bitmap bitmap, int i) {
        Palette.Swatch vibrantSwatch;
        Palette paletteGenerate = Palette.generate(bitmap);
        if (i == 0) {
            vibrantSwatch = paletteGenerate.getVibrantSwatch();
        } else if (i == 1) {
            vibrantSwatch = paletteGenerate.getLightVibrantSwatch();
        } else if (i == 2) {
            vibrantSwatch = paletteGenerate.getDarkVibrantSwatch();
        } else if (i == 3) {
            vibrantSwatch = paletteGenerate.getMutedSwatch();
        } else if (i == 4) {
            vibrantSwatch = paletteGenerate.getLightMutedSwatch();
        } else if (i == 5) {
            vibrantSwatch = paletteGenerate.getDarkMutedSwatch();
        } else {
            vibrantSwatch = paletteGenerate.getVibrantSwatch();
        }
        return vibrantSwatch == null ? paletteGenerate.getVibrantSwatch() : vibrantSwatch;
    }

    public static Bitmap createVideoThumbnail(String str, int i, int i2) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                if (Build.VERSION.SDK_INT >= 14) {
                    mediaMetadataRetriever.setDataSource(str, new HashMap());
                } else {
                    mediaMetadataRetriever.setDataSource(str);
                }
                Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime();
                try {
                    mediaMetadataRetriever.release();
                    return frameAtTime;
                } catch (RuntimeException unused) {
                    return frameAtTime;
                }
            } catch (IllegalArgumentException | RuntimeException unused2) {
                mediaMetadataRetriever.release();
                return null;
            } catch (Throwable th) {
                try {
                    mediaMetadataRetriever.release();
                } catch (RuntimeException unused3) {
                }
                throw th;
            }
        } catch (RuntimeException unused4) {
            return null;
        }
    }

    public static ViewOutlineProvider getOutline(boolean z, final int i, final int i2) {
        if (z) {
            return new ViewOutlineProvider() { // from class: com.byd.music.view.jingyun.util.ImageUtil.1
                @Override // android.view.ViewOutlineProvider
                public void getOutline(View view, Outline outline) {
                    int iMin = Math.min(view.getWidth(), view.getHeight()) / i;
                    outline.setOval(iMin, iMin, view.getWidth() - iMin, view.getHeight() - iMin);
                }
            };
        }
        return new ViewOutlineProvider() { // from class: com.byd.music.view.jingyun.util.ImageUtil.2
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline outline) {
                int iMin = Math.min(view.getWidth(), view.getHeight()) / i;
                outline.setRoundRect(iMin, iMin, view.getWidth() - iMin, view.getHeight() - iMin, i2);
            }
        };
    }

    public static void centerCrop(Rect rect, Rect rect2) {
        if (rect.height() / rect2.height() > rect.width() / rect2.width()) {
            rect.left = 0;
            rect.top = (rect.height() - ((rect2.height() * rect.width()) / rect2.width())) / 2;
            rect.bottom -= rect.top;
        } else {
            rect.top = 0;
            rect.left = (rect.width() - ((rect2.width() * rect.height()) / rect2.height())) / 2;
            rect.right -= rect.left;
        }
    }
}
