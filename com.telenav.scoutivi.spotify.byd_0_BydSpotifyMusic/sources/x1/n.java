package x1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import java.util.concurrent.locks.Lock;

/* JADX INFO: compiled from: DrawableToBitmapConverter.java */
/* JADX INFO: loaded from: classes.dex */
final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final r1.d f9788a = new a();

    /* JADX INFO: compiled from: DrawableToBitmapConverter.java */
    class a extends r1.e {
        a() {
        }

        @Override // r1.e, r1.d
        public void d(Bitmap bitmap) {
        }
    }

    static q1.v<Bitmap> a(r1.d dVar, Drawable drawable, int i7, int i8) {
        Bitmap bitmapB;
        Drawable current = drawable.getCurrent();
        boolean z6 = false;
        if (current instanceof BitmapDrawable) {
            bitmapB = ((BitmapDrawable) current).getBitmap();
        } else if (current instanceof Animatable) {
            bitmapB = null;
        } else {
            bitmapB = b(dVar, current, i7, i8);
            z6 = true;
        }
        if (!z6) {
            dVar = f9788a;
        }
        return e.f(bitmapB, dVar);
    }

    private static Bitmap b(r1.d dVar, Drawable drawable, int i7, int i8) {
        if (i7 == Integer.MIN_VALUE && drawable.getIntrinsicWidth() <= 0) {
            if (Log.isLoggable("DrawableToBitmap", 5)) {
                Log.w("DrawableToBitmap", "Unable to draw " + drawable + " to Bitmap with Target.SIZE_ORIGINAL because the Drawable has no intrinsic width");
            }
            return null;
        }
        if (i8 == Integer.MIN_VALUE && drawable.getIntrinsicHeight() <= 0) {
            if (Log.isLoggable("DrawableToBitmap", 5)) {
                Log.w("DrawableToBitmap", "Unable to draw " + drawable + " to Bitmap with Target.SIZE_ORIGINAL because the Drawable has no intrinsic height");
            }
            return null;
        }
        if (drawable.getIntrinsicWidth() > 0) {
            i7 = drawable.getIntrinsicWidth();
        }
        if (drawable.getIntrinsicHeight() > 0) {
            i8 = drawable.getIntrinsicHeight();
        }
        Lock lockH = y.h();
        lockH.lock();
        Bitmap bitmapC = dVar.c(i7, i8, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmapC);
            drawable.setBounds(0, 0, i7, i8);
            drawable.draw(canvas);
            canvas.setBitmap(null);
            return bitmapC;
        } finally {
            lockH.unlock();
        }
    }
}
