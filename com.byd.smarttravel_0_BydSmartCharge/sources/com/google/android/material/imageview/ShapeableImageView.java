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
import c.b.a.a.s.c;
import c.b.a.a.v.h;
import c.b.a.a.v.m;
import c.b.a.a.v.n;
import c.b.a.a.v.p;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;

/* JADX INFO: loaded from: classes.dex */
public class ShapeableImageView extends AppCompatImageView implements p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f2193d = R$style.Widget_MaterialComponents_ShapeableImageView;
    public final n e;
    public final RectF f;
    public final RectF g;
    public final Paint h;
    public final Paint i;
    public final Path j;
    public ColorStateList k;
    public m l;
    public float m;
    public Path n;
    public final h o;

    @TargetApi(21)
    public class a extends ViewOutlineProvider {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Rect f2194a = new Rect();

        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            if (ShapeableImageView.this.l == null) {
                return;
            }
            ShapeableImageView.this.f.round(this.f2194a);
            ShapeableImageView.this.o.setBounds(this.f2194a);
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
        canvas.drawPath(this.n, this.i);
        f(canvas);
    }

    @Override // android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        g(i, i2);
    }

    @Override // c.b.a.a.v.p
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

    public void setStrokeColorResource(int i) {
        setStrokeColor(b.b.b.a.a.c(getContext(), i));
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
        int i2 = f2193d;
        super(c.b.a.a.z.a.a.c(context, attributeSet, i, i2), attributeSet, i);
        this.e = new n();
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
        this.k = c.a(context2, context2.obtainStyledAttributes(attributeSet, R$styleable.ShapeableImageView, i, i2), R$styleable.ShapeableImageView_strokeColor);
        this.m = r1.getDimensionPixelSize(R$styleable.ShapeableImageView_strokeWidth, 0);
        Paint paint2 = new Paint();
        this.h = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);
        this.l = m.e(context2, attributeSet, i, i2).m();
        this.o = new h(this.l);
        if (Build.VERSION.SDK_INT >= 21) {
            setOutlineProvider(new a());
        }
    }
}
