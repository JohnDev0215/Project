package defpackage;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.hj;

/* JADX INFO: compiled from: TooltipDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class gl extends hk implements hj.b {
    public CharSequence B;
    public final Context C;
    public final Paint.FontMetrics D;
    public final hj E;
    public final View.OnLayoutChangeListener F;
    public final Rect G;
    public int H;
    public int I;
    public int J;
    public int K;
    public int L;
    public int M;
    public static final int z = R$style.Widget_MaterialComponents_Tooltip;
    public static final int A = R$attr.tooltipStyle;

    /* JADX INFO: compiled from: TooltipDrawable.java */
    public class a implements View.OnLayoutChangeListener {
        public a() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            gl.this.A0(view);
        }
    }

    public gl(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.D = new Paint.FontMetrics();
        hj hjVar = new hj(this);
        this.E = hjVar;
        this.F = new a();
        this.G = new Rect();
        this.C = context;
        hjVar.e().density = context.getResources().getDisplayMetrics().density;
        hjVar.e().setTextAlign(Paint.Align.CENTER);
    }

    public static gl r0(Context context, AttributeSet attributeSet, int i, int i2) {
        gl glVar = new gl(context, attributeSet, i, i2);
        glVar.w0(attributeSet, i, i2);
        return glVar;
    }

    public final void A0(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        this.M = iArr[0];
        view.getWindowVisibleDisplayFrame(this.G);
    }

    @Override // hj.b
    public void a() {
        invalidateSelf();
    }

    @Override // defpackage.hk, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(o0(), (float) (-((((double) this.L) * Math.sqrt(2.0d)) - ((double) this.L))));
        super.draw(canvas);
        u0(canvas);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) Math.max(this.E.e().getTextSize(), this.J);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return (int) Math.max((this.H * 2) + v0(), this.I);
    }

    public final float o0() {
        int i;
        if (((this.G.right - getBounds().right) - this.M) - this.K < 0) {
            i = ((this.G.right - getBounds().right) - this.M) - this.K;
        } else {
            if (((this.G.left - getBounds().left) - this.M) + this.K <= 0) {
                return 0.0f;
            }
            i = ((this.G.left - getBounds().left) - this.M) + this.K;
        }
        return i;
    }

    @Override // defpackage.hk, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        setShapeAppearanceModel(D().v().r(s0()).m());
    }

    @Override // defpackage.hk, android.graphics.drawable.Drawable, hj.b
    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    public final float p0() {
        this.E.e().getFontMetrics(this.D);
        Paint.FontMetrics fontMetrics = this.D;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    public final float q0(Rect rect) {
        return rect.centerY() - p0();
    }

    public final fk s0() {
        float f = -o0();
        float fWidth = ((float) (((double) getBounds().width()) - (((double) this.L) * Math.sqrt(2.0d)))) / 2.0f;
        return new jk(new gk(this.L), Math.min(Math.max(f, -fWidth), fWidth));
    }

    public void t0(View view) {
        if (view == null) {
            return;
        }
        view.removeOnLayoutChangeListener(this.F);
    }

    public final void u0(Canvas canvas) {
        if (this.B == null) {
            return;
        }
        int iQ0 = (int) q0(getBounds());
        if (this.E.d() != null) {
            this.E.e().drawableState = getState();
            this.E.j(this.C);
        }
        CharSequence charSequence = this.B;
        canvas.drawText(charSequence, 0, charSequence.length(), r0.centerX(), iQ0, this.E.e());
    }

    public final float v0() {
        CharSequence charSequence = this.B;
        if (charSequence == null) {
            return 0.0f;
        }
        return this.E.f(charSequence.toString());
    }

    public final void w0(AttributeSet attributeSet, int i, int i2) {
        TypedArray typedArrayH = jj.h(this.C, attributeSet, R$styleable.Tooltip, i, i2, new int[0]);
        this.L = this.C.getResources().getDimensionPixelSize(R$dimen.mtrl_tooltip_arrowSize);
        setShapeAppearanceModel(D().v().r(s0()).m());
        y0(typedArrayH.getText(R$styleable.Tooltip_android_text));
        z0(sj.f(this.C, typedArrayH, R$styleable.Tooltip_android_textAppearance));
        X(ColorStateList.valueOf(typedArrayH.getColor(R$styleable.Tooltip_backgroundTint, bi.e(n7.d(bi.b(this.C, R.attr.colorBackground, gl.class.getCanonicalName()), 229), n7.d(bi.b(this.C, R$attr.colorOnBackground, gl.class.getCanonicalName()), 153)))));
        i0(ColorStateList.valueOf(bi.b(this.C, R$attr.colorSurface, gl.class.getCanonicalName())));
        this.H = typedArrayH.getDimensionPixelSize(R$styleable.Tooltip_android_padding, 0);
        this.I = typedArrayH.getDimensionPixelSize(R$styleable.Tooltip_android_minWidth, 0);
        this.J = typedArrayH.getDimensionPixelSize(R$styleable.Tooltip_android_minHeight, 0);
        this.K = typedArrayH.getDimensionPixelSize(R$styleable.Tooltip_android_layout_margin, 0);
        typedArrayH.recycle();
    }

    public void x0(View view) {
        if (view == null) {
            return;
        }
        A0(view);
        view.addOnLayoutChangeListener(this.F);
    }

    public void y0(CharSequence charSequence) {
        if (TextUtils.equals(this.B, charSequence)) {
            return;
        }
        this.B = charSequence;
        this.E.i(true);
        invalidateSelf();
    }

    public void z0(tj tjVar) {
        this.E.h(tjVar, this.C);
    }
}
