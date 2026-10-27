package com.android.systemui.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceControl;
import android.view.WindowManager;

/* JADX INFO: loaded from: classes.dex */
public class ScreenShotUtil {
    private static final String TAG = "ScreenShotUtil";
    private static volatile ScreenShotUtil instance;

    private ScreenShotUtil() {
    }

    public static ScreenShotUtil getInstance() {
        synchronized (ScreenShotUtil.class) {
            if (instance == null) {
                instance = new ScreenShotUtil();
            }
        }
        return instance;
    }

    public Bitmap cropBitmapTop(Bitmap srcBitmap, int needHeight, boolean recycleSrc) {
        if (srcBitmap == null) {
            Log.d(TAG, "srcBitmap == null");
            return null;
        }
        Log.d(TAG, "cropBitmapBottom before h : " + srcBitmap.getHeight());
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(), needHeight);
        Log.d(TAG, "cropBitmapBottom after h : " + bitmapCreateBitmap.getHeight());
        if (recycleSrc && !srcBitmap.equals(bitmapCreateBitmap) && !srcBitmap.isRecycled()) {
            srcBitmap.recycle();
        }
        return bitmapCreateBitmap;
    }

    public static Bitmap takeScreenShot(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getRealMetrics(displayMetrics);
        Rect rect = new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        Bitmap bitmapScreenshot = SurfaceControl.screenshot(rect, rect.width(), rect.height(), defaultDisplay.getRotation());
        if (bitmapScreenshot == null) {
            Log.e(TAG, "screenshot mScreenBitmap is null");
            return null;
        }
        bitmapScreenshot.setHasAlpha(false);
        bitmapScreenshot.prepareToDraw();
        return bitmapScreenshot.copy(Bitmap.Config.ARGB_8888, true);
    }

    public static Bitmap convertHardWareBitmap(Bitmap src) {
        if (src.getConfig() != Bitmap.Config.HARDWARE) {
            return src;
        }
        int width = src.getWidth();
        int height = src.getHeight();
        Picture picture = new Picture();
        picture.beginRecording(width, height).drawBitmap(src, 0.0f, 0.0f, (Paint) null);
        picture.endRecording();
        return Bitmap.createBitmap(picture, width, height, Bitmap.Config.ARGB_8888);
    }
}
