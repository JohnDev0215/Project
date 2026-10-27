package com.google.android.material.floatingactionbutton;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import d.b.a.a.x.m;
import d.b.a.a.x.n;

/* JADX INFO: compiled from: BorderDrawable.java */
/* JADX INFO: loaded from: classes.dex */
class c extends Drawable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Paint f4049b;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    float f4055h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f4056i;
    private int j;
    private int k;
    private int l;
    private int m;
    private m o;
    private ColorStateList p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final n f4048a = new n();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Path f4050c = new Path();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Rect f4051d = new Rect();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final RectF f4052e = new RectF();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final RectF f4053f = new RectF();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final b f4054g = new b();
    private boolean n = true;

    /* JADX INFO: compiled from: BorderDrawable.java */
    private class b extends Drawable.ConstantState {
        private b() {
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return c.this;
        }
    }

    c(m mVar) {
        this.o = mVar;
        Paint paint = new Paint(1);
        this.f4049b = paint;
        paint.setStyle(Paint.Style.STROKE);
    }

    private Shader a() {
        copyBounds(this.f4051d);
        float fHeight = this.f4055h / r0.height();
        return new LinearGradient(0.0f, r0.top, 0.0f, r0.bottom, new int[]{b.g.d.a.b(this.f4056i, this.m), b.g.d.a.b(this.j, this.m), b.g.d.a.b(b.g.d.a.d(this.j, 0), this.m), b.g.d.a.b(b.g.d.a.d(this.l, 0), this.m), b.g.d.a.b(this.l, this.m), b.g.d.a.b(this.k, this.m)}, new float[]{0.0f, fHeight, 0.5f, 0.5f, 1.0f - fHeight, 1.0f}, Shader.TileMode.CLAMP);
    }

    protected RectF b() {
        this.f4053f.set(getBounds());
        return this.f4053f;
    }

    void c(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.m = colorStateList.getColorForState(getState(), this.m);
        }
        this.p = colorStateList;
        this.n = true;
        invalidateSelf();
    }

    public void d(float f2) {
        if (this.f4055h != f2) {
            this.f4055h = f2;
            this.f4049b.setStrokeWidth(f2 * 1.3333f);
            this.n = true;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.n) {
            this.f4049b.setShader(a());
            this.n = false;
        }
        float strokeWidth = this.f4049b.getStrokeWidth() / 2.0f;
        copyBounds(this.f4051d);
        this.f4052e.set(this.f4051d);
        float fMin = Math.min(this.o.r().a(b()), this.f4052e.width() / 2.0f);
        if (this.o.u(b())) {
            this.f4052e.inset(strokeWidth, strokeWidth);
            canvas.drawRoundRect(this.f4052e, fMin, fMin, this.f4049b);
        }
    }

    void e(int i2, int i3, int i4, int i5) {
        this.f4056i = i2;
        this.j = i3;
        this.k = i4;
        this.l = i5;
    }

    public void f(m mVar) {
        this.o = mVar;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f4054g;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f4055h > 0.0f ? -3 : -2;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.o.u(b())) {
            outline.setRoundRect(getBounds(), this.o.r().a(b()));
            return;
        }
        copyBounds(this.f4051d);
        this.f4052e.set(this.f4051d);
        this.f4048a.d(this.o, 1.0f, this.f4052e, this.f4050c);
        if (this.f4050c.isConvex()) {
            outline.setConvexPath(this.f4050c);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        if (!this.o.u(b())) {
            return true;
        }
        int iRound = Math.round(this.f4055h);
        rect.set(iRound, iRound, iRound, iRound);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList = this.p;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.n = true;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        int colorForState;
        ColorStateList colorStateList = this.p;
        if (colorStateList != null && (colorForState = colorStateList.getColorForState(iArr, this.m)) != this.m) {
            this.n = true;
            this.m = colorForState;
        }
        if (this.n) {
            invalidateSelf();
        }
        return this.n;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f4049b.setAlpha(i2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f4049b.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
