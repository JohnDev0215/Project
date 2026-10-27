package com.android.car.apps.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.PathParser;

/* JADX INFO: loaded from: classes5.dex */
public final class IconCropper {
    private static final float ICON_MASK_SIZE = 100.0f;
    private final Path mIconMask;

    public IconCropper(Path iconMask) {
        this.mIconMask = iconMask;
    }

    public IconCropper(Context context) {
        this(getDefaultMask(context));
    }

    private static Path getDefaultMask(Context context) {
        return PathParser.createPathFromPathData(context.getString(R.string.config_crop_icon_mask));
    }

    public Bitmap crop(Drawable source) {
        return crop(BitmapUtils.fromDrawable(source, null));
    }

    public Bitmap crop(Bitmap icon) {
        int width = icon.getWidth();
        int height = icon.getHeight();
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(-1);
        canvas.save();
        canvas.scale(width / 100.0f, height / 100.0f);
        canvas.drawPath(this.mIconMask, paint);
        canvas.restore();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(icon, 0.0f, 0.0f, paint);
        return output;
    }
}
