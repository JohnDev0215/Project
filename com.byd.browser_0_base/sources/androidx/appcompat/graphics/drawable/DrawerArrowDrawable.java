package androidx.appcompat.graphics.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.appcompat.R;
import defpackage.C0173a;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class DrawerArrowDrawable extends Drawable {
    public static final float l = (float) Math.toRadians(45.0d);
    public final Paint a;
    public final float b;
    public final float c;
    public final float d;
    public float e;
    public boolean f;
    public final Path g;
    public final int h;
    public boolean i;
    public float j;
    public final int k;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ArrowDirection {
    }

    public DrawerArrowDrawable(Context context) {
        Paint paint = new Paint();
        this.a = paint;
        this.g = new Path();
        this.i = false;
        this.k = 2;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, R.styleable.DrawerArrowToggle, R.attr.drawerArrowStyle, R.style.Base_Widget_AppCompat_DrawerArrowToggle);
        int color = typedArrayObtainStyledAttributes.getColor(R.styleable.DrawerArrowToggle_color, 0);
        if (color != paint.getColor()) {
            paint.setColor(color);
            invalidateSelf();
        }
        float dimension = typedArrayObtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_thickness, 0.0f);
        if (paint.getStrokeWidth() != dimension) {
            paint.setStrokeWidth(dimension);
            this.j = (float) (Math.cos(l) * ((double) (dimension / 2.0f)));
            invalidateSelf();
        }
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R.styleable.DrawerArrowToggle_spinBars, true);
        if (this.f != z) {
            this.f = z;
            invalidateSelf();
        }
        float fRound = Math.round(typedArrayObtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_gapBetweenBars, 0.0f));
        if (fRound != this.e) {
            this.e = fRound;
            invalidateSelf();
        }
        this.h = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.DrawerArrowToggle_drawableSize, 0);
        this.c = Math.round(typedArrayObtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_barLength, 0.0f));
        this.b = Math.round(typedArrayObtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_arrowHeadLength, 0.0f));
        this.d = typedArrayObtainStyledAttributes.getDimension(R.styleable.DrawerArrowToggle_arrowShaftLength, 0.0f);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Rect bounds = getBounds();
        boolean z = true;
        int i = this.k;
        if (i == 0 || (i != 1 && (i == 3 ? getLayoutDirection() != 0 : getLayoutDirection() != 1))) {
            z = false;
        }
        float f = this.b;
        float fSqrt = (float) Math.sqrt(f * f * 2.0f);
        float f2 = this.c;
        float f3 = C0173a.f(fSqrt, f2, 0.0f, f2);
        float f4 = C0173a.f(this.d, f2, 0.0f, f2);
        float fRound = Math.round(((this.j - 0.0f) * 0.0f) + 0.0f);
        float f5 = C0173a.f(l, 0.0f, 0.0f, 0.0f);
        float f6 = z ? 0.0f : -180.0f;
        float f7 = C0173a.f(z ? 180.0f : 0.0f, f6, 0.0f, f6);
        double d = f3;
        double d2 = f5;
        float fRound2 = Math.round(Math.cos(d2) * d);
        float fRound3 = Math.round(Math.sin(d2) * d);
        Path path = this.g;
        path.rewind();
        float f8 = this.e;
        Paint paint = this.a;
        float strokeWidth = paint.getStrokeWidth() + f8;
        float f9 = C0173a.f(-this.j, strokeWidth, 0.0f, strokeWidth);
        float f10 = (-f4) / 2.0f;
        path.moveTo(f10 + fRound, 0.0f);
        path.rLineTo(f4 - (fRound * 2.0f), 0.0f);
        path.moveTo(f10, f9);
        path.rLineTo(fRound2, fRound3);
        path.moveTo(f10, -f9);
        path.rLineTo(fRound2, -fRound3);
        path.close();
        canvas.save();
        float strokeWidth2 = paint.getStrokeWidth();
        float fHeight = bounds.height() - (3.0f * strokeWidth2);
        canvas.translate(bounds.centerX(), (strokeWidth2 * 1.5f) + this.e + ((((int) (fHeight - (2.0f * r7))) / 4) * 2));
        if (this.f) {
            canvas.rotate(f7 * (this.i ^ z ? -1 : 1));
        } else if (z) {
            canvas.rotate(180.0f);
        }
        canvas.drawPath(path, paint);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.h;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.h;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        Paint paint = this.a;
        if (i != paint.getAlpha()) {
            paint.setAlpha(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
