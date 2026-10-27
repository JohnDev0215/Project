package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.view.View;
import defpackage.c83;
import defpackage.uj2;

/* JADX INFO: compiled from: TooltipDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public final class kd3 extends sc1 implements c83.b {
    public CharSequence B;
    public final Context C;
    public final Paint.FontMetrics D;
    public final c83 E;
    public final a F;
    public final Rect G;
    public int H;
    public int I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public int f43J;
    public int K;
    public int L;
    public int M;
    public float N;
    public float O;
    public float P;
    public float Q;

    /* JADX INFO: compiled from: TooltipDrawable.java */
    public class a implements View.OnLayoutChangeListener {
        public a() {
        }

        @Override // android.view.View.OnLayoutChangeListener
        public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            kd3 kd3Var = kd3.this;
            kd3Var.getClass();
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            kd3Var.M = iArr[0];
            view.getWindowVisibleDisplayFrame(kd3Var.G);
        }
    }

    public kd3(Context context, int i) {
        super(context, null, 0, i);
        this.D = new Paint.FontMetrics();
        c83 c83Var = new c83(this);
        this.E = c83Var;
        this.F = new a();
        this.G = new Rect();
        this.N = 1.0f;
        this.O = 1.0f;
        this.P = 0.5f;
        this.Q = 1.0f;
        this.C = context;
        TextPaint textPaint = c83Var.a;
        textPaint.density = context.getResources().getDisplayMetrics().density;
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override // c83.b
    public final void a() {
        invalidateSelf();
    }

    @Override // defpackage.sc1, android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        canvas.save();
        float fW = w();
        float f = (float) (-((Math.sqrt(2.0d) * ((double) this.L)) - ((double) this.L)));
        canvas.scale(this.N, this.O, (getBounds().width() * 0.5f) + getBounds().left, (getBounds().height() * this.P) + getBounds().top);
        canvas.translate(fW, f);
        super.draw(canvas);
        if (this.B != null) {
            float fCenterY = getBounds().centerY();
            c83 c83Var = this.E;
            TextPaint textPaint = c83Var.a;
            Paint.FontMetrics fontMetrics = this.D;
            textPaint.getFontMetrics(fontMetrics);
            int i = (int) (fCenterY - ((fontMetrics.descent + fontMetrics.ascent) / 2.0f));
            s73 s73Var = c83Var.f;
            TextPaint textPaint2 = c83Var.a;
            if (s73Var != null) {
                textPaint2.drawableState = getState();
                c83Var.f.e(this.C, textPaint2, c83Var.b);
                textPaint2.setAlpha((int) (this.Q * 255.0f));
            }
            CharSequence charSequence = this.B;
            canvas.drawText(charSequence, 0, charSequence.length(), r0.centerX(), i, textPaint2);
        }
        canvas.restore();
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return (int) Math.max(this.E.a.getTextSize(), this.f43J);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        float f = this.H * 2;
        CharSequence charSequence = this.B;
        return (int) Math.max(f + (charSequence == null ? 0.0f : this.E.a(charSequence.toString())), this.I);
    }

    @Override // defpackage.sc1, android.graphics.drawable.Drawable
    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        uj2 uj2Var = this.e.a;
        uj2Var.getClass();
        uj2.a aVar = new uj2.a(uj2Var);
        aVar.k = x();
        setShapeAppearanceModel(new uj2(aVar));
    }

    @Override // defpackage.sc1, android.graphics.drawable.Drawable, c83.b
    public final boolean onStateChange(int[] iArr) {
        return super.onStateChange(iArr);
    }

    public final float w() {
        int i;
        Rect rect = this.G;
        if (((rect.right - getBounds().right) - this.M) - this.K < 0) {
            i = ((rect.right - getBounds().right) - this.M) - this.K;
        } else {
            if (((rect.left - getBounds().left) - this.M) + this.K <= 0) {
                return 0.0f;
            }
            i = ((rect.left - getBounds().left) - this.M) + this.K;
        }
        return i;
    }

    public final jr1 x() {
        float f = -w();
        float fWidth = ((float) (((double) getBounds().width()) - (Math.sqrt(2.0d) * ((double) this.L)))) / 2.0f;
        return new jr1(new ac1(this.L), Math.min(Math.max(f, -fWidth), fWidth));
    }
}
