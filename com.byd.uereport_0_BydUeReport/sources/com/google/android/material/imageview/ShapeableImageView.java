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
import b6.c;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import e6.h;
import e6.m;
import e6.n;
import e6.p;

/* JADX INFO: loaded from: classes2.dex */
public class ShapeableImageView extends AppCompatImageView implements p {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int f3911f = R$style.Widget_MaterialComponents_ShapeableImageView;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final n f3912g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final RectF f3913h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final RectF f3914i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Paint f3915j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Paint f3916k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Path f3917l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ColorStateList f3918m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public m f3919n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public float f3920o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Path f3921p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final h f3922q;

    @TargetApi(21)
    public class a extends ViewOutlineProvider {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Rect f3923a = new Rect();

        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            if (ShapeableImageView.this.f3919n == null) {
                return;
            }
            ShapeableImageView.this.f3913h.round(this.f3923a);
            ShapeableImageView.this.f3922q.setBounds(this.f3923a);
            ShapeableImageView.this.f3922q.getOutline(outline);
        }
    }

    public ShapeableImageView(Context context) {
        this(context, null, 0);
    }

    public final void f(Canvas canvas) {
        if (this.f3918m == null) {
            return;
        }
        this.f3915j.setStrokeWidth(this.f3920o);
        int colorForState = this.f3918m.getColorForState(getDrawableState(), this.f3918m.getDefaultColor());
        if (this.f3920o <= 0.0f || colorForState == 0) {
            return;
        }
        this.f3915j.setColor(colorForState);
        canvas.drawPath(this.f3917l, this.f3915j);
    }

    public final void g(int i8, int i9) {
        this.f3913h.set(getPaddingLeft(), getPaddingTop(), i8 - getPaddingRight(), i9 - getPaddingBottom());
        this.f3912g.d(this.f3919n, 1.0f, this.f3913h, this.f3917l);
        this.f3921p.rewind();
        this.f3921p.addPath(this.f3917l);
        this.f3914i.set(0.0f, 0.0f, i8, i9);
        this.f3921p.addRect(this.f3914i, Path.Direction.CCW);
    }

    public m getShapeAppearanceModel() {
        return this.f3919n;
    }

    public ColorStateList getStrokeColor() {
        return this.f3918m;
    }

    public float getStrokeWidth() {
        return this.f3920o;
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
        canvas.drawPath(this.f3921p, this.f3916k);
        f(canvas);
    }

    @Override // android.view.View
    public void onSizeChanged(int i8, int i9, int i10, int i11) {
        super.onSizeChanged(i8, i9, i10, i11);
        g(i8, i9);
    }

    @Override // e6.p
    public void setShapeAppearanceModel(m mVar) {
        this.f3919n = mVar;
        this.f3922q.setShapeAppearanceModel(mVar);
        g(getWidth(), getHeight());
        invalidate();
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        this.f3918m = colorStateList;
        invalidate();
    }

    public void setStrokeColorResource(int i8) {
        setStrokeColor(m.a.c(getContext(), i8));
    }

    public void setStrokeWidth(float f8) {
        if (this.f3920o != f8) {
            this.f3920o = f8;
            invalidate();
        }
    }

    public void setStrokeWidthResource(int i8) {
        setStrokeWidth(getResources().getDimensionPixelSize(i8));
    }

    public ShapeableImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ShapeableImageView(Context context, AttributeSet attributeSet, int i8) {
        int i9 = f3911f;
        super(i6.a.c(context, attributeSet, i8, i9), attributeSet, i8);
        this.f3912g = new n();
        this.f3917l = new Path();
        Context context2 = getContext();
        Paint paint = new Paint();
        this.f3916k = paint;
        paint.setAntiAlias(true);
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.f3913h = new RectF();
        this.f3914i = new RectF();
        this.f3921p = new Path();
        this.f3918m = c.a(context2, context2.obtainStyledAttributes(attributeSet, R$styleable.ShapeableImageView, i8, i9), R$styleable.ShapeableImageView_strokeColor);
        this.f3920o = r1.getDimensionPixelSize(R$styleable.ShapeableImageView_strokeWidth, 0);
        Paint paint2 = new Paint();
        this.f3915j = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);
        this.f3919n = m.e(context2, attributeSet, i8, i9).m();
        this.f3922q = new h(this.f3919n);
        if (Build.VERSION.SDK_INT >= 21) {
            setOutlineProvider(new a());
        }
    }
}
