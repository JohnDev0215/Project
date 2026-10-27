package c.b.a.a.c0;

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
import c.b.a.a.i0.m;
import c.b.a.a.i0.n;

/* JADX INFO: compiled from: BorderDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends Drawable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Paint f3708b;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f3714h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3715i;
    public int j;
    public int k;
    public int l;
    public int m;
    public m o;
    public ColorStateList p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final n f3707a = new n();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Path f3709c = new Path();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f3710d = new Rect();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final RectF f3711e = new RectF();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final RectF f3712f = new RectF();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final b f3713g = new b();
    public boolean n = true;

    /* JADX INFO: compiled from: BorderDrawable.java */
    public class b extends Drawable.ConstantState {
        public b() {
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

    public c(m mVar) {
        this.o = mVar;
        Paint paint = new Paint(1);
        this.f3708b = paint;
        paint.setStyle(Paint.Style.STROKE);
    }

    public final Shader a() {
        copyBounds(this.f3710d);
        float fHeight = this.f3714h / r0.height();
        return new LinearGradient(0.0f, r0.top, 0.0f, r0.bottom, new int[]{b.h.f.a.b(this.f3715i, this.m), b.h.f.a.b(this.j, this.m), b.h.f.a.b(b.h.f.a.d(this.j, 0), this.m), b.h.f.a.b(b.h.f.a.d(this.l, 0), this.m), b.h.f.a.b(this.l, this.m), b.h.f.a.b(this.k, this.m)}, new float[]{0.0f, fHeight, 0.5f, 0.5f, 1.0f - fHeight, 1.0f}, Shader.TileMode.CLAMP);
    }

    public RectF b() {
        this.f3712f.set(getBounds());
        return this.f3712f;
    }

    public void c(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.m = colorStateList.getColorForState(getState(), this.m);
        }
        this.p = colorStateList;
        this.n = true;
        invalidateSelf();
    }

    public void d(float f2) {
        if (this.f3714h != f2) {
            this.f3714h = f2;
            this.f3708b.setStrokeWidth(f2 * 1.3333f);
            this.n = true;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.n) {
            this.f3708b.setShader(a());
            this.n = false;
        }
        float strokeWidth = this.f3708b.getStrokeWidth() / 2.0f;
        copyBounds(this.f3710d);
        this.f3711e.set(this.f3710d);
        float fMin = Math.min(this.o.r().a(b()), this.f3711e.width() / 2.0f);
        if (this.o.u(b())) {
            this.f3711e.inset(strokeWidth, strokeWidth);
            canvas.drawRoundRect(this.f3711e, fMin, fMin, this.f3708b);
        }
    }

    public void e(int i2, int i3, int i4, int i5) {
        this.f3715i = i2;
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
        return this.f3713g;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f3714h > 0.0f ? -3 : -2;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.o.u(b())) {
            outline.setRoundRect(getBounds(), this.o.r().a(b()));
            return;
        }
        copyBounds(this.f3710d);
        this.f3711e.set(this.f3710d);
        this.f3707a.d(this.o, 1.0f, this.f3711e, this.f3709c);
        if (this.f3709c.isConvex()) {
            outline.setConvexPath(this.f3709c);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        if (!this.o.u(b())) {
            return true;
        }
        int iRound = Math.round(this.f3714h);
        rect.set(iRound, iRound, iRound, iRound);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList = this.p;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.n = true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
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
        this.f3708b.setAlpha(i2);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f3708b.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
