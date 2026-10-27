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
import b3.c;
import b3.d;
import c3.b;
import com.google.android.material.internal.h;
import com.google.android.material.internal.j;
import com.google.android.material.internal.l;
import e3.g;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* JADX INFO: compiled from: ChipDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends g implements Drawable.Callback, h.b {
    private static final int[] N0 = {R.attr.state_enabled};
    private static final ShapeDrawable O0 = new ShapeDrawable(new OvalShape());
    private int A0;
    private ColorFilter B0;
    private PorterDuffColorFilter C0;
    private ColorStateList D;
    private ColorStateList D0;
    private ColorStateList E;
    private PorterDuff.Mode E0;
    private float F;
    private int[] F0;
    private float G;
    private boolean G0;
    private ColorStateList H;
    private ColorStateList H0;
    private float I;
    private WeakReference<InterfaceC0047a> I0;
    private ColorStateList J;
    private TextUtils.TruncateAt J0;
    private CharSequence K;
    private boolean K0;
    private boolean L;
    private int L0;
    private Drawable M;
    private boolean M0;
    private ColorStateList N;
    private float O;
    private boolean P;
    private boolean Q;
    private Drawable R;
    private Drawable S;
    private ColorStateList T;
    private float U;
    private CharSequence V;
    private boolean W;
    private boolean X;
    private Drawable Y;
    private ColorStateList Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    private p2.h f4336a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    private p2.h f4337b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    private float f4338c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    private float f4339d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    private float f4340e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    private float f4341f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    private float f4342g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    private float f4343h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    private float f4344i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    private float f4345j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    private final Context f4346k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    private final Paint f4347l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    private final Paint f4348m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    private final Paint.FontMetrics f4349n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    private final RectF f4350o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    private final PointF f4351p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    private final Path f4352q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    private final h f4353r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    private int f4354s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    private int f4355t0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    private int f4356u0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    private int f4357v0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    private int f4358w0;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    private int f4359x0;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    private boolean f4360y0;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    private int f4361z0;

    /* JADX INFO: renamed from: com.google.android.material.chip.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ChipDrawable.java */
    public interface InterfaceC0047a {
        void a();
    }

    private a(Context context, AttributeSet attributeSet, int i7, int i8) {
        super(context, attributeSet, i7, i8);
        this.G = -1.0f;
        this.f4347l0 = new Paint(1);
        this.f4349n0 = new Paint.FontMetrics();
        this.f4350o0 = new RectF();
        this.f4351p0 = new PointF();
        this.f4352q0 = new Path();
        this.A0 = 255;
        this.E0 = PorterDuff.Mode.SRC_IN;
        this.I0 = new WeakReference<>(null);
        M(context);
        this.f4346k0 = context;
        h hVar = new h(this);
        this.f4353r0 = hVar;
        this.K = "";
        hVar.e().density = context.getResources().getDisplayMetrics().density;
        this.f4348m0 = null;
        int[] iArr = N0;
        setState(iArr);
        j2(iArr);
        this.K0 = true;
        if (b.f3589a) {
            O0.setTint(-1);
        }
    }

    private void A0(Canvas canvas, Rect rect) {
        this.f4347l0.setColor(this.f4358w0);
        this.f4347l0.setStyle(Paint.Style.FILL);
        this.f4350o0.set(rect);
        if (!this.M0) {
            canvas.drawRoundRect(this.f4350o0, G0(), G0(), this.f4347l0);
        } else {
            h(new RectF(rect), this.f4352q0);
            super.p(canvas, this.f4347l0, this.f4352q0, u());
        }
    }

    private void B0(Canvas canvas, Rect rect) {
        Paint paint = this.f4348m0;
        if (paint != null) {
            paint.setColor(z.a.d(-16777216, 127));
            canvas.drawRect(rect, this.f4348m0);
            if (L2() || K2()) {
                j0(rect, this.f4350o0);
                canvas.drawRect(this.f4350o0, this.f4348m0);
            }
            if (this.K != null) {
                canvas.drawLine(rect.left, rect.exactCenterY(), rect.right, rect.exactCenterY(), this.f4348m0);
            }
            if (M2()) {
                m0(rect, this.f4350o0);
                canvas.drawRect(this.f4350o0, this.f4348m0);
            }
            this.f4348m0.setColor(z.a.d(-65536, 127));
            l0(rect, this.f4350o0);
            canvas.drawRect(this.f4350o0, this.f4348m0);
            this.f4348m0.setColor(z.a.d(-16711936, 127));
            n0(rect, this.f4350o0);
            canvas.drawRect(this.f4350o0, this.f4348m0);
        }
    }

    private void C0(Canvas canvas, Rect rect) {
        if (this.K != null) {
            Paint.Align alignR0 = r0(rect, this.f4351p0);
            p0(rect, this.f4350o0);
            if (this.f4353r0.d() != null) {
                this.f4353r0.e().drawableState = getState();
                this.f4353r0.j(this.f4346k0);
            }
            this.f4353r0.e().setTextAlign(alignR0);
            int iSave = 0;
            boolean z6 = Math.round(this.f4353r0.f(f1().toString())) > Math.round(this.f4350o0.width());
            if (z6) {
                iSave = canvas.save();
                canvas.clipRect(this.f4350o0);
            }
            CharSequence charSequenceEllipsize = this.K;
            if (z6 && this.J0 != null) {
                charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, this.f4353r0.e(), this.f4350o0.width(), this.J0);
            }
            CharSequence charSequence = charSequenceEllipsize;
            int length = charSequence.length();
            PointF pointF = this.f4351p0;
            canvas.drawText(charSequence, 0, length, pointF.x, pointF.y, this.f4353r0.e());
            if (z6) {
                canvas.restoreToCount(iSave);
            }
        }
    }

    private boolean K2() {
        return this.X && this.Y != null && this.f4360y0;
    }

    private boolean L2() {
        return this.L && this.M != null;
    }

    private boolean M2() {
        return this.Q && this.R != null;
    }

    private void N2(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    private void O2() {
        this.H0 = this.G0 ? b.d(this.J) : null;
    }

    @TargetApi(21)
    private void P2() {
        this.S = new RippleDrawable(b.d(d1()), this.R, O0);
    }

    private float X0() {
        Drawable drawable = this.f4360y0 ? this.Y : this.M;
        float fCeil = this.O;
        if (fCeil <= 0.0f && drawable != null) {
            fCeil = (float) Math.ceil(l.b(this.f4346k0, 24));
            if (drawable.getIntrinsicHeight() <= fCeil) {
                return drawable.getIntrinsicHeight();
            }
        }
        return fCeil;
    }

    private float Y0() {
        Drawable drawable = this.f4360y0 ? this.Y : this.M;
        float f7 = this.O;
        return (f7 > 0.0f || drawable == null) ? f7 : drawable.getIntrinsicWidth();
    }

    private void Z1(ColorStateList colorStateList) {
        if (this.D != colorStateList) {
            this.D = colorStateList;
            onStateChange(getState());
        }
    }

    private void i0(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        drawable.setCallback(this);
        a0.a.g(drawable, a0.a.b(this));
        drawable.setLevel(getLevel());
        drawable.setVisible(isVisible(), false);
        if (drawable == this.R) {
            if (drawable.isStateful()) {
                drawable.setState(U0());
            }
            a0.a.i(drawable, this.T);
            return;
        }
        if (drawable.isStateful()) {
            drawable.setState(getState());
        }
        Drawable drawable2 = this.M;
        if (drawable == drawable2 && this.P) {
            a0.a.i(drawable2, this.N);
        }
    }

    private void j0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (L2() || K2()) {
            float f7 = this.f4338c0 + this.f4339d0;
            float fY0 = Y0();
            if (a0.a.b(this) == 0) {
                float f8 = rect.left + f7;
                rectF.left = f8;
                rectF.right = f8 + fY0;
            } else {
                float f9 = rect.right - f7;
                rectF.right = f9;
                rectF.left = f9 - fY0;
            }
            float fX0 = X0();
            float fExactCenterY = rect.exactCenterY() - (fX0 / 2.0f);
            rectF.top = fExactCenterY;
            rectF.bottom = fExactCenterY + fX0;
        }
    }

    private ColorFilter j1() {
        ColorFilter colorFilter = this.B0;
        return colorFilter != null ? colorFilter : this.C0;
    }

    private void l0(Rect rect, RectF rectF) {
        rectF.set(rect);
        if (M2()) {
            float f7 = this.f4345j0 + this.f4344i0 + this.U + this.f4343h0 + this.f4342g0;
            if (a0.a.b(this) == 0) {
                rectF.right = rect.right - f7;
            } else {
                rectF.left = rect.left + f7;
            }
        }
    }

    private static boolean l1(int[] iArr, int i7) {
        if (iArr == null) {
            return false;
        }
        for (int i8 : iArr) {
            if (i8 == i7) {
                return true;
            }
        }
        return false;
    }

    private void m0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (M2()) {
            float f7 = this.f4345j0 + this.f4344i0;
            if (a0.a.b(this) == 0) {
                float f8 = rect.right - f7;
                rectF.right = f8;
                rectF.left = f8 - this.U;
            } else {
                float f9 = rect.left + f7;
                rectF.left = f9;
                rectF.right = f9 + this.U;
            }
            float fExactCenterY = rect.exactCenterY();
            float f10 = this.U;
            float f11 = fExactCenterY - (f10 / 2.0f);
            rectF.top = f11;
            rectF.bottom = f11 + f10;
        }
    }

    private void n0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (M2()) {
            float f7 = this.f4345j0 + this.f4344i0 + this.U + this.f4343h0 + this.f4342g0;
            if (a0.a.b(this) == 0) {
                float f8 = rect.right;
                rectF.right = f8;
                rectF.left = f8 - f7;
            } else {
                int i7 = rect.left;
                rectF.left = i7;
                rectF.right = i7 + f7;
            }
            rectF.top = rect.top;
            rectF.bottom = rect.bottom;
        }
    }

    private void p0(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (this.K != null) {
            float fK0 = this.f4338c0 + k0() + this.f4341f0;
            float fO0 = this.f4345j0 + o0() + this.f4342g0;
            if (a0.a.b(this) == 0) {
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

    private static boolean p1(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    private float q0() {
        this.f4353r0.e().getFontMetrics(this.f4349n0);
        Paint.FontMetrics fontMetrics = this.f4349n0;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    private static boolean q1(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    private static boolean r1(d dVar) {
        ColorStateList colorStateList;
        return (dVar == null || (colorStateList = dVar.f3530a) == null || !colorStateList.isStateful()) ? false : true;
    }

    private boolean s0() {
        return this.X && this.Y != null && this.W;
    }

    private void s1(AttributeSet attributeSet, int i7, int i8) {
        TypedArray typedArrayH = j.h(this.f4346k0, attributeSet, o2.l.Chip, i7, i8, new int[0]);
        this.M0 = typedArrayH.hasValue(o2.l.Chip_shapeAppearance);
        Z1(c.a(this.f4346k0, typedArrayH, o2.l.Chip_chipSurfaceColor));
        D1(c.a(this.f4346k0, typedArrayH, o2.l.Chip_chipBackgroundColor));
        R1(typedArrayH.getDimension(o2.l.Chip_chipMinHeight, 0.0f));
        int i9 = o2.l.Chip_chipCornerRadius;
        if (typedArrayH.hasValue(i9)) {
            F1(typedArrayH.getDimension(i9, 0.0f));
        }
        V1(c.a(this.f4346k0, typedArrayH, o2.l.Chip_chipStrokeColor));
        X1(typedArrayH.getDimension(o2.l.Chip_chipStrokeWidth, 0.0f));
        w2(c.a(this.f4346k0, typedArrayH, o2.l.Chip_rippleColor));
        B2(typedArrayH.getText(o2.l.Chip_android_text));
        d dVarF = c.f(this.f4346k0, typedArrayH, o2.l.Chip_android_textAppearance);
        dVarF.f3543n = typedArrayH.getDimension(o2.l.Chip_android_textSize, dVarF.f3543n);
        C2(dVarF);
        int i10 = typedArrayH.getInt(o2.l.Chip_android_ellipsize, 0);
        if (i10 == 1) {
            o2(TextUtils.TruncateAt.START);
        } else if (i10 == 2) {
            o2(TextUtils.TruncateAt.MIDDLE);
        } else if (i10 == 3) {
            o2(TextUtils.TruncateAt.END);
        }
        Q1(typedArrayH.getBoolean(o2.l.Chip_chipIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") == null) {
            Q1(typedArrayH.getBoolean(o2.l.Chip_chipIconEnabled, false));
        }
        J1(c.d(this.f4346k0, typedArrayH, o2.l.Chip_chipIcon));
        int i11 = o2.l.Chip_chipIconTint;
        if (typedArrayH.hasValue(i11)) {
            N1(c.a(this.f4346k0, typedArrayH, i11));
        }
        L1(typedArrayH.getDimension(o2.l.Chip_chipIconSize, -1.0f));
        m2(typedArrayH.getBoolean(o2.l.Chip_closeIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") == null) {
            m2(typedArrayH.getBoolean(o2.l.Chip_closeIconEnabled, false));
        }
        a2(c.d(this.f4346k0, typedArrayH, o2.l.Chip_closeIcon));
        k2(c.a(this.f4346k0, typedArrayH, o2.l.Chip_closeIconTint));
        f2(typedArrayH.getDimension(o2.l.Chip_closeIconSize, 0.0f));
        v1(typedArrayH.getBoolean(o2.l.Chip_android_checkable, false));
        C1(typedArrayH.getBoolean(o2.l.Chip_checkedIconVisible, false));
        if (attributeSet != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") != null && attributeSet.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") == null) {
            C1(typedArrayH.getBoolean(o2.l.Chip_checkedIconEnabled, false));
        }
        x1(c.d(this.f4346k0, typedArrayH, o2.l.Chip_checkedIcon));
        int i12 = o2.l.Chip_checkedIconTint;
        if (typedArrayH.hasValue(i12)) {
            z1(c.a(this.f4346k0, typedArrayH, i12));
        }
        z2(p2.h.b(this.f4346k0, typedArrayH, o2.l.Chip_showMotionSpec));
        p2(p2.h.b(this.f4346k0, typedArrayH, o2.l.Chip_hideMotionSpec));
        T1(typedArrayH.getDimension(o2.l.Chip_chipStartPadding, 0.0f));
        t2(typedArrayH.getDimension(o2.l.Chip_iconStartPadding, 0.0f));
        r2(typedArrayH.getDimension(o2.l.Chip_iconEndPadding, 0.0f));
        G2(typedArrayH.getDimension(o2.l.Chip_textStartPadding, 0.0f));
        E2(typedArrayH.getDimension(o2.l.Chip_textEndPadding, 0.0f));
        h2(typedArrayH.getDimension(o2.l.Chip_closeIconStartPadding, 0.0f));
        c2(typedArrayH.getDimension(o2.l.Chip_closeIconEndPadding, 0.0f));
        H1(typedArrayH.getDimension(o2.l.Chip_chipEndPadding, 0.0f));
        v2(typedArrayH.getDimensionPixelSize(o2.l.Chip_android_maxWidth, Integer.MAX_VALUE));
        typedArrayH.recycle();
    }

    public static a t0(Context context, AttributeSet attributeSet, int i7, int i8) {
        a aVar = new a(context, attributeSet, i7, i8);
        aVar.s1(attributeSet, i7, i8);
        return aVar;
    }

    private void u0(Canvas canvas, Rect rect) {
        if (K2()) {
            j0(rect, this.f4350o0);
            RectF rectF = this.f4350o0;
            float f7 = rectF.left;
            float f8 = rectF.top;
            canvas.translate(f7, f8);
            this.Y.setBounds(0, 0, (int) this.f4350o0.width(), (int) this.f4350o0.height());
            this.Y.draw(canvas);
            canvas.translate(-f7, -f8);
        }
    }

    private boolean u1(int[] iArr, int[] iArr2) {
        boolean z6;
        boolean zOnStateChange = super.onStateChange(iArr);
        ColorStateList colorStateList = this.D;
        int iL = l(colorStateList != null ? colorStateList.getColorForState(iArr, this.f4354s0) : 0);
        boolean state = true;
        if (this.f4354s0 != iL) {
            this.f4354s0 = iL;
            zOnStateChange = true;
        }
        ColorStateList colorStateList2 = this.E;
        int iL2 = l(colorStateList2 != null ? colorStateList2.getColorForState(iArr, this.f4355t0) : 0);
        if (this.f4355t0 != iL2) {
            this.f4355t0 = iL2;
            zOnStateChange = true;
        }
        int iE = u2.a.e(iL, iL2);
        if ((this.f4356u0 != iE) | (x() == null)) {
            this.f4356u0 = iE;
            X(ColorStateList.valueOf(iE));
            zOnStateChange = true;
        }
        ColorStateList colorStateList3 = this.H;
        int colorForState = colorStateList3 != null ? colorStateList3.getColorForState(iArr, this.f4357v0) : 0;
        if (this.f4357v0 != colorForState) {
            this.f4357v0 = colorForState;
            zOnStateChange = true;
        }
        int colorForState2 = (this.H0 == null || !b.e(iArr)) ? 0 : this.H0.getColorForState(iArr, this.f4358w0);
        if (this.f4358w0 != colorForState2) {
            this.f4358w0 = colorForState2;
            if (this.G0) {
                zOnStateChange = true;
            }
        }
        int colorForState3 = (this.f4353r0.d() == null || this.f4353r0.d().f3530a == null) ? 0 : this.f4353r0.d().f3530a.getColorForState(iArr, this.f4359x0);
        if (this.f4359x0 != colorForState3) {
            this.f4359x0 = colorForState3;
            zOnStateChange = true;
        }
        boolean z7 = l1(getState(), R.attr.state_checked) && this.W;
        if (this.f4360y0 == z7 || this.Y == null) {
            z6 = false;
        } else {
            float fK0 = k0();
            this.f4360y0 = z7;
            if (fK0 != k0()) {
                zOnStateChange = true;
                z6 = true;
            } else {
                z6 = false;
                zOnStateChange = true;
            }
        }
        ColorStateList colorStateList4 = this.D0;
        int colorForState4 = colorStateList4 != null ? colorStateList4.getColorForState(iArr, this.f4361z0) : 0;
        if (this.f4361z0 != colorForState4) {
            this.f4361z0 = colorForState4;
            this.C0 = w2.a.a(this, this.D0, this.E0);
        } else {
            state = zOnStateChange;
        }
        if (q1(this.M)) {
            state |= this.M.setState(iArr);
        }
        if (q1(this.Y)) {
            state |= this.Y.setState(iArr);
        }
        if (q1(this.R)) {
            int[] iArr3 = new int[iArr.length + iArr2.length];
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            System.arraycopy(iArr2, 0, iArr3, iArr.length, iArr2.length);
            state |= this.R.setState(iArr3);
        }
        if (b.f3589a && q1(this.S)) {
            state |= this.S.setState(iArr2);
        }
        if (state) {
            invalidateSelf();
        }
        if (z6) {
            t1();
        }
        return state;
    }

    private void v0(Canvas canvas, Rect rect) {
        if (this.M0) {
            return;
        }
        this.f4347l0.setColor(this.f4355t0);
        this.f4347l0.setStyle(Paint.Style.FILL);
        this.f4347l0.setColorFilter(j1());
        this.f4350o0.set(rect);
        canvas.drawRoundRect(this.f4350o0, G0(), G0(), this.f4347l0);
    }

    private void w0(Canvas canvas, Rect rect) {
        if (L2()) {
            j0(rect, this.f4350o0);
            RectF rectF = this.f4350o0;
            float f7 = rectF.left;
            float f8 = rectF.top;
            canvas.translate(f7, f8);
            this.M.setBounds(0, 0, (int) this.f4350o0.width(), (int) this.f4350o0.height());
            this.M.draw(canvas);
            canvas.translate(-f7, -f8);
        }
    }

    private void x0(Canvas canvas, Rect rect) {
        if (this.I <= 0.0f || this.M0) {
            return;
        }
        this.f4347l0.setColor(this.f4357v0);
        this.f4347l0.setStyle(Paint.Style.STROKE);
        if (!this.M0) {
            this.f4347l0.setColorFilter(j1());
        }
        RectF rectF = this.f4350o0;
        float f7 = rect.left;
        float f8 = this.I;
        rectF.set(f7 + (f8 / 2.0f), rect.top + (f8 / 2.0f), rect.right - (f8 / 2.0f), rect.bottom - (f8 / 2.0f));
        float f9 = this.G - (this.I / 2.0f);
        canvas.drawRoundRect(this.f4350o0, f9, f9, this.f4347l0);
    }

    private void y0(Canvas canvas, Rect rect) {
        if (this.M0) {
            return;
        }
        this.f4347l0.setColor(this.f4354s0);
        this.f4347l0.setStyle(Paint.Style.FILL);
        this.f4350o0.set(rect);
        canvas.drawRoundRect(this.f4350o0, G0(), G0(), this.f4347l0);
    }

    private void z0(Canvas canvas, Rect rect) {
        if (M2()) {
            m0(rect, this.f4350o0);
            RectF rectF = this.f4350o0;
            float f7 = rectF.left;
            float f8 = rectF.top;
            canvas.translate(f7, f8);
            this.R.setBounds(0, 0, (int) this.f4350o0.width(), (int) this.f4350o0.height());
            if (b.f3589a) {
                this.S.setBounds(this.R.getBounds());
                this.S.jumpToCurrentState();
                this.S.draw(canvas);
            } else {
                this.R.draw(canvas);
            }
            canvas.translate(-f7, -f8);
        }
    }

    public void A1(int i7) {
        z1(f.a.a(this.f4346k0, i7));
    }

    public void A2(int i7) {
        z2(p2.h.c(this.f4346k0, i7));
    }

    public void B1(int i7) {
        C1(this.f4346k0.getResources().getBoolean(i7));
    }

    public void B2(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        if (TextUtils.equals(this.K, charSequence)) {
            return;
        }
        this.K = charSequence;
        this.f4353r0.i(true);
        invalidateSelf();
        t1();
    }

    public void C1(boolean z6) {
        if (this.X != z6) {
            boolean zK2 = K2();
            this.X = z6;
            boolean zK22 = K2();
            if (zK2 != zK22) {
                if (zK22) {
                    i0(this.Y);
                } else {
                    N2(this.Y);
                }
                invalidateSelf();
                t1();
            }
        }
    }

    public void C2(d dVar) {
        this.f4353r0.h(dVar, this.f4346k0);
    }

    public Drawable D0() {
        return this.Y;
    }

    public void D1(ColorStateList colorStateList) {
        if (this.E != colorStateList) {
            this.E = colorStateList;
            onStateChange(getState());
        }
    }

    public void D2(int i7) {
        C2(new d(this.f4346k0, i7));
    }

    public ColorStateList E0() {
        return this.Z;
    }

    public void E1(int i7) {
        D1(f.a.a(this.f4346k0, i7));
    }

    public void E2(float f7) {
        if (this.f4342g0 != f7) {
            this.f4342g0 = f7;
            invalidateSelf();
            t1();
        }
    }

    public ColorStateList F0() {
        return this.E;
    }

    @Deprecated
    public void F1(float f7) {
        if (this.G != f7) {
            this.G = f7;
            setShapeAppearanceModel(C().w(f7));
        }
    }

    public void F2(int i7) {
        E2(this.f4346k0.getResources().getDimension(i7));
    }

    public float G0() {
        return this.M0 ? F() : this.G;
    }

    @Deprecated
    public void G1(int i7) {
        F1(this.f4346k0.getResources().getDimension(i7));
    }

    public void G2(float f7) {
        if (this.f4341f0 != f7) {
            this.f4341f0 = f7;
            invalidateSelf();
            t1();
        }
    }

    public float H0() {
        return this.f4345j0;
    }

    public void H1(float f7) {
        if (this.f4345j0 != f7) {
            this.f4345j0 = f7;
            invalidateSelf();
            t1();
        }
    }

    public void H2(int i7) {
        G2(this.f4346k0.getResources().getDimension(i7));
    }

    public Drawable I0() {
        Drawable drawable = this.M;
        if (drawable != null) {
            return a0.a.k(drawable);
        }
        return null;
    }

    public void I1(int i7) {
        H1(this.f4346k0.getResources().getDimension(i7));
    }

    public void I2(boolean z6) {
        if (this.G0 != z6) {
            this.G0 = z6;
            O2();
            onStateChange(getState());
        }
    }

    public float J0() {
        return this.O;
    }

    public void J1(Drawable drawable) {
        Drawable drawableI0 = I0();
        if (drawableI0 != drawable) {
            float fK0 = k0();
            this.M = drawable != null ? a0.a.l(drawable).mutate() : null;
            float fK02 = k0();
            N2(drawableI0);
            if (L2()) {
                i0(this.M);
            }
            invalidateSelf();
            if (fK0 != fK02) {
                t1();
            }
        }
    }

    boolean J2() {
        return this.K0;
    }

    public ColorStateList K0() {
        return this.N;
    }

    public void K1(int i7) {
        J1(f.a.b(this.f4346k0, i7));
    }

    public float L0() {
        return this.F;
    }

    public void L1(float f7) {
        if (this.O != f7) {
            float fK0 = k0();
            this.O = f7;
            float fK02 = k0();
            invalidateSelf();
            if (fK0 != fK02) {
                t1();
            }
        }
    }

    public float M0() {
        return this.f4338c0;
    }

    public void M1(int i7) {
        L1(this.f4346k0.getResources().getDimension(i7));
    }

    public ColorStateList N0() {
        return this.H;
    }

    public void N1(ColorStateList colorStateList) {
        this.P = true;
        if (this.N != colorStateList) {
            this.N = colorStateList;
            if (L2()) {
                a0.a.i(this.M, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public float O0() {
        return this.I;
    }

    public void O1(int i7) {
        N1(f.a.a(this.f4346k0, i7));
    }

    public Drawable P0() {
        Drawable drawable = this.R;
        if (drawable != null) {
            return a0.a.k(drawable);
        }
        return null;
    }

    public void P1(int i7) {
        Q1(this.f4346k0.getResources().getBoolean(i7));
    }

    public CharSequence Q0() {
        return this.V;
    }

    public void Q1(boolean z6) {
        if (this.L != z6) {
            boolean zL2 = L2();
            this.L = z6;
            boolean zL22 = L2();
            if (zL2 != zL22) {
                if (zL22) {
                    i0(this.M);
                } else {
                    N2(this.M);
                }
                invalidateSelf();
                t1();
            }
        }
    }

    public float R0() {
        return this.f4344i0;
    }

    public void R1(float f7) {
        if (this.F != f7) {
            this.F = f7;
            invalidateSelf();
            t1();
        }
    }

    public float S0() {
        return this.U;
    }

    public void S1(int i7) {
        R1(this.f4346k0.getResources().getDimension(i7));
    }

    public float T0() {
        return this.f4343h0;
    }

    public void T1(float f7) {
        if (this.f4338c0 != f7) {
            this.f4338c0 = f7;
            invalidateSelf();
            t1();
        }
    }

    public int[] U0() {
        return this.F0;
    }

    public void U1(int i7) {
        T1(this.f4346k0.getResources().getDimension(i7));
    }

    public ColorStateList V0() {
        return this.T;
    }

    public void V1(ColorStateList colorStateList) {
        if (this.H != colorStateList) {
            this.H = colorStateList;
            if (this.M0) {
                d0(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void W0(RectF rectF) {
        n0(getBounds(), rectF);
    }

    public void W1(int i7) {
        V1(f.a.a(this.f4346k0, i7));
    }

    public void X1(float f7) {
        if (this.I != f7) {
            this.I = f7;
            this.f4347l0.setStrokeWidth(f7);
            if (this.M0) {
                super.e0(f7);
            }
            invalidateSelf();
        }
    }

    public void Y1(int i7) {
        X1(this.f4346k0.getResources().getDimension(i7));
    }

    public TextUtils.TruncateAt Z0() {
        return this.J0;
    }

    @Override // com.google.android.material.internal.h.b
    public void a() {
        t1();
        invalidateSelf();
    }

    public p2.h a1() {
        return this.f4337b0;
    }

    public void a2(Drawable drawable) {
        Drawable drawableP0 = P0();
        if (drawableP0 != drawable) {
            float fO0 = o0();
            this.R = drawable != null ? a0.a.l(drawable).mutate() : null;
            if (b.f3589a) {
                P2();
            }
            float fO02 = o0();
            N2(drawableP0);
            if (M2()) {
                i0(this.R);
            }
            invalidateSelf();
            if (fO0 != fO02) {
                t1();
            }
        }
    }

    public float b1() {
        return this.f4340e0;
    }

    public void b2(CharSequence charSequence) {
        if (this.V != charSequence) {
            this.V = g0.a.c().h(charSequence);
            invalidateSelf();
        }
    }

    public float c1() {
        return this.f4339d0;
    }

    public void c2(float f7) {
        if (this.f4344i0 != f7) {
            this.f4344i0 = f7;
            invalidateSelf();
            if (M2()) {
                t1();
            }
        }
    }

    public ColorStateList d1() {
        return this.J;
    }

    public void d2(int i7) {
        c2(this.f4346k0.getResources().getDimension(i7));
    }

    @Override // e3.g, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds.isEmpty() || getAlpha() == 0) {
            return;
        }
        int i7 = this.A0;
        int iA = i7 < 255 ? q2.a.a(canvas, bounds.left, bounds.top, bounds.right, bounds.bottom, i7) : 0;
        y0(canvas, bounds);
        v0(canvas, bounds);
        if (this.M0) {
            super.draw(canvas);
        }
        x0(canvas, bounds);
        A0(canvas, bounds);
        w0(canvas, bounds);
        u0(canvas, bounds);
        if (this.K0) {
            C0(canvas, bounds);
        }
        z0(canvas, bounds);
        B0(canvas, bounds);
        if (this.A0 < 255) {
            canvas.restoreToCount(iA);
        }
    }

    public p2.h e1() {
        return this.f4336a0;
    }

    public void e2(int i7) {
        a2(f.a.b(this.f4346k0, i7));
    }

    public CharSequence f1() {
        return this.K;
    }

    public void f2(float f7) {
        if (this.U != f7) {
            this.U = f7;
            invalidateSelf();
            if (M2()) {
                t1();
            }
        }
    }

    public d g1() {
        return this.f4353r0.d();
    }

    public void g2(int i7) {
        f2(this.f4346k0.getResources().getDimension(i7));
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
        return Math.min(Math.round(this.f4338c0 + k0() + this.f4341f0 + this.f4353r0.f(f1().toString()) + this.f4342g0 + o0() + this.f4345j0), this.L0);
    }

    @Override // e3.g, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // e3.g, android.graphics.drawable.Drawable
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

    public float h1() {
        return this.f4342g0;
    }

    public void h2(float f7) {
        if (this.f4343h0 != f7) {
            this.f4343h0 = f7;
            invalidateSelf();
            if (M2()) {
                t1();
            }
        }
    }

    public float i1() {
        return this.f4341f0;
    }

    public void i2(int i7) {
        h2(this.f4346k0.getResources().getDimension(i7));
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // e3.g, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return p1(this.D) || p1(this.E) || p1(this.H) || (this.G0 && p1(this.H0)) || r1(this.f4353r0.d()) || s0() || q1(this.M) || q1(this.Y) || p1(this.D0);
    }

    public boolean j2(int[] iArr) {
        if (Arrays.equals(this.F0, iArr)) {
            return false;
        }
        this.F0 = iArr;
        if (M2()) {
            return u1(getState(), iArr);
        }
        return false;
    }

    float k0() {
        if (L2() || K2()) {
            return this.f4339d0 + Y0() + this.f4340e0;
        }
        return 0.0f;
    }

    public boolean k1() {
        return this.G0;
    }

    public void k2(ColorStateList colorStateList) {
        if (this.T != colorStateList) {
            this.T = colorStateList;
            if (M2()) {
                a0.a.i(this.R, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void l2(int i7) {
        k2(f.a.a(this.f4346k0, i7));
    }

    public boolean m1() {
        return this.W;
    }

    public void m2(boolean z6) {
        if (this.Q != z6) {
            boolean zM2 = M2();
            this.Q = z6;
            boolean zM22 = M2();
            if (zM2 != zM22) {
                if (zM22) {
                    i0(this.R);
                } else {
                    N2(this.R);
                }
                invalidateSelf();
                t1();
            }
        }
    }

    public boolean n1() {
        return q1(this.R);
    }

    public void n2(InterfaceC0047a interfaceC0047a) {
        this.I0 = new WeakReference<>(interfaceC0047a);
    }

    float o0() {
        if (M2()) {
            return this.f4343h0 + this.U + this.f4344i0;
        }
        return 0.0f;
    }

    public boolean o1() {
        return this.Q;
    }

    public void o2(TextUtils.TruncateAt truncateAt) {
        this.J0 = truncateAt;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i7) {
        boolean zOnLayoutDirectionChanged = super.onLayoutDirectionChanged(i7);
        if (L2()) {
            zOnLayoutDirectionChanged |= a0.a.g(this.M, i7);
        }
        if (K2()) {
            zOnLayoutDirectionChanged |= a0.a.g(this.Y, i7);
        }
        if (M2()) {
            zOnLayoutDirectionChanged |= a0.a.g(this.R, i7);
        }
        if (!zOnLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i7) {
        boolean zOnLevelChange = super.onLevelChange(i7);
        if (L2()) {
            zOnLevelChange |= this.M.setLevel(i7);
        }
        if (K2()) {
            zOnLevelChange |= this.Y.setLevel(i7);
        }
        if (M2()) {
            zOnLevelChange |= this.R.setLevel(i7);
        }
        if (zOnLevelChange) {
            invalidateSelf();
        }
        return zOnLevelChange;
    }

    @Override // e3.g, android.graphics.drawable.Drawable, com.google.android.material.internal.h.b
    public boolean onStateChange(int[] iArr) {
        if (this.M0) {
            super.onStateChange(iArr);
        }
        return u1(iArr, U0());
    }

    public void p2(p2.h hVar) {
        this.f4337b0 = hVar;
    }

    public void q2(int i7) {
        p2(p2.h.c(this.f4346k0, i7));
    }

    Paint.Align r0(Rect rect, PointF pointF) {
        pointF.set(0.0f, 0.0f);
        Paint.Align align = Paint.Align.LEFT;
        if (this.K != null) {
            float fK0 = this.f4338c0 + k0() + this.f4341f0;
            if (a0.a.b(this) == 0) {
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

    public void r2(float f7) {
        if (this.f4340e0 != f7) {
            float fK0 = k0();
            this.f4340e0 = f7;
            float fK02 = k0();
            invalidateSelf();
            if (fK0 != fK02) {
                t1();
            }
        }
    }

    public void s2(int i7) {
        r2(this.f4346k0.getResources().getDimension(i7));
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j7) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j7);
        }
    }

    @Override // e3.g, android.graphics.drawable.Drawable
    public void setAlpha(int i7) {
        if (this.A0 != i7) {
            this.A0 = i7;
            invalidateSelf();
        }
    }

    @Override // e3.g, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.B0 != colorFilter) {
            this.B0 = colorFilter;
            invalidateSelf();
        }
    }

    @Override // e3.g, android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        if (this.D0 != colorStateList) {
            this.D0 = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // e3.g, android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        if (this.E0 != mode) {
            this.E0 = mode;
            this.C0 = w2.a.a(this, this.D0, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z6, boolean z7) {
        boolean visible = super.setVisible(z6, z7);
        if (L2()) {
            visible |= this.M.setVisible(z6, z7);
        }
        if (K2()) {
            visible |= this.Y.setVisible(z6, z7);
        }
        if (M2()) {
            visible |= this.R.setVisible(z6, z7);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    protected void t1() {
        InterfaceC0047a interfaceC0047a = this.I0.get();
        if (interfaceC0047a != null) {
            interfaceC0047a.a();
        }
    }

    public void t2(float f7) {
        if (this.f4339d0 != f7) {
            float fK0 = k0();
            this.f4339d0 = f7;
            float fK02 = k0();
            invalidateSelf();
            if (fK0 != fK02) {
                t1();
            }
        }
    }

    public void u2(int i7) {
        t2(this.f4346k0.getResources().getDimension(i7));
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public void v1(boolean z6) {
        if (this.W != z6) {
            this.W = z6;
            float fK0 = k0();
            if (!z6 && this.f4360y0) {
                this.f4360y0 = false;
            }
            float fK02 = k0();
            invalidateSelf();
            if (fK0 != fK02) {
                t1();
            }
        }
    }

    public void v2(int i7) {
        this.L0 = i7;
    }

    public void w1(int i7) {
        v1(this.f4346k0.getResources().getBoolean(i7));
    }

    public void w2(ColorStateList colorStateList) {
        if (this.J != colorStateList) {
            this.J = colorStateList;
            O2();
            onStateChange(getState());
        }
    }

    public void x1(Drawable drawable) {
        if (this.Y != drawable) {
            float fK0 = k0();
            this.Y = drawable;
            float fK02 = k0();
            N2(this.Y);
            i0(this.Y);
            invalidateSelf();
            if (fK0 != fK02) {
                t1();
            }
        }
    }

    public void x2(int i7) {
        w2(f.a.a(this.f4346k0, i7));
    }

    public void y1(int i7) {
        x1(f.a.b(this.f4346k0, i7));
    }

    void y2(boolean z6) {
        this.K0 = z6;
    }

    public void z1(ColorStateList colorStateList) {
        if (this.Z != colorStateList) {
            this.Z = colorStateList;
            if (s0()) {
                a0.a.i(this.Y, colorStateList);
            }
            onStateChange(getState());
        }
    }

    public void z2(p2.h hVar) {
        this.f4336a0 = hVar;
    }
}
