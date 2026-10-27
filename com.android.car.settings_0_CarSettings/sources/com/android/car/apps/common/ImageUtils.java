package com.android.car.apps.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Size;

/* JADX INFO: loaded from: classes5.dex */
public class ImageUtils {
    private static final float MAX_BLUR = 25.0f;
    private static final float MIN_BLUR = 0.1f;

    public static Bitmap blur(Context context, Bitmap image, Size bitmapTargetSize, float bitmapBlurPercent) {
        Bitmap image2 = maybeResize(image, bitmapTargetSize);
        float blurRadius = getBitmapDimension(image2) * bitmapBlurPercent;
        if (blurRadius <= MIN_BLUR) {
            return image2;
        }
        if (blurRadius > MAX_BLUR) {
            blurRadius = MAX_BLUR;
        }
        if (image2.getConfig() != Bitmap.Config.ARGB_8888) {
            image2 = image2.copy(Bitmap.Config.ARGB_8888, true);
        }
        Bitmap outputBitmap = Bitmap.createBitmap(image2);
        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        Allocation tmpIn = Allocation.createFromBitmap(rs, image2);
        Allocation tmpOut = Allocation.createFromBitmap(rs, outputBitmap);
        theIntrinsic.setRadius(blurRadius);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);
        return outputBitmap;
    }

    private static Bitmap maybeResize(Bitmap image, Size bitmapTargetSize) {
        if (image.getWidth() > bitmapTargetSize.getWidth() || image.getHeight() > bitmapTargetSize.getHeight()) {
            int imageDim = getBitmapDimension(image);
            float scale = getAverage(bitmapTargetSize) / imageDim;
            int width = Math.round(image.getWidth() * scale);
            int height = Math.round(image.getHeight() * scale);
            return Bitmap.createScaledBitmap(image, width, height, false);
        }
        return image;
    }

    private static int getAverage(Size size) {
        return (size.getWidth() + size.getHeight()) / 2;
    }

    private static int getBitmapDimension(Bitmap image) {
        return (image.getWidth() + image.getHeight()) / 2;
    }
}
