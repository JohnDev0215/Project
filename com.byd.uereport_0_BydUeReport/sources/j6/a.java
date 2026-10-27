package j6;

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
import b6.c;
import b6.d;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import e6.f;
import e6.g;
import e6.h;
import e6.j;
import z5.i;
import z5.k;

/* JADX INFO: compiled from: TooltipDrawable.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends h implements i.b {
    public static final int B = R$style.Widget_MaterialComponents_Tooltip;
    public static final int C = R$attr.tooltipStyle;
    public CharSequence D;
    public final Context E;
    public final Paint.FontMetrics F;
    public final i G;
    public final View.OnLayoutChangeListener H;
    public final Rect I;
    public int J;
    public int K;
    public int L;
    public int M;
    public int N;
    public int O;

    /* JADX INFO: renamed from: j6.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TooltipDrawable.java */
    public class ViewOnLayoutChangeListenerC0067a implements View.OnLayoutChangeListener {
        public ViewOnLayoutChangeListenerC0067a() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
            a.this.A0(view);
        }
    }

    public a(Context context, AttributeSet attributeSet, int i8, int i9) {
        super(context, attributeSet, i8, i9);
        this.F = new Paint.FontMetrics();
        i iVar = new i(this);
        this.G = iVar;
        this.H = new ViewOnLayoutChangeListenerC0067a();
        this.I = new Rect();
        this.E = context;
        iVar.e().density = context.getResources().getDisplayMetrics().density;
        iVar.e().setTextAlign(Paint.Align.CENTER);
    }

    public static a r0(Context context, AttributeSet attributeSet, int i8, int i9) {
        a aVar = new a(context, attributeSet, i8, i9);
        aVar.w0(attributeSet, i8, i9);
        return aVar;
    }

    public final void A0(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        this.O = iArr[0];
        view.getWindowVisibleDisplayFrame(this.I);
    }

    @Override // z5.i.b
    public void a() {
        invalidateSelf();
    }

    @Override // e6.h, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        canvas.save();
        canvas.translate(o0(), (float) (-((((double) this.N) * Math.sqrt(2.0d)) - ((double) this.N))));
        super.draw(canvas);
        u0(canvas);
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) Math.max(this.G.e().getTextSize(), this.L);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return (int) Math.max((this.J * 2) + v0(), this.K);
    }

    public final float o0() {
        int i8;
        if (((this.I.right - getBounds().right) - this.O) - this.M < 0) {
            i8 = ((this.I.right - getBounds().right) - this.O) - this.M;
        } else {
            if (((this.I.left - getBounds().left) - this.O) + this.M <= 0) {
                return 0.0f;
            }
            i8 = ((this.I.left - getBounds().left) - this.O) + this.M;
        }
        return i8;
    }

    @Override // e6.h, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        setShapeAppearanceModel(D().v().r(s0()).m());
    }

    @Override // e6.h, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    public final float p0() {
        this.G.e().getFontMetrics(this.F);
        Paint.FontMetrics fontMetrics = this.F;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    public final float q0(Rect rect) {
        return rect.centerY() - p0();
    }

    public final f s0() {
        float f8 = -o0();
        float fWidth = ((float) (((double) getBounds().width()) - (((double) this.N) * Math.sqrt(2.0d)))) / 2.0f;
        return new j(new g(this.N), Math.min(Math.max(f8, -fWidth), fWidth));
    }

    public void t0(View view) {
        if (view == null) {
            return;
        }
        view.removeOnLayoutChangeListener(this.H);
    }

    public final void u0(Canvas canvas) {
        if (this.D == null) {
            return;
        }
        int iQ0 = (int) q0(getBounds());
        if (this.G.d() != null) {
            this.G.e().drawableState = getState();
            this.G.j(this.E);
        }
        CharSequence charSequence = this.D;
        canvas.drawText(charSequence, 0, charSequence.length(), r0.centerX(), iQ0, this.G.e());
    }

    public final float v0() {
        CharSequence charSequence = this.D;
        if (charSequence == null) {
            return 0.0f;
        }
        return this.G.f(charSequence.toString());
    }

    public final void w0(AttributeSet attributeSet, int i8, int i9) {
        TypedArray typedArrayH = k.h(this.E, attributeSet, R$styleable.Tooltip, i8, i9, new int[0]);
        this.N = this.E.getResources().getDimensionPixelSize(R$dimen.mtrl_tooltip_arrowSize);
        setShapeAppearanceModel(D().v().r(s0()).m());
        y0(typedArrayH.getText(R$styleable.Tooltip_android_text));
        z0(c.f(this.E, typedArrayH, R$styleable.Tooltip_android_textAppearance));
        X(ColorStateList.valueOf(typedArrayH.getColor(R$styleable.Tooltip_backgroundTint, t5.a.e(g0.a.d(t5.a.b(this.E, R.attr.colorBackground, a.class.getCanonicalName()), 229), g0.a.d(t5.a.b(this.E, R$attr.colorOnBackground, a.class.getCanonicalName()), 153)))));
        i0(ColorStateList.valueOf(t5.a.b(this.E, R$attr.colorSurface, a.class.getCanonicalName())));
        this.J = typedArrayH.getDimensionPixelSize(R$styleable.Tooltip_android_padding, 0);
        this.K = typedArrayH.getDimensionPixelSize(R$styleable.Tooltip_android_minWidth, 0);
        this.L = typedArrayH.getDimensionPixelSize(R$styleable.Tooltip_android_minHeight, 0);
        this.M = typedArrayH.getDimensionPixelSize(R$styleable.Tooltip_android_layout_margin, 0);
        typedArrayH.recycle();
    }

    public void x0(View view) {
        if (view == null) {
            return;
        }
        A0(view);
        view.addOnLayoutChangeListener(this.H);
    }

    public void y0(CharSequence charSequence) {
        if (TextUtils.equals(this.D, charSequence)) {
            return;
        }
        this.D = charSequence;
        this.G.i(true);
        invalidateSelf();
    }

    public void z0(d dVar) {
        this.G.h(dVar, this.E);
    }
}
