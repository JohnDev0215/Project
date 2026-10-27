package com.android.systemui.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.SurfaceControl;
import android.view.WindowManager;
import com.android.systemui.R;
import com.android.systemui.statusbar.wm.Util;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class BlurUtil {
    private static final float BITMAP_SCALE = 0.4f;
    private static final int BLUR_RADIUS = 7;
    public static final int BLUR_RADIUS_MAX = 25;

    public static Bitmap blur(Context context, Bitmap bitmap) {
        return blur(context, bitmap, 0.4f, 7);
    }

    public static Bitmap blur(Context context, Bitmap bitmap, float bitmap_scale) {
        return blur(context, bitmap, bitmap_scale, 7);
    }

    public static Bitmap blur(Context context, Bitmap bitmap, int blur_radius) {
        return blur(context, bitmap, 0.4f, blur_radius);
    }

    public static Bitmap blur(Context context, Bitmap bitmap, float bitmap_scale, int blur_radius) {
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, Math.round(bitmap.getWidth() * bitmap_scale), Math.round(bitmap.getHeight() * bitmap_scale), false);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapCreateScaledBitmap);
        RenderScript renderScriptCreate = RenderScript.create(context);
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScriptCreate, Element.U8_4(renderScriptCreate));
        Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(renderScriptCreate, bitmapCreateScaledBitmap);
        Allocation allocationCreateFromBitmap2 = Allocation.createFromBitmap(renderScriptCreate, bitmapCreateBitmap);
        scriptIntrinsicBlurCreate.setRadius(blur_radius);
        scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
        scriptIntrinsicBlurCreate.forEach(allocationCreateFromBitmap2);
        allocationCreateFromBitmap2.copyTo(bitmapCreateBitmap);
        renderScriptCreate.destroy();
        bitmap.recycle();
        bitmapCreateScaledBitmap.recycle();
        allocationCreateFromBitmap.destroy();
        allocationCreateFromBitmap2.destroy();
        scriptIntrinsicBlurCreate.destroy();
        return bitmapCreateBitmap;
    }

    public static Bitmap getScreenshot(Context context, int left, int top, int width, int height) {
        int rotation = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRotation();
        int i = context.getResources().getDisplayMetrics().widthPixels;
        int i2 = context.getResources().getDisplayMetrics().heightPixels;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.margin_bottom_navigation_bar_on_air_set);
        int navigationBarHeight = Util.getNavigationBarHeight(context);
        BydLogHelper.logD("getScreenshot mScreenWidth : " + i + " , mScreenHeight = " + i2 + " , marginBottom : " + dimensionPixelSize + " , navigationBarHeight : " + navigationBarHeight + " , statusBarHeight : " + Util.getStatusBarHeight(context));
        if (rotation == 1) {
            top = navigationBarHeight + dimensionPixelSize;
        } else if (rotation == 2) {
            left = (i - left) - width;
            top = navigationBarHeight + dimensionPixelSize;
        } else if (rotation == 3) {
            left = (i - left) - width;
            top = (i2 - height) - dimensionPixelSize;
        }
        int i3 = left + width;
        int i4 = top + height;
        BydLogHelper.logD("getScreenshot rotation : " + rotation + " , left = " + left + ", top = " + top + ", right = " + i3 + ", bottom = " + i4 + " ,width : " + width + " , height : " + height);
        return getBlurImage(context, SurfaceControl.screenshot(new Rect(left, top, i3, i4), width, height, rotation), 0.9f, 25.0f);
    }

    public static Bitmap getBlurImage(Context context, Bitmap bitmap, float ambiguity, float radius) {
        BydLogHelper.logD("getBlurImage: start");
        if (bitmap == null) {
            return null;
        }
        Bitmap bitmapCopy = bitmap.copy(Bitmap.Config.ARGB_8888, false);
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapCopy, Math.round(bitmapCopy.getWidth() * ambiguity), Math.round(bitmapCopy.getHeight() * ambiguity), false);
        bitmapCopy.recycle();
        RenderScript renderScriptCreate = RenderScript.create(context);
        Allocation allocationCreateFromBitmap = Allocation.createFromBitmap(renderScriptCreate, bitmapCreateScaledBitmap);
        Allocation allocationCreateTyped = Allocation.createTyped(renderScriptCreate, allocationCreateFromBitmap.getType());
        ScriptIntrinsicBlur scriptIntrinsicBlurCreate = ScriptIntrinsicBlur.create(renderScriptCreate, Element.U8_4(renderScriptCreate));
        scriptIntrinsicBlurCreate.setInput(allocationCreateFromBitmap);
        scriptIntrinsicBlurCreate.setRadius(radius);
        scriptIntrinsicBlurCreate.forEach(allocationCreateTyped);
        allocationCreateTyped.copyTo(bitmapCreateScaledBitmap);
        renderScriptCreate.destroy();
        BydLogHelper.logD("getBlurImage: end");
        return bitmapCreateScaledBitmap;
    }
}
