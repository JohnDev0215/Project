package com.google.android.material.chip;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
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
import com.google.android.material.internal.i;
import com.google.android.material.internal.k;
import d.b.a.a.l;
import d.b.a.a.t.c;
import d.b.a.a.t.d;
import d.b.a.a.w.h;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: ChipDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends h implements b, Drawable.Callback, i.b {
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
    private WeakReference<InterfaceC0064a> G0;
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
    private d.b.a.a.m.h Y;
    private d.b.a.a.m.h Z;
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
    private final i p0;
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
    /* JADX INFO: compiled from: ChipDrawable.java */
    public interface InterfaceC0064a {
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
        N(context);
        this.i0 = context;
        i iVar = new i(this);
        this.p0 = iVar;
        this.I = HttpUrl.FRAGMENT_ENCODE_SET;
        iVar.e().density = context.getResources().getDisplayMetrics().density;
        this.k0 = null;
        int[] iArr = z;
        setState(iArr);
        m2(iArr);
        this.I0 = true;
        if (d.b.a.a.u.b.f4810a) {
            A.setTint(-1);
        }
    }

    private void A0(Canvas canvas, Rect rect) {
        if (this.K0) {
            return;
        }
        this.j0.setColor(this.r0);
        this.j0.setStyle(Paint.Style.FILL);
        this.j0.setColorFilter(m1());
        this.m0.set(rect);
        canvas.drawRoundRect(this.m0, L0(), L0(), this.j0);
    }

    private void B0(Canvas canvas, Rect rect) {
        if (O2()) {
            o0(rect, this.m0);
            RectF rectF = this.m0;
            float f2 = rectF.left;
            float f3 = rectF.top;
            canvas.translate(f2, f3);
            this.K.setBounds(0, 0, (int) this.m0.width(), (int) this.m0.height());
            this.K.draw(canvas);
            canvas.translate(-f2, -f3);
        }
    }

    private void C0(Canvas canvas, Rect rect) {
        if (this.G <= 0.0f || this.K0) {
            return;
        }
        this.j0.setColor(this.t0);
        this.j0.setStyle(Paint.Style.STROKE);
        if (!this.K0) {
            this.j0.setColorFilter(m1());
        }
        RectF rectF = this.m0;
        float f2 = rect.left;
        float f3 = this.G;
        rectF.set(f2 + (f3 / 2.0f), rect.top + (f3 / 2.0f), rect.right - (f3 / 2.0f), rect.bottom - (f3 / 2.0f));
        float f4 = this.E - (this.G / 2.0f);
        canvas.drawRoundRect(this.m0, f4, f4, this.j0);
    }

    private void D0(Canvas canvas, Rect rect) {
        if (this.K0) {
            return;
        }
        this.j0.setColor(this.q0);
        this.j0.setStyle(Paint.Style.FILL);
        this.m0.set(rect);
        canvas.drawRoundRect(this.m0, L0(), L0(), this.j0);
    }

    private void E0(Canvas canvas, Rect rect) {
        if (P2()) {
            r0(rect, this.m0);
            RectF rectF = this.m0;
            float f2 = rectF.left;
            float f3 = rectF.top;
            canvas.translate(f2, f3);
            this.P.setBounds(0, 0, (int) this.m0.width(), (int) this.m0.height());
            if (d.b.a.a.u.b.f4810a) {
                this.Q.setBounds(this.P.getBounds());
                this.Q.jumpToCurrentState();
                this.Q.draw(canvas);
            } else {
                this.P.draw(canvas);
            }
            canvas.translate(-f2, -f3);
        }
    }

    private void F0(Canvas canvas, Rect rect) {
        this.j0.setColor(this.u0);
        this.j0.setStyle(Paint.Style.FILL);
        this.m0.set(rect);
        if (!this.K0) {
            canvas.drawRoundRect(this.m0, L0(), L0(), this.j0);
        } else {
            h(new RectF(rect), this.o0);
            super.p(canvas, this.j0, this.o0, u());
        }
    }

    private void G0(Canvas canvas, Rect rect) {
        Paint paint = this.k0;
        if (paint != null) {
            paint.setColor(b.g.d.a.d(-16777216, 127));
            canvas.drawRect(rect, this.k0);
            if (O2() || N2()) {
                o0(rect, this.m0);
                canvas.drawRect(this.m0, this.k0);
            }
            if (this.I != null) {
                canvas.drawLine(rect.left, rect.exactCenterY(), rect.right, rect.exactCenterY(), this.k0);
            }
            if (P2()) {
                r0(rect, this.m0);
                canvas.drawRect(this.m0, this.k0);
            }
            this.k0.setColor(b.g.d.a.d(-65536, 127));
            q0(rect, this.m0);
            canvas.drawRect(this.m0, this.k0);
            this.k0.setColor(b.g.d.a.d(-16711936, 127));
            s0(rect, this.m0);
            canvas.drawRect(this.m0, this.k0);
        }
    }

    private void H0(Canvas canvas, Rect rect) {
        if (this.I != null) {
            Paint.Align alignW0 = w0(rect, this.n0);
            u0(rect, this.m0);
            if (this.p0.d() != null) {
                this.p0.e().drawableState = getState();
                this.p0.j(this.i0);
            }
            this.p0.e().setTextAlign(alignW0);
            int iSave = 0;
            boolean z2 = Math.round(this.p0.f(i1().toString())) > Math.round(this.m0.width());
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

    private boolean N2() {
        return this.V && this.W != null && this.w0;
    }

    private boolean O2() {
        return this.J && this.K != null;
    }

    private boolean P2() {
        return this.O && this.P != null;
    }

    private void Q2(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    private void R2() {
        this.F0 = this.E0 ? d.b.a.a.u.b.d(this.H) : null;
    }

    @TargetApi(21)
    private void S2() {
        this.Q = new RippleDrawable(d.b.a.a.u.b.d(g1()), this.P, A);
    }

    private void c2(ColorStateList colorStateList) {
        if (this.B != colorStateList) {
            this.B = colorStateList;
            onStateChange(getState());
        }
    }

    private ColorFilter m1() {
        ColorFilter colorFilter = this.z0;
        return colorFilter != null ? colorFilter : this.A0;
    }

    private void n0(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        drawable.setCallback(this);
        androidx.core.graphics.drawable.a.m(drawable, androidx.core.graphics.drawable.a.f(this));
        drawable.setLevel(getLevel());
        drawable.setVisible(isVisible(), false);
        if (drawable == this.P) {
            if (drawable.isStateful()) {
                drawable.setState(Z0());
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

    private void o0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (O2() || N2()) {
            float f2 = this.a0 + this.b0;
            if (androidx.core.graphics.drawable.a.f(this) == 0) {
                float f3 = rect.left + f2;
                rectF.left = f3;
                rectF.right = f3 + this.M;
            } else {
                float f4 = rect.right - f2;
                rectF.right = f4;
                rectF.left = f4 - this.M;
            }
            float fExactCenterY = rect.exactCenterY();
            float f5 = this.M;
            float f6 = fExactCenterY - (f5 / 2.0f);
            rectF.top = f6;
            rectF.bottom = f6 + f5;
        }
    }

    private static boolean o1(int[] iArr, int i) {
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

    private void q0(Rect rect, RectF rectF) {
        rectF.set(rect);
        if (P2()) {
            float f2 = this.h0 + this.g0 + this.S + this.f0 + this.e0;
            if (androidx.core.graphics.drawable.a.f(this) == 0) {
                rectF.right = rect.right - f2;
            } else {
                rectF.left = rect.left + f2;
            }
        }
    }

    private void r0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (P2()) {
            float f2 = this.h0 + this.g0;
            if (androidx.core.graphics.drawable.a.f(this) == 0) {
                float f3 = rect.right - f2;
                rectF.right = f3;
                rectF.left = f3 - this.S;
            } else {
                float f4 = rect.left + f2;
                rectF.left = f4;
                rectF.right = f4 + this.S;
            }
            float fExactCenterY = rect.exactCenterY();
            float f5 = this.S;
            float f6 = fExactCenterY - (f5 / 2.0f);
            rectF.top = f6;
            rectF.bottom = f6 + f5;
        }
    }

    private void s0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (P2()) {
            float f2 = this.h0 + this.g0 + this.S + this.f0 + this.e0;
            if (androidx.core.graphics.drawable.a.f(this) == 0) {
                float f3 = rect.right;
                rectF.right = f3;
                rectF.left = f3 - f2;
            } else {
                int i = rect.left;
                rectF.left = i;
                rectF.right = i + f2;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    private static boolean s1(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    private static boolean t1(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    private void u0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (this.I != null) {
            float fP0 = this.a0 + p0() + this.d0;
            float fT0 = this.h0 + t0() + this.e0;
            if (androidx.core.graphics.drawable.a.f(this) == 0) {
                rectF.left = rect.left + fP0;
                rectF.right = rect.right - fT0;
            } else {
                rectF.left = rect.left + fT0;
                rectF.right = rect.right - fP0;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    private static boolean u1(d dVar) {
        ColorStateList colorStateList;
        return (dVar == null || (colorStateList = dVar.f4794b) == null || !colorStateList.isStateful()) ? false : true;
    }

    private float v0() {
        this.p0.e().getFontMetrics(this.l0);
        Paint.FontMetrics fontMetrics = this.l0;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    private void v1(AttributeSet attributeSet, int i, int i2) {
        TypedArray typedArrayH = k.h(this.i0, attributeSet, l.z0, i, i2, new int[0]);
        this.K0 = typedArrayH.hasValue(l.k1);
        c2(c.a(this.i0, typedArrayH, l.X0));
        G1(c.a(this.i0, typedArrayH, l.K0));
        U1(typedArrayH.getDimension(l.S0, 0.0f));
        int i3 = l.L0;
        if (typedArrayH.hasValue(i3)) {
            I1(typedArrayH.getDimension(i3, 0.0f));
        }
        Y1(c.a(this.i0, typedArrayH, l.V0));
        a2(typedArrayH.getDimension(l.W0, 0.0f));
        z2(c.a(this.i0, typedArrayH, l.j1));
        E2(typedArrayH.getText(l.E0));
        F2(c.f(this.i0, typedArrayH, l.A0));
        int i4 = typedArrayH.getInt(l.C0, 0);
        if (i4 == 1) {
            r2(TextUtils.TruncateAt.START);
        } else if (i4 == 2) {
            r2(TextUtils.TruncateAt.MIDDLE);
        } else if (i4 == 3) {
            r2(TextUtils.TruncateAt.END);
        }
        T1(typedArrayH.getBoolean(l.R0, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") == null) {
            T1(typedArrayH.getBoolean(l.O0, false));
        }
        M1(c.d(this.i0, typedArrayH, l.N0));
        int i5 = l.Q0;
        if (typedArrayH.hasValue(i5)) {
            Q1(c.a(this.i0, typedArrayH, i5));
        }
        O1(typedArrayH.getDimension(l.P0, 0.0f));
        p2(typedArrayH.getBoolean(l.e1, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") == null) {
            p2(typedArrayH.getBoolean(l.Z0, false));
        }
        d2(c.d(this.i0, typedArrayH, l.Y0));
        n2(c.a(this.i0, typedArrayH, l.d1));
        i2(typedArrayH.getDimension(l.b1, 0.0f));
        y1(typedArrayH.getBoolean(l.F0, false));
        F1(typedArrayH.getBoolean(l.J0, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") == null) {
            F1(typedArrayH.getBoolean(l.H0, false));
        }
        A1(c.d(this.i0, typedArrayH, l.G0));
        int i6 = l.I0;
        if (typedArrayH.hasValue(i6)) {
            C1(c.a(this.i0, typedArrayH, i6));
        }
        C2(d.b.a.a.m.h.c(this.i0, typedArrayH, l.l1));
        s2(d.b.a.a.m.h.c(this.i0, typedArrayH, l.g1));
        W1(typedArrayH.getDimension(l.U0, 0.0f));
        w2(typedArrayH.getDimension(l.i1, 0.0f));
        u2(typedArrayH.getDimension(l.h1, 0.0f));
        J2(typedArrayH.getDimension(l.n1, 0.0f));
        H2(typedArrayH.getDimension(l.m1, 0.0f));
        k2(typedArrayH.getDimension(l.c1, 0.0f));
        f2(typedArrayH.getDimension(l.a1, 0.0f));
        K1(typedArrayH.getDimension(l.M0, 0.0f));
        y2(typedArrayH.getDimensionPixelSize(l.D0, Integer.MAX_VALUE));
        typedArrayH.recycle();
    }

    private boolean x0() {
        return this.V && this.W != null && this.U;
    }

    private boolean x1(int[] iArr, int[] iArr2) {
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
        int iE = d.b.a.a.o.a.e(colorForState, colorForState2);
        if ((this.s0 != iE) | (x() == null)) {
            this.s0 = iE;
            X(ColorStateList.valueOf(iE));
            zOnStateChange = true;
        }
        ColorStateList colorStateList3 = this.F;
        int colorForState3 = colorStateList3 != null ? colorStateList3.getColorForState(iArr, this.t0) : 0;
        if (this.t0 != colorForState3) {
            this.t0 = colorForState3;
            zOnStateChange = true;
        }
        int colorForState4 = (this.F0 == null || !d.b.a.a.u.b.e(iArr)) ? 0 : this.F0.getColorForState(iArr, this.u0);
        if (this.u0 != colorForState4) {
            this.u0 = colorForState4;
            if (this.E0) {
                zOnStateChange = true;
            }
        }
        int colorForState5 = (this.p0.d() == null || this.p0.d().f4794b == null) ? 0 : this.p0.d().f4794b.getColorForState(iArr, this.v0);
        if (this.v0 != colorForState5) {
            this.v0 = colorForState5;
            zOnStateChange = true;
        }
        boolean z3 = o1(getState(), R.attr.state_checked) && this.U;
        if (this.w0 == z3 || this.W == null) {
            z2 = false;
        } else {
            float fP0 = p0();
            this.w0 = z3;
            if (fP0 != p0()) {
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
            this.A0 = d.b.a.a.p.a.b(this, this.B0, this.C0);
        } else {
            state = zOnStateChange;
        }
        if (t1(this.K)) {
            state |= this.K.setState(iArr);
        }
        if (t1(this.W)) {
            state |= this.W.setState(iArr);
        }
        if (t1(this.P)) {
            int[] iArr3 = new int[iArr.length + iArr2.length];
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
            state |= this.P.setState(iArr3);
        }
        if (d.b.a.a.u.b.f4810a && t1(this.Q)) {
            state |= this.Q.setState(iArr2);
        }
        if (state) {
            invalidateSelf();
        }
        if (z2) {
            w1();
        }
        return state;
    }

    public static a y0(Context context, AttributeSet attributeSet, int i, int i2) {
        a aVar = new a(context, attributeSet, i, i2);
        aVar.v1(attributeSet, i, i2);
        return aVar;
    }

    private void z0(Canvas canvas, Rect rect) {
        if (N2()) {
            o0(rect, this.m0);
            RectF rectF = this.m0;
            float f2 = rectF.left;
            float f3 = rectF.top;
            canvas.translate(f2, f3);
            this.W.setBounds(0, 0, (int) this.m0.width(), (int) this.m0.height());
            this.W.draw(canvas);
            canvas.translate(-f2, -f3);
        }
    }

    public void A1(Drawable drawable) {
        if (this.W != drawable) {
            float fP0 = p0();
            this.W = drawable;
            float fP02 = p0();
            Q2(this.W);
            n0(this.W);
            invalidateSelf();
            if (fP0 != fP02) {
                w1();
            }
        }
    }

    public void A2(int i) {
        z2(b.a.k.a.a.c(this.i0, i));
    }

    public void B1(int i) {
        A1(b.a.k.a.a.d(this.i0, i));
    }

    void B2(boolean z2) {
        this.I0 = z2;
    }

    public void C1(ColorStateList colorStateList) {
        if (this.X != colorStateList) {
            this.X = colorStateList;
            if (x0()) {
                androidx.core.graphics.drawable.a.o(this.W, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void C2(d.b.a.a.m.h hVar) {
        this.Y = hVar;
    }

    public void D1(int i) {
        C1(b.a.k.a.a.c(this.i0, i));
    }

    public void D2(int i) {
        C2(d.b.a.a.m.h.d(this.i0, i));
    }

    public void E1(int i) {
        F1(this.i0.getResources().getBoolean(i));
    }

    public void E2(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        if (TextUtils.equals(this.I, charSequence)) {
            return;
        }
        this.I = charSequence;
        this.p0.i(true);
        invalidateSelf();
        w1();
    }

    public void F1(boolean z2) {
        if (this.V != z2) {
            boolean zN2 = N2();
            this.V = z2;
            boolean zN22 = N2();
            if (zN2 != zN22) {
                if (zN22) {
                    n0(this.W);
                } else {
                    Q2(this.W);
                }
                invalidateSelf();
                w1();
            }
        }
    }

    public void F2(d dVar) {
        this.p0.h(dVar, this.i0);
    }

    public void G1(ColorStateList colorStateList) {
        if (this.C != colorStateList) {
            this.C = colorStateList;
            onStateChange(getState());
        }
    }

    public void G2(int i) {
        F2(new d(this.i0, i));
    }

    public void H1(int i) {
        G1(b.a.k.a.a.c(this.i0, i));
    }

    public void H2(float f2) {
        if (this.e0 != f2) {
            this.e0 = f2;
            invalidateSelf();
            w1();
        }
    }

    public Drawable I0() {
        return this.W;
    }

    @Deprecated
    public void I1(float f2) {
        if (this.E != f2) {
            this.E = f2;
            setShapeAppearanceModel(D().w(f2));
        }
    }

    public void I2(int i) {
        H2(this.i0.getResources().getDimension(i));
    }

    public ColorStateList J0() {
        return this.X;
    }

    @Deprecated
    public void J1(int i) {
        I1(this.i0.getResources().getDimension(i));
    }

    public void J2(float f2) {
        if (this.d0 != f2) {
            this.d0 = f2;
            invalidateSelf();
            w1();
        }
    }

    public ColorStateList K0() {
        return this.C;
    }

    public void K1(float f2) {
        if (this.h0 != f2) {
            this.h0 = f2;
            invalidateSelf();
            w1();
        }
    }

    public void K2(int i) {
        J2(this.i0.getResources().getDimension(i));
    }

    public float L0() {
        return this.K0 ? G() : this.E;
    }

    public void L1(int i) {
        K1(this.i0.getResources().getDimension(i));
    }

    public void L2(boolean z2) {
        if (this.E0 != z2) {
            this.E0 = z2;
            R2();
            onStateChange(getState());
        }
    }

    public float M0() {
        return this.h0;
    }

    public void M1(Drawable drawable) {
        Drawable drawableN0 = N0();
        if (drawableN0 != drawable) {
            float fP0 = p0();
            this.K = drawable != null ? androidx.core.graphics.drawable.a.r(drawable).mutate() : null;
            float fP02 = p0();
            Q2(drawableN0);
            if (O2()) {
                n0(this.K);
            }
            invalidateSelf();
            if (fP0 != fP02) {
                w1();
            }
        }
    }

    boolean M2() {
        return this.I0;
    }

    public Drawable N0() {
        Drawable drawable = this.K;
        if (drawable != null) {
            return androidx.core.graphics.drawable.a.q(drawable);
        }
        return null;
    }

    public void N1(int i) {
        M1(b.a.k.a.a.d(this.i0, i));
    }

    public float O0() {
        return this.M;
    }

    public void O1(float f2) {
        if (this.M != f2) {
            float fP0 = p0();
            this.M = f2;
            float fP02 = p0();
            invalidateSelf();
            if (fP0 != fP02) {
                w1();
            }
        }
    }

    public ColorStateList P0() {
        return this.L;
    }

    public void P1(int i) {
        O1(this.i0.getResources().getDimension(i));
    }

    public float Q0() {
        return this.D;
    }

    public void Q1(ColorStateList colorStateList) {
        this.N = true;
        if (this.L != colorStateList) {
            this.L = colorStateList;
            if (O2()) {
                androidx.core.graphics.drawable.a.o(this.K, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public float R0() {
        return this.a0;
    }

    public void R1(int i) {
        Q1(b.a.k.a.a.c(this.i0, i));
    }

    public ColorStateList S0() {
        return this.F;
    }

    public void S1(int i) {
        T1(this.i0.getResources().getBoolean(i));
    }

    public float T0() {
        return this.G;
    }

    public void T1(boolean z2) {
        if (this.J != z2) {
            boolean zO2 = O2();
            this.J = z2;
            boolean zO22 = O2();
            if (zO2 != zO22) {
                if (zO22) {
                    n0(this.K);
                } else {
                    Q2(this.K);
                }
                invalidateSelf();
                w1();
            }
        }
    }

    public Drawable U0() {
        Drawable drawable = this.P;
        if (drawable != null) {
            return androidx.core.graphics.drawable.a.q(drawable);
        }
        return null;
    }

    public void U1(float f2) {
        if (this.D != f2) {
            this.D = f2;
            invalidateSelf();
            w1();
        }
    }

    public CharSequence V0() {
        return this.T;
    }

    public void V1(int i) {
        U1(this.i0.getResources().getDimension(i));
    }

    public float W0() {
        return this.g0;
    }

    public void W1(float f2) {
        if (this.a0 != f2) {
            this.a0 = f2;
            invalidateSelf();
            w1();
        }
    }

    public float X0() {
        return this.S;
    }

    public void X1(int i) {
        W1(this.i0.getResources().getDimension(i));
    }

    public float Y0() {
        return this.f0;
    }

    public void Y1(ColorStateList colorStateList) {
        if (this.F != colorStateList) {
            this.F = colorStateList;
            if (this.K0) {
                i0(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public int[] Z0() {
        return this.D0;
    }

    public void Z1(int i) {
        Y1(b.a.k.a.a.c(this.i0, i));
    }

    @Override // com.google.android.material.internal.i.b
    public void a() {
        w1();
        invalidateSelf();
    }

    public ColorStateList a1() {
        return this.R;
    }

    public void a2(float f2) {
        if (this.G != f2) {
            this.G = f2;
            this.j0.setStrokeWidth(f2);
            if (this.K0) {
                super.j0(f2);
            }
            invalidateSelf();
        }
    }

    public void b1(RectF rectF) {
        s0(getBounds(), rectF);
    }

    public void b2(int i) {
        a2(this.i0.getResources().getDimension(i));
    }

    public TextUtils.TruncateAt c1() {
        return this.H0;
    }

    public d.b.a.a.m.h d1() {
        return this.Z;
    }

    public void d2(Drawable drawable) {
        Drawable drawableU0 = U0();
        if (drawableU0 != drawable) {
            float fT0 = t0();
            this.P = drawable != null ? androidx.core.graphics.drawable.a.r(drawable).mutate() : null;
            if (d.b.a.a.u.b.f4810a) {
                S2();
            }
            float fT02 = t0();
            Q2(drawableU0);
            if (P2()) {
                n0(this.P);
            }
            invalidateSelf();
            if (fT0 != fT02) {
                w1();
            }
        }
    }

    @Override // d.b.a.a.w.h, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds.isEmpty() || getAlpha() == 0) {
            return;
        }
        int i = this.y0;
        int iA = i < 255 ? d.b.a.a.n.a.a(canvas, bounds.left, bounds.top, bounds.right, bounds.bottom, i) : 0;
        D0(canvas, bounds);
        A0(canvas, bounds);
        if (this.K0) {
            super.draw(canvas);
        }
        C0(canvas, bounds);
        F0(canvas, bounds);
        B0(canvas, bounds);
        z0(canvas, bounds);
        if (this.I0) {
            H0(canvas, bounds);
        }
        E0(canvas, bounds);
        G0(canvas, bounds);
        if (this.y0 < 255) {
            canvas.restoreToCount(iA);
        }
    }

    public float e1() {
        return this.c0;
    }

    public void e2(CharSequence charSequence) {
        if (this.T != charSequence) {
            this.T = b.g.i.a.c().h(charSequence);
            invalidateSelf();
        }
    }

    public float f1() {
        return this.b0;
    }

    public void f2(float f2) {
        if (this.g0 != f2) {
            this.g0 = f2;
            invalidateSelf();
            if (P2()) {
                w1();
            }
        }
    }

    public ColorStateList g1() {
        return this.H;
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
        return Math.min(Math.round(this.a0 + p0() + this.d0 + this.p0.f(i1().toString()) + this.e0 + t0() + this.h0), this.J0);
    }

    @Override // d.b.a.a.w.h, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // d.b.a.a.w.h, android.graphics.drawable.Drawable
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

    public d.b.a.a.m.h h1() {
        return this.Y;
    }

    public void h2(int i) {
        d2(b.a.k.a.a.d(this.i0, i));
    }

    public CharSequence i1() {
        return this.I;
    }

    public void i2(float f2) {
        if (this.S != f2) {
            this.S = f2;
            invalidateSelf();
            if (P2()) {
                w1();
            }
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // d.b.a.a.w.h, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return s1(this.B) || s1(this.C) || s1(this.F) || (this.E0 && s1(this.F0)) || u1(this.p0.d()) || x0() || t1(this.K) || t1(this.W) || s1(this.B0);
    }

    public d j1() {
        return this.p0.d();
    }

    public void j2(int i) {
        i2(this.i0.getResources().getDimension(i));
    }

    public float k1() {
        return this.e0;
    }

    public void k2(float f2) {
        if (this.f0 != f2) {
            this.f0 = f2;
            invalidateSelf();
            if (P2()) {
                w1();
            }
        }
    }

    public float l1() {
        return this.d0;
    }

    public void l2(int i) {
        k2(this.i0.getResources().getDimension(i));
    }

    public boolean m2(int[] iArr) {
        if (Arrays.equals(this.D0, iArr)) {
            return false;
        }
        this.D0 = iArr;
        if (P2()) {
            return x1(getState(), iArr);
        }
        return false;
    }

    public boolean n1() {
        return this.E0;
    }

    public void n2(ColorStateList colorStateList) {
        if (this.R != colorStateList) {
            this.R = colorStateList;
            if (P2()) {
                androidx.core.graphics.drawable.a.o(this.P, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void o2(int i) {
        n2(b.a.k.a.a.c(this.i0, i));
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i) {
        boolean zOnLayoutDirectionChanged = super.onLayoutDirectionChanged(i);
        if (O2()) {
            zOnLayoutDirectionChanged |= androidx.core.graphics.drawable.a.m(this.K, i);
        }
        if (N2()) {
            zOnLayoutDirectionChanged |= androidx.core.graphics.drawable.a.m(this.W, i);
        }
        if (P2()) {
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
        if (O2()) {
            zOnLevelChange |= this.K.setLevel(i);
        }
        if (N2()) {
            zOnLevelChange |= this.W.setLevel(i);
        }
        if (P2()) {
            zOnLevelChange |= this.P.setLevel(i);
        }
        if (zOnLevelChange) {
            invalidateSelf();
        }
        return zOnLevelChange;
    }

    @Override // d.b.a.a.w.h, android.graphics.drawable.Drawable, com.google.android.material.internal.i.b
    public boolean onStateChange(int[] iArr) {
        if (this.K0) {
            super.onStateChange(iArr);
        }
        return x1(iArr, Z0());
    }

    float p0() {
        if (O2() || N2()) {
            return this.b0 + this.M + this.c0;
        }
        return 0.0f;
    }

    public boolean p1() {
        return this.U;
    }

    public void p2(boolean z2) {
        if (this.O != z2) {
            boolean zP2 = P2();
            this.O = z2;
            boolean zP22 = P2();
            if (zP2 != zP22) {
                if (zP22) {
                    n0(this.P);
                } else {
                    Q2(this.P);
                }
                invalidateSelf();
                w1();
            }
        }
    }

    public boolean q1() {
        return t1(this.P);
    }

    public void q2(InterfaceC0064a interfaceC0064a) {
        this.G0 = new WeakReference<>(interfaceC0064a);
    }

    public boolean r1() {
        return this.O;
    }

    public void r2(TextUtils.TruncateAt truncateAt) {
        this.H0 = truncateAt;
    }

    public void s2(d.b.a.a.m.h hVar) {
        this.Z = hVar;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // d.b.a.a.w.h, android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (this.y0 != i) {
            this.y0 = i;
            invalidateSelf();
        }
    }

    @Override // d.b.a.a.w.h, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.z0 != colorFilter) {
            this.z0 = colorFilter;
            invalidateSelf();
        }
    }

    @Override // d.b.a.a.w.h, android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        if (this.B0 != colorStateList) {
            this.B0 = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // d.b.a.a.w.h, android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.C0 != mode) {
            this.C0 = mode;
            this.A0 = d.b.a.a.p.a.b(this, this.B0, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z2, boolean z3) {
        boolean visible = super.setVisible(z2, z3);
        if (O2()) {
            visible |= this.K.setVisible(z2, z3);
        }
        if (N2()) {
            visible |= this.W.setVisible(z2, z3);
        }
        if (P2()) {
            visible |= this.P.setVisible(z2, z3);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    float t0() {
        if (P2()) {
            return this.f0 + this.S + this.g0;
        }
        return 0.0f;
    }

    public void t2(int i) {
        s2(d.b.a.a.m.h.d(this.i0, i));
    }

    public void u2(float f2) {
        if (this.c0 != f2) {
            float fP0 = p0();
            this.c0 = f2;
            float fP02 = p0();
            invalidateSelf();
            if (fP0 != fP02) {
                w1();
            }
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public void v2(int i) {
        u2(this.i0.getResources().getDimension(i));
    }

    Paint.Align w0(Rect rect, PointF pointF) {
        pointF.set(0.0f, 0.0f);
        Paint.Align align = Paint.Align.LEFT;
        if (this.I != null) {
            float fP0 = this.a0 + p0() + this.d0;
            if (androidx.core.graphics.drawable.a.f(this) == 0) {
                pointF.x = rect.left + fP0;
                align = Paint.Align.LEFT;
            } else {
                pointF.x = rect.right - fP0;
                align = Paint.Align.RIGHT;
            }
            pointF.y = rect.centerY() - v0();
        }
        return align;
    }

    protected void w1() {
        InterfaceC0064a interfaceC0064a = this.G0.get();
        if (interfaceC0064a != null) {
            interfaceC0064a.a();
        }
    }

    public void w2(float f2) {
        if (this.b0 != f2) {
            float fP0 = p0();
            this.b0 = f2;
            float fP02 = p0();
            invalidateSelf();
            if (fP0 != fP02) {
                w1();
            }
        }
    }

    public void x2(int i) {
        w2(this.i0.getResources().getDimension(i));
    }

    public void y1(boolean z2) {
        if (this.U != z2) {
            this.U = z2;
            float fP0 = p0();
            if (!z2 && this.w0) {
                this.w0 = false;
            }
            float fP02 = p0();
            invalidateSelf();
            if (fP0 != fP02) {
                w1();
            }
        }
    }

    public void y2(int i) {
        this.J0 = i;
    }

    public void z1(int i) {
        y1(this.i0.getResources().getBoolean(i));
    }

    public void z2(ColorStateList colorStateList) {
        if (this.H != colorStateList) {
            this.H = colorStateList;
            R2();
            onStateChange(getState());
        }
    }
}
