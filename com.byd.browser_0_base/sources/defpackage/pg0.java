package defpackage;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

/* JADX INFO: compiled from: EmojiSpan.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class pg0 extends ReplacementSpan {
    public final kh3 f;
    public final Paint.FontMetricsInt e = new Paint.FontMetricsInt();
    public short g = -1;
    public float h = 1.0f;

    public pg0(kh3 kh3Var) {
        if (kh3Var == null) {
            throw new NullPointerException("rasterizer cannot be null");
        }
        this.f = kh3Var;
    }

    @Override // android.text.style.ReplacementSpan
    public final int getSize(Paint paint, @SuppressLint({"UnknownNullness"}) CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        Paint.FontMetricsInt fontMetricsInt2 = this.e;
        paint.getFontMetricsInt(fontMetricsInt2);
        float fAbs = Math.abs(fontMetricsInt2.descent - fontMetricsInt2.ascent) * 1.0f;
        kh3 kh3Var = this.f;
        this.h = fAbs / (kh3Var.c().a(14) != 0 ? r8.b.getShort(r1 + r8.a) : (short) 0);
        mh1 mh1VarC = kh3Var.c();
        int iA = mh1VarC.a(14);
        if (iA != 0) {
            mh1VarC.b.getShort(iA + mh1VarC.a);
        }
        short s = (short) ((kh3Var.c().a(12) != 0 ? r5.b.getShort(r7 + r5.a) : (short) 0) * this.h);
        this.g = s;
        if (fontMetricsInt != null) {
            fontMetricsInt.ascent = fontMetricsInt2.ascent;
            fontMetricsInt.descent = fontMetricsInt2.descent;
            fontMetricsInt.top = fontMetricsInt2.top;
            fontMetricsInt.bottom = fontMetricsInt2.bottom;
        }
        return s;
    }
}
