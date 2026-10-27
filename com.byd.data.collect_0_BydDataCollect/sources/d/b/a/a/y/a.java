package d.b.a.a.y;

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
import com.google.android.material.internal.j;
import com.google.android.material.internal.l;
import d.b.a.a.b;
import d.b.a.a.d;
import d.b.a.a.k;
import d.b.a.a.u.c;
import d.b.a.a.x.f;
import d.b.a.a.x.g;
import d.b.a.a.x.h;

/* JADX INFO: compiled from: TooltipDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends h implements j.b {
    private CharSequence B;
    private final Context C;
    private final Paint.FontMetrics D;
    private final j E;
    private final View.OnLayoutChangeListener F;
    private final Rect G;
    private int H;
    private int I;
    private int J;
    private int K;
    private int L;
    private int M;
    private static final int z = k.Widget_MaterialComponents_Tooltip;
    private static final int A = b.tooltipStyle;

    /* JADX INFO: renamed from: d.b.a.a.y.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TooltipDrawable.java */
    class ViewOnLayoutChangeListenerC0090a implements View.OnLayoutChangeListener {
        ViewOnLayoutChangeListenerC0090a() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            a.this.A0(view);
        }
    }

    private a(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.D = new Paint.FontMetrics();
        j jVar = new j(this);
        this.E = jVar;
        this.F = new ViewOnLayoutChangeListenerC0090a();
        this.G = new Rect();
        this.C = context;
        jVar.e().density = context.getResources().getDisplayMetrics().density;
        jVar.e().setTextAlign(Paint.Align.CENTER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A0(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        this.M = iArr[0];
        view.getWindowVisibleDisplayFrame(this.G);
    }

    private float o0() {
        int i2;
        if (((this.G.right - getBounds().right) - this.M) - this.K < 0) {
            i2 = ((this.G.right - getBounds().right) - this.M) - this.K;
        } else {
            if (((this.G.left - getBounds().left) - this.M) + this.K <= 0) {
                return 0.0f;
            }
            i2 = ((this.G.left - getBounds().left) - this.M) + this.K;
        }
        return i2;
    }

    private float p0() {
        this.E.e().getFontMetrics(this.D);
        Paint.FontMetrics fontMetrics = this.D;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    private float q0(Rect rect) {
        return rect.centerY() - p0();
    }

    public static a r0(Context context, AttributeSet attributeSet, int i2, int i3) {
        a aVar = new a(context, attributeSet, i2, i3);
        aVar.w0(attributeSet, i2, i3);
        return aVar;
    }

    private f s0() {
        float f2 = -o0();
        float fWidth = ((float) (((double) getBounds().width()) - (((double) this.L) * Math.sqrt(2.0d)))) / 2.0f;
        return new d.b.a.a.x.j(new g(this.L), Math.min(Math.max(f2, -fWidth), fWidth));
    }

    private void u0(Canvas canvas) {
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

    private float v0() {
        CharSequence charSequence = this.B;
        if (charSequence == null) {
            return 0.0f;
        }
        return this.E.f(charSequence.toString());
    }

    private void w0(AttributeSet attributeSet, int i2, int i3) {
        TypedArray typedArrayH = l.h(this.C, attributeSet, d.b.a.a.l.Tooltip, i2, i3, new int[0]);
        this.L = this.C.getResources().getDimensionPixelSize(d.mtrl_tooltip_arrowSize);
        setShapeAppearanceModel(D().v().r(s0()).m());
        y0(typedArrayH.getText(d.b.a.a.l.Tooltip_android_text));
        z0(c.f(this.C, typedArrayH, d.b.a.a.l.Tooltip_android_textAppearance));
        X(ColorStateList.valueOf(typedArrayH.getColor(d.b.a.a.l.Tooltip_backgroundTint, d.b.a.a.o.a.e(b.g.d.a.d(d.b.a.a.o.a.b(this.C, R.attr.colorBackground, a.class.getCanonicalName()), 229), b.g.d.a.d(d.b.a.a.o.a.b(this.C, b.colorOnBackground, a.class.getCanonicalName()), 153)))));
        i0(ColorStateList.valueOf(d.b.a.a.o.a.b(this.C, b.colorSurface, a.class.getCanonicalName())));
        this.H = typedArrayH.getDimensionPixelSize(d.b.a.a.l.Tooltip_android_padding, 0);
        this.I = typedArrayH.getDimensionPixelSize(d.b.a.a.l.Tooltip_android_minWidth, 0);
        this.J = typedArrayH.getDimensionPixelSize(d.b.a.a.l.Tooltip_android_minHeight, 0);
        this.K = typedArrayH.getDimensionPixelSize(d.b.a.a.l.Tooltip_android_layout_margin, 0);
        typedArrayH.recycle();
    }

    @Override // com.google.android.material.internal.j.b
    public void a() {
        invalidateSelf();
    }

    @Override // d.b.a.a.x.h, android.graphics.drawable.Drawable
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

    @Override // d.b.a.a.x.h, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        setShapeAppearanceModel(D().v().r(s0()).m());
    }

    @Override // d.b.a.a.x.h, android.graphics.drawable.Drawable, com.google.android.material.internal.j.b
    public boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    public void t0(View view) {
        if (view == null) {
            return;
        }
        view.removeOnLayoutChangeListener(this.F);
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

    public void z0(d.b.a.a.u.d dVar) {
        this.E.h(dVar, this.C);
    }
}
