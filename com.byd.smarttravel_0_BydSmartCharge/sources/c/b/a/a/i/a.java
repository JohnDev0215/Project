package c.b.a.a.i;

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
import b.g.c.l.b;
import c.b.a.a.q.i;
import c.b.a.a.q.k;
import c.b.a.a.s.c;
import c.b.a.a.s.d;
import c.b.a.a.v.h;
import com.google.android.material.R$styleable;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* JADX INFO: compiled from: ChipDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends h implements b, Drawable.Callback, i.b {
    public PorterDuffColorFilter A0;
    public ColorStateList B;
    public ColorStateList B0;
    public ColorStateList C;
    public PorterDuff.Mode C0;
    public float D;
    public int[] D0;
    public float E;
    public boolean E0;
    public ColorStateList F;
    public ColorStateList F0;
    public float G;
    public WeakReference<InterfaceC0051a> G0;
    public ColorStateList H;
    public TextUtils.TruncateAt H0;
    public CharSequence I;
    public boolean I0;
    public boolean J;
    public int J0;
    public Drawable K;
    public boolean K0;
    public ColorStateList L;
    public float M;
    public boolean N;
    public boolean O;
    public Drawable P;
    public Drawable Q;
    public ColorStateList R;
    public float S;
    public CharSequence T;
    public boolean U;
    public boolean V;
    public Drawable W;
    public ColorStateList X;
    public c.b.a.a.a.h Y;
    public c.b.a.a.a.h Z;
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
    public final Paint k0;
    public final Paint.FontMetrics l0;
    public final RectF m0;
    public final PointF n0;
    public final Path o0;
    public final i p0;
    public int q0;
    public int r0;
    public int s0;
    public int t0;
    public int u0;
    public int v0;
    public boolean w0;
    public int x0;
    public int y0;
    public ColorFilter z0;
    public static final int[] z = {R.attr.state_enabled};
    public static final ShapeDrawable A = new ShapeDrawable(new OvalShape());

    /* JADX INFO: renamed from: c.b.a.a.i.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChipDrawable.java */
    public interface InterfaceC0051a {
        void a();
    }

    public a(Context context, AttributeSet attributeSet, int i, int i2) {
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
        this.I = "";
        iVar.e().density = context.getResources().getDisplayMetrics().density;
        this.k0 = null;
        int[] iArr = z;
        setState(iArr);
        m2(iArr);
        this.I0 = true;
        if (c.b.a.a.t.b.f1838a) {
            A.setTint(-1);
        }
    }

    public static boolean o1(int[] iArr, int i) {
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

    public static boolean s1(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    public static boolean t1(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    public static boolean u1(d dVar) {
        ColorStateList colorStateList;
        return (dVar == null || (colorStateList = dVar.f1826b) == null || !colorStateList.isStateful()) ? false : true;
    }

    public static a y0(Context context, AttributeSet attributeSet, int i, int i2) {
        a aVar = new a(context, attributeSet, i, i2);
        aVar.v1(attributeSet, i, i2);
        return aVar;
    }

    public final void A0(Canvas canvas, Rect rect) {
        if (this.K0) {
            return;
        }
        this.j0.setColor(this.r0);
        this.j0.setStyle(Paint.Style.FILL);
        this.j0.setColorFilter(m1());
        this.m0.set(rect);
        canvas.drawRoundRect(this.m0, L0(), L0(), this.j0);
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
        z2(b.b.b.a.a.c(this.i0, i));
    }

    public final void B0(Canvas canvas, Rect rect) {
        if (O2()) {
            o0(rect, this.m0);
            RectF rectF = this.m0;
            float f = rectF.left;
            float f2 = rectF.top;
            canvas.translate(f, f2);
            this.K.setBounds(0, 0, (int) this.m0.width(), (int) this.m0.height());
            this.K.draw(canvas);
            canvas.translate(-f, -f2);
        }
    }

    public void B1(int i) {
        A1(b.b.b.a.a.d(this.i0, i));
    }

    public void B2(boolean z2) {
        this.I0 = z2;
    }

    public final void C0(Canvas canvas, Rect rect) {
        if (this.G <= 0.0f || this.K0) {
            return;
        }
        this.j0.setColor(this.t0);
        this.j0.setStyle(Paint.Style.STROKE);
        if (!this.K0) {
            this.j0.setColorFilter(m1());
        }
        RectF rectF = this.m0;
        float f = rect.left;
        float f2 = this.G;
        rectF.set(f + (f2 / 2.0f), rect.top + (f2 / 2.0f), rect.right - (f2 / 2.0f), rect.bottom - (f2 / 2.0f));
        float f3 = this.E - (this.G / 2.0f);
        canvas.drawRoundRect(this.m0, f3, f3, this.j0);
    }

    public void C1(ColorStateList colorStateList) {
        if (this.X != colorStateList) {
            this.X = colorStateList;
            if (x0()) {
                b.g.c.l.a.o(this.W, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void C2(c.b.a.a.a.h hVar) {
        this.Y = hVar;
    }

    public final void D0(Canvas canvas, Rect rect) {
        if (this.K0) {
            return;
        }
        this.j0.setColor(this.q0);
        this.j0.setStyle(Paint.Style.FILL);
        this.m0.set(rect);
        canvas.drawRoundRect(this.m0, L0(), L0(), this.j0);
    }

    public void D1(int i) {
        C1(b.b.b.a.a.c(this.i0, i));
    }

    public void D2(int i) {
        C2(c.b.a.a.a.h.d(this.i0, i));
    }

    public final void E0(Canvas canvas, Rect rect) {
        if (P2()) {
            r0(rect, this.m0);
            RectF rectF = this.m0;
            float f = rectF.left;
            float f2 = rectF.top;
            canvas.translate(f, f2);
            this.P.setBounds(0, 0, (int) this.m0.width(), (int) this.m0.height());
            if (c.b.a.a.t.b.f1838a) {
                this.Q.setBounds(this.P.getBounds());
                this.Q.jumpToCurrentState();
                this.Q.draw(canvas);
            } else {
                this.P.draw(canvas);
            }
            canvas.translate(-f, -f2);
        }
    }

    public void E1(int i) {
        F1(this.i0.getResources().getBoolean(i));
    }

    public void E2(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        if (TextUtils.equals(this.I, charSequence)) {
            return;
        }
        this.I = charSequence;
        this.p0.i(true);
        invalidateSelf();
        w1();
    }

    public final void F0(Canvas canvas, Rect rect) {
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

    public final void G0(Canvas canvas, Rect rect) {
        Paint paint = this.k0;
        if (paint != null) {
            paint.setColor(b.g.c.a.d(-16777216, 127));
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
            this.k0.setColor(b.g.c.a.d(-65536, 127));
            q0(rect, this.m0);
            canvas.drawRect(this.m0, this.k0);
            this.k0.setColor(b.g.c.a.d(-16711936, 127));
            s0(rect, this.m0);
            canvas.drawRect(this.m0, this.k0);
        }
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

    public final void H0(Canvas canvas, Rect rect) {
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

    public void H1(int i) {
        G1(b.b.b.a.a.c(this.i0, i));
    }

    public void H2(float f) {
        if (this.e0 != f) {
            this.e0 = f;
            invalidateSelf();
            w1();
        }
    }

    public Drawable I0() {
        return this.W;
    }

    @Deprecated
    public void I1(float f) {
        if (this.E != f) {
            this.E = f;
            setShapeAppearanceModel(D().w(f));
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

    public void J2(float f) {
        if (this.d0 != f) {
            this.d0 = f;
            invalidateSelf();
            w1();
        }
    }

    public ColorStateList K0() {
        return this.C;
    }

    public void K1(float f) {
        if (this.h0 != f) {
            this.h0 = f;
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
            this.K = drawable != null ? b.g.c.l.a.r(drawable).mutate() : null;
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

    public boolean M2() {
        return this.I0;
    }

    public Drawable N0() {
        Drawable drawable = this.K;
        if (drawable != null) {
            return b.g.c.l.a.q(drawable);
        }
        return null;
    }

    public void N1(int i) {
        M1(b.b.b.a.a.d(this.i0, i));
    }

    public final boolean N2() {
        return this.V && this.W != null && this.w0;
    }

    public float O0() {
        return this.M;
    }

    public void O1(float f) {
        if (this.M != f) {
            float fP0 = p0();
            this.M = f;
            float fP02 = p0();
            invalidateSelf();
            if (fP0 != fP02) {
                w1();
            }
        }
    }

    public final boolean O2() {
        return this.J && this.K != null;
    }

    public ColorStateList P0() {
        return this.L;
    }

    public void P1(int i) {
        O1(this.i0.getResources().getDimension(i));
    }

    public final boolean P2() {
        return this.O && this.P != null;
    }

    public float Q0() {
        return this.D;
    }

    public void Q1(ColorStateList colorStateList) {
        this.N = true;
        if (this.L != colorStateList) {
            this.L = colorStateList;
            if (O2()) {
                b.g.c.l.a.o(this.K, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void Q2(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public float R0() {
        return this.a0;
    }

    public void R1(int i) {
        Q1(b.b.b.a.a.c(this.i0, i));
    }

    public final void R2() {
        this.F0 = this.E0 ? c.b.a.a.t.b.d(this.H) : null;
    }

    public ColorStateList S0() {
        return this.F;
    }

    public void S1(int i) {
        T1(this.i0.getResources().getBoolean(i));
    }

    @TargetApi(21)
    public final void S2() {
        this.Q = new RippleDrawable(c.b.a.a.t.b.d(g1()), this.P, A);
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
            return b.g.c.l.a.q(drawable);
        }
        return null;
    }

    public void U1(float f) {
        if (this.D != f) {
            this.D = f;
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

    public void W1(float f) {
        if (this.a0 != f) {
            this.a0 = f;
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
        Y1(b.b.b.a.a.c(this.i0, i));
    }

    @Override // c.b.a.a.q.i.b
    public void a() {
        w1();
        invalidateSelf();
    }

    public ColorStateList a1() {
        return this.R;
    }

    public void a2(float f) {
        if (this.G != f) {
            this.G = f;
            this.j0.setStrokeWidth(f);
            if (this.K0) {
                super.j0(f);
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

    public final void c2(ColorStateList colorStateList) {
        if (this.B != colorStateList) {
            this.B = colorStateList;
            onStateChange(getState());
        }
    }

    public c.b.a.a.a.h d1() {
        return this.Z;
    }

    public void d2(Drawable drawable) {
        Drawable drawableU0 = U0();
        if (drawableU0 != drawable) {
            float fT0 = t0();
            this.P = drawable != null ? b.g.c.l.a.r(drawable).mutate() : null;
            if (c.b.a.a.t.b.f1838a) {
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

    @Override // c.b.a.a.v.h, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds.isEmpty() || getAlpha() == 0) {
            return;
        }
        int i = this.y0;
        int iA = i < 255 ? c.b.a.a.g.a.a(canvas, bounds.left, bounds.top, bounds.right, bounds.bottom, i) : 0;
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
            this.T = b.g.h.a.c().h(charSequence);
            invalidateSelf();
        }
    }

    public float f1() {
        return this.b0;
    }

    public void f2(float f) {
        if (this.g0 != f) {
            this.g0 = f;
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

    @Override // c.b.a.a.v.h, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // c.b.a.a.v.h, android.graphics.drawable.Drawable
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

    public c.b.a.a.a.h h1() {
        return this.Y;
    }

    public void h2(int i) {
        d2(b.b.b.a.a.d(this.i0, i));
    }

    public CharSequence i1() {
        return this.I;
    }

    public void i2(float f) {
        if (this.S != f) {
            this.S = f;
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

    @Override // c.b.a.a.v.h, android.graphics.drawable.Drawable
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

    public void k2(float f) {
        if (this.f0 != f) {
            this.f0 = f;
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

    public final ColorFilter m1() {
        ColorFilter colorFilter = this.z0;
        return colorFilter != null ? colorFilter : this.A0;
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

    public final void n0(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        drawable.setCallback(this);
        b.g.c.l.a.m(drawable, b.g.c.l.a.f(this));
        drawable.setLevel(getLevel());
        drawable.setVisible(isVisible(), false);
        if (drawable == this.P) {
            if (drawable.isStateful()) {
                drawable.setState(Z0());
            }
            b.g.c.l.a.o(drawable, this.R);
            return;
        }
        if (drawable.isStateful()) {
            drawable.setState(getState());
        }
        Drawable drawable2 = this.K;
        if (drawable == drawable2 && this.N) {
            b.g.c.l.a.o(drawable2, this.L);
        }
    }

    public boolean n1() {
        return this.E0;
    }

    public void n2(ColorStateList colorStateList) {
        if (this.R != colorStateList) {
            this.R = colorStateList;
            if (P2()) {
                b.g.c.l.a.o(this.P, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void o0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (O2() || N2()) {
            float f = this.a0 + this.b0;
            if (b.g.c.l.a.f(this) == 0) {
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

    public void o2(int i) {
        n2(b.b.b.a.a.c(this.i0, i));
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i) {
        boolean zOnLayoutDirectionChanged = super.onLayoutDirectionChanged(i);
        if (O2()) {
            zOnLayoutDirectionChanged |= b.g.c.l.a.m(this.K, i);
        }
        if (N2()) {
            zOnLayoutDirectionChanged |= b.g.c.l.a.m(this.W, i);
        }
        if (P2()) {
            zOnLayoutDirectionChanged |= b.g.c.l.a.m(this.P, i);
        }
        if (!zOnLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i) {
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

    @Override // c.b.a.a.v.h, android.graphics.drawable.Drawable, c.b.a.a.q.i.b
    public boolean onStateChange(int[] iArr) {
        if (this.K0) {
            super.onStateChange(iArr);
        }
        return x1(iArr, Z0());
    }

    public float p0() {
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

    public final void q0(Rect rect, RectF rectF) {
        rectF.set(rect);
        if (P2()) {
            float f = this.h0 + this.g0 + this.S + this.f0 + this.e0;
            if (b.g.c.l.a.f(this) == 0) {
                rectF.right = rect.right - f;
            } else {
                rectF.left = rect.left + f;
            }
        }
    }

    public boolean q1() {
        return t1(this.P);
    }

    public void q2(InterfaceC0051a interfaceC0051a) {
        this.G0 = new WeakReference<>(interfaceC0051a);
    }

    public final void r0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (P2()) {
            float f = this.h0 + this.g0;
            if (b.g.c.l.a.f(this) == 0) {
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

    public boolean r1() {
        return this.O;
    }

    public void r2(TextUtils.TruncateAt truncateAt) {
        this.H0 = truncateAt;
    }

    public final void s0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (P2()) {
            float f = this.h0 + this.g0 + this.S + this.f0 + this.e0;
            if (b.g.c.l.a.f(this) == 0) {
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

    public void s2(c.b.a.a.a.h hVar) {
        this.Z = hVar;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // c.b.a.a.v.h, android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (this.y0 != i) {
            this.y0 = i;
            invalidateSelf();
        }
    }

    @Override // c.b.a.a.v.h, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.z0 != colorFilter) {
            this.z0 = colorFilter;
            invalidateSelf();
        }
    }

    @Override // c.b.a.a.v.h, android.graphics.drawable.Drawable, b.g.c.l.b
    public void setTintList(ColorStateList colorStateList) {
        if (this.B0 != colorStateList) {
            this.B0 = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // c.b.a.a.v.h, android.graphics.drawable.Drawable, b.g.c.l.b
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.C0 != mode) {
            this.C0 = mode;
            this.A0 = c.b.a.a.m.a.b(this, this.B0, mode);
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

    public float t0() {
        if (P2()) {
            return this.f0 + this.S + this.g0;
        }
        return 0.0f;
    }

    public void t2(int i) {
        s2(c.b.a.a.a.h.d(this.i0, i));
    }

    public final void u0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (this.I != null) {
            float fP0 = this.a0 + p0() + this.d0;
            float fT0 = this.h0 + t0() + this.e0;
            if (b.g.c.l.a.f(this) == 0) {
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

    public void u2(float f) {
        if (this.c0 != f) {
            float fP0 = p0();
            this.c0 = f;
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

    public final float v0() {
        this.p0.e().getFontMetrics(this.l0);
        Paint.FontMetrics fontMetrics = this.l0;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    public final void v1(AttributeSet attributeSet, int i, int i2) {
        TypedArray typedArrayH = k.h(this.i0, attributeSet, R$styleable.Chip, i, i2, new int[0]);
        this.K0 = typedArrayH.hasValue(R$styleable.Chip_shapeAppearance);
        c2(c.a(this.i0, typedArrayH, R$styleable.Chip_chipSurfaceColor));
        G1(c.a(this.i0, typedArrayH, R$styleable.Chip_chipBackgroundColor));
        U1(typedArrayH.getDimension(R$styleable.Chip_chipMinHeight, 0.0f));
        int i3 = R$styleable.Chip_chipCornerRadius;
        if (typedArrayH.hasValue(i3)) {
            I1(typedArrayH.getDimension(i3, 0.0f));
        }
        Y1(c.a(this.i0, typedArrayH, R$styleable.Chip_chipStrokeColor));
        a2(typedArrayH.getDimension(R$styleable.Chip_chipStrokeWidth, 0.0f));
        z2(c.a(this.i0, typedArrayH, R$styleable.Chip_rippleColor));
        E2(typedArrayH.getText(R$styleable.Chip_android_text));
        F2(c.f(this.i0, typedArrayH, R$styleable.Chip_android_textAppearance));
        int i4 = typedArrayH.getInt(R$styleable.Chip_android_ellipsize, 0);
        if (i4 == 1) {
            r2(TextUtils.TruncateAt.START);
        } else if (i4 == 2) {
            r2(TextUtils.TruncateAt.MIDDLE);
        } else if (i4 == 3) {
            r2(TextUtils.TruncateAt.END);
        }
        T1(typedArrayH.getBoolean(R$styleable.Chip_chipIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") == null) {
            T1(typedArrayH.getBoolean(R$styleable.Chip_chipIconEnabled, false));
        }
        M1(c.d(this.i0, typedArrayH, R$styleable.Chip_chipIcon));
        int i5 = R$styleable.Chip_chipIconTint;
        if (typedArrayH.hasValue(i5)) {
            Q1(c.a(this.i0, typedArrayH, i5));
        }
        O1(typedArrayH.getDimension(R$styleable.Chip_chipIconSize, 0.0f));
        p2(typedArrayH.getBoolean(R$styleable.Chip_closeIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") == null) {
            p2(typedArrayH.getBoolean(R$styleable.Chip_closeIconEnabled, false));
        }
        d2(c.d(this.i0, typedArrayH, R$styleable.Chip_closeIcon));
        n2(c.a(this.i0, typedArrayH, R$styleable.Chip_closeIconTint));
        i2(typedArrayH.getDimension(R$styleable.Chip_closeIconSize, 0.0f));
        y1(typedArrayH.getBoolean(R$styleable.Chip_android_checkable, false));
        F1(typedArrayH.getBoolean(R$styleable.Chip_checkedIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") == null) {
            F1(typedArrayH.getBoolean(R$styleable.Chip_checkedIconEnabled, false));
        }
        A1(c.d(this.i0, typedArrayH, R$styleable.Chip_checkedIcon));
        int i6 = R$styleable.Chip_checkedIconTint;
        if (typedArrayH.hasValue(i6)) {
            C1(c.a(this.i0, typedArrayH, i6));
        }
        C2(c.b.a.a.a.h.c(this.i0, typedArrayH, R$styleable.Chip_showMotionSpec));
        s2(c.b.a.a.a.h.c(this.i0, typedArrayH, R$styleable.Chip_hideMotionSpec));
        W1(typedArrayH.getDimension(R$styleable.Chip_chipStartPadding, 0.0f));
        w2(typedArrayH.getDimension(R$styleable.Chip_iconStartPadding, 0.0f));
        u2(typedArrayH.getDimension(R$styleable.Chip_iconEndPadding, 0.0f));
        J2(typedArrayH.getDimension(R$styleable.Chip_textStartPadding, 0.0f));
        H2(typedArrayH.getDimension(R$styleable.Chip_textEndPadding, 0.0f));
        k2(typedArrayH.getDimension(R$styleable.Chip_closeIconStartPadding, 0.0f));
        f2(typedArrayH.getDimension(R$styleable.Chip_closeIconEndPadding, 0.0f));
        K1(typedArrayH.getDimension(R$styleable.Chip_chipEndPadding, 0.0f));
        y2(typedArrayH.getDimensionPixelSize(R$styleable.Chip_android_maxWidth, Integer.MAX_VALUE));
        typedArrayH.recycle();
    }

    public void v2(int i) {
        u2(this.i0.getResources().getDimension(i));
    }

    public Paint.Align w0(Rect rect, PointF pointF) {
        pointF.set(0.0f, 0.0f);
        Paint.Align align = Paint.Align.LEFT;
        if (this.I != null) {
            float fP0 = this.a0 + p0() + this.d0;
            if (b.g.c.l.a.f(this) == 0) {
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

    public void w1() {
        InterfaceC0051a interfaceC0051a = this.G0.get();
        if (interfaceC0051a != null) {
            interfaceC0051a.a();
        }
    }

    public void w2(float f) {
        if (this.b0 != f) {
            float fP0 = p0();
            this.b0 = f;
            float fP02 = p0();
            invalidateSelf();
            if (fP0 != fP02) {
                w1();
            }
        }
    }

    public final boolean x0() {
        return this.V && this.W != null && this.U;
    }

    public final boolean x1(int[] iArr, int[] iArr2) {
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
        int iE = c.b.a.a.k.a.e(colorForState, colorForState2);
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
        int colorForState4 = (this.F0 == null || !c.b.a.a.t.b.e(iArr)) ? 0 : this.F0.getColorForState(iArr, this.u0);
        if (this.u0 != colorForState4) {
            this.u0 = colorForState4;
            if (this.E0) {
                zOnStateChange = true;
            }
        }
        int colorForState5 = (this.p0.d() == null || this.p0.d().f1826b == null) ? 0 : this.p0.d().f1826b.getColorForState(iArr, this.v0);
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
            this.A0 = c.b.a.a.m.a.b(this, this.B0, this.C0);
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
        if (c.b.a.a.t.b.f1838a && t1(this.Q)) {
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

    public final void z0(Canvas canvas, Rect rect) {
        if (N2()) {
            o0(rect, this.m0);
            RectF rectF = this.m0;
            float f = rectF.left;
            float f2 = rectF.top;
            canvas.translate(f, f2);
            this.W.setBounds(0, 0, (int) this.m0.width(), (int) this.m0.height());
            this.W.draw(canvas);
            canvas.translate(-f, -f2);
        }
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
