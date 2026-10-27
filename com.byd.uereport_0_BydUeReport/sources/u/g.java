package u;

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
    public static final double f8101a = Math.cos(Math.toRadians(45.0d));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static a f8102b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f8103c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Paint f8105e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Paint f8106f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final RectF f8107g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f8108h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Path f8109i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f8110j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f8111k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f8112l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ColorStateList f8113m;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final int f8115o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f8116p;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f8114n = true;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f8117q = true;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f8118r = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Paint f8104d = new Paint(5);

    /* JADX INFO: compiled from: RoundRectDrawableWithShadow.java */
    public interface a {
        void a(Canvas canvas, RectF rectF, float f8, Paint paint);
    }

    public g(Resources resources, ColorStateList colorStateList, float f8, float f9, float f10) {
        this.f8115o = resources.getColor(R$color.cardview_shadow_start_color);
        this.f8116p = resources.getColor(R$color.cardview_shadow_end_color);
        this.f8103c = resources.getDimensionPixelSize(R$dimen.cardview_compat_inset_shadow);
        n(colorStateList);
        Paint paint = new Paint(5);
        this.f8105e = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f8108h = (int) (f8 + 0.5f);
        this.f8107g = new RectF();
        Paint paint2 = new Paint(this.f8105e);
        this.f8106f = paint2;
        paint2.setAntiAlias(false);
        s(f9, f10);
    }

    public static float c(float f8, float f9, boolean z7) {
        return z7 ? (float) (((double) f8) + ((1.0d - f8101a) * ((double) f9))) : f8;
    }

    public static float d(float f8, float f9, boolean z7) {
        return z7 ? (float) (((double) (f8 * 1.5f)) + ((1.0d - f8101a) * ((double) f9))) : f8 * 1.5f;
    }

    public final void a(Rect rect) {
        float f8 = this.f8110j;
        float f9 = 1.5f * f8;
        this.f8107g.set(rect.left + f8, rect.top + f9, rect.right - f8, rect.bottom - f9);
        b();
    }

    public final void b() {
        float f8 = this.f8108h;
        RectF rectF = new RectF(-f8, -f8, f8, f8);
        RectF rectF2 = new RectF(rectF);
        float f9 = this.f8111k;
        rectF2.inset(-f9, -f9);
        Path path = this.f8109i;
        if (path == null) {
            this.f8109i = new Path();
        } else {
            path.reset();
        }
        this.f8109i.setFillType(Path.FillType.EVEN_ODD);
        this.f8109i.moveTo(-this.f8108h, 0.0f);
        this.f8109i.rLineTo(-this.f8111k, 0.0f);
        this.f8109i.arcTo(rectF2, 180.0f, 90.0f, false);
        this.f8109i.arcTo(rectF, 270.0f, -90.0f, false);
        this.f8109i.close();
        float f10 = this.f8108h;
        float f11 = this.f8111k;
        float f12 = f10 / (f10 + f11);
        Paint paint = this.f8105e;
        float f13 = f10 + f11;
        int i8 = this.f8115o;
        paint.setShader(new RadialGradient(0.0f, 0.0f, f13, new int[]{i8, i8, this.f8116p}, new float[]{0.0f, f12, 1.0f}, Shader.TileMode.CLAMP));
        Paint paint2 = this.f8106f;
        float f14 = this.f8108h;
        float f15 = this.f8111k;
        int i9 = this.f8115o;
        paint2.setShader(new LinearGradient(0.0f, (-f14) + f15, 0.0f, (-f14) - f15, new int[]{i9, i9, this.f8116p}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));
        this.f8106f.setAntiAlias(false);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.f8114n) {
            a(getBounds());
            this.f8114n = false;
        }
        canvas.translate(0.0f, this.f8112l / 2.0f);
        e(canvas);
        canvas.translate(0.0f, (-this.f8112l) / 2.0f);
        f8102b.a(canvas, this.f8107g, this.f8108h, this.f8104d);
    }

    public final void e(Canvas canvas) {
        float f8 = this.f8108h;
        float f9 = (-f8) - this.f8111k;
        float f10 = f8 + this.f8103c + (this.f8112l / 2.0f);
        float f11 = f10 * 2.0f;
        boolean z7 = this.f8107g.width() - f11 > 0.0f;
        boolean z8 = this.f8107g.height() - f11 > 0.0f;
        int iSave = canvas.save();
        RectF rectF = this.f8107g;
        canvas.translate(rectF.left + f10, rectF.top + f10);
        canvas.drawPath(this.f8109i, this.f8105e);
        if (z7) {
            canvas.drawRect(0.0f, f9, this.f8107g.width() - f11, -this.f8108h, this.f8106f);
        }
        canvas.restoreToCount(iSave);
        int iSave2 = canvas.save();
        RectF rectF2 = this.f8107g;
        canvas.translate(rectF2.right - f10, rectF2.bottom - f10);
        canvas.rotate(180.0f);
        canvas.drawPath(this.f8109i, this.f8105e);
        if (z7) {
            canvas.drawRect(0.0f, f9, this.f8107g.width() - f11, (-this.f8108h) + this.f8111k, this.f8106f);
        }
        canvas.restoreToCount(iSave2);
        int iSave3 = canvas.save();
        RectF rectF3 = this.f8107g;
        canvas.translate(rectF3.left + f10, rectF3.bottom - f10);
        canvas.rotate(270.0f);
        canvas.drawPath(this.f8109i, this.f8105e);
        if (z8) {
            canvas.drawRect(0.0f, f9, this.f8107g.height() - f11, -this.f8108h, this.f8106f);
        }
        canvas.restoreToCount(iSave3);
        int iSave4 = canvas.save();
        RectF rectF4 = this.f8107g;
        canvas.translate(rectF4.right - f10, rectF4.top + f10);
        canvas.rotate(90.0f);
        canvas.drawPath(this.f8109i, this.f8105e);
        if (z8) {
            canvas.drawRect(0.0f, f9, this.f8107g.height() - f11, -this.f8108h, this.f8106f);
        }
        canvas.restoreToCount(iSave4);
    }

    public ColorStateList f() {
        return this.f8113m;
    }

    public float g() {
        return this.f8108h;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        int iCeil = (int) Math.ceil(d(this.f8110j, this.f8108h, this.f8117q));
        int iCeil2 = (int) Math.ceil(c(this.f8110j, this.f8108h, this.f8117q));
        rect.set(iCeil2, iCeil, iCeil2, iCeil);
        return true;
    }

    public void h(Rect rect) {
        getPadding(rect);
    }

    public float i() {
        return this.f8110j;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList = this.f8113m;
        return (colorStateList != null && colorStateList.isStateful()) || super.isStateful();
    }

    public float j() {
        float f8 = this.f8110j;
        return (Math.max(f8, this.f8108h + this.f8103c + ((f8 * 1.5f) / 2.0f)) * 2.0f) + (((this.f8110j * 1.5f) + this.f8103c) * 2.0f);
    }

    public float k() {
        float f8 = this.f8110j;
        return (Math.max(f8, this.f8108h + this.f8103c + (f8 / 2.0f)) * 2.0f) + ((this.f8110j + this.f8103c) * 2.0f);
    }

    public float l() {
        return this.f8112l;
    }

    public void m(boolean z7) {
        this.f8117q = z7;
        invalidateSelf();
    }

    public final void n(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.f8113m = colorStateList;
        this.f8104d.setColor(colorStateList.getColorForState(getState(), this.f8113m.getDefaultColor()));
    }

    public void o(ColorStateList colorStateList) {
        n(colorStateList);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.f8114n = true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        ColorStateList colorStateList = this.f8113m;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        if (this.f8104d.getColor() == colorForState) {
            return false;
        }
        this.f8104d.setColor(colorForState);
        this.f8114n = true;
        invalidateSelf();
        return true;
    }

    public void p(float f8) {
        if (f8 < 0.0f) {
            throw new IllegalArgumentException("Invalid radius " + f8 + ". Must be >= 0");
        }
        float f9 = (int) (f8 + 0.5f);
        if (this.f8108h == f9) {
            return;
        }
        this.f8108h = f9;
        this.f8114n = true;
        invalidateSelf();
    }

    public void q(float f8) {
        s(this.f8112l, f8);
    }

    public void r(float f8) {
        s(f8, this.f8110j);
    }

    public final void s(float f8, float f9) {
        if (f8 < 0.0f) {
            throw new IllegalArgumentException("Invalid shadow size " + f8 + ". Must be >= 0");
        }
        if (f9 < 0.0f) {
            throw new IllegalArgumentException("Invalid max shadow size " + f9 + ". Must be >= 0");
        }
        float fT = t(f8);
        float fT2 = t(f9);
        if (fT > fT2) {
            if (!this.f8118r) {
                this.f8118r = true;
            }
            fT = fT2;
        }
        if (this.f8112l == fT && this.f8110j == fT2) {
            return;
        }
        this.f8112l = fT;
        this.f8110j = fT2;
        this.f8111k = (int) ((fT * 1.5f) + this.f8103c + 0.5f);
        this.f8114n = true;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i8) {
        this.f8104d.setAlpha(i8);
        this.f8105e.setAlpha(i8);
        this.f8106f.setAlpha(i8);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.f8104d.setColorFilter(colorFilter);
    }

    public final int t(float f8) {
        int i8 = (int) (f8 + 0.5f);
        return i8 % 2 == 1 ? i8 - 1 : i8;
    }
}
