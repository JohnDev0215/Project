package org.chromium.ui.resources.statics;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.ui.resources.Resource;

/* JADX INFO: loaded from: classes4.dex */
public class StaticResource implements Resource {
    private final Bitmap mBitmap;
    private final Rect mBitmapSize;
    private final NinePatchData mNinePatchData;

    public StaticResource(Bitmap bitmap) {
        this.mBitmap = bitmap;
        this.mNinePatchData = NinePatchData.create(bitmap);
        this.mBitmapSize = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
    }

    @Override // org.chromium.ui.resources.Resource
    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    @Override // org.chromium.ui.resources.Resource
    public Rect getBitmapSize() {
        return this.mBitmapSize;
    }

    @Override // org.chromium.ui.resources.Resource
    public Rect getPadding() {
        NinePatchData ninePatchData = this.mNinePatchData;
        return ninePatchData != null ? ninePatchData.getPadding() : this.mBitmapSize;
    }

    @Override // org.chromium.ui.resources.Resource
    public Rect getAperture() {
        NinePatchData ninePatchData = this.mNinePatchData;
        return ninePatchData != null ? ninePatchData.getAperture() : this.mBitmapSize;
    }

    public static StaticResource create(Resources resources, int i, int i2, int i3) {
        if (i <= 0) {
            return null;
        }
        Bitmap bitmapDecodeBitmap = decodeBitmap(resources, i, i2, i3);
        if (bitmapDecodeBitmap == null) {
            bitmapDecodeBitmap = decodeDrawable(resources, i, i2, i3);
        }
        if (bitmapDecodeBitmap == null) {
            return null;
        }
        return new StaticResource(bitmapDecodeBitmap);
    }

    private static Bitmap decodeBitmap(Resources resources, int i, int i2, int i3) {
        BitmapFactory.Options optionsCreateOptions = createOptions(resources, i, i2, i3);
        optionsCreateOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(resources, i, optionsCreateOptions);
        if (bitmapDecodeResource == null) {
            return null;
        }
        if (bitmapDecodeResource.getConfig() == optionsCreateOptions.inPreferredConfig) {
            return bitmapDecodeResource;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeResource.getWidth(), bitmapDecodeResource.getHeight(), optionsCreateOptions.inPreferredConfig);
        new Canvas(bitmapCreateBitmap).drawBitmap(bitmapDecodeResource, 0.0f, 0.0f, (Paint) null);
        bitmapDecodeResource.recycle();
        return bitmapCreateBitmap;
    }

    private static Bitmap decodeDrawable(Resources resources, int i, int i2, int i3) {
        try {
            Drawable drawable = ApiCompatibilityUtils.getDrawable(resources, i);
            int iMax = Math.max(drawable.getMinimumWidth(), Math.max(i2, 1));
            int iMax2 = Math.max(drawable.getMinimumHeight(), Math.max(i3, 1));
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iMax, iMax2, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawable.setBounds(0, 0, iMax, iMax2);
            drawable.draw(canvas);
            return bitmapCreateBitmap;
        } catch (Resources.NotFoundException unused) {
            return null;
        }
    }

    private static BitmapFactory.Options createOptions(Resources resources, int i, int i2, int i3) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        if (i2 != 0 && i3 != 0) {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(resources, i, options);
            options.inJustDecodeBounds = false;
            if (options.outHeight <= i3 && options.outWidth <= i2) {
                return options;
            }
            options.inSampleSize = Math.min(Math.round(options.outHeight / i3), Math.round(options.outWidth / i2));
        }
        return options;
    }
}
