package com.byd.card.drag;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public class BitmapManager {
    public static Bitmap copy(Bitmap paramBitmap, Bitmap.Config config, boolean isMutable) {
        return paramBitmap.copy(config, isMutable);
    }

    public static Bitmap createBitmap(int width, int height, Bitmap.Config config) {
        return Bitmap.createBitmap(width, height, config);
    }

    public static Bitmap createBitmap(Bitmap source, int x, int y, int width, int height, Matrix m, boolean filter) {
        return Bitmap.createBitmap(source, x, y, width, height, m, filter);
    }

    public static Bitmap getBitmap(Resources res, int id, BitmapFactory.Options opts) {
        return BitmapFactory.decodeResource(res, id, opts);
    }

    public static Bitmap getBitmap(Context context, int id) {
        return getBitmap(context.getResources(), id, Bitmap.Config.ARGB_8888);
    }

    public static Bitmap getBitmap(Context context, int id, Bitmap.Config config) {
        return getBitmap(context.getResources(), id, config);
    }

    public static Bitmap getBitmap(Resources resource, int id, Bitmap.Config config) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = config;
        return BitmapFactory.decodeResource(resource, id, options);
    }

    public static Bitmap getBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmapCreateBitmap = createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        drawable.setBounds(0, 0, bitmapCreateBitmap.getWidth(), bitmapCreateBitmap.getHeight());
        drawable.draw(new Canvas(bitmapCreateBitmap));
        return bitmapCreateBitmap;
    }

    public static Bitmap decodeByteArray(byte[] paramArrayOfByte) {
        return BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
    }

    public static Bitmap createScaledBitmap(Bitmap bitmap, int iconWidth, int iconHeight, boolean filter) {
        return Bitmap.createScaledBitmap(bitmap, iconWidth, iconHeight, filter);
    }
}
