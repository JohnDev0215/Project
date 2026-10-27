package com.google.android.material.imageview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.appcompat.widget.AppCompatImageView;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.fl;
import defpackage.hk;
import defpackage.mk;
import defpackage.nk;
import defpackage.pk;
import defpackage.sj;
import defpackage.u0;

/* JADX INFO: loaded from: classes.dex */
public class ShapeableImageView extends AppCompatImageView implements pk {
    public static final int d = R$style.Widget_MaterialComponents_ShapeableImageView;
    public final nk e;
    public final RectF f;
    public final RectF g;
    public final Paint h;
    public final Paint i;
    public final Path j;
    public ColorStateList k;
    public mk l;
    public float m;
    public Path n;
    public final hk o;

    @TargetApi(21)
    public class a extends ViewOutlineProvider {
        public final Rect a = new Rect();

        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            if (ShapeableImageView.this.l == null) {
                return;
            }
            ShapeableImageView.this.f.round(this.a);
            ShapeableImageView.this.o.setBounds(this.a);
            ShapeableImageView.this.o.getOutline(outline);
        }
    }

    public ShapeableImageView(Context context) {
        this(context, null, 0);
    }

    public final void f(Canvas canvas) {
        if (this.k == null) {
            return;
        }
        this.h.setStrokeWidth(this.m);
        int colorForState = this.k.getColorForState(getDrawableState(), this.k.getDefaultColor());
        if (this.m <= 0.0f || colorForState == 0) {
            return;
        }
        this.h.setColor(colorForState);
        canvas.drawPath(this.j, this.h);
    }

    public final void g(int i, int i2) {
        this.f.set(getPaddingLeft(), getPaddingTop(), i - getPaddingRight(), i2 - getPaddingBottom());
        this.e.d(this.l, 1.0f, this.f, this.j);
        this.n.rewind();
        this.n.addPath(this.j);
        this.g.set(0.0f, 0.0f, i, i2);
        this.n.addRect(this.g, Path.Direction.CCW);
    }

    public mk getShapeAppearanceModel() {
        return this.l;
    }

    public ColorStateList getStrokeColor() {
        return this.k;
    }

    public float getStrokeWidth() {
        return this.m;
    }

    @Override // android.widget.ImageView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        setLayerType(2, null);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        setLayerType(0, null);
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(this.n, this.i);
        f(canvas);
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        g(i, i2);
    }

    @Override // defpackage.pk
    public void setShapeAppearanceModel(mk mkVar) {
        this.l = mkVar;
        this.o.setShapeAppearanceModel(mkVar);
        g(getWidth(), getHeight());
        invalidate();
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        this.k = colorStateList;
        invalidate();
    }

    public void setStrokeColorResource(int i) {
        setStrokeColor(u0.c(getContext(), i));
    }

    public void setStrokeWidth(float f) {
        if (this.m != f) {
            this.m = f;
            invalidate();
        }
    }

    public void setStrokeWidthResource(int i) {
        setStrokeWidth(getResources().getDimensionPixelSize(i));
    }

    public ShapeableImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ShapeableImageView(Context context, AttributeSet attributeSet, int i) {
        int i2 = d;
        super(fl.c(context, attributeSet, i, i2), attributeSet, i);
        this.e = new nk();
        this.j = new Path();
        Context context2 = getContext();
        Paint paint = new Paint();
        this.i = paint;
        paint.setAntiAlias(true);
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.f = new RectF();
        this.g = new RectF();
        this.n = new Path();
        this.k = sj.a(context2, context2.obtainStyledAttributes(attributeSet, R$styleable.ShapeableImageView, i, i2), R$styleable.ShapeableImageView_strokeColor);
        this.m = r1.getDimensionPixelSize(R$styleable.ShapeableImageView_strokeWidth, 0);
        Paint paint2 = new Paint();
        this.h = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);
        this.l = mk.e(context2, attributeSet, i, i2).m();
        this.o = new hk(this.l);
        if (Build.VERSION.SDK_INT >= 21) {
            setOutlineProvider(new a());
        }
    }
}
