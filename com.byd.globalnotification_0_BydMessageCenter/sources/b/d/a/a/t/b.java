package b.d.a.a.t;

import a.b.k.l;
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
import android.util.TypedValue;
import b.d.a.a.e0.g;
import b.d.a.a.e0.j;
import b.d.a.a.z.i;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class b extends g implements Drawable.Callback, i.b {
    public static final int[] I0 = {R.attr.state_enabled};
    public static final ShapeDrawable J0 = new ShapeDrawable(new OvalShape());
    public float A;
    public int[] A0;
    public float B;
    public boolean B0;
    public ColorStateList C;
    public ColorStateList C0;
    public float D;
    public WeakReference<a> D0;
    public ColorStateList E;
    public TextUtils.TruncateAt E0;
    public CharSequence F;
    public boolean F0;
    public boolean G;
    public int G0;
    public Drawable H;
    public boolean H0;
    public ColorStateList I;
    public float J;
    public boolean K;
    public boolean L;
    public Drawable M;
    public Drawable N;
    public ColorStateList O;
    public float P;
    public CharSequence Q;
    public boolean R;
    public boolean S;
    public Drawable T;
    public ColorStateList U;
    public b.d.a.a.l.g V;
    public b.d.a.a.l.g W;
    public float X;
    public float Y;
    public float Z;
    public float a0;
    public float b0;
    public float c0;
    public float d0;
    public float e0;
    public final Context f0;
    public final Paint g0;
    public final Paint h0;
    public final Paint.FontMetrics i0;
    public final RectF j0;
    public final PointF k0;
    public final Path l0;
    public final i m0;
    public int n0;
    public int o0;
    public int p0;
    public int q0;
    public int r0;
    public int s0;
    public boolean t0;
    public int u0;
    public int v0;
    public ColorFilter w0;
    public PorterDuffColorFilter x0;
    public ColorStateList y;
    public ColorStateList y0;
    public ColorStateList z;
    public PorterDuff.Mode z0;

    public interface a {
        void a();
    }

    public b(Context context, AttributeSet attributeSet, int i, int i2) {
        super(j.b(context, attributeSet, i, i2).a());
        this.B = -1.0f;
        this.g0 = new Paint(1);
        this.i0 = new Paint.FontMetrics();
        this.j0 = new RectF();
        this.k0 = new PointF();
        this.l0 = new Path();
        this.v0 = 255;
        this.z0 = PorterDuff.Mode.SRC_IN;
        Paint paint = null;
        this.D0 = new WeakReference<>(null);
        this.f1828a.f1836b = new b.d.a.a.w.a(context);
        x();
        this.f0 = context;
        i iVar = new i(this);
        this.m0 = iVar;
        this.F = "";
        iVar.f2181a.density = context.getResources().getDisplayMetrics().density;
        this.h0 = null;
        if (0 != 0) {
            paint.setStyle(Paint.Style.STROKE);
        }
        setState(I0);
        e0(I0);
        this.F0 = true;
        boolean z = b.d.a.a.c0.a.f1815a;
        J0.setTint(-1);
    }

    public static boolean H(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    public static boolean I(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    public float A() {
        if (!q0() && !p0()) {
            return 0.0f;
        }
        return G() + this.Y + this.Z;
    }

    public final void B(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (r0()) {
            float f2 = this.e0 + this.d0;
            if (getLayoutDirection() == 0) {
                float f3 = rect.right - f2;
                rectF.right = f3;
                rectF.left = f3 - this.P;
            } else {
                float f4 = rect.left + f2;
                rectF.left = f4;
                rectF.right = f4 + this.P;
            }
            float fExactCenterY = rect.exactCenterY();
            float f5 = this.P;
            float f6 = fExactCenterY - (f5 / 2.0f);
            rectF.top = f6;
            rectF.bottom = f6 + f5;
        }
    }

    public final void C(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (r0()) {
            float f2 = this.e0 + this.d0 + this.P + this.c0 + this.b0;
            if (getLayoutDirection() == 0) {
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

    public float D() {
        if (r0()) {
            return this.c0 + this.P + this.d0;
        }
        return 0.0f;
    }

    public float E() {
        return this.H0 ? m() : this.B;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Drawable F() {
        Drawable drawable = this.M;
        if (drawable != 0) {
            return drawable instanceof a.h.e.f.a ? ((a.h.e.f.a) drawable).a() : drawable;
        }
        return null;
    }

    public final float G() {
        return (this.J > 0.0f || (this.t0 ? this.T : this.H) == null) ? this.J : r0.getIntrinsicWidth();
    }

    public void J() {
        a aVar = this.D0.get();
        if (aVar != null) {
            aVar.a();
        }
    }

    public final boolean K(int[] iArr, int[] iArr2) {
        boolean z;
        boolean z2;
        ColorStateList colorStateList;
        boolean zOnStateChange = super.onStateChange(iArr);
        ColorStateList colorStateList2 = this.y;
        int iE = e(colorStateList2 != null ? colorStateList2.getColorForState(iArr, this.n0) : 0);
        boolean state = true;
        if (this.n0 != iE) {
            this.n0 = iE;
            zOnStateChange = true;
        }
        ColorStateList colorStateList3 = this.z;
        int iE2 = e(colorStateList3 != null ? colorStateList3.getColorForState(iArr, this.o0) : 0);
        if (this.o0 != iE2) {
            this.o0 = iE2;
            zOnStateChange = true;
        }
        int iA = a.h.e.a.a(iE2, iE);
        if ((this.p0 != iA) | (this.f1828a.f1838d == null)) {
            this.p0 = iA;
            q(ColorStateList.valueOf(iA));
            zOnStateChange = true;
        }
        ColorStateList colorStateList4 = this.C;
        int colorForState = colorStateList4 != null ? colorStateList4.getColorForState(iArr, this.q0) : 0;
        if (this.q0 != colorForState) {
            this.q0 = colorForState;
            zOnStateChange = true;
        }
        int colorForState2 = (this.C0 == null || !b.d.a.a.c0.a.b(iArr)) ? 0 : this.C0.getColorForState(iArr, this.r0);
        if (this.r0 != colorForState2) {
            this.r0 = colorForState2;
            if (this.B0) {
                zOnStateChange = true;
            }
        }
        b.d.a.a.b0.b bVar = this.m0.f2186f;
        int colorForState3 = (bVar == null || (colorStateList = bVar.f1804a) == null) ? 0 : colorStateList.getColorForState(iArr, this.s0);
        if (this.s0 != colorForState3) {
            this.s0 = colorForState3;
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
        boolean z3 = z && this.R;
        if (this.t0 == z3 || this.T == null) {
            z2 = false;
        } else {
            float fA = A();
            this.t0 = z3;
            if (fA != A()) {
                zOnStateChange = true;
                z2 = true;
            } else {
                z2 = false;
                zOnStateChange = true;
            }
        }
        ColorStateList colorStateList5 = this.y0;
        int colorForState4 = colorStateList5 != null ? colorStateList5.getColorForState(iArr, this.u0) : 0;
        if (this.u0 != colorForState4) {
            this.u0 = colorForState4;
            this.x0 = l.i.E0(this, this.y0, this.z0);
        } else {
            state = zOnStateChange;
        }
        if (I(this.H)) {
            state |= this.H.setState(iArr);
        }
        if (I(this.T)) {
            state |= this.T.setState(iArr);
        }
        if (I(this.M)) {
            int[] iArr3 = new int[iArr.length + iArr2.length];
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
            state |= this.M.setState(iArr3);
        }
        boolean z4 = b.d.a.a.c0.a.f1815a;
        if (I(this.N)) {
            state |= this.N.setState(iArr2);
        }
        if (state) {
            invalidateSelf();
        }
        if (z2) {
            J();
        }
        return state;
    }

    public void L(boolean z) {
        if (this.R != z) {
            this.R = z;
            float fA = A();
            if (!z && this.t0) {
                this.t0 = false;
            }
            float fA2 = A();
            invalidateSelf();
            if (fA != fA2) {
                J();
            }
        }
    }

    public void M(Drawable drawable) {
        if (this.T != drawable) {
            float fA = A();
            this.T = drawable;
            float fA2 = A();
            s0(this.T);
            y(this.T);
            invalidateSelf();
            if (fA != fA2) {
                J();
            }
        }
    }

    public void N(ColorStateList colorStateList) {
        if (this.U != colorStateList) {
            this.U = colorStateList;
            if (this.S && this.T != null && this.R) {
                this.T.setTintList(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void O(boolean z) {
        if (this.S != z) {
            boolean zP0 = p0();
            this.S = z;
            boolean zP02 = p0();
            if (zP0 != zP02) {
                if (zP02) {
                    y(this.T);
                } else {
                    s0(this.T);
                }
                invalidateSelf();
                J();
            }
        }
    }

    public void P(ColorStateList colorStateList) {
        if (this.z != colorStateList) {
            this.z = colorStateList;
            onStateChange(getState());
        }
    }

    @Deprecated
    public void Q(float f2) {
        if (this.B != f2) {
            this.B = f2;
            this.f1828a.f1835a = this.f1828a.f1835a.e(f2);
            invalidateSelf();
        }
    }

    public void R(float f2) {
        if (this.e0 != f2) {
            this.e0 = f2;
            invalidateSelf();
            J();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.graphics.drawable.Drawable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r3v0, types: [b.d.a.a.e0.g, b.d.a.a.t.b] */
    public void S(Drawable drawable) {
        ?? A;
        Object obj = this.H;
        if (obj != null) {
            boolean z = obj instanceof a.h.e.f.a;
            A = obj;
            if (z) {
                A = ((a.h.e.f.a) obj).a();
            }
        } else {
            A = 0;
        }
        if (A != drawable) {
            float fA = A();
            this.H = drawable != null ? drawable.mutate() : null;
            float fA2 = A();
            s0(A);
            if (q0()) {
                y(this.H);
            }
            invalidateSelf();
            if (fA != fA2) {
                J();
            }
        }
    }

    public void T(float f2) {
        if (this.J != f2) {
            float fA = A();
            this.J = f2;
            float fA2 = A();
            invalidateSelf();
            if (fA != fA2) {
                J();
            }
        }
    }

    public void U(ColorStateList colorStateList) {
        this.K = true;
        if (this.I != colorStateList) {
            this.I = colorStateList;
            if (q0()) {
                this.H.setTintList(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void V(boolean z) {
        if (this.G != z) {
            boolean zQ0 = q0();
            this.G = z;
            boolean zQ02 = q0();
            if (zQ0 != zQ02) {
                if (zQ02) {
                    y(this.H);
                } else {
                    s0(this.H);
                }
                invalidateSelf();
                J();
            }
        }
    }

    public void W(float f2) {
        if (this.A != f2) {
            this.A = f2;
            invalidateSelf();
            J();
        }
    }

    public void X(float f2) {
        if (this.X != f2) {
            this.X = f2;
            invalidateSelf();
            J();
        }
    }

    public void Y(ColorStateList colorStateList) {
        if (this.C != colorStateList) {
            this.C = colorStateList;
            if (this.H0) {
                u(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void Z(float f2) {
        if (this.D != f2) {
            this.D = f2;
            this.g0.setStrokeWidth(f2);
            if (this.H0) {
                this.f1828a.l = f2;
                invalidateSelf();
            }
            invalidateSelf();
        }
    }

    @Override // b.d.a.a.z.i.b
    public void a() {
        J();
        invalidateSelf();
    }

    public void a0(Drawable drawable) {
        Drawable drawableF = F();
        if (drawableF != drawable) {
            float fD = D();
            this.M = drawable != null ? drawable.mutate() : null;
            boolean z = b.d.a.a.c0.a.f1815a;
            this.N = new RippleDrawable(b.d.a.a.c0.a.a(this.E), this.M, J0);
            float fD2 = D();
            s0(drawableF);
            if (r0()) {
                y(this.M);
            }
            invalidateSelf();
            if (fD != fD2) {
                J();
            }
        }
    }

    public void b0(float f2) {
        if (this.d0 != f2) {
            this.d0 = f2;
            invalidateSelf();
            if (r0()) {
                J();
            }
        }
    }

    public void c0(float f2) {
        if (this.P != f2) {
            this.P = f2;
            invalidateSelf();
            if (r0()) {
                J();
            }
        }
    }

    public void d0(float f2) {
        if (this.c0 != f2) {
            this.c0 = f2;
            invalidateSelf();
            if (r0()) {
                J();
            }
        }
    }

    @Override // b.d.a.a.e0.g, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int iSave;
        float f2;
        Rect bounds = getBounds();
        if (bounds.isEmpty() || (i = this.v0) == 0) {
            return;
        }
        int iSaveLayerAlpha = i < 255 ? canvas.saveLayerAlpha(bounds.left, bounds.top, bounds.right, bounds.bottom, i) : 0;
        if (!this.H0) {
            this.g0.setColor(this.n0);
            this.g0.setStyle(Paint.Style.FILL);
            this.j0.set(bounds);
            canvas.drawRoundRect(this.j0, E(), E(), this.g0);
        }
        if (!this.H0) {
            this.g0.setColor(this.o0);
            this.g0.setStyle(Paint.Style.FILL);
            Paint paint = this.g0;
            ColorFilter colorFilter = this.w0;
            if (colorFilter == null) {
                colorFilter = this.x0;
            }
            paint.setColorFilter(colorFilter);
            this.j0.set(bounds);
            canvas.drawRoundRect(this.j0, E(), E(), this.g0);
        }
        if (this.H0) {
            super.draw(canvas);
        }
        if (this.D > 0.0f && !this.H0) {
            this.g0.setColor(this.q0);
            this.g0.setStyle(Paint.Style.STROKE);
            if (!this.H0) {
                Paint paint2 = this.g0;
                ColorFilter colorFilter2 = this.w0;
                if (colorFilter2 == null) {
                    colorFilter2 = this.x0;
                }
                paint2.setColorFilter(colorFilter2);
            }
            RectF rectF = this.j0;
            float f3 = bounds.left;
            float f4 = this.D / 2.0f;
            rectF.set(f3 + f4, bounds.top + f4, bounds.right - f4, bounds.bottom - f4);
            float f5 = this.B - (this.D / 2.0f);
            canvas.drawRoundRect(this.j0, f5, f5, this.g0);
        }
        this.g0.setColor(this.r0);
        this.g0.setStyle(Paint.Style.FILL);
        this.j0.set(bounds);
        if (this.H0) {
            c(new RectF(bounds), this.l0);
            h(canvas, this.g0, this.l0, this.f1828a.f1835a, i());
        } else {
            canvas.drawRoundRect(this.j0, E(), E(), this.g0);
        }
        if (q0()) {
            z(bounds, this.j0);
            RectF rectF2 = this.j0;
            float f6 = rectF2.left;
            float f7 = rectF2.top;
            canvas.translate(f6, f7);
            this.H.setBounds(0, 0, (int) this.j0.width(), (int) this.j0.height());
            this.H.draw(canvas);
            canvas.translate(-f6, -f7);
        }
        if (p0()) {
            z(bounds, this.j0);
            RectF rectF3 = this.j0;
            float f8 = rectF3.left;
            float f9 = rectF3.top;
            canvas.translate(f8, f9);
            this.T.setBounds(0, 0, (int) this.j0.width(), (int) this.j0.height());
            this.T.draw(canvas);
            canvas.translate(-f8, -f9);
        }
        if (!this.F0 || this.F == null) {
            i2 = iSaveLayerAlpha;
            i3 = 0;
            i4 = 255;
        } else {
            PointF pointF = this.k0;
            pointF.set(0.0f, 0.0f);
            Paint.Align align = Paint.Align.LEFT;
            if (this.F != null) {
                float fA = A() + this.X + this.a0;
                if (getLayoutDirection() == 0) {
                    pointF.x = bounds.left + fA;
                    align = Paint.Align.LEFT;
                } else {
                    pointF.x = bounds.right - fA;
                    align = Paint.Align.RIGHT;
                }
                float fCenterY = bounds.centerY();
                this.m0.f2181a.getFontMetrics(this.i0);
                Paint.FontMetrics fontMetrics = this.i0;
                pointF.y = fCenterY - ((fontMetrics.descent + fontMetrics.ascent) / 2.0f);
            }
            RectF rectF4 = this.j0;
            rectF4.setEmpty();
            if (this.F != null) {
                float fA2 = A() + this.X + this.a0;
                float fD = D() + this.e0 + this.b0;
                if (getLayoutDirection() == 0) {
                    rectF4.left = bounds.left + fA2;
                    f2 = bounds.right - fD;
                } else {
                    rectF4.left = bounds.left + fD;
                    f2 = bounds.right - fA2;
                }
                rectF4.right = f2;
                rectF4.top = bounds.top;
                rectF4.bottom = bounds.bottom;
            }
            i iVar = this.m0;
            if (iVar.f2186f != null) {
                iVar.f2181a.drawableState = getState();
                i iVar2 = this.m0;
                iVar2.f2186f.e(this.f0, iVar2.f2181a, iVar2.f2182b);
            }
            this.m0.f2181a.setTextAlign(align);
            boolean z = Math.round(this.m0.a(this.F.toString())) > Math.round(this.j0.width());
            if (z) {
                iSave = canvas.save();
                canvas.clipRect(this.j0);
            } else {
                iSave = 0;
            }
            CharSequence charSequenceEllipsize = this.F;
            if (z && this.E0 != null) {
                charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, this.m0.f2181a, this.j0.width(), this.E0);
            }
            CharSequence charSequence = charSequenceEllipsize;
            int length = charSequence.length();
            PointF pointF2 = this.k0;
            i2 = iSaveLayerAlpha;
            i3 = 0;
            i4 = 255;
            canvas.drawText(charSequence, 0, length, pointF2.x, pointF2.y, this.m0.f2181a);
            if (z) {
                canvas.restoreToCount(iSave);
            }
        }
        if (r0()) {
            B(bounds, this.j0);
            RectF rectF5 = this.j0;
            float f10 = rectF5.left;
            float f11 = rectF5.top;
            canvas.translate(f10, f11);
            this.M.setBounds(i3, i3, (int) this.j0.width(), (int) this.j0.height());
            boolean z2 = b.d.a.a.c0.a.f1815a;
            this.N.setBounds(this.M.getBounds());
            this.N.jumpToCurrentState();
            this.N.draw(canvas);
            canvas.translate(-f10, -f11);
        }
        Paint paint3 = this.h0;
        if (paint3 != null) {
            paint3.setColor(a.h.e.a.c(-16777216, 127));
            canvas.drawRect(bounds, this.h0);
            if (q0() || p0()) {
                z(bounds, this.j0);
                canvas.drawRect(this.j0, this.h0);
            }
            if (this.F != null) {
                i5 = i4;
                i6 = i2;
                canvas.drawLine(bounds.left, bounds.exactCenterY(), bounds.right, bounds.exactCenterY(), this.h0);
            } else {
                i5 = i4;
                i6 = i2;
            }
            if (r0()) {
                B(bounds, this.j0);
                canvas.drawRect(this.j0, this.h0);
            }
            this.h0.setColor(a.h.e.a.c(-65536, 127));
            RectF rectF6 = this.j0;
            rectF6.set(bounds);
            if (r0()) {
                float f12 = this.e0 + this.d0 + this.P + this.c0 + this.b0;
                if (getLayoutDirection() == 0) {
                    rectF6.right = bounds.right - f12;
                } else {
                    rectF6.left = bounds.left + f12;
                }
            }
            canvas.drawRect(this.j0, this.h0);
            this.h0.setColor(a.h.e.a.c(-16711936, 127));
            C(bounds, this.j0);
            canvas.drawRect(this.j0, this.h0);
        } else {
            i5 = i4;
            i6 = i2;
        }
        if (this.v0 < i5) {
            canvas.restoreToCount(i6);
        }
    }

    public boolean e0(int[] iArr) {
        if (Arrays.equals(this.A0, iArr)) {
            return false;
        }
        this.A0 = iArr;
        if (r0()) {
            return K(getState(), iArr);
        }
        return false;
    }

    public void f0(ColorStateList colorStateList) {
        if (this.O != colorStateList) {
            this.O = colorStateList;
            if (r0()) {
                this.M.setTintList(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void g0(boolean z) {
        if (this.L != z) {
            boolean zR0 = r0();
            this.L = z;
            boolean zR02 = r0();
            if (zR0 != zR02) {
                if (zR02) {
                    y(this.M);
                } else {
                    s0(this.M);
                }
                invalidateSelf();
                J();
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.v0;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.w0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) this.A;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return Math.min(Math.round(D() + this.m0.a(this.F.toString()) + A() + this.X + this.a0 + this.b0 + this.e0), this.G0);
    }

    @Override // b.d.a.a.e0.g, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // b.d.a.a.e0.g, android.graphics.drawable.Drawable
    @TargetApi(21)
    public void getOutline(Outline outline) {
        if (this.H0) {
            super.getOutline(outline);
            return;
        }
        Rect bounds = getBounds();
        if (bounds.isEmpty()) {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), (int) this.A, this.B);
        } else {
            outline.setRoundRect(bounds, this.B);
        }
        outline.setAlpha(this.v0 / 255.0f);
    }

    public void h0(float f2) {
        if (this.Z != f2) {
            float fA = A();
            this.Z = f2;
            float fA2 = A();
            invalidateSelf();
            if (fA != fA2) {
                J();
            }
        }
    }

    public void i0(float f2) {
        if (this.Y != f2) {
            float fA = A();
            this.Y = f2;
            float fA2 = A();
            invalidateSelf();
            if (fA != fA2) {
                J();
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

    @Override // b.d.a.a.e0.g, android.graphics.drawable.Drawable
    public boolean isStateful() {
        ColorStateList colorStateList;
        if (!H(this.y) && !H(this.z) && !H(this.C) && (!this.B0 || !H(this.C0))) {
            b.d.a.a.b0.b bVar = this.m0.f2186f;
            if (!((bVar == null || (colorStateList = bVar.f1804a) == null || !colorStateList.isStateful()) ? false : true)) {
                if (!(this.S && this.T != null && this.R) && !I(this.H) && !I(this.T) && !H(this.y0)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void j0(ColorStateList colorStateList) {
        if (this.E != colorStateList) {
            this.E = colorStateList;
            this.C0 = this.B0 ? b.d.a.a.c0.a.a(colorStateList) : null;
            onStateChange(getState());
        }
    }

    public void k0(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        if (TextUtils.equals(this.F, charSequence)) {
            return;
        }
        this.F = charSequence;
        this.m0.f2184d = true;
        invalidateSelf();
        J();
    }

    public void l0(b.d.a.a.b0.b bVar) {
        i iVar = this.m0;
        Context context = this.f0;
        if (iVar.f2186f != bVar) {
            iVar.f2186f = bVar;
            if (bVar != null) {
                bVar.f(context, iVar.f2181a, iVar.f2182b);
                i.b bVar2 = iVar.f2185e.get();
                if (bVar2 != null) {
                    iVar.f2181a.drawableState = bVar2.getState();
                }
                bVar.e(context, iVar.f2181a, iVar.f2182b);
                iVar.f2184d = true;
            }
            i.b bVar3 = iVar.f2185e.get();
            if (bVar3 != null) {
                bVar3.a();
                bVar3.onStateChange(bVar3.getState());
            }
        }
    }

    public void m0(float f2) {
        if (this.b0 != f2) {
            this.b0 = f2;
            invalidateSelf();
            J();
        }
    }

    public void n0(float f2) {
        if (this.a0 != f2) {
            this.a0 = f2;
            invalidateSelf();
            J();
        }
    }

    public void o0(boolean z) {
        if (this.B0 != z) {
            this.B0 = z;
            this.C0 = z ? b.d.a.a.c0.a.a(this.E) : null;
            onStateChange(getState());
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i) {
        boolean zOnLayoutDirectionChanged = super.onLayoutDirectionChanged(i);
        if (q0()) {
            zOnLayoutDirectionChanged |= this.H.setLayoutDirection(i);
        }
        if (p0()) {
            zOnLayoutDirectionChanged |= this.T.setLayoutDirection(i);
        }
        if (r0()) {
            zOnLayoutDirectionChanged |= this.M.setLayoutDirection(i);
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
        if (q0()) {
            zOnLevelChange |= this.H.setLevel(i);
        }
        if (p0()) {
            zOnLevelChange |= this.T.setLevel(i);
        }
        if (r0()) {
            zOnLevelChange |= this.M.setLevel(i);
        }
        if (zOnLevelChange) {
            invalidateSelf();
        }
        return zOnLevelChange;
    }

    @Override // b.d.a.a.e0.g, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        if (this.H0) {
            super.onStateChange(iArr);
        }
        return K(iArr, this.A0);
    }

    public final boolean p0() {
        return this.S && this.T != null && this.t0;
    }

    public final boolean q0() {
        return this.G && this.H != null;
    }

    public final boolean r0() {
        return this.L && this.M != null;
    }

    public final void s0(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j);
        }
    }

    @Override // b.d.a.a.e0.g, android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (this.v0 != i) {
            this.v0 = i;
            invalidateSelf();
        }
    }

    @Override // b.d.a.a.e0.g, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.w0 != colorFilter) {
            this.w0 = colorFilter;
            invalidateSelf();
        }
    }

    @Override // b.d.a.a.e0.g, android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        if (this.y0 != colorStateList) {
            this.y0 = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // b.d.a.a.e0.g, android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.z0 != mode) {
            this.z0 = mode;
            this.x0 = l.i.E0(this, this.y0, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (q0()) {
            visible |= this.H.setVisible(z, z2);
        }
        if (p0()) {
            visible |= this.T.setVisible(z, z2);
        }
        if (r0()) {
            visible |= this.M.setVisible(z, z2);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public final void y(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        drawable.setCallback(this);
        drawable.setLayoutDirection(getLayoutDirection());
        drawable.setLevel(getLevel());
        drawable.setVisible(isVisible(), false);
        if (drawable == this.M) {
            if (drawable.isStateful()) {
                drawable.setState(this.A0);
            }
            drawable.setTintList(this.O);
            return;
        }
        if (drawable.isStateful()) {
            drawable.setState(getState());
        }
        Drawable drawable2 = this.H;
        if (drawable == drawable2 && this.K) {
            drawable2.setTintList(this.I);
        }
    }

    public final void z(Rect rect, RectF rectF) {
        float fCeil;
        rectF.setEmpty();
        if (q0() || p0()) {
            float f2 = this.X + this.Y;
            float fG = G();
            if (getLayoutDirection() == 0) {
                float f3 = rect.left + f2;
                rectF.left = f3;
                rectF.right = f3 + fG;
            } else {
                float f4 = rect.right - f2;
                rectF.right = f4;
                rectF.left = f4 - fG;
            }
            Drawable drawable = this.t0 ? this.T : this.H;
            if (this.J > 0.0f || drawable == null) {
                fCeil = this.J;
            } else {
                fCeil = (float) Math.ceil(TypedValue.applyDimension(1, 24, this.f0.getResources().getDisplayMetrics()));
                if (drawable.getIntrinsicHeight() <= fCeil) {
                    fCeil = drawable.getIntrinsicHeight();
                }
            }
            float fExactCenterY = rect.exactCenterY() - (fCeil / 2.0f);
            rectF.top = fExactCenterY;
            rectF.bottom = fExactCenterY + fCeil;
        }
    }
}
