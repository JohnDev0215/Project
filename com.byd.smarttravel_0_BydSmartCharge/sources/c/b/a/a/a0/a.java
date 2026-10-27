package c.b.a.a.a0;

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
import c.b.a.a.q.i;
import c.b.a.a.q.k;
import c.b.a.a.s.c;
import c.b.a.a.s.d;
import c.b.a.a.v.f;
import c.b.a.a.v.g;
import c.b.a.a.v.h;
import c.b.a.a.v.j;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;

/* JADX INFO: compiled from: TooltipDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends h implements i.b {
    public CharSequence B;
    public final Context C;
    public final Paint.FontMetrics D;
    public final i E;
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

    /* JADX INFO: renamed from: c.b.a.a.a0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TooltipDrawable.java */
    public class ViewOnLayoutChangeListenerC0048a implements View.OnLayoutChangeListener {
        public ViewOnLayoutChangeListenerC0048a() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            a.this.A0(view);
        }
    }

    public a(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.D = new Paint.FontMetrics();
        i iVar = new i(this);
        this.E = iVar;
        this.F = new ViewOnLayoutChangeListenerC0048a();
        this.G = new Rect();
        this.C = context;
        iVar.e().density = context.getResources().getDisplayMetrics().density;
        iVar.e().setTextAlign(Paint.Align.CENTER);
    }

    public static a r0(Context context, AttributeSet attributeSet, int i, int i2) {
        a aVar = new a(context, attributeSet, i, i2);
        aVar.w0(attributeSet, i, i2);
        return aVar;
    }

    public final void A0(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        this.M = iArr[0];
        view.getWindowVisibleDisplayFrame(this.G);
    }

    @Override // c.b.a.a.q.i.b
    public void a() {
        invalidateSelf();
    }

    @Override // c.b.a.a.v.h, android.graphics.drawable.Drawable
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

    @Override // c.b.a.a.v.h, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        setShapeAppearanceModel(D().v().r(s0()).m());
    }

    @Override // c.b.a.a.v.h, android.graphics.drawable.Drawable, c.b.a.a.q.i.b
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

    public final f s0() {
        float f = -o0();
        float fWidth = ((float) (((double) getBounds().width()) - (((double) this.L) * Math.sqrt(2.0d)))) / 2.0f;
        return new j(new g(this.L), Math.min(Math.max(f, -fWidth), fWidth));
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
        TypedArray typedArrayH = k.h(this.C, attributeSet, R$styleable.Tooltip, i, i2, new int[0]);
        this.L = this.C.getResources().getDimensionPixelSize(R$dimen.mtrl_tooltip_arrowSize);
        setShapeAppearanceModel(D().v().r(s0()).m());
        y0(typedArrayH.getText(R$styleable.Tooltip_android_text));
        z0(c.f(this.C, typedArrayH, R$styleable.Tooltip_android_textAppearance));
        X(ColorStateList.valueOf(typedArrayH.getColor(R$styleable.Tooltip_backgroundTint, c.b.a.a.k.a.e(b.g.c.a.d(c.b.a.a.k.a.b(this.C, R.attr.colorBackground, a.class.getCanonicalName()), 229), b.g.c.a.d(c.b.a.a.k.a.b(this.C, R$attr.colorOnBackground, a.class.getCanonicalName()), 153)))));
        i0(ColorStateList.valueOf(c.b.a.a.k.a.b(this.C, R$attr.colorSurface, a.class.getCanonicalName())));
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

    public void z0(d dVar) {
        this.E.h(dVar, this.C);
    }
}
