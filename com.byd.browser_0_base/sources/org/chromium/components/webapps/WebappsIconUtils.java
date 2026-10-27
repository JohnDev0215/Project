package org.chromium.components.webapps;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.util.Log;
import defpackage.ao3;
import defpackage.c6;
import defpackage.uc2;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public class WebappsIconUtils {
    public static int a(Context context, int i) {
        return Math.round(context.getResources().getDimension(i));
    }

    @CalledByNative
    public static Bitmap createHomeScreenIconFromWebIcon(Bitmap bitmap, boolean z) {
        int iMin = Math.min(Math.round(((ActivityManager) xz.a.getSystemService("activity")).getLauncherLargeIconSize() * 1.25f), Math.max(bitmap.getWidth(), bitmap.getHeight()));
        int iRound = 0;
        Rect rect = new Rect(0, 0, iMin, iMin);
        if (z) {
            iRound = Math.round(iMin * 0.15454549f);
        } else {
            int width = bitmap.getWidth() - 1;
            int height = bitmap.getHeight() - 1;
            if ((Color.alpha(bitmap.getPixel(0, 0)) == 0 || Color.alpha(bitmap.getPixel(width, height)) == 0 || Color.alpha(bitmap.getPixel(0, height)) == 0 || Color.alpha(bitmap.getPixel(width, 0)) == 0) ? false : true) {
                iRound = Math.round(iMin * 0.045454547f);
            }
        }
        int i = (iRound * 2) + iMin;
        rect.offset(iRound, iRound);
        try {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            Paint paint = new Paint(1);
            paint.setFilterBitmap(true);
            canvas.drawBitmap(bitmap, (Rect) null, rect, paint);
            return bitmapCreateBitmap;
        } catch (OutOfMemoryError unused) {
            Log.e("cr_WebappsIconUtils", "OutOfMemoryError while creating bitmap for home screen icon.");
            return bitmap;
        }
    }

    @CalledByNative
    public static Bitmap generateAdaptiveIconBitmap(Bitmap bitmap) {
        AdaptiveIconDrawable adaptiveIconDrawable = (AdaptiveIconDrawable) Icon.createWithAdaptiveBitmap(createHomeScreenIconFromWebIcon(bitmap, true)).loadDrawable(xz.a);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(adaptiveIconDrawable.getIntrinsicWidth(), adaptiveIconDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        adaptiveIconDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        adaptiveIconDrawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    @CalledByNative
    public static Bitmap generateHomeScreenIcon(GURL gurl, int i, int i2, int i3) {
        Context context = xz.a;
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        int launcherLargeIconSize = activityManager.getLauncherLargeIconSize();
        int launcherLargeIconDensity = activityManager.getLauncherLargeIconDensity();
        try {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(launcherLargeIconSize, launcherLargeIconSize, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            float f = launcherLargeIconSize;
            int i4 = (int) (0.083333336f * f);
            Rect rect = new Rect(0, 0, launcherLargeIconSize, launcherLargeIconSize);
            Drawable drawableC = c6.c(context.getResources(), R$mipmap.shortcut_icon_shadow, launcherLargeIconDensity);
            canvas.drawBitmap(drawableC instanceof BitmapDrawable ? ((BitmapDrawable) drawableC).getBitmap() : null, (Rect) null, rect, new Paint(2));
            int i5 = launcherLargeIconSize - (i4 * 2);
            Bitmap bitmapB = new uc2(i5, i5, Math.round(0.0625f * f), Color.rgb(i, i2, i3), Math.round(f * 0.33333334f)).b(gurl.j(), false);
            if (bitmapB == null) {
                return null;
            }
            float f2 = i4;
            canvas.drawBitmap(bitmapB, f2, f2, (Paint) null);
            return bitmapCreateBitmap;
        } catch (OutOfMemoryError unused) {
            Log.w("cr_WebappsIconUtils", "OutOfMemoryError while trying to draw bitmap on canvas.");
            return null;
        }
    }

    @CalledByNative
    public static int[] getIconSizes() {
        Context context = xz.a;
        int i = R$dimen.webapp_home_screen_icon_size;
        float dimension = context.getResources().getDimension(i);
        float f = context.getResources().getDisplayMetrics().density;
        return new int[]{a(context, i), Math.round((f - 1.0f) * (dimension / f)), a(context, R$dimen.webapp_splash_image_size_ideal), a(context, R$dimen.webapp_splash_image_size_minimum), a(context, R$dimen.webapk_monochrome_icon_size), a(context, R$dimen.webapk_adaptive_icon_size), ao3.a(context, 48.0f)};
    }

    @CalledByNative
    public static int getIdealIconCornerRadiusPxForPromptUI() {
        return xz.a.getResources().getDimensionPixelSize(R$dimen.webapk_prompt_ui_icon_radius);
    }

    @CalledByNative
    public static boolean isIconLargeEnoughForLauncher(int i, int i2) {
        int launcherLargeIconSize = ((ActivityManager) xz.a.getSystemService("activity")).getLauncherLargeIconSize() / 2;
        return i >= launcherLargeIconSize && i2 >= launcherLargeIconSize;
    }
}
