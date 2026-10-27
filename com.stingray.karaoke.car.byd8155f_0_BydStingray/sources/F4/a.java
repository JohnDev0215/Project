package F4;

import A7.m;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f2409a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final a f2410b = new a();

    public Bitmap a(Drawable drawable, int i7, int i10) {
        m.f(drawable, "drawable");
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() == null) {
                throw new IllegalArgumentException("bitmap is null");
            }
            if (config == null || bitmapDrawable.getBitmap().getConfig() == config) {
                return (i7 == bitmapDrawable.getBitmap().getWidth() && i10 == bitmapDrawable.getBitmap().getHeight()) ? bitmapDrawable.getBitmap() : Bitmap.createScaledBitmap(bitmapDrawable.getBitmap(), i7, i10, true);
            }
        }
        Rect bounds = drawable.getBounds();
        int i11 = bounds.left;
        int i12 = bounds.top;
        int i13 = bounds.right;
        int i14 = bounds.bottom;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i7, i10, config);
        drawable.setBounds(0, 0, i7, i10);
        drawable.draw(new Canvas(bitmapCreateBitmap));
        drawable.setBounds(i11, i12, i13, i14);
        return bitmapCreateBitmap;
    }
}
