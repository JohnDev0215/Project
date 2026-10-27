package p;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.google.android.material.chip.Chip;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class os extends qf1 implements Drawable.Callback, pj2 {
    public static final int[] O0 = {R.attr.state_enabled};
    public static final ShapeDrawable P0 = new ShapeDrawable(new OvalShape());
    public int A0;
    public int B0;
    public ColorFilter C0;
    public PorterDuffColorFilter D0;
    public ColorStateList E0;
    public ColorStateList F;
    public PorterDuff.Mode F0;
    public ColorStateList G;
    public int[] G0;
    public float H;
    public boolean H0;
    public float I;
    public ColorStateList I0;
    public ColorStateList J;
    public WeakReference J0;
    public float K;
    public TextUtils.TruncateAt K0;
    public ColorStateList L;
    public boolean L0;
    public CharSequence M;
    public int M0;
    public boolean N;
    public boolean N0;
    public Drawable O;
    public ColorStateList P;
    public float Q;
    public boolean R;
    public boolean S;
    public Drawable T;
    public RippleDrawable U;
    public ColorStateList V;
    public float W;
    public SpannableStringBuilder X;
    public boolean Y;
    public boolean Z;
    public Drawable a0;
    public ColorStateList b0;
    public ll1 c0;
    public ll1 d0;
    public float e0;
    public float f0;
    public float g0;
    public float h0;
    public float i0;
    public float j0;
    public float k0;
    public float l0;
    public final Context m0;
    public final Paint n0;
    public final Paint.FontMetrics o0;
    public final RectF p0;
    public final PointF q0;
    public final Path r0;
    public final qj2 s0;
    public int t0;
    public int u0;
    public int v0;
    public int w0;
    public int x0;
    public int y0;
    public boolean z0;

    public os(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, com.spotify.music.R.attr.chipStyle, com.spotify.music.R.style.Widget_MaterialComponents_Chip_Action);
        this.I = -1.0f;
        this.n0 = new Paint(1);
        this.o0 = new Paint.FontMetrics();
        this.p0 = new RectF();
        this.q0 = new PointF();
        this.r0 = new Path();
        this.B0 = 255;
        this.F0 = PorterDuff.Mode.SRC_IN;
        this.J0 = new WeakReference(null);
        i(context);
        this.m0 = context;
        qj2 qj2Var = new qj2(this);
        this.s0 = qj2Var;
        this.M = "";
        qj2Var.a.density = context.getResources().getDisplayMetrics().density;
        int[] iArr = O0;
        setState(iArr);
        if (!Arrays.equals(this.G0, iArr)) {
            this.G0 = iArr;
            if (U()) {
                w(getState(), iArr);
            }
        }
        this.L0 = true;
        P0.setTint(-1);
    }

    public static void V(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public static boolean t(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    public static boolean u(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    public final void A(boolean z) {
        if (this.Z != z) {
            boolean zS = S();
            this.Z = z;
            boolean zS2 = S();
            if (zS != zS2) {
                if (zS2) {
                    o(this.a0);
                } else {
                    V(this.a0);
                }
                invalidateSelf();
                v();
            }
        }
    }

    public final void B(float f) {
        if (this.I != f) {
            this.I = f;
            wg wgVarE = this.c.a.e();
            wgVarE.e = new y0(f);
            wgVarE.f = new y0(f);
            wgVarE.g = new y0(f);
            wgVarE.h = new y0(f);
            setShapeAppearanceModel(wgVarE.a());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.graphics.drawable.Drawable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    public final void C(Drawable drawable) {
        ?? r0;
        Object obj = this.O;
        if (obj != null) {
            boolean z = obj instanceof wy2;
            r0 = obj;
            if (z) {
                ((xy2) ((wy2) obj)).getClass();
                r0 = 0;
            }
        } else {
            r0 = 0;
        }
        if (r0 != drawable) {
            float fQ = q();
            this.O = drawable != null ? drawable.mutate() : null;
            float fQ2 = q();
            V(r0);
            if (T()) {
                o(this.O);
            }
            invalidateSelf();
            if (fQ != fQ2) {
                v();
            }
        }
    }

    public final void D(float f) {
        if (this.Q != f) {
            float fQ = q();
            this.Q = f;
            float fQ2 = q();
            invalidateSelf();
            if (fQ != fQ2) {
                v();
            }
        }
    }

    public final void E(ColorStateList colorStateList) {
        this.R = true;
        if (this.P != colorStateList) {
            this.P = colorStateList;
            if (T()) {
                ya0.h(this.O, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void F(boolean z) {
        if (this.N != z) {
            boolean zT = T();
            this.N = z;
            boolean zT2 = T();
            if (zT != zT2) {
                if (zT2) {
                    o(this.O);
                } else {
                    V(this.O);
                }
                invalidateSelf();
                v();
            }
        }
    }

    public final void G(ColorStateList colorStateList) {
        if (this.J != colorStateList) {
            this.J = colorStateList;
            if (this.N0) {
                pf1 pf1Var = this.c;
                if (pf1Var.d != colorStateList) {
                    pf1Var.d = colorStateList;
                    onStateChange(getState());
                }
            }
            onStateChange(getState());
        }
    }

    public final void H(float f) {
        if (this.K != f) {
            this.K = f;
            this.n0.setStrokeWidth(f);
            if (this.N0) {
                this.c.k = f;
                invalidateSelf();
            }
            invalidateSelf();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.graphics.drawable.Drawable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    public final void I(Drawable drawable) {
        ?? r0;
        Object obj = this.T;
        if (obj != null) {
            boolean z = obj instanceof wy2;
            r0 = obj;
            if (z) {
                ((xy2) ((wy2) obj)).getClass();
                r0 = 0;
            }
        } else {
            r0 = 0;
        }
        if (r0 != drawable) {
            float fR = r();
            this.T = drawable != null ? drawable.mutate() : null;
            this.U = new RippleDrawable(q30.t(this.L), this.T, P0);
            float fR2 = r();
            V(r0);
            if (U()) {
                o(this.T);
            }
            invalidateSelf();
            if (fR != fR2) {
                v();
            }
        }
    }

    public final void J(float f) {
        if (this.k0 != f) {
            this.k0 = f;
            invalidateSelf();
            if (U()) {
                v();
            }
        }
    }

    public final void K(float f) {
        if (this.W != f) {
            this.W = f;
            invalidateSelf();
            if (U()) {
                v();
            }
        }
    }

    public final void L(float f) {
        if (this.j0 != f) {
            this.j0 = f;
            invalidateSelf();
            if (U()) {
                v();
            }
        }
    }

    public final void M(ColorStateList colorStateList) {
        if (this.V != colorStateList) {
            this.V = colorStateList;
            if (U()) {
                ya0.h(this.T, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void N(boolean z) {
        if (this.S != z) {
            boolean zU = U();
            this.S = z;
            boolean zU2 = U();
            if (zU != zU2) {
                if (zU2) {
                    o(this.T);
                } else {
                    V(this.T);
                }
                invalidateSelf();
                v();
            }
        }
    }

    public final void O(float f) {
        if (this.g0 != f) {
            float fQ = q();
            this.g0 = f;
            float fQ2 = q();
            invalidateSelf();
            if (fQ != fQ2) {
                v();
            }
        }
    }

    public final void P(float f) {
        if (this.f0 != f) {
            float fQ = q();
            this.f0 = f;
            float fQ2 = q();
            invalidateSelf();
            if (fQ != fQ2) {
                v();
            }
        }
    }

    public final void Q(ColorStateList colorStateList) {
        if (this.L != colorStateList) {
            this.L = colorStateList;
            this.I0 = this.H0 ? q30.t(colorStateList) : null;
            onStateChange(getState());
        }
    }

    public final void R(kj2 kj2Var) {
        qj2 qj2Var = this.s0;
        if (qj2Var.f != kj2Var) {
            qj2Var.f = kj2Var;
            if (kj2Var != null) {
                TextPaint textPaint = qj2Var.a;
                Context context = this.m0;
                ks ksVar = qj2Var.b;
                kj2Var.f(context, textPaint, ksVar);
                pj2 pj2Var = (pj2) qj2Var.e.get();
                if (pj2Var != null) {
                    textPaint.drawableState = pj2Var.getState();
                }
                kj2Var.e(context, textPaint, ksVar);
                qj2Var.d = true;
            }
            pj2 pj2Var2 = (pj2) qj2Var.e.get();
            if (pj2Var2 != null) {
                os osVar = (os) pj2Var2;
                osVar.v();
                osVar.invalidateSelf();
                osVar.onStateChange(pj2Var2.getState());
            }
        }
    }

    public final boolean S() {
        return this.Z && this.a0 != null && this.z0;
    }

    public final boolean T() {
        return this.N && this.O != null;
    }

    public final boolean U() {
        return this.S && this.T != null;
    }

    @Override // p.qf1, android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        int i;
        RectF rectF;
        int i2;
        int i3;
        int i4;
        RectF rectF2;
        float f;
        int iSave;
        Rect bounds = getBounds();
        if (bounds.isEmpty() || (i = this.B0) == 0) {
            return;
        }
        int iSaveLayerAlpha = i < 255 ? canvas.saveLayerAlpha(bounds.left, bounds.top, bounds.right, bounds.bottom, i) : 0;
        boolean z = this.N0;
        Paint paint = this.n0;
        RectF rectF3 = this.p0;
        if (!z) {
            paint.setColor(this.t0);
            paint.setStyle(Paint.Style.FILL);
            rectF3.set(bounds);
            canvas.drawRoundRect(rectF3, s(), s(), paint);
        }
        if (!this.N0) {
            paint.setColor(this.u0);
            paint.setStyle(Paint.Style.FILL);
            ColorFilter colorFilter = this.C0;
            if (colorFilter == null) {
                colorFilter = this.D0;
            }
            paint.setColorFilter(colorFilter);
            rectF3.set(bounds);
            canvas.drawRoundRect(rectF3, s(), s(), paint);
        }
        if (this.N0) {
            super.draw(canvas);
        }
        if (this.K > 0.0f && !this.N0) {
            paint.setColor(this.w0);
            paint.setStyle(Paint.Style.STROKE);
            if (!this.N0) {
                ColorFilter colorFilter2 = this.C0;
                if (colorFilter2 == null) {
                    colorFilter2 = this.D0;
                }
                paint.setColorFilter(colorFilter2);
            }
            float f2 = bounds.left;
            float f3 = this.K / 2.0f;
            rectF3.set(f2 + f3, bounds.top + f3, bounds.right - f3, bounds.bottom - f3);
            float f4 = this.I - (this.K / 2.0f);
            canvas.drawRoundRect(rectF3, f4, f4, paint);
        }
        paint.setColor(this.x0);
        paint.setStyle(Paint.Style.FILL);
        rectF3.set(bounds);
        if (this.N0) {
            RectF rectF4 = new RectF(bounds);
            Path path = this.r0;
            qc2 qc2Var = this.z;
            pf1 pf1Var = this.c;
            qc2Var.a(pf1Var.a, pf1Var.j, rectF4, this.y, path);
            e(canvas, paint, path, this.c.a, g());
        } else {
            canvas.drawRoundRect(rectF3, s(), s(), paint);
        }
        if (T()) {
            p(bounds, rectF3);
            float f5 = rectF3.left;
            float f6 = rectF3.top;
            canvas.translate(f5, f6);
            this.O.setBounds(0, 0, (int) rectF3.width(), (int) rectF3.height());
            this.O.draw(canvas);
            canvas.translate(-f5, -f6);
        }
        if (S()) {
            p(bounds, rectF3);
            float f7 = rectF3.left;
            float f8 = rectF3.top;
            canvas.translate(f7, f8);
            this.a0.setBounds(0, 0, (int) rectF3.width(), (int) rectF3.height());
            this.a0.draw(canvas);
            canvas.translate(-f7, -f8);
        }
        if (!this.L0 || this.M == null) {
            rectF = rectF3;
            i2 = iSaveLayerAlpha;
            i3 = 255;
            i4 = 0;
        } else {
            PointF pointF = this.q0;
            pointF.set(0.0f, 0.0f);
            Paint.Align align = Paint.Align.LEFT;
            CharSequence charSequence = this.M;
            qj2 qj2Var = this.s0;
            if (charSequence != null) {
                float fQ = q() + this.e0 + this.h0;
                if (za0.a(this) == 0) {
                    pointF.x = bounds.left + fQ;
                } else {
                    pointF.x = bounds.right - fQ;
                    align = Paint.Align.RIGHT;
                }
                float fCenterY = bounds.centerY();
                TextPaint textPaint = qj2Var.a;
                Paint.FontMetrics fontMetrics = this.o0;
                textPaint.getFontMetrics(fontMetrics);
                pointF.y = fCenterY - ((fontMetrics.descent + fontMetrics.ascent) / 2.0f);
            }
            rectF3.setEmpty();
            if (this.M != null) {
                float fQ2 = q() + this.e0 + this.h0;
                float fR = r() + this.l0 + this.i0;
                if (za0.a(this) == 0) {
                    rectF3.left = bounds.left + fQ2;
                    rectF3.right = bounds.right - fR;
                } else {
                    rectF3.left = bounds.left + fR;
                    rectF3.right = bounds.right - fQ2;
                }
                rectF3.top = bounds.top;
                rectF3.bottom = bounds.bottom;
            }
            kj2 kj2Var = qj2Var.f;
            TextPaint textPaint2 = qj2Var.a;
            if (kj2Var != null) {
                textPaint2.drawableState = getState();
                qj2Var.f.e(this.m0, textPaint2, qj2Var.b);
            }
            textPaint2.setTextAlign(align);
            String string = this.M.toString();
            if (qj2Var.d) {
                float fMeasureText = string != null ? textPaint2.measureText((CharSequence) string, 0, string.length()) : 0.0f;
                qj2Var.c = fMeasureText;
                qj2Var.d = false;
                f = fMeasureText;
            } else {
                f = qj2Var.c;
            }
            boolean z2 = Math.round(f) > Math.round(rectF3.width());
            if (z2) {
                iSave = canvas.save();
                canvas.clipRect(rectF3);
            } else {
                iSave = 0;
            }
            CharSequence charSequenceEllipsize = this.M;
            if (z2 && this.K0 != null) {
                charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, textPaint2, rectF3.width(), this.K0);
            }
            CharSequence charSequence2 = charSequenceEllipsize;
            int length = charSequence2.length();
            float f9 = pointF.x;
            float f10 = pointF.y;
            rectF = rectF3;
            i2 = iSaveLayerAlpha;
            i3 = 255;
            i4 = 0;
            canvas.drawText(charSequence2, 0, length, f9, f10, textPaint2);
            if (z2) {
                canvas.restoreToCount(iSave);
            }
        }
        if (U()) {
            rectF.setEmpty();
            if (U()) {
                float f11 = this.l0 + this.k0;
                if (za0.a(this) == 0) {
                    float f12 = bounds.right - f11;
                    rectF2 = rectF;
                    rectF2.right = f12;
                    rectF2.left = f12 - this.W;
                } else {
                    rectF2 = rectF;
                    float f13 = bounds.left + f11;
                    rectF2.left = f13;
                    rectF2.right = f13 + this.W;
                }
                float fExactCenterY = bounds.exactCenterY();
                float f14 = this.W;
                float f15 = fExactCenterY - (f14 / 2.0f);
                rectF2.top = f15;
                rectF2.bottom = f15 + f14;
            } else {
                rectF2 = rectF;
            }
            float f16 = rectF2.left;
            float f17 = rectF2.top;
            canvas.translate(f16, f17);
            this.T.setBounds(i4, i4, (int) rectF2.width(), (int) rectF2.height());
            this.U.setBounds(this.T.getBounds());
            this.U.jumpToCurrentState();
            this.U.draw(canvas);
            canvas.translate(-f16, -f17);
        }
        if (this.B0 < i3) {
            canvas.restoreToCount(i2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.B0;
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        return this.C0;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return (int) this.H;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        float fMeasureText;
        float fQ = q() + this.e0 + this.h0;
        String string = this.M.toString();
        qj2 qj2Var = this.s0;
        if (qj2Var.d) {
            fMeasureText = string == null ? 0.0f : qj2Var.a.measureText((CharSequence) string, 0, string.length());
            qj2Var.c = fMeasureText;
            qj2Var.d = false;
        } else {
            fMeasureText = qj2Var.c;
        }
        return Math.min(Math.round(r() + fMeasureText + fQ + this.i0 + this.l0), this.M0);
    }

    @Override // p.qf1, android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // p.qf1, android.graphics.drawable.Drawable
    public final void getOutline(Outline outline) {
        if (this.N0) {
            super.getOutline(outline);
            return;
        }
        Rect bounds = getBounds();
        if (bounds.isEmpty()) {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), (int) this.H, this.I);
        } else {
            outline.setRoundRect(bounds, this.I);
        }
        outline.setAlpha(this.B0 / 255.0f);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // p.qf1, android.graphics.drawable.Drawable
    public final boolean isStateful() {
        kj2 kj2Var;
        ColorStateList colorStateList;
        return t(this.F) || t(this.G) || t(this.J) || (this.H0 && t(this.I0)) || (!((kj2Var = this.s0.f) == null || (colorStateList = kj2Var.j) == null || !colorStateList.isStateful()) || ((this.Z && this.a0 != null && this.Y) || u(this.O) || u(this.a0) || t(this.E0)));
    }

    public final void o(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        drawable.setCallback(this);
        za0.b(drawable, za0.a(this));
        drawable.setLevel(getLevel());
        drawable.setVisible(isVisible(), false);
        if (drawable == this.T) {
            if (drawable.isStateful()) {
                drawable.setState(this.G0);
            }
            ya0.h(drawable, this.V);
            return;
        }
        Drawable drawable2 = this.O;
        if (drawable == drawable2 && this.R) {
            ya0.h(drawable2, this.P);
        }
        if (drawable.isStateful()) {
            drawable.setState(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLayoutDirectionChanged(int i) {
        boolean zOnLayoutDirectionChanged = super.onLayoutDirectionChanged(i);
        if (T()) {
            zOnLayoutDirectionChanged |= za0.b(this.O, i);
        }
        if (S()) {
            zOnLayoutDirectionChanged |= za0.b(this.a0, i);
        }
        if (U()) {
            zOnLayoutDirectionChanged |= za0.b(this.T, i);
        }
        if (!zOnLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLevelChange(int i) {
        boolean zOnLevelChange = super.onLevelChange(i);
        if (T()) {
            zOnLevelChange |= this.O.setLevel(i);
        }
        if (S()) {
            zOnLevelChange |= this.a0.setLevel(i);
        }
        if (U()) {
            zOnLevelChange |= this.T.setLevel(i);
        }
        if (zOnLevelChange) {
            invalidateSelf();
        }
        return zOnLevelChange;
    }

    @Override // p.qf1, android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        if (this.N0) {
            super.onStateChange(iArr);
        }
        return w(iArr, this.G0);
    }

    public final void p(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (T() || S()) {
            float f = this.e0 + this.f0;
            Drawable drawable = this.z0 ? this.a0 : this.O;
            float intrinsicWidth = this.Q;
            if (intrinsicWidth <= 0.0f && drawable != null) {
                intrinsicWidth = drawable.getIntrinsicWidth();
            }
            if (za0.a(this) == 0) {
                float f2 = rect.left + f;
                rectF.left = f2;
                rectF.right = f2 + intrinsicWidth;
            } else {
                float f3 = rect.right - f;
                rectF.right = f3;
                rectF.left = f3 - intrinsicWidth;
            }
            Drawable drawable2 = this.z0 ? this.a0 : this.O;
            float fCeil = this.Q;
            if (fCeil <= 0.0f && drawable2 != null) {
                fCeil = (float) Math.ceil(TypedValue.applyDimension(1, 24, this.m0.getResources().getDisplayMetrics()));
                if (drawable2.getIntrinsicHeight() <= fCeil) {
                    fCeil = drawable2.getIntrinsicHeight();
                }
            }
            float fExactCenterY = rect.exactCenterY() - (fCeil / 2.0f);
            rectF.top = fExactCenterY;
            rectF.bottom = fExactCenterY + fCeil;
        }
    }

    public final float q() {
        if (!T() && !S()) {
            return 0.0f;
        }
        float f = this.f0;
        Drawable drawable = this.z0 ? this.a0 : this.O;
        float intrinsicWidth = this.Q;
        if (intrinsicWidth <= 0.0f && drawable != null) {
            intrinsicWidth = drawable.getIntrinsicWidth();
        }
        return intrinsicWidth + f + this.g0;
    }

    public final float r() {
        if (U()) {
            return this.j0 + this.W + this.k0;
        }
        return 0.0f;
    }

    public final float s() {
        return this.N0 ? this.c.a.e.a(g()) : this.I;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // p.qf1, android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        if (this.B0 != i) {
            this.B0 = i;
            invalidateSelf();
        }
    }

    @Override // p.qf1, android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        if (this.C0 != colorFilter) {
            this.C0 = colorFilter;
            invalidateSelf();
        }
    }

    @Override // p.qf1, android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        if (this.E0 != colorStateList) {
            this.E0 = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // p.qf1, android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        if (this.F0 != mode) {
            this.F0 = mode;
            ColorStateList colorStateList = this.E0;
            this.D0 = (colorStateList == null || mode == null) ? null : new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (T()) {
            visible |= this.O.setVisible(z, z2);
        }
        if (S()) {
            visible |= this.a0.setVisible(z, z2);
        }
        if (U()) {
            visible |= this.T.setVisible(z, z2);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public final void v() {
        ns nsVar = (ns) this.J0.get();
        if (nsVar != null) {
            Chip chip = (Chip) nsVar;
            chip.b(chip.x);
            chip.requestLayout();
            chip.invalidateOutline();
        }
    }

    public final boolean w(int[] iArr, int[] iArr2) {
        boolean z;
        boolean z2;
        ColorStateList colorStateList;
        boolean zOnStateChange = super.onStateChange(iArr);
        ColorStateList colorStateList2 = this.F;
        int iC = c(colorStateList2 != null ? colorStateList2.getColorForState(iArr, this.t0) : 0);
        boolean state = true;
        if (this.t0 != iC) {
            this.t0 = iC;
            zOnStateChange = true;
        }
        ColorStateList colorStateList3 = this.G;
        int iC2 = c(colorStateList3 != null ? colorStateList3.getColorForState(iArr, this.u0) : 0);
        if (this.u0 != iC2) {
            this.u0 = iC2;
            zOnStateChange = true;
        }
        int iB = qu.b(iC2, iC);
        if ((this.v0 != iB) | (this.c.c == null)) {
            this.v0 = iB;
            k(ColorStateList.valueOf(iB));
            zOnStateChange = true;
        }
        ColorStateList colorStateList4 = this.J;
        int colorForState = colorStateList4 != null ? colorStateList4.getColorForState(iArr, this.w0) : 0;
        if (this.w0 != colorForState) {
            this.w0 = colorForState;
            zOnStateChange = true;
        }
        int colorForState2 = (this.I0 == null || !q30.u(iArr)) ? 0 : this.I0.getColorForState(iArr, this.x0);
        if (this.x0 != colorForState2) {
            this.x0 = colorForState2;
            if (this.H0) {
                zOnStateChange = true;
            }
        }
        kj2 kj2Var = this.s0.f;
        int colorForState3 = (kj2Var == null || (colorStateList = kj2Var.j) == null) ? 0 : colorStateList.getColorForState(iArr, this.y0);
        if (this.y0 != colorForState3) {
            this.y0 = colorForState3;
            zOnStateChange = true;
        }
        int[] state2 = getState();
        if (state2 == null) {
            z = false;
        } else {
            int length = state2.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (state2[i] != 16842912) {
                    i++;
                } else if (this.Y) {
                    z = true;
                }
            }
            z = false;
        }
        if (this.z0 == z || this.a0 == null) {
            z2 = false;
        } else {
            float fQ = q();
            this.z0 = z;
            if (fQ != q()) {
                zOnStateChange = true;
                z2 = true;
            } else {
                z2 = false;
                zOnStateChange = true;
            }
        }
        ColorStateList colorStateList5 = this.E0;
        int colorForState4 = colorStateList5 != null ? colorStateList5.getColorForState(iArr, this.A0) : 0;
        if (this.A0 != colorForState4) {
            this.A0 = colorForState4;
            ColorStateList colorStateList6 = this.E0;
            PorterDuff.Mode mode = this.F0;
            this.D0 = (colorStateList6 == null || mode == null) ? null : new PorterDuffColorFilter(colorStateList6.getColorForState(getState(), 0), mode);
        } else {
            state = zOnStateChange;
        }
        if (u(this.O)) {
            state |= this.O.setState(iArr);
        }
        if (u(this.a0)) {
            state |= this.a0.setState(iArr);
        }
        if (u(this.T)) {
            int[] iArr3 = new int[iArr.length + iArr2.length];
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
            state |= this.T.setState(iArr3);
        }
        if (u(this.U)) {
            state |= this.U.setState(iArr2);
        }
        if (state) {
            invalidateSelf();
        }
        if (z2) {
            v();
        }
        return state;
    }

    public final void x(boolean z) {
        if (this.Y != z) {
            this.Y = z;
            float fQ = q();
            if (!z && this.z0) {
                this.z0 = false;
            }
            float fQ2 = q();
            invalidateSelf();
            if (fQ != fQ2) {
                v();
            }
        }
    }

    public final void y(Drawable drawable) {
        if (this.a0 != drawable) {
            float fQ = q();
            this.a0 = drawable;
            float fQ2 = q();
            V(this.a0);
            o(this.a0);
            invalidateSelf();
            if (fQ != fQ2) {
                v();
            }
        }
    }

    public final void z(ColorStateList colorStateList) {
        Drawable drawable;
        if (this.b0 != colorStateList) {
            this.b0 = colorStateList;
            if (this.Z && (drawable = this.a0) != null && this.Y) {
                ya0.h(drawable, colorStateList);
            }
            onStateChange(getState());
        }
    }
}
