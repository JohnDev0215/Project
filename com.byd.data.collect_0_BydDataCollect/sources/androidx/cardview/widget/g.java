package androidx.cardview.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

/* JADX INFO: compiled from: RoundRectDrawableWithShadow.java */
/* JADX INFO: loaded from: classes.dex */
class g extends Drawable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final double f777a = Math.cos(Math.toRadians(45.0d));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static a f778b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f779c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Paint f781e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Paint f782f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final RectF f783g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f784h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private Path f785i;
    private float j;
    private float k;
    private float l;
    private ColorStateList m;
    private final int o;
    private final int p;
    private boolean n = true;
    private boolean q = true;
    private boolean r = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Paint f780d = new Paint(5);

    /* JADX INFO: compiled from: RoundRectDrawableWithShadow.java */
    interface a {
        void a(Canvas canvas, RectF rectF, float f2, Paint paint);
    }

    g(Resources resources, ColorStateList colorStateList, float f2, float f3, float f4) {
        this.o = resources.getColor(b.c.b.cardview_shadow_start_color);
        this.p = resources.getColor(b.c.b.cardview_shadow_end_color);
        this.f779c = resources.getDimensionPixelSize(b.c.c.cardview_compat_inset_shadow);
        n(colorStateList);
        Paint paint = new Paint(5);
        this.f781e = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f784h = (int) (f2 + 0.5f);
        this.f783g = new RectF();
        Paint paint2 = new Paint(this.f781e);
        this.f782f = paint2;
        paint2.setAntiAlias(false);
        s(f3, f4);
    }

    private void a(Rect rect) {
        float f2 = this.j;
        float f3 = 1.5f * f2;
        this.f783g.set(rect.left + f2, rect.top + f3, rect.right - f2, rect.bottom - f3);
        b();
    }

    private void b() {
        float f2 = this.f784h;
        RectF rectF = new RectF(-f2, -f2, f2, f2);
        RectF rectF2 = new RectF(rectF);
        float f3 = this.k;
        rectF2.inset(-f3, -f3);
        Path path = this.f785i;
        if (path == null) {
            this.f785i = new Path();
        } else {
            path.reset();
        }
        this.f785i.setFillType(Path.FillType.EVEN_ODD);
        this.f785i.moveTo(-this.f784h, 0.0f);
        this.f785i.rLineTo(-this.k, 0.0f);
        this.f785i.arcTo(rectF2, 180.0f, 90.0f, false);
        this.f785i.arcTo(rectF, 270.0f, -90.0f, false);
        this.f785i.close();
        float f4 = this.f784h;
        float f5 = this.k;
        float f6 = f4 / (f4 + f5);
        Paint paint = this.f781e;
        float f7 = f4 + f5;
        int i2 = this.o;
        paint.setShader(new RadialGradient(0.0f, 0.0f, f7, new int[]{i2, i2, this.p}, new float[]{0.0f, f6, 1.0f}, Shader.TileMode.CLAMP));
        Paint paint2 = this.f782f;
        float f8 = this.f784h;
        float f9 = this.k;
        int i3 = this.o;
        paint2.setShader(new LinearGradient(0.0f, (-f8) + f9, 0.0f, (-f8) - f9, new int[]{i3, i3, this.p}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));
        this.f782f.setAntiAlias(false);
    }

    static float c(float f2, float f3, boolean z) {
        return z ? (float) (((double) f2) + ((1.0d - f777a) * ((double) f3))) : f2;
    }

    static float d(float f2, float f3, boolean z) {
        return z ? (float) (((double) (f2 * 1.5f)) + ((1.0d - f777a) * ((double) f3))) : f2 * 1.5f;
    }

    private void e(Canvas canvas) {
        float f2 = this.f784h;
        float f3 = (-f2) - this.k;
        float f4 = f2 + this.f779c + (this.l / 2.0f);
        float f5 = f4 * 2.0f;
        boolean z = this.f783g.width() - f5 > 0.0f;
        boolean z2 = this.f783g.height() - f5 > 0.0f;
        int iSave = canvas.save();
        RectF rectF = this.f783g;
        canvas.translate(rectF.left + f4, rectF.top + f4);
        canvas.drawPath(this.f785i, this.f781e);
        if (z) {
            canvas.drawRect(0.0f, f3, this.f783g.width() - f5, -this.f784h, this.f782f);
        }
        canvas.restoreToCount(iSave);
        int iSave2 = canvas.save();
        RectF rectF2 = this.f783g;
        canvas.translate(rectF2.right - f4, rectF2.bottom - f4);
        canvas.rotate(180.0f);
        canvas.drawPath(this.f785i, this.f781e);
        if (z) {
            canvas.drawRect(0.0f, f3, this.f783g.width() - f5, (-this.f784h) + this.k, this.f782f);
        }
        canvas.restoreToCount(iSave2);
        int iSave3 = canvas.save();
        RectF rectF3 = this.f783g;
        canvas.translate(rectF3.left + f4, rectF3.bottom - f4);
        canvas.rotate(270.0f);
        canvas.drawPath(this.f785i, this.f781e);
        if (z2) {
            canvas.drawRect(0.0f, f3, this.f783g.height() - f5, -this.f784h, this.f782f);
        }
        canvas.restoreToCount(iSave3);
        int iSave4 = canvas.save();
        RectF rectF4 = this.f783g;
        canvas.translate(rectF4.right - f4, rectF4.top + f4);
        canvas.rotate(90.0f);
        canvas.drawPath(this.f785i, this.f781e);
        if (z2) {
            canvas.drawRect(0.0f, f3, this.f783g.height() - f5, -this.f784h, this.f782f);
        }
        canvas.restoreToCount(iSave4);
    }

    private void n(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.m = colorStateList;
        this.f780d.setColor(colorStateList.getColorForState(getState(), this.m.getDefaultColor()));
    }

    private void s(float f2, float f3) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("Invalid shadow size " + f2 + ". Must be >= 0");
        }
        if (f3 < 0.0f) {
            throw new IllegalArgumentException("Invalid max shadow size " + f3 + ". Must be >= 0");
        }
        float fT = t(f2);
        float fT2 = t(f3);
        if (fT > fT2) {
            if (!this.r) {
                this.r = true;
            }
            fT = fT2;
        }
        if (this.l == fT && this.j == fT2) {
            return;
        }
        this.l = fT;
        this.j = fT2;
        this.k = (int) ((fT * 1.5f) + this.f779c + 0.5f);
        this.n = true;
        invalidateSelf();
    }

    private int t(float f2) {
        int i2 = (int) (f2 + 0.5f);
        return i2 % 2 == 1 ? i2 - 1 : i2;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.n) {
            a(getBounds());
            this.n = false;
        }
        canvas.translate(0.0f, this.l / 2.0f);
        e(canvas);
        canvas.translate(0.0f, (-this.l) / 2.0f);
        f778b.a(canvas, this.f783g, this.f784h, this.f780d);
    }

    ColorStateList f() {
        return this.m;
    }

    float g() {
        return this.f784h;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        int iCeil = (int) Math.ceil(d(this.j, this.f784h, this.q));
        int iCeil2 = (int) Math.ceil(c(this.j, this.f784h, this.q));
        rect.set(iCeil2, iCeil, iCeil2, iCeil);
        return true;
    }

    void h(Rect rect) {
        getPadding(rect);
    }

    float i() {
        return this.j;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList = this.m;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    float j() {
        float f2 = this.j;
        return (Math.max(f2, this.f784h + this.f779c + ((f2 * 1.5f) / 2.0f)) * 2.0f) + (((this.j * 1.5f) + this.f779c) * 2.0f);
    }

    float k() {
        float f2 = this.j;
        return (Math.max(f2, this.f784h + this.f779c + (f2 / 2.0f)) * 2.0f) + ((this.j + this.f779c) * 2.0f);
    }

    float l() {
        return this.l;
    }

    void m(boolean z) {
        this.q = z;
        invalidateSelf();
    }

    void o(ColorStateList colorStateList) {
        n(colorStateList);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.n = true;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        ColorStateList colorStateList = this.m;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        if (this.f780d.getColor() == colorForState) {
            return false;
        }
        this.f780d.setColor(colorForState);
        this.n = true;
        invalidateSelf();
        return true;
    }

    void p(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("Invalid radius " + f2 + ". Must be >= 0");
        }
        float f3 = (int) (f2 + 0.5f);
        if (this.f784h == f3) {
            return;
        }
        this.f784h = f3;
        this.n = true;
        invalidateSelf();
    }

    void q(float f2) {
        s(this.l, f2);
    }

    void r(float f2) {
        s(f2, this.j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.f780d.setAlpha(i2);
        this.f781e.setAlpha(i2);
        this.f782f.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f780d.setColorFilter(colorFilter);
    }
}
