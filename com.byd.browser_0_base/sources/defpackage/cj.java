package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/* JADX INFO: compiled from: BrandingSecurityButtonAnimationDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class cj {
    public boolean a;
    public int b;
    public final ImageView c;

    public cj(ImageView imageView) {
        this.c = imageView;
    }

    public static BitmapDrawable a(Resources resources, Drawable drawable, int i, int i2) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicHeight > i2 || intrinsicWidth > i) {
            throw new IllegalArgumentException("The input drawable has a larger size than the target width / height.");
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        int i3 = (i - intrinsicWidth) / 2;
        int i4 = (i2 - intrinsicHeight) / 2;
        drawable.setBounds(i3, i4, i - i3, i2 - i4);
        drawable.draw(canvas);
        return new BitmapDrawable(resources, bitmapCreateBitmap);
    }
}
