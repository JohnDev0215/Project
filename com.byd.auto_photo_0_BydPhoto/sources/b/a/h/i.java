package b.a.h;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes2.dex */
public class i {

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f491a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f492b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f493c;
        int d;
        int e;
        int f = 5;
        int g;

        /* JADX INFO: Access modifiers changed from: private */
        public boolean b() {
            return this.f492b == 0 || this.f493c == 0;
        }

        public a c(int i) {
            this.g = i;
            return this;
        }

        public a d(int i) {
            this.e = i;
            return this;
        }

        public a e(int i) {
            this.f = i;
            return this;
        }

        public a f(int i) {
            this.f491a = i;
            return this;
        }

        public a g(int i, int i2) {
            this.f492b = i;
            this.f493c = i2;
            return this;
        }
    }

    public static Bitmap a(Bitmap bitmap, a aVar) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (aVar.b()) {
            aVar.g(width, height);
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(aVar.f492b, aVar.f493c, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        Rect rect = new Rect(0, 0, width, height);
        canvas.drawColor(0);
        int i = aVar.f;
        int i2 = (i > 0 || aVar.e > 0) ? i + aVar.e : 0;
        float f = aVar.f492b / 2.0f;
        float f2 = aVar.f493c / 2.0f;
        int i3 = aVar.f491a;
        if (i3 == 1) {
            RectF rectF = new RectF(rect);
            if (i2 > 0) {
                float f3 = i2;
                rectF.inset(f3, f3);
            }
            int i4 = aVar.d;
            canvas.drawRoundRect(rectF, i4, i4, paint);
        } else if (i3 == 2) {
            canvas.drawCircle(f, f2, Math.min(f, f2) - i2, paint);
        }
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Rect rect2 = new Rect(0, 0, aVar.f492b, aVar.f493c);
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        paint.setXfermode(null);
        if (aVar.f > 0) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(aVar.g);
            paint.setStrokeWidth(aVar.f);
            int i5 = aVar.f / 2;
            int i6 = aVar.f491a;
            if (i6 == 1) {
                RectF rectF2 = new RectF(rect2);
                float f4 = i5;
                rectF2.inset(f4, f4);
                int i7 = aVar.d;
                canvas.drawRoundRect(rectF2, i7, i7, paint);
            } else if (i6 == 2) {
                canvas.drawCircle(f, f2, Math.min(f, f2) - i5, paint);
            }
        }
        return bitmapCreateBitmap;
    }
}
