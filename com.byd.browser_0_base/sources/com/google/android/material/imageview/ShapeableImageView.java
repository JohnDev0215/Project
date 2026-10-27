package com.google.android.material.imageview;

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
import androidx.appcompat.widget.AppCompatImageView;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.bz;
import defpackage.rc1;
import defpackage.sc1;
import defpackage.uc1;
import defpackage.uj2;
import defpackage.vj2;
import defpackage.yj2;

/* JADX INFO: loaded from: classes.dex */
public class ShapeableImageView extends AppCompatImageView implements yj2 {
    public static final int z = R$style.Widget_MaterialComponents_ShapeableImageView;
    public final vj2 h;
    public final RectF i;
    public final RectF j;
    public final Paint k;
    public final Paint l;
    public final Path m;
    public ColorStateList n;
    public sc1 o;
    public uj2 p;
    public float q;
    public final Path r;
    public final int s;
    public final int t;
    public final int u;
    public final int v;
    public final int w;
    public final int x;
    public boolean y;

    @TargetApi(21)
    public class a extends ViewOutlineProvider {
        public final Rect a = new Rect();

        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public final void getOutline(View view, Outline outline) {
            ShapeableImageView shapeableImageView = ShapeableImageView.this;
            if (shapeableImageView.p == null) {
                return;
            }
            if (shapeableImageView.o == null) {
                shapeableImageView.o = new sc1(shapeableImageView.p);
            }
            RectF rectF = shapeableImageView.i;
            Rect rect = this.a;
            rectF.round(rect);
            shapeableImageView.o.setBounds(rect);
            shapeableImageView.o.getOutline(outline);
        }
    }

    public ShapeableImageView(Context context) {
        this(context, null, 0);
    }

    public final boolean c() {
        return getLayoutDirection() == 1;
    }

    public final void d(int i, int i2) {
        RectF rectF = this.i;
        rectF.set(getPaddingLeft(), getPaddingTop(), i - getPaddingRight(), i2 - getPaddingBottom());
        uj2 uj2Var = this.p;
        Path path = this.m;
        this.h.a(uj2Var, 1.0f, rectF, null, path);
        Path path2 = this.r;
        path2.rewind();
        path2.addPath(path);
        RectF rectF2 = this.j;
        rectF2.set(0.0f, 0.0f, i, i2);
        path2.addRect(rectF2, Path.Direction.CCW);
    }

    public int getContentPaddingBottom() {
        return this.v;
    }

    public final int getContentPaddingEnd() {
        int i = this.x;
        return i != Integer.MIN_VALUE ? i : c() ? this.s : this.u;
    }

    public int getContentPaddingLeft() {
        int i = this.x;
        int i2 = this.w;
        if ((i2 == Integer.MIN_VALUE && i == Integer.MIN_VALUE) ? false : true) {
            if (c() && i != Integer.MIN_VALUE) {
                return i;
            }
            if (!c() && i2 != Integer.MIN_VALUE) {
                return i2;
            }
        }
        return this.s;
    }

    public int getContentPaddingRight() {
        int i = this.x;
        int i2 = this.w;
        if ((i2 == Integer.MIN_VALUE && i == Integer.MIN_VALUE) ? false : true) {
            if (c() && i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (!c() && i != Integer.MIN_VALUE) {
                return i;
            }
        }
        return this.u;
    }

    public final int getContentPaddingStart() {
        int i = this.w;
        return i != Integer.MIN_VALUE ? i : c() ? this.u : this.s;
    }

    public int getContentPaddingTop() {
        return this.t;
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

    public uj2 getShapeAppearanceModel() {
        return this.p;
    }

    public ColorStateList getStrokeColor() {
        return this.n;
    }

    public float getStrokeWidth() {
        return this.q;
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        setLayerType(2, null);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDetachedFromWindow() {
        setLayerType(0, null);
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(this.r, this.l);
        if (this.n == null) {
            return;
        }
        Paint paint = this.k;
        paint.setStrokeWidth(this.q);
        int colorForState = this.n.getColorForState(getDrawableState(), this.n.getDefaultColor());
        if (this.q <= 0.0f || colorForState == 0) {
            return;
        }
        paint.setColor(colorForState);
        canvas.drawPath(this.m, paint);
    }

    @Override // android.widget.ImageView, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (!this.y && isLayoutDirectionResolved()) {
            boolean z2 = true;
            this.y = true;
            if (!isPaddingRelative()) {
                if (this.w == Integer.MIN_VALUE && this.x == Integer.MIN_VALUE) {
                    z2 = false;
                }
                if (!z2) {
                    setPadding(super.getPaddingLeft(), super.getPaddingTop(), super.getPaddingRight(), super.getPaddingBottom());
                    return;
                }
            }
            setPaddingRelative(super.getPaddingStart(), super.getPaddingTop(), super.getPaddingEnd(), super.getPaddingBottom());
        }
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        d(i, i2);
    }

    @Override // android.view.View
    public final void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(getContentPaddingLeft() + i, getContentPaddingTop() + i2, getContentPaddingRight() + i3, getContentPaddingBottom() + i4);
    }

    @Override // android.view.View
    public final void setPaddingRelative(int i, int i2, int i3, int i4) {
        super.setPaddingRelative(getContentPaddingStart() + i, getContentPaddingTop() + i2, getContentPaddingEnd() + i3, getContentPaddingBottom() + i4);
    }

    @Override // defpackage.yj2
    public void setShapeAppearanceModel(uj2 uj2Var) {
        this.p = uj2Var;
        sc1 sc1Var = this.o;
        if (sc1Var != null) {
            sc1Var.setShapeAppearanceModel(uj2Var);
        }
        d(getWidth(), getHeight());
        invalidate();
        invalidateOutline();
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        this.n = colorStateList;
        invalidate();
    }

    public void setStrokeColorResource(int i) {
        setStrokeColor(bz.b(getContext(), i));
    }

    public void setStrokeWidth(float f) {
        if (this.q != f) {
            this.q = f;
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
        int i2 = z;
        super(uc1.a(context, attributeSet, i, i2), attributeSet, i);
        this.h = vj2.a.a;
        this.m = new Path();
        this.y = false;
        Context context2 = getContext();
        Paint paint = new Paint();
        this.l = paint;
        paint.setAntiAlias(true);
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.i = new RectF();
        this.j = new RectF();
        this.r = new Path();
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R$styleable.ShapeableImageView, i, i2);
        this.n = rc1.a(context2, typedArrayObtainStyledAttributes, R$styleable.ShapeableImageView_strokeColor);
        this.q = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_strokeWidth, 0);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPadding, 0);
        this.s = dimensionPixelSize;
        this.t = dimensionPixelSize;
        this.u = dimensionPixelSize;
        this.v = dimensionPixelSize;
        this.s = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPaddingLeft, dimensionPixelSize);
        this.t = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPaddingTop, dimensionPixelSize);
        this.u = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPaddingRight, dimensionPixelSize);
        this.v = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPaddingBottom, dimensionPixelSize);
        this.w = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPaddingStart, Integer.MIN_VALUE);
        this.x = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ShapeableImageView_contentPaddingEnd, Integer.MIN_VALUE);
        typedArrayObtainStyledAttributes.recycle();
        Paint paint2 = new Paint();
        this.k = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);
        this.p = uj2.b(context2, attributeSet, i, i2).a();
        setOutlineProvider(new a());
    }
}
