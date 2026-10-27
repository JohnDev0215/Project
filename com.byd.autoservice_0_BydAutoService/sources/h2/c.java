package h2;

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
import n2.m;
import n2.n;

/* JADX INFO: compiled from: BorderDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends Drawable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Paint f3074b;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f3080h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3081i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f3082j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f3083k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f3084l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3085m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public m f3087o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ColorStateList f3088p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final n f3073a = new n();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Path f3075c = new Path();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f3076d = new Rect();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final RectF f3077e = new RectF();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final RectF f3078f = new RectF();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final b f3079g = new b();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f3086n = true;

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
        this.f3087o = mVar;
        Paint paint = new Paint(1);
        this.f3074b = paint;
        paint.setStyle(Paint.Style.STROKE);
    }

    public final Shader a() {
        copyBounds(this.f3076d);
        float fHeight = this.f3080h / r0.height();
        return new LinearGradient(0.0f, r0.top, 0.0f, r0.bottom, new int[]{d0.a.b(this.f3081i, this.f3085m), d0.a.b(this.f3082j, this.f3085m), d0.a.b(d0.a.d(this.f3082j, 0), this.f3085m), d0.a.b(d0.a.d(this.f3084l, 0), this.f3085m), d0.a.b(this.f3084l, this.f3085m), d0.a.b(this.f3083k, this.f3085m)}, new float[]{0.0f, fHeight, 0.5f, 0.5f, 1.0f - fHeight, 1.0f}, Shader.TileMode.CLAMP);
    }

    public RectF b() {
        this.f3078f.set(getBounds());
        return this.f3078f;
    }

    public void c(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.f3085m = colorStateList.getColorForState(getState(), this.f3085m);
        }
        this.f3088p = colorStateList;
        this.f3086n = true;
        invalidateSelf();
    }

    public void d(float f3) {
        if (this.f3080h != f3) {
            this.f3080h = f3;
            this.f3074b.setStrokeWidth(f3 * 1.3333f);
            this.f3086n = true;
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.f3086n) {
            this.f3074b.setShader(a());
            this.f3086n = false;
        }
        float strokeWidth = this.f3074b.getStrokeWidth() / 2.0f;
        copyBounds(this.f3076d);
        this.f3077e.set(this.f3076d);
        float fMin = Math.min(this.f3087o.r().a(b()), this.f3077e.width() / 2.0f);
        if (this.f3087o.u(b())) {
            this.f3077e.inset(strokeWidth, strokeWidth);
            canvas.drawRoundRect(this.f3077e, fMin, fMin, this.f3074b);
        }
    }

    public void e(int i3, int i4, int i5, int i6) {
        this.f3081i = i3;
        this.f3082j = i4;
        this.f3083k = i5;
        this.f3084l = i6;
    }

    public void f(m mVar) {
        this.f3087o = mVar;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.f3079g;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.f3080h > 0.0f ? -3 : -2;
    }

    @Override // android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.f3087o.u(b())) {
            outline.setRoundRect(getBounds(), this.f3087o.r().a(b()));
            return;
        }
        copyBounds(this.f3076d);
        this.f3077e.set(this.f3076d);
        this.f3073a.d(this.f3087o, 1.0f, this.f3077e, this.f3075c);
        if (this.f3075c.isConvex()) {
            outline.setConvexPath(this.f3075c);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        if (!this.f3087o.u(b())) {
            return true;
        }
        int iRound = Math.round(this.f3080h);
        rect.set(iRound, iRound, iRound, iRound);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList = this.f3088p;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.f3086n = true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        int colorForState;
        ColorStateList colorStateList = this.f3088p;
        if (colorStateList != null && (colorForState = colorStateList.getColorForState(iArr, this.f3085m)) != this.f3085m) {
            this.f3086n = true;
            this.f3085m = colorForState;
        }
        if (this.f3086n) {
            invalidateSelf();
        }
        return this.f3086n;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i3) {
        this.f3074b.setAlpha(i3);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f3074b.setColorFilter(colorFilter);
        invalidateSelf();
    }
}
