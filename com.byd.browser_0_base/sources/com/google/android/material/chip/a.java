package com.google.android.material.chip;

import android.R;
import android.annotation.TargetApi;
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
import defpackage.c83;
import defpackage.dc2;
import defpackage.fj1;
import defpackage.gv;
import defpackage.s73;
import defpackage.sc1;
import defpackage.vj2;
import defpackage.xt3;
import defpackage.yn3;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* JADX INFO: compiled from: ChipDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends sc1 implements Drawable.Callback, c83.b {
    public static final int[] K0 = {R.attr.state_enabled};
    public static final ShapeDrawable L0 = new ShapeDrawable(new OvalShape());
    public ColorStateList A0;
    public ColorStateList B;
    public PorterDuff.Mode B0;
    public ColorStateList C;
    public int[] C0;
    public float D;
    public boolean D0;
    public float E;
    public ColorStateList E0;
    public ColorStateList F;
    public WeakReference<InterfaceC0033a> F0;
    public float G;
    public TextUtils.TruncateAt G0;
    public ColorStateList H;
    public boolean H0;
    public CharSequence I;
    public int I0;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f29J;
    public boolean J0;
    public Drawable K;
    public ColorStateList L;
    public float M;
    public boolean N;
    public boolean O;
    public Drawable P;
    public RippleDrawable Q;
    public ColorStateList R;
    public float S;
    public SpannableStringBuilder T;
    public boolean U;
    public boolean V;
    public Drawable W;
    public ColorStateList X;
    public fj1 Y;
    public fj1 Z;
    public float a0;
    public float b0;
    public float c0;
    public float d0;
    public float e0;
    public float f0;
    public float g0;
    public float h0;
    public final Context i0;
    public final Paint j0;
    public final Paint.FontMetrics k0;
    public final RectF l0;
    public final PointF m0;
    public final Path n0;
    public final c83 o0;
    public int p0;
    public int q0;
    public int r0;
    public int s0;
    public int t0;
    public int u0;
    public boolean v0;
    public int w0;
    public int x0;
    public ColorFilter y0;
    public PorterDuffColorFilter z0;

    /* JADX INFO: renamed from: com.google.android.material.chip.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChipDrawable.java */
    public interface InterfaceC0033a {
        void a();
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, Chip.B);
        this.E = -1.0f;
        this.j0 = new Paint(1);
        this.k0 = new Paint.FontMetrics();
        this.l0 = new RectF();
        this.m0 = new PointF();
        this.n0 = new Path();
        this.x0 = 255;
        this.B0 = PorterDuff.Mode.SRC_IN;
        this.F0 = new WeakReference<>(null);
        k(context);
        this.i0 = context;
        c83 c83Var = new c83(this);
        this.o0 = c83Var;
        this.I = "";
        c83Var.a.density = context.getResources().getDisplayMetrics().density;
        int[] iArr = K0;
        setState(iArr);
        if (!Arrays.equals(this.C0, iArr)) {
            this.C0 = iArr;
            if (b0()) {
                E(getState(), iArr);
            }
        }
        this.H0 = true;
        L0.setTint(-1);
    }

    public static boolean B(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    public static boolean C(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    public static void c0(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public final float A() {
        return this.J0 ? j() : this.E;
    }

    public final void D() {
        InterfaceC0033a interfaceC0033a = this.F0.get();
        if (interfaceC0033a != null) {
            interfaceC0033a.a();
        }
    }

    public final boolean E(int[] iArr, int[] iArr2) {
        boolean z;
        boolean z2;
        ColorStateList colorStateList;
        boolean zOnStateChange = super.onStateChange(iArr);
        ColorStateList colorStateList2 = this.B;
        int iD = d(colorStateList2 != null ? colorStateList2.getColorForState(iArr, this.p0) : 0);
        boolean state = true;
        if (this.p0 != iD) {
            this.p0 = iD;
            zOnStateChange = true;
        }
        ColorStateList colorStateList3 = this.C;
        int iD2 = d(colorStateList3 != null ? colorStateList3.getColorForState(iArr, this.q0) : 0);
        if (this.q0 != iD2) {
            this.q0 = iD2;
            zOnStateChange = true;
        }
        int iF = gv.f(iD2, iD);
        if ((this.r0 != iF) | (this.e.c == null)) {
            this.r0 = iF;
            n(ColorStateList.valueOf(iF));
            zOnStateChange = true;
        }
        ColorStateList colorStateList4 = this.F;
        int colorForState = colorStateList4 != null ? colorStateList4.getColorForState(iArr, this.s0) : 0;
        if (this.s0 != colorForState) {
            this.s0 = colorForState;
            zOnStateChange = true;
        }
        int colorForState2 = (this.E0 == null || !dc2.d(iArr)) ? 0 : this.E0.getColorForState(iArr, this.t0);
        if (this.t0 != colorForState2) {
            this.t0 = colorForState2;
            if (this.D0) {
                zOnStateChange = true;
            }
        }
        s73 s73Var = this.o0.f;
        int colorForState3 = (s73Var == null || (colorStateList = s73Var.j) == null) ? 0 : colorStateList.getColorForState(iArr, this.u0);
        if (this.u0 != colorForState3) {
            this.u0 = colorForState3;
            zOnStateChange = true;
        }
        int[] state2 = getState();
        if (state2 == null) {
            z = false;
        } else {
            for (int i : state2) {
                if (i == 16842912) {
                    z = true;
                    break;
                }
            }
            z = false;
        }
        boolean z3 = z && this.U;
        if (this.v0 == z3 || this.W == null) {
            z2 = false;
        } else {
            float fY = y();
            this.v0 = z3;
            if (fY != y()) {
                zOnStateChange = true;
                z2 = true;
            } else {
                z2 = false;
                zOnStateChange = true;
            }
        }
        ColorStateList colorStateList5 = this.A0;
        int colorForState4 = colorStateList5 != null ? colorStateList5.getColorForState(iArr, this.w0) : 0;
        if (this.w0 != colorForState4) {
            this.w0 = colorForState4;
            ColorStateList colorStateList6 = this.A0;
            PorterDuff.Mode mode = this.B0;
            this.z0 = (colorStateList6 == null || mode == null) ? null : new PorterDuffColorFilter(colorStateList6.getColorForState(getState(), 0), mode);
        } else {
            state = zOnStateChange;
        }
        if (C(this.K)) {
            state |= this.K.setState(iArr);
        }
        if (C(this.W)) {
            state |= this.W.setState(iArr);
        }
        if (C(this.P)) {
            int[] iArr3 = new int[iArr.length + iArr2.length];
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
            state |= this.P.setState(iArr3);
        }
        if (C(this.Q)) {
            state |= this.Q.setState(iArr2);
        }
        if (state) {
            invalidateSelf();
        }
        if (z2) {
            D();
        }
        return state;
    }

    public final void F(boolean z) {
        if (this.U != z) {
            this.U = z;
            float fY = y();
            if (!z && this.v0) {
                this.v0 = false;
            }
            float fY2 = y();
            invalidateSelf();
            if (fY != fY2) {
                D();
            }
        }
    }

    public final void G(Drawable drawable) {
        if (this.W != drawable) {
            float fY = y();
            this.W = drawable;
            float fY2 = y();
            c0(this.W);
            w(this.W);
            invalidateSelf();
            if (fY != fY2) {
                D();
            }
        }
    }

    public final void H(ColorStateList colorStateList) {
        if (this.X != colorStateList) {
            this.X = colorStateList;
            if (this.V && this.W != null && this.U) {
                this.W.setTintList(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void I(boolean z) {
        if (this.V != z) {
            boolean Z = Z();
            this.V = z;
            boolean Z2 = Z();
            if (Z != Z2) {
                if (Z2) {
                    w(this.W);
                } else {
                    c0(this.W);
                }
                invalidateSelf();
                D();
            }
        }
    }

    @Deprecated
    public final void J(float f) {
        if (this.E != f) {
            this.E = f;
            setShapeAppearanceModel(this.e.a.f(f));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.graphics.drawable.Drawable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    public final void K(Drawable drawable) {
        ?? B;
        Object obj = this.K;
        if (obj != null) {
            boolean z = obj instanceof xt3;
            B = obj;
            if (z) {
                B = ((xt3) obj).b();
            }
        } else {
            B = 0;
        }
        if (B != drawable) {
            float fY = y();
            this.K = drawable != null ? drawable.mutate() : null;
            float fY2 = y();
            c0(B);
            if (a0()) {
                w(this.K);
            }
            invalidateSelf();
            if (fY != fY2) {
                D();
            }
        }
    }

    public final void L(float f) {
        if (this.M != f) {
            float fY = y();
            this.M = f;
            float fY2 = y();
            invalidateSelf();
            if (fY != fY2) {
                D();
            }
        }
    }

    public final void M(ColorStateList colorStateList) {
        this.N = true;
        if (this.L != colorStateList) {
            this.L = colorStateList;
            if (a0()) {
                this.K.setTintList(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void N(boolean z) {
        if (this.f29J != z) {
            boolean zA0 = a0();
            this.f29J = z;
            boolean zA02 = a0();
            if (zA0 != zA02) {
                if (zA02) {
                    w(this.K);
                } else {
                    c0(this.K);
                }
                invalidateSelf();
                D();
            }
        }
    }

    public final void O(ColorStateList colorStateList) {
        if (this.F != colorStateList) {
            this.F = colorStateList;
            if (this.J0) {
                s(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void P(float f) {
        if (this.G != f) {
            this.G = f;
            this.j0.setStrokeWidth(f);
            if (this.J0) {
                this.e.k = f;
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
    public final void Q(Drawable drawable) {
        ?? B;
        Object obj = this.P;
        if (obj != null) {
            boolean z = obj instanceof xt3;
            B = obj;
            if (z) {
                B = ((xt3) obj).b();
            }
        } else {
            B = 0;
        }
        if (B != drawable) {
            float fZ = z();
            this.P = drawable != null ? drawable.mutate() : null;
            this.Q = new RippleDrawable(dc2.c(this.H), this.P, L0);
            float fZ2 = z();
            c0(B);
            if (b0()) {
                w(this.P);
            }
            invalidateSelf();
            if (fZ != fZ2) {
                D();
            }
        }
    }

    public final void R(float f) {
        if (this.g0 != f) {
            this.g0 = f;
            invalidateSelf();
            if (b0()) {
                D();
            }
        }
    }

    public final void S(float f) {
        if (this.S != f) {
            this.S = f;
            invalidateSelf();
            if (b0()) {
                D();
            }
        }
    }

    public final void T(float f) {
        if (this.f0 != f) {
            this.f0 = f;
            invalidateSelf();
            if (b0()) {
                D();
            }
        }
    }

    public final void U(ColorStateList colorStateList) {
        if (this.R != colorStateList) {
            this.R = colorStateList;
            if (b0()) {
                this.P.setTintList(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void V(boolean z) {
        if (this.O != z) {
            boolean zB0 = b0();
            this.O = z;
            boolean zB02 = b0();
            if (zB0 != zB02) {
                if (zB02) {
                    w(this.P);
                } else {
                    c0(this.P);
                }
                invalidateSelf();
                D();
            }
        }
    }

    public final void W(float f) {
        if (this.c0 != f) {
            float fY = y();
            this.c0 = f;
            float fY2 = y();
            invalidateSelf();
            if (fY != fY2) {
                D();
            }
        }
    }

    public final void X(float f) {
        if (this.b0 != f) {
            float fY = y();
            this.b0 = f;
            float fY2 = y();
            invalidateSelf();
            if (fY != fY2) {
                D();
            }
        }
    }

    public final void Y(ColorStateList colorStateList) {
        if (this.H != colorStateList) {
            this.H = colorStateList;
            this.E0 = this.D0 ? dc2.c(colorStateList) : null;
            onStateChange(getState());
        }
    }

    public final boolean Z() {
        return this.V && this.W != null && this.v0;
    }

    @Override // c83.b
    public final void a() {
        D();
        invalidateSelf();
    }

    public final boolean a0() {
        return this.f29J && this.K != null;
    }

    public final boolean b0() {
        return this.O && this.P != null;
    }

    @Override // defpackage.sc1, android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        int i;
        RectF rectF;
        int i2;
        int i3;
        int i4;
        RectF rectF2;
        int iSave;
        Rect bounds = getBounds();
        if (bounds.isEmpty() || (i = this.x0) == 0) {
            return;
        }
        int iSaveLayerAlpha = i < 255 ? canvas.saveLayerAlpha(bounds.left, bounds.top, bounds.right, bounds.bottom, i) : 0;
        boolean z = this.J0;
        Paint paint = this.j0;
        RectF rectF3 = this.l0;
        if (!z) {
            paint.setColor(this.p0);
            paint.setStyle(Paint.Style.FILL);
            rectF3.set(bounds);
            canvas.drawRoundRect(rectF3, A(), A(), paint);
        }
        if (!this.J0) {
            paint.setColor(this.q0);
            paint.setStyle(Paint.Style.FILL);
            ColorFilter colorFilter = this.y0;
            if (colorFilter == null) {
                colorFilter = this.z0;
            }
            paint.setColorFilter(colorFilter);
            rectF3.set(bounds);
            canvas.drawRoundRect(rectF3, A(), A(), paint);
        }
        if (this.J0) {
            super.draw(canvas);
        }
        if (this.G > 0.0f && !this.J0) {
            paint.setColor(this.s0);
            paint.setStyle(Paint.Style.STROKE);
            if (!this.J0) {
                ColorFilter colorFilter2 = this.y0;
                if (colorFilter2 == null) {
                    colorFilter2 = this.z0;
                }
                paint.setColorFilter(colorFilter2);
            }
            float f = bounds.left;
            float f2 = this.G / 2.0f;
            rectF3.set(f + f2, bounds.top + f2, bounds.right - f2, bounds.bottom - f2);
            float f3 = this.E - (this.G / 2.0f);
            canvas.drawRoundRect(rectF3, f3, f3, paint);
        }
        paint.setColor(this.t0);
        paint.setStyle(Paint.Style.FILL);
        rectF3.set(bounds);
        if (this.J0) {
            RectF rectF4 = new RectF(bounds);
            Path path = this.n0;
            vj2 vj2Var = this.v;
            sc1.b bVar = this.e;
            vj2Var.a(bVar.a, bVar.j, rectF4, this.u, path);
            f(canvas, paint, path, this.e.a, h());
        } else {
            canvas.drawRoundRect(rectF3, A(), A(), paint);
        }
        if (a0()) {
            x(bounds, rectF3);
            float f4 = rectF3.left;
            float f5 = rectF3.top;
            canvas.translate(f4, f5);
            this.K.setBounds(0, 0, (int) rectF3.width(), (int) rectF3.height());
            this.K.draw(canvas);
            canvas.translate(-f4, -f5);
        }
        if (Z()) {
            x(bounds, rectF3);
            float f6 = rectF3.left;
            float f7 = rectF3.top;
            canvas.translate(f6, f7);
            this.W.setBounds(0, 0, (int) rectF3.width(), (int) rectF3.height());
            this.W.draw(canvas);
            canvas.translate(-f6, -f7);
        }
        if (!this.H0 || this.I == null) {
            rectF = rectF3;
            i2 = iSaveLayerAlpha;
            i3 = 0;
            i4 = 255;
        } else {
            PointF pointF = this.m0;
            pointF.set(0.0f, 0.0f);
            Paint.Align align = Paint.Align.LEFT;
            CharSequence charSequence = this.I;
            c83 c83Var = this.o0;
            if (charSequence != null) {
                float fY = y() + this.a0 + this.d0;
                if (getLayoutDirection() == 0) {
                    pointF.x = bounds.left + fY;
                    align = Paint.Align.LEFT;
                } else {
                    pointF.x = bounds.right - fY;
                    align = Paint.Align.RIGHT;
                }
                float fCenterY = bounds.centerY();
                TextPaint textPaint = c83Var.a;
                Paint.FontMetrics fontMetrics = this.k0;
                textPaint.getFontMetrics(fontMetrics);
                pointF.y = fCenterY - ((fontMetrics.descent + fontMetrics.ascent) / 2.0f);
            }
            rectF3.setEmpty();
            if (this.I != null) {
                float fY2 = y() + this.a0 + this.d0;
                float fZ = z() + this.h0 + this.e0;
                if (getLayoutDirection() == 0) {
                    rectF3.left = bounds.left + fY2;
                    rectF3.right = bounds.right - fZ;
                } else {
                    rectF3.left = bounds.left + fZ;
                    rectF3.right = bounds.right - fY2;
                }
                rectF3.top = bounds.top;
                rectF3.bottom = bounds.bottom;
            }
            s73 s73Var = c83Var.f;
            TextPaint textPaint2 = c83Var.a;
            if (s73Var != null) {
                textPaint2.drawableState = getState();
                c83Var.f.e(this.i0, textPaint2, c83Var.b);
            }
            textPaint2.setTextAlign(align);
            boolean z2 = Math.round(c83Var.a(this.I.toString())) > Math.round(rectF3.width());
            if (z2) {
                iSave = canvas.save();
                canvas.clipRect(rectF3);
            } else {
                iSave = 0;
            }
            CharSequence charSequenceEllipsize = this.I;
            if (z2 && this.G0 != null) {
                charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, textPaint2, rectF3.width(), this.G0);
            }
            CharSequence charSequence2 = charSequenceEllipsize;
            int length = charSequence2.length();
            float f8 = pointF.x;
            float f9 = pointF.y;
            rectF = rectF3;
            i2 = iSaveLayerAlpha;
            i3 = 0;
            i4 = 255;
            canvas.drawText(charSequence2, 0, length, f8, f9, textPaint2);
            if (z2) {
                canvas.restoreToCount(iSave);
            }
        }
        if (b0()) {
            rectF.setEmpty();
            if (b0()) {
                float f10 = this.h0 + this.g0;
                if (getLayoutDirection() == 0) {
                    float f11 = bounds.right - f10;
                    rectF2 = rectF;
                    rectF2.right = f11;
                    rectF2.left = f11 - this.S;
                } else {
                    rectF2 = rectF;
                    float f12 = bounds.left + f10;
                    rectF2.left = f12;
                    rectF2.right = f12 + this.S;
                }
                float fExactCenterY = bounds.exactCenterY();
                float f13 = this.S;
                float f14 = fExactCenterY - (f13 / 2.0f);
                rectF2.top = f14;
                rectF2.bottom = f14 + f13;
            } else {
                rectF2 = rectF;
            }
            float f15 = rectF2.left;
            float f16 = rectF2.top;
            canvas.translate(f15, f16);
            this.P.setBounds(i3, i3, (int) rectF2.width(), (int) rectF2.height());
            this.Q.setBounds(this.P.getBounds());
            this.Q.jumpToCurrentState();
            this.Q.draw(canvas);
            canvas.translate(-f15, -f16);
        }
        if (this.x0 < i4) {
            canvas.restoreToCount(i2);
        }
    }

    @Override // defpackage.sc1, android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.x0;
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        return this.y0;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return (int) this.D;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return Math.min(Math.round(z() + this.o0.a(this.I.toString()) + y() + this.a0 + this.d0 + this.e0 + this.h0), this.I0);
    }

    @Override // defpackage.sc1, android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // defpackage.sc1, android.graphics.drawable.Drawable
    @TargetApi(21)
    public final void getOutline(Outline outline) {
        if (this.J0) {
            super.getOutline(outline);
            return;
        }
        Rect bounds = getBounds();
        if (bounds.isEmpty()) {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), (int) this.D, this.E);
        } else {
            outline.setRoundRect(bounds, this.E);
        }
        outline.setAlpha(this.x0 / 255.0f);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // defpackage.sc1, android.graphics.drawable.Drawable
    public final boolean isStateful() {
        ColorStateList colorStateList;
        if (B(this.B) || B(this.C) || B(this.F)) {
            return true;
        }
        if (this.D0 && B(this.E0)) {
            return true;
        }
        s73 s73Var = this.o0.f;
        if ((s73Var == null || (colorStateList = s73Var.j) == null || !colorStateList.isStateful()) ? false : true) {
            return true;
        }
        return (this.V && this.W != null && this.U) || C(this.K) || C(this.W) || B(this.A0);
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLayoutDirectionChanged(int i) {
        boolean zOnLayoutDirectionChanged = super.onLayoutDirectionChanged(i);
        if (a0()) {
            zOnLayoutDirectionChanged |= this.K.setLayoutDirection(i);
        }
        if (Z()) {
            zOnLayoutDirectionChanged |= this.W.setLayoutDirection(i);
        }
        if (b0()) {
            zOnLayoutDirectionChanged |= this.P.setLayoutDirection(i);
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
        if (a0()) {
            zOnLevelChange |= this.K.setLevel(i);
        }
        if (Z()) {
            zOnLevelChange |= this.W.setLevel(i);
        }
        if (b0()) {
            zOnLevelChange |= this.P.setLevel(i);
        }
        if (zOnLevelChange) {
            invalidateSelf();
        }
        return zOnLevelChange;
    }

    @Override // defpackage.sc1, android.graphics.drawable.Drawable, c83.b
    public final boolean onStateChange(int[] iArr) {
        if (this.J0) {
            super.onStateChange(iArr);
        }
        return E(iArr, this.C0);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // defpackage.sc1, android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        if (this.x0 != i) {
            this.x0 = i;
            invalidateSelf();
        }
    }

    @Override // defpackage.sc1, android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        if (this.y0 != colorFilter) {
            this.y0 = colorFilter;
            invalidateSelf();
        }
    }

    @Override // defpackage.sc1, android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        if (this.A0 != colorStateList) {
            this.A0 = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // defpackage.sc1, android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        if (this.B0 != mode) {
            this.B0 = mode;
            ColorStateList colorStateList = this.A0;
            this.z0 = (colorStateList == null || mode == null) ? null : new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (a0()) {
            visible |= this.K.setVisible(z, z2);
        }
        if (Z()) {
            visible |= this.W.setVisible(z, z2);
        }
        if (b0()) {
            visible |= this.P.setVisible(z, z2);
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

    public final void w(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        drawable.setCallback(this);
        drawable.setLayoutDirection(getLayoutDirection());
        drawable.setLevel(getLevel());
        drawable.setVisible(isVisible(), false);
        if (drawable == this.P) {
            if (drawable.isStateful()) {
                drawable.setState(this.C0);
            }
            drawable.setTintList(this.R);
            return;
        }
        Drawable drawable2 = this.K;
        if (drawable == drawable2 && this.N) {
            drawable2.setTintList(this.L);
        }
        if (drawable.isStateful()) {
            drawable.setState(getState());
        }
    }

    public final void x(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (a0() || Z()) {
            float f = this.a0 + this.b0;
            Drawable drawable = this.v0 ? this.W : this.K;
            float intrinsicWidth = this.M;
            if (intrinsicWidth <= 0.0f && drawable != null) {
                intrinsicWidth = drawable.getIntrinsicWidth();
            }
            if (getLayoutDirection() == 0) {
                float f2 = rect.left + f;
                rectF.left = f2;
                rectF.right = f2 + intrinsicWidth;
            } else {
                float f3 = rect.right - f;
                rectF.right = f3;
                rectF.left = f3 - intrinsicWidth;
            }
            Drawable drawable2 = this.v0 ? this.W : this.K;
            float f4 = this.M;
            if (f4 <= 0.0f && drawable2 != null) {
                float fCeil = (float) Math.ceil(yn3.b(this.i0, 24));
                if (drawable2.getIntrinsicHeight() <= fCeil) {
                    fCeil = drawable2.getIntrinsicHeight();
                }
                f4 = fCeil;
            }
            float fExactCenterY = rect.exactCenterY() - (f4 / 2.0f);
            rectF.top = fExactCenterY;
            rectF.bottom = fExactCenterY + f4;
        }
    }

    public final float y() {
        if (!a0() && !Z()) {
            return 0.0f;
        }
        float f = this.b0;
        Drawable drawable = this.v0 ? this.W : this.K;
        float intrinsicWidth = this.M;
        if (intrinsicWidth <= 0.0f && drawable != null) {
            intrinsicWidth = drawable.getIntrinsicWidth();
        }
        return intrinsicWidth + f + this.c0;
    }

    public final float z() {
        if (b0()) {
            return this.f0 + this.S + this.g0;
        }
        return 0.0f;
    }
}
