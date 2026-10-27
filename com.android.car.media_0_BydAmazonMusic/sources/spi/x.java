package spi;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Size;
import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: BitmapUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class x {
    @Nullable
    public static Bitmap a(@Nullable Bitmap bitmap, @ColorInt int i) {
        if (bitmap == null) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint());
        canvas.drawColor(i);
        return bitmapCreateBitmap;
    }

    public static Bitmap b(Drawable drawable, @Nullable Size size) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        Matrix matrix = new Matrix();
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            size = new Size(1, 1);
            drawable.setBounds(0, 0, size.getWidth(), size.getHeight());
        } else {
            if (size == null) {
                size = new Size(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            }
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            matrix.setRectToRect(new RectF(0.0f, 0.0f, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight()), new RectF(0.0f, 0.0f, size.getWidth(), size.getHeight()), Matrix.ScaleToFit.CENTER);
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(size.getWidth(), size.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.setMatrix(matrix);
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }
}
