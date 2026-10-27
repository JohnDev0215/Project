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
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.core.graphics.drawable.b;
import b.a.a.a.b0.g;
import b.a.a.a.y.d;
import com.google.android.material.internal.h;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class a extends g implements b, Drawable.Callback, h.b {
    private PorterDuffColorFilter A0;
    private ColorStateList B;
    private ColorStateList B0;
    private ColorStateList C;
    private PorterDuff.Mode C0;
    private float D;
    private int[] D0;
    private float E;
    private boolean E0;
    private ColorStateList F;
    private ColorStateList F0;
    private float G;
    private WeakReference<InterfaceC0109a> G0;
    private ColorStateList H;
    private TextUtils.TruncateAt H0;
    private CharSequence I;
    private boolean I0;
    private boolean J;
    private int J0;
    private Drawable K;
    private boolean K0;
    private ColorStateList L;
    private float M;
    private boolean N;
    private boolean O;
    private Drawable P;
    private Drawable Q;
    private ColorStateList R;
    private float S;
    private CharSequence T;
    private boolean U;
    private boolean V;
    private Drawable W;
    private ColorStateList X;
    private b.a.a.a.m.h Y;
    private b.a.a.a.m.h Z;
    private float a0;
    private float b0;
    private float c0;
    private float d0;
    private float e0;
    private float f0;
    private float g0;
    private float h0;
    private final Context i0;
    private final Paint j0;
    private final Paint k0;
    private final Paint.FontMetrics l0;
    private final RectF m0;
    private final PointF n0;
    private final Path o0;
    private final h p0;
    private int q0;
    private int r0;
    private int s0;
    private int t0;
    private int u0;
    private int v0;
    private boolean w0;
    private int x0;
    private int y0;
    private ColorFilter z0;
    private static final int[] z = {R.attr.state_enabled};
    private static final ShapeDrawable A = new ShapeDrawable(new OvalShape());

    /* JADX INFO: renamed from: com.google.android.material.chip.a$a, reason: collision with other inner class name */
    public interface InterfaceC0109a {
        void a();
    }

    private a(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.E = -1.0f;
        this.j0 = new Paint(1);
        this.l0 = new Paint.FontMetrics();
        this.m0 = new RectF();
        this.n0 = new PointF();
        this.o0 = new Path();
        this.y0 = 255;
        this.C0 = PorterDuff.Mode.SRC_IN;
        this.G0 = new WeakReference<>(null);
        M(context);
        this.i0 = context;
        h hVar = new h(this);
        this.p0 = hVar;
        this.I = "";
        hVar.e().density = context.getResources().getDisplayMetrics().density;
        this.k0 = null;
        int[] iArr = z;
        setState(iArr);
        h2(iArr);
        this.I0 = true;
        if (b.a.a.a.z.b.f1790a) {
            A.setTint(-1);
        }
    }

    private void A0(Canvas canvas, Rect rect) {
        this.j0.setColor(this.u0);
        this.j0.setStyle(Paint.Style.FILL);
        this.m0.set(rect);
        if (!this.K0) {
            canvas.drawRoundRect(this.m0, G0(), G0(), this.j0);
        } else {
            h(new RectF(rect), this.o0);
            super.p(canvas, this.j0, this.o0, u());
        }
    }

    private void B0(Canvas canvas, Rect rect) {
        Paint paint = this.k0;
        if (paint != null) {
            paint.setColor(a.g.d.a.d(-16777216, 127));
            canvas.drawRect(rect, this.k0);
            if (J2() || I2()) {
                j0(rect, this.m0);
                canvas.drawRect(this.m0, this.k0);
            }
            if (this.I != null) {
                canvas.drawLine(rect.left, rect.exactCenterY(), rect.right, rect.exactCenterY(), this.k0);
            }
            if (K2()) {
                m0(rect, this.m0);
                canvas.drawRect(this.m0, this.k0);
            }
            this.k0.setColor(a.g.d.a.d(-65536, 127));
            l0(rect, this.m0);
            canvas.drawRect(this.m0, this.k0);
            this.k0.setColor(a.g.d.a.d(-16711936, 127));
            n0(rect, this.m0);
            canvas.drawRect(this.m0, this.k0);
        }
    }

    private void C0(Canvas canvas, Rect rect) {
        if (this.I != null) {
            Paint.Align alignR0 = r0(rect, this.n0);
            p0(rect, this.m0);
            if (this.p0.d() != null) {
                this.p0.e().drawableState = getState();
                this.p0.j(this.i0);
            }
            this.p0.e().setTextAlign(alignR0);
            int iSave = 0;
            boolean z2 = Math.round(this.p0.f(d1().toString())) > Math.round(this.m0.width());
            if (z2) {
                iSave = canvas.save();
                canvas.clipRect(this.m0);
            }
            CharSequence charSequenceEllipsize = this.I;
            if (z2 && this.H0 != null) {
                charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, this.p0.e(), this.m0.width(), this.H0);
            }
            CharSequence charSequence = charSequenceEllipsize;
            int length = charSequence.length();
            PointF pointF = this.n0;
            canvas.drawText(charSequence, 0, length, pointF.x, pointF.y, this.p0.e());
            if (z2) {
                canvas.restoreToCount(iSave);
            }
        }
    }

    private boolean I2() {
        return this.V && this.W != null && this.w0;
    }

    private boolean J2() {
        return this.J && this.K != null;
    }

    private boolean K2() {
        return this.O && this.P != null;
    }

    private void L2(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    private void M2() {
        this.F0 = this.E0 ? b.a.a.a.z.b.d(this.H) : null;
    }

    @TargetApi(21)
    private void N2() {
        this.Q = new RippleDrawable(b.a.a.a.z.b.d(b1()), this.P, A);
    }

    private void X1(ColorStateList colorStateList) {
        if (this.B != colorStateList) {
            this.B = colorStateList;
            onStateChange(getState());
        }
    }

    private ColorFilter h1() {
        ColorFilter colorFilter = this.z0;
        return colorFilter != null ? colorFilter : this.A0;
    }

    private void i0(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        drawable.setCallback(this);
        androidx.core.graphics.drawable.a.m(drawable, androidx.core.graphics.drawable.a.f(this));
        drawable.setLevel(getLevel());
        drawable.setVisible(isVisible(), false);
        if (drawable == this.P) {
            if (drawable.isStateful()) {
                drawable.setState(U0());
            }
            androidx.core.graphics.drawable.a.o(drawable, this.R);
            return;
        }
        if (drawable.isStateful()) {
            drawable.setState(getState());
        }
        Drawable drawable2 = this.K;
        if (drawable == drawable2 && this.N) {
            androidx.core.graphics.drawable.a.o(drawable2, this.L);
        }
    }

    private void j0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (J2() || I2()) {
            float f = this.a0 + this.b0;
            if (androidx.core.graphics.drawable.a.f(this) == 0) {
                float f2 = rect.left + f;
                rectF.left = f2;
                rectF.right = f2 + this.M;
            } else {
                float f3 = rect.right - f;
                rectF.right = f3;
                rectF.left = f3 - this.M;
            }
            float fExactCenterY = rect.exactCenterY();
            float f4 = this.M;
            float f5 = fExactCenterY - (f4 / 2.0f);
            rectF.top = f5;
            rectF.bottom = f5 + f4;
        }
    }

    private static boolean j1(int[] iArr, int i) {
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private void l0(Rect rect, RectF rectF) {
        rectF.set(rect);
        if (K2()) {
            float f = this.h0 + this.g0 + this.S + this.f0 + this.e0;
            if (androidx.core.graphics.drawable.a.f(this) == 0) {
                rectF.right = rect.right - f;
            } else {
                rectF.left = rect.left + f;
            }
        }
    }

    private void m0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (K2()) {
            float f = this.h0 + this.g0;
            if (androidx.core.graphics.drawable.a.f(this) == 0) {
                float f2 = rect.right - f;
                rectF.right = f2;
                rectF.left = f2 - this.S;
            } else {
                float f3 = rect.left + f;
                rectF.left = f3;
                rectF.right = f3 + this.S;
            }
            float fExactCenterY = rect.exactCenterY();
            float f4 = this.S;
            float f5 = fExactCenterY - (f4 / 2.0f);
            rectF.top = f5;
            rectF.bottom = f5 + f4;
        }
    }

    private void n0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (K2()) {
            float f = this.h0 + this.g0 + this.S + this.f0 + this.e0;
            if (androidx.core.graphics.drawable.a.f(this) == 0) {
                float f2 = rect.right;
                rectF.right = f2;
                rectF.left = f2 - f;
            } else {
                int i = rect.left;
                rectF.left = i;
                rectF.right = i + f;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    private static boolean n1(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    private static boolean o1(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    private void p0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (this.I != null) {
            float fK0 = this.a0 + k0() + this.d0;
            float fO0 = this.h0 + o0() + this.e0;
            if (androidx.core.graphics.drawable.a.f(this) == 0) {
                rectF.left = rect.left + fK0;
                rectF.right = rect.right - fO0;
            } else {
                rectF.left = rect.left + fO0;
                rectF.right = rect.right - fK0;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    private static boolean p1(d dVar) {
        ColorStateList colorStateList;
        return (dVar == null || (colorStateList = dVar.f1778b) == null || !colorStateList.isStateful()) ? false : true;
    }

    private float q0() {
        this.p0.e().getFontMetrics(this.l0);
        Paint.FontMetrics fontMetrics = this.l0;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0161  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void q1(android.util.AttributeSet r8, int r9, int r10) {
        /*
            Method dump skipped, instruction units count: 472
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.chip.a.q1(android.util.AttributeSet, int, int):void");
    }

    private boolean s0() {
        return this.V && this.W != null && this.U;
    }

    private boolean s1(int[] iArr, int[] iArr2) {
        boolean z2;
        boolean zOnStateChange = super.onStateChange(iArr);
        ColorStateList colorStateList = this.B;
        int colorForState = colorStateList != null ? colorStateList.getColorForState(iArr, this.q0) : 0;
        boolean state = true;
        if (this.q0 != colorForState) {
            this.q0 = colorForState;
            zOnStateChange = true;
        }
        ColorStateList colorStateList2 = this.C;
        int colorForState2 = colorStateList2 != null ? colorStateList2.getColorForState(iArr, this.r0) : 0;
        if (this.r0 != colorForState2) {
            this.r0 = colorForState2;
            zOnStateChange = true;
        }
        int iE = b.a.a.a.s.a.e(colorForState, colorForState2);
        if ((this.s0 != iE) | (x() == null)) {
            this.s0 = iE;
            W(ColorStateList.valueOf(iE));
            zOnStateChange = true;
        }
        ColorStateList colorStateList3 = this.F;
        int colorForState3 = colorStateList3 != null ? colorStateList3.getColorForState(iArr, this.t0) : 0;
        if (this.t0 != colorForState3) {
            this.t0 = colorForState3;
            zOnStateChange = true;
        }
        int colorForState4 = (this.F0 == null || !b.a.a.a.z.b.e(iArr)) ? 0 : this.F0.getColorForState(iArr, this.u0);
        if (this.u0 != colorForState4) {
            this.u0 = colorForState4;
            if (this.E0) {
                zOnStateChange = true;
            }
        }
        int colorForState5 = (this.p0.d() == null || this.p0.d().f1778b == null) ? 0 : this.p0.d().f1778b.getColorForState(iArr, this.v0);
        if (this.v0 != colorForState5) {
            this.v0 = colorForState5;
            zOnStateChange = true;
        }
        boolean z3 = j1(getState(), R.attr.state_checked) && this.U;
        if (this.w0 == z3 || this.W == null) {
            z2 = false;
        } else {
            float fK0 = k0();
            this.w0 = z3;
            if (fK0 != k0()) {
                zOnStateChange = true;
                z2 = true;
            } else {
                z2 = false;
                zOnStateChange = true;
            }
        }
        ColorStateList colorStateList4 = this.B0;
        int colorForState6 = colorStateList4 != null ? colorStateList4.getColorForState(iArr, this.x0) : 0;
        if (this.x0 != colorForState6) {
            this.x0 = colorForState6;
            this.A0 = b.a.a.a.t.a.a(this, this.B0, this.C0);
        } else {
            state = zOnStateChange;
        }
        if (o1(this.K)) {
            state |= this.K.setState(iArr);
        }
        if (o1(this.W)) {
            state |= this.W.setState(iArr);
        }
        if (o1(this.P)) {
            int[] iArr3 = new int[iArr.length + iArr2.length];
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
            state |= this.P.setState(iArr3);
        }
        if (b.a.a.a.z.b.f1790a && o1(this.Q)) {
            state |= this.Q.setState(iArr2);
        }
        if (state) {
            invalidateSelf();
        }
        if (z2) {
            r1();
        }
        return state;
    }

    public static a t0(Context context, AttributeSet attributeSet, int i, int i2) {
        a aVar = new a(context, attributeSet, i, i2);
        aVar.q1(attributeSet, i, i2);
        return aVar;
    }

    private void u0(Canvas canvas, Rect rect) {
        if (I2()) {
            j0(rect, this.m0);
            RectF rectF = this.m0;
            float f = rectF.left;
            float f2 = rectF.top;
            canvas.translate(f, f2);
            this.W.setBounds(0, 0, (int) this.m0.width(), (int) this.m0.height());
            this.W.draw(canvas);
            canvas.translate(-f, -f2);
        }
    }

    private void v0(Canvas canvas, Rect rect) {
        if (this.K0) {
            return;
        }
        this.j0.setColor(this.r0);
        this.j0.setStyle(Paint.Style.FILL);
        this.j0.setColorFilter(h1());
        this.m0.set(rect);
        canvas.drawRoundRect(this.m0, G0(), G0(), this.j0);
    }

    private void w0(Canvas canvas, Rect rect) {
        if (J2()) {
            j0(rect, this.m0);
            RectF rectF = this.m0;
            float f = rectF.left;
            float f2 = rectF.top;
            canvas.translate(f, f2);
            this.K.setBounds(0, 0, (int) this.m0.width(), (int) this.m0.height());
            this.K.draw(canvas);
            canvas.translate(-f, -f2);
        }
    }

    private void x0(Canvas canvas, Rect rect) {
        if (this.G <= 0.0f || this.K0) {
            return;
        }
        this.j0.setColor(this.t0);
        this.j0.setStyle(Paint.Style.STROKE);
        if (!this.K0) {
            this.j0.setColorFilter(h1());
        }
        RectF rectF = this.m0;
        float f = rect.left;
        float f2 = this.G;
        rectF.set(f + (f2 / 2.0f), rect.top + (f2 / 2.0f), rect.right - (f2 / 2.0f), rect.bottom - (f2 / 2.0f));
        float f3 = this.E - (this.G / 2.0f);
        canvas.drawRoundRect(this.m0, f3, f3, this.j0);
    }

    private void y0(Canvas canvas, Rect rect) {
        if (this.K0) {
            return;
        }
        this.j0.setColor(this.q0);
        this.j0.setStyle(Paint.Style.FILL);
        this.m0.set(rect);
        canvas.drawRoundRect(this.m0, G0(), G0(), this.j0);
    }

    private void z0(Canvas canvas, Rect rect) {
        Drawable drawable;
        if (K2()) {
            m0(rect, this.m0);
            RectF rectF = this.m0;
            float f = rectF.left;
            float f2 = rectF.top;
            canvas.translate(f, f2);
            this.P.setBounds(0, 0, (int) this.m0.width(), (int) this.m0.height());
            if (b.a.a.a.z.b.f1790a) {
                this.Q.setBounds(this.P.getBounds());
                this.Q.jumpToCurrentState();
                drawable = this.Q;
            } else {
                drawable = this.P;
            }
            drawable.draw(canvas);
            canvas.translate(-f, -f2);
        }
    }

    public void A1(boolean z2) {
        if (this.V != z2) {
            boolean zI2 = I2();
            this.V = z2;
            boolean zI22 = I2();
            if (zI2 != zI22) {
                if (zI22) {
                    i0(this.W);
                } else {
                    L2(this.W);
                }
                invalidateSelf();
                r1();
            }
        }
    }

    public void A2(d dVar) {
        this.p0.h(dVar, this.i0);
    }

    public void B1(ColorStateList colorStateList) {
        if (this.C != colorStateList) {
            this.C = colorStateList;
            onStateChange(getState());
        }
    }

    public void B2(int i) {
        A2(new d(this.i0, i));
    }

    public void C1(int i) {
        B1(a.a.k.a.a.c(this.i0, i));
    }

    public void C2(float f) {
        if (this.e0 != f) {
            this.e0 = f;
            invalidateSelf();
            r1();
        }
    }

    public Drawable D0() {
        return this.W;
    }

    @Deprecated
    public void D1(float f) {
        if (this.E != f) {
            this.E = f;
            setShapeAppearanceModel(C().w(f));
        }
    }

    public void D2(int i) {
        C2(this.i0.getResources().getDimension(i));
    }

    public ColorStateList E0() {
        return this.X;
    }

    @Deprecated
    public void E1(int i) {
        D1(this.i0.getResources().getDimension(i));
    }

    public void E2(float f) {
        if (this.d0 != f) {
            this.d0 = f;
            invalidateSelf();
            r1();
        }
    }

    public ColorStateList F0() {
        return this.C;
    }

    public void F1(float f) {
        if (this.h0 != f) {
            this.h0 = f;
            invalidateSelf();
            r1();
        }
    }

    public void F2(int i) {
        E2(this.i0.getResources().getDimension(i));
    }

    public float G0() {
        return this.K0 ? F() : this.E;
    }

    public void G1(int i) {
        F1(this.i0.getResources().getDimension(i));
    }

    public void G2(boolean z2) {
        if (this.E0 != z2) {
            this.E0 = z2;
            M2();
            onStateChange(getState());
        }
    }

    public float H0() {
        return this.h0;
    }

    public void H1(Drawable drawable) {
        Drawable drawableI0 = I0();
        if (drawableI0 != drawable) {
            float fK0 = k0();
            this.K = drawable != null ? androidx.core.graphics.drawable.a.r(drawable).mutate() : null;
            float fK02 = k0();
            L2(drawableI0);
            if (J2()) {
                i0(this.K);
            }
            invalidateSelf();
            if (fK0 != fK02) {
                r1();
            }
        }
    }

    boolean H2() {
        return this.I0;
    }

    public Drawable I0() {
        Drawable drawable = this.K;
        if (drawable != null) {
            return androidx.core.graphics.drawable.a.q(drawable);
        }
        return null;
    }

    public void I1(int i) {
        H1(a.a.k.a.a.d(this.i0, i));
    }

    public float J0() {
        return this.M;
    }

    public void J1(float f) {
        if (this.M != f) {
            float fK0 = k0();
            this.M = f;
            float fK02 = k0();
            invalidateSelf();
            if (fK0 != fK02) {
                r1();
            }
        }
    }

    public ColorStateList K0() {
        return this.L;
    }

    public void K1(int i) {
        J1(this.i0.getResources().getDimension(i));
    }

    public float L0() {
        return this.D;
    }

    public void L1(ColorStateList colorStateList) {
        this.N = true;
        if (this.L != colorStateList) {
            this.L = colorStateList;
            if (J2()) {
                androidx.core.graphics.drawable.a.o(this.K, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public float M0() {
        return this.a0;
    }

    public void M1(int i) {
        L1(a.a.k.a.a.c(this.i0, i));
    }

    public ColorStateList N0() {
        return this.F;
    }

    public void N1(int i) {
        O1(this.i0.getResources().getBoolean(i));
    }

    public float O0() {
        return this.G;
    }

    public void O1(boolean z2) {
        if (this.J != z2) {
            boolean zJ2 = J2();
            this.J = z2;
            boolean zJ22 = J2();
            if (zJ2 != zJ22) {
                if (zJ22) {
                    i0(this.K);
                } else {
                    L2(this.K);
                }
                invalidateSelf();
                r1();
            }
        }
    }

    public Drawable P0() {
        Drawable drawable = this.P;
        if (drawable != null) {
            return androidx.core.graphics.drawable.a.q(drawable);
        }
        return null;
    }

    public void P1(float f) {
        if (this.D != f) {
            this.D = f;
            invalidateSelf();
            r1();
        }
    }

    public CharSequence Q0() {
        return this.T;
    }

    public void Q1(int i) {
        P1(this.i0.getResources().getDimension(i));
    }

    public float R0() {
        return this.g0;
    }

    public void R1(float f) {
        if (this.a0 != f) {
            this.a0 = f;
            invalidateSelf();
            r1();
        }
    }

    public float S0() {
        return this.S;
    }

    public void S1(int i) {
        R1(this.i0.getResources().getDimension(i));
    }

    public float T0() {
        return this.f0;
    }

    public void T1(ColorStateList colorStateList) {
        if (this.F != colorStateList) {
            this.F = colorStateList;
            if (this.K0) {
                d0(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public int[] U0() {
        return this.D0;
    }

    public void U1(int i) {
        T1(a.a.k.a.a.c(this.i0, i));
    }

    public ColorStateList V0() {
        return this.R;
    }

    public void V1(float f) {
        if (this.G != f) {
            this.G = f;
            this.j0.setStrokeWidth(f);
            if (this.K0) {
                super.e0(f);
            }
            invalidateSelf();
        }
    }

    public void W0(RectF rectF) {
        n0(getBounds(), rectF);
    }

    public void W1(int i) {
        V1(this.i0.getResources().getDimension(i));
    }

    public TextUtils.TruncateAt X0() {
        return this.H0;
    }

    public b.a.a.a.m.h Y0() {
        return this.Z;
    }

    public void Y1(Drawable drawable) {
        Drawable drawableP0 = P0();
        if (drawableP0 != drawable) {
            float fO0 = o0();
            this.P = drawable != null ? androidx.core.graphics.drawable.a.r(drawable).mutate() : null;
            if (b.a.a.a.z.b.f1790a) {
                N2();
            }
            float fO02 = o0();
            L2(drawableP0);
            if (K2()) {
                i0(this.P);
            }
            invalidateSelf();
            if (fO0 != fO02) {
                r1();
            }
        }
    }

    public float Z0() {
        return this.c0;
    }

    public void Z1(CharSequence charSequence) {
        if (this.T != charSequence) {
            this.T = a.g.i.a.c().h(charSequence);
            invalidateSelf();
        }
    }

    @Override // com.google.android.material.internal.h.b
    public void a() {
        r1();
        invalidateSelf();
    }

    public float a1() {
        return this.b0;
    }

    public void a2(float f) {
        if (this.g0 != f) {
            this.g0 = f;
            invalidateSelf();
            if (K2()) {
                r1();
            }
        }
    }

    public ColorStateList b1() {
        return this.H;
    }

    public void b2(int i) {
        a2(this.i0.getResources().getDimension(i));
    }

    public b.a.a.a.m.h c1() {
        return this.Y;
    }

    public void c2(int i) {
        Y1(a.a.k.a.a.d(this.i0, i));
    }

    public CharSequence d1() {
        return this.I;
    }

    public void d2(float f) {
        if (this.S != f) {
            this.S = f;
            invalidateSelf();
            if (K2()) {
                r1();
            }
        }
    }

    @Override // b.a.a.a.b0.g, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds.isEmpty() || getAlpha() == 0) {
            return;
        }
        int i = this.y0;
        int iA = i < 255 ? b.a.a.a.o.a.a(canvas, bounds.left, bounds.top, bounds.right, bounds.bottom, i) : 0;
        y0(canvas, bounds);
        v0(canvas, bounds);
        if (this.K0) {
            super.draw(canvas);
        }
        x0(canvas, bounds);
        A0(canvas, bounds);
        w0(canvas, bounds);
        u0(canvas, bounds);
        if (this.I0) {
            C0(canvas, bounds);
        }
        z0(canvas, bounds);
        B0(canvas, bounds);
        if (this.y0 < 255) {
            canvas.restoreToCount(iA);
        }
    }

    public d e1() {
        return this.p0.d();
    }

    public void e2(int i) {
        d2(this.i0.getResources().getDimension(i));
    }

    public float f1() {
        return this.e0;
    }

    public void f2(float f) {
        if (this.f0 != f) {
            this.f0 = f;
            invalidateSelf();
            if (K2()) {
                r1();
            }
        }
    }

    public float g1() {
        return this.d0;
    }

    public void g2(int i) {
        f2(this.i0.getResources().getDimension(i));
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.y0;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.z0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.D;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return Math.min(Math.round(this.a0 + k0() + this.d0 + this.p0.f(d1().toString()) + this.e0 + o0() + this.h0), this.J0);
    }

    @Override // b.a.a.a.b0.g, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // b.a.a.a.b0.g, android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.K0) {
            super.getOutline(outline);
            return;
        }
        Rect bounds = getBounds();
        if (bounds.isEmpty()) {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), getIntrinsicHeight(), this.E);
        } else {
            outline.setRoundRect(bounds, this.E);
        }
        outline.setAlpha(getAlpha() / 255.0f);
    }

    public boolean h2(int[] iArr) {
        if (Arrays.equals(this.D0, iArr)) {
            return false;
        }
        this.D0 = iArr;
        if (K2()) {
            return s1(getState(), iArr);
        }
        return false;
    }

    public boolean i1() {
        return this.E0;
    }

    public void i2(ColorStateList colorStateList) {
        if (this.R != colorStateList) {
            this.R = colorStateList;
            if (K2()) {
                androidx.core.graphics.drawable.a.o(this.P, colorStateList);
            }
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // b.a.a.a.b0.g, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return n1(this.B) || n1(this.C) || n1(this.F) || (this.E0 && n1(this.F0)) || p1(this.p0.d()) || s0() || o1(this.K) || o1(this.W) || n1(this.B0);
    }

    public void j2(int i) {
        i2(a.a.k.a.a.c(this.i0, i));
    }

    float k0() {
        if (J2() || I2()) {
            return this.b0 + this.M + this.c0;
        }
        return 0.0f;
    }

    public boolean k1() {
        return this.U;
    }

    public void k2(boolean z2) {
        if (this.O != z2) {
            boolean zK2 = K2();
            this.O = z2;
            boolean zK22 = K2();
            if (zK2 != zK22) {
                if (zK22) {
                    i0(this.P);
                } else {
                    L2(this.P);
                }
                invalidateSelf();
                r1();
            }
        }
    }

    public boolean l1() {
        return o1(this.P);
    }

    public void l2(InterfaceC0109a interfaceC0109a) {
        this.G0 = new WeakReference<>(interfaceC0109a);
    }

    public boolean m1() {
        return this.O;
    }

    public void m2(TextUtils.TruncateAt truncateAt) {
        this.H0 = truncateAt;
    }

    public void n2(b.a.a.a.m.h hVar) {
        this.Z = hVar;
    }

    float o0() {
        if (K2()) {
            return this.f0 + this.S + this.g0;
        }
        return 0.0f;
    }

    public void o2(int i) {
        n2(b.a.a.a.m.h.c(this.i0, i));
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i) {
        boolean zOnLayoutDirectionChanged = super.onLayoutDirectionChanged(i);
        if (J2()) {
            zOnLayoutDirectionChanged |= androidx.core.graphics.drawable.a.m(this.K, i);
        }
        if (I2()) {
            zOnLayoutDirectionChanged |= androidx.core.graphics.drawable.a.m(this.W, i);
        }
        if (K2()) {
            zOnLayoutDirectionChanged |= androidx.core.graphics.drawable.a.m(this.P, i);
        }
        if (!zOnLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        boolean zOnLevelChange = super.onLevelChange(i);
        if (J2()) {
            zOnLevelChange |= this.K.setLevel(i);
        }
        if (I2()) {
            zOnLevelChange |= this.W.setLevel(i);
        }
        if (K2()) {
            zOnLevelChange |= this.P.setLevel(i);
        }
        if (zOnLevelChange) {
            invalidateSelf();
        }
        return zOnLevelChange;
    }

    @Override // b.a.a.a.b0.g, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        if (this.K0) {
            super.onStateChange(iArr);
        }
        return s1(iArr, U0());
    }

    public void p2(float f) {
        if (this.c0 != f) {
            float fK0 = k0();
            this.c0 = f;
            float fK02 = k0();
            invalidateSelf();
            if (fK0 != fK02) {
                r1();
            }
        }
    }

    public void q2(int i) {
        p2(this.i0.getResources().getDimension(i));
    }

    Paint.Align r0(Rect rect, PointF pointF) {
        pointF.set(0.0f, 0.0f);
        Paint.Align align = Paint.Align.LEFT;
        if (this.I != null) {
            float fK0 = this.a0 + k0() + this.d0;
            if (androidx.core.graphics.drawable.a.f(this) == 0) {
                pointF.x = rect.left + fK0;
                align = Paint.Align.LEFT;
            } else {
                pointF.x = rect.right - fK0;
                align = Paint.Align.RIGHT;
            }
            pointF.y = rect.centerY() - q0();
        }
        return align;
    }

    protected void r1() {
        InterfaceC0109a interfaceC0109a = this.G0.get();
        if (interfaceC0109a != null) {
            interfaceC0109a.a();
        }
    }

    public void r2(float f) {
        if (this.b0 != f) {
            float fK0 = k0();
            this.b0 = f;
            float fK02 = k0();
            invalidateSelf();
            if (fK0 != fK02) {
                r1();
            }
        }
    }

    public void s2(int i) {
        r2(this.i0.getResources().getDimension(i));
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // b.a.a.a.b0.g, android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (this.y0 != i) {
            this.y0 = i;
            invalidateSelf();
        }
    }

    @Override // b.a.a.a.b0.g, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.z0 != colorFilter) {
            this.z0 = colorFilter;
            invalidateSelf();
        }
    }

    @Override // b.a.a.a.b0.g, android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        if (this.B0 != colorStateList) {
            this.B0 = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // b.a.a.a.b0.g, android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.C0 != mode) {
            this.C0 = mode;
            this.A0 = b.a.a.a.t.a.a(this, this.B0, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z2, boolean z3) {
        boolean visible = super.setVisible(z2, z3);
        if (J2()) {
            visible |= this.K.setVisible(z2, z3);
        }
        if (I2()) {
            visible |= this.W.setVisible(z2, z3);
        }
        if (K2()) {
            visible |= this.P.setVisible(z2, z3);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    public void t1(boolean z2) {
        if (this.U != z2) {
            this.U = z2;
            float fK0 = k0();
            if (!z2 && this.w0) {
                this.w0 = false;
            }
            float fK02 = k0();
            invalidateSelf();
            if (fK0 != fK02) {
                r1();
            }
        }
    }

    public void t2(int i) {
        this.J0 = i;
    }

    public void u1(int i) {
        t1(this.i0.getResources().getBoolean(i));
    }

    public void u2(ColorStateList colorStateList) {
        if (this.H != colorStateList) {
            this.H = colorStateList;
            M2();
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public void v1(Drawable drawable) {
        if (this.W != drawable) {
            float fK0 = k0();
            this.W = drawable;
            float fK02 = k0();
            L2(this.W);
            i0(this.W);
            invalidateSelf();
            if (fK0 != fK02) {
                r1();
            }
        }
    }

    public void v2(int i) {
        u2(a.a.k.a.a.c(this.i0, i));
    }

    public void w1(int i) {
        v1(a.a.k.a.a.d(this.i0, i));
    }

    void w2(boolean z2) {
        this.I0 = z2;
    }

    public void x1(ColorStateList colorStateList) {
        if (this.X != colorStateList) {
            this.X = colorStateList;
            if (s0()) {
                androidx.core.graphics.drawable.a.o(this.W, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void x2(b.a.a.a.m.h hVar) {
        this.Y = hVar;
    }

    public void y1(int i) {
        x1(a.a.k.a.a.c(this.i0, i));
    }

    public void y2(int i) {
        x2(b.a.a.a.m.h.c(this.i0, i));
    }

    public void z1(int i) {
        A1(this.i0.getResources().getBoolean(i));
    }

    public void z2(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        if (TextUtils.equals(this.I, charSequence)) {
            return;
        }
        this.I = charSequence;
        this.p0.i(true);
        invalidateSelf();
        r1();
    }
}
