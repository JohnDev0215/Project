package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import defpackage.in3;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class RoundedCornerImageView extends AppCompatImageView {
    public final RectF h;
    public final Matrix i;
    public final Paint j;
    public Paint k;
    public boolean l;
    public final boolean m;
    public RoundRectShape n;
    public int o;

    public RoundedCornerImageView(Context context) {
        this(context, null, 0);
    }

    public final void c() {
        Drawable drawable = getDrawable();
        if (this.m) {
            boolean z = drawable instanceof ColorDrawable;
            Paint paint = this.j;
            if (z) {
                paint.setColor(((ColorDrawable) getDrawable()).getColor());
                this.k = null;
                return;
            }
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                if (bitmapDrawable.getBitmap() != null) {
                    paint.setColor(this.o);
                    this.k = new Paint(1);
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    Paint paint2 = this.k;
                    Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                    paint2.setShader(new BitmapShader(bitmap, tileMode, tileMode));
                    return;
                }
            }
            paint.setColor(this.o);
            this.k = null;
        }
    }

    public final void d(int i, int i2, int i3, int i4) {
        float[] fArr;
        boolean z = (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) ? false : true;
        this.l = z;
        if (z) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            if (om3.e.c(this) == 0) {
                float f = i;
                float f2 = i2;
                float f3 = i4;
                float f4 = i3;
                fArr = new float[]{f, f, f2, f2, f3, f3, f4, f4};
            } else {
                float f5 = i2;
                float f6 = i;
                float f7 = i3;
                float f8 = i4;
                fArr = new float[]{f5, f5, f6, f6, f7, f7, f8, f8};
            }
            this.n = new RoundRectShape(fArr, null, null);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        if (!this.l) {
            super.onDraw(canvas);
            return;
        }
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
        if (width <= 0 || height <= 0) {
            return;
        }
        this.n.resize(width, height);
        int iSave = canvas.save();
        try {
            canvas.translate(getPaddingLeft(), getPaddingTop());
            Paint paint = this.j;
            if (paint.getColor() != 0) {
                this.n.draw(canvas, paint);
                if (getDrawable() instanceof ColorDrawable) {
                    return;
                }
            }
            Paint paint2 = this.k;
            if (paint2 == null) {
                canvas.restoreToCount(iSave);
                super.onDraw(canvas);
                return;
            }
            Shader shader = paint2.getShader();
            if (shader != null) {
                Bitmap bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
                Matrix matrix = this.i;
                matrix.set(getImageMatrix());
                matrix.preScale(r2.getIntrinsicWidth() / bitmap.getWidth(), r2.getIntrinsicHeight() / bitmap.getHeight());
                shader.setLocalMatrix(matrix);
                RectF rectF = this.h;
                rectF.set(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight());
                matrix.mapRect(rectF);
                canvas.clipRect(rectF);
            }
            this.n.draw(canvas, this.k);
        } finally {
            canvas.restoreToCount(iSave);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        c();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        c();
    }

    @Override // androidx.appcompat.widget.AppCompatImageView, android.widget.ImageView
    public void setImageResource(int i) {
        super.setImageResource(i);
        c();
    }

    public void setRoundedFillColor(int i) {
        this.o = i;
        this.j.setColor(i);
        invalidate();
    }

    public RoundedCornerImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundedCornerImageView(Context context, AttributeSet attributeSet, int i) {
        int i2;
        int dimensionPixelSize;
        int dimensionPixelSize2;
        int dimensionPixelSize3;
        super(context, attributeSet, i);
        this.h = new RectF();
        this.i = new Matrix();
        this.j = new Paint(1);
        new Matrix();
        int i3 = 0;
        this.o = 0;
        this.m = true;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.RoundedCornerImageView, 0, 0);
            int dimensionPixelSize4 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.RoundedCornerImageView_cornerRadiusTopStart, 0);
            dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.RoundedCornerImageView_cornerRadiusTopEnd, 0);
            dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.RoundedCornerImageView_cornerRadiusBottomStart, 0);
            dimensionPixelSize3 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.RoundedCornerImageView_cornerRadiusBottomEnd, 0);
            int color = typedArrayObtainStyledAttributes.getColor(R$styleable.RoundedCornerImageView_roundedfillColor, 0);
            typedArrayObtainStyledAttributes.recycle();
            i2 = color;
            i3 = dimensionPixelSize4;
        } else {
            i2 = 0;
            dimensionPixelSize = 0;
            dimensionPixelSize2 = 0;
            dimensionPixelSize3 = 0;
        }
        d(i3, dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize3);
        setRoundedFillColor(i2);
        c();
    }
}
