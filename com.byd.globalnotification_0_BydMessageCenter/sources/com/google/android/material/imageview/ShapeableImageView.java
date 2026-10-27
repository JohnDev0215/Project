package com.google.android.material.imageview;

import a.b.k.l;
import a.b.p.n;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import b.d.a.a.e0.g;
import b.d.a.a.e0.k;
import b.d.a.a.j;

/* JADX INFO: loaded from: classes.dex */
public class ShapeableImageView extends n implements b.d.a.a.e0.n {
    public static final int u = j.Widget_MaterialComponents_ShapeableImageView;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final k f2447c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final RectF f2448d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final RectF f2449e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Paint f2450f;
    public final Paint g;
    public final Path h;
    public ColorStateList i;
    public g j;
    public b.d.a.a.e0.j k;
    public float l;
    public Path m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public boolean t;

    @TargetApi(21)
    public class a extends ViewOutlineProvider {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Rect f2451a = new Rect();

        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            ShapeableImageView shapeableImageView = ShapeableImageView.this;
            if (shapeableImageView.k == null) {
                return;
            }
            if (shapeableImageView.j == null) {
                shapeableImageView.j = new g(ShapeableImageView.this.k);
            }
            ShapeableImageView.this.f2448d.round(this.f2451a);
            ShapeableImageView.this.j.setBounds(this.f2451a);
            ShapeableImageView.this.j.getOutline(outline);
        }
    }

    public ShapeableImageView(Context context, AttributeSet attributeSet) {
        super(b.d.a.a.i0.a.a.a(context, attributeSet, 0, u), attributeSet, 0);
        this.f2447c = k.a.f1861a;
        this.h = new Path();
        this.t = false;
        Context context2 = getContext();
        Paint paint = new Paint();
        this.g = paint;
        paint.setAntiAlias(true);
        this.g.setColor(-1);
        this.g.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.f2448d = new RectF();
        this.f2449e = new RectF();
        this.m = new Path();
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, b.d.a.a.k.ShapeableImageView, 0, u);
        this.i = l.i.y(context2, typedArrayObtainStyledAttributes, b.d.a.a.k.ShapeableImageView_strokeColor);
        this.l = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.d.a.a.k.ShapeableImageView_strokeWidth, 0);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.d.a.a.k.ShapeableImageView_contentPadding, 0);
        this.n = dimensionPixelSize;
        this.o = dimensionPixelSize;
        this.p = dimensionPixelSize;
        this.q = dimensionPixelSize;
        this.n = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.d.a.a.k.ShapeableImageView_contentPaddingLeft, dimensionPixelSize);
        this.o = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.d.a.a.k.ShapeableImageView_contentPaddingTop, dimensionPixelSize);
        this.p = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.d.a.a.k.ShapeableImageView_contentPaddingRight, dimensionPixelSize);
        this.q = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.d.a.a.k.ShapeableImageView_contentPaddingBottom, dimensionPixelSize);
        this.r = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.d.a.a.k.ShapeableImageView_contentPaddingStart, Integer.MIN_VALUE);
        this.s = typedArrayObtainStyledAttributes.getDimensionPixelSize(b.d.a.a.k.ShapeableImageView_contentPaddingEnd, Integer.MIN_VALUE);
        typedArrayObtainStyledAttributes.recycle();
        Paint paint2 = new Paint();
        this.f2450f = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        this.f2450f.setAntiAlias(true);
        this.k = b.d.a.a.e0.j.b(context2, attributeSet, 0, u).a();
        setOutlineProvider(new a());
    }

    public final boolean c() {
        return (this.r == Integer.MIN_VALUE && this.s == Integer.MIN_VALUE) ? false : true;
    }

    public final boolean d() {
        return getLayoutDirection() == 1;
    }

    public final void e(int i, int i2) {
        this.f2448d.set(getPaddingLeft(), getPaddingTop(), i - getPaddingRight(), i2 - getPaddingBottom());
        this.f2447c.a(this.k, 1.0f, this.f2448d, null, this.h);
        this.m.rewind();
        this.m.addPath(this.h);
        this.f2449e.set(0.0f, 0.0f, i, i2);
        this.m.addRect(this.f2449e, Path.Direction.CCW);
    }

    public int getContentPaddingBottom() {
        return this.q;
    }

    public final int getContentPaddingEnd() {
        int i = this.s;
        return i != Integer.MIN_VALUE ? i : d() ? this.n : this.p;
    }

    public int getContentPaddingLeft() {
        int i;
        int i2;
        if (c()) {
            if (d() && (i2 = this.s) != Integer.MIN_VALUE) {
                return i2;
            }
            if (!d() && (i = this.r) != Integer.MIN_VALUE) {
                return i;
            }
        }
        return this.n;
    }

    public int getContentPaddingRight() {
        int i;
        int i2;
        if (c()) {
            if (d() && (i2 = this.r) != Integer.MIN_VALUE) {
                return i2;
            }
            if (!d() && (i = this.s) != Integer.MIN_VALUE) {
                return i;
            }
        }
        return this.p;
    }

    public final int getContentPaddingStart() {
        int i = this.r;
        return i != Integer.MIN_VALUE ? i : d() ? this.p : this.n;
    }

    public int getContentPaddingTop() {
        return this.o;
    }

    @Override // android.view.View
    public int getPaddingBottom() {
        return super.getPaddingBottom() - getContentPaddingBottom();
    }

    @Override // android.view.View
    public int getPaddingEnd() {
        return super.getPaddingEnd() - getContentPaddingEnd();
    }

    @Override // android.view.View
    public int getPaddingLeft() {
        return super.getPaddingLeft() - getContentPaddingLeft();
    }

    @Override // android.view.View
    public int getPaddingRight() {
        return super.getPaddingRight() - getContentPaddingRight();
    }

    @Override // android.view.View
    public int getPaddingStart() {
        return super.getPaddingStart() - getContentPaddingStart();
    }

    @Override // android.view.View
    public int getPaddingTop() {
        return super.getPaddingTop() - getContentPaddingTop();
    }

    public b.d.a.a.e0.j getShapeAppearanceModel() {
        return this.k;
    }

    public ColorStateList getStrokeColor() {
        return this.i;
    }

    public float getStrokeWidth() {
        return this.l;
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
        canvas.drawPath(this.m, this.g);
        if (this.i == null) {
            return;
        }
        this.f2450f.setStrokeWidth(this.l);
        int colorForState = this.i.getColorForState(getDrawableState(), this.i.getDefaultColor());
        if (this.l <= 0.0f || colorForState == 0) {
            return;
        }
        this.f2450f.setColor(colorForState);
        canvas.drawPath(this.h, this.f2450f);
    }

    @Override // android.widget.ImageView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (!this.t && isLayoutDirectionResolved()) {
            this.t = true;
            if (isPaddingRelative() || c()) {
                setPaddingRelative(super.getPaddingStart(), super.getPaddingTop(), super.getPaddingEnd(), super.getPaddingBottom());
            } else {
                setPadding(super.getPaddingLeft(), super.getPaddingTop(), super.getPaddingRight(), super.getPaddingBottom());
            }
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        e(i, i2);
    }

    @Override // android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(getContentPaddingLeft() + i, getContentPaddingTop() + i2, getContentPaddingRight() + i3, getContentPaddingBottom() + i4);
    }

    @Override // android.view.View
    public void setPaddingRelative(int i, int i2, int i3, int i4) {
        super.setPaddingRelative(getContentPaddingStart() + i, getContentPaddingTop() + i2, getContentPaddingEnd() + i3, getContentPaddingBottom() + i4);
    }

    @Override // b.d.a.a.e0.n
    public void setShapeAppearanceModel(b.d.a.a.e0.j jVar) {
        this.k = jVar;
        g gVar = this.j;
        if (gVar != null) {
            gVar.f1828a.f1835a = jVar;
            gVar.invalidateSelf();
        }
        e(getWidth(), getHeight());
        invalidate();
        invalidateOutline();
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        this.i = colorStateList;
        invalidate();
    }

    public void setStrokeColorResource(int i) {
        setStrokeColor(a.b.l.a.a.a(getContext(), i));
    }

    public void setStrokeWidth(float f2) {
        if (this.l != f2) {
            this.l = f2;
            invalidate();
        }
    }

    public void setStrokeWidthResource(int i) {
        setStrokeWidth(getResources().getDimensionPixelSize(i));
    }
}
