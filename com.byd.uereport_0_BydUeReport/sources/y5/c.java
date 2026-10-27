package y5;

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
import e6.m;
import e6.n;

/* JADX INFO: compiled from: BorderDrawable.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends Drawable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Paint f9330b;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f9336h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f9337i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f9338j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f9339k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f9340l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f9341m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public m f9343o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ColorStateList f9344p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final n f9329a = new n();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Path f9331c = new Path();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f9332d = new Rect();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final RectF f9333e = new RectF();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final RectF f9334f = new RectF();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final b f9335g = new b();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f9342n = true;

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
        this.f9343o = mVar;
        Paint paint = new Paint(1);
        this.f9330b = paint;
        paint.setStyle(Paint.Style.STROKE);
    }

    public final Shader a() {
        copyBounds(this.f9332d);
        float fHeight = this.f9336h / r0.height();
        return new LinearGradient(0.0f, r0.top, 0.0f, r0.bottom, new int[]{g0.a.b(this.f9337i, this.f9341m), g0.a.b(this.f9338j, this.f9341m), g0.a.b(g0.a.d(this.f9338j, 0), this.f9341m), g0.a.b(g0.a.d(this.f9340l, 0), this.f9341m), g0.a.b(this.f9340l, this.f9341m), g0.a.b(this.f9339k, this.f9341m)}, new float[]{0.0f, fHeight, 0.5f, 0.5f, 1.0f - fHeight, 1.0f}, Shader.TileMode.CLAMP);
    }

    public RectF b() {
        this.f9334f.set(getBounds());
        return this.f9334f;
    }

    public void c(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.f9341m = colorStateList.getColorForState(getState(), this.f9341m);
        }
        this.f9344p = colorStateList;
        this.f9342n = true;
        invalidateSelf();
    }

    public void d(float f8) {
        if (this.f9336h != f8) {
            this.f9336h = f8;
            this.f9330b.setStrokeWidth(f8 * 1.3333f);
            this.f9342n = true;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.f9342n) {
            this.f9330b.setShader(a());
            this.f9342n = false;
        }
        float strokeWidth = this.f9330b.getStrokeWidth() / 2.0f;
        copyBounds(this.f9332d);
        this.f9333e.set(this.f9332d);
        float fMin = Math.min(this.f9343o.r().a(b()), this.f9333e.width() / 2.0f);
        if (this.f9343o.u(b())) {
            this.f9333e.inset(strokeWidth, strokeWidth);
            canvas.drawRoundRect(this.f9333e, fMin, fMin, this.f9330b);
        }
    }

    public void e(int i8, int i9, int i10, int i11) {
        this.f9337i = i8;
        this.f9338j = i9;
        this.f9339k = i10;
        this.f9340l = i11;
    }

    public void f(m mVar) {
        this.f9343o = mVar;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f9335g;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f9336h > 0.0f ? -3 : -2;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.f9343o.u(b())) {
            outline.setRoundRect(getBounds(), this.f9343o.r().a(b()));
            return;
        }
        copyBounds(this.f9332d);
        this.f9333e.set(this.f9332d);
        this.f9329a.d(this.f9343o, 1.0f, this.f9333e, this.f9331c);
        if (this.f9331c.isConvex()) {
            outline.setConvexPath(this.f9331c);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        if (!this.f9343o.u(b())) {
            return true;
        }
        int iRound = Math.round(this.f9336h);
        rect.set(iRound, iRound, iRound, iRound);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList = this.f9344p;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.f9342n = true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        int colorForState;
        ColorStateList colorStateList = this.f9344p;
        if (colorStateList != null && (colorForState = colorStateList.getColorForState(iArr, this.f9341m)) != this.f9341m) {
            this.f9342n = true;
            this.f9341m = colorForState;
        }
        if (this.f9342n) {
            invalidateSelf();
        }
        return this.f9342n;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i8) {
        this.f9330b.setAlpha(i8);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f9330b.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
