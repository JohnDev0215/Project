package r5;

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
import b6.c;
import b6.d;
import com.google.android.material.R$styleable;
import e6.h;
import h0.b;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import z5.i;
import z5.k;

/* JADX INFO: compiled from: ChipDrawable.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends h implements b, Drawable.Callback, i.b {
    public static final int[] B = {R.attr.state_enabled};
    public static final ShapeDrawable C = new ShapeDrawable(new OvalShape());
    public int A0;
    public ColorFilter B0;
    public PorterDuffColorFilter C0;
    public ColorStateList D;
    public ColorStateList D0;
    public ColorStateList E;
    public PorterDuff.Mode E0;
    public float F;
    public int[] F0;
    public float G;
    public boolean G0;
    public ColorStateList H;
    public ColorStateList H0;
    public float I;
    public WeakReference<InterfaceC0114a> I0;
    public ColorStateList J;
    public TextUtils.TruncateAt J0;
    public CharSequence K;
    public boolean K0;
    public boolean L;
    public int L0;
    public Drawable M;
    public boolean M0;
    public ColorStateList N;
    public float O;
    public boolean P;
    public boolean Q;
    public Drawable R;
    public Drawable S;
    public ColorStateList T;
    public float U;
    public CharSequence V;
    public boolean W;
    public boolean X;
    public Drawable Y;
    public ColorStateList Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public j5.h f7818a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public j5.h f7819b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public float f7820c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public float f7821d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public float f7822e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public float f7823f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public float f7824g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public float f7825h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public float f7826i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public float f7827j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public final Context f7828k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public final Paint f7829l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public final Paint f7830m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public final Paint.FontMetrics f7831n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public final RectF f7832o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public final PointF f7833p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public final Path f7834q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public final i f7835r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public int f7836s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public int f7837t0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public int f7838u0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public int f7839v0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public int f7840w0;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public int f7841x0;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public boolean f7842y0;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public int f7843z0;

    /* JADX INFO: renamed from: r5.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChipDrawable.java */
    public interface InterfaceC0114a {
        void a();
    }

    public a(Context context, AttributeSet attributeSet, int i8, int i9) {
        super(context, attributeSet, i8, i9);
        this.G = -1.0f;
        this.f7829l0 = new Paint(1);
        this.f7831n0 = new Paint.FontMetrics();
        this.f7832o0 = new RectF();
        this.f7833p0 = new PointF();
        this.f7834q0 = new Path();
        this.A0 = 255;
        this.E0 = PorterDuff.Mode.SRC_IN;
        this.I0 = new WeakReference<>(null);
        N(context);
        this.f7828k0 = context;
        i iVar = new i(this);
        this.f7835r0 = iVar;
        this.K = "";
        iVar.e().density = context.getResources().getDisplayMetrics().density;
        this.f7830m0 = null;
        int[] iArr = B;
        setState(iArr);
        m2(iArr);
        this.K0 = true;
        if (c6.b.f2328a) {
            C.setTint(-1);
        }
    }

    public static boolean o1(int[] iArr, int i8) {
        if (iArr == null) {
            return false;
        }
        for (int i9 : iArr) {
            if (i9 == i8) {
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
        return (dVar == null || (colorStateList = dVar.f2106b) == null || !colorStateList.isStateful()) ? false : true;
    }

    public static a y0(Context context, AttributeSet attributeSet, int i8, int i9) {
        a aVar = new a(context, attributeSet, i8, i9);
        aVar.v1(attributeSet, i8, i9);
        return aVar;
    }

    public final void A0(Canvas canvas, Rect rect) {
        if (this.M0) {
            return;
        }
        this.f7829l0.setColor(this.f7837t0);
        this.f7829l0.setStyle(Paint.Style.FILL);
        this.f7829l0.setColorFilter(m1());
        this.f7832o0.set(rect);
        canvas.drawRoundRect(this.f7832o0, L0(), L0(), this.f7829l0);
    }

    public void A1(Drawable drawable) {
        if (this.Y != drawable) {
            float fP0 = p0();
            this.Y = drawable;
            float fP02 = p0();
            Q2(this.Y);
            n0(this.Y);
            invalidateSelf();
            if (fP0 != fP02) {
                w1();
            }
        }
    }

    public void A2(int i8) {
        z2(m.a.c(this.f7828k0, i8));
    }

    public final void B0(Canvas canvas, Rect rect) {
        if (O2()) {
            o0(rect, this.f7832o0);
            RectF rectF = this.f7832o0;
            float f8 = rectF.left;
            float f9 = rectF.top;
            canvas.translate(f8, f9);
            this.M.setBounds(0, 0, (int) this.f7832o0.width(), (int) this.f7832o0.height());
            this.M.draw(canvas);
            canvas.translate(-f8, -f9);
        }
    }

    public void B1(int i8) {
        A1(m.a.d(this.f7828k0, i8));
    }

    public void B2(boolean z7) {
        this.K0 = z7;
    }

    public final void C0(Canvas canvas, Rect rect) {
        if (this.I <= 0.0f || this.M0) {
            return;
        }
        this.f7829l0.setColor(this.f7839v0);
        this.f7829l0.setStyle(Paint.Style.STROKE);
        if (!this.M0) {
            this.f7829l0.setColorFilter(m1());
        }
        RectF rectF = this.f7832o0;
        float f8 = rect.left;
        float f9 = this.I;
        rectF.set(f8 + (f9 / 2.0f), rect.top + (f9 / 2.0f), rect.right - (f9 / 2.0f), rect.bottom - (f9 / 2.0f));
        float f10 = this.G - (this.I / 2.0f);
        canvas.drawRoundRect(this.f7832o0, f10, f10, this.f7829l0);
    }

    public void C1(ColorStateList colorStateList) {
        if (this.Z != colorStateList) {
            this.Z = colorStateList;
            if (x0()) {
                h0.a.o(this.Y, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void C2(j5.h hVar) {
        this.f7818a0 = hVar;
    }

    public final void D0(Canvas canvas, Rect rect) {
        if (this.M0) {
            return;
        }
        this.f7829l0.setColor(this.f7836s0);
        this.f7829l0.setStyle(Paint.Style.FILL);
        this.f7832o0.set(rect);
        canvas.drawRoundRect(this.f7832o0, L0(), L0(), this.f7829l0);
    }

    public void D1(int i8) {
        C1(m.a.c(this.f7828k0, i8));
    }

    public void D2(int i8) {
        C2(j5.h.d(this.f7828k0, i8));
    }

    public final void E0(Canvas canvas, Rect rect) {
        if (P2()) {
            r0(rect, this.f7832o0);
            RectF rectF = this.f7832o0;
            float f8 = rectF.left;
            float f9 = rectF.top;
            canvas.translate(f8, f9);
            this.R.setBounds(0, 0, (int) this.f7832o0.width(), (int) this.f7832o0.height());
            if (c6.b.f2328a) {
                this.S.setBounds(this.R.getBounds());
                this.S.jumpToCurrentState();
                this.S.draw(canvas);
            } else {
                this.R.draw(canvas);
            }
            canvas.translate(-f8, -f9);
        }
    }

    public void E1(int i8) {
        F1(this.f7828k0.getResources().getBoolean(i8));
    }

    public void E2(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        if (TextUtils.equals(this.K, charSequence)) {
            return;
        }
        this.K = charSequence;
        this.f7835r0.i(true);
        invalidateSelf();
        w1();
    }

    public final void F0(Canvas canvas, Rect rect) {
        this.f7829l0.setColor(this.f7840w0);
        this.f7829l0.setStyle(Paint.Style.FILL);
        this.f7832o0.set(rect);
        if (!this.M0) {
            canvas.drawRoundRect(this.f7832o0, L0(), L0(), this.f7829l0);
        } else {
            h(new RectF(rect), this.f7834q0);
            super.p(canvas, this.f7829l0, this.f7834q0, u());
        }
    }

    public void F1(boolean z7) {
        if (this.X != z7) {
            boolean zN2 = N2();
            this.X = z7;
            boolean zN22 = N2();
            if (zN2 != zN22) {
                if (zN22) {
                    n0(this.Y);
                } else {
                    Q2(this.Y);
                }
                invalidateSelf();
                w1();
            }
        }
    }

    public void F2(d dVar) {
        this.f7835r0.h(dVar, this.f7828k0);
    }

    public final void G0(Canvas canvas, Rect rect) {
        Paint paint = this.f7830m0;
        if (paint != null) {
            paint.setColor(g0.a.d(-16777216, 127));
            canvas.drawRect(rect, this.f7830m0);
            if (O2() || N2()) {
                o0(rect, this.f7832o0);
                canvas.drawRect(this.f7832o0, this.f7830m0);
            }
            if (this.K != null) {
                canvas.drawLine(rect.left, rect.exactCenterY(), rect.right, rect.exactCenterY(), this.f7830m0);
            }
            if (P2()) {
                r0(rect, this.f7832o0);
                canvas.drawRect(this.f7832o0, this.f7830m0);
            }
            this.f7830m0.setColor(g0.a.d(-65536, 127));
            q0(rect, this.f7832o0);
            canvas.drawRect(this.f7832o0, this.f7830m0);
            this.f7830m0.setColor(g0.a.d(-16711936, 127));
            s0(rect, this.f7832o0);
            canvas.drawRect(this.f7832o0, this.f7830m0);
        }
    }

    public void G1(ColorStateList colorStateList) {
        if (this.E != colorStateList) {
            this.E = colorStateList;
            onStateChange(getState());
        }
    }

    public void G2(int i8) {
        F2(new d(this.f7828k0, i8));
    }

    public final void H0(Canvas canvas, Rect rect) {
        if (this.K != null) {
            Paint.Align alignW0 = w0(rect, this.f7833p0);
            u0(rect, this.f7832o0);
            if (this.f7835r0.d() != null) {
                this.f7835r0.e().drawableState = getState();
                this.f7835r0.j(this.f7828k0);
            }
            this.f7835r0.e().setTextAlign(alignW0);
            int iSave = 0;
            boolean z7 = Math.round(this.f7835r0.f(i1().toString())) > Math.round(this.f7832o0.width());
            if (z7) {
                iSave = canvas.save();
                canvas.clipRect(this.f7832o0);
            }
            CharSequence charSequenceEllipsize = this.K;
            if (z7 && this.J0 != null) {
                charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, this.f7835r0.e(), this.f7832o0.width(), this.J0);
            }
            CharSequence charSequence = charSequenceEllipsize;
            int length = charSequence.length();
            PointF pointF = this.f7833p0;
            canvas.drawText(charSequence, 0, length, pointF.x, pointF.y, this.f7835r0.e());
            if (z7) {
                canvas.restoreToCount(iSave);
            }
        }
    }

    public void H1(int i8) {
        G1(m.a.c(this.f7828k0, i8));
    }

    public void H2(float f8) {
        if (this.f7824g0 != f8) {
            this.f7824g0 = f8;
            invalidateSelf();
            w1();
        }
    }

    public Drawable I0() {
        return this.Y;
    }

    @Deprecated
    public void I1(float f8) {
        if (this.G != f8) {
            this.G = f8;
            setShapeAppearanceModel(D().w(f8));
        }
    }

    public void I2(int i8) {
        H2(this.f7828k0.getResources().getDimension(i8));
    }

    public ColorStateList J0() {
        return this.Z;
    }

    @Deprecated
    public void J1(int i8) {
        I1(this.f7828k0.getResources().getDimension(i8));
    }

    public void J2(float f8) {
        if (this.f7823f0 != f8) {
            this.f7823f0 = f8;
            invalidateSelf();
            w1();
        }
    }

    public ColorStateList K0() {
        return this.E;
    }

    public void K1(float f8) {
        if (this.f7827j0 != f8) {
            this.f7827j0 = f8;
            invalidateSelf();
            w1();
        }
    }

    public void K2(int i8) {
        J2(this.f7828k0.getResources().getDimension(i8));
    }

    public float L0() {
        return this.M0 ? G() : this.G;
    }

    public void L1(int i8) {
        K1(this.f7828k0.getResources().getDimension(i8));
    }

    public void L2(boolean z7) {
        if (this.G0 != z7) {
            this.G0 = z7;
            R2();
            onStateChange(getState());
        }
    }

    public float M0() {
        return this.f7827j0;
    }

    public void M1(Drawable drawable) {
        Drawable drawableN0 = N0();
        if (drawableN0 != drawable) {
            float fP0 = p0();
            this.M = drawable != null ? h0.a.r(drawable).mutate() : null;
            float fP02 = p0();
            Q2(drawableN0);
            if (O2()) {
                n0(this.M);
            }
            invalidateSelf();
            if (fP0 != fP02) {
                w1();
            }
        }
    }

    public boolean M2() {
        return this.K0;
    }

    public Drawable N0() {
        Drawable drawable = this.M;
        if (drawable != null) {
            return h0.a.q(drawable);
        }
        return null;
    }

    public void N1(int i8) {
        M1(m.a.d(this.f7828k0, i8));
    }

    public final boolean N2() {
        return this.X && this.Y != null && this.f7842y0;
    }

    public float O0() {
        return this.O;
    }

    public void O1(float f8) {
        if (this.O != f8) {
            float fP0 = p0();
            this.O = f8;
            float fP02 = p0();
            invalidateSelf();
            if (fP0 != fP02) {
                w1();
            }
        }
    }

    public final boolean O2() {
        return this.L && this.M != null;
    }

    public ColorStateList P0() {
        return this.N;
    }

    public void P1(int i8) {
        O1(this.f7828k0.getResources().getDimension(i8));
    }

    public final boolean P2() {
        return this.Q && this.R != null;
    }

    public float Q0() {
        return this.F;
    }

    public void Q1(ColorStateList colorStateList) {
        this.P = true;
        if (this.N != colorStateList) {
            this.N = colorStateList;
            if (O2()) {
                h0.a.o(this.M, colorStateList);
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
        return this.f7820c0;
    }

    public void R1(int i8) {
        Q1(m.a.c(this.f7828k0, i8));
    }

    public final void R2() {
        this.H0 = this.G0 ? c6.b.d(this.J) : null;
    }

    public ColorStateList S0() {
        return this.H;
    }

    public void S1(int i8) {
        T1(this.f7828k0.getResources().getBoolean(i8));
    }

    @TargetApi(21)
    public final void S2() {
        this.S = new RippleDrawable(c6.b.d(g1()), this.R, C);
    }

    public float T0() {
        return this.I;
    }

    public void T1(boolean z7) {
        if (this.L != z7) {
            boolean zO2 = O2();
            this.L = z7;
            boolean zO22 = O2();
            if (zO2 != zO22) {
                if (zO22) {
                    n0(this.M);
                } else {
                    Q2(this.M);
                }
                invalidateSelf();
                w1();
            }
        }
    }

    public Drawable U0() {
        Drawable drawable = this.R;
        if (drawable != null) {
            return h0.a.q(drawable);
        }
        return null;
    }

    public void U1(float f8) {
        if (this.F != f8) {
            this.F = f8;
            invalidateSelf();
            w1();
        }
    }

    public CharSequence V0() {
        return this.V;
    }

    public void V1(int i8) {
        U1(this.f7828k0.getResources().getDimension(i8));
    }

    public float W0() {
        return this.f7826i0;
    }

    public void W1(float f8) {
        if (this.f7820c0 != f8) {
            this.f7820c0 = f8;
            invalidateSelf();
            w1();
        }
    }

    public float X0() {
        return this.U;
    }

    public void X1(int i8) {
        W1(this.f7828k0.getResources().getDimension(i8));
    }

    public float Y0() {
        return this.f7825h0;
    }

    public void Y1(ColorStateList colorStateList) {
        if (this.H != colorStateList) {
            this.H = colorStateList;
            if (this.M0) {
                i0(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public int[] Z0() {
        return this.F0;
    }

    public void Z1(int i8) {
        Y1(m.a.c(this.f7828k0, i8));
    }

    @Override // z5.i.b
    public void a() {
        w1();
        invalidateSelf();
    }

    public ColorStateList a1() {
        return this.T;
    }

    public void a2(float f8) {
        if (this.I != f8) {
            this.I = f8;
            this.f7829l0.setStrokeWidth(f8);
            if (this.M0) {
                super.j0(f8);
            }
            invalidateSelf();
        }
    }

    public void b1(RectF rectF) {
        s0(getBounds(), rectF);
    }

    public void b2(int i8) {
        a2(this.f7828k0.getResources().getDimension(i8));
    }

    public TextUtils.TruncateAt c1() {
        return this.J0;
    }

    public final void c2(ColorStateList colorStateList) {
        if (this.D != colorStateList) {
            this.D = colorStateList;
            onStateChange(getState());
        }
    }

    public j5.h d1() {
        return this.f7819b0;
    }

    public void d2(Drawable drawable) {
        Drawable drawableU0 = U0();
        if (drawableU0 != drawable) {
            float fT0 = t0();
            this.R = drawable != null ? h0.a.r(drawable).mutate() : null;
            if (c6.b.f2328a) {
                S2();
            }
            float fT02 = t0();
            Q2(drawableU0);
            if (P2()) {
                n0(this.R);
            }
            invalidateSelf();
            if (fT0 != fT02) {
                w1();
            }
        }
    }

    @Override // e6.h, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds.isEmpty() || getAlpha() == 0) {
            return;
        }
        int i8 = this.A0;
        int iA = i8 < 255 ? p5.a.a(canvas, bounds.left, bounds.top, bounds.right, bounds.bottom, i8) : 0;
        D0(canvas, bounds);
        A0(canvas, bounds);
        if (this.M0) {
            super.draw(canvas);
        }
        C0(canvas, bounds);
        F0(canvas, bounds);
        B0(canvas, bounds);
        z0(canvas, bounds);
        if (this.K0) {
            H0(canvas, bounds);
        }
        E0(canvas, bounds);
        G0(canvas, bounds);
        if (this.A0 < 255) {
            canvas.restoreToCount(iA);
        }
    }

    public float e1() {
        return this.f7822e0;
    }

    public void e2(CharSequence charSequence) {
        if (this.V != charSequence) {
            this.V = m0.a.c().h(charSequence);
            invalidateSelf();
        }
    }

    public float f1() {
        return this.f7821d0;
    }

    public void f2(float f8) {
        if (this.f7826i0 != f8) {
            this.f7826i0 = f8;
            invalidateSelf();
            if (P2()) {
                w1();
            }
        }
    }

    public ColorStateList g1() {
        return this.J;
    }

    public void g2(int i8) {
        f2(this.f7828k0.getResources().getDimension(i8));
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.A0;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.B0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.F;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return Math.min(Math.round(this.f7820c0 + p0() + this.f7823f0 + this.f7835r0.f(i1().toString()) + this.f7824g0 + t0() + this.f7827j0), this.L0);
    }

    @Override // e6.h, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // e6.h, android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.M0) {
            super.getOutline(outline);
            return;
        }
        Rect bounds = getBounds();
        if (bounds.isEmpty()) {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), getIntrinsicHeight(), this.G);
        } else {
            outline.setRoundRect(bounds, this.G);
        }
        outline.setAlpha(getAlpha() / 255.0f);
    }

    public j5.h h1() {
        return this.f7818a0;
    }

    public void h2(int i8) {
        d2(m.a.d(this.f7828k0, i8));
    }

    public CharSequence i1() {
        return this.K;
    }

    public void i2(float f8) {
        if (this.U != f8) {
            this.U = f8;
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

    @Override // e6.h, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return s1(this.D) || s1(this.E) || s1(this.H) || (this.G0 && s1(this.H0)) || u1(this.f7835r0.d()) || x0() || t1(this.M) || t1(this.Y) || s1(this.D0);
    }

    public d j1() {
        return this.f7835r0.d();
    }

    public void j2(int i8) {
        i2(this.f7828k0.getResources().getDimension(i8));
    }

    public float k1() {
        return this.f7824g0;
    }

    public void k2(float f8) {
        if (this.f7825h0 != f8) {
            this.f7825h0 = f8;
            invalidateSelf();
            if (P2()) {
                w1();
            }
        }
    }

    public float l1() {
        return this.f7823f0;
    }

    public void l2(int i8) {
        k2(this.f7828k0.getResources().getDimension(i8));
    }

    public final ColorFilter m1() {
        ColorFilter colorFilter = this.B0;
        return colorFilter != null ? colorFilter : this.C0;
    }

    public boolean m2(int[] iArr) {
        if (Arrays.equals(this.F0, iArr)) {
            return false;
        }
        this.F0 = iArr;
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
        h0.a.m(drawable, h0.a.f(this));
        drawable.setLevel(getLevel());
        drawable.setVisible(isVisible(), false);
        if (drawable == this.R) {
            if (drawable.isStateful()) {
                drawable.setState(Z0());
            }
            h0.a.o(drawable, this.T);
            return;
        }
        if (drawable.isStateful()) {
            drawable.setState(getState());
        }
        Drawable drawable2 = this.M;
        if (drawable == drawable2 && this.P) {
            h0.a.o(drawable2, this.N);
        }
    }

    public boolean n1() {
        return this.G0;
    }

    public void n2(ColorStateList colorStateList) {
        if (this.T != colorStateList) {
            this.T = colorStateList;
            if (P2()) {
                h0.a.o(this.R, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void o0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (O2() || N2()) {
            float f8 = this.f7820c0 + this.f7821d0;
            if (h0.a.f(this) == 0) {
                float f9 = rect.left + f8;
                rectF.left = f9;
                rectF.right = f9 + this.O;
            } else {
                float f10 = rect.right - f8;
                rectF.right = f10;
                rectF.left = f10 - this.O;
            }
            float fExactCenterY = rect.exactCenterY();
            float f11 = this.O;
            float f12 = fExactCenterY - (f11 / 2.0f);
            rectF.top = f12;
            rectF.bottom = f12 + f11;
        }
    }

    public void o2(int i8) {
        n2(m.a.c(this.f7828k0, i8));
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i8) {
        boolean zOnLayoutDirectionChanged = super.onLayoutDirectionChanged(i8);
        if (O2()) {
            zOnLayoutDirectionChanged |= h0.a.m(this.M, i8);
        }
        if (N2()) {
            zOnLayoutDirectionChanged |= h0.a.m(this.Y, i8);
        }
        if (P2()) {
            zOnLayoutDirectionChanged |= h0.a.m(this.R, i8);
        }
        if (!zOnLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLevelChange(int i8) {
        boolean zOnLevelChange = super.onLevelChange(i8);
        if (O2()) {
            zOnLevelChange |= this.M.setLevel(i8);
        }
        if (N2()) {
            zOnLevelChange |= this.Y.setLevel(i8);
        }
        if (P2()) {
            zOnLevelChange |= this.R.setLevel(i8);
        }
        if (zOnLevelChange) {
            invalidateSelf();
        }
        return zOnLevelChange;
    }

    @Override // e6.h, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        if (this.M0) {
            super.onStateChange(iArr);
        }
        return x1(iArr, Z0());
    }

    public float p0() {
        if (O2() || N2()) {
            return this.f7821d0 + this.O + this.f7822e0;
        }
        return 0.0f;
    }

    public boolean p1() {
        return this.W;
    }

    public void p2(boolean z7) {
        if (this.Q != z7) {
            boolean zP2 = P2();
            this.Q = z7;
            boolean zP22 = P2();
            if (zP2 != zP22) {
                if (zP22) {
                    n0(this.R);
                } else {
                    Q2(this.R);
                }
                invalidateSelf();
                w1();
            }
        }
    }

    public final void q0(Rect rect, RectF rectF) {
        rectF.set(rect);
        if (P2()) {
            float f8 = this.f7827j0 + this.f7826i0 + this.U + this.f7825h0 + this.f7824g0;
            if (h0.a.f(this) == 0) {
                rectF.right = rect.right - f8;
            } else {
                rectF.left = rect.left + f8;
            }
        }
    }

    public boolean q1() {
        return t1(this.R);
    }

    public void q2(InterfaceC0114a interfaceC0114a) {
        this.I0 = new WeakReference<>(interfaceC0114a);
    }

    public final void r0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (P2()) {
            float f8 = this.f7827j0 + this.f7826i0;
            if (h0.a.f(this) == 0) {
                float f9 = rect.right - f8;
                rectF.right = f9;
                rectF.left = f9 - this.U;
            } else {
                float f10 = rect.left + f8;
                rectF.left = f10;
                rectF.right = f10 + this.U;
            }
            float fExactCenterY = rect.exactCenterY();
            float f11 = this.U;
            float f12 = fExactCenterY - (f11 / 2.0f);
            rectF.top = f12;
            rectF.bottom = f12 + f11;
        }
    }

    public boolean r1() {
        return this.Q;
    }

    public void r2(TextUtils.TruncateAt truncateAt) {
        this.J0 = truncateAt;
    }

    public final void s0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (P2()) {
            float f8 = this.f7827j0 + this.f7826i0 + this.U + this.f7825h0 + this.f7824g0;
            if (h0.a.f(this) == 0) {
                float f9 = rect.right;
                rectF.right = f9;
                rectF.left = f9 - f8;
            } else {
                int i8 = rect.left;
                rectF.left = i8;
                rectF.right = i8 + f8;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    public void s2(j5.h hVar) {
        this.f7819b0 = hVar;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j8) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j8);
        }
    }

    @Override // e6.h, android.graphics.drawable.Drawable
    public void setAlpha(int i8) {
        if (this.A0 != i8) {
            this.A0 = i8;
            invalidateSelf();
        }
    }

    @Override // e6.h, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.B0 != colorFilter) {
            this.B0 = colorFilter;
            invalidateSelf();
        }
    }

    @Override // e6.h, android.graphics.drawable.Drawable, h0.b
    public void setTintList(ColorStateList colorStateList) {
        if (this.D0 != colorStateList) {
            this.D0 = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // e6.h, android.graphics.drawable.Drawable, h0.b
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.E0 != mode) {
            this.E0 = mode;
            this.C0 = v5.a.b(this, this.D0, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z7, boolean z8) {
        boolean visible = super.setVisible(z7, z8);
        if (O2()) {
            visible |= this.M.setVisible(z7, z8);
        }
        if (N2()) {
            visible |= this.Y.setVisible(z7, z8);
        }
        if (P2()) {
            visible |= this.R.setVisible(z7, z8);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    public float t0() {
        if (P2()) {
            return this.f7825h0 + this.U + this.f7826i0;
        }
        return 0.0f;
    }

    public void t2(int i8) {
        s2(j5.h.d(this.f7828k0, i8));
    }

    public final void u0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (this.K != null) {
            float fP0 = this.f7820c0 + p0() + this.f7823f0;
            float fT0 = this.f7827j0 + t0() + this.f7824g0;
            if (h0.a.f(this) == 0) {
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

    public void u2(float f8) {
        if (this.f7822e0 != f8) {
            float fP0 = p0();
            this.f7822e0 = f8;
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
        this.f7835r0.e().getFontMetrics(this.f7831n0);
        Paint.FontMetrics fontMetrics = this.f7831n0;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    public final void v1(AttributeSet attributeSet, int i8, int i9) {
        TypedArray typedArrayH = k.h(this.f7828k0, attributeSet, R$styleable.Chip, i8, i9, new int[0]);
        this.M0 = typedArrayH.hasValue(R$styleable.Chip_shapeAppearance);
        c2(c.a(this.f7828k0, typedArrayH, R$styleable.Chip_chipSurfaceColor));
        G1(c.a(this.f7828k0, typedArrayH, R$styleable.Chip_chipBackgroundColor));
        U1(typedArrayH.getDimension(R$styleable.Chip_chipMinHeight, 0.0f));
        int i10 = R$styleable.Chip_chipCornerRadius;
        if (typedArrayH.hasValue(i10)) {
            I1(typedArrayH.getDimension(i10, 0.0f));
        }
        Y1(c.a(this.f7828k0, typedArrayH, R$styleable.Chip_chipStrokeColor));
        a2(typedArrayH.getDimension(R$styleable.Chip_chipStrokeWidth, 0.0f));
        z2(c.a(this.f7828k0, typedArrayH, R$styleable.Chip_rippleColor));
        E2(typedArrayH.getText(R$styleable.Chip_android_text));
        F2(c.f(this.f7828k0, typedArrayH, R$styleable.Chip_android_textAppearance));
        int i11 = typedArrayH.getInt(R$styleable.Chip_android_ellipsize, 0);
        if (i11 == 1) {
            r2(TextUtils.TruncateAt.START);
        } else if (i11 == 2) {
            r2(TextUtils.TruncateAt.MIDDLE);
        } else if (i11 == 3) {
            r2(TextUtils.TruncateAt.END);
        }
        T1(typedArrayH.getBoolean(R$styleable.Chip_chipIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") == null) {
            T1(typedArrayH.getBoolean(R$styleable.Chip_chipIconEnabled, false));
        }
        M1(c.d(this.f7828k0, typedArrayH, R$styleable.Chip_chipIcon));
        int i12 = R$styleable.Chip_chipIconTint;
        if (typedArrayH.hasValue(i12)) {
            Q1(c.a(this.f7828k0, typedArrayH, i12));
        }
        O1(typedArrayH.getDimension(R$styleable.Chip_chipIconSize, 0.0f));
        p2(typedArrayH.getBoolean(R$styleable.Chip_closeIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") == null) {
            p2(typedArrayH.getBoolean(R$styleable.Chip_closeIconEnabled, false));
        }
        d2(c.d(this.f7828k0, typedArrayH, R$styleable.Chip_closeIcon));
        n2(c.a(this.f7828k0, typedArrayH, R$styleable.Chip_closeIconTint));
        i2(typedArrayH.getDimension(R$styleable.Chip_closeIconSize, 0.0f));
        y1(typedArrayH.getBoolean(R$styleable.Chip_android_checkable, false));
        F1(typedArrayH.getBoolean(R$styleable.Chip_checkedIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") == null) {
            F1(typedArrayH.getBoolean(R$styleable.Chip_checkedIconEnabled, false));
        }
        A1(c.d(this.f7828k0, typedArrayH, R$styleable.Chip_checkedIcon));
        int i13 = R$styleable.Chip_checkedIconTint;
        if (typedArrayH.hasValue(i13)) {
            C1(c.a(this.f7828k0, typedArrayH, i13));
        }
        C2(j5.h.c(this.f7828k0, typedArrayH, R$styleable.Chip_showMotionSpec));
        s2(j5.h.c(this.f7828k0, typedArrayH, R$styleable.Chip_hideMotionSpec));
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

    public void v2(int i8) {
        u2(this.f7828k0.getResources().getDimension(i8));
    }

    public Paint.Align w0(Rect rect, PointF pointF) {
        pointF.set(0.0f, 0.0f);
        Paint.Align align = Paint.Align.LEFT;
        if (this.K != null) {
            float fP0 = this.f7820c0 + p0() + this.f7823f0;
            if (h0.a.f(this) == 0) {
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
        InterfaceC0114a interfaceC0114a = this.I0.get();
        if (interfaceC0114a != null) {
            interfaceC0114a.a();
        }
    }

    public void w2(float f8) {
        if (this.f7821d0 != f8) {
            float fP0 = p0();
            this.f7821d0 = f8;
            float fP02 = p0();
            invalidateSelf();
            if (fP0 != fP02) {
                w1();
            }
        }
    }

    public final boolean x0() {
        return this.X && this.Y != null && this.W;
    }

    public final boolean x1(int[] iArr, int[] iArr2) {
        boolean z7;
        boolean zOnStateChange = super.onStateChange(iArr);
        ColorStateList colorStateList = this.D;
        int colorForState = colorStateList != null ? colorStateList.getColorForState(iArr, this.f7836s0) : 0;
        boolean state = true;
        if (this.f7836s0 != colorForState) {
            this.f7836s0 = colorForState;
            zOnStateChange = true;
        }
        ColorStateList colorStateList2 = this.E;
        int colorForState2 = colorStateList2 != null ? colorStateList2.getColorForState(iArr, this.f7837t0) : 0;
        if (this.f7837t0 != colorForState2) {
            this.f7837t0 = colorForState2;
            zOnStateChange = true;
        }
        int iE = t5.a.e(colorForState, colorForState2);
        if ((this.f7838u0 != iE) | (x() == null)) {
            this.f7838u0 = iE;
            X(ColorStateList.valueOf(iE));
            zOnStateChange = true;
        }
        ColorStateList colorStateList3 = this.H;
        int colorForState3 = colorStateList3 != null ? colorStateList3.getColorForState(iArr, this.f7839v0) : 0;
        if (this.f7839v0 != colorForState3) {
            this.f7839v0 = colorForState3;
            zOnStateChange = true;
        }
        int colorForState4 = (this.H0 == null || !c6.b.e(iArr)) ? 0 : this.H0.getColorForState(iArr, this.f7840w0);
        if (this.f7840w0 != colorForState4) {
            this.f7840w0 = colorForState4;
            if (this.G0) {
                zOnStateChange = true;
            }
        }
        int colorForState5 = (this.f7835r0.d() == null || this.f7835r0.d().f2106b == null) ? 0 : this.f7835r0.d().f2106b.getColorForState(iArr, this.f7841x0);
        if (this.f7841x0 != colorForState5) {
            this.f7841x0 = colorForState5;
            zOnStateChange = true;
        }
        boolean z8 = o1(getState(), R.attr.state_checked) && this.W;
        if (this.f7842y0 == z8 || this.Y == null) {
            z7 = false;
        } else {
            float fP0 = p0();
            this.f7842y0 = z8;
            if (fP0 != p0()) {
                zOnStateChange = true;
                z7 = true;
            } else {
                z7 = false;
                zOnStateChange = true;
            }
        }
        ColorStateList colorStateList4 = this.D0;
        int colorForState6 = colorStateList4 != null ? colorStateList4.getColorForState(iArr, this.f7843z0) : 0;
        if (this.f7843z0 != colorForState6) {
            this.f7843z0 = colorForState6;
            this.C0 = v5.a.b(this, this.D0, this.E0);
        } else {
            state = zOnStateChange;
        }
        if (t1(this.M)) {
            state |= this.M.setState(iArr);
        }
        if (t1(this.Y)) {
            state |= this.Y.setState(iArr);
        }
        if (t1(this.R)) {
            int[] iArr3 = new int[iArr.length + iArr2.length];
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
            state |= this.R.setState(iArr3);
        }
        if (c6.b.f2328a && t1(this.S)) {
            state |= this.S.setState(iArr2);
        }
        if (state) {
            invalidateSelf();
        }
        if (z7) {
            w1();
        }
        return state;
    }

    public void x2(int i8) {
        w2(this.f7828k0.getResources().getDimension(i8));
    }

    public void y1(boolean z7) {
        if (this.W != z7) {
            this.W = z7;
            float fP0 = p0();
            if (!z7 && this.f7842y0) {
                this.f7842y0 = false;
            }
            float fP02 = p0();
            invalidateSelf();
            if (fP0 != fP02) {
                w1();
            }
        }
    }

    public void y2(int i8) {
        this.L0 = i8;
    }

    public final void z0(Canvas canvas, Rect rect) {
        if (N2()) {
            o0(rect, this.f7832o0);
            RectF rectF = this.f7832o0;
            float f8 = rectF.left;
            float f9 = rectF.top;
            canvas.translate(f8, f9);
            this.Y.setBounds(0, 0, (int) this.f7832o0.width(), (int) this.f7832o0.height());
            this.Y.draw(canvas);
            canvas.translate(-f8, -f9);
        }
    }

    public void z1(int i8) {
        y1(this.f7828k0.getResources().getBoolean(i8));
    }

    public void z2(ColorStateList colorStateList) {
        if (this.J != colorStateList) {
            this.J = colorStateList;
            R2();
            onStateChange(getState());
        }
    }
}
