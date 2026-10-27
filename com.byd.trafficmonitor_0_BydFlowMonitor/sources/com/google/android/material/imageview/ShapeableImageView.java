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
import c.b.a.a.f0.c;
import c.b.a.a.i0.h;
import c.b.a.a.i0.m;
import c.b.a.a.i0.n;
import c.b.a.a.i0.p;
import c.b.a.a.k;
import c.b.a.a.l;

/* JADX INFO: loaded from: classes.dex */
public class ShapeableImageView extends AppCompatImageView implements p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f4749d = k.Widget_MaterialComponents_ShapeableImageView;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final n f4750e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final RectF f4751f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final RectF f4752g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Paint f4753h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Paint f4754i;
    public final Path j;
    public ColorStateList k;
    public m l;
    public float m;
    public Path n;
    public final h o;

    @TargetApi(21)
    public class a extends ViewOutlineProvider {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Rect f4755a = new Rect();

        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            if (ShapeableImageView.this.l == null) {
                return;
            }
            ShapeableImageView.this.f4751f.round(this.f4755a);
            ShapeableImageView.this.o.setBounds(this.f4755a);
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
        this.f4753h.setStrokeWidth(this.m);
        int colorForState = this.k.getColorForState(getDrawableState(), this.k.getDefaultColor());
        if (this.m <= 0.0f || colorForState == 0) {
            return;
        }
        this.f4753h.setColor(colorForState);
        canvas.drawPath(this.j, this.f4753h);
    }

    public final void g(int i2, int i3) {
        this.f4751f.set(getPaddingLeft(), getPaddingTop(), i2 - getPaddingRight(), i3 - getPaddingBottom());
        this.f4750e.d(this.l, 1.0f, this.f4751f, this.j);
        this.n.rewind();
        this.n.addPath(this.j);
        this.f4752g.set(0.0f, 0.0f, i2, i3);
        this.n.addRect(this.f4752g, Path.Direction.CCW);
    }

    public m getShapeAppearanceModel() {
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
        canvas.drawPath(this.n, this.f4754i);
        f(canvas);
    }

    @Override // android.view.View
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        g(i2, i3);
    }

    @Override // c.b.a.a.i0.p
    public void setShapeAppearanceModel(m mVar) {
        this.l = mVar;
        this.o.setShapeAppearanceModel(mVar);
        g(getWidth(), getHeight());
        invalidate();
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        this.k = colorStateList;
        invalidate();
    }

    public void setStrokeColorResource(int i2) {
        setStrokeColor(b.b.l.a.a.c(getContext(), i2));
    }

    public void setStrokeWidth(float f2) {
        if (this.m != f2) {
            this.m = f2;
            invalidate();
        }
    }

    public void setStrokeWidthResource(int i2) {
        setStrokeWidth(getResources().getDimensionPixelSize(i2));
    }

    public ShapeableImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ShapeableImageView(Context context, AttributeSet attributeSet, int i2) {
        int i3 = f4749d;
        super(c.b.a.a.m0.a.a.c(context, attributeSet, i2, i3), attributeSet, i2);
        this.f4750e = new n();
        this.j = new Path();
        Context context2 = getContext();
        Paint paint = new Paint();
        this.f4754i = paint;
        paint.setAntiAlias(true);
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.f4751f = new RectF();
        this.f4752g = new RectF();
        this.n = new Path();
        this.k = c.a(context2, context2.obtainStyledAttributes(attributeSet, l.ShapeableImageView, i2, i3), l.ShapeableImageView_strokeColor);
        this.m = r1.getDimensionPixelSize(l.ShapeableImageView_strokeWidth, 0);
        Paint paint2 = new Paint();
        this.f4753h = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);
        this.l = m.e(context2, attributeSet, i2, i3).m();
        this.o = new h(this.l);
        if (Build.VERSION.SDK_INT >= 21) {
            setOutlineProvider(new a());
        }
    }
}
