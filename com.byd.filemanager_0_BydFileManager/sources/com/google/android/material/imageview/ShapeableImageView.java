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
import c.a.a.a.k;
import c.a.a.a.l;
import c.a.a.a.t.c;
import c.a.a.a.w.h;
import c.a.a.a.w.m;
import c.a.a.a.w.n;
import c.a.a.a.w.p;

/* JADX INFO: loaded from: classes.dex */
public class ShapeableImageView extends AppCompatImageView implements p {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int f4273d = k.B;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final n f4274e;
    private final RectF f;
    private final RectF g;
    private final Paint h;
    private final Paint i;
    private final Path j;
    private ColorStateList k;
    private m l;
    private float m;
    private Path n;
    private final h o;

    @TargetApi(21)
    class a extends ViewOutlineProvider {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Rect f4275a = new Rect();

        a() {
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View view, Outline outline) {
            if (ShapeableImageView.this.l == null) {
                return;
            }
            ShapeableImageView.this.f.round(this.f4275a);
            ShapeableImageView.this.o.setBounds(this.f4275a);
            ShapeableImageView.this.o.getOutline(outline);
        }
    }

    public ShapeableImageView(Context context) {
        this(context, null, 0);
    }

    private void f(Canvas canvas) {
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

    private void g(int i, int i2) {
        this.f.set(getPaddingLeft(), getPaddingTop(), i - getPaddingRight(), i2 - getPaddingBottom());
        this.f4274e.d(this.l, 1.0f, this.f, this.j);
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
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setLayerType(2, null);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        setLayerType(0, null);
        super.onDetachedFromWindow();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(this.n, this.i);
        f(canvas);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        g(i, i2);
    }

    @Override // c.a.a.a.w.p
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
        setStrokeColor(b.a.k.a.a.c(getContext(), i));
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
        int i2 = f4273d;
        super(com.google.android.material.theme.a.a.c(context, attributeSet, i, i2), attributeSet, i);
        this.f4274e = new n();
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
        this.k = c.a(context2, context2.obtainStyledAttributes(attributeSet, l.c5, i, i2), l.d5);
        this.m = r1.getDimensionPixelSize(l.e5, 0);
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
