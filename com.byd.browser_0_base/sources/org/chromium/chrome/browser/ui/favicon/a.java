package org.chromium.chrome.browser.ui.favicon;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import defpackage.qc2;
import defpackage.uc2;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: FaviconUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    public static uc2 a(Context context) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.circular_monogram_size);
        return new uc2(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize / 2, context.getColor(R$color.default_favicon_background_color), r0.getDimensionPixelSize(R$dimen.circular_monogram_text_size));
    }

    public static uc2 b(Context context, int i) {
        Resources resources = context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.circular_monogram_size);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R$dimen.circular_monogram_text_size);
        if (i < 0) {
            i = dimensionPixelSize / 2;
        }
        return new uc2(dimensionPixelSize, dimensionPixelSize, i, context.getColor(R$color.default_favicon_background_color), dimensionPixelSize2);
    }

    public static qc2 c(Resources resources, Bitmap bitmap) {
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.default_favicon_corner_radius);
        qc2 qc2Var = new qc2(resources, bitmap);
        qc2Var.b(dimensionPixelSize);
        return qc2Var;
    }

    public static uc2 d(Context context) {
        Resources resources = context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.default_favicon_size);
        return new uc2(dimensionPixelSize, dimensionPixelSize, resources.getDimensionPixelSize(R$dimen.default_favicon_corner_radius), context.getColor(R$color.default_favicon_background_color), resources.getDimensionPixelSize(R$dimen.default_favicon_icon_text_size));
    }

    @Deprecated
    public static Drawable e(Bitmap bitmap, String str, int i, uc2 uc2Var, Resources resources, int i2) {
        if (bitmap != null) {
            return c(resources, Bitmap.createScaledBitmap(bitmap, i2, i2, false));
        }
        uc2Var.e.setColor(i);
        return new BitmapDrawable(resources, uc2Var.b(str, false));
    }

    public static Drawable f(Bitmap bitmap, GURL gurl, int i, uc2 uc2Var, Resources resources, int i2) {
        return e(bitmap, gurl.j(), i, uc2Var, resources, i2);
    }
}
