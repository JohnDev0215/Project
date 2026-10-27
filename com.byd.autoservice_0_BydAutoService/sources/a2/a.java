package a2;

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
import com.google.android.material.R$styleable;
import e0.b;
import i2.i;
import i2.k;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import k2.c;
import k2.d;
import n2.h;

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
    public WeakReference<InterfaceC0002a> G0;
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
    public s1.h Y;
    public s1.h Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public float f83a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public float f84b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public float f85c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public float f86d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public float f87e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public float f88f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public float f89g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public float f90h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public final Context f91i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public final Paint f92j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public final Paint f93k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public final Paint.FontMetrics f94l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public final RectF f95m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public final PointF f96n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public final Path f97o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public final i f98p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public int f99q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public int f100r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public int f101s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public int f102t0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public int f103u0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public int f104v0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public boolean f105w0;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public int f106x0;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public int f107y0;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public ColorFilter f108z0;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final int[] f82z = {R.attr.state_enabled};
    public static final ShapeDrawable A = new ShapeDrawable(new OvalShape());

    /* JADX INFO: renamed from: a2.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChipDrawable.java */
    public interface InterfaceC0002a {
        void a();
    }

    public a(Context context, AttributeSet attributeSet, int i3, int i4) {
        super(context, attributeSet, i3, i4);
        this.E = -1.0f;
        this.f92j0 = new Paint(1);
        this.f94l0 = new Paint.FontMetrics();
        this.f95m0 = new RectF();
        this.f96n0 = new PointF();
        this.f97o0 = new Path();
        this.f107y0 = 255;
        this.C0 = PorterDuff.Mode.SRC_IN;
        this.G0 = new WeakReference<>(null);
        N(context);
        this.f91i0 = context;
        i iVar = new i(this);
        this.f98p0 = iVar;
        this.I = "";
        iVar.e().density = context.getResources().getDisplayMetrics().density;
        this.f93k0 = null;
        int[] iArr = f82z;
        setState(iArr);
        m2(iArr);
        this.I0 = true;
        if (l2.b.f3717a) {
            A.setTint(-1);
        }
    }

    public static boolean o1(int[] iArr, int i3) {
        if (iArr == null) {
            return false;
        }
        for (int i4 : iArr) {
            if (i4 == i3) {
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
        return (dVar == null || (colorStateList = dVar.f3588b) == null || !colorStateList.isStateful()) ? false : true;
    }

    public static a y0(Context context, AttributeSet attributeSet, int i3, int i4) {
        a aVar = new a(context, attributeSet, i3, i4);
        aVar.v1(attributeSet, i3, i4);
        return aVar;
    }

    public final void A0(Canvas canvas, Rect rect) {
        if (this.K0) {
            return;
        }
        this.f92j0.setColor(this.f100r0);
        this.f92j0.setStyle(Paint.Style.FILL);
        this.f92j0.setColorFilter(m1());
        this.f95m0.set(rect);
        canvas.drawRoundRect(this.f95m0, L0(), L0(), this.f92j0);
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

    public void A2(int i3) {
        z2(j.a.c(this.f91i0, i3));
    }

    public final void B0(Canvas canvas, Rect rect) {
        if (O2()) {
            o0(rect, this.f95m0);
            RectF rectF = this.f95m0;
            float f3 = rectF.left;
            float f4 = rectF.top;
            canvas.translate(f3, f4);
            this.K.setBounds(0, 0, (int) this.f95m0.width(), (int) this.f95m0.height());
            this.K.draw(canvas);
            canvas.translate(-f3, -f4);
        }
    }

    public void B1(int i3) {
        A1(j.a.d(this.f91i0, i3));
    }

    public void B2(boolean z3) {
        this.I0 = z3;
    }

    public final void C0(Canvas canvas, Rect rect) {
        if (this.G <= 0.0f || this.K0) {
            return;
        }
        this.f92j0.setColor(this.f102t0);
        this.f92j0.setStyle(Paint.Style.STROKE);
        if (!this.K0) {
            this.f92j0.setColorFilter(m1());
        }
        RectF rectF = this.f95m0;
        float f3 = rect.left;
        float f4 = this.G;
        rectF.set(f3 + (f4 / 2.0f), rect.top + (f4 / 2.0f), rect.right - (f4 / 2.0f), rect.bottom - (f4 / 2.0f));
        float f5 = this.E - (this.G / 2.0f);
        canvas.drawRoundRect(this.f95m0, f5, f5, this.f92j0);
    }

    public void C1(ColorStateList colorStateList) {
        if (this.X != colorStateList) {
            this.X = colorStateList;
            if (x0()) {
                e0.a.o(this.W, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void C2(s1.h hVar) {
        this.Y = hVar;
    }

    public final void D0(Canvas canvas, Rect rect) {
        if (this.K0) {
            return;
        }
        this.f92j0.setColor(this.f99q0);
        this.f92j0.setStyle(Paint.Style.FILL);
        this.f95m0.set(rect);
        canvas.drawRoundRect(this.f95m0, L0(), L0(), this.f92j0);
    }

    public void D1(int i3) {
        C1(j.a.c(this.f91i0, i3));
    }

    public void D2(int i3) {
        C2(s1.h.d(this.f91i0, i3));
    }

    public final void E0(Canvas canvas, Rect rect) {
        if (P2()) {
            r0(rect, this.f95m0);
            RectF rectF = this.f95m0;
            float f3 = rectF.left;
            float f4 = rectF.top;
            canvas.translate(f3, f4);
            this.P.setBounds(0, 0, (int) this.f95m0.width(), (int) this.f95m0.height());
            if (l2.b.f3717a) {
                this.Q.setBounds(this.P.getBounds());
                this.Q.jumpToCurrentState();
                this.Q.draw(canvas);
            } else {
                this.P.draw(canvas);
            }
            canvas.translate(-f3, -f4);
        }
    }

    public void E1(int i3) {
        F1(this.f91i0.getResources().getBoolean(i3));
    }

    public void E2(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        if (TextUtils.equals(this.I, charSequence)) {
            return;
        }
        this.I = charSequence;
        this.f98p0.i(true);
        invalidateSelf();
        w1();
    }

    public final void F0(Canvas canvas, Rect rect) {
        this.f92j0.setColor(this.f103u0);
        this.f92j0.setStyle(Paint.Style.FILL);
        this.f95m0.set(rect);
        if (!this.K0) {
            canvas.drawRoundRect(this.f95m0, L0(), L0(), this.f92j0);
        } else {
            h(new RectF(rect), this.f97o0);
            super.p(canvas, this.f92j0, this.f97o0, u());
        }
    }

    public void F1(boolean z3) {
        if (this.V != z3) {
            boolean zN2 = N2();
            this.V = z3;
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
        this.f98p0.h(dVar, this.f91i0);
    }

    public final void G0(Canvas canvas, Rect rect) {
        Paint paint = this.f93k0;
        if (paint != null) {
            paint.setColor(d0.a.d(-16777216, 127));
            canvas.drawRect(rect, this.f93k0);
            if (O2() || N2()) {
                o0(rect, this.f95m0);
                canvas.drawRect(this.f95m0, this.f93k0);
            }
            if (this.I != null) {
                canvas.drawLine(rect.left, rect.exactCenterY(), rect.right, rect.exactCenterY(), this.f93k0);
            }
            if (P2()) {
                r0(rect, this.f95m0);
                canvas.drawRect(this.f95m0, this.f93k0);
            }
            this.f93k0.setColor(d0.a.d(-65536, 127));
            q0(rect, this.f95m0);
            canvas.drawRect(this.f95m0, this.f93k0);
            this.f93k0.setColor(d0.a.d(-16711936, 127));
            s0(rect, this.f95m0);
            canvas.drawRect(this.f95m0, this.f93k0);
        }
    }

    public void G1(ColorStateList colorStateList) {
        if (this.C != colorStateList) {
            this.C = colorStateList;
            onStateChange(getState());
        }
    }

    public void G2(int i3) {
        F2(new d(this.f91i0, i3));
    }

    public final void H0(Canvas canvas, Rect rect) {
        if (this.I != null) {
            Paint.Align alignW0 = w0(rect, this.f96n0);
            u0(rect, this.f95m0);
            if (this.f98p0.d() != null) {
                this.f98p0.e().drawableState = getState();
                this.f98p0.j(this.f91i0);
            }
            this.f98p0.e().setTextAlign(alignW0);
            int iSave = 0;
            boolean z3 = Math.round(this.f98p0.f(i1().toString())) > Math.round(this.f95m0.width());
            if (z3) {
                iSave = canvas.save();
                canvas.clipRect(this.f95m0);
            }
            CharSequence charSequenceEllipsize = this.I;
            if (z3 && this.H0 != null) {
                charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, this.f98p0.e(), this.f95m0.width(), this.H0);
            }
            CharSequence charSequence = charSequenceEllipsize;
            int length = charSequence.length();
            PointF pointF = this.f96n0;
            canvas.drawText(charSequence, 0, length, pointF.x, pointF.y, this.f98p0.e());
            if (z3) {
                canvas.restoreToCount(iSave);
            }
        }
    }

    public void H1(int i3) {
        G1(j.a.c(this.f91i0, i3));
    }

    public void H2(float f3) {
        if (this.f87e0 != f3) {
            this.f87e0 = f3;
            invalidateSelf();
            w1();
        }
    }

    public Drawable I0() {
        return this.W;
    }

    @Deprecated
    public void I1(float f3) {
        if (this.E != f3) {
            this.E = f3;
            setShapeAppearanceModel(D().w(f3));
        }
    }

    public void I2(int i3) {
        H2(this.f91i0.getResources().getDimension(i3));
    }

    public ColorStateList J0() {
        return this.X;
    }

    @Deprecated
    public void J1(int i3) {
        I1(this.f91i0.getResources().getDimension(i3));
    }

    public void J2(float f3) {
        if (this.f86d0 != f3) {
            this.f86d0 = f3;
            invalidateSelf();
            w1();
        }
    }

    public ColorStateList K0() {
        return this.C;
    }

    public void K1(float f3) {
        if (this.f90h0 != f3) {
            this.f90h0 = f3;
            invalidateSelf();
            w1();
        }
    }

    public void K2(int i3) {
        J2(this.f91i0.getResources().getDimension(i3));
    }

    public float L0() {
        return this.K0 ? G() : this.E;
    }

    public void L1(int i3) {
        K1(this.f91i0.getResources().getDimension(i3));
    }

    public void L2(boolean z3) {
        if (this.E0 != z3) {
            this.E0 = z3;
            R2();
            onStateChange(getState());
        }
    }

    public float M0() {
        return this.f90h0;
    }

    public void M1(Drawable drawable) {
        Drawable drawableN0 = N0();
        if (drawableN0 != drawable) {
            float fP0 = p0();
            this.K = drawable != null ? e0.a.r(drawable).mutate() : null;
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
            return e0.a.q(drawable);
        }
        return null;
    }

    public void N1(int i3) {
        M1(j.a.d(this.f91i0, i3));
    }

    public final boolean N2() {
        return this.V && this.W != null && this.f105w0;
    }

    public float O0() {
        return this.M;
    }

    public void O1(float f3) {
        if (this.M != f3) {
            float fP0 = p0();
            this.M = f3;
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

    public void P1(int i3) {
        O1(this.f91i0.getResources().getDimension(i3));
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
                e0.a.o(this.K, colorStateList);
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
        return this.f83a0;
    }

    public void R1(int i3) {
        Q1(j.a.c(this.f91i0, i3));
    }

    public final void R2() {
        this.F0 = this.E0 ? l2.b.d(this.H) : null;
    }

    public ColorStateList S0() {
        return this.F;
    }

    public void S1(int i3) {
        T1(this.f91i0.getResources().getBoolean(i3));
    }

    @TargetApi(21)
    public final void S2() {
        this.Q = new RippleDrawable(l2.b.d(g1()), this.P, A);
    }

    public float T0() {
        return this.G;
    }

    public void T1(boolean z3) {
        if (this.J != z3) {
            boolean zO2 = O2();
            this.J = z3;
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
            return e0.a.q(drawable);
        }
        return null;
    }

    public void U1(float f3) {
        if (this.D != f3) {
            this.D = f3;
            invalidateSelf();
            w1();
        }
    }

    public CharSequence V0() {
        return this.T;
    }

    public void V1(int i3) {
        U1(this.f91i0.getResources().getDimension(i3));
    }

    public float W0() {
        return this.f89g0;
    }

    public void W1(float f3) {
        if (this.f83a0 != f3) {
            this.f83a0 = f3;
            invalidateSelf();
            w1();
        }
    }

    public float X0() {
        return this.S;
    }

    public void X1(int i3) {
        W1(this.f91i0.getResources().getDimension(i3));
    }

    public float Y0() {
        return this.f88f0;
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

    public void Z1(int i3) {
        Y1(j.a.c(this.f91i0, i3));
    }

    @Override // i2.i.b
    public void a() {
        w1();
        invalidateSelf();
    }

    public ColorStateList a1() {
        return this.R;
    }

    public void a2(float f3) {
        if (this.G != f3) {
            this.G = f3;
            this.f92j0.setStrokeWidth(f3);
            if (this.K0) {
                super.j0(f3);
            }
            invalidateSelf();
        }
    }

    public void b1(RectF rectF) {
        s0(getBounds(), rectF);
    }

    public void b2(int i3) {
        a2(this.f91i0.getResources().getDimension(i3));
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

    public s1.h d1() {
        return this.Z;
    }

    public void d2(Drawable drawable) {
        Drawable drawableU0 = U0();
        if (drawableU0 != drawable) {
            float fT0 = t0();
            this.P = drawable != null ? e0.a.r(drawable).mutate() : null;
            if (l2.b.f3717a) {
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

    @Override // n2.h, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds.isEmpty() || getAlpha() == 0) {
            return;
        }
        int i3 = this.f107y0;
        int iA = i3 < 255 ? y1.a.a(canvas, bounds.left, bounds.top, bounds.right, bounds.bottom, i3) : 0;
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
        if (this.f107y0 < 255) {
            canvas.restoreToCount(iA);
        }
    }

    public float e1() {
        return this.f85c0;
    }

    public void e2(CharSequence charSequence) {
        if (this.T != charSequence) {
            this.T = j0.a.c().h(charSequence);
            invalidateSelf();
        }
    }

    public float f1() {
        return this.f84b0;
    }

    public void f2(float f3) {
        if (this.f89g0 != f3) {
            this.f89g0 = f3;
            invalidateSelf();
            if (P2()) {
                w1();
            }
        }
    }

    public ColorStateList g1() {
        return this.H;
    }

    public void g2(int i3) {
        f2(this.f91i0.getResources().getDimension(i3));
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f107y0;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.f108z0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.D;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return Math.min(Math.round(this.f83a0 + p0() + this.f86d0 + this.f98p0.f(i1().toString()) + this.f87e0 + t0() + this.f90h0), this.J0);
    }

    @Override // n2.h, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // n2.h, android.graphics.drawable.Drawable
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

    public s1.h h1() {
        return this.Y;
    }

    public void h2(int i3) {
        d2(j.a.d(this.f91i0, i3));
    }

    public CharSequence i1() {
        return this.I;
    }

    public void i2(float f3) {
        if (this.S != f3) {
            this.S = f3;
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

    @Override // n2.h, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return s1(this.B) || s1(this.C) || s1(this.F) || (this.E0 && s1(this.F0)) || u1(this.f98p0.d()) || x0() || t1(this.K) || t1(this.W) || s1(this.B0);
    }

    public d j1() {
        return this.f98p0.d();
    }

    public void j2(int i3) {
        i2(this.f91i0.getResources().getDimension(i3));
    }

    public float k1() {
        return this.f87e0;
    }

    public void k2(float f3) {
        if (this.f88f0 != f3) {
            this.f88f0 = f3;
            invalidateSelf();
            if (P2()) {
                w1();
            }
        }
    }

    public float l1() {
        return this.f86d0;
    }

    public void l2(int i3) {
        k2(this.f91i0.getResources().getDimension(i3));
    }

    public final ColorFilter m1() {
        ColorFilter colorFilter = this.f108z0;
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
        e0.a.m(drawable, e0.a.f(this));
        drawable.setLevel(getLevel());
        drawable.setVisible(isVisible(), false);
        if (drawable == this.P) {
            if (drawable.isStateful()) {
                drawable.setState(Z0());
            }
            e0.a.o(drawable, this.R);
            return;
        }
        if (drawable.isStateful()) {
            drawable.setState(getState());
        }
        Drawable drawable2 = this.K;
        if (drawable == drawable2 && this.N) {
            e0.a.o(drawable2, this.L);
        }
    }

    public boolean n1() {
        return this.E0;
    }

    public void n2(ColorStateList colorStateList) {
        if (this.R != colorStateList) {
            this.R = colorStateList;
            if (P2()) {
                e0.a.o(this.P, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void o0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (O2() || N2()) {
            float f3 = this.f83a0 + this.f84b0;
            if (e0.a.f(this) == 0) {
                float f4 = rect.left + f3;
                rectF.left = f4;
                rectF.right = f4 + this.M;
            } else {
                float f5 = rect.right - f3;
                rectF.right = f5;
                rectF.left = f5 - this.M;
            }
            float fExactCenterY = rect.exactCenterY();
            float f6 = this.M;
            float f7 = fExactCenterY - (f6 / 2.0f);
            rectF.top = f7;
            rectF.bottom = f7 + f6;
        }
    }

    public void o2(int i3) {
        n2(j.a.c(this.f91i0, i3));
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i3) {
        boolean zOnLayoutDirectionChanged = super.onLayoutDirectionChanged(i3);
        if (O2()) {
            zOnLayoutDirectionChanged |= e0.a.m(this.K, i3);
        }
        if (N2()) {
            zOnLayoutDirectionChanged |= e0.a.m(this.W, i3);
        }
        if (P2()) {
            zOnLayoutDirectionChanged |= e0.a.m(this.P, i3);
        }
        if (!zOnLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i3) {
        boolean zOnLevelChange = super.onLevelChange(i3);
        if (O2()) {
            zOnLevelChange |= this.K.setLevel(i3);
        }
        if (N2()) {
            zOnLevelChange |= this.W.setLevel(i3);
        }
        if (P2()) {
            zOnLevelChange |= this.P.setLevel(i3);
        }
        if (zOnLevelChange) {
            invalidateSelf();
        }
        return zOnLevelChange;
    }

    @Override // n2.h, android.graphics.drawable.Drawable, i2.i.b
    public boolean onStateChange(int[] iArr) {
        if (this.K0) {
            super.onStateChange(iArr);
        }
        return x1(iArr, Z0());
    }

    public float p0() {
        if (O2() || N2()) {
            return this.f84b0 + this.M + this.f85c0;
        }
        return 0.0f;
    }

    public boolean p1() {
        return this.U;
    }

    public void p2(boolean z3) {
        if (this.O != z3) {
            boolean zP2 = P2();
            this.O = z3;
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
            float f3 = this.f90h0 + this.f89g0 + this.S + this.f88f0 + this.f87e0;
            if (e0.a.f(this) == 0) {
                rectF.right = rect.right - f3;
            } else {
                rectF.left = rect.left + f3;
            }
        }
    }

    public boolean q1() {
        return t1(this.P);
    }

    public void q2(InterfaceC0002a interfaceC0002a) {
        this.G0 = new WeakReference<>(interfaceC0002a);
    }

    public final void r0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (P2()) {
            float f3 = this.f90h0 + this.f89g0;
            if (e0.a.f(this) == 0) {
                float f4 = rect.right - f3;
                rectF.right = f4;
                rectF.left = f4 - this.S;
            } else {
                float f5 = rect.left + f3;
                rectF.left = f5;
                rectF.right = f5 + this.S;
            }
            float fExactCenterY = rect.exactCenterY();
            float f6 = this.S;
            float f7 = fExactCenterY - (f6 / 2.0f);
            rectF.top = f7;
            rectF.bottom = f7 + f6;
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
            float f3 = this.f90h0 + this.f89g0 + this.S + this.f88f0 + this.f87e0;
            if (e0.a.f(this) == 0) {
                float f4 = rect.right;
                rectF.right = f4;
                rectF.left = f4 - f3;
            } else {
                int i3 = rect.left;
                rectF.left = i3;
                rectF.right = i3 + f3;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    public void s2(s1.h hVar) {
        this.Z = hVar;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j3) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j3);
        }
    }

    @Override // n2.h, android.graphics.drawable.Drawable
    public void setAlpha(int i3) {
        if (this.f107y0 != i3) {
            this.f107y0 = i3;
            invalidateSelf();
        }
    }

    @Override // n2.h, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.f108z0 != colorFilter) {
            this.f108z0 = colorFilter;
            invalidateSelf();
        }
    }

    @Override // n2.h, android.graphics.drawable.Drawable, e0.b
    public void setTintList(ColorStateList colorStateList) {
        if (this.B0 != colorStateList) {
            this.B0 = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // n2.h, android.graphics.drawable.Drawable, e0.b
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.C0 != mode) {
            this.C0 = mode;
            this.A0 = e2.a.b(this, this.B0, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z3, boolean z4) {
        boolean visible = super.setVisible(z3, z4);
        if (O2()) {
            visible |= this.K.setVisible(z3, z4);
        }
        if (N2()) {
            visible |= this.W.setVisible(z3, z4);
        }
        if (P2()) {
            visible |= this.P.setVisible(z3, z4);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    public float t0() {
        if (P2()) {
            return this.f88f0 + this.S + this.f89g0;
        }
        return 0.0f;
    }

    public void t2(int i3) {
        s2(s1.h.d(this.f91i0, i3));
    }

    public final void u0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (this.I != null) {
            float fP0 = this.f83a0 + p0() + this.f86d0;
            float fT0 = this.f90h0 + t0() + this.f87e0;
            if (e0.a.f(this) == 0) {
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

    public void u2(float f3) {
        if (this.f85c0 != f3) {
            float fP0 = p0();
            this.f85c0 = f3;
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
        this.f98p0.e().getFontMetrics(this.f94l0);
        Paint.FontMetrics fontMetrics = this.f94l0;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    public final void v1(AttributeSet attributeSet, int i3, int i4) {
        TypedArray typedArrayH = k.h(this.f91i0, attributeSet, R$styleable.Chip, i3, i4, new int[0]);
        this.K0 = typedArrayH.hasValue(R$styleable.Chip_shapeAppearance);
        c2(c.a(this.f91i0, typedArrayH, R$styleable.Chip_chipSurfaceColor));
        G1(c.a(this.f91i0, typedArrayH, R$styleable.Chip_chipBackgroundColor));
        U1(typedArrayH.getDimension(R$styleable.Chip_chipMinHeight, 0.0f));
        int i5 = R$styleable.Chip_chipCornerRadius;
        if (typedArrayH.hasValue(i5)) {
            I1(typedArrayH.getDimension(i5, 0.0f));
        }
        Y1(c.a(this.f91i0, typedArrayH, R$styleable.Chip_chipStrokeColor));
        a2(typedArrayH.getDimension(R$styleable.Chip_chipStrokeWidth, 0.0f));
        z2(c.a(this.f91i0, typedArrayH, R$styleable.Chip_rippleColor));
        E2(typedArrayH.getText(R$styleable.Chip_android_text));
        F2(c.f(this.f91i0, typedArrayH, R$styleable.Chip_android_textAppearance));
        int i6 = typedArrayH.getInt(R$styleable.Chip_android_ellipsize, 0);
        if (i6 == 1) {
            r2(TextUtils.TruncateAt.START);
        } else if (i6 == 2) {
            r2(TextUtils.TruncateAt.MIDDLE);
        } else if (i6 == 3) {
            r2(TextUtils.TruncateAt.END);
        }
        T1(typedArrayH.getBoolean(R$styleable.Chip_chipIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") == null) {
            T1(typedArrayH.getBoolean(R$styleable.Chip_chipIconEnabled, false));
        }
        M1(c.d(this.f91i0, typedArrayH, R$styleable.Chip_chipIcon));
        int i7 = R$styleable.Chip_chipIconTint;
        if (typedArrayH.hasValue(i7)) {
            Q1(c.a(this.f91i0, typedArrayH, i7));
        }
        O1(typedArrayH.getDimension(R$styleable.Chip_chipIconSize, 0.0f));
        p2(typedArrayH.getBoolean(R$styleable.Chip_closeIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") == null) {
            p2(typedArrayH.getBoolean(R$styleable.Chip_closeIconEnabled, false));
        }
        d2(c.d(this.f91i0, typedArrayH, R$styleable.Chip_closeIcon));
        n2(c.a(this.f91i0, typedArrayH, R$styleable.Chip_closeIconTint));
        i2(typedArrayH.getDimension(R$styleable.Chip_closeIconSize, 0.0f));
        y1(typedArrayH.getBoolean(R$styleable.Chip_android_checkable, false));
        F1(typedArrayH.getBoolean(R$styleable.Chip_checkedIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") == null) {
            F1(typedArrayH.getBoolean(R$styleable.Chip_checkedIconEnabled, false));
        }
        A1(c.d(this.f91i0, typedArrayH, R$styleable.Chip_checkedIcon));
        int i8 = R$styleable.Chip_checkedIconTint;
        if (typedArrayH.hasValue(i8)) {
            C1(c.a(this.f91i0, typedArrayH, i8));
        }
        C2(s1.h.c(this.f91i0, typedArrayH, R$styleable.Chip_showMotionSpec));
        s2(s1.h.c(this.f91i0, typedArrayH, R$styleable.Chip_hideMotionSpec));
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

    public void v2(int i3) {
        u2(this.f91i0.getResources().getDimension(i3));
    }

    public Paint.Align w0(Rect rect, PointF pointF) {
        pointF.set(0.0f, 0.0f);
        Paint.Align align = Paint.Align.LEFT;
        if (this.I != null) {
            float fP0 = this.f83a0 + p0() + this.f86d0;
            if (e0.a.f(this) == 0) {
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
        InterfaceC0002a interfaceC0002a = this.G0.get();
        if (interfaceC0002a != null) {
            interfaceC0002a.a();
        }
    }

    public void w2(float f3) {
        if (this.f84b0 != f3) {
            float fP0 = p0();
            this.f84b0 = f3;
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
        boolean z3;
        boolean zOnStateChange = super.onStateChange(iArr);
        ColorStateList colorStateList = this.B;
        int colorForState = colorStateList != null ? colorStateList.getColorForState(iArr, this.f99q0) : 0;
        boolean state = true;
        if (this.f99q0 != colorForState) {
            this.f99q0 = colorForState;
            zOnStateChange = true;
        }
        ColorStateList colorStateList2 = this.C;
        int colorForState2 = colorStateList2 != null ? colorStateList2.getColorForState(iArr, this.f100r0) : 0;
        if (this.f100r0 != colorForState2) {
            this.f100r0 = colorForState2;
            zOnStateChange = true;
        }
        int iE = c2.a.e(colorForState, colorForState2);
        if ((this.f101s0 != iE) | (x() == null)) {
            this.f101s0 = iE;
            X(ColorStateList.valueOf(iE));
            zOnStateChange = true;
        }
        ColorStateList colorStateList3 = this.F;
        int colorForState3 = colorStateList3 != null ? colorStateList3.getColorForState(iArr, this.f102t0) : 0;
        if (this.f102t0 != colorForState3) {
            this.f102t0 = colorForState3;
            zOnStateChange = true;
        }
        int colorForState4 = (this.F0 == null || !l2.b.e(iArr)) ? 0 : this.F0.getColorForState(iArr, this.f103u0);
        if (this.f103u0 != colorForState4) {
            this.f103u0 = colorForState4;
            if (this.E0) {
                zOnStateChange = true;
            }
        }
        int colorForState5 = (this.f98p0.d() == null || this.f98p0.d().f3588b == null) ? 0 : this.f98p0.d().f3588b.getColorForState(iArr, this.f104v0);
        if (this.f104v0 != colorForState5) {
            this.f104v0 = colorForState5;
            zOnStateChange = true;
        }
        boolean z4 = o1(getState(), R.attr.state_checked) && this.U;
        if (this.f105w0 == z4 || this.W == null) {
            z3 = false;
        } else {
            float fP0 = p0();
            this.f105w0 = z4;
            if (fP0 != p0()) {
                zOnStateChange = true;
                z3 = true;
            } else {
                z3 = false;
                zOnStateChange = true;
            }
        }
        ColorStateList colorStateList4 = this.B0;
        int colorForState6 = colorStateList4 != null ? colorStateList4.getColorForState(iArr, this.f106x0) : 0;
        if (this.f106x0 != colorForState6) {
            this.f106x0 = colorForState6;
            this.A0 = e2.a.b(this, this.B0, this.C0);
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
        if (l2.b.f3717a && t1(this.Q)) {
            state |= this.Q.setState(iArr2);
        }
        if (state) {
            invalidateSelf();
        }
        if (z3) {
            w1();
        }
        return state;
    }

    public void x2(int i3) {
        w2(this.f91i0.getResources().getDimension(i3));
    }

    public void y1(boolean z3) {
        if (this.U != z3) {
            this.U = z3;
            float fP0 = p0();
            if (!z3 && this.f105w0) {
                this.f105w0 = false;
            }
            float fP02 = p0();
            invalidateSelf();
            if (fP0 != fP02) {
                w1();
            }
        }
    }

    public void y2(int i3) {
        this.J0 = i3;
    }

    public final void z0(Canvas canvas, Rect rect) {
        if (N2()) {
            o0(rect, this.f95m0);
            RectF rectF = this.f95m0;
            float f3 = rectF.left;
            float f4 = rectF.top;
            canvas.translate(f3, f4);
            this.W.setBounds(0, 0, (int) this.f95m0.width(), (int) this.f95m0.height());
            this.W.draw(canvas);
            canvas.translate(-f3, -f4);
        }
    }

    public void z1(int i3) {
        y1(this.f91i0.getResources().getBoolean(i3));
    }

    public void z2(ColorStateList colorStateList) {
        if (this.H != colorStateList) {
            this.H = colorStateList;
            R2();
            onStateChange(getState());
        }
    }
}
