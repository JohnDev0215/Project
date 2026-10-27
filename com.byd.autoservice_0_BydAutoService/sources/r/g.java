package r;

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
import androidx.cardview.R$color;
import androidx.cardview.R$dimen;

/* JADX INFO: compiled from: RoundRectDrawableWithShadow.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends Drawable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final double f4656a = Math.cos(Math.toRadians(45.0d));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static a f4657b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f4658c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Paint f4660e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Paint f4661f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final RectF f4662g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f4663h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Path f4664i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f4665j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f4666k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f4667l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ColorStateList f4668m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f4670o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f4671p;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f4669n = true;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f4672q = true;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f4673r = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Paint f4659d = new Paint(5);

    /* JADX INFO: compiled from: RoundRectDrawableWithShadow.java */
    public interface a {
        void a(Canvas canvas, RectF rectF, float f3, Paint paint);
    }

    public g(Resources resources, ColorStateList colorStateList, float f3, float f4, float f5) {
        this.f4670o = resources.getColor(R$color.cardview_shadow_start_color);
        this.f4671p = resources.getColor(R$color.cardview_shadow_end_color);
        this.f4658c = resources.getDimensionPixelSize(R$dimen.cardview_compat_inset_shadow);
        n(colorStateList);
        Paint paint = new Paint(5);
        this.f4660e = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f4663h = (int) (f3 + 0.5f);
        this.f4662g = new RectF();
        Paint paint2 = new Paint(this.f4660e);
        this.f4661f = paint2;
        paint2.setAntiAlias(false);
        s(f4, f5);
    }

    public static float c(float f3, float f4, boolean z3) {
        return z3 ? (float) (((double) f3) + ((1.0d - f4656a) * ((double) f4))) : f3;
    }

    public static float d(float f3, float f4, boolean z3) {
        return z3 ? (float) (((double) (f3 * 1.5f)) + ((1.0d - f4656a) * ((double) f4))) : f3 * 1.5f;
    }

    public final void a(Rect rect) {
        float f3 = this.f4665j;
        float f4 = 1.5f * f3;
        this.f4662g.set(rect.left + f3, rect.top + f4, rect.right - f3, rect.bottom - f4);
        b();
    }

    public final void b() {
        float f3 = this.f4663h;
        RectF rectF = new RectF(-f3, -f3, f3, f3);
        RectF rectF2 = new RectF(rectF);
        float f4 = this.f4666k;
        rectF2.inset(-f4, -f4);
        Path path = this.f4664i;
        if (path == null) {
            this.f4664i = new Path();
        } else {
            path.reset();
        }
        this.f4664i.setFillType(Path.FillType.EVEN_ODD);
        this.f4664i.moveTo(-this.f4663h, 0.0f);
        this.f4664i.rLineTo(-this.f4666k, 0.0f);
        this.f4664i.arcTo(rectF2, 180.0f, 90.0f, false);
        this.f4664i.arcTo(rectF, 270.0f, -90.0f, false);
        this.f4664i.close();
        float f5 = this.f4663h;
        float f6 = this.f4666k;
        float f7 = f5 / (f5 + f6);
        Paint paint = this.f4660e;
        float f8 = f5 + f6;
        int i3 = this.f4670o;
        paint.setShader(new RadialGradient(0.0f, 0.0f, f8, new int[]{i3, i3, this.f4671p}, new float[]{0.0f, f7, 1.0f}, Shader.TileMode.CLAMP));
        Paint paint2 = this.f4661f;
        float f9 = this.f4663h;
        float f10 = this.f4666k;
        int i4 = this.f4670o;
        paint2.setShader(new LinearGradient(0.0f, (-f9) + f10, 0.0f, (-f9) - f10, new int[]{i4, i4, this.f4671p}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));
        this.f4661f.setAntiAlias(false);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.f4669n) {
            a(getBounds());
            this.f4669n = false;
        }
        canvas.translate(0.0f, this.f4667l / 2.0f);
        e(canvas);
        canvas.translate(0.0f, (-this.f4667l) / 2.0f);
        f4657b.a(canvas, this.f4662g, this.f4663h, this.f4659d);
    }

    public final void e(Canvas canvas) {
        float f3 = this.f4663h;
        float f4 = (-f3) - this.f4666k;
        float f5 = f3 + this.f4658c + (this.f4667l / 2.0f);
        float f6 = f5 * 2.0f;
        boolean z3 = this.f4662g.width() - f6 > 0.0f;
        boolean z4 = this.f4662g.height() - f6 > 0.0f;
        int iSave = canvas.save();
        RectF rectF = this.f4662g;
        canvas.translate(rectF.left + f5, rectF.top + f5);
        canvas.drawPath(this.f4664i, this.f4660e);
        if (z3) {
            canvas.drawRect(0.0f, f4, this.f4662g.width() - f6, -this.f4663h, this.f4661f);
        }
        canvas.restoreToCount(iSave);
        int iSave2 = canvas.save();
        RectF rectF2 = this.f4662g;
        canvas.translate(rectF2.right - f5, rectF2.bottom - f5);
        canvas.rotate(180.0f);
        canvas.drawPath(this.f4664i, this.f4660e);
        if (z3) {
            canvas.drawRect(0.0f, f4, this.f4662g.width() - f6, (-this.f4663h) + this.f4666k, this.f4661f);
        }
        canvas.restoreToCount(iSave2);
        int iSave3 = canvas.save();
        RectF rectF3 = this.f4662g;
        canvas.translate(rectF3.left + f5, rectF3.bottom - f5);
        canvas.rotate(270.0f);
        canvas.drawPath(this.f4664i, this.f4660e);
        if (z4) {
            canvas.drawRect(0.0f, f4, this.f4662g.height() - f6, -this.f4663h, this.f4661f);
        }
        canvas.restoreToCount(iSave3);
        int iSave4 = canvas.save();
        RectF rectF4 = this.f4662g;
        canvas.translate(rectF4.right - f5, rectF4.top + f5);
        canvas.rotate(90.0f);
        canvas.drawPath(this.f4664i, this.f4660e);
        if (z4) {
            canvas.drawRect(0.0f, f4, this.f4662g.height() - f6, -this.f4663h, this.f4661f);
        }
        canvas.restoreToCount(iSave4);
    }

    public ColorStateList f() {
        return this.f4668m;
    }

    public float g() {
        return this.f4663h;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        int iCeil = (int) Math.ceil(d(this.f4665j, this.f4663h, this.f4672q));
        int iCeil2 = (int) Math.ceil(c(this.f4665j, this.f4663h, this.f4672q));
        rect.set(iCeil2, iCeil, iCeil2, iCeil);
        return true;
    }

    public void h(Rect rect) {
        getPadding(rect);
    }

    public float i() {
        return this.f4665j;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList = this.f4668m;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    public float j() {
        float f3 = this.f4665j;
        return (Math.max(f3, this.f4663h + this.f4658c + ((f3 * 1.5f) / 2.0f)) * 2.0f) + (((this.f4665j * 1.5f) + this.f4658c) * 2.0f);
    }

    public float k() {
        float f3 = this.f4665j;
        return (Math.max(f3, this.f4663h + this.f4658c + (f3 / 2.0f)) * 2.0f) + ((this.f4665j + this.f4658c) * 2.0f);
    }

    public float l() {
        return this.f4667l;
    }

    public void m(boolean z3) {
        this.f4672q = z3;
        invalidateSelf();
    }

    public final void n(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.f4668m = colorStateList;
        this.f4659d.setColor(colorStateList.getColorForState(getState(), this.f4668m.getDefaultColor()));
    }

    public void o(ColorStateList colorStateList) {
        n(colorStateList);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f4669n = true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        ColorStateList colorStateList = this.f4668m;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        if (this.f4659d.getColor() == colorForState) {
            return false;
        }
        this.f4659d.setColor(colorForState);
        this.f4669n = true;
        invalidateSelf();
        return true;
    }

    public void p(float f3) {
        if (f3 < 0.0f) {
            throw new IllegalArgumentException("Invalid radius " + f3 + ". Must be >= 0");
        }
        float f4 = (int) (f3 + 0.5f);
        if (this.f4663h == f4) {
            return;
        }
        this.f4663h = f4;
        this.f4669n = true;
        invalidateSelf();
    }

    public void q(float f3) {
        s(this.f4667l, f3);
    }

    public void r(float f3) {
        s(f3, this.f4665j);
    }

    public final void s(float f3, float f4) {
        if (f3 < 0.0f) {
            throw new IllegalArgumentException("Invalid shadow size " + f3 + ". Must be >= 0");
        }
        if (f4 < 0.0f) {
            throw new IllegalArgumentException("Invalid max shadow size " + f4 + ". Must be >= 0");
        }
        float fT = t(f3);
        float fT2 = t(f4);
        if (fT > fT2) {
            if (!this.f4673r) {
                this.f4673r = true;
            }
            fT = fT2;
        }
        if (this.f4667l == fT && this.f4665j == fT2) {
            return;
        }
        this.f4667l = fT;
        this.f4665j = fT2;
        this.f4666k = (int) ((fT * 1.5f) + this.f4658c + 0.5f);
        this.f4669n = true;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i3) {
        this.f4659d.setAlpha(i3);
        this.f4660e.setAlpha(i3);
        this.f4661f.setAlpha(i3);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f4659d.setColorFilter(colorFilter);
    }

    public final int t(float f3) {
        int i3 = (int) (f3 + 0.5f);
        return i3 % 2 == 1 ? i3 - 1 : i3;
    }
}
