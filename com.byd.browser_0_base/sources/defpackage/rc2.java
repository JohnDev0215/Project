package defpackage;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: RoundedBitmapDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class rc2 extends Drawable {
    public final Bitmap a;
    public final int b;
    public final BitmapShader e;
    public float g;
    public boolean k;
    public int l;
    public int m;
    public int c = 119;
    public final Paint d = new Paint(3);
    public final Matrix f = new Matrix();
    public final Rect h = new Rect();
    public final RectF i = new RectF();
    public boolean j = true;

    public rc2(Resources resources, Bitmap bitmap) {
        this.b = 160;
        if (resources != null) {
            this.b = resources.getDisplayMetrics().densityDpi;
        }
        this.a = bitmap;
        if (bitmap == null) {
            this.m = -1;
            this.l = -1;
            this.e = null;
        } else {
            int i = this.b;
            this.l = bitmap.getScaledWidth(i);
            this.m = bitmap.getScaledHeight(i);
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            this.e = new BitmapShader(bitmap, tileMode, tileMode);
        }
    }

    public abstract void a(int i, int i2, int i3, Rect rect, Rect rect2);

    public final void b(float f) {
        if (this.g == f) {
            return;
        }
        this.k = false;
        boolean z = f > 0.05f;
        Paint paint = this.d;
        if (z) {
            paint.setShader(this.e);
        } else {
            paint.setShader(null);
        }
        this.g = f;
        invalidateSelf();
    }

    public final void c() {
        if (this.j) {
            boolean z = this.k;
            Rect rect = this.h;
            if (z) {
                int iMin = Math.min(this.l, this.m);
                a(this.c, iMin, iMin, getBounds(), this.h);
                int iMin2 = Math.min(rect.width(), rect.height());
                rect.inset(Math.max(0, (rect.width() - iMin2) / 2), Math.max(0, (rect.height() - iMin2) / 2));
                this.g = iMin2 * 0.5f;
            } else {
                a(this.c, this.l, this.m, getBounds(), this.h);
            }
            RectF rectF = this.i;
            rectF.set(rect);
            BitmapShader bitmapShader = this.e;
            if (bitmapShader != null) {
                Matrix matrix = this.f;
                matrix.setTranslate(rectF.left, rectF.top);
                float fWidth = rectF.width();
                Bitmap bitmap = this.a;
                matrix.preScale(fWidth / bitmap.getWidth(), rectF.height() / bitmap.getHeight());
                bitmapShader.setLocalMatrix(matrix);
                this.d.setShader(bitmapShader);
            }
            this.j = false;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Bitmap bitmap = this.a;
        if (bitmap == null) {
            return;
        }
        c();
        Paint paint = this.d;
        if (paint.getShader() == null) {
            canvas.drawBitmap(bitmap, (Rect) null, this.h, paint);
            return;
        }
        RectF rectF = this.i;
        float f = this.g;
        canvas.drawRoundRect(rectF, f, f, paint);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.d.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        return this.d.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.m;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.l;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        Bitmap bitmap;
        if (this.c == 119 && !this.k && (bitmap = this.a) != null && !bitmap.hasAlpha() && this.d.getAlpha() >= 255) {
            if (!(this.g > 0.05f)) {
                return -1;
            }
        }
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (this.k) {
            this.g = Math.min(this.m, this.l) / 2;
        }
        this.j = true;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        Paint paint = this.d;
        if (i != paint.getAlpha()) {
            paint.setAlpha(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.d.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setDither(boolean z) {
        this.d.setDither(z);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setFilterBitmap(boolean z) {
        this.d.setFilterBitmap(z);
        invalidateSelf();
    }
}
