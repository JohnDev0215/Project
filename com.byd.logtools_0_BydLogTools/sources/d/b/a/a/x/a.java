package d.b.a.a.x;

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
import com.google.android.material.internal.i;
import d.b.a.a.b;
import d.b.a.a.d;
import d.b.a.a.k;
import d.b.a.a.l;
import d.b.a.a.t.c;
import d.b.a.a.w.f;
import d.b.a.a.w.g;
import d.b.a.a.w.h;
import d.b.a.a.w.j;

/* JADX INFO: compiled from: TooltipDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends h implements i.b {
    private CharSequence B;
    private final Context C;
    private final Paint.FontMetrics D;
    private final i E;
    private final View.OnLayoutChangeListener F;
    private final Rect G;
    private int H;
    private int I;
    private int J;
    private int K;
    private int L;
    private int M;
    private static final int z = k.E;
    private static final int A = b.P;

    /* JADX INFO: renamed from: d.b.a.a.x.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: TooltipDrawable.java */
    class ViewOnLayoutChangeListenerC0086a implements View.OnLayoutChangeListener {
        ViewOnLayoutChangeListenerC0086a() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            a.this.A0(view);
        }
    }

    private a(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.D = new Paint.FontMetrics();
        i iVar = new i(this);
        this.E = iVar;
        this.F = new ViewOnLayoutChangeListenerC0086a();
        this.G = new Rect();
        this.C = context;
        iVar.e().density = context.getResources().getDisplayMetrics().density;
        iVar.e().setTextAlign(Paint.Align.CENTER);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void A0(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        this.M = iArr[0];
        view.getWindowVisibleDisplayFrame(this.G);
    }

    private float o0() {
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

    private float p0() {
        this.E.e().getFontMetrics(this.D);
        Paint.FontMetrics fontMetrics = this.D;
        return (fontMetrics.descent + fontMetrics.ascent) / 2.0f;
    }

    private float q0(Rect rect) {
        return rect.centerY() - p0();
    }

    public static a r0(Context context, AttributeSet attributeSet, int i, int i2) {
        a aVar = new a(context, attributeSet, i, i2);
        aVar.w0(attributeSet, i, i2);
        return aVar;
    }

    private f s0() {
        float f2 = -o0();
        float fWidth = ((float) (((double) getBounds().width()) - (((double) this.L) * Math.sqrt(2.0d)))) / 2.0f;
        return new j(new g(this.L), Math.min(Math.max(f2, -fWidth), fWidth));
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

    private void w0(AttributeSet attributeSet, int i, int i2) {
        TypedArray typedArrayH = com.google.android.material.internal.k.h(this.C, attributeSet, l.W7, i, i2, new int[0]);
        this.L = this.C.getResources().getDimensionPixelSize(d.T);
        setShapeAppearanceModel(D().v().r(s0()).m());
        y0(typedArrayH.getText(l.c8));
        z0(c.f(this.C, typedArrayH, l.X7));
        X(ColorStateList.valueOf(typedArrayH.getColor(l.d8, d.b.a.a.o.a.e(b.g.d.a.d(d.b.a.a.o.a.b(this.C, R.attr.colorBackground, a.class.getCanonicalName()), 229), b.g.d.a.d(d.b.a.a.o.a.b(this.C, b.l, a.class.getCanonicalName()), 153)))));
        i0(ColorStateList.valueOf(d.b.a.a.o.a.b(this.C, b.p, a.class.getCanonicalName())));
        this.H = typedArrayH.getDimensionPixelSize(l.Y7, 0);
        this.I = typedArrayH.getDimensionPixelSize(l.a8, 0);
        this.J = typedArrayH.getDimensionPixelSize(l.b8, 0);
        this.K = typedArrayH.getDimensionPixelSize(l.Z7, 0);
        typedArrayH.recycle();
    }

    @Override // com.google.android.material.internal.i.b
    public void a() {
        invalidateSelf();
    }

    @Override // d.b.a.a.w.h, android.graphics.drawable.Drawable
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

    @Override // d.b.a.a.w.h, android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        setShapeAppearanceModel(D().v().r(s0()).m());
    }

    @Override // d.b.a.a.w.h, android.graphics.drawable.Drawable, com.google.android.material.internal.i.b
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

    public void z0(d.b.a.a.t.d dVar) {
        this.E.h(dVar, this.C);
    }
}
