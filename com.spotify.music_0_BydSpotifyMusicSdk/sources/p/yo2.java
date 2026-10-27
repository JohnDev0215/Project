package p;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.style.ReplacementSpan;

/* JADX INFO: loaded from: classes.dex */
public final class yo2 extends ReplacementSpan {
    public final uc0 b;
    public final Paint.FontMetricsInt a = new Paint.FontMetricsInt();
    public float c = 1.0f;

    public yo2(uc0 uc0Var) {
        if (uc0Var == null) {
            throw new NullPointerException("metadata cannot be null");
        }
        this.b = uc0Var;
    }

    @Override // android.text.style.ReplacementSpan
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        Paint.FontMetricsInt fontMetricsInt2 = this.a;
        paint.getFontMetricsInt(fontMetricsInt2);
        float fAbs = Math.abs(fontMetricsInt2.descent - fontMetricsInt2.ascent) * 1.0f;
        uc0 uc0Var = this.b;
        this.c = fAbs / (uc0Var.d().a(14) != 0 ? r8.b.getShort(r1 + r8.a) : (short) 0);
        dk1 dk1VarD = uc0Var.d();
        int iA = dk1VarD.a(14);
        if (iA != 0) {
            dk1VarD.b.getShort(iA + dk1VarD.a);
        }
        short s = (short) ((uc0Var.d().a(12) != 0 ? r5.b.getShort(r7 + r5.a) : (short) 0) * this.c);
        if (fontMetricsInt != null) {
            fontMetricsInt.ascent = fontMetricsInt2.ascent;
            fontMetricsInt.descent = fontMetricsInt2.descent;
            fontMetricsInt.top = fontMetricsInt2.top;
            fontMetricsInt.bottom = fontMetricsInt2.bottom;
        }
        return s;
    }

    @Override // android.text.style.ReplacementSpan
    public final void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        jc0.a().getClass();
        uc0 uc0Var = this.b;
        gh2 gh2Var = uc0Var.b;
        Typeface typeface = (Typeface) gh2Var.m;
        Typeface typeface2 = paint.getTypeface();
        paint.setTypeface(typeface);
        canvas.drawText((char[]) gh2Var.k, uc0Var.a * 2, 2, f, i4, paint);
        paint.setTypeface(typeface2);
    }
}
