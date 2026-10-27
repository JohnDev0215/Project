package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import org.chromium.components.browser_ui.widget.R$dimen;

/* JADX INFO: compiled from: ArrowBubbleDrawable.java */
/* JADX INFO: loaded from: classes2.dex */
public final class s8 extends Drawable implements Drawable.Callback {
    public final Rect e = new Rect();
    public final int f;
    public final int g;
    public final Path h;
    public final Paint i;
    public final ShapeDrawable j;
    public final boolean k;
    public final Context l;
    public int m;
    public int n;
    public boolean o;
    public boolean p;

    public s8(Context context, boolean z) {
        this.l = context;
        this.k = z;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.text_bubble_arrow_width);
        this.f = dimensionPixelSize;
        int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R$dimen.text_bubble_arrow_height);
        this.g = dimensionPixelSize2;
        Path path = new Path();
        this.h = path;
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo((-dimensionPixelSize) / 2.0f, dimensionPixelSize2);
        path.lineTo(0.0f, 0.0f);
        path.lineTo(dimensionPixelSize / 2.0f, dimensionPixelSize2);
        path.lineTo((-dimensionPixelSize) / 2.0f, dimensionPixelSize2);
        path.close();
        Paint paint = new Paint(1);
        this.i = paint;
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        this.j = shapeDrawable;
        shapeDrawable.setCallback(this);
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        this.j.draw(canvas);
        if (this.p) {
            canvas.save();
            if (!this.o) {
                canvas.scale(1.0f, -1.0f, this.n, getBounds().height() - (this.g / 2));
                canvas.translate(0.0f, r0 - (r2 / 2));
            }
            canvas.translate(this.n, 0.0f);
            canvas.drawPath(this.h, this.i);
            canvas.restore();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean getPadding(Rect rect) {
        this.j.getPadding(rect);
        int i = rect.left;
        int i2 = rect.top;
        boolean z = this.o;
        int i3 = this.g;
        int iMax = Math.max(i2, z ? i3 : 0);
        int i4 = rect.right;
        int i5 = rect.bottom;
        if (this.o) {
            i3 = 0;
        }
        rect.set(i, iMax, i4, Math.max(i5, i3));
        return true;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        if (rect == null) {
            return;
        }
        if (this.k) {
            this.m = rect.height() / 2;
        } else {
            this.m = this.l.getResources().getDimensionPixelSize(R$dimen.text_bubble_corner_radius);
        }
        ShapeDrawable shapeDrawable = this.j;
        int i = this.m;
        shapeDrawable.setShape(new RoundRectShape(new float[]{i, i, i, i, i, i, i, i}, null, null));
        Rect rect2 = this.e;
        shapeDrawable.getPadding(rect2);
        int i2 = rect.left;
        int i3 = rect.top;
        boolean z = this.o;
        int i4 = this.g;
        shapeDrawable.setBounds(i2, i3 + (z ? i4 - rect2.top : 0), rect.right, rect.bottom - (z ? 0 : i4 - rect2.bottom));
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.j.setAlpha(i);
        this.i.setAlpha(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
    }
}
