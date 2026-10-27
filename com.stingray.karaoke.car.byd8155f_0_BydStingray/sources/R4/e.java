package R4;

import Y4.j;
import Y4.k;
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
import z1.InterfaceC2753a;

/* JADX INFO: loaded from: classes.dex */
public final class e extends Y4.g implements Drawable.Callback, U4.h {

    /* JADX INFO: renamed from: O0, reason: collision with root package name */
    public static final int[] f7429O0 = {R.attr.state_enabled};

    /* JADX INFO: renamed from: P0, reason: collision with root package name */
    public static final ShapeDrawable f7430P0 = new ShapeDrawable(new OvalShape());

    /* JADX INFO: renamed from: A0, reason: collision with root package name */
    public boolean f7431A0;
    public int B0;

    /* JADX INFO: renamed from: C0, reason: collision with root package name */
    public int f7432C0;

    /* JADX INFO: renamed from: D0, reason: collision with root package name */
    public ColorFilter f7433D0;

    /* JADX INFO: renamed from: E0, reason: collision with root package name */
    public PorterDuffColorFilter f7434E0;

    /* JADX INFO: renamed from: F0, reason: collision with root package name */
    public ColorStateList f7435F0;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public ColorStateList f7436G;
    public PorterDuff.Mode G0;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public ColorStateList f7437H;

    /* JADX INFO: renamed from: H0, reason: collision with root package name */
    public int[] f7438H0;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public float f7439I;

    /* JADX INFO: renamed from: I0, reason: collision with root package name */
    public ColorStateList f7440I0;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public float f7441J;

    /* JADX INFO: renamed from: J0, reason: collision with root package name */
    public WeakReference f7442J0;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public ColorStateList f7443K;

    /* JADX INFO: renamed from: K0, reason: collision with root package name */
    public TextUtils.TruncateAt f7444K0;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public float f7445L;

    /* JADX INFO: renamed from: L0, reason: collision with root package name */
    public boolean f7446L0;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public ColorStateList f7447M;

    /* JADX INFO: renamed from: M0, reason: collision with root package name */
    public int f7448M0;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public CharSequence f7449N;

    /* JADX INFO: renamed from: N0, reason: collision with root package name */
    public boolean f7450N0;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public boolean f7451O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public Drawable f7452P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public ColorStateList f7453Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public float f7454R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public boolean f7455S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public boolean f7456T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public Drawable f7457U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public RippleDrawable f7458V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public ColorStateList f7459W;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public float f7460X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public SpannableStringBuilder f7461Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public boolean f7462Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public boolean f7463a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public Drawable f7464b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public ColorStateList f7465c0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public L4.b f7466d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public L4.b f7467e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public float f7468f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public float f7469g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public float f7470h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public float f7471i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public float f7472j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public float f7473k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public float f7474l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public float f7475m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public final Context f7476n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public final Paint f7477o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public final Paint.FontMetrics f7478p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public final RectF f7479q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public final PointF f7480r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public final Path f7481s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public final U4.i f7482t0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public int f7483u0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public int f7484v0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public int f7485w0;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public int f7486x0;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public int f7487y0;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public int f7488z0;

    public e(Context context, AttributeSet attributeSet) {
        super(k.a(context, attributeSet, com.stingray.drivemodedetector.R.attr.chipStyle, com.stingray.drivemodedetector.R.style.Widget_MaterialComponents_Chip_Action).a());
        this.f7441J = -1.0f;
        this.f7477o0 = new Paint(1);
        this.f7478p0 = new Paint.FontMetrics();
        this.f7479q0 = new RectF();
        this.f7480r0 = new PointF();
        this.f7481s0 = new Path();
        this.f7432C0 = 255;
        this.G0 = PorterDuff.Mode.SRC_IN;
        this.f7442J0 = new WeakReference(null);
        f(context);
        this.f7476n0 = context;
        U4.i iVar = new U4.i(this);
        this.f7482t0 = iVar;
        this.f7449N = "";
        iVar.f9071a.density = context.getResources().getDisplayMetrics().density;
        int[] iArr = f7429O0;
        setState(iArr);
        if (!Arrays.equals(this.f7438H0, iArr)) {
            this.f7438H0 = iArr;
            if (R()) {
                t(getState(), iArr);
            }
        }
        this.f7446L0 = true;
        f7430P0.setTint(-1);
    }

    public static void S(Drawable drawable) {
        if (drawable != null) {
            drawable.setCallback(null);
        }
    }

    public static boolean q(ColorStateList colorStateList) {
        return colorStateList != null && colorStateList.isStateful();
    }

    public static boolean r(Drawable drawable) {
        return drawable != null && drawable.isStateful();
    }

    public final void A(float f10) {
        if (this.f7454R != f10) {
            float fN = n();
            this.f7454R = f10;
            float fN2 = n();
            invalidateSelf();
            if (fN != fN2) {
                s();
            }
        }
    }

    public final void B(ColorStateList colorStateList) {
        this.f7455S = true;
        if (this.f7453Q != colorStateList) {
            this.f7453Q = colorStateList;
            if (Q()) {
                this.f7452P.setTintList(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void C(boolean z6) {
        if (this.f7451O != z6) {
            boolean zQ = Q();
            this.f7451O = z6;
            boolean zQ2 = Q();
            if (zQ != zQ2) {
                if (zQ2) {
                    l(this.f7452P);
                } else {
                    S(this.f7452P);
                }
                invalidateSelf();
                s();
            }
        }
    }

    public final void D(ColorStateList colorStateList) {
        if (this.f7443K != colorStateList) {
            this.f7443K = colorStateList;
            if (this.f7450N0) {
                Y4.f fVar = this.l;
                if (fVar.d != colorStateList) {
                    fVar.d = colorStateList;
                    onStateChange(getState());
                }
            }
            onStateChange(getState());
        }
    }

    public final void E(float f10) {
        if (this.f7445L != f10) {
            this.f7445L = f10;
            this.f7477o0.setStrokeWidth(f10);
            if (this.f7450N0) {
                this.l.f10715j = f10;
                invalidateSelf();
            }
            invalidateSelf();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.graphics.drawable.Drawable] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    public final void F(Drawable drawable) {
        ?? r02;
        Object obj = this.f7457U;
        if (obj != null) {
            boolean z6 = obj instanceof InterfaceC2753a;
            r02 = obj;
            if (z6) {
                r02 = 0;
            }
        } else {
            r02 = 0;
        }
        if (r02 != drawable) {
            float fO = o();
            this.f7457U = drawable != null ? drawable.mutate() : null;
            ColorStateList colorStateListValueOf = this.f7447M;
            if (colorStateListValueOf == null) {
                colorStateListValueOf = ColorStateList.valueOf(0);
            }
            this.f7458V = new RippleDrawable(colorStateListValueOf, this.f7457U, f7430P0);
            float fO2 = o();
            S(r02);
            if (R()) {
                l(this.f7457U);
            }
            invalidateSelf();
            if (fO != fO2) {
                s();
            }
        }
    }

    public final void G(float f10) {
        if (this.f7474l0 != f10) {
            this.f7474l0 = f10;
            invalidateSelf();
            if (R()) {
                s();
            }
        }
    }

    public final void H(float f10) {
        if (this.f7460X != f10) {
            this.f7460X = f10;
            invalidateSelf();
            if (R()) {
                s();
            }
        }
    }

    public final void I(float f10) {
        if (this.f7473k0 != f10) {
            this.f7473k0 = f10;
            invalidateSelf();
            if (R()) {
                s();
            }
        }
    }

    public final void J(ColorStateList colorStateList) {
        if (this.f7459W != colorStateList) {
            this.f7459W = colorStateList;
            if (R()) {
                this.f7457U.setTintList(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void K(boolean z6) {
        if (this.f7456T != z6) {
            boolean zR = R();
            this.f7456T = z6;
            boolean zR2 = R();
            if (zR != zR2) {
                if (zR2) {
                    l(this.f7457U);
                } else {
                    S(this.f7457U);
                }
                invalidateSelf();
                s();
            }
        }
    }

    public final void L(float f10) {
        if (this.f7470h0 != f10) {
            float fN = n();
            this.f7470h0 = f10;
            float fN2 = n();
            invalidateSelf();
            if (fN != fN2) {
                s();
            }
        }
    }

    public final void M(float f10) {
        if (this.f7469g0 != f10) {
            float fN = n();
            this.f7469g0 = f10;
            float fN2 = n();
            invalidateSelf();
            if (fN != fN2) {
                s();
            }
        }
    }

    public final void N(ColorStateList colorStateList) {
        if (this.f7447M != colorStateList) {
            this.f7447M = colorStateList;
            this.f7440I0 = null;
            onStateChange(getState());
        }
    }

    public final void O(V4.c cVar) {
        U4.i iVar = this.f7482t0;
        if (iVar.f9074f != cVar) {
            iVar.f9074f = cVar;
            if (cVar != null) {
                TextPaint textPaint = iVar.f9071a;
                Context context = this.f7476n0;
                a aVar = iVar.f9072b;
                cVar.f(context, textPaint, aVar);
                U4.h hVar = (U4.h) iVar.f9073e.get();
                if (hVar != null) {
                    textPaint.drawableState = hVar.getState();
                }
                cVar.e(context, textPaint, aVar);
                iVar.d = true;
            }
            U4.h hVar2 = (U4.h) iVar.f9073e.get();
            if (hVar2 != null) {
                e eVar = (e) hVar2;
                eVar.s();
                eVar.invalidateSelf();
                eVar.onStateChange(hVar2.getState());
            }
        }
    }

    public final boolean P() {
        return this.f7463a0 && this.f7464b0 != null && this.f7431A0;
    }

    public final boolean Q() {
        return this.f7451O && this.f7452P != null;
    }

    public final boolean R() {
        return this.f7456T && this.f7457U != null;
    }

    @Override // Y4.g, android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        int i7;
        RectF rectF;
        int i10;
        int i11;
        int i12;
        RectF rectF2;
        float f10;
        int iSave;
        Rect bounds = getBounds();
        if (bounds.isEmpty() || (i7 = this.f7432C0) == 0) {
            return;
        }
        int iSaveLayerAlpha = i7 < 255 ? canvas.saveLayerAlpha(bounds.left, bounds.top, bounds.right, bounds.bottom, i7) : 0;
        boolean z6 = this.f7450N0;
        Paint paint = this.f7477o0;
        RectF rectF3 = this.f7479q0;
        if (!z6) {
            paint.setColor(this.f7483u0);
            paint.setStyle(Paint.Style.FILL);
            rectF3.set(bounds);
            canvas.drawRoundRect(rectF3, p(), p(), paint);
        }
        if (!this.f7450N0) {
            paint.setColor(this.f7484v0);
            paint.setStyle(Paint.Style.FILL);
            ColorFilter colorFilter = this.f7433D0;
            if (colorFilter == null) {
                colorFilter = this.f7434E0;
            }
            paint.setColorFilter(colorFilter);
            rectF3.set(bounds);
            canvas.drawRoundRect(rectF3, p(), p(), paint);
        }
        if (this.f7450N0) {
            super.draw(canvas);
        }
        if (this.f7445L > 0.0f && !this.f7450N0) {
            paint.setColor(this.f7486x0);
            paint.setStyle(Paint.Style.STROKE);
            if (!this.f7450N0) {
                ColorFilter colorFilter2 = this.f7433D0;
                if (colorFilter2 == null) {
                    colorFilter2 = this.f7434E0;
                }
                paint.setColorFilter(colorFilter2);
            }
            float f11 = bounds.left;
            float f12 = this.f7445L / 2.0f;
            rectF3.set(f11 + f12, bounds.top + f12, bounds.right - f12, bounds.bottom - f12);
            float f13 = this.f7441J - (this.f7445L / 2.0f);
            canvas.drawRoundRect(rectF3, f13, f13, paint);
        }
        paint.setColor(this.f7487y0);
        paint.setStyle(Paint.Style.FILL);
        rectF3.set(bounds);
        if (this.f7450N0) {
            RectF rectF4 = new RectF(bounds);
            Path path = this.f7481s0;
            Y4.f fVar = this.l;
            this.B.b(fVar.f10708a, fVar.f10714i, rectF4, this.f10721A, path);
            c(canvas, paint, path, this.l.f10708a, d());
        } else {
            canvas.drawRoundRect(rectF3, p(), p(), paint);
        }
        if (Q()) {
            m(bounds, rectF3);
            float f14 = rectF3.left;
            float f15 = rectF3.top;
            canvas.translate(f14, f15);
            this.f7452P.setBounds(0, 0, (int) rectF3.width(), (int) rectF3.height());
            this.f7452P.draw(canvas);
            canvas.translate(-f14, -f15);
        }
        if (P()) {
            m(bounds, rectF3);
            float f16 = rectF3.left;
            float f17 = rectF3.top;
            canvas.translate(f16, f17);
            this.f7464b0.setBounds(0, 0, (int) rectF3.width(), (int) rectF3.height());
            this.f7464b0.draw(canvas);
            canvas.translate(-f16, -f17);
        }
        if (!this.f7446L0 || this.f7449N == null) {
            rectF = rectF3;
            i10 = iSaveLayerAlpha;
            i11 = 255;
            i12 = 0;
        } else {
            PointF pointF = this.f7480r0;
            pointF.set(0.0f, 0.0f);
            Paint.Align align = Paint.Align.LEFT;
            CharSequence charSequence = this.f7449N;
            U4.i iVar = this.f7482t0;
            if (charSequence != null) {
                float fN = n() + this.f7468f0 + this.f7471i0;
                if (getLayoutDirection() == 0) {
                    pointF.x = bounds.left + fN;
                } else {
                    pointF.x = bounds.right - fN;
                    align = Paint.Align.RIGHT;
                }
                float fCenterY = bounds.centerY();
                TextPaint textPaint = iVar.f9071a;
                Paint.FontMetrics fontMetrics = this.f7478p0;
                textPaint.getFontMetrics(fontMetrics);
                pointF.y = fCenterY - ((fontMetrics.descent + fontMetrics.ascent) / 2.0f);
            }
            rectF3.setEmpty();
            if (this.f7449N != null) {
                float fN2 = n() + this.f7468f0 + this.f7471i0;
                float fO = o() + this.f7475m0 + this.f7472j0;
                if (getLayoutDirection() == 0) {
                    rectF3.left = bounds.left + fN2;
                    rectF3.right = bounds.right - fO;
                } else {
                    rectF3.left = bounds.left + fO;
                    rectF3.right = bounds.right - fN2;
                }
                rectF3.top = bounds.top;
                rectF3.bottom = bounds.bottom;
            }
            V4.c cVar = iVar.f9074f;
            TextPaint textPaint2 = iVar.f9071a;
            if (cVar != null) {
                textPaint2.drawableState = getState();
                iVar.f9074f.e(this.f7476n0, textPaint2, iVar.f9072b);
            }
            textPaint2.setTextAlign(align);
            String string = this.f7449N.toString();
            if (iVar.d) {
                float fMeasureText = string != null ? textPaint2.measureText((CharSequence) string, 0, string.length()) : 0.0f;
                iVar.c = fMeasureText;
                iVar.d = false;
                f10 = fMeasureText;
            } else {
                f10 = iVar.c;
            }
            boolean z10 = Math.round(f10) > Math.round(rectF3.width());
            if (z10) {
                iSave = canvas.save();
                canvas.clipRect(rectF3);
            } else {
                iSave = 0;
            }
            CharSequence charSequenceEllipsize = this.f7449N;
            if (z10 && this.f7444K0 != null) {
                charSequenceEllipsize = TextUtils.ellipsize(charSequenceEllipsize, textPaint2, rectF3.width(), this.f7444K0);
            }
            CharSequence charSequence2 = charSequenceEllipsize;
            int length = charSequence2.length();
            float f18 = pointF.x;
            float f19 = pointF.y;
            rectF = rectF3;
            i10 = iSaveLayerAlpha;
            i11 = 255;
            i12 = 0;
            canvas.drawText(charSequence2, 0, length, f18, f19, textPaint2);
            if (z10) {
                canvas.restoreToCount(iSave);
            }
        }
        if (R()) {
            rectF.setEmpty();
            if (R()) {
                float f20 = this.f7475m0 + this.f7474l0;
                if (getLayoutDirection() == 0) {
                    float f21 = bounds.right - f20;
                    rectF2 = rectF;
                    rectF2.right = f21;
                    rectF2.left = f21 - this.f7460X;
                } else {
                    rectF2 = rectF;
                    float f22 = bounds.left + f20;
                    rectF2.left = f22;
                    rectF2.right = f22 + this.f7460X;
                }
                float fExactCenterY = bounds.exactCenterY();
                float f23 = this.f7460X;
                float f24 = fExactCenterY - (f23 / 2.0f);
                rectF2.top = f24;
                rectF2.bottom = f24 + f23;
            } else {
                rectF2 = rectF;
            }
            float f25 = rectF2.left;
            float f26 = rectF2.top;
            canvas.translate(f25, f26);
            this.f7457U.setBounds(i12, i12, (int) rectF2.width(), (int) rectF2.height());
            this.f7458V.setBounds(this.f7457U.getBounds());
            this.f7458V.jumpToCurrentState();
            this.f7458V.draw(canvas);
            canvas.translate(-f25, -f26);
        }
        if (this.f7432C0 < i11) {
            canvas.restoreToCount(i10);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final int getAlpha() {
        return this.f7432C0;
    }

    @Override // android.graphics.drawable.Drawable
    public final ColorFilter getColorFilter() {
        return this.f7433D0;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return (int) this.f7439I;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        float fMeasureText;
        float fN = n() + this.f7468f0 + this.f7471i0;
        String string = this.f7449N.toString();
        U4.i iVar = this.f7482t0;
        if (iVar.d) {
            fMeasureText = string == null ? 0.0f : iVar.f9071a.measureText((CharSequence) string, 0, string.length());
            iVar.c = fMeasureText;
            iVar.d = false;
        } else {
            fMeasureText = iVar.c;
        }
        return Math.min(Math.round(o() + fMeasureText + fN + this.f7472j0 + this.f7475m0), this.f7448M0);
    }

    @Override // Y4.g, android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -3;
    }

    @Override // Y4.g, android.graphics.drawable.Drawable
    public final void getOutline(Outline outline) {
        if (this.f7450N0) {
            super.getOutline(outline);
            return;
        }
        Rect bounds = getBounds();
        if (bounds.isEmpty()) {
            outline.setRoundRect(0, 0, getIntrinsicWidth(), (int) this.f7439I, this.f7441J);
        } else {
            outline.setRoundRect(bounds, this.f7441J);
        }
        outline.setAlpha(this.f7432C0 / 255.0f);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override // Y4.g, android.graphics.drawable.Drawable
    public final boolean isStateful() {
        V4.c cVar;
        ColorStateList colorStateList;
        return q(this.f7436G) || q(this.f7437H) || q(this.f7443K) || !((cVar = this.f7482t0.f9074f) == null || (colorStateList = cVar.f9465a) == null || !colorStateList.isStateful()) || ((this.f7463a0 && this.f7464b0 != null && this.f7462Z) || r(this.f7452P) || r(this.f7464b0) || q(this.f7435F0));
    }

    public final void l(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        drawable.setCallback(this);
        drawable.setLayoutDirection(getLayoutDirection());
        drawable.setLevel(getLevel());
        drawable.setVisible(isVisible(), false);
        if (drawable == this.f7457U) {
            if (drawable.isStateful()) {
                drawable.setState(this.f7438H0);
            }
            drawable.setTintList(this.f7459W);
            return;
        }
        if (drawable.isStateful()) {
            drawable.setState(getState());
        }
        Drawable drawable2 = this.f7452P;
        if (drawable == drawable2 && this.f7455S) {
            drawable2.setTintList(this.f7453Q);
        }
    }

    public final void m(Rect rect, RectF rectF) {
        rectF.setEmpty();
        if (Q() || P()) {
            float f10 = this.f7468f0 + this.f7469g0;
            Drawable drawable = this.f7431A0 ? this.f7464b0 : this.f7452P;
            float intrinsicWidth = this.f7454R;
            if (intrinsicWidth <= 0.0f && drawable != null) {
                intrinsicWidth = drawable.getIntrinsicWidth();
            }
            if (getLayoutDirection() == 0) {
                float f11 = rect.left + f10;
                rectF.left = f11;
                rectF.right = f11 + intrinsicWidth;
            } else {
                float f12 = rect.right - f10;
                rectF.right = f12;
                rectF.left = f12 - intrinsicWidth;
            }
            Drawable drawable2 = this.f7431A0 ? this.f7464b0 : this.f7452P;
            float fCeil = this.f7454R;
            if (fCeil <= 0.0f && drawable2 != null) {
                fCeil = (float) Math.ceil(TypedValue.applyDimension(1, 24, this.f7476n0.getResources().getDisplayMetrics()));
                if (drawable2.getIntrinsicHeight() <= fCeil) {
                    fCeil = drawable2.getIntrinsicHeight();
                }
            }
            float fExactCenterY = rect.exactCenterY() - (fCeil / 2.0f);
            rectF.top = fExactCenterY;
            rectF.bottom = fExactCenterY + fCeil;
        }
    }

    public final float n() {
        if (!Q() && !P()) {
            return 0.0f;
        }
        float f10 = this.f7469g0;
        Drawable drawable = this.f7431A0 ? this.f7464b0 : this.f7452P;
        float intrinsicWidth = this.f7454R;
        if (intrinsicWidth <= 0.0f && drawable != null) {
            intrinsicWidth = drawable.getIntrinsicWidth();
        }
        return intrinsicWidth + f10 + this.f7470h0;
    }

    public final float o() {
        if (R()) {
            return this.f7473k0 + this.f7460X + this.f7474l0;
        }
        return 0.0f;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLayoutDirectionChanged(int i7) {
        boolean zOnLayoutDirectionChanged = super.onLayoutDirectionChanged(i7);
        if (Q()) {
            zOnLayoutDirectionChanged |= this.f7452P.setLayoutDirection(i7);
        }
        if (P()) {
            zOnLayoutDirectionChanged |= this.f7464b0.setLayoutDirection(i7);
        }
        if (R()) {
            zOnLayoutDirectionChanged |= this.f7457U.setLayoutDirection(i7);
        }
        if (!zOnLayoutDirectionChanged) {
            return true;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onLevelChange(int i7) {
        boolean zOnLevelChange = super.onLevelChange(i7);
        if (Q()) {
            zOnLevelChange |= this.f7452P.setLevel(i7);
        }
        if (P()) {
            zOnLevelChange |= this.f7464b0.setLevel(i7);
        }
        if (R()) {
            zOnLevelChange |= this.f7457U.setLevel(i7);
        }
        if (zOnLevelChange) {
            invalidateSelf();
        }
        return zOnLevelChange;
    }

    @Override // Y4.g, android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        if (this.f7450N0) {
            super.onStateChange(iArr);
        }
        return t(iArr, this.f7438H0);
    }

    public final float p() {
        return this.f7450N0 ? this.l.f10708a.f10750e.a(d()) : this.f7441J;
    }

    public final void s() {
        d dVar = (d) this.f7442J0.get();
        if (dVar != null) {
            Chip chip = (Chip) dVar;
            chip.c(chip.f12820A);
            chip.requestLayout();
            chip.invalidateOutline();
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j3) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, runnable, j3);
        }
    }

    @Override // Y4.g, android.graphics.drawable.Drawable
    public final void setAlpha(int i7) {
        if (this.f7432C0 != i7) {
            this.f7432C0 = i7;
            invalidateSelf();
        }
    }

    @Override // Y4.g, android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        if (this.f7433D0 != colorFilter) {
            this.f7433D0 = colorFilter;
            invalidateSelf();
        }
    }

    @Override // Y4.g, android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        if (this.f7435F0 != colorStateList) {
            this.f7435F0 = colorStateList;
            onStateChange(getState());
        }
    }

    @Override // Y4.g, android.graphics.drawable.Drawable
    public final void setTintMode(PorterDuff.Mode mode) {
        if (this.G0 != mode) {
            this.G0 = mode;
            ColorStateList colorStateList = this.f7435F0;
            this.f7434E0 = (colorStateList == null || mode == null) ? null : new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z6, boolean z10) {
        boolean visible = super.setVisible(z6, z10);
        if (Q()) {
            visible |= this.f7452P.setVisible(z6, z10);
        }
        if (P()) {
            visible |= this.f7464b0.setVisible(z6, z10);
        }
        if (R()) {
            visible |= this.f7457U.setVisible(z6, z10);
        }
        if (visible) {
            invalidateSelf();
        }
        return visible;
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x00a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean t(int[] r12, int[] r13) {
        /*
            Method dump skipped, instruction units count: 383
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: R4.e.t(int[], int[]):boolean");
    }

    public final void u(boolean z6) {
        if (this.f7462Z != z6) {
            this.f7462Z = z6;
            float fN = n();
            if (!z6 && this.f7431A0) {
                this.f7431A0 = false;
            }
            float fN2 = n();
            invalidateSelf();
            if (fN != fN2) {
                s();
            }
        }
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, runnable);
        }
    }

    public final void v(Drawable drawable) {
        if (this.f7464b0 != drawable) {
            float fN = n();
            this.f7464b0 = drawable;
            float fN2 = n();
            S(this.f7464b0);
            l(this.f7464b0);
            invalidateSelf();
            if (fN != fN2) {
                s();
            }
        }
    }

    public final void w(ColorStateList colorStateList) {
        Drawable drawable;
        if (this.f7465c0 != colorStateList) {
            this.f7465c0 = colorStateList;
            if (this.f7463a0 && (drawable = this.f7464b0) != null && this.f7462Z) {
                drawable.setTintList(colorStateList);
            }
            onStateChange(getState());
        }
    }

    public final void x(boolean z6) {
        if (this.f7463a0 != z6) {
            boolean zP = P();
            this.f7463a0 = z6;
            boolean zP2 = P();
            if (zP != zP2) {
                if (zP2) {
                    l(this.f7464b0);
                } else {
                    S(this.f7464b0);
                }
                invalidateSelf();
                s();
            }
        }
    }

    public final void y(float f10) {
        if (this.f7441J != f10) {
            this.f7441J = f10;
            j jVarD = this.l.f10708a.d();
            jVarD.f10741e = new Y4.a(f10);
            jVarD.f10742f = new Y4.a(f10);
            jVarD.f10743g = new Y4.a(f10);
            jVarD.f10744h = new Y4.a(f10);
            setShapeAppearanceModel(jVarD.a());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [android.graphics.drawable.Drawable] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    public final void z(Drawable drawable) {
        ?? r02;
        Object obj = this.f7452P;
        if (obj != null) {
            boolean z6 = obj instanceof InterfaceC2753a;
            r02 = obj;
            if (z6) {
                r02 = 0;
            }
        } else {
            r02 = 0;
        }
        if (r02 != drawable) {
            float fN = n();
            this.f7452P = drawable != null ? drawable.mutate() : null;
            float fN2 = n();
            S(r02);
            if (Q()) {
                l(this.f7452P);
            }
            invalidateSelf();
            if (fN != fN2) {
                s();
            }
        }
    }
}
