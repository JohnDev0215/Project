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
import k2.c;
import n2.h;
import n2.m;
import n2.n;
import n2.p;

/* JADX INFO: loaded from: classes.dex */
public class ShapeableImageView extends AppCompatImageView implements p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int f2328d = R$style.Widget_MaterialComponents_ShapeableImageView;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final n f2329e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final RectF f2330f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final RectF f2331g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Paint f2332h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Paint f2333i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Path f2334j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ColorStateList f2335k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public m f2336l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f2337m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Path f2338n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final h f2339o;

    @TargetApi(21)
    public class a extends ViewOutlineProvider {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Rect f2340a = new Rect();

        public a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            if (ShapeableImageView.this.f2336l == null) {
                return;
            }
            ShapeableImageView.this.f2330f.round(this.f2340a);
            ShapeableImageView.this.f2339o.setBounds(this.f2340a);
            ShapeableImageView.this.f2339o.getOutline(outline);
        }
    }

    public ShapeableImageView(Context context) {
        this(context, null, 0);
    }

    public final void f(Canvas canvas) {
        if (this.f2335k == null) {
            return;
        }
        this.f2332h.setStrokeWidth(this.f2337m);
        int colorForState = this.f2335k.getColorForState(getDrawableState(), this.f2335k.getDefaultColor());
        if (this.f2337m <= 0.0f || colorForState == 0) {
            return;
        }
        this.f2332h.setColor(colorForState);
        canvas.drawPath(this.f2334j, this.f2332h);
    }

    public final void g(int i3, int i4) {
        this.f2330f.set(getPaddingLeft(), getPaddingTop(), i3 - getPaddingRight(), i4 - getPaddingBottom());
        this.f2329e.d(this.f2336l, 1.0f, this.f2330f, this.f2334j);
        this.f2338n.rewind();
        this.f2338n.addPath(this.f2334j);
        this.f2331g.set(0.0f, 0.0f, i3, i4);
        this.f2338n.addRect(this.f2331g, Path.Direction.CCW);
    }

    public m getShapeAppearanceModel() {
        return this.f2336l;
    }

    public ColorStateList getStrokeColor() {
        return this.f2335k;
    }

    public float getStrokeWidth() {
        return this.f2337m;
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
        canvas.drawPath(this.f2338n, this.f2333i);
        f(canvas);
    }

    @Override // android.view.View
    public void onSizeChanged(int i3, int i4, int i5, int i6) {
        super.onSizeChanged(i3, i4, i5, i6);
        g(i3, i4);
    }

    @Override // n2.p
    public void setShapeAppearanceModel(m mVar) {
        this.f2336l = mVar;
        this.f2339o.setShapeAppearanceModel(mVar);
        g(getWidth(), getHeight());
        invalidate();
    }

    public void setStrokeColor(ColorStateList colorStateList) {
        this.f2335k = colorStateList;
        invalidate();
    }

    public void setStrokeColorResource(int i3) {
        setStrokeColor(j.a.c(getContext(), i3));
    }

    public void setStrokeWidth(float f3) {
        if (this.f2337m != f3) {
            this.f2337m = f3;
            invalidate();
        }
    }

    public void setStrokeWidthResource(int i3) {
        setStrokeWidth(getResources().getDimensionPixelSize(i3));
    }

    public ShapeableImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ShapeableImageView(Context context, AttributeSet attributeSet, int i3) {
        int i4 = f2328d;
        super(r2.a.c(context, attributeSet, i3, i4), attributeSet, i3);
        this.f2329e = new n();
        this.f2334j = new Path();
        Context context2 = getContext();
        Paint paint = new Paint();
        this.f2333i = paint;
        paint.setAntiAlias(true);
        paint.setColor(-1);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.f2330f = new RectF();
        this.f2331g = new RectF();
        this.f2338n = new Path();
        this.f2335k = c.a(context2, context2.obtainStyledAttributes(attributeSet, R$styleable.ShapeableImageView, i3, i4), R$styleable.ShapeableImageView_strokeColor);
        this.f2337m = r1.getDimensionPixelSize(R$styleable.ShapeableImageView_strokeWidth, 0);
        Paint paint2 = new Paint();
        this.f2332h = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);
        this.f2336l = m.e(context2, attributeSet, i3, i4).m();
        this.f2339o = new h(this.f2336l);
        if (Build.VERSION.SDK_INT >= 21) {
            setOutlineProvider(new a());
        }
    }
}
